package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityGiantZombie;
import net.minecraft.util.ResourceLocation;

public class RenderGiantZombie extends RenderLiving<EntityGiantZombie> {
   private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");
   private final float scale;

   public RenderGiantZombie(RenderManager p_i47206_1_, float scaleIn) {
      super(p_i47206_1_, new ModelZombie(), 0.5F * scaleIn);
      this.scale = scaleIn;
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new LayerBipedArmor(this) {
         protected void initArmor() {
            this.modelLeggings = new ModelZombie(0.5F, true);
            this.modelArmor = new ModelZombie(1.0F, true);
         }
      });
   }

   public void transformHeldFull3DItemLayer() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   protected void preRenderCallback(EntityGiantZombie entitylivingbaseIn, float partialTickTime) {
      GlStateManager.scale(this.scale, this.scale, this.scale);
   }

   protected ResourceLocation getEntityTexture(EntityGiantZombie entity) {
      return ZOMBIE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void preRenderCallback(EntityLivingBase var1, float var2) {
      this.preRenderCallback((EntityGiantZombie)var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityGiantZombie)var1);
   }
}
