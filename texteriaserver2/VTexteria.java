package net.xtrafrancyz.VimeNetwork;

import java.util.List;
import net.xtrafrancyz.VimeNetwork.VNPlugin;
import net.xtrafrancyz.VimeNetwork.api.VimeNetwork;
import net.xtrafrancyz.VimeNetwork.api.player.NetworkPlayer;
import net.xtrafrancyz.VimeNetwork.api.player.achievement.Achievement;
import net.xtrafrancyz.VimeNetwork.api.player.goals.Goal;
import net.xtrafrancyz.VimeNetwork.impl.StreamMenu;
import net.xtrafrancyz.VimeNetwork.impl.player.VPlayer;
import net.xtrafrancyz.bukkit.texteria.Texteria2D;
import net.xtrafrancyz.bukkit.texteria.elements.Button;
import net.xtrafrancyz.bukkit.texteria.elements.Element;
import net.xtrafrancyz.bukkit.texteria.elements.Rectangle;
import net.xtrafrancyz.bukkit.texteria.elements.Text;
import net.xtrafrancyz.bukkit.texteria.elements.TextTimer;
import net.xtrafrancyz.bukkit.texteria.utils.Animation2D;
import net.xtrafrancyz.bukkit.texteria.utils.Attachment;
import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import net.xtrafrancyz.bukkit.texteria.utils.IntColor;
import net.xtrafrancyz.bukkit.texteria.utils.OnClick;
import net.xtrafrancyz.bukkit.texteria.utils.Position;
import net.xtrafrancyz.bukkit.texteria.utils.Animation2D.Params;
import net.xtrafrancyz.bukkit.texteria.utils.OnClick.Action;
import net.xtrafrancyz.bukkit.texteria.utils.Visibility.Always;
import net.xtrafrancyz.bukkit.texteria.utils.Visibility.IngameNotF3;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class VTexteria {
   private static final String T_COINS = "vn.c.";
   private static final String T_COINS_X = "vn.c.x";
   private static final String T_COINS_TEXT = "vn.c.text";
   private static final String T_COINS_CHANGE = "vn.c.change";
   public static final String T_NOTIFICATION = "vn.n.";
   private static final String T_NOTIFICATION_BG = "vn.n.bg";
   private static final String T_NOTIFICATION_TITLE = "vn.n.title";
   private static final String T_NOTIFICATION_TEXT = "vn.n.text";
   private static final String T_NOTIFICATION_TEXT2 = "vn.n.text2";
   private static final Animation2D ANIM_GIVE_EXP = (new Animation2D()).setFinish((new Params()).setY(-30));
   private static final Animation2D ANIM_ADD_COINS = (new Animation2D()).setFinish((new Params()).setY(30));
   private static final Animation2D ANIM_NOTIFICATION = (new Animation2D()).setBoth((new Params()).setX(-150));
   private static final Text TC_SERVER_ID = (Text)((Text)((Text)(new Text("vn.s", new String[]{VimeNetwork.lobby().getServerId()})).setPosition(Position.TOP_LEFT)).setOffset(2, 12)).setVisibility(new IngameNotF3());
   private static final ByteMap CALLBACK_OPEN_GOALS = new ByteMap();
   private static final ByteMap CALLBACK_OPEN_STREAMS = new ByteMap();

   public static void showServerId(Player... players) {
      Texteria2D.add(TC_SERVER_ID, players);
   }

   public static void showUsername(VPlayer player) {
      String text = player.username;
      if(player.getLevel() != 0) {
         text = "[&e" + player.getLevel() + "&r] " + text;
      }

      Texteria2D.add(((Text)((Text)(new Text("vn.n", new String[]{text})).setPosition(Position.TOP_LEFT)).setOffset(2, 2)).setVisibility(new IngameNotF3()), new Player[]{player.player});
   }

   public static void showGiveExp(VPlayer player, int amount) {
      Texteria2D.add(((Text)((Text)((Text)((Text)((Text)(new Text((String)null, new String[]{"&a+" + String.valueOf(amount)})).setFadeStart(300)).setFadeFinish(500)).setDuration(1200L)).setOffset(4, 0)).setAnimation(ANIM_GIVE_EXP)).setAttachment((new Attachment("vn.n", Position.RIGHT)).setRemoveWhenParentRemove(false)), new Player[]{player.player});
   }

   public static void showCoins(VPlayer player) {
      String text = "Количество коинов: " + (player.getCoins() < 0?"&c":"&e") + player.getCoins();
      Text elem;
      if(player.getMultipliers().getCurrentMultiplier() != 1) {
         elem = (Text)(new Text("vn.c.text", new String[]{text})).setAttachment(new Attachment("vn.c.x", Position.RIGHT));
         int prev = player.coinsTexteria;
         Text x;
         long to;
         if(player.multipliers.isActivated() && (to = player.multipliers.getExtraEndTime() - System.currentTimeMillis()) > 0L) {
            String timeFormat = "{H}ч. {M}м.";
            player.coinsTexteria = 1;
            if(to > 86400000L) {
               timeFormat = "{D}д. {H}ч.";
               player.coinsTexteria = 2;
            } else if(to <= 3601000L) {
               timeFormat = "{M}м.";
               player.coinsTexteria = 3;
            }

            x = (new TextTimer("vn.c.x", new String[]{"&e[&dx" + player.getMultipliers().getCurrentMultiplier() + "&f - " + timeFormat + "&e]&f "})).setTimerDuration(to);
         } else {
            x = new Text("vn.c.x", new String[]{"&e[&dx" + player.getMultipliers().getCurrentMultiplier() + "&e]&f "});
            player.coinsTexteria = 4;
         }

         if(prev != player.coinsTexteria) {
            Texteria2D.add(((Text)((Text)x.setPosition(Position.BOTTOM_LEFT)).setOffset(2, 16)).setVisibility(new Always()), new Player[]{player.player});
         }
      } else {
         elem = (Text)((Text)(new Text("vn.c.text", new String[]{text})).setOffset(2, 16)).setPosition(Position.BOTTOM_LEFT);
      }

      Texteria2D.add(elem.setVisibility(new Always()), new Player[]{player.player});
   }

   public static void showAchievementMessage(Achievement achievement, Player player) {
      Texteria2D.add(new Element[]{((Text)((Text)(new Text("a1", new String[]{achievement.getName()})).setScale(4.0F)).setDuration(5000L)).setOffset(-10, -20), ((Text)((Text)(new Text("a2", achievement.getDescription())).setScale(2.0F)).setOffset(0, 10)).setDuration(5000L)}, new Player[]{player});
      Bukkit.getScheduler().scheduleSyncDelayedTask(VNPlugin.instance(), () -> {
         Texteria2D.add(((Text)((Text)((Text)((Text)(new Text("a3", new String[]{"&a✔"})).setAttachment(new Attachment("a1", Position.RIGHT))).setDuration(4250L)).setScale(4.0F)).setOffset(15, 0)).setFadeStart(400), new Player[]{player});
      }, 15L);
   }

   public static void showPartyInvite(Player player, String inviter) {
      Attachment att = (new Attachment("vn.n.bg", Position.TOP_LEFT)).setOrientation(Position.BOTTOM_RIGHT);
      Texteria2D.remove("vn.n.", new Player[]{player});
      Texteria2D.add(new Always(), new Element[]{((Rectangle)((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Rectangle("vn.n.bg", 142, 43)).setColor(-14575885)).setFade(500)).setOffset(2, 12)).setPosition(Position.TOP_LEFT)).setDuration(8000L)).setAnimation(ANIM_NOTIFICATION), ((Text)((Text)(new Text("vn.n.title", new String[]{"Приглашение в группу"})).setOffset(5, 2)).setFade(500)).setAttachment(att), ((Text)((Text)((Text)(new Text("vn.n.text", new String[]{"&rОт игрока &l" + inviter})).setOffset(5, 14)).setFade(500)).setAttachment(att)).setOrientation(1), ((Button)((Button)((Button)((Button)(new Button("vn.n.text2", 50, 11, "Принять")).setColor(-12858815)).setHoverColor(-10757535).setOffset(-4, -4)).setFade(500)).setOnClick(new OnClick(Action.CHAT, "/p j " + inviter))).setAttachment((new Attachment("vn.n.bg", Position.BOTTOM_RIGHT)).setOrientation(Position.TOP_LEFT))}, new Player[]{player});
   }

   public static void showFriendRequest(Player player, String requester) {
      Attachment att = (new Attachment("vn.n.bg", Position.TOP_LEFT)).setOrientation(Position.BOTTOM_RIGHT);
      Texteria2D.remove("vn.n.", new Player[]{player});
      Texteria2D.add(new Always(), new Element[]{((Rectangle)((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Rectangle("vn.n.bg", 142, 43)).setColor(-14575885)).setFade(500)).setOffset(2, 12)).setPosition(Position.TOP_LEFT)).setDuration(8000L)).setAnimation(ANIM_NOTIFICATION), ((Text)((Text)(new Text("vn.n.title", new String[]{"Запрос в друзья"})).setOffset(5, 2)).setFade(500)).setAttachment(att), ((Text)((Text)((Text)(new Text("vn.n.text", new String[]{"&rОт игрока &l" + requester})).setOffset(5, 14)).setFade(500)).setAttachment(att)).setOrientation(1), ((Button)((Button)((Button)((Button)(new Button("vn.n.text2", 50, 11, "Отклонить")).setColor(-769226)).setHoverColor(-37269).setOffset(-58, -4)).setFade(500)).setOnClick(new OnClick(Action.CHAT, "/f dny " + requester))).setAttachment((new Attachment("vn.n.bg", Position.BOTTOM_RIGHT)).setOrientation(Position.TOP_LEFT)), ((Button)((Button)((Button)((Button)(new Button("vn.n.text3", 50, 11, "Принять")).setColor(-12858815)).setHoverColor(-10757535).setOffset(-4, -4)).setFade(500)).setOnClick(new OnClick(Action.CHAT, "/f acpt " + requester))).setAttachment((new Attachment("vn.n.bg", Position.BOTTOM_RIGHT)).setOrientation(Position.TOP_LEFT))}, new Player[]{player});
   }

   public static void showCoinsChange(VPlayer player, int amount) {
      if(amount > 0) {
         Texteria2D.add(((Text)((Text)((Text)((Text)((Text)((Text)(new Text("vn.c.change", new String[]{"+" + amount})).setColor(-16711936)).setOffset(1, 0)).setFadeFinish(500)).setDuration(1200L)).setVisibility(new Always())).setAnimation(ANIM_ADD_COINS)).setAttachment((new Attachment("vn.c.text", Position.RIGHT)).setRemoveWhenParentRemove(false)), new Player[]{player.player});
      } else if(amount < 0) {
         Texteria2D.add(((Text)((Text)((Text)((Text)((Text)((Text)(new Text("vn.c.change", new String[]{String.valueOf(amount)})).setColor(-65536)).setOffset(1, 0)).setFadeFinish(500)).setDuration(1200L)).setVisibility(new Always())).setAnimation(ANIM_ADD_COINS)).setAttachment((new Attachment("vn.c.text", Position.RIGHT)).setRemoveWhenParentRemove(false)), new Player[]{player.player});
      }

   }

   public static void showStreamMessage(List<StreamMenu.StreamerData> streams, Player[] players) {
      streamMessage(players, IntColor.setAlpha(-14575885, 200), "&lАктивных стримов: " + streams.size(), new String[0]);
   }

   public static void showNewStreamer(StreamMenu.StreamerData streamer, Player[] players) {
      streamMessage(players, IntColor.setAlpha(-14575885, 200), "&lНовый стрим на " + ((StreamMenu.StreamData)streamer.sortedStreams().get(0)).platform, new String[]{"Игрок &e" + streamer.owner + "&f ведёт", "стрим. Зрителей: &e" + streamer.getTotalViewers()});
   }

   private static void streamMessage(Player[] players, int color, String title, String... text) {
      Attachment att = (new Attachment("vn.n.bg", Position.TOP_LEFT)).setOrientation(Position.BOTTOM_RIGHT);
      Texteria2D.remove("vn.n.", players);
      Texteria2D.add(new Always(), new Element[]{((Rectangle)((Rectangle)((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Rectangle("vn.n.bg", 142, 17 + 9 * text.length + 12)).setColor(color)).setFade(500)).setOffset(2, 12)).setPosition(Position.TOP_LEFT)).setDuration(8000L)).setAnimation(ANIM_NOTIFICATION)).setOnClick(new OnClick(Action.CALLBACK, CALLBACK_OPEN_STREAMS)), ((Text)((Text)(new Text("vn.n.title", new String[]{title})).setOffset(5, 2)).setFade(500)).setAttachment(att), ((Text)((Text)((Text)(new Text("vn.n.text", text)).setOffset(5, 14)).setFade(500)).setAttachment(att)).setOrientation(1), ((Text)((Text)(new Text("vn.n.text2", new String[]{"Напишите &l/streams&f для просмотра"})).setOffset(5, 17 + 9 * text.length)).setFade(500)).setAttachment(att)}, players);
   }

   public static void showGoalComplete(NetworkPlayer player, Goal goal) {
      goalMessage(player.getBukkitPlayer(), IntColor.setAlpha(-11751600, 200), "&lВы выполнили задание:", getGoalText(goal));
   }

   public static void showGoalAdded(NetworkPlayer player, Goal goal) {
      String[] text = getGoalText(goal);
      Attachment att = (new Attachment("vn.n.bg", Position.TOP_LEFT)).setOrientation(Position.BOTTOM_RIGHT);
      Texteria2D.remove("vn.n.", new Player[]{player.getBukkitPlayer()});
      Texteria2D.add(new Always(), new Element[]{((Rectangle)((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Rectangle("vn.n.bg", 132, 19 + 9 * text.length + 12)).setColor(IntColor.setAlpha(-14575885, 200))).setFade(500)).setOffset(2, 12)).setPosition(Position.TOP_LEFT)).setDuration(6000L)).setAnimation(ANIM_NOTIFICATION), ((Text)((Text)(new Text("vn.n.title", new String[]{"&lДобавлено новое задание:"})).setOffset(5, 2)).setFade(500)).setAttachment(att), ((Text)((Text)((Text)(new Text("vn.n.text", text)).setOffset(5, 14)).setFade(500)).setAttachment(att)).setOrientation(1), ((Button)((Button)((Button)((Button)(new Button("vn.n.text2", 50, 11, "Открыть")).setColor(-10177034)).setHoverColor(-12409355).setOffset(-4, -4)).setFade(500)).setOnClick(new OnClick(Action.CALLBACK, CALLBACK_OPEN_GOALS))).setAttachment((new Attachment("vn.n.bg", Position.BOTTOM_RIGHT)).setOrientation(Position.TOP_LEFT))}, new Player[]{player.getBukkitPlayer()});
   }

   public static void showGoalMessage(NetworkPlayer player) {
      goalMessage(player.getBukkitPlayer(), IntColor.setAlpha(-16537100, 200), "&lАктивно заданий: " + player.getGoals().getActiveGoals().size(), new String[]{"Нажмите для просмотра"});
   }

   public static void showGoalExpired(NetworkPlayer player, Goal goal) {
      goalMessage(player.getBukkitPlayer(), IntColor.setAlpha(-769226, 200), "&lЗадание просрочено: ", getGoalText(goal));
   }

   private static String[] getGoalText(Goal goal) {
      List<String> text = goal.getText(true);
      return (String[])text.toArray(new String[text.size()]);
   }

   private static void goalMessage(Player player, int color, String title, String... text) {
      Attachment att = (new Attachment("vn.n.bg", Position.TOP_LEFT)).setOrientation(Position.BOTTOM_RIGHT);
      Texteria2D.remove("vn.n.", new Player[]{player});
      Texteria2D.add(new Always(), new Element[]{((Rectangle)((Rectangle)((Rectangle)((Rectangle)((Rectangle)((Rectangle)(new Rectangle("vn.n.bg", 132, 17 + 9 * text.length)).setColor(color)).setFade(500)).setOffset(2, 12)).setPosition(Position.TOP_LEFT)).setDuration(6000L)).setAnimation(ANIM_NOTIFICATION)).setOnClick(new OnClick(Action.CALLBACK, CALLBACK_OPEN_GOALS)), ((Text)((Text)(new Text("vn.n.title", new String[]{title})).setOffset(5, 2)).setFade(500)).setAttachment(att), ((Text)((Text)((Text)(new Text("vn.n.text", text)).setOffset(5, 14)).setFade(500)).setAttachment(att)).setOrientation(1)}, new Player[]{player});
   }

   static {
      CALLBACK_OPEN_GOALS.put("module", "VimeNetwork");
      CALLBACK_OPEN_GOALS.put("action", "goals-inv");
      CALLBACK_OPEN_STREAMS.put("module", "VimeNetwork");
      CALLBACK_OPEN_STREAMS.put("action", "streams-inv");
   }
}
