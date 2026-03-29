package neo;

import com.google.common.base.MoreObjects;
import java.util.Objects;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class yo {
   private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
   private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
   private final nC mc;
   private Qy itemStackMainHand;
   private Qy itemStackOffHand;
   private float equippedProgressMainHand;
   private float prevEquippedProgressMainHand;
   private float equippedProgressOffHand;
   private float prevEquippedProgressOffHand;
   private final wC renderManager;
   private final yK itemRenderer;

   public yo(nC mcIn) {
      this.itemStackMainHand = Qy.EMPTY;
      this.itemStackOffHand = Qy.EMPTY;
      this.mc = mcIn;
      this.renderManager = mcIn.getRenderManager();
      this.itemRenderer = mcIn.getRenderItem();
   }

   public void renderItem(Iw entityIn, Qy heldStack, sf transform) {
      this.renderItemSide(entityIn, heldStack, transform, false);
   }

   public void renderItemSide(Iw entitylivingbaseIn, Qy heldStack, sf transform, boolean leftHanded) {
      if (!heldStack.isEmpty()) {
         OL item = heldStack.getItem();
         co block = co.getBlockFromItem(item);
         yh.pushMatrix();
         boolean flag = this.itemRenderer.shouldRenderItemIn3D(heldStack) && block.getRenderLayer() == BlockRenderLayer.TRANSLUCENT;
         if (flag && (!XH.isShaders() || !bpq.renderItemKeepDepthMask)) {
            yh.depthMask(false);
         }

         this.itemRenderer.renderItem(heldStack, entitylivingbaseIn, transform, leftHanded);
         if (flag) {
            yh.depthMask(true);
         }

         yh.popMatrix();
      }

   }

   private void rotateArroundXAndY(float angle, float angleY) {
      yh.pushMatrix();
      yh.rotate(angle, 1.0F, 0.0F, 0.0F);
      yh.rotate(angleY, 0.0F, 1.0F, 0.0F);
      yz.enableStandardItemLighting();
      yh.popMatrix();
   }

   private void setLightmap() {
      nC var10000 = this.mc;
      jf abstractclientplayer = nC.player;
      int i = this.mc.world.getCombinedLight(new BlockPos(abstractclientplayer.posX, abstractclientplayer.posY + (double)((jf)abstractclientplayer).getEyeHeight(), abstractclientplayer.posZ), 0);
      if (XH.isDynamicLights()) {
         i = bjP.getCombinedLight(this.mc.getRenderViewEntity(), i);
      }

      float f = (float)(i & '\uffff');
      float f1 = (float)(i >> 16);
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, f, f1);
   }

   private void rotateArm(float p_187458_1_) {
      nC var10000 = this.mc;
      jh entityplayersp = nC.player;
      float f = entityplayersp.prevRenderArmPitch + (entityplayersp.renderArmPitch - entityplayersp.prevRenderArmPitch) * p_187458_1_;
      float f1 = entityplayersp.prevRenderArmYaw + (entityplayersp.renderArmYaw - entityplayersp.prevRenderArmYaw) * p_187458_1_;
      yh.rotate((entityplayersp.rotationPitch - f) * 0.1F, 1.0F, 0.0F, 0.0F);
      yh.rotate((entityplayersp.rotationYaw - f1) * 0.1F, 0.0F, 1.0F, 0.0F);
   }

   private float getMapAngleFromPitch(float pitch) {
      float f = 1.0F - pitch / 45.0F + 0.1F;
      f = MathHelper.clamp(f, 0.0F, 1.0F);
      f = -MathHelper.cos(f * 3.1415927F) * 0.5F + 0.5F;
      return f;
   }

   private void renderArms() {
      nC var10000 = this.mc;
      if (!nC.player.isInvisible()) {
         yh.disableCull();
         yh.pushMatrix();
         yh.rotate(90.0F, 0.0F, 1.0F, 0.0F);
         this.renderArm(EnumHandSide.RIGHT);
         this.renderArm(EnumHandSide.LEFT);
         yh.popMatrix();
         yh.enableCull();
      }

   }

   private void renderArm(EnumHandSide p_187455_1_) {
      zf var10000 = this.mc.getTextureManager();
      nC var10001 = this.mc;
      var10000.bindTexture(nC.player.getLocationSkin());
      var10001 = this.mc;
      vI<jf> render = this.renderManager.getEntityRenderObject(nC.player);
      wM renderplayer = (wM)render;
      yh.pushMatrix();
      float f = p_187455_1_ == EnumHandSide.RIGHT ? 1.0F : -1.0F;
      yh.rotate(92.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(45.0F, 1.0F, 0.0F, 0.0F);
      yh.rotate(f * -41.0F, 0.0F, 0.0F, 1.0F);
      yh.translate(f * 0.3F, -1.1F, 0.45F);
      if (p_187455_1_ == EnumHandSide.RIGHT) {
         var10001 = this.mc;
         renderplayer.renderRightArm(nC.player);
      } else {
         var10001 = this.mc;
         renderplayer.renderLeftArm(nC.player);
      }

      yh.popMatrix();
   }

   private void renderMapFirstPersonSide(float p_187465_1_, EnumHandSide hand, float p_187465_3_, Qy stack) {
      float f = hand == EnumHandSide.RIGHT ? 1.0F : -1.0F;
      yh.translate(f * 0.125F, -0.125F, 0.0F);
      nC var10000 = this.mc;
      if (!nC.player.isInvisible()) {
         yh.pushMatrix();
         yh.rotate(f * 10.0F, 0.0F, 0.0F, 1.0F);
         this.renderArmFirstPerson(p_187465_1_, p_187465_3_, hand);
         yh.popMatrix();
      }

      yh.pushMatrix();
      yh.translate(f * 0.51F, -0.08F + p_187465_1_ * -1.2F, -0.75F);
      float f1 = MathHelper.sqrt(p_187465_3_);
      float f2 = MathHelper.sin(f1 * 3.1415927F);
      float f3 = -0.5F * f2;
      float f4 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
      float f5 = -0.3F * MathHelper.sin(p_187465_3_ * 3.1415927F);
      yh.translate(f * f3, f4 - 0.3F * f2, f5);
      yh.rotate(f2 * -45.0F, 1.0F, 0.0F, 0.0F);
      yh.rotate(f * f2 * -30.0F, 0.0F, 1.0F, 0.0F);
      this.renderMapFirstPerson(stack);
      yh.popMatrix();
   }

   private void renderMapFirstPerson(float p_187463_1_, float p_187463_2_, float p_187463_3_) {
      float f = MathHelper.sqrt(p_187463_3_);
      float f1 = -0.2F * MathHelper.sin(p_187463_3_ * 3.1415927F);
      float f2 = -0.4F * MathHelper.sin(f * 3.1415927F);
      yh.translate(0.0F, -f1 / 2.0F, f2);
      float f3 = this.getMapAngleFromPitch(p_187463_1_);
      yh.translate(0.0F, 0.04F + p_187463_2_ * -1.2F + f3 * -0.5F, -0.72F);
      yh.rotate(f3 * -85.0F, 1.0F, 0.0F, 0.0F);
      this.renderArms();
      float f4 = MathHelper.sin(f * 3.1415927F);
      yh.rotate(f4 * 20.0F, 1.0F, 0.0F, 0.0F);
      yh.scale(2.0F, 2.0F, 2.0F);
      this.renderMapFirstPerson(this.itemStackMainHand);
   }

   private void renderMapFirstPerson(Qy stack) {
      yh.rotate(180.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      yh.scale(0.38F, 0.38F, 0.38F);
      yh.disableLighting();
      this.mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      yh.translate(-0.5F, -0.5F, 0.0F);
      yh.scale(0.0078125F, 0.0078125F, 0.0078125F);
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
      bufferbuilder.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
      bufferbuilder.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
      bufferbuilder.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
      tessellator.draw();
      bhE mapdata = bnQ.getMapData(NK.FILLED_MAP, stack, this.mc.world);
      if (mapdata != null) {
         this.mc.entityRenderer.getMapItemRenderer().renderMap(mapdata, false);
      }

      yh.enableLighting();
   }

   private void renderArmFirstPerson(float p_187456_1_, float p_187456_2_, EnumHandSide p_187456_3_) {
      boolean flag = p_187456_3_ != EnumHandSide.LEFT;
      float f = flag ? 1.0F : -1.0F;
      float f1 = MathHelper.sqrt(p_187456_2_);
      float f2 = -0.3F * MathHelper.sin(f1 * 3.1415927F);
      float f3 = 0.4F * MathHelper.sin(f1 * 6.2831855F);
      float f4 = -0.4F * MathHelper.sin(p_187456_2_ * 3.1415927F);
      yh.translate(f * (f2 + 0.64000005F), f3 + -0.6F + p_187456_1_ * -0.6F, f4 + -0.71999997F);
      yh.rotate(f * 45.0F, 0.0F, 1.0F, 0.0F);
      float f5 = MathHelper.sin(p_187456_2_ * p_187456_2_ * 3.1415927F);
      float f6 = MathHelper.sin(f1 * 3.1415927F);
      yh.rotate(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
      nC var10000 = this.mc;
      jf abstractclientplayer = nC.player;
      this.mc.getTextureManager().bindTexture(((jf)abstractclientplayer).getLocationSkin());
      yh.translate(f * -1.0F, 3.6F, 3.5F);
      yh.rotate(f * 120.0F, 0.0F, 0.0F, 1.0F);
      yh.rotate(200.0F, 1.0F, 0.0F, 0.0F);
      yh.rotate(f * -135.0F, 0.0F, 1.0F, 0.0F);
      yh.translate(f * 5.6F, 0.0F, 0.0F);
      wM renderplayer = (wM)this.renderManager.getEntityRenderObject(abstractclientplayer);
      yh.disableCull();
      if (flag) {
         renderplayer.renderRightArm(abstractclientplayer);
      } else {
         renderplayer.renderLeftArm(abstractclientplayer);
      }

      yh.enableCull();
   }

   private void transformEatFirstPerson(float p_187454_1_, EnumHandSide hand, Qy stack) {
      nC var10000 = this.mc;
      float f = (float)nC.player.getItemInUseCount() - p_187454_1_ + 1.0F;
      float f1 = f / (float)stack.getMaxItemUseDuration();
      float f3;
      if (f1 < 0.8F) {
         f3 = MathHelper.abs(MathHelper.cos(f / 4.0F * 3.1415927F) * 0.1F);
         yh.translate(0.0F, f3, 0.0F);
      }

      f3 = 1.0F - (float)Math.pow((double)f1, 27.0);
      int i = hand == EnumHandSide.RIGHT ? 1 : -1;
      yh.translate(f3 * 0.6F * (float)i, f3 * -0.5F, f3 * 0.0F);
      yh.rotate((float)i * f3 * 90.0F, 0.0F, 1.0F, 0.0F);
      yh.rotate(f3 * 10.0F, 1.0F, 0.0F, 0.0F);
      yh.rotate((float)i * f3 * 30.0F, 0.0F, 0.0F, 1.0F);
   }

   private void transformFirstPerson(EnumHandSide hand, float p_187453_2_) {
      int i = hand == EnumHandSide.RIGHT ? 1 : -1;
      float f = MathHelper.sin(p_187453_2_ * p_187453_2_ * 3.1415927F);
      yh.rotate((float)i * (45.0F + f * -20.0F), 0.0F, 1.0F, 0.0F);
      float f1 = MathHelper.sin(MathHelper.sqrt(p_187453_2_) * 3.1415927F);
      yh.rotate((float)i * f1 * -20.0F, 0.0F, 0.0F, 1.0F);
      yh.rotate(f1 * -80.0F, 1.0F, 0.0F, 0.0F);
      yh.rotate((float)i * -45.0F, 0.0F, 1.0F, 0.0F);
   }

   private void transformSideFirstPerson(EnumHandSide hand, float p_187459_2_) {
      int i = hand == EnumHandSide.RIGHT ? 1 : -1;
      yh.translate((float)i * 0.56F, -0.52F + p_187459_2_ * -0.6F, -0.72F);
   }

   public void renderItemInFirstPerson(float partialTicks) {
      nC var10000 = this.mc;
      jf abstractclientplayer = nC.player;
      float f = ((jf)abstractclientplayer).getSwingProgress(partialTicks);
      EnumHand enumhand = (EnumHand)MoreObjects.firstNonNull(abstractclientplayer.swingingHand, EnumHand.MAIN_HAND);
      float f1 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
      float f2 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
      boolean flag = true;
      boolean flag1 = true;
      if (((jf)abstractclientplayer).isHandActive()) {
         Qy itemstack = ((jf)abstractclientplayer).getActiveItemStack();
         if (itemstack.getItem() instanceof Pd) {
            EnumHand enumhand1 = ((jf)abstractclientplayer).getActiveHand();
            flag = enumhand1 == EnumHand.MAIN_HAND;
            flag1 = !flag;
         }
      }

      this.rotateArroundXAndY(f1, f2);
      this.setLightmap();
      this.rotateArm(partialTicks);
      yh.enableRescaleNormal();
      float f4;
      float f6;
      if (flag) {
         f4 = enumhand == EnumHand.MAIN_HAND ? f : 0.0F;
         f6 = 1.0F - (this.prevEquippedProgressMainHand + (this.equippedProgressMainHand - this.prevEquippedProgressMainHand) * partialTicks);
         if (!bnK.ForgeHooksClient_renderSpecificFirstPersonHand.exists() || !bnK.callBoolean(bnK.ForgeHooksClient_renderSpecificFirstPersonHand, EnumHand.MAIN_HAND, partialTicks, f1, f4, f6, this.itemStackMainHand)) {
            this.renderItemInFirstPerson(abstractclientplayer, partialTicks, f1, EnumHand.MAIN_HAND, f4, this.itemStackMainHand, f6);
         }
      }

      if (flag1) {
         f4 = enumhand == EnumHand.OFF_HAND ? f : 0.0F;
         f6 = 1.0F - (this.prevEquippedProgressOffHand + (this.equippedProgressOffHand - this.prevEquippedProgressOffHand) * partialTicks);
         if (!bnK.ForgeHooksClient_renderSpecificFirstPersonHand.exists() || !bnK.callBoolean(bnK.ForgeHooksClient_renderSpecificFirstPersonHand, EnumHand.OFF_HAND, partialTicks, f1, f4, f6, this.itemStackOffHand)) {
            this.renderItemInFirstPerson(abstractclientplayer, partialTicks, f1, EnumHand.OFF_HAND, f4, this.itemStackOffHand, f6);
         }
      }

      yh.disableRescaleNormal();
      yz.disableStandardItemLighting();
   }

   public void renderItemInFirstPerson(jf player, float p_187457_2_, float p_187457_3_, EnumHand hand, float p_187457_5_, Qy stack, float p_187457_7_) {
      if (!XH.isShaders() || !bpq.isSkipRenderHand(hand)) {
         boolean flag = hand == EnumHand.MAIN_HAND;
         EnumHandSide enumhandside = flag ? player.getPrimaryHand() : player.getPrimaryHand().opposite();
         yh.pushMatrix();
         if (stack.isEmpty()) {
            if (flag && !player.isInvisible()) {
               this.renderArmFirstPerson(p_187457_7_, p_187457_5_, enumhandside);
            }
         } else if (stack.getItem() instanceof PT) {
            if (flag && this.itemStackOffHand.isEmpty()) {
               this.renderMapFirstPerson(p_187457_3_, p_187457_7_, p_187457_5_);
            } else {
               this.renderMapFirstPersonSide(p_187457_7_, enumhandside, p_187457_5_, stack);
            }
         } else {
            boolean flag1 = enumhandside == EnumHandSide.RIGHT;
            float f5;
            float f6;
            if (player.isHandActive() && player.getItemInUseCount() > 0 && player.getActiveHand() == hand) {
               int j = flag1 ? 1 : -1;
               switch (stack.getItemUseAction()) {
                  case NONE:
                     this.transformSideFirstPerson(enumhandside, p_187457_7_);
                     break;
                  case EAT:
                  case DRINK:
                     this.transformEatFirstPerson(p_187457_2_, enumhandside, stack);
                     this.transformSideFirstPerson(enumhandside, p_187457_7_);
                     break;
                  case BLOCK:
                     this.transformSideFirstPerson(enumhandside, p_187457_7_);
                     break;
                  case BOW:
                     this.transformSideFirstPerson(enumhandside, p_187457_7_);
                     yh.translate((float)j * -0.2785682F, 0.18344387F, 0.15731531F);
                     yh.rotate(-13.935F, 1.0F, 0.0F, 0.0F);
                     yh.rotate((float)j * 35.3F, 0.0F, 1.0F, 0.0F);
                     yh.rotate((float)j * -9.785F, 0.0F, 0.0F, 1.0F);
                     float var10000 = (float)stack.getMaxItemUseDuration();
                     nC var10001 = this.mc;
                     f5 = var10000 - ((float)nC.player.getItemInUseCount() - p_187457_2_ + 1.0F);
                     f6 = f5 / 20.0F;
                     f6 = (f6 * f6 + f6 * 2.0F) / 3.0F;
                     if (f6 > 1.0F) {
                        f6 = 1.0F;
                     }

                     if (f6 > 0.1F) {
                        float f7 = MathHelper.sin((f5 - 0.1F) * 1.3F);
                        float f3 = f6 - 0.1F;
                        float f4 = f7 * f3;
                        yh.translate(f4 * 0.0F, f4 * 0.004F, f4 * 0.0F);
                     }

                     yh.translate(f6 * 0.0F, f6 * 0.0F, f6 * 0.04F);
                     yh.scale(1.0F, 1.0F, 1.0F + f6 * 0.2F);
                     yh.rotate((float)j * 45.0F, 0.0F, -1.0F, 0.0F);
               }
            } else {
               float f = -0.4F * MathHelper.sin(MathHelper.sqrt(p_187457_5_) * 3.1415927F);
               f5 = 0.2F * MathHelper.sin(MathHelper.sqrt(p_187457_5_) * 6.2831855F);
               f6 = -0.2F * MathHelper.sin(p_187457_5_ * 3.1415927F);
               int i = flag1 ? 1 : -1;
               yh.translate((float)i * f, f5, f6);
               this.transformSideFirstPerson(enumhandside, p_187457_7_);
               this.transformFirstPerson(enumhandside, p_187457_5_);
            }

            this.renderItemSide(player, stack, flag1 ? sf.FIRST_PERSON_RIGHT_HAND : sf.FIRST_PERSON_LEFT_HAND, !flag1);
         }

         yh.popMatrix();
      }

   }

   public void renderOverlays(float partialTicks) {
      yh.disableAlpha();
      nC var10000 = this.mc;
      bnR var15;
      Object[] var10001;
      nC var10004;
      if (nC.player.isEntityInsideOpaqueBlock()) {
         nC var10003 = this.mc;
         in iblockstate = this.mc.world.getBlockState(new BlockPos(nC.player));
         nC var10002 = this.mc;
         BlockPos blockpos = new BlockPos(nC.player);
         var10000 = this.mc;
         ME entityplayer = nC.player;

         for(int i = 0; i < 8; ++i) {
            double d0 = entityplayer.posX + (double)(((float)((i >> 0) % 2) - 0.5F) * entityplayer.width * 0.8F);
            double d1 = entityplayer.posY + (double)(((float)((i >> 1) % 2) - 0.5F) * 0.1F);
            double d2 = entityplayer.posZ + (double)(((float)((i >> 2) % 2) - 0.5F) * entityplayer.width * 0.8F);
            BlockPos blockpos1 = new BlockPos(d0, d1 + (double)((ME)entityplayer).getEyeHeight(), d2);
            in iblockstate1 = this.mc.world.getBlockState(blockpos1);
            if (iblockstate1.causesSuffocation()) {
               iblockstate = iblockstate1;
               blockpos = blockpos1;
            }
         }

         if (iblockstate.getRenderType() != EnumBlockRenderType.INVISIBLE) {
            Object object = bnK.getFieldValue(bnK.RenderBlockOverlayEvent_OverlayType_BLOCK);
            var15 = bnK.ForgeEventFactory_renderBlockOverlay;
            var10001 = new Object[5];
            var10004 = this.mc;
            var10001[0] = nC.player;
            var10001[1] = partialTicks;
            var10001[2] = object;
            var10001[3] = iblockstate;
            var10001[4] = blockpos;
            if (!bnK.callBoolean(var15, var10001)) {
               this.renderSuffocationOverlay(this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(iblockstate));
            }
         }
      }

      var10000 = this.mc;
      if (!nC.player.isSpectator()) {
         var10000 = this.mc;
         if (nC.player.isInsideOfMaterial(hM.WATER)) {
            var15 = bnK.ForgeEventFactory_renderWaterOverlay;
            var10001 = new Object[2];
            var10004 = this.mc;
            var10001[0] = nC.player;
            var10001[1] = partialTicks;
            if (!bnK.callBoolean(var15, var10001)) {
               this.renderWaterOverlayTexture(partialTicks);
            }
         }

         var10000 = this.mc;
         if (nC.player.isBurning()) {
            var15 = bnK.ForgeEventFactory_renderFireOverlay;
            var10001 = new Object[2];
            var10004 = this.mc;
            var10001[0] = nC.player;
            var10001[1] = partialTicks;
            if (!bnK.callBoolean(var15, var10001)) {
               this.renderFireInFirstPerson();
            }
         }
      }

      yh.enableAlpha();
   }

   private void renderSuffocationOverlay(zd sprite) {
      this.mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      float f = 0.1F;
      yh.color(0.1F, 0.1F, 0.1F, 0.5F);
      yh.pushMatrix();
      float f1 = -1.0F;
      float f2 = 1.0F;
      float f3 = -1.0F;
      float f4 = 1.0F;
      float f5 = -0.5F;
      float f6 = sprite.getMinU();
      float f7 = sprite.getMaxU();
      float f8 = sprite.getMinV();
      float f9 = sprite.getMaxV();
      bufferbuilder.begin(7, zK.POSITION_TEX);
      bufferbuilder.pos(-1.0, -1.0, -0.5).tex((double)f7, (double)f9).endVertex();
      bufferbuilder.pos(1.0, -1.0, -0.5).tex((double)f6, (double)f9).endVertex();
      bufferbuilder.pos(1.0, 1.0, -0.5).tex((double)f6, (double)f8).endVertex();
      bufferbuilder.pos(-1.0, 1.0, -0.5).tex((double)f7, (double)f8).endVertex();
      tessellator.draw();
      yh.popMatrix();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   private void renderWaterOverlayTexture(float partialTicks) {
      if (!XH.isShaders() || bpq.isUnderwaterOverlay()) {
         this.mc.getTextureManager().bindTexture(RES_UNDERWATER_OVERLAY);
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         nC var10000 = this.mc;
         float f = nC.player.getBrightness();
         yh.color(f, f, f, 0.5F);
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yh.pushMatrix();
         float f1 = 4.0F;
         float f2 = -1.0F;
         float f3 = 1.0F;
         float f4 = -1.0F;
         float f5 = 1.0F;
         float f6 = -0.5F;
         var10000 = this.mc;
         float f7 = -nC.player.rotationYaw / 64.0F;
         var10000 = this.mc;
         float f8 = nC.player.rotationPitch / 64.0F;
         bufferbuilder.begin(7, zK.POSITION_TEX);
         bufferbuilder.pos(-1.0, -1.0, -0.5).tex((double)(4.0F + f7), (double)(4.0F + f8)).endVertex();
         bufferbuilder.pos(1.0, -1.0, -0.5).tex((double)(0.0F + f7), (double)(4.0F + f8)).endVertex();
         bufferbuilder.pos(1.0, 1.0, -0.5).tex((double)(0.0F + f7), (double)(0.0F + f8)).endVertex();
         bufferbuilder.pos(-1.0, 1.0, -0.5).tex((double)(4.0F + f7), (double)(0.0F + f8)).endVertex();
         tessellator.draw();
         yh.popMatrix();
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.disableBlend();
      }

   }

   private void renderFireInFirstPerson() {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      yh.color(1.0F, 1.0F, 1.0F, 0.9F);
      yh.depthFunc(519);
      yh.depthMask(false);
      yh.enableBlend();
      yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
      float f = 1.0F;

      for(int i = 0; i < 2; ++i) {
         yh.pushMatrix();
         zd textureatlassprite = this.mc.getTextureMapBlocks().getAtlasSprite("minecraft:blocks/fire_layer_1");
         this.mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         float f1 = textureatlassprite.getMinU();
         float f2 = textureatlassprite.getMaxU();
         float f3 = textureatlassprite.getMinV();
         float f4 = textureatlassprite.getMaxV();
         float f5 = -0.5F;
         float f6 = 0.5F;
         float f7 = -0.5F;
         float f8 = 0.5F;
         float f9 = -0.5F;
         yh.translate((float)(-(i * 2 - 1)) * 0.24F, -0.3F, 0.0F);
         yh.rotate((float)(i * 2 - 1) * 10.0F, 0.0F, 1.0F, 0.0F);
         bufferbuilder.begin(7, zK.POSITION_TEX);
         bufferbuilder.setSprite(textureatlassprite);
         bufferbuilder.pos(-0.5, -0.5, -0.5).tex((double)f2, (double)f4).endVertex();
         bufferbuilder.pos(0.5, -0.5, -0.5).tex((double)f1, (double)f4).endVertex();
         bufferbuilder.pos(0.5, 0.5, -0.5).tex((double)f1, (double)f3).endVertex();
         bufferbuilder.pos(-0.5, 0.5, -0.5).tex((double)f2, (double)f3).endVertex();
         tessellator.draw();
         yh.popMatrix();
      }

      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      yh.disableBlend();
      yh.depthMask(true);
      yh.depthFunc(515);
   }

   public void updateEquippedItem() {
      this.prevEquippedProgressMainHand = this.equippedProgressMainHand;
      this.prevEquippedProgressOffHand = this.equippedProgressOffHand;
      nC var10000 = this.mc;
      jh entityplayersp = nC.player;
      Qy itemstack = entityplayersp.getHeldItemMainhand();
      Qy itemstack1 = entityplayersp.getHeldItemOffhand();
      if (entityplayersp.isRowingBoat()) {
         this.equippedProgressMainHand = MathHelper.clamp(this.equippedProgressMainHand - 0.4F, 0.0F, 1.0F);
         this.equippedProgressOffHand = MathHelper.clamp(this.equippedProgressOffHand - 0.4F, 0.0F, 1.0F);
      } else {
         float f = entityplayersp.getCooledAttackStrength(1.0F);
         if (bnK.ForgeHooksClient_shouldCauseReequipAnimation.exists()) {
            boolean flag = bnK.callBoolean(bnK.ForgeHooksClient_shouldCauseReequipAnimation, this.itemStackMainHand, itemstack, entityplayersp.inventory.currentItem);
            boolean flag1 = bnK.callBoolean(bnK.ForgeHooksClient_shouldCauseReequipAnimation, this.itemStackOffHand, itemstack1, -1);
            if (!flag && !Objects.equals(this.itemStackMainHand, itemstack)) {
               this.itemStackMainHand = itemstack;
            }

            if (!flag && !Objects.equals(this.itemStackOffHand, itemstack1)) {
               this.itemStackOffHand = itemstack1;
            }

            this.equippedProgressMainHand += MathHelper.clamp((!flag ? f * f * f : 0.0F) - this.equippedProgressMainHand, -0.4F, 0.4F);
            this.equippedProgressOffHand += MathHelper.clamp((float)(!flag1 ? 1 : 0) - this.equippedProgressOffHand, -0.4F, 0.4F);
         } else {
            this.equippedProgressMainHand += MathHelper.clamp((Objects.equals(this.itemStackMainHand, itemstack) ? f * f * f : 0.0F) - this.equippedProgressMainHand, -0.4F, 0.4F);
            this.equippedProgressOffHand += MathHelper.clamp((float)(Objects.equals(this.itemStackOffHand, itemstack1) ? 1 : 0) - this.equippedProgressOffHand, -0.4F, 0.4F);
         }
      }

      if (this.equippedProgressMainHand < 0.1F) {
         this.itemStackMainHand = itemstack;
         if (XH.isShaders()) {
            bpq.setItemToRenderMain(this.itemStackMainHand);
         }
      }

      if (this.equippedProgressOffHand < 0.1F) {
         this.itemStackOffHand = itemstack1;
         if (XH.isShaders()) {
            bpq.setItemToRenderOff(this.itemStackOffHand);
         }
      }

   }

   public void resetEquippedProgress(EnumHand hand) {
      if (hand == EnumHand.MAIN_HAND) {
         this.equippedProgressMainHand = 0.0F;
      } else {
         this.equippedProgressOffHand = 0.0F;
      }

   }
}
