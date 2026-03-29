package neo;

public class hN extends hM {
   public hN(hK color) {
      super(color);
      this.setReplaceable();
      this.setNoPushMobility();
   }

   public boolean isLiquid() {
      return true;
   }

   public boolean blocksMovement() {
      return false;
   }

   public boolean isSolid() {
      return false;
   }
}
