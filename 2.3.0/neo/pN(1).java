package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;

public class pN implements pK {
   public pN() {
   }

   @Nullable
   public pM createParticle(int particleID, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn, int... p_178902_15_) {
      in iblockstate = co.getStateById(p_178902_15_[0]);
      return iblockstate.getRenderType() == EnumBlockRenderType.INVISIBLE ? null : (new pO(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn, iblockstate)).init();
   }
}
