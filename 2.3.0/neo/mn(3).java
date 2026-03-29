package neo;

import java.util.Iterator;
import net.minecraft.util.ResourceLocation;

class mn {
   private final bhE mapData;
   private final yP mapTexture;
   private final ResourceLocation location;
   private final int[] mapTextureData;
   // $FF: synthetic field
   final mo this$0;

   private mn(mo this$0, bhE mapdataIn) {
      this.this$0 = this$0;
      this.mapData = mapdataIn;
      this.mapTexture = new yP(128, 128);
      this.mapTextureData = this.mapTexture.getTextureData();
      this.location = mo.access$500(this$0).getDynamicTextureLocation("map/" + mapdataIn.mapName, this.mapTexture);

      for(int i = 0; i < this.mapTextureData.length; ++i) {
         this.mapTextureData[i] = 0;
      }

   }

   private void updateMapTexture() {
      for(int i = 0; i < 16384; ++i) {
         int j = this.mapData.colors[i] & 255;
         if (j / 4 == 0) {
            this.mapTextureData[i] = (i + i / 128 & 1) * 8 + 16 << 24;
         } else {
            this.mapTextureData[i] = hK.COLORS[j / 4].getMapColor(j & 3);
         }
      }

      this.mapTexture.updateDynamicTexture();
   }

   private void render(boolean noOverlayRendering) {
      int i = false;
      int j = false;
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      float f = 0.0F;
      mo.access$500(this.this$0).bindTexture(this.location);
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.ONE, xR.ONE_MINUS_SRC_ALPHA, ya.ZERO, xR.ONE);
      yh.disableAlpha();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(0.0, 128.0, -0.009999999776482582).tex(0.0, 1.0).endVertex();
      bufferbuilder.pos(128.0, 128.0, -0.009999999776482582).tex(1.0, 1.0).endVertex();
      bufferbuilder.pos(128.0, 0.0, -0.009999999776482582).tex(1.0, 0.0).endVertex();
      bufferbuilder.pos(0.0, 0.0, -0.009999999776482582).tex(0.0, 0.0).endVertex();
      tessellator.draw();
      yh.enableAlpha();
      yh.disableBlend();
      mo.access$500(this.this$0).bindTexture(mo.access$600());
      int k = 0;
      Iterator var8 = this.mapData.mapDecorations.values().iterator();

      while(true) {
         bhG mapdecoration;
         do {
            if (!var8.hasNext()) {
               yh.pushMatrix();
               yh.translate(0.0F, 0.0F, -0.04F);
               yh.scale(1.0F, 1.0F, 1.0F);
               yh.popMatrix();
               return;
            }

            mapdecoration = (bhG)var8.next();
         } while(noOverlayRendering && !mapdecoration.renderOnFrame());

         yh.pushMatrix();
         yh.translate(0.0F + (float)mapdecoration.getX() / 2.0F + 64.0F, 0.0F + (float)mapdecoration.getY() / 2.0F + 64.0F, -0.02F);
         yh.rotate((float)(mapdecoration.getRotation() * 360) / 16.0F, 0.0F, 0.0F, 1.0F);
         yh.scale(4.0F, 4.0F, 3.0F);
         yh.translate(-0.125F, 0.125F, 0.0F);
         byte b0 = mapdecoration.getImage();
         float f1 = (float)(b0 % 4 + 0) / 4.0F;
         float f2 = (float)(b0 / 4 + 0) / 4.0F;
         float f3 = (float)(b0 % 4 + 1) / 4.0F;
         float f4 = (float)(b0 / 4 + 1) / 4.0F;
         bufferbuilder.begin(7, zK.POSITION_TEX);
         float f5 = -0.001F;
         bufferbuilder.pos(-1.0, 1.0, (double)((float)k * -0.001F)).tex((double)f1, (double)f2).endVertex();
         bufferbuilder.pos(1.0, 1.0, (double)((float)k * -0.001F)).tex((double)f3, (double)f2).endVertex();
         bufferbuilder.pos(1.0, -1.0, (double)((float)k * -0.001F)).tex((double)f3, (double)f4).endVertex();
         bufferbuilder.pos(-1.0, -1.0, (double)((float)k * -0.001F)).tex((double)f1, (double)f4).endVertex();
         tessellator.draw();
         yh.popMatrix();
         ++k;
      }
   }

   // $FF: synthetic method
   static void access$000(mn x0) {
      x0.updateMapTexture();
   }

   // $FF: synthetic method
   static void access$100(mn x0, boolean x1) {
      x0.render(x1);
   }

   // $FF: synthetic method
   mn(mo x0, bhE x1, Object x2) {
      this(x0, x1);
   }

   // $FF: synthetic method
   static ResourceLocation access$300(mn x0) {
      return x0.location;
   }

   // $FF: synthetic method
   static bhE access$400(mn x0) {
      return x0.mapData;
   }
}
