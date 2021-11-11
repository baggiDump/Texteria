package net.xtrafrancyz.VimeNetwork.api;

import java.util.function.Supplier;
import net.xtrafrancyz.VimeNetwork.VNPlugin;
import net.xtrafrancyz.bukkit.texteria.Texteria2D;
import net.xtrafrancyz.bukkit.texteria.elements.Image;
import net.xtrafrancyz.bukkit.texteria.elements.Rectangle;
import net.xtrafrancyz.bukkit.texteria.elements.Text;
import net.xtrafrancyz.bukkit.texteria.utils.Animation2D;
import net.xtrafrancyz.bukkit.texteria.utils.OnClick;
import net.xtrafrancyz.bukkit.texteria.utils.Position;
import net.xtrafrancyz.bukkit.texteria.utils.Animation2D.Params;
import net.xtrafrancyz.bukkit.texteria.utils.OnClick.Action;
import net.xtrafrancyz.bukkit.texteria.utils.Visibility.Always;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VimeTexteria {
   private static final String T_GROUP = "vn.api.";
   private static final String T_TOPMSG = "vn.api.topmsg";
   private static final String T_VICTORY = "vn.api.victory";
   private static final String T_DEFEAT = "vn.api.defeat";
   private static final String T_TIE = "vn.api.tie";
   static final VimeTexteria inst = new VimeTexteria();

   public void showInsufficientlyCoins(Player player) {
      Texteria2D.add(this.genInvErrorMessage(5000L, new String[]{"&cУ вас недостаточно коинов", "Их можно заработать, либо же купить в ЛК", "&ohttp://cp.vimeworld.ru"}).setOnClick(new OnClick(Action.URL, "http://cp.vimeworld.ru/exchange")), new Player[]{player});
   }

   public void showInvErrorMessage(Player player, long duration, String... message) {
      Texteria2D.add(this.genInvErrorMessage(duration, message), new Player[]{player});
   }

   private Text genInvErrorMessage(long duration, String... message) {
      return (Text)((Text)((Text)((Text)((Text)(new Text("vn.api.topmsg", message)).setPosition(Position.TOP)).setOffset(0, 30)).setDuration(duration)).setVisibility(new Always())).setScale(2.0F);
   }

   public void showBasicMessage(Player player, long duration, int color, String... message) {
      Texteria2D.add(((Text)((Text)((Text)((Text)(new Text("vn.api.topmsg", message)).setPosition(Position.TOP)).setOffset(0, 30)).setDuration(duration)).setColor(color)).setScale(2.0F), new Player[]{player});
   }

   public void showVictory(Player... players) {
      Texteria2D.add(((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Image("vn.api.victory", -1, "file:texteria/victory.png")).setPosition(Position.TOP)).setOffset(0, 10)).setFade(1500)).setScale(0.5F)).setDuration(7000L), players);
   }

   public void showDefeat(Player... players) {
      Texteria2D.add(((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Image("vn.api.defeat", -1, "file:texteria/defeat.png")).setPosition(Position.TOP)).setOffset(0, 10)).setFade(1500)).setScale(0.5F)).setDuration(7000L), players);
   }

   public void showTie(Player... players) {
      Texteria2D.add(((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Image("vn.api.tie", -1, "file:texteria/tie.png")).setPosition(Position.TOP)).setOffset(0, 10)).setFade(1500)).setScale(0.5F)).setDuration(7000L), players);
   }

   public void showCountdown(int seconds, String message, Supplier<Player[]> players) {
      for(int i = 0; i <= seconds; ++i) {
         Bukkit.getScheduler().scheduleSyncDelayedTask(VNPlugin.instance(), () -> {
            int color = -1;
            switch(seconds - i) {
            case 0:
               color = -10167017;
               break;
            case 1:
               color = -35840;
               break;
            case 2:
               color = -20992;
               break;
            case 3:
               color = -11008;
            }

            Text text = (Text)((Text)((Text)((Text)((Text)((Text)(new Text((String)null, new String[]{String.valueOf(seconds - i)})).setScale(6.0F)).setAnimation((new Animation2D()).setFinish((new Params()).setScale(25.0F)).setStart((new Params()).setScale(-6.0F)))).setColor(color)).setFadeStart(400)).setFadeFinish(500)).setDuration(1000L);
            if(seconds == i) {
               ((Text)text.setDuration(1500L)).setText(new String[]{message});
            }

            Texteria2D.add(text, (Player[])players.get());
         }, (long)(i * 20));
      }

   }
}
