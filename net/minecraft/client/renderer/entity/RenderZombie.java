package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.util.ResourceLocation;

public class RenderZombie extends RenderBiped<EntityZombie> {
   private static final ResourceLocation ZOMBIE_TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

   public RenderZombie(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelZombie(), 0.5F);
      LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
         protected void initArmor() {
            this.modelLeggings = new ModelZombie(0.5F, true);
            this.modelArmor = new ModelZombie(1.0F, true);
         }
      };
      this.addLayer(layerbipedarmor);
   }

   protected ResourceLocation getEntityTexture(EntityZombie entity) {
      return ZOMBIE_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(EntityLiving var1) {
      return this.getEntityTexture((EntityZombie)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityZombie)var1);
   }
}
