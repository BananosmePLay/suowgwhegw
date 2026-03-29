package neo;

import java.nio.IntBuffer;
import java.util.Iterator;
import net.minecraft.util.BlockRenderLayer;

public class yL extends ul {
   private double viewEntityX;
   private double viewEntityY;
   private double viewEntityZ;
   IntBuffer bufferLists = xE.createDirectIntBuffer(16);

   public yL() {
   }

   public void renderChunkLayer(BlockRenderLayer layer) {
      if (this.initialized) {
         if (!XH.isRenderRegions()) {
            Iterator var8 = this.renderChunks.iterator();

            while(var8.hasNext()) {
               ug renderchunk1 = (ug)var8.next();
               ue listedrenderchunk1 = (ue)renderchunk1;
               yh.pushMatrix();
               this.preRenderChunk(renderchunk1);
               yh.callList(listedrenderchunk1.getDisplayList(layer, listedrenderchunk1.getCompiledChunk()));
               yh.popMatrix();
            }
         } else {
            int i = Integer.MIN_VALUE;
            int j = Integer.MIN_VALUE;
            Iterator var4 = this.renderChunks.iterator();

            while(true) {
               if (!var4.hasNext()) {
                  if (this.bufferLists.position() > 0) {
                     this.drawRegion(i, j, this.bufferLists);
                  }
                  break;
               }

               ug renderchunk = (ug)var4.next();
               ue listedrenderchunk = (ue)renderchunk;
               if (i != renderchunk.regionX || j != renderchunk.regionZ) {
                  if (this.bufferLists.position() > 0) {
                     this.drawRegion(i, j, this.bufferLists);
                  }

                  i = renderchunk.regionX;
                  j = renderchunk.regionZ;
               }

               if (this.bufferLists.position() >= this.bufferLists.capacity()) {
                  IntBuffer intbuffer = xE.createDirectIntBuffer(this.bufferLists.capacity() * 2);
                  this.bufferLists.flip();
                  intbuffer.put(this.bufferLists);
                  this.bufferLists = intbuffer;
               }

               this.bufferLists.put(listedrenderchunk.getDisplayList(layer, listedrenderchunk.getCompiledChunk()));
            }
         }

         if (XH.isMultiTexture()) {
            yh.bindCurrentTexture();
         }

         yh.resetColor();
         this.renderChunks.clear();
      }

   }

   public void initialize(double viewEntityXIn, double viewEntityYIn, double viewEntityZIn) {
      this.viewEntityX = viewEntityXIn;
      this.viewEntityY = viewEntityYIn;
      this.viewEntityZ = viewEntityZIn;
      super.initialize(viewEntityXIn, viewEntityYIn, viewEntityZIn);
   }

   private void drawRegion(int p_drawRegion_1_, int p_drawRegion_2_, IntBuffer p_drawRegion_3_) {
      yh.pushMatrix();
      this.preRenderRegion(p_drawRegion_1_, 0, p_drawRegion_2_);
      p_drawRegion_3_.flip();
      yh.callLists(p_drawRegion_3_);
      p_drawRegion_3_.clear();
      yh.popMatrix();
   }

   public void preRenderRegion(int p_preRenderRegion_1_, int p_preRenderRegion_2_, int p_preRenderRegion_3_) {
      yh.translate((float)((double)p_preRenderRegion_1_ - this.viewEntityX), (float)((double)p_preRenderRegion_2_ - this.viewEntityY), (float)((double)p_preRenderRegion_3_ - this.viewEntityZ));
   }
}
