package neo;

public class ZI extends Zi {
   public ZI(Zf properties) {
      super(properties);
      this.decorator.treesPerChunk = -100;
      this.decorator.flowersPerChunk = -100;
      this.decorator.grassPerChunk = -100;
      this.decorator.mushroomsPerChunk = 1;
      this.decorator.bigMushroomsPerChunk = 1;
      this.topBlock = Nk.MYCELIUM.getDefaultState();
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCreatureList.add(new Zg(LL.class, 8, 4, 8));
   }
}
