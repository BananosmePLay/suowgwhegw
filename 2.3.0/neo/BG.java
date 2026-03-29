package neo;

import java.util.function.Function;

public enum BG {
   MOVEMENT("movement", BB::new),
   FIND_TREE("find_tree", Bz::new),
   PUNCH_TREE("punch_tree", BE::new),
   OPEN_INVENTORY("open_inventory", BC::new),
   CRAFT_PLANKS("craft_planks", By::new),
   NONE("none", Bx::new);

   private final String name;
   private final Function<BF, ? extends BA> tutorial;

   private BG(String nameIn, Function constructor) {
      this.name = nameIn;
      this.tutorial = constructor;
   }

   public BA create(BF tutorial) {
      return (BA)this.tutorial.apply(tutorial);
   }

   public String getName() {
      return this.name;
   }

   public static BG getTutorial(String tutorialName) {
      BG[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         BG tutorialsteps = var1[var3];
         if (tutorialsteps.name.equals(tutorialName)) {
            return tutorialsteps;
         }
      }

      return NONE;
   }
}
