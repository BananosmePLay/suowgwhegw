package neo;

import java.util.BitSet;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class ty {
   private final uy blockColors;
   private static float aoLightValueOpaque = 0.2F;
   private static boolean separateAoLightValue = false;
   private static final BlockRenderLayer[] OVERLAY_LAYERS;

   public ty(uy blockColorsIn) {
      this.blockColors = blockColorsIn;
      if (bnK.ForgeModContainer_forgeLightPipelineEnabled.exists()) {
         bnK.setFieldValue(bnK.ForgeModContainer_forgeLightPipelineEnabled, false);
      }

   }

   public boolean renderModel(bfZ blockAccessIn, sc modelIn, in blockStateIn, BlockPos blockPosIn, tN buffer, boolean checkSides) {
      return this.renderModel(blockAccessIn, modelIn, blockStateIn, blockPosIn, buffer, checkSides, MathHelper.getPositionRandom(blockPosIn));
   }

   public boolean renderModel(bfZ worldIn, sc modelIn, in stateIn, BlockPos posIn, tN buffer, boolean checkSides, long rand) {
      boolean flag = nC.isAmbientOcclusionEnabled() && bnQ.getLightValue(stateIn, worldIn, posIn) == 0 && bnQ.isAmbientOcclusion(modelIn, stateIn);

      try {
         if (XH.isShaders()) {
            bpz.pushEntity(stateIn, posIn, worldIn, buffer);
         }

         if (!XH.isAlternateBlocks()) {
            rand = 0L;
         }

         boa renderenv = buffer.getRenderEnv(stateIn, posIn);
         modelIn = bnb.getRenderModel(modelIn, stateIn, renderenv);
         boolean flag1 = flag ? this.renderModelSmooth(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand) : this.renderModelFlat(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand);
         if (flag1) {
            this.renderOverlayModels(worldIn, modelIn, stateIn, posIn, buffer, checkSides, rand, renderenv, flag);
         }

         if (XH.isShaders()) {
            bpz.popEntity(buffer);
         }

         return flag1;
      } catch (Throwable var13) {
         Throwable throwable1 = var13;
         Er crashreport = Er.makeCrashReport(throwable1, "Tesselating block model");
         Ey crashreportcategory = crashreport.makeCategory("Block model being tesselated");
         Ey.addBlockInfo(crashreportcategory, posIn, stateIn);
         crashreportcategory.addCrashSection("Using AO", flag);
         throw new ReportedException(crashreport);
      }
   }

   public boolean renderModelSmooth(bfZ worldIn, sc modelIn, in stateIn, BlockPos posIn, tN buffer, boolean checkSides, long rand) {
      boolean flag = false;
      boa renderenv = buffer.getRenderEnv(stateIn, posIn);
      BlockRenderLayer blockrenderlayer = buffer.getBlockLayer();
      EnumFacing[] var12 = EnumFacing.VALUES;
      int var13 = var12.length;

      for(int var14 = 0; var14 < var13; ++var14) {
         EnumFacing enumfacing = var12[var14];
         List<rK> list = modelIn.getQuads(stateIn, enumfacing, rand);
         if (!list.isEmpty() && (!checkSides || stateIn.shouldSideBeRendered(worldIn, posIn, enumfacing))) {
            list = bnb.getRenderQuads(list, worldIn, stateIn, posIn, enumfacing, blockrenderlayer, rand, renderenv);
            this.renderQuadsSmooth(worldIn, stateIn, posIn, buffer, list, renderenv);
            flag = true;
         }
      }

      List<rK> list1 = modelIn.getQuads(stateIn, (EnumFacing)null, rand);
      if (!list1.isEmpty()) {
         list1 = bnb.getRenderQuads(list1, worldIn, stateIn, posIn, (EnumFacing)null, blockrenderlayer, rand, renderenv);
         this.renderQuadsSmooth(worldIn, stateIn, posIn, buffer, list1, renderenv);
         flag = true;
      }

      return flag;
   }

   public boolean renderModelFlat(bfZ worldIn, sc modelIn, in stateIn, BlockPos posIn, tN buffer, boolean checkSides, long rand) {
      boolean flag = false;
      boa renderenv = buffer.getRenderEnv(stateIn, posIn);
      BlockRenderLayer blockrenderlayer = buffer.getBlockLayer();
      EnumFacing[] var12 = EnumFacing.VALUES;
      int var13 = var12.length;

      for(int var14 = 0; var14 < var13; ++var14) {
         EnumFacing enumfacing = var12[var14];
         List<rK> list = modelIn.getQuads(stateIn, enumfacing, rand);
         if (!list.isEmpty() && (!checkSides || stateIn.shouldSideBeRendered(worldIn, posIn, enumfacing))) {
            int i = stateIn.getPackedLightmapCoords(worldIn, posIn.offset(enumfacing));
            list = bnb.getRenderQuads(list, worldIn, stateIn, posIn, enumfacing, blockrenderlayer, rand, renderenv);
            this.renderQuadsFlat(worldIn, stateIn, posIn, i, false, buffer, list, renderenv);
            flag = true;
         }
      }

      List<rK> list1 = modelIn.getQuads(stateIn, (EnumFacing)null, rand);
      if (!list1.isEmpty()) {
         list1 = bnb.getRenderQuads(list1, worldIn, stateIn, posIn, (EnumFacing)null, blockrenderlayer, rand, renderenv);
         this.renderQuadsFlat(worldIn, stateIn, posIn, -1, true, buffer, list1, renderenv);
         flag = true;
      }

      return flag;
   }

   private void renderQuadsSmooth(bfZ p_renderQuadsSmooth_1_, in p_renderQuadsSmooth_2_, BlockPos p_renderQuadsSmooth_3_, tN p_renderQuadsSmooth_4_, List<rK> p_renderQuadsSmooth_5_, boa p_renderQuadsSmooth_6_) {
      float[] afloat = p_renderQuadsSmooth_6_.getQuadBounds();
      BitSet bitset = p_renderQuadsSmooth_6_.getBoundsFlags();
      tu blockmodelrenderer$ambientocclusionface = p_renderQuadsSmooth_6_.getAoFace();
      Vec3d vec3d = p_renderQuadsSmooth_2_.getOffset(p_renderQuadsSmooth_1_, p_renderQuadsSmooth_3_);
      double d0 = (double)p_renderQuadsSmooth_3_.getX() + vec3d.x;
      double d1 = (double)p_renderQuadsSmooth_3_.getY() + vec3d.y;
      double d2 = (double)p_renderQuadsSmooth_3_.getZ() + vec3d.z;
      int i = 0;

      for(int j = p_renderQuadsSmooth_5_.size(); i < j; ++i) {
         rK bakedquad = (rK)p_renderQuadsSmooth_5_.get(i);
         this.fillQuadBounds(p_renderQuadsSmooth_2_, bakedquad.getVertexData(), bakedquad.getFace(), afloat, bitset);
         blockmodelrenderer$ambientocclusionface.updateVertexBrightness(p_renderQuadsSmooth_1_, p_renderQuadsSmooth_2_, p_renderQuadsSmooth_3_, bakedquad.getFace(), afloat, bitset);
         if (bakedquad.getSprite().isEmissive) {
            blockmodelrenderer$ambientocclusionface.setMaxBlockLight();
         }

         if (p_renderQuadsSmooth_4_.isMultiTexture()) {
            p_renderQuadsSmooth_4_.addVertexData(bakedquad.getVertexDataSingle());
         } else {
            p_renderQuadsSmooth_4_.addVertexData(bakedquad.getVertexData());
         }

         p_renderQuadsSmooth_4_.putSprite(bakedquad.getSprite());
         p_renderQuadsSmooth_4_.putBrightness4(tu.access$000(blockmodelrenderer$ambientocclusionface)[0], tu.access$000(blockmodelrenderer$ambientocclusionface)[1], tu.access$000(blockmodelrenderer$ambientocclusionface)[2], tu.access$000(blockmodelrenderer$ambientocclusionface)[3]);
         if (bakedquad.shouldApplyDiffuseLighting()) {
            float f = sb.getFaceBrightness(bakedquad.getFace());
            float[] afloat1 = tu.access$100(blockmodelrenderer$ambientocclusionface);
            afloat1[0] *= f;
            afloat1 = tu.access$100(blockmodelrenderer$ambientocclusionface);
            afloat1[1] *= f;
            afloat1 = tu.access$100(blockmodelrenderer$ambientocclusionface);
            afloat1[2] *= f;
            afloat1 = tu.access$100(blockmodelrenderer$ambientocclusionface);
            afloat1[3] *= f;
         }

         int l = bjy.getColorMultiplier(bakedquad, p_renderQuadsSmooth_2_, p_renderQuadsSmooth_1_, p_renderQuadsSmooth_3_, p_renderQuadsSmooth_6_);
         if (!bakedquad.hasTintIndex() && l == -1) {
            if (separateAoLightValue) {
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, tu.access$100(blockmodelrenderer$ambientocclusionface)[0], 4);
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, tu.access$100(blockmodelrenderer$ambientocclusionface)[1], 3);
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, tu.access$100(blockmodelrenderer$ambientocclusionface)[2], 2);
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(1.0F, 1.0F, 1.0F, tu.access$100(blockmodelrenderer$ambientocclusionface)[3], 1);
            } else {
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[0], tu.access$100(blockmodelrenderer$ambientocclusionface)[0], tu.access$100(blockmodelrenderer$ambientocclusionface)[0], 4);
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[1], tu.access$100(blockmodelrenderer$ambientocclusionface)[1], tu.access$100(blockmodelrenderer$ambientocclusionface)[1], 3);
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[2], tu.access$100(blockmodelrenderer$ambientocclusionface)[2], tu.access$100(blockmodelrenderer$ambientocclusionface)[2], 2);
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[3], tu.access$100(blockmodelrenderer$ambientocclusionface)[3], tu.access$100(blockmodelrenderer$ambientocclusionface)[3], 1);
            }
         } else {
            int k = l;
            if (l == -1) {
               k = this.blockColors.colorMultiplier(p_renderQuadsSmooth_2_, p_renderQuadsSmooth_1_, p_renderQuadsSmooth_3_, bakedquad.getTintIndex());
            }

            if (xz.anaglyphEnable) {
               k = zk.anaglyphColor(k);
            }

            float f1 = (float)(k >> 16 & 255) / 255.0F;
            float f2 = (float)(k >> 8 & 255) / 255.0F;
            float f3 = (float)(k & 255) / 255.0F;
            if (separateAoLightValue) {
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(f1, f2, f3, tu.access$100(blockmodelrenderer$ambientocclusionface)[0], 4);
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(f1, f2, f3, tu.access$100(blockmodelrenderer$ambientocclusionface)[1], 3);
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(f1, f2, f3, tu.access$100(blockmodelrenderer$ambientocclusionface)[2], 2);
               p_renderQuadsSmooth_4_.putColorMultiplierRgba(f1, f2, f3, tu.access$100(blockmodelrenderer$ambientocclusionface)[3], 1);
            } else {
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[0] * f1, tu.access$100(blockmodelrenderer$ambientocclusionface)[0] * f2, tu.access$100(blockmodelrenderer$ambientocclusionface)[0] * f3, 4);
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[1] * f1, tu.access$100(blockmodelrenderer$ambientocclusionface)[1] * f2, tu.access$100(blockmodelrenderer$ambientocclusionface)[1] * f3, 3);
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[2] * f1, tu.access$100(blockmodelrenderer$ambientocclusionface)[2] * f2, tu.access$100(blockmodelrenderer$ambientocclusionface)[2] * f3, 2);
               p_renderQuadsSmooth_4_.putColorMultiplier(tu.access$100(blockmodelrenderer$ambientocclusionface)[3] * f1, tu.access$100(blockmodelrenderer$ambientocclusionface)[3] * f2, tu.access$100(blockmodelrenderer$ambientocclusionface)[3] * f3, 1);
            }
         }

         p_renderQuadsSmooth_4_.putPosition(d0, d1, d2);
      }

   }

   private void fillQuadBounds(in stateIn, int[] vertexData, EnumFacing face, @Nullable float[] quadBounds, BitSet boundsFlags) {
      float f = 32.0F;
      float f1 = 32.0F;
      float f2 = 32.0F;
      float f3 = -32.0F;
      float f4 = -32.0F;
      float f5 = -32.0F;
      int i = vertexData.length / 4;

      int k;
      float f10;
      for(k = 0; k < 4; ++k) {
         f10 = Float.intBitsToFloat(vertexData[k * i]);
         float f7 = Float.intBitsToFloat(vertexData[k * i + 1]);
         float f8 = Float.intBitsToFloat(vertexData[k * i + 2]);
         f = Math.min(f, f10);
         f1 = Math.min(f1, f7);
         f2 = Math.min(f2, f8);
         f3 = Math.max(f3, f10);
         f4 = Math.max(f4, f7);
         f5 = Math.max(f5, f8);
      }

      if (quadBounds != null) {
         quadBounds[EnumFacing.WEST.getIndex()] = f;
         quadBounds[EnumFacing.EAST.getIndex()] = f3;
         quadBounds[EnumFacing.DOWN.getIndex()] = f1;
         quadBounds[EnumFacing.UP.getIndex()] = f4;
         quadBounds[EnumFacing.NORTH.getIndex()] = f2;
         quadBounds[EnumFacing.SOUTH.getIndex()] = f5;
         k = EnumFacing.VALUES.length;
         quadBounds[EnumFacing.WEST.getIndex() + k] = 1.0F - f;
         quadBounds[EnumFacing.EAST.getIndex() + k] = 1.0F - f3;
         quadBounds[EnumFacing.DOWN.getIndex() + k] = 1.0F - f1;
         quadBounds[EnumFacing.UP.getIndex() + k] = 1.0F - f4;
         quadBounds[EnumFacing.NORTH.getIndex() + k] = 1.0F - f2;
         quadBounds[EnumFacing.SOUTH.getIndex() + k] = 1.0F - f5;
      }

      float f9 = 1.0E-4F;
      f10 = 0.9999F;
      switch (face) {
         case DOWN:
            boundsFlags.set(1, f >= 1.0E-4F || f2 >= 1.0E-4F || f3 <= 0.9999F || f5 <= 0.9999F);
            boundsFlags.set(0, (f1 < 1.0E-4F || stateIn.isFullCube()) && f1 == f4);
            break;
         case UP:
            boundsFlags.set(1, f >= 1.0E-4F || f2 >= 1.0E-4F || f3 <= 0.9999F || f5 <= 0.9999F);
            boundsFlags.set(0, (f4 > 0.9999F || stateIn.isFullCube()) && f1 == f4);
            break;
         case NORTH:
            boundsFlags.set(1, f >= 1.0E-4F || f1 >= 1.0E-4F || f3 <= 0.9999F || f4 <= 0.9999F);
            boundsFlags.set(0, (f2 < 1.0E-4F || stateIn.isFullCube()) && f2 == f5);
            break;
         case SOUTH:
            boundsFlags.set(1, f >= 1.0E-4F || f1 >= 1.0E-4F || f3 <= 0.9999F || f4 <= 0.9999F);
            boundsFlags.set(0, (f5 > 0.9999F || stateIn.isFullCube()) && f2 == f5);
            break;
         case WEST:
            boundsFlags.set(1, f1 >= 1.0E-4F || f2 >= 1.0E-4F || f4 <= 0.9999F || f5 <= 0.9999F);
            boundsFlags.set(0, (f < 1.0E-4F || stateIn.isFullCube()) && f == f3);
            break;
         case EAST:
            boundsFlags.set(1, f1 >= 1.0E-4F || f2 >= 1.0E-4F || f4 <= 0.9999F || f5 <= 0.9999F);
            boundsFlags.set(0, (f3 > 0.9999F || stateIn.isFullCube()) && f == f3);
      }

   }

   private void renderQuadsFlat(bfZ p_renderQuadsFlat_1_, in p_renderQuadsFlat_2_, BlockPos p_renderQuadsFlat_3_, int p_renderQuadsFlat_4_, boolean p_renderQuadsFlat_5_, tN p_renderQuadsFlat_6_, List<rK> p_renderQuadsFlat_7_, boa p_renderQuadsFlat_8_) {
      BitSet bitset = p_renderQuadsFlat_8_.getBoundsFlags();
      Vec3d vec3d = p_renderQuadsFlat_2_.getOffset(p_renderQuadsFlat_1_, p_renderQuadsFlat_3_);
      double d0 = (double)p_renderQuadsFlat_3_.getX() + vec3d.x;
      double d1 = (double)p_renderQuadsFlat_3_.getY() + vec3d.y;
      double d2 = (double)p_renderQuadsFlat_3_.getZ() + vec3d.z;
      int i = 0;

      for(int j = p_renderQuadsFlat_7_.size(); i < j; ++i) {
         rK bakedquad = (rK)p_renderQuadsFlat_7_.get(i);
         if (p_renderQuadsFlat_5_) {
            this.fillQuadBounds(p_renderQuadsFlat_2_, bakedquad.getVertexData(), bakedquad.getFace(), (float[])null, bitset);
            BlockPos blockpos = bitset.get(0) ? p_renderQuadsFlat_3_.offset(bakedquad.getFace()) : p_renderQuadsFlat_3_;
            p_renderQuadsFlat_4_ = p_renderQuadsFlat_2_.getPackedLightmapCoords(p_renderQuadsFlat_1_, blockpos);
         }

         if (bakedquad.getSprite().isEmissive) {
            p_renderQuadsFlat_4_ |= 240;
         }

         if (p_renderQuadsFlat_6_.isMultiTexture()) {
            p_renderQuadsFlat_6_.addVertexData(bakedquad.getVertexDataSingle());
         } else {
            p_renderQuadsFlat_6_.addVertexData(bakedquad.getVertexData());
         }

         p_renderQuadsFlat_6_.putSprite(bakedquad.getSprite());
         p_renderQuadsFlat_6_.putBrightness4(p_renderQuadsFlat_4_, p_renderQuadsFlat_4_, p_renderQuadsFlat_4_, p_renderQuadsFlat_4_);
         int l = bjy.getColorMultiplier(bakedquad, p_renderQuadsFlat_2_, p_renderQuadsFlat_1_, p_renderQuadsFlat_3_, p_renderQuadsFlat_8_);
         if (!bakedquad.hasTintIndex() && l == -1) {
            if (bakedquad.shouldApplyDiffuseLighting()) {
               float f4 = sb.getFaceBrightness(bakedquad.getFace());
               p_renderQuadsFlat_6_.putColorMultiplier(f4, f4, f4, 4);
               p_renderQuadsFlat_6_.putColorMultiplier(f4, f4, f4, 3);
               p_renderQuadsFlat_6_.putColorMultiplier(f4, f4, f4, 2);
               p_renderQuadsFlat_6_.putColorMultiplier(f4, f4, f4, 1);
            }
         } else {
            int k = l;
            if (l == -1) {
               k = this.blockColors.colorMultiplier(p_renderQuadsFlat_2_, p_renderQuadsFlat_1_, p_renderQuadsFlat_3_, bakedquad.getTintIndex());
            }

            if (xz.anaglyphEnable) {
               k = zk.anaglyphColor(k);
            }

            float f = (float)(k >> 16 & 255) / 255.0F;
            float f1 = (float)(k >> 8 & 255) / 255.0F;
            float f2 = (float)(k & 255) / 255.0F;
            if (bakedquad.shouldApplyDiffuseLighting()) {
               float f3 = sb.getFaceBrightness(bakedquad.getFace());
               f *= f3;
               f1 *= f3;
               f2 *= f3;
            }

            p_renderQuadsFlat_6_.putColorMultiplier(f, f1, f2, 4);
            p_renderQuadsFlat_6_.putColorMultiplier(f, f1, f2, 3);
            p_renderQuadsFlat_6_.putColorMultiplier(f, f1, f2, 2);
            p_renderQuadsFlat_6_.putColorMultiplier(f, f1, f2, 1);
         }

         p_renderQuadsFlat_6_.putPosition(d0, d1, d2);
      }

   }

   public void renderModelBrightnessColor(sc bakedModel, float p_178262_2_, float red, float green, float blue) {
      this.renderModelBrightnessColor((in)null, bakedModel, p_178262_2_, red, green, blue);
   }

   public void renderModelBrightnessColor(in state, sc p_187495_2_, float p_187495_3_, float p_187495_4_, float p_187495_5_, float p_187495_6_) {
      EnumFacing[] var7 = EnumFacing.VALUES;
      int var8 = var7.length;

      for(int var9 = 0; var9 < var8; ++var9) {
         EnumFacing enumfacing = var7[var9];
         this.renderModelBrightnessColorQuads(p_187495_3_, p_187495_4_, p_187495_5_, p_187495_6_, p_187495_2_.getQuads(state, enumfacing, 0L));
      }

      this.renderModelBrightnessColorQuads(p_187495_3_, p_187495_4_, p_187495_5_, p_187495_6_, p_187495_2_.getQuads(state, (EnumFacing)null, 0L));
   }

   public void renderModelBrightness(sc model, in state, float brightness, boolean p_178266_4_) {
      co block = state.getBlock();
      yh.rotate(90.0F, 0.0F, 1.0F, 0.0F);
      int i = this.blockColors.colorMultiplier(state, (bfZ)null, (BlockPos)null, 0);
      if (xz.anaglyphEnable) {
         i = zk.anaglyphColor(i);
      }

      float f = (float)(i >> 16 & 255) / 255.0F;
      float f1 = (float)(i >> 8 & 255) / 255.0F;
      float f2 = (float)(i & 255) / 255.0F;
      if (!p_178266_4_) {
         yh.color(brightness, brightness, brightness, 1.0F);
      }

      this.renderModelBrightnessColor(state, model, brightness, f, f1, f2);
   }

   private void renderModelBrightnessColorQuads(float brightness, float red, float green, float blue, List<rK> listQuads) {
      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      int i = 0;

      for(int j = listQuads.size(); i < j; ++i) {
         rK bakedquad = (rK)listQuads.get(i);
         bufferbuilder.begin(7, zK.ITEM);
         bufferbuilder.addVertexData(bakedquad.getVertexData());
         bufferbuilder.putSprite(bakedquad.getSprite());
         if (bakedquad.hasTintIndex()) {
            bufferbuilder.putColorRGB_F4(red * brightness, green * brightness, blue * brightness);
         } else {
            bufferbuilder.putColorRGB_F4(brightness, brightness, brightness);
         }

         Vec3i vec3i = bakedquad.getFace().getDirectionVec();
         bufferbuilder.putNormal((float)vec3i.getX(), (float)vec3i.getY(), (float)vec3i.getZ());
         tessellator.draw();
      }

   }

   public static float fixAoLightValue(float p_fixAoLightValue_0_) {
      return p_fixAoLightValue_0_ == 0.2F ? aoLightValueOpaque : p_fixAoLightValue_0_;
   }

   public static void updateAoLightValue() {
      aoLightValueOpaque = 1.0F - XH.getAmbientOcclusionLevel() * 0.8F;
      separateAoLightValue = XH.isShaders() && bpq.isSeparateAo();
   }

   private void renderOverlayModels(bfZ p_renderOverlayModels_1_, sc p_renderOverlayModels_2_, in p_renderOverlayModels_3_, BlockPos p_renderOverlayModels_4_, tN p_renderOverlayModels_5_, boolean p_renderOverlayModels_6_, long p_renderOverlayModels_7_, boa p_renderOverlayModels_9_, boolean p_renderOverlayModels_10_) {
      if (p_renderOverlayModels_9_.isOverlaysRendered()) {
         for(int i = 0; i < OVERLAY_LAYERS.length; ++i) {
            BlockRenderLayer blockrenderlayer = OVERLAY_LAYERS[i];
            bnd listquadsoverlay = p_renderOverlayModels_9_.getListQuadsOverlay(blockrenderlayer);
            if (listquadsoverlay.size() > 0) {
               yu regionrendercachebuilder = p_renderOverlayModels_9_.getRegionRenderCacheBuilder();
               if (regionrendercachebuilder != null) {
                  tN bufferbuilder = regionrendercachebuilder.getWorldRendererByLayer(blockrenderlayer);
                  if (!bufferbuilder.isDrawing()) {
                     bufferbuilder.begin(7, zK.BLOCK);
                     bufferbuilder.setTranslation(p_renderOverlayModels_5_.getXOffset(), p_renderOverlayModels_5_.getYOffset(), p_renderOverlayModels_5_.getZOffset());
                  }

                  for(int j = 0; j < listquadsoverlay.size(); ++j) {
                     rK bakedquad = listquadsoverlay.getQuad(j);
                     List<rK> list = listquadsoverlay.getListQuadsSingle(bakedquad);
                     in iblockstate = listquadsoverlay.getBlockState(j);
                     if (bakedquad.getQuadEmissive() != null) {
                        listquadsoverlay.addQuad(bakedquad.getQuadEmissive(), iblockstate);
                     }

                     p_renderOverlayModels_9_.reset(iblockstate, p_renderOverlayModels_4_);
                     if (p_renderOverlayModels_10_) {
                        this.renderQuadsSmooth(p_renderOverlayModels_1_, iblockstate, p_renderOverlayModels_4_, bufferbuilder, list, p_renderOverlayModels_9_);
                     } else {
                        int k = iblockstate.getPackedLightmapCoords(p_renderOverlayModels_1_, p_renderOverlayModels_4_.offset(bakedquad.getFace()));
                        this.renderQuadsFlat(p_renderOverlayModels_1_, iblockstate, p_renderOverlayModels_4_, k, false, bufferbuilder, list, p_renderOverlayModels_9_);
                     }
                  }
               }

               listquadsoverlay.clear();
            }
         }
      }

      if (XH.isBetterSnow() && !p_renderOverlayModels_9_.isBreakingAnimation() && biJ.shouldRender(p_renderOverlayModels_1_, p_renderOverlayModels_3_, p_renderOverlayModels_4_)) {
         sc ibakedmodel = biJ.getModelSnowLayer();
         in iblockstate1 = biJ.getStateSnowLayer();
         this.renderModel(p_renderOverlayModels_1_, ibakedmodel, iblockstate1, p_renderOverlayModels_4_, p_renderOverlayModels_5_, p_renderOverlayModels_6_, p_renderOverlayModels_7_);
      }

   }

   static {
      OVERLAY_LAYERS = new BlockRenderLayer[]{BlockRenderLayer.CUTOUT, BlockRenderLayer.CUTOUT_MIPPED, BlockRenderLayer.TRANSLUCENT};
   }
}
