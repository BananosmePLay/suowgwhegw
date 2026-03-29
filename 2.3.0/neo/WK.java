package neo;

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
import net.minecraft.util.CryptManager;
import net.minecraft.util.HttpUtil;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WK extends Xx {
   private static final Logger LOGGER = LogManager.getLogger();
   private final nC mc;
   private final biw worldSettings;
   private boolean isGamePaused;
   private boolean isPublic;
   private ph lanServerPing;
   private long ticksSaveLast = 0L;
   public bij difficultyUpdateWorld = null;
   public BlockPos difficultyUpdatePos = null;
   public baL difficultyLast = null;

   public WK(nC clientIn, String folderNameIn, String worldNameIn, biw worldSettingsIn, YggdrasilAuthenticationService authServiceIn, MinecraftSessionService sessionServiceIn, GameProfileRepository profileRepoIn, Xd profileCacheIn) {
      super(new File(clientIn.gameDir, "saves"), clientIn.getProxy(), clientIn.getDataFixer(), authServiceIn, sessionServiceIn, profileRepoIn, profileCacheIn);
      this.setServerOwner(clientIn.getSession().getUsername());
      this.setFolderName(folderNameIn);
      this.setWorldName(worldNameIn);
      this.setDemo(clientIn.isDemo());
      this.canCreateBonusChest(worldSettingsIn.isBonusChestEnabled());
      this.setBuildLimit(256);
      this.setPlayerList(new WG(this));
      this.mc = clientIn;
      this.worldSettings = this.isDemo() ? bit.DEMO_WORLD_SETTINGS : worldSettingsIn;
      bgm isavehandler = this.getActiveAnvilConverter().getSaveLoader(folderNameIn, false);
      bhY worldinfo = isavehandler.loadWorldInfo();
      if (worldinfo != null) {
         QQ nbttagcompound = worldinfo.getPlayerNBTTagCompound();
         if (nbttagcompound != null && nbttagcompound.hasKey("Dimension")) {
            int i = nbttagcompound.getInteger("Dimension");
            SC.lastDimensionId = i;
            this.mc.loadingScreen.setLoadingProgress(-1);
         }
      }

   }

   public Eh createCommandManager() {
      return new WL(this);
   }

   public void loadAllWorlds(String saveName, String worldNameIn, long seed, bix type, String generatorOptions) {
      this.convertMapIfNeeded(saveName);
      boolean flag = bnK.DimensionManager.exists();
      if (!flag) {
         this.worlds = new bis[3];
         this.timeOfLastDimensionTick = new long[this.worlds.length][100];
      }

      bgm isavehandler = this.getActiveAnvilConverter().getSaveLoader(saveName, true);
      this.setResourcePackFromWorld(this.getFolderName(), isavehandler);
      bhY worldinfo = isavehandler.loadWorldInfo();
      if (worldinfo == null) {
         worldinfo = new bhY(this.worldSettings, worldNameIn);
      } else {
         worldinfo.setWorldName(worldNameIn);
      }

      nC var10001;
      if (flag) {
         bis worldserver = this.isDemo() ? (bis)(new bit(this, isavehandler, worldinfo, 0, this.profiler)).init() : (bis)(new bis(this, isavehandler, worldinfo, 0, this.profiler)).init();
         worldserver.initialize(this.worldSettings);
         Integer[] ainteger = (Integer[])((Integer[])bnK.call(bnK.DimensionManager_getStaticDimensionIDs));
         Integer[] ainteger1 = ainteger;
         int i1 = ainteger.length;

         for(int j1 = 0; j1 < i1; ++j1) {
            int k = ainteger1[j1];
            bis worldserver1 = k == 0 ? worldserver : (bis)(new biv(this, isavehandler, k, worldserver, this.profiler)).init();
            worldserver1.addEventListener(new bgi(this, worldserver1));
            if (!this.isSinglePlayer()) {
               worldserver1.getWorldInfo().setGameType(this.getGameType());
            }

            if (bnK.EventBus.exists()) {
               bnK.postForgeBusEvent(bnK.WorldEvent_Load_Constructor, worldserver1);
            }
         }

         this.getPlayerList().setPlayerManager(new bis[]{worldserver});
         if (worldserver.getWorldInfo().getDifficulty() == null) {
            var10001 = this.mc;
            this.setDifficultyForAllWorlds(nC.gameSettings.difficulty);
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
                  this.worlds[l] = (bis)(new bit(this, isavehandler, worldinfo, i1, this.profiler)).init();
               } else {
                  this.worlds[l] = (bis)(new bis(this, isavehandler, worldinfo, i1, this.profiler)).init();
               }

               this.worlds[l].initialize(this.worldSettings);
            } else {
               this.worlds[l] = (bis)(new biv(this, isavehandler, i1, this.worlds[0], this.profiler)).init();
            }

            this.worlds[l].addEventListener(new bgi(this, this.worlds[l]));
         }

         this.getPlayerList().setPlayerManager(this.worlds);
         if (this.worlds[0].getWorldInfo().getDifficulty() == null) {
            var10001 = this.mc;
            this.setDifficultyForAllWorlds(nC.gameSettings.difficulty);
         }
      }

      this.initialWorldChunkLoad();
   }

   public boolean init() throws IOException {
      LOGGER.info("Starting integrated minecraft server version 1.12.2");
      this.setOnlineMode(false);
      this.setCanSpawnAnimals(true);
      this.setCanSpawnNPCs(true);
      this.setAllowPvp(true);
      this.setAllowFlight(true);
      LOGGER.info("Generating keypair");
      this.setKeyPair(CryptManager.generateKeyPair());
      Object object1;
      if (bnK.FMLCommonHandler_handleServerAboutToStart.exists()) {
         object1 = bnK.call(bnK.FMLCommonHandler_instance);
         if (!bnK.callBoolean(object1, bnK.FMLCommonHandler_handleServerAboutToStart, this)) {
            return false;
         }
      }

      this.loadAllWorlds(this.getFolderName(), this.getWorldName(), this.worldSettings.getSeed(), this.worldSettings.getTerrainType(), this.worldSettings.getGeneratorOptions());
      this.setMOTD(this.getServerOwner() + " - " + this.worlds[0].getWorldInfo().getWorldName());
      if (bnK.FMLCommonHandler_handleServerStarting.exists()) {
         object1 = bnK.call(bnK.FMLCommonHandler_instance);
         if (bnK.FMLCommonHandler_handleServerStarting.getReturnType() == Boolean.TYPE) {
            return bnK.callBoolean(object1, bnK.FMLCommonHandler_handleServerStarting, this);
         }

         bnK.callVoid(object1, bnK.FMLCommonHandler_handleServerStarting, this);
      }

      return true;
   }

   public void tick() {
      this.onTick();
      boolean flag = this.isGamePaused;
      this.isGamePaused = nC.getMinecraft().getConnection() != null && nC.getMinecraft().isGamePaused();
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
         nC var10000 = this.mc;
         if (nC.gameSettings.renderDistanceChunks != this.getPlayerList().getViewDistance()) {
            nC var10002 = this.mc;
            LOGGER.info("Changing view distance to {}, from {}", nC.gameSettings.renderDistanceChunks, this.getPlayerList().getViewDistance());
            WY var9 = this.getPlayerList();
            nC var10001 = this.mc;
            var9.setViewDistance(nC.gameSettings.renderDistanceChunks);
         }

         if (this.mc.world != null) {
            bhY worldinfo1 = this.worlds[0].getWorldInfo();
            bhY worldinfo = this.mc.world.getWorldInfo();
            if (!worldinfo1.isDifficultyLocked() && worldinfo.getDifficulty() != worldinfo1.getDifficulty()) {
               LOGGER.info("Changing difficulty to {}, from {}", worldinfo.getDifficulty(), worldinfo1.getDifficulty());
               this.setDifficultyForAllWorlds(worldinfo.getDifficulty());
            } else if (worldinfo.isDifficultyLocked() && !worldinfo1.isDifficultyLocked()) {
               LOGGER.info("Locking difficulty to {}", worldinfo.getDifficulty());
               bis[] var4 = this.worlds;
               int var5 = var4.length;

               for(int var6 = 0; var6 < var5; ++var6) {
                  bis worldserver = var4[var6];
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

   public bbb getGameType() {
      return this.worldSettings.getGameType();
   }

   public baV getDifficulty() {
      baV var1;
      if (this.mc.world == null) {
         nC var10000 = this.mc;
         var1 = nC.gameSettings.difficulty;
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
         nC var10000 = this.mc;
         int j = nC.gameSettings.ofAutoSaveTicks;
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

   public void finalTick(Er report) {
      this.mc.crashed(report);
   }

   public Er addServerInfoToCrashReport(Er report) {
      report = super.addServerInfoToCrashReport(report);
      report.getCategory().addDetail("Type", new Ez<String>() {
         public String call() throws Exception {
            return "Integrated Server (map_client.txt)";
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      report.getCategory().addDetail("Is Modded", new Ez<String>() {
         public String call() throws Exception {
            String s = je.getClientModName();
            if (!s.equals("vanilla")) {
               return "Definitely; Client brand changed to '" + s + "'";
            } else {
               s = WK.this.getServerModName();
               if (!"vanilla".equals(s)) {
                  return "Definitely; Server brand changed to '" + s + "'";
               } else {
                  return nC.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and both client + server brands are untouched.";
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

   public void setDifficultyForAllWorlds(baV difficulty) {
      super.setDifficultyForAllWorlds(difficulty);
      if (this.mc.world != null) {
         this.mc.world.getWorldInfo().setDifficulty(difficulty);
      }

   }

   public void addServerStatsToSnooper(Wm playerSnooper) {
      super.addServerStatsToSnooper(playerSnooper);
      playerSnooper.addClientStat("snooper_partner", this.mc.getPlayerUsageSnooper().getUniqueID());
   }

   public boolean isSnooperEnabled() {
      return nC.getMinecraft().isSnooperEnabled();
   }

   public String shareToLAN(bbb type, boolean allowCheats) {
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
         this.lanServerPing = new ph(this.getMOTD(), i + "");
         this.lanServerPing.start();
         this.getPlayerList().setGameType(type);
         this.getPlayerList().setCommandsAllowedForAll(allowCheats);
         nC var10000 = this.mc;
         nC.player.setPermissionLevel(allowCheats ? 4 : 0);
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
      if (!bnK.MinecraftForge.exists() || this.isServerRunning()) {
         Futures.getUnchecked(this.addScheduledTask(new Runnable() {
            public void run() {
               Iterator var1 = Lists.newArrayList(WK.this.getPlayerList().getPlayers()).iterator();

               while(var1.hasNext()) {
                  MG entityplayermp = (MG)var1.next();
                  UUID var10000 = entityplayermp.getUniqueID();
                  WK.this.mc;
                  if (!var10000.equals(nC.player.getUniqueID())) {
                     WK.this.getPlayerList().playerLoggedOut(entityplayermp);
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

   public void setGameType(bbb gameMode) {
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
         bis worldserver = (bis)var1.next();
         this.onTick(worldserver);
      }

   }

   public baL getDifficultyAsync(bij p_getDifficultyAsync_1_, BlockPos p_getDifficultyAsync_2_) {
      this.difficultyUpdateWorld = p_getDifficultyAsync_1_;
      this.difficultyUpdatePos = p_getDifficultyAsync_2_;
      return this.difficultyLast;
   }

   private void onTick(bis p_onTick_1_) {
      if (!XH.isTimeDefault()) {
         this.fixWorldTime(p_onTick_1_);
      }

      if (!XH.isWeatherEnabled()) {
         this.fixWorldWeather(p_onTick_1_);
      }

      if (XH.waterOpacityChanged) {
         XH.waterOpacityChanged = false;
         biP.updateWaterOpacity(XH.getGameSettings(), p_onTick_1_);
      }

      if (this.difficultyUpdateWorld == p_onTick_1_ && this.difficultyUpdatePos != null) {
         this.difficultyLast = p_onTick_1_.getDifficultyForLocation(this.difficultyUpdatePos);
         this.difficultyUpdateWorld = null;
         this.difficultyUpdatePos = null;
      }

   }

   private void fixWorldWeather(bis p_fixWorldWeather_1_) {
      bhY worldinfo = p_fixWorldWeather_1_.getWorldInfo();
      if (worldinfo.isRaining() || worldinfo.isThundering()) {
         worldinfo.setRainTime(0);
         worldinfo.setRaining(false);
         p_fixWorldWeather_1_.setRainStrength(0.0F);
         worldinfo.setThunderTime(0);
         worldinfo.setThundering(false);
         p_fixWorldWeather_1_.setThunderStrength(0.0F);
         this.getPlayerList().sendPacketToAllPlayers(new TC(2, 0.0F));
         this.getPlayerList().sendPacketToAllPlayers(new TC(7, 0.0F));
         this.getPlayerList().sendPacketToAllPlayers(new TC(8, 0.0F));
      }

   }

   private void fixWorldTime(bis p_fixWorldTime_1_) {
      bhY worldinfo = p_fixWorldTime_1_.getWorldInfo();
      if (worldinfo.getGameType().getID() == 1) {
         long i = p_fixWorldTime_1_.getWorldTime();
         long j = i % 24000L;
         if (XH.isTimeDayOnly()) {
            if (j <= 1000L) {
               p_fixWorldTime_1_.setWorldTime(i - j + 1001L);
            }

            if (j >= 11000L) {
               p_fixWorldTime_1_.setWorldTime(i - j + 24001L);
            }
         }

         if (XH.isTimeNightOnly()) {
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
