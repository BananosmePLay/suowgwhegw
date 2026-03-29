package neo;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class fp extends de {
   private static final AxisAlignedBB[] POTATO_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.1875, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.3125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.4375, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5625, 1.0)};

   public fp() {
   }

   protected OL getSeed() {
      return NK.POTATO;
   }

   protected OL getCrop() {
      return NK.POTATO;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      if (!worldIn.isRemote && this.isMaxAge(state) && worldIn.rand.nextInt(50) == 0) {
         spawnAsEntity(worldIn, pos, new Qy(NK.POISONOUS_POTATO));
      }

   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return POTATO_AABB[(Integer)state.getValue(this.getAgeProperty())];
   }
}
