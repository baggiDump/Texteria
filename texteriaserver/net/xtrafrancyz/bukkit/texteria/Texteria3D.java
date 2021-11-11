package net.xtrafrancyz.bukkit.texteria;

import net.xtrafrancyz.bukkit.texteria.Texteria;
import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import net.xtrafrancyz.bukkit.texteria.world.WorldGroup;
import org.bukkit.entity.Player;

public class Texteria3D {
   private static byte[] removeAllPacket;

   public static void addGroup(WorldGroup group, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "3d:add");
      group.write(map);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void addToGroup(String group, Element element, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "3d:add:to");
      map.put("group", group);
      ByteMap els = new ByteMap();
      element.write(els);
      map.put("e", els);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void editGroup(String group, ByteMap data, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "3d:edit");
      map.put("group", group);
      map.put("data", data);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void editElementInGroup(String group, String elem, ByteMap data, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "3d:edit:in");
      map.put("group", group);
      map.put("id", elem);
      map.put("data", data);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void removeGroup(String group, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "3d:rm");
      map.put("group", group);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void removeFromGroup(String group, String element, Player... players) {
      ByteMap map = new ByteMap();
      map.put("%", "3d:rm:from");
      map.put("group", group);
      map.put("id", element);
      Texteria.sendData(map.toByteArray(), players);
   }

   public static void removeAllGroups(Player... players) {
      Texteria.sendData(removeAllPacket, players);
   }

   static {
      ByteMap map = new ByteMap();
      map.put("%", "3d:rm:all");
      removeAllPacket = map.toByteArray();
   }
}
