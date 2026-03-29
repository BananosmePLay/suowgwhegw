package neo;

import java.util.Random;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class cD extends de {
   public static final hZ BEETROOT_AGE = hZ.create("age", 0, 3);
   private static final AxisAlignedBB[] BEETROOT_AABB = new AxisAlignedBB[]{new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.125, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.25, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.375, 1.0), new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.5, 1.0)};

   public cD() {
   }

   protected hZ getAgeProperty() {
      return BEETROOT_AGE;
   }

   public int getMaxAge() {
      return 3;
   }

   protected OL getSeed() {
      return NK.BEETROOT_SEEDS;
   }

   protected OL getCrop() {
      return NK.BEETROOT;
   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (rand.nextInt(3) == 0) {
         this.checkAndDropBlock(worldIn, pos, state);
      } else {
         super.updateTick(worldIn, pos, state, rand);
      }

   }

   protected int getBonemealAgeIncrease(bij worldIn) {
      return super.getBonemealAgeIncrease(worldIn) / 3;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{BEETROOT_AGE});
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return BEETROOT_AABB[(Integer)state.getValue(this.getAgeProperty())];
   }
}
