package neo;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class ben extends bdr {
   private int spacing;
   private int separation;
   public static final List<Zi> WATER_BIOMES;
   public static final List<Zi> SPAWN_BIOMES;
   private static final List<Zg> MONUMENT_ENEMIES;

   public ben() {
      this.spacing = 32;
      this.separation = 5;
   }

   public ben(Map<String, String> p_i45608_1_) {
      this();
      Iterator var2 = p_i45608_1_.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, String> entry = (Map.Entry)var2.next();
         if (((String)entry.getKey()).equals("spacing")) {
            this.spacing = MathHelper.getInt((String)((String)entry.getValue()), this.spacing, 1);
         } else if (((String)entry.getKey()).equals("separation")) {
            this.separation = MathHelper.getInt((String)((String)entry.getValue()), this.separation, 1);
         }
      }

   }

   public String getStructureName() {
      return "Monument";
   }

   protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
      int i = chunkX;
      int j = chunkZ;
      if (chunkX < 0) {
         chunkX -= this.spacing - 1;
      }

      if (chunkZ < 0) {
         chunkZ -= this.spacing - 1;
      }

      int k = chunkX / this.spacing;
      int l = chunkZ / this.spacing;
      Random random = this.world.setRandomSeed(k, l, 10387313);
      k *= this.spacing;
      l *= this.spacing;
      k += (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
      l += (random.nextInt(this.spacing - this.separation) + random.nextInt(this.spacing - this.separation)) / 2;
      if (i == k && j == l) {
         if (!this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 16, SPAWN_BIOMES)) {
            return false;
         }

         boolean flag = this.world.getBiomeProvider().areBiomesViable(i * 16 + 8, j * 16 + 8, 29, WATER_BIOMES);
         if (flag) {
            return true;
         }
      }

      return false;
   }

   public BlockPos getNearestStructurePos(bij worldIn, BlockPos pos, boolean findUnexplored) {
      this.world = worldIn;
      return findNearestStructurePosBySpacing(worldIn, this, pos, this.spacing, this.separation, 10387313, true, 100, findUnexplored);
   }

   protected beM getStructureStart(int chunkX, int chunkZ) {
      return new bem(this.world, this.rand, chunkX, chunkZ);
   }

   public List<Zg> getMonsters() {
      return MONUMENT_ENEMIES;
   }

   static {
      WATER_BIOMES = Arrays.asList(Nj.OCEAN, Nj.DEEP_OCEAN, Nj.RIVER, Nj.FROZEN_OCEAN, Nj.FROZEN_RIVER);
      SPAWN_BIOMES = Arrays.asList(Nj.DEEP_OCEAN);
      MONUMENT_ENEMIES = Lists.newArrayList();
      MONUMENT_ENEMIES.add(new Zg(Kc.class, 1, 2, 4));
   }
}
