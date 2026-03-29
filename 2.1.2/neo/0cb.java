package neo;

public class 0cb extends 0cB {
   public static 0bv autoSwim = new 0bv(XP6we90IFv("֫֟֞օֹ֝փև"), (boolean)(4805 ^ -31454 ^ 8011 ^ -30547));
   public static 0bv autoJump = new 0bv(XP6we90IFv("֫֟֞օ֠֟և֚"), (boolean)(22100 ^ -6614 ^ 27565 ^ -9262));

   public _cb/* $FF was: 0cb*/() {
      super(XP6we90IFv("֨օׇ֞֨\u058b֘փ֞օք֏"), 0bV.Bots);
      0bC[] var10001 = new 0bC[3352 ^ -17423 ^ 21478 ^ -6899];
      var10001[27126 ^ -5070 ^ 23218 ^ -8330] = autoJump;
      var10001[29910 ^ -5869 ^ 4605 ^ -29639] = autoSwim;
      this.addSetting(var10001);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String XP6we90IFv(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 16711 ^ -10450 ^ 18555 ^ -8686; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 10636 ^ -28021 ^ 28524 ^ -11903));
      }

      return var1.toString();
   }

   public void onEnable() {
      super.onEnable();
      this.toggle();
   }
}
