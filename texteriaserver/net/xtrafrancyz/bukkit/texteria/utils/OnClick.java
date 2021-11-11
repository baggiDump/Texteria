package net.xtrafrancyz.bukkit.texteria.utils;

public class OnClick {
   public OnClick.Action action;
   public Object data;

   public OnClick(OnClick.Action action, Object data) {
      this.action = action;
      this.data = data;
   }

   public static enum Action {
      URL,
      CHAT,
      CALLBACK;
   }
}
