package neo;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bjj {
   private static Map[] spriteQuadMaps = null;
   private static Map[] spriteQuadFullMaps = null;
   private static Map[][] spriteQuadCompactMaps = (Map[][])((Map[][])null);
   private static bjh[][] blockProperties = (bjh[][])((bjh[][])null);
   private static bjh[][] tileProperties = (bjh[][])((bjh[][])null);
   private static boolean multipass = false;
   protected static final int UNKNOWN = -1;
   protected static final int Y_NEG_DOWN = 0;
   protected static final int Y_POS_UP = 1;
   protected static final int Z_NEG_NORTH = 2;
   protected static final int Z_POS_SOUTH = 3;
   protected static final int X_NEG_WEST = 4;
   protected static final int X_POS_EAST = 5;
   private static final int Y_AXIS = 0;
   private static final int Z_AXIS = 1;
   private static final int X_AXIS = 2;
   public static final in AIR_DEFAULT_STATE;
   private static zd emptySprite;
   private static final biK[] SIDES_Y_NEG_DOWN;
   private static final biK[] SIDES_Y_POS_UP;
   private static final biK[] SIDES_Z_NEG_NORTH;
   private static final biK[] SIDES_Z_POS_SOUTH;
   private static final biK[] SIDES_X_NEG_WEST;
   private static final biK[] SIDES_X_POS_EAST;
   private static final biK[] SIDES_Z_NEG_NORTH_Z_AXIS;
   private static final biK[] SIDES_X_POS_EAST_X_AXIS;
   private static final biK[] EDGES_Y_NEG_DOWN;
   private static final biK[] EDGES_Y_POS_UP;
   private static final biK[] EDGES_Z_NEG_NORTH;
   private static final biK[] EDGES_Z_POS_SOUTH;
   private static final biK[] EDGES_X_NEG_WEST;
   private static final biK[] EDGES_X_POS_EAST;
   private static final biK[] EDGES_Z_NEG_NORTH_Z_AXIS;
   private static final biK[] EDGES_X_POS_EAST_X_AXIS;
   public static final zd SPRITE_DEFAULT;

   public bjj() {
   }

   public static rK[] getConnectedTexture(bfZ blockAccess, in blockState, BlockPos blockPos, rK quad, boa renderEnv) {
      zd textureatlassprite = quad.getSprite();
      if (textureatlassprite == null) {
         return renderEnv.getArrayQuadsCtm(quad);
      } else {
         co block = blockState.getBlock();
         if (skipConnectedTexture(blockAccess, blockState, blockPos, quad, renderEnv)) {
            quad = getQuad(emptySprite, quad);
            return renderEnv.getArrayQuadsCtm(quad);
         } else {
            EnumFacing enumfacing = quad.getFace();
            rK[] abakedquad = getConnectedTextureMultiPass(blockAccess, blockState, blockPos, enumfacing, quad, renderEnv);
            return abakedquad;
         }
      }
   }

   private static boolean skipConnectedTexture(bfZ blockAccess, in blockState, BlockPos blockPos, rK quad, boa renderEnv) {
      co block = blockState.getBlock();
      if (block instanceof fd) {
         EnumFacing enumfacing = quad.getFace();
         if (enumfacing != EnumFacing.UP && enumfacing != EnumFacing.DOWN) {
            return false;
         }

         if (!quad.isFaceQuad()) {
            return false;
         }

         BlockPos blockpos = blockPos.offset(quad.getFace());
         in iblockstate = blockAccess.getBlockState(blockpos);
         if (iblockstate.getBlock() != block) {
            return false;
         }

         if (block == Nk.STAINED_GLASS_PANE && iblockstate.getValue(gP.COLOR) != blockState.getValue(gP.COLOR)) {
            return false;
         }

         iblockstate = iblockstate.getActualState(blockAccess, blockpos);
         double d0 = (double)quad.getMidX();
         if (d0 < 0.4) {
            if ((Boolean)iblockstate.getValue(fd.WEST)) {
               return true;
            }
         } else if (d0 > 0.6) {
            if ((Boolean)iblockstate.getValue(fd.EAST)) {
               return true;
            }
         } else {
            double d1 = quad.getMidZ();
            if (d1 < 0.4) {
               if ((Boolean)iblockstate.getValue(fd.NORTH)) {
                  return true;
               }
            } else {
               if (d1 <= 0.6) {
                  return true;
               }

               if ((Boolean)iblockstate.getValue(fd.SOUTH)) {
                  return true;
               }
            }
         }
      }

      return false;
   }

   protected static rK[] getQuads(zd sprite, rK quadIn, boa renderEnv) {
      if (sprite == null) {
         return null;
      } else if (sprite == SPRITE_DEFAULT) {
         return renderEnv.getArrayQuadsCtm(quadIn);
      } else {
         rK bakedquad = getQuad(sprite, quadIn);
         rK[] abakedquad = renderEnv.getArrayQuadsCtm(bakedquad);
         return abakedquad;
      }
   }

   private static synchronized rK getQuad(zd sprite, rK quadIn) {
      if (spriteQuadMaps == null) {
         return quadIn;
      } else {
         int i = sprite.getIndexInMap();
         if (i >= 0 && i < spriteQuadMaps.length) {
            Map map = spriteQuadMaps[i];
            if (map == null) {
               map = new IdentityHashMap(1);
               spriteQuadMaps[i] = (Map)map;
            }

            rK bakedquad = (rK)((Map)map).get(quadIn);
            if (bakedquad == null) {
               bakedquad = makeSpriteQuad(quadIn, sprite);
               ((Map)map).put(quadIn, bakedquad);
            }

            return bakedquad;
         } else {
            return quadIn;
         }
      }
   }

   private static synchronized rK getQuadFull(zd sprite, rK quadIn, int tintIndex) {
      if (spriteQuadFullMaps == null) {
         return null;
      } else if (sprite == null) {
         return null;
      } else {
         int i = sprite.getIndexInMap();
         if (i >= 0 && i < spriteQuadFullMaps.length) {
            Map map = spriteQuadFullMaps[i];
            if (map == null) {
               map = new EnumMap(EnumFacing.class);
               spriteQuadFullMaps[i] = (Map)map;
            }

            EnumFacing enumfacing = quadIn.getFace();
            rK bakedquad = (rK)((Map)map).get(enumfacing);
            if (bakedquad == null) {
               bakedquad = bnc.makeBakedQuad(enumfacing, sprite, tintIndex);
               ((Map)map).put(enumfacing, bakedquad);
            }

            return bakedquad;
         } else {
            return null;
         }
      }
   }

   private static rK makeSpriteQuad(rK quad, zd sprite) {
      int[] aint = (int[])((int[])quad.getVertexData().clone());
      zd textureatlassprite = quad.getSprite();

      for(int i = 0; i < 4; ++i) {
         fixVertex(aint, i, textureatlassprite, sprite);
      }

      rK bakedquad = new rK(aint, quad.getTintIndex(), quad.getFace(), sprite);
      return bakedquad;
   }

   private static void fixVertex(int[] data, int vertex, zd spriteFrom, zd spriteTo) {
      int i = data.length / 4;
      int j = i * vertex;
      float f = Float.intBitsToFloat(data[j + 4]);
      float f1 = Float.intBitsToFloat(data[j + 4 + 1]);
      double d0 = spriteFrom.getSpriteU16(f);
      double d1 = spriteFrom.getSpriteV16(f1);
      data[j + 4] = Float.floatToRawIntBits(spriteTo.getInterpolatedU(d0));
      data[j + 4 + 1] = Float.floatToRawIntBits(spriteTo.getInterpolatedV(d1));
   }

   private static rK[] getConnectedTextureMultiPass(bfZ blockAccess, in blockState, BlockPos blockPos, EnumFacing side, rK quad, boa renderEnv) {
      rK[] abakedquad = getConnectedTextureSingle(blockAccess, blockState, blockPos, side, quad, true, 0, renderEnv);
      if (!multipass) {
         return abakedquad;
      } else if (abakedquad.length == 1 && abakedquad[0] == quad) {
         return abakedquad;
      } else {
         List<rK> list = renderEnv.getListQuadsCtmMultipass(abakedquad);

         int i;
         for(i = 0; i < list.size(); ++i) {
            rK bakedquad = (rK)list.get(i);
            rK bakedquad1 = bakedquad;

            for(int j = 0; j < 3; ++j) {
               rK[] abakedquad1 = getConnectedTextureSingle(blockAccess, blockState, blockPos, side, bakedquad1, false, j + 1, renderEnv);
               if (abakedquad1.length != 1 || abakedquad1[0] == bakedquad1) {
                  break;
               }

               bakedquad1 = abakedquad1[0];
            }

            list.set(i, bakedquad1);
         }

         for(i = 0; i < abakedquad.length; ++i) {
            abakedquad[i] = (rK)list.get(i);
         }

         return abakedquad;
      }
   }

   public static rK[] getConnectedTextureSingle(bfZ blockAccess, in blockState, BlockPos blockPos, EnumFacing facing, rK quad, boolean checkBlocks, int pass, boa renderEnv) {
      co block = blockState.getBlock();
      if (!(blockState instanceof ie)) {
         return renderEnv.getArrayQuadsCtm(quad);
      } else {
         ie blockstatebase = (ie)blockState;
         zd textureatlassprite = quad.getSprite();
         int l;
         bjh[] aconnectedproperties1;
         int i1;
         int j1;
         bjh connectedproperties1;
         rK[] abakedquad1;
         if (tileProperties != null) {
            l = textureatlassprite.getIndexInMap();
            if (l >= 0 && l < tileProperties.length) {
               aconnectedproperties1 = tileProperties[l];
               if (aconnectedproperties1 != null) {
                  i1 = getSide(facing);

                  for(j1 = 0; j1 < aconnectedproperties1.length; ++j1) {
                     connectedproperties1 = aconnectedproperties1[j1];
                     if (connectedproperties1 != null && connectedproperties1.matchesBlockId(blockstatebase.getBlockId())) {
                        abakedquad1 = getConnectedTexture(connectedproperties1, blockAccess, blockstatebase, blockPos, i1, quad, pass, renderEnv);
                        if (abakedquad1 != null) {
                           return abakedquad1;
                        }
                     }
                  }
               }
            }
         }

         if (blockProperties != null && checkBlocks) {
            l = renderEnv.getBlockId();
            if (l >= 0 && l < blockProperties.length) {
               aconnectedproperties1 = blockProperties[l];
               if (aconnectedproperties1 != null) {
                  i1 = getSide(facing);

                  for(j1 = 0; j1 < aconnectedproperties1.length; ++j1) {
                     connectedproperties1 = aconnectedproperties1[j1];
                     if (connectedproperties1 != null && connectedproperties1.matchesIcon(textureatlassprite)) {
                        abakedquad1 = getConnectedTexture(connectedproperties1, blockAccess, blockstatebase, blockPos, i1, quad, pass, renderEnv);
                        if (abakedquad1 != null) {
                           return abakedquad1;
                        }
                     }
                  }
               }
            }
         }

         return renderEnv.getArrayQuadsCtm(quad);
      }
   }

   public static int getSide(EnumFacing facing) {
      if (facing == null) {
         return -1;
      } else {
         switch (facing) {
            case DOWN:
               return 0;
            case UP:
               return 1;
            case EAST:
               return 5;
            case WEST:
               return 4;
            case NORTH:
               return 2;
            case SOUTH:
               return 3;
            default:
               return -1;
         }
      }
   }

   private static EnumFacing getFacing(int side) {
      switch (side) {
         case 0:
            return EnumFacing.DOWN;
         case 1:
            return EnumFacing.UP;
         case 2:
            return EnumFacing.NORTH;
         case 3:
            return EnumFacing.SOUTH;
         case 4:
            return EnumFacing.WEST;
         case 5:
            return EnumFacing.EAST;
         default:
            return EnumFacing.UP;
      }
   }

   private static rK[] getConnectedTexture(bjh cp, bfZ blockAccess, ie blockState, BlockPos blockPos, int side, rK quad, int pass, boa renderEnv) {
      int i = 0;
      int j = blockState.getMetadata();
      int k = j;
      co block = blockState.getBlock();
      if (block instanceof gi) {
         i = getWoodAxis(side, j);
         if (cp.getMetadataMax() <= 3) {
            k = j & 3;
         }
      }

      if (block instanceof fE) {
         i = getQuartzAxis(side, j);
         if (cp.getMetadataMax() <= 2 && k > 2) {
            k = 2;
         }
      }

      if (!cp.matchesBlock(blockState.getBlockId(), k)) {
         return null;
      } else {
         int l;
         if (side >= 0 && cp.faces != 63) {
            l = side;
            if (i != 0) {
               l = fixSideByAxis(side, i);
            }

            if ((1 << l & cp.faces) == 0) {
               return null;
            }
         }

         l = blockPos.getY();
         if (cp.heights != null && !cp.heights.isInRange(l)) {
            return null;
         } else {
            if (cp.biomes != null) {
               Zi biome = blockAccess.getBiome(blockPos);
               if (!cp.matchesBiome(biome)) {
                  return null;
               }
            }

            if (cp.nbtName != null) {
               String s = bqT.getTileEntityName(blockAccess, blockPos);
               if (!cp.nbtName.matchesValue(s)) {
                  return null;
               }
            }

            zd textureatlassprite = quad.getSprite();
            switch (cp.method) {
               case 1:
                  return getQuads(getConnectedTextureCtm(cp, blockAccess, blockState, blockPos, i, side, textureatlassprite, j, renderEnv), quad, renderEnv);
               case 2:
                  return getQuads(getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, i, side, textureatlassprite, j), quad, renderEnv);
               case 3:
                  return getQuads(getConnectedTextureTop(cp, blockAccess, blockState, blockPos, i, side, textureatlassprite, j), quad, renderEnv);
               case 4:
                  return getQuads(getConnectedTextureRandom(cp, blockAccess, blockState, blockPos, side), quad, renderEnv);
               case 5:
                  return getQuads(getConnectedTextureRepeat(cp, blockPos, side), quad, renderEnv);
               case 6:
                  return getQuads(getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, i, side, textureatlassprite, j), quad, renderEnv);
               case 7:
                  return getQuads(getConnectedTextureFixed(cp), quad, renderEnv);
               case 8:
                  return getQuads(getConnectedTextureHorizontalVertical(cp, blockAccess, blockState, blockPos, i, side, textureatlassprite, j), quad, renderEnv);
               case 9:
                  return getQuads(getConnectedTextureVerticalHorizontal(cp, blockAccess, blockState, blockPos, i, side, textureatlassprite, j), quad, renderEnv);
               case 10:
                  if (pass == 0) {
                     return getConnectedTextureCtmCompact(cp, blockAccess, blockState, blockPos, i, side, quad, j, renderEnv);
                  }
               default:
                  return null;
               case 11:
                  return getConnectedTextureOverlay(cp, blockAccess, blockState, blockPos, i, side, quad, j, renderEnv);
               case 12:
                  return getConnectedTextureOverlayFixed(cp, quad, renderEnv);
               case 13:
                  return getConnectedTextureOverlayRandom(cp, blockAccess, blockState, blockPos, side, quad, renderEnv);
               case 14:
                  return getConnectedTextureOverlayRepeat(cp, blockPos, side, quad, renderEnv);
               case 15:
                  return getConnectedTextureOverlayCtm(cp, blockAccess, blockState, blockPos, i, side, quad, j, renderEnv);
            }
         }
      }
   }

   private static int fixSideByAxis(int side, int vertAxis) {
      switch (vertAxis) {
         case 0:
            return side;
         case 1:
            switch (side) {
               case 0:
                  return 2;
               case 1:
                  return 3;
               case 2:
                  return 1;
               case 3:
                  return 0;
               default:
                  return side;
            }
         case 2:
            switch (side) {
               case 0:
                  return 4;
               case 1:
                  return 5;
               case 2:
               case 3:
               default:
                  return side;
               case 4:
                  return 1;
               case 5:
                  return 0;
            }
         default:
            return side;
      }
   }

   private static int getWoodAxis(int side, int metadata) {
      int i = (metadata & 12) >> 2;
      switch (i) {
         case 1:
            return 2;
         case 2:
            return 1;
         default:
            return 0;
      }
   }

   private static int getQuartzAxis(int side, int metadata) {
      switch (metadata) {
         case 3:
            return 2;
         case 4:
            return 1;
         default:
            return 0;
      }
   }

   private static zd getConnectedTextureRandom(bjh cp, bfZ blockAccess, ie blockState, BlockPos blockPos, int side) {
      if (cp.tileIcons.length == 1) {
         return cp.tileIcons[0];
      } else {
         int i = side / cp.symmetry * cp.symmetry;
         if (cp.linked) {
            BlockPos blockpos = blockPos.down();

            for(in iblockstate = blockAccess.getBlockState(blockpos); iblockstate.getBlock() == blockState.getBlock(); iblockstate = blockAccess.getBlockState(blockpos)) {
               blockPos = blockpos;
               blockpos = blockpos.down();
               if (blockpos.getY() < 0) {
                  break;
               }
            }
         }

         int l = XH.getRandom(blockPos, i) & Integer.MAX_VALUE;

         int j1;
         for(j1 = 0; j1 < cp.randomLoops; ++j1) {
            l = XH.intHash(l);
         }

         j1 = 0;
         if (cp.weights == null) {
            j1 = l % cp.tileIcons.length;
         } else {
            int j = l % cp.sumAllWeights;
            int[] aint = cp.sumWeights;

            for(int k = 0; k < aint.length; ++k) {
               if (j < aint[k]) {
                  j1 = k;
                  break;
               }
            }
         }

         return cp.tileIcons[j1];
      }
   }

   private static zd getConnectedTextureFixed(bjh cp) {
      return cp.tileIcons[0];
   }

   private static zd getConnectedTextureRepeat(bjh cp, BlockPos blockPos, int side) {
      if (cp.tileIcons.length == 1) {
         return cp.tileIcons[0];
      } else {
         int i = blockPos.getX();
         int j = blockPos.getY();
         int k = blockPos.getZ();
         int l = 0;
         int i1 = 0;
         switch (side) {
            case 0:
               l = i;
               i1 = -k - 1;
               break;
            case 1:
               l = i;
               i1 = k;
               break;
            case 2:
               l = -i - 1;
               i1 = -j;
               break;
            case 3:
               l = i;
               i1 = -j;
               break;
            case 4:
               l = k;
               i1 = -j;
               break;
            case 5:
               l = -k - 1;
               i1 = -j;
         }

         l %= cp.width;
         i1 %= cp.height;
         if (l < 0) {
            l += cp.width;
         }

         if (i1 < 0) {
            i1 += cp.height;
         }

         int j1 = i1 * cp.width + l;
         return cp.tileIcons[j1];
      }
   }

   private static zd getConnectedTextureCtm(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, zd icon, int metadata, boa renderEnv) {
      int i = getConnectedTextureCtmIndex(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata, renderEnv);
      return cp.tileIcons[i];
   }

   private static synchronized rK[] getConnectedTextureCtmCompact(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, rK quad, int metadata, boa renderEnv) {
      zd textureatlassprite = quad.getSprite();
      int i = getConnectedTextureCtmIndex(cp, blockAccess, blockState, blockPos, vertAxis, side, textureatlassprite, metadata, renderEnv);
      return bjm.getConnectedTextureCtmCompact(i, cp, side, quad, renderEnv);
   }

   private static rK[] getConnectedTextureOverlay(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, rK quad, int metadata, boa renderEnv) {
      if (!quad.isFullQuad()) {
         return null;
      } else {
         zd textureatlassprite = quad.getSprite();
         biK[] ablockdir = getSideDirections(side, vertAxis);
         boolean[] aboolean = renderEnv.getBorderFlags();

         for(int i = 0; i < 4; ++i) {
            aboolean[i] = isNeighbourOverlay(cp, blockAccess, blockState, ablockdir[i].offset(blockPos), side, textureatlassprite, metadata);
         }

         bnd listquadsoverlay = renderEnv.getListQuadsOverlay(cp.layer);

         rK[] var14;
         try {
            Object dirEdges;
            if (aboolean[0] && aboolean[1] && aboolean[2] && aboolean[3]) {
               listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[8], quad, cp.tintIndex), cp.tintBlockState);
               dirEdges = null;
               return (rK[])((rK[])dirEdges);
            }

            if (aboolean[0] && aboolean[1] && aboolean[2]) {
               listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[5], quad, cp.tintIndex), cp.tintBlockState);
               dirEdges = null;
               var14 = (rK[])((rK[])dirEdges);
               return var14;
            }

            if (aboolean[0] && aboolean[2] && aboolean[3]) {
               listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[6], quad, cp.tintIndex), cp.tintBlockState);
               dirEdges = null;
               var14 = (rK[])((rK[])dirEdges);
               return var14;
            }

            if (aboolean[1] && aboolean[2] && aboolean[3]) {
               listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[12], quad, cp.tintIndex), cp.tintBlockState);
               dirEdges = null;
               var14 = (rK[])((rK[])dirEdges);
               return var14;
            }

            if (!aboolean[0] || !aboolean[1] || !aboolean[3]) {
               biK[] ablockdir1 = getEdgeDirections(side, vertAxis);
               boolean[] aboolean1 = renderEnv.getBorderFlags2();

               for(int j = 0; j < 4; ++j) {
                  aboolean1[j] = isNeighbourOverlay(cp, blockAccess, blockState, ablockdir1[j].offset(blockPos), side, textureatlassprite, metadata);
               }

               Object object1;
               rK[] object5;
               if (aboolean[1] && aboolean[2]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[3], quad, cp.tintIndex), cp.tintBlockState);
                  if (aboolean1[3]) {
                     listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[16], quad, cp.tintIndex), cp.tintBlockState);
                  }

                  object1 = null;
                  object5 = (rK[])((rK[])object1);
                  return object5;
               }

               if (aboolean[0] && aboolean[2]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[4], quad, cp.tintIndex), cp.tintBlockState);
                  if (aboolean1[2]) {
                     listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[14], quad, cp.tintIndex), cp.tintBlockState);
                  }

                  object1 = null;
                  object5 = (rK[])((rK[])object1);
                  return object5;
               }

               if (aboolean[1] && aboolean[3]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[10], quad, cp.tintIndex), cp.tintBlockState);
                  if (aboolean1[1]) {
                     listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[2], quad, cp.tintIndex), cp.tintBlockState);
                  }

                  object1 = null;
                  object5 = (rK[])((rK[])object1);
                  return object5;
               }

               if (aboolean[0] && aboolean[3]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[11], quad, cp.tintIndex), cp.tintBlockState);
                  if (aboolean1[0]) {
                     listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[0], quad, cp.tintIndex), cp.tintBlockState);
                  }

                  object1 = null;
                  object5 = (rK[])((rK[])object1);
                  return object5;
               }

               boolean[] aboolean2 = renderEnv.getBorderFlags3();

               for(int k = 0; k < 4; ++k) {
                  aboolean2[k] = isNeighbourMatching(cp, blockAccess, blockState, ablockdir[k].offset(blockPos), side, textureatlassprite, metadata);
               }

               if (aboolean[0]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[9], quad, cp.tintIndex), cp.tintBlockState);
               }

               if (aboolean[1]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[7], quad, cp.tintIndex), cp.tintBlockState);
               }

               if (aboolean[2]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[1], quad, cp.tintIndex), cp.tintBlockState);
               }

               if (aboolean[3]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[15], quad, cp.tintIndex), cp.tintBlockState);
               }

               if (aboolean1[0] && (aboolean2[1] || aboolean2[2]) && !aboolean[1] && !aboolean[2]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[0], quad, cp.tintIndex), cp.tintBlockState);
               }

               if (aboolean1[1] && (aboolean2[0] || aboolean2[2]) && !aboolean[0] && !aboolean[2]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[2], quad, cp.tintIndex), cp.tintBlockState);
               }

               if (aboolean1[2] && (aboolean2[1] || aboolean2[3]) && !aboolean[1] && !aboolean[3]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[14], quad, cp.tintIndex), cp.tintBlockState);
               }

               if (aboolean1[3] && (aboolean2[0] || aboolean2[3]) && !aboolean[0] && !aboolean[3]) {
                  listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[16], quad, cp.tintIndex), cp.tintBlockState);
               }

               object5 = null;
               rK[] var18 = (rK[])((rK[])object5);
               return var18;
            }

            listquadsoverlay.addQuad(getQuadFull(cp.tileIcons[13], quad, cp.tintIndex), cp.tintBlockState);
            dirEdges = null;
            var14 = (rK[])((rK[])dirEdges);
         } finally {
            if (listquadsoverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }

         }

         return var14;
      }
   }

   private static rK[] getConnectedTextureOverlayFixed(bjh cp, rK quad, boa renderEnv) {
      if (!quad.isFullQuad()) {
         return null;
      } else {
         bnd listquadsoverlay = renderEnv.getListQuadsOverlay(cp.layer);

         Object object;
         try {
            zd textureatlassprite = getConnectedTextureFixed(cp);
            if (textureatlassprite != null) {
               listquadsoverlay.addQuad(getQuadFull(textureatlassprite, quad, cp.tintIndex), cp.tintBlockState);
            }

            object = null;
         } finally {
            if (listquadsoverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }

         }

         return (rK[])((rK[])object);
      }
   }

   private static rK[] getConnectedTextureOverlayRandom(bjh cp, bfZ blockAccess, ie blockState, BlockPos blockPos, int side, rK quad, boa renderEnv) {
      if (!quad.isFullQuad()) {
         return null;
      } else {
         bnd listquadsoverlay = renderEnv.getListQuadsOverlay(cp.layer);

         Object object;
         try {
            zd textureatlassprite = getConnectedTextureRandom(cp, blockAccess, blockState, blockPos, side);
            if (textureatlassprite != null) {
               listquadsoverlay.addQuad(getQuadFull(textureatlassprite, quad, cp.tintIndex), cp.tintBlockState);
            }

            object = null;
         } finally {
            if (listquadsoverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }

         }

         return (rK[])((rK[])object);
      }
   }

   private static rK[] getConnectedTextureOverlayRepeat(bjh cp, BlockPos blockPos, int side, rK quad, boa renderEnv) {
      if (!quad.isFullQuad()) {
         return null;
      } else {
         bnd listquadsoverlay = renderEnv.getListQuadsOverlay(cp.layer);

         Object object;
         try {
            zd textureatlassprite = getConnectedTextureRepeat(cp, blockPos, side);
            if (textureatlassprite != null) {
               listquadsoverlay.addQuad(getQuadFull(textureatlassprite, quad, cp.tintIndex), cp.tintBlockState);
            }

            object = null;
         } finally {
            if (listquadsoverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }

         }

         return (rK[])((rK[])object);
      }
   }

   private static rK[] getConnectedTextureOverlayCtm(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, rK quad, int metadata, boa renderEnv) {
      if (!quad.isFullQuad()) {
         return null;
      } else {
         bnd listquadsoverlay = renderEnv.getListQuadsOverlay(cp.layer);

         Object object;
         try {
            zd textureatlassprite = getConnectedTextureCtm(cp, blockAccess, blockState, blockPos, vertAxis, side, quad.getSprite(), metadata, renderEnv);
            if (textureatlassprite != null) {
               listquadsoverlay.addQuad(getQuadFull(textureatlassprite, quad, cp.tintIndex), cp.tintBlockState);
            }

            object = null;
         } finally {
            if (listquadsoverlay.size() > 0) {
               renderEnv.setOverlaysRendered(true);
            }

         }

         return (rK[])((rK[])object);
      }
   }

   private static biK[] getSideDirections(int side, int vertAxis) {
      switch (side) {
         case 0:
            return SIDES_Y_NEG_DOWN;
         case 1:
            return SIDES_Y_POS_UP;
         case 2:
            if (vertAxis == 1) {
               return SIDES_Z_NEG_NORTH_Z_AXIS;
            }

            return SIDES_Z_NEG_NORTH;
         case 3:
            return SIDES_Z_POS_SOUTH;
         case 4:
            return SIDES_X_NEG_WEST;
         case 5:
            if (vertAxis == 2) {
               return SIDES_X_POS_EAST_X_AXIS;
            }

            return SIDES_X_POS_EAST;
         default:
            throw new IllegalArgumentException("Unknown side: " + side);
      }
   }

   private static biK[] getEdgeDirections(int side, int vertAxis) {
      switch (side) {
         case 0:
            return EDGES_Y_NEG_DOWN;
         case 1:
            return EDGES_Y_POS_UP;
         case 2:
            if (vertAxis == 1) {
               return EDGES_Z_NEG_NORTH_Z_AXIS;
            }

            return EDGES_Z_NEG_NORTH;
         case 3:
            return EDGES_Z_POS_SOUTH;
         case 4:
            return EDGES_X_NEG_WEST;
         case 5:
            if (vertAxis == 2) {
               return EDGES_X_POS_EAST_X_AXIS;
            }

            return EDGES_X_POS_EAST;
         default:
            throw new IllegalArgumentException("Unknown side: " + side);
      }
   }

   protected static Map[][] getSpriteQuadCompactMaps() {
      return spriteQuadCompactMaps;
   }

   private static int getConnectedTextureCtmIndex(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, zd icon, int metadata, boa renderEnv) {
      boolean[] aboolean = renderEnv.getBorderFlags();
      BlockPos blockpos;
      switch (side) {
         case 0:
            aboolean[0] = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
            aboolean[1] = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
            aboolean[2] = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
            aboolean[3] = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
            if (cp.innerSeams) {
               blockpos = blockPos.down();
               aboolean[0] = aboolean[0] && !isNeighbour(cp, blockAccess, blockState, blockpos.west(), side, icon, metadata);
               aboolean[1] = aboolean[1] && !isNeighbour(cp, blockAccess, blockState, blockpos.east(), side, icon, metadata);
               aboolean[2] = aboolean[2] && !isNeighbour(cp, blockAccess, blockState, blockpos.north(), side, icon, metadata);
               aboolean[3] = aboolean[3] && !isNeighbour(cp, blockAccess, blockState, blockpos.south(), side, icon, metadata);
            }
            break;
         case 1:
            aboolean[0] = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
            aboolean[1] = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
            aboolean[2] = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
            aboolean[3] = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
            if (cp.innerSeams) {
               blockpos = blockPos.up();
               aboolean[0] = aboolean[0] && !isNeighbour(cp, blockAccess, blockState, blockpos.west(), side, icon, metadata);
               aboolean[1] = aboolean[1] && !isNeighbour(cp, blockAccess, blockState, blockpos.east(), side, icon, metadata);
               aboolean[2] = aboolean[2] && !isNeighbour(cp, blockAccess, blockState, blockpos.south(), side, icon, metadata);
               aboolean[3] = aboolean[3] && !isNeighbour(cp, blockAccess, blockState, blockpos.north(), side, icon, metadata);
            }
            break;
         case 2:
            aboolean[0] = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
            aboolean[1] = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
            aboolean[2] = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
            aboolean[3] = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            if (cp.innerSeams) {
               blockpos = blockPos.north();
               aboolean[0] = aboolean[0] && !isNeighbour(cp, blockAccess, blockState, blockpos.east(), side, icon, metadata);
               aboolean[1] = aboolean[1] && !isNeighbour(cp, blockAccess, blockState, blockpos.west(), side, icon, metadata);
               aboolean[2] = aboolean[2] && !isNeighbour(cp, blockAccess, blockState, blockpos.down(), side, icon, metadata);
               aboolean[3] = aboolean[3] && !isNeighbour(cp, blockAccess, blockState, blockpos.up(), side, icon, metadata);
            }

            if (vertAxis == 1) {
               switchValues(0, 1, aboolean);
               switchValues(2, 3, aboolean);
            }
            break;
         case 3:
            aboolean[0] = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
            aboolean[1] = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
            aboolean[2] = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
            aboolean[3] = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            if (cp.innerSeams) {
               blockpos = blockPos.south();
               aboolean[0] = aboolean[0] && !isNeighbour(cp, blockAccess, blockState, blockpos.west(), side, icon, metadata);
               aboolean[1] = aboolean[1] && !isNeighbour(cp, blockAccess, blockState, blockpos.east(), side, icon, metadata);
               aboolean[2] = aboolean[2] && !isNeighbour(cp, blockAccess, blockState, blockpos.down(), side, icon, metadata);
               aboolean[3] = aboolean[3] && !isNeighbour(cp, blockAccess, blockState, blockpos.up(), side, icon, metadata);
            }
            break;
         case 4:
            aboolean[0] = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
            aboolean[1] = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
            aboolean[2] = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
            aboolean[3] = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            if (cp.innerSeams) {
               blockpos = blockPos.west();
               aboolean[0] = aboolean[0] && !isNeighbour(cp, blockAccess, blockState, blockpos.north(), side, icon, metadata);
               aboolean[1] = aboolean[1] && !isNeighbour(cp, blockAccess, blockState, blockpos.south(), side, icon, metadata);
               aboolean[2] = aboolean[2] && !isNeighbour(cp, blockAccess, blockState, blockpos.down(), side, icon, metadata);
               aboolean[3] = aboolean[3] && !isNeighbour(cp, blockAccess, blockState, blockpos.up(), side, icon, metadata);
            }
            break;
         case 5:
            aboolean[0] = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
            aboolean[1] = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
            aboolean[2] = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
            aboolean[3] = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            if (cp.innerSeams) {
               blockpos = blockPos.east();
               aboolean[0] = aboolean[0] && !isNeighbour(cp, blockAccess, blockState, blockpos.south(), side, icon, metadata);
               aboolean[1] = aboolean[1] && !isNeighbour(cp, blockAccess, blockState, blockpos.north(), side, icon, metadata);
               aboolean[2] = aboolean[2] && !isNeighbour(cp, blockAccess, blockState, blockpos.down(), side, icon, metadata);
               aboolean[3] = aboolean[3] && !isNeighbour(cp, blockAccess, blockState, blockpos.up(), side, icon, metadata);
            }

            if (vertAxis == 2) {
               switchValues(0, 1, aboolean);
               switchValues(2, 3, aboolean);
            }
      }

      int i = 0;
      if (aboolean[0] & !aboolean[1] & !aboolean[2] & !aboolean[3]) {
         i = 3;
      } else if (!aboolean[0] & aboolean[1] & !aboolean[2] & !aboolean[3]) {
         i = 1;
      } else if (!aboolean[0] & !aboolean[1] & aboolean[2] & !aboolean[3]) {
         i = 12;
      } else if (!aboolean[0] & !aboolean[1] & !aboolean[2] & aboolean[3]) {
         i = 36;
      } else if (aboolean[0] & aboolean[1] & !aboolean[2] & !aboolean[3]) {
         i = 2;
      } else if (!aboolean[0] & !aboolean[1] & aboolean[2] & aboolean[3]) {
         i = 24;
      } else if (aboolean[0] & !aboolean[1] & aboolean[2] & !aboolean[3]) {
         i = 15;
      } else if (aboolean[0] & !aboolean[1] & !aboolean[2] & aboolean[3]) {
         i = 39;
      } else if (!aboolean[0] & aboolean[1] & aboolean[2] & !aboolean[3]) {
         i = 13;
      } else if (!aboolean[0] & aboolean[1] & !aboolean[2] & aboolean[3]) {
         i = 37;
      } else if (!aboolean[0] & aboolean[1] & aboolean[2] & aboolean[3]) {
         i = 25;
      } else if (aboolean[0] & !aboolean[1] & aboolean[2] & aboolean[3]) {
         i = 27;
      } else if (aboolean[0] & aboolean[1] & !aboolean[2] & aboolean[3]) {
         i = 38;
      } else if (aboolean[0] & aboolean[1] & aboolean[2] & !aboolean[3]) {
         i = 14;
      } else if (aboolean[0] & aboolean[1] & aboolean[2] & aboolean[3]) {
         i = 26;
      }

      if (i == 0) {
         return i;
      } else if (!XH.isConnectedTexturesFancy()) {
         return i;
      } else {
         BlockPos blockpos1;
         switch (side) {
            case 0:
               aboolean[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().north(), side, icon, metadata);
               aboolean[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().north(), side, icon, metadata);
               aboolean[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().south(), side, icon, metadata);
               aboolean[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().south(), side, icon, metadata);
               if (cp.innerSeams) {
                  blockpos1 = blockPos.down();
                  aboolean[0] = aboolean[0] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().north(), side, icon, metadata);
                  aboolean[1] = aboolean[1] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().north(), side, icon, metadata);
                  aboolean[2] = aboolean[2] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().south(), side, icon, metadata);
                  aboolean[3] = aboolean[3] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().south(), side, icon, metadata);
               }
               break;
            case 1:
               aboolean[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().south(), side, icon, metadata);
               aboolean[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().south(), side, icon, metadata);
               aboolean[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().north(), side, icon, metadata);
               aboolean[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().north(), side, icon, metadata);
               if (cp.innerSeams) {
                  blockpos1 = blockPos.up();
                  aboolean[0] = aboolean[0] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().south(), side, icon, metadata);
                  aboolean[1] = aboolean[1] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().south(), side, icon, metadata);
                  aboolean[2] = aboolean[2] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().north(), side, icon, metadata);
                  aboolean[3] = aboolean[3] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().north(), side, icon, metadata);
               }
               break;
            case 2:
               aboolean[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().down(), side, icon, metadata);
               aboolean[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().down(), side, icon, metadata);
               aboolean[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().up(), side, icon, metadata);
               aboolean[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().up(), side, icon, metadata);
               if (cp.innerSeams) {
                  blockpos1 = blockPos.north();
                  aboolean[0] = aboolean[0] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().down(), side, icon, metadata);
                  aboolean[1] = aboolean[1] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().down(), side, icon, metadata);
                  aboolean[2] = aboolean[2] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().up(), side, icon, metadata);
                  aboolean[3] = aboolean[3] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().up(), side, icon, metadata);
               }

               if (vertAxis == 1) {
                  switchValues(0, 3, aboolean);
                  switchValues(1, 2, aboolean);
               }
               break;
            case 3:
               aboolean[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().down(), side, icon, metadata);
               aboolean[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().down(), side, icon, metadata);
               aboolean[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.east().up(), side, icon, metadata);
               aboolean[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.west().up(), side, icon, metadata);
               if (cp.innerSeams) {
                  blockpos1 = blockPos.south();
                  aboolean[0] = aboolean[0] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().down(), side, icon, metadata);
                  aboolean[1] = aboolean[1] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().down(), side, icon, metadata);
                  aboolean[2] = aboolean[2] || isNeighbour(cp, blockAccess, blockState, blockpos1.east().up(), side, icon, metadata);
                  aboolean[3] = aboolean[3] || isNeighbour(cp, blockAccess, blockState, blockpos1.west().up(), side, icon, metadata);
               }
               break;
            case 4:
               aboolean[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.down().south(), side, icon, metadata);
               aboolean[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.down().north(), side, icon, metadata);
               aboolean[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.up().south(), side, icon, metadata);
               aboolean[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.up().north(), side, icon, metadata);
               if (cp.innerSeams) {
                  blockpos1 = blockPos.west();
                  aboolean[0] = aboolean[0] || isNeighbour(cp, blockAccess, blockState, blockpos1.down().south(), side, icon, metadata);
                  aboolean[1] = aboolean[1] || isNeighbour(cp, blockAccess, blockState, blockpos1.down().north(), side, icon, metadata);
                  aboolean[2] = aboolean[2] || isNeighbour(cp, blockAccess, blockState, blockpos1.up().south(), side, icon, metadata);
                  aboolean[3] = aboolean[3] || isNeighbour(cp, blockAccess, blockState, blockpos1.up().north(), side, icon, metadata);
               }
               break;
            case 5:
               aboolean[0] = !isNeighbour(cp, blockAccess, blockState, blockPos.down().north(), side, icon, metadata);
               aboolean[1] = !isNeighbour(cp, blockAccess, blockState, blockPos.down().south(), side, icon, metadata);
               aboolean[2] = !isNeighbour(cp, blockAccess, blockState, blockPos.up().north(), side, icon, metadata);
               aboolean[3] = !isNeighbour(cp, blockAccess, blockState, blockPos.up().south(), side, icon, metadata);
               if (cp.innerSeams) {
                  blockpos1 = blockPos.east();
                  aboolean[0] = aboolean[0] || isNeighbour(cp, blockAccess, blockState, blockpos1.down().north(), side, icon, metadata);
                  aboolean[1] = aboolean[1] || isNeighbour(cp, blockAccess, blockState, blockpos1.down().south(), side, icon, metadata);
                  aboolean[2] = aboolean[2] || isNeighbour(cp, blockAccess, blockState, blockpos1.up().north(), side, icon, metadata);
                  aboolean[3] = aboolean[3] || isNeighbour(cp, blockAccess, blockState, blockpos1.up().south(), side, icon, metadata);
               }

               if (vertAxis == 2) {
                  switchValues(0, 3, aboolean);
                  switchValues(1, 2, aboolean);
               }
         }

         if (i == 13 && aboolean[0]) {
            i = 4;
         } else if (i == 15 && aboolean[1]) {
            i = 5;
         } else if (i == 37 && aboolean[2]) {
            i = 16;
         } else if (i == 39 && aboolean[3]) {
            i = 17;
         } else if (i == 14 && aboolean[0] && aboolean[1]) {
            i = 7;
         } else if (i == 25 && aboolean[0] && aboolean[2]) {
            i = 6;
         } else if (i == 27 && aboolean[3] && aboolean[1]) {
            i = 19;
         } else if (i == 38 && aboolean[3] && aboolean[2]) {
            i = 18;
         } else if (i == 14 && !aboolean[0] && aboolean[1]) {
            i = 31;
         } else if (i == 25 && aboolean[0] && !aboolean[2]) {
            i = 30;
         } else if (i == 27 && !aboolean[3] && aboolean[1]) {
            i = 41;
         } else if (i == 38 && aboolean[3] && !aboolean[2]) {
            i = 40;
         } else if (i == 14 && aboolean[0] && !aboolean[1]) {
            i = 29;
         } else if (i == 25 && !aboolean[0] && aboolean[2]) {
            i = 28;
         } else if (i == 27 && aboolean[3] && !aboolean[1]) {
            i = 43;
         } else if (i == 38 && !aboolean[3] && aboolean[2]) {
            i = 42;
         } else if (i == 26 && aboolean[0] && aboolean[1] && aboolean[2] && aboolean[3]) {
            i = 46;
         } else if (i == 26 && !aboolean[0] && aboolean[1] && aboolean[2] && aboolean[3]) {
            i = 9;
         } else if (i == 26 && aboolean[0] && !aboolean[1] && aboolean[2] && aboolean[3]) {
            i = 21;
         } else if (i == 26 && aboolean[0] && aboolean[1] && !aboolean[2] && aboolean[3]) {
            i = 8;
         } else if (i == 26 && aboolean[0] && aboolean[1] && aboolean[2] && !aboolean[3]) {
            i = 20;
         } else if (i == 26 && aboolean[0] && aboolean[1] && !aboolean[2] && !aboolean[3]) {
            i = 11;
         } else if (i == 26 && !aboolean[0] && !aboolean[1] && aboolean[2] && aboolean[3]) {
            i = 22;
         } else if (i == 26 && !aboolean[0] && aboolean[1] && !aboolean[2] && aboolean[3]) {
            i = 23;
         } else if (i == 26 && aboolean[0] && !aboolean[1] && aboolean[2] && !aboolean[3]) {
            i = 10;
         } else if (i == 26 && aboolean[0] && !aboolean[1] && !aboolean[2] && aboolean[3]) {
            i = 34;
         } else if (i == 26 && !aboolean[0] && aboolean[1] && aboolean[2] && !aboolean[3]) {
            i = 35;
         } else if (i == 26 && aboolean[0] && !aboolean[1] && !aboolean[2] && !aboolean[3]) {
            i = 32;
         } else if (i == 26 && !aboolean[0] && aboolean[1] && !aboolean[2] && !aboolean[3]) {
            i = 33;
         } else if (i == 26 && !aboolean[0] && !aboolean[1] && aboolean[2] && !aboolean[3]) {
            i = 44;
         } else if (i == 26 && !aboolean[0] && !aboolean[1] && !aboolean[2] && aboolean[3]) {
            i = 45;
         }

         return i;
      }
   }

   private static void switchValues(int ix1, int ix2, boolean[] arr) {
      boolean flag = arr[ix1];
      arr[ix1] = arr[ix2];
      arr[ix2] = flag;
   }

   private static boolean isNeighbourOverlay(bjh cp, bfZ iblockaccess, in blockState, BlockPos blockPos, int side, zd icon, int metadata) {
      in iblockstate = iblockaccess.getBlockState(blockPos);
      if (!isFullCubeModel(iblockstate)) {
         return false;
      } else {
         if (cp.connectBlocks != null) {
            ie blockstatebase = (ie)iblockstate;
            if (!bja.block(blockstatebase.getBlockId(), blockstatebase.getMetadata(), cp.connectBlocks)) {
               return false;
            }
         }

         if (cp.connectTileIcons != null) {
            zd textureatlassprite = getNeighbourIcon(iblockaccess, blockState, blockPos, iblockstate, side);
            if (!XH.isSameOne(textureatlassprite, cp.connectTileIcons)) {
               return false;
            }
         }

         in iblockstate1 = iblockaccess.getBlockState(blockPos.offset(getFacing(side)));
         if (iblockstate1.isOpaqueCube()) {
            return false;
         } else if (side == 1 && iblockstate1.getBlock() == Nk.SNOW_LAYER) {
            return false;
         } else {
            return !isNeighbour(cp, iblockaccess, blockState, blockPos, iblockstate, side, icon, metadata);
         }
      }
   }

   private static boolean isFullCubeModel(in state) {
      if (state.isFullCube()) {
         return true;
      } else {
         co block = state.getBlock();
         return block instanceof dZ ? true : block instanceof gN;
      }
   }

   private static boolean isNeighbourMatching(bjh cp, bfZ iblockaccess, in blockState, BlockPos blockPos, int side, zd icon, int metadata) {
      in iblockstate = iblockaccess.getBlockState(blockPos);
      if (iblockstate == AIR_DEFAULT_STATE) {
         return false;
      } else {
         if (cp.matchBlocks != null && iblockstate instanceof ie) {
            ie blockstatebase = (ie)iblockstate;
            if (!cp.matchesBlock(blockstatebase.getBlockId(), blockstatebase.getMetadata())) {
               return false;
            }
         }

         if (cp.matchTileIcons != null) {
            zd textureatlassprite = getNeighbourIcon(iblockaccess, blockState, blockPos, iblockstate, side);
            if (textureatlassprite != icon) {
               return false;
            }
         }

         in iblockstate1 = iblockaccess.getBlockState(blockPos.offset(getFacing(side)));
         if (iblockstate1.isOpaqueCube()) {
            return false;
         } else {
            return side != 1 || iblockstate1.getBlock() != Nk.SNOW_LAYER;
         }
      }
   }

   private static boolean isNeighbour(bjh cp, bfZ iblockaccess, in blockState, BlockPos blockPos, int side, zd icon, int metadata) {
      in iblockstate = iblockaccess.getBlockState(blockPos);
      return isNeighbour(cp, iblockaccess, blockState, blockPos, iblockstate, side, icon, metadata);
   }

   private static boolean isNeighbour(bjh cp, bfZ iblockaccess, in blockState, BlockPos blockPos, in neighbourState, int side, zd icon, int metadata) {
      if (blockState == neighbourState) {
         return true;
      } else if (cp.connect == 2) {
         if (neighbourState == null) {
            return false;
         } else if (neighbourState == AIR_DEFAULT_STATE) {
            return false;
         } else {
            zd textureatlassprite = getNeighbourIcon(iblockaccess, blockState, blockPos, neighbourState, side);
            return textureatlassprite == icon;
         }
      } else if (cp.connect == 3) {
         if (neighbourState == null) {
            return false;
         } else if (neighbourState == AIR_DEFAULT_STATE) {
            return false;
         } else {
            return neighbourState.getMaterial() == blockState.getMaterial();
         }
      } else if (!(neighbourState instanceof ie)) {
         return false;
      } else {
         ie blockstatebase = (ie)neighbourState;
         co block = blockstatebase.getBlock();
         int i = blockstatebase.getMetadata();
         return block == blockState.getBlock() && i == metadata;
      }
   }

   private static zd getNeighbourIcon(bfZ iblockaccess, in blockState, BlockPos blockPos, in neighbourState, int side) {
      neighbourState = neighbourState.getBlock().getActualState(neighbourState, iblockaccess, blockPos);
      sc ibakedmodel = nC.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes().getModelForState(neighbourState);
      if (ibakedmodel == null) {
         return null;
      } else {
         if (bnK.ForgeBlock_getExtendedState.exists()) {
            neighbourState = (in)bnK.call(neighbourState.getBlock(), bnK.ForgeBlock_getExtendedState, neighbourState, iblockaccess, blockPos);
         }

         EnumFacing enumfacing = getFacing(side);
         List list = ibakedmodel.getQuads(neighbourState, enumfacing, 0L);
         if (list == null) {
            return null;
         } else {
            if (XH.isBetterGrass()) {
               list = biI.getFaceQuads(iblockaccess, neighbourState, blockPos, enumfacing, list);
            }

            if (list.size() > 0) {
               rK bakedquad1 = (rK)list.get(0);
               return bakedquad1.getSprite();
            } else {
               List list1 = ibakedmodel.getQuads(neighbourState, (EnumFacing)null, 0L);
               if (list1 == null) {
                  return null;
               } else {
                  for(int i = 0; i < list1.size(); ++i) {
                     rK bakedquad = (rK)list1.get(i);
                     if (bakedquad.getFace() == enumfacing) {
                        return bakedquad.getSprite();
                     }
                  }

                  return null;
               }
            }
         }
      }
   }

   private static zd getConnectedTextureHorizontal(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, zd icon, int metadata) {
      boolean flag;
      boolean flag1;
      flag = false;
      flag1 = false;
      label49:
      switch (vertAxis) {
         case 0:
            switch (side) {
               case 0:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  break label49;
               case 1:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  break label49;
               case 2:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  break label49;
               case 3:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  break label49;
               case 4:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
                  break label49;
               case 5:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
               default:
                  break label49;
            }
         case 1:
            switch (side) {
               case 0:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  break label49;
               case 1:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  break label49;
               case 2:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  break label49;
               case 3:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
                  break label49;
               case 4:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
                  break label49;
               case 5:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
               default:
                  break label49;
            }
         case 2:
            switch (side) {
               case 0:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
                  break;
               case 1:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
                  break;
               case 2:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
                  break;
               case 3:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
                  break;
               case 4:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
                  break;
               case 5:
                  flag = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
                  flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
            }
      }

      int i = true;
      byte i;
      if (flag) {
         if (flag1) {
            i = 1;
         } else {
            i = 2;
         }
      } else if (flag1) {
         i = 0;
      } else {
         i = 3;
      }

      return cp.tileIcons[i];
   }

   private static zd getConnectedTextureVertical(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, zd icon, int metadata) {
      boolean flag = false;
      boolean flag1 = false;
      switch (vertAxis) {
         case 0:
            if (side == 1) {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
            } else if (side == 0) {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
            } else {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            }
            break;
         case 1:
            if (side == 3) {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            } else if (side == 2) {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
            } else {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.north(), side, icon, metadata);
            }
            break;
         case 2:
            if (side == 5) {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
            } else if (side == 4) {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.down(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            } else {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.west(), side, icon, metadata);
               flag1 = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
            }
      }

      int i = true;
      byte i;
      if (flag) {
         if (flag1) {
            i = 1;
         } else {
            i = 2;
         }
      } else if (flag1) {
         i = 0;
      } else {
         i = 3;
      }

      return cp.tileIcons[i];
   }

   private static zd getConnectedTextureHorizontalVertical(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, zd icon, int metadata) {
      zd[] atextureatlassprite = cp.tileIcons;
      zd textureatlassprite = getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
      if (textureatlassprite != null && textureatlassprite != icon && textureatlassprite != atextureatlassprite[3]) {
         return textureatlassprite;
      } else {
         zd textureatlassprite1 = getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
         if (textureatlassprite1 == atextureatlassprite[0]) {
            return atextureatlassprite[4];
         } else if (textureatlassprite1 == atextureatlassprite[1]) {
            return atextureatlassprite[5];
         } else {
            return textureatlassprite1 == atextureatlassprite[2] ? atextureatlassprite[6] : textureatlassprite1;
         }
      }
   }

   private static zd getConnectedTextureVerticalHorizontal(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, zd icon, int metadata) {
      zd[] atextureatlassprite = cp.tileIcons;
      zd textureatlassprite = getConnectedTextureVertical(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
      if (textureatlassprite != null && textureatlassprite != icon && textureatlassprite != atextureatlassprite[3]) {
         return textureatlassprite;
      } else {
         zd textureatlassprite1 = getConnectedTextureHorizontal(cp, blockAccess, blockState, blockPos, vertAxis, side, icon, metadata);
         if (textureatlassprite1 == atextureatlassprite[0]) {
            return atextureatlassprite[4];
         } else if (textureatlassprite1 == atextureatlassprite[1]) {
            return atextureatlassprite[5];
         } else {
            return textureatlassprite1 == atextureatlassprite[2] ? atextureatlassprite[6] : textureatlassprite1;
         }
      }
   }

   private static zd getConnectedTextureTop(bjh cp, bfZ blockAccess, in blockState, BlockPos blockPos, int vertAxis, int side, zd icon, int metadata) {
      boolean flag = false;
      switch (vertAxis) {
         case 0:
            if (side == 1 || side == 0) {
               return null;
            }

            flag = isNeighbour(cp, blockAccess, blockState, blockPos.up(), side, icon, metadata);
            break;
         case 1:
            if (side != 3 && side != 2) {
               flag = isNeighbour(cp, blockAccess, blockState, blockPos.south(), side, icon, metadata);
               break;
            }

            return null;
         case 2:
            if (side == 5 || side == 4) {
               return null;
            }

            flag = isNeighbour(cp, blockAccess, blockState, blockPos.east(), side, icon, metadata);
      }

      if (flag) {
         return cp.tileIcons[0];
      } else {
         return null;
      }
   }

   public static void updateIcons(zj textureMap) {
      blockProperties = (bjh[][])((bjh[][])null);
      tileProperties = (bjh[][])((bjh[][])null);
      spriteQuadMaps = null;
      spriteQuadCompactMaps = (Map[][])((Map[][])null);
      if (XH.isConnectedTextures()) {
         AC[] airesourcepack = XH.getResourcePacks();

         for(int i = airesourcepack.length - 1; i >= 0; --i) {
            AC iresourcepack = airesourcepack[i];
            updateIcons(textureMap, iresourcepack);
         }

         updateIcons(textureMap, XH.getDefaultResourcePack());
         ResourceLocation resourcelocation = new ResourceLocation("mcpatcher/ctm/default/empty");
         emptySprite = textureMap.registerSprite(resourcelocation);
         spriteQuadMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
         spriteQuadFullMaps = new Map[textureMap.getCountRegisteredSprites() + 1];
         spriteQuadCompactMaps = new Map[textureMap.getCountRegisteredSprites() + 1][];
         if (blockProperties.length <= 0) {
            blockProperties = (bjh[][])((bjh[][])null);
         }

         if (tileProperties.length <= 0) {
            tileProperties = (bjh[][])((bjh[][])null);
         }
      }

   }

   private static void updateIconEmpty(zj textureMap) {
   }

   public static void updateIcons(zj textureMap, AC rp) {
      String[] astring = bqN.collectFiles(rp, "mcpatcher/ctm/", ".properties", getDefaultCtmPaths());
      Arrays.sort((Object[])astring);
      List list = makePropertyList(tileProperties);
      List list1 = makePropertyList(blockProperties);

      for(int i = 0; i < astring.length; ++i) {
         String s = astring[i];
         XH.dbg("ConnectedTextures: " + s);

         try {
            ResourceLocation resourcelocation = new ResourceLocation(s);
            InputStream inputstream = rp.getInputStream(resourcelocation);
            if (inputstream == null) {
               XH.warn("ConnectedTextures file not found: " + s);
            } else {
               Properties properties = new bqL();
               ((Properties)properties).load(inputstream);
               inputstream.close();
               bjh connectedproperties = new bjh(properties, s);
               if (connectedproperties.isValid(s)) {
                  connectedproperties.updateIcons(textureMap);
                  addToTileList(connectedproperties, list);
                  addToBlockList(connectedproperties, list1);
               }
            }
         } catch (FileNotFoundException var11) {
            XH.warn("ConnectedTextures file not found: " + s);
         } catch (Exception var12) {
            Exception exception = var12;
            exception.printStackTrace();
         }
      }

      blockProperties = propertyListToArray(list1);
      tileProperties = propertyListToArray(list);
      multipass = detectMultipass();
      XH.dbg("Multipass connected textures: " + multipass);
   }

   private static List makePropertyList(bjh[][] propsArr) {
      List list = new ArrayList();
      if (propsArr != null) {
         for(int i = 0; i < propsArr.length; ++i) {
            bjh[] aconnectedproperties = propsArr[i];
            List list1 = null;
            if (aconnectedproperties != null) {
               list1 = new ArrayList(Arrays.asList(aconnectedproperties));
            }

            list.add(list1);
         }
      }

      return list;
   }

   private static boolean detectMultipass() {
      List list = new ArrayList();

      int k;
      bjh[] aconnectedproperties2;
      for(k = 0; k < tileProperties.length; ++k) {
         aconnectedproperties2 = tileProperties[k];
         if (aconnectedproperties2 != null) {
            list.addAll(Arrays.asList(aconnectedproperties2));
         }
      }

      for(k = 0; k < blockProperties.length; ++k) {
         aconnectedproperties2 = blockProperties[k];
         if (aconnectedproperties2 != null) {
            list.addAll(Arrays.asList(aconnectedproperties2));
         }
      }

      bjh[] aconnectedproperties1 = (bjh[])((bjh[])list.toArray(new bjh[list.size()]));
      Set set1 = new HashSet();
      Set set = new HashSet();

      for(int j = 0; j < aconnectedproperties1.length; ++j) {
         bjh connectedproperties = aconnectedproperties1[j];
         if (connectedproperties.matchTileIcons != null) {
            set1.addAll(Arrays.asList(connectedproperties.matchTileIcons));
         }

         if (connectedproperties.tileIcons != null) {
            set.addAll(Arrays.asList(connectedproperties.tileIcons));
         }
      }

      set1.retainAll(set);
      return !set1.isEmpty();
   }

   private static bjh[][] propertyListToArray(List list) {
      bjh[][] propArr = new bjh[list.size()][];

      for(int i2 = 0; i2 < list.size(); ++i2) {
         List<bjh> subList = (List)list.get(i2);
         if (subList != null) {
            bjh[] subArr = (bjh[])subList.toArray(new bjh[subList.size()]);
            propArr[i2] = subArr;
         }
      }

      return propArr;
   }

   private static void addToTileList(bjh cp, List tileList) {
      if (cp.matchTileIcons != null) {
         for(int i = 0; i < cp.matchTileIcons.length; ++i) {
            zd textureatlassprite = cp.matchTileIcons[i];
            if (!(textureatlassprite instanceof zd)) {
               XH.warn("TextureAtlasSprite is not TextureAtlasSprite: " + textureatlassprite + ", name: " + textureatlassprite.getIconName());
            } else {
               int j = textureatlassprite.getIndexInMap();
               if (j < 0) {
                  XH.warn("Invalid tile ID: " + j + ", icon: " + textureatlassprite.getIconName());
               } else {
                  addToList(cp, tileList, j);
               }
            }
         }
      }

   }

   private static void addToBlockList(bjh cp, List blockList) {
      if (cp.matchBlocks != null) {
         for(int i = 0; i < cp.matchBlocks.length; ++i) {
            int j = cp.matchBlocks[i].getBlockId();
            if (j < 0) {
               XH.warn("Invalid block ID: " + j);
            } else {
               addToList(cp, blockList, j);
            }
         }
      }

   }

   private static void addToList(bjh cp, List list, int id) {
      while(id >= list.size()) {
         list.add((Object)null);
      }

      ArrayList<bjh> subList = (ArrayList)list.get(id);
      if (subList == null) {
         subList = new ArrayList();
         list.set(id, subList);
      }

      subList.add(cp);
   }

   private static String[] getDefaultCtmPaths() {
      List list = new ArrayList();
      String s = "mcpatcher/ctm/default/";
      if (XH.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass.png"))) {
         list.add(s + "glass.properties");
         list.add(s + "glasspane.properties");
      }

      if (XH.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/bookshelf.png"))) {
         list.add(s + "bookshelf.properties");
      }

      if (XH.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/sandstone_normal.png"))) {
         list.add(s + "sandstone.properties");
      }

      String[] astring = new String[]{"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "silver", "cyan", "purple", "blue", "brown", "green", "red", "black"};

      for(int i = 0; i < astring.length; ++i) {
         String s1 = astring[i];
         if (XH.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/glass_" + s1 + ".png"))) {
            list.add(s + i + "_glass_" + s1 + "/glass_" + s1 + ".properties");
            list.add(s + i + "_glass_" + s1 + "/glass_pane_" + s1 + ".properties");
         }
      }

      String[] astring1 = (String[])((String[])list.toArray(new String[list.size()]));
      return astring1;
   }

   static {
      AIR_DEFAULT_STATE = Nk.AIR.getDefaultState();
      emptySprite = null;
      SIDES_Y_NEG_DOWN = new biK[]{biK.WEST, biK.EAST, biK.NORTH, biK.SOUTH};
      SIDES_Y_POS_UP = new biK[]{biK.WEST, biK.EAST, biK.SOUTH, biK.NORTH};
      SIDES_Z_NEG_NORTH = new biK[]{biK.EAST, biK.WEST, biK.DOWN, biK.UP};
      SIDES_Z_POS_SOUTH = new biK[]{biK.WEST, biK.EAST, biK.DOWN, biK.UP};
      SIDES_X_NEG_WEST = new biK[]{biK.NORTH, biK.SOUTH, biK.DOWN, biK.UP};
      SIDES_X_POS_EAST = new biK[]{biK.SOUTH, biK.NORTH, biK.DOWN, biK.UP};
      SIDES_Z_NEG_NORTH_Z_AXIS = new biK[]{biK.WEST, biK.EAST, biK.UP, biK.DOWN};
      SIDES_X_POS_EAST_X_AXIS = new biK[]{biK.NORTH, biK.SOUTH, biK.UP, biK.DOWN};
      EDGES_Y_NEG_DOWN = new biK[]{biK.NORTH_EAST, biK.NORTH_WEST, biK.SOUTH_EAST, biK.SOUTH_WEST};
      EDGES_Y_POS_UP = new biK[]{biK.SOUTH_EAST, biK.SOUTH_WEST, biK.NORTH_EAST, biK.NORTH_WEST};
      EDGES_Z_NEG_NORTH = new biK[]{biK.DOWN_WEST, biK.DOWN_EAST, biK.UP_WEST, biK.UP_EAST};
      EDGES_Z_POS_SOUTH = new biK[]{biK.DOWN_EAST, biK.DOWN_WEST, biK.UP_EAST, biK.UP_WEST};
      EDGES_X_NEG_WEST = new biK[]{biK.DOWN_SOUTH, biK.DOWN_NORTH, biK.UP_SOUTH, biK.UP_NORTH};
      EDGES_X_POS_EAST = new biK[]{biK.DOWN_NORTH, biK.DOWN_SOUTH, biK.UP_NORTH, biK.UP_SOUTH};
      EDGES_Z_NEG_NORTH_Z_AXIS = new biK[]{biK.UP_EAST, biK.UP_WEST, biK.DOWN_EAST, biK.DOWN_WEST};
      EDGES_X_POS_EAST_X_AXIS = new biK[]{biK.UP_SOUTH, biK.UP_NORTH, biK.DOWN_SOUTH, biK.DOWN_NORTH};
      SPRITE_DEFAULT = new zd("<default>");
   }
}
