package neo;

import net.minecraft.util.EnumFacing;

public abstract class EQ extends EP {
   public EQ() {
   }

   public Qy dispenseStack(ET source, Qy stack) {
      bij world = source.getWorld();
      EW iposition = dk.getDispensePosition(source);
      EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
      IJ iprojectile = this.getProjectileEntity(world, iposition, stack);
      iprojectile.shoot((double)enumfacing.getXOffset(), (double)((float)enumfacing.getYOffset() + 0.1F), (double)enumfacing.getZOffset(), this.getProjectileVelocity(), this.getProjectileInaccuracy());
      world.spawnEntity((Ig)iprojectile);
      stack.shrink(1);
      return stack;
   }

   protected void playDispenseSound(ET source) {
      source.getWorld().playEvent(1002, source.getBlockPos(), 0);
   }

   protected abstract IJ getProjectileEntity(bij var1, EW var2, Qy var3);

   protected float getProjectileInaccuracy() {
      return 6.0F;
   }

   protected float getProjectileVelocity() {
      return 1.1F;
   }
}
