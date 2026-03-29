package neo;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class 0cQ extends CopyOnWriteArrayList<0dD> implements 0cE<0dD>, 0cD {
   public final 0cP field_e = new 0cP();
   public final List<String> field_b = new ArrayList();
   public static final String[] field_a;
   public static final Logger field_c = LogManager.getLogger();
   public final ScriptEngineManager field_d = new ScriptEngineManager();
   private static int _DSC GG NEOWARECLIENT _;

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_byB(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 3020 ^ -25081 ^ 30228 ^ -7201; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 17961 ^ -53335 ^ '썈' ^ -20593));
      }

      return var1.toString();
   }

   public void method_byw(0dD a) {
      this.add(a);
   }

   private static ScriptEngineManager method_byF(0cQ var0) {
      return var0.field_d;
   }

   public void method_byv(String a) {
      if (!method_byD(this).contains(a)) {
         String var10000 = method_byB("ԴԤԵԮԷԳԮԩԠթԪԢԳԯԨԣթԣԢԷԵԢԤԦԳԢԣ");
         Object[] var10001 = new Object[31685 ^ -7159 ^ 32473 ^ -7916];
         var10001[19147 ^ -11399 ^ 26458 ^ -280] = a;
         0ek.addMessage(0cT.method_byW(var10000, var10001));
         method_byE(this).add(a);
      }

   }

   public _cQ/* $FF was: 0cQ*/() {
      Executors.newSingleThreadScheduledExecutor().scheduleAtFixedRate(this::init, 0L, 5L, TimeUnit.SECONDS);
   }

   public void init() {
      try {
         File[] d = (File[])Objects.requireNonNull(0ed.method_bFf(method_byB("ըԉԢԨԐԦԵԢըԴԤԵԮԷԳԴը")).listFiles());
         if (d.length > 0) {
            File[] var2 = d;
            int var3 = d.length;

            for(int var4 = 11461 ^ -12193 ^ 22051 ^ -21831; var4 < var3; ++var4) {
               File c = var2[var4];
               if (c.getName().endsWith(method_byB("թԭԴ"))) {
                  String a = 0ee.readFile(c);
                  if (a.length() > (12801 ^ 4163557 ^ 12118 ^ 2071218)) {
                     method_byC().error(method_byB("ԒԩԦԥԫԢէԳԨէԫԨԦԣէԔԤԵԮԷԳսէԩԦԪԢպԼԺիէԴԮԽԢպԼԺէկԪԦԿէԴԮԽԢսէյշվհնղյծ"), c.getName(), a.length());
                  } else {
                     0dD b = this.method_byz(c.getName());
                     if (b == null) {
                        if (!a.isEmpty()) {
                           b = new 0dD(c.getName(), a);
                           this.method_byw(b);
                        }
                     } else if (b.method_bCx().hashCode() != a.hashCode()) {
                        b.method_bCy(a);
                     }
                  }
               }
            }
         }
      } catch (Exception var8) {
         Exception e = var8;
         e.printStackTrace();
      }

   }

   static {
      String[] var10000 = new String[31808 ^ -31328 ^ 8052 ^ -6501];
      var10000[11191 ^ -11695 ^ 19372 ^ -19894] = method_byB("ԨԩԒԷԣԦԳԢ");
      var10000[3562 ^ -16953 ^ 11880 ^ -25020] = method_byB("ԔԗԦԤԬԢԳԍԨԮԩԀԦԪԢ");
      var10000[23778 ^ -2083 ^ 30835 ^ -11442] = method_byB("ԔԗԦԤԬԢԳԗԫԦԾԢԵԋԮԴԳԏԢԦԣԢԵԁԨԨԳԢԵ");
      var10000[22155 ^ -29090 ^ 13464 ^ -5042] = method_byB("ԔԗԦԤԬԢԳԓԦԥԄԨԪԷԫԢԳԢ");
      var10000[6578 ^ -23946 ^ 27495 ^ -12121] = method_byB("ԔԗԦԤԬԢԳԗԦԵԳԮԤԫԢԴ");
      var10000[11350 ^ -20821 ^ 30941 ^ -1499] = method_byB("ԔԗԦԤԬԢԳԕԢԴԨԲԵԤԢԗԦԤԬԔԢԩԣ");
      var10000[32623 ^ -12804 ^ 22360 ^ -6707] = method_byB("ԔԗԦԤԬԢԳԒԷԣԦԳԢԅԨԴԴԎԩԡԨ");
      var10000[12118 ^ -22614 ^ 16595 ^ -14296] = method_byB("ԔԗԦԤԬԢԳԓԮԳԫԢ");
      var10000[9192 ^ -18484 ^ 15435 ^ -22425] = method_byB("ԔԗԦԤԬԢԳԔԨԲԩԣԂԡԡԢԤԳ");
      var10000[26835 ^ -32567 ^ 185 ^ -5974] = method_byB("ԔԗԦԤԬԢԳԃԮԴԤԨԩԩԢԤԳ");
      var10000[7974 ^ -15740 ^ 24491 ^ -32253] = method_byB("ԔԗԦԤԬԢԳԄԯԦԳ");
      var10000[10386 ^ -29683 ^ 21583 ^ -3877] = method_byB("ԠԢԳԆԲԳԯԨԵ");
      var10000[18783 ^ -27081 ^ 29220 ^ -21184] = method_byB("ԠԢԳԉԦԪԢ");
      var10000[28021 ^ -29933 ^ 17590 ^ -23843] = method_byB("ԨԩԅԨԳԄԦԷԳԤԯԦ");
      var10000[23967 ^ -20871 ^ 28594 ^ -25510] = method_byB("ԨԩԋԨԠԮԩ");
      field_a = var10000;
   }

   private static List method_byD(0cQ var0) {
      return var0.field_b;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void register(Object var1) {
      this.method_byw((0dD)var1);
   }

   public 0cP method_byy() {
      return method_byG(this);
   }

   private static 0cP method_byG(0cQ var0) {
      return var0.field_e;
   }

   public boolean method_byu(String b, String[] c) {
      boolean d = 13836 ^ -32191 ^ 18038 ^ -3525;
      Iterator var4 = this.iterator();

      while(var4.hasNext()) {
         0dD a = (0dD)var4.next();
         if (a.method_bCt(b, c)) {
            d = 2510 ^ -2754 ^ 21753 ^ -22520;
         }
      }

      return (boolean)d;
   }

   public ScriptEngine method_byx() {
      return method_byF(this).getEngineByName(method_byB("ԭԴ"));
   }

   private static List method_byE(0cQ var0) {
      return var0.field_b;
   }

   public 0dD method_byz(String a) {
      return (0dD)this.stream().filter((b) -> {
         return b.method_bCw().equalsIgnoreCase(a);
      }).findAny().orElse((Object)null);
   }

   private static Logger method_byC() {
      return field_c;
   }
}
