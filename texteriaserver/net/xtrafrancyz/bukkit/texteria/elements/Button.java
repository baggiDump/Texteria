package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class Button extends Element<Button> {
   public int width;
   public int height;
   public int hoverColor = -1;
   public String text;
   public int textColor = -1;

   public Button(String id, int width, int height, String text) {
      super(id);
      this.width = width;
      this.height = height;
      this.text = text;
      this.hoverable = true;
   }

   public Button setHoverColor(int color) {
      this.hoverColor = color;
      return this;
   }

   public Button setTextColor(int color) {
      this.textColor = color;
      return this;
   }

   public void write(ByteMap map) {
      super.write(map);
      map.put("w", Integer.valueOf(this.width));
      map.put("h", Integer.valueOf(this.height));
      map.put("t", this.text);
      if(this.hoverColor != -1) {
         map.put("hc", Integer.valueOf(this.hoverColor));
      }

      if(this.textColor != -1) {
         map.put("tc", Integer.valueOf(this.textColor));
      }

      if(!this.hoverable) {
         map.put("hv", Boolean.valueOf(false));
      } else {
         map.remove("hv");
      }

   }

   protected String getType() {
      return "Button";
   }
}
