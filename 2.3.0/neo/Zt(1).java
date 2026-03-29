package neo;

public class Zt extends Zi {
   public Zt(Zf properties) {
      super(properties);
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCaveCreatureList.clear();
      this.spawnableMonsterList.add(new Zg(JJ.class, 10, 4, 4));
      this.topBlock = Nk.DIRT.getDefaultState();
      this.fillerBlock = Nk.DIRT.getDefaultState();
      this.decorator = new Zw();
   }

   public int getSkyColorByTemp(float currentTemperature) {
      return 0;
   }
}
