package net.xtrafrancyz.bukkit.texteria.utils;

import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class Animation2D {
   public Animation2D.Params start = null;
   public Animation2D.Params finish = null;

   public Animation2D setStart(Animation2D.Params params) {
      this.start = params;
      return this;
   }

   public Animation2D setFinish(Animation2D.Params params) {
      this.finish = params;
      return this;
   }

   public Animation2D setBoth(Animation2D.Params params) {
      this.finish = this.start = params;
      return this;
   }

   public static class Params {
      public int x = 0;
      public int y = 0;
      public float scaleX = 0.0F;
      public float scaleY = 0.0F;
      public float rotation = 0.0F;

      public Animation2D.Params setX(int x) {
         this.x = x;
         return this;
      }

      public Animation2D.Params setY(int y) {
         this.y = y;
         return this;
      }

      public Animation2D.Params setScale(float scale) {
         this.scaleX = this.scaleY = scale;
         return this;
      }

      public Animation2D.Params setScaleX(float scale) {
         this.scaleX = scale;
         return this;
      }

      public Animation2D.Params setScaleY(float scale) {
         this.scaleY = scale;
         return this;
      }

      public Animation2D.Params setRotation(float angle) {
         this.rotation = 360.0F;
         return this;
      }

      public ByteMap serialize() {
         ByteMap map = new ByteMap();
         if(this.x != 0) {
            map.put("x", Integer.valueOf(this.x));
         }

         if(this.y != 0) {
            map.put("y", Integer.valueOf(this.y));
         }

         if(this.scaleX != 0.0F || this.scaleY != 0.0F) {
            if(this.scaleX == this.scaleY) {
               map.put("scale", Float.valueOf(this.scaleX));
            } else {
               if(this.scaleX != 0.0F) {
                  map.put("scale.x", Float.valueOf(this.scaleX));
               }

               if(this.scaleY != 0.0F) {
                  map.put("scale.y", Float.valueOf(this.scaleY));
               }
            }
         }

         if(this.rotation != 0.0F) {
            map.put("rot", Float.valueOf(this.rotation));
         }

         return map;
      }
   }
}
