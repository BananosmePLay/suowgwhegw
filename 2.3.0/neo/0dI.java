package neo;

import java.security.MessageDigest;

public class 0dI {
   public static String field_b;
   public static final char[] field_a = method_bDB("ԷԶԵԴԳԲԱ\u0530ԿԾզեդգբա").toCharArray();
   private static String _ _;

   private static String method_bDH() {
      return field_b;
   }

   private static String method_bDD() {
      return field_b;
   }

   private static void method_bDG(String var0) {
      field_b = var0;
   }

   public static String method_bDA() throws Exception {
      if (method_bDC() != null) {
         return method_bDD();
      } else {
         byte[] c = MessageDigest.getInstance(method_bDB("ՔՏՆԪԵԵԳ")).digest((System.getProperty(method_bDB("ղմբյԩթզժբ")) + System.getenv(method_bDB("ՄՈՊ\u0557ՒՓՂՕՉՆՊՂ")) + System.getenv(method_bDB("ըմ")) + System.getProperty(method_bDB("ըմԩթզժբ")) + System.getenv(method_bDB("\u0557ՕՈՄՂՔՔՈՕ\u0558ՎՃՂՉՓՎՁՎՂՕ")) + System.getProperty(method_bDB("ըմԩզյդկ")) + System.getProperty(method_bDB("ըմԩձբյմծըթ")) + System.getProperty(method_bDB("ղմբյԩիզթՠղզՠբ")) + System.getenv(method_bDB("ՔվմճբժՕըըճ")) + System.getenv(method_bDB("ՏՈՊՂՃՕՎՑՂ")) + System.getenv(method_bDB("\u0557ՕՈՄՂՔՔՈՕ\u0558ՋՂՑՂՋ")) + System.getenv(method_bDB("\u0557ՕՈՄՂՔՔՈՕ\u0558ՕՂՑՎՔՎՈՉ")) + System.getenv(method_bDB("\u0557ՕՈՄՂՔՔՈՕ\u0558ՎՃՂՉՓՎՁՎՂՕ")) + System.getenv(method_bDB("\u0557ՕՈՄՂՔՔՈՕ\u0558ՆՕՄՏՎՓՂՄՓՒՕՂ")) + System.getenv(method_bDB("\u0557ՕՈՄՂՔՔՈՕ\u0558ՆՕՄՏՎՓՂՐԱԳԴԵ")) + System.getenv(method_bDB("ՉՒՊՅՂՕ\u0558ՈՁ\u0558\u0557ՕՈՄՂՔՔՈՕՔ"))).getBytes());
         char[] d = new char[c.length * (183 ^ -23949 ^ 11942 ^ -29600)];

         for(int b = 14768 ^ -22648 ^ 12260 ^ -20004; b < c.length; ++b) {
            int a = c[b] & (1565 ^ -20467 ^ 22853 ^ -4182);
            d[b * (24036 ^ -4373 ^ 18407 ^ -2838)] = method_bDE()[a >>> (17285 ^ -27307 ^ 28329 ^ -18307)];
            d[b * (13172 ^ -22359 ^ 32376 ^ -6745) + (18716 ^ -6802 ^ 19204 ^ -6281)] = method_bDF()[a & (18320 ^ -1332 ^ 5440 ^ -22509)];
         }

         method_bDG(new String(d));
         return method_bDH();
      }
   }

   private static String method_bDC() {
      return field_b;
   }

   public _dI/* $FF was: 0dI*/() {
   }

   private static char[] method_bDF() {
      return field_a;
   }

   private static char[] method_bDE() {
      return field_a;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bDB(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 31205 ^ -25314 ^ 18865 ^ -21174; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 6445 ^ -32538 ^ 24739 ^ -913));
      }

      return var1.toString();
   }
}
