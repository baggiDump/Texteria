package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Text;

public class TextStopwatch extends Text {
   public TextStopwatch(String id, String... lines) {
      super(id, lines);
   }

   protected String getType() {
      return "TextStopwatch";
   }
}
