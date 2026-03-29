package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class Zr {
   protected boolean decorating;
   protected BlockPos chunkPos;
   protected bbk chunkProviderSettings;
   protected bbE clayGen = new bbw(4);
   protected bbE sandGen;
   protected bbE gravelGen;
   protected bbE dirtGen;
   protected bbE gravelOreGen;
   protected bbE graniteGen;
   protected bbE dioriteGen;
   protected bbE andesiteGen;
   protected bbE coalGen;
   protected bbE ironGen;
   protected bbE goldGen;
   protected bbE redstoneGen;
   protected bbE diamondGen;
   protected bbE lapisGen;
   protected bbH flowerGen;
   protected bbE mushroomBrownGen;
   protected bbE mushroomRedGen;
   protected bbE bigMushroomGen;
   protected bbE reedGen;
   protected bbE cactusGen;
   protected bbE waterlilyGen;
   protected int waterlilyPerChunk;
   protected int treesPerChunk;
   protected float extraTreeChance;
   protected int flowersPerChunk;
   protected int grassPerChunk;
   protected int deadBushPerChunk;
   protected int mushroomsPerChunk;
   protected int reedsPerChunk;
   protected int cactiPerChunk;
   protected int gravelPatchesPerChunk;
   protected int sandPatchesPerChunk;
   protected int clayPerChunk;
   protected int bigMushroomsPerChunk;
   public boolean generateFalls;

   public Zr() {
      this.sandGen = new bbZ(Nk.SAND, 7);
      this.gravelGen = new bbZ(Nk.GRAVEL, 6);
      this.flowerGen = new bbH(Nk.YELLOW_FLOWER, dR.DANDELION);
      this.mushroomBrownGen = new bbt(Nk.BROWN_MUSHROOM);
      this.mushroomRedGen = new bbt(Nk.RED_MUSHROOM);
      this.bigMushroomGen = new bbo();
      this.reedGen = new bbY();
      this.cactusGen = new bbu();
      this.waterlilyGen = new bck();
      this.extraTreeChance = 0.1F;
      this.flowersPerChunk = 2;
      this.grassPerChunk = 1;
      this.gravelPatchesPerChunk = 1;
      this.sandPatchesPerChunk = 3;
      this.clayPerChunk = 1;
      this.generateFalls = true;
   }

   public void decorate(bij worldIn, Random random, Zi biome, BlockPos pos) {
      if (this.decorating) {
         throw new RuntimeException("Already decorating");
      } else {
         this.chunkProviderSettings = bbi.jsonToFactory(worldIn.getWorldInfo().getGeneratorOptions()).build();
         this.chunkPos = pos;
         this.dirtGen = new bbW(Nk.DIRT.getDefaultState(), this.chunkProviderSettings.dirtSize);
         this.gravelOreGen = new bbW(Nk.GRAVEL.getDefaultState(), this.chunkProviderSettings.gravelSize);
         this.graniteGen = new bbW(Nk.STONE.getDefaultState().withProperty(gZ.VARIANT, gY.GRANITE), this.chunkProviderSettings.graniteSize);
         this.dioriteGen = new bbW(Nk.STONE.getDefaultState().withProperty(gZ.VARIANT, gY.DIORITE), this.chunkProviderSettings.dioriteSize);
         this.andesiteGen = new bbW(Nk.STONE.getDefaultState().withProperty(gZ.VARIANT, gY.ANDESITE), this.chunkProviderSettings.andesiteSize);
         this.coalGen = new bbW(Nk.COAL_ORE.getDefaultState(), this.chunkProviderSettings.coalSize);
         this.ironGen = new bbW(Nk.IRON_ORE.getDefaultState(), this.chunkProviderSettings.ironSize);
         this.goldGen = new bbW(Nk.GOLD_ORE.getDefaultState(), this.chunkProviderSettings.goldSize);
         this.redstoneGen = new bbW(Nk.REDSTONE_ORE.getDefaultState(), this.chunkProviderSettings.redstoneSize);
         this.diamondGen = new bbW(Nk.DIAMOND_ORE.getDefaultState(), this.chunkProviderSettings.diamondSize);
         this.lapisGen = new bbW(Nk.LAPIS_ORE.getDefaultState(), this.chunkProviderSettings.lapisSize);
         this.genDecorations(biome, worldIn, random);
         this.decorating = false;
      }
   }

   protected void genDecorations(Zi biomeIn, bij worldIn, Random random) {
      this.generateOres(worldIn, random);

      int k1;
      int l5;
      int j10;
      for(k1 = 0; k1 < this.sandPatchesPerChunk; ++k1) {
         l5 = random.nextInt(16) + 8;
         j10 = random.nextInt(16) + 8;
         this.sandGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(l5, 0, j10)));
      }

      for(k1 = 0; k1 < this.clayPerChunk; ++k1) {
         l5 = random.nextInt(16) + 8;
         j10 = random.nextInt(16) + 8;
         this.clayGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(l5, 0, j10)));
      }

      for(k1 = 0; k1 < this.gravelPatchesPerChunk; ++k1) {
         l5 = random.nextInt(16) + 8;
         j10 = random.nextInt(16) + 8;
         this.gravelGen.generate(worldIn, random, worldIn.getTopSolidOrLiquidBlock(this.chunkPos.add(l5, 0, j10)));
      }

      k1 = this.treesPerChunk;
      if (random.nextFloat() < this.extraTreeChance) {
         ++k1;
      }

      int i14;
      BlockPos blockpos3;
      for(l5 = 0; l5 < k1; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         bbn worldgenabstracttree = biomeIn.getRandomTreeFeature(random);
         worldgenabstracttree.setDecorationDefaults();
         blockpos3 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14));
         if (worldgenabstracttree.generate(worldIn, random, blockpos3)) {
            worldgenabstracttree.generateSaplings(worldIn, random, blockpos3);
         }
      }

      for(l5 = 0; l5 < this.bigMushroomsPerChunk; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         this.bigMushroomGen.generate(worldIn, random, worldIn.getHeight(this.chunkPos.add(j10, 0, i14)));
      }

      BlockPos blockpos6;
      int j17;
      int k19;
      for(l5 = 0; l5 < this.flowersPerChunk; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() + 32;
         if (j17 > 0) {
            k19 = random.nextInt(j17);
            blockpos6 = this.chunkPos.add(j10, k19, i14);
            dR blockflower$enumflowertype = biomeIn.pickRandomFlower(random, blockpos6);
            dS blockflower = blockflower$enumflowertype.getBlockType().getBlock();
            if (blockflower.getDefaultState().getMaterial() != hM.AIR) {
               this.flowerGen.setGeneratedBlock(blockflower, blockflower$enumflowertype);
               this.flowerGen.generate(worldIn, random, blockpos6);
            }
         }
      }

      for(l5 = 0; l5 < this.grassPerChunk; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() * 2;
         if (j17 > 0) {
            k19 = random.nextInt(j17);
            biomeIn.getRandomWorldGenForGrass(random).generate(worldIn, random, this.chunkPos.add(j10, k19, i14));
         }
      }

      for(l5 = 0; l5 < this.deadBushPerChunk; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() * 2;
         if (j17 > 0) {
            k19 = random.nextInt(j17);
            (new bbx()).generate(worldIn, random, this.chunkPos.add(j10, k19, i14));
         }
      }

      for(l5 = 0; l5 < this.waterlilyPerChunk; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() * 2;
         if (j17 > 0) {
            k19 = random.nextInt(j17);

            BlockPos blockpos7;
            for(blockpos6 = this.chunkPos.add(j10, k19, i14); blockpos6.getY() > 0; blockpos6 = blockpos7) {
               blockpos7 = blockpos6.down();
               if (!worldIn.isAirBlock(blockpos7)) {
                  break;
               }
            }

            this.waterlilyGen.generate(worldIn, random, blockpos6);
         }
      }

      for(l5 = 0; l5 < this.mushroomsPerChunk; ++l5) {
         if (random.nextInt(4) == 0) {
            j10 = random.nextInt(16) + 8;
            i14 = random.nextInt(16) + 8;
            BlockPos blockpos2 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14));
            this.mushroomBrownGen.generate(worldIn, random, blockpos2);
         }

         if (random.nextInt(8) == 0) {
            j10 = random.nextInt(16) + 8;
            i14 = random.nextInt(16) + 8;
            j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() * 2;
            if (j17 > 0) {
               k19 = random.nextInt(j17);
               blockpos6 = this.chunkPos.add(j10, k19, i14);
               this.mushroomRedGen.generate(worldIn, random, blockpos6);
            }
         }
      }

      if (random.nextInt(4) == 0) {
         l5 = random.nextInt(16) + 8;
         j10 = random.nextInt(16) + 8;
         i14 = worldIn.getHeight(this.chunkPos.add(l5, 0, j10)).getY() * 2;
         if (i14 > 0) {
            j17 = random.nextInt(i14);
            this.mushroomBrownGen.generate(worldIn, random, this.chunkPos.add(l5, j17, j10));
         }
      }

      if (random.nextInt(8) == 0) {
         l5 = random.nextInt(16) + 8;
         j10 = random.nextInt(16) + 8;
         i14 = worldIn.getHeight(this.chunkPos.add(l5, 0, j10)).getY() * 2;
         if (i14 > 0) {
            j17 = random.nextInt(i14);
            this.mushroomRedGen.generate(worldIn, random, this.chunkPos.add(l5, j17, j10));
         }
      }

      for(l5 = 0; l5 < this.reedsPerChunk; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() * 2;
         if (j17 > 0) {
            k19 = random.nextInt(j17);
            this.reedGen.generate(worldIn, random, this.chunkPos.add(j10, k19, i14));
         }
      }

      for(l5 = 0; l5 < 10; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() * 2;
         if (j17 > 0) {
            k19 = random.nextInt(j17);
            this.reedGen.generate(worldIn, random, this.chunkPos.add(j10, k19, i14));
         }
      }

      if (random.nextInt(32) == 0) {
         l5 = random.nextInt(16) + 8;
         j10 = random.nextInt(16) + 8;
         i14 = worldIn.getHeight(this.chunkPos.add(l5, 0, j10)).getY() * 2;
         if (i14 > 0) {
            j17 = random.nextInt(i14);
            (new bbX()).generate(worldIn, random, this.chunkPos.add(l5, j17, j10));
         }
      }

      for(l5 = 0; l5 < this.cactiPerChunk; ++l5) {
         j10 = random.nextInt(16) + 8;
         i14 = random.nextInt(16) + 8;
         j17 = worldIn.getHeight(this.chunkPos.add(j10, 0, i14)).getY() * 2;
         if (j17 > 0) {
            k19 = random.nextInt(j17);
            this.cactusGen.generate(worldIn, random, this.chunkPos.add(j10, k19, i14));
         }
      }

      if (this.generateFalls) {
         for(l5 = 0; l5 < 50; ++l5) {
            j10 = random.nextInt(16) + 8;
            i14 = random.nextInt(16) + 8;
            j17 = random.nextInt(248) + 8;
            if (j17 > 0) {
               k19 = random.nextInt(j17);
               blockpos6 = this.chunkPos.add(j10, k19, i14);
               (new bbQ(Nk.FLOWING_WATER)).generate(worldIn, random, blockpos6);
            }
         }

         for(l5 = 0; l5 < 20; ++l5) {
            j10 = random.nextInt(16) + 8;
            i14 = random.nextInt(16) + 8;
            j17 = random.nextInt(random.nextInt(random.nextInt(240) + 8) + 8);
            blockpos3 = this.chunkPos.add(j10, j17, i14);
            (new bbQ(Nk.FLOWING_LAVA)).generate(worldIn, random, blockpos3);
         }
      }

   }

   protected void generateOres(bij worldIn, Random random) {
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.dirtCount, this.dirtGen, this.chunkProviderSettings.dirtMinHeight, this.chunkProviderSettings.dirtMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.gravelCount, this.gravelOreGen, this.chunkProviderSettings.gravelMinHeight, this.chunkProviderSettings.gravelMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.dioriteCount, this.dioriteGen, this.chunkProviderSettings.dioriteMinHeight, this.chunkProviderSettings.dioriteMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.graniteCount, this.graniteGen, this.chunkProviderSettings.graniteMinHeight, this.chunkProviderSettings.graniteMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.andesiteCount, this.andesiteGen, this.chunkProviderSettings.andesiteMinHeight, this.chunkProviderSettings.andesiteMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.coalCount, this.coalGen, this.chunkProviderSettings.coalMinHeight, this.chunkProviderSettings.coalMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.ironCount, this.ironGen, this.chunkProviderSettings.ironMinHeight, this.chunkProviderSettings.ironMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.goldCount, this.goldGen, this.chunkProviderSettings.goldMinHeight, this.chunkProviderSettings.goldMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.redstoneCount, this.redstoneGen, this.chunkProviderSettings.redstoneMinHeight, this.chunkProviderSettings.redstoneMaxHeight);
      this.genStandardOre1(worldIn, random, this.chunkProviderSettings.diamondCount, this.diamondGen, this.chunkProviderSettings.diamondMinHeight, this.chunkProviderSettings.diamondMaxHeight);
      this.genStandardOre2(worldIn, random, this.chunkProviderSettings.lapisCount, this.lapisGen, this.chunkProviderSettings.lapisCenterHeight, this.chunkProviderSettings.lapisSpread);
   }

   protected void genStandardOre1(bij worldIn, Random random, int blockCount, bbE generator, int minHeight, int maxHeight) {
      int j;
      if (maxHeight < minHeight) {
         j = minHeight;
         minHeight = maxHeight;
         maxHeight = j;
      } else if (maxHeight == minHeight) {
         if (minHeight < 255) {
            ++maxHeight;
         } else {
            --minHeight;
         }
      }

      for(j = 0; j < blockCount; ++j) {
         BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
         generator.generate(worldIn, random, blockpos);
      }

   }

   protected void genStandardOre2(bij worldIn, Random random, int blockCount, bbE generator, int centerHeight, int spread) {
      for(int i = 0; i < blockCount; ++i) {
         BlockPos blockpos = this.chunkPos.add(random.nextInt(16), random.nextInt(spread) + random.nextInt(spread) + centerHeight - spread, random.nextInt(16));
         generator.generate(worldIn, random, blockpos);
      }

   }
}
