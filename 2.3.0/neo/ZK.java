package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZK extends Zi {
   protected boolean sunflowers;

   protected ZK(boolean p_i46699_1_, Zf properties) {
      super(properties);
      this.sunflowers = p_i46699_1_;
      this.spawnableCreatureList.add(new Zg(LF.class, 5, 2, 6));
      this.spawnableCreatureList.add(new Zg(LC.class, 1, 1, 3));
      this.decorator.treesPerChunk = 0;
      this.decorator.extraTreeChance = 0.05F;
      this.decorator.flowersPerChunk = 4;
      this.decorator.grassPerChunk = 10;
   }

   public dR pickRandomFlower(Random rand, BlockPos pos) {
      double d0 = GRASS_COLOR_NOISE.getValue((double)pos.getX() / 200.0, (double)pos.getZ() / 200.0);
      int i;
      if (d0 < -0.8) {
         i = rand.nextInt(4);
         switch (i) {
            case 0:
               return dR.ORANGE_TULIP;
            case 1:
               return dR.RED_TULIP;
            case 2:
               return dR.PINK_TULIP;
            case 3:
            default:
               return dR.WHITE_TULIP;
         }
      } else if (rand.nextInt(3) > 0) {
         i = rand.nextInt(3);
         if (i == 0) {
            return dR.POPPY;
         } else {
            return i == 1 ? dR.HOUSTONIA : dR.OXEYE_DAISY;
         }
      } else {
         return dR.DANDELION;
      }
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      double d0 = GRASS_COLOR_NOISE.getValue((double)(pos.getX() + 8) / 200.0, (double)(pos.getZ() + 8) / 200.0);
      int i1;
      int j1;
      int k1;
      int l1;
      if (d0 < -0.8) {
         this.decorator.flowersPerChunk = 15;
         this.decorator.grassPerChunk = 5;
      } else {
         this.decorator.flowersPerChunk = 4;
         this.decorator.grassPerChunk = 10;
         DOUBLE_PLANT_GENERATOR.setPlantType(dq.GRASS);

         for(i1 = 0; i1 < 7; ++i1) {
            j1 = rand.nextInt(16) + 8;
            k1 = rand.nextInt(16) + 8;
            l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
            DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
         }
      }

      if (this.sunflowers) {
         DOUBLE_PLANT_GENERATOR.setPlantType(dq.SUNFLOWER);

         for(i1 = 0; i1 < 10; ++i1) {
            j1 = rand.nextInt(16) + 8;
            k1 = rand.nextInt(16) + 8;
            l1 = rand.nextInt(worldIn.getHeight(pos.add(j1, 0, k1)).getY() + 32);
            DOUBLE_PLANT_GENERATOR.generate(worldIn, rand, pos.add(j1, l1, k1));
         }
      }

      super.decorate(worldIn, rand, pos);
   }

   public bbn getRandomTreeFeature(Random rand) {
      return (bbn)(rand.nextInt(3) == 0 ? BIG_TREE_FEATURE : TREE_FEATURE);
   }
}
