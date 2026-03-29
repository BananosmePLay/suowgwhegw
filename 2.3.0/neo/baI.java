package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class baI implements bfZ {
   protected int chunkX;
   protected int chunkZ;
   protected bam[][] chunkArray;
   protected boolean empty;
   protected bij world;

   public baI(bij worldIn, BlockPos posFromIn, BlockPos posToIn, int subIn) {
      this.world = worldIn;
      this.chunkX = posFromIn.getX() - subIn >> 4;
      this.chunkZ = posFromIn.getZ() - subIn >> 4;
      int i = posToIn.getX() + subIn >> 4;
      int j = posToIn.getZ() + subIn >> 4;
      this.chunkArray = new bam[i - this.chunkX + 1][j - this.chunkZ + 1];
      this.empty = true;

      int i1;
      int j1;
      for(i1 = this.chunkX; i1 <= i; ++i1) {
         for(j1 = this.chunkZ; j1 <= j; ++j1) {
            this.chunkArray[i1 - this.chunkX][j1 - this.chunkZ] = worldIn.getChunk(i1, j1);
         }
      }

      for(i1 = posFromIn.getX() >> 4; i1 <= posToIn.getX() >> 4; ++i1) {
         for(j1 = posFromIn.getZ() >> 4; j1 <= posToIn.getZ() >> 4; ++j1) {
            bam chunk = this.chunkArray[i1 - this.chunkX][j1 - this.chunkZ];
            if (chunk != null && !chunk.isEmptyBetween(posFromIn.getY(), posToIn.getY())) {
               this.empty = false;
            }
         }
      }

   }

   public boolean isEmpty() {
      return this.empty;
   }

   @Nullable
   public Yg getTileEntity(BlockPos pos) {
      return this.getTileEntity(pos, bal.IMMEDIATE);
   }

   @Nullable
   public Yg getTileEntity(BlockPos pos, bal createType) {
      int i = (pos.getX() >> 4) - this.chunkX;
      int j = (pos.getZ() >> 4) - this.chunkZ;
      return this.chunkArray[i][j].getTileEntity(pos, createType);
   }

   public int getCombinedLight(BlockPos pos, int lightValue) {
      int i = this.getLightForExt(baW.SKY, pos);
      int j = this.getLightForExt(baW.BLOCK, pos);
      if (j < lightValue) {
         j = lightValue;
      }

      return i << 20 | j << 4;
   }

   public in getBlockState(BlockPos pos) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         int i = (pos.getX() >> 4) - this.chunkX;
         int j = (pos.getZ() >> 4) - this.chunkZ;
         if (i >= 0 && i < this.chunkArray.length && j >= 0 && j < this.chunkArray[i].length) {
            bam chunk = this.chunkArray[i][j];
            if (chunk != null) {
               return chunk.getBlockState(pos);
            }
         }
      }

      return Nk.AIR.getDefaultState();
   }

   public Zi getBiome(BlockPos pos) {
      int i = (pos.getX() >> 4) - this.chunkX;
      int j = (pos.getZ() >> 4) - this.chunkZ;
      return this.chunkArray[i][j].getBiome(pos, this.world.getBiomeProvider());
   }

   private int getLightForExt(baW type, BlockPos pos) {
      if (type == baW.SKY && !this.world.provider.hasSkyLight()) {
         return 0;
      } else if (pos.getY() >= 0 && pos.getY() < 256) {
         int l;
         if (this.getBlockState(pos).useNeighborBrightness()) {
            l = 0;
            EnumFacing[] var9 = EnumFacing.values();
            int var5 = var9.length;

            for(int var6 = 0; var6 < var5; ++var6) {
               EnumFacing enumfacing = var9[var6];
               int k = this.getLightFor(type, pos.offset(enumfacing));
               if (k > l) {
                  l = k;
               }

               if (l >= 15) {
                  return l;
               }
            }

            return l;
         } else {
            l = (pos.getX() >> 4) - this.chunkX;
            int j = (pos.getZ() >> 4) - this.chunkZ;
            return this.chunkArray[l][j].getLightFor(type, pos);
         }
      } else {
         return type.defaultLightValue;
      }
   }

   public boolean isAirBlock(BlockPos pos) {
      return this.getBlockState(pos).getMaterial() == hM.AIR;
   }

   public int getLightFor(baW type, BlockPos pos) {
      if (pos.getY() >= 0 && pos.getY() < 256) {
         int i = (pos.getX() >> 4) - this.chunkX;
         int j = (pos.getZ() >> 4) - this.chunkZ;
         return this.chunkArray[i][j].getLightFor(type, pos);
      } else {
         return type.defaultLightValue;
      }
   }

   public int getStrongPower(BlockPos pos, EnumFacing direction) {
      return this.getBlockState(pos).getStrongPower(this, pos, direction);
   }

   public bix getWorldType() {
      return this.world.getWorldType();
   }
}
