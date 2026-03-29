package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelZombieVillager;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombieVillager;
import net.minecraft.util.ResourceLocation;

public class RenderZombieVillager extends RenderBiped<EntityZombieVillager> {
   private static final ResourceLocation ZOMBIE_VILLAGER_TEXTURES = new ResourceLocation("textures/entity/zombie_villager/zombie_villager.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_FARMER_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_farmer.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_LIBRARIAN_LOC = new ResourceLocation("textures/entity/zombie_villager/zombie_librarian.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_PRIEST_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_priest.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_SMITH_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_smith.png");
   private static final ResourceLocation ZOMBIE_VILLAGER_BUTCHER_LOCATION = new ResourceLocation("textures/entity/zombie_villager/zombie_butcher.png");

   public RenderZombieVillager(RenderManager p_i47186_1_) {
      super(p_i47186_1_, new ModelZombieVillager(), 0.5F);
      this.addLayer(new LayerVillagerArmor(this));
   }

   protected ResourceLocation getEntityTexture(EntityZombieVillager entity) {
      switch (entity.getProfession()) {
         case 0:
            return ZOMBIE_VILLAGER_FARMER_LOCATION;
         case 1:
            return ZOMBIE_VILLAGER_LIBRARIAN_LOC;
         case 2:
            return ZOMBIE_VILLAGER_PRIEST_LOCATION;
         case 3:
            return ZOMBIE_VILLAGER_SMITH_LOCATION;
         case 4:
            return ZOMBIE_VILLAGER_BUTCHER_LOCATION;
         case 5:
         default:
            return ZOMBIE_VILLAGER_TEXTURES;
      }
   }

   protected void applyRotations(EntityZombieVillager entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      if (entityLiving.isConverting()) {
         rotationYaw += (float)(Math.cos((double)entityLiving.ticksExisted * 3.25) * Math.PI * 0.25);
      }

      super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(EntityLiving var1) {
      return this.getEntityTexture((EntityZombieVillager)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(EntityLivingBase var1, float var2, float var3, float var4) {
      this.applyRotations((EntityZombieVillager)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityZombieVillager)var1);
   }
}
