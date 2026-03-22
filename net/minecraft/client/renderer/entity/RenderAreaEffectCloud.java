package net.minecraft.client.renderer.entity;

import javax.annotation.Nullable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.util.ResourceLocation;

public class RenderAreaEffectCloud extends Render<EntityAreaEffectCloud> {
   public RenderAreaEffectCloud(RenderManager manager) {
      super(manager);
   }

   @Nullable
   protected ResourceLocation getEntityTexture(EntityAreaEffectCloud entity) {
      return null;
   }

   // $FF: synthetic method
   // $FF: bridge method
   @Nullable
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityAreaEffectCloud)var1);
   }
}
