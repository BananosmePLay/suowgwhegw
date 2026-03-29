package neo;

import net.minecraft.util.math.BlockPos;

public class biP {
   public biP() {
   }

   public static void updateWaterOpacity(Bj settings, bij world) {
      if (settings != null) {
         int i = 3;
         if (settings.ofClearWater) {
            i = 1;
         }

         cp.setLightOpacity(Nk.WATER, i);
         cp.setLightOpacity(Nk.FLOWING_WATER, i);
      }

      if (world != null) {
         bar ichunkprovider = world.getChunkProvider();
         if (ichunkprovider != null) {
            Ig entity = XH.getMinecraft().getRenderViewEntity();
            if (entity != null) {
               int j = (int)entity.posX / 16;
               int k = (int)entity.posZ / 16;
               int l = j - 512;
               int i1 = j + 512;
               int j1 = k - 512;
               int k1 = k + 512;
               int l1 = 0;

               for(int i2 = l; i2 < i1; ++i2) {
                  for(int j2 = j1; j2 < k1; ++j2) {
                     bam chunk = ichunkprovider.getLoadedChunk(i2, j2);
                     if (chunk != null && !(chunk instanceof bao)) {
                        int k2 = i2 << 4;
                        int l2 = j2 << 4;
                        int i3 = k2 + 16;
                        int j3 = l2 + 16;
                        biN blockposm = new biN(0, 0, 0);
                        biN blockposm1 = new biN(0, 0, 0);

                        for(int k3 = k2; k3 < i3; ++k3) {
                           for(int l3 = l2; l3 < j3; ++l3) {
                              blockposm.setXyz(k3, 0, l3);
                              BlockPos blockpos = world.getPrecipitationHeight(blockposm);

                              for(int i4 = 0; i4 < blockpos.getY(); ++i4) {
                                 blockposm1.setXyz(k3, i4, l3);
                                 in iblockstate = world.getBlockState(blockposm1);
                                 if (iblockstate.getMaterial() == hM.WATER) {
                                    world.markBlocksDirtyVertical(k3, l3, blockposm1.getY(), blockpos.getY());
                                    ++l1;
                                    break;
                                 }
                              }
                           }
                        }
                     }
                  }
               }

               if (l1 > 0) {
                  String s = "server";
                  if (XH.isMinecraftThread()) {
                     s = "client";
                  }

                  XH.dbg("ClearWater (" + s + ") relighted " + l1 + " chunks");
               }
            }
         }
      }

   }
}
