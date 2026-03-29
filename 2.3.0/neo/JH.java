package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

class JH extends Gi {
   private final JJ enderman;

   public JH(JJ p_i45843_1_) {
      this.enderman = p_i45843_1_;
   }

   public boolean shouldExecute() {
      if (this.enderman.getHeldBlockState() == null) {
         return false;
      } else if (!this.enderman.world.getGameRules().getBoolean("mobGriefing")) {
         return false;
      } else {
         return this.enderman.getRNG().nextInt(2000) == 0;
      }
   }

   public void updateTask() {
      Random random = this.enderman.getRNG();
      bij world = this.enderman.world;
      int i = MathHelper.floor(this.enderman.posX - 1.0 + random.nextDouble() * 2.0);
      int j = MathHelper.floor(this.enderman.posY + random.nextDouble() * 2.0);
      int k = MathHelper.floor(this.enderman.posZ - 1.0 + random.nextDouble() * 2.0);
      BlockPos blockpos = new BlockPos(i, j, k);
      in iblockstate = world.getBlockState(blockpos);
      in iblockstate1 = world.getBlockState(blockpos.down());
      in iblockstate2 = this.enderman.getHeldBlockState();
      if (iblockstate2 != null && this.canPlaceBlock(world, blockpos, iblockstate2.getBlock(), iblockstate, iblockstate1)) {
         world.setBlockState(blockpos, iblockstate2, 3);
         this.enderman.setHeldBlockState((in)null);
      }

   }

   private boolean canPlaceBlock(bij p_188518_1_, BlockPos p_188518_2_, co p_188518_3_, in p_188518_4_, in p_188518_5_) {
      if (!p_188518_3_.canPlaceBlockAt(p_188518_1_, p_188518_2_)) {
         return false;
      } else if (p_188518_4_.getMaterial() != hM.AIR) {
         return false;
      } else {
         return p_188518_5_.getMaterial() == hM.AIR ? false : p_188518_5_.isFullCube();
      }
   }
}
