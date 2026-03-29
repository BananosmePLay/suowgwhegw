package neo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class bpu {
   public bpu() {
   }

   public static Iterator<ug> makeShadowChunkIterator(pm world, double partialTicks, Ig viewEntity, int renderDistanceChunks, zT viewFrustum) {
      float f = bpq.getShadowRenderDistance();
      if (f > 0.0F && f < (float)((renderDistanceChunks - 1) * 16)) {
         int i = MathHelper.ceil(f / 16.0F) + 1;
         float f6 = world.getCelestialAngleRadians((float)partialTicks);
         float f1 = bpq.sunPathRotation * MathHelper.deg2Rad;
         float f2 = f6 > MathHelper.PId2 && f6 < 3.0F * MathHelper.PId2 ? f6 + MathHelper.PI : f6;
         float f3 = -MathHelper.sin(f2);
         float f4 = MathHelper.cos(f2) * MathHelper.cos(f1);
         float f5 = -MathHelper.cos(f2) * MathHelper.sin(f1);
         BlockPos blockpos = new BlockPos(MathHelper.floor(viewEntity.posX) >> 4, MathHelper.floor(viewEntity.posY) >> 4, MathHelper.floor(viewEntity.posZ) >> 4);
         BlockPos blockpos1 = blockpos.add((double)(-f3 * (float)i), (double)(-f4 * (float)i), (double)(-f5 * (float)i));
         BlockPos blockpos2 = blockpos.add((double)(f3 * (float)renderDistanceChunks), (double)(f4 * (float)renderDistanceChunks), (double)(f5 * (float)renderDistanceChunks));
         bpe iteratorrenderchunks = new bpe(viewFrustum, blockpos1, blockpos2, i, i);
         return iteratorrenderchunks;
      } else {
         List<ug> list = Arrays.asList(viewFrustum.renderChunks);
         Iterator<ug> iterator = list.iterator();
         return iterator;
      }
   }
}
