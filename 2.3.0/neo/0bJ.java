package neo;

import java.io.File;
import java.util.function.Supplier;

public class 0bJ extends 0dr {
   public static 0ct field_g;
   public static 0cs field_a;
   public static 0cp field_c;
   private static final 0cr field_d;
   public static 0cu field_h;
   public static final String[] field_b;
   private static final 0cr field_f;
   public static 0cu field_e;
   private static final 0cr field_i;
   private static String _ _;

   private static 0cs method_SA() {
      return field_a;
   }

   public void method_bAU() {
      super.method_bAU();
      if (method_SA().method_bnr(method_Sz("ñÚÀÝßÛÇß")) && !(new File(method_SB().method_bnP())).exists()) {
         0ek.addMessage(0cT.method_byX(method_Sz("ßÝÖÇÞ×\u009cÅ×ÐÁÝÞÄ×À\u009cÂÓÆÚ\u009c×ßÂÆË")));
         this.method_bBi();
      }

   }

   private static 0cs method_SF() {
      return field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_Sz(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 16150 ^ -10173 ^ 20347 ^ -22482; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 30815 ^ -1770 ^ 17802 ^ -15247));
      }

      return var1.toString();
   }

   public _bJ/* $FF was: 0bJ*/() {
      super(method_Sz("å×ÐáÝÞÄ×À"), 0dz.field_h, 4324 ^ -6473 ^ 3374 ^ -1155);
      0cv[] var10001 = new 0cv[25907 ^ -6586 ^ 374 ^ -32245];
      var10001[3991 ^ -27284 ^ 133 ^ -25986] = field_a;
      var10001[5829 ^ -25712 ^ 6138 ^ -25938] = field_f;
      var10001[617 ^ -9964 ^ 793 ^ -10138] = field_h;
      var10001[16221 ^ -28504 ^ 7983 ^ -20263] = field_e;
      var10001[4637 ^ -27418 ^ 2378 ^ -28747] = field_c;
      var10001[17637 ^ -21793 ^ 26453 ^ -30358] = field_i;
      var10001[8430 ^ -6031 ^ 2018 ^ -12421] = field_g;
      var10001[31747 ^ -293 ^ 24623 ^ -7440] = field_d;
      this.method_bBe(var10001);
   }

   private static String[] method_SC() {
      return field_b;
   }

   private static 0cs method_SE() {
      return field_a;
   }

   public void method_bAV() {
      super.method_bAV();
   }

   public static boolean method_Su(String a) {
      return (boolean)(a.contains(method_Sz("ÚÆÆÂ")) && 0eg.method_bFr(method_SC(), a) ? 32280 ^ -3380 ^ 26021 ^ -5776 : 21535 ^ -23882 ^ 22188 ^ -24571);
   }

   static {
      String var10002 = method_Sz("ÿÝÖ×");
      String var10003 = method_Sz("ñÚÀÝßÛÇß");
      String[] var10004 = new String[13099 ^ -2135 ^ 12769 ^ -2719];
      var10004[12234 ^ -8537 ^ 27917 ^ -25504] = method_Sz("ö×ÔÓÇÞÆ");
      var10004[21694 ^ -16331 ^ 11365 ^ -18193] = method_Sz("å×Ðóâû");
      field_a = new 0cs(var10002, var10003, var10004);
      String[] var0 = new String[1799 ^ -27370 ^ 25932 ^ -2219];
      var0[2094 ^ -7011 ^ 22180 ^ -17897] = method_Sz("ҠҊ҆ӹ\u0092Ӳ҇Ӻ҇ҏҊӽ\u0088\u0092");
      var0[1849 ^ -28472 ^ 21233 ^ -15103] = method_Sz("\u0092ñÚÀÝßÛÇß\u0092\u009f\u0092҃ӱ҆҇Ӱ\u0092ҊӳҍҌ҉Ӿ҅ҌҀ҂ӰӾӳӽ");
      var0[3074 ^ -22106 ^ 24797 ^ -14981] = method_Sz("ӱ҈҂҅҂ҏҏӹҋ\u0092Ҁ\u0092âÓÆÚ\u0092҃Ӳ҂ӱ҅҇Ӳ\u009c");
      var0[3667 ^ -4886 ^ 3928 ^ -4638] = method_Sz("үҧ\u0092ӳҌҀҎ҇ӳӰҊҎ\u0092ӳ\u0092ôÛÀ×ÔÝÊ\u009e\u0092ýÂ×ÀÓõê");
      var0[25832 ^ -11560 ^ 10464 ^ -24876] = method_Sz("\u0092ö×ÔÓÇÞÆ\u0092\u009f\u0092ӳӳӹ҉҈Ҋ\u0092҃ӱ҆ӱӰ\u0092ҌӰ҈ӲӹҀ҂ӰӾӳӽ");
      var0[7337 ^ -12561 ^ 5828 ^ -15225] = method_Sz("҉Ӽ҃ӹҎ\u0092҃Ӳ҂ӱ҅҇ӲҌҎ\u009e\u0092ҏҌ\u0092҃҇҅\u0092ҍӲҌ҈ӳҊ\u0093");
      var0[11170 ^ -17389 ^ 22979 ^ -12684] = method_Sz("\u0092å×Ðóâû\u0092\u009f\u0092Ғ҂҃ҌӰ҂\u0092õ÷æ\u0092҅҂ҍӲҌӳ҂ҎҊ");
      var0[3088 ^ -7751 ^ 28009 ^ -32569] = method_Sz("҆҉ӽ\u0092Ҁҏ҇ӺҏҊӷ\u0092ҍӲҊ҉Ҍ҄҇ҏҊҋ");
      field_f = new 0cr(var0);
      field_h = new 0cu(method_Sz("å×Ðóâû\u0092ÚÝÁÆ"), method_Sz("ÚÆÆÂ\u0088\u009d\u009d\u0083\u0080\u0085\u009c\u0082\u009c\u0082\u009c\u0083\u0088\u008a\u0082\u008a\u0082"), () -> {
         return method_SG().method_bnr(method_Sz("å×Ðóâû"));
      });
      field_e = new 0cu(method_Sz("âÓÆÚ"), method_Sz("ñ\u0088\u009dâÀÝÕÀÓß\u0092ôÛÞ×Á\u009dõÝÝÕÞ×\u009dñÚÀÝß×\u009dóÂÂÞÛÑÓÆÛÝÜ\u009dÑÚÀÝß×\u009c×Ê×"), () -> {
         return method_SF().method_bnr(method_Sz("ñÚÀÝßÛÇß"));
      });
      field_c = new 0cp(method_Sz("çÁ×\u0092âÀÝÊË"), (boolean)(20338 ^ -32018 ^ 30902 ^ -19157), () -> {
         return method_SE().method_bnr(method_Sz("ñÚÀÝßÛÇß"));
      });
      Supplier var1 = () -> {
         return method_SD().method_bnr(method_Sz("ñÚÀÝßÛÇß"));
      };
      String[] var2 = new String[12926 ^ -14104 ^ 15797 ^ -14559];
      var2[12583 ^ -30944 ^ 6806 ^ -21359] = method_Sz("ҪӳҍҌ҉Ӿ҅ҌҀ҂ӰӾ\u0092҉Ҋ\u0092ҍӲҌ҈ӳҊ\u0092ҌӰ\u0092҃ҌӰ҂");
      var2[6849 ^ -9049 ^ 8657 ^ -6218] = method_Sz("҆҉ӽ\u0092ҌӰ҈ӲӹӰҊӽ\u0092ӳӰӲ҂ҏҊӴӹ");
      field_i = new 0cr(var1, var2);
      field_g = new 0ct(method_Sz("ýÂ×Ü\u0092Ö×ÞÓË"), Float.intBitsToFloat(247747 ^ 236930 ^ 6984 ^ -125725775 ^ 3176 ^ 472012 ^ 494706 ^ -1124376210), Float.intBitsToFloat(7723 ^ 120941 ^ 107550 ^ -712079755 ^ 106511 ^ 93385 ^ 22549 ^ -1756991234), Float.intBitsToFloat(702 ^ '샟' ^ 7265 ^ 906975968 ^ 19261 ^ '캜' ^ 30282 ^ 1945466635));
      var0 = new String[30052 ^ -26660 ^ 28411 ^ -29632];
      var0[31920 ^ -17483 ^ 18281 ^ -32660] = method_Sz("ґӳӰ҂ҏҌҀ҈҂\u0092҅҂҆҇Ӳ҄҈Ҋ\u0092ҏ҂\u0092ҌӰ҈ӲӹӰҊ҇");
      var0[14099 ^ -6536 ^ 21508 ^ -31378] = method_Sz("ӳӰӲ҂ҏҊӴ\u009c\u0092ү҇\u0092҆҂ӳӰ\u0092ҌӰ҈ӲӹӰӾ\u0092Ҁӳ҇\u0092ӳӲ҂҅ӱ");
      var0[10175 ^ -7892 ^ 31494 ^ -17001] = method_Sz("҇ӳ҉Ҋ\u0092҅҂ҋ҆҇Ӱ\u0092ҎҏҌҁҌ\u0092҃ҌӰҌҀ");
      field_d = new 0cr(var0);
      String[] var10000 = new String[30813 ^ -12887 ^ 6582 ^ -21434];
      var10000[10016 ^ -4627 ^ 8868 ^ -6039] = method_Sz("ÓÜÆÛÐÝÆ");
      var10000[6785 ^ -7385 ^ 22736 ^ -24201] = method_Sz("ÑÓÂÆÑÚÓ");
      var10000[21154 ^ -1312 ^ 13494 ^ -25354] = method_Sz("ÛÖ\u008f");
      var10000[7544 ^ -15629 ^ 19713 ^ -28023] = method_Sz("Æ\u008f");
      field_b = var10000;
   }

   private static 0cu method_SB() {
      return field_e;
   }

   private static 0cs method_SD() {
      return field_a;
   }

   private static 0cs method_SG() {
      return field_a;
   }
}
