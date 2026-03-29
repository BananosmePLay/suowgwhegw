package neo;

public abstract class Jv extends Kl {
   protected static final Rd<Byte> AGGRESSIVE;

   public Jv(bij p_i47509_1_) {
      super(p_i47509_1_);
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(AGGRESSIVE, (byte)0);
   }

   protected boolean isAggressive(int mask) {
      int i = (Byte)this.dataManager.get(AGGRESSIVE);
      return (i & mask) != 0;
   }

   protected void setAggressive(int mask, boolean value) {
      int i = (Byte)this.dataManager.get(AGGRESSIVE);
      if (value) {
         i |= mask;
      } else {
         i &= ~mask;
      }

      this.dataManager.set(AGGRESSIVE, (byte)(i & 255));
   }

   public IB getCreatureAttribute() {
      return IB.ILLAGER;
   }

   public Ju getArmPose() {
      return Ju.CROSSED;
   }

   static {
      AGGRESSIVE = Rv.createKey(Jv.class, Rt.BYTE);
   }
}
