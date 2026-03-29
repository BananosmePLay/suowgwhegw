package net.optifine;

import java.util.Comparator;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MathHelper;

public class ChunkPosComparator implements Comparator<ChunkPos> {
   private int chunkPosX;
   private int chunkPosZ;
   private double yawRad;
   private double pitchNorm;

   public ChunkPosComparator(int chunkPosX, int chunkPosZ, double yawRad, double pitchRad) {
      this.chunkPosX = chunkPosX;
      this.chunkPosZ = chunkPosZ;
      this.yawRad = yawRad;
      this.pitchNorm = 1.0 - MathHelper.clamp(Math.abs(pitchRad) / 1.5707963267948966, 0.0, 1.0);
   }

   public int compare(ChunkPos cp1, ChunkPos cp2) {
      int i = this.getDistSq(cp1);
      int j = this.getDistSq(cp2);
      return i - j;
   }

   private int getDistSq(ChunkPos cp) {
      int i = cp.x - this.chunkPosX;
      int j = cp.z - this.chunkPosZ;
      int k = i * i + j * j;
      double d0 = MathHelper.atan2((double)j, (double)i);
      double d1 = Math.abs(d0 - this.yawRad);
      if (d1 > Math.PI) {
         d1 = 6.283185307179586 - d1;
      }

      k = (int)((double)k * 1000.0 * this.pitchNorm * d1 * d1);
      return k;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public int compare(Object var1, Object var2) {
      return this.compare((ChunkPos)var1, (ChunkPos)var2);
   }
}
