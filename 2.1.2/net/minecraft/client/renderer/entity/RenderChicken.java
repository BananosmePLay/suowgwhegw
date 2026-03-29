package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelChicken;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderChicken extends RenderLiving<EntityChicken> {
   private static final ResourceLocation CHICKEN_TEXTURES = new ResourceLocation("textures/entity/chicken.png");

   public RenderChicken(RenderManager p_i47211_1_) {
      super(p_i47211_1_, new ModelChicken(), 0.3F);
   }

   protected ResourceLocation getEntityTexture(EntityChicken entity) {
      return CHICKEN_TEXTURES;
   }

   protected float handleRotationFloat(EntityChicken livingBase, float partialTicks) {
      float f = livingBase.oFlap + (livingBase.wingRotation - livingBase.oFlap) * partialTicks;
      float f1 = livingBase.oFlapSpeed + (livingBase.destPos - livingBase.oFlapSpeed) * partialTicks;
      return (MathHelper.sin(f) + 1.0F) * f1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected float handleRotationFloat(EntityLivingBase var1, float var2) {
      return this.handleRotationFloat((EntityChicken)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityChicken)var1);
   }
}
