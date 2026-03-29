package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class zu extends zv {
   private static final ResourceLocation END_GATEWAY_BEAM_TEXTURE = new ResourceLocation("textures/entity/end_gateway_beam.png");

   public zu() {
   }

   public void render(Yy te, double x, double y, double z, float partialTicks, int destroyStage, float alpha) {
      yh.disableFog();
      Yx tileentityendgateway = (Yx)te;
      if (tileentityendgateway.isSpawning() || tileentityendgateway.isCoolingDown()) {
         yh.alphaFunc(516, 0.1F);
         this.bindTexture(END_GATEWAY_BEAM_TEXTURE);
         float f = tileentityendgateway.isSpawning() ? tileentityendgateway.getSpawnPercent(partialTicks) : tileentityendgateway.getCooldownPercent(partialTicks);
         double d0 = tileentityendgateway.isSpawning() ? 256.0 - y : 50.0;
         f = MathHelper.sin(f * 3.1415927F);
         int i = MathHelper.floor((double)f * d0);
         float[] afloat = tileentityendgateway.isSpawning() ? Om.MAGENTA.getColorComponentValues() : Om.PURPLE.getColorComponentValues();
         zp.renderBeamSegment(x, y, z, (double)partialTicks, (double)f, (double)tileentityendgateway.getWorld().getTotalWorldTime(), 0, i, afloat, 0.15, 0.175);
         zp.renderBeamSegment(x, y, z, (double)partialTicks, (double)f, (double)tileentityendgateway.getWorld().getTotalWorldTime(), 0, -i, afloat, 0.15, 0.175);
      }

      super.render(te, x, y, z, partialTicks, destroyStage, alpha);
      yh.enableFog();
   }

   protected int getPasses(double p_191286_1_) {
      return super.getPasses(p_191286_1_) + 1;
   }

   protected float getOffset() {
      return 1.0F;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void render(Yg var1, double var2, double var4, double var6, float var8, int var9, float var10) {
      this.render((Yy)var1, var2, var4, var6, var8, var9, var10);
   }
}
