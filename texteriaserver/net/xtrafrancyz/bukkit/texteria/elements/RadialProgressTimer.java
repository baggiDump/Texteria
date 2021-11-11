package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.RadialProgressBar;

public class RadialProgressTimer extends RadialProgressBar {
   public RadialProgressTimer(String id, int size) {
      super(id, size, -99.0F);
   }

   protected String getType() {
      return "RadialProgressTimer";
   }
}
