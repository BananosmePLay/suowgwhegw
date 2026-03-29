package neo;

import net.minecraft.util.BlockRenderLayer;

public class yu {
   private final tN[] worldRenderers = new tN[BlockRenderLayer.values().length];

   public yu() {
      this.worldRenderers[BlockRenderLayer.SOLID.ordinal()] = new tN(2097152);
      this.worldRenderers[BlockRenderLayer.CUTOUT.ordinal()] = new tN(131072);
      this.worldRenderers[BlockRenderLayer.CUTOUT_MIPPED.ordinal()] = new tN(131072);
      this.worldRenderers[BlockRenderLayer.TRANSLUCENT.ordinal()] = new tN(262144);
   }

   public tN getWorldRendererByLayer(BlockRenderLayer layer) {
      return this.worldRenderers[layer.ordinal()];
   }

   public tN getWorldRendererByLayerId(int id) {
      return this.worldRenderers[id];
   }
}
