package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class 0dr implements 0cD, 0cC {
   public boolean field_e;
   public List<0cv> field_d;
   public final 0dz field_b;
   public int field_a;
   public final String field_c;
   public boolean field_f;
   private static String _ _;

   private static 0bz method_bBn() {
      return client;
   }

   private static 0cp method_bBH() {
      return 0bL.field_a;
   }

   public int method_bBb() {
      return method_bBy(this);
   }

   private static void method_bBz(0dr var0, int var1) {
      var0.field_a = var1;
   }

   private static List method_bBA(0dr var0) {
      return var0.field_d;
   }

   public void method_bBg(boolean a) {
      method_bBD(this, a);
   }

   public 0dz method_bBa() {
      return method_bBx(this);
   }

   private static 0bz method_bBo() {
      return client;
   }

   private static 0bz method_bBp() {
      return client;
   }

   public int method_bAY() {
      if (this.method_bBf() && this.method_bBd().size() != 0) {
         int b = 490 ^ -9602 ^ 4017 ^ -11227;
         Iterator var2 = this.method_bBd().iterator();

         while(var2.hasNext()) {
            0cv a = (0cv)var2.next();
            if (a.method_bnY()) {
               b += a.method_bob() + (4805 ^ -24665 ^ 29920 ^ -1657);
            }
         }

         return b + (336 ^ -11057 ^ 7677 ^ -14213) + (16790 ^ -19391 ^ 229 ^ -2760);
      } else {
         return 29005 ^ -28650 ^ 8051 ^ -463;
      }
   }

   public boolean method_bBf() {
      return method_bBC(this);
   }

   public void method_bAU() {
      if (method_bBm().method_Qu() != null) {
         method_bBn().method_Qu().method_bxF(method_bBl("{zy~jsk"));
      }

      method_bBo().method_Qn().register(this);
   }

   private static 0bz method_bBr() {
      return client;
   }

   private static 0dz method_bBx(0dr var0) {
      return var0.field_b;
   }

   private static int method_bBt(0dr var0) {
      return var0.field_a;
   }

   private static boolean method_bBF(0dr var0) {
      return var0.field_e;
   }

   public void method_bAV() {
      if (method_bBp().method_Qu() != null) {
         method_bBq().method_Qu().method_bxF(method_bBl("{zy~jsk"));
      }

      method_bBr().method_Qn().unregister(this);
   }

   private static int method_bBy(0dr var0) {
      return var0.field_a;
   }

   public JSONObject method_bAW() {
      JSONObject b = new JSONObject();
      b.put(method_bBl("zq~}sz{"), method_bBs(this));
      b.put(method_bBl("tzf"), method_bBt(this));
      JSONObject c = new JSONObject();
      Iterator var3 = method_bBu(this).iterator();

      while(var3.hasNext()) {
         0cv a = (0cv)var3.next();
         if (a instanceof 0cp) {
            c.put(a.method_boa(), ((0cp)a).method_bna());
         } else if (a instanceof 0cs) {
            c.put(a.method_boa(), (Object)((0cs)a).method_bnq());
         } else if (a instanceof 0ct) {
            c.put(a.method_boa(), ((0ct)a).method_bnH());
         } else if (a instanceof 0cu) {
            c.put(a.method_boa(), (Object)((0cu)a).method_bnP());
         }
      }

      return b.put(method_bBl("lzkkvqxl"), (Object)c);
   }

   public void method_bBj(boolean a) {
      if (method_bBF(this) != a) {
         method_bBG(this, a);
         if (a) {
            this.method_bAU();
         } else {
            this.method_bAV();
         }

      }
   }

   private static boolean method_bBC(0dr var0) {
      return var0.field_f;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bBl(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 28733 ^ -30604 ^ 25788 ^ -25355; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 31194 ^ -24341 ^ 22239 ^ -28687));
      }

      return var1.toString();
   }

   public void method_bAX(JSONObject b) {
      this.method_bBj(b.getBoolean(method_bBl("zq~}sz{")));
      this.method_bBc(b.getInt(method_bBl("tzf")));
      JSONObject c = b.getJSONObject(method_bBl("lzkkvqxl"));
      Iterator var3 = method_bBv(this).iterator();

      while(var3.hasNext()) {
         0cv a = (0cv)var3.next();
         if (c.has(a.method_boa())) {
            if (a instanceof 0cp) {
               ((0cp)a).method_bmZ(c.getBoolean(a.method_boa()));
            } else if (a instanceof 0cs) {
               ((0cs)a).method_bns(c.getString(a.method_boa()));
            } else if (a instanceof 0ct) {
               ((0ct)a).method_bnG(c.getFloat(a.method_boa()));
            } else if (a instanceof 0cu) {
               ((0cu)a).method_bnQ(c.getString(a.method_boa()));
            }
         }
      }

   }

   private static void method_bBG(0dr var0, boolean var1) {
      var0.field_e = var1;
   }

   private static boolean method_bBE(0dr var0) {
      return var0.field_e;
   }

   public List<0cv> method_bBd() {
      return (List)method_bBA(this).stream().filter((a) -> {
         return (boolean)(a instanceof 0cr && !method_bBH().method_bna() ? 19186 ^ -22218 ^ 19572 ^ -20560 : 10578 ^ -16109 ^ 20329 ^ -22743);
      }).collect(Collectors.toList());
   }

   private static 0bz method_bBq() {
      return client;
   }

   public _dr/* $FF was: 0dr*/(String a, 0dz b, int c) {
      this.field_c = a;
      this.field_b = b;
      this.field_a = c;
      this.field_d = new ArrayList();
      this.field_f = (boolean)(21711 ^ -8365 ^ 30178 ^ -385);
   }

   private static 0bz method_bBm() {
      return client;
   }

   public void method_bBe(0cv... a) {
      method_bBB(this).addAll(Arrays.asList(a));
   }

   private static List method_bBv(0dr var0) {
      return var0.field_d;
   }

   public void method_bBc(int a) {
      method_bBz(this, a);
   }

   public void method_bBi() {
      this.method_bBj((boolean)(!this.method_bBh() ? 29614 ^ -965 ^ 4283 ^ -24785 : 5495 ^ -16542 ^ 14972 ^ -28567));
   }

   private static List method_bBB(0dr var0) {
      return var0.field_d;
   }

   private static List method_bBu(0dr var0) {
      return var0.field_d;
   }

   private static String method_bBw(0dr var0) {
      return var0.field_c;
   }

   public boolean method_bBh() {
      return method_bBE(this);
   }

   public String method_bAZ() {
      return method_bBw(this);
   }

   private static void method_bBD(0dr var0, boolean var1) {
      var0.field_f = var1;
   }

   private static boolean method_bBs(0dr var0) {
      return var0.field_e;
   }
}
