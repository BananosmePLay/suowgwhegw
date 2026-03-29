package neo;

public class Zj extends Zi {
   public Zj(Zf properties) {
      super(properties);
      this.spawnableCreatureList.clear();
      this.topBlock = Nk.SAND.getDefaultState();
      this.fillerBlock = Nk.SAND.getDefaultState();
      this.decorator.treesPerChunk = -999;
      this.decorator.deadBushPerChunk = 0;
      this.decorator.reedsPerChunk = 0;
      this.decorator.cactiPerChunk = 0;
   }
}
