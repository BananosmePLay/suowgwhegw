package neo;

import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class wj extends vI<MU> {
   private static final ResourceLocation FISH_PARTICLES = new ResourceLocation("textures/particle/particles.png");

   public wj(wC renderManagerIn) {
      super(renderManagerIn);
   }

   public void doRender(MU entity, double x, double y, double z, float entityYaw, float partialTicks) {
      ME entityplayer = entity.getAngler();
      if (entityplayer != null && !this.renderOutlines) {
         yh.pushMatrix();
         yh.translate((float)x, (float)y, (float)z);
         yh.enableRescaleNormal();
         yh.scale(0.5F, 0.5F, 0.5F);
         this.bindEntityTexture(entity);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         int i = true;
         int j = true;
         float f = 0.0625F;
         float f1 = 0.125F;
         float f2 = 0.125F;
         float f3 = 0.1875F;
         float f4 = 1.0F;
         float f5 = 0.5F;
         float f6 = 0.5F;
         yh.rotate(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
         yh.rotate((float)(this.renderManager.options.thirdPersonView == 2 ? -1 : 1) * -this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
         if (this.renderOutlines) {
            yh.enableColorMaterial();
            yh.enableOutlineMode(this.getTeamColor(entity));
         }

         bufferbuilder.begin(7, zK.POSITION_TEX_NORMAL);
         bufferbuilder.pos(-0.5, -0.5, 0.0).tex(0.0625, 0.1875).normal(0.0F, 1.0F, 0.0F).endVertex();
         bufferbuilder.pos(0.5, -0.5, 0.0).tex(0.125, 0.1875).normal(0.0F, 1.0F, 0.0F).endVertex();
         bufferbuilder.pos(0.5, 0.5, 0.0).tex(0.125, 0.125).normal(0.0F, 1.0F, 0.0F).endVertex();
         bufferbuilder.pos(-0.5, 0.5, 0.0).tex(0.0625, 0.125).normal(0.0F, 1.0F, 0.0F).endVertex();
         tessellator.draw();
         if (this.renderOutlines) {
            yh.disableOutlineMode();
            yh.disableColorMaterial();
         }

         yh.disableRescaleNormal();
         yh.popMatrix();
         int k = entityplayer.getPrimaryHand() == EnumHandSide.RIGHT ? 1 : -1;
         Qy itemstack = entityplayer.getHeldItemMainhand();
         if (itemstack.getItem() != NK.FISHING_ROD) {
            k = -k;
         }

         double d4;
         double d5;
         double d6;
         double d7;
         label68: {
            float f7 = entityplayer.getSwingProgress(partialTicks);
            float f8 = MathHelper.sin(MathHelper.sqrt(f7) * 3.1415927F);
            float f9 = (entityplayer.prevRenderYawOffset + (entityplayer.renderYawOffset - entityplayer.prevRenderYawOffset) * partialTicks) * 0.017453292F;
            double d0 = (double)MathHelper.sin(f9);
            double d1 = (double)MathHelper.cos(f9);
            double d2 = (double)k * 0.35;
            double d3 = 0.8;
            if (this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) {
               nC.getMinecraft();
               if (entityplayer == nC.player) {
                  float f10 = this.renderManager.options.fovSetting;
                  f10 /= 100.0F;
                  Vec3d vec3d = new Vec3d((double)k * -0.36 * (double)f10, -0.045 * (double)f10, 0.4);
                  vec3d = vec3d.rotatePitch(-(entityplayer.prevRotationPitch + (entityplayer.rotationPitch - entityplayer.prevRotationPitch) * partialTicks) * 0.017453292F);
                  vec3d = vec3d.rotateYaw(-(entityplayer.prevRotationYaw + (entityplayer.rotationYaw - entityplayer.prevRotationYaw) * partialTicks) * 0.017453292F);
                  vec3d = vec3d.rotateYaw(f8 * 0.5F);
                  vec3d = vec3d.rotatePitch(-f8 * 0.7F);
                  d4 = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)partialTicks + vec3d.x;
                  d5 = entityplayer.prevPosY + (entityplayer.posY - entityplayer.prevPosY) * (double)partialTicks + vec3d.y;
                  d6 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)partialTicks + vec3d.z;
                  d7 = (double)entityplayer.getEyeHeight();
                  break label68;
               }
            }

            d4 = entityplayer.prevPosX + (entityplayer.posX - entityplayer.prevPosX) * (double)partialTicks - d1 * d2 - d0 * 0.8;
            d5 = entityplayer.prevPosY + (double)entityplayer.getEyeHeight() + (entityplayer.posY - entityplayer.prevPosY) * (double)partialTicks - 0.45;
            d6 = entityplayer.prevPosZ + (entityplayer.posZ - entityplayer.prevPosZ) * (double)partialTicks - d0 * d2 + d1 * 0.8;
            d7 = entityplayer.isSneaking() ? -0.1875 : 0.0;
         }

         double d13 = entity.prevPosX + (entity.posX - entity.prevPosX) * (double)partialTicks;
         double d8 = entity.prevPosY + (entity.posY - entity.prevPosY) * (double)partialTicks + 0.25;
         double d9 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * (double)partialTicks;
         double d10 = (double)((float)(d4 - d13));
         double d11 = (double)((float)(d5 - d8)) + d7;
         double d12 = (double)((float)(d6 - d9));
         yh.disableTexture2D();
         yh.disableLighting();
         bufferbuilder.begin(3, zK.POSITION_COLOR);
         int l = true;

         for(int i1 = 0; i1 <= 16; ++i1) {
            float f11 = (float)i1 / 16.0F;
            bufferbuilder.pos(x + d10 * (double)f11, y + d11 * (double)(f11 * f11 + f11) * 0.5 + 0.25, z + d12 * (double)f11).color(0, 0, 0, 255).endVertex();
         }

         tessellator.draw();
         yh.enableLighting();
         yh.enableTexture2D();
         super.doRender(entity, x, y, z, entityYaw, partialTicks);
      }

   }

   protected ResourceLocation getEntityTexture(MU entity) {
      return FISH_PARTICLES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((MU)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((MU)var1, var2, var4, var6, var8, var9);
   }
}
