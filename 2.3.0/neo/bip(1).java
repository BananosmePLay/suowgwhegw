package neo;

public class bip extends bil {
   public bip() {
   }

   public baM getDimensionType() {
      return baM.OVERWORLD;
   }

   public boolean canDropChunk(int x, int z) {
      return !this.world.isSpawnChunk(x, z);
   }
}
