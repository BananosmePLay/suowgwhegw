package neo;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class bcc {
   private final int centerX;
   private final int centerZ;
   private final int radius;
   private final int height;
   private final boolean guarded;
   private final AxisAlignedBB topBoundingBox;

   public bcc(int p_i47020_1_, int p_i47020_2_, int p_i47020_3_, int p_i47020_4_, boolean p_i47020_5_) {
      this.centerX = p_i47020_1_;
      this.centerZ = p_i47020_2_;
      this.radius = p_i47020_3_;
      this.height = p_i47020_4_;
      this.guarded = p_i47020_5_;
      this.topBoundingBox = new AxisAlignedBB((double)(p_i47020_1_ - p_i47020_3_), 0.0, (double)(p_i47020_2_ - p_i47020_3_), (double)(p_i47020_1_ + p_i47020_3_), 256.0, (double)(p_i47020_2_ + p_i47020_3_));
   }

   public boolean doesStartInChunk(BlockPos p_186154_1_) {
      int i = this.centerX - this.radius;
      int j = this.centerZ - this.radius;
      return p_186154_1_.getX() == (i & -16) && p_186154_1_.getZ() == (j & -16);
   }

   public int getCenterX() {
      return this.centerX;
   }

   public int getCenterZ() {
      return this.centerZ;
   }

   public int getRadius() {
      return this.radius;
   }

   public int getHeight() {
      return this.height;
   }

   public boolean isGuarded() {
      return this.guarded;
   }

   public AxisAlignedBB getTopBoundingBox() {
      return this.topBoundingBox;
   }
}
