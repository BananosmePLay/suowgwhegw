package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class WY {
   public static final File FILE_PLAYERBANS = new File("banned-players.json");
   public static final File FILE_IPBANS = new File("banned-ips.json");
   public static final File FILE_OPS = new File("ops.json");
   public static final File FILE_WHITELIST = new File("whitelist.json");
   private static final Logger LOGGER = LogManager.getLogger();
   private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
   private final Xx server;
   private final List<MG> playerEntityList = Lists.newArrayList();
   private final Map<UUID, MG> uuidToPlayerMap = Maps.newHashMap();
   private final Xk bannedPlayers;
   private final Xo bannedIPs;
   private final Xq ops;
   private final Xs whiteListedPlayers;
   private final Map<UUID, XU> playerStatFiles;
   private final Map<UUID, cl> advancements;
   private bgk playerDataManager;
   private boolean whiteListEnforced;
   protected int maxPlayers;
   private int viewDistance;
   private bbb gameType;
   private boolean commandsAllowedForAll;
   private int playerPingIndex;

   public WY(Xx server) {
      this.bannedPlayers = new Xk(FILE_PLAYERBANS);
      this.bannedIPs = new Xo(FILE_IPBANS);
      this.ops = new Xq(FILE_OPS);
      this.whiteListedPlayers = new Xs(FILE_WHITELIST);
      this.playerStatFiles = Maps.newHashMap();
      this.advancements = Maps.newHashMap();
      this.server = server;
      this.bannedPlayers.setLanServer(false);
      this.bannedIPs.setLanServer(false);
      this.maxPlayers = 100;
   }

   public void initializeConnectionToPlayer(Sp netManager, MG playerIn) {
      GameProfile gameprofile = playerIn.getGameProfile();
      Xd playerprofilecache = this.server.getPlayerProfileCache();
      GameProfile gameprofile1 = playerprofilecache.getProfileByUUID(gameprofile.getId());
      String s = gameprofile1 == null ? gameprofile.getName() : gameprofile1.getName();
      playerprofilecache.addEntry(gameprofile);
      QQ nbttagcompound = this.readPlayerDataFromFile(playerIn);
      playerIn.setWorld(this.server.getWorld(playerIn.dimension));
      playerIn.interactionManager.setWorld((bis)playerIn.world);
      String s1 = "local";
      if (netManager.getRemoteAddress() != null) {
         s1 = netManager.getRemoteAddress().toString();
      }

      LOGGER.info("{}[{}] logged in with entity id {} at ({}, {}, {})", playerIn.getName(), s1, playerIn.getEntityId(), playerIn.posX, playerIn.posY, playerIn.posZ);
      bis worldserver = this.server.getWorld(playerIn.dimension);
      bhY worldinfo = worldserver.getWorldInfo();
      this.setPlayerGameTypeBasedOnOther(playerIn, (MG)null, worldserver);
      RY nethandlerplayserver = new RY(this.server, netManager, playerIn);
      nethandlerplayserver.sendPacket(new Ui(playerIn.getEntityId(), playerIn.interactionManager.getGameType(), worldinfo.isHardcoreModeEnabled(), worldserver.provider.getDimensionType().getId(), worldserver.getDifficulty(), this.getMaxPlayers(), worldinfo.getTerrainType(), worldserver.getGameRules().getBoolean("reducedDebugInfo")));
      nethandlerplayserver.sendPacket(new TM("MC|Brand", (new SA(Unpooled.buffer())).writeString(this.getServerInstance().getServerModName())));
      nethandlerplayserver.sendPacket(new UG(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
      nethandlerplayserver.sendPacket(new Ur(playerIn.capabilities));
      nethandlerplayserver.sendPacket(new Uh(playerIn.inventory.currentItem));
      this.updatePermissionLevel(playerIn);
      playerIn.getStatFile().markAllDirty();
      playerIn.getRecipeBook().init(playerIn);
      this.sendScoreboard((WB)worldserver.getScoreboard(), playerIn);
      this.server.refreshStatusNextTick();
      TextComponentTranslation textcomponenttranslation;
      if (playerIn.getName().equalsIgnoreCase(s)) {
         textcomponenttranslation = new TextComponentTranslation("multiplayer.player.joined", new Object[]{playerIn.getDisplayName()});
      } else {
         textcomponenttranslation = new TextComponentTranslation("multiplayer.player.joined.renamed", new Object[]{playerIn.getDisplayName(), s});
      }

      textcomponenttranslation.getStyle().setColor(TextFormatting.YELLOW);
      this.sendMessage(textcomponenttranslation);
      this.playerLoggedIn(playerIn);
      nethandlerplayserver.setPlayerLocation(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw, playerIn.rotationPitch);
      this.updateTimeAndWeatherForPlayer(playerIn, worldserver);
      if (!this.server.getResourcePackUrl().isEmpty()) {
         playerIn.loadResourcePack(this.server.getResourcePackUrl(), this.server.getResourcePackHash());
      }

      Iterator var13 = playerIn.getActivePotionEffects().iterator();

      while(var13.hasNext()) {
         VZ potioneffect = (VZ)var13.next();
         nethandlerplayserver.sendPacket(new TX(playerIn.getEntityId(), potioneffect));
      }

      if (nbttagcompound != null && nbttagcompound.hasKey("RootVehicle", 10)) {
         QQ nbttagcompound1 = nbttagcompound.getCompoundTag("RootVehicle");
         Ig entity1 = bav.readWorldEntity(nbttagcompound1.getCompoundTag("Entity"), worldserver, true);
         if (entity1 != null) {
            UUID uuid = nbttagcompound1.getUniqueId("Attach");
            Iterator var16;
            Ig entity2;
            if (entity1.getUniqueID().equals(uuid)) {
               playerIn.startRiding(entity1, true);
            } else {
               var16 = entity1.getRecursivePassengers().iterator();

               while(var16.hasNext()) {
                  entity2 = (Ig)var16.next();
                  if (entity2.getUniqueID().equals(uuid)) {
                     playerIn.startRiding(entity2, true);
                     break;
                  }
               }
            }

            if (!playerIn.isRiding()) {
               LOGGER.warn("Couldn't reattach entity to player");
               worldserver.removeEntityDangerously(entity1);
               var16 = entity1.getRecursivePassengers().iterator();

               while(var16.hasNext()) {
                  entity2 = (Ig)var16.next();
                  worldserver.removeEntityDangerously(entity2);
               }
            }
         }
      }

      playerIn.addSelfToInternalCraftingInventory();
   }

   protected void sendScoreboard(WB scoreboardIn, MG playerIn) {
      Set<Wz> set = Sets.newHashSet();
      Iterator var4 = scoreboardIn.getTeams().iterator();

      while(var4.hasNext()) {
         WA scoreplayerteam = (WA)var4.next();
         playerIn.connection.sendPacket(new UV(scoreplayerteam, 0));
      }

      for(int i = 0; i < 19; ++i) {
         Wz scoreobjective = scoreboardIn.getObjectiveInDisplaySlot(i);
         if (scoreobjective != null && !set.contains(scoreobjective)) {
            Iterator var6 = scoreboardIn.getCreatePackets(scoreobjective).iterator();

            while(var6.hasNext()) {
               Sz<?> packet = (Sz)var6.next();
               playerIn.connection.sendPacket(packet);
            }

            set.add(scoreobjective);
         }
      }

   }

   public void setPlayerManager(bis[] worldServers) {
      this.playerDataManager = worldServers[0].getSaveHandler().getPlayerNBTManager();
      worldServers[0].getWorldBorder().addListener(new ZZ() {
         public void onSizeChanged(bab border, double newSize) {
            WY.this.sendPacketToAllPlayers(new Vm(border, Vl.SET_SIZE));
         }

         public void onTransitionStarted(bab border, double oldSize, double newSize, long time) {
            WY.this.sendPacketToAllPlayers(new Vm(border, Vl.LERP_SIZE));
         }

         public void onCenterChanged(bab border, double x, double z) {
            WY.this.sendPacketToAllPlayers(new Vm(border, Vl.SET_CENTER));
         }

         public void onWarningTimeChanged(bab border, int newTime) {
            WY.this.sendPacketToAllPlayers(new Vm(border, Vl.SET_WARNING_TIME));
         }

         public void onWarningDistanceChanged(bab border, int newDistance) {
            WY.this.sendPacketToAllPlayers(new Vm(border, Vl.SET_WARNING_BLOCKS));
         }

         public void onDamageAmountChanged(bab border, double newAmount) {
         }

         public void onDamageBufferChanged(bab border, double newSize) {
         }
      });
   }

   public void preparePlayer(MG playerIn, @Nullable bis worldIn) {
      bis worldserver = playerIn.getServerWorld();
      if (worldIn != null) {
         worldIn.getPlayerChunkMap().removePlayer(playerIn);
      }

      worldserver.getPlayerChunkMap().addPlayer(playerIn);
      worldserver.getChunkProvider().provideChunk((int)playerIn.posX >> 4, (int)playerIn.posZ >> 4);
      if (worldIn != null) {
         bY.CHANGED_DIMENSION.trigger(playerIn, worldIn.provider.getDimensionType(), worldserver.provider.getDimensionType());
         if (worldIn.provider.getDimensionType() == baM.NETHER && playerIn.world.provider.getDimensionType() == baM.OVERWORLD && playerIn.getEnteredNetherPosition() != null) {
            bY.NETHER_TRAVEL.trigger(playerIn, playerIn.getEnteredNetherPosition());
         }
      }

   }

   public int getEntityViewDistance() {
      return WU.getFurthestViewableBlock(this.getViewDistance());
   }

   @Nullable
   public QQ readPlayerDataFromFile(MG playerIn) {
      QQ nbttagcompound = this.server.worlds[0].getWorldInfo().getPlayerNBTTagCompound();
      QQ nbttagcompound1;
      if (playerIn.getName().equals(this.server.getServerOwner()) && nbttagcompound != null) {
         nbttagcompound1 = nbttagcompound;
         playerIn.readFromNBT(nbttagcompound);
         LOGGER.debug("loading single player");
      } else {
         nbttagcompound1 = this.playerDataManager.readPlayerData(playerIn);
      }

      return nbttagcompound1;
   }

   protected void writePlayerData(MG playerIn) {
      this.playerDataManager.writePlayerData(playerIn);
      XU statisticsmanagerserver = (XU)this.playerStatFiles.get(playerIn.getUniqueID());
      if (statisticsmanagerserver != null) {
         statisticsmanagerserver.saveStatFile();
      }

      cl playeradvancements = (cl)this.advancements.get(playerIn.getUniqueID());
      if (playeradvancements != null) {
         playeradvancements.save();
      }

   }

   public void playerLoggedIn(MG playerIn) {
      this.playerEntityList.add(playerIn);
      this.uuidToPlayerMap.put(playerIn.getUniqueID(), playerIn);
      this.sendPacketToAllPlayers(new Uw(Uu.ADD_PLAYER, new MG[]{playerIn}));
      bis worldserver = this.server.getWorld(playerIn.dimension);

      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         playerIn.connection.sendPacket(new Uw(Uu.ADD_PLAYER, new MG[]{(MG)this.playerEntityList.get(i)}));
      }

      worldserver.spawnEntity(playerIn);
      this.preparePlayer(playerIn, (bis)null);
   }

   public void serverUpdateMovingPlayer(MG playerIn) {
      playerIn.getServerWorld().getPlayerChunkMap().updateMovingPlayer(playerIn);
   }

   public void playerLoggedOut(MG playerIn) {
      bis worldserver = playerIn.getServerWorld();
      playerIn.addStat(XV.LEAVE_GAME);
      this.writePlayerData(playerIn);
      if (playerIn.isRiding()) {
         Ig entity = playerIn.getLowestRidingEntity();
         if (entity.getRecursivePassengersByType(MG.class).size() == 1) {
            LOGGER.debug("Removing player mount");
            playerIn.dismountRidingEntity();
            worldserver.removeEntityDangerously(entity);
            Iterator var4 = entity.getRecursivePassengers().iterator();

            while(var4.hasNext()) {
               Ig entity1 = (Ig)var4.next();
               worldserver.removeEntityDangerously(entity1);
            }

            worldserver.getChunk(playerIn.chunkCoordX, playerIn.chunkCoordZ).markDirty();
         }
      }

      worldserver.removeEntity(playerIn);
      worldserver.getPlayerChunkMap().removePlayer(playerIn);
      playerIn.getAdvancements().dispose();
      this.playerEntityList.remove(playerIn);
      UUID uuid = playerIn.getUniqueID();
      MG entityplayermp = (MG)this.uuidToPlayerMap.get(uuid);
      if (entityplayermp == playerIn) {
         this.uuidToPlayerMap.remove(uuid);
         this.playerStatFiles.remove(uuid);
         this.advancements.remove(uuid);
      }

      this.sendPacketToAllPlayers(new Uw(Uu.REMOVE_PLAYER, new MG[]{playerIn}));
   }

   public String allowUserToConnect(SocketAddress address, GameProfile profile) {
      String s;
      if (this.bannedPlayers.isBanned(profile)) {
         Xl userlistbansentry = (Xl)this.bannedPlayers.getEntry(profile);
         s = "You are banned from this server!\nReason: " + userlistbansentry.getBanReason();
         if (userlistbansentry.getBanEndDate() != null) {
            s = s + "\nYour ban will be removed on " + DATE_FORMAT.format(userlistbansentry.getBanEndDate());
         }

         return s;
      } else if (!this.canJoin(profile)) {
         return "You are not white-listed on this server!";
      } else if (this.bannedIPs.isBanned(address)) {
         Xp userlistipbansentry = this.bannedIPs.getBanEntry(address);
         s = "Your IP address is banned from this server!\nReason: " + userlistipbansentry.getBanReason();
         if (userlistipbansentry.getBanEndDate() != null) {
            s = s + "\nYour ban will be removed on " + DATE_FORMAT.format(userlistipbansentry.getBanEndDate());
         }

         return s;
      } else {
         return this.playerEntityList.size() >= this.maxPlayers && !this.bypassesPlayerLimit(profile) ? "The server is full!" : null;
      }
   }

   public MG createPlayerForUser(GameProfile profile) {
      UUID uuid = ME.getUUID(profile);
      List<MG> list = Lists.newArrayList();

      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         MG entityplayermp = (MG)this.playerEntityList.get(i);
         if (entityplayermp.getUniqueID().equals(uuid)) {
            list.add(entityplayermp);
         }
      }

      MG entityplayermp2 = (MG)this.uuidToPlayerMap.get(profile.getId());
      if (entityplayermp2 != null && !list.contains(entityplayermp2)) {
         list.add(entityplayermp2);
      }

      Iterator var8 = list.iterator();

      while(var8.hasNext()) {
         MG entityplayermp1 = (MG)var8.next();
         entityplayermp1.connection.disconnect(new TextComponentTranslation("multiplayer.disconnect.duplicate_login", new Object[0]));
      }

      Object playerinteractionmanager;
      if (this.server.isDemo()) {
         playerinteractionmanager = new WN(this.server.getWorld(0));
      } else {
         playerinteractionmanager = new WW(this.server.getWorld(0));
      }

      return new MG(this.server, this.server.getWorld(0), profile, (WW)playerinteractionmanager);
   }

   public MG recreatePlayerEntity(MG playerIn, int dimension, boolean conqueredEnd) {
      playerIn.getServerWorld().getEntityTracker().removePlayerFromTrackers(playerIn);
      playerIn.getServerWorld().getEntityTracker().untrack(playerIn);
      playerIn.getServerWorld().getPlayerChunkMap().removePlayer(playerIn);
      this.playerEntityList.remove(playerIn);
      this.server.getWorld(playerIn.dimension).removeEntityDangerously(playerIn);
      BlockPos blockpos = playerIn.getBedLocation();
      boolean flag = playerIn.isSpawnForced();
      playerIn.dimension = dimension;
      Object playerinteractionmanager;
      if (this.server.isDemo()) {
         playerinteractionmanager = new WN(this.server.getWorld(playerIn.dimension));
      } else {
         playerinteractionmanager = new WW(this.server.getWorld(playerIn.dimension));
      }

      MG entityplayermp = new MG(this.server, this.server.getWorld(playerIn.dimension), playerIn.getGameProfile(), (WW)playerinteractionmanager);
      entityplayermp.connection = playerIn.connection;
      entityplayermp.copyFrom(playerIn, conqueredEnd);
      entityplayermp.setEntityId(playerIn.getEntityId());
      entityplayermp.setCommandStats(playerIn);
      entityplayermp.setPrimaryHand(playerIn.getPrimaryHand());
      Iterator var8 = playerIn.getTags().iterator();

      while(var8.hasNext()) {
         String s = (String)var8.next();
         entityplayermp.addTag(s);
      }

      bis worldserver = this.server.getWorld(playerIn.dimension);
      this.setPlayerGameTypeBasedOnOther(entityplayermp, playerIn, worldserver);
      BlockPos blockpos2;
      if (blockpos != null) {
         blockpos2 = ME.getBedSpawnLocation(this.server.getWorld(playerIn.dimension), blockpos, flag);
         if (blockpos2 != null) {
            entityplayermp.setLocationAndAngles((double)((float)blockpos2.getX() + 0.5F), (double)((float)blockpos2.getY() + 0.1F), (double)((float)blockpos2.getZ() + 0.5F), 0.0F, 0.0F);
            entityplayermp.setSpawnPoint(blockpos, flag);
         } else {
            entityplayermp.connection.sendPacket(new TC(0, 0.0F));
         }
      }

      worldserver.getChunkProvider().provideChunk((int)entityplayermp.posX >> 4, (int)entityplayermp.posZ >> 4);

      while(!worldserver.getCollisionBoxes(entityplayermp, entityplayermp.getEntityBoundingBox()).isEmpty() && entityplayermp.posY < 256.0) {
         entityplayermp.setPosition(entityplayermp.posX, entityplayermp.posY + 1.0, entityplayermp.posZ);
      }

      entityplayermp.connection.sendPacket(new UD(entityplayermp.dimension, entityplayermp.world.getDifficulty(), entityplayermp.world.getWorldInfo().getTerrainType(), entityplayermp.interactionManager.getGameType()));
      blockpos2 = worldserver.getSpawnPoint();
      entityplayermp.connection.setPlayerLocation(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
      entityplayermp.connection.sendPacket(new US(blockpos2));
      entityplayermp.connection.sendPacket(new UH(entityplayermp.experience, entityplayermp.experienceTotal, entityplayermp.experienceLevel));
      this.updateTimeAndWeatherForPlayer(entityplayermp, worldserver);
      this.updatePermissionLevel(entityplayermp);
      worldserver.getPlayerChunkMap().addPlayer(entityplayermp);
      worldserver.spawnEntity(entityplayermp);
      this.playerEntityList.add(entityplayermp);
      this.uuidToPlayerMap.put(entityplayermp.getUniqueID(), entityplayermp);
      entityplayermp.addSelfToInternalCraftingInventory();
      entityplayermp.setHealth(entityplayermp.getHealth());
      return entityplayermp;
   }

   public void updatePermissionLevel(MG player) {
      GameProfile gameprofile = player.getGameProfile();
      int i = this.canSendCommands(gameprofile) ? this.ops.getPermissionLevel(gameprofile) : 0;
      i = this.server.isSinglePlayer() && this.server.worlds[0].getWorldInfo().areCommandsAllowed() ? 4 : i;
      i = this.commandsAllowedForAll ? 4 : i;
      this.sendPlayerPermissionLevel(player, i);
   }

   public void changePlayerDimension(MG player, int dimensionIn) {
      int i = player.dimension;
      bis worldserver = this.server.getWorld(player.dimension);
      player.dimension = dimensionIn;
      bis worldserver1 = this.server.getWorld(player.dimension);
      player.connection.sendPacket(new UD(player.dimension, player.world.getDifficulty(), player.world.getWorldInfo().getTerrainType(), player.interactionManager.getGameType()));
      this.updatePermissionLevel(player);
      worldserver.removeEntityDangerously(player);
      player.isDead = false;
      this.transferEntityToWorld(player, i, worldserver, worldserver1);
      this.preparePlayer(player, worldserver);
      player.connection.setPlayerLocation(player.posX, player.posY, player.posZ, player.rotationYaw, player.rotationPitch);
      player.interactionManager.setWorld(worldserver1);
      player.connection.sendPacket(new Ur(player.capabilities));
      this.updateTimeAndWeatherForPlayer(player, worldserver1);
      this.syncPlayerInventory(player);
      Iterator var6 = player.getActivePotionEffects().iterator();

      while(var6.hasNext()) {
         VZ potioneffect = (VZ)var6.next();
         player.connection.sendPacket(new TX(player.getEntityId(), potioneffect));
      }

   }

   public void transferEntityToWorld(Ig entityIn, int lastDimension, bis oldWorldIn, bis toWorldIn) {
      double d0 = entityIn.posX;
      double d1 = entityIn.posZ;
      double d2 = 8.0;
      float f = entityIn.rotationYaw;
      oldWorldIn.profiler.startSection("moving");
      if (entityIn.dimension == -1) {
         d0 = MathHelper.clamp(d0 / 8.0, toWorldIn.getWorldBorder().minX() + 16.0, toWorldIn.getWorldBorder().maxX() - 16.0);
         d1 = MathHelper.clamp(d1 / 8.0, toWorldIn.getWorldBorder().minZ() + 16.0, toWorldIn.getWorldBorder().maxZ() - 16.0);
         entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);
         if (entityIn.isEntityAlive()) {
            oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
         }
      } else if (entityIn.dimension == 0) {
         d0 = MathHelper.clamp(d0 * 8.0, toWorldIn.getWorldBorder().minX() + 16.0, toWorldIn.getWorldBorder().maxX() - 16.0);
         d1 = MathHelper.clamp(d1 * 8.0, toWorldIn.getWorldBorder().minZ() + 16.0, toWorldIn.getWorldBorder().maxZ() - 16.0);
         entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);
         if (entityIn.isEntityAlive()) {
            oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
         }
      } else {
         BlockPos blockpos;
         if (lastDimension == 1) {
            blockpos = toWorldIn.getSpawnPoint();
         } else {
            blockpos = toWorldIn.getSpawnCoordinate();
         }

         d0 = (double)blockpos.getX();
         entityIn.posY = (double)blockpos.getY();
         d1 = (double)blockpos.getZ();
         entityIn.setLocationAndAngles(d0, entityIn.posY, d1, 90.0F, 0.0F);
         if (entityIn.isEntityAlive()) {
            oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
         }
      }

      oldWorldIn.profiler.endSection();
      if (lastDimension != 1) {
         oldWorldIn.profiler.startSection("placing");
         d0 = (double)MathHelper.clamp((int)d0, -29999872, 29999872);
         d1 = (double)MathHelper.clamp((int)d1, -29999872, 29999872);
         if (entityIn.isEntityAlive()) {
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);
            toWorldIn.getDefaultTeleporter().placeInPortal(entityIn, f);
            toWorldIn.spawnEntity(entityIn);
            toWorldIn.updateEntityWithOptionalForce(entityIn, false);
         }

         oldWorldIn.profiler.endSection();
      }

      entityIn.setWorld(toWorldIn);
   }

   public void onTick() {
      if (++this.playerPingIndex > 600) {
         this.sendPacketToAllPlayers(new Uw(Uu.UPDATE_LATENCY, this.playerEntityList));
         this.playerPingIndex = 0;
      }

   }

   public void sendPacketToAllPlayers(Sz<?> packetIn) {
      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         ((MG)this.playerEntityList.get(i)).connection.sendPacket(packetIn);
      }

   }

   public void sendPacketToAllPlayersInDimension(Sz<?> packetIn, int dimension) {
      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         MG entityplayermp = (MG)this.playerEntityList.get(i);
         if (entityplayermp.dimension == dimension) {
            entityplayermp.connection.sendPacket(packetIn);
         }
      }

   }

   public void sendMessageToAllTeamMembers(ME player, ITextComponent message) {
      WE team = player.getTeam();
      if (team != null) {
         Iterator var4 = team.getMembershipCollection().iterator();

         while(var4.hasNext()) {
            String s = (String)var4.next();
            MG entityplayermp = this.getPlayerByUsername(s);
            if (entityplayermp != null && entityplayermp != player) {
               entityplayermp.sendMessage(message);
            }
         }
      }

   }

   public void sendMessageToTeamOrAllPlayers(ME player, ITextComponent message) {
      WE team = player.getTeam();
      if (team == null) {
         this.sendMessage(message);
      } else {
         for(int i = 0; i < this.playerEntityList.size(); ++i) {
            MG entityplayermp = (MG)this.playerEntityList.get(i);
            if (entityplayermp.getTeam() != team) {
               entityplayermp.sendMessage(message);
            }
         }
      }

   }

   public String getFormattedListOfPlayers(boolean includeUUIDs) {
      String s = "";
      List<MG> list = Lists.newArrayList(this.playerEntityList);

      for(int i = 0; i < list.size(); ++i) {
         if (i > 0) {
            s = s + ", ";
         }

         s = s + ((MG)list.get(i)).getName();
         if (includeUUIDs) {
            s = s + " (" + ((MG)list.get(i)).getCachedUniqueIdString() + ")";
         }
      }

      return s;
   }

   public String[] getOnlinePlayerNames() {
      String[] astring = new String[this.playerEntityList.size()];

      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         astring[i] = ((MG)this.playerEntityList.get(i)).getName();
      }

      return astring;
   }

   public GameProfile[] getOnlinePlayerProfiles() {
      GameProfile[] agameprofile = new GameProfile[this.playerEntityList.size()];

      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         agameprofile[i] = ((MG)this.playerEntityList.get(i)).getGameProfile();
      }

      return agameprofile;
   }

   public Xk getBannedPlayers() {
      return this.bannedPlayers;
   }

   public Xo getBannedIPs() {
      return this.bannedIPs;
   }

   public void addOp(GameProfile profile) {
      int i = this.server.getOpPermissionLevel();
      this.ops.addEntry(new Xr(profile, this.server.getOpPermissionLevel(), this.ops.bypassesPlayerLimit(profile)));
      this.sendPlayerPermissionLevel(this.getPlayerByUUID(profile.getId()), i);
   }

   public void removeOp(GameProfile profile) {
      this.ops.removeEntry(profile);
      this.sendPlayerPermissionLevel(this.getPlayerByUUID(profile.getId()), 0);
   }

   private void sendPlayerPermissionLevel(MG player, int permLevel) {
      if (player != null && player.connection != null) {
         byte b0;
         if (permLevel <= 0) {
            b0 = 24;
         } else if (permLevel >= 4) {
            b0 = 28;
         } else {
            b0 = (byte)(24 + permLevel);
         }

         player.connection.sendPacket(new Ud(player, b0));
      }

   }

   public boolean canJoin(GameProfile profile) {
      return !this.whiteListEnforced || this.ops.hasEntry(profile) || this.whiteListedPlayers.hasEntry(profile);
   }

   public boolean canSendCommands(GameProfile profile) {
      return this.ops.hasEntry(profile) || this.server.isSinglePlayer() && this.server.worlds[0].getWorldInfo().areCommandsAllowed() && this.server.getServerOwner().equalsIgnoreCase(profile.getName()) || this.commandsAllowedForAll;
   }

   @Nullable
   public MG getPlayerByUsername(String username) {
      Iterator var2 = this.playerEntityList.iterator();

      MG entityplayermp;
      do {
         if (!var2.hasNext()) {
            return null;
         }

         entityplayermp = (MG)var2.next();
      } while(!entityplayermp.getName().equalsIgnoreCase(username));

      return entityplayermp;
   }

   public void sendToAllNearExcept(@Nullable ME except, double x, double y, double z, double radius, int dimension, Sz<?> packetIn) {
      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         MG entityplayermp = (MG)this.playerEntityList.get(i);
         if (entityplayermp != except && entityplayermp.dimension == dimension) {
            double d0 = x - entityplayermp.posX;
            double d1 = y - entityplayermp.posY;
            double d2 = z - entityplayermp.posZ;
            if (d0 * d0 + d1 * d1 + d2 * d2 < radius * radius) {
               entityplayermp.connection.sendPacket(packetIn);
            }
         }
      }

   }

   public void saveAllPlayerData() {
      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         this.writePlayerData((MG)this.playerEntityList.get(i));
      }

   }

   public void addWhitelistedPlayer(GameProfile profile) {
      this.whiteListedPlayers.addEntry(new Xt(profile));
   }

   public void removePlayerFromWhitelist(GameProfile profile) {
      this.whiteListedPlayers.removeEntry(profile);
   }

   public Xs getWhitelistedPlayers() {
      return this.whiteListedPlayers;
   }

   public String[] getWhitelistedPlayerNames() {
      return this.whiteListedPlayers.getKeys();
   }

   public Xq getOppedPlayers() {
      return this.ops;
   }

   public String[] getOppedPlayerNames() {
      return this.ops.getKeys();
   }

   public void reloadWhitelist() {
   }

   public void updateTimeAndWeatherForPlayer(MG playerIn, bis worldIn) {
      bab worldborder = this.server.worlds[0].getWorldBorder();
      playerIn.connection.sendPacket(new Vm(worldborder, Vl.INITIALIZE));
      playerIn.connection.sendPacket(new UW(worldIn.getTotalWorldTime(), worldIn.getWorldTime(), worldIn.getGameRules().getBoolean("doDaylightCycle")));
      BlockPos blockpos = worldIn.getSpawnPoint();
      playerIn.connection.sendPacket(new US(blockpos));
      if (worldIn.isRaining()) {
         playerIn.connection.sendPacket(new TC(1, 0.0F));
         playerIn.connection.sendPacket(new TC(7, worldIn.getRainStrength(1.0F)));
         playerIn.connection.sendPacket(new TC(8, worldIn.getThunderStrength(1.0F)));
      }

   }

   public void syncPlayerInventory(MG playerIn) {
      playerIn.sendContainerToPlayer(playerIn.inventoryContainer);
      playerIn.setPlayerHealthUpdated();
      playerIn.connection.sendPacket(new Uh(playerIn.inventory.currentItem));
   }

   public int getCurrentPlayerCount() {
      return this.playerEntityList.size();
   }

   public int getMaxPlayers() {
      return this.maxPlayers;
   }

   public String[] getAvailablePlayerDat() {
      return this.server.worlds[0].getSaveHandler().getPlayerNBTManager().getAvailablePlayerDat();
   }

   public void setWhiteListEnabled(boolean whitelistEnabled) {
      this.whiteListEnforced = whitelistEnabled;
   }

   public List<MG> getPlayersMatchingAddress(String address) {
      List<MG> list = Lists.newArrayList();
      Iterator var3 = this.playerEntityList.iterator();

      while(var3.hasNext()) {
         MG entityplayermp = (MG)var3.next();
         if (entityplayermp.getPlayerIP().equals(address)) {
            list.add(entityplayermp);
         }
      }

      return list;
   }

   public int getViewDistance() {
      return this.viewDistance;
   }

   public Xx getServerInstance() {
      return this.server;
   }

   public QQ getHostPlayerData() {
      return null;
   }

   public void setGameType(bbb gameModeIn) {
      this.gameType = gameModeIn;
   }

   private void setPlayerGameTypeBasedOnOther(MG target, MG source, bij worldIn) {
      if (source != null) {
         target.interactionManager.setGameType(source.interactionManager.getGameType());
      } else if (this.gameType != null) {
         target.interactionManager.setGameType(this.gameType);
      }

      target.interactionManager.initializeGameType(worldIn.getWorldInfo().getGameType());
   }

   public void setCommandsAllowedForAll(boolean p_72387_1_) {
      this.commandsAllowedForAll = p_72387_1_;
   }

   public void removeAllPlayers() {
      for(int i = 0; i < this.playerEntityList.size(); ++i) {
         ((MG)this.playerEntityList.get(i)).connection.disconnect(new TextComponentTranslation("multiplayer.disconnect.server_shutdown", new Object[0]));
      }

   }

   public void sendMessage(ITextComponent component, boolean isSystem) {
      this.server.sendMessage(component);
      ChatType chattype = isSystem ? ChatType.SYSTEM : ChatType.CHAT;
      this.sendPacketToAllPlayers(new TD(component, chattype));
   }

   public void sendMessage(ITextComponent component) {
      this.sendMessage(component, true);
   }

   public XU getPlayerStatsFile(ME playerIn) {
      UUID uuid = playerIn.getUniqueID();
      XU statisticsmanagerserver = uuid == null ? null : (XU)this.playerStatFiles.get(uuid);
      if (statisticsmanagerserver == null) {
         File file1 = new File(this.server.getWorld(0).getSaveHandler().getWorldDirectory(), "stats");
         File file2 = new File(file1, uuid + ".json");
         if (!file2.exists()) {
            File file3 = new File(file1, playerIn.getName() + ".json");
            if (file3.exists() && file3.isFile()) {
               file3.renameTo(file2);
            }
         }

         statisticsmanagerserver = new XU(this.server, file2);
         statisticsmanagerserver.readStatFile();
         this.playerStatFiles.put(uuid, statisticsmanagerserver);
      }

      return statisticsmanagerserver;
   }

   public cl getPlayerAdvancements(MG p_192054_1_) {
      UUID uuid = p_192054_1_.getUniqueID();
      cl playeradvancements = (cl)this.advancements.get(uuid);
      if (playeradvancements == null) {
         File file1 = new File(this.server.getWorld(0).getSaveHandler().getWorldDirectory(), "advancements");
         File file2 = new File(file1, uuid + ".json");
         playeradvancements = new cl(this.server, file2, p_192054_1_);
         this.advancements.put(uuid, playeradvancements);
      }

      playeradvancements.setPlayer(p_192054_1_);
      return playeradvancements;
   }

   public void setViewDistance(int distance) {
      this.viewDistance = distance;
      if (this.server.worlds != null) {
         bis[] var2 = this.server.worlds;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            bis worldserver = var2[var4];
            if (worldserver != null) {
               worldserver.getPlayerChunkMap().setPlayerViewRadius(distance);
               worldserver.getEntityTracker().setViewDistance(distance);
            }
         }
      }

   }

   public List<MG> getPlayers() {
      return this.playerEntityList;
   }

   public MG getPlayerByUUID(UUID playerUUID) {
      return (MG)this.uuidToPlayerMap.get(playerUUID);
   }

   public boolean bypassesPlayerLimit(GameProfile profile) {
      return false;
   }

   public void reloadResources() {
      Iterator var1 = this.advancements.values().iterator();

      while(var1.hasNext()) {
         cl playeradvancements = (cl)var1.next();
         playeradvancements.reload();
      }

   }
}
