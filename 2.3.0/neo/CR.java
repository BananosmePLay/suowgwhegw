package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

class CR {
   double x;
   double z;

   CR() {
   }

   CR(double xIn, double zIn) {
      this.x = xIn;
      this.z = zIn;
   }

   double dist(CR pos) {
      double d0 = this.x - pos.x;
      double d1 = this.z - pos.z;
      return Math.sqrt(d0 * d0 + d1 * d1);
   }

   void normalize() {
      double d0 = (double)this.getLength();
      this.x /= d0;
      this.z /= d0;
   }

   float getLength() {
      return MathHelper.sqrt(this.x * this.x + this.z * this.z);
   }

   public void moveAway(CR pos) {
      this.x -= pos.x;
      this.z -= pos.z;
   }

   public boolean clamp(double p_111093_1_, double p_111093_3_, double p_111093_5_, double p_111093_7_) {
      boolean flag = false;
      if (this.x < p_111093_1_) {
         this.x = p_111093_1_;
         flag = true;
      } else if (this.x > p_111093_5_) {
         this.x = p_111093_5_;
         flag = true;
      }

      if (this.z < p_111093_3_) {
         this.z = p_111093_3_;
         flag = true;
      } else if (this.z > p_111093_7_) {
         this.z = p_111093_7_;
         flag = true;
      }

      return flag;
   }

   public int getSpawnY(bij worldIn) {
      BlockPos blockpos = new BlockPos(this.x, 256.0, this.z);

      do {
         if (blockpos.getY() <= 0) {
            return 257;
         }

         blockpos = blockpos.down();
      } while(worldIn.getBlockState(blockpos).getMaterial() == hM.AIR);

      return blockpos.getY() + 1;
   }

   public boolean isSafe(bij worldIn) {
      BlockPos blockpos = new BlockPos(this.x, 256.0, this.z);

      hM material;
      do {
         if (blockpos.getY() <= 0) {
            return false;
         }

         blockpos = blockpos.down();
         material = worldIn.getBlockState(blockpos).getMaterial();
      } while(material == hM.AIR);

      return !material.isLiquid() && material != hM.FIRE;
   }

   public void randomize(Random rand, double p_111097_2_, double p_111097_4_, double p_111097_6_, double p_111097_8_) {
      this.x = MathHelper.nextDouble(rand, p_111097_2_, p_111097_6_);
      this.z = MathHelper.nextDouble(rand, p_111097_4_, p_111097_8_);
   }
}
