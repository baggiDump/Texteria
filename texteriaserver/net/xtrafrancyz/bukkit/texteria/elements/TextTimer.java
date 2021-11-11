package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Text;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class TextTimer extends Text {
   public long millis = -1L;

   public TextTimer(String id, String... text) {
      super(id, text);
   }

   public TextTimer setTimerDuration(long millis) {
      this.millis = millis;
      return this;
   }

   public void write(ByteMap map) {
      super.write(map);
      if(this.millis != -1L) {
         map.put("millis", Long.valueOf(this.millis));
      }

   }

   protected String getType() {
      return "TextTimer";
   }
}
