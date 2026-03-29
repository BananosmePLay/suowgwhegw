package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class Zy extends Zi {
   protected static final bbr SUPER_BIRCH_TREE = new bbr(false, true);
   protected static final bbr BIRCH_TREE = new bbr(false, false);
   protected static final bbv ROOF_TREE = new bbv(false);
   private final Zx type;

   public Zy(Zx typeIn, Zf properties) {
      super(properties);
      this.type = typeIn;
      this.decorator.treesPerChunk = 10;
      this.decorator.grassPerChunk = 2;
      if (this.type == Zx.FLOWER) {
         this.decorator.treesPerChunk = 6;
         this.decorator.flowersPerChunk = 100;
         this.decorator.grassPerChunk = 1;
         this.spawnableCreatureList.add(new Zg(LY.class, 4, 2, 3));
      }

      if (this.type == Zx.NORMAL) {
         this.spawnableCreatureList.add(new Zg(Mu.class, 5, 4, 4));
      }

      if (this.type == Zx.ROOFED) {
         this.decorator.treesPerChunk = -999;
      }

   }

   public bbn getRandomTreeFeature(Random rand) {
      if (this.type == Zx.ROOFED && rand.nextInt(3) > 0) {
         return ROOF_TREE;
      } else if (this.type != Zx.BIRCH && rand.nextInt(5) != 0) {
         return (bbn)(rand.nextInt(10) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
      } else {
         return BIRCH_TREE;
      }
   }

   public dR pickRandomFlower(Random rand, BlockPos pos) {
      if (this.type == Zx.FLOWER) {
         double d0 = MathHelper.clamp((1.0 + GRASS_COLOR_NOISE.getValue((double)pos.getX() / 48.0, (double)pos.getZ() / 48.0)) / 2.0, 0.0, 0.9999);
         dR blockflower$enumflowertype = dR.values()[(int)(d0 * (double)dR.values().length)];
         return blockflower$enumflowertype == dR.BLUE_ORCHID ? dR.POPPY : blockflower$enumflowertype;
      } else {
         return super.pickRandomFlower(rand, pos);
      }
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      if (this.type == Zx.ROOFED) {
         this.addMushrooms(worldIn, rand, pos);
      }

      int i = rand.nextInt(5) - 3;
      if (this.type == Zx.FLOWER) {
         i += 2;
      }

      this.addDoublePlants(worldIn, rand, pos, i);
      super.decorate(worldIn, rand, pos);
   }

   protected void addMushrooms(bij p_185379_1_, Random p_185379_2_, BlockPos p_185379_3_) {
      for(int i = 0; i < 4; ++i) {
         for(int j = 0; j < 4; ++j) {
            int k = i * 4 + 1 + 8 + p_185379_2_.nextInt(3);
            int l = j * 4 + 1 + 8 + p_185379_2_.nextInt(3);
            BlockPos blockpos = p_185379_1_.getHeight(p_185379_3_.add(k, 0, l));
            if (p_185379_2_.nextInt(20) == 0) {
               bbo worldgenbigmushroom = new bbo();
               worldgenbigmushroom.generate(p_185379_1_, p_185379_2_, blockpos);
            } else {
               bbn worldgenabstracttree = this.getRandomTreeFeature(p_185379_2_);
               worldgenabstracttree.setDecorationDefaults();
               if (worldgenabstracttree.generate(p_185379_1_, p_185379_2_, blockpos)) {
                  worldgenabstracttree.generateSaplings(p_185379_1_, p_185379_2_, blockpos);
               }
            }
         }
      }

   }

   protected void addDoublePlants(bij p_185378_1_, Random p_185378_2_, BlockPos p_185378_3_, int p_185378_4_) {
      for(int i = 0; i < p_185378_4_; ++i) {
         int j = p_185378_2_.nextInt(3);
         if (j == 0) {
            DOUBLE_PLANT_GENERATOR.setPlantType(dq.SYRINGA);
         } else if (j == 1) {
            DOUBLE_PLANT_GENERATOR.setPlantType(dq.ROSE);
         } else if (j == 2) {
            DOUBLE_PLANT_GENERATOR.setPlantType(dq.PAEONIA);
         }

         for(int k = 0; k < 5; ++k) {
            int l = p_185378_2_.nextInt(16) + 8;
            int i1 = p_185378_2_.nextInt(16) + 8;
            int j1 = p_185378_2_.nextInt(p_185378_1_.getHeight(p_185378_3_.add(l, 0, i1)).getY() + 32);
            if (DOUBLE_PLANT_GENERATOR.generate(p_185378_1_, p_185378_2_, new BlockPos(p_185378_3_.getX() + l, j1, p_185378_3_.getZ() + i1))) {
               break;
            }
         }
      }

   }

   public Class<? extends Zi> getBiomeClass() {
      return Zy.class;
   }

   public int getGrassColorAtPos(BlockPos pos) {
      int i = super.getGrassColorAtPos(pos);
      return this.type == Zx.ROOFED ? (i & 16711422) + 2634762 >> 1 : i;
   }
}
