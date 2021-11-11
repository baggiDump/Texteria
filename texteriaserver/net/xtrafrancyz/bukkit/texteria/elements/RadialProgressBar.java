package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class RadialProgressBar extends Element<RadialProgressBar> {
   public int size;
   public float progress;

   public RadialProgressBar(String id, int size, float progress) {
      super(id);
      this.size = size;
      this.progress = progress;
   }

   public void write(ByteMap map) {
      super.write(map);
      map.put("size", Integer.valueOf(this.size));
      if(this.progress != -99.0F) {
         map.put("progress", Float.valueOf(this.progress));
      }

   }

   protected String getType() {
      return "RadialProgressBar";
   }
}
