package neo;

import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;

public class gH extends cG {
   public gH() {
      super(hM.CLAY, false, hK.GRASS);
      this.setCreativeTab(EN.DECORATIONS);
      this.slipperiness = 0.8F;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.TRANSLUCENT;
   }

   public void onFallenUpon(bij worldIn, BlockPos pos, Ig entityIn, float fallDistance) {
      if (entityIn.isSneaking()) {
         super.onFallenUpon(worldIn, pos, entityIn, fallDistance);
      } else {
         entityIn.fall(fallDistance, 0.0F);
      }

   }

   public void onLanded(bij worldIn, Ig entityIn) {
      if (entityIn.isSneaking()) {
         super.onLanded(worldIn, entityIn);
      } else if (entityIn.motionY < 0.0) {
         entityIn.motionY = -entityIn.motionY;
         if (!(entityIn instanceof Iw)) {
            entityIn.motionY *= 0.8;
         }
      }

   }

   public void onEntityWalk(bij worldIn, BlockPos pos, Ig entityIn) {
      if (Math.abs(entityIn.motionY) < 0.1 && !entityIn.isSneaking()) {
         double d0 = 0.4 + Math.abs(entityIn.motionY) * 0.2;
         entityIn.motionX *= d0;
         entityIn.motionZ *= d0;
      }

      super.onEntityWalk(worldIn, pos, entityIn);
   }
}
