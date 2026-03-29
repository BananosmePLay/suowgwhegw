package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelShulker;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.culling.ICamera;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityShulker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class RenderShulker extends RenderLiving<EntityShulker> {
   public static final ResourceLocation[] SHULKER_ENDERGOLEM_TEXTURE = new ResourceLocation[]{new ResourceLocation("textures/entity/shulker/shulker_white.png"), new ResourceLocation("textures/entity/shulker/shulker_orange.png"), new ResourceLocation("textures/entity/shulker/shulker_magenta.png"), new ResourceLocation("textures/entity/shulker/shulker_light_blue.png"), new ResourceLocation("textures/entity/shulker/shulker_yellow.png"), new ResourceLocation("textures/entity/shulker/shulker_lime.png"), new ResourceLocation("textures/entity/shulker/shulker_pink.png"), new ResourceLocation("textures/entity/shulker/shulker_gray.png"), new ResourceLocation("textures/entity/shulker/shulker_silver.png"), new ResourceLocation("textures/entity/shulker/shulker_cyan.png"), new ResourceLocation("textures/entity/shulker/shulker_purple.png"), new ResourceLocation("textures/entity/shulker/shulker_blue.png"), new ResourceLocation("textures/entity/shulker/shulker_brown.png"), new ResourceLocation("textures/entity/shulker/shulker_green.png"), new ResourceLocation("textures/entity/shulker/shulker_red.png"), new ResourceLocation("textures/entity/shulker/shulker_black.png")};

   public RenderShulker(RenderManager p_i47194_1_) {
      super(p_i47194_1_, new ModelShulker(), 0.0F);
      this.addLayer(new HeadLayer());
   }

   public ModelShulker getMainModel() {
      return (ModelShulker)super.getMainModel();
   }

   public void doRender(EntityShulker entity, double x, double y, double z, float entityYaw, float partialTicks) {
      int i = entity.getClientTeleportInterp();
      if (i > 0 && entity.isAttachedToBlock()) {
         BlockPos blockpos = entity.getAttachmentPos();
         BlockPos blockpos1 = entity.getOldAttachPos();
         double d0 = (double)((float)i - partialTicks) / 6.0;
         d0 *= d0;
         double d1 = (double)(blockpos.getX() - blockpos1.getX()) * d0;
         double d2 = (double)(blockpos.getY() - blockpos1.getY()) * d0;
         double d3 = (double)(blockpos.getZ() - blockpos1.getZ()) * d0;
         super.doRender((EntityLiving)entity, x - d1, y - d2, z - d3, entityYaw, partialTicks);
      } else {
         super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
      }

   }

   public boolean shouldRender(EntityShulker livingEntity, ICamera camera, double camX, double camY, double camZ) {
      if (super.shouldRender((EntityLiving)livingEntity, camera, camX, camY, camZ)) {
         return true;
      } else {
         if (livingEntity.getClientTeleportInterp() > 0 && livingEntity.isAttachedToBlock()) {
            BlockPos blockpos = livingEntity.getOldAttachPos();
            BlockPos blockpos1 = livingEntity.getAttachmentPos();
            Vec3d vec3d = new Vec3d((double)blockpos1.getX(), (double)blockpos1.getY(), (double)blockpos1.getZ());
            Vec3d vec3d1 = new Vec3d((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ());
            if (camera.isBoundingBoxInFrustum(new AxisAlignedBB(vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y, vec3d.z))) {
               return true;
            }
         }

         return false;
      }
   }

   protected ResourceLocation getEntityTexture(EntityShulker entity) {
      return SHULKER_ENDERGOLEM_TEXTURE[entity.getColor().getMetadata()];
   }

   protected void applyRotations(EntityShulker entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
      switch (entityLiving.getAttachmentFacing()) {
         case DOWN:
         default:
            break;
         case EAST:
            GlStateManager.translate(0.5F, 0.5F, 0.0F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            break;
         case WEST:
            GlStateManager.translate(-0.5F, 0.5F, 0.0F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            break;
         case NORTH:
            GlStateManager.translate(0.0F, 0.5F, -0.5F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            break;
         case SOUTH:
            GlStateManager.translate(0.0F, 0.5F, 0.5F);
            GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            break;
         case UP:
            GlStateManager.translate(0.0F, 1.0F, 0.0F);
            GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
      }

   }

   protected void preRenderCallback(EntityShulker entitylivingbaseIn, float partialTickTime) {
      float f = 0.999F;
      GlStateManager.scale(0.999F, 0.999F, 0.999F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityShulker)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(EntityLiving var1, ICamera var2, double var3, double var5, double var7) {
      return this.shouldRender((EntityShulker)var1, var2, var3, var5, var7);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityShulker)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(EntityLivingBase var1, float var2, float var3, float var4) {
      this.applyRotations((EntityShulker)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityShulker)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ModelBase getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityShulker)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityShulker)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(Entity var1, ICamera var2, double var3, double var5, double var7) {
      return this.shouldRender((EntityShulker)var1, var2, var3, var5, var7);
   }

   class HeadLayer implements LayerRenderer<EntityShulker> {
      private HeadLayer() {
      }

      public void doRenderLayer(EntityShulker entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
         GlStateManager.pushMatrix();
         switch (entitylivingbaseIn.getAttachmentFacing()) {
            case DOWN:
            default:
               break;
            case EAST:
               GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
               GlStateManager.translate(1.0F, -1.0F, 0.0F);
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               break;
            case WEST:
               GlStateManager.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
               GlStateManager.translate(-1.0F, -1.0F, 0.0F);
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
               break;
            case NORTH:
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
               GlStateManager.translate(0.0F, -1.0F, -1.0F);
               break;
            case SOUTH:
               GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
               GlStateManager.translate(0.0F, -1.0F, 1.0F);
               break;
            case UP:
               GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
               GlStateManager.translate(0.0F, -2.0F, 0.0F);
         }

         ModelRenderer modelrenderer = RenderShulker.this.getMainModel().head;
         modelrenderer.rotateAngleY = netHeadYaw * 0.017453292F;
         modelrenderer.rotateAngleX = headPitch * 0.017453292F;
         RenderShulker.this.bindTexture(RenderShulker.SHULKER_ENDERGOLEM_TEXTURE[entitylivingbaseIn.getColor().getMetadata()]);
         modelrenderer.render(scale);
         GlStateManager.popMatrix();
      }

      public boolean shouldCombineTextures() {
         return false;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public void doRenderLayer(EntityLivingBase var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
         this.doRenderLayer((EntityShulker)var1, var2, var3, var4, var5, var6, var7, var8);
      }

      // $FF: synthetic method
      HeadLayer(Object x1) {
         this();
      }
   }
}
