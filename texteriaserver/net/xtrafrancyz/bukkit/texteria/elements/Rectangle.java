package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class Rectangle extends Element<Rectangle> {
   public int width;
   public int height;

   public Rectangle(String id, int size) {
      this(id, size, size);
   }

   public Rectangle(String id, int width, int height) {
      super(id);
      this.width = width;
      this.height = height;
   }

   public Rectangle setSize(int size) {
      this.width = size;
      this.height = size;
      return this;
   }

   public void write(ByteMap map) {
      super.write(map);
      if(this.width == this.height) {
         map.put("size", Integer.valueOf(this.width));
      } else {
         map.put("width", Integer.valueOf(this.width));
         map.put("height", Integer.valueOf(this.height));
      }

   }

   protected String getType() {
      return "Rectangle";
   }
}
