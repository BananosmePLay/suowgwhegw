package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelLlamaSpit;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityLlamaSpit;
import net.minecraft.util.ResourceLocation;

public class RenderLlamaSpit extends Render<EntityLlamaSpit> {
   private static final ResourceLocation LLAMA_SPIT_TEXTURE = new ResourceLocation("textures/entity/llama/spit.png");
   private final ModelLlamaSpit model = new ModelLlamaSpit();

   public RenderLlamaSpit(RenderManager p_i47202_1_) {
      super(p_i47202_1_);
   }

   public void doRender(EntityLlamaSpit entity, double x, double y, double z, float entityYaw, float partialTicks) {
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)x, (float)y + 0.15F, (float)z);
      GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks - 90.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, 0.0F, 0.0F, 1.0F);
      this.bindEntityTexture(entity);
      if (this.renderOutlines) {
         GlStateManager.enableColorMaterial();
         GlStateManager.enableOutlineMode(this.getTeamColor(entity));
      }

      this.model.render(entity, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
      if (this.renderOutlines) {
         GlStateManager.disableOutlineMode();
         GlStateManager.disableColorMaterial();
      }

      GlStateManager.popMatrix();
      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(EntityLlamaSpit entity) {
      return LLAMA_SPIT_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityLlamaSpit)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityLlamaSpit)var1, var2, var4, var6, var8, var9);
   }
}
