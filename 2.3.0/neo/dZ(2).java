package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;

public class dZ extends cG {
   public dZ(hM materialIn, boolean ignoreSimilarity) {
      super(materialIn, ignoreSimilarity);
      this.setCreativeTab(EN.BUILDING_BLOCKS);
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   protected boolean canSilkHarvest() {
      return true;
   }
}
