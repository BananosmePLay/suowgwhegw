package neo;

import com.google.gson.JsonObject;
import java.util.Iterator;

public class 0dM {
   private static String P4S6ogGLOW() {
      return 0bK.VERSION_TYPE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   private static String leQk1a7AJD(String var0) {
      StringBuilder var1 = new StringBuilder();

      for(int var2 = 30151 ^ -9428 ^ 29887 ^ -9644; var2 < var0.length(); ++var2) {
         var1.append((char)(var0.charAt(var2) ^ 19786 ^ -34898 ^ '춥' ^ -3124));
      }

      return var1.toString();
   }

   public _dM/* $FF was: 0dM*/() {
   }

   private static 0bO xFLfkQPhdK(0bK var0) {
      return var0.themeManager;
   }

   private static void RvY9r81lQl(0eA var0) {
      0br.currentTheme = var0;
   }

   private static 0eA peFDxibNkg() {
      return 0br.currentTheme;
   }

   private static 0bN noIVaWheZe(0bK var0) {
      return var0.moduleManager;
   }

   public static JsonObject save() {
      JsonObject jsonObject = new JsonObject();
      JsonObject modulesObject = new JsonObject();
      Iterator var2 = noIVaWheZe(0bK.getInstance()).getModules().iterator();

      while(var2.hasNext()) {
         0cB module = (0cB)var2.next();
         if (!module.getModuleCategory().equals(6Y7LIjgiJv())) {
            modulesObject.add(module.getModuleName(), module.save());
         }
      }

      jsonObject.add(leQk1a7AJD("ӋӨӬӹӸӿӨӾ"), modulesObject);
      jsonObject.addProperty(leQk1a7AJD("ӛӨӿӾӤӢӣ"), rOAxY0jeUI());
      jsonObject.addProperty(leQk1a7AJD("әӥӨӠӨ"), zadwBHt76N() != null ? peFDxibNkg().getName() : V4aR2ojnoB(0bK.getInstance()).getDefaultTheme().getName());
      return jsonObject;
   }

   public static void load(JsonObject object) {
      try {
         if (object.get(leQk1a7AJD("ӛӨӿӾӤӢӣ")) == null) {
            return;
         }

         if (!object.get(leQk1a7AJD("ӛӨӿӾӤӢӣ")).getAsString().equals(P4S6ogGLOW())) {
            return;
         }
      } catch (Exception var5) {
         return;
      }

      try {
         RvY9r81lQl(xFLfkQPhdK(0bK.getInstance()).getThemeByName(object.get(leQk1a7AJD("әӥӨӠӨ")).getAsString()));
      } catch (Exception var4) {
         Exception e = var4;
         e.printStackTrace();
      }

      if (object.has(leQk1a7AJD("ӋӨӬӹӸӿӨӾ"))) {
         JsonObject modulesObject = object.getAsJsonObject(leQk1a7AJD("ӋӨӬӹӸӿӨӾ"));
         Iterator var2 = dggqiAtSlT(0bK.getInstance()).getModules().iterator();

         while(var2.hasNext()) {
            0cB module = (0cB)var2.next();
            module.setState((boolean)(21431 ^ -23977 ^ 11219 ^ -9677));
            module.load(modulesObject.getAsJsonObject(module.getModuleName()));
         }
      }

   }

   private static 0bO V4aR2ojnoB(0bK var0) {
      return var0.themeManager;
   }

   private static String rOAxY0jeUI() {
      return 0bK.VERSION_TYPE;
   }

   private static 0bN dggqiAtSlT(0bK var0) {
      return var0.moduleManager;
   }

   private static 0bV _Y7LIjgiJv/* $FF was: 6Y7LIjgiJv*/() {
      return 0bV.Themes;
   }

   private static 0eA zadwBHt76N() {
      return 0br.currentTheme;
   }
}
