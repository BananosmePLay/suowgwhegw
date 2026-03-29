package neo;

import net.minecraft.client.renderer.chunk.RenderChunk;

public class 0S extends 0n {
   public RenderChunk renderChunk;

   public _S/* $FF was: 0S*/(RenderChunk renderChunk) {
      this.renderChunk = renderChunk;
   }

   private static RenderChunk ltnlqBheSG(0S var0) {
      return var0.renderChunk;
   }

   private static void NyJybeBtqS(0S var0, RenderChunk var1) {
      var0.renderChunk = var1;
   }

   public void setRenderChunk(RenderChunk renderChunk) {
      NyJybeBtqS(this, renderChunk);
   }

   public RenderChunk getRenderChunk() {
      return ltnlqBheSG(this);
   }
}
