package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class ZQ extends Zi {
   private final boolean superIcy;
   private final bbO iceSpike = new bbO();
   private final bbN icePatch = new bbN(4);

   public ZQ(boolean superIcyIn, Zf properties) {
      super(properties);
      this.superIcy = superIcyIn;
      if (superIcyIn) {
         this.topBlock = Nk.SNOW.getDefaultState();
      }

      this.spawnableCreatureList.clear();
      this.spawnableCreatureList.add(new Zg(LY.class, 10, 2, 3));
      this.spawnableCreatureList.add(new Zg(Kv.class, 1, 1, 2));
      Iterator<Zg> iterator = this.spawnableMonsterList.iterator();

      while(iterator.hasNext()) {
         Zg biome$spawnlistentry = (Zg)iterator.next();
         if (biome$spawnlistentry.entityClass == KH.class) {
            iterator.remove();
         }
      }

      this.spawnableMonsterList.add(new Zg(KH.class, 20, 4, 4));
      this.spawnableMonsterList.add(new Zg(KX.class, 80, 4, 4));
   }

   public float getSpawningChance() {
      return 0.07F;
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      if (this.superIcy) {
         int l;
         int i1;
         int j1;
         for(l = 0; l < 3; ++l) {
            i1 = rand.nextInt(16) + 8;
            j1 = rand.nextInt(16) + 8;
            this.iceSpike.generate(worldIn, rand, worldIn.getHeight(pos.add(i1, 0, j1)));
         }

         for(l = 0; l < 2; ++l) {
            i1 = rand.nextInt(16) + 8;
            j1 = rand.nextInt(16) + 8;
            this.icePatch.generate(worldIn, rand, worldIn.getHeight(pos.add(i1, 0, j1)));
         }
      }

      super.decorate(worldIn, rand, pos);
   }

   public bbn getRandomTreeFeature(Random rand) {
      return new bcg(false);
   }
}
