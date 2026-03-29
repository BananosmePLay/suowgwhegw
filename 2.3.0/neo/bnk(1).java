package neo;

import java.util.Arrays;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bnk implements bfZ {
   private final baI chunkCache;
   private final int posX;
   private final int posY;
   private final int posZ;
   private final int sizeX;
   private final int sizeY;
   private final int sizeZ;
   private final int sizeXY;
   private int[] combinedLights;
   private in[] blockStates;
   private final int arraySize;
   private final boolean dynamicLights = XH.isDynamicLights();
   private static final bqg cacheCombinedLights;
   private static final bqg cacheBlockStates;

   public bnk(baI chunkCache, BlockPos posFromIn, BlockPos posToIn, int subIn) {
      this.chunkCache = chunkCache;
      int i = posFromIn.getX() - subIn >> 4;
      int j = posFromIn.getY() - subIn >> 4;
      int k = posFromIn.getZ() - subIn >> 4;
      int l = posToIn.getX() + subIn >> 4;
      int i1 = posToIn.getY() + subIn >> 4;
      int j1 = posToIn.getZ() + subIn >> 4;
      this.sizeX = l - i + 1 << 4;
      this.sizeY = i1 - j + 1 << 4;
      this.sizeZ = j1 - k + 1 << 4;
      this.sizeXY = this.sizeX * this.sizeY;
      this.arraySize = this.sizeX * this.sizeY * this.sizeZ;
      this.posX = i << 4;
      this.posY = j << 4;
      this.posZ = k << 4;
   }

   private int getPositionIndex(BlockPos pos) {
      int i = pos.getX() - this.posX;
      if (i >= 0 && i < this.sizeX) {
         int j = pos.getY() - this.posY;
         if (j >= 0 && j < this.sizeY) {
            int k = pos.getZ() - this.posZ;
            return k >= 0 && k < this.sizeZ ? k * this.sizeXY + j * this.sizeX + i : -1;
         } else {
            return -1;
         }
      } else {
         return -1;
      }
   }

   public int getCombinedLight(BlockPos pos, int lightValue) {
      int i = this.getPositionIndex(pos);
      if (i >= 0 && i < this.arraySize && this.combinedLights != null) {
         int j = this.combinedLights[i];
         if (j == -1) {
            j = this.getCombinedLightRaw(pos, lightValue);
            this.combinedLights[i] = j;
         }

         return j;
      } else {
         return this.getCombinedLightRaw(pos, lightValue);
      }
   }

   private int getCombinedLightRaw(BlockPos pos, int lightValue) {
      int i = this.chunkCache.getCombinedLight(pos, lightValue);
      if (this.dynamicLights && !this.getBlockState(pos).isOpaqueCube()) {
         i = bjP.getCombinedLight(pos, i);
      }

      return i;
   }

   public in getBlockState(BlockPos pos) {
      int i = this.getPositionIndex(pos);
      if (i >= 0 && i < this.arraySize && this.blockStates != null) {
         in iblockstate = this.blockStates[i];
         if (iblockstate == null) {
            iblockstate = this.chunkCache.getBlockState(pos);
            this.blockStates[i] = iblockstate;
         }

         return iblockstate;
      } else {
         return this.chunkCache.getBlockState(pos);
      }
   }

   public void renderStart() {
      if (this.combinedLights == null) {
         this.combinedLights = (int[])((int[])cacheCombinedLights.allocate(this.arraySize));
      }

      Arrays.fill(this.combinedLights, -1);
      if (this.blockStates == null) {
         this.blockStates = (in[])((in[])cacheBlockStates.allocate(this.arraySize));
      }

      Arrays.fill(this.blockStates, (Object)null);
   }

   public void renderFinish() {
      cacheCombinedLights.free(this.combinedLights);
      this.combinedLights = null;
      cacheBlockStates.free(this.blockStates);
      this.blockStates = null;
   }

   public boolean isEmpty() {
      return this.chunkCache.isEmpty();
   }

   public Zi getBiome(BlockPos pos) {
      return this.chunkCache.getBiome(pos);
   }

   public int getStrongPower(BlockPos pos, EnumFacing direction) {
      return this.chunkCache.getStrongPower(pos, direction);
   }

   public Yg getTileEntity(BlockPos pos) {
      return this.chunkCache.getTileEntity(pos, bal.CHECK);
   }

   public Yg getTileEntity(BlockPos pos, bal type) {
      return this.chunkCache.getTileEntity(pos, type);
   }

   public bix getWorldType() {
      return this.chunkCache.getWorldType();
   }

   public boolean isAirBlock(BlockPos pos) {
      return this.chunkCache.isAirBlock(pos);
   }

   public boolean isSideSolid(BlockPos pos, EnumFacing side, boolean _default) {
      return bnK.callBoolean(this.chunkCache, bnK.ForgeChunkCache_isSideSolid, pos, side, _default);
   }

   static {
      cacheCombinedLights = new bqg(Integer.TYPE, 16);
      cacheBlockStates = new bqg(in.class, 16);
   }
}
