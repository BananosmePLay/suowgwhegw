package net.minecraft.client.renderer.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.util.ResourceLocation;

public class RenderTippedArrow extends RenderArrow<EntityTippedArrow> {
   public static final ResourceLocation RES_ARROW = new ResourceLocation("textures/entity/projectiles/arrow.png");
   public static final ResourceLocation RES_TIPPED_ARROW = new ResourceLocation("textures/entity/projectiles/tipped_arrow.png");

   public RenderTippedArrow(RenderManager manager) {
      super(manager);
   }

   protected ResourceLocation getEntityTexture(EntityTippedArrow entity) {
      return entity.getColor() > 0 ? RES_TIPPED_ARROW : RES_ARROW;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityTippedArrow)var1);
   }
}
