package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Supplier;

public class 0cs extends 0cv {
   public ArrayList<String> field_c = new ArrayList();
   public int field_a;
   public boolean field_b;
   private static String _ _;

   public _cs/* $FF was: 0cs*/(String a, String b, String... c) {
      super(a);
      this.field_c.add(b);
      this.field_c.addAll(Arrays.asList(c));
      this.method_bnZ(() -> {
         return Boolean.valueOf((boolean)(24137 ^ -30049 ^ 4832 ^ -14793));
      });
   }

   public boolean method_bnr(String a) {
      return this.method_bnq().equalsIgnoreCase(a);
   }

   private static ArrayList method_bnF(0cs var0) {
      return var0.field_c;
   }

   public void method_bns(String a) {
      method_bnA(this, method_bnz(this).indexOf(a));
   }

   private static ArrayList method_bnx(0cs var0) {
      return var0.field_c;
   }

   public _cs/* $FF was: 0cs*/(String a, String b, Supplier<Boolean> c, String... d) {
      super(a);
      this.field_c.add(b);
      this.field_c.addAll(Arrays.asList(d));
      this.method_bnZ(c);
   }

   public void method_bnu(boolean a) {
      method_bnC(this, a);
   }

   private static int method_bny(0cs var0) {
      return var0.field_a;
   }

   public ArrayList<String> method_bnv() {
      return method_bnD(this);
   }

   public boolean method_bnt() {
      return method_bnB(this);
   }

   private static void method_bnC(0cs var0, boolean var1) {
      var0.field_b = var1;
   }

   public String method_bnq() {
      return (String)method_bnx(this).get(method_bny(this));
   }

   private static boolean method_bnB(0cs var0) {
      return var0.field_b;
   }

   private static void method_bnA(0cs var0, int var1) {
      var0.field_a = var1;
   }

   private static ArrayList method_bnD(0cs var0) {
      return var0.field_c;
   }

   private static boolean method_bnE(0cs var0) {
      return var0.field_b;
   }

   private static ArrayList method_bnz(0cs var0) {
      return var0.field_c;
   }

   public int method_bob() {
      return !method_bnE(this) ? 13065 ^ -29540 ^ 1489 ^ -17848 : method_bnF(this).size() * (29213 ^ -29486 ^ 13020 ^ -13285) + (2984 ^ -25122 ^ 20892 ^ -14344);
   }
}
