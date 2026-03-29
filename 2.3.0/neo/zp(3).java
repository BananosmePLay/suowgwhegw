package neo;

import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class zp extends zF<Yj> {
   public static final ResourceLocation TEXTURE_BEACON_BEAM = new ResourceLocation("textures/entity/beacon_beam.png");

   public zp() {
   }

   public void render(Yj te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      this.renderBeacon(x, y, z, (double)partialTicks, (double)te.shouldBeamRender(), te.getBeamSegments(), (double)te.getWorld().getTotalWorldTime());
   }

   public void renderBeacon(double x, double y, double z, double partialTicks, double textureScale, List<Yi> beamSegments, double totalWorldTime) {
      if (textureScale > 0.0 && beamSegments.size() > 0) {
         if (XH.isShaders()) {
            bpq.beginBeacon();
         }

         yh.alphaFunc(516, 0.1F);
         this.bindTexture(TEXTURE_BEACON_BEAM);
         if (textureScale > 0.0) {
            yh.disableFog();
            int i = 0;

            for(int j = 0; j < beamSegments.size(); ++j) {
               Yi tileentitybeacon$beamsegment = (Yi)beamSegments.get(j);
               renderBeamSegment(x, y, z, partialTicks, textureScale, totalWorldTime, i, tileentitybeacon$beamsegment.getHeight(), tileentitybeacon$beamsegment.getColors());
               i += tileentitybeacon$beamsegment.getHeight();
            }

            yh.enableFog();
         }

         if (XH.isShaders()) {
            bpq.endBeacon();
         }
      }

   }

   public static void renderBeamSegment(double x, double y, double z, double partialTicks, double textureScale, double totalWorldTime, int yOffset, int height, float[] colors) {
      renderBeamSegment(x, y, z, partialTicks, textureScale, totalWorldTime, yOffset, height, colors, 0.2, 0.25);
   }

   public static void renderBeamSegment(double x, double y, double z, double partialTicks, double textureScale, double totalWorldTime, int yOffset, int height, float[] colors, double beamRadius, double glowRadius) {
      int i = yOffset + height;
      yh.glTexParameteri(3553, 10242, 10497);
      yh.glTexParameteri(3553, 10243, 10497);
      yh.disableLighting();
      yh.disableCull();
      yh.disableBlend();
      yh.depthMask(true);
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE, ya.ONE, xR.ZERO);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      double d0 = totalWorldTime + partialTicks;
      double d1 = height < 0 ? d0 : -d0;
      double d2 = MathHelper.frac(d1 * 0.2 - (double)MathHelper.floor(d1 * 0.1));
      float f = colors[0];
      float f1 = colors[1];
      float f2 = colors[2];
      double d3 = d0 * 0.025 * -1.5;
      double d4 = 0.5 + Math.cos(d3 + 2.356194490192345) * beamRadius;
      double d5 = 0.5 + Math.sin(d3 + 2.356194490192345) * beamRadius;
      double d6 = 0.5 + Math.cos(d3 + 0.7853981633974483) * beamRadius;
      double d7 = 0.5 + Math.sin(d3 + 0.7853981633974483) * beamRadius;
      double d8 = 0.5 + Math.cos(d3 + 3.9269908169872414) * beamRadius;
      double d9 = 0.5 + Math.sin(d3 + 3.9269908169872414) * beamRadius;
      double d10 = 0.5 + Math.cos(d3 + 5.497787143782138) * beamRadius;
      double d11 = 0.5 + Math.sin(d3 + 5.497787143782138) * beamRadius;
      double d12 = 0.0;
      double d13 = 1.0;
      double d14 = -1.0 + d2;
      double d15 = (double)height * textureScale * (0.5 / beamRadius) + d14;
      bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
      bufferbuilder.pos(x + d4, y + (double)i, z + d5).tex(1.0, d15).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d4, y + (double)yOffset, z + d5).tex(1.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d6, y + (double)yOffset, z + d7).tex(0.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d6, y + (double)i, z + d7).tex(0.0, d15).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d10, y + (double)i, z + d11).tex(1.0, d15).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d10, y + (double)yOffset, z + d11).tex(1.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d8, y + (double)yOffset, z + d9).tex(0.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d8, y + (double)i, z + d9).tex(0.0, d15).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d6, y + (double)i, z + d7).tex(1.0, d15).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d6, y + (double)yOffset, z + d7).tex(1.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d10, y + (double)yOffset, z + d11).tex(0.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d10, y + (double)i, z + d11).tex(0.0, d15).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d8, y + (double)i, z + d9).tex(1.0, d15).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d8, y + (double)yOffset, z + d9).tex(1.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d4, y + (double)yOffset, z + d5).tex(0.0, d14).color(f, f1, f2, 1.0F).endVertex();
      bufferbuilder.pos(x + d4, y + (double)i, z + d5).tex(0.0, d15).color(f, f1, f2, 1.0F).endVertex();
      tessellator.draw();
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      yh.depthMask(false);
      if (XH.isShaders()) {
         yh.depthMask(bpq.isBeaconBeamDepth());
      }

      d3 = 0.5 - glowRadius;
      d4 = 0.5 - glowRadius;
      d5 = 0.5 + glowRadius;
      d6 = 0.5 - glowRadius;
      d7 = 0.5 - glowRadius;
      d8 = 0.5 + glowRadius;
      d9 = 0.5 + glowRadius;
      d10 = 0.5 + glowRadius;
      d11 = 0.0;
      d12 = 1.0;
      d13 = -1.0 + d2;
      d14 = (double)height * textureScale + d13;
      bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
      bufferbuilder.pos(x + d3, y + (double)i, z + d4).tex(1.0, d14).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d3, y + (double)yOffset, z + d4).tex(1.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d5, y + (double)yOffset, z + d6).tex(0.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d5, y + (double)i, z + d6).tex(0.0, d14).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d9, y + (double)i, z + d10).tex(1.0, d14).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d9, y + (double)yOffset, z + d10).tex(1.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d7, y + (double)yOffset, z + d8).tex(0.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d7, y + (double)i, z + d8).tex(0.0, d14).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d5, y + (double)i, z + d6).tex(1.0, d14).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d5, y + (double)yOffset, z + d6).tex(1.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d9, y + (double)yOffset, z + d10).tex(0.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d9, y + (double)i, z + d10).tex(0.0, d14).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d7, y + (double)i, z + d8).tex(1.0, d14).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d7, y + (double)yOffset, z + d8).tex(1.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d3, y + (double)yOffset, z + d4).tex(0.0, d13).color(f, f1, f2, 0.125F).endVertex();
      bufferbuilder.pos(x + d3, y + (double)i, z + d4).tex(0.0, d14).color(f, f1, f2, 0.125F).endVertex();
      tessellator.draw();
      yh.enableLighting();
      yh.enableTexture2D();
      yh.depthMask(true);
   }

   public boolean isGlobalRenderer(Yj te) {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean isGlobalRenderer(Yg var1) {
      return this.isGlobalRenderer((Yj)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((Yj)var1, var2, var4, var6, var8, var9, var10);
   }
}
