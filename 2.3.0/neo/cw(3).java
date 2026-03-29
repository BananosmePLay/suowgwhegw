package neo;

import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.math.BlockPos;

public class cw extends co {
   protected cw() {
      super(hM.BARRIER);
      this.setBlockUnbreakable();
      this.setResistance(6000001.0F);
      this.disableStats();
      this.translucent = true;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.INVISIBLE;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public float getAmbientOcclusionLightValue(in state) {
      return 1.0F;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
   }
}
