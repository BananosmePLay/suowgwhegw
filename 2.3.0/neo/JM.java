package neo;

import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

class JM extends KQ {
   // $FF: synthetic field
   final JR this$0;

   private JM(JR this$0) {
      super(this$0);
      this.this$0 = this$0;
   }

   protected int getCastingTime() {
      return 40;
   }

   protected int getCastingInterval() {
      return 100;
   }

   protected void castSpell() {
      Iw entitylivingbase = this.this$0.getAttackTarget();
      double d0 = Math.min(entitylivingbase.posY, this.this$0.posY);
      double d1 = Math.max(entitylivingbase.posY, this.this$0.posY) + 1.0;
      float f = (float)MathHelper.atan2(entitylivingbase.posZ - this.this$0.posZ, entitylivingbase.posX - this.this$0.posX);
      int k;
      if (this.this$0.getDistanceSq(entitylivingbase) < 9.0) {
         float f2;
         for(k = 0; k < 5; ++k) {
            f2 = f + (float)k * 3.1415927F * 0.4F;
            this.spawnFangs(this.this$0.posX + (double)MathHelper.cos(f2) * 1.5, this.this$0.posZ + (double)MathHelper.sin(f2) * 1.5, d0, d1, f2, 0);
         }

         for(k = 0; k < 8; ++k) {
            f2 = f + (float)k * 3.1415927F * 2.0F / 8.0F + 1.2566371F;
            this.spawnFangs(this.this$0.posX + (double)MathHelper.cos(f2) * 2.5, this.this$0.posZ + (double)MathHelper.sin(f2) * 2.5, d0, d1, f2, 3);
         }
      } else {
         for(k = 0; k < 16; ++k) {
            double d2 = 1.25 * (double)(k + 1);
            int j = 1 * k;
            this.spawnFangs(this.this$0.posX + (double)MathHelper.cos(f) * d2, this.this$0.posZ + (double)MathHelper.sin(f) * d2, d0, d1, f, j);
         }
      }

   }

   private void spawnFangs(double p_190876_1_, double p_190876_3_, double p_190876_5_, double p_190876_7_, float p_190876_9_, int p_190876_10_) {
      BlockPos blockpos = new BlockPos(p_190876_1_, p_190876_7_, p_190876_3_);
      boolean flag = false;
      double d0 = 0.0;

      do {
         if (!this.this$0.world.isBlockNormalCube(blockpos, true) && this.this$0.world.isBlockNormalCube(blockpos.down(), true)) {
            if (!this.this$0.world.isAirBlock(blockpos)) {
               in iblockstate = this.this$0.world.getBlockState(blockpos);
               AxisAlignedBB axisalignedbb = iblockstate.getCollisionBoundingBox(this.this$0.world, blockpos);
               if (axisalignedbb != null) {
                  d0 = axisalignedbb.maxY;
               }
            }

            flag = true;
            break;
         }

         blockpos = blockpos.down();
      } while(blockpos.getY() >= MathHelper.floor(p_190876_5_) - 1);

      if (flag) {
         MR entityevokerfangs = new MR(this.this$0.world, p_190876_1_, (double)blockpos.getY() + d0, p_190876_3_, p_190876_9_, p_190876_10_, this.this$0);
         this.this$0.world.spawnEntity(entityevokerfangs);
      }

   }

   protected SoundEvent getSpellPrepareSound() {
      return NO.EVOCATION_ILLAGER_PREPARE_ATTACK;
   }

   protected KR getSpellType() {
      return KR.FANGS;
   }

   // $FF: synthetic method
   JM(JR x0, Object x1) {
      this(x0);
   }
}
