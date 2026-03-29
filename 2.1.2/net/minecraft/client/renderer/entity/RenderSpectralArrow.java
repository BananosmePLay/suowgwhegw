package net.minecraft.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntitySpectralArrow;
import net.minecraft.util.ResourceLocation;

public class RenderSpectralArrow extends RenderArrow<EntitySpectralArrow> {
   public static final ResourceLocation RES_SPECTRAL_ARROW = new ResourceLocation("textures/entity/projectiles/spectral_arrow.png");

   public RenderSpectralArrow(RenderManager manager) {
      super(manager);
   }

   protected ResourceLocation getEntityTexture(EntitySpectralArrow entity) {
      return RES_SPECTRAL_ARROW;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntitySpectralArrow)var1);
   }
}
