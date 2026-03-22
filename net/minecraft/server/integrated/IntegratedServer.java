package net.minecraft.server.integrated;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.Futures;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;
import java.util.concurrent.FutureTask;
import net.minecraft.client.ClientBrandRetriever;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.command.ServerCommandManager;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketThreadUtil;
import net.minecraft.network.play.server.SPacketChangeGameState;
import net.minecraft.profiler.Snooper;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.PlayerList;
import net.minecraft.server.management.PlayerProfileCache;
import net.minecraft.src.Config;
import net.minecraft.util.CryptManager;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.GameType;
import net.minecraft.world.ServerWorldEventHandler;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldServerDemo;
import net.minecraft.world.WorldServerMulti;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.WorldType;
import net.minecraft.world.storage.ISaveHandler;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.ClearWater;
import net.optifine.reflect.Reflector;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntegratedServer extends MinecraftServer {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Minecraft mc;
   private final WorldSettings worldSettings;
   private boolean isGamePaused;
   private boolean isPublic;
   private ThreadLanServerPing lanServerPing;
   private long ticksSaveLast = 0L;
   public World difficultyUpdateWorld = null;
   public BlockPos difficultyUpdatePos = null;
   public DifficultyInstance difficultyLast = null;

   public IntegratedServer(Minecraft clientIn, String folderNameIn, String worldNameIn, WorldSettings worldSettingsIn, YggdrasilAuthenticationService authServiceIn, MinecraftSessionService sessionServiceIn, GameProfileRepository profileRepoIn, PlayerProfileCache profileCacheIn) {
      super(new File(clientIn.gameDir, "saves"), clientIn.getProxy(), clientIn.getDataFixer(), authServiceIn, sessionServiceIn, profileRepoIn, profileCacheIn);
      this.setServerOwner(clientIn.getSession().getUsername());
      this.setFolderName(folderNameIn);
      this.setWorldName(worldNameIn);
      this.setDemo(clientIn.isDemo());
      this.canCreateBonusChest(worldSettingsIn.isBonusChestEnabled());
      this.setBuildLimit(256);
      this.setPlayerList(new IntegratedPlayerList(this));
      this.mc = clientIn;
      this.worldSettings = this.isDemo() ? WorldServerDemo.DEMO_WORLD_SETTINGS : worldSettingsIn;
      ISaveHandler isavehandler = this.getActiveAnvilConverter().getSaveLoader(folderNameIn, false);
      WorldInfo worldinfo = isavehandler.loadWorldInfo();
      if (worldinfo != null) {
         NBTTagCompound nbttagcompound = worldinfo.getPlayerNBTTagCompound();
         if (nbttagcompound != null && nbttagcompound.hasKey("Dimension")) {
            int i = nbttagcompound.getInteger("Dimension");
            PacketThreadUtil.lastDimensionId = i;
            this.mc.loadingScreen.setLoadingProgress(-1);
         }
      }

   }

   public ServerCommandManager createCommandManager() {
      return new IntegratedServerCommandManager(this);
   }

   public void loadAllWorlds(String saveName, String worldNameIn, long seed, WorldType type, String generatorOptions) {
      this.convertMapIfNeeded(saveName);
      boolean flag = Reflector.DimensionManager.exists();
      if (!flag) {
         this.worlds = new WorldServer[3];
         this.timeOfLastDimensionTick = new long[this.worlds.length][100];
      }

      ISaveHandler isavehandler = this.getActiveAnvilConverter().getSaveLoader(saveName, true);
      this.setResourcePackFromWorld(this.getFolderName(), isavehandler);
      WorldInfo worldinfo = isavehandler.loadWorldInfo();
      if (worldinfo == null) {
         worldinfo = new WorldInfo(this.worldSettings, worldNameIn);
      } else {
         worldinfo.setWorldName(worldNameIn);
      }

      Minecraft var10001;
      if (flag) {
         WorldServer worldserver = this.isDemo() ? (WorldServer)(new WorldServerDemo(this, isavehandler, worldinfo, 0, this.profiler)).init() : (WorldServer)(new WorldServer(this, isavehandler, worldinfo, 0, this.profiler)).init();
         worldserver.initialize(this.worldSettings);
         Integer[] ainteger = (Integer[])((Integer[])Reflector.call(Reflector.DimensionManager_getStaticDimensionIDs));
         Integer[] ainteger1 = ainteger;
         int i1 = ainteger.length;

         for(int j1 = 0; j1 < i1; ++j1) {
            int k = ainteger1[j1];
            WorldServer worldserver1 = k == 0 ? worldserver : (WorldServer)(new WorldServerMulti(this, isavehandler, k, worldserver, this.profiler)).init();
            worldserver1.addEventListener(new ServerWorldEventHandler(this, worldserver1));
            if (!this.isSinglePlayer()) {
               worldserver1.getWorldInfo().setGameType(this.getGameType());
            }

            if (Reflector.EventBus.exists()) {
               Reflector.postForgeBusEvent(Reflector.WorldEvent_Load_Constructor, worldserver1);
            }
         }

         this.getPlayerList().setPlayerManager(new WorldServer[]{worldserver});
         if (worldserver.getWorldInfo().getDifficulty() == null) {
            var10001 = this.mc;
            this.setDifficultyForAllWorlds(Minecraft.gameSettings.difficulty);
         }
      } else {
         for(int l = 0; l < this.worlds.length; ++l) {
            int i1 = 0;
            if (l == 1) {
               i1 = -1;
            }

            if (l == 2) {
               i1 = 1;
            }

            if (l == 0) {
               if (this.isDemo()) {
                  this.worlds[l] = (WorldServer)(new WorldServerDemo(this, isavehandler, worldinfo, i1, this.profiler)).init();
               } else {
                  this.worlds[l] = (WorldServer)(new WorldServer(this, isavehandler, worldinfo, i1, this.profiler)).init();
               }

               this.worlds[l].initialize(this.worldSettings);
            } else {
               this.worlds[l] = (WorldServer)(new WorldServerMulti(this, isavehandler, i1, this.worlds[0], this.profiler)).init();
            }

            this.worlds[l].addEventListener(new ServerWorldEventHandler(this, this.worlds[l]));
         }

         this.getPlayerList().setPlayerManager(this.worlds);
         if (this.worlds[0].getWorldInfo().getDifficulty() == null) {
            var10001 = this.mc;
            this.setDifficultyForAllWorlds(Minecraft.gameSettings.difficulty);
         }
      }

      this.initialWorldChunkLoad();
   }

   public boolean init() throws IOException {
      LOGGER.info("Starting integrated minecraft server version 1.12.2");
      this.setOnlineMode(true);
      this.setCanSpawnAnimals(true);
      this.setCanSpawnNPCs(true);
      this.setAllowPvp(true);
      this.setAllowFlight(true);
      LOGGER.info("Generating keypair");
      this.setKeyPair(CryptManager.generateKeyPair());
      Object object1;
      if (Reflector.FMLCommonHandler_handleServerAboutToStart.exists()) {
         object1 = Reflector.call(Reflector.FMLCommonHandler_instance);
         if (!Reflector.callBoolean(object1, Reflector.FMLCommonHandler_handleServerAboutToStart, this)) {
            return false;
         }
      }

      this.loadAllWorlds(this.getFolderName(), this.getWorldName(), this.worldSettings.getSeed(), this.worldSettings.getTerrainType(), this.worldSettings.getGeneratorOptions());
      this.setMOTD(this.getServerOwner() + " - " + this.worlds[0].getWorldInfo().getWorldName());
      if (Reflector.FMLCommonHandler_handleServerStarting.exists()) {
         object1 = Reflector.call(Reflector.FMLCommonHandler_instance);
         if (Reflector.FMLCommonHandler_handleServerStarting.getReturnType() == Boolean.TYPE) {
            return Reflector.callBoolean(object1, Reflector.FMLCommonHandler_handleServerStarting, this);
         }

         Reflector.callVoid(object1, Reflector.FMLCommonHandler_handleServerStarting, this);
      }

      return true;
   }

   public void tick() {
      this.onTick();
      boolean flag = this.isGamePaused;
      this.isGamePaused = Minecraft.getMinecraft().getConnection() != null && Minecraft.getMinecraft().isGamePaused();
      if (!flag && this.isGamePaused) {
         LOGGER.info("Saving and pausing game...");
         this.getPlayerList().saveAllPlayerData();
         this.saveAllWorlds(false);
      }

      if (this.isGamePaused) {
         synchronized(this.futureTaskQueue) {
            while(!this.futureTaskQueue.isEmpty()) {
               Util.runTask((FutureTask)this.futureTaskQueue.poll(), LOGGER);
            }
         }
      } else {
         super.tick();
         Minecraft var10000 = this.mc;
         if (Minecraft.gameSettings.renderDistanceChunks != this.getPlayerList().getViewDistance()) {
            Minecraft var10002 = this.mc;
            LOGGER.info("Changing view distance to {}, from {}", Minecraft.gameSettings.renderDistanceChunks, this.getPlayerList().getViewDistance());
            PlayerList var9 = this.getPlayerList();
            Minecraft var10001 = this.mc;
            var9.setViewDistance(Minecraft.gameSettings.renderDistanceChunks);
         }

         if (this.mc.world != null) {
            WorldInfo worldinfo1 = this.worlds[0].getWorldInfo();
            WorldInfo worldinfo = this.mc.world.getWorldInfo();
            if (!worldinfo1.isDifficultyLocked() && worldinfo.getDifficulty() != worldinfo1.getDifficulty()) {
               LOGGER.info("Changing difficulty to {}, from {}", worldinfo.getDifficulty(), worldinfo1.getDifficulty());
               this.setDifficultyForAllWorlds(worldinfo.getDifficulty());
            } else if (worldinfo.isDifficultyLocked() && !worldinfo1.isDifficultyLocked()) {
               LOGGER.info("Locking difficulty to {}", worldinfo.getDifficulty());
               WorldServer[] var4 = this.worlds;
               int var5 = var4.length;

               for(int var6 = 0; var6 < var5; ++var6) {
                  WorldServer worldserver = var4[var6];
                  if (worldserver != null) {
                     worldserver.getWorldInfo().setDifficultyLocked(true);
                  }
               }
            }
         }
      }

   }

   public boolean canStructuresSpawn() {
      return false;
   }

   public GameType getGameType() {
      return this.worldSettings.getGameType();
   }

   public EnumDifficulty getDifficulty() {
      EnumDifficulty var1;
      if (this.mc.world == null) {
         Minecraft var10000 = this.mc;
         var1 = Minecraft.gameSettings.difficulty;
      } else {
         var1 = this.mc.world.getWorldInfo().getDifficulty();
      }

      return var1;
   }

   public boolean isHardcore() {
      return this.worldSettings.getHardcoreEnabled();
   }

   public boolean shouldBroadcastRconToOps() {
      return true;
   }

   public boolean shouldBroadcastConsoleToOps() {
      return true;
   }

   public void saveAllWorlds(boolean isSilent) {
      if (isSilent) {
         int i = this.getTickCounter();
         Minecraft var10000 = this.mc;
         int j = Minecraft.gameSettings.ofAutoSaveTicks;
         if ((long)i < this.ticksSaveLast + (long)j) {
            return;
         }

         this.ticksSaveLast = (long)i;
      }

      super.saveAllWorlds(isSilent);
   }

   public File getDataDirectory() {
      return this.mc.gameDir;
   }

   public boolean isDedicatedServer() {
      return false;
   }

   public boolean shouldUseNativeTransport() {
      return false;
   }

   public void finalTick(CrashReport report) {
      this.mc.crashed(report);
   }

   public CrashReport addServerInfoToCrashReport(CrashReport report) {
      report = super.addServerInfoToCrashReport(report);
      report.getCategory().addDetail("Type", new ICrashReportDetail<String>() {
         public String call() throws Exception {
            return "Integrated Server (map_client.txt)";
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      report.getCategory().addDetail("Is Modded", new ICrashReportDetail<String>() {
         public String call() throws Exception {
            String s = ClientBrandRetriever.getClientModName();
            if (!s.equals("vanilla")) {
               return "Definitely; Client brand changed to '" + s + "'";
            } else {
               s = IntegratedServer.this.getServerModName();
               if (!"vanilla".equals(s)) {
                  return "Definitely; Server brand changed to '" + s + "'";
               } else {
                  return Minecraft.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and both client + server brands are untouched.";
               }
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      return report;
   }

   public void setDifficultyForAllWorlds(EnumDifficulty difficulty) {
      super.setDifficultyForAllWorlds(difficulty);
      if (this.mc.world != null) {
         this.mc.world.getWorldInfo().setDifficulty(difficulty);
      }

   }

   public void addServerStatsToSnooper(Snooper playerSnooper) {
      super.addServerStatsToSnooper(playerSnooper);
      playerSnooper.addClientStat("snooper_partner", this.mc.getPlayerUsageSnooper().getUniqueID());
   }

   public boolean isSnooperEnabled() {
      return Minecraft.getMinecraft().isSnooperEnabled();
   }

   public String shareToLAN(GameType type, boolean allowCheats) {
      try {
         int i = -1;

         try {
            i = HttpUtil.getSuitableLanPort();
         } catch (IOException var5) {
         }

         if (i <= 0) {
            i = 25564;
         }

         this.getNetworkSystem().addEndpoint((InetAddress)null, i);
         LOGGER.info("Started on {}", i);
         this.isPublic = true;
         this.lanServerPing = new ThreadLanServerPing(this.getMOTD(), i + "");
         this.lanServerPing.start();
         this.getPlayerList().setGameType(type);
         this.getPlayerList().setCommandsAllowedForAll(allowCheats);
         Minecraft var10000 = this.mc;
         Minecraft.player.setPermissionLevel(allowCheats ? 4 : 0);
         return i + "";
      } catch (IOException var6) {
         return null;
      }
   }

   public void stopServer() {
      super.stopServer();
      if (this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }

   }

   public void initiateShutdown() {
      if (!Reflector.MinecraftForge.exists() || this.isServerRunning()) {
         Futures.getUnchecked(this.addScheduledTask(new Runnable() {
            public void run() {
               Iterator var1 = Lists.newArrayList((Iterable)IntegratedServer.this.getPlayerList().getPlayers()).iterator();

               while(var1.hasNext()) {
                  EntityPlayerMP entityplayermp = (EntityPlayerMP)var1.next();
                  UUID var10000 = entityplayermp.getUniqueID();
                  IntegratedServer.this.mc;
                  if (!var10000.equals(Minecraft.player.getUniqueID())) {
                     IntegratedServer.this.getPlayerList().playerLoggedOut(entityplayermp);
                  }
               }

            }
         }));
      }

      super.initiateShutdown();
      if (this.lanServerPing != null) {
         this.lanServerPing.interrupt();
         this.lanServerPing = null;
      }

   }

   public boolean getPublic() {
      return this.isPublic;
   }

   public void setGameType(GameType gameMode) {
      super.setGameType(gameMode);
      this.getPlayerList().setGameType(gameMode);
   }

   public boolean isCommandBlockEnabled() {
      return true;
   }

   public int getOpPermissionLevel() {
      return 4;
   }

   private void onTick() {
      Iterator var1 = Arrays.asList(this.worlds).iterator();

      while(var1.hasNext()) {
         WorldServer worldserver = (WorldServer)var1.next();
         this.onTick(worldserver);
      }

   }

   public DifficultyInstance getDifficultyAsync(World p_getDifficultyAsync_1_, BlockPos p_getDifficultyAsync_2_) {
      this.difficultyUpdateWorld = p_getDifficultyAsync_1_;
      this.difficultyUpdatePos = p_getDifficultyAsync_2_;
      return this.difficultyLast;
   }

   private void onTick(WorldServer p_onTick_1_) {
      if (!Config.isTimeDefault()) {
         this.fixWorldTime(p_onTick_1_);
      }

      if (!Config.isWeatherEnabled()) {
         this.fixWorldWeather(p_onTick_1_);
      }

      if (Config.waterOpacityChanged) {
         Config.waterOpacityChanged = false;
         ClearWater.updateWaterOpacity(Config.getGameSettings(), p_onTick_1_);
      }

      if (this.difficultyUpdateWorld == p_onTick_1_ && this.difficultyUpdatePos != null) {
         this.difficultyLast = p_onTick_1_.getDifficultyForLocation(this.difficultyUpdatePos);
         this.difficultyUpdateWorld = null;
         this.difficultyUpdatePos = null;
      }

   }

   private void fixWorldWeather(WorldServer p_fixWorldWeather_1_) {
      WorldInfo worldinfo = p_fixWorldWeather_1_.getWorldInfo();
      if (worldinfo.isRaining() || worldinfo.isThundering()) {
         worldinfo.setRainTime(0);
         worldinfo.setRaining(false);
         p_fixWorldWeather_1_.setRainStrength(0.0F);
         worldinfo.setThunderTime(0);
         worldinfo.setThundering(false);
         p_fixWorldWeather_1_.setThunderStrength(0.0F);
         this.getPlayerList().sendPacketToAllPlayers(new SPacketChangeGameState(2, 0.0F));
         this.getPlayerList().sendPacketToAllPlayers(new SPacketChangeGameState(7, 0.0F));
         this.getPlayerList().sendPacketToAllPlayers(new SPacketChangeGameState(8, 0.0F));
      }

   }

   private void fixWorldTime(WorldServer p_fixWorldTime_1_) {
      WorldInfo worldinfo = p_fixWorldTime_1_.getWorldInfo();
      if (worldinfo.getGameType().getID() == 1) {
         long i = p_fixWorldTime_1_.getWorldTime();
         long j = i % 24000L;
         if (Config.isTimeDayOnly()) {
            if (j <= 1000L) {
               p_fixWorldTime_1_.setWorldTime(i - j + 1001L);
            }

            if (j >= 11000L) {
               p_fixWorldTime_1_.setWorldTime(i - j + 24001L);
            }
         }

         if (Config.isTimeNightOnly()) {
            if (j <= 14000L) {
               p_fixWorldTime_1_.setWorldTime(i - j + 14001L);
            }

            if (j >= 22000L) {
               p_fixWorldTime_1_.setWorldTime(i - j + 24000L + 14001L);
            }
         }
      }

   }
}
