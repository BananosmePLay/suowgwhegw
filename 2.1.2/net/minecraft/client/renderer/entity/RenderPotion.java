package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class RenderPotion extends RenderSnowball<EntityPotion> {
   public RenderPotion(RenderManager renderManagerIn, RenderItem itemRendererIn) {
      super(renderManagerIn, Items.POTIONITEM, itemRendererIn);
   }

   public ItemStack getStackToRender(EntityPotion entityIn) {
      return entityIn.getPotion();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ItemStack getStackToRender(Entity var1) {
      return this.getStackToRender((EntityPotion)var1);
   }
}
