package neo;

public enum IC {
   MONSTER(Lo.class, 70, hM.AIR, false, false),
   CREATURE(Ly.class, 10, hM.AIR, true, true),
   AMBIENT(Lx.class, 15, hM.AIR, true, false),
   WATER_CREATURE(Mr.class, 5, hM.WATER, true, false);

   private final Class<? extends Mx> creatureClass;
   private final int maxNumberOfCreature;
   private final hM creatureMaterial;
   private final boolean isPeacefulCreature;
   private final boolean isAnimal;

   private IC(Class creatureClassIn, int maxNumberOfCreatureIn, hM creatureMaterialIn, boolean isPeacefulCreatureIn, boolean isAnimalIn) {
      this.creatureClass = creatureClassIn;
      this.maxNumberOfCreature = maxNumberOfCreatureIn;
      this.creatureMaterial = creatureMaterialIn;
      this.isPeacefulCreature = isPeacefulCreatureIn;
      this.isAnimal = isAnimalIn;
   }

   public Class<? extends Mx> getCreatureClass() {
      return this.creatureClass;
   }

   public int getMaxNumberOfCreature() {
      return this.maxNumberOfCreature;
   }

   public boolean getPeacefulCreature() {
      return this.isPeacefulCreature;
   }

   public boolean getAnimal() {
      return this.isAnimal;
   }
}
