package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.ProgressBar;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class ProgressTimer extends ProgressBar {
   public boolean reverse = false;

   public ProgressTimer(String id, int width, int height) {
      super(id, width, height, -99.0F);
   }

   public ProgressTimer setReverse(boolean flag) {
      this.reverse = flag;
      return this;
   }

   public void write(ByteMap map) {
      super.write(map);
      if(this.reverse) {
         map.put("reverse", Boolean.valueOf(true));
      }

   }

   protected String getType() {
      return "ProgressTimer";
   }
}
