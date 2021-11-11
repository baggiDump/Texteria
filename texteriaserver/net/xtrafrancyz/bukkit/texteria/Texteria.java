package net.xtrafrancyz.bukkit.texteria;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.server.v1_6_R3.Packet250CustomPayload;
import net.minecraft.server.v1_6_R3.PlayerConnection;
import net.xtrafrancyz.bukkit.texteria.Commands;
import net.xtrafrancyz.bukkit.texteria.Texteria2D;
import net.xtrafrancyz.bukkit.texteria.Texteria3D;
import net.xtrafrancyz.bukkit.texteria.TexteriaCallbackEvent;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_6_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Texteria extends JavaPlugin implements Listener, PluginMessageListener {
   private static Map<Player, LinkedList<byte[]>> buffer = new HashMap(128);

   public void onEnable() {
      this.getServer().getMessenger().registerOutgoingPluginChannel(this, "Texteria");
      this.getServer().getMessenger().registerIncomingPluginChannel(this, "Texteria", this);
      this.getServer().getPluginCommand("texteria").setExecutor(new Commands());
      this.getServer().getPluginManager().registerEvents(this, this);
      Player[] players = Bukkit.getOnlinePlayers();
      Texteria2D.removeAll(players);
      Texteria3D.removeAllGroups(players);
      Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
         if(!buffer.isEmpty()) {
            Map<Player, LinkedList<byte[]>> buf = buffer;
            buffer = new HashMap(128);

            for(Entry<Player, LinkedList<byte[]>> entry : buf.entrySet()) {
               int size = 2;

               for(byte[] arr : (LinkedList)entry.getValue()) {
                  size += 4 + arr.length;
               }

               ByteArrayOutputStream baos = new ByteArrayOutputStream(size);
               DataOutputStream dos = new DataOutputStream(baos);

               try {
                  dos.writeShort(((LinkedList)entry.getValue()).size());

                  for(byte[] arr : (LinkedList)entry.getValue()) {
                     dos.writeInt(arr.length);
                     dos.write(arr);
                  }

                  dos.flush();
               } catch (IOException var8) {
                  ;
               }

               PlayerConnection conn = ((CraftPlayer)entry.getKey()).getHandle().playerConnection;
               if(conn != null) {
                  conn.sendPacket(new Packet250CustomPayload("Texteria", baos.toByteArray()));
               }
            }

            buf.clear();
         }
      }, 1L, 1L);
   }

   @EventHandler(
      priority = EventPriority.LOW
   )
   private void onPlayerJoin(PlayerJoinEvent event) {
      ((CraftPlayer)event.getPlayer()).addChannel("Texteria");
      Texteria2D.removeAll(new Player[]{event.getPlayer()});
      Texteria3D.removeAllGroups(new Player[]{event.getPlayer()});
   }

   public void onPluginMessageReceived(String channel, Player player, byte[] data) {
      if(channel.equals("Texteria")) {
         ByteMap map = new ByteMap(data);
         String var5 = map.getString("%", "");
         byte var6 = -1;
         switch(var5.hashCode()) {
         case -172220347:
            if(var5.equals("callback")) {
               var6 = 0;
            }
         default:
            switch(var6) {
            case 0:
               Bukkit.getPluginManager().callEvent(new TexteriaCallbackEvent(player, map.getMap("data")));
            }
         }
      }

   }

   public static void openUrl(String url, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "url");
      map.put("url", url);
      sendData(map.toByteArray(), players);
   }

   static void sendData(byte[] bytes, Player... players) {
      for(Player player : players) {
         LinkedList<byte[]> list = (LinkedList)buffer.get(player);
         if(list == null) {
            buffer.put(player, list = new LinkedList());
         }

         list.addLast(bytes);
      }

   }
}
