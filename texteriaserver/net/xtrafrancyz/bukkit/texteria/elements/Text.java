package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import org.bukkit.ChatColor;

public class Text extends Element<Text> {
   public static final int LEFT = 1;
   public static final int CENTER = 2;
   public static final int RIGHT = 3;
   public String[] text = new String[0];
   public int orientation = 2;
   public int width = -1;
   public boolean shadow = true;
   public int background = -1;

   public Text(String id, String... lines) {
      super(id);
      this.setText(lines);
   }

   public Text setOrientation(int orientation) {
      this.orientation = orientation;
      return this;
   }

   public Text setShadow(boolean shadow) {
      this.shadow = shadow;
      return this;
   }

   public Text setWidth(int width) {
      this.width = width;
      return this;
   }

   public Text setText(String... lines) {
      this.text = new String[lines.length];

      for(int i = 0; i < lines.length; ++i) {
         this.text[i] = ChatColor.translateAlternateColorCodes('&', lines[i]);
      }

      return this;
   }

   public Text setBackground(int background) {
      this.background = background;
      return this;
   }

   public void write(ByteMap map) {
      super.write(map);
      map.put("text", this.text);
      if(this.width != -1) {
         map.put("width", Integer.valueOf(this.width));
      }

      if(this.orientation != 2) {
         map.put("or", Integer.valueOf(this.orientation));
      }

      if(!this.shadow) {
         map.put("shadow", Boolean.valueOf(false));
      }

      if(this.background != -1) {
         map.put("bg", Integer.valueOf(this.background));
      }

   }

   protected String getType() {
      return "Text";
   }
}
