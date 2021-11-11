package net.xtrafrancyz.bukkit.texteria.utils;

import java.util.LinkedList;
import java.util.List;
import net.xtrafrancyz.bukkit.texteria.Texteria2D;
import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.utils.Visibility;
import org.bukkit.entity.Player;

public class TexteriaBuffer {
   private List<Element> list = new LinkedList();
   private boolean enabled = false;

   public void enable() {
      this.enabled = true;
   }

   public void add(Element com, Player... players) {
      if(this.enabled) {
         this.list.add(com);
      } else {
         Texteria2D.add(com, players);
      }

   }

   public void send(Player... players) {
      this.send((Visibility)null, players);
   }

   public void send(Visibility vis, Player... players) {
      this.enabled = false;
      if(!this.list.isEmpty()) {
         if(this.list.size() == 1) {
            Texteria2D.add(((Element)this.list.get(0)).setVisibility(vis), players);
         } else {
            Texteria2D.add(vis, (Element[])this.list.toArray(new Element[this.list.size()]), players);
         }

         this.list.clear();
      }
   }
}
