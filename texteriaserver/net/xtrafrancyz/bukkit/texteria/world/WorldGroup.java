package net.xtrafrancyz.bukkit.texteria.world;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.Animation3D;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public class WorldGroup {
   public String id;
   public float x = 0.0F;
   public float y = 0.0F;
   public float z = 0.0F;
   public float scaleX = 1.0F;
   public float scaleY = 1.0F;
   public float scaleZ = 1.0F;
   public float angleX = 0.0F;
   public float angleY = 0.0F;
   public float angleZ = 0.0F;
   public long duration = -1L;
   public int fadeStart = 255;
   public int fadeFinish = 255;
   public boolean culling = false;
   public int renderDistance = 64;
   public boolean adjustableAngle = false;
   public boolean centered = false;
   public boolean hoverable = false;
   public int hoverRange = 10;
   public Animation3D animation = new Animation3D();
   public List<Element> elements;

   public WorldGroup(String id) {
      this.id = id;
      this.elements = new LinkedList();
   }

   public void clear() {
      this.elements.clear();
   }

   public void add(Element element) {
      this.elements.add(element);
   }

   public void add(Element... elements) {
      this.elements.addAll(Arrays.asList(elements));
   }

   public void setLocation(float x, float y, float z) {
      this.x = x;
      this.y = y;
      this.z = z;
   }

   public void setScale(float scale) {
      this.setScale(scale, scale, scale);
   }

   public void setScale(float x, float y, float z) {
      this.scaleX = x;
      this.scaleY = y;
      this.scaleZ = z;
   }

   public void setRotation(float x, float y, float z) {
      this.angleX = x;
      this.angleY = y;
      this.angleZ = z;
   }

   public void setDuration(long duration) {
      this.duration = duration;
   }

   public void setFadeStart(int fade) {
      this.fadeStart = fade;
   }

   public void setFadeFinish(int fade) {
      this.fadeFinish = fade;
   }

   public void setFade(int fade) {
      this.fadeFinish = this.fadeStart = fade;
   }

   public void setCulling(boolean flag) {
      this.culling = flag;
   }

   public void setAdjustableAngle(boolean adjustableAngle) {
      this.adjustableAngle = adjustableAngle;
   }

   public void setRenderDistance(int renderDistance) {
      if(renderDistance <= 0) {
         throw new IllegalArgumentException("renderDistance must be greater 0");
      } else {
         this.renderDistance = renderDistance;
      }
   }

   public void setHoverable(boolean hoverable) {
      this.hoverable = hoverable;
   }

   public void setHoverRange(int range) {
      this.hoverRange = range;
   }

   public void setCentered(boolean centered) {
      this.centered = centered;
   }

   public void write(ByteMap map) {
      if(this.id != null) {
         map.put("id", this.id);
      }

      if(this.duration > 0L) {
         map.put("dur", Long.valueOf(this.duration));
      }

      if(this.culling) {
         map.put("culling", Boolean.valueOf(true));
      }

      if(this.adjustableAngle) {
         map.put("adjAngle", Boolean.valueOf(true));
      }

      if(this.renderDistance != 64) {
         map.put("rndrDist", Integer.valueOf(this.renderDistance));
      }

      if(this.centered) {
         map.put("centered", Boolean.valueOf(true));
      }

      if(this.hoverable) {
         map.put("hv", Boolean.valueOf(true));
         if(this.hoverRange != 10) {
            map.put("hr", Integer.valueOf(this.hoverRange));
         }
      }

      if(this.fadeStart != 255 || this.fadeFinish != 255) {
         if(this.fadeStart == this.fadeFinish) {
            map.put("fade", Integer.valueOf(this.fadeStart));
         } else {
            if(this.fadeStart != 255) {
               map.put("fade.s", Integer.valueOf(this.fadeStart));
            }

            if(this.fadeFinish != 255) {
               map.put("fade.f", Integer.valueOf(this.fadeFinish));
            }
         }
      }

      if(this.x != 0.0F) {
         map.put("loc.x", Float.valueOf(this.x));
      }

      if(this.y != 0.0F) {
         map.put("loc.y", Float.valueOf(this.y));
      }

      if(this.z != 0.0F) {
         map.put("loc.z", Float.valueOf(this.z));
      }

      if(this.angleX != 0.0F) {
         map.put("angle.x", Float.valueOf(this.angleX));
      }

      if(this.angleY != 0.0F) {
         map.put("angle.y", Float.valueOf(this.angleY));
      }

      if(this.angleZ != 0.0F) {
         map.put("angle.z", Float.valueOf(this.angleZ));
      }

      if(this.scaleX == this.scaleY && this.scaleX == this.scaleZ && this.scaleX != 1.0F) {
         map.put("scale", Float.valueOf(this.scaleX));
      } else {
         if(this.scaleX != 1.0F) {
            map.put("scale.x", Float.valueOf(this.scaleX));
         }

         if(this.scaleY != 1.0F) {
            map.put("scale.y", Float.valueOf(this.scaleY));
         }

         if(this.scaleZ != 1.0F) {
            map.put("scale.z", Float.valueOf(this.scaleZ));
         }
      }

      ByteMap[] e = new ByteMap[this.elements.size()];

      for(int i = 0; i < e.length; ++i) {
         ((Element)this.elements.get(i)).write(e[i] = new ByteMap());
      }

      map.put("e", e);
      if(this.animation.start != null) {
         map.put("anim.s", this.animation.start.serialize());
      }

      if(this.animation.finish != null) {
         map.put("anim.f", this.animation.finish.serialize());
      }

   }
}
