package net.xtrafrancyz.bukkit.texteria;

import net.xtrafrancyz.bukkit.texteria.Texteria;
import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import net.xtrafrancyz.bukkit.texteria.utils.Visibility;
import org.bukkit.entity.Player;

public class Texteria2D {
   private static byte[] removeAllPacket;

   public static void add(Element element, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "add");
      element.write(map);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void add(Element[] elements, Player... players) {
      add((Visibility)null, elements, players);
   }

   public static void add(Visibility vis, Element[] elements, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "add:group");
      ByteMap[] e = new ByteMap[elements.length];

      for(int i = 0; i < elements.length; ++i) {
         elements[i].visibility = null;
         elements[i].write(e[i] = new ByteMap());
      }

      map.put("e", e);
      if(vis != null) {
         ByteMap vism = new ByteMap();
         vis.write(vism);
         map.put("vis", vism);
      }

      Texteria.sendData(map.toByteArray(), players);
   }

   public static void edit(String id, ByteMap data, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "edit");
      map.put("id", id);
      map.put("data", data);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void remove(String id, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "rm:id");
      map.put("id", id);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void removeGroup(String group, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "rm:group");
      map.put("group", group);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void removeAll(Player... players) {
      Texteria.sendData(removeAllPacket, players);
   }

   static {
      ByteMap map = new ByteMap();
      map.put("%", "rm:all");
      removeAllPacket = map.toByteArray();
   }
}
