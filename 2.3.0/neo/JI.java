package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;

class JI extends Gi {
   private final JJ enderman;

   public JI(JJ p_i45841_1_) {
      this.enderman = p_i45841_1_;
   }

   public boolean shouldExecute() {
      if (this.enderman.getHeldBlockState() != null) {
         return false;
      } else if (!this.enderman.world.getGameRules().getBoolean("mobGriefing")) {
         return false;
      } else {
         return this.enderman.getRNG().nextInt(20) == 0;
      }
   }

   public void updateTask() {
      Random random = this.enderman.getRNG();
      bij world = this.enderman.world;
      int i = MathHelper.floor(this.enderman.posX - 2.0 + random.nextDouble() * 4.0);
      int j = MathHelper.floor(this.enderman.posY + random.nextDouble() * 3.0);
      int k = MathHelper.floor(this.enderman.posZ - 2.0 + random.nextDouble() * 4.0);
      BlockPos blockpos = new BlockPos(i, j, k);
      in iblockstate = world.getBlockState(blockpos);
      co block = iblockstate.getBlock();
      RayTraceResult raytraceresult = world.rayTraceBlocks(new Vec3d((double)((float)MathHelper.floor(this.enderman.posX) + 0.5F), (double)((float)j + 0.5F), (double)((float)MathHelper.floor(this.enderman.posZ) + 0.5F)), new Vec3d((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F)), false, true, false);
      boolean flag = raytraceresult != null && raytraceresult.getBlockPos().equals(blockpos);
      if (JJ.access$200().contains(block) && flag) {
         this.enderman.setHeldBlockState(iblockstate);
         world.setBlockToAir(blockpos);
      }

   }
}
