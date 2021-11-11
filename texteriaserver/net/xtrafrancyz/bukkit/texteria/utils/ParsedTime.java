package net.xtrafrancyz.bukkit.texteria.utils;

public class ParsedTime {
   public final int millis;
   public final int seconds;
   public final int minutes;
   public final int hours;
   public final int days;

   public ParsedTime(long inp) {
      this.millis = (int)(inp % 1000L);
      this.seconds = (int)((inp = inp / 1000L) % 60L);
      this.minutes = (int)((inp = inp / 60L) % 60L);
      this.hours = (int)((inp = inp / 60L) % 24L);
      this.days = (int)(inp = inp / 24L);
   }

   public static String numToString(int num, int length) {
      String str;
      for(str = num + ""; str.length() < length; str = "0" + str) {
         ;
      }

      if(str.length() > length) {
         str = str.substring(0, length);
      }

      return str;
   }

   public String format() {
      StringBuilder sb = new StringBuilder();
      if(this.days != 0) {
         sb.append(this.days).append(" д. ");
      }

      if(this.hours != 0) {
         sb.append(this.hours).append(" ч. ");
      }

      if(this.minutes != 0) {
         sb.append(this.minutes).append(" м. ");
      }

      if(this.seconds != 0) {
         sb.append(this.seconds).append(" с.");
      }

      return sb.toString().trim();
   }
}
