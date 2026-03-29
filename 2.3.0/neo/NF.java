package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class NF extends EP {
   private final EP dispenseBehavior = new EP();
   private final IQ boatType;

   public NF(IQ boatTypeIn) {
      this.boatType = boatTypeIn;
   }

   public Qy dispenseStack(ET source, Qy stack) {
      EnumFacing enumfacing = (EnumFacing)source.getBlockState().getValue(dk.FACING);
      bij world = source.getWorld();
      double d0 = source.getX() + (double)((float)enumfacing.getXOffset() * 1.125F);
      double d1 = source.getY() + (double)((float)enumfacing.getYOffset() * 1.125F);
      double d2 = source.getZ() + (double)((float)enumfacing.getZOffset() * 1.125F);
      BlockPos blockpos = source.getBlockPos().offset(enumfacing);
      hM material = world.getBlockState(blockpos).getMaterial();
      double d3;
      if (hM.WATER.equals(material)) {
         d3 = 1.0;
      } else {
         if (!hM.AIR.equals(material) || !hM.WATER.equals(world.getBlockState(blockpos.down()).getMaterial())) {
            return this.dispenseBehavior.dispense(source, stack);
         }

         d3 = 0.0;
      }

      IR entityboat = new IR(world, d0, d1 + d3, d2);
      entityboat.setBoatType(this.boatType);
      entityboat.rotationYaw = enumfacing.getHorizontalAngle();
      world.spawnEntity(entityboat);
      stack.shrink(1);
      return stack;
   }

   protected void playDispenseSound(ET source) {
      source.getWorld().playEvent(1000, source.getBlockPos(), 0);
   }
}
