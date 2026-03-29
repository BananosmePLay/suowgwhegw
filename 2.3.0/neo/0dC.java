package neo;

import io.netty.handler.proxy.HttpProxyHandler;
import io.netty.handler.proxy.ProxyHandler;
import io.netty.handler.proxy.Socks4ProxyHandler;
import io.netty.handler.proxy.Socks5ProxyHandler;
import java.net.InetSocketAddress;

public class 0dC {
   public String field_b;
   public String field_a;
   public String field_d;
   public 0dB field_c;
   private static String _ _;

   private static String method_bBY(0dC var0) {
      return var0.field_d;
   }

   private static void method_bBZ(0dC var0, String var1) {
      var0.field_d = var1;
   }

   public _dC/* $FF was: 0dC*/(String a, 0dB b) {
      this.field_b = a;
      this.field_d = method_bBU("");
      this.field_a = method_bBU("");
      this.field_c = b;
   }

   private static void method_bBX(0dC var0, String var1) {
      var0.field_b = var1;
   }

   public String toString() {
      return method_bBU("̶̯̙̍̐̇̆̑̐̄̏̍̐̇̆͂͘") + method_bCm(this) + ('繍' ^ '뿷' ^ '߆' ^ '왛') + method_bBU("͓̞̊̌̍̑̒͂̚̚͘͟") + method_bCn(this) + ('怱' ^ '躬' ^ '侤' ^ 'ꄞ') + method_bBU("̛͓̞̏̌̌̈̐̍͂͘͟") + method_bCo(this) + ('揥' ^ '蚼' ^ 'ḇ' ^ 'ﭹ') + method_bBU("͓̋̆̏͂̚͟") + method_bCp(this) + ('ኞ' ^ '鿚' ^ '纟' ^ '\uf3a6');
   }

   private static String method_bCo(0dC var0) {
      return var0.field_a;
   }

   private static void method_bCe(0dC var0, 0dB var1) {
      var0.field_c = var1;
   }

   public 0dB method_bBP() {
      return method_bCc(this);
   }

   private static String method_bCm(0dC var0) {
      return var0.field_b;
   }

   public String method_bBN() {
      return method_bCa(this);
   }

   public String method_bBL() {
      return method_bBY(this);
   }

   public String method_bBJ() {
      return method_bBW(this);
   }

   public void method_bBK(String a) {
      method_bBX(this, a);
   }

   private static 0dB method_bCc(0dC var0) {
      return var0.field_c;
   }

   public void method_bBR(0dB a) {
      method_bCe(this, a);
   }

   private static String method_bCf(0dC var0) {
      return var0.field_d;
   }

   public boolean method_bBS() {
      return (boolean)(!method_bCf(this).isEmpty() && !method_bCg(this).isEmpty() ? 19294 ^ -14922 ^ 13244 ^ -17067 : 20785 ^ -18541 ^ 11693 ^ -13553);
   }

   private static String method_bCa(0dC var0) {
      return var0.field_a;
   }

   private static String method_bCh(0dC var0) {
      return var0.field_b;
   }

   public static 0dC method_bBI() {
      return new 0dC(method_bBU("͏͑͏͑͏͑͏"), method_bBV());
   }

   private static 0dB method_bCl(0dC var0) {
      return var0.field_c;
   }

   private static void method_bCb(0dC var0, String var1) {
      var0.field_a = var1;
   }

   private static String method_bCg(0dC var0) {
      return var0.field_a;
   }

   public void method_bBO(String a) {
      method_bCb(this, a);
   }

   private static String method_bCj(0dC var0) {
      return var0.field_b;
   }

   private static String method_bBW(0dC var0) {
      return var0.field_b;
   }

   private static 0dB method_bCp(0dC var0) {
      return var0.field_c;
   }

   public ProxyHandler method_bBT() {
      if (!method_bCh(this).isEmpty() && !method_bCi(this).equals(method_bBU("͏͑͏͑͏͑͏")) && method_bCj(this).split(method_bBU("ͅ")).length == (20574 ^ -15277 ^ 14280 ^ -23609)) {
         switch (method_bCk()[method_bCl(this).ordinal()]) {
            case 1:
               return new HttpProxyHandler(new InetSocketAddress(this.method_bBJ().split(method_bBU("ͅ"))[15193 ^ -28474 ^ 1417 ^ -20970], Integer.parseInt(this.method_bBJ().split(method_bBU("ͅ"))[24917 ^ -17678 ^ 4441 ^ -13569])));
            case 2:
               return new Socks4ProxyHandler(new InetSocketAddress(this.method_bBJ().split(method_bBU("ͅ"))[3148 ^ -12831 ^ 28299 ^ -20698], Integer.parseInt(this.method_bBJ().split(method_bBU("ͅ"))[834 ^ -25509 ^ 4926 ^ -29658])));
            case 3:
               if (this.method_bBS()) {
                  return new Socks5ProxyHandler(new InetSocketAddress(this.method_bBJ().split(method_bBU("ͅ"))[32699 ^ -19969 ^ 20704 ^ -24924], Integer.parseInt(this.method_bBJ().split(method_bBU("ͅ"))[23779 ^ -18585 ^ 10225 ^ -13196])), this.method_bBL(), this.method_bBN());
               }

               return new Socks5ProxyHandler(new InetSocketAddress(this.method_bBJ().split(method_bBU("ͅ"))[9965 ^ -27789 ^ 31142 ^ -13256], Integer.parseInt(this.method_bBJ().split(method_bBU("ͅ"))[28088 ^ -18754 ^ 28263 ^ -19104])));
            default:
               return null;
         }
      } else {
         return null;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bBU(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 8204 ^ -22689 ^ 15042 ^ -17007; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 24381 ^ -10096 ^ 32381 ^ -1361));
      }

      return var1.toString();
   }

   private static String method_bCi(0dC var0) {
      return var0.field_b;
   }

   public void method_bBM(String a) {
      method_bBZ(this, a);
   }

   private static String method_bCn(0dC var0) {
      return var0.field_d;
   }

   private static int[] method_bCk() {
      return 0dA.field_a;
   }

   public boolean method_bBQ(0dB a) {
      return method_bCd(this).equals(a);
   }

   private static 0dB method_bCd(0dC var0) {
      return var0.field_c;
   }

   private static 0dB method_bBV() {
      return 0dB.field_c;
   }
}
