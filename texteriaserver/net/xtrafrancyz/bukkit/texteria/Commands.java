package net.xtrafrancyz.bukkit.texteria;

import net.xtrafrancyz.bukkit.texteria.Texteria2D;
import net.xtrafrancyz.bukkit.texteria.Texteria3D;
import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.elements.ProgressTimer;
import net.xtrafrancyz.bukkit.texteria.elements.RadialProgressTimer;
import net.xtrafrancyz.bukkit.texteria.elements.Rectangle;
import net.xtrafrancyz.bukkit.texteria.elements.Table;
import net.xtrafrancyz.bukkit.texteria.elements.Text;
import net.xtrafrancyz.bukkit.texteria.elements.Vignette;
import net.xtrafrancyz.bukkit.texteria.utils.Animation2D;
import net.xtrafrancyz.bukkit.texteria.utils.Animation3D;
import net.xtrafrancyz.bukkit.texteria.utils.Visibility;
import net.xtrafrancyz.bukkit.texteria.world.WorldGroup;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
   public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
      if(args.length == 0) {
         sender.sendMessage("/texteria group|text|rect|ptimer|rtimer|vig|vis|anim|3d");
         return true;
      } else if(args[0].equals("group")) {
         Element[] cs = new Element[]{((Text)(new Text("test.text", new String[]{"eeeeeeeeee"})).setOffset(0, 30)).setDuration(3000L), (new Rectangle("test.rect", 20)).setDuration(3000L)};
         Texteria2D.add(cs, new Player[]{(Player)sender});
         sender.sendMessage("Пакет отправлен");
         return true;
      } else {
         Element elem = null;
         String var6 = args[0];
         byte var7 = -1;
         switch(var6.hashCode()) {
         case -978137899:
            if(var6.equals("ptimer")) {
               var7 = 2;
            }
            break;
         case -920879597:
            if(var6.equals("rtimer")) {
               var7 = 3;
            }
            break;
         case 1681:
            if(var6.equals("3d")) {
               var7 = 7;
            }
            break;
         case 116756:
            if(var6.equals("vig")) {
               var7 = 4;
            }
            break;
         case 116768:
            if(var6.equals("vis")) {
               var7 = 5;
            }
            break;
         case 2998801:
            if(var6.equals("anim")) {
               var7 = 6;
            }
            break;
         case 3496420:
            if(var6.equals("rect")) {
               var7 = 1;
            }
            break;
         case 3556653:
            if(var6.equals("text")) {
               var7 = 0;
            }
         }

         switch(var7) {
         case 0:
            elem = new Text("test", new String[]{"Test text"});
            break;
         case 1:
            elem = new Rectangle("test", 50, 100);
            break;
         case 2:
            elem = (new ProgressTimer("test", 100, 50)).setBarColor(-65536);
            break;
         case 3:
            elem = new RadialProgressTimer("test", 40);
            break;
         case 4:
            elem = (new Vignette("test")).setColor(-16737025);
            break;
         case 5:
            elem = ((Text)(new Text("test", new String[]{"Видно всегда"})).setScale(2.0F)).setVisibility(new Visibility.Always());
            break;
         case 6:
            elem = ((Text)((Text)(new Text("test", new String[]{"Анимация"})).setAnimation((new Animation2D()).setStart((new Animation2D.Params()).setY(10).setRotation(360.0F)).setFinish((new Animation2D.Params()).setScale(20.0F)))).setFadeStart(1000)).setFadeFinish(500);
            break;
         case 7:
            WorldGroup group = new WorldGroup("test");
            group.setDuration(10000L);
            group.setFade(500);
            group.setScale(8.0F);
            group.setLocation(-9.0F, 60.0F, -25.0F);
            group.animation.setBoth((new Animation3D.Params()).setOffset(8.0F, -5.0F, 0.0F).setScale(-8.0F).setRotation(90.0F, 0.0F, 0.0F));
            group.add((Element)(new Table("test")).setTitle("Таблица рекордов").addColumn((new Table.Column("#", 15)).setCenter(true)).addColumn(new Table.Column("Ник игрока", 80)).addColumn((new Table.Column("Время", 62)).setColor(-7617718)).addRow(new String[]{"1", "xtrafrancyz", "43 м. 13.3 с."}).addRow(new String[]{"2", "SmaIK", "44 м. 59.7 с."}).addRow(new String[]{"3", "Lucy", "82 м. 21.0 с."}).addRow(new String[]{"4", "Test1", "82 м. 21.0 с."}).addRow(new String[]{"5", "Test1", "82 м. 21.0 с."}).addRow(new String[]{"6", "Test1", "82 м. 21.0 с."}).addRow(new String[]{"7", "Test1", "82 м. 21.0 с."}));
            Texteria3D.addGroup(group, new Player[]{(Player)sender});
            sender.sendMessage("Пакет отправлен");
            return true;
         }

         if(elem == null) {
            sender.sendMessage(ChatColor.RED + "Ты чего написал, а?");
            return true;
         } else {
            Texteria2D.add(elem.setDuration(3000L), new Player[]{(Player)sender});
            sender.sendMessage("Пакет отправлен");
            return true;
         }
      }
   }
}
