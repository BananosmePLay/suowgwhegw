package neo;

import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class wt extends vI<IZ> {
   private static final ResourceLocation MAP_BACKGROUND_TEXTURES = new ResourceLocation("textures/map/map_background.png");
   private final nC mc = nC.getMinecraft();
   private final sD itemFrameModel = new sD("item_frame", "normal");
   private final sD mapModel = new sD("item_frame", "map");
   private final yK itemRenderer;
   private static double itemRenderDistanceSq = 4096.0;

   public wt(wC renderManagerIn, yK itemRendererIn) {
      super(renderManagerIn);
      this.itemRenderer = itemRendererIn;
   }

   public void doRender(IZ entity, double x, double y, double z, float entityYaw, float partialTicks) {
      yh.pushMatrix();
      BlockPos blockpos = entity.getHangingPosition();
      double d0 = (double)blockpos.getX() - entity.posX + x;
      double d1 = (double)blockpos.getY() - entity.posY + y;
      double d2 = (double)blockpos.getZ() - entity.posZ + z;
      yh.translate(d0 + 0.5, d1 + 0.5, d2 + 0.5);
      yh.rotate(180.0F - entity.rotationYaw, 0.0F, 1.0F, 0.0F);
      this.renderManager.renderEngine.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      tJ blockrendererdispatcher = this.mc.getBlockRendererDispatcher();
      sC modelmanager = blockrendererdispatcher.getBlockModelShapes().getModelManager();
      sc ibakedmodel;
      if (entity.getDisplayedItem().getItem() instanceof PT) {
         ibakedmodel = modelmanager.getModel(this.mapModel);
      } else {
         ibakedmodel = modelmanager.getModel(this.itemFrameModel);
      }

      yh.pushMatrix();
      yh.translate(-0.5F, -0.5F, -0.5F);
      if (this.renderOutlines) {
         yh.enableColorMaterial();
         yh.enableOutlineMode(this.getTeamColor(entity));
      }

      blockrendererdispatcher.getBlockModelRenderer().renderModelBrightnessColor(ibakedmodel, 1.0F, 1.0F, 1.0F, 1.0F);
      if (this.renderOutlines) {
         yh.disableOutlineMode();
         yh.disableColorMaterial();
      }

      yh.popMatrix();
      yh.translate(0.0F, 0.0F, 0.4375F);
      this.renderItem(entity);
      yh.popMatrix();
      this.renderName(entity, x + (double)((float)entity.facingDirection.getXOffset() * 0.3F), y - 0.25, z + (double)((float)entity.facingDirection.getZOffset() * 0.3F));
   }

   @Nullable
   protected ResourceLocation getEntityTexture(IZ entity) {
      return null;
   }

   private void renderItem(IZ itemFrame) {
      Qy itemstack = itemFrame.getDisplayedItem();
      if (!itemstack.isEmpty()) {
         if (!this.isRenderItem(itemFrame)) {
            return;
         }

         if (!XH.zoomMode) {
            nC var10000 = this.mc;
            Ig entity = nC.player;
            double d0 = itemFrame.getDistanceSq(entity.posX, entity.posY, entity.posZ);
            if (d0 > 4096.0) {
               return;
            }
         }

         yh.pushMatrix();
         yh.disableLighting();
         boolean flag = itemstack.getItem() instanceof PT;
         int i = flag ? itemFrame.getRotation() % 4 * 2 : itemFrame.getRotation();
         yh.rotate((float)i * 360.0F / 8.0F, 0.0F, 0.0F, 1.0F);
         if (!bnK.postForgeBusEvent(bnK.RenderItemInFrameEvent_Constructor, itemFrame, this)) {
            if (flag) {
               this.renderManager.renderEngine.bindTexture(MAP_BACKGROUND_TEXTURES);
               yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
               float f = 0.0078125F;
               yh.scale(0.0078125F, 0.0078125F, 0.0078125F);
               yh.translate(-64.0F, -64.0F, 0.0F);
               bhE mapdata = bnQ.getMapData(NK.FILLED_MAP, itemstack, itemFrame.world);
               yh.translate(0.0F, 0.0F, -1.0F);
               if (mapdata != null) {
                  this.mc.entityRenderer.getMapItemRenderer().renderMap(mapdata, true);
               }
            } else {
               yh.scale(0.5F, 0.5F, 0.5F);
               yh.pushAttrib();
               yz.enableStandardItemLighting();
               this.itemRenderer.renderItem(itemstack, sf.FIXED);
               yz.disableStandardItemLighting();
               yh.popAttrib();
            }
         }

         yh.enableLighting();
         yh.popMatrix();
      }

   }

   protected void renderName(IZ entity, double x, double y, double z) {
      if (nC.isGuiEnabled() && !entity.getDisplayedItem().isEmpty() && entity.getDisplayedItem().hasDisplayName() && this.renderManager.pointedEntity == entity) {
         double d0 = entity.getDistanceSq(this.renderManager.renderViewEntity);
         float f = entity.isSneaking() ? 32.0F : 64.0F;
         if (d0 < (double)(f * f)) {
            String s = entity.getDisplayedItem().getDisplayName();
            this.renderLivingLabel(entity, s, x, y, z, 64);
         }
      }

   }

   private boolean isRenderItem(IZ p_isRenderItem_1_) {
      if (bpq.isShadowPass) {
         return false;
      } else {
         if (!XH.zoomMode) {
            Ig entity = this.mc.getRenderViewEntity();
            double d0 = p_isRenderItem_1_.getDistanceSq(entity.posX, entity.posY, entity.posZ);
            if (d0 > itemRenderDistanceSq) {
               return false;
            }
         }

         return true;
      }
   }

   public static void updateItemRenderDistance() {
      nC minecraft = XH.getMinecraft();
      double d0 = (double)XH.limit(nC.gameSettings.fovSetting, 1.0F, 120.0F);
      double d1 = Math.max(6.0 * (double)minecraft.displayHeight / d0, 16.0);
      itemRenderDistanceSq = d1 * d1;
   }

   // $FF: synthetic method
   // $FF: bridge method
   @Nullable
   protected ResourceLocation getEntityTexture(Ig var1) {
      return this.getEntityTexture((IZ)var1);
   }

   // $FF: synthetic method
   // $FF: bridge method
   protected void renderName(Ig var1, double var2, double var4, double var6) {
      this.renderName((IZ)var1, var2, var4, var6);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void doRender(Ig var1, double var2, double var4, double var6, float var8, float var9) {
      this.doRender((IZ)var1, var2, var4, var6, var8, var9);
   }
}
