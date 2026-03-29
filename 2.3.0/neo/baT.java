package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.BlockPos;

public enum baT {
   START {
      public void process(bis worldIn, baN manager, List<IS> crystals, int ticks, BlockPos pos) {
         BlockPos blockpos = new BlockPos(0, 128, 0);
         Iterator var7 = crystals.iterator();

         while(var7.hasNext()) {
            IS entityendercrystal = (IS)var7.next();
            entityendercrystal.setBeamTarget(blockpos);
         }

         manager.setRespawnState(PREPARING_TO_SUMMON_PILLARS);
      }
   },
   PREPARING_TO_SUMMON_PILLARS {
      public void process(bis worldIn, baN manager, List<IS> crystals, int ticks, BlockPos pos) {
         if (ticks < 100) {
            if (ticks == 0 || ticks == 50 || ticks == 51 || ticks == 52 || ticks >= 95) {
               worldIn.playEvent(3001, new BlockPos(0, 128, 0), 0);
            }
         } else {
            manager.setRespawnState(SUMMONING_PILLARS);
         }

      }
   },
   SUMMONING_PILLARS {
      public void process(bis worldIn, baN manager, List<IS> crystals, int ticks, BlockPos pos) {
         int i = true;
         boolean flag = ticks % 40 == 0;
         boolean flag1 = ticks % 40 == 39;
         if (flag || flag1) {
            bcc[] aworldgenspikes$endspike = Zw.getSpikesForWorld(worldIn);
            int j = ticks / 40;
            if (j < aworldgenspikes$endspike.length) {
               bcc worldgenspikes$endspike = aworldgenspikes$endspike[j];
               if (flag) {
                  Iterator var12 = crystals.iterator();

                  while(var12.hasNext()) {
                     IS entityendercrystal = (IS)var12.next();
                     entityendercrystal.setBeamTarget(new BlockPos(worldgenspikes$endspike.getCenterX(), worldgenspikes$endspike.getHeight() + 1, worldgenspikes$endspike.getCenterZ()));
                  }
               } else {
                  int k = true;
                  Iterator var16 = BlockPos.getAllInBoxMutable(new BlockPos(worldgenspikes$endspike.getCenterX() - 10, worldgenspikes$endspike.getHeight() - 10, worldgenspikes$endspike.getCenterZ() - 10), new BlockPos(worldgenspikes$endspike.getCenterX() + 10, worldgenspikes$endspike.getHeight() + 10, worldgenspikes$endspike.getCenterZ() + 10)).iterator();

                  while(var16.hasNext()) {
                     BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var16.next();
                     worldIn.setBlockToAir(blockpos$mutableblockpos);
                  }

                  worldIn.createExplosion((Ig)null, (double)((float)worldgenspikes$endspike.getCenterX() + 0.5F), (double)worldgenspikes$endspike.getHeight(), (double)((float)worldgenspikes$endspike.getCenterZ() + 0.5F), 5.0F, true);
                  bcd worldgenspikes = new bcd();
                  worldgenspikes.setSpike(worldgenspikes$endspike);
                  worldgenspikes.setCrystalInvulnerable(true);
                  worldgenspikes.setBeamTarget(new BlockPos(0, 128, 0));
                  worldgenspikes.generate(worldIn, new Random(), new BlockPos(worldgenspikes$endspike.getCenterX(), 45, worldgenspikes$endspike.getCenterZ()));
               }
            } else if (flag) {
               manager.setRespawnState(SUMMONING_DRAGON);
            }
         }

      }
   },
   SUMMONING_DRAGON {
      public void process(bis worldIn, baN manager, List<IS> crystals, int ticks, BlockPos pos) {
         Iterator var6;
         IS entityendercrystal1;
         if (ticks >= 100) {
            manager.setRespawnState(END);
            manager.resetSpikeCrystals();
            var6 = crystals.iterator();

            while(var6.hasNext()) {
               entityendercrystal1 = (IS)var6.next();
               entityendercrystal1.setBeamTarget((BlockPos)null);
               worldIn.createExplosion(entityendercrystal1, entityendercrystal1.posX, entityendercrystal1.posY, entityendercrystal1.posZ, 6.0F, false);
               entityendercrystal1.setDead();
            }
         } else if (ticks >= 80) {
            worldIn.playEvent(3001, new BlockPos(0, 128, 0), 0);
         } else if (ticks == 0) {
            var6 = crystals.iterator();

            while(var6.hasNext()) {
               entityendercrystal1 = (IS)var6.next();
               entityendercrystal1.setBeamTarget(new BlockPos(0, 128, 0));
            }
         } else if (ticks < 5) {
            worldIn.playEvent(3001, new BlockPos(0, 128, 0), 0);
         }

      }
   },
   END {
      public void process(bis worldIn, baN manager, List<IS> crystals, int ticks, BlockPos pos) {
      }
   };

   private baT() {
   }

   public abstract void process(bis var1, baN var2, List<IS> var3, int var4, BlockPos var5);

   // $FF: synthetic method
   baT(Object x2) {
      this();
   }
}
