package neo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bdv extends bdr {
   public static final List<Zi> VILLAGE_SPAWN_BIOMES;
   private int size;
   private int distance;
   private final int minTownSeparation;

   public bdv() {
      this.distance = 32;
      this.minTownSeparation = 8;
   }

   public bdv(Map<String, String> map) {
      this();
      Iterator var2 = map.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, String> entry = (Map.Entry)var2.next();
         if (((String)entry.getKey()).equals("size")) {
            this.size = MathHelper.getInt((String)((String)entry.getValue()), this.size, 0);
         } else if (((String)entry.getKey()).equals("distance")) {
            this.distance = MathHelper.getInt((String)((String)entry.getValue()), this.distance, 9);
         }
      }

   }

   public String getStructureName() {
      return "Village";
   }

   protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
      int i = chunkX;
      int j = chunkZ;
      if (chunkX < 0) {
         chunkX -= this.distance - 1;
      }

      if (chunkZ < 0) {
         chunkZ -= this.distance - 1;
      }

      int k = chunkX / this.distance;
      int l = chunkZ / this.distance;
      Random random = this.world.setRandomSeed(k, l, 10387312);
      k *= this.distance;
      l *= this.distance;
      k += random.nextInt(this.distance - 8);
      l += random.nextInt(this.distance - 8);
      if (i == k && j == l) {
         boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 0, VILLAGE_SPAWN_BIOMES);
         if (flag) {
            return true;
         }
      }

      return false;
   }

   public BlockPos getNearestStructurePos(bij worldIn, BlockPos pos, boolean findUnexplored) {
      this.world = worldIn;
      return findNearestStructurePosBySpacing(worldIn, this, pos, this.distance, 8, 10387312, false, 100, findUnexplored);
   }

   protected beM getStructureStart(int chunkX, int chunkZ) {
      return new bdu(this.world, this.rand, chunkX, chunkZ, this.size);
   }

   static {
      VILLAGE_SPAWN_BIOMES = Arrays.asList(Nj.PLAINS, Nj.DESERT, Nj.SAVANNA, Nj.TAIGA);
   }
}
