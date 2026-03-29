package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;

public class qt implements pK {
   public qt() {
   }

   @Nullable
   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      in iblockstate = co.getStateById(p_178902_15_[0]);
      if (iblockstate.getBlock() != Nk.AIR && iblockstate.getRenderType() == EnumBlockRenderType.INVISIBLE) {
         return null;
      } else {
         int i = nC.getMinecraft().getBlockColors().getColor(iblockstate, worldIn, new BlockPos(xCoordIn, yCoordIn, zCoordIn));
         if (iblockstate.getBlock() instanceof dH) {
            i = ((dH)iblockstate.getBlock()).getDustColor(iblockstate);
         }

         float f = (float)(i >> 16 & 255) / 255.0F;
         float f1 = (float)(i >> 8 & 255) / 255.0F;
         float f2 = (float)(i & 255) / 255.0F;
         return new qu(worldIn, xCoordIn, yCoordIn, zCoordIn, f, f1, f2);
      }
   }
}
