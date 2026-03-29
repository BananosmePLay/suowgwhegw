package neo;

import com.sun.jna.Structure;
import java.util.Arrays;
import java.util.List;

public class 0dU extends Structure {
   public String avatar;
   public String username;
   /** @deprecated */
   @Deprecated
   public String discriminator;
   public String userId;
   private static String _DSC GG NEOWARECLIENT _;

   public _dU/* $FF was: 0dU*/() {
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String f3DLcW5ejr(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 28026 ^ -10855 ^ 12141 ^ -26738; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 2889 ^ 30677 ^ 32034 ^ 556));
      }

      return var1.toString();
   }

   protected List<String> getFieldOrder() {
      String[] var10000 = new String[13116 ^ -12427 ^ 26604 ^ -25695];
      var10000[16077 ^ -12231 ^ 27749 ^ -32111] = f3DLcW5ejr("ϧϡϷϠϛ϶");
      var10000[25598 ^ -16690 ^ 30463 ^ -21554] = f3DLcW5ejr("ϧϡϷϠϼϳϿϷ");
      var10000[18466 ^ -26015 ^ 23735 ^ -28938] = f3DLcW5ejr("϶ϻϡϱϠϻϿϻϼϳϦϽϠ");
      var10000[8010 ^ -14087 ^ 17884 ^ -28052] = f3DLcW5ejr("ϳϤϳϦϳϠ");
      return Arrays.asList(var10000);
   }
}
