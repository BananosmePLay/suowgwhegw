package neo;

public class hP extends hM {
   public hP(hK color) {
      super(color);
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
