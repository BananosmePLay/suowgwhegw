package neo;

import java.util.Random;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;

public class bdf extends bdr {
   private final int citySpacing = 20;
   private final int minCitySeparation = 11;
   private final bbd endProvider;

   public bdf(bbd endProviderIn) {
      this.endProvider = endProviderIn;
   }

   public String getStructureName() {
      return "EndCity";
   }

   protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
      int i = chunkX;
      int j = chunkZ;
      if (chunkX < 0) {
         chunkX -= 19;
      }

      if (chunkZ < 0) {
         chunkZ -= 19;
      }

      int k = chunkX / 20;
      int l = chunkZ / 20;
      Random random = this.world.setRandomSeed(k, l, 10387313);
      k *= 20;
      l *= 20;
      k += (random.nextInt(9) + random.nextInt(9)) / 2;
      l += (random.nextInt(9) + random.nextInt(9)) / 2;
      if (i == k && j == l && this.endProvider.isIslandChunk(i, j)) {
         int i1 = getYPosForStructure(i, j, this.endProvider);
         return i1 >= 60;
      } else {
         return false;
      }
   }

   protected beM getStructureStart(int chunkX, int chunkZ) {
      return new bde(this.world, this.endProvider, this.rand, chunkX, chunkZ);
   }

   public BlockPos getNearestStructurePos(bij worldIn, BlockPos pos, boolean findUnexplored) {
      this.world = worldIn;
      return findNearestStructurePosBySpacing(worldIn, this, pos, 20, 11, 10387313, true, 100, findUnexplored);
   }

   private static int getYPosForStructure(int chunkX, int chunkY, bbd generatorIn) {
      Random random = new Random((long)(chunkX + chunkY * 10387313));
      Rotation rotation = Rotation.values()[random.nextInt(Rotation.values().length)];
      ban chunkprimer = new ban();
      generatorIn.setBlocksInChunk(chunkX, chunkY, chunkprimer);
      int i = 5;
      int j = 5;
      if (rotation == Rotation.CLOCKWISE_90) {
         i = -5;
      } else if (rotation == Rotation.CLOCKWISE_180) {
         i = -5;
         j = -5;
      } else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
         j = -5;
      }

      int k = chunkprimer.findGroundBlockIdx(7, 7);
      int l = chunkprimer.findGroundBlockIdx(7, 7 + j);
      int i1 = chunkprimer.findGroundBlockIdx(7 + i, 7);
      int j1 = chunkprimer.findGroundBlockIdx(7 + i, 7 + j);
      int k1 = Math.min(Math.min(k, l), Math.min(i1, j1));
      return k1;
   }

   // $FF: synthetic method
   static int access$000(int x0, int x1, bbd x2) {
      return getYPosForStructure(x0, x1, x2);
   }
}
