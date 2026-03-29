package neo;

public class uS implements uQ {
   private final nC minecraft;

   public uS(nC minecraftIn) {
      this.minecraft = minecraftIn;
   }

   public void render(float partialTicks, long finishTimeNano) {
      if (!bpq.isShadowPass) {
         if (XH.isShaders()) {
            bpq.beginLeash();
         }

         nC var10000 = this.minecraft;
         ME entityplayer = nC.player;
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         double d0 = entityplayer.lastTickPosX + (entityplayer.posX - entityplayer.lastTickPosX) * (double)partialTicks;
         double d1 = entityplayer.lastTickPosY + (entityplayer.posY - entityplayer.lastTickPosY) * (double)partialTicks;
         double d2 = entityplayer.lastTickPosZ + (entityplayer.posZ - entityplayer.lastTickPosZ) * (double)partialTicks;
         double d3 = 0.0 - d1;
         double d4 = 256.0 - d1;
         yh.disableTexture2D();
         yh.disableBlend();
         double d5 = (double)(entityplayer.chunkCoordX << 4) - d0;
         double d6 = (double)(entityplayer.chunkCoordZ << 4) - d2;
         yh.glLineWidth(1.0F);
         bufferbuilder.begin(3, zK.POSITION_COLOR);

         int k1;
         int l1;
         for(k1 = -16; k1 <= 32; k1 += 16) {
            for(l1 = -16; l1 <= 32; l1 += 16) {
               bufferbuilder.pos(d5 + (double)k1, d3, d6 + (double)l1).color(1.0F, 0.0F, 0.0F, 0.0F).endVertex();
               bufferbuilder.pos(d5 + (double)k1, d3, d6 + (double)l1).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder.pos(d5 + (double)k1, d4, d6 + (double)l1).color(1.0F, 0.0F, 0.0F, 0.5F).endVertex();
               bufferbuilder.pos(d5 + (double)k1, d4, d6 + (double)l1).color(1.0F, 0.0F, 0.0F, 0.0F).endVertex();
            }
         }

         for(k1 = 2; k1 < 16; k1 += 2) {
            bufferbuilder.pos(d5 + (double)k1, d3, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5 + (double)k1, d3, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + (double)k1, d4, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + (double)k1, d4, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5 + (double)k1, d3, d6 + 16.0).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5 + (double)k1, d3, d6 + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + (double)k1, d4, d6 + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + (double)k1, d4, d6 + 16.0).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
         }

         for(k1 = 2; k1 < 16; k1 += 2) {
            bufferbuilder.pos(d5, d3, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5, d3, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d4, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d4, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d3, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d3, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d4, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d4, d6 + (double)k1).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
         }

         double d8;
         for(k1 = 0; k1 <= 256; k1 += 2) {
            d8 = (double)k1 - d1;
            bufferbuilder.pos(d5, d8, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6 + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d8, d6 + 16.0).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d8, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6).color(1.0F, 1.0F, 0.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6).color(1.0F, 1.0F, 0.0F, 0.0F).endVertex();
         }

         tessellator.draw();
         yh.glLineWidth(2.0F);
         bufferbuilder.begin(3, zK.POSITION_COLOR);

         for(k1 = 0; k1 <= 16; k1 += 16) {
            for(l1 = 0; l1 <= 16; l1 += 16) {
               bufferbuilder.pos(d5 + (double)k1, d3, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
               bufferbuilder.pos(d5 + (double)k1, d3, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
               bufferbuilder.pos(d5 + (double)k1, d4, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
               bufferbuilder.pos(d5 + (double)k1, d4, d6 + (double)l1).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
            }
         }

         for(k1 = 0; k1 <= 256; k1 += 16) {
            d8 = (double)k1 - d1;
            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6 + 16.0).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d8, d6 + 16.0).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5 + 16.0, d8, d6).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 1.0F).endVertex();
            bufferbuilder.pos(d5, d8, d6).color(0.25F, 0.25F, 1.0F, 0.0F).endVertex();
         }

         tessellator.draw();
         yh.glLineWidth(1.0F);
         yh.enableBlend();
         yh.enableTexture2D();
         if (XH.isShaders()) {
            bpq.endLeash();
         }
      }

   }
}
