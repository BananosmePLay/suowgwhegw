package neo;

import java.util.Iterator;
import net.minecraft.util.math.BlockPos;

public class Zq {
   private static final Zp GRASS_COLOR = new Zp() {
      public int getColorAtPos(Zi biome, BlockPos blockPosition) {
         return biome.getGrassColorAtPos(blockPosition);
      }
   };
   private static final Zp FOLIAGE_COLOR = new Zp() {
      public int getColorAtPos(Zi biome, BlockPos blockPosition) {
         return biome.getFoliageColorAtPos(blockPosition);
      }
   };
   private static final Zp WATER_COLOR = new Zp() {
      public int getColorAtPos(Zi biome, BlockPos blockPosition) {
         return biome.getWaterColor();
      }
   };

   public Zq() {
   }

   private static int getColorAtPos(bfZ blockAccess, BlockPos pos, Zp colorResolver) {
      int i = 0;
      int j = 0;
      int k = 0;

      int l;
      for(Iterator var6 = BlockPos.getAllInBoxMutable(pos.add(-1, 0, -1), pos.add(1, 0, 1)).iterator(); var6.hasNext(); k += l & 255) {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var6.next();
         l = colorResolver.getColorAtPos(blockAccess.getBiome(blockpos$mutableblockpos), blockpos$mutableblockpos);
         i += (l & 16711680) >> 16;
         j += (l & '\uff00') >> 8;
      }

      return (i / 9 & 255) << 16 | (j / 9 & 255) << 8 | k / 9 & 255;
   }

   public static int getGrassColorAtPos(bfZ blockAccess, BlockPos pos) {
      return getColorAtPos(blockAccess, pos, GRASS_COLOR);
   }

   public static int getFoliageColorAtPos(bfZ blockAccess, BlockPos pos) {
      return getColorAtPos(blockAccess, pos, FOLIAGE_COLOR);
   }

   public static int getWaterColorAtPos(bfZ blockAccess, BlockPos pos) {
      return getColorAtPos(blockAccess, pos, WATER_COLOR);
   }
}
