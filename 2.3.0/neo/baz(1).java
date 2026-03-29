package neo;

public class baz {
   public long lastUpdated;
   public boolean terrainPopulated;
   public byte[] heightmap;
   public baD blockLight;
   public baD skyLight;
   public baD data;
   public byte[] blocks;
   public QW entities;
   public QW tileEntities;
   public QW tileTicks;
   public final int x;
   public final int z;

   public baz(int xIn, int zIn) {
      this.x = xIn;
      this.z = zIn;
   }
}
