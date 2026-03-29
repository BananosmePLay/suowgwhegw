package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

class KE extends Hn {
   private EnumFacing facing;
   private boolean doMerge;

   public KE(KG silverfishIn) {
      super(silverfishIn, 1.0, 10);
      this.setMutexBits(1);
   }

   public boolean shouldExecute() {
      if (this.entity.getAttackTarget() != null) {
         return false;
      } else if (!this.entity.getNavigator().noPath()) {
         return false;
      } else {
         Random random = this.entity.getRNG();
         if (this.entity.world.getGameRules().getBoolean("mobGriefing") && random.nextInt(10) == 0) {
            this.facing = EnumFacing.random(random);
            BlockPos blockpos = (new BlockPos(this.entity.posX, this.entity.posY + 0.5, this.entity.posZ)).offset(this.facing);
            in iblockstate = this.entity.world.getBlockState(blockpos);
            if (gB.canContainSilverfish(iblockstate)) {
               this.doMerge = true;
               return true;
            }
         }

         this.doMerge = false;
         return super.shouldExecute();
      }
   }

   public boolean shouldContinueExecuting() {
      return this.doMerge ? false : super.shouldContinueExecuting();
   }

   public void startExecuting() {
      if (!this.doMerge) {
         super.startExecuting();
      } else {
         bij world = this.entity.world;
         BlockPos blockpos = (new BlockPos(this.entity.posX, this.entity.posY + 0.5, this.entity.posZ)).offset(this.facing);
         in iblockstate = world.getBlockState(blockpos);
         if (gB.canContainSilverfish(iblockstate)) {
            world.setBlockState(blockpos, Nk.MONSTER_EGG.getDefaultState().withProperty(gB.VARIANT, gA.forModelBlock(iblockstate)), 3);
            this.entity.spawnExplosionParticle();
            this.entity.setDead();
         }
      }

   }
}
