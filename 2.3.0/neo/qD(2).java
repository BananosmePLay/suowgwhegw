package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class qD extends pM {
   private static final ResourceLocation FOOTPRINT_TEXTURE = new ResourceLocation("textures/particle/footprint.png");
   private int footstepAge;
   private final int footstepMaxAge;
   private final zf currentFootSteps;

   protected qD(zf currentFootStepsIn, bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      this.currentFootSteps = currentFootStepsIn;
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.footstepMaxAge = 200;
   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      float f = ((float)this.footstepAge + partialTicks) / (float)this.footstepMaxAge;
      f *= f;
      float f1 = 2.0F - f * 2.0F;
      if (f1 > 1.0F) {
         f1 = 1.0F;
      }

      f1 *= 0.2F;
      yh.disableLighting();
      float f2 = 0.125F;
      float f3 = (float)(this.posX - interpPosX);
      float f4 = (float)(this.posY - interpPosY);
      float f5 = (float)(this.posZ - interpPosZ);
      float f6 = this.world.getLightBrightness(new BlockPos(this.posX, this.posY, this.posZ));
      this.currentFootSteps.bindTexture(FOOTPRINT_TEXTURE);
      yh.enableBlend();
      yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
      buffer.begin(7, zK.POSITION_TEX_COLOR);
      buffer.pos((double)(f3 - 0.125F), (double)f4, (double)(f5 + 0.125F)).tex(0.0, 1.0).color(f6, f6, f6, f1).endVertex();
      buffer.pos((double)(f3 + 0.125F), (double)f4, (double)(f5 + 0.125F)).tex(1.0, 1.0).color(f6, f6, f6, f1).endVertex();
      buffer.pos((double)(f3 + 0.125F), (double)f4, (double)(f5 - 0.125F)).tex(1.0, 0.0).color(f6, f6, f6, f1).endVertex();
      buffer.pos((double)(f3 - 0.125F), (double)f4, (double)(f5 - 0.125F)).tex(0.0, 0.0).color(f6, f6, f6, f1).endVertex();
      yN.getInstance().draw();
      yh.disableBlend();
      yh.enableLighting();
   }

   public void onUpdate() {
      ++this.footstepAge;
      if (this.footstepAge == this.footstepMaxAge) {
         this.setExpired();
      }

   }

   public int getFXLayer() {
      return 3;
   }
}
