package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Rectangle;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class ProgressBar extends Rectangle {
   public float progress;
   public int barColor;
   public int borderColor;

   public ProgressBar(String id, int width, int height, float progress) {
      super(id, width, height);
      this.progress = progress;
      this.barColor = -1;
      this.borderColor = -1;
   }

   public ProgressBar setBarColor(int color) {
      this.barColor = color;
      return this;
   }

   public ProgressBar setBorderColor(int color) {
      this.borderColor = color;
      return this;
   }

   public void write(ByteMap map) {
      super.write(map);
      map.put("barColor", Integer.valueOf(this.barColor));
      if(this.progress != -99.0F) {
         map.put("progress", Float.valueOf(this.progress));
      }

      if(this.borderColor != -1) {
         map.put("border", Integer.valueOf(this.borderColor));
      }

   }

   protected String getType() {
      return "ProgressBar";
   }
}
