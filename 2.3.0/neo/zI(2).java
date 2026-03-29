package neo;

import java.util.Iterator;
import net.minecraft.util.BlockRenderLayer;

public class zI extends ul {
   private double viewEntityX;
   private double viewEntityY;
   private double viewEntityZ;

   public zI() {
   }

   public void renderChunkLayer(BlockRenderLayer layer) {
      if (this.initialized) {
         if (!XH.isRenderRegions()) {
            Iterator var9 = this.renderChunks.iterator();

            while(var9.hasNext()) {
               ug renderchunk1 = (ug)var9.next();
               zM vertexbuffer1 = renderchunk1.getVertexBufferByLayer(layer.ordinal());
               yh.pushMatrix();
               this.preRenderChunk(renderchunk1);
               renderchunk1.multModelviewMatrix();
               vertexbuffer1.bindBuffer();
               this.setupArrayPointers();
               vertexbuffer1.drawArrays(7);
               yh.popMatrix();
            }
         } else {
            int i = Integer.MIN_VALUE;
            int j = Integer.MIN_VALUE;
            boc vboregion = null;
            Iterator var5 = this.renderChunks.iterator();

            while(true) {
               if (!var5.hasNext()) {
                  if (vboregion != null) {
                     this.drawRegion(i, j, vboregion);
                  }
                  break;
               }

               ug renderchunk = (ug)var5.next();
               zM vertexbuffer = renderchunk.getVertexBufferByLayer(layer.ordinal());
               boc vboregion1 = vertexbuffer.getVboRegion();
               if (vboregion1 != vboregion || i != renderchunk.regionX || j != renderchunk.regionZ) {
                  if (vboregion != null) {
                     this.drawRegion(i, j, vboregion);
                  }

                  i = renderchunk.regionX;
                  j = renderchunk.regionZ;
                  vboregion = vboregion1;
               }

               vertexbuffer.drawArrays(7);
            }
         }

         ys.glBindBuffer(ys.GL_ARRAY_BUFFER, 0);
         yh.resetColor();
         this.renderChunks.clear();
      }

   }

   public void setupArrayPointers() {
      if (XH.isShaders()) {
         bpr.setupArrayPointersVbo();
      } else {
         yh.glVertexPointer(3, 5126, 28, 0);
         yh.glColorPointer(4, 5121, 28, 12);
         yh.glTexCoordPointer(2, 5126, 28, 16);
         ys.setClientActiveTexture(ys.lightmapTexUnit);
         yh.glTexCoordPointer(2, 5122, 28, 24);
         ys.setClientActiveTexture(ys.defaultTexUnit);
      }

   }

   public void initialize(double viewEntityXIn, double viewEntityYIn, double viewEntityZIn) {
      this.viewEntityX = viewEntityXIn;
      this.viewEntityY = viewEntityYIn;
      this.viewEntityZ = viewEntityZIn;
      super.initialize(viewEntityXIn, viewEntityYIn, viewEntityZIn);
   }

   private void drawRegion(int p_drawRegion_1_, int p_drawRegion_2_, boc p_drawRegion_3_) {
      yh.pushMatrix();
      this.preRenderRegion(p_drawRegion_1_, 0, p_drawRegion_2_);
      p_drawRegion_3_.finishDraw(this);
      yh.popMatrix();
   }

   public void preRenderRegion(int p_preRenderRegion_1_, int p_preRenderRegion_2_, int p_preRenderRegion_3_) {
      yh.translate((float)((double)p_preRenderRegion_1_ - this.viewEntityX), (float)((double)p_preRenderRegion_2_ - this.viewEntityY), (float)((double)p_preRenderRegion_3_ - this.viewEntityZ));
   }
}
