package neo;

import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class wr extends ww<Kl> {
   private static final ResourceLocation ILLUSIONIST = new ResourceLocation("textures/entity/illager/illusionist.png");

   public wr(wC p_i47477_1_) {
      super(p_i47477_1_, new og(0.0F, 0.0F, 64, 64), 0.5F);
      this.addLayer(new vr(this) {
         public void doRenderLayer(Iw entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
            if (((Kh)entitylivingbaseIn).isSpellcasting() || ((Kh)entitylivingbaseIn).isAggressive()) {
               super.doRenderLayer(entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);
            }

         }

         protected void translateToHand(EnumHandSide p_191361_1_) {
            ((og)this.livingEntityRenderer.getMainModel()).getArm(p_191361_1_).postRender(0.0625F);
         }
      });
      ((og)this.getMainModel()).hat.showModel = true;
   }

   protected ResourceLocation getEntityTexture(Kl entity) {
      return ILLUSIONIST;
   }

   protected void preRenderCallback(Kl entitylivingbaseIn, float partialTickTime) {
      float f = 0.9375F;
      yh.scale(0.9375F, 0.9375F, 0.9375F);
   }

   public void doRender(Kl entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.isInvisible()) {
         Vec3d[] avec3d = ((Kh)entity).getRenderLocations(partialTicks);
         float f = this.handleRotationFloat(entity, partialTicks);

         for(int i = 0; i < avec3d.length; ++i) {
            super.doRender((Iu)entity, x + avec3d[i].x + (double)MathHelper.cos((float)i + f * 0.5F) * 0.025, y + avec3d[i].y + (double)MathHelper.cos((float)i + f * 0.75F) * 0.0125, z + avec3d[i].z + (double)MathHelper.cos((float)i + f * 0.7F) * 0.025, entityYaw, partialTicks);
         }
      } else {
         super.doRender((Iu)entity, x, y, z, entityYaw, partialTicks);
      }

   }

   public void renderName(Kl entity, double x, double y, double z) {
      super.renderName(entity, x, y, z);
   }

   protected boolean isVisible(Kl p_193115_1_) {
      return true;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iu var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kl)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderName(Iw var1, double var2, double var4, double var6) {
      this.renderName((Kl)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(Iw var1, float var2) {
      this.preRenderCallback((Kl)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean isVisible(Iw var1) {
      return this.isVisible((Kl)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Iw var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kl)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((Kl)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void renderName(Ig var1, double var2, double var4, double var6) {
      this.renderName((Kl)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((Kl)var1, var2, var4, var6, var8, var9);
   }
}
