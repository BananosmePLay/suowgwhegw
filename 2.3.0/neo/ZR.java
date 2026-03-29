package neo;

public class ZR extends Zi {
   public ZR(Zf properties) {
      super(properties);
      this.spawnableCreatureList.clear();
      this.topBlock = Nk.STONE.getDefaultState();
      this.fillerBlock = Nk.STONE.getDefaultState();
      this.decorator.treesPerChunk = -999;
      this.decorator.deadBushPerChunk = 0;
      this.decorator.reedsPerChunk = 0;
      this.decorator.cactiPerChunk = 0;
   }
}
