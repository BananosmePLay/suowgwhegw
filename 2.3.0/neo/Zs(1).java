package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class Zs extends Zi {
   public Zs(Zf properties) {
      super(properties);
      this.spawnableCreatureList.clear();
      this.topBlock = Nk.SAND.getDefaultState();
      this.fillerBlock = Nk.SAND.getDefaultState();
      this.decorator.treesPerChunk = -999;
      this.decorator.deadBushPerChunk = 2;
      this.decorator.reedsPerChunk = 50;
      this.decorator.cactiPerChunk = 10;
      this.spawnableCreatureList.clear();
      this.spawnableCreatureList.add(new Zg(LY.class, 4, 2, 3));
      Iterator<Zg> iterator = this.spawnableMonsterList.iterator();

      while(true) {
         Zg biome$spawnlistentry;
         do {
            if (!iterator.hasNext()) {
               this.spawnableMonsterList.add(new Zg(Lk.class, 19, 4, 4));
               this.spawnableMonsterList.add(new Zg(Ll.class, 1, 1, 1));
               this.spawnableMonsterList.add(new Zg(Kd.class, 80, 4, 4));
               return;
            }

            biome$spawnlistentry = (Zg)iterator.next();
         } while(biome$spawnlistentry.entityClass != Lk.class && biome$spawnlistentry.entityClass != Ll.class);

         iterator.remove();
      }
   }

   public void decorate(bij worldIn, Random rand, BlockPos pos) {
      super.decorate(worldIn, rand, pos);
      if (rand.nextInt(1000) == 0) {
         int i = rand.nextInt(16) + 8;
         int j = rand.nextInt(16) + 8;
         BlockPos blockpos = worldIn.getHeight(pos.add(i, 0, j)).up();
         (new bby()).generate(worldIn, rand, blockpos);
      }

      if (rand.nextInt(64) == 0) {
         (new bbI()).generate(worldIn, rand, pos);
      }

   }
}
