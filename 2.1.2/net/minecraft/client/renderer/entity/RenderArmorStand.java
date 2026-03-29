package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelArmorStand;
import net.minecraft.client.model.ModelArmorStandArmor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerElytra;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class RenderArmorStand extends RenderLivingBase<EntityArmorStand> {
   public static final ResourceLocation TEXTURE_ARMOR_STAND = new ResourceLocation("textures/entity/armorstand/wood.png");

   public RenderArmorStand(RenderManager manager) {
      super(manager, new ModelArmorStand(), 0.0F);
      LayerBipedArmor layerbipedarmor = new LayerBipedArmor(this) {
         protected void initArmor() {
            this.modelLeggings = new ModelArmorStandArmor(0.5F);
            this.modelArmor = new ModelArmorStandArmor(1.0F);
         }
      };
      this.addLayer(layerbipedarmor);
      this.addLayer(new LayerHeldItem(this));
      this.addLayer(new LayerElytra(this));
      this.addLayer(new LayerCustomHead(this.getMainModel().bipedHead));
   }

   protected ResourceLocation getEntityTexture(EntityArmorStand entity) {
      return TEXTURE_ARMOR_STAND;
   }

   public ModelArmorStand getMainModel() {
      return (ModelArmorStand)super.getMainModel();
   }

   protected void applyRotations(EntityArmorStand entityLiving, float ageInTicks, float rotationYaw, float partialTicks) {
      GlStateManager.rotate(180.0F - rotationYaw, 0.0F, 1.0F, 0.0F);
      float f = (float)(entityLiving.world.getTotalWorldTime() - entityLiving.punchCooldown) + partialTicks;
      if (f < 5.0F) {
         GlStateManager.rotate(MathHelper.sin(f / 1.5F * 3.1415927F) * 3.0F, 0.0F, 1.0F, 0.0F);
      }

   }

   protected boolean canRenderName(EntityArmorStand entity) {
      return entity.getAlwaysRenderNameTag();
   }

   public void doRender(EntityArmorStand entity, double x, double y, double z, float entityYaw, float partialTicks) {
      if (entity.hasMarker()) {
         this.renderMarker = true;
      }

      super.doRender((EntityLivingBase)entity, x, y, z, entityYaw, partialTicks);
      if (entity.hasMarker()) {
         this.renderMarker = false;
      }

   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(EntityLivingBase var1) {
      return this.canRenderName((EntityArmorStand)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void applyRotations(EntityLivingBase var1, float var2, float var3, float var4) {
      this.applyRotations((EntityArmorStand)var1, var2, var3, var4);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityArmorStand)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ModelBase getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityArmorStand)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected boolean canRenderName(Entity var1) {
      return this.canRenderName((EntityArmorStand)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityArmorStand)var1, var2, var4, var6, var8, var9);
   }
}
