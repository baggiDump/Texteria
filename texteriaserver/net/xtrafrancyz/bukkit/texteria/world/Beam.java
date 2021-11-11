package net.xtrafrancyz.bukkit.texteria.world;

import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import net.xtrafrancyz.bukkit.texteria.world.WorldGroup;

public class Beam extends WorldGroup {
   public int color;

   public Beam(String id, int color) {
      super(id);
      this.color = color;
   }

   public void write(ByteMap map) {
      super.write(map);
      map.put("type", "beam");
      map.put("color", Integer.valueOf(this.color));
      map.remove("e");
   }
}
