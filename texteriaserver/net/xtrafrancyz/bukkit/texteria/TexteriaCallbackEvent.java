package net.xtrafrancyz.bukkit.texteria;

import net.xtrafrancyz.bukkit.texteria.utils.ByteMap;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;

public class TexteriaCallbackEvent extends PlayerEvent {
   private static final HandlerList HANDLERS = new HandlerList();
   private final ByteMap data;

   public TexteriaCallbackEvent(Player who, ByteMap data) {
      super(who);
      this.data = data;
   }

   public ByteMap getData() {
      return this.data;
   }

   public HandlerList getHandlers() {
      return HANDLERS;
   }

   public static HandlerList getHandlerList() {
      return HANDLERS;
   }
}
