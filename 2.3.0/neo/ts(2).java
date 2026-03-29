package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ts {
   private final uy blockColors;
   private final zd[] atlasSpritesLava = new zd[2];
   private final zd[] atlasSpritesWater = new zd[2];
   private zd atlasSpriteWaterOverlay;

   public ts(uy blockColorsIn) {
      this.blockColors = blockColorsIn;
      this.initAtlasSprites();
   }

   protected void initAtlasSprites() {
      zj texturemap = nC.getMinecraft().getTextureMapBlocks();
      this.atlasSpritesLava[0] = texturemap.getAtlasSprite("minecraft:blocks/lava_still");
      this.atlasSpritesLava[1] = texturemap.getAtlasSprite("minecraft:blocks/lava_flow");
      this.atlasSpritesWater[0] = texturemap.getAtlasSprite("minecraft:blocks/water_still");
      this.atlasSpritesWater[1] = texturemap.getAtlasSprite("minecraft:blocks/water_flow");
      this.atlasSpriteWaterOverlay = texturemap.getAtlasSprite("minecraft:blocks/water_overlay");
   }

   public boolean renderFluid(bfZ blockAccess, in blockStateIn, BlockPos blockPosIn, tN bufferBuilderIn) {
      boolean flag3;
      try {
         if (XH.isShaders()) {
            bpz.pushEntity(blockStateIn, blockPosIn, blockAccess, bufferBuilderIn);
         }

         eB blockliquid = (eB)blockStateIn.getBlock();
         boolean flag = blockStateIn.getMaterial() == hM.LAVA;
         zd[] atextureatlassprite = flag ? this.atlasSpritesLava : this.atlasSpritesWater;
         boa renderenv = bufferBuilderIn.getRenderEnv(blockStateIn, blockPosIn);
         int i = bjy.getFluidColor(blockAccess, blockStateIn, blockPosIn, renderenv);
         float f = (float)(i >> 16 & 255) / 255.0F;
         float f1 = (float)(i >> 8 & 255) / 255.0F;
         float f2 = (float)(i & 255) / 255.0F;
         boolean flag1 = blockStateIn.shouldSideBeRendered(blockAccess, blockPosIn, EnumFacing.UP);
         boolean flag2 = blockStateIn.shouldSideBeRendered(blockAccess, blockPosIn, EnumFacing.DOWN);
         boolean[] aboolean = renderenv.getBorderFlags();
         aboolean[0] = blockStateIn.shouldSideBeRendered(blockAccess, blockPosIn, EnumFacing.NORTH);
         aboolean[1] = blockStateIn.shouldSideBeRendered(blockAccess, blockPosIn, EnumFacing.SOUTH);
         aboolean[2] = blockStateIn.shouldSideBeRendered(blockAccess, blockPosIn, EnumFacing.WEST);
         aboolean[3] = blockStateIn.shouldSideBeRendered(blockAccess, blockPosIn, EnumFacing.EAST);
         if (flag1 || flag2 || aboolean[0] || aboolean[1] || aboolean[2] || aboolean[3]) {
            flag3 = false;
            float f3 = 0.5F;
            float f4 = 1.0F;
            float f5 = 0.8F;
            float f6 = 0.6F;
            hM material = blockStateIn.getMaterial();
            float f7 = this.getFluidHeight(blockAccess, blockPosIn, material);
            float f8 = this.getFluidHeight(blockAccess, blockPosIn.south(), material);
            float f9 = this.getFluidHeight(blockAccess, blockPosIn.east().south(), material);
            float f10 = this.getFluidHeight(blockAccess, blockPosIn.east(), material);
            double d0 = (double)blockPosIn.getX();
            double d1 = (double)blockPosIn.getY();
            double d2 = (double)blockPosIn.getZ();
            float f11 = 0.001F;
            float f38;
            float f13;
            float f14;
            float f42;
            float f43;
            float f45;
            float f46;
            if (flag1) {
               flag3 = true;
               f38 = eB.getSlopeAngle(blockAccess, blockPosIn, material, blockStateIn);
               zd textureatlassprite = f38 > -999.0F ? atextureatlassprite[1] : atextureatlassprite[0];
               bufferBuilderIn.setSprite(textureatlassprite);
               f7 -= 0.001F;
               f8 -= 0.001F;
               f9 -= 0.001F;
               f10 -= 0.001F;
               float f15;
               float f20;
               if (f38 < -999.0F) {
                  f13 = textureatlassprite.getInterpolatedU(0.0);
                  f43 = textureatlassprite.getInterpolatedV(0.0);
                  f14 = f13;
                  f45 = textureatlassprite.getInterpolatedV(16.0);
                  f15 = textureatlassprite.getInterpolatedU(16.0);
                  f46 = f45;
                  f42 = f15;
                  f20 = f43;
               } else {
                  float f21 = MathHelper.sin(f38) * 0.25F;
                  float f22 = MathHelper.cos(f38) * 0.25F;
                  float f23 = 8.0F;
                  f13 = textureatlassprite.getInterpolatedU((double)(8.0F + (-f22 - f21) * 16.0F));
                  f43 = textureatlassprite.getInterpolatedV((double)(8.0F + (-f22 + f21) * 16.0F));
                  f14 = textureatlassprite.getInterpolatedU((double)(8.0F + (-f22 + f21) * 16.0F));
                  f45 = textureatlassprite.getInterpolatedV((double)(8.0F + (f22 + f21) * 16.0F));
                  f15 = textureatlassprite.getInterpolatedU((double)(8.0F + (f22 + f21) * 16.0F));
                  f46 = textureatlassprite.getInterpolatedV((double)(8.0F + (f22 - f21) * 16.0F));
                  f42 = textureatlassprite.getInterpolatedU((double)(8.0F + (f22 - f21) * 16.0F));
                  f20 = textureatlassprite.getInterpolatedV((double)(8.0F + (-f22 - f21) * 16.0F));
               }

               int k2 = blockStateIn.getPackedLightmapCoords(blockAccess, blockPosIn);
               int l2 = k2 >> 16 & '\uffff';
               int i3 = k2 & '\uffff';
               float f24 = 1.0F * f;
               float f25 = 1.0F * f1;
               float f26 = 1.0F * f2;
               bufferBuilderIn.pos(d0 + 0.0, d1 + (double)f7, d2 + 0.0).color(f24, f25, f26, 1.0F).tex((double)f13, (double)f43).lightmap(l2, i3).endVertex();
               bufferBuilderIn.pos(d0 + 0.0, d1 + (double)f8, d2 + 1.0).color(f24, f25, f26, 1.0F).tex((double)f14, (double)f45).lightmap(l2, i3).endVertex();
               bufferBuilderIn.pos(d0 + 1.0, d1 + (double)f9, d2 + 1.0).color(f24, f25, f26, 1.0F).tex((double)f15, (double)f46).lightmap(l2, i3).endVertex();
               bufferBuilderIn.pos(d0 + 1.0, d1 + (double)f10, d2 + 0.0).color(f24, f25, f26, 1.0F).tex((double)f42, (double)f20).lightmap(l2, i3).endVertex();
               if (blockliquid.shouldRenderSides(blockAccess, blockPosIn.up())) {
                  bufferBuilderIn.pos(d0 + 0.0, d1 + (double)f7, d2 + 0.0).color(f24, f25, f26, 1.0F).tex((double)f13, (double)f43).lightmap(l2, i3).endVertex();
                  bufferBuilderIn.pos(d0 + 1.0, d1 + (double)f10, d2 + 0.0).color(f24, f25, f26, 1.0F).tex((double)f42, (double)f20).lightmap(l2, i3).endVertex();
                  bufferBuilderIn.pos(d0 + 1.0, d1 + (double)f9, d2 + 1.0).color(f24, f25, f26, 1.0F).tex((double)f15, (double)f46).lightmap(l2, i3).endVertex();
                  bufferBuilderIn.pos(d0 + 0.0, d1 + (double)f8, d2 + 1.0).color(f24, f25, f26, 1.0F).tex((double)f14, (double)f45).lightmap(l2, i3).endVertex();
               }
            }

            if (flag2) {
               bufferBuilderIn.setSprite(atextureatlassprite[0]);
               f38 = atextureatlassprite[0].getMinU();
               float f39 = atextureatlassprite[0].getMaxU();
               f13 = atextureatlassprite[0].getMinV();
               f14 = atextureatlassprite[0].getMaxV();
               int l1 = blockStateIn.getPackedLightmapCoords(blockAccess, blockPosIn.down());
               int i2 = l1 >> 16 & '\uffff';
               int j2 = l1 & '\uffff';
               f45 = sb.getFaceBrightness(EnumFacing.DOWN);
               bufferBuilderIn.pos(d0, d1, d2 + 1.0).color(f * f45, f1 * f45, f2 * f45, 1.0F).tex((double)f38, (double)f14).lightmap(i2, j2).endVertex();
               bufferBuilderIn.pos(d0, d1, d2).color(f * f45, f1 * f45, f2 * f45, 1.0F).tex((double)f38, (double)f13).lightmap(i2, j2).endVertex();
               bufferBuilderIn.pos(d0 + 1.0, d1, d2).color(f * f45, f1 * f45, f2 * f45, 1.0F).tex((double)f39, (double)f13).lightmap(i2, j2).endVertex();
               bufferBuilderIn.pos(d0 + 1.0, d1, d2 + 1.0).color(f * f45, f1 * f45, f2 * f45, 1.0F).tex((double)f39, (double)f14).lightmap(i2, j2).endVertex();
               flag3 = true;
            }

            for(int i1 = 0; i1 < 4; ++i1) {
               int j1 = 0;
               int k1 = 0;
               if (i1 == 0) {
                  --k1;
               }

               if (i1 == 1) {
                  ++k1;
               }

               if (i1 == 2) {
                  --j1;
               }

               if (i1 == 3) {
                  ++j1;
               }

               BlockPos blockpos = blockPosIn.add(j1, 0, k1);
               zd textureatlassprite1 = atextureatlassprite[1];
               bufferBuilderIn.setSprite(textureatlassprite1);
               f42 = 0.0F;
               f43 = 0.0F;
               if (!flag) {
                  in iblockstate = blockAccess.getBlockState(blockpos);
                  co block = iblockstate.getBlock();
                  if (block == Nk.GLASS || block == Nk.STAINED_GLASS || block == Nk.BEACON || block == Nk.SLIME_BLOCK) {
                     textureatlassprite1 = this.atlasSpriteWaterOverlay;
                     bufferBuilderIn.setSprite(textureatlassprite1);
                  }

                  if (block == Nk.FARMLAND || block == Nk.GRASS_PATH) {
                     f42 = 0.9375F;
                     f43 = 0.9375F;
                  }

                  if (block instanceof gG) {
                     gG blockslab = (gG)block;
                     if (!blockslab.isDouble() && iblockstate.getValue(gG.HALF) == gF.BOTTOM) {
                        f42 = 0.5F;
                        f43 = 0.5F;
                     }
                  }
               }

               if (aboolean[i1]) {
                  double d3;
                  double d4;
                  double d5;
                  double d6;
                  if (i1 == 0) {
                     f45 = f7;
                     f46 = f10;
                     d3 = d0;
                     d5 = d0 + 1.0;
                     d4 = d2 + 0.0010000000474974513;
                     d6 = d2 + 0.0010000000474974513;
                  } else if (i1 == 1) {
                     f45 = f9;
                     f46 = f8;
                     d3 = d0 + 1.0;
                     d5 = d0;
                     d4 = d2 + 1.0 - 0.0010000000474974513;
                     d6 = d2 + 1.0 - 0.0010000000474974513;
                  } else if (i1 == 2) {
                     f45 = f8;
                     f46 = f7;
                     d3 = d0 + 0.0010000000474974513;
                     d5 = d0 + 0.0010000000474974513;
                     d4 = d2 + 1.0;
                     d6 = d2;
                  } else {
                     f45 = f10;
                     f46 = f9;
                     d3 = d0 + 1.0 - 0.0010000000474974513;
                     d5 = d0 + 1.0 - 0.0010000000474974513;
                     d4 = d2;
                     d6 = d2 + 1.0;
                  }

                  if (f45 > f42 || f46 > f43) {
                     f42 = Math.min(f42, f45);
                     f43 = Math.min(f43, f46);
                     if (f42 > f11) {
                        f42 -= f11;
                     }

                     if (f43 > f11) {
                        f43 -= f11;
                     }

                     flag3 = true;
                     float f27 = textureatlassprite1.getInterpolatedU(0.0);
                     float f28 = textureatlassprite1.getInterpolatedU(8.0);
                     float f29 = textureatlassprite1.getInterpolatedV((double)((1.0F - f45) * 16.0F * 0.5F));
                     float f30 = textureatlassprite1.getInterpolatedV((double)((1.0F - f46) * 16.0F * 0.5F));
                     float f31 = textureatlassprite1.getInterpolatedV(8.0);
                     float f32 = textureatlassprite1.getInterpolatedV((double)((1.0F - f42) * 16.0F * 0.5F));
                     float f33 = textureatlassprite1.getInterpolatedV((double)((1.0F - f43) * 16.0F * 0.5F));
                     int j = blockStateIn.getPackedLightmapCoords(blockAccess, blockpos);
                     int k = j >> 16 & '\uffff';
                     int l = j & '\uffff';
                     float f34 = i1 < 2 ? sb.getFaceBrightness(EnumFacing.NORTH) : sb.getFaceBrightness(EnumFacing.WEST);
                     float f35 = 1.0F * f34 * f;
                     float f36 = 1.0F * f34 * f1;
                     float f37 = 1.0F * f34 * f2;
                     bufferBuilderIn.pos(d3, d1 + (double)f45, d4).color(f35, f36, f37, 1.0F).tex((double)f27, (double)f29).lightmap(k, l).endVertex();
                     bufferBuilderIn.pos(d5, d1 + (double)f46, d6).color(f35, f36, f37, 1.0F).tex((double)f28, (double)f30).lightmap(k, l).endVertex();
                     bufferBuilderIn.pos(d5, d1 + (double)f43, d6).color(f35, f36, f37, 1.0F).tex((double)f28, (double)f33).lightmap(k, l).endVertex();
                     bufferBuilderIn.pos(d3, d1 + (double)f42, d4).color(f35, f36, f37, 1.0F).tex((double)f27, (double)f32).lightmap(k, l).endVertex();
                     if (textureatlassprite1 != this.atlasSpriteWaterOverlay) {
                        bufferBuilderIn.pos(d3, d1 + (double)f42, d4).color(f35, f36, f37, 1.0F).tex((double)f27, (double)f32).lightmap(k, l).endVertex();
                        bufferBuilderIn.pos(d5, d1 + (double)f43, d6).color(f35, f36, f37, 1.0F).tex((double)f28, (double)f33).lightmap(k, l).endVertex();
                        bufferBuilderIn.pos(d5, d1 + (double)f46, d6).color(f35, f36, f37, 1.0F).tex((double)f28, (double)f30).lightmap(k, l).endVertex();
                        bufferBuilderIn.pos(d3, d1 + (double)f45, d4).color(f35, f36, f37, 1.0F).tex((double)f27, (double)f29).lightmap(k, l).endVertex();
                     }
                  }
               }
            }

            bufferBuilderIn.setSprite((zd)null);
            boolean var71 = flag3;
            return var71;
         }

         flag3 = false;
      } finally {
         if (XH.isShaders()) {
            bpz.popEntity(bufferBuilderIn);
         }

      }

      return flag3;
   }

   private float getFluidHeight(bfZ blockAccess, BlockPos blockPosIn, hM blockMaterial) {
      int i = 0;
      float f = 0.0F;

      for(int j = 0; j < 4; ++j) {
         BlockPos blockpos = blockPosIn.add(-(j & 1), 0, -(j >> 1 & 1));
         if (blockAccess.getBlockState(blockpos.up()).getMaterial() == blockMaterial) {
            return 1.0F;
         }

         in iblockstate = blockAccess.getBlockState(blockpos);
         hM material = iblockstate.getMaterial();
         if (material != blockMaterial) {
            if (!material.isSolid()) {
               ++f;
               ++i;
            }
         } else {
            int k = (Integer)iblockstate.getValue(eB.LEVEL);
            if (k >= 8 || k == 0) {
               f += eB.getLiquidHeightPercent(k) * 10.0F;
               i += 10;
            }

            f += eB.getLiquidHeightPercent(k);
            ++i;
         }
      }

      return 1.0F - f / (float)i;
   }
}
