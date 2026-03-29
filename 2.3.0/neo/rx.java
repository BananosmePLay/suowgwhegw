package neo;

import net.minecraft.util.ResourceLocation;

public class rx extends pM {
   private static final ResourceLocation SWEEP_TEXTURE = new ResourceLocation("textures/entity/sweep.png");
   private static final zO VERTEX_FORMAT;
   private int life;
   private final int lifeTime;
   private final zf textureManager;
   private final float size;

   protected rx(zf textureManagerIn, bij worldIn, double x, double y, double z, double p_i46582_9_, double p_i46582_11_, double p_i46582_13_) {
      super(worldIn, x, y, z, 0.0, 0.0, 0.0);
      this.textureManager = textureManagerIn;
      this.lifeTime = 4;
      float f = this.rand.nextFloat() * 0.6F + 0.4F;
      this.particleRed = f;
      this.particleGreen = f;
      this.particleBlue = f;
      this.size = 1.0F - (float)p_i46582_9_ * 0.5F;
   }

   public void renderParticle(tN buffer, Ig entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
      int i = (int)(((float)this.life + partialTicks) * 3.0F / (float)this.lifeTime);
      if (i <= 7) {
         this.textureManager.bindTexture(SWEEP_TEXTURE);
         float f = (float)(i % 4) / 4.0F;
         float f1 = f + 0.24975F;
         float f2 = (float)(i / 2) / 2.0F;
         float f3 = f2 + 0.4995F;
         float f4 = 1.0F * this.size;
         float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
         float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
         float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.disableLighting();
         yz.disableStandardItemLighting();
         buffer.begin(7, VERTEX_FORMAT);
         buffer.pos((double)(f5 - rotationX * f4 - rotationXY * f4), (double)(f6 - rotationZ * f4 * 0.5F), (double)(f7 - rotationYZ * f4 - rotationXZ * f4)).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
         buffer.pos((double)(f5 - rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4 * 0.5F), (double)(f7 - rotationYZ * f4 + rotationXZ * f4)).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
         buffer.pos((double)(f5 + rotationX * f4 + rotationXY * f4), (double)(f6 + rotationZ * f4 * 0.5F), (double)(f7 + rotationYZ * f4 + rotationXZ * f4)).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
         buffer.pos((double)(f5 + rotationX * f4 - rotationXY * f4), (double)(f6 - rotationZ * f4 * 0.5F), (double)(f7 + rotationYZ * f4 - rotationXZ * f4)).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, 1.0F).lightmap(0, 240).normal(0.0F, 1.0F, 0.0F).endVertex();
         yN.getInstance().draw();
         yh.enableLighting();
      }

   }

   public int getBrightnessForRender(float partialTick) {
      return 61680;
   }

   public void onUpdate() {
      this.prevPosX = this.posX;
      this.prevPosY = this.posY;
      this.prevPosZ = this.posZ;
      ++this.life;
      if (this.life == this.lifeTime) {
         this.setExpired();
      }

   }

   public int getFXLayer() {
      return 3;
   }

   static {
      VERTEX_FORMAT = (new zO()).addElement(zK.POSITION_3F).addElement(zK.TEX_2F).addElement(zK.COLOR_4UB).addElement(zK.TEX_2S).addElement(zK.NORMAL_3B).addElement(zK.PADDING_1B);
   }
}
