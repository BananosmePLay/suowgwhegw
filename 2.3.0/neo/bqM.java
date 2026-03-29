package neo;

import net.minecraft.util.math.MathHelper;

public class bqM {
   public bqM() {
   }

   public static int getCountBlocks(ug renderChunk) {
      baB[] aextendedblockstorage = renderChunk.getChunk().getBlockStorageArray();
      if (aextendedblockstorage == null) {
         return 0;
      } else {
         int i = renderChunk.getPosition().getY() >> 4;
         baB extendedblockstorage = aextendedblockstorage[i];
         return extendedblockstorage == null ? 0 : extendedblockstorage.getBlockRefCount();
      }
   }

   public static double getRelativeBufferSize(ug renderChunk) {
      int i = getCountBlocks(renderChunk);
      double d0 = getRelativeBufferSize(i);
      return d0;
   }

   public static double getRelativeBufferSize(int blockCount) {
      double d0 = (double)blockCount / 4096.0;
      d0 *= 0.995;
      double d1 = d0 * 2.0 - 1.0;
      d1 = MathHelper.clamp(d1, -1.0, 1.0);
      return (double)MathHelper.sqrt(1.0 - d1 * d1);
   }
}
