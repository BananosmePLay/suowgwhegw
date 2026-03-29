package neo;

import com.google.common.collect.Lists;
import com.google.common.primitives.Doubles;
import com.google.common.primitives.Floats;
import com.google.common.util.concurrent.Futures;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ITickable;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.Mirror;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.ServerRecipeBookHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RY implements Tt, ITickable {
   private static final Logger LOGGER = LogManager.getLogger();
   public final Sp netManager;
   private final Xx server;
   public MG player;
   private int networkTickCount;
   private long field_194402_f;
   private boolean field_194403_g;
   private long field_194404_h;
   private int chatSpamThresholdCount;
   private int itemDropThreshold;
   private final IntHashMap<Short> pendingTransactions = new IntHashMap();
   private double firstGoodX;
   private double firstGoodY;
   private double firstGoodZ;
   private double lastGoodX;
   private double lastGoodY;
   private double lastGoodZ;
   private Ig lowestRiddenEnt;
   private double lowestRiddenX;
   private double lowestRiddenY;
   private double lowestRiddenZ;
   private double lowestRiddenX1;
   private double lowestRiddenY1;
   private double lowestRiddenZ1;
   private Vec3d targetPos;
   private int teleportId;
   private int lastPositionUpdate;
   private boolean floating;
   private int floatingTickCount;
   private boolean vehicleFloating;
   private int vehicleFloatingTickCount;
   private int movePacketCounter;
   private int lastMovePacketCounter;
   private ServerRecipeBookHelper field_194309_H = new ServerRecipeBookHelper();

   public RY(Xx server, Sp networkManagerIn, MG playerIn) {
      this.server = server;
      this.netManager = networkManagerIn;
      networkManagerIn.setNetHandler(this);
      this.player = playerIn;
      playerIn.connection = this;
   }

   public void update() {
      this.captureCurrentPosition();
      this.player.onUpdateEntity();
      this.player.setPositionAndRotation(this.firstGoodX, this.firstGoodY, this.firstGoodZ, this.player.rotationYaw, this.player.rotationPitch);
      ++this.networkTickCount;
      this.lastMovePacketCounter = this.movePacketCounter;
      if (this.floating) {
         if (++this.floatingTickCount > 80) {
            LOGGER.warn("{} was kicked for floating too long!", this.player.getName());
            this.disconnect(new TextComponentTranslation("multiplayer.disconnect.flying", new Object[0]));
            return;
         }
      } else {
         this.floating = false;
         this.floatingTickCount = 0;
      }

      this.lowestRiddenEnt = this.player.getLowestRidingEntity();
      if (this.lowestRiddenEnt != this.player && this.lowestRiddenEnt.getControllingPassenger() == this.player) {
         this.lowestRiddenX = this.lowestRiddenEnt.posX;
         this.lowestRiddenY = this.lowestRiddenEnt.posY;
         this.lowestRiddenZ = this.lowestRiddenEnt.posZ;
         this.lowestRiddenX1 = this.lowestRiddenEnt.posX;
         this.lowestRiddenY1 = this.lowestRiddenEnt.posY;
         this.lowestRiddenZ1 = this.lowestRiddenEnt.posZ;
         if (this.vehicleFloating && this.player.getLowestRidingEntity().getControllingPassenger() == this.player) {
            if (++this.vehicleFloatingTickCount > 80) {
               LOGGER.warn("{} was kicked for floating a vehicle too long!", this.player.getName());
               this.disconnect(new TextComponentTranslation("multiplayer.disconnect.flying", new Object[0]));
               return;
            }
         } else {
            this.vehicleFloating = false;
            this.vehicleFloatingTickCount = 0;
         }
      } else {
         this.lowestRiddenEnt = null;
         this.vehicleFloating = false;
         this.vehicleFloatingTickCount = 0;
      }

      this.server.profiler.startSection("keepAlive");
      long i = this.currentTimeMillis();
      if (i - this.field_194402_f >= 15000L) {
         if (this.field_194403_g) {
            this.disconnect(new TextComponentTranslation("disconnect.timeout", new Object[0]));
         } else {
            this.field_194403_g = true;
            this.field_194402_f = i;
            this.field_194404_h = i;
            this.sendPacket(new Uj(this.field_194404_h));
         }
      }

      this.server.profiler.endSection();
      if (this.chatSpamThresholdCount > 0) {
         --this.chatSpamThresholdCount;
      }

      if (this.itemDropThreshold > 0) {
         --this.itemDropThreshold;
      }

      if (this.player.getLastActiveTime() > 0L && this.server.getMaxPlayerIdleMinutes() > 0 && Xx.getCurrentTimeMillis() - this.player.getLastActiveTime() > (long)(this.server.getMaxPlayerIdleMinutes() * 1000 * 60)) {
         this.disconnect(new TextComponentTranslation("multiplayer.disconnect.idling", new Object[0]));
      }

   }

   private void captureCurrentPosition() {
      this.firstGoodX = this.player.posX;
      this.firstGoodY = this.player.posY;
      this.firstGoodZ = this.player.posZ;
      this.lastGoodX = this.player.posX;
      this.lastGoodY = this.player.posY;
      this.lastGoodZ = this.player.posZ;
   }

   public Sp getNetworkManager() {
      return this.netManager;
   }

   public void disconnect(final ITextComponent textComponent) {
      this.netManager.sendPacket(new TP(textComponent), new GenericFutureListener<Future<? super Void>>() {
         public void operationComplete(Future<? super Void> p_operationComplete_1_) throws Exception {
            RY.this.netManager.closeChannel(textComponent);
         }
      });
      this.netManager.disableAutoRead();
      Futures.getUnchecked(this.server.addScheduledTask(new Runnable() {
         public void run() {
            RY.this.netManager.handleDisconnection();
         }
      }));
   }

   public void processInput(SS packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.setEntityActionState(packetIn.getStrafeSpeed(), packetIn.getForwardSpeed(), packetIn.isJumping(), packetIn.isSneaking());
   }

   private static boolean isMovePlayerPacketInvalid(SY packetIn) {
      if (Doubles.isFinite(packetIn.getX(0.0)) && Doubles.isFinite(packetIn.getY(0.0)) && Doubles.isFinite(packetIn.getZ(0.0)) && Floats.isFinite(packetIn.getPitch(0.0F)) && Floats.isFinite(packetIn.getYaw(0.0F))) {
         return Math.abs(packetIn.getX(0.0)) > 3.0E7 || Math.abs(packetIn.getY(0.0)) > 3.0E7 || Math.abs(packetIn.getZ(0.0)) > 3.0E7;
      } else {
         return true;
      }
   }

   private static boolean isMoveVehiclePacketInvalid(Tq packetIn) {
      return !Doubles.isFinite(packetIn.getX()) || !Doubles.isFinite(packetIn.getY()) || !Doubles.isFinite(packetIn.getZ()) || !Floats.isFinite(packetIn.getPitch()) || !Floats.isFinite(packetIn.getYaw());
   }

   public void processVehicleMove(Tq packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      if (isMoveVehiclePacketInvalid(packetIn)) {
         this.disconnect(new TextComponentTranslation("multiplayer.disconnect.invalid_vehicle_movement", new Object[0]));
      } else {
         Ig entity = this.player.getLowestRidingEntity();
         if (entity != this.player && entity.getControllingPassenger() == this.player && entity == this.lowestRiddenEnt) {
            bis worldserver = this.player.getServerWorld();
            double d0 = entity.posX;
            double d1 = entity.posY;
            double d2 = entity.posZ;
            double d3 = packetIn.getX();
            double d4 = packetIn.getY();
            double d5 = packetIn.getZ();
            float f = packetIn.getYaw();
            float f1 = packetIn.getPitch();
            double d6 = d3 - this.lowestRiddenX;
            double d7 = d4 - this.lowestRiddenY;
            double d8 = d5 - this.lowestRiddenZ;
            double d9 = entity.motionX * entity.motionX + entity.motionY * entity.motionY + entity.motionZ * entity.motionZ;
            double d10 = d6 * d6 + d7 * d7 + d8 * d8;
            if (d10 - d9 > 100.0 && (!this.server.isSinglePlayer() || !this.server.getServerOwner().equals(entity.getName()))) {
               LOGGER.warn("{} (vehicle of {}) moved too quickly! {},{},{}", entity.getName(), this.player.getName(), d6, d7, d8);
               this.netManager.sendPacket(new Ul(entity));
               return;
            }

            boolean flag = worldserver.getCollisionBoxes(entity, entity.getEntityBoundingBox().shrink(0.0625)).isEmpty();
            d6 = d3 - this.lowestRiddenX1;
            d7 = d4 - this.lowestRiddenY1 - 1.0E-6;
            d8 = d5 - this.lowestRiddenZ1;
            entity.move(Lq.PLAYER, d6, d7, d8);
            double d11 = d7;
            d6 = d3 - entity.posX;
            d7 = d4 - entity.posY;
            if (d7 > -0.5 || d7 < 0.5) {
               d7 = 0.0;
            }

            d8 = d5 - entity.posZ;
            d10 = d6 * d6 + d7 * d7 + d8 * d8;
            boolean flag1 = false;
            if (d10 > 0.0625) {
               flag1 = true;
               LOGGER.warn("{} moved wrongly!", entity.getName());
            }

            entity.setPositionAndRotation(d3, d4, d5, f, f1);
            boolean flag2 = worldserver.getCollisionBoxes(entity, entity.getEntityBoundingBox().shrink(0.0625)).isEmpty();
            if (flag && (flag1 || !flag2)) {
               entity.setPositionAndRotation(d0, d1, d2, f, f1);
               this.netManager.sendPacket(new Ul(entity));
               return;
            }

            this.server.getPlayerList().serverUpdateMovingPlayer(this.player);
            this.player.addMovementStat(this.player.posX - d0, this.player.posY - d1, this.player.posZ - d2);
            this.vehicleFloating = d11 >= -0.03125 && !this.server.isFlightAllowed() && !worldserver.checkBlockCollision(entity.getEntityBoundingBox().grow(0.0625).expand(0.0, -0.55, 0.0));
            this.lowestRiddenX1 = entity.posX;
            this.lowestRiddenY1 = entity.posY;
            this.lowestRiddenZ1 = entity.posZ;
         }
      }

   }

   public void processConfirmTeleport(SK packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      if (packetIn.getTeleportId() == this.teleportId) {
         this.player.setPositionAndRotation(this.targetPos.x, this.targetPos.y, this.targetPos.z, this.player.rotationYaw, this.player.rotationPitch);
         if (this.player.isInvulnerableDimensionChange()) {
            this.lastGoodX = this.targetPos.x;
            this.lastGoodY = this.targetPos.y;
            this.lastGoodZ = this.targetPos.z;
            this.player.clearInvulnerableDimensionChange();
         }

         this.targetPos = null;
      }

   }

   public void handleRecipeBookUpdate(Tf p_191984_1_) {
      SC.checkThreadAndEnqueue(p_191984_1_, this, this.player.getServerWorld());
      if (p_191984_1_.getPurpose() == Te.SHOWN) {
         this.player.getRecipeBook().markSeen(p_191984_1_.getRecipe());
      } else if (p_191984_1_.getPurpose() == Te.SETTINGS) {
         this.player.getRecipeBook().setGuiOpen(p_191984_1_.isGuiOpen());
         this.player.getRecipeBook().setFilteringCraftable(p_191984_1_.isFilteringCraftable());
      }

   }

   public void handleSeenAdvancements(Tj p_194027_1_) {
      SC.checkThreadAndEnqueue(p_194027_1_, this, this.player.getServerWorld());
      if (p_194027_1_.getAction() == Ti.OPENED_TAB) {
         ResourceLocation resourcelocation = p_194027_1_.getTab();
         b advancement = this.server.getAdvancementManager().getAdvancement(resourcelocation);
         if (advancement != null) {
            this.player.getAdvancements().setSelectedTab(advancement);
         }
      }

   }

   public void processPlayer(SY packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      if (isMovePlayerPacketInvalid(packetIn)) {
         this.disconnect(new TextComponentTranslation("multiplayer.disconnect.invalid_player_movement", new Object[0]));
      } else {
         bis worldserver = this.server.getWorld(this.player.dimension);
         if (!this.player.queuedEndExit) {
            if (this.networkTickCount == 0) {
               this.captureCurrentPosition();
            }

            if (this.targetPos != null) {
               if (this.networkTickCount - this.lastPositionUpdate > 20) {
                  this.lastPositionUpdate = this.networkTickCount;
                  this.setPlayerLocation(this.targetPos.x, this.targetPos.y, this.targetPos.z, this.player.rotationYaw, this.player.rotationPitch);
               }
            } else {
               this.lastPositionUpdate = this.networkTickCount;
               if (this.player.isRiding()) {
                  this.player.setPositionAndRotation(this.player.posX, this.player.posY, this.player.posZ, packetIn.getYaw(this.player.rotationYaw), packetIn.getPitch(this.player.rotationPitch));
                  this.server.getPlayerList().serverUpdateMovingPlayer(this.player);
               } else {
                  double d0 = this.player.posX;
                  double d1 = this.player.posY;
                  double d2 = this.player.posZ;
                  double d3 = this.player.posY;
                  double d4 = packetIn.getX(this.player.posX);
                  double d5 = packetIn.getY(this.player.posY);
                  double d6 = packetIn.getZ(this.player.posZ);
                  float f = packetIn.getYaw(this.player.rotationYaw);
                  float f1 = packetIn.getPitch(this.player.rotationPitch);
                  double d7 = d4 - this.firstGoodX;
                  double d8 = d5 - this.firstGoodY;
                  double d9 = d6 - this.firstGoodZ;
                  double d10 = this.player.motionX * this.player.motionX + this.player.motionY * this.player.motionY + this.player.motionZ * this.player.motionZ;
                  double d11 = d7 * d7 + d8 * d8 + d9 * d9;
                  if (this.player.isPlayerSleeping()) {
                     if (d11 > 1.0) {
                        this.setPlayerLocation(this.player.posX, this.player.posY, this.player.posZ, packetIn.getYaw(this.player.rotationYaw), packetIn.getPitch(this.player.rotationPitch));
                     }
                  } else {
                     ++this.movePacketCounter;
                     int i = this.movePacketCounter - this.lastMovePacketCounter;
                     if (i > 5) {
                        LOGGER.debug("{} is sending move packets too frequently ({} packets since last tick)", this.player.getName(), i);
                        i = 1;
                     }

                     if (!this.player.isInvulnerableDimensionChange() && (!this.player.getServerWorld().getGameRules().getBoolean("disableElytraMovementCheck") || !this.player.isElytraFlying())) {
                        float f2 = this.player.isElytraFlying() ? 300.0F : 100.0F;
                        if (d11 - d10 > (double)(f2 * (float)i) && (!this.server.isSinglePlayer() || !this.server.getServerOwner().equals(this.player.getName()))) {
                           LOGGER.warn("{} moved too quickly! {},{},{}", this.player.getName(), d7, d8, d9);
                           this.setPlayerLocation(this.player.posX, this.player.posY, this.player.posZ, this.player.rotationYaw, this.player.rotationPitch);
                           return;
                        }
                     }

                     boolean flag2 = worldserver.getCollisionBoxes(this.player, this.player.getEntityBoundingBox().shrink(0.0625)).isEmpty();
                     d7 = d4 - this.lastGoodX;
                     d8 = d5 - this.lastGoodY;
                     d9 = d6 - this.lastGoodZ;
                     if (this.player.onGround && !packetIn.isOnGround() && d8 > 0.0) {
                        this.player.jump();
                     }

                     this.player.move(Lq.PLAYER, d7, d8, d9);
                     this.player.onGround = packetIn.isOnGround();
                     double d12 = d8;
                     d7 = d4 - this.player.posX;
                     d8 = d5 - this.player.posY;
                     if (d8 > -0.5 || d8 < 0.5) {
                        d8 = 0.0;
                     }

                     d9 = d6 - this.player.posZ;
                     d11 = d7 * d7 + d8 * d8 + d9 * d9;
                     boolean flag = false;
                     if (!this.player.isInvulnerableDimensionChange() && d11 > 0.0625 && !this.player.isPlayerSleeping() && !this.player.interactionManager.isCreative() && this.player.interactionManager.getGameType() != bbb.SPECTATOR) {
                        flag = true;
                        LOGGER.warn("{} moved wrongly!", this.player.getName());
                     }

                     this.player.setPositionAndRotation(d4, d5, d6, f, f1);
                     this.player.addMovementStat(this.player.posX - d0, this.player.posY - d1, this.player.posZ - d2);
                     if (!this.player.noClip && !this.player.isPlayerSleeping()) {
                        boolean flag1 = worldserver.getCollisionBoxes(this.player, this.player.getEntityBoundingBox().shrink(0.0625)).isEmpty();
                        if (flag2 && (flag || !flag1)) {
                           this.setPlayerLocation(d0, d1, d2, f, f1);
                           return;
                        }
                     }

                     this.floating = d12 >= -0.03125;
                     this.floating &= !this.server.isFlightAllowed() && !this.player.capabilities.allowFlying;
                     this.floating &= !this.player.isPotionActive(NL.LEVITATION) && !this.player.isElytraFlying() && !worldserver.checkBlockCollision(this.player.getEntityBoundingBox().grow(0.0625).expand(0.0, -0.55, 0.0));
                     this.player.onGround = packetIn.isOnGround();
                     this.server.getPlayerList().serverUpdateMovingPlayer(this.player);
                     this.player.handleFalling(this.player.posY - d3, packetIn.isOnGround());
                     this.lastGoodX = this.player.posX;
                     this.lastGoodY = this.player.posY;
                     this.lastGoodZ = this.player.posZ;
                  }
               }
            }
         }
      }

   }

   public void setPlayerLocation(double x, double y, double z, float yaw, float pitch) {
      this.setPlayerLocation(x, y, z, yaw, pitch, Collections.emptySet());
   }

   public void setPlayerLocation(double x, double y, double z, float yaw, float pitch, Set<Ux> relativeSet) {
      double d0 = relativeSet.contains(Ux.X) ? this.player.posX : 0.0;
      double d1 = relativeSet.contains(Ux.Y) ? this.player.posY : 0.0;
      double d2 = relativeSet.contains(Ux.Z) ? this.player.posZ : 0.0;
      this.targetPos = new Vec3d(x + d0, y + d1, z + d2);
      float f = yaw;
      float f1 = pitch;
      if (relativeSet.contains(Ux.Y_ROT)) {
         f = yaw + this.player.rotationYaw;
      }

      if (relativeSet.contains(Ux.X_ROT)) {
         f1 = pitch + this.player.rotationPitch;
      }

      if (++this.teleportId == Integer.MAX_VALUE) {
         this.teleportId = 0;
      }

      this.lastPositionUpdate = this.networkTickCount;
      this.player.setPositionAndRotation(this.targetPos.x, this.targetPos.y, this.targetPos.z, f, f1);
      this.player.connection.sendPacket(new Uy(x, y, z, yaw, pitch, relativeSet, this.teleportId));
   }

   public void processPlayerDigging(Tb packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      bis worldserver = this.server.getWorld(this.player.dimension);
      BlockPos blockpos = packetIn.getPosition();
      this.player.markPlayerActive();
      switch (packetIn.getAction()) {
         case SWAP_HELD_ITEMS:
            if (!this.player.isSpectator()) {
               Qy itemstack = this.player.getHeldItem(EnumHand.OFF_HAND);
               this.player.setHeldItem(EnumHand.OFF_HAND, this.player.getHeldItem(EnumHand.MAIN_HAND));
               this.player.setHeldItem(EnumHand.MAIN_HAND, itemstack);
            }

            return;
         case DROP_ITEM:
            if (!this.player.isSpectator()) {
               this.player.dropItem(false);
            }

            return;
         case DROP_ALL_ITEMS:
            if (!this.player.isSpectator()) {
               this.player.dropItem(true);
            }

            return;
         case RELEASE_USE_ITEM:
            this.player.stopActiveHand();
            return;
         case START_DESTROY_BLOCK:
         case ABORT_DESTROY_BLOCK:
         case STOP_DESTROY_BLOCK:
            double d0 = this.player.posX - ((double)blockpos.getX() + 0.5);
            double d1 = this.player.posY - ((double)blockpos.getY() + 0.5) + 1.5;
            double d2 = this.player.posZ - ((double)blockpos.getZ() + 0.5);
            double d3 = d0 * d0 + d1 * d1 + d2 * d2;
            if (d3 > 36.0) {
               return;
            } else if (blockpos.getY() >= this.server.getBuildLimit()) {
               return;
            } else {
               if (packetIn.getAction() == Ta.START_DESTROY_BLOCK) {
                  if (!this.server.isBlockProtected(worldserver, blockpos, this.player) && worldserver.getWorldBorder().contains(blockpos)) {
                     this.player.interactionManager.onBlockClicked(blockpos, packetIn.getFacing());
                  } else {
                     this.player.connection.sendPacket(new TA(worldserver, blockpos));
                  }
               } else {
                  if (packetIn.getAction() == Ta.STOP_DESTROY_BLOCK) {
                     this.player.interactionManager.blockRemoving(blockpos);
                  } else if (packetIn.getAction() == Ta.ABORT_DESTROY_BLOCK) {
                     this.player.interactionManager.cancelDestroyingBlock();
                  }

                  if (worldserver.getBlockState(blockpos).getMaterial() != hM.AIR) {
                     this.player.connection.sendPacket(new TA(worldserver, blockpos));
                  }
               }

               return;
            }
         default:
            throw new IllegalArgumentException("Invalid player action");
      }
   }

   public void processTryUseItemOnBlock(Td packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      bis worldserver = this.server.getWorld(this.player.dimension);
      EnumHand enumhand = packetIn.getHand();
      Qy itemstack = this.player.getHeldItem(enumhand);
      BlockPos blockpos = packetIn.getPos();
      EnumFacing enumfacing = packetIn.getDirection();
      this.player.markPlayerActive();
      if (blockpos.getY() >= this.server.getBuildLimit() - 1 && (enumfacing == EnumFacing.UP || blockpos.getY() >= this.server.getBuildLimit())) {
         TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("build.tooHigh", new Object[]{this.server.getBuildLimit()});
         textcomponenttranslation.getStyle().setColor(TextFormatting.RED);
         this.player.connection.sendPacket(new TD(textcomponenttranslation, ChatType.GAME_INFO));
      } else if (this.targetPos == null && this.player.getDistanceSq((double)blockpos.getX() + 0.5, (double)blockpos.getY() + 0.5, (double)blockpos.getZ() + 0.5) < 64.0 && !this.server.isBlockProtected(worldserver, blockpos, this.player) && worldserver.getWorldBorder().contains(blockpos)) {
         this.player.interactionManager.processRightClickBlock(this.player, worldserver, itemstack, enumhand, blockpos, enumfacing, packetIn.getFacingX(), packetIn.getFacingY(), packetIn.getFacingZ());
      }

      this.player.connection.sendPacket(new TA(worldserver, blockpos));
      this.player.connection.sendPacket(new TA(worldserver, blockpos.offset(enumfacing)));
   }

   public void processTryUseItem(Tc packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      bis worldserver = this.server.getWorld(this.player.dimension);
      EnumHand enumhand = packetIn.getHand();
      Qy itemstack = this.player.getHeldItem(enumhand);
      this.player.markPlayerActive();
      if (!itemstack.isEmpty()) {
         this.player.interactionManager.processRightClick(this.player, worldserver, itemstack, enumhand);
      }

   }

   public void handleSpectate(Tk packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      if (this.player.isSpectator()) {
         Ig entity = null;
         bis[] var3 = this.server.worlds;
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            bis worldserver = var3[var5];
            if (worldserver != null) {
               entity = packetIn.getEntity(worldserver);
               if (entity != null) {
                  break;
               }
            }
         }

         if (entity != null) {
            this.player.setSpectatingEntity(this.player);
            this.player.dismountRidingEntity();
            if (entity.world == this.player.world) {
               this.player.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
            } else {
               bis worldserver1 = this.player.getServerWorld();
               bis worldserver2 = (bis)entity.world;
               this.player.dimension = entity.dimension;
               this.sendPacket(new UD(this.player.dimension, worldserver1.getDifficulty(), worldserver1.getWorldInfo().getTerrainType(), this.player.interactionManager.getGameType()));
               this.server.getPlayerList().updatePermissionLevel(this.player);
               worldserver1.removeEntityDangerously(this.player);
               this.player.isDead = false;
               this.player.setLocationAndAngles(entity.posX, entity.posY, entity.posZ, entity.rotationYaw, entity.rotationPitch);
               if (this.player.isEntityAlive()) {
                  worldserver1.updateEntityWithOptionalForce(this.player, false);
                  worldserver2.spawnEntity(this.player);
                  worldserver2.updateEntityWithOptionalForce(this.player, false);
               }

               this.player.setWorld(worldserver2);
               this.server.getPlayerList().preparePlayer(this.player, worldserver1);
               this.player.setPositionAndUpdate(entity.posX, entity.posY, entity.posZ);
               this.player.interactionManager.setWorld(worldserver2);
               this.server.getPlayerList().updateTimeAndWeatherForPlayer(this.player, worldserver2);
               this.server.getPlayerList().syncPlayerInventory(this.player);
            }
         }
      }

   }

   public void handleResourcePackStatus(Th packetIn) {
   }

   public void processSteerBoat(Tl packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      Ig entity = this.player.getRidingEntity();
      if (entity instanceof IR) {
         ((IR)entity).setPaddleState(packetIn.getLeft(), packetIn.getRight());
      }

   }

   public void onDisconnect(ITextComponent reason) {
      LOGGER.info("{} lost connection: {}", this.player.getName(), reason.getUnformattedText());
      this.server.refreshStatusNextTick();
      TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("multiplayer.player.left", new Object[]{this.player.getDisplayName()});
      textcomponenttranslation.getStyle().setColor(TextFormatting.YELLOW);
      this.server.getPlayerList().sendMessage(textcomponenttranslation);
      this.player.mountEntityAndWakeUp();
      this.server.getPlayerList().playerLoggedOut(this.player);
      if (this.server.isSinglePlayer() && this.player.getName().equals(this.server.getServerOwner())) {
         LOGGER.info("Stopping singleplayer server as player logged out");
         this.server.initiateShutdown();
      }

   }

   public void sendPacket(final Sz<?> packetIn) {
      if (packetIn instanceof TD) {
         TD spacketchat = (TD)packetIn;
         MB entityplayer$enumchatvisibility = this.player.getChatVisibility();
         if (entityplayer$enumchatvisibility == MB.HIDDEN && spacketchat.getType() != ChatType.GAME_INFO) {
            return;
         }

         if (entityplayer$enumchatvisibility == MB.SYSTEM && !spacketchat.isSystem()) {
            return;
         }
      }

      try {
         this.netManager.sendPacket(packetIn);
      } catch (Throwable var5) {
         Throwable throwable = var5;
         Er crashreport = Er.makeCrashReport(throwable, "Sending packet");
         Ey crashreportcategory = crashreport.makeCategory("Packet being sent");
         crashreportcategory.addDetail("Packet class", new Ez<String>() {
            public String call() throws Exception {
               return packetIn.getClass().getCanonicalName();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         throw new ReportedException(crashreport);
      }
   }

   public void processHeldItemChange(SR packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      if (packetIn.getSlotId() >= 0 && packetIn.getSlotId() < MJ.getHotbarSize()) {
         this.player.inventory.currentItem = packetIn.getSlotId();
         this.player.markPlayerActive();
      } else {
         LOGGER.warn("{} tried to set an invalid carried item", this.player.getName());
      }

   }

   public void processChatMessage(SE packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      if (this.player.getChatVisibility() == MB.HIDDEN) {
         TextComponentTranslation textcomponenttranslation = new TextComponentTranslation("chat.cannotSend", new Object[0]);
         textcomponenttranslation.getStyle().setColor(TextFormatting.RED);
         this.sendPacket(new TD(textcomponenttranslation));
      } else {
         this.player.markPlayerActive();
         String s = packetIn.getMessage();
         s = StringUtils.normalizeSpace(s);

         for(int i = 0; i < s.length(); ++i) {
            if (!ChatAllowedCharacters.isAllowedCharacter(s.charAt(i))) {
               this.disconnect(new TextComponentTranslation("multiplayer.disconnect.illegal_characters", new Object[0]));
               return;
            }
         }

         if (s.startsWith("/")) {
            this.handleSlashCommand(s);
         } else {
            ITextComponent itextcomponent = new TextComponentTranslation("chat.type.text", new Object[]{this.player.getDisplayName(), s});
            this.server.getPlayerList().sendMessage(itextcomponent, false);
         }

         this.chatSpamThresholdCount += 20;
         if (this.chatSpamThresholdCount > 200 && !this.server.getPlayerList().canSendCommands(this.player.getGameProfile())) {
            this.disconnect(new TextComponentTranslation("disconnect.spam", new Object[0]));
         }
      }

   }

   private void handleSlashCommand(String command) {
      this.server.getCommandManager().executeCommand(this.player, command);
   }

   public void handleAnimation(SD packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.markPlayerActive();
      this.player.swingArm(packetIn.getHand());
   }

   public void processEntityAction(SQ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.markPlayerActive();
      IG ijumpingmount1;
      switch (packetIn.getAction()) {
         case START_SNEAKING:
            this.player.setSneaking(true);
            break;
         case STOP_SNEAKING:
            this.player.setSneaking(false);
            break;
         case START_SPRINTING:
            this.player.setSprinting(true);
            break;
         case STOP_SPRINTING:
            this.player.setSprinting(false);
            break;
         case STOP_SLEEPING:
            if (this.player.isPlayerSleeping()) {
               this.player.wakeUpPlayer(false, true, true);
               this.targetPos = new Vec3d(this.player.posX, this.player.posY, this.player.posZ);
            }
            break;
         case START_RIDING_JUMP:
            if (this.player.getRidingEntity() instanceof IG) {
               ijumpingmount1 = (IG)this.player.getRidingEntity();
               int i = packetIn.getAuxData();
               if (ijumpingmount1.canJump() && i > 0) {
                  ijumpingmount1.handleStartJump(i);
               }
            }
            break;
         case STOP_RIDING_JUMP:
            if (this.player.getRidingEntity() instanceof IG) {
               ijumpingmount1 = (IG)this.player.getRidingEntity();
               ijumpingmount1.handleStopJump();
            }
            break;
         case OPEN_INVENTORY:
            if (this.player.getRidingEntity() instanceof Lw) {
               ((Lw)this.player.getRidingEntity()).openGUI(this.player);
            }
            break;
         case START_FALL_FLYING:
            if (!this.player.onGround && this.player.motionY < 0.0 && !this.player.isElytraFlying() && !this.player.isInWater()) {
               Qy itemstack = this.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST);
               if (itemstack.getItem() == NK.ELYTRA && Pt.isUsable(itemstack)) {
                  this.player.setElytraFlying();
               }
            } else {
               this.player.clearElytraFlying();
            }
            break;
         default:
            throw new IllegalArgumentException("Invalid client command!");
      }

   }

   public void processUseEntity(Tp packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      bis worldserver = this.server.getWorld(this.player.dimension);
      Ig entity = packetIn.getEntityFromWorld(worldserver);
      this.player.markPlayerActive();
      if (entity != null) {
         boolean flag = this.player.canEntityBeSeen(entity);
         double d0 = 36.0;
         if (!flag) {
            d0 = 9.0;
         }

         if (this.player.getDistanceSq(entity) < d0) {
            EnumHand enumhand1;
            if (packetIn.getAction() == To.INTERACT) {
               enumhand1 = packetIn.getHand();
               this.player.interactOn(entity, enumhand1);
            } else if (packetIn.getAction() == To.INTERACT_AT) {
               enumhand1 = packetIn.getHand();
               entity.applyPlayerInteraction(this.player, packetIn.getHitVec(), enumhand1);
            } else if (packetIn.getAction() == To.ATTACK) {
               if (entity instanceof IY || entity instanceof Js || entity instanceof MO || entity == this.player) {
                  this.disconnect(new TextComponentTranslation("multiplayer.disconnect.invalid_entity_attacked", new Object[0]));
                  this.server.logWarning("Player " + this.player.getName() + " tried to attack an invalid entity");
                  return;
               }

               this.player.attackTargetEntityWithCurrentItem(entity);
            }
         }
      }

   }

   public void processClientStatus(SI packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.markPlayerActive();
      SH cpacketclientstatus$state = packetIn.getStatus();
      switch (cpacketclientstatus$state) {
         case PERFORM_RESPAWN:
            if (this.player.queuedEndExit) {
               this.player.queuedEndExit = false;
               this.player = this.server.getPlayerList().recreatePlayerEntity(this.player, 0, true);
               bY.CHANGED_DIMENSION.trigger(this.player, baM.THE_END, baM.OVERWORLD);
            } else {
               if (this.player.getHealth() > 0.0F) {
                  return;
               }

               this.player = this.server.getPlayerList().recreatePlayerEntity(this.player, 0, false);
               if (this.server.isHardcore()) {
                  this.player.setGameType(bbb.SPECTATOR);
                  this.player.getServerWorld().getGameRules().setOrCreateGameRule("spectatorsGenerateChunks", "false");
               }
            }
            break;
         case REQUEST_STATS:
            this.player.getStatFile().sendStats(this.player);
      }

   }

   public void processCloseWindow(SJ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.closeContainer();
   }

   public void processClickWindow(SF packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.markPlayerActive();
      if (this.player.openContainer.windowId == packetIn.getWindowId() && this.player.openContainer.getCanCraft(this.player)) {
         if (this.player.isSpectator()) {
            NonNullList<Qy> nonnulllist = NonNullList.create();

            for(int i = 0; i < this.player.openContainer.inventorySlots.size(); ++i) {
               nonnulllist.add(((Slot)this.player.openContainer.inventorySlots.get(i)).getStack());
            }

            this.player.sendAllContents(this.player.openContainer, nonnulllist);
         } else {
            Qy itemstack2 = this.player.openContainer.slotClick(packetIn.getSlotId(), packetIn.getUsedButton(), packetIn.getClickType(), this.player);
            if (Qy.areItemStacksEqual(packetIn.getClickedItem(), itemstack2)) {
               this.player.connection.sendPacket(new TK(packetIn.getWindowId(), packetIn.getActionNumber(), true));
               this.player.isChangingQuantityOnly = true;
               this.player.openContainer.detectAndSendChanges();
               this.player.updateHeldItem();
               this.player.isChangingQuantityOnly = false;
            } else {
               this.pendingTransactions.addKey(this.player.openContainer.windowId, packetIn.getActionNumber());
               this.player.connection.sendPacket(new TK(packetIn.getWindowId(), packetIn.getActionNumber(), false));
               this.player.openContainer.setCanCraft(this.player, false);
               NonNullList<Qy> nonnulllist1 = NonNullList.create();

               for(int j = 0; j < this.player.openContainer.inventorySlots.size(); ++j) {
                  Qy itemstack = ((Slot)this.player.openContainer.inventorySlots.get(j)).getStack();
                  Qy itemstack1 = itemstack.isEmpty() ? Qy.EMPTY : itemstack;
                  nonnulllist1.add(itemstack1);
               }

               this.player.sendAllContents(this.player.openContainer, nonnulllist1);
            }
         }
      }

   }

   public void func_194308_a(SU p_194308_1_) {
      SC.checkThreadAndEnqueue(p_194308_1_, this, this.player.getServerWorld());
      this.player.markPlayerActive();
      if (!this.player.isSpectator() && this.player.openContainer.windowId == p_194308_1_.func_194318_a() && this.player.openContainer.getCanCraft(this.player)) {
         this.field_194309_H.func_194327_a(this.player, p_194308_1_.func_194317_b(), p_194308_1_.func_194319_c());
      }

   }

   public void processEnchantItem(SO packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.markPlayerActive();
      if (this.player.openContainer.windowId == packetIn.getWindowId() && this.player.openContainer.getCanCraft(this.player) && !this.player.isSpectator()) {
         this.player.openContainer.enchantItem(this.player, packetIn.getButton());
         this.player.openContainer.detectAndSendChanges();
      }

   }

   public void processCreativeInventoryAction(SM packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      if (this.player.interactionManager.isCreative()) {
         boolean flag = packetIn.getSlotId() < 0;
         Qy itemstack = packetIn.getStack();
         if (!itemstack.isEmpty() && itemstack.hasTagCompound() && itemstack.getTagCompound().hasKey("BlockEntityTag", 10)) {
            QQ nbttagcompound = itemstack.getTagCompound().getCompoundTag("BlockEntityTag");
            if (nbttagcompound.hasKey("x") && nbttagcompound.hasKey("y") && nbttagcompound.hasKey("z")) {
               BlockPos blockpos = new BlockPos(nbttagcompound.getInteger("x"), nbttagcompound.getInteger("y"), nbttagcompound.getInteger("z"));
               Yg tileentity = this.player.world.getTileEntity(blockpos);
               if (tileentity != null) {
                  QQ nbttagcompound1 = tileentity.writeToNBT(new QQ());
                  nbttagcompound1.removeTag("x");
                  nbttagcompound1.removeTag("y");
                  nbttagcompound1.removeTag("z");
                  itemstack.setTagInfo("BlockEntityTag", nbttagcompound1);
               }
            }
         }

         boolean flag1 = packetIn.getSlotId() >= 1 && packetIn.getSlotId() <= 45;
         boolean flag2 = itemstack.isEmpty() || itemstack.getMetadata() >= 0 && itemstack.getCount() <= 64 && !itemstack.isEmpty();
         if (flag1 && flag2) {
            if (itemstack.isEmpty()) {
               this.player.inventoryContainer.putStackInSlot(packetIn.getSlotId(), Qy.EMPTY);
            } else {
               this.player.inventoryContainer.putStackInSlot(packetIn.getSlotId(), itemstack);
            }

            this.player.inventoryContainer.setCanCraft(this.player, true);
         } else if (flag && flag2 && this.itemDropThreshold < 200) {
            this.itemDropThreshold += 20;
            IY entityitem = this.player.dropItem(itemstack, true);
            if (entityitem != null) {
               entityitem.setAgeToCreativeDespawnTime();
            }
         }
      }

   }

   public void processConfirmTransaction(SL packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      Short oshort = (Short)this.pendingTransactions.lookup(this.player.openContainer.windowId);
      if (oshort != null && packetIn.getUid() == oshort && this.player.openContainer.windowId == packetIn.getWindowId() && !this.player.openContainer.getCanCraft(this.player) && !this.player.isSpectator()) {
         this.player.openContainer.setCanCraft(this.player, true);
      }

   }

   public void processUpdateSign(Tn packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.markPlayerActive();
      bis worldserver = this.server.getWorld(this.player.dimension);
      BlockPos blockpos = packetIn.getPosition();
      if (worldserver.isBlockLoaded(blockpos)) {
         in iblockstate = worldserver.getBlockState(blockpos);
         Yg tileentity = worldserver.getTileEntity(blockpos);
         if (!(tileentity instanceof YQ)) {
            return;
         }

         YQ tileentitysign = (YQ)tileentity;
         if (!tileentitysign.getIsEditable() || tileentitysign.getPlayer() != this.player) {
            this.server.logWarning("Player " + this.player.getName() + " just tried to change non-editable sign");
            return;
         }

         String[] astring = packetIn.getLines();

         for(int i = 0; i < astring.length; ++i) {
            tileentitysign.signText[i] = new TextComponentString(TextFormatting.getTextWithoutFormattingCodes(astring[i]));
         }

         tileentitysign.markDirty();
         worldserver.notifyBlockUpdate(blockpos, iblockstate, iblockstate, 3);
      }

   }

   public void processKeepAlive(ST packetIn) {
      if (this.field_194403_g && packetIn.getKey() == this.field_194404_h) {
         int i = (int)(this.currentTimeMillis() - this.field_194402_f);
         this.player.ping = (this.player.ping * 3 + i) / 4;
         this.field_194403_g = false;
      } else if (!this.player.getName().equals(this.server.getServerOwner())) {
         this.disconnect(new TextComponentTranslation("disconnect.timeout", new Object[0]));
      }

   }

   private long currentTimeMillis() {
      return System.nanoTime() / 1000000L;
   }

   public void processPlayerAbilities(SZ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.capabilities.isFlying = packetIn.isFlying() && this.player.capabilities.allowFlying;
   }

   public void processTabComplete(Tm packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      List<String> list = Lists.newArrayList();
      Iterator var3 = this.server.getTabCompletions(this.player, packetIn.getMessage(), packetIn.getTargetBlock(), packetIn.hasTargetBlock()).iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         list.add(s);
      }

      this.player.connection.sendPacket(new UU((String[])((String[])list.toArray(new String[list.size()]))));
   }

   public void processClientSettings(SG packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      this.player.handleClientSettings(packetIn);
   }

   public void processCustomPayload(SN packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.player.getServerWorld());
      String s = packetIn.getChannelName();
      SA packetbuffer5;
      Exception exception1;
      Qy itemstack4;
      Qy itemstack3;
      if ("MC|BEdit".equals(s)) {
         packetbuffer5 = packetIn.getBufferData();

         try {
            itemstack3 = packetbuffer5.readItemStack();
            if (itemstack3.isEmpty()) {
               return;
            }

            if (!QC.isNBTValid(itemstack3.getTagCompound())) {
               throw new IOException("Invalid book tag!");
            }

            itemstack4 = this.player.getHeldItemMainhand();
            if (itemstack4.isEmpty()) {
               return;
            }

            if (itemstack3.getItem() == NK.WRITABLE_BOOK && itemstack3.getItem() == itemstack4.getItem()) {
               itemstack4.setTagInfo("pages", itemstack3.getTagCompound().getTagList("pages", 8));
            }
         } catch (Exception var25) {
            exception1 = var25;
            LOGGER.error("Couldn't handle book info", exception1);
         }
      } else {
         String s8;
         if ("MC|BSign".equals(s)) {
            packetbuffer5 = packetIn.getBufferData();

            try {
               itemstack3 = packetbuffer5.readItemStack();
               if (itemstack3.isEmpty()) {
                  return;
               }

               if (!QD.validBookTagContents(itemstack3.getTagCompound())) {
                  throw new IOException("Invalid book tag!");
               }

               itemstack4 = this.player.getHeldItemMainhand();
               if (itemstack4.isEmpty()) {
                  return;
               }

               if (itemstack3.getItem() == NK.WRITABLE_BOOK && itemstack4.getItem() == NK.WRITABLE_BOOK) {
                  Qy itemstack2 = new Qy(NK.WRITTEN_BOOK);
                  itemstack2.setTagInfo("author", new Ra(this.player.getName()));
                  itemstack2.setTagInfo("title", new Ra(itemstack3.getTagCompound().getString("title")));
                  QW nbttaglist = itemstack3.getTagCompound().getTagList("pages", 8);

                  for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                     s8 = nbttaglist.getStringTagAt(i);
                     ITextComponent itextcomponent = new TextComponentString(s8);
                     s8 = ITextComponent.Serializer.componentToJson(itextcomponent);
                     nbttaglist.set(i, new Ra(s8));
                  }

                  itemstack2.setTagInfo("pages", nbttaglist);
                  this.player.setItemStackToSlot(EntityEquipmentSlot.MAINHAND, itemstack2);
               }
            } catch (Exception var26) {
               exception1 = var26;
               LOGGER.error("Couldn't sign book", exception1);
            }
         } else if ("MC|TrSel".equals(s)) {
            try {
               int k = packetIn.getBufferData().readInt();
               Container container = this.player.openContainer;
               if (container instanceof ContainerMerchant) {
                  ((ContainerMerchant)container).setCurrentRecipeIndex(k);
               }
            } catch (Exception var24) {
               LOGGER.error("Couldn't select trade", var24);
            }
         } else {
            int j1;
            Yg tileentity1;
            if ("MC|AdvCmd".equals(s)) {
               if (!this.server.isCommandBlockEnabled()) {
                  this.player.sendMessage(new TextComponentTranslation("advMode.notEnabled", new Object[0]));
                  return;
               }

               if (!this.player.canUseCommandBlock()) {
                  this.player.sendMessage(new TextComponentTranslation("advMode.notAllowed", new Object[0]));
                  return;
               }

               packetbuffer5 = packetIn.getBufferData();

               try {
                  j1 = packetbuffer5.readByte();
                  XZ commandblockbaselogic1 = null;
                  if (j1 == 0) {
                     tileentity1 = this.player.world.getTileEntity(new BlockPos(packetbuffer5.readInt(), packetbuffer5.readInt(), packetbuffer5.readInt()));
                     if (tileentity1 instanceof Yq) {
                        commandblockbaselogic1 = ((Yq)tileentity1).getCommandBlockLogic();
                     }
                  } else if (j1 == 1) {
                     Ig entity = this.player.world.getEntityByID(packetbuffer5.readInt());
                     if (entity instanceof Jg) {
                        commandblockbaselogic1 = ((Jg)entity).getCommandBlockLogic();
                     }
                  }

                  String s6 = packetbuffer5.readString(packetbuffer5.readableBytes());
                  boolean flag2 = packetbuffer5.readBoolean();
                  if (commandblockbaselogic1 != null) {
                     commandblockbaselogic1.setCommand(s6);
                     commandblockbaselogic1.setTrackOutput(flag2);
                     if (!flag2) {
                        commandblockbaselogic1.setLastOutput((ITextComponent)null);
                     }

                     commandblockbaselogic1.updateCommand();
                     this.player.sendMessage(new TextComponentTranslation("advMode.setCommand.success", new Object[]{s6}));
                  }
               } catch (Exception var23) {
                  exception1 = var23;
                  LOGGER.error("Couldn't set command block", exception1);
               }
            } else if ("MC|AutoCmd".equals(s)) {
               if (!this.server.isCommandBlockEnabled()) {
                  this.player.sendMessage(new TextComponentTranslation("advMode.notEnabled", new Object[0]));
                  return;
               }

               if (!this.player.canUseCommandBlock()) {
                  this.player.sendMessage(new TextComponentTranslation("advMode.notAllowed", new Object[0]));
                  return;
               }

               packetbuffer5 = packetIn.getBufferData();

               try {
                  XZ commandblockbaselogic = null;
                  Yq tileentitycommandblock = null;
                  BlockPos blockpos1 = new BlockPos(packetbuffer5.readInt(), packetbuffer5.readInt(), packetbuffer5.readInt());
                  Yg tileentity2 = this.player.world.getTileEntity(blockpos1);
                  if (tileentity2 instanceof Yq) {
                     tileentitycommandblock = (Yq)tileentity2;
                     commandblockbaselogic = tileentitycommandblock.getCommandBlockLogic();
                  }

                  String s7 = packetbuffer5.readString(packetbuffer5.readableBytes());
                  boolean flag3 = packetbuffer5.readBoolean();
                  Yp tileentitycommandblock$mode = Yp.valueOf(packetbuffer5.readString(16));
                  boolean flag = packetbuffer5.readBoolean();
                  boolean flag1 = packetbuffer5.readBoolean();
                  if (commandblockbaselogic != null) {
                     EnumFacing enumfacing = (EnumFacing)this.player.world.getBlockState(blockpos1).getValue(da.FACING);
                     switch (tileentitycommandblock$mode) {
                        case SEQUENCE:
                           in iblockstate3 = Nk.CHAIN_COMMAND_BLOCK.getDefaultState();
                           this.player.world.setBlockState(blockpos1, iblockstate3.withProperty(da.FACING, enumfacing).withProperty(da.CONDITIONAL, flag), 2);
                           break;
                        case AUTO:
                           in iblockstate2 = Nk.REPEATING_COMMAND_BLOCK.getDefaultState();
                           this.player.world.setBlockState(blockpos1, iblockstate2.withProperty(da.FACING, enumfacing).withProperty(da.CONDITIONAL, flag), 2);
                           break;
                        case REDSTONE:
                           in iblockstate = Nk.COMMAND_BLOCK.getDefaultState();
                           this.player.world.setBlockState(blockpos1, iblockstate.withProperty(da.FACING, enumfacing).withProperty(da.CONDITIONAL, flag), 2);
                     }

                     tileentity2.validate();
                     this.player.world.setTileEntity(blockpos1, tileentity2);
                     commandblockbaselogic.setCommand(s7);
                     commandblockbaselogic.setTrackOutput(flag3);
                     if (!flag3) {
                        commandblockbaselogic.setLastOutput((ITextComponent)null);
                     }

                     tileentitycommandblock.setAuto(flag1);
                     commandblockbaselogic.updateCommand();
                     if (!net.minecraft.util.StringUtils.isNullOrEmpty(s7)) {
                        this.player.sendMessage(new TextComponentTranslation("advMode.setCommand.success", new Object[]{s7}));
                     }
                  }
               } catch (Exception var22) {
                  exception1 = var22;
                  LOGGER.error("Couldn't set command block", exception1);
               }
            } else if ("MC|Beacon".equals(s)) {
               if (this.player.openContainer instanceof ContainerBeacon) {
                  try {
                     packetbuffer5 = packetIn.getBufferData();
                     j1 = packetbuffer5.readInt();
                     int k1 = packetbuffer5.readInt();
                     ContainerBeacon containerbeacon = (ContainerBeacon)this.player.openContainer;
                     Slot slot = containerbeacon.getSlot(0);
                     if (slot.getHasStack()) {
                        slot.decrStackSize(1);
                        IInventory iinventory = containerbeacon.getTileEntity();
                        iinventory.setField(1, j1);
                        iinventory.setField(2, k1);
                        iinventory.markDirty();
                     }
                  } catch (Exception var21) {
                     LOGGER.error("Couldn't set beacon", var21);
                  }
               }
            } else if ("MC|ItemName".equals(s)) {
               if (this.player.openContainer instanceof ContainerRepair) {
                  ContainerRepair containerrepair = (ContainerRepair)this.player.openContainer;
                  if (packetIn.getBufferData() != null && packetIn.getBufferData().readableBytes() >= 1) {
                     String s5 = ChatAllowedCharacters.filterAllowedCharacters(packetIn.getBufferData().readString(32767));
                     if (s5.length() <= 35) {
                        containerrepair.updateItemName(s5);
                     }
                  } else {
                     containerrepair.updateItemName("");
                  }
               }
            } else if ("MC|Struct".equals(s)) {
               if (!this.player.canUseCommandBlock()) {
                  return;
               }

               packetbuffer5 = packetIn.getBufferData();

               try {
                  BlockPos blockpos = new BlockPos(packetbuffer5.readInt(), packetbuffer5.readInt(), packetbuffer5.readInt());
                  in iblockstate1 = this.player.world.getBlockState(blockpos);
                  tileentity1 = this.player.world.getTileEntity(blockpos);
                  if (tileentity1 instanceof YV) {
                     YV tileentitystructure = (YV)tileentity1;
                     int l1 = packetbuffer5.readByte();
                     s8 = packetbuffer5.readString(32);
                     tileentitystructure.setMode(YU.valueOf(s8));
                     tileentitystructure.setName(packetbuffer5.readString(64));
                     int i2 = MathHelper.clamp(packetbuffer5.readInt(), -32, 32);
                     int j2 = MathHelper.clamp(packetbuffer5.readInt(), -32, 32);
                     int k2 = MathHelper.clamp(packetbuffer5.readInt(), -32, 32);
                     tileentitystructure.setPosition(new BlockPos(i2, j2, k2));
                     int l2 = MathHelper.clamp(packetbuffer5.readInt(), 0, 32);
                     int i3 = MathHelper.clamp(packetbuffer5.readInt(), 0, 32);
                     int j = MathHelper.clamp(packetbuffer5.readInt(), 0, 32);
                     tileentitystructure.setSize(new BlockPos(l2, i3, j));
                     String s2 = packetbuffer5.readString(32);
                     tileentitystructure.setMirror(Mirror.valueOf(s2));
                     String s3 = packetbuffer5.readString(32);
                     tileentitystructure.setRotation(Rotation.valueOf(s3));
                     tileentitystructure.setMetadata(packetbuffer5.readString(128));
                     tileentitystructure.setIgnoresEntities(packetbuffer5.readBoolean());
                     tileentitystructure.setShowAir(packetbuffer5.readBoolean());
                     tileentitystructure.setShowBoundingBox(packetbuffer5.readBoolean());
                     tileentitystructure.setIntegrity(MathHelper.clamp(packetbuffer5.readFloat(), 0.0F, 1.0F));
                     tileentitystructure.setSeed(packetbuffer5.readVarLong());
                     String s4 = tileentitystructure.getName();
                     if (l1 == 2) {
                        if (tileentitystructure.save()) {
                           this.player.sendStatusMessage(new TextComponentTranslation("structure_block.save_success", new Object[]{s4}), false);
                        } else {
                           this.player.sendStatusMessage(new TextComponentTranslation("structure_block.save_failure", new Object[]{s4}), false);
                        }
                     } else if (l1 == 3) {
                        if (!tileentitystructure.isStructureLoadable()) {
                           this.player.sendStatusMessage(new TextComponentTranslation("structure_block.load_not_found", new Object[]{s4}), false);
                        } else if (tileentitystructure.load()) {
                           this.player.sendStatusMessage(new TextComponentTranslation("structure_block.load_success", new Object[]{s4}), false);
                        } else {
                           this.player.sendStatusMessage(new TextComponentTranslation("structure_block.load_prepare", new Object[]{s4}), false);
                        }
                     } else if (l1 == 4) {
                        if (tileentitystructure.detectSize()) {
                           this.player.sendStatusMessage(new TextComponentTranslation("structure_block.size_success", new Object[]{s4}), false);
                        } else {
                           this.player.sendStatusMessage(new TextComponentTranslation("structure_block.size_failure", new Object[0]), false);
                        }
                     }

                     tileentitystructure.markDirty();
                     this.player.world.notifyBlockUpdate(blockpos, iblockstate1, iblockstate1, 3);
                  }
               } catch (Exception var20) {
                  exception1 = var20;
                  LOGGER.error("Couldn't set structure block", exception1);
               }
            } else if ("MC|PickItem".equals(s)) {
               packetbuffer5 = packetIn.getBufferData();

               try {
                  j1 = packetbuffer5.readVarInt();
                  this.player.inventory.pickItem(j1);
                  this.player.connection.sendPacket(new UJ(-2, this.player.inventory.currentItem, this.player.inventory.getStackInSlot(this.player.inventory.currentItem)));
                  this.player.connection.sendPacket(new UJ(-2, j1, this.player.inventory.getStackInSlot(j1)));
                  this.player.connection.sendPacket(new Uh(this.player.inventory.currentItem));
               } catch (Exception var19) {
                  exception1 = var19;
                  LOGGER.error("Couldn't pick item", exception1);
               }
            }
         }
      }

   }
}
