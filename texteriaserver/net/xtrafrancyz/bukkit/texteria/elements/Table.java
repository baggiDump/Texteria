package net.xtrafrancyz.bukkit.texteria.elements;

import java.util.LinkedList;
import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import org.bukkit.ChatColor;

public class Table extends Element<Table> {
   public LinkedList<Table.Column> columns = new LinkedList();
   public LinkedList<String[]> rows = new LinkedList();
   public int titleColor = -769226;
   public String title = null;
   public int headingColor = -5317;
   public boolean drawBack = false;
   public int maxRows = -1;
   public int scrollbarColor = -5317;
   public int rowHoverColor = 1140946643;

   public Table(String id) {
      super(id);
   }

   public Table addColumn(Table.Column column) {
      this.columns.add(column);
      return this;
   }

   public Table addRow(String... row) {
      for(int i = 0; i < row.length; ++i) {
         row[i] = ChatColor.translateAlternateColorCodes('&', row[i]);
      }

      this.rows.add(row);
      return this;
   }

   public Table setTitle(String title) {
      this.title = ChatColor.translateAlternateColorCodes('&', title);
      return this;
   }

   public Table setTitleColor(int color) {
      this.titleColor = color;
      return this;
   }

   public Table setHeadingColor(int color) {
      this.headingColor = color;
      return this;
   }

   public Table setDrawBack(boolean drawBack) {
      this.drawBack = drawBack;
      return this;
   }

   public Table setMaxRows(int maxRows) {
      this.maxRows = maxRows;
      return this;
   }

   public Table setScrollbarColor(int color) {
      this.scrollbarColor = color;
      return this;
   }

   public Table setRowHoverColor(int color) {
      this.rowHoverColor = color;
      return this;
   }

   public void write(ByteMap map) {
      ByteMap cols = new ByteMap();
      cols.put("size", Integer.valueOf(this.columns.size()));
      int id = 0;

      for(Table.Column column : this.columns) {
         cols.put(id + ".n", column.name);
         cols.put(id + ".w", Integer.valueOf(column.width));
         if(column.center) {
            cols.put(id + ".c", Boolean.valueOf(true));
         }

         if(column.color != -1) {
            cols.put(id + ".t", Integer.valueOf(column.color));
         }

         ++id;
      }

      map.put("cols", cols);
      ByteMap rows = new ByteMap();
      id = 0;

      for(String[] row : this.rows) {
         rows.put(id++ + "", row);
      }

      map.put("rows", rows);
      if(this.title != null) {
         map.put("title", this.title);
      }

      if(this.titleColor != -5317) {
         map.put("title.c", Integer.valueOf(this.titleColor));
      }

      if(this.headingColor != -5317) {
         map.put("heading.c", Integer.valueOf(this.headingColor));
      }

      if(this.drawBack) {
         map.put("drawBack", Boolean.valueOf(true));
      }

      if(this.maxRows != -1) {
         map.put("maxRows", Integer.valueOf(this.maxRows));
      }

      if(this.scrollbarColor != -5317) {
         map.put("sb.c", Integer.valueOf(this.scrollbarColor));
      }

      if(this.rowHoverColor != 1140946643) {
         map.put("rh.c", Integer.valueOf(this.rowHoverColor));
      }

      super.write(map);
   }

   protected String getType() {
      return "Table";
   }

   public static class Column {
      public String name;
      public int width;
      public boolean center = false;
      public int color = -1;

      public Column(String name, int width) {
         this.name = ChatColor.translateAlternateColorCodes('&', name);
         this.width = width;
      }

      public Table.Column setCenter(boolean flag) {
         this.center = flag;
         return this;
      }

      public Table.Column setColor(int color) {
         this.color = color;
         return this;
      }
   }
}
