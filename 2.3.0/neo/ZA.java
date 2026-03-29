package neo;

public class ZA extends Zi {
   public ZA(Zf properties) {
      super(properties);
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCaveCreatureList.clear();
      this.spawnableMonsterList.add(new Zg(JW.class, 50, 4, 4));
      this.spawnableMonsterList.add(new Zg(Ko.class, 100, 4, 4));
      this.spawnableMonsterList.add(new Zg(Kk.class, 2, 4, 4));
      this.spawnableMonsterList.add(new Zg(JJ.class, 1, 4, 4));
      this.decorator = new ZB();
   }
}
