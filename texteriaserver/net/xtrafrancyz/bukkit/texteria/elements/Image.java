package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.elements.Rectangle;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class Image extends Rectangle {
   public String image;
   public byte[] data;

   public Image(String id, int size, String image) {
      this(id, size, size, image);
   }

   public Image(String id, int width, int height, String image) {
      super(id, width, height);
      this.data = null;
      this.image = image;
   }

   public Image setData(byte[] data) {
      this.data = data;
      return this;
   }

   public void write(ByteMap map) {
      super.write(map);
      map.put("image", this.image);
      if(this.data != null) {
         map.put("idata", this.data);
      }

   }

   protected String getType() {
      return "Image";
   }
}
