package neo;

import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3i;

public class yK implements AB {
   private static final ResourceLocation RES_ITEM_GLINT = new ResourceLocation("textures/misc/enchanted_item_glint.png");
   private boolean notRenderingEffectsInGUI = true;
   public float zLevel;
   private final ym itemModelMesher;
   private final zf textureManager;
   private final uJ itemColors;
   private ResourceLocation modelLocation = null;
   private boolean renderItemGui = false;
   public sC modelManager = null;
   private boolean renderModelHasEmissive = false;
   private boolean renderModelEmissive = false;
   private boolean forgeAllowEmissiveItems;

   public yK(zf p_i46552_1_, sC p_i46552_2_, uJ p_i46552_3_) {
      this.forgeAllowEmissiveItems = bnK.getFieldValueBoolean(bnK.ForgeModContainer_allowEmissiveItems, false);
      this.textureManager = p_i46552_1_;
      this.modelManager = p_i46552_2_;
      if (bnK.ItemModelMesherForge_Constructor.exists()) {
         this.itemModelMesher = (ym)bnK.newInstance(bnK.ItemModelMesherForge_Constructor, p_i46552_2_);
      } else {
         this.itemModelMesher = new ym(p_i46552_2_);
      }

      this.registerItems();
      this.itemColors = p_i46552_3_;
   }

   public ym getItemModelMesher() {
      return this.itemModelMesher;
   }

   protected void registerItem(OL itm, int subType, String identifier) {
      this.itemModelMesher.register(itm, subType, new sD(identifier, "inventory"));
   }

   protected void registerBlock(co blk, int subType, String identifier) {
      this.registerItem(OL.getItemFromBlock(blk), subType, identifier);
   }

   private void registerBlock(co blk, String identifier) {
      this.registerBlock(blk, 0, identifier);
   }

   private void registerItem(OL itm, String identifier) {
      this.registerItem(itm, 0, identifier);
   }

   private void renderModel(sc model, Qy stack) {
      this.renderModel(model, -1, stack);
   }

   public void renderModel(sc model, int color) {
      this.renderModel(model, color, Qy.EMPTY);
   }

   private void renderModel(sc model, int color, Qy stack) {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      boolean flag = nC.getMinecraft().getTextureMapBlocks().isTextureBound();
      boolean flag1 = XH.isMultiTexture() && flag;
      if (flag1) {
         bufferbuilder.setBlockLayer(BlockRenderLayer.SOLID);
      }

      bufferbuilder.begin(7, zK.ITEM);
      EnumFacing[] var8 = EnumFacing.VALUES;
      int var9 = var8.length;

      for(int var10 = 0; var10 < var9; ++var10) {
         EnumFacing enumfacing = var8[var10];
         this.renderQuads(bufferbuilder, model.getQuads((in)null, enumfacing, 0L), color, stack);
      }

      this.renderQuads(bufferbuilder, model.getQuads((in)null, (EnumFacing)null, 0L), color, stack);
      tessellator.draw();
      if (flag1) {
         bufferbuilder.setBlockLayer((BlockRenderLayer)null);
         yh.bindCurrentTexture();
      }

   }

   public void renderItem(Qy stack, sc model) {
      if (!stack.isEmpty()) {
         yh.pushMatrix();
         yh.translate(-0.5F, -0.5F, -0.5F);
         if (model.isBuiltInRenderer()) {
            yh.color(1.0F, 1.0F, 1.0F, 1.0F);
            yh.enableRescaleNormal();
            if (bnK.ForgeItem_getTileEntityItemStackRenderer.exists()) {
               zw tileentityitemstackrenderer = (zw)bnK.call(stack.getItem(), bnK.ForgeItem_getTileEntityItemStackRenderer);
               tileentityitemstackrenderer.renderByItem(stack);
            } else {
               zw.instance.renderByItem(stack);
            }
         } else {
            if (XH.isCustomItems()) {
               model = bjG.getCustomItemModel(stack, model, this.modelLocation, false);
               this.modelLocation = null;
            }

            this.renderModelHasEmissive = false;
            this.renderModel(model, stack);
            if (this.renderModelHasEmissive) {
               float f = ys.lastBrightnessX;
               float f1 = ys.lastBrightnessY;
               ys.setLightmapTextureCoords(ys.lightmapTexUnit, 240.0F, f1);
               this.renderModelEmissive = true;
               this.renderModel(model, stack);
               this.renderModelEmissive = false;
               ys.setLightmapTextureCoords(ys.lightmapTexUnit, f, f1);
            }

            if (stack.hasEffect() && (!XH.isCustomItems() || !bjG.renderCustomEffect(this, stack, model))) {
               this.renderEffect(model);
            }
         }

         yh.popMatrix();
      }

   }

   private void renderEffect(sc model) {
      if ((!XH.isCustomItems() || bjG.isUseGlint()) && (!XH.isShaders() || !bpq.isShadowPass)) {
         yh.depthMask(false);
         yh.depthFunc(514);
         yh.disableLighting();
         yh.blendFunc(ya.SRC_COLOR, xR.ONE);
         this.textureManager.bindTexture(RES_ITEM_GLINT);
         if (XH.isShaders() && !this.renderItemGui) {
            bpr.renderEnchantedGlintBegin();
         }

         yh.matrixMode(5890);
         yh.pushMatrix();
         yh.scale(8.0F, 8.0F, 8.0F);
         float f = (float)(nC.getSystemTime() % 3000L) / 3000.0F / 8.0F;
         yh.translate(f, 0.0F, 0.0F);
         yh.rotate(-50.0F, 0.0F, 0.0F, 1.0F);
         this.renderModel(model, -8372020);
         yh.popMatrix();
         yh.pushMatrix();
         yh.scale(8.0F, 8.0F, 8.0F);
         float f1 = (float)(nC.getSystemTime() % 4873L) / 4873.0F / 8.0F;
         yh.translate(-f1, 0.0F, 0.0F);
         yh.rotate(10.0F, 0.0F, 0.0F, 1.0F);
         this.renderModel(model, -8372020);
         yh.popMatrix();
         yh.matrixMode(5888);
         yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
         yh.enableLighting();
         yh.depthFunc(515);
         yh.depthMask(true);
         this.textureManager.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         if (XH.isShaders() && !this.renderItemGui) {
            bpr.renderEnchantedGlintEnd();
         }
      }

   }

   private void putQuadNormal(tN renderer, rK quad) {
      Vec3i vec3i = quad.getFace().getDirectionVec();
      renderer.putNormal((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
   }

   private void renderQuad(tN renderer, rK quad, int color) {
      if (this.renderModelEmissive) {
         if (quad.getQuadEmissive() == null) {
            return;
         }

         quad = quad.getQuadEmissive();
      } else if (quad.getQuadEmissive() != null) {
         this.renderModelHasEmissive = true;
      }

      if (renderer.isMultiTexture()) {
         renderer.addVertexData(quad.getVertexDataSingle());
      } else {
         renderer.addVertexData(quad.getVertexData());
      }

      renderer.putSprite(quad.getSprite());
      if (bnK.ForgeHooksClient_putQuadColor.exists()) {
         bnK.call(bnK.ForgeHooksClient_putQuadColor, renderer, quad, color);
      } else {
         renderer.putColor4(color);
      }

      this.putQuadNormal(renderer, quad);
   }

   private void renderQuads(tN renderer, List<rK> quads, int color, Qy stack) {
      boolean flag = color == -1 && !stack.isEmpty();
      int i = 0;

      for(int j = quads.size(); i < j; ++i) {
         rK bakedquad = (rK)quads.get(i);
         int k = color;
         if (flag && bakedquad.hasTintIndex()) {
            k = this.itemColors.colorMultiplier(stack, bakedquad.getTintIndex());
            if (XH.isCustomColors()) {
               k = bjy.getColorFromItemStack(stack, bakedquad.getTintIndex(), k);
            }

            if (xz.anaglyphEnable) {
               k = zk.anaglyphColor(k);
            }

            k |= -16777216;
         }

         this.renderQuad(renderer, bakedquad, k);
      }

   }

   public boolean shouldRenderItemIn3D(Qy stack) {
      sc ibakedmodel = this.itemModelMesher.getItemModel(stack);
      return ibakedmodel == null ? false : ibakedmodel.isGui3d();
   }

   public void renderItem(Qy stack, sf cameraTransformType) {
      if (!stack.isEmpty()) {
         sc ibakedmodel = this.getItemModelWithOverrides(stack, (bij)null, (Iw)null);
         this.renderItemModel(stack, ibakedmodel, cameraTransformType, false);
      }

   }

   public sc getItemModelWithOverrides(Qy stack, @Nullable bij worldIn, @Nullable Iw entitylivingbaseIn) {
      sc ibakedmodel = this.itemModelMesher.getItemModel(stack);
      OL item = stack.getItem();
      if (XH.isCustomItems()) {
         if (item != null && item.hasCustomProperties()) {
            this.modelLocation = ibakedmodel.getOverrides().applyOverride(stack, worldIn, entitylivingbaseIn);
         }

         sc ibakedmodel1 = bjG.getCustomItemModel(stack, ibakedmodel, this.modelLocation, true);
         if (ibakedmodel1 != ibakedmodel) {
            return ibakedmodel1;
         }
      }

      if (bnK.ModelLoader_getInventoryVariant.exists()) {
         return ibakedmodel.getOverrides().handleItemState(ibakedmodel, stack, worldIn, entitylivingbaseIn);
      } else if (item != null && item.hasCustomProperties()) {
         ResourceLocation resourcelocation = ibakedmodel.getOverrides().applyOverride(stack, worldIn, entitylivingbaseIn);
         return resourcelocation == null ? ibakedmodel : this.itemModelMesher.getModelManager().getModel(new sD(resourcelocation, "inventory"));
      } else {
         return ibakedmodel;
      }
   }

   public void renderItem(Qy stack, Iw entitylivingbaseIn, sf transform, boolean leftHanded) {
      if (!stack.isEmpty() && entitylivingbaseIn != null) {
         sc ibakedmodel = this.getItemModelWithOverrides(stack, entitylivingbaseIn.world, entitylivingbaseIn);
         this.renderItemModel(stack, ibakedmodel, transform, leftHanded);
      }

   }

   protected void renderItemModel(Qy stack, sc bakedmodel, sf transform, boolean leftHanded) {
      if (!stack.isEmpty()) {
         this.textureManager.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         this.textureManager.getTexture(zj.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
         yh.color(1.0F, 1.0F, 1.0F, 1.0F);
         yh.enableRescaleNormal();
         yh.alphaFunc(516, 0.1F);
         yh.enableBlend();
         yh.tryBlendFuncSeparate(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA, ya.ONE, xR.ZERO);
         yh.pushMatrix();
         if (bnK.ForgeHooksClient_handleCameraTransforms.exists()) {
            bakedmodel = (sc)bnK.call(bnK.ForgeHooksClient_handleCameraTransforms, bakedmodel, transform, leftHanded);
         } else {
            sg itemcameratransforms = bakedmodel.getItemCameraTransforms();
            sg.applyTransformSide(itemcameratransforms.getTransform(transform), leftHanded);
            if (this.isThereOneNegativeScale(itemcameratransforms.getTransform(transform))) {
               yh.cullFace(xO.FRONT);
            }
         }

         bjG.setRenderOffHand(leftHanded);
         this.renderItem(stack, bakedmodel);
         bjG.setRenderOffHand(false);
         yh.cullFace(xO.BACK);
         yh.popMatrix();
         yh.disableRescaleNormal();
         yh.disableBlend();
         this.textureManager.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         this.textureManager.getTexture(zj.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
      }

   }

   private boolean isThereOneNegativeScale(sp itemTranformVec) {
      return itemTranformVec.scale.x < 0.0F ^ itemTranformVec.scale.y < 0.0F ^ itemTranformVec.scale.z < 0.0F;
   }

   public void renderItemIntoGUI(Qy stack, int x, int y) {
      this.renderItemModelIntoGUI(stack, x, y, this.getItemModelWithOverrides(stack, (bij)null, (Iw)null));
   }

   protected void renderItemModelIntoGUI(Qy stack, int x, int y, sc bakedmodel) {
      this.renderItemGui = true;
      yh.pushMatrix();
      this.textureManager.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      this.textureManager.getTexture(zj.LOCATION_BLOCKS_TEXTURE).setBlurMipmap(false, false);
      yh.enableRescaleNormal();
      yh.enableAlpha();
      yh.alphaFunc(516, 0.1F);
      yh.enableBlend();
      yh.blendFunc(ya.SRC_ALPHA, xR.ONE_MINUS_SRC_ALPHA);
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.setupGuiTransform(x, y, bakedmodel.isGui3d());
      if (bnK.ForgeHooksClient_handleCameraTransforms.exists()) {
         bakedmodel = (sc)bnK.call(bnK.ForgeHooksClient_handleCameraTransforms, bakedmodel, sf.GUI, false);
      } else {
         bakedmodel.getItemCameraTransforms().applyTransform(sf.GUI);
      }

      this.renderItem(stack, bakedmodel);
      yh.disableAlpha();
      yh.disableRescaleNormal();
      yh.disableLighting();
      yh.popMatrix();
      this.textureManager.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      this.textureManager.getTexture(zj.LOCATION_BLOCKS_TEXTURE).restoreLastBlurMipmap();
      this.renderItemGui = false;
   }

   private void setupGuiTransform(int xPosition, int yPosition, boolean isGui3d) {
      yh.translate((float)xPosition, (float)yPosition, 100.0F + this.zLevel);
      yh.translate(8.0F, 8.0F, 0.0F);
      yh.scale(1.0F, -1.0F, 1.0F);
      yh.scale(16.0F, 16.0F, 16.0F);
      if (isGui3d) {
         yh.enableLighting();
      } else {
         yh.disableLighting();
      }

   }

   public void renderItemAndEffectIntoGUI(Qy stack, int xPosition, int yPosition) {
      nC.getMinecraft();
      this.renderItemAndEffectIntoGUI(nC.player, stack, xPosition, yPosition);
   }

   public void renderItemAndEffectIntoGUI(@Nullable Iw p_184391_1_, final Qy p_184391_2_, int p_184391_3_, int p_184391_4_) {
      if (!p_184391_2_.isEmpty()) {
         this.zLevel += 50.0F;

         try {
            this.renderItemModelIntoGUI(p_184391_2_, p_184391_3_, p_184391_4_, this.getItemModelWithOverrides(p_184391_2_, (bij)null, p_184391_1_));
         } catch (Throwable var8) {
            Throwable throwable = var8;
            Er crashreport = Er.makeCrashReport(throwable, "Rendering item");
            Ey crashreportcategory = crashreport.makeCategory("Item being rendered");
            crashreportcategory.addDetail("Item Type", new Ez<String>() {
               public String call() throws Exception {
                  return String.valueOf(p_184391_2_.getItem());
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            if (bnK.IForgeRegistryEntry_Impl_getRegistryName.exists()) {
               crashreportcategory.addDetail("Registry Name", bnQ.getDetailItemRegistryName(p_184391_2_.getItem()));
            }

            crashreportcategory.addDetail("Item Aux", new Ez<String>() {
               public String call() throws Exception {
                  return String.valueOf(p_184391_2_.getMetadata());
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            crashreportcategory.addDetail("Item NBT", new Ez<String>() {
               public String call() throws Exception {
                  return String.valueOf(p_184391_2_.getTagCompound());
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            crashreportcategory.addDetail("Item Foil", new Ez<String>() {
               public String call() throws Exception {
                  return String.valueOf(p_184391_2_.hasEffect());
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            throw new ReportedException(crashreport);
         }

         this.zLevel -= 50.0F;
      }

   }

   public void renderItemOverlays(jH fr, Qy stack, int xPosition, int yPosition) {
      this.renderItemOverlayIntoGUI(fr, stack, xPosition, yPosition, (String)null);
   }

   public void renderItemOverlayIntoGUI(jH fr, Qy stack, int xPosition, int yPosition, @Nullable String text) {
      if (!stack.isEmpty()) {
         if (stack.getCount() != 1 || text != null) {
            String s = text == null ? String.valueOf(stack.getCount()) : text;
            yh.disableLighting();
            yh.disableDepth();
            yh.disableBlend();
            fr.drawStringWithShadow(s, (float)(xPosition + 19 - 2 - fr.getStringWidth(s)), (float)(yPosition + 6 + 3), 16777215);
            yh.enableLighting();
            yh.enableDepth();
            yh.enableBlend();
         }

         if (bnQ.isItemDamaged(stack)) {
            yh.disableLighting();
            yh.disableDepth();
            yh.disableTexture2D();
            yh.disableAlpha();
            yh.disableBlend();
            yN tessellator = yN.getInstance();
            tN bufferbuilder = tessellator.getBuffer();
            float f = (float)stack.getItemDamage();
            float f1 = (float)stack.getMaxDamage();
            float f2 = Math.max(0.0F, (f1 - f) / f1);
            int i = Math.round(13.0F - f * 13.0F / f1);
            int j = MathHelper.hsvToRGB(f2 / 3.0F, 1.0F, 1.0F);
            double d1;
            int l;
            if (bnK.ForgeItem_getDurabilityForDisplay.exists() && bnK.ForgeItem_getRGBDurabilityForDisplay.exists()) {
               d1 = bnK.callDouble(stack.getItem(), bnK.ForgeItem_getDurabilityForDisplay, stack);
               l = bnK.callInt(stack.getItem(), bnK.ForgeItem_getRGBDurabilityForDisplay, stack);
               i = Math.round(13.0F - (float)d1 * 13.0F);
               j = l;
            }

            if (XH.isCustomColors()) {
               j = bjy.getDurabilityColor(f2, j);
            }

            if (bnK.ForgeItem_getDurabilityForDisplay.exists() && bnK.ForgeItem_getRGBDurabilityForDisplay.exists()) {
               d1 = bnK.callDouble(stack.getItem(), bnK.ForgeItem_getDurabilityForDisplay, stack);
               l = bnK.callInt(stack.getItem(), bnK.ForgeItem_getRGBDurabilityForDisplay, stack);
               i = Math.round(13.0F - (float)d1 * 13.0F);
               j = l;
            }

            if (XH.isCustomColors()) {
               j = bjy.getDurabilityColor(f2, j);
            }

            this.draw(bufferbuilder, xPosition + 2, yPosition + 13, 13, 2, 0, 0, 0, 255);
            this.draw(bufferbuilder, xPosition + 2, yPosition + 13, i, 1, j >> 16 & 255, j >> 8 & 255, j & 255, 255);
            yh.enableBlend();
            yh.enableAlpha();
            yh.enableTexture2D();
            yh.enableLighting();
            yh.enableDepth();
         }

         nC.getMinecraft();
         jh entityplayersp = nC.player;
         float f3 = entityplayersp == null ? 0.0F : entityplayersp.getCooldownTracker().getCooldown(stack.getItem(), nC.getMinecraft().getRenderPartialTicks());
         if (f3 > 0.0F) {
            yh.disableLighting();
            yh.disableDepth();
            yh.disableTexture2D();
            yN tessellator1 = yN.getInstance();
            tN bufferbuilder1 = tessellator1.getBuffer();
            this.draw(bufferbuilder1, xPosition, yPosition + MathHelper.floor(16.0F * (1.0F - f3)), 16, MathHelper.ceil(16.0F * f3), 255, 255, 255, 127);
            yh.enableTexture2D();
            yh.enableLighting();
            yh.enableDepth();
         }
      }

   }

   private void draw(tN renderer, int x, int y, int width, int height, int red, int green, int blue, int alpha) {
      renderer.begin(7, zK.POSITION_COLOR);
      renderer.pos((double)(x + 0), (double)(y + 0), 0.0).color(red, green, blue, alpha).endVertex();
      renderer.pos((double)(x + 0), (double)(y + height), 0.0).color(red, green, blue, alpha).endVertex();
      renderer.pos((double)(x + width), (double)(y + height), 0.0).color(red, green, blue, alpha).endVertex();
      renderer.pos((double)(x + width), (double)(y + 0), 0.0).color(red, green, blue, alpha).endVertex();
      yN.getInstance().draw();
   }

   private void registerItems() {
      this.registerBlock(Nk.ANVIL, "anvil_intact");
      this.registerBlock(Nk.ANVIL, 1, "anvil_slightly_damaged");
      this.registerBlock(Nk.ANVIL, 2, "anvil_very_damaged");
      this.registerBlock(Nk.CARPET, Om.BLACK.getMetadata(), "black_carpet");
      this.registerBlock(Nk.CARPET, Om.BLUE.getMetadata(), "blue_carpet");
      this.registerBlock(Nk.CARPET, Om.BROWN.getMetadata(), "brown_carpet");
      this.registerBlock(Nk.CARPET, Om.CYAN.getMetadata(), "cyan_carpet");
      this.registerBlock(Nk.CARPET, Om.GRAY.getMetadata(), "gray_carpet");
      this.registerBlock(Nk.CARPET, Om.GREEN.getMetadata(), "green_carpet");
      this.registerBlock(Nk.CARPET, Om.LIGHT_BLUE.getMetadata(), "light_blue_carpet");
      this.registerBlock(Nk.CARPET, Om.LIME.getMetadata(), "lime_carpet");
      this.registerBlock(Nk.CARPET, Om.MAGENTA.getMetadata(), "magenta_carpet");
      this.registerBlock(Nk.CARPET, Om.ORANGE.getMetadata(), "orange_carpet");
      this.registerBlock(Nk.CARPET, Om.PINK.getMetadata(), "pink_carpet");
      this.registerBlock(Nk.CARPET, Om.PURPLE.getMetadata(), "purple_carpet");
      this.registerBlock(Nk.CARPET, Om.RED.getMetadata(), "red_carpet");
      this.registerBlock(Nk.CARPET, Om.SILVER.getMetadata(), "silver_carpet");
      this.registerBlock(Nk.CARPET, Om.WHITE.getMetadata(), "white_carpet");
      this.registerBlock(Nk.CARPET, Om.YELLOW.getMetadata(), "yellow_carpet");
      this.registerBlock(Nk.COBBLESTONE_WALL, hy.MOSSY.getMetadata(), "mossy_cobblestone_wall");
      this.registerBlock(Nk.COBBLESTONE_WALL, hy.NORMAL.getMetadata(), "cobblestone_wall");
      this.registerBlock(Nk.DIRT, di.COARSE_DIRT.getMetadata(), "coarse_dirt");
      this.registerBlock(Nk.DIRT, di.DIRT.getMetadata(), "dirt");
      this.registerBlock(Nk.DIRT, di.PODZOL.getMetadata(), "podzol");
      this.registerBlock(Nk.DOUBLE_PLANT, dq.FERN.getMeta(), "double_fern");
      this.registerBlock(Nk.DOUBLE_PLANT, dq.GRASS.getMeta(), "double_grass");
      this.registerBlock(Nk.DOUBLE_PLANT, dq.PAEONIA.getMeta(), "paeonia");
      this.registerBlock(Nk.DOUBLE_PLANT, dq.ROSE.getMeta(), "double_rose");
      this.registerBlock(Nk.DOUBLE_PLANT, dq.SUNFLOWER.getMeta(), "sunflower");
      this.registerBlock(Nk.DOUBLE_PLANT, dq.SYRINGA.getMeta(), "syringa");
      this.registerBlock(Nk.LEAVES, fk.BIRCH.getMetadata(), "birch_leaves");
      this.registerBlock(Nk.LEAVES, fk.JUNGLE.getMetadata(), "jungle_leaves");
      this.registerBlock(Nk.LEAVES, fk.OAK.getMetadata(), "oak_leaves");
      this.registerBlock(Nk.LEAVES, fk.SPRUCE.getMetadata(), "spruce_leaves");
      this.registerBlock(Nk.LEAVES2, fk.ACACIA.getMetadata() - 4, "acacia_leaves");
      this.registerBlock(Nk.LEAVES2, fk.DARK_OAK.getMetadata() - 4, "dark_oak_leaves");
      this.registerBlock(Nk.LOG, fk.BIRCH.getMetadata(), "birch_log");
      this.registerBlock(Nk.LOG, fk.JUNGLE.getMetadata(), "jungle_log");
      this.registerBlock(Nk.LOG, fk.OAK.getMetadata(), "oak_log");
      this.registerBlock(Nk.LOG, fk.SPRUCE.getMetadata(), "spruce_log");
      this.registerBlock(Nk.LOG2, fk.ACACIA.getMetadata() - 4, "acacia_log");
      this.registerBlock(Nk.LOG2, fk.DARK_OAK.getMetadata() - 4, "dark_oak_log");
      this.registerBlock(Nk.MONSTER_EGG, gA.CHISELED_STONEBRICK.getMetadata(), "chiseled_brick_monster_egg");
      this.registerBlock(Nk.MONSTER_EGG, gA.COBBLESTONE.getMetadata(), "cobblestone_monster_egg");
      this.registerBlock(Nk.MONSTER_EGG, gA.CRACKED_STONEBRICK.getMetadata(), "cracked_brick_monster_egg");
      this.registerBlock(Nk.MONSTER_EGG, gA.MOSSY_STONEBRICK.getMetadata(), "mossy_brick_monster_egg");
      this.registerBlock(Nk.MONSTER_EGG, gA.STONE.getMetadata(), "stone_monster_egg");
      this.registerBlock(Nk.MONSTER_EGG, gA.STONEBRICK.getMetadata(), "stone_brick_monster_egg");
      this.registerBlock(Nk.PLANKS, fk.ACACIA.getMetadata(), "acacia_planks");
      this.registerBlock(Nk.PLANKS, fk.BIRCH.getMetadata(), "birch_planks");
      this.registerBlock(Nk.PLANKS, fk.DARK_OAK.getMetadata(), "dark_oak_planks");
      this.registerBlock(Nk.PLANKS, fk.JUNGLE.getMetadata(), "jungle_planks");
      this.registerBlock(Nk.PLANKS, fk.OAK.getMetadata(), "oak_planks");
      this.registerBlock(Nk.PLANKS, fk.SPRUCE.getMetadata(), "spruce_planks");
      this.registerBlock(Nk.PRISMARINE, fu.BRICKS.getMetadata(), "prismarine_bricks");
      this.registerBlock(Nk.PRISMARINE, fu.DARK.getMetadata(), "dark_prismarine");
      this.registerBlock(Nk.PRISMARINE, fu.ROUGH.getMetadata(), "prismarine");
      this.registerBlock(Nk.QUARTZ_BLOCK, fD.CHISELED.getMetadata(), "chiseled_quartz_block");
      this.registerBlock(Nk.QUARTZ_BLOCK, fD.DEFAULT.getMetadata(), "quartz_block");
      this.registerBlock(Nk.QUARTZ_BLOCK, fD.LINES_Y.getMetadata(), "quartz_column");
      this.registerBlock(Nk.RED_FLOWER, dR.ALLIUM.getMeta(), "allium");
      this.registerBlock(Nk.RED_FLOWER, dR.BLUE_ORCHID.getMeta(), "blue_orchid");
      this.registerBlock(Nk.RED_FLOWER, dR.HOUSTONIA.getMeta(), "houstonia");
      this.registerBlock(Nk.RED_FLOWER, dR.ORANGE_TULIP.getMeta(), "orange_tulip");
      this.registerBlock(Nk.RED_FLOWER, dR.OXEYE_DAISY.getMeta(), "oxeye_daisy");
      this.registerBlock(Nk.RED_FLOWER, dR.PINK_TULIP.getMeta(), "pink_tulip");
      this.registerBlock(Nk.RED_FLOWER, dR.POPPY.getMeta(), "poppy");
      this.registerBlock(Nk.RED_FLOWER, dR.RED_TULIP.getMeta(), "red_tulip");
      this.registerBlock(Nk.RED_FLOWER, dR.WHITE_TULIP.getMeta(), "white_tulip");
      this.registerBlock(Nk.SAND, gj.RED_SAND.getMetadata(), "red_sand");
      this.registerBlock(Nk.SAND, gj.SAND.getMetadata(), "sand");
      this.registerBlock(Nk.SANDSTONE, gl.CHISELED.getMetadata(), "chiseled_sandstone");
      this.registerBlock(Nk.SANDSTONE, gl.DEFAULT.getMetadata(), "sandstone");
      this.registerBlock(Nk.SANDSTONE, gl.SMOOTH.getMetadata(), "smooth_sandstone");
      this.registerBlock(Nk.RED_SANDSTONE, fS.CHISELED.getMetadata(), "chiseled_red_sandstone");
      this.registerBlock(Nk.RED_SANDSTONE, fS.DEFAULT.getMetadata(), "red_sandstone");
      this.registerBlock(Nk.RED_SANDSTONE, fS.SMOOTH.getMetadata(), "smooth_red_sandstone");
      this.registerBlock(Nk.SAPLING, fk.ACACIA.getMetadata(), "acacia_sapling");
      this.registerBlock(Nk.SAPLING, fk.BIRCH.getMetadata(), "birch_sapling");
      this.registerBlock(Nk.SAPLING, fk.DARK_OAK.getMetadata(), "dark_oak_sapling");
      this.registerBlock(Nk.SAPLING, fk.JUNGLE.getMetadata(), "jungle_sapling");
      this.registerBlock(Nk.SAPLING, fk.OAK.getMetadata(), "oak_sapling");
      this.registerBlock(Nk.SAPLING, fk.SPRUCE.getMetadata(), "spruce_sapling");
      this.registerBlock(Nk.SPONGE, 0, "sponge");
      this.registerBlock(Nk.SPONGE, 1, "sponge_wet");
      this.registerBlock(Nk.STAINED_GLASS, Om.BLACK.getMetadata(), "black_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.BLUE.getMetadata(), "blue_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.BROWN.getMetadata(), "brown_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.CYAN.getMetadata(), "cyan_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.GRAY.getMetadata(), "gray_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.GREEN.getMetadata(), "green_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.LIGHT_BLUE.getMetadata(), "light_blue_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.LIME.getMetadata(), "lime_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.MAGENTA.getMetadata(), "magenta_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.ORANGE.getMetadata(), "orange_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.PINK.getMetadata(), "pink_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.PURPLE.getMetadata(), "purple_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.RED.getMetadata(), "red_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.SILVER.getMetadata(), "silver_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.WHITE.getMetadata(), "white_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS, Om.YELLOW.getMetadata(), "yellow_stained_glass");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.BLACK.getMetadata(), "black_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.BLUE.getMetadata(), "blue_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.BROWN.getMetadata(), "brown_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.CYAN.getMetadata(), "cyan_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.GRAY.getMetadata(), "gray_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.GREEN.getMetadata(), "green_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.LIGHT_BLUE.getMetadata(), "light_blue_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.LIME.getMetadata(), "lime_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.MAGENTA.getMetadata(), "magenta_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.ORANGE.getMetadata(), "orange_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.PINK.getMetadata(), "pink_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.PURPLE.getMetadata(), "purple_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.RED.getMetadata(), "red_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.SILVER.getMetadata(), "silver_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.WHITE.getMetadata(), "white_stained_glass_pane");
      this.registerBlock(Nk.STAINED_GLASS_PANE, Om.YELLOW.getMetadata(), "yellow_stained_glass_pane");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.BLACK.getMetadata(), "black_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.BLUE.getMetadata(), "blue_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.BROWN.getMetadata(), "brown_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.CYAN.getMetadata(), "cyan_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.GRAY.getMetadata(), "gray_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.GREEN.getMetadata(), "green_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.LIGHT_BLUE.getMetadata(), "light_blue_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.LIME.getMetadata(), "lime_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.MAGENTA.getMetadata(), "magenta_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.ORANGE.getMetadata(), "orange_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.PINK.getMetadata(), "pink_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.PURPLE.getMetadata(), "purple_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.RED.getMetadata(), "red_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.SILVER.getMetadata(), "silver_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.WHITE.getMetadata(), "white_stained_hardened_clay");
      this.registerBlock(Nk.STAINED_HARDENED_CLAY, Om.YELLOW.getMetadata(), "yellow_stained_hardened_clay");
      this.registerBlock(Nk.STONE, gY.ANDESITE.getMetadata(), "andesite");
      this.registerBlock(Nk.STONE, gY.ANDESITE_SMOOTH.getMetadata(), "andesite_smooth");
      this.registerBlock(Nk.STONE, gY.DIORITE.getMetadata(), "diorite");
      this.registerBlock(Nk.STONE, gY.DIORITE_SMOOTH.getMetadata(), "diorite_smooth");
      this.registerBlock(Nk.STONE, gY.GRANITE.getMetadata(), "granite");
      this.registerBlock(Nk.STONE, gY.GRANITE_SMOOTH.getMetadata(), "granite_smooth");
      this.registerBlock(Nk.STONE, gY.STONE.getMetadata(), "stone");
      this.registerBlock(Nk.STONEBRICK, ha.CRACKED.getMetadata(), "cracked_stonebrick");
      this.registerBlock(Nk.STONEBRICK, ha.DEFAULT.getMetadata(), "stonebrick");
      this.registerBlock(Nk.STONEBRICK, ha.CHISELED.getMetadata(), "chiseled_stonebrick");
      this.registerBlock(Nk.STONEBRICK, ha.MOSSY.getMetadata(), "mossy_stonebrick");
      this.registerBlock(Nk.STONE_SLAB, hc.BRICK.getMetadata(), "brick_slab");
      this.registerBlock(Nk.STONE_SLAB, hc.COBBLESTONE.getMetadata(), "cobblestone_slab");
      this.registerBlock(Nk.STONE_SLAB, hc.WOOD.getMetadata(), "old_wood_slab");
      this.registerBlock(Nk.STONE_SLAB, hc.NETHERBRICK.getMetadata(), "nether_brick_slab");
      this.registerBlock(Nk.STONE_SLAB, hc.QUARTZ.getMetadata(), "quartz_slab");
      this.registerBlock(Nk.STONE_SLAB, hc.SAND.getMetadata(), "sandstone_slab");
      this.registerBlock(Nk.STONE_SLAB, hc.SMOOTHBRICK.getMetadata(), "stone_brick_slab");
      this.registerBlock(Nk.STONE_SLAB, hc.STONE.getMetadata(), "stone_slab");
      this.registerBlock(Nk.STONE_SLAB2, he.RED_SANDSTONE.getMetadata(), "red_sandstone_slab");
      this.registerBlock(Nk.TALLGRASS, hj.DEAD_BUSH.getMeta(), "dead_bush");
      this.registerBlock(Nk.TALLGRASS, hj.FERN.getMeta(), "fern");
      this.registerBlock(Nk.TALLGRASS, hj.GRASS.getMeta(), "tall_grass");
      this.registerBlock(Nk.WOODEN_SLAB, fk.ACACIA.getMetadata(), "acacia_slab");
      this.registerBlock(Nk.WOODEN_SLAB, fk.BIRCH.getMetadata(), "birch_slab");
      this.registerBlock(Nk.WOODEN_SLAB, fk.DARK_OAK.getMetadata(), "dark_oak_slab");
      this.registerBlock(Nk.WOODEN_SLAB, fk.JUNGLE.getMetadata(), "jungle_slab");
      this.registerBlock(Nk.WOODEN_SLAB, fk.OAK.getMetadata(), "oak_slab");
      this.registerBlock(Nk.WOODEN_SLAB, fk.SPRUCE.getMetadata(), "spruce_slab");
      this.registerBlock(Nk.WOOL, Om.BLACK.getMetadata(), "black_wool");
      this.registerBlock(Nk.WOOL, Om.BLUE.getMetadata(), "blue_wool");
      this.registerBlock(Nk.WOOL, Om.BROWN.getMetadata(), "brown_wool");
      this.registerBlock(Nk.WOOL, Om.CYAN.getMetadata(), "cyan_wool");
      this.registerBlock(Nk.WOOL, Om.GRAY.getMetadata(), "gray_wool");
      this.registerBlock(Nk.WOOL, Om.GREEN.getMetadata(), "green_wool");
      this.registerBlock(Nk.WOOL, Om.LIGHT_BLUE.getMetadata(), "light_blue_wool");
      this.registerBlock(Nk.WOOL, Om.LIME.getMetadata(), "lime_wool");
      this.registerBlock(Nk.WOOL, Om.MAGENTA.getMetadata(), "magenta_wool");
      this.registerBlock(Nk.WOOL, Om.ORANGE.getMetadata(), "orange_wool");
      this.registerBlock(Nk.WOOL, Om.PINK.getMetadata(), "pink_wool");
      this.registerBlock(Nk.WOOL, Om.PURPLE.getMetadata(), "purple_wool");
      this.registerBlock(Nk.WOOL, Om.RED.getMetadata(), "red_wool");
      this.registerBlock(Nk.WOOL, Om.SILVER.getMetadata(), "silver_wool");
      this.registerBlock(Nk.WOOL, Om.WHITE.getMetadata(), "white_wool");
      this.registerBlock(Nk.WOOL, Om.YELLOW.getMetadata(), "yellow_wool");
      this.registerBlock(Nk.FARMLAND, "farmland");
      this.registerBlock(Nk.ACACIA_STAIRS, "acacia_stairs");
      this.registerBlock(Nk.ACTIVATOR_RAIL, "activator_rail");
      this.registerBlock(Nk.BEACON, "beacon");
      this.registerBlock(Nk.BEDROCK, "bedrock");
      this.registerBlock(Nk.BIRCH_STAIRS, "birch_stairs");
      this.registerBlock(Nk.BOOKSHELF, "bookshelf");
      this.registerBlock(Nk.BRICK_BLOCK, "brick_block");
      this.registerBlock(Nk.BRICK_BLOCK, "brick_block");
      this.registerBlock(Nk.BRICK_STAIRS, "brick_stairs");
      this.registerBlock(Nk.BROWN_MUSHROOM, "brown_mushroom");
      this.registerBlock(Nk.CACTUS, "cactus");
      this.registerBlock(Nk.CLAY, "clay");
      this.registerBlock(Nk.COAL_BLOCK, "coal_block");
      this.registerBlock(Nk.COAL_ORE, "coal_ore");
      this.registerBlock(Nk.COBBLESTONE, "cobblestone");
      this.registerBlock(Nk.CRAFTING_TABLE, "crafting_table");
      this.registerBlock(Nk.DARK_OAK_STAIRS, "dark_oak_stairs");
      this.registerBlock(Nk.DAYLIGHT_DETECTOR, "daylight_detector");
      this.registerBlock(Nk.DEADBUSH, "dead_bush");
      this.registerBlock(Nk.DETECTOR_RAIL, "detector_rail");
      this.registerBlock(Nk.DIAMOND_BLOCK, "diamond_block");
      this.registerBlock(Nk.DIAMOND_ORE, "diamond_ore");
      this.registerBlock(Nk.DISPENSER, "dispenser");
      this.registerBlock(Nk.DROPPER, "dropper");
      this.registerBlock(Nk.EMERALD_BLOCK, "emerald_block");
      this.registerBlock(Nk.EMERALD_ORE, "emerald_ore");
      this.registerBlock(Nk.ENCHANTING_TABLE, "enchanting_table");
      this.registerBlock(Nk.END_PORTAL_FRAME, "end_portal_frame");
      this.registerBlock(Nk.END_STONE, "end_stone");
      this.registerBlock(Nk.OAK_FENCE, "oak_fence");
      this.registerBlock(Nk.SPRUCE_FENCE, "spruce_fence");
      this.registerBlock(Nk.BIRCH_FENCE, "birch_fence");
      this.registerBlock(Nk.JUNGLE_FENCE, "jungle_fence");
      this.registerBlock(Nk.DARK_OAK_FENCE, "dark_oak_fence");
      this.registerBlock(Nk.ACACIA_FENCE, "acacia_fence");
      this.registerBlock(Nk.OAK_FENCE_GATE, "oak_fence_gate");
      this.registerBlock(Nk.SPRUCE_FENCE_GATE, "spruce_fence_gate");
      this.registerBlock(Nk.BIRCH_FENCE_GATE, "birch_fence_gate");
      this.registerBlock(Nk.JUNGLE_FENCE_GATE, "jungle_fence_gate");
      this.registerBlock(Nk.DARK_OAK_FENCE_GATE, "dark_oak_fence_gate");
      this.registerBlock(Nk.ACACIA_FENCE_GATE, "acacia_fence_gate");
      this.registerBlock(Nk.FURNACE, "furnace");
      this.registerBlock(Nk.GLASS, "glass");
      this.registerBlock(Nk.GLASS_PANE, "glass_pane");
      this.registerBlock(Nk.GLOWSTONE, "glowstone");
      this.registerBlock(Nk.GOLDEN_RAIL, "golden_rail");
      this.registerBlock(Nk.GOLD_BLOCK, "gold_block");
      this.registerBlock(Nk.GOLD_ORE, "gold_ore");
      this.registerBlock(Nk.GRASS, "grass");
      this.registerBlock(Nk.GRASS_PATH, "grass_path");
      this.registerBlock(Nk.GRAVEL, "gravel");
      this.registerBlock(Nk.HARDENED_CLAY, "hardened_clay");
      this.registerBlock(Nk.HAY_BLOCK, "hay_block");
      this.registerBlock(Nk.HEAVY_WEIGHTED_PRESSURE_PLATE, "heavy_weighted_pressure_plate");
      this.registerBlock(Nk.HOPPER, "hopper");
      this.registerBlock(Nk.ICE, "ice");
      this.registerBlock(Nk.IRON_BARS, "iron_bars");
      this.registerBlock(Nk.IRON_BLOCK, "iron_block");
      this.registerBlock(Nk.IRON_ORE, "iron_ore");
      this.registerBlock(Nk.IRON_TRAPDOOR, "iron_trapdoor");
      this.registerBlock(Nk.JUKEBOX, "jukebox");
      this.registerBlock(Nk.JUNGLE_STAIRS, "jungle_stairs");
      this.registerBlock(Nk.LADDER, "ladder");
      this.registerBlock(Nk.LAPIS_BLOCK, "lapis_block");
      this.registerBlock(Nk.LAPIS_ORE, "lapis_ore");
      this.registerBlock(Nk.LEVER, "lever");
      this.registerBlock(Nk.LIGHT_WEIGHTED_PRESSURE_PLATE, "light_weighted_pressure_plate");
      this.registerBlock(Nk.LIT_PUMPKIN, "lit_pumpkin");
      this.registerBlock(Nk.MELON_BLOCK, "melon_block");
      this.registerBlock(Nk.MOSSY_COBBLESTONE, "mossy_cobblestone");
      this.registerBlock(Nk.MYCELIUM, "mycelium");
      this.registerBlock(Nk.NETHERRACK, "netherrack");
      this.registerBlock(Nk.NETHER_BRICK, "nether_brick");
      this.registerBlock(Nk.NETHER_BRICK_FENCE, "nether_brick_fence");
      this.registerBlock(Nk.NETHER_BRICK_STAIRS, "nether_brick_stairs");
      this.registerBlock(Nk.NOTEBLOCK, "noteblock");
      this.registerBlock(Nk.OAK_STAIRS, "oak_stairs");
      this.registerBlock(Nk.OBSIDIAN, "obsidian");
      this.registerBlock(Nk.PACKED_ICE, "packed_ice");
      this.registerBlock(Nk.PISTON, "piston");
      this.registerBlock(Nk.PUMPKIN, "pumpkin");
      this.registerBlock(Nk.QUARTZ_ORE, "quartz_ore");
      this.registerBlock(Nk.QUARTZ_STAIRS, "quartz_stairs");
      this.registerBlock(Nk.RAIL, "rail");
      this.registerBlock(Nk.REDSTONE_BLOCK, "redstone_block");
      this.registerBlock(Nk.REDSTONE_LAMP, "redstone_lamp");
      this.registerBlock(Nk.REDSTONE_ORE, "redstone_ore");
      this.registerBlock(Nk.REDSTONE_TORCH, "redstone_torch");
      this.registerBlock(Nk.RED_MUSHROOM, "red_mushroom");
      this.registerBlock(Nk.SANDSTONE_STAIRS, "sandstone_stairs");
      this.registerBlock(Nk.RED_SANDSTONE_STAIRS, "red_sandstone_stairs");
      this.registerBlock(Nk.SEA_LANTERN, "sea_lantern");
      this.registerBlock(Nk.SLIME_BLOCK, "slime");
      this.registerBlock(Nk.SNOW, "snow");
      this.registerBlock(Nk.SNOW_LAYER, "snow_layer");
      this.registerBlock(Nk.SOUL_SAND, "soul_sand");
      this.registerBlock(Nk.SPRUCE_STAIRS, "spruce_stairs");
      this.registerBlock(Nk.STICKY_PISTON, "sticky_piston");
      this.registerBlock(Nk.STONE_BRICK_STAIRS, "stone_brick_stairs");
      this.registerBlock(Nk.STONE_BUTTON, "stone_button");
      this.registerBlock(Nk.STONE_PRESSURE_PLATE, "stone_pressure_plate");
      this.registerBlock(Nk.STONE_STAIRS, "stone_stairs");
      this.registerBlock(Nk.TNT, "tnt");
      this.registerBlock(Nk.TORCH, "torch");
      this.registerBlock(Nk.TRAPDOOR, "trapdoor");
      this.registerBlock(Nk.TRIPWIRE_HOOK, "tripwire_hook");
      this.registerBlock(Nk.VINE, "vine");
      this.registerBlock(Nk.WATERLILY, "waterlily");
      this.registerBlock(Nk.WEB, "web");
      this.registerBlock(Nk.WOODEN_BUTTON, "wooden_button");
      this.registerBlock(Nk.WOODEN_PRESSURE_PLATE, "wooden_pressure_plate");
      this.registerBlock(Nk.YELLOW_FLOWER, dR.DANDELION.getMeta(), "dandelion");
      this.registerBlock(Nk.END_ROD, "end_rod");
      this.registerBlock(Nk.CHORUS_PLANT, "chorus_plant");
      this.registerBlock(Nk.CHORUS_FLOWER, "chorus_flower");
      this.registerBlock(Nk.PURPUR_BLOCK, "purpur_block");
      this.registerBlock(Nk.PURPUR_PILLAR, "purpur_pillar");
      this.registerBlock(Nk.PURPUR_STAIRS, "purpur_stairs");
      this.registerBlock(Nk.PURPUR_SLAB, "purpur_slab");
      this.registerBlock(Nk.PURPUR_DOUBLE_SLAB, "purpur_double_slab");
      this.registerBlock(Nk.END_BRICKS, "end_bricks");
      this.registerBlock(Nk.MAGMA, "magma");
      this.registerBlock(Nk.NETHER_WART_BLOCK, "nether_wart_block");
      this.registerBlock(Nk.RED_NETHER_BRICK, "red_nether_brick");
      this.registerBlock(Nk.BONE_BLOCK, "bone_block");
      this.registerBlock(Nk.STRUCTURE_VOID, "structure_void");
      this.registerBlock(Nk.OBSERVER, "observer");
      this.registerBlock(Nk.WHITE_SHULKER_BOX, "white_shulker_box");
      this.registerBlock(Nk.ORANGE_SHULKER_BOX, "orange_shulker_box");
      this.registerBlock(Nk.MAGENTA_SHULKER_BOX, "magenta_shulker_box");
      this.registerBlock(Nk.LIGHT_BLUE_SHULKER_BOX, "light_blue_shulker_box");
      this.registerBlock(Nk.YELLOW_SHULKER_BOX, "yellow_shulker_box");
      this.registerBlock(Nk.LIME_SHULKER_BOX, "lime_shulker_box");
      this.registerBlock(Nk.PINK_SHULKER_BOX, "pink_shulker_box");
      this.registerBlock(Nk.GRAY_SHULKER_BOX, "gray_shulker_box");
      this.registerBlock(Nk.SILVER_SHULKER_BOX, "silver_shulker_box");
      this.registerBlock(Nk.CYAN_SHULKER_BOX, "cyan_shulker_box");
      this.registerBlock(Nk.PURPLE_SHULKER_BOX, "purple_shulker_box");
      this.registerBlock(Nk.BLUE_SHULKER_BOX, "blue_shulker_box");
      this.registerBlock(Nk.BROWN_SHULKER_BOX, "brown_shulker_box");
      this.registerBlock(Nk.GREEN_SHULKER_BOX, "green_shulker_box");
      this.registerBlock(Nk.RED_SHULKER_BOX, "red_shulker_box");
      this.registerBlock(Nk.BLACK_SHULKER_BOX, "black_shulker_box");
      this.registerBlock(Nk.WHITE_GLAZED_TERRACOTTA, "white_glazed_terracotta");
      this.registerBlock(Nk.ORANGE_GLAZED_TERRACOTTA, "orange_glazed_terracotta");
      this.registerBlock(Nk.MAGENTA_GLAZED_TERRACOTTA, "magenta_glazed_terracotta");
      this.registerBlock(Nk.LIGHT_BLUE_GLAZED_TERRACOTTA, "light_blue_glazed_terracotta");
      this.registerBlock(Nk.YELLOW_GLAZED_TERRACOTTA, "yellow_glazed_terracotta");
      this.registerBlock(Nk.LIME_GLAZED_TERRACOTTA, "lime_glazed_terracotta");
      this.registerBlock(Nk.PINK_GLAZED_TERRACOTTA, "pink_glazed_terracotta");
      this.registerBlock(Nk.GRAY_GLAZED_TERRACOTTA, "gray_glazed_terracotta");
      this.registerBlock(Nk.SILVER_GLAZED_TERRACOTTA, "silver_glazed_terracotta");
      this.registerBlock(Nk.CYAN_GLAZED_TERRACOTTA, "cyan_glazed_terracotta");
      this.registerBlock(Nk.PURPLE_GLAZED_TERRACOTTA, "purple_glazed_terracotta");
      this.registerBlock(Nk.BLUE_GLAZED_TERRACOTTA, "blue_glazed_terracotta");
      this.registerBlock(Nk.BROWN_GLAZED_TERRACOTTA, "brown_glazed_terracotta");
      this.registerBlock(Nk.GREEN_GLAZED_TERRACOTTA, "green_glazed_terracotta");
      this.registerBlock(Nk.RED_GLAZED_TERRACOTTA, "red_glazed_terracotta");
      this.registerBlock(Nk.BLACK_GLAZED_TERRACOTTA, "black_glazed_terracotta");
      Om[] var1 = Om.values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         Om enumdyecolor = var1[var3];
         this.registerBlock(Nk.CONCRETE, enumdyecolor.getMetadata(), enumdyecolor.getDyeColorName() + "_concrete");
         this.registerBlock(Nk.CONCRETE_POWDER, enumdyecolor.getMetadata(), enumdyecolor.getDyeColorName() + "_concrete_powder");
      }

      this.registerBlock(Nk.CHEST, "chest");
      this.registerBlock(Nk.TRAPPED_CHEST, "trapped_chest");
      this.registerBlock(Nk.ENDER_CHEST, "ender_chest");
      this.registerItem(NK.IRON_SHOVEL, "iron_shovel");
      this.registerItem(NK.IRON_PICKAXE, "iron_pickaxe");
      this.registerItem(NK.IRON_AXE, "iron_axe");
      this.registerItem(NK.FLINT_AND_STEEL, "flint_and_steel");
      this.registerItem(NK.APPLE, "apple");
      this.registerItem(NK.BOW, "bow");
      this.registerItem(NK.ARROW, "arrow");
      this.registerItem(NK.SPECTRAL_ARROW, "spectral_arrow");
      this.registerItem(NK.TIPPED_ARROW, "tipped_arrow");
      this.registerItem(NK.COAL, 0, "coal");
      this.registerItem(NK.COAL, 1, "charcoal");
      this.registerItem(NK.DIAMOND, "diamond");
      this.registerItem(NK.IRON_INGOT, "iron_ingot");
      this.registerItem(NK.GOLD_INGOT, "gold_ingot");
      this.registerItem(NK.IRON_SWORD, "iron_sword");
      this.registerItem(NK.WOODEN_SWORD, "wooden_sword");
      this.registerItem(NK.WOODEN_SHOVEL, "wooden_shovel");
      this.registerItem(NK.WOODEN_PICKAXE, "wooden_pickaxe");
      this.registerItem(NK.WOODEN_AXE, "wooden_axe");
      this.registerItem(NK.STONE_SWORD, "stone_sword");
      this.registerItem(NK.STONE_SHOVEL, "stone_shovel");
      this.registerItem(NK.STONE_PICKAXE, "stone_pickaxe");
      this.registerItem(NK.STONE_AXE, "stone_axe");
      this.registerItem(NK.DIAMOND_SWORD, "diamond_sword");
      this.registerItem(NK.DIAMOND_SHOVEL, "diamond_shovel");
      this.registerItem(NK.DIAMOND_PICKAXE, "diamond_pickaxe");
      this.registerItem(NK.DIAMOND_AXE, "diamond_axe");
      this.registerItem(NK.STICK, "stick");
      this.registerItem(NK.BOWL, "bowl");
      this.registerItem(NK.MUSHROOM_STEW, "mushroom_stew");
      this.registerItem(NK.GOLDEN_SWORD, "golden_sword");
      this.registerItem(NK.GOLDEN_SHOVEL, "golden_shovel");
      this.registerItem(NK.GOLDEN_PICKAXE, "golden_pickaxe");
      this.registerItem(NK.GOLDEN_AXE, "golden_axe");
      this.registerItem(NK.STRING, "string");
      this.registerItem(NK.FEATHER, "feather");
      this.registerItem(NK.GUNPOWDER, "gunpowder");
      this.registerItem(NK.WOODEN_HOE, "wooden_hoe");
      this.registerItem(NK.STONE_HOE, "stone_hoe");
      this.registerItem(NK.IRON_HOE, "iron_hoe");
      this.registerItem(NK.DIAMOND_HOE, "diamond_hoe");
      this.registerItem(NK.GOLDEN_HOE, "golden_hoe");
      this.registerItem(NK.WHEAT_SEEDS, "wheat_seeds");
      this.registerItem(NK.WHEAT, "wheat");
      this.registerItem(NK.BREAD, "bread");
      this.registerItem(NK.LEATHER_HELMET, "leather_helmet");
      this.registerItem(NK.LEATHER_CHESTPLATE, "leather_chestplate");
      this.registerItem(NK.LEATHER_LEGGINGS, "leather_leggings");
      this.registerItem(NK.LEATHER_BOOTS, "leather_boots");
      this.registerItem(NK.CHAINMAIL_HELMET, "chainmail_helmet");
      this.registerItem(NK.CHAINMAIL_CHESTPLATE, "chainmail_chestplate");
      this.registerItem(NK.CHAINMAIL_LEGGINGS, "chainmail_leggings");
      this.registerItem(NK.CHAINMAIL_BOOTS, "chainmail_boots");
      this.registerItem(NK.IRON_HELMET, "iron_helmet");
      this.registerItem(NK.IRON_CHESTPLATE, "iron_chestplate");
      this.registerItem(NK.IRON_LEGGINGS, "iron_leggings");
      this.registerItem(NK.IRON_BOOTS, "iron_boots");
      this.registerItem(NK.DIAMOND_HELMET, "diamond_helmet");
      this.registerItem(NK.DIAMOND_CHESTPLATE, "diamond_chestplate");
      this.registerItem(NK.DIAMOND_LEGGINGS, "diamond_leggings");
      this.registerItem(NK.DIAMOND_BOOTS, "diamond_boots");
      this.registerItem(NK.GOLDEN_HELMET, "golden_helmet");
      this.registerItem(NK.GOLDEN_CHESTPLATE, "golden_chestplate");
      this.registerItem(NK.GOLDEN_LEGGINGS, "golden_leggings");
      this.registerItem(NK.GOLDEN_BOOTS, "golden_boots");
      this.registerItem(NK.FLINT, "flint");
      this.registerItem(NK.PORKCHOP, "porkchop");
      this.registerItem(NK.COOKED_PORKCHOP, "cooked_porkchop");
      this.registerItem(NK.PAINTING, "painting");
      this.registerItem(NK.GOLDEN_APPLE, "golden_apple");
      this.registerItem(NK.GOLDEN_APPLE, 1, "golden_apple");
      this.registerItem(NK.SIGN, "sign");
      this.registerItem(NK.OAK_DOOR, "oak_door");
      this.registerItem(NK.SPRUCE_DOOR, "spruce_door");
      this.registerItem(NK.BIRCH_DOOR, "birch_door");
      this.registerItem(NK.JUNGLE_DOOR, "jungle_door");
      this.registerItem(NK.ACACIA_DOOR, "acacia_door");
      this.registerItem(NK.DARK_OAK_DOOR, "dark_oak_door");
      this.registerItem(NK.BUCKET, "bucket");
      this.registerItem(NK.WATER_BUCKET, "water_bucket");
      this.registerItem(NK.LAVA_BUCKET, "lava_bucket");
      this.registerItem(NK.MINECART, "minecart");
      this.registerItem(NK.SADDLE, "saddle");
      this.registerItem(NK.IRON_DOOR, "iron_door");
      this.registerItem(NK.REDSTONE, "redstone");
      this.registerItem(NK.SNOWBALL, "snowball");
      this.registerItem(NK.BOAT, "oak_boat");
      this.registerItem(NK.SPRUCE_BOAT, "spruce_boat");
      this.registerItem(NK.BIRCH_BOAT, "birch_boat");
      this.registerItem(NK.JUNGLE_BOAT, "jungle_boat");
      this.registerItem(NK.ACACIA_BOAT, "acacia_boat");
      this.registerItem(NK.DARK_OAK_BOAT, "dark_oak_boat");
      this.registerItem(NK.LEATHER, "leather");
      this.registerItem(NK.MILK_BUCKET, "milk_bucket");
      this.registerItem(NK.BRICK, "brick");
      this.registerItem(NK.CLAY_BALL, "clay_ball");
      this.registerItem(NK.REEDS, "reeds");
      this.registerItem(NK.PAPER, "paper");
      this.registerItem(NK.BOOK, "book");
      this.registerItem(NK.SLIME_BALL, "slime_ball");
      this.registerItem(NK.CHEST_MINECART, "chest_minecart");
      this.registerItem(NK.FURNACE_MINECART, "furnace_minecart");
      this.registerItem(NK.EGG, "egg");
      this.registerItem(NK.COMPASS, "compass");
      this.registerItem(NK.FISHING_ROD, "fishing_rod");
      this.registerItem(NK.CLOCK, "clock");
      this.registerItem(NK.GLOWSTONE_DUST, "glowstone_dust");
      this.registerItem(NK.FISH, PD.COD.getMetadata(), "cod");
      this.registerItem(NK.FISH, PD.SALMON.getMetadata(), "salmon");
      this.registerItem(NK.FISH, PD.CLOWNFISH.getMetadata(), "clownfish");
      this.registerItem(NK.FISH, PD.PUFFERFISH.getMetadata(), "pufferfish");
      this.registerItem(NK.COOKED_FISH, PD.COD.getMetadata(), "cooked_cod");
      this.registerItem(NK.COOKED_FISH, PD.SALMON.getMetadata(), "cooked_salmon");
      this.registerItem(NK.DYE, Om.BLACK.getDyeDamage(), "dye_black");
      this.registerItem(NK.DYE, Om.RED.getDyeDamage(), "dye_red");
      this.registerItem(NK.DYE, Om.GREEN.getDyeDamage(), "dye_green");
      this.registerItem(NK.DYE, Om.BROWN.getDyeDamage(), "dye_brown");
      this.registerItem(NK.DYE, Om.BLUE.getDyeDamage(), "dye_blue");
      this.registerItem(NK.DYE, Om.PURPLE.getDyeDamage(), "dye_purple");
      this.registerItem(NK.DYE, Om.CYAN.getDyeDamage(), "dye_cyan");
      this.registerItem(NK.DYE, Om.SILVER.getDyeDamage(), "dye_silver");
      this.registerItem(NK.DYE, Om.GRAY.getDyeDamage(), "dye_gray");
      this.registerItem(NK.DYE, Om.PINK.getDyeDamage(), "dye_pink");
      this.registerItem(NK.DYE, Om.LIME.getDyeDamage(), "dye_lime");
      this.registerItem(NK.DYE, Om.YELLOW.getDyeDamage(), "dye_yellow");
      this.registerItem(NK.DYE, Om.LIGHT_BLUE.getDyeDamage(), "dye_light_blue");
      this.registerItem(NK.DYE, Om.MAGENTA.getDyeDamage(), "dye_magenta");
      this.registerItem(NK.DYE, Om.ORANGE.getDyeDamage(), "dye_orange");
      this.registerItem(NK.DYE, Om.WHITE.getDyeDamage(), "dye_white");
      this.registerItem(NK.BONE, "bone");
      this.registerItem(NK.SUGAR, "sugar");
      this.registerItem(NK.CAKE, "cake");
      this.registerItem(NK.REPEATER, "repeater");
      this.registerItem(NK.COOKIE, "cookie");
      this.registerItem(NK.SHEARS, "shears");
      this.registerItem(NK.MELON, "melon");
      this.registerItem(NK.PUMPKIN_SEEDS, "pumpkin_seeds");
      this.registerItem(NK.MELON_SEEDS, "melon_seeds");
      this.registerItem(NK.BEEF, "beef");
      this.registerItem(NK.COOKED_BEEF, "cooked_beef");
      this.registerItem(NK.CHICKEN, "chicken");
      this.registerItem(NK.COOKED_CHICKEN, "cooked_chicken");
      this.registerItem(NK.RABBIT, "rabbit");
      this.registerItem(NK.COOKED_RABBIT, "cooked_rabbit");
      this.registerItem(NK.MUTTON, "mutton");
      this.registerItem(NK.COOKED_MUTTON, "cooked_mutton");
      this.registerItem(NK.RABBIT_FOOT, "rabbit_foot");
      this.registerItem(NK.RABBIT_HIDE, "rabbit_hide");
      this.registerItem(NK.RABBIT_STEW, "rabbit_stew");
      this.registerItem(NK.ROTTEN_FLESH, "rotten_flesh");
      this.registerItem(NK.ENDER_PEARL, "ender_pearl");
      this.registerItem(NK.BLAZE_ROD, "blaze_rod");
      this.registerItem(NK.GHAST_TEAR, "ghast_tear");
      this.registerItem(NK.GOLD_NUGGET, "gold_nugget");
      this.registerItem(NK.NETHER_WART, "nether_wart");
      this.registerItem(NK.BEETROOT, "beetroot");
      this.registerItem(NK.BEETROOT_SEEDS, "beetroot_seeds");
      this.registerItem(NK.BEETROOT_SOUP, "beetroot_soup");
      this.registerItem(NK.TOTEM_OF_UNDYING, "totem");
      this.registerItem(NK.POTIONITEM, "bottle_drinkable");
      this.registerItem(NK.SPLASH_POTION, "bottle_splash");
      this.registerItem(NK.LINGERING_POTION, "bottle_lingering");
      this.registerItem(NK.GLASS_BOTTLE, "glass_bottle");
      this.registerItem(NK.DRAGON_BREATH, "dragon_breath");
      this.registerItem(NK.SPIDER_EYE, "spider_eye");
      this.registerItem(NK.FERMENTED_SPIDER_EYE, "fermented_spider_eye");
      this.registerItem(NK.BLAZE_POWDER, "blaze_powder");
      this.registerItem(NK.MAGMA_CREAM, "magma_cream");
      this.registerItem(NK.BREWING_STAND, "brewing_stand");
      this.registerItem(NK.CAULDRON, "cauldron");
      this.registerItem(NK.ENDER_EYE, "ender_eye");
      this.registerItem(NK.SPECKLED_MELON, "speckled_melon");
      this.itemModelMesher.register(NK.SPAWN_EGG, new yl() {
         public sD getModelLocation(Qy stack) {
            return new sD("spawn_egg", "inventory");
         }
      });
      this.registerItem(NK.EXPERIENCE_BOTTLE, "experience_bottle");
      this.registerItem(NK.FIRE_CHARGE, "fire_charge");
      this.registerItem(NK.WRITABLE_BOOK, "writable_book");
      this.registerItem(NK.EMERALD, "emerald");
      this.registerItem(NK.ITEM_FRAME, "item_frame");
      this.registerItem(NK.FLOWER_POT, "flower_pot");
      this.registerItem(NK.CARROT, "carrot");
      this.registerItem(NK.POTATO, "potato");
      this.registerItem(NK.BAKED_POTATO, "baked_potato");
      this.registerItem(NK.POISONOUS_POTATO, "poisonous_potato");
      this.registerItem(NK.MAP, "map");
      this.registerItem(NK.GOLDEN_CARROT, "golden_carrot");
      this.registerItem(NK.SKULL, 0, "skull_skeleton");
      this.registerItem(NK.SKULL, 1, "skull_wither");
      this.registerItem(NK.SKULL, 2, "skull_zombie");
      this.registerItem(NK.SKULL, 3, "skull_char");
      this.registerItem(NK.SKULL, 4, "skull_creeper");
      this.registerItem(NK.SKULL, 5, "skull_dragon");
      this.registerItem(NK.CARROT_ON_A_STICK, "carrot_on_a_stick");
      this.registerItem(NK.NETHER_STAR, "nether_star");
      this.registerItem(NK.END_CRYSTAL, "end_crystal");
      this.registerItem(NK.PUMPKIN_PIE, "pumpkin_pie");
      this.registerItem(NK.FIREWORK_CHARGE, "firework_charge");
      this.registerItem(NK.COMPARATOR, "comparator");
      this.registerItem(NK.NETHERBRICK, "netherbrick");
      this.registerItem(NK.QUARTZ, "quartz");
      this.registerItem(NK.TNT_MINECART, "tnt_minecart");
      this.registerItem(NK.HOPPER_MINECART, "hopper_minecart");
      this.registerItem(NK.ARMOR_STAND, "armor_stand");
      this.registerItem(NK.IRON_HORSE_ARMOR, "iron_horse_armor");
      this.registerItem(NK.GOLDEN_HORSE_ARMOR, "golden_horse_armor");
      this.registerItem(NK.DIAMOND_HORSE_ARMOR, "diamond_horse_armor");
      this.registerItem(NK.LEAD, "lead");
      this.registerItem(NK.NAME_TAG, "name_tag");
      this.itemModelMesher.register(NK.BANNER, new yl() {
         public sD getModelLocation(Qy stack) {
            return new sD("banner", "inventory");
         }
      });
      this.itemModelMesher.register(NK.BED, new yl() {
         public sD getModelLocation(Qy stack) {
            return new sD("bed", "inventory");
         }
      });
      this.itemModelMesher.register(NK.SHIELD, new yl() {
         public sD getModelLocation(Qy stack) {
            return new sD("shield", "inventory");
         }
      });
      this.registerItem(NK.ELYTRA, "elytra");
      this.registerItem(NK.CHORUS_FRUIT, "chorus_fruit");
      this.registerItem(NK.CHORUS_FRUIT_POPPED, "chorus_fruit_popped");
      this.registerItem(NK.SHULKER_SHELL, "shulker_shell");
      this.registerItem(NK.IRON_NUGGET, "iron_nugget");
      this.registerItem(NK.RECORD_13, "record_13");
      this.registerItem(NK.RECORD_CAT, "record_cat");
      this.registerItem(NK.RECORD_BLOCKS, "record_blocks");
      this.registerItem(NK.RECORD_CHIRP, "record_chirp");
      this.registerItem(NK.RECORD_FAR, "record_far");
      this.registerItem(NK.RECORD_MALL, "record_mall");
      this.registerItem(NK.RECORD_MELLOHI, "record_mellohi");
      this.registerItem(NK.RECORD_STAL, "record_stal");
      this.registerItem(NK.RECORD_STRAD, "record_strad");
      this.registerItem(NK.RECORD_WARD, "record_ward");
      this.registerItem(NK.RECORD_11, "record_11");
      this.registerItem(NK.RECORD_WAIT, "record_wait");
      this.registerItem(NK.PRISMARINE_SHARD, "prismarine_shard");
      this.registerItem(NK.PRISMARINE_CRYSTALS, "prismarine_crystals");
      this.registerItem(NK.KNOWLEDGE_BOOK, "knowledge_book");
      this.itemModelMesher.register(NK.ENCHANTED_BOOK, new yl() {
         public sD getModelLocation(Qy stack) {
            return new sD("enchanted_book", "inventory");
         }
      });
      this.itemModelMesher.register(NK.FILLED_MAP, new yl() {
         public sD getModelLocation(Qy stack) {
            return new sD("filled_map", "inventory");
         }
      });
      this.registerBlock(Nk.COMMAND_BLOCK, "command_block");
      this.registerItem(NK.FIREWORKS, "fireworks");
      this.registerItem(NK.COMMAND_BLOCK_MINECART, "command_block_minecart");
      this.registerBlock(Nk.BARRIER, "barrier");
      this.registerBlock(Nk.MOB_SPAWNER, "mob_spawner");
      this.registerItem(NK.WRITTEN_BOOK, "written_book");
      this.registerBlock(Nk.BROWN_MUSHROOM_BLOCK, ep.ALL_INSIDE.getMetadata(), "brown_mushroom_block");
      this.registerBlock(Nk.RED_MUSHROOM_BLOCK, ep.ALL_INSIDE.getMetadata(), "red_mushroom_block");
      this.registerBlock(Nk.DRAGON_EGG, "dragon_egg");
      this.registerBlock(Nk.REPEATING_COMMAND_BLOCK, "repeating_command_block");
      this.registerBlock(Nk.CHAIN_COMMAND_BLOCK, "chain_command_block");
      this.registerBlock(Nk.STRUCTURE_BLOCK, YU.SAVE.getModeId(), "structure_block");
      this.registerBlock(Nk.STRUCTURE_BLOCK, YU.LOAD.getModeId(), "structure_block");
      this.registerBlock(Nk.STRUCTURE_BLOCK, YU.CORNER.getModeId(), "structure_block");
      this.registerBlock(Nk.STRUCTURE_BLOCK, YU.DATA.getModeId(), "structure_block");
      if (bnK.ModelLoader_onRegisterItems.exists()) {
         bnK.call(bnK.ModelLoader_onRegisterItems, this.itemModelMesher);
      }

   }

   public void onResourceManagerReload(AA resourceManager) {
      this.itemModelMesher.rebuildCache();
   }
}
