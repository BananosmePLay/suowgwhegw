package neo;

import com.google.common.collect.Sets;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IA {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Ig trackedEntity;
   private final int range;
   private int maxRange;
   private final int updateFrequency;
   private long encodedPosX;
   private long encodedPosY;
   private long encodedPosZ;
   private int encodedRotationYaw;
   private int encodedRotationPitch;
   private int lastHeadMotion;
   private double lastTrackedEntityMotionX;
   private double lastTrackedEntityMotionY;
   private double motionZ;
   public int updateCounter;
   private double lastTrackedEntityPosX;
   private double lastTrackedEntityPosY;
   private double lastTrackedEntityPosZ;
   private boolean updatedPlayerVisibility;
   private final boolean sendVelocityUpdates;
   private int ticksSinceLastForcedTeleport;
   private List<Ig> passengers = Collections.emptyList();
   private boolean ridingEntity;
   private boolean onGround;
   public boolean playerEntitiesUpdated;
   private final Set<MG> trackingPlayers = Sets.newHashSet();

   public IA(Ig entityIn, int rangeIn, int maxRangeIn, int updateFrequencyIn, boolean sendVelocityUpdatesIn) {
      this.trackedEntity = entityIn;
      this.range = rangeIn;
      this.maxRange = maxRangeIn;
      this.updateFrequency = updateFrequencyIn;
      this.sendVelocityUpdates = sendVelocityUpdatesIn;
      this.encodedPosX = Iz.getPositionLong(entityIn.posX);
      this.encodedPosY = Iz.getPositionLong(entityIn.posY);
      this.encodedPosZ = Iz.getPositionLong(entityIn.posZ);
      this.encodedRotationYaw = MathHelper.floor(entityIn.rotationYaw * 256.0F / 360.0F);
      this.encodedRotationPitch = MathHelper.floor(entityIn.rotationPitch * 256.0F / 360.0F);
      this.lastHeadMotion = MathHelper.floor(entityIn.getRotationYawHead() * 256.0F / 360.0F);
      this.onGround = entityIn.onGround;
   }

   public boolean equals(Object p_equals_1_) {
      if (p_equals_1_ instanceof IA) {
         return ((IA)p_equals_1_).trackedEntity.getEntityId() == this.trackedEntity.getEntityId();
      } else {
         return false;
      }
   }

   public int hashCode() {
      return this.trackedEntity.getEntityId();
   }

   public void updatePlayerList(List<ME> players) {
      this.playerEntitiesUpdated = false;
      if (!this.updatedPlayerVisibility || this.trackedEntity.getDistanceSq(this.lastTrackedEntityPosX, this.lastTrackedEntityPosY, this.lastTrackedEntityPosZ) > 16.0) {
         this.lastTrackedEntityPosX = this.trackedEntity.posX;
         this.lastTrackedEntityPosY = this.trackedEntity.posY;
         this.lastTrackedEntityPosZ = this.trackedEntity.posZ;
         this.updatedPlayerVisibility = true;
         this.playerEntitiesUpdated = true;
         this.updatePlayerEntities(players);
      }

      List<Ig> list = this.trackedEntity.getPassengers();
      if (!list.equals(this.passengers)) {
         this.passengers = list;
         this.sendPacketToTrackedPlayers(new UI(this.trackedEntity));
      }

      if (this.trackedEntity instanceof IZ && this.updateCounter % 10 == 0) {
         IZ entityitemframe = (IZ)this.trackedEntity;
         Qy itemstack = entityitemframe.getDisplayedItem();
         if (itemstack.getItem() instanceof PT) {
            bhE mapdata = NK.FILLED_MAP.getMapData(itemstack, this.trackedEntity.world);
            Iterator var6 = players.iterator();

            while(var6.hasNext()) {
               ME entityplayer = (ME)var6.next();
               MG entityplayermp = (MG)entityplayer;
               mapdata.updateVisiblePlayers(entityplayermp, itemstack);
               Sz<?> packet = NK.FILLED_MAP.createMapDataPacket(itemstack, this.trackedEntity.world, entityplayermp);
               if (packet != null) {
                  entityplayermp.connection.sendPacket(packet);
               }
            }
         }

         this.sendMetadata();
      }

      if (this.updateCounter % this.updateFrequency == 0 || this.trackedEntity.isAirBorne || this.trackedEntity.getDataManager().isDirty()) {
         int k1;
         if (this.trackedEntity.isRiding()) {
            k1 = MathHelper.floor(this.trackedEntity.rotationYaw * 256.0F / 360.0F);
            int l1 = MathHelper.floor(this.trackedEntity.rotationPitch * 256.0F / 360.0F);
            boolean flag3 = Math.abs(k1 - this.encodedRotationYaw) >= 1 || Math.abs(l1 - this.encodedRotationPitch) >= 1;
            if (flag3) {
               this.sendPacketToTrackedPlayers(new TT(this.trackedEntity.getEntityId(), (byte)k1, (byte)l1, this.trackedEntity.onGround));
               this.encodedRotationYaw = k1;
               this.encodedRotationPitch = l1;
            }

            this.encodedPosX = Iz.getPositionLong(this.trackedEntity.posX);
            this.encodedPosY = Iz.getPositionLong(this.trackedEntity.posY);
            this.encodedPosZ = Iz.getPositionLong(this.trackedEntity.posZ);
            this.sendMetadata();
            this.ridingEntity = true;
         } else {
            ++this.ticksSinceLastForcedTeleport;
            long i1 = Iz.getPositionLong(this.trackedEntity.posX);
            long i2 = Iz.getPositionLong(this.trackedEntity.posY);
            long j2 = Iz.getPositionLong(this.trackedEntity.posZ);
            int k2 = MathHelper.floor(this.trackedEntity.rotationYaw * 256.0F / 360.0F);
            int i = MathHelper.floor(this.trackedEntity.rotationPitch * 256.0F / 360.0F);
            long j = i1 - this.encodedPosX;
            long k = i2 - this.encodedPosY;
            long l = j2 - this.encodedPosZ;
            Sz<?> packet1 = null;
            boolean flag = j * j + k * k + l * l >= 128L || this.updateCounter % 60 == 0;
            boolean flag1 = Math.abs(k2 - this.encodedRotationYaw) >= 1 || Math.abs(i - this.encodedRotationPitch) >= 1;
            if (this.updateCounter > 0 || this.trackedEntity instanceof MO) {
               if (j >= -32768L && j < 32768L && k >= -32768L && k < 32768L && l >= -32768L && l < 32768L && this.ticksSinceLastForcedTeleport <= 400 && !this.ridingEntity && this.onGround == this.trackedEntity.onGround) {
                  if ((!flag || !flag1) && !(this.trackedEntity instanceof MO)) {
                     if (flag) {
                        packet1 = new TS(this.trackedEntity.getEntityId(), j, k, l, this.trackedEntity.onGround);
                     } else if (flag1) {
                        packet1 = new TT(this.trackedEntity.getEntityId(), (byte)k2, (byte)i, this.trackedEntity.onGround);
                     }
                  } else {
                     packet1 = new TU(this.trackedEntity.getEntityId(), j, k, l, (byte)k2, (byte)i, this.trackedEntity.onGround);
                  }
               } else {
                  this.onGround = this.trackedEntity.onGround;
                  this.ticksSinceLastForcedTeleport = 0;
                  this.resetPlayerVisibility();
                  packet1 = new Ue(this.trackedEntity);
               }
            }

            boolean flag2 = this.sendVelocityUpdates;
            if (this.trackedEntity instanceof Iw && ((Iw)this.trackedEntity).isElytraFlying()) {
               flag2 = true;
            }

            if (flag2 && this.updateCounter > 0) {
               double d0 = this.trackedEntity.motionX - this.lastTrackedEntityMotionX;
               double d1 = this.trackedEntity.motionY - this.lastTrackedEntityMotionY;
               double d2 = this.trackedEntity.motionZ - this.motionZ;
               double d3 = 0.02;
               double d4 = d0 * d0 + d1 * d1 + d2 * d2;
               if (d4 > 4.0E-4 || d4 > 0.0 && this.trackedEntity.motionX == 0.0 && this.trackedEntity.motionY == 0.0 && this.trackedEntity.motionZ == 0.0) {
                  this.lastTrackedEntityMotionX = this.trackedEntity.motionX;
                  this.lastTrackedEntityMotionY = this.trackedEntity.motionY;
                  this.motionZ = this.trackedEntity.motionZ;
                  this.sendPacketToTrackedPlayers(new Uf(this.trackedEntity.getEntityId(), this.lastTrackedEntityMotionX, this.lastTrackedEntityMotionY, this.motionZ));
               }
            }

            if (packet1 != null) {
               this.sendPacketToTrackedPlayers((Sz)packet1);
            }

            this.sendMetadata();
            if (flag) {
               this.encodedPosX = i1;
               this.encodedPosY = i2;
               this.encodedPosZ = j2;
            }

            if (flag1) {
               this.encodedRotationYaw = k2;
               this.encodedRotationPitch = i;
            }

            this.ridingEntity = false;
         }

         k1 = MathHelper.floor(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
         if (Math.abs(k1 - this.lastHeadMotion) >= 1) {
            this.sendPacketToTrackedPlayers(new TZ(this.trackedEntity, (byte)k1));
            this.lastHeadMotion = k1;
         }

         this.trackedEntity.isAirBorne = false;
      }

      ++this.updateCounter;
      if (this.trackedEntity.velocityChanged) {
         this.sendToTrackingAndSelf(new Uf(this.trackedEntity));
         this.trackedEntity.velocityChanged = false;
      }

   }

   private void sendMetadata() {
      Rv entitydatamanager = this.trackedEntity.getDataManager();
      if (entitydatamanager.isDirty()) {
         this.sendToTrackingAndSelf(new Ua(this.trackedEntity.getEntityId(), entitydatamanager, false));
      }

      if (this.trackedEntity instanceof Iw) {
         FV attributemap = (FV)((Iw)this.trackedEntity).getAttributeMap();
         Set<FZ> set = attributemap.getDirtyInstances();
         if (!set.isEmpty()) {
            this.sendToTrackingAndSelf(new Uc(this.trackedEntity.getEntityId(), set));
         }

         set.clear();
      }

   }

   public void sendPacketToTrackedPlayers(Sz<?> packetIn) {
      Iterator var2 = this.trackingPlayers.iterator();

      while(var2.hasNext()) {
         MG entityplayermp = (MG)var2.next();
         entityplayermp.connection.sendPacket(packetIn);
      }

   }

   public void sendToTrackingAndSelf(Sz<?> packetIn) {
      this.sendPacketToTrackedPlayers(packetIn);
      if (this.trackedEntity instanceof MG) {
         ((MG)this.trackedEntity).connection.sendPacket(packetIn);
      }

   }

   public void sendDestroyEntityPacketToTrackedPlayers() {
      Iterator var1 = this.trackingPlayers.iterator();

      while(var1.hasNext()) {
         MG entityplayermp = (MG)var1.next();
         this.trackedEntity.removeTrackingPlayer(entityplayermp);
         entityplayermp.removeEntity(this.trackedEntity);
      }

   }

   public void removeFromTrackedPlayers(MG playerMP) {
      if (this.trackingPlayers.contains(playerMP)) {
         this.trackedEntity.removeTrackingPlayer(playerMP);
         playerMP.removeEntity(this.trackedEntity);
         this.trackingPlayers.remove(playerMP);
      }

   }

   public void updatePlayerEntity(MG playerMP) {
      if (playerMP != this.trackedEntity) {
         if (this.isVisibleTo(playerMP)) {
            if (!this.trackingPlayers.contains(playerMP) && (this.isPlayerWatchingThisChunk(playerMP) || this.trackedEntity.forceSpawn)) {
               this.trackingPlayers.add(playerMP);
               Sz<?> packet = this.createSpawnPacket();
               playerMP.connection.sendPacket(packet);
               if (!this.trackedEntity.getDataManager().isEmpty()) {
                  playerMP.connection.sendPacket(new Ua(this.trackedEntity.getEntityId(), this.trackedEntity.getDataManager(), true));
               }

               boolean flag = this.sendVelocityUpdates;
               if (this.trackedEntity instanceof Iw) {
                  FV attributemap = (FV)((Iw)this.trackedEntity).getAttributeMap();
                  Collection<FZ> collection = attributemap.getWatchedAttributes();
                  if (!collection.isEmpty()) {
                     playerMP.connection.sendPacket(new Uc(this.trackedEntity.getEntityId(), collection));
                  }

                  if (((Iw)this.trackedEntity).isElytraFlying()) {
                     flag = true;
                  }
               }

               this.lastTrackedEntityMotionX = this.trackedEntity.motionX;
               this.lastTrackedEntityMotionY = this.trackedEntity.motionY;
               this.motionZ = this.trackedEntity.motionZ;
               if (flag && !(packet instanceof UO)) {
                  playerMP.connection.sendPacket(new Uf(this.trackedEntity.getEntityId(), this.trackedEntity.motionX, this.trackedEntity.motionY, this.trackedEntity.motionZ));
               }

               if (this.trackedEntity instanceof Iw) {
                  EntityEquipmentSlot[] var9 = EntityEquipmentSlot.values();
                  int var12 = var9.length;

                  for(int var6 = 0; var6 < var12; ++var6) {
                     EntityEquipmentSlot entityequipmentslot = var9[var6];
                     Qy itemstack = ((Iw)this.trackedEntity).getItemStackFromSlot(entityequipmentslot);
                     if (!itemstack.isEmpty()) {
                        playerMP.connection.sendPacket(new TY(this.trackedEntity.getEntityId(), entityequipmentslot, itemstack));
                     }
                  }
               }

               if (this.trackedEntity instanceof ME) {
                  ME entityplayer = (ME)this.trackedEntity;
                  if (entityplayer.isPlayerSleeping()) {
                     playerMP.connection.sendPacket(new Vh(entityplayer, new BlockPos(this.trackedEntity)));
                  }
               }

               if (this.trackedEntity instanceof Iw) {
                  Iw entitylivingbase = (Iw)this.trackedEntity;
                  Iterator var13 = entitylivingbase.getActivePotionEffects().iterator();

                  while(var13.hasNext()) {
                     VZ potioneffect = (VZ)var13.next();
                     playerMP.connection.sendPacket(new TX(this.trackedEntity.getEntityId(), potioneffect));
                  }
               }

               if (!this.trackedEntity.getPassengers().isEmpty()) {
                  playerMP.connection.sendPacket(new UI(this.trackedEntity));
               }

               if (this.trackedEntity.isRiding()) {
                  playerMP.connection.sendPacket(new UI(this.trackedEntity.getRidingEntity()));
               }

               this.trackedEntity.addTrackingPlayer(playerMP);
               playerMP.addEntity(this.trackedEntity);
            }
         } else if (this.trackingPlayers.contains(playerMP)) {
            this.trackingPlayers.remove(playerMP);
            this.trackedEntity.removeTrackingPlayer(playerMP);
            playerMP.removeEntity(this.trackedEntity);
         }
      }

   }

   public boolean isVisibleTo(MG playerMP) {
      double d0 = playerMP.posX - (double)this.encodedPosX / 4096.0;
      double d1 = playerMP.posZ - (double)this.encodedPosZ / 4096.0;
      int i = Math.min(this.range, this.maxRange);
      return d0 >= (double)(-i) && d0 <= (double)i && d1 >= (double)(-i) && d1 <= (double)i && this.trackedEntity.isSpectatedByPlayer(playerMP);
   }

   private boolean isPlayerWatchingThisChunk(MG playerMP) {
      return playerMP.getServerWorld().getPlayerChunkMap().isPlayerWatchingChunk(playerMP, this.trackedEntity.chunkCoordX, this.trackedEntity.chunkCoordZ);
   }

   public void updatePlayerEntities(List<ME> players) {
      for(int i = 0; i < players.size(); ++i) {
         this.updatePlayerEntity((MG)players.get(i));
      }

   }

   private Sz<?> createSpawnPacket() {
      if (this.trackedEntity.isDead) {
         LOGGER.warn("Fetching addPacket for removed entity");
      }

      if (this.trackedEntity instanceof MG) {
         return new UR((ME)this.trackedEntity);
      } else if (this.trackedEntity instanceof Mx) {
         this.lastHeadMotion = MathHelper.floor(this.trackedEntity.getRotationYawHead() * 256.0F / 360.0F);
         return new UO((Iw)this.trackedEntity);
      } else if (this.trackedEntity instanceof Jq) {
         return new UQ((Jq)this.trackedEntity);
      } else if (this.trackedEntity instanceof IY) {
         return new UP(this.trackedEntity, 2, 1);
      } else if (this.trackedEntity instanceof Jc) {
         Jc entityminecart = (Jc)this.trackedEntity;
         return new UP(this.trackedEntity, 10, entityminecart.getType().getId());
      } else if (this.trackedEntity instanceof IR) {
         return new UP(this.trackedEntity, 1);
      } else if (this.trackedEntity instanceof Js) {
         return new UM((Js)this.trackedEntity);
      } else if (this.trackedEntity instanceof MU) {
         Ig entity2 = ((MU)this.trackedEntity).getAngler();
         return new UP(this.trackedEntity, 90, entity2 == null ? this.trackedEntity.getEntityId() : ((Ig)entity2).getEntityId());
      } else {
         Ig entity;
         if (this.trackedEntity instanceof Nc) {
            entity = ((Nc)this.trackedEntity).shootingEntity;
            return new UP(this.trackedEntity, 91, 1 + (entity == null ? this.trackedEntity.getEntityId() : entity.getEntityId()));
         } else if (this.trackedEntity instanceof Ne) {
            entity = ((MO)this.trackedEntity).shootingEntity;
            return new UP(this.trackedEntity, 60, 1 + (entity == null ? this.trackedEntity.getEntityId() : entity.getEntityId()));
         } else if (this.trackedEntity instanceof Nb) {
            return new UP(this.trackedEntity, 61);
         } else if (this.trackedEntity instanceof MW) {
            return new UP(this.trackedEntity, 68);
         } else if (this.trackedEntity instanceof MY) {
            return new UP(this.trackedEntity, 73);
         } else if (this.trackedEntity instanceof IV) {
            return new UP(this.trackedEntity, 75);
         } else if (this.trackedEntity instanceof IU) {
            return new UP(this.trackedEntity, 65);
         } else if (this.trackedEntity instanceof IT) {
            return new UP(this.trackedEntity, 72);
         } else if (this.trackedEntity instanceof IX) {
            return new UP(this.trackedEntity, 76);
         } else if (this.trackedEntity instanceof MS) {
            MS entityfireball = (MS)this.trackedEntity;
            UP spacketspawnobject = null;
            int i = 63;
            if (this.trackedEntity instanceof Na) {
               i = 64;
            } else if (this.trackedEntity instanceof MP) {
               i = 93;
            } else if (this.trackedEntity instanceof Nf) {
               i = 66;
            }

            if (entityfireball.shootingEntity != null) {
               spacketspawnobject = new UP(this.trackedEntity, i, ((MS)this.trackedEntity).shootingEntity.getEntityId());
            } else {
               spacketspawnobject = new UP(this.trackedEntity, i, 0);
            }

            spacketspawnobject.setSpeedX((int)(entityfireball.accelerationX * 8000.0));
            spacketspawnobject.setSpeedY((int)(entityfireball.accelerationY * 8000.0));
            spacketspawnobject.setSpeedZ((int)(entityfireball.accelerationZ * 8000.0));
            return spacketspawnobject;
         } else if (this.trackedEntity instanceof MZ) {
            UP spacketspawnobject1 = new UP(this.trackedEntity, 67, 0);
            spacketspawnobject1.setSpeedX((int)(this.trackedEntity.motionX * 8000.0));
            spacketspawnobject1.setSpeedY((int)(this.trackedEntity.motionY * 8000.0));
            spacketspawnobject1.setSpeedZ((int)(this.trackedEntity.motionZ * 8000.0));
            return spacketspawnobject1;
         } else if (this.trackedEntity instanceof MQ) {
            return new UP(this.trackedEntity, 62);
         } else if (this.trackedEntity instanceof MR) {
            return new UP(this.trackedEntity, 79);
         } else if (this.trackedEntity instanceof Jr) {
            return new UP(this.trackedEntity, 50);
         } else if (this.trackedEntity instanceof IS) {
            return new UP(this.trackedEntity, 51);
         } else if (this.trackedEntity instanceof IW) {
            IW entityfallingblock = (IW)this.trackedEntity;
            return new UP(this.trackedEntity, 70, co.getStateId(entityfallingblock.getBlock()));
         } else if (this.trackedEntity instanceof IN) {
            return new UP(this.trackedEntity, 78);
         } else if (this.trackedEntity instanceof IZ) {
            IZ entityitemframe = (IZ)this.trackedEntity;
            return new UP(this.trackedEntity, 71, entityitemframe.facingDirection.getHorizontalIndex(), entityitemframe.getHangingPosition());
         } else if (this.trackedEntity instanceof Ip) {
            Ip entityleashknot = (Ip)this.trackedEntity;
            return new UP(this.trackedEntity, 77, 0, entityleashknot.getHangingPosition());
         } else if (this.trackedEntity instanceof Ii) {
            return new UP(this.trackedEntity, 3);
         } else {
            throw new IllegalArgumentException("Don't know how to add " + this.trackedEntity.getClass() + "!");
         }
      }
   }

   public void removeTrackedPlayerSymmetric(MG playerMP) {
      if (this.trackingPlayers.contains(playerMP)) {
         this.trackingPlayers.remove(playerMP);
         this.trackedEntity.removeTrackingPlayer(playerMP);
         playerMP.removeEntity(this.trackedEntity);
      }

   }

   public Ig getTrackedEntity() {
      return this.trackedEntity;
   }

   public void setMaxRange(int maxRangeIn) {
      this.maxRange = maxRangeIn;
   }

   public void resetPlayerVisibility() {
      this.updatedPlayerVisibility = false;
   }
}
