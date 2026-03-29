package neo;

import java.nio.FloatBuffer;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

public class zv extends zF<Yy> {
   private static final ResourceLocation END_SKY_TEXTURE = new ResourceLocation("textures/environment/end_sky.png");
   private static final ResourceLocation END_PORTAL_TEXTURE = new ResourceLocation("textures/entity/end_portal.png");
   private static final Random RANDOM = new Random(31100L);
   private static final FloatBuffer MODELVIEW = xE.createDirectFloatBuffer(16);
   private static final FloatBuffer PROJECTION = xE.createDirectFloatBuffer(16);
   private final FloatBuffer buffer = xE.createDirectFloatBuffer(16);

   public zv() {
   }

   public void render(Yy te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      if (!XH.isShaders() || !bpr.renderEndPortal(te, x, y, z, partialTicks, destroyStage, this.getOffset())) {
         yh.disableLighting();
         RANDOM.setSeed(31100L);
         yh.getFloat(2982, MODELVIEW);
         yh.getFloat(2983, PROJECTION);
         double d0 = x * x + y * y + z * z;
         int i = this.getPasses(d0);
         float f = this.getOffset();
         boolean flag = false;

         for(int j = 0; j < i; ++j) {
            yh.pushMatrix();
            float f1 = 2.0F / (float)(18 - j);
            if (j == 0) {
               this.bindTexture(END_SKY_TEXTURE);
               f1 = 0.15F;
               yh.enableBlend();
               yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
            }

            if (j >= 1) {
               this.bindTexture(END_PORTAL_TEXTURE);
               flag = true;
               nC.getMinecraft().entityRenderer.setupFogColor(true);
            }

            if (j == 1) {
               yh.enableBlend();
               yh.blendFunc(ya.ONE, xR.ONE);
            }

            yh.texGen(yd.S, 9216);
            yh.texGen(yd.T, 9216);
            yh.texGen(yd.R, 9216);
            yh.texGen(yd.S, 9474, this.getBuffer(1.0F, 0.0F, 0.0F, 0.0F));
            yh.texGen(yd.T, 9474, this.getBuffer(0.0F, 1.0F, 0.0F, 0.0F));
            yh.texGen(yd.R, 9474, this.getBuffer(0.0F, 0.0F, 1.0F, 0.0F));
            yh.enableTexGenCoord(yd.S);
            yh.enableTexGenCoord(yd.T);
            yh.enableTexGenCoord(yd.R);
            yh.popMatrix();
            yh.matrixMode(5890);
            yh.pushMatrix();
            yh.loadIdentity();
            yh.translate(0.5F, 0.5F, 0.0F);
            yh.scale(0.5F, 0.5F, 1.0F);
            float f2 = (float)(j + 1);
            yh.translate(17.0F / f2, (2.0F + f2 / 1.5F) * ((float)nC.getSystemTime() % 800000.0F / 800000.0F), 0.0F);
            yh.rotate((f2 * f2 * 4321.0F + f2 * 9.0F) * 2.0F, 0.0F, 0.0F, 1.0F);
            yh.scale(4.5F - f2 / 4.0F, 4.5F - f2 / 4.0F, 1.0F);
            yh.multMatrix(PROJECTION);
            yh.multMatrix(MODELVIEW);
            yN tessellator = yN.getInstance();
            tN bufferbuilder = tessellator.getBuffer();
            bufferbuilder.begin(7, zK.POSITION_COLOR);
            float f3 = (RANDOM.nextFloat() * 0.5F + 0.1F) * f1;
            float f4 = (RANDOM.nextFloat() * 0.5F + 0.4F) * f1;
            float f5 = (RANDOM.nextFloat() * 0.5F + 0.5F) * f1;
            if (te.shouldRenderFace(EnumFacing.SOUTH)) {
               bufferbuilder.pos(x, y, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y + 1.0, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x, y + 1.0, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
            }

            if (te.shouldRenderFace(EnumFacing.NORTH)) {
               bufferbuilder.pos(x, y + 1.0, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y + 1.0, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x, y, z).color(f3, f4, f5, 1.0F).endVertex();
            }

            if (te.shouldRenderFace(EnumFacing.EAST)) {
               bufferbuilder.pos(x + 1.0, y + 1.0, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y + 1.0, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y, z).color(f3, f4, f5, 1.0F).endVertex();
            }

            if (te.shouldRenderFace(EnumFacing.WEST)) {
               bufferbuilder.pos(x, y, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x, y, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x, y + 1.0, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x, y + 1.0, z).color(f3, f4, f5, 1.0F).endVertex();
            }

            if (te.shouldRenderFace(EnumFacing.DOWN)) {
               bufferbuilder.pos(x, y, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x, y, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
            }

            if (te.shouldRenderFace(EnumFacing.UP)) {
               bufferbuilder.pos(x, y + (double)f, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y + (double)f, z + 1.0).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x + 1.0, y + (double)f, z).color(f3, f4, f5, 1.0F).endVertex();
               bufferbuilder.pos(x, y + (double)f, z).color(f3, f4, f5, 1.0F).endVertex();
            }

            tessellator.draw();
            yh.popMatrix();
            yh.matrixMode(5888);
            this.bindTexture(END_SKY_TEXTURE);
         }

         yh.disableBlend();
         yh.disableTexGenCoord(yd.S);
         yh.disableTexGenCoord(yd.T);
         yh.disableTexGenCoord(yd.R);
         yh.enableLighting();
         if (flag) {
            nC.getMinecraft().entityRenderer.setupFogColor(false);
         }
      }

   }

   protected int getPasses(double p_191286_1_) {
      byte i;
      if (p_191286_1_ > 36864.0) {
         i = 1;
      } else if (p_191286_1_ > 25600.0) {
         i = 3;
      } else if (p_191286_1_ > 16384.0) {
         i = 5;
      } else if (p_191286_1_ > 9216.0) {
         i = 7;
      } else if (p_191286_1_ > 4096.0) {
         i = 9;
      } else if (p_191286_1_ > 1024.0) {
         i = 11;
      } else if (p_191286_1_ > 576.0) {
         i = 13;
      } else if (p_191286_1_ > 256.0) {
         i = 14;
      } else {
         i = 15;
      }

      return i;
   }

   protected float getOffset() {
      return 0.75F;
   }

   private FloatBuffer getBuffer(float p_147525_1_, float p_147525_2_, float p_147525_3_, float p_147525_4_) {
      this.buffer.clear();
      this.buffer.put(p_147525_1_).put(p_147525_2_).put(p_147525_3_).put(p_147525_4_);
      this.buffer.flip();
      return this.buffer;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((Yy)var1, var2, var4, var6, var8, var9, var10);
   }
}
