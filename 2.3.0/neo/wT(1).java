package neo;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class wT extends ww<KD> {
   public static final ResourceLocation[] SHULKER_ENDERGOLEM_TEXTURE = new ResourceLocation[]{new ResourceLocation("textures/entity/shulker/shulker_white.png"), new ResourceLocation("textures/entity/shulker/shulker_orange.png"), new ResourceLocation("textures/entity/shulker/shulker_magenta.png"), new ResourceLocation("textures/entity/shulker/shulker_light_blue.png"), new ResourceLocation("textures/entity/shulker/shulker_yellow.png"), new ResourceLocation("textures/entity/shulker/shulker_lime.png"), new ResourceLocation("textures/entity/shulker/shulker_pink.png"), new ResourceLocation("textures/entity/shulker/shulker_gray.png"), new ResourceLocation("textures/entity/shulker/shulker_silver.png"), new ResourceLocation("textures/entity/shulker/shulker_cyan.png"), new ResourceLocation("textures/entity/shulker/shulker_purple.png"), new ResourceLocation("textures/entity/shulker/shulker_blue.png"), new ResourceLocation("textures/entity/shulker/shulker_brown.png"), new ResourceLocation("textures/entity/shulker/shulker_green.png"), new ResourceLocation("textures/entity/shulker/shulker_red.png"), new ResourceLocation("textures/entity/shulker/shulker_black.png")};

   public wT(wC p_i47194_1_) {
      super(p_i47194_1_, new oA(), 0.0F);
      this.addLayer(new wS(this));
   }

   public oA getMainModel() {
      return (oA)super.getMainModel();
   }

   public void doRender(KD entity, double x, double y, double z, float entityYaw, float partialTicks) {
      int i = entity.getClientTeleportInterp();
      if (i > 0 && entity.isAttachedToBlock()) {
         BlockPos blockpos = entity.getAttachmentPos();
         BlockPos blockpos1 = entity.getOldAttachPos();
         double d0 = (double)((float)i - partialTicks) / 6.0;
         d0 *= d0;
         double d1 = (double)(blockpos.getX() - blockpos1.getX()) * d0;
         double d2 = (double)(blockpos.getY() - blockpos1.getY()) * d0;
         double d3 = (double)(blockpos.getZ() - blockpos1.getZ()) * d0;
         super.doRender((Iu)entity, x - d1, y - d2, z - d3, entityYaw, partialTicks);
      } else {
         super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
      }

   }

   public boolean shouldRender(KD livingEntity, uO camera, double camX, double camY, double camZ) {
      if (super.shouldRender((Iu)livingEntity, camera, camX, camY, camZ)) {
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

   protected ResourceLocation getEntityTexture(KD entity) {
      return SHULKER_ENDERGOLEM_TEXTURE[entity.getColor().getMetadata()];
   }

   protected void applyRotations(KD entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
      switch (entityLiving.getAttachmentFacing()) {
         case DOWN:
         default:
            break;
         case EAST:
            yh.translate(0.5F, 0.5F, 0.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(90.0F, 0.0F, 0.0F, 1.0F);
            break;
         case WEST:
            yh.translate(-0.5F, 0.5F, 0.0F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(-90.0F, 0.0F, 0.0F, 1.0F);
            break;
         case NORTH:
            yh.translate(0.0F, 0.5F, -0.5F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            break;
         case SOUTH:
            yh.translate(0.0F, 0.5F, 0.5F);
            yh.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
            break;
         case UP:
            yh.translate(0.0F, 1.0F, 0.0F);
            yh.rotate(180.0F, 1.0F, 0.0F, 0.0F);
      }

   }

   protected void preRenderCallback(KD entitylivingbaseIn, float partialTickTime) {
      float f = 0.999F;
      yh.scale(0.999F, 0.999F, 0.999F);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((KD)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(Iu var1, uO var2, double var3, double var5, double var7) {
      return this.shouldRender((KD)var1, var2, var3, var5, var7);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((KD)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(Iw var1, float var2, float var3, float var4) {
      this.applyRotations((KD)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((KD)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public nH getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((KD)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((KD)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean shouldRender(Ig var1, uO var2, double var3, double var5, double var7) {
      return this.shouldRender((KD)var1, var2, var3, var5, var7);
   }
}
