package neo;

public class hO extends hM {
   public hO(hK color) {
      super(color);
      this.setAdventureModeExempt();
   }

   public boolean isSolid() {
      return false;
   }

   public boolean blocksLight() {
      return false;
   }

   public boolean blocksMovement() {
      return false;
   }
}
