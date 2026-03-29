package neo;

public abstract class 0cW {
   public final String name;
   public final 0cC bot;
   public final 0cV baritone;
   public final 0cH mc;

   public void sendDebug(String message) {
      if (tTxyrdYom9(gvyFgMVhiM())) {
         0dK.defaultMsg(message);
      }

   }

   public 0cC getBot() {
      return Yxiw8Xb9aa(this);
   }

   private static 0cH _P2KMysddg/* $FF was: 9P2KMysddg*/(0cW var0) {
      return var0.mc;
   }

   public String getName() {
      return 9k6T0tAJ8w(this);
   }

   private static 0cV _l9QLjmZi8/* $FF was: 2l9QLjmZi8*/(0cW var0) {
      return var0.baritone;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String Q99oqnm2hB(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 18307 ^ -22396 ^ 20800 ^ -16825; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 7346 ^ -26262 ^ 31006 ^ -1525));
      }

      return var1.toString();
   }

   public void onFinish() {
      this.sendDebug(Q99oqnm2hB("۫۸ږ۫کڏڢڹ۠ڏڬڿڤڹڢڣڨ۫۸ڐۭ۫ۺڏڢڹۭ") + this.getBot().getNickname() + Q99oqnm2hB("ۭڱۭڢڦۭڮڬڣڮڨڡڨک"));
   }

   private static 0bv gvyFgMVhiM() {
      return 0cc.baritoneDebug;
   }

   private static boolean tTxyrdYom9(0bv var0) {
      return var0.value;
   }

   private static 0cC Yxiw8Xb9aa(0cW var0) {
      return var0.bot;
   }

   public 0cH getMc() {
      return 9P2KMysddg(this);
   }

   private static String _k6T0tAJ8w/* $FF was: 9k6T0tAJ8w*/(0cW var0) {
      return var0.name;
   }

   public 0cV getBaritone() {
      return 2l9QLjmZi8(this);
   }

   public _cW/* $FF was: 0cW*/(0cC bot, String name) {
      this.bot = bot;
      this.name = name;
      this.mc = bot.mc;
      this.baritone = bot.getBaritone();
   }

   public abstract void onUpdate();

   public abstract void init();
}
