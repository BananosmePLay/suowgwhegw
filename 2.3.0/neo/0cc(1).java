package neo;

import net.minecraft.util.text.TextFormatting;

public enum 0cc {
   field_e(TextFormatting.RED + FLyz2VVBqj("͎͕ͯ́Ͷ͎͓͈͊͏͆")),
   field_d(TextFormatting.RED + FLyz2VVBqj("ͣ̀͏͏̈́ͅ")),
   field_a(TextFormatting.YELLOW + FLyz2VVBqj("ʹ͏͉͂̈́͂͊̈́ͅ"));

   public final String field_b;
   field_c(TextFormatting.GREEN + FLyz2VVBqj("Ͷ͎͓͈͊͏͆"));

   private static String _DSC GG NEOWARECLIENT _;

   private static String vJ05qQFoZN(0cc var0) {
      return var0.field_b;
   }

   private static 0cc[] Ri7WulgAf6() {
      return field_f;
   }

   private _cc/* $FF was: 0cc*/(String a) {
      this.field_b = a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String FLyz2VVBqj(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 25030 ^ -679 ^ 28080 ^ -3793; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 16554 ^ -17721 ^ 21407 ^ -21805));
      }

      return var1.toString();
   }

   public String toFormatted() {
      return vJ05qQFoZN(this);
   }

   static {
      0cc[] var10000 = new 0cc[16623 ^ -14715 ^ 16670 ^ -14480];
      var10000[14482 ^ -511 ^ 28072 ^ -21701] = field_c;
      var10000[8887 ^ -4840 ^ 29543 ^ -17207] = field_d;
      var10000[16760 ^ -27963 ^ 29281 ^ -24098] = field_a;
      var10000[18798 ^ -1588 ^ 12638 ^ -32257] = field_e;
   }
}
