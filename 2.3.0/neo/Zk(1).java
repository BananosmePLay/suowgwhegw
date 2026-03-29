package neo;

public class Zk {
   public Zi[] biomes;
   public int x;
   public int z;
   public long lastAccessTime;
   // $FF: synthetic field
   final Zl this$0;

   public Zk(Zl this$0, int x, int z) {
      this.this$0 = this$0;
      this.biomes = new Zi[256];
      this.x = x;
      this.z = z;
      Zl.access$000(this$0).getBiomes(this.biomes, x << 4, z << 4, 16, 16, false);
   }

   public Zi getBiome(int x, int z) {
      return this.biomes[x & 15 | (z & 15) << 4];
   }
}
