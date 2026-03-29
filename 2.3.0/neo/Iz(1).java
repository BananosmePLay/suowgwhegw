package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Iz {
   private static final Logger LOGGER = LogManager.getLogger();
   private final bis world;
   private final Set<IA> entries = Sets.newHashSet();
   private final IntHashMap<IA> trackedEntityHashTable = new IntHashMap();
   private int maxTrackingDistanceThreshold;

   public Iz(bis theWorldIn) {
      this.world = theWorldIn;
      this.maxTrackingDistanceThreshold = theWorldIn.getMinecraftServer().getPlayerList().getEntityViewDistance();
   }

   public static long getPositionLong(double value) {
      return MathHelper.lfloor(value * 4096.0);
   }

   public static void updateServerPosition(Ig entityIn, double x, double y, double z) {
      entityIn.serverPosX = getPositionLong(x);
      entityIn.serverPosY = getPositionLong(y);
      entityIn.serverPosZ = getPositionLong(z);
   }

   public void track(Ig entityIn) {
      if (entityIn instanceof MG) {
         this.track(entityIn, 512, 2);
         MG entityplayermp = (MG)entityIn;
         Iterator var3 = this.entries.iterator();

         while(var3.hasNext()) {
            IA entitytrackerentry = (IA)var3.next();
            if (entitytrackerentry.getTrackedEntity() != entityplayermp) {
               entitytrackerentry.updatePlayerEntity(entityplayermp);
            }
         }
      } else if (entityIn instanceof MU) {
         this.track(entityIn, 64, 5, true);
      } else if (entityIn instanceof MO) {
         this.track(entityIn, 64, 20, false);
      } else if (entityIn instanceof Na) {
         this.track(entityIn, 64, 10, false);
      } else if (entityIn instanceof MS) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof Nb) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof MW) {
         this.track(entityIn, 64, 10, false);
      } else if (entityIn instanceof IU) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof IT) {
         this.track(entityIn, 64, 4, true);
      } else if (entityIn instanceof MQ) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof MY) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof IV) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof IX) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof IY) {
         this.track(entityIn, 64, 20, true);
      } else if (entityIn instanceof Jc) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof IR) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof Mf) {
         this.track(entityIn, 64, 3, true);
      } else if (entityIn instanceof HV) {
         this.track(entityIn, 80, 3, false);
      } else if (entityIn instanceof MZ) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof Lz) {
         this.track(entityIn, 80, 3, false);
      } else if (entityIn instanceof HS) {
         this.track(entityIn, 160, 3, true);
      } else if (entityIn instanceof Mx) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof Jr) {
         this.track(entityIn, 160, 10, true);
      } else if (entityIn instanceof IW) {
         this.track(entityIn, 160, 20, true);
      } else if (entityIn instanceof Io) {
         this.track(entityIn, 160, Integer.MAX_VALUE, false);
      } else if (entityIn instanceof IN) {
         this.track(entityIn, 160, 3, true);
      } else if (entityIn instanceof Js) {
         this.track(entityIn, 160, 20, true);
      } else if (entityIn instanceof Ii) {
         this.track(entityIn, 160, Integer.MAX_VALUE, true);
      } else if (entityIn instanceof IS) {
         this.track(entityIn, 256, Integer.MAX_VALUE, false);
      } else if (entityIn instanceof MR) {
         this.track(entityIn, 160, 2, false);
      }

   }

   public void track(Ig entityIn, int trackingRange, int updateFrequency) {
      this.track(entityIn, trackingRange, updateFrequency, false);
   }

   public void track(Ig entityIn, int trackingRange, final int updateFrequency, boolean sendVelocityUpdates) {
      try {
         if (this.trackedEntityHashTable.containsItem(entityIn.getEntityId())) {
            throw new IllegalStateException("Entity is already tracked!");
         }

         IA entitytrackerentry = new IA(entityIn, trackingRange, this.maxTrackingDistanceThreshold, updateFrequency, sendVelocityUpdates);
         this.entries.add(entitytrackerentry);
         this.trackedEntityHashTable.addKey(entityIn.getEntityId(), entitytrackerentry);
         entitytrackerentry.updatePlayerEntities(this.world.playerEntities);
      } catch (Throwable var10) {
         Throwable throwable = var10;
         Er crashreport = Er.makeCrashReport(throwable, "Adding entity to track");
         Ey crashreportcategory = crashreport.makeCategory("Entity To Track");
         crashreportcategory.addCrashSection("Tracking range", trackingRange + " blocks");
         crashreportcategory.addDetail("Update interval", new Ez<String>() {
            public String call() throws Exception {
               String s = "Once per " + updateFrequency + " ticks";
               if (updateFrequency == Integer.MAX_VALUE) {
                  s = "Maximum (" + s + ")";
               }

               return s;
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         entityIn.addEntityCrashInfo(crashreportcategory);
         ((IA)this.trackedEntityHashTable.lookup(entityIn.getEntityId())).getTrackedEntity().addEntityCrashInfo(crashreport.makeCategory("Entity That Is Already Tracked"));

         try {
            throw new ReportedException(crashreport);
         } catch (ReportedException var9) {
            ReportedException reportedexception = var9;
            LOGGER.error("\"Silently\" catching entity tracking error.", reportedexception);
         }
      }

   }

   public void untrack(Ig entityIn) {
      if (entityIn instanceof MG) {
         MG entityplayermp = (MG)entityIn;
         Iterator var3 = this.entries.iterator();

         while(var3.hasNext()) {
            IA entitytrackerentry = (IA)var3.next();
            entitytrackerentry.removeFromTrackedPlayers(entityplayermp);
         }
      }

      IA entitytrackerentry1 = (IA)this.trackedEntityHashTable.removeObject(entityIn.getEntityId());
      if (entitytrackerentry1 != null) {
         this.entries.remove(entitytrackerentry1);
         entitytrackerentry1.sendDestroyEntityPacketToTrackedPlayers();
      }

   }

   public void tick() {
      List<MG> list = Lists.newArrayList();
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         IA entitytrackerentry = (IA)var2.next();
         entitytrackerentry.updatePlayerList(this.world.playerEntities);
         if (entitytrackerentry.playerEntitiesUpdated) {
            Ig entity = entitytrackerentry.getTrackedEntity();
            if (entity instanceof MG) {
               list.add((MG)entity);
            }
         }
      }

      for(int i = 0; i < list.size(); ++i) {
         MG entityplayermp = (MG)list.get(i);
         Iterator var8 = this.entries.iterator();

         while(var8.hasNext()) {
            IA entitytrackerentry1 = (IA)var8.next();
            if (entitytrackerentry1.getTrackedEntity() != entityplayermp) {
               entitytrackerentry1.updatePlayerEntity(entityplayermp);
            }
         }
      }

   }

   public void updateVisibility(MG player) {
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         IA entitytrackerentry = (IA)var2.next();
         if (entitytrackerentry.getTrackedEntity() == player) {
            entitytrackerentry.updatePlayerEntities(this.world.playerEntities);
         } else {
            entitytrackerentry.updatePlayerEntity(player);
         }
      }

   }

   public void sendToTracking(Ig entityIn, Sz<?> packetIn) {
      IA entitytrackerentry = (IA)this.trackedEntityHashTable.lookup(entityIn.getEntityId());
      if (entitytrackerentry != null) {
         entitytrackerentry.sendPacketToTrackedPlayers(packetIn);
      }

   }

   public void sendToTrackingAndSelf(Ig entityIn, Sz<?> packetIn) {
      IA entitytrackerentry = (IA)this.trackedEntityHashTable.lookup(entityIn.getEntityId());
      if (entitytrackerentry != null) {
         entitytrackerentry.sendToTrackingAndSelf(packetIn);
      }

   }

   public void removePlayerFromTrackers(MG player) {
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         IA entitytrackerentry = (IA)var2.next();
         entitytrackerentry.removeTrackedPlayerSymmetric(player);
      }

   }

   public void sendLeashedEntitiesInChunk(MG player, bam chunkIn) {
      List<Ig> list = Lists.newArrayList();
      List<Ig> list1 = Lists.newArrayList();
      Iterator var5 = this.entries.iterator();

      while(var5.hasNext()) {
         IA entitytrackerentry = (IA)var5.next();
         Ig entity = entitytrackerentry.getTrackedEntity();
         if (entity != player && entity.chunkCoordX == chunkIn.x && entity.chunkCoordZ == chunkIn.z) {
            entitytrackerentry.updatePlayerEntity(player);
            if (entity instanceof Iu && ((Iu)entity).getLeashHolder() != null) {
               list.add(entity);
            }

            if (!entity.getPassengers().isEmpty()) {
               list1.add(entity);
            }
         }
      }

      Ig entity2;
      if (!list.isEmpty()) {
         var5 = list.iterator();

         while(var5.hasNext()) {
            entity2 = (Ig)var5.next();
            player.connection.sendPacket(new TW(entity2, ((Iu)entity2).getLeashHolder()));
         }
      }

      if (!list1.isEmpty()) {
         var5 = list1.iterator();

         while(var5.hasNext()) {
            entity2 = (Ig)var5.next();
            player.connection.sendPacket(new UI(entity2));
         }
      }

   }

   public void setViewDistance(int distance) {
      this.maxTrackingDistanceThreshold = (distance - 1) * 16;
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         IA entitytrackerentry = (IA)var2.next();
         entitytrackerentry.setMaxRange(this.maxTrackingDistanceThreshold);
      }

   }
}
