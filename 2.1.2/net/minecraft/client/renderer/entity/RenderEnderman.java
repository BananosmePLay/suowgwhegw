package net.minecraft.client.renderer.entity;

import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderman;
import net.minecraft.client.renderer.entity.layers.LayerEndermanEyes;
import net.minecraft.client.renderer.entity.layers.LayerHeldBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.ResourceLocation;

public class RenderEnderman extends RenderLiving<EntityEnderman> {
   private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation("textures/entity/enderman/enderman.png");
   private final Random rnd = new Random();

   public RenderEnderman(RenderManager renderManagerIn) {
      super(renderManagerIn, new ModelEnderman(0.0F), 0.5F);
      this.addLayer(new LayerEndermanEyes(this));
      this.addLayer(new LayerHeldBlock(this));
   }

   public ModelEnderman getMainModel() {
      return (ModelEnderman)super.getMainModel();
   }

   public void doRender(EntityEnderman entity, double x, double y, double z, float entityYaw, float partialTicks) {
      IBlockState iblockstate = entity.getHeldBlockState();
      ModelEnderman modelenderman = this.getMainModel();
      modelenderman.isCarrying = iblockstate != null;
      modelenderman.isAttacking = entity.isScreaming();
      if (entity.isScreaming()) {
         double d0 = 0.02;
         x += this.rnd.nextGaussian() * 0.02;
         z += this.rnd.nextGaussian() * 0.02;
      }

      super.doRender((EntityLiving)entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(EntityEnderman entity) {
      return ENDERMAN_TEXTURES;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLiving var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityEnderman)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(EntityLivingBase var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityEnderman)var1, var2, var4, var6, var8, var9);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public ModelBase getMainModel() {
      return this.getMainModel();
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityEnderman)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityEnderman)var1, var2, var4, var6, var8, var9);
   }
}
