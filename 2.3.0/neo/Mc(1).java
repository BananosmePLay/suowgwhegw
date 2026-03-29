package neo;

public abstract class Mc extends Mg {
   private int rideCooldownCounter;

   public Mc(bij p_i47410_1_) {
      super(p_i47410_1_);
   }

   public boolean setEntityOnShoulder(ME p_191994_1_) {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setString("id", this.getEntityString());
      this.writeToNBT(nbttagcompound);
      if (p_191994_1_.addShoulderEntity(nbttagcompound)) {
         this.world.removeEntity(this);
         return true;
      } else {
         return false;
      }
   }

   public void onUpdate() {
      ++this.rideCooldownCounter;
      super.onUpdate();
   }

   public boolean canSitOnShoulder() {
      return this.rideCooldownCounter > 100;
   }
}
