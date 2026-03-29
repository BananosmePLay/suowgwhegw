package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZU extends Zi {
   private static final bcf PINE_GENERATOR = new bcf();
   private static final bcg SPRUCE_GENERATOR = new bcg(false);
   private static final bbS MEGA_PINE_GENERATOR = new bbS(false, false);
   private static final bbS MEGA_SPRUCE_GENERATOR = new bbS(false, true);
   private static final bbs FOREST_ROCK_GENERATOR;
   private final ZT type;

   public ZU(ZT typeIn, Zf properties) {
      super(properties);
      this.type = typeIn;
      this.spawnableCreatureList.add(new Zg(Mu.class, 8, 4, 4));
      this.spawnableCreatureList.add(new Zg(LY.class, 4, 2, 3));
      this.decorator.treesPerChunk = 10;
      if (typeIn != ZT.MEGA && typeIn != ZT.MEGA_SPRUCE) {
         this.decorator.grassPerChunk = 1;
         this.decorator.mushroomsPerChunk = 1;
      } else {
         this.decorator.grassPerChunk = 7;
         this.decorator.deadBushPerChunk = 1;
         this.decorator.mushroomsPerChunk = 3;
      }

   }

   public bbn getRandomTreeFeature(Random rand) {
      if ((this.type == ZT.MEGA || this.type == ZT.MEGA_SPRUCE) && rand.nextInt(3) == 0) {
         return this.type != ZT.MEGA_SPRUCE && rand.nextInt(13) != 0 ? MEGA_PINE_GENERATOR : MEGA_SPRUCE_GENERATOR;
      } else {
         return (bbn)(rand.nextInt(3) == 0 ? PINE_GENERATOR : SPRUCE_GENERATOR);
      }
   }

   public bbE getRandomWorldGenForGrass(Random rand) {
      return rand.nextInt(5) > 0 ? new bch(hj.FERN) : new bch(hj.GRASS);
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      int i1;
      int j1;
      int k1;
      int l1;
      if (this.type == ZT.MEGA || this.type == ZT.MEGA_SPRUCE) {
         i1 = rand.nextInt(3);

         for(j1 = 0; j1 < i1; ++j1) {
            k1 = rand.nextInt(16) + 8;
            l1 = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(k1, 0, l1));
            FOREST_ROCK_GENERATOR.generate(worldIn, rand, blockpos);
         }
      }

      DOUBLE_PLANT_GENERATOR.setPlantType(dq.FERN);

      for(i1 = 0; i1 < 7; ++i1) {
         j1 = rand.nextInt(16) + 8;
         k1 = rand.nextInt(16) + 8;
         l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
         DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
      }

      super.decorate(worldIn, rand, pos);
   }

   public void genTerrainBlocks(bij worldIn, Random rand, ban chunkPrimerIn, int x, int z, double noiseVal) {
      if (this.type == ZT.MEGA || this.type == ZT.MEGA_SPRUCE) {
         this.topBlock = Nk.GRASS.getDefaultState();
         this.fillerBlock = Nk.DIRT.getDefaultState();
         if (noiseVal > 1.75) {
            this.topBlock = Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.COARSE_DIRT);
         } else if (noiseVal > -0.95) {
            this.topBlock = Nk.DIRT.getDefaultState().withProperty(dj.VARIANT, di.PODZOL);
         }
      }

      this.generateBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
   }

   static {
      FOREST_ROCK_GENERATOR = new bbs(Nk.MOSSY_COBBLESTONE, 0);
   }
}
