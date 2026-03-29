package neo;

import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class wD<T extends Jc> extends vI<T> {
   private static final ResourceLocation MINECART_TEXTURES = new ResourceLocation("textures/entity/minecart.png");
   protected nH modelMinecart = new on();

   public wD(wC renderManagerIn) {
      super(renderManagerIn);
      this.shadowSize = 0.5F;
   }

   public void doRender(T entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      this.bindEntityTexture(entity);
      long i = (long)entity.getEntityId() * 493286711L;
      i = i * i * 4392167121L + i * 98761L;
      float f = (((float)(i >> 16 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float f1 = (((float)(i >> 20 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      float f2 = (((float)(i >> 24 & 7L) + 0.5F) / 8.0F - 0.5F) * 0.004F;
      yh.translate(f, f1, f2);
      double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
      double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
      double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
      double d3 = 0.30000001192092896;
      Vec3d vec3d = entity.getPos(d0, d1, d2);
      float f3 = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
      if (vec3d != null) {
         Vec3d vec3d1 = entity.getPosOffset(d0, d1, d2, 0.30000001192092896);
         Vec3d vec3d2 = entity.getPosOffset(d0, d1, d2, -0.30000001192092896);
         if (vec3d1 == null) {
            vec3d1 = vec3d;
         }

         if (vec3d2 == null) {
            vec3d2 = vec3d;
         }

         x += vec3d.x - d0;
         y += (vec3d1.y + vec3d2.y) / 2.0 - d1;
         z += vec3d.z - d2;
         Vec3d vec3d3 = vec3d2.add(-vec3d1.x, -vec3d1.y, -vec3d1.z);
         if (vec3d3.length() != 0.0) {
            vec3d3 = vec3d3.normalize();
            entityYaw = (float)(Math.atan2(vec3d3.z, vec3d3.x) * 180.0 / Math.PI);
            f3 = (float)(Math.atan(vec3d3.y) * 73.0);
         }
      }

      yh.translate((float)x, (float)y + 0.375F, (float)z);
      yh.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
      yh.rotate(-f3, 0.0F, 0.0F, 1.0F);
      float f5 = (float)entity.getRollingAmplitude() - partialTicks;
      float f6 = entity.getDamage() - partialTicks;
      if (f6 < 0.0F) {
         f6 = 0.0F;
      }

      if (f5 > 0.0F) {
         yh.rotate(MathHelper.sin(f5) * f5 * f6 / 10.0F * (float)entity.getRollingDirection(), 1.0F, 0.0F, 0.0F);
      }

      int j = entity.getDisplayTileOffset();
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      in iblockstate = entity.getDisplayTile();
      if (iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
         yh.pushMatrix();
         this.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         float f4 = 0.75F;
         yh.scale(0.75F, 0.75F, 0.75F);
         yh.translate(-0.5F, (float)(j - 8) / 16.0F, 0.5F);
         this.renderCartContents(entity, partialTicks, iblockstate);
         yh.popMatrix();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.bindEntityTexture(entity);
      }

      yh.scale(-1.0F, -1.0F, 1.0F);
      this.modelMinecart.render(entity, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      yh.popMatrix();
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(T entity) {
      return MINECART_TEXTURES;
   }

   protected void renderCartContents(T p_188319_1_, float partialTicks, in p_188319_3_) {
      yh.pushMatrix();
      nC.getMinecraft().getBlockRendererDispatcher().renderBlockBrightness(p_188319_3_, p_188319_1_.getBrightness());
      yh.popMatrix();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Jc)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Jc)var1, var2, var4, var6, var8, var9);
   }
}
