package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bdk extends beM {
   public bdk() {
   }

   public bdk(bij worldIn, Random random, int chunkX, int chunkZ) {
      this(worldIn, random, chunkX, chunkZ, worldIn.getBiome(new BlockPos(chunkX * 16 + 8, 0, chunkZ * 16 + 8)));
   }

   public bdk(bij worldIn, Random random, int chunkX, int chunkZ, Zi biomeIn) {
      super(chunkX, chunkZ);
      if (biomeIn != Nj.JUNGLE && biomeIn != Nj.JUNGLE_HILLS) {
         if (biomeIn == Nj.SWAMPLAND) {
            bdc componentscatteredfeaturepieces$swamphut = new bdc(random, chunkX * 16, chunkZ * 16);
            this.components.add(componentscatteredfeaturepieces$swamphut);
         } else if (biomeIn != Nj.DESERT && biomeIn != Nj.DESERT_HILLS) {
            if (biomeIn == Nj.ICE_PLAINS || biomeIn == Nj.COLD_TAIGA) {
               bcZ componentscatteredfeaturepieces$igloo = new bcZ(random, chunkX * 16, chunkZ * 16);
               this.components.add(componentscatteredfeaturepieces$igloo);
            }
         } else {
            bcX componentscatteredfeaturepieces$desertpyramid = new bcX(random, chunkX * 16, chunkZ * 16);
            this.components.add(componentscatteredfeaturepieces$desertpyramid);
         }
      } else {
         bdb componentscatteredfeaturepieces$junglepyramid = new bdb(random, chunkX * 16, chunkZ * 16);
         this.components.add(componentscatteredfeaturepieces$junglepyramid);
      }

      this.updateBoundingBox();
   }
}
