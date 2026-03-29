package neo;

public class ZV extends Zi {
   public ZV(Zf properties) {
      super(properties);
      this.spawnableMonsterList.clear();
      this.spawnableCreatureList.clear();
      this.spawnableWaterCreatureList.clear();
      this.spawnableCaveCreatureList.clear();
      this.decorator = new ZW();
   }

   public boolean ignorePlayerSpawnSuitability() {
      return true;
   }
}
