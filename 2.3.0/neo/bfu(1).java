package neo;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class bfu extends bfx {
   public ZL biomeProvider;
   public int terrainType;
   public bfs lastPlaced;
   public List<bfs> structureVillageWeightedPieceList;
   public List<bdB> pendingHouses = Lists.newArrayList();
   public List<bdB> pendingRoads = Lists.newArrayList();

   public bfu() {
   }

   public bfu(ZL biomeProviderIn, int p_i2104_2_, Random rand, int p_i2104_4_, int p_i2104_5_, List<bfs> p_i2104_6_, int p_i2104_7_) {
      super((bfu)null, 0, rand, p_i2104_4_, p_i2104_5_);
      this.biomeProvider = biomeProviderIn;
      this.structureVillageWeightedPieceList = p_i2104_6_;
      this.terrainType = p_i2104_7_;
      Zi biome = biomeProviderIn.getBiome(new BlockPos(p_i2104_4_, 0, p_i2104_5_), Nj.DEFAULT);
      if (biome instanceof Zs) {
         this.structureType = 1;
      } else if (biome instanceof ZO) {
         this.structureType = 2;
      } else if (biome instanceof ZU) {
         this.structureType = 3;
      }

      this.setStructureType(this.structureType);
      this.isZombieInfested = rand.nextInt(50) == 0;
   }
}
