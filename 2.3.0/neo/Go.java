package neo;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import net.minecraft.util.math.BlockPos;

public class Go extends Gi {
   private static final Predicate<in> IS_TALL_GRASS;
   private final Iu grassEaterEntity;
   private final bij entityWorld;
   int eatingGrassTimer;

   public Go(Iu grassEaterEntityIn) {
      this.grassEaterEntity = grassEaterEntityIn;
      this.entityWorld = grassEaterEntityIn.world;
      this.setMutexBits(7);
   }

   public boolean shouldExecute() {
      if (this.grassEaterEntity.getRNG().nextInt(this.grassEaterEntity.isChild() ? 50 : 1000) != 0) {
         return false;
      } else {
         BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
         if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos))) {
            return true;
         } else {
            return this.entityWorld.getBlockState(blockpos.down()).getBlock() == Nk.GRASS;
         }
      }
   }

   public void startExecuting() {
      this.eatingGrassTimer = 40;
      this.entityWorld.setEntityState(this.grassEaterEntity, (byte)10);
      this.grassEaterEntity.getNavigator().clearPath();
   }

   public void resetTask() {
      this.eatingGrassTimer = 0;
   }

   public boolean shouldContinueExecuting() {
      return this.eatingGrassTimer > 0;
   }

   public int getEatingGrassTimer() {
      return this.eatingGrassTimer;
   }

   public void updateTask() {
      this.eatingGrassTimer = Math.max(0, this.eatingGrassTimer - 1);
      if (this.eatingGrassTimer == 4) {
         BlockPos blockpos = new BlockPos(this.grassEaterEntity.posX, this.grassEaterEntity.posY, this.grassEaterEntity.posZ);
         if (IS_TALL_GRASS.apply(this.entityWorld.getBlockState(blockpos))) {
            if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
               this.entityWorld.destroyBlock(blockpos, false);
            }

            this.grassEaterEntity.eatGrassBonus();
         } else {
            BlockPos blockpos1 = blockpos.down();
            if (this.entityWorld.getBlockState(blockpos1).getBlock() == Nk.GRASS) {
               if (this.entityWorld.getGameRules().getBoolean("mobGriefing")) {
                  this.entityWorld.playEvent(2001, blockpos1, co.getIdFromBlock(Nk.GRASS));
                  this.entityWorld.setBlockState(blockpos1, Nk.DIRT.getDefaultState(), 2);
               }

               this.grassEaterEntity.eatGrassBonus();
            }
         }
      }

   }

   static {
      IS_TALL_GRASS = iv.forBlock(Nk.TALLGRASS).where(hk.TYPE, Predicates.equalTo(hj.GRASS));
   }
}
