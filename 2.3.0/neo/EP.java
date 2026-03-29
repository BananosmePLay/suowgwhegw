package neo;

import net.minecraft.util.EnumFacing;

public class EP implements ES {
   public EP() {
   }

   public final Qy dispense(ET source, Qy stack) {
      Qy itemstack = this.dispenseStack(source, stack);
      this.playDispenseSound(source);
      this.spawnDispenseParticles(source, (EnumFacing)source.getBlockState().getValue(dk.FACING));
      return itemstack;
   }

   protected Qy dispenseStack(ET source, Qy stack) {
      EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
      EW iposition = dk.getDispensePosition(source);
      Qy itemstack = stack.splitStack(1);
      doDispense(source.getWorld(), itemstack, 6, enumfacing, iposition);
      return stack;
   }

   public static void doDispense(bij worldIn, Qy stack, int speed, EnumFacing facing, EW position) {
      double d0 = position.getX();
      double d1 = position.getY();
      double d2 = position.getZ();
      if (facing.getAxis() == EnumFacing.Axis.Y) {
         d1 -= 0.125;
      } else {
         d1 -= 0.15625;
      }

      IY entityitem = new IY(worldIn, d0, d1, d2, stack);
      double d3 = worldIn.rand.nextDouble() * 0.1 + 0.2;
      entityitem.motionX = (double)facing.getXOffset() * d3;
      entityitem.motionY = 0.20000000298023224;
      entityitem.motionZ = (double)facing.getZOffset() * d3;
      entityitem.motionX += worldIn.rand.nextGaussian() * 0.007499999832361937 * (double)speed;
      entityitem.motionY += worldIn.rand.nextGaussian() * 0.007499999832361937 * (double)speed;
      entityitem.motionZ += worldIn.rand.nextGaussian() * 0.007499999832361937 * (double)speed;
      worldIn.spawnEntity(entityitem);
   }

   protected void playDispenseSound(ET source) {
      source.getWorld().playEvent(1000, source.getBlockPos(), 0);
   }

   protected void spawnDispenseParticles(ET source, EnumFacing facingIn) {
      source.getWorld().playEvent(2000, source.getBlockPos(), this.getWorldEventDataFrom(facingIn));
   }

   private int getWorldEventDataFrom(EnumFacing facingIn) {
      return facingIn.getXOffset() + 1 + (facingIn.getZOffset() + 1) * 3;
   }
}
