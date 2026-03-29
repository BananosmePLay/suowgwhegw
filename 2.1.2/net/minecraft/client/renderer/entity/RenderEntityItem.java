package net.minecraft.client.renderer.entity;

import java.util.Random;
import neo.0bK;
import neo.0ck;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class RenderEntityItem extends Render<EntityItem> {
   private final RenderItem itemRenderer;
   private final Random random = new Random();
   public static Random randomm = new Random();
   public static Minecraft mc = Minecraft.getMinecraft();
   public static RenderItem renderItem;
   public static long tick;
   public static double rotation;

   public RenderEntityItem(RenderManager renderManagerIn, RenderItem p_i46167_2_) {
      super(renderManagerIn);
      this.itemRenderer = p_i46167_2_;
      this.shadowSize = 0.15F;
      this.shadowOpaque = 0.75F;
   }

   private int transformModelCount(EntityItem itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
      ItemStack itemstack = itemIn.getItem();
      Item item = itemstack.getItem();
      if (item == null) {
         return 0;
      } else {
         boolean flag = p_177077_9_.isGui3d();
         int i = this.getModelCount(itemstack);
         float f = 0.25F;
         float f1 = 0bK.getInstance().moduleManager.getModule(0ck.class).isModuleState() ? 0.0F : MathHelper.sin(((float)itemIn.getAge() + p_177077_8_) / 10.0F + itemIn.hoverStart) * 0.1F + 0.1F;
         GlStateManager.rotate(0.0F, 0.0F, 1.0F, 0.0F);
         float f2 = p_177077_9_.getItemCameraTransforms().getTransform(ItemCameraTransforms.TransformType.GROUND).scale.y;
         GlStateManager.translate((float)p_177077_2_, (float)p_177077_4_ + f1 + 0.25F * f2, (float)p_177077_6_);
         if (!0bK.getInstance().moduleManager.getModule(0ck.class).isModuleState() && (flag || this.renderManager.options != null)) {
            float f3 = (((float)itemIn.getAge() + p_177077_8_) / 20.0F + itemIn.hoverStart) * 57.295776F;
            GlStateManager.rotate(f3, 0.0F, 1.0F, 0.0F);
         }

         this.shadowSize = 0bK.getInstance().moduleManager.getModule(0ck.class).isModuleState() ? 0.0F : 0.15F;
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         return i;
      }
   }

   private int getModelCount(ItemStack stack) {
      int i = 1;
      if (stack.getCount() > 48) {
         i = 5;
      } else if (stack.getCount() > 32) {
         i = 4;
      } else if (stack.getCount() > 16) {
         i = 3;
      } else if (stack.getCount() > 1) {
         i = 2;
      }

      return i;
   }

   public static boolean shouldSpreadItems() {
      return true;
   }

   public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
      ItemStack itemstack;
      boolean m3;
      if (!0bK.getInstance().moduleManager.getModule(0ck.class).isModuleState()) {
         itemstack = entity.getItem();
         int i = itemstack.isEmpty() ? 187 : Item.getIdFromItem(itemstack.getItem()) + itemstack.getMetadata();
         this.random.setSeed((long)i);
         boolean flag = false;
         if (this.bindEntityTexture(entity)) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).setBlurMipmap(false, false);
            flag = true;
         }

         GlStateManager.enableRescaleNormal();
         GlStateManager.alphaFunc(516, 0.1F);
         GlStateManager.enableBlend();
         RenderHelper.enableStandardItemLighting();
         GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
         GlStateManager.pushMatrix();
         IBakedModel ibakedmodel = this.itemRenderer.getItemModelWithOverrides(itemstack, entity.world, (EntityLivingBase)null);
         int j = this.transformModelCount(entity, x, y, z, partialTicks, ibakedmodel);
         float f = ibakedmodel.getItemCameraTransforms().ground.scale.x;
         float f1 = ibakedmodel.getItemCameraTransforms().ground.scale.y;
         float f2 = ibakedmodel.getItemCameraTransforms().ground.scale.z;
         m3 = ibakedmodel.isGui3d();
         float f7;
         float f9;
         if (!m3) {
            float f3 = -0.0F * (float)(j - 1) * 0.5F * f;
            f7 = -0.0F * (float)(j - 1) * 0.5F * f1;
            f9 = -0.09375F * (float)(j - 1) * 0.5F * f2;
            GlStateManager.translate(f3, f7, f9);
         }

         if (this.renderOutlines) {
            GlStateManager.enableColorMaterial();
            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
         }

         for(int k = 0; k < j; ++k) {
            if (m3) {
               GlStateManager.pushMatrix();
               if (k > 0) {
                  f7 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                  f9 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                  float f6 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F;
                  GlStateManager.translate(f7, f9, f6);
               }

               ibakedmodel.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GROUND);
               this.itemRenderer.renderItem(itemstack, ibakedmodel);
               GlStateManager.popMatrix();
            } else {
               GlStateManager.pushMatrix();
               if (k > 0) {
                  f7 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                  f9 = (this.random.nextFloat() * 2.0F - 1.0F) * 0.15F * 0.5F;
                  GlStateManager.translate(f7, f9, 0.0F);
               }

               ibakedmodel.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GROUND);
               this.itemRenderer.renderItem(itemstack, ibakedmodel);
               GlStateManager.popMatrix();
               GlStateManager.translate(0.0F * f, 0.0F * f1, 0.09375F * f2);
            }
         }

         if (this.renderOutlines) {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
         }

         GlStateManager.popMatrix();
         GlStateManager.disableRescaleNormal();
         GlStateManager.disableBlend();
         this.bindEntityTexture(entity);
         if (flag) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).restoreLastBlurMipmap();
         }
      } else {
         rotation = (double)(System.nanoTime() - tick) / 3000000.0 * (double)0ck.physicsSpeed.value;
         if (!mc.inGameHasFocus) {
            rotation = 0.0;
         }

         if ((itemstack = entity.getItem()).getItem() != null) {
            randomm.setSeed(187L);
            boolean flag2 = false;
            if (TextureMap.LOCATION_BLOCKS_TEXTURE != null) {
               mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
               mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
               flag2 = true;
            }

            GlStateManager.enableRescaleNormal();
            GlStateManager.alphaFunc(516, 0.1F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.pushMatrix();
            IBakedModel ibakedmodel = renderItem.getItemModelMesher().getItemModel(itemstack);
            int k = this.transformModelCount(entity, x, y, z, partialTicks, ibakedmodel);
            BlockPos pos = new BlockPos(entity);
            if (entity.rotationPitch > 360.0F) {
               entity.rotationPitch = 0.0F;
            }

            if (entity != null && !Double.isNaN((double)entity.getAge()) && !Double.isNaN((double)entity.getAir()) && !Double.isNaN((double)entity.getEntityId()) && entity.getPosition() != null && !entity.onGround) {
               BlockPos posUp = new BlockPos(entity);
               posUp.add(0, 1, 0);
               Material m1 = entity.world.getBlockState(posUp).getBlock().getMaterial((IBlockState)null);
               Material m2 = entity.world.getBlockState(pos).getBlock().getMaterial((IBlockState)null);
               m3 = entity.isInsideOfMaterial(Material.WATER);
               boolean m4 = entity.isInWater();
               entity.rotationPitch = m3 | m1 == Material.WATER | m2 == Material.WATER | m4 ? (entity.rotationPitch += (float)(rotation / 4.0)) : (entity.rotationPitch += (float)(rotation * 2.0));
            }

            GL11.glRotatef(entity.rotationYaw, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(entity.rotationPitch + 90.0F, 1.0F, 0.0F, 0.0F);

            for(int l = 0; l < k; ++l) {
               if (ibakedmodel.isAmbientOcclusion()) {
                  GlStateManager.pushMatrix();
                  GlStateManager.scale(0.3F, 0.3F, 0.3F);
                  renderItem.renderItem(itemstack, ibakedmodel);
                  GlStateManager.popMatrix();
               } else {
                  GlStateManager.pushMatrix();
                  GlStateManager.scale(0.6F, 0.6F, 0.6F);
                  if (l > 0 && shouldSpreadItems()) {
                     GlStateManager.translate(0.0F, 0.0F, 0.046875F * (float)l);
                  }

                  renderItem.renderItem(itemstack, ibakedmodel);
                  if (!shouldSpreadItems()) {
                     GlStateManager.translate(0.0F, 0.0F, 0.046875F);
                  }

                  GlStateManager.popMatrix();
               }
            }

            GlStateManager.popMatrix();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
            mc.getRenderManager().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
            if (flag2) {
               mc.getRenderManager().renderEngine.getTexture(TextureMap.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
            }
         }
      }

      super.doRender(entity, x, y, z, entityYaw, partialTicks);
   }

   protected ResourceLocation getEntityTexture(EntityItem entity) {
      return TextureMap.LOCATION_BLOCKS_TEXTURE;
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected ResourceLocation getEntityTexture(Entity var1) {
      return this.getEntityTexture((EntityItem)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Entity var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((EntityItem)var1, var2, var4, var6, var8, var9);
   }

   static {
      renderItem = mc.getRenderItem();
   }
}
