package neo;

import java.awt.Color;
import java.util.Random;

public enum 0dE {
   field_c(WiNXk7TgYj("܍ܝ܌ܗ\u070e܊ܗܐܙݐ܍ܟܐܚܜܑ܆ݐ܍܊܌ܟܐܙܛ"), new Color(26394 ^ -5120 ^ 5417 ^ -24884, 7951 ^ -16738 ^ 7486 ^ -17187, 7991 ^ -19380 ^ 15837 ^ -26970), 25358 ^ -18241 ^ 24254 ^ -31474),
   field_g(WiNXk7TgYj("܍ܝ܌ܗ\u070e܊ܗܐܙݐ܍ܟܐܚܜܑ܆ݐܜܒܑܝܕܛܚ"), new Color(32288 ^ -26088 ^ 8841 ^ -14746, 22198 ^ -26807 ^ 21923 ^ -27525, 22770 ^ -16190 ^ 32445 ^ -6486), 6027 ^ -4240 ^ 12615 ^ -13890);

   public final Color field_h;
   field_a(WiNXk7TgYj("܍ܝ܌ܗ\u070e܊ܗܐܙݐ܍ܟܐܚܜܑ܆ݐܝܖܛܝܕܛܚ"), new Color(186 ^ -10376 ^ 1126 ^ -11339, 14298 ^ -25545 ^ 29198 ^ -9956, 17736 ^ -6482 ^ 22416 ^ -2954), 9541 ^ -17795 ^ 12886 ^ -21137);

   public final String field_d;
   public final int field_f;
   field_b(WiNXk7TgYj("܍ܝ܌ܗ\u070e܊ܗܐܙݐ܍ܟܐܚܜܑ܆ݐ܋ܐܝܖܛܝܕܛܚ"), new Color(21024 ^ -4709 ^ 18453 ^ -2243, 10098 ^ -12239 ^ 4874 ^ -6950, 11884 ^ -10654 ^ 18691 ^ -20066), 6417 ^ -30442 ^ 18190 ^ -10487);

   private static String _DSC GG NEOWARECLIENT _;

   public static 0dE random() {
      return values()[(new Random()).nextInt(values().length)];
   }

   private static 0dE[] DFm7wFGItN() {
      return field_e;
   }

   public String getName() {
      return 0cT.method_byX(BSvAJNnLYY(this));
   }

   private static String BSvAJNnLYY(0dE var0) {
      return var0.field_d;
   }

   static {
      0dE[] var10000 = new 0dE[5009 ^ -19602 ^ 29961 ^ -10766];
      var10000[6775 ^ -26449 ^ 23479 ^ -9873] = field_b;
      var10000[609 ^ -19011 ^ 24455 ^ -6054] = field_a;
      var10000[19028 ^ -12139 ^ 4212 ^ -30025] = field_c;
      var10000[5050 ^ -21086 ^ 25 ^ -16894] = field_g;
   }

   public Color getColor() {
      return QbpqZCTDQ6(this);
   }

   private static int jI7tdrYHpq(0dE var0) {
      return var0.field_f;
   }

   public int getStage() {
      return jI7tdrYHpq(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String WiNXk7TgYj(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 9617 ^ -31091 ^ 23983 ^ -333; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31174 ^ -43326 ^ 'ﶹ' ^ -10813));
      }

      return var1.toString();
   }

   private _dE/* $FF was: 0dE*/(String a, Color b, int c) {
      this.field_d = a;
      this.field_h = b;
      this.field_f = c;
   }

   private static Color QbpqZCTDQ6(0dE var0) {
      return var0.field_h;
   }
}
