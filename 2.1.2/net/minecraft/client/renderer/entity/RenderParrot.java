package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelParrot;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityParrot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderParrot extends RenderLiving<EntityParrot> {
   public static final ResourceLocation[] PARROT_TEXTURES = new ResourceLocation[]{new ResourceLocation("textures/entity/parrot/parrot_red_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_green.png"), new ResourceLocation("textures/entity/parrot/parrot_yellow_blue.png"), new ResourceLocation("textures/entity/parrot/parrot_grey.png")};

   public RenderParrot(RenderManager p_i47375_1_) {
      super(p_i47375_1_, new ModelParrot(), 0.3F);
   }

   protected ResourceLocation getEntityTexture(EntityParrot entity) {
      return PARROT_TEXTURES[entity.getVariant()];
   }

   public float handleRotationFloat(EntityParrot livingBase, float partialTicks) {
      return this.getCustomBob(livingBase, partialTicks);
   }

   private float getCustomBob(EntityParrot parrot, float p_192861_2_) {
      float f = parrot.oFlap + (parrot.flap - parrot.oFlap) * p_192861_2_;
      float f1 = parrot.oFlapSpeed + (parrot.flapSpeed - parrot.oFlapSpeed) * p_192861_2_;
      return (MathHelper.sin(f) + 1.0F) * f1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public float handleRotationFloat(EntityLivingBase var1, float var2) {
      return this.handleRotationFloat((EntityParrot)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityParrot)var1);
   }
}
