package neo;

import net.minecraft.util.BlockRenderLayer;

public class ue extends ug {
   private final int baseDisplayList = xE.generateDisplayLists(BlockRenderLayer.values().length);

   public ue(bij worldIn, yy renderGlobalIn, int index) {
      super(worldIn, renderGlobalIn, index);
   }

   public int getDisplayList(BlockRenderLayer layer, ub p_178600_2_) {
      return !p_178600_2_.isLayerEmpty(layer) ? this.baseDisplayList + layer.ordinal() : -1;
   }

   public void deleteGlResources() {
      super.deleteGlResources();
      xE.deleteDisplayLists(this.baseDisplayList, BlockRenderLayer.values().length);
   }
}
