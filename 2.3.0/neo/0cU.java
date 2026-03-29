package neo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class 0cU extends Properties implements 0cC, 0cD {
   private static String _ _;

   private InputStream method_bzb(String a) {
      String var10001 = method_bzc("ټزؠؠضاؠټؾغؽضذءزصاټؽضؼؤزءضټؿزؽشټٶؠٽؿزؽش");
      Object[] var10002 = new Object[26232 ^ -13825 ^ 3696 ^ -24074];
      var10002[20183 ^ -21744 ^ 32158 ^ -26535] = a;
      return 0cU.class.getResourceAsStream(String.format(var10001, var10002));
   }

   public void method_byZ(String c) {
      try {
         InputStream a = this.method_bzb(c);
         if (a != null) {
            this.load(a);
         } else {
            this.load(0cU.class.getResourceAsStream(method_bzc("ټزؠؠضاؠټؾغؽضذءزصاټؽضؼؤزءضټؿزؽشټضؽ،ئؠٽؿزؽش")));
         }
      } catch (IOException var3) {
         IOException b = var3;
         b.printStackTrace();
      }

   }

   public synchronized void load(InputStream a) throws IOException {
      super.load(a);
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bzc(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6881 ^ -28356 ^ 26863 ^ -7374; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 16917 ^ -11397 ^ 29728 ^ -7395));
      }

      return var1.toString();
   }

   public _cU/* $FF was: 0cU*/() {
      this.method_byZ(method_bzc("ضؽ،ئؠ"));
   }

   public String getProperty(String a) {
      return super.getProperty(a);
   }

   public void method_bza() {
      this.method_byZ(method_bzd().getLanguageManager().getCurrentLanguage().getLanguageCode());
   }

   private static nC method_bzd() {
      return mc;
   }
}
