package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dg extends cI {
   protected static final AxisAlignedBB DEAD_BUSH_AABB = new AxisAlignedBB(0.09999999403953552, 0.0, 0.09999999403953552, 0.8999999761581421, 0.800000011920929, 0.8999999761581421);

   protected dg() {
      super(hM.VINE);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return DEAD_BUSH_AABB;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      return hK.WOOD;
   }

   protected boolean canSustainBush(in state) {
      return state.getBlock() == Nk.SAND || state.getBlock() == Nk.HARDENED_CLAY || state.getBlock() == Nk.STAINED_HARDENED_CLAY || state.getBlock() == Nk.DIRT;
   }

   public boolean isReplaceable(bfZ worldIn, BlockPos pos) {
      return true;
   }

   public int quantityDropped(Random random) {
      return random.nextInt(3);
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.STICK;
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (!worldIn.isRemote && stack.getItem() == NK.SHEARS) {
         player.addStat(XV.getBlockStats(this));
         spawnAsEntity(worldIn, pos, new Qy(Nk.DEADBUSH, 1, 0));
      } else {
         super.harvestBlock(worldIn, player, pos, state, te, stack);
      }

   }
}
