package neo;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class 0dZ extends Structure {
   public String userId;
   public String avatar;
   public String username;
   /** @deprecated */
   @Deprecated
   public String discriminator;

   // $FF: synthetic method
   // $FF: bridge method
   private static String lUQIiWTIed(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 6768 ^ -8721 ^ 26370 ^ -24419; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 2454 ^ -11737 ^ 7999 ^ -16167));
      }

      return var1.toString();
   }

   protected List<String> getFieldOrder() {
      String[] var10000 = new String[19495 ^ -19282 ^ 12277 ^ -10376];
      var10000[27427 ^ -7889 ^ 17906 ^ -12290] = lUQIiWTIed("ТФвХОг");
      var10000[7897 ^ -16971 ^ 22778 ^ -1129] = lUQIiWTIed("ТФвХйжкв");
      var10000[823 ^ -8989 ^ 11603 ^ -3451] = lUQIiWTIed("гоФдХокойжУиХ");
      var10000[17842 ^ -23143 ^ 8174 ^ -58] = lUQIiWTIed("жСжУжХ");
      return Arrays.asList(var10000);
   }

   public _dZ/* $FF was: 0dZ*/() {
   }
}
