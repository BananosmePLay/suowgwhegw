package net.minecraft.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.boss.EntityDragon;
import net.minecraft.entity.boss.EntityWither;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityEnderEye;
import net.minecraft.entity.item.EntityEnderPearl;
import net.minecraft.entity.item.EntityExpBottle;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.item.EntityFireworkRocket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.IAnimals;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityEvokerFangs;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntityLlamaSpit;
import net.minecraft.entity.projectile.EntityPotion;
import net.minecraft.entity.projectile.EntityShulkerBullet;
import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketEntityAttach;
import net.minecraft.network.play.server.SPacketSetPassengers;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EntityTracker {
   private static final Logger LOGGER = LogManager.getLogger();
   private final WorldServer world;
   private final Set<EntityTrackerEntry> entries = Sets.newHashSet();
   private final IntHashMap<EntityTrackerEntry> trackedEntityHashTable = new IntHashMap();
   private int maxTrackingDistanceThreshold;

   public EntityTracker(WorldServer theWorldIn) {
      this.world = theWorldIn;
      this.maxTrackingDistanceThreshold = theWorldIn.getMinecraftServer().getPlayerList().getEntityViewDistance();
   }

   public static long getPositionLong(double value) {
      return MathHelper.lfloor(value * 4096.0);
   }

   public static void updateServerPosition(Entity entityIn, double x, double y, double z) {
      entityIn.serverPosX = getPositionLong(x);
      entityIn.serverPosY = getPositionLong(y);
      entityIn.serverPosZ = getPositionLong(z);
   }

   public void track(Entity entityIn) {
      if (entityIn instanceof EntityPlayerMP) {
         this.track(entityIn, 512, 2);
         EntityPlayerMP entityplayermp = (EntityPlayerMP)entityIn;
         Iterator var3 = this.entries.iterator();

         while(var3.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)var3.next();
            if (entitytrackerentry.getTrackedEntity() != entityplayermp) {
               entitytrackerentry.updatePlayerEntity(entityplayermp);
            }
         }
      } else if (entityIn instanceof EntityFishHook) {
         this.track(entityIn, 64, 5, true);
      } else if (entityIn instanceof EntityArrow) {
         this.track(entityIn, 64, 20, false);
      } else if (entityIn instanceof EntitySmallFireball) {
         this.track(entityIn, 64, 10, false);
      } else if (entityIn instanceof EntityFireball) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof EntitySnowball) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof EntityLlamaSpit) {
         this.track(entityIn, 64, 10, false);
      } else if (entityIn instanceof EntityEnderPearl) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof EntityEnderEye) {
         this.track(entityIn, 64, 4, true);
      } else if (entityIn instanceof EntityEgg) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof EntityPotion) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof EntityExpBottle) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof EntityFireworkRocket) {
         this.track(entityIn, 64, 10, true);
      } else if (entityIn instanceof EntityItem) {
         this.track(entityIn, 64, 20, true);
      } else if (entityIn instanceof EntityMinecart) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof EntityBoat) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof EntitySquid) {
         this.track(entityIn, 64, 3, true);
      } else if (entityIn instanceof EntityWither) {
         this.track(entityIn, 80, 3, false);
      } else if (entityIn instanceof EntityShulkerBullet) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof EntityBat) {
         this.track(entityIn, 80, 3, false);
      } else if (entityIn instanceof EntityDragon) {
         this.track(entityIn, 160, 3, true);
      } else if (entityIn instanceof IAnimals) {
         this.track(entityIn, 80, 3, true);
      } else if (entityIn instanceof EntityTNTPrimed) {
         this.track(entityIn, 160, 10, true);
      } else if (entityIn instanceof EntityFallingBlock) {
         this.track(entityIn, 160, 20, true);
      } else if (entityIn instanceof EntityHanging) {
         this.track(entityIn, 160, Integer.MAX_VALUE, false);
      } else if (entityIn instanceof EntityArmorStand) {
         this.track(entityIn, 160, 3, true);
      } else if (entityIn instanceof EntityXPOrb) {
         this.track(entityIn, 160, 20, true);
      } else if (entityIn instanceof EntityAreaEffectCloud) {
         this.track(entityIn, 160, Integer.MAX_VALUE, true);
      } else if (entityIn instanceof EntityEnderCrystal) {
         this.track(entityIn, 256, Integer.MAX_VALUE, false);
      } else if (entityIn instanceof EntityEvokerFangs) {
         this.track(entityIn, 160, 2, false);
      }

   }

   public void track(Entity entityIn, int trackingRange, int updateFrequency) {
      this.track(entityIn, trackingRange, updateFrequency, false);
   }

   public void track(Entity entityIn, int trackingRange, final int updateFrequency, boolean sendVelocityUpdates) {
      try {
         if (this.trackedEntityHashTable.containsItem(entityIn.getEntityId())) {
            throw new IllegalStateException("Entity is already tracked!");
         }

         EntityTrackerEntry entitytrackerentry = new EntityTrackerEntry(entityIn, trackingRange, this.maxTrackingDistanceThreshold, updateFrequency, sendVelocityUpdates);
         this.entries.add(entitytrackerentry);
         this.trackedEntityHashTable.addKey(entityIn.getEntityId(), entitytrackerentry);
         entitytrackerentry.updatePlayerEntities(this.world.playerEntities);
      } catch (Throwable var10) {
         Throwable throwable = var10;
         CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Adding entity to track");
         CrashReportCategory crashreportcategory = crashreport.makeCategory("Entity To Track");
         crashreportcategory.addCrashSection("Tracking range", trackingRange + " blocks");
         crashreportcategory.addDetail("Update interval", new ICrashReportDetail<String>() {
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
         ((EntityTrackerEntry)this.trackedEntityHashTable.lookup(entityIn.getEntityId())).getTrackedEntity().addEntityCrashInfo(crashreport.makeCategory("Entity That Is Already Tracked"));

         try {
            throw new ReportedException(crashreport);
         } catch (ReportedException var9) {
            ReportedException reportedexception = var9;
            LOGGER.error("\"Silently\" catching entity tracking error.", reportedexception);
         }
      }

   }

   public void untrack(Entity entityIn) {
      if (entityIn instanceof EntityPlayerMP) {
         EntityPlayerMP entityplayermp = (EntityPlayerMP)entityIn;
         Iterator var3 = this.entries.iterator();

         while(var3.hasNext()) {
            EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)var3.next();
            entitytrackerentry.removeFromTrackedPlayers(entityplayermp);
         }
      }

      EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)this.trackedEntityHashTable.removeObject(entityIn.getEntityId());
      if (entitytrackerentry1 != null) {
         this.entries.remove(entitytrackerentry1);
         entitytrackerentry1.sendDestroyEntityPacketToTrackedPlayers();
      }

   }

   public void tick() {
      List<EntityPlayerMP> list = Lists.newArrayList();
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)var2.next();
         entitytrackerentry.updatePlayerList(this.world.playerEntities);
         if (entitytrackerentry.playerEntitiesUpdated) {
            Entity entity = entitytrackerentry.getTrackedEntity();
            if (entity instanceof EntityPlayerMP) {
               list.add((EntityPlayerMP)entity);
            }
         }
      }

      for(int i = 0; i < list.size(); ++i) {
         EntityPlayerMP entityplayermp = (EntityPlayerMP)list.get(i);
         Iterator var8 = this.entries.iterator();

         while(var8.hasNext()) {
            EntityTrackerEntry entitytrackerentry1 = (EntityTrackerEntry)var8.next();
            if (entitytrackerentry1.getTrackedEntity() != entityplayermp) {
               entitytrackerentry1.updatePlayerEntity(entityplayermp);
            }
         }
      }

   }

   public void updateVisibility(EntityPlayerMP player) {
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)var2.next();
         if (entitytrackerentry.getTrackedEntity() == player) {
            entitytrackerentry.updatePlayerEntities(this.world.playerEntities);
         } else {
            entitytrackerentry.updatePlayerEntity(player);
         }
      }

   }

   public void sendToTracking(Entity entityIn, Packet<?> packetIn) {
      EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(entityIn.getEntityId());
      if (entitytrackerentry != null) {
         entitytrackerentry.sendPacketToTrackedPlayers(packetIn);
      }

   }

   public void sendToTrackingAndSelf(Entity entityIn, Packet<?> packetIn) {
      EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)this.trackedEntityHashTable.lookup(entityIn.getEntityId());
      if (entitytrackerentry != null) {
         entitytrackerentry.sendToTrackingAndSelf(packetIn);
      }

   }

   public void removePlayerFromTrackers(EntityPlayerMP player) {
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)var2.next();
         entitytrackerentry.removeTrackedPlayerSymmetric(player);
      }

   }

   public void sendLeashedEntitiesInChunk(EntityPlayerMP player, Chunk chunkIn) {
      List<Entity> list = Lists.newArrayList();
      List<Entity> list1 = Lists.newArrayList();
      Iterator var5 = this.entries.iterator();

      while(var5.hasNext()) {
         EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)var5.next();
         Entity entity = entitytrackerentry.getTrackedEntity();
         if (entity != player && entity.chunkCoordX == chunkIn.x && entity.chunkCoordZ == chunkIn.z) {
            entitytrackerentry.updatePlayerEntity(player);
            if (entity instanceof EntityLiving && ((EntityLiving)entity).getLeashHolder() != null) {
               list.add(entity);
            }

            if (!entity.getPassengers().isEmpty()) {
               list1.add(entity);
            }
         }
      }

      Entity entity2;
      if (!list.isEmpty()) {
         var5 = list.iterator();

         while(var5.hasNext()) {
            entity2 = (Entity)var5.next();
            player.connection.sendPacket(new SPacketEntityAttach(entity2, ((EntityLiving)entity2).getLeashHolder()));
         }
      }

      if (!list1.isEmpty()) {
         var5 = list1.iterator();

         while(var5.hasNext()) {
            entity2 = (Entity)var5.next();
            player.connection.sendPacket(new SPacketSetPassengers(entity2));
         }
      }

   }

   public void setViewDistance(int distance) {
      this.maxTrackingDistanceThreshold = (distance - 1) * 16;
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         EntityTrackerEntry entitytrackerentry = (EntityTrackerEntry)var2.next();
         entitytrackerentry.setMaxRange(this.maxTrackingDistanceThreshold);
      }

   }
}
