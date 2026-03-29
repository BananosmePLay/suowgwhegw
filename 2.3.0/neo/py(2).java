package neo;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHorseChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.ITabCompleter;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class py implements Ts {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Sp netManager;
   private final GameProfile profile;
   private final lg guiScreenServer;
   private nC client;
   private pm world;
   private boolean doneLoadingTerrain;
   private final Map<UUID, pB> playerInfoMap = Maps.newHashMap();
   public int currentServerMaxPlayers = 20;
   private boolean hasStatistics;
   private final oY advancementManager;
   private final Random avRandomizer = new Random();

   public py(nC mcIn, lg p_i46300_2_, Sp networkManagerIn, GameProfile profileIn) {
      this.client = mcIn;
      this.guiScreenServer = p_i46300_2_;
      this.netManager = networkManagerIn;
      this.profile = profileIn;
      this.advancementManager = new oY(mcIn);
   }

   public void cleanup() {
      this.world = null;
   }

   public void handleJoinGame(Ui packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.playerController = new pc(this.client, this);
      this.world = new pm(this, new biw(0L, packetIn.getGameType(), false, packetIn.isHardcoreMode(), packetIn.getWorldType()), packetIn.getDimension(), packetIn.getDifficulty(), this.client.profiler);
      nC var10000 = this.client;
      nC.gameSettings.difficulty = packetIn.getDifficulty();
      this.client.loadWorld(this.world);
      var10000 = this.client;
      nC.player.dimension = packetIn.getDimension();
      this.client.displayGuiScreen(new ke());
      var10000 = this.client;
      nC.player.setEntityId(packetIn.getPlayerId());
      this.currentServerMaxPlayers = packetIn.getMaxPlayers();
      var10000 = this.client;
      nC.player.setReducedDebug(packetIn.isReducedDebugInfo());
      this.client.playerController.setGameType(packetIn.getGameType());
      var10000 = this.client;
      nC.gameSettings.sendSettingsToServer();
      this.netManager.sendPacket(new SN("MC|Brand", (new SA(Unpooled.buffer())).writeString(je.getClientModName())));
   }

   public void handleSpawnObject(UP packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      Ig entity = null;
      if (packetIn.getType() == 10) {
         entity = Jc.create(this.world, d0, d1, d2, Jb.getById(packetIn.getData()));
      } else if (packetIn.getType() == 90) {
         Ig entity1 = this.world.getEntityByID(packetIn.getData());
         if (entity1 instanceof ME) {
            entity = new MU(this.world, (ME)entity1, d0, d1, d2);
         }

         packetIn.setData(0);
      } else if (packetIn.getType() == 60) {
         entity = new Ne(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 91) {
         entity = new Nc(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 61) {
         entity = new Nb(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 68) {
         entity = new MW(this.world, d0, d1, d2, (double)packetIn.getSpeedX() / 8000.0, (double)packetIn.getSpeedY() / 8000.0, (double)packetIn.getSpeedZ() / 8000.0);
      } else if (packetIn.getType() == 71) {
         entity = new IZ(this.world, new BlockPos(d0, d1, d2), EnumFacing.byHorizontalIndex(packetIn.getData()));
         packetIn.setData(0);
      } else if (packetIn.getType() == 77) {
         entity = new Ip(this.world, new BlockPos(MathHelper.floor(d0), MathHelper.floor(d1), MathHelper.floor(d2)));
         packetIn.setData(0);
      } else if (packetIn.getType() == 65) {
         entity = new IU(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 72) {
         entity = new IT(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 76) {
         entity = new IX(this.world, d0, d1, d2, Qy.EMPTY);
      } else if (packetIn.getType() == 63) {
         entity = new MV(this.world, d0, d1, d2, (double)packetIn.getSpeedX() / 8000.0, (double)packetIn.getSpeedY() / 8000.0, (double)packetIn.getSpeedZ() / 8000.0);
         packetIn.setData(0);
      } else if (packetIn.getType() == 93) {
         entity = new MP(this.world, d0, d1, d2, (double)packetIn.getSpeedX() / 8000.0, (double)packetIn.getSpeedY() / 8000.0, (double)packetIn.getSpeedZ() / 8000.0);
         packetIn.setData(0);
      } else if (packetIn.getType() == 64) {
         entity = new Na(this.world, d0, d1, d2, (double)packetIn.getSpeedX() / 8000.0, (double)packetIn.getSpeedY() / 8000.0, (double)packetIn.getSpeedZ() / 8000.0);
         packetIn.setData(0);
      } else if (packetIn.getType() == 66) {
         entity = new Nf(this.world, d0, d1, d2, (double)packetIn.getSpeedX() / 8000.0, (double)packetIn.getSpeedY() / 8000.0, (double)packetIn.getSpeedZ() / 8000.0);
         packetIn.setData(0);
      } else if (packetIn.getType() == 67) {
         entity = new MZ(this.world, d0, d1, d2, (double)packetIn.getSpeedX() / 8000.0, (double)packetIn.getSpeedY() / 8000.0, (double)packetIn.getSpeedZ() / 8000.0);
         packetIn.setData(0);
      } else if (packetIn.getType() == 62) {
         entity = new MQ(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 79) {
         entity = new MR(this.world, d0, d1, d2, 0.0F, 0, (Iw)null);
      } else if (packetIn.getType() == 73) {
         entity = new MY(this.world, d0, d1, d2, Qy.EMPTY);
         packetIn.setData(0);
      } else if (packetIn.getType() == 75) {
         entity = new IV(this.world, d0, d1, d2);
         packetIn.setData(0);
      } else if (packetIn.getType() == 1) {
         entity = new IR(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 50) {
         entity = new Jr(this.world, d0, d1, d2, (Iw)null);
      } else if (packetIn.getType() == 78) {
         entity = new IN(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 51) {
         entity = new IS(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 2) {
         entity = new IY(this.world, d0, d1, d2);
      } else if (packetIn.getType() == 70) {
         entity = new IW(this.world, d0, d1, d2, co.getStateById(packetIn.getData() & '\uffff'));
         packetIn.setData(0);
      } else if (packetIn.getType() == 3) {
         entity = new Ii(this.world, d0, d1, d2);
      }

      if (entity != null) {
         Iz.updateServerPosition((Ig)entity, d0, d1, d2);
         ((Ig)entity).rotationPitch = (float)(packetIn.getPitch() * 360) / 256.0F;
         ((Ig)entity).rotationYaw = (float)(packetIn.getYaw() * 360) / 256.0F;
         Ig[] aentity = ((Ig)entity).getParts();
         if (aentity != null) {
            int i = packetIn.getEntityID() - ((Ig)entity).getEntityId();
            Ig[] var11 = aentity;
            int var12 = aentity.length;

            for(int var13 = 0; var13 < var12; ++var13) {
               Ig entity2 = var11[var13];
               entity2.setEntityId(entity2.getEntityId() + i);
            }
         }

         ((Ig)entity).setEntityId(packetIn.getEntityID());
         ((Ig)entity).setUniqueId(packetIn.getUniqueId());
         this.world.addEntityToWorld(packetIn.getEntityID(), (Ig)entity);
         if (packetIn.getData() > 0) {
            if (packetIn.getType() == 60 || packetIn.getType() == 91) {
               Ig entity3 = this.world.getEntityByID(packetIn.getData() - 1);
               if (entity3 instanceof Iw && entity instanceof MO) {
                  ((MO)entity).shootingEntity = entity3;
               }
            }

            ((Ig)entity).setVelocity((double)packetIn.getSpeedX() / 8000.0, (double)packetIn.getSpeedY() / 8000.0, (double)packetIn.getSpeedZ() / 8000.0);
         }
      }

   }

   public void handleSpawnExperienceOrb(UM packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      Ig entity = new Js(this.world, d0, d1, d2, packetIn.getXPValue());
      Iz.updateServerPosition(entity, d0, d1, d2);
      entity.rotationYaw = 0.0F;
      entity.rotationPitch = 0.0F;
      ((Ig)entity).setEntityId(packetIn.getEntityID());
      this.world.addEntityToWorld(packetIn.getEntityID(), entity);
   }

   public void handleSpawnGlobalEntity(UN packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      Ig entity = null;
      if (packetIn.getType() == 1) {
         entity = new HX(this.world, d0, d1, d2, false);
      }

      if (entity != null) {
         Iz.updateServerPosition(entity, d0, d1, d2);
         entity.rotationYaw = 0.0F;
         entity.rotationPitch = 0.0F;
         ((Ig)entity).setEntityId(packetIn.getEntityId());
         this.world.addWeatherEffect(entity);
      }

   }

   public void handleSpawnPainting(UQ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Jq entitypainting = new Jq(this.world, packetIn.getPosition(), packetIn.getFacing(), packetIn.getTitle());
      entitypainting.setUniqueId(packetIn.getUniqueId());
      this.world.addEntityToWorld(packetIn.getEntityID(), entitypainting);
   }

   public void handleEntityVelocity(Uf packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityID());
      if (entity != null) {
         entity.setVelocity((double)packetIn.getMotionX() / 8000.0, (double)packetIn.getMotionY() / 8000.0, (double)packetIn.getMotionZ() / 8000.0);
      }

   }

   public void handleEntityMetadata(Ua packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityId());
      if (entity != null && packetIn.getDataManagerEntries() != null) {
         entity.getDataManager().setEntryValues(packetIn.getDataManagerEntries());
      }

   }

   public void handleSpawnPlayer(UR packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      float f = (float)(packetIn.getYaw() * 360) / 256.0F;
      float f1 = (float)(packetIn.getPitch() * 360) / 256.0F;
      jg entityotherplayermp = new jg(this.client.world, this.getPlayerInfo(packetIn.getUniqueId()).getGameProfile());
      entityotherplayermp.prevPosX = d0;
      entityotherplayermp.lastTickPosX = d0;
      entityotherplayermp.prevPosY = d1;
      entityotherplayermp.lastTickPosY = d1;
      entityotherplayermp.prevPosZ = d2;
      entityotherplayermp.lastTickPosZ = d2;
      Iz.updateServerPosition(entityotherplayermp, d0, d1, d2);
      entityotherplayermp.setPositionAndRotation(d0, d1, d2, f, f1);
      this.world.addEntityToWorld(packetIn.getEntityID(), entityotherplayermp);
      List<Ru<?>> list = packetIn.getDataManagerEntries();
      if (list != null) {
         entityotherplayermp.getDataManager().setEntryValues(list);
      }

   }

   public void handleEntityTeleport(Ue packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityId());
      if (entity != null) {
         double d0 = packetIn.getX();
         double d1 = packetIn.getY();
         double d2 = packetIn.getZ();
         Iz.updateServerPosition(entity, d0, d1, d2);
         if (!entity.canPassengerSteer()) {
            float f = (float)(packetIn.getYaw() * 360) / 256.0F;
            float f1 = (float)(packetIn.getPitch() * 360) / 256.0F;
            if (Math.abs(entity.posX - d0) < 0.03125 && Math.abs(entity.posY - d1) < 0.015625 && Math.abs(entity.posZ - d2) < 0.03125) {
               entity.setPositionAndRotationDirect(entity.posX, entity.posY, entity.posZ, f, f1, 0, true);
            } else {
               entity.setPositionAndRotationDirect(d0, d1, d2, f, f1, 3, true);
            }

            entity.onGround = packetIn.getOnGround();
         }
      }

   }

   public void handleHeldItemChange(Uh packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      if (MJ.isHotbar(packetIn.getHeldItemHotbarIndex())) {
         nC var10000 = this.client;
         nC.player.inventory.currentItem = packetIn.getHeldItemHotbarIndex();
      }

   }

   public void handleEntityMovement(TV packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = packetIn.getEntity(this.world);
      if (entity != null) {
         entity.serverPosX += (long)packetIn.getX();
         entity.serverPosY += (long)packetIn.getY();
         entity.serverPosZ += (long)packetIn.getZ();
         double d0 = (double)entity.serverPosX / 4096.0;
         double d1 = (double)entity.serverPosY / 4096.0;
         double d2 = (double)entity.serverPosZ / 4096.0;
         if (!entity.canPassengerSteer()) {
            float f = packetIn.isRotating() ? (float)(packetIn.getYaw() * 360) / 256.0F : entity.rotationYaw;
            float f1 = packetIn.isRotating() ? (float)(packetIn.getPitch() * 360) / 256.0F : entity.rotationPitch;
            entity.setPositionAndRotationDirect(d0, d1, d2, f, f1, 3, false);
            entity.onGround = packetIn.getOnGround();
         }
      }

   }

   public void handleEntityHeadLook(TZ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = packetIn.getEntity(this.world);
      if (entity != null) {
         float f = (float)(packetIn.getYaw() * 360) / 256.0F;
         entity.setRotationYawHead(f);
      }

   }

   public void handleDestroyEntities(TO packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);

      for(int i = 0; i < packetIn.getEntityIDs().length; ++i) {
         this.world.removeEntityFromWorld(packetIn.getEntityIDs()[i]);
      }

   }

   public void handlePlayerPosLook(Uy packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      ME entityplayer = nC.player;
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      float f = packetIn.getYaw();
      float f1 = packetIn.getPitch();
      if (packetIn.getFlags().contains(Ux.X)) {
         d0 += entityplayer.posX;
      } else {
         entityplayer.motionX = 0.0;
      }

      if (packetIn.getFlags().contains(Ux.Y)) {
         d1 += entityplayer.posY;
      } else {
         entityplayer.motionY = 0.0;
      }

      if (packetIn.getFlags().contains(Ux.Z)) {
         d2 += entityplayer.posZ;
      } else {
         entityplayer.motionZ = 0.0;
      }

      if (packetIn.getFlags().contains(Ux.X_ROT)) {
         f1 += entityplayer.rotationPitch;
      }

      if (packetIn.getFlags().contains(Ux.Y_ROT)) {
         f += entityplayer.rotationYaw;
      }

      ((ME)entityplayer).setPositionAndRotation(d0, d1, d2, f, f1);
      this.netManager.sendPacket(new SK(packetIn.getTeleportId()));
      this.netManager.sendPacket(new SW(entityplayer.posX, ((ME)entityplayer).getEntityBoundingBox().minY, entityplayer.posZ, entityplayer.rotationYaw, entityplayer.rotationPitch, false));
      if (!this.doneLoadingTerrain) {
         var10000 = this.client;
         nC var10001 = this.client;
         nC.player.prevPosX = nC.player.posX;
         var10000 = this.client;
         var10001 = this.client;
         nC.player.prevPosY = nC.player.posY;
         var10000 = this.client;
         var10001 = this.client;
         nC.player.prevPosZ = nC.player.posZ;
         this.doneLoadingTerrain = true;
         this.client.displayGuiScreen((lg)null);
      }

   }

   public void handleMultiBlockChange(Un packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Um[] var2 = packetIn.getChangedBlocks();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Um spacketmultiblockchange$blockupdatedata = var2[var4];
         this.world.invalidateRegionAndSetBlock(spacketmultiblockchange$blockupdatedata.getPos(), spacketmultiblockchange$blockupdatedata.getBlockState());
      }

   }

   public void handleChunkData(TE packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      if (packetIn.isFullChunk()) {
         this.world.doPreChunk(packetIn.getChunkX(), packetIn.getChunkZ(), true);
      }

      this.world.invalidateBlockReceiveRegion(packetIn.getChunkX() << 4, 0, packetIn.getChunkZ() << 4, (packetIn.getChunkX() << 4) + 15, 256, (packetIn.getChunkZ() << 4) + 15);
      bam chunk = this.world.getChunk(packetIn.getChunkX(), packetIn.getChunkZ());
      chunk.read(packetIn.getReadBuffer(), packetIn.getExtractedSize(), packetIn.isFullChunk());
      this.world.markBlockRangeForRenderUpdate(packetIn.getChunkX() << 4, 0, packetIn.getChunkZ() << 4, (packetIn.getChunkX() << 4) + 15, 256, (packetIn.getChunkZ() << 4) + 15);
      if (!packetIn.isFullChunk() || !(this.world.provider instanceof bip)) {
         chunk.resetRelightChecks();
      }

      Iterator var3 = packetIn.getTileEntityTags().iterator();

      while(var3.hasNext()) {
         QQ nbttagcompound = (QQ)var3.next();
         BlockPos blockpos = new BlockPos(nbttagcompound.getInteger("x"), nbttagcompound.getInteger("y"), nbttagcompound.getInteger("z"));
         Yg tileentity = this.world.getTileEntity(blockpos);
         if (tileentity != null) {
            tileentity.readFromNBT(nbttagcompound);
         }
      }

   }

   public void processChunkUnload(UZ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.world.doPreChunk(packetIn.getX(), packetIn.getZ(), false);
   }

   public void handleBlockChange(TA packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.world.invalidateRegionAndSetBlock(packetIn.getBlockPosition(), packetIn.getBlockState());
   }

   public void handleDisconnect(TP packetIn) {
      this.netManager.closeChannel(packetIn.getReason());
   }

   public void onDisconnect(ITextComponent reason) {
      this.client.loadWorld((pm)null);
      if (this.guiScreenServer != null) {
         this.client.displayGuiScreen(new kd(this.guiScreenServer, "disconnect.lost", reason));
      } else {
         this.client.displayGuiScreen(new kd(new kI(new 0cx()), "disconnect.lost", reason));
      }

   }

   public void sendPacket(Sz<?> packetIn) {
      this.netManager.sendPacket(packetIn);
   }

   public void handleCollectItem(TG packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getCollectedItemEntityID());
      Iw entitylivingbase = (Iw)this.world.getEntityByID(packetIn.getEntityID());
      if (entitylivingbase == null) {
         nC var10000 = this.client;
         entitylivingbase = nC.player;
      }

      if (entity != null) {
         if (entity instanceof Js) {
            this.world.playSound(entity.posX, entity.posY, entity.posZ, NO.ENTITY_EXPERIENCE_ORB_PICKUP, SoundCategory.PLAYERS, 0.1F, (this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 0.35F + 0.9F, false);
         } else {
            this.world.playSound(entity.posX, entity.posY, entity.posZ, NO.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, (this.avRandomizer.nextFloat() - this.avRandomizer.nextFloat()) * 1.4F + 2.0F, false);
         }

         if (entity instanceof IY) {
            ((IY)entity).getItem().setCount(packetIn.getAmount());
         }

         this.client.effectRenderer.addEffect(new qH(this.world, entity, (Ig)entitylivingbase, 0.5F));
         this.world.removeEntityFromWorld(packetIn.getCollectedItemEntityID());
      }

   }

   public void handleChat(TD packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.ingameGUI.addChatMessage(packetIn.getType(), packetIn.getChatComponent());
   }

   public void handleAnimation(Tx packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityID());
      if (entity != null) {
         Iw entitylivingbase1;
         if (packetIn.getAnimationType() == 0) {
            entitylivingbase1 = (Iw)entity;
            entitylivingbase1.swingArm(EnumHand.MAIN_HAND);
         } else if (packetIn.getAnimationType() == 3) {
            entitylivingbase1 = (Iw)entity;
            entitylivingbase1.swingArm(EnumHand.OFF_HAND);
         } else if (packetIn.getAnimationType() == 1) {
            entity.performHurtAnimation();
         } else if (packetIn.getAnimationType() == 2) {
            ME entityplayer = (ME)entity;
            entityplayer.wakeUpPlayer(false, false, false);
         } else if (packetIn.getAnimationType() == 4) {
            this.client.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT);
         } else if (packetIn.getAnimationType() == 5) {
            this.client.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.CRIT_MAGIC);
         }
      }

   }

   public void handleUseBed(Vh packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      packetIn.getPlayer(this.world).trySleep(packetIn.getBedPosition());
   }

   public void handleSpawnMob(UO packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      double d0 = packetIn.getX();
      double d1 = packetIn.getY();
      double d2 = packetIn.getZ();
      float f = (float)(packetIn.getYaw() * 360) / 256.0F;
      float f1 = (float)(packetIn.getPitch() * 360) / 256.0F;
      Iw entitylivingbase = (Iw)Ir.createEntityByID(packetIn.getEntityType(), this.client.world);
      if (entitylivingbase != null) {
         Iz.updateServerPosition(entitylivingbase, d0, d1, d2);
         entitylivingbase.renderYawOffset = (float)(packetIn.getHeadPitch() * 360) / 256.0F;
         entitylivingbase.rotationYawHead = (float)(packetIn.getHeadPitch() * 360) / 256.0F;
         Ig[] aentity = entitylivingbase.getParts();
         if (aentity != null) {
            int i = packetIn.getEntityID() - entitylivingbase.getEntityId();
            Ig[] var13 = aentity;
            int var14 = aentity.length;

            for(int var15 = 0; var15 < var14; ++var15) {
               Ig entity = var13[var15];
               entity.setEntityId(entity.getEntityId() + i);
            }
         }

         entitylivingbase.setEntityId(packetIn.getEntityID());
         entitylivingbase.setUniqueId(packetIn.getUniqueId());
         entitylivingbase.setPositionAndRotation(d0, d1, d2, f, f1);
         entitylivingbase.motionX = (double)((float)packetIn.getVelocityX() / 8000.0F);
         entitylivingbase.motionY = (double)((float)packetIn.getVelocityY() / 8000.0F);
         entitylivingbase.motionZ = (double)((float)packetIn.getVelocityZ() / 8000.0F);
         this.world.addEntityToWorld(packetIn.getEntityID(), entitylivingbase);
         List<Ru<?>> list = packetIn.getDataManagerEntries();
         if (list != null) {
            entitylivingbase.getDataManager().setEntryValues(list);
         }
      }

   }

   public void handleTimeUpdate(UW packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.world.setTotalWorldTime(packetIn.getTotalWorldTime());
      this.client.world.setWorldTime(packetIn.getWorldTime());
   }

   public void handleSpawnPosition(US packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      nC.player.setSpawnPoint(packetIn.getSpawnPos(), true);
      this.client.world.getWorldInfo().setSpawn(packetIn.getSpawnPos());
   }

   public void handleSetPassengers(UI packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityId());
      if (entity == null) {
         LOGGER.warn("Received passengers for unknown entity");
      } else {
         nC var10001 = this.client;
         boolean flag = entity.isRidingOrBeingRiddenBy(nC.player);
         entity.removePassengers();
         int[] var4 = packetIn.getPassengerIds();
         int var5 = var4.length;

         for(int var6 = 0; var6 < var5; ++var6) {
            int i = var4[var6];
            Ig entity1 = this.world.getEntityByID(i);
            if (entity1 != null) {
               entity1.startRiding(entity, true);
               var10001 = this.client;
               if (entity1 == nC.player && !flag) {
                  kn var10000 = this.client.ingameGUI;
                  Object[] var10002 = new Object[1];
                  nC var10005 = this.client;
                  var10002[0] = Bj.getKeyDisplayString(nC.gameSettings.keyBindSneak.getKeyCode());
                  var10000.setOverlayMessage(Ax.format("mount.onboard", var10002), false);
               }
            }
         }
      }

   }

   public void handleEntityAttach(TW packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityId());
      Ig entity1 = this.world.getEntityByID(packetIn.getVehicleEntityId());
      if (entity instanceof Iu) {
         if (entity1 != null) {
            ((Iu)entity).setLeashHolder(entity1, false);
         } else {
            ((Iu)entity).clearLeashed(false, false);
         }
      }

   }

   public void handleEntityStatus(Ud packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = packetIn.getEntity(this.world);
      if (entity != null) {
         if (packetIn.getOpCode() == 21) {
            this.client.getSoundHandler().playSound(new iA((Kc)entity));
         } else if (packetIn.getOpCode() == 35) {
            int i = true;
            this.client.effectRenderer.emitParticleAtEntity(entity, EnumParticleTypes.TOTEM, 30);
            this.world.playSound(entity.posX, entity.posY, entity.posZ, NO.ITEM_TOTEM_USE, entity.getSoundCategory(), 1.0F, 1.0F, false);
            nC var10001 = this.client;
            if (entity == nC.player) {
               this.client.entityRenderer.displayItemActivation(new Qy(NK.TOTEM_OF_UNDYING));
            }
         } else {
            entity.handleStatusUpdate(packetIn.getOpCode());
         }
      }

   }

   public void handleUpdateHealth(Vd packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      nC.player.setPlayerSPHealth(packetIn.getHealth());
      var10000 = this.client;
      nC.player.getFoodStats().setFoodLevel(packetIn.getFoodLevel());
      var10000 = this.client;
      nC.player.getFoodStats().setFoodSaturationLevel(packetIn.getSaturationLevel());
   }

   public void handleSetExperience(UH packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      nC.player.setXPStats(packetIn.getExperienceBar(), packetIn.getTotalExperience(), packetIn.getLevel());
   }

   public void handleRespawn(UD packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      int var10000 = packetIn.getDimensionID();
      nC var10001 = this.client;
      if (var10000 != nC.player.dimension) {
         this.doneLoadingTerrain = false;
         Ws scoreboard = this.world.getScoreboard();
         this.world = new pm(this, new biw(0L, packetIn.getGameType(), false, this.client.world.getWorldInfo().isHardcoreModeEnabled(), packetIn.getWorldType()), packetIn.getDimensionID(), packetIn.getDifficulty(), this.client.profiler);
         this.world.setWorldScoreboard(scoreboard);
         this.client.loadWorld(this.world);
         nC var3 = this.client;
         nC.player.dimension = packetIn.getDimensionID();
         this.client.displayGuiScreen(new ke());
      }

      this.client.setDimensionAndSpawnPlayer(packetIn.getDimensionID());
      this.client.playerController.setGameType(packetIn.getGameType());
   }

   public void handleExplosion(Ug packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      baX explosion = new baX(this.client.world, (Ig)null, packetIn.getX(), packetIn.getY(), packetIn.getZ(), packetIn.getStrength(), packetIn.getAffectedBlockPositions());
      explosion.doExplosionB(true);
      nC var10000 = this.client;
      jh var3 = nC.player;
      var3.motionX += (double)packetIn.getMotionX();
      var10000 = this.client;
      var3 = nC.player;
      var3.motionY += (double)packetIn.getMotionY();
      var10000 = this.client;
      var3 = nC.player;
      var3.motionZ += (double)packetIn.getMotionZ();
   }

   public void handleOpenWindow(Uo packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      jh entityplayersp = nC.player;
      if ("minecraft:container".equals(packetIn.getGuiId())) {
         entityplayersp.displayGUIChest(new InventoryBasic(packetIn.getWindowTitle(), packetIn.getSlotCount()));
         entityplayersp.openContainer.windowId = packetIn.getWindowId();
      } else if ("minecraft:villager".equals(packetIn.getGuiId())) {
         entityplayersp.displayVillagerTradeGui(new Ls(entityplayersp, packetIn.getWindowTitle()));
         entityplayersp.openContainer.windowId = packetIn.getWindowId();
      } else if ("EntityHorse".equals(packetIn.getGuiId())) {
         Ig entity = this.world.getEntityByID(packetIn.getEntityId());
         if (entity instanceof Lw) {
            entityplayersp.openGuiHorseInventory((Lw)entity, new ContainerHorseChest(packetIn.getWindowTitle(), packetIn.getSlotCount()));
            entityplayersp.openContainer.windowId = packetIn.getWindowId();
         }
      } else if (!packetIn.hasSlots()) {
         entityplayersp.displayGui(new rD(packetIn.getGuiId(), packetIn.getWindowTitle()));
         entityplayersp.openContainer.windowId = packetIn.getWindowId();
      } else {
         IInventory iinventory = new rC(packetIn.getGuiId(), packetIn.getWindowTitle(), packetIn.getSlotCount());
         entityplayersp.displayGUIChest(iinventory);
         entityplayersp.openContainer.windowId = packetIn.getWindowId();
      }

   }

   public void handleSetSlot(UJ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      ME entityplayer = nC.player;
      Qy itemstack = packetIn.getStack();
      int i = packetIn.getSlot();
      this.client.getTutorial().handleSetSlot(itemstack);
      if (packetIn.getWindowId() == -1) {
         entityplayer.inventory.setItemStack(itemstack);
      } else if (packetIn.getWindowId() == -2) {
         entityplayer.inventory.setInventorySlotContents(i, itemstack);
      } else {
         boolean flag = false;
         if (this.client.currentScreen instanceof lY) {
            lY guicontainercreative = (lY)this.client.currentScreen;
            flag = guicontainercreative.getSelectedTabIndex() != EN.INVENTORY.getIndex();
         }

         if (packetIn.getWindowId() == 0 && packetIn.getSlot() >= 36 && i < 45) {
            if (!itemstack.isEmpty()) {
               Qy itemstack1 = entityplayer.inventoryContainer.getSlot(i).getStack();
               if (itemstack1.isEmpty() || itemstack1.getCount() < itemstack.getCount()) {
                  itemstack.setAnimationsToGo(5);
               }
            }

            entityplayer.inventoryContainer.putStackInSlot(i, itemstack);
         } else if (packetIn.getWindowId() == entityplayer.openContainer.windowId && (packetIn.getWindowId() != 0 || !flag)) {
            entityplayer.openContainer.putStackInSlot(i, itemstack);
         }
      }

   }

   public void handleConfirmTransaction(TK packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Container container = null;
      nC var10000 = this.client;
      ME entityplayer = nC.player;
      if (packetIn.getWindowId() == 0) {
         container = entityplayer.inventoryContainer;
      } else if (packetIn.getWindowId() == entityplayer.openContainer.windowId) {
         container = entityplayer.openContainer;
      }

      if (container != null && !packetIn.wasAccepted()) {
         this.sendPacket(new SL(packetIn.getWindowId(), packetIn.getActionNumber(), true));
      }

   }

   public void handleWindowItems(Vi packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      ME entityplayer = nC.player;
      if (packetIn.getWindowId() == 0) {
         entityplayer.inventoryContainer.setAll(packetIn.getItemStacks());
      } else if (packetIn.getWindowId() == entityplayer.openContainer.windowId) {
         entityplayer.openContainer.setAll(packetIn.getItemStacks());
      }

   }

   public void handleSignEditorOpen(UK packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Yg tileentity = this.world.getTileEntity(packetIn.getSignPosition());
      if (!(tileentity instanceof YQ)) {
         tileentity = new YQ();
         ((Yg)tileentity).setWorld(this.world);
         ((Yg)tileentity).setPos(packetIn.getSignPosition());
      }

      nC var10000 = this.client;
      nC.player.openEditSign((YQ)tileentity);
   }

   public void handleUpdateTileEntity(Vg packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      if (this.client.world.isBlockLoaded(packetIn.getPos())) {
         Yg tileentity = this.client.world.getTileEntity(packetIn.getPos());
         int i = packetIn.getTileEntityType();
         boolean flag = i == 2 && tileentity instanceof Yq;
         if (i == 1 && tileentity instanceof YG || flag || i == 3 && tileentity instanceof Yj || i == 4 && tileentity instanceof YR || i == 5 && tileentity instanceof Yz || i == 6 && tileentity instanceof Yh || i == 7 && tileentity instanceof YV || i == 8 && tileentity instanceof Yx || i == 9 && tileentity instanceof YQ || i == 10 && tileentity instanceof YN || i == 11 && tileentity instanceof Yk) {
            QQ nbt = packetIn.getNbtCompound();
            tileentity.readFromNBT(nbt);
         }

         if (flag && this.client.currentScreen instanceof jS) {
            ((jS)this.client.currentScreen).updateGui();
         }
      }

   }

   public void handleWindowProperty(Vj packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      ME entityplayer = nC.player;
      if (entityplayer.openContainer != null && entityplayer.openContainer.windowId == packetIn.getWindowId()) {
         entityplayer.openContainer.updateProgressBar(packetIn.getProperty(), packetIn.getValue());
      }

   }

   public void handleEntityEquipment(TY packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityID());
      if (entity != null) {
         entity.setItemStackToSlot(packetIn.getEquipmentSlot(), packetIn.getItemStack());
      }

   }

   public void handleCloseWindow(TF packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      nC.player.closeScreenAndDropStack();
   }

   public void handleBlockAction(Ty packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.world.addBlockEvent(packetIn.getBlockPosition(), packetIn.getBlockType(), packetIn.getData1(), packetIn.getData2());
   }

   public void handleBlockBreakAnim(Tz packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.world.sendBlockBreakProgress(packetIn.getBreakerId(), packetIn.getPosition(), packetIn.getProgress());
   }

   public void handleChangeGameState(TC packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      ME entityplayer = nC.player;
      int i = packetIn.getGameState();
      float f = packetIn.getValue();
      int j = MathHelper.floor(f + 0.5F);
      if (i >= 0 && i < TC.MESSAGE_NAMES.length && TC.MESSAGE_NAMES[i] != null) {
         ((ME)entityplayer).sendStatusMessage(new TextComponentTranslation(TC.MESSAGE_NAMES[i], new Object[0]), false);
      }

      if (i == 1) {
         this.world.getWorldInfo().setRaining(true);
         this.world.setRainStrength(0.0F);
      } else if (i == 2) {
         this.world.getWorldInfo().setRaining(false);
         this.world.setRainStrength(1.0F);
      } else if (i == 3) {
         this.client.playerController.setGameType(bbb.getByID(j));
      } else if (i == 4) {
         if (j == 0) {
            var10000 = this.client;
            nC.player.connection.sendPacket(new SI(SH.PERFORM_RESPAWN));
            this.client.displayGuiScreen(new ke());
         } else if (j == 1) {
            this.client.displayGuiScreen(new lH(true, () -> {
               nC var10000 = this.client;
               nC.player.connection.sendPacket(new SI(SH.PERFORM_RESPAWN));
            }));
         }
      } else if (i == 5) {
         var10000 = this.client;
         Bj gamesettings = nC.gameSettings;
         if (f == 0.0F) {
            this.client.displayGuiScreen(new lo());
         } else if (f == 101.0F) {
            this.client.ingameGUI.getChatGUI().printChatMessage(new TextComponentTranslation("demo.help.movement", new Object[]{Bj.getKeyDisplayString(gamesettings.keyBindForward.getKeyCode()), Bj.getKeyDisplayString(gamesettings.keyBindLeft.getKeyCode()), Bj.getKeyDisplayString(gamesettings.keyBindBack.getKeyCode()), Bj.getKeyDisplayString(gamesettings.keyBindRight.getKeyCode())}));
         } else if (f == 102.0F) {
            this.client.ingameGUI.getChatGUI().printChatMessage(new TextComponentTranslation("demo.help.jump", new Object[]{Bj.getKeyDisplayString(gamesettings.keyBindJump.getKeyCode())}));
         } else if (f == 103.0F) {
            this.client.ingameGUI.getChatGUI().printChatMessage(new TextComponentTranslation("demo.help.inventory", new Object[]{Bj.getKeyDisplayString(gamesettings.keyBindInventory.getKeyCode())}));
         }
      } else if (i == 6) {
         this.world.playSound(entityplayer, entityplayer.posX, entityplayer.posY + (double)((ME)entityplayer).getEyeHeight(), entityplayer.posZ, NO.ENTITY_ARROW_HIT_PLAYER, SoundCategory.PLAYERS, 0.18F, 0.45F);
      } else if (i == 7) {
         this.world.setRainStrength(f);
      } else if (i == 8) {
         this.world.setThunderStrength(f);
      } else if (i == 10) {
         this.world.spawnParticle(EnumParticleTypes.MOB_APPEARANCE, entityplayer.posX, entityplayer.posY, entityplayer.posZ, 0.0, 0.0, 0.0, new int[0]);
         this.world.playSound(entityplayer, entityplayer.posX, entityplayer.posY, entityplayer.posZ, NO.ENTITY_ELDER_GUARDIAN_CURSE, SoundCategory.HOSTILE, 1.0F, 1.0F);
      }

   }

   public void handleMaps(Uk packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      mo mapitemrenderer = this.client.entityRenderer.getMapItemRenderer();
      bhE mapdata = PT.loadMapData(packetIn.getMapId(), this.client.world);
      if (mapdata == null) {
         String s = "map_" + packetIn.getMapId();
         mapdata = new bhE(s);
         if (mapitemrenderer.getMapInstanceIfExists(s) != null) {
            bhE mapdata1 = mapitemrenderer.getData(mapitemrenderer.getMapInstanceIfExists(s));
            if (mapdata1 != null) {
               mapdata = mapdata1;
            }
         }

         this.client.world.setData(s, mapdata);
      }

      packetIn.setMapdataTo(mapdata);
      mapitemrenderer.updateMapTexture(mapdata);
   }

   public void handleEffect(TR packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      if (packetIn.isSoundServerwide()) {
         this.client.world.playBroadcastSound(packetIn.getSoundType(), packetIn.getSoundPos(), packetIn.getSoundData());
      } else {
         this.client.world.playEvent(packetIn.getSoundType(), packetIn.getSoundPos(), packetIn.getSoundData());
      }

   }

   public void handleAdvancementInfo(Tw packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.advancementManager.read(packetIn);
   }

   public void handleSelectAdvancementsTab(UF packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      ResourceLocation resourcelocation = packetIn.getTab();
      if (resourcelocation == null) {
         this.advancementManager.setSelectedTab((b)null, false);
      } else {
         b advancement = this.advancementManager.getAdvancementList().getAdvancement(resourcelocation);
         this.advancementManager.setSelectedTab(advancement, false);
      }

   }

   public void handleStatistics(UT packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Iterator var2 = packetIn.getStatisticMap().entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<XQ, Integer> entry = (Map.Entry)var2.next();
         XQ statbase = (XQ)entry.getKey();
         int k = (Integer)entry.getValue();
         nC var10000 = this.client;
         XT var6 = nC.player.getStatFileWriter();
         nC var10001 = this.client;
         var6.unlockAchievement(nC.player, statbase, k);
      }

      this.hasStatistics = true;
      if (this.client.currentScreen instanceof ml) {
         ((ml)this.client.currentScreen).onStatsUpdated();
      }

   }

   public void handleRecipeBook(UA packetIn) {
      XK recipebook;
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      recipebook = nC.player.getRecipeBook();
      recipebook.setGuiOpen(packetIn.isGuiOpen());
      recipebook.setFilteringCraftable(packetIn.isFilteringCraftable());
      Uz spacketrecipebook$state = packetIn.getState();
      label21:
      switch (spacketrecipebook$state) {
         case REMOVE:
            Iterator iterator = packetIn.getRecipes().iterator();

            while(true) {
               if (!iterator.hasNext()) {
                  break label21;
               }

               NT irecipe = (NT)iterator.next();
               recipebook.lock(irecipe);
            }
         case INIT:
            packetIn.getRecipes().forEach(recipebook::unlock);
            packetIn.getDisplayedRecipes().forEach(recipebook::markNew);
            break;
         case ADD:
            packetIn.getRecipes().forEach((p_194025_2_) -> {
               recipebook.unlock(p_194025_2_);
               recipebook.markNew(p_194025_2_);
               ng.addOrUpdate(this.client.getToastGui(), p_194025_2_);
            });
      }

      BP.ALL_RECIPES.forEach((p_194023_1_) -> {
         p_194023_1_.updateKnownRecipes(recipebook);
      });
      if (this.client.currentScreen instanceof mx) {
         ((mx)this.client.currentScreen).recipesUpdated();
      }

   }

   public void handleEntityEffect(TX packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityId());
      if (entity instanceof Iw) {
         VW potion = VW.getPotionById(packetIn.getEffectId());
         if (potion != null) {
            VZ potioneffect = new VZ(potion, packetIn.getDuration(), packetIn.getAmplifier(), packetIn.getIsAmbient(), packetIn.doesShowParticles());
            potioneffect.setPotionDurationMax(packetIn.isMaxDuration());
            ((Iw)entity).addPotionEffect(potioneffect);
         }
      }

   }

   public void handleCombatEvent(TJ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      if (packetIn.eventType == TI.ENTITY_DIED) {
         Ig entity = this.world.getEntityByID(packetIn.playerId);
         nC var10001 = this.client;
         if (entity == nC.player) {
            this.client.displayGuiScreen(new kk(packetIn.deathMessage));
         }
      }

   }

   public void handleServerDifficulty(UG packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.world.getWorldInfo().setDifficulty(packetIn.getDifficulty());
      this.client.world.getWorldInfo().setDifficultyLocked(packetIn.isDifficultyLocked());
   }

   public void handleCamera(TB packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = packetIn.getEntity(this.world);
      if (entity != null) {
         this.client.setRenderViewEntity(entity);
      }

   }

   public void handleWorldBorder(Vm packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      packetIn.apply(this.world.getWorldBorder());
   }

   public void handleTitle(UY packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      UX spackettitle$type = packetIn.getType();
      String s = null;
      String s1 = null;
      String s2 = packetIn.getMessage() != null ? packetIn.getMessage().getFormattedText() : "";
      switch (spackettitle$type) {
         case TITLE:
            s = s2;
            break;
         case SUBTITLE:
            s1 = s2;
            break;
         case ACTIONBAR:
            this.client.ingameGUI.setOverlayMessage(s2, false);
            return;
         case RESET:
            this.client.ingameGUI.displayTitle("", "", -1, -1, -1);
            this.client.ingameGUI.setDefaultTitlesTimes();
            return;
      }

      this.client.ingameGUI.displayTitle(s, s1, packetIn.getFadeInTime(), packetIn.getDisplayTime(), packetIn.getFadeOutTime());
   }

   public void handlePlayerListHeaderFooter(Us packetIn) {
      this.client.ingameGUI.getTabList().setHeader(packetIn.getHeader().getFormattedText().isEmpty() ? null : packetIn.getHeader());
      this.client.ingameGUI.getTabList().setFooter(packetIn.getFooter().getFormattedText().isEmpty() ? null : packetIn.getFooter());
   }

   public void handleRemoveEntityEffect(UB packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = packetIn.getEntity(this.world);
      if (entity instanceof Iw) {
         ((Iw)entity).removeActivePotionEffect(packetIn.getPotion());
      }

   }

   public void handlePlayerListItem(Uw packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Iterator var2 = packetIn.getEntries().iterator();

      while(var2.hasNext()) {
         Uv playerdata = (Uv)var2.next();
         if (packetIn.getAction() == Uu.REMOVE_PLAYER) {
            this.playerInfoMap.remove(playerdata.getProfile().getId());
         } else {
            pB networkplayerinfo = (pB)this.playerInfoMap.get(playerdata.getProfile().getId());
            if (packetIn.getAction() == Uu.ADD_PLAYER) {
               networkplayerinfo = new pB(playerdata);
               this.playerInfoMap.put(networkplayerinfo.getGameProfile().getId(), networkplayerinfo);
            }

            switch (packetIn.getAction()) {
               case ADD_PLAYER:
                  networkplayerinfo.setGameType(playerdata.getGameMode());
                  networkplayerinfo.setResponseTime(playerdata.getPing());
                  break;
               case UPDATE_GAME_MODE:
                  networkplayerinfo.setGameType(playerdata.getGameMode());
                  break;
               case UPDATE_LATENCY:
                  networkplayerinfo.setResponseTime(playerdata.getPing());
                  break;
               case UPDATE_DISPLAY_NAME:
                  networkplayerinfo.setDisplayName(playerdata.getDisplayName());
            }
         }
      }

   }

   public void handleKeepAlive(Uj packetIn) {
      this.sendPacket(new ST(packetIn.getId()));
   }

   public void handlePlayerAbilities(Ur packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      ME entityplayer1 = nC.player;
      entityplayer1.capabilities.isFlying = packetIn.isFlying();
      entityplayer1.capabilities.isCreativeMode = packetIn.isCreativeMode();
      entityplayer1.capabilities.disableDamage = packetIn.isInvulnerable();
      entityplayer1.capabilities.allowFlying = packetIn.isAllowFlying();
      entityplayer1.capabilities.setFlySpeed(packetIn.getFlySpeed());
      entityplayer1.capabilities.setPlayerWalkSpeed(packetIn.getWalkSpeed());
   }

   public void handleTabComplete(UU packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      String[] astring = packetIn.getMatches();
      Arrays.sort((Object[])astring);
      if (this.client.currentScreen instanceof ITabCompleter) {
         ((ITabCompleter)this.client.currentScreen).setCompletions(astring);
      }

   }

   public void handleSoundEffect(UL packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10001 = this.client;
      this.client.world.playSound(nC.player, packetIn.getX(), packetIn.getY(), packetIn.getZ(), packetIn.getSound(), packetIn.getCategory(), packetIn.getVolume(), packetIn.getPitch());
   }

   public void handleCustomSound(TN packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.getSoundHandler().playSound(new iN(new ResourceLocation(packetIn.getSoundName()), packetIn.getCategory(), packetIn.getVolume(), packetIn.getPitch(), false, 0, iB.LINEAR, (float)packetIn.getX(), (float)packetIn.getY(), (float)packetIn.getZ()));
   }

   public void handleResourcePack(UC packetIn) {
      final String s = packetIn.getURL();
      final String s1 = packetIn.getHash();
      if (this.validateResourcePackUrl(s)) {
         if (s.startsWith("level://")) {
            try {
               String s2 = URLDecoder.decode(s.substring("level://".length()), StandardCharsets.UTF_8.toString());
               File file1 = new File(this.client.gameDir, "saves");
               File file2 = new File(file1, s2);
               if (file2.isFile()) {
                  this.netManager.sendPacket(new Th(Tg.ACCEPTED));
                  Futures.addCallback(this.client.getResourcePackRepository().setServerResourcePack(file2), this.createDownloadCallback());
                  return;
               }
            } catch (UnsupportedEncodingException var7) {
            }

            this.netManager.sendPacket(new Th(Tg.FAILED_DOWNLOAD));
         } else {
            pf serverdata = this.client.getCurrentServerData();
            if (serverdata != null && serverdata.getResourceMode() == pe.ENABLED) {
               this.netManager.sendPacket(new Th(Tg.ACCEPTED));
               Futures.addCallback(this.client.getResourcePackRepository().downloadResourcePack(s, s1), this.createDownloadCallback());
            } else if (serverdata != null && serverdata.getResourceMode() != pe.PROMPT) {
               this.netManager.sendPacket(new Th(Tg.DECLINED));
            } else {
               this.client.addScheduledTask(new Runnable() {
                  public void run() {
                     py.this.client.displayGuiScreen(new lK(new lL() {
                        public void confirmClicked(boolean result, int id) {
                           py.this.client = nC.getMinecraft();
                           pf serverdata1 = py.this.client.getCurrentServerData();
                           if (result) {
                              if (serverdata1 != null) {
                                 serverdata1.setResourceMode(pe.ENABLED);
                              }

                              py.this.netManager.sendPacket(new Th(Tg.ACCEPTED));
                              Futures.addCallback(py.this.client.getResourcePackRepository().downloadResourcePack(s, s1), py.this.createDownloadCallback());
                           } else {
                              if (serverdata1 != null) {
                                 serverdata1.setResourceMode(pe.DISABLED);
                              }

                              py.this.netManager.sendPacket(new Th(Tg.DECLINED));
                           }

                           pg.saveSingleServer(serverdata1);
                           py.this.client.displayGuiScreen((lg)null);
                        }
                     }, Ax.format("multiplayer.texturePrompt.line1"), Ax.format("multiplayer.texturePrompt.line2"), 0));
                  }
               });
            }
         }
      }

   }

   private boolean validateResourcePackUrl(String url) {
      try {
         URI uri = new URI(url);
         String s = uri.getScheme();
         boolean flag = "level".equals(s);
         if (!"http".equals(s) && !"https".equals(s) && !flag) {
            throw new URISyntaxException(url, "Wrong protocol");
         } else if (flag && (url.contains("..") || !url.endsWith("/resources.zip"))) {
            throw new URISyntaxException(url, "Invalid levelstorage resourcepack path");
         } else {
            return true;
         }
      } catch (URISyntaxException var5) {
         this.netManager.sendPacket(new Th(Tg.FAILED_DOWNLOAD));
         return false;
      }
   }

   private FutureCallback<Object> createDownloadCallback() {
      return new FutureCallback<Object>() {
         public void onSuccess(@Nullable Object p_onSuccess_1_) {
            py.this.netManager.sendPacket(new Th(Tg.SUCCESSFULLY_LOADED));
         }

         public void onFailure(Throwable p_onFailure_1_) {
            py.this.netManager.sendPacket(new Th(Tg.FAILED_DOWNLOAD));
         }
      };
   }

   public void handleUpdateBossInfo(Vc packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      this.client.ingameGUI.getBossOverlay().read(packetIn);
   }

   public void handleCooldown(TL packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000;
      if (packetIn.getTicks() == 0) {
         var10000 = this.client;
         nC.player.getCooldownTracker().removeCooldown(packetIn.getItem());
      } else {
         var10000 = this.client;
         nC.player.getCooldownTracker().setCooldown(packetIn.getItem(), packetIn.getTicks());
      }

   }

   public void handleMoveVehicle(Ul packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      nC var10000 = this.client;
      Ig entity = nC.player.getLowestRidingEntity();
      nC var10001 = this.client;
      if (entity != nC.player && entity.canPassengerSteer()) {
         entity.setPositionAndRotation(packetIn.getX(), packetIn.getY(), packetIn.getZ(), packetIn.getYaw(), packetIn.getPitch());
         this.netManager.sendPacket(new Tq(entity));
      }

   }

   public void handleCustomPayload(TM packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      SA packetbuffer3;
      int k;
      if ("MC|TrList".equals(packetIn.getChannelName())) {
         packetbuffer3 = packetIn.getBufferData();

         try {
            k = packetbuffer3.readInt();
            lg guiscreen = this.client.currentScreen;
            if (guiscreen != null && guiscreen instanceof kH) {
               nC var10001 = this.client;
               if (k == nC.player.openContainer.windowId) {
                  IH imerchant = ((kH)guiscreen).getMerchant();
                  YX merchantrecipelist = YX.readFromBuf(packetbuffer3);
                  imerchant.setRecipes(merchantrecipelist);
               }
            }
         } catch (IOException var10) {
            IOException ioexception = var10;
            LOGGER.error("Couldn't load trade info", ioexception);
         } finally {
            packetbuffer3.release();
         }
      } else {
         nC var10000;
         if ("MC|Brand".equals(packetIn.getChannelName())) {
            var10000 = this.client;
            nC.player.setServerBrand(packetIn.getBufferData().readString(32767));
         } else if ("MC|BOpen".equals(packetIn.getChannelName())) {
            EnumHand enumhand = (EnumHand)packetIn.getBufferData().readEnumValue(EnumHand.class);
            Qy var21;
            if (enumhand == EnumHand.OFF_HAND) {
               var10000 = this.client;
               var21 = nC.player.getHeldItemOffhand();
            } else {
               var10000 = this.client;
               var21 = nC.player.getHeldItemMainhand();
            }

            Qy itemstack = var21;
            if (itemstack.getItem() == NK.WRITTEN_BOOK) {
               nC var10003 = this.client;
               this.client.displayGuiScreen(new lk(nC.player, itemstack, false));
            }
         } else if ("MC|DebugPath".equals(packetIn.getChannelName())) {
            packetbuffer3 = packetIn.getBufferData();
            k = packetbuffer3.readInt();
            float f1 = packetbuffer3.readFloat();
            VI path = VI.read(packetbuffer3);
            ((uW)this.client.debugRenderer.pathfinding).addPath(k, path, f1);
         } else if ("MC|DebugNeighborsUpdate".equals(packetIn.getChannelName())) {
            packetbuffer3 = packetIn.getBufferData();
            long i1 = packetbuffer3.readVarLong();
            BlockPos blockpos = packetbuffer3.readBlockPos();
            ((uV)this.client.debugRenderer.neighborsUpdate).addUpdate(i1, blockpos);
         } else if ("MC|StopSound".equals(packetIn.getChannelName())) {
            packetbuffer3 = packetIn.getBufferData();
            String s = packetbuffer3.readString(32767);
            String s1 = packetbuffer3.readString(256);
            this.client.getSoundHandler().stop(s1, SoundCategory.getByName(s));
         }
      }

   }

   public void handleScoreboardObjective(UE packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ws scoreboard = this.world.getScoreboard();
      Wz scoreobjective1;
      if (packetIn.getAction() == 0) {
         scoreobjective1 = scoreboard.addScoreObjective(packetIn.getObjectiveName(), Wo.DUMMY);
         scoreobjective1.setDisplayName(packetIn.getObjectiveValue());
         scoreobjective1.setRenderType(packetIn.getRenderType());
      } else {
         scoreobjective1 = scoreboard.getObjective(packetIn.getObjectiveName());
         if (packetIn.getAction() == 1) {
            scoreboard.removeObjective(scoreobjective1);
         } else if (packetIn.getAction() == 2) {
            scoreobjective1.setDisplayName(packetIn.getObjectiveValue());
            scoreobjective1.setRenderType(packetIn.getRenderType());
         }
      }

   }

   public void handleUpdateScore(Vf packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ws scoreboard = this.world.getScoreboard();
      Wz scoreobjective = scoreboard.getObjective(packetIn.getObjectiveName());
      if (packetIn.getScoreAction() == Ve.CHANGE) {
         Wr score = scoreboard.getOrCreateScore(packetIn.getPlayerName(), scoreobjective);
         score.setScorePoints(packetIn.getScoreValue());
      } else if (packetIn.getScoreAction() == Ve.REMOVE) {
         if (StringUtils.isNullOrEmpty(packetIn.getObjectiveName())) {
            scoreboard.removeObjectiveFromEntity(packetIn.getPlayerName(), (Wz)null);
         } else if (scoreobjective != null) {
            scoreboard.removeObjectiveFromEntity(packetIn.getPlayerName(), scoreobjective);
         }
      }

   }

   public void handleDisplayObjective(TQ packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ws scoreboard = this.world.getScoreboard();
      if (packetIn.getName().isEmpty()) {
         scoreboard.setObjectiveInDisplaySlot(packetIn.getPosition(), (Wz)null);
      } else {
         Wz scoreobjective = scoreboard.getObjective(packetIn.getName());
         scoreboard.setObjectiveInDisplaySlot(packetIn.getPosition(), scoreobjective);
      }

   }

   public void handleTeams(UV packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ws scoreboard = this.world.getScoreboard();
      WA scoreplayerteam;
      if (packetIn.getAction() == 0) {
         scoreplayerteam = scoreboard.createTeam(packetIn.getName());
      } else {
         scoreplayerteam = scoreboard.getTeam(packetIn.getName());
      }

      if (packetIn.getAction() == 0 || packetIn.getAction() == 2) {
         scoreplayerteam.setDisplayName(packetIn.getDisplayName());
         scoreplayerteam.setPrefix(packetIn.getPrefix());
         scoreplayerteam.setSuffix(packetIn.getSuffix());
         scoreplayerteam.setColor(TextFormatting.fromColorIndex(packetIn.getColor()));
         scoreplayerteam.setFriendlyFlags(packetIn.getFriendlyFlags());
         WD team$enumvisible = WD.getByName(packetIn.getNameTagVisibility());
         if (team$enumvisible != null) {
            scoreplayerteam.setNameTagVisibility(team$enumvisible);
         }

         WC team$collisionrule = WC.getByName(packetIn.getCollisionRule());
         if (team$collisionrule != null) {
            scoreplayerteam.setCollisionRule(team$collisionrule);
         }
      }

      Iterator var6;
      String s1;
      if (packetIn.getAction() == 0 || packetIn.getAction() == 3) {
         var6 = packetIn.getPlayers().iterator();

         while(var6.hasNext()) {
            s1 = (String)var6.next();
            scoreboard.addPlayerToTeam(s1, packetIn.getName());
         }
      }

      if (packetIn.getAction() == 4) {
         var6 = packetIn.getPlayers().iterator();

         while(var6.hasNext()) {
            s1 = (String)var6.next();
            scoreboard.removePlayerFromTeam(s1, scoreplayerteam);
         }
      }

      if (packetIn.getAction() == 1) {
         scoreboard.removeTeam(scoreplayerteam);
      }

   }

   public void handleParticles(Up packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      if (packetIn.getParticleCount() == 0) {
         double d0 = (double)(packetIn.getParticleSpeed() * packetIn.getXOffset());
         double d2 = (double)(packetIn.getParticleSpeed() * packetIn.getYOffset());
         double d4 = (double)(packetIn.getParticleSpeed() * packetIn.getZOffset());

         try {
            this.world.spawnParticle(packetIn.getParticleType(), packetIn.isLongDistance(), packetIn.getXCoordinate(), packetIn.getYCoordinate(), packetIn.getZCoordinate(), d0, d2, d4, packetIn.getParticleArgs());
         } catch (Throwable var17) {
            LOGGER.warn("Could not spawn particle effect {}", packetIn.getParticleType());
         }
      } else {
         for(int k = 0; k < packetIn.getParticleCount(); ++k) {
            double d1 = this.avRandomizer.nextGaussian() * (double)packetIn.getXOffset();
            double d3 = this.avRandomizer.nextGaussian() * (double)packetIn.getYOffset();
            double d5 = this.avRandomizer.nextGaussian() * (double)packetIn.getZOffset();
            double d6 = this.avRandomizer.nextGaussian() * (double)packetIn.getParticleSpeed();
            double d7 = this.avRandomizer.nextGaussian() * (double)packetIn.getParticleSpeed();
            double d8 = this.avRandomizer.nextGaussian() * (double)packetIn.getParticleSpeed();

            try {
               this.world.spawnParticle(packetIn.getParticleType(), packetIn.isLongDistance(), packetIn.getXCoordinate() + d1, packetIn.getYCoordinate() + d3, packetIn.getZCoordinate() + d5, d6, d7, d8, packetIn.getParticleArgs());
            } catch (Throwable var16) {
               LOGGER.warn("Could not spawn particle effect {}", packetIn.getParticleType());
               return;
            }
         }
      }

   }

   public void handleEntityProperties(Uc packetIn) {
      SC.checkThreadAndEnqueue(packetIn, this, this.client);
      Ig entity = this.world.getEntityByID(packetIn.getEntityId());
      if (entity != null) {
         if (!(entity instanceof Iw)) {
            throw new IllegalStateException("Server tried to update attributes of a non-living entity (actually: " + entity + ")");
         }

         FU abstractattributemap = ((Iw)entity).getAttributeMap();
         Iterator var4 = packetIn.getSnapshots().iterator();

         while(var4.hasNext()) {
            Ub spacketentityproperties$snapshot = (Ub)var4.next();
            FZ iattributeinstance = abstractattributemap.getAttributeInstanceByName(spacketentityproperties$snapshot.getName());
            if (iattributeinstance == null) {
               iattributeinstance = abstractattributemap.registerAttribute(new Gc((FY)null, spacketentityproperties$snapshot.getName(), 0.0, Double.MIN_NORMAL, Double.MAX_VALUE));
            }

            iattributeinstance.setBaseValue(spacketentityproperties$snapshot.getBaseValue());
            iattributeinstance.removeAllModifiers();
            Iterator var7 = spacketentityproperties$snapshot.getModifiers().iterator();

            while(var7.hasNext()) {
               FW attributemodifier = (FW)var7.next();
               iattributeinstance.applyModifier(attributemodifier);
            }
         }
      }

   }

   public void func_194307_a(Uq p_194307_1_) {
      SC.checkThreadAndEnqueue(p_194307_1_, this, this.client);
      nC var10000 = this.client;
      Container container = nC.player.openContainer;
      if (container.windowId == p_194307_1_.func_194313_b()) {
         nC var10001 = this.client;
         if (container.getCanCraft(nC.player) && this.client.currentScreen instanceof mx) {
            mu guirecipebook = ((mx)this.client.currentScreen).func_194310_f();
            guirecipebook.setupGhostRecipe(p_194307_1_.func_194311_a(), container.inventorySlots);
         }
      }

   }

   public Sp getNetworkManager() {
      return this.netManager;
   }

   public Collection<pB> getPlayerInfoMap() {
      return this.playerInfoMap.values();
   }

   public void addPlayerInfo(UUID uuid, pB player) {
      this.playerInfoMap.put(uuid, player);
   }

   public pB getPlayerInfo(UUID uniqueId) {
      return (pB)this.playerInfoMap.get(uniqueId);
   }

   @Nullable
   public pB getPlayerInfo(String name) {
      Iterator var2 = this.playerInfoMap.values().iterator();

      pB networkplayerinfo;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         networkplayerinfo = (pB)var2.next();
      } while(!networkplayerinfo.getGameProfile().getName().equals(name));

      return networkplayerinfo;
   }

   public GameProfile getGameProfile() {
      return this.profile;
   }

   public oY getAdvancementManager() {
      return this.advancementManager;
   }
}
