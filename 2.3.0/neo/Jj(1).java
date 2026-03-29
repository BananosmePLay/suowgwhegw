package neo;

import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Jj extends Jc {
   private static final Rd<Boolean> POWERED;
   private int fuel;
   public double pushX;
   public double pushZ;

   public Jj(bij worldIn) {
      super(worldIn);
   }

   public Jj(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
   }

   public static void registerFixesMinecartFurnace(DataFixer fixer) {
      Jc.registerFixesMinecart(fixer, Jj.class);
   }

   public Jb getType() {
      return Jb.FURNACE;
   }

   protected void entityInit() {
      super.entityInit();
      this.dataManager.register(POWERED, false);
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.fuel > 0) {
         --this.fuel;
      }

      if (this.fuel <= 0) {
         this.pushX = 0.0;
         this.pushZ = 0.0;
      }

      this.setMinecartPowered(this.fuel > 0);
      if (this.isMinecartPowered() && this.rand.nextInt(4) == 0) {
         this.world.spawnParticle(EnumParticleTypes.SMOKE_LARGE, this.posX, this.posY + 0.8, this.posZ, 0.0, 0.0, 0.0);
      }

   }

   protected double getMaximumSpeed() {
      return 0.2;
   }

   public void killMinecart(DamageSource source) {
      super.killMinecart(source);
      if (!source.isExplosion() && this.world.getGameRules().getBoolean("doEntityDrops")) {
         this.entityDropItem(new Qy(Nk.FURNACE, 1), 0.0F);
      }

   }

   protected void moveAlongTrack(BlockPos pos, in state) {
      super.moveAlongTrack(pos, state);
      double d0 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if (d0 > 1.0E-4 && this.motionX * this.motionX + this.motionZ * this.motionZ > 0.001) {
         d0 = (double)MathHelper.sqrt(d0);
         this.pushX /= d0;
         this.pushZ /= d0;
         if (this.pushX * this.motionX + this.pushZ * this.motionZ < 0.0) {
            this.pushX = 0.0;
            this.pushZ = 0.0;
         } else {
            double d1 = d0 / this.getMaximumSpeed();
            this.pushX *= d1;
            this.pushZ *= d1;
         }
      }

   }

   protected void applyDrag() {
      double d0 = this.pushX * this.pushX + this.pushZ * this.pushZ;
      if (d0 > 1.0E-4) {
         d0 = (double)MathHelper.sqrt(d0);
         this.pushX /= d0;
         this.pushZ /= d0;
         double d1 = 1.0;
         this.motionX *= 0.800000011920929;
         this.motionY *= 0.0;
         this.motionZ *= 0.800000011920929;
         this.motionX += this.pushX * 1.0;
         this.motionZ += this.pushZ * 1.0;
      } else {
         this.motionX *= 0.9800000190734863;
         this.motionY *= 0.0;
         this.motionZ *= 0.9800000190734863;
      }

      super.applyDrag();
   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      Qy itemstack = player.getHeldItem(hand);
      if (itemstack.getItem() == NK.COAL && this.fuel + 3600 <= 32000) {
         if (!player.capabilities.isCreativeMode) {
            itemstack.shrink(1);
         }

         this.fuel += 3600;
      }

      this.pushX = this.posX - player.posX;
      this.pushZ = this.posZ - player.posZ;
      return true;
   }

   protected void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setDouble("PushX", this.pushX);
      compound.setDouble("PushZ", this.pushZ);
      compound.setShort("Fuel", (short)this.fuel);
   }

   protected void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.pushX = compound.getDouble("PushX");
      this.pushZ = compound.getDouble("PushZ");
      this.fuel = compound.getShort("Fuel");
   }

   protected boolean isMinecartPowered() {
      return (Boolean)this.dataManager.get(POWERED);
   }

   protected void setMinecartPowered(boolean p_94107_1_) {
      this.dataManager.set(POWERED, p_94107_1_);
   }

   public in getDefaultDisplayTile() {
      return (this.isMinecartPowered() ? Nk.LIT_FURNACE : Nk.FURNACE).getDefaultState().withProperty(dY.FACING, EnumFacing.NORTH);
   }

   static {
      POWERED = Rv.createKey(Jj.class, Rt.BOOLEAN);
   }
}
