package neo;

import java.util.Iterator;

public class 0O extends 0M {
   public final String field_a;
   private static int _DSC GG NEOWARECLIENT _;

   private static String method_kG(0O var0) {
      return var0.field_a;
   }

   public String method_kB() {
      Iterator var1 = method_kF().method_Qv().method_bwh().iterator();

      String a;
      do {
         if (!var1.hasNext()) {
            return null;
         }

         0dD b = (0dD)var1.next();
         String var10001 = method_kG(this);
         Object[] var10002 = new Object[4913 ^ -23633 ^ 6161 ^ -22386];
         var10002[24319 ^ -19121 ^ 6829 ^ -3811] = null;
         a = b.method_bCs(var10001, var10002);
      } while(a == null);

      return a;
   }

   public _O/* $FF was: 0O*/(String a, String b) {
      super(a);
      this.field_a = b;
   }

   private static 0bz method_kF() {
      return client;
   }
}
