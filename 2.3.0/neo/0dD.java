package neo;

import java.util.Arrays;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class 0dD implements 0cB {
   public String field_c;
   public final String field_b;
   public final 0dF field_a;
   public boolean field_e;
   public ScriptEngine field_d;
   private static int _DSC GG NEOWARECLIENT _;

   private static void method_bCE(0dD var0, ScriptEngine var1) {
      var0.field_d = var1;
   }

   private void method_bCB(String a, Class<?> b) throws ScriptException {
      ScriptEngine var10000 = method_bDd(this);
      String var10001 = method_bCC("څڒځۓۖڀۓێۓڹڒڅڒ\u06ddڇڊڃږۛ۔ۖڀ۔ۚۈ");
      Object[] var10002 = new Object[29507 ^ -118 ^ 4256 ^ -25493];
      var10002[24841 ^ -1473 ^ 19520 ^ -10378] = a;
      var10002[24923 ^ -6215 ^ 6456 ^ -24613] = b.getCanonicalName();
      var10000.eval(String.format(var10001, var10002));
   }

   private static ScriptEngine method_bCP(0dD var0) {
      return var0.field_d;
   }

   private static String method_bCR(0dD var0) {
      return var0.field_b;
   }

   public boolean method_bCv() {
      return method_bCS(this);
   }

   private static ScriptEngine method_bCK(0dD var0) {
      return var0.field_d;
   }

   private static ScriptEngine method_bCJ(0dD var0) {
      return var0.field_d;
   }

   public void method_bCr(String c, Object... d) {
      if (method_bCJ(this) != null && this.method_bCv()) {
         try {
            ((Invocable)method_bCK(this)).invokeFunction(c, d);
         } catch (NoSuchMethodException var4) {
            NoSuchMethodException a = var4;
            if (!Arrays.asList(method_bCL()).contains(c)) {
               this.method_bCu(a);
            }
         } catch (Exception var5) {
            Exception b = var5;
            this.method_bCu(b);
         }
      }

   }

   private static 0dF method_bCI(0dD var0) {
      return var0.field_a;
   }

   private static void method_bCV(0dD var0, String var1) {
      var0.field_c = var1;
   }

   private static String method_bCU(0dD var0) {
      return var0.field_c;
   }

   private static 0dE method_bCW() {
      return 0dE.field_b;
   }

   public void method_bCA() {
      if (this.method_bCz().method_bDg().getStage() == 0) {
         method_bCY().method_bwh().method_byy().method_byk(this);
      } else if (this.method_bCz().method_bDg().getStage() == (1295 ^ -23364 ^ 25396 ^ -15738)) {
         method_bDa(this, (boolean)(!method_bCZ(this) ? 23170 ^ -31001 ^ 19895 ^ -28205 : 10016 ^ -16298 ^ 29263 ^ -27335));
         if (method_bDb(this)) {
            this.method_bCq();
         } else {
            method_bDc(this, (ScriptEngine)null);
         }

      }
   }

   private void method_bCu(Exception a) {
      a.printStackTrace();
      if (method_bCQ().method_bna()) {
         String var10000 = method_bCC("ٔڐڠڐځښڃڇڶڋڐږڃڇښڜڝۉۓڶځځڜځۓښڝۓڕښڟږۓۖڀ");
         Object[] var10001 = new Object[28861 ^ -7710 ^ 25114 ^ -3260];
         var10001[25444 ^ -22560 ^ 14110 ^ -3174] = method_bCR(this);
         0ek.addMessage(String.format(var10000, var10001));
         0ek.addException(a);
      }

   }

   private static ScriptEngine method_bCM(0dD var0) {
      return var0.field_d;
   }

   private static String method_bCT(0dD var0) {
      return var0.field_b;
   }

   private static void method_bDc(0dD var0, ScriptEngine var1) {
      var0.field_d = var1;
   }

   private static 0dF method_bCH(0dD var0) {
      return var0.field_a;
   }

   private static String method_bCG(0dD var0) {
      return var0.field_c;
   }

   public 0dF method_bCz() {
      return method_bCX(this);
   }

   private static 0cG method_bCY() {
      return botManager;
   }

   public void method_bCy(String a) {
      method_bCV(this, a);
      this.method_bCz().method_bDj(method_bCW());
   }

   private static 0cp method_bCQ() {
      return 0bF.field_f;
   }

   private static boolean method_bCS(0dD var0) {
      return var0.field_e;
   }

   private static ScriptEngine method_bCF(0dD var0) {
      return var0.field_d;
   }

   public String method_bCs(String a, Object... b) {
      if (method_bCM(this) != null && this.method_bCv()) {
         try {
            return (String)((Invocable)method_bCN(this)).invokeFunction(a, b);
         } catch (Exception var4) {
         }
      }

      return null;
   }

   private static boolean method_bCZ(0dD var0) {
      return var0.field_e;
   }

   private static void method_bDa(0dD var0, boolean var1) {
      var0.field_e = var1;
   }

   private static ScriptEngine method_bCO(0dD var0) {
      return var0.field_d;
   }

   public _dD/* $FF was: 0dD*/(String a, String b) {
      this.field_b = a;
      this.field_c = b;
      this.field_a = new 0dF();
   }

   private static ScriptEngine method_bCN(0dD var0) {
      return var0.field_d;
   }

   public boolean method_bCt(String c, String[] d) {
      if (method_bCO(this) != null && this.method_bCv()) {
         try {
            Invocable var10000 = (Invocable)method_bCP(this);
            String var10001 = method_bCC("ڜڝڰڟښږڝڇڰڜڞڞڒڝڗ");
            Object[] var10002 = new Object[2216 ^ -1736 ^ 7413 ^ -4761];
            var10002[12821 ^ -3099 ^ 32582 ^ -16714] = c.toLowerCase();
            var10002[2109 ^ -28174 ^ 31813 ^ -6773] = d;
            var10000.invokeFunction(var10001, var10002);
            return (boolean)(9797 ^ -28297 ^ 1591 ^ -20220);
         } catch (NoSuchMethodException var4) {
            return (boolean)(6487 ^ -19133 ^ 16496 ^ -5020);
         } catch (Exception var5) {
            Exception b = var5;
            this.method_bCu(b);
            return (boolean)(25332 ^ -14041 ^ 3599 ^ -23075);
         }
      } else {
         return (boolean)(3512 ^ -30278 ^ 31709 ^ -33);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bCC(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 1887 ^ -7759 ^ 503 ^ -6375; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31213 ^ -26317 ^ 29445 ^ -27352));
      }

      return var1.toString();
   }

   private void method_bCq() {
      try {
         method_bCE(this, method_bCD().method_bwh().method_byx());
         this.method_bCB(method_bCC("ڠڐځښڃڇڲڣں"), 0cZ.class);
         this.method_bCB(method_bCC("ڣڟڒڐږڛڜڟڗږځڲڣں"), 0V.class);
         this.method_bCB(method_bCC("ڱڜڇڦڇښڟ"), 0X.class);
         this.method_bCB(method_bCC("ڰڛڒڇڦڇښڟ"), 0ek.class);
         this.method_bCB(method_bCC("ھښڝږڐځڒڕڇ"), nC.class);
         this.method_bCB(method_bCC("ڡڒڝڗڜڞڦڇښڟ"), 0ec.class);
         this.method_bCB(method_bCC("ھڒڇڛڦڇښڟ"), 0eb.class);
         this.method_bCB(method_bCC("ڵښڟږڦڇښڟ"), 0ee.class);
         this.method_bCB(method_bCC("ڠږځڅږځڦڇښڟ"), 0ef.class);
         this.method_bCB(method_bCC("ڧڛځږڒڗڦڇښڟ"), 0eh.class);
         method_bCF(this).eval(method_bCG(this));
         method_bCH(this).method_bDh(this.method_bCs(method_bCC("ڔږڇڽڒڞږ")));
         method_bCI(this).method_bDi(this.method_bCs(method_bCC("ڔږڇڲچڇڛڜځ")));
      } catch (Exception var2) {
         Exception a = var2;
         a.printStackTrace();
      }

   }

   public String method_bCx() {
      return method_bCU(this);
   }

   private static ScriptEngine method_bDd(0dD var0) {
      return var0.field_d;
   }

   private static String[] method_bCL() {
      return 0cQ.field_a;
   }

   private static 0cG method_bCD() {
      return botManager;
   }

   private static 0dF method_bCX(0dD var0) {
      return var0.field_a;
   }

   public String method_bCw() {
      return method_bCT(this);
   }

   private static boolean method_bDb(0dD var0) {
      return var0.field_e;
   }
}
