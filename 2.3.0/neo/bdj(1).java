package neo;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.math.BlockPos;

public class bdj extends bdr {
   private final List<Zg> spawnList = Lists.newArrayList();

   public bdj() {
      this.spawnList.add(new Zg(Jz.class, 10, 2, 3));
      this.spawnList.add(new Zg(Ko.class, 5, 4, 4));
      this.spawnList.add(new Zg(Lh.class, 8, 5, 5));
      this.spawnList.add(new Zg(KH.class, 2, 5, 5));
      this.spawnList.add(new Zg(Kk.class, 3, 4, 4));
   }

   public String getStructureName() {
      return "Fortress";
   }

   public List<Zg> getSpawnList() {
      return this.spawnList;
   }

   protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
      int i = chunkX >> 4;
      int j = chunkZ >> 4;
      this.rand.setSeed((long)(i ^ j << 4) ^ this.world.getSeed());
      this.rand.nextInt();
      if (this.rand.nextInt(3) != 0) {
         return false;
      } else if (chunkX != (i << 4) + 4 + this.rand.nextInt(8)) {
         return false;
      } else {
         return chunkZ == (j << 4) + 4 + this.rand.nextInt(8);
      }
   }

   protected beM getStructureStart(int chunkX, int chunkZ) {
      return new bdi(this.world, this.rand, chunkX, chunkZ);
   }

   public BlockPos getNearestStructurePos(bij worldIn, BlockPos pos, boolean findUnexplored) {
      int i = true;
      int j = pos.getX() >> 4;
      int k = pos.getZ() >> 4;

      for(int l = 0; l <= 1000; ++l) {
         for(int i1 = -l; i1 <= l; ++i1) {
            boolean flag = i1 == -l || i1 == l;

            for(int j1 = -l; j1 <= l; ++j1) {
               boolean flag1 = j1 == -l || j1 == l;
               if (flag || flag1) {
                  int k1 = j + i1;
                  int l1 = k + j1;
                  if (this.canSpawnStructureAtCoords(k1, l1) && (!findUnexplored || !worldIn.isChunkGeneratedAt(k1, l1))) {
                     return new BlockPos((k1 << 4) + 8, 64, (l1 << 4) + 8);
                  }
               }
            }
         }
      }

      return null;
   }
}
