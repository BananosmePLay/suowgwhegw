package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.src.Config;
import net.optifine.shaders.Shaders;

public abstract class RenderLiving<T extends EntityLiving> extends RenderLivingBase<T> {
   public RenderLiving(RenderManager rendermanagerIn, ModelBase modelbaseIn, float shadowsizeIn) {
      super(rendermanagerIn, modelbaseIn, shadowsizeIn);
   }

   protected boolean canRenderName(T entity) {
      return super.canRenderName((EntityLivingBase)entity) && (entity.getAlwaysRenderNameTagForRender() || entity.hasCustomName() && entity == this.renderManager.pointedEntity);
   }

   public boolean shouldRender(T livingEntity, ICamera camera, double camX, double camY, double camZ) {
      if (super.shouldRender(livingEntity, camera, camX, camY, camZ)) {
         return true;
      } else if (livingEntity.getLeashed() && livingEntity.getLeashHolder() != null) {
         Entity entity = livingEntity.getLeashHolder();
         return camera.isBoundingBoxInFrustum(entity.getRenderBoundingBox());
      } else {
         return false;
      }
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      super.doRender((EntityLivingBase)entity, x, y, z, entityYaw, partialTicks);
      if (!this.renderOutlines) {
         this.renderLeash(entity, x, y, z, entityYaw, partialTicks);
      }

   }

   public void setLightmap(T entityLivingIn) {
      int i = entityLivingIn.getBrightnessForRender();
      int j = i % 65536;
      int k = i / 65536;
      OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)j, (float)k);
   }

   private double interpolateValue(double start, double end, double pct) {
      return start + (end - start) * pct;
   }

   protected void renderLeash(T entityLivingIn, double x, double y, double z, float entityYaw, float partialTicks) {
      if (!Config.isShaders() || !Shaders.isShadowPass) {
         Entity entity = entityLivingIn.getLeashHolder();
         if (entity != null) {
            y -= (1.6 - (double)entityLivingIn.height) * 0.5;
            Tessellator tessellator = Tessellator.getInstance();
            BufferBuilder bufferbuilder = tessellator.getBuffer();
            double d0 = this.interpolateValue((double)entity.prevRotationYaw, (double)entity.rotationYaw, (double)(partialTicks * 0.5F)) * 0.01745329238474369;
            double d1 = this.interpolateValue((double)entity.prevRotationPitch, (double)entity.rotationPitch, (double)(partialTicks * 0.5F)) * 0.01745329238474369;
            double d2 = Math.cos(d0);
            double d3 = Math.sin(d0);
            double d4 = Math.sin(d1);
            if (entity instanceof EntityHanging) {
               d2 = 0.0;
               d3 = 0.0;
               d4 = -1.0;
            }

            double d5 = Math.cos(d1);
            double d6 = this.interpolateValue(entity.prevPosX, entity.posX, (double)partialTicks) - d2 * 0.7 - d3 * 0.5 * d5;
            double d7 = this.interpolateValue(entity.prevPosY + (double)entity.getEyeHeight() * 0.7, entity.posY + (double)entity.getEyeHeight() * 0.7, (double)partialTicks) - d4 * 0.5 - 0.25;
            double d8 = this.interpolateValue(entity.prevPosZ, entity.posZ, (double)partialTicks) - d3 * 0.7 + d2 * 0.5 * d5;
            double d9 = this.interpolateValue((double)entityLivingIn.prevRenderYawOffset, (double)entityLivingIn.renderYawOffset, (double)partialTicks) * 0.01745329238474369 + 1.5707963267948966;
            d2 = Math.cos(d9) * (double)entityLivingIn.width * 0.4;
            d3 = Math.sin(d9) * (double)entityLivingIn.width * 0.4;
            double d10 = this.interpolateValue(entityLivingIn.prevPosX, entityLivingIn.posX, (double)partialTicks) + d2;
            double d11 = this.interpolateValue(entityLivingIn.prevPosY, entityLivingIn.posY, (double)partialTicks);
            double d12 = this.interpolateValue(entityLivingIn.prevPosZ, entityLivingIn.posZ, (double)partialTicks) + d3;
            x += d2;
            z += d3;
            double d13 = (double)((float)(d6 - d10));
            double d14 = (double)((float)(d7 - d11));
            double d15 = (double)((float)(d8 - d12));
            GlStateManager.disableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableCull();
            if (Config.isShaders()) {
               Shaders.beginLeash();
            }

            int i = true;
            double d16 = 0.025;
            bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);

            int k;
            float f4;
            float f5;
            float f6;
            float f7;
            for(k = 0; k <= 24; ++k) {
               f4 = 0.5F;
               f5 = 0.4F;
               f6 = 0.3F;
               if (k % 2 == 0) {
                  f4 *= 0.7F;
                  f5 *= 0.7F;
                  f6 *= 0.7F;
               }

               f7 = (float)k / 24.0F;
               bufferbuilder.pos(x + d13 * (double)f7 + 0.0, y + d14 * (double)(f7 * f7 + f7) * 0.5 + (double)((24.0F - (float)k) / 18.0F + 0.125F), z + d15 * (double)f7).color(f4, f5, f6, 1.0F).endVertex();
               bufferbuilder.pos(x + d13 * (double)f7 + 0.025, y + d14 * (double)(f7 * f7 + f7) * 0.5 + (double)((24.0F - (float)k) / 18.0F + 0.125F) + 0.025, z + d15 * (double)f7).color(f4, f5, f6, 1.0F).endVertex();
            }

            tessellator.draw();
            bufferbuilder.begin(5, DefaultVertexFormats.POSITION_COLOR);

            for(k = 0; k <= 24; ++k) {
               f4 = 0.5F;
               f5 = 0.4F;
               f6 = 0.3F;
               if (k % 2 == 0) {
                  f4 *= 0.7F;
                  f5 *= 0.7F;
                  f6 *= 0.7F;
               }

               f7 = (float)k / 24.0F;
               bufferbuilder.pos(x + d13 * (double)f7 + 0.0, y + d14 * (double)(f7 * f7 + f7) * 0.5 + (double)((24.0F - (float)k) / 18.0F + 0.125F) + 0.025, z + d15 * (double)f7).color(f4, f5, f6, 1.0F).endVertex();
               bufferbuilder.pos(x + d13 * (double)f7 + 0.025, y + d14 * (double)(f7 * f7 + f7) * 0.5 + (double)((24.0F - (float)k) / 18.0F + 0.125F), z + d15 * (double)f7 + 0.025).color(f4, f5, f6, 1.0F).endVertex();
            }

            tessellator.draw();
            if (Config.isShaders()) {
               Shaders.endLeash();
            }

            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
            GlStateManager.enableCull();
         }
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(EntityLivingBase var1) {
      return this.canRenderName((EntityLiving)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(Entity var1) {
      return this.canRenderName((EntityLiving)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityLiving)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(Entity var1, ICamera var2, double var3, double var5, double var7) {
      return this.shouldRender((EntityLiving)var1, var2, var3, var5, var7);
   }
}
