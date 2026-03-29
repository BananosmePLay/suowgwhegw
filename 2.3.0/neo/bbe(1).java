package neo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public class bbe implements bcn {
   private final bij world;
   private final Random random;
   private final in[] cachedBlockIDs = new in[256];
   private final bcl flatWorldGenInfo;
   private final Map<String, bdr> structureGenerators = new HashMap();
   private final boolean hasDecoration;
   private final boolean hasDungeons;
   private bbP waterLakeGenerator;
   private bbP lavaLakeGenerator;

   public bbe(bij worldIn, long seed, boolean generateStructures, String flatGeneratorSettings) {
      this.world = worldIn;
      this.random = new Random(seed);
      this.flatWorldGenInfo = bcl.createFlatGeneratorFromString(flatGeneratorSettings);
      if (generateStructures) {
         Map<String, Map<String, String>> map = this.flatWorldGenInfo.getWorldFeatures();
         if (map.containsKey("village")) {
            Map<String, String> map1 = (Map)map.get("village");
            if (!map1.containsKey("size")) {
               map1.put("size", "1");
            }

            this.structureGenerators.put("Village", new bdv(map1));
         }

         if (map.containsKey("biome_1")) {
            this.structureGenerators.put("Temple", new bdl((Map)map.get("biome_1")));
         }

         if (map.containsKey("mineshaft")) {
            this.structureGenerators.put("Mineshaft", new bdh((Map)map.get("mineshaft")));
         }

         if (map.containsKey("stronghold")) {
            this.structureGenerators.put("Stronghold", new bdn((Map)map.get("stronghold")));
         }

         if (map.containsKey("oceanmonument")) {
            this.structureGenerators.put("Monument", new ben((Map)map.get("oceanmonument")));
         }
      }

      if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lake")) {
         this.waterLakeGenerator = new bbP(Nk.WATER);
      }

      if (this.flatWorldGenInfo.getWorldFeatures().containsKey("lava_lake")) {
         this.lavaLakeGenerator = new bbP(Nk.LAVA);
      }

      this.hasDungeons = this.flatWorldGenInfo.getWorldFeatures().containsKey("dungeon");
      int j = 0;
      int k = 0;
      boolean flag = true;
      Iterator var9 = this.flatWorldGenInfo.getFlatLayers().iterator();

      while(var9.hasNext()) {
         bcm flatlayerinfo = (bcm)var9.next();

         for(int i = flatlayerinfo.getMinY(); i < flatlayerinfo.getMinY() + flatlayerinfo.getLayerCount(); ++i) {
            in iblockstate = flatlayerinfo.getLayerMaterial();
            if (iblockstate.getBlock() != Nk.AIR) {
               flag = false;
               this.cachedBlockIDs[i] = iblockstate;
            }
         }

         if (flatlayerinfo.getLayerMaterial().getBlock() == Nk.AIR) {
            k += flatlayerinfo.getLayerCount();
         } else {
            j += flatlayerinfo.getLayerCount() + k;
            k = 0;
         }
      }

      worldIn.setSeaLevel(j);
      this.hasDecoration = flag && this.flatWorldGenInfo.getBiome() != Zi.getIdForBiome(Nj.VOID) ? false : this.flatWorldGenInfo.getWorldFeatures().containsKey("decoration");
   }

   public bam generateChunk(int x, int z) {
      ban chunkprimer = new ban();

      int k;
      for(int i = 0; i < this.cachedBlockIDs.length; ++i) {
         in iblockstate = this.cachedBlockIDs[i];
         if (iblockstate != null) {
            for(int j = 0; j < 16; ++j) {
               for(k = 0; k < 16; ++k) {
                  chunkprimer.setBlockState(j, i, k, iblockstate);
               }
            }
         }
      }

      Iterator var8 = this.structureGenerators.values().iterator();

      while(var8.hasNext()) {
         bcM mapgenbase = (bcM)var8.next();
         mapgenbase.generate(this.world, x, z, chunkprimer);
      }

      bam chunk = new bam(this.world, chunkprimer, x, z);
      Zi[] abiome = this.world.getBiomeProvider().getBiomes((Zi[])null, x * 16, z * 16, 16, 16);
      byte[] abyte = chunk.getBiomeArray();

      for(k = 0; k < abyte.length; ++k) {
         abyte[k] = (byte)Zi.getIdForBiome(abiome[k]);
      }

      chunk.generateSkylightMap();
      return chunk;
   }

   public void populate(int x, int z) {
      int i = x * 16;
      int j = z * 16;
      BlockPos blockpos = new BlockPos(i, 0, j);
      Zi biome = this.world.getBiome(new BlockPos(i + 16, 0, j + 16));
      boolean flag = false;
      this.random.setSeed(this.world.getSeed());
      long k = this.random.nextLong() / 2L * 2L + 1L;
      long l = this.random.nextLong() / 2L * 2L + 1L;
      this.random.setSeed((long)x * k + (long)z * l ^ this.world.getSeed());
      ChunkPos chunkpos = new ChunkPos(x, z);
      Iterator var13 = this.structureGenerators.values().iterator();

      while(var13.hasNext()) {
         bdr mapgenstructure = (bdr)var13.next();
         boolean flag1 = mapgenstructure.generateStructure(this.world, this.random, chunkpos);
         if (mapgenstructure instanceof bdv) {
            flag |= flag1;
         }
      }

      if (this.waterLakeGenerator != null && !flag && this.random.nextInt(4) == 0) {
         this.waterLakeGenerator.generate(this.world, this.random, blockpos.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
      }

      if (this.lavaLakeGenerator != null && !flag && this.random.nextInt(8) == 0) {
         BlockPos blockpos1 = blockpos.add(this.random.nextInt(16) + 8, this.random.nextInt(this.random.nextInt(248) + 8), this.random.nextInt(16) + 8);
         if (blockpos1.getY() < this.world.getSeaLevel() || this.random.nextInt(10) == 0) {
            this.lavaLakeGenerator.generate(this.world, this.random, blockpos1);
         }
      }

      if (this.hasDungeons) {
         for(int i1 = 0; i1 < 8; ++i1) {
            (new bbA()).generate(this.world, this.random, blockpos.add(this.random.nextInt(16) + 8, this.random.nextInt(256), this.random.nextInt(16) + 8));
         }
      }

      if (this.hasDecoration) {
         biome.decorate(this.world, this.random, blockpos);
      }

   }

   public boolean generateStructures(bam chunkIn, int x, int z) {
      return false;
   }

   public List<Zg> getPossibleCreatures(IC creatureType, BlockPos pos) {
      Zi biome = this.world.getBiome(pos);
      return biome.getSpawnableList(creatureType);
   }

   @Nullable
   public BlockPos getNearestStructurePos(bij worldIn, String structureName, BlockPos position, boolean findUnexplored) {
      bdr mapgenstructure = (bdr)this.structureGenerators.get(structureName);
      return mapgenstructure != null ? mapgenstructure.getNearestStructurePos(worldIn, position, findUnexplored) : null;
   }

   public boolean isInsideStructure(bij worldIn, String structureName, BlockPos pos) {
      bdr mapgenstructure = (bdr)this.structureGenerators.get(structureName);
      return mapgenstructure != null ? mapgenstructure.isInsideStructure(pos) : false;
   }

   public void recreateStructures(bam chunkIn, int x, int z) {
      Iterator var4 = this.structureGenerators.values().iterator();

      while(var4.hasNext()) {
         bdr mapgenstructure = (bdr)var4.next();
         mapgenstructure.generate(this.world, x, z, (ban)null);
      }

   }
}
