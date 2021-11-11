package net.xtrafrancyz.bukkit.texteria.utils;

import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;

public interface Visibility {
   void write(ByteMap var1);

   public static class Always implements Visibility {
      public void write(ByteMap map) {
         map.put("type", "always");
      }
   }

   public static class AlwaysNotTab implements Visibility {
      public void write(ByteMap map) {
         map.put("type", "alwaysnottab");
      }
   }

   public static class Ingame implements Visibility {
      public void write(ByteMap map) {
         map.put("type", "ingame");
      }
   }

   public static class IngameNotChat implements Visibility {
      public void write(ByteMap map) {
         map.put("type", "notchat");
      }
   }

   public static class IngameNotF3 implements Visibility {
      public void write(ByteMap map) {
         map.put("type", "notf3");
      }
   }

   public static class IngameNotTab implements Visibility {
      public void write(ByteMap map) {
         map.put("type", "nottab");
      }
   }

   public static class Inventory implements Visibility {
      public String[] titles;

      public Inventory(String... titles) {
         this.titles = titles;
      }

      public void write(ByteMap map) {
         map.put("type", "inventory");
         map.put("title", this.titles);
      }
   }

   public static class Screen implements Visibility {
      public String[] screens;

      public Screen(String... screens) {
         this.screens = screens;
      }

      public void write(ByteMap map) {
         map.put("type", "screen");
         map.put("class", this.screens);
      }
   }
}
