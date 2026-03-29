package neo;

import org.json.JSONObject;

public class 0cK implements 0cC {
   private static int _DSC GG NEOWARECLIENT _;

   public void method_bxF(String d) {
      try {
         JSONObject a = new JSONObject();
         JSONObject b = new JSONObject();
         method_bxK().method_Qs().forEach((bx) -> {
            b.put(bx.method_bAZ(), (Object)bx.method_bAW());
         });
         a.put(method_bxJ("ɜɏɘəɃɅɄ"), (Object)0dH.method_bDy());
         a.put(method_bxJ("ɞɂɏɇɏ"), (Object)method_bxL().method_Qt().method_byM().method_bDt());
         a.put(method_bxJ("ɇɅɎɟɆɏə"), (Object)b);
         0ee.saveFile(0ed.method_bFf(method_bxJ("ȅɤɏɅɽɋɘɏȅɉɅɄɌɃɍəȅ") + d + method_bxJ("ȄɉɌɍ")), a.toString());
      } catch (Exception var4) {
         Exception c = var4;
         c.printStackTrace();
      }

   }

   private static 0bz method_bxM() {
      return client;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String method_bxJ(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 12699 ^ -13895 ^ 9144 ^ -9318; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 19291 ^ -32499 ^ 31380 ^ -19736));
      }

      return var1.toString();
   }

   private static 0bz method_bxN() {
      return client;
   }

   private static 0bz method_bxL() {
      return client;
   }

   public _cK/* $FF was: 0cK*/() {
      this.method_bxG(method_bxJ("ɎɏɌɋɟɆɞ"));
   }

   private static 0bz method_bxK() {
      return client;
   }

   public void method_bxG(String d) {
      try {
         if (!0ee.fileExists(0ed.method_bFf(method_bxJ("ȅɤɏɅɽɋɘɏȅɉɅɄɌɃɍəȅ") + d + method_bxJ("ȄɉɌɍ")))) {
            return;
         }

         JSONObject a = new JSONObject(0ee.readFile(0ed.method_bFf(method_bxJ("ȅɤɏɅɽɋɘɏȅɉɅɄɌɃɍəȅ") + d + method_bxJ("ȄɉɌɍ"))));
         if (!a.has(method_bxJ("ɜɏɘəɃɅɄ")) || !a.has(method_bxJ("ɞɂɏɇɏ")) || !a.has(method_bxJ("ɇɅɎɟɆɏə")) || !a.getString(method_bxJ("ɜɏɘəɃɅɄ")).equals(0dH.method_bDy())) {
            return;
         }

         JSONObject b = a.getJSONObject(method_bxJ("ɇɅɎɟɆɏə"));
         method_bxM().method_Qt().method_byO(a.getString(method_bxJ("ɞɂɏɇɏ")));
         method_bxN().method_Qs().forEach((bx) -> {
            bx.method_bBj((boolean)(29831 ^ -7306 ^ 12739 ^ -22990));
            bx.method_bAX(b.getJSONObject(bx.method_bAZ()));
         });
      } catch (Exception var4) {
         Exception c = var4;
         c.printStackTrace();
      }

   }
}
