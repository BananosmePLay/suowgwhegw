package neo;

import java.util.ArrayList;
import java.util.Iterator;

public class 0bN {
   public final ArrayList<0cB> modules = new ArrayList();

   public ArrayList<0cB> getModules() {
      return 1rizaYo2TV(this);
   }

   public void register(0cB module) {
      hNnU9jEueo(this).add(module);
   }

   public 0cB getModule(Class moduleClass) {
      Iterator var2 = 2zzrdrtD7L(this).iterator();

      while(var2.hasNext()) {
         0cB module = (0cB)var2.next();
         if (module.getClass() == moduleClass) {
            return module;
         }
      }

      return null;
   }

   private static ArrayList _zzrdrtD7L/* $FF was: 2zzrdrtD7L*/(0bN var0) {
      return var0.modules;
   }

   private static ArrayList Cd828bMWta(0bN var0) {
      return var0.modules;
   }

   private static ArrayList hNnU9jEueo(0bN var0) {
      return var0.modules;
   }

   public _bN/* $FF was: 0bN*/() {
      this.register(new 0cx());
      this.register(new 0cz());
      this.register(new 0cl());
      this.register(new 0cy());
      this.register(new 0ct());
      this.register(new 0cs());
      this.register(new 0cw());
      this.register(new 0cr());
      this.register(new 0cq());
      this.register(new 0ck());
      this.register(new 0cu());
      this.register(new 0cj());
      this.register(new 0cg());
      this.register(new 0bZ());
      this.register(new 0ce());
      this.register(new 0cd());
      this.register(new 0ca());
      this.register(new 0cc());
      this.register(new 0ci());
      this.register(new 0co());
      this.register(new 0ch());
      this.register(new 0bY());
      this.register(new 0cp());
      this.register(new 0cf());
      this.register(new 0cb());
      this.register(new 0cv());
      Iterator var1 = 0bK.getInstance().themeManager.getThemes().iterator();

      while(var1.hasNext()) {
         0eA theme = (0eA)var1.next();
         this.register(new 0cA(theme.getName(), theme.getOneColor(), theme.getTwoColor()));
      }

   }

   private static ArrayList _rizaYo2TV/* $FF was: 1rizaYo2TV*/(0bN var0) {
      return var0.modules;
   }

   public ArrayList<0cB> getModulesForCategory(0bV category) {
      ArrayList<0cB> returned = new ArrayList();
      Iterator var3 = Cd828bMWta(this).iterator();

      while(var3.hasNext()) {
         0cB module = (0cB)var3.next();
         if (module.getModuleCategory() == category) {
            returned.add(module);
         }
      }

      return returned;
   }
}
