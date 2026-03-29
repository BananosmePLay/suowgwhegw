package neo;

public enum dP {
   YELLOW,
   RED;

   private dP() {
   }

   public dS getBlock() {
      return this == YELLOW ? Nk.YELLOW_FLOWER : Nk.RED_FLOWER;
   }
}
