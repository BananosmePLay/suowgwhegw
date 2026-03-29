package neo;

import java.util.IdentityHashMap;
import java.util.Map;

public class bjm {
   private static final int COMPACT_NONE = 0;
   private static final int COMPACT_ALL = 1;
   private static final int COMPACT_V = 2;
   private static final int COMPACT_H = 3;
   private static final int COMPACT_HV = 4;

   public bjm() {
   }

   public static rK[] getConnectedTextureCtmCompact(int ctmIndex, bjh cp, int side, rK quad, boa renderEnv) {
      if (cp.ctmTileIndexes != null && ctmIndex >= 0 && ctmIndex < cp.ctmTileIndexes.length) {
         int i = cp.ctmTileIndexes[ctmIndex];
         if (i >= 0 && i <= cp.tileIcons.length) {
            return getQuadsCompact(i, cp.tileIcons, quad, renderEnv);
         }
      }

      switch (ctmIndex) {
         case 1:
            return getQuadsCompactH(0, 3, cp.tileIcons, side, quad, renderEnv);
         case 2:
            return getQuadsCompact(3, cp.tileIcons, quad, renderEnv);
         case 3:
            return getQuadsCompactH(3, 0, cp.tileIcons, side, quad, renderEnv);
         case 4:
            return getQuadsCompact4(0, 3, 2, 4, cp.tileIcons, side, quad, renderEnv);
         case 5:
            return getQuadsCompact4(3, 0, 4, 2, cp.tileIcons, side, quad, renderEnv);
         case 6:
            return getQuadsCompact4(2, 4, 2, 4, cp.tileIcons, side, quad, renderEnv);
         case 7:
            return getQuadsCompact4(3, 3, 4, 4, cp.tileIcons, side, quad, renderEnv);
         case 8:
            return getQuadsCompact4(4, 1, 4, 4, cp.tileIcons, side, quad, renderEnv);
         case 9:
            return getQuadsCompact4(4, 4, 4, 1, cp.tileIcons, side, quad, renderEnv);
         case 10:
            return getQuadsCompact4(1, 4, 1, 4, cp.tileIcons, side, quad, renderEnv);
         case 11:
            return getQuadsCompact4(1, 1, 4, 4, cp.tileIcons, side, quad, renderEnv);
         case 12:
            return getQuadsCompactV(0, 2, cp.tileIcons, side, quad, renderEnv);
         case 13:
            return getQuadsCompact4(0, 3, 2, 1, cp.tileIcons, side, quad, renderEnv);
         case 14:
            return getQuadsCompactV(3, 1, cp.tileIcons, side, quad, renderEnv);
         case 15:
            return getQuadsCompact4(3, 0, 1, 2, cp.tileIcons, side, quad, renderEnv);
         case 16:
            return getQuadsCompact4(2, 4, 0, 3, cp.tileIcons, side, quad, renderEnv);
         case 17:
            return getQuadsCompact4(4, 2, 3, 0, cp.tileIcons, side, quad, renderEnv);
         case 18:
            return getQuadsCompact4(4, 4, 3, 3, cp.tileIcons, side, quad, renderEnv);
         case 19:
            return getQuadsCompact4(4, 2, 4, 2, cp.tileIcons, side, quad, renderEnv);
         case 20:
            return getQuadsCompact4(1, 4, 4, 4, cp.tileIcons, side, quad, renderEnv);
         case 21:
            return getQuadsCompact4(4, 4, 1, 4, cp.tileIcons, side, quad, renderEnv);
         case 22:
            return getQuadsCompact4(4, 4, 1, 1, cp.tileIcons, side, quad, renderEnv);
         case 23:
            return getQuadsCompact4(4, 1, 4, 1, cp.tileIcons, side, quad, renderEnv);
         case 24:
            return getQuadsCompact(2, cp.tileIcons, quad, renderEnv);
         case 25:
            return getQuadsCompactH(2, 1, cp.tileIcons, side, quad, renderEnv);
         case 26:
            return getQuadsCompact(1, cp.tileIcons, quad, renderEnv);
         case 27:
            return getQuadsCompactH(1, 2, cp.tileIcons, side, quad, renderEnv);
         case 28:
            return getQuadsCompact4(2, 4, 2, 1, cp.tileIcons, side, quad, renderEnv);
         case 29:
            return getQuadsCompact4(3, 3, 1, 4, cp.tileIcons, side, quad, renderEnv);
         case 30:
            return getQuadsCompact4(2, 1, 2, 4, cp.tileIcons, side, quad, renderEnv);
         case 31:
            return getQuadsCompact4(3, 3, 4, 1, cp.tileIcons, side, quad, renderEnv);
         case 32:
            return getQuadsCompact4(1, 1, 1, 4, cp.tileIcons, side, quad, renderEnv);
         case 33:
            return getQuadsCompact4(1, 1, 4, 1, cp.tileIcons, side, quad, renderEnv);
         case 34:
            return getQuadsCompact4(4, 1, 1, 4, cp.tileIcons, side, quad, renderEnv);
         case 35:
            return getQuadsCompact4(1, 4, 4, 1, cp.tileIcons, side, quad, renderEnv);
         case 36:
            return getQuadsCompactV(2, 0, cp.tileIcons, side, quad, renderEnv);
         case 37:
            return getQuadsCompact4(2, 1, 0, 3, cp.tileIcons, side, quad, renderEnv);
         case 38:
            return getQuadsCompactV(1, 3, cp.tileIcons, side, quad, renderEnv);
         case 39:
            return getQuadsCompact4(1, 2, 3, 0, cp.tileIcons, side, quad, renderEnv);
         case 40:
            return getQuadsCompact4(4, 1, 3, 3, cp.tileIcons, side, quad, renderEnv);
         case 41:
            return getQuadsCompact4(1, 2, 4, 2, cp.tileIcons, side, quad, renderEnv);
         case 42:
            return getQuadsCompact4(1, 4, 3, 3, cp.tileIcons, side, quad, renderEnv);
         case 43:
            return getQuadsCompact4(4, 2, 1, 2, cp.tileIcons, side, quad, renderEnv);
         case 44:
            return getQuadsCompact4(1, 4, 1, 1, cp.tileIcons, side, quad, renderEnv);
         case 45:
            return getQuadsCompact4(4, 1, 1, 1, cp.tileIcons, side, quad, renderEnv);
         case 46:
            return getQuadsCompact(4, cp.tileIcons, quad, renderEnv);
         default:
            return getQuadsCompact(0, cp.tileIcons, quad, renderEnv);
      }
   }

   private static rK[] getQuadsCompactH(int indexLeft, int indexRight, zd[] sprites, int side, rK quad, boa renderEnv) {
      return getQuadsCompact(bjl.LEFT, indexLeft, bjl.RIGHT, indexRight, sprites, side, quad, renderEnv);
   }

   private static rK[] getQuadsCompactV(int indexUp, int indexDown, zd[] sprites, int side, rK quad, boa renderEnv) {
      return getQuadsCompact(bjl.UP, indexUp, bjl.DOWN, indexDown, sprites, side, quad, renderEnv);
   }

   private static rK[] getQuadsCompact4(int upLeft, int upRight, int downLeft, int downRight, zd[] sprites, int side, rK quad, boa renderEnv) {
      if (upLeft == upRight) {
         return downLeft == downRight ? getQuadsCompact(bjl.UP, upLeft, bjl.DOWN, downLeft, sprites, side, quad, renderEnv) : getQuadsCompact(bjl.UP, upLeft, bjl.DOWN_LEFT, downLeft, bjl.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
      } else if (downLeft == downRight) {
         return getQuadsCompact(bjl.UP_LEFT, upLeft, bjl.UP_RIGHT, upRight, bjl.DOWN, downLeft, sprites, side, quad, renderEnv);
      } else if (upLeft == downLeft) {
         return upRight == downRight ? getQuadsCompact(bjl.LEFT, upLeft, bjl.RIGHT, upRight, sprites, side, quad, renderEnv) : getQuadsCompact(bjl.LEFT, upLeft, bjl.UP_RIGHT, upRight, bjl.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
      } else {
         return upRight == downRight ? getQuadsCompact(bjl.UP_LEFT, upLeft, bjl.DOWN_LEFT, downLeft, bjl.RIGHT, upRight, sprites, side, quad, renderEnv) : getQuadsCompact(bjl.UP_LEFT, upLeft, bjl.UP_RIGHT, upRight, bjl.DOWN_LEFT, downLeft, bjl.DOWN_RIGHT, downRight, sprites, side, quad, renderEnv);
      }
   }

   private static rK[] getQuadsCompact(int index, zd[] sprites, rK quad, boa renderEnv) {
      zd textureatlassprite = sprites[index];
      return bjj.getQuads(textureatlassprite, quad, renderEnv);
   }

   private static rK[] getQuadsCompact(bjl dir1, int index1, bjl dir2, int index2, zd[] sprites, int side, rK quad, boa renderEnv) {
      rK bakedquad = getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
      rK bakedquad1 = getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
      return renderEnv.getArrayQuadsCtm(bakedquad, bakedquad1);
   }

   private static rK[] getQuadsCompact(bjl dir1, int index1, bjl dir2, int index2, bjl dir3, int index3, zd[] sprites, int side, rK quad, boa renderEnv) {
      rK bakedquad = getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
      rK bakedquad1 = getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
      rK bakedquad2 = getQuadCompact(sprites[index3], dir3, side, quad, renderEnv);
      return renderEnv.getArrayQuadsCtm(bakedquad, bakedquad1, bakedquad2);
   }

   private static rK[] getQuadsCompact(bjl dir1, int index1, bjl dir2, int index2, bjl dir3, int index3, bjl dir4, int index4, zd[] sprites, int side, rK quad, boa renderEnv) {
      rK bakedquad = getQuadCompact(sprites[index1], dir1, side, quad, renderEnv);
      rK bakedquad1 = getQuadCompact(sprites[index2], dir2, side, quad, renderEnv);
      rK bakedquad2 = getQuadCompact(sprites[index3], dir3, side, quad, renderEnv);
      rK bakedquad3 = getQuadCompact(sprites[index4], dir4, side, quad, renderEnv);
      return renderEnv.getArrayQuadsCtm(bakedquad, bakedquad1, bakedquad2, bakedquad3);
   }

   private static rK getQuadCompact(zd sprite, bjl dir, int side, rK quad, boa renderEnv) {
      switch (dir) {
         case UP:
            return getQuadCompact(sprite, dir, 0, 0, 16, 8, side, quad, renderEnv);
         case UP_RIGHT:
            return getQuadCompact(sprite, dir, 8, 0, 16, 8, side, quad, renderEnv);
         case RIGHT:
            return getQuadCompact(sprite, dir, 8, 0, 16, 16, side, quad, renderEnv);
         case DOWN_RIGHT:
            return getQuadCompact(sprite, dir, 8, 8, 16, 16, side, quad, renderEnv);
         case DOWN:
            return getQuadCompact(sprite, dir, 0, 8, 16, 16, side, quad, renderEnv);
         case DOWN_LEFT:
            return getQuadCompact(sprite, dir, 0, 8, 8, 16, side, quad, renderEnv);
         case LEFT:
            return getQuadCompact(sprite, dir, 0, 0, 8, 16, side, quad, renderEnv);
         case UP_LEFT:
            return getQuadCompact(sprite, dir, 0, 0, 8, 8, side, quad, renderEnv);
         default:
            return quad;
      }
   }

   private static rK getQuadCompact(zd sprite, bjl dir, int x1, int y1, int x2, int y2, int side, rK quadIn, boa renderEnv) {
      Map[][] amap = bjj.getSpriteQuadCompactMaps();
      if (amap == null) {
         return quadIn;
      } else {
         int i = sprite.getIndexInMap();
         if (i >= 0 && i < amap.length) {
            Map[] amap1 = amap[i];
            if (amap1 == null) {
               amap1 = new Map[bjl.VALUES.length];
               amap[i] = amap1;
            }

            Map<rK, rK> map = amap1[dir.ordinal()];
            if (map == null) {
               map = new IdentityHashMap(1);
               amap1[dir.ordinal()] = (Map)map;
            }

            rK bakedquad = (rK)((Map)map).get(quadIn);
            if (bakedquad == null) {
               bakedquad = makeSpriteQuadCompact(quadIn, sprite, side, x1, y1, x2, y2);
               ((Map)map).put(quadIn, bakedquad);
            }

            return bakedquad;
         } else {
            return quadIn;
         }
      }
   }

   private static rK makeSpriteQuadCompact(rK quad, zd sprite, int side, int x1, int y1, int x2, int y2) {
      int[] aint = (int[])((int[])quad.getVertexData().clone());
      zd textureatlassprite = quad.getSprite();

      for(int i = 0; i < 4; ++i) {
         fixVertexCompact(aint, i, textureatlassprite, sprite, side, x1, y1, x2, y2);
      }

      rK bakedquad = new rK(aint, quad.getTintIndex(), quad.getFace(), sprite);
      return bakedquad;
   }

   private static void fixVertexCompact(int[] data, int vertex, zd spriteFrom, zd spriteTo, int side, int x1, int y1, int x2, int y2) {
      int i = data.length / 4;
      int j = i * vertex;
      float f = Float.intBitsToFloat(data[j + 4]);
      float f1 = Float.intBitsToFloat(data[j + 4 + 1]);
      double d0 = spriteFrom.getSpriteU16(f);
      double d1 = spriteFrom.getSpriteV16(f1);
      float f2 = Float.intBitsToFloat(data[j + 0]);
      float f3 = Float.intBitsToFloat(data[j + 1]);
      float f4 = Float.intBitsToFloat(data[j + 2]);
      float f5;
      float f6;
      switch (side) {
         case 0:
            f5 = f2;
            f6 = 1.0F - f4;
            break;
         case 1:
            f5 = f2;
            f6 = f4;
            break;
         case 2:
            f5 = 1.0F - f2;
            f6 = 1.0F - f3;
            break;
         case 3:
            f5 = f2;
            f6 = 1.0F - f3;
            break;
         case 4:
            f5 = f4;
            f6 = 1.0F - f3;
            break;
         case 5:
            f5 = 1.0F - f4;
            f6 = 1.0F - f3;
            break;
         default:
            return;
      }

      float f7 = 15.968F;
      float f8 = 15.968F;
      if (d0 < (double)x1) {
         f5 = (float)((double)f5 + ((double)x1 - d0) / (double)f7);
         d0 = (double)x1;
      }

      if (d0 > (double)x2) {
         f5 = (float)((double)f5 - (d0 - (double)x2) / (double)f7);
         d0 = (double)x2;
      }

      if (d1 < (double)y1) {
         f6 = (float)((double)f6 + ((double)y1 - d1) / (double)f8);
         d1 = (double)y1;
      }

      if (d1 > (double)y2) {
         f6 = (float)((double)f6 - (d1 - (double)y2) / (double)f8);
         d1 = (double)y2;
      }

      switch (side) {
         case 0:
            f2 = f5;
            f4 = 1.0F - f6;
            break;
         case 1:
            f2 = f5;
            f4 = f6;
            break;
         case 2:
            f2 = 1.0F - f5;
            f3 = 1.0F - f6;
            break;
         case 3:
            f2 = f5;
            f3 = 1.0F - f6;
            break;
         case 4:
            f4 = f5;
            f3 = 1.0F - f6;
            break;
         case 5:
            f4 = 1.0F - f5;
            f3 = 1.0F - f6;
            break;
         default:
            return;
      }

      data[j + 4] = Float.floatToRawIntBits(spriteTo.getInterpolatedU(d0));
      data[j + 4 + 1] = Float.floatToRawIntBits(spriteTo.getInterpolatedV(d1));
      data[j + 0] = Float.floatToRawIntBits(f2);
      data[j + 1] = Float.floatToRawIntBits(f3);
      data[j + 2] = Float.floatToRawIntBits(f4);
   }
}
