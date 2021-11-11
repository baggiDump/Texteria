package net.xtrafrancyz.bukkit.texteria.utils;

import net.xtrafrancyz.bukkit.texteria.utils.Position;

public class Attachment {
   public String attachTo;
   public Position attachLocation;
   public Position orientation;
   public boolean removeWhenParentRemove = true;

   public Attachment(String attachTo, Position attachLocation) {
      this.attachTo = attachTo;
      this.attachLocation = attachLocation;
      this.orientation = attachLocation;
   }

   public Attachment setOrientation(Position orientation) {
      this.orientation = orientation;
      return this;
   }

   public Attachment setRemoveWhenParentRemove(boolean removeWhenParentRemove) {
      this.removeWhenParentRemove = removeWhenParentRemove;
      return this;
   }
}
