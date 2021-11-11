package net.xtrafrancyz.bukkit.texteria.elements;

import net.xtrafrancyz.bukkit.texteria.utils.Animation2D;
import net.xtrafrancyz.bukkit.texteria.utils.Attachment;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import net.xtrafrancyz.bukkit.texteria.utils.OnClick;
import net.xtrafrancyz.bukkit.texteria.utils.Position;
import net.xtrafrancyz.bukkit.texteria.utils.Visibility;

public abstract class Element<T extends Element> {
   public String id;
   public int color = -1;
   public long duration = -1L;
   public Position pos = Position.CENTER;
   public Attachment attach = null;
   public Visibility visibility = null;
   public Animation2D anim = null;
   public float scaleX = 1.0F;
   public float scaleY = 1.0F;
   public int x = 0;
   public int y = 0;
   public float rotation = 0.0F;
   public int delay = 0;
   public int fadeStart = 255;
   public int fadeFinish = 255;
   public OnClick click;
   public boolean hoverable = false;

   protected Element(String id) {
      this.id = id;
   }

   public T setScale(float scale) {
      return this.setScale(scale, scale);
   }

   public T setScale(float scaleX, float scaleY) {
      this.scaleX = scaleX;
      this.scaleY = scaleY;
      return (T)this;
   }

   public T setColor(int color) {
      this.color = color;
      return (T)this;
   }

   public T setPosition(Position pos) {
      this.pos = pos;
      return (T)this;
   }

   public T setOffset(int x, int y) {
      this.x = x;
      this.y = y;
      return (T)this;
   }

   public T setDuration(long duration) {
      this.duration = duration;
      return (T)this;
   }

   public T setDelay(int delay) {
      this.delay = delay;
      return (T)this;
   }

   public T setFadeStart(int fade) {
      this.fadeStart = fade;
      return (T)this;
   }

   public T setFadeFinish(int fade) {
      this.fadeFinish = fade;
      return (T)this;
   }

   public T setFade(int fade) {
      this.fadeFinish = this.fadeStart = fade;
      return (T)this;
   }

   public T setRotation(float angle) {
      this.rotation = angle;
      return (T)this;
   }

   public T setHoverable(boolean hoverable) {
      this.hoverable = hoverable;
      return (T)this;
   }

   public T setOnClick(OnClick click) {
      this.hoverable = true;
      this.click = click;
      return (T)this;
   }

   public T setVisibility(Visibility visibility) {
      this.visibility = visibility;
      return (T)this;
   }

   public T setAttachment(Attachment attach) {
      this.attach = attach;
      return (T)this;
   }

   public T setAnimation(Animation2D anim) {
      this.anim = anim;
      return (T)this;
   }

   public void write(ByteMap map) {
      map.put("type", this.getType());
      if(this.id != null) {
         map.put("id", this.id);
      }

      if(this.x != 0) {
         map.put("x", Integer.valueOf(this.x));
      }

      if(this.y != 0) {
         map.put("y", Integer.valueOf(this.y));
      }

      if(this.duration > 0L) {
         map.put("dur", Long.valueOf(this.duration));
      }

      if(this.delay != 0) {
         map.put("delay", Integer.valueOf(this.delay));
      }

      if(this.color != -1) {
         map.put("color", Integer.valueOf(this.color));
      }

      if(this.rotation != 0.0F) {
         map.put("rot", Float.valueOf(this.rotation));
      }

      if(this.hoverable && this.click == null) {
         map.put("hv", Boolean.valueOf(true));
      }

      if(this.scaleX != 1.0F || this.scaleY != 1.0F) {
         if(this.scaleX == this.scaleY) {
            map.put("scale", Float.valueOf(this.scaleX));
         } else {
            if(this.scaleX != 1.0F) {
               map.put("scale.x", Float.valueOf(this.scaleX));
            }

            if(this.scaleY != 1.0F) {
               map.put("scale.y", Float.valueOf(this.scaleY));
            }
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

      if(this.click != null) {
         ByteMap c = new ByteMap();
         c.put("act", this.click.action.name());
         c.put("data", this.click.data);
         map.put("click", c);
      }

      if(this.visibility != null) {
         ByteMap vis = new ByteMap();
         this.visibility.write(vis);
         map.put("vis", vis);
      }

      if(this.attach != null) {
         map.put("attach.to", this.attach.attachTo);
         map.put("attach.loc", this.attach.attachLocation.name());
         if(this.attach.attachLocation != this.attach.orientation) {
            map.put("attach.orient", this.attach.orientation.name());
         }

         if(!this.attach.removeWhenParentRemove) {
            map.put("attach.rwpr", Boolean.valueOf(false));
         }
      } else if(this.pos != Position.CENTER) {
         map.put("pos", this.pos.name());
      }

      if(this.anim != null) {
         if(this.anim.start != null) {
            map.put("anim.s", this.anim.start.serialize());
         }

         if(this.anim.finish != null) {
            map.put("anim.f", this.anim.finish.serialize());
         }
      }

   }

   protected abstract String getType();
}
