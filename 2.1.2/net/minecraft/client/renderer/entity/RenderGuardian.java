package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class RenderGuardian extends RenderLiving<EntityGuardian> {
   private static final ResourceLocation GUARDIAN_TEXTURE = new ResourceLocation("textures/entity/guardian.png");
   private static final ResourceLocation GUARDIAN_BEAM_TEXTURE = new ResourceLocation("textures/entity/guardian_beam.png");

   public RenderGuardian(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelGuardian(), 0.5F);
   }

   public boolean shouldRender(EntityGuardian livingEntity, ICamera camera, double camX, double camY, double camZ) {
      if (super.shouldRender((EntityLiving)livingEntity, camera, camX, camY, camZ)) {
         return true;
      } else {
         if (livingEntity.hasTargetedEntity()) {
            EntityLivingBase entitylivingbase = livingEntity.getTargetedEntity();
            if (entitylivingbase != null) {
               Vec3d vec3d = this.getPosition(entitylivingbase, (double)entitylivingbase.height * 0.5, 1.0F);
               Vec3d vec3d1 = this.getPosition(livingEntity, (double)livingEntity.getEyeHeight(), 1.0F);
               if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y, vec3d.z))) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   private Vec3d getPosition(EntityLivingBase entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
      double d0 = entityLivingBaseIn.lastTickPosX + (entityLivingBaseIn.posX - entityLivingBaseIn.lastTickPosX) * (double)p_177110_4_;
      double d1 = p_177110_2_ + entityLivingBaseIn.lastTickPosY + (entityLivingBaseIn.posY - entityLivingBaseIn.lastTickPosY) * (double)p_177110_4_;
      double d2 = entityLivingBaseIn.lastTickPosZ + (entityLivingBaseIn.posZ - entityLivingBaseIn.lastTickPosZ) * (double)p_177110_4_;
      return new Vec3d(d0, d1, d2);
   }

   public void doRender(EntityGuardian entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
      EntityLivingBase entitylivingbase = entity.getTargetedEntity();
      if (entitylivingbase != null) {
         float f = entity.getAttackAnimationScale(partialTicks);
         Tessellator tessellator = Tessellator.getInstance();
         BufferBuilder bufferbuilder = tessellator.getBuffer();
         this.bindTexture(GUARDIAN_BEAM_TEXTURE);
         GlStateManager.glTexParameteri(3553, 10242, 10497);
         GlStateManager.glTexParameteri(3553, 10243, 10497);
         GlStateManager.disableLighting();
         GlStateManager.disableCull();
         GlStateManager.disableBlend();
         GlStateManager.depthMask(true);
         float f1 = 240.0F;
         OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 240.0F, 240.0F);
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         float f2 = (float)entity.world.getTotalWorldTime() + partialTicks;
         float f3 = f2 * 0.5F % 1.0F;
         float f4 = entity.getEyeHeight();
         GlStateManager.pushMatrix();
         GlStateManager.translate((float)x, (float)y + f4, (float)z);
         Vec3d vec3d = this.getPosition(entitylivingbase, (double)entitylivingbase.height * 0.5, partialTicks);
         Vec3d vec3d1 = this.getPosition(entity, (double)f4, partialTicks);
         Vec3d vec3d2 = vec3d.subtract(vec3d1);
         double d0 = vec3d2.length() + 1.0;
         vec3d2 = vec3d2.normalize();
         float f5 = (float)Math.acos(vec3d2.y);
         float f6 = (float)Math.atan2(vec3d2.z, vec3d2.x);
         GlStateManager.rotate((1.5707964F + -f6) * 57.295776F, 0.0F, 1.0F, 0.0F);
         GlStateManager.rotate(f5 * 57.295776F, 1.0F, 0.0F, 0.0F);
         int i = true;
         double d1 = (double)f2 * 0.05 * -1.5;
         bufferbuilder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
         float f7 = f * f;
         int j = 64 + (int)(f7 * 191.0F);
         int k = 32 + (int)(f7 * 191.0F);
         int l = 128 - (int)(f7 * 64.0F);
         double d2 = 0.2;
         double d3 = 0.282;
         double d4 = 0.0 + Math.cos(d1 + 2.356194490192345) * 0.282;
         double d5 = 0.0 + Math.sin(d1 + 2.356194490192345) * 0.282;
         double d6 = 0.0 + Math.cos(d1 + 0.7853981633974483) * 0.282;
         double d7 = 0.0 + Math.sin(d1 + 0.7853981633974483) * 0.282;
         double d8 = 0.0 + Math.cos(d1 + 3.9269908169872414) * 0.282;
         double d9 = 0.0 + Math.sin(d1 + 3.9269908169872414) * 0.282;
         double d10 = 0.0 + Math.cos(d1 + 5.497787143782138) * 0.282;
         double d11 = 0.0 + Math.sin(d1 + 5.497787143782138) * 0.282;
         double d12 = 0.0 + Math.cos(d1 + Math.PI) * 0.2;
         double d13 = 0.0 + Math.sin(d1 + Math.PI) * 0.2;
         double d14 = 0.0 + Math.cos(d1 + 0.0) * 0.2;
         double d15 = 0.0 + Math.sin(d1 + 0.0) * 0.2;
         double d16 = 0.0 + Math.cos(d1 + 1.5707963267948966) * 0.2;
         double d17 = 0.0 + Math.sin(d1 + 1.5707963267948966) * 0.2;
         double d18 = 0.0 + Math.cos(d1 + 4.71238898038469) * 0.2;
         double d19 = 0.0 + Math.sin(d1 + 4.71238898038469) * 0.2;
         double d20 = 0.0;
         double d21 = 0.4999;
         double d22 = (double)(-1.0F + f3);
         double d23 = d0 * 2.5 + d22;
         bufferbuilder.pos(d12, d0, d13).tex(0.4999, d23).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d12, 0.0, d13).tex(0.4999, d22).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d14, 0.0, d15).tex(0.0, d22).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d14, d0, d15).tex(0.0, d23).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d16, d0, d17).tex(0.4999, d23).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d16, 0.0, d17).tex(0.4999, d22).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d18, 0.0, d19).tex(0.0, d22).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d18, d0, d19).tex(0.0, d23).color(j, k, l, 255).endVertex();
         double d24 = 0.0;
         if (entity.ticksExisted % 2 == 0) {
            d24 = 0.5;
         }

         bufferbuilder.pos(d4, d0, d5).tex(0.5, d24 + 0.5).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d6, d0, d7).tex(1.0, d24 + 0.5).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d10, d0, d11).tex(1.0, d24).color(j, k, l, 255).endVertex();
         bufferbuilder.pos(d8, d0, d9).tex(0.5, d24).color(j, k, l, 255).endVertex();
         tessellator.draw();
         GlStateManager.popMatrix();
      }

   }

   protected ResourceLocation getEntityTexture(EntityGuardian entity) {
      return GUARDIAN_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityGuardian)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(EntityLiving var1, ICamera var2, double var3, double var5, double var7) {
      return this.shouldRender((EntityGuardian)var1, var2, var3, var5, var7);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityGuardian)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityGuardian)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityGuardian)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(Entity var1, ICamera var2, double var3, double var5, double var7) {
      return this.shouldRender((EntityGuardian)var1, var2, var3, var5, var7);
   }
}
