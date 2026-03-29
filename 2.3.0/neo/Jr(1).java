package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumParticleTypes;

public class Jr extends Ig {
   private static final Rd<Integer> FUSE;
   @Nullable
   private Iw tntPlacedBy;
   private int fuse;

   public Jr(bij worldIn) {
      super(worldIn);
      this.fuse = 80;
      this.preventEntitySpawning = true;
      this.isImmuneToFire = true;
      this.setSize(0.98F, 0.98F);
   }

   public Jr(bij worldIn, double x, double y, double z, Iw igniter) {
      this(worldIn);
      this.setPosition(x, y, z);
      float f = (float)(Math.random() * 6.283185307179586);
      this.motionX = (double)(-((float)Math.sin((double)f)) * 0.02F);
      this.motionY = 0.20000000298023224;
      this.motionZ = (double)(-((float)Math.cos((double)f)) * 0.02F);
      this.setFuse(80);
      this.prevPosX = x;
      this.prevPosY = y;
      this.prevPosZ = z;
      this.tntPlacedBy = igniter;
   }

   protected void entityInit() {
      this.dataManager.register(FUSE, 80);
   }

   protected boolean canTriggerWalking() {
      return false;
   }

   public boolean canBeCollidedWith() {
      return !this.isDead;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      if (!this.hasNoGravity()) {
         this.motionY -= 0.03999999910593033;
      }

      this.move(Lq.SELF, this.motionX, this.motionY, this.motionZ);
      this.motionX *= 0.9800000190734863;
      this.motionY *= 0.9800000190734863;
      this.motionZ *= 0.9800000190734863;
      if (this.onGround) {
         this.motionX *= 0.699999988079071;
         this.motionZ *= 0.699999988079071;
         this.motionY *= -0.5;
      }

      --this.fuse;
      if (this.fuse <= 0) {
         this.setDead();
         if (!this.world.isRemote) {
            this.explode();
         }
      } else {
         this.handleWaterMovement();
         this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, this.posX, this.posY + 0.5, this.posZ, 0.0, 0.0, 0.0);
      }

   }

   private void explode() {
      float f = 4.0F;
      this.world.createExplosion(this, this.posX, this.posY + (double)(this.height / 16.0F), this.posZ, 4.0F, true);
   }

   protected void writeEntityToNBT(QQ compound) {
      compound.setShort("Fuse", (short)this.getFuse());
   }

   protected void readEntityFromNBT(QQ compound) {
      this.setFuse(compound.getShort("Fuse"));
   }

   @Nullable
   public Iw getTntPlacedBy() {
      return this.tntPlacedBy;
   }

   public float getEyeHeight() {
      return 0.0F;
   }

   public void setFuse(int fuseIn) {
      this.dataManager.set(FUSE, fuseIn);
      this.fuse = fuseIn;
   }

   public void notifyDataManagerChange(Rd<?> key) {
      if (FUSE.equals(key)) {
         this.fuse = this.getFuseDataManager();
      }

   }

   public int getFuseDataManager() {
      return (Integer)this.dataManager.get(FUSE);
   }

   public int getFuse() {
      return this.fuse;
   }

   static {
      FUSE = Rv.createKey(Jr.class, Rt.VARINT);
   }
}
