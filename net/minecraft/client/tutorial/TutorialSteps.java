package net.minecraft.client.tutorial;

import java.util.function.Function;

public enum TutorialSteps {
   MOVEMENT("movement", MovementStep::new),
   FIND_TREE("find_tree", FindTreeStep::new),
   PUNCH_TREE("punch_tree", PunchTreeStep::new),
   OPEN_INVENTORY("open_inventory", OpenInventoryStep::new),
   CRAFT_PLANKS("craft_planks", CraftPlanksStep::new),
   NONE("none", CompletedTutorialStep::new);

   private final String name;
   private final Function<Tutorial, ? extends ITutorialStep> tutorial;

   private TutorialSteps(String nameIn, Function constructor) {
      this.name = nameIn;
      this.tutorial = constructor;
   }

   public ITutorialStep create(Tutorial tutorial) {
      return (ITutorialStep)this.tutorial.apply(tutorial);
   }

   public String getName() {
      return this.name;
   }

   public static TutorialSteps getTutorial(String tutorialName) {
      TutorialSteps[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         TutorialSteps tutorialsteps = var1[var3];
         if (tutorialsteps.name.equals(tutorialName)) {
            return tutorialsteps;
         }
      }

      return NONE;
   }
}
