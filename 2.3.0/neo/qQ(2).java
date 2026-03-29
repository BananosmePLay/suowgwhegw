package neo;

import net.minecraft.util.math.MathHelper;

public class qQ extends pM {
   private Iw entity;

   protected qQ(bij worldIn, double xCoordIn, double yCoordIn, double zCoordIn) {
      super(worldIn, xCoordIn, yCoordIn, zCoordIn, 0.0, 0.0, 0.0);
      this.particleRed = 1.0F;
      this.particleGreen = 1.0F;
      this.particleBlue = 1.0F;
      this.motionX = 0.0;
      this.motionY = 0.0;
      this.motionZ = 0.0;
      this.particleGravity = 0.0F;
      this.particleMaxAge = 30;
   }

   public int getFXLayer() {
      return 3;
   }

   public void onUpdate() {
      super.onUpdate();
      if (this.entity == null) {
         JD entityelderguardian = new JD(this.world);
         entityelderguardian.setGhost();
         this.entity = entityelderguardian;
      }

   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      if (this.entity != null) {
         wC rendermanager = nC.getMinecraft().getRenderManager();
         rendermanager.setRenderPosition(pM.interpPosX, pM.interpPosY, pM.interpPosZ);
         float f = 0.42553192F;
         float f1 = ((float)this.particleAge + partialTicks) / (float)this.particleMaxAge;
         yh.depthMask(true);
         yh.enableBlend();
         yh.enableDepth();
         yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
         float f2 = 240.0F;
         ys.setLightmapTextureCoords(ys.lightmapTexUnit, 240.0F, 240.0F);
         yh.pushMatrix();
         float f3 = 0.05F + 0.5F * MathHelper.sin(f1 * 3.1415927F);
         yh.color(1.0F, 1.0F, 1.0F, f3);
         yh.translate(0.0F, 1.8F, 0.0F);
         yh.rotate(180.0F - entityIn.rotationYaw, 0.0F, 1.0F, 0.0F);
         yh.rotate(60.0F - 150.0F * f1 - entityIn.rotationPitch, 1.0F, 0.0F, 0.0F);
         yh.translate(0.0F, -0.4F, -1.5F);
         yh.scale(0.42553192F, 0.42553192F, 0.42553192F);
         this.entity.rotationYaw = 0.0F;
         this.entity.rotationYawHead = 0.0F;
         this.entity.prevRotationYaw = 0.0F;
         this.entity.prevRotationYawHead = 0.0F;
         rendermanager.renderEntity(this.entity, 0.0, 0.0, 0.0, 0.0F, partialTicks, false);
         yh.popMatrix();
         yh.enableDepth();
      }

   }
}
