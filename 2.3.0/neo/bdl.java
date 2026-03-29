package neo;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bdl extends bdr {
   private static final List<Zi> BIOMELIST;
   private final List<Zg> monsters;
   private int maxDistanceBetweenScatteredFeatures;
   private final int minDistanceBetweenScatteredFeatures;

   public bdl() {
      this.monsters = Lists.newArrayList();
      this.maxDistanceBetweenScatteredFeatures = 32;
      this.minDistanceBetweenScatteredFeatures = 8;
      this.monsters.add(new Zg(Lg.class, 1, 1, 1));
   }

   public bdl(Map<String, String> p_i2061_1_) {
      this();
      Iterator var2 = p_i2061_1_.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, String> entry = (Map.Entry)var2.next();
         if (((String)entry.getKey()).equals("distance")) {
            this.maxDistanceBetweenScatteredFeatures = MathHelper.getInt((String)((String)entry.getValue()), this.maxDistanceBetweenScatteredFeatures, 9);
         }
      }

   }

   public String getStructureName() {
      return "Temple";
   }

   protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
      int i = chunkX;
      int j = chunkZ;
      if (chunkX < 0) {
         chunkX -= this.maxDistanceBetweenScatteredFeatures - 1;
      }

      if (chunkZ < 0) {
         chunkZ -= this.maxDistanceBetweenScatteredFeatures - 1;
      }

      int k = chunkX / this.maxDistanceBetweenScatteredFeatures;
      int l = chunkZ / this.maxDistanceBetweenScatteredFeatures;
      Random random = this.world.setRandomSeed(k, l, 14357617);
      k *= this.maxDistanceBetweenScatteredFeatures;
      l *= this.maxDistanceBetweenScatteredFeatures;
      k += random.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);
      l += random.nextInt(this.maxDistanceBetweenScatteredFeatures - 8);
      if (i == k && j == l) {
         Zi biome = this.world.getBiomeProvider().getBiome(new BlockPos(i * 16 + 8, 0, j * 16 + 8));
         if (biome == null) {
            return false;
         }

         Iterator var9 = BIOMELIST.iterator();

         while(var9.hasNext()) {
            Zi biome1 = (Zi)var9.next();
            if (biome == biome1) {
               return true;
            }
         }
      }

      return false;
   }

   public BlockPos getNearestStructurePos(bij worldIn, BlockPos pos, boolean findUnexplored) {
      this.world = worldIn;
      return findNearestStructurePosBySpacing(worldIn, this, pos, this.maxDistanceBetweenScatteredFeatures, 8, 14357617, false, 100, findUnexplored);
   }

   protected beM getStructureStart(int chunkX, int chunkZ) {
      return new bdk(this.world, this.rand, chunkX, chunkZ);
   }

   public boolean isSwampHut(BlockPos pos) {
      beM structurestart = this.getStructureAt(pos);
      if (structurestart != null && structurestart instanceof bdk && !structurestart.components.isEmpty()) {
         bdB structurecomponent = (bdB)structurestart.components.get(0);
         return structurecomponent instanceof bdc;
      } else {
         return false;
      }
   }

   public List<Zg> getMonsters() {
      return this.monsters;
   }

   static {
      BIOMELIST = Arrays.asList(Nj.DESERT, Nj.DESERT_HILLS, Nj.JUNGLE, Nj.JUNGLE_HILLS, Nj.SWAMPLAND, Nj.ICE_PLAINS, Nj.COLD_TAIGA);
   }
}
