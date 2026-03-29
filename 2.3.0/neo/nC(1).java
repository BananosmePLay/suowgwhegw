package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import com.google.common.hash.Hashing;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.GameProfileRepository;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Proxy;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.imageio.ImageIO;
import net.minecraft.client.main.GameConfiguration;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.FrameTimer;
import net.minecraft.util.IThreadListener;
import net.minecraft.util.MinecraftError;
import net.minecraft.util.MouseHelper;
import net.minecraft.util.MovementInputFromOptions;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ScreenShotHelper;
import net.minecraft.util.Session;
import net.minecraft.util.Timer;
import net.minecraft.util.Util;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.DataFixesManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentKeybind;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.opengl.OpenGLException;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.util.glu.GLU;

public class nC implements IThreadListener, Wh {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation LOCATION_MOJANG_PNG = new ResourceLocation("textures/gui/title/mojang.png");
   public static final boolean IS_RUNNING_ON_MAC;
   public static byte[] memoryReserve;
   private static final List<DisplayMode> MAC_DISPLAY_MODES;
   private final File fileResourcepacks;
   private final PropertyMap twitchDetails;
   private final PropertyMap profileProperties;
   private pf currentServerData;
   public zf renderEngine;
   private static nC instance;
   private final DataFixer dataFixer;
   public pc playerController;
   private boolean fullscreen;
   private final boolean enableGLErrorChecking = true;
   private boolean hasCrashed;
   private Er crashReporter;
   public int displayWidth;
   public int displayHeight;
   private boolean connectedToRealms;
   public final Timer timer = new Timer(20.0F);
   private final Wm usageSnooper = new Wm("client", this, Xx.getCurrentTimeMillis());
   public pm world;
   public yy renderGlobal;
   private wC renderManager;
   private yK renderItem;
   private yo itemRenderer;
   public static jh player;
   @Nullable
   private Ig renderViewEntity;
   public Ig pointedEntity;
   public qO effectRenderer;
   private BV searchTreeManager = new BV();
   public Session session;
   private boolean isGamePaused;
   private float renderPartialTicksPaused;
   public jH fontRenderer;
   public jH standardGalacticFontRenderer;
   @Nullable
   public lg currentScreen;
   public nl loadingScreen;
   public xz entityRenderer;
   public uR debugRenderer;
   private int leftClickCounter;
   private final int tempDisplayWidth;
   private final int tempDisplayHeight;
   @Nullable
   private WK integratedServer;
   public kn ingameGUI;
   public boolean skipRenderWorld;
   public RayTraceResult objectMouseOver;
   public static Bj gameSettings;
   public Bf creativeSettings;
   public MouseHelper mouseHelper;
   public final File gameDir;
   private final File fileAssets;
   private final String launchedVersion;
   private final String versionType;
   private final Proxy proxy;
   private bgl saveLoader;
   private static int debugFPS;
   private int rightClickDelayTimer;
   private String serverName;
   private int serverPort;
   public boolean inGameHasFocus;
   long systemTime = getSystemTime();
   private int joinPlayerCounter;
   public final FrameTimer frameTimer = new FrameTimer();
   long startNanoTime = System.nanoTime();
   private final boolean jvm64bit;
   private final boolean isDemo;
   @Nullable
   private Sp networkManager;
   private boolean integratedServerIsRunning;
   public final Wk profiler = new Wk();
   private long debugCrashKeyPressTime = -1L;
   private Ay resourceManager;
   private final Aj metadataSerializer = new Aj();
   private final List<AC> defaultResourcePacks = Lists.newArrayList();
   private final Aq defaultResourcePack;
   private AV resourcePackRepository;
   private AE languageManager;
   private uy blockColors;
   private uJ itemColors;
   private Bn framebuffer;
   private zj textureMapBlocks;
   private iU soundHandler;
   private iK musicTicker;
   private ResourceLocation mojangLogo;
   private final MinecraftSessionService sessionService;
   private Be skinManager;
   private final Queue<FutureTask<?>> scheduledTasks = Queues.newArrayDeque();
   private final Thread thread = Thread.currentThread();
   private sC modelManager;
   private tJ blockRenderDispatcher;
   private final nc toastGui;
   volatile boolean running = true;
   public String debug = "";
   public boolean renderChunksMany = true;
   private long debugUpdateTime = getSystemTime();
   private int fpsCounter;
   private boolean actionKeyF3;
   private final BF tutorial;
   long prevFrameTime = -1L;
   private String debugProfilerName = "root";
   public 0bz main = new 0bz();

   public nC(GameConfiguration gameConfig) {
      instance = this;
      this.gameDir = gameConfig.folderInfo.gameDir;
      this.fileAssets = gameConfig.folderInfo.assetsDir;
      this.fileResourcepacks = gameConfig.folderInfo.resourcePacksDir;
      this.launchedVersion = gameConfig.gameInfo.version;
      this.versionType = gameConfig.gameInfo.versionType;
      this.twitchDetails = gameConfig.userInfo.userProperties;
      this.profileProperties = gameConfig.userInfo.profileProperties;
      this.defaultResourcePack = new Aq(gameConfig.folderInfo.getAssetsIndex());
      this.proxy = gameConfig.userInfo.proxy == null ? Proxy.NO_PROXY : gameConfig.userInfo.proxy;
      this.sessionService = (new YggdrasilAuthenticationService(this.proxy, UUID.randomUUID().toString())).createMinecraftSessionService();
      this.session = gameConfig.userInfo.session;
      LOGGER.info("Setting user: {}", this.session.getUsername());
      LOGGER.debug("(Session ID is {})", this.session.getSessionID());
      this.isDemo = gameConfig.gameInfo.isDemo;
      this.displayWidth = gameConfig.displayInfo.width > 0 ? gameConfig.displayInfo.width : 1;
      this.displayHeight = gameConfig.displayInfo.height > 0 ? gameConfig.displayInfo.height : 1;
      this.tempDisplayWidth = gameConfig.displayInfo.width;
      this.tempDisplayHeight = gameConfig.displayInfo.height;
      this.fullscreen = gameConfig.displayInfo.fullscreen;
      this.jvm64bit = isJvm64bit();
      this.integratedServer = null;
      if (gameConfig.serverInfo.serverName != null) {
         this.serverName = gameConfig.serverInfo.serverName;
         this.serverPort = gameConfig.serverInfo.serverPort;
      }

      ImageIO.setUseCache(false);
      Locale.setDefault(Locale.ROOT);
      NI.register();
      TextComponentKeybind.displaySupplierFunction = Bl::getDisplayString;
      this.dataFixer = DataFixesManager.createFixer();
      this.toastGui = new nc(this);
      this.tutorial = new BF(this);
   }

   public void run() {
      this.running = true;

      Throwable throwable1;
      Er crashreport1;
      try {
         this.init();
      } catch (Throwable var11) {
         throwable1 = var11;
         crashreport1 = Er.makeCrashReport(throwable1, "Initializing game");
         crashreport1.makeCategory("Initialization");
         this.displayCrashReport(this.addGraphicsAndWorldToCrashReport(crashreport1));
         return;
      }

      try {
         while(this.running) {
            if (this.hasCrashed && this.crashReporter != null) {
               this.displayCrashReport(this.crashReporter);
            } else {
               try {
                  this.runGameLoop();
               } catch (OutOfMemoryError var10) {
                  this.freeMemory();
                  this.displayGuiScreen(new kF());
                  System.gc();
               }
            }
         }

         return;
      } catch (MinecraftError var12) {
      } catch (ReportedException var13) {
         ReportedException reportedexception = var13;
         this.addGraphicsAndWorldToCrashReport(reportedexception.getCrashReport());
         this.freeMemory();
         LOGGER.fatal("Reported exception thrown!", reportedexception);
         this.displayCrashReport(reportedexception.getCrashReport());
      } catch (Throwable var14) {
         throwable1 = var14;
         crashreport1 = this.addGraphicsAndWorldToCrashReport(new Er("Unexpected error", throwable1));
         this.freeMemory();
         LOGGER.fatal("Unreported exception thrown!", throwable1);
         this.displayCrashReport(crashreport1);
      } finally {
         this.shutdownMinecraftApplet();
      }

   }

   private void init() throws LWJGLException {
      gameSettings = new Bj(this, this.gameDir);
      this.creativeSettings = new Bf(this, this.gameDir);
      this.defaultResourcePacks.add(this.defaultResourcePack);
      this.startTimerHackThread();
      if (gameSettings.overrideHeight > 0 && gameSettings.overrideWidth > 0) {
         this.displayWidth = gameSettings.overrideWidth;
         this.displayHeight = gameSettings.overrideHeight;
      }

      LOGGER.info("LWJGL Version: {}", Sys.getVersion());
      this.setWindowIcon();
      this.setInitialDisplayMode();
      this.createDisplay();
      ys.initializeTextures();
      this.framebuffer = new Bn(this.displayWidth, this.displayHeight, true);
      this.framebuffer.setFramebufferColor(0.0F, 0.0F, 0.0F, 0.0F);
      this.registerMetadataSerializers();
      this.resourcePackRepository = new AV(this.fileResourcepacks, new File(this.gameDir, "server-resource-packs"), this.defaultResourcePack, this.metadataSerializer, gameSettings);
      this.resourceManager = new AX(this.metadataSerializer);
      this.languageManager = new AE(this.metadataSerializer, gameSettings.language);
      this.resourceManager.registerReloadListener(this.languageManager);
      this.refreshResources();
      this.renderEngine = new zf(this.resourceManager);
      this.resourceManager.registerReloadListener(this.renderEngine);
      0cA.method_bvS(0);
      this.skinManager = new Be(this.renderEngine, new File(this.fileAssets, "skins"), this.sessionService);
      this.saveLoader = new bax(new File(this.gameDir, "saves"), this.dataFixer);
      this.soundHandler = new iU(this.resourceManager, gameSettings);
      this.resourceManager.registerReloadListener(this.soundHandler);
      this.musicTicker = new iK(this);
      this.fontRenderer = new jH(gameSettings, new ResourceLocation("textures/font/ascii.png"), this.renderEngine, false);
      0cA.method_bvS(1);
      this.main.method_Qk();
      if (gameSettings.language != null) {
         this.fontRenderer.setUnicodeFlag(this.isUnicode());
         this.fontRenderer.setBidiFlag(this.languageManager.isCurrentLanguageBidirectional());
      }

      0cA.method_bvS(6);
      this.standardGalacticFontRenderer = new jH(gameSettings, new ResourceLocation("textures/font/ascii_sga.png"), this.renderEngine, false);
      this.resourceManager.registerReloadListener(this.fontRenderer);
      this.resourceManager.registerReloadListener(this.standardGalacticFontRenderer);
      this.resourceManager.registerReloadListener(new Aw());
      this.resourceManager.registerReloadListener(new Av());
      this.mouseHelper = new MouseHelper();
      this.checkGLError("Pre startup");
      0cA.method_bvS(7);
      yh.enableTexture2D();
      yh.shadeModel(7425);
      yh.clearDepth(1.0);
      yh.enableDepth();
      yh.depthFunc(515);
      yh.enableAlpha();
      yh.alphaFunc(516, 0.1F);
      yh.cullFace(xO.BACK);
      yh.matrixMode(5889);
      yh.loadIdentity();
      yh.matrixMode(5888);
      this.checkGLError("Startup");
      this.textureMapBlocks = new zj("textures");
      this.textureMapBlocks.setMipmapLevels(gameSettings.mipmapLevels);
      this.renderEngine.loadTickableTexture(zj.LOCATION_BLOCKS_TEXTURE, this.textureMapBlocks);
      this.renderEngine.bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
      this.textureMapBlocks.setBlurMipmapDirect(false, gameSettings.mipmapLevels > 0);
      0cA.method_bvS(8);
      this.modelManager = new sC(this.textureMapBlocks);
      this.resourceManager.registerReloadListener(this.modelManager);
      this.blockColors = uy.init();
      this.itemColors = uJ.init(this.blockColors);
      this.renderItem = new yK(this.renderEngine, this.modelManager, this.itemColors);
      0cA.method_bvS(9);
      this.renderManager = new wC(this.renderEngine, this.renderItem);
      this.itemRenderer = new yo(this);
      this.resourceManager.registerReloadListener(this.renderItem);
      this.entityRenderer = new xz(this, this.resourceManager);
      this.resourceManager.registerReloadListener(this.entityRenderer);
      0cA.method_bvS(10);
      this.blockRenderDispatcher = new tJ(this.modelManager.getBlockModelShapes(), this.blockColors);
      this.resourceManager.registerReloadListener(this.blockRenderDispatcher);
      this.renderGlobal = new yy(this);
      this.resourceManager.registerReloadListener(this.renderGlobal);
      this.populateSearchTreeManager();
      this.resourceManager.registerReloadListener(this.searchTreeManager);
      yh.viewport(0, 0, this.displayWidth, this.displayHeight);
      this.effectRenderer = new qO(this.world, this.renderEngine);
      0cA.method_bvS(11);
      this.checkGLError("Post startup");
      this.ingameGUI = new kn(this);
      0cA.method_bvS(12);
      if (this.serverName != null) {
         this.displayGuiScreen(new pa(new 0cx(), this, this.serverName, this.serverPort));
      } else {
         this.displayGuiScreen(new 0cx());
      }

      this.renderEngine.deleteTexture(this.mojangLogo);
      this.mojangLogo = null;
      this.loadingScreen = new nl(this);
      this.debugRenderer = new uR(this);
      if (gameSettings.fullScreen && !this.fullscreen) {
         this.toggleFullscreen();
      }

      try {
         Display.setVSyncEnabled(gameSettings.enableVsync);
      } catch (OpenGLException var2) {
         gameSettings.enableVsync = false;
         gameSettings.saveOptions();
      }

      this.renderGlobal.makeEntityOutlineShader();
   }

   private void populateSearchTreeManager() {
      BT<Qy> searchtree = new BT((p_193988_0_) -> {
         return (List)p_193988_0_.getTooltip((ME)null, BI.NORMAL).stream().map(TextFormatting::getTextWithoutFormattingCodes).map(String::trim).filter((p_193984_0_) -> {
            return !p_193984_0_.isEmpty();
         }).collect(Collectors.toList());
      }, (p_193985_0_) -> {
         return Collections.singleton(OL.REGISTRY.getNameForObject(p_193985_0_.getItem()));
      });
      NonNullList<Qy> nonnulllist = NonNullList.create();
      Iterator var3 = OL.REGISTRY.iterator();

      while(var3.hasNext()) {
         OL item = (OL)var3.next();
         item.getSubItems(EN.SEARCH, nonnulllist);
      }

      nonnulllist.forEach(searchtree::add);
      BT<mB> searchtree1 = new BT((p_193990_0_) -> {
         return (List)p_193990_0_.getRecipes().stream().flatMap((p_193993_0_) -> {
            return p_193993_0_.getRecipeOutput().getTooltip((ME)null, BI.NORMAL).stream();
         }).map(TextFormatting::getTextWithoutFormattingCodes).map(String::trim).filter((p_193994_0_) -> {
            return !p_193994_0_.isEmpty();
         }).collect(Collectors.toList());
      }, (p_193991_0_) -> {
         return (List)p_193991_0_.getRecipes().stream().map((p_193992_0_) -> {
            return (ResourceLocation)OL.REGISTRY.getNameForObject(p_193992_0_.getRecipeOutput().getItem());
         }).collect(Collectors.toList());
      });
      BP.ALL_RECIPES.forEach(searchtree1::add);
      this.searchTreeManager.register(BV.ITEMS, searchtree);
      this.searchTreeManager.register(BV.RECIPES, searchtree1);
   }

   private void registerMetadataSerializers() {
      this.metadataSerializer.registerMetadataSectionType(new Ao(), An.class);
      this.metadataSerializer.registerMetadataSectionType(new Ac(), Ab.class);
      this.metadataSerializer.registerMetadataSectionType(new zZ(), zY.class);
      this.metadataSerializer.registerMetadataSectionType(new Am(), Al.class);
      this.metadataSerializer.registerMetadataSectionType(new Ag(), Af.class);
   }

   private void createDisplay() throws LWJGLException {
      Display.setResizable(true);

      try {
         Display.create((new PixelFormat()).withDepthBits(24));
      } catch (LWJGLException var4) {
         LWJGLException lwjglexception = var4;
         LOGGER.error("Couldn't set pixel format", lwjglexception);

         try {
            Thread.sleep(1000L);
         } catch (InterruptedException var3) {
         }

         if (this.fullscreen) {
            this.updateDisplayMode();
         }

         Display.create();
      }

   }

   private void setInitialDisplayMode() throws LWJGLException {
      if (this.fullscreen) {
         Display.setFullscreen(true);
         DisplayMode displaymode = Display.getDisplayMode();
         this.displayWidth = Math.max(1, displaymode.getWidth());
         this.displayHeight = Math.max(1, displaymode.getHeight());
      } else {
         Display.setDisplayMode(new DisplayMode(this.displayWidth, this.displayHeight));
      }

   }

   private void setWindowIcon() {
      Util.EnumOS util$enumos = Util.getOSType();
      if (util$enumos != Util.EnumOS.OSX) {
         InputStream inputstream = null;
         InputStream inputstream1 = null;

         try {
            inputstream = this.defaultResourcePack.getInputStream(new ResourceLocation("neoware/images/window/icon64.png"));
            inputstream1 = this.defaultResourcePack.getInputStream(new ResourceLocation("neoware/images/window/icon32.png"));
            if (inputstream != null && inputstream1 != null) {
               Display.setIcon(new ByteBuffer[]{this.readImageToBuffer(inputstream), this.readImageToBuffer(inputstream1)});
            }
         } catch (IOException var8) {
            IOException ioexception = var8;
            LOGGER.error("Couldn't set icon", ioexception);
         } finally {
            IOUtils.closeQuietly(inputstream);
            IOUtils.closeQuietly(inputstream1);
         }
      }

   }

   private static boolean isJvm64bit() {
      String[] astring = new String[]{"sun.arch.data.model", "com.ibm.vm.bitmode", "os.arch"};
      String[] var1 = astring;
      int var2 = astring.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         String s = var1[var3];
         String s1 = System.getProperty(s);
         if (s1 != null && s1.contains("64")) {
            return true;
         }
      }

      return false;
   }

   public Bn getFramebuffer() {
      return this.framebuffer;
   }

   public String getVersion() {
      return this.launchedVersion;
   }

   public String getVersionType() {
      return this.versionType;
   }

   private void startTimerHackThread() {
      Thread thread = new Thread("Timer hack thread") {
         public void run() {
            while(nC.this.running) {
               try {
                  Thread.sleep(2147483647L);
               } catch (InterruptedException var2) {
               }
            }

         }
      };
      thread.setDaemon(true);
      thread.start();
   }

   public void crashed(Er crash) {
      this.hasCrashed = true;
      this.crashReporter = crash;
   }

   public void displayCrashReport(Er crashReportIn) {
      File file1 = new File(getMinecraft().gameDir, "crash-reports");
      File file2 = new File(file1, "crash-" + (new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss")).format(new Date()) + "-client.txt");
      NI.printToSYSOUT(crashReportIn.getCompleteReport());
      if (crashReportIn.getFile() != null) {
         NI.printToSYSOUT("#@!@# Game crashed! Crash report saved to: #@!@# " + crashReportIn.getFile());
         System.exit(-1);
      } else if (crashReportIn.saveToFile(file2)) {
         NI.printToSYSOUT("#@!@# Game crashed! Crash report saved to: #@!@# " + file2.getAbsolutePath());
         System.exit(-1);
      } else {
         NI.printToSYSOUT("#@?@# Game crashed! Crash report could not be saved. #@?@#");
         System.exit(-2);
      }

   }

   public boolean isUnicode() {
      return this.languageManager.isCurrentLocaleUnicode() || gameSettings.forceUnicodeFont;
   }

   public void refreshResources() {
      List<AC> list = Lists.newArrayList(this.defaultResourcePacks);
      if (this.integratedServer != null) {
         this.integratedServer.reload();
      }

      Iterator var2 = this.resourcePackRepository.getRepositoryEntries().iterator();

      while(var2.hasNext()) {
         AU resourcepackrepository$entry = (AU)var2.next();
         list.add(resourcepackrepository$entry.getResourcePack());
      }

      if (this.resourcePackRepository.getServerResourcePack() != null) {
         list.add(this.resourcePackRepository.getServerResourcePack());
      }

      try {
         this.resourceManager.reloadResources(list);
      } catch (RuntimeException var4) {
         RuntimeException runtimeexception = var4;
         LOGGER.info("Caught error stitching, removing all assigned resourcepacks", runtimeexception);
         list.clear();
         list.addAll(this.defaultResourcePacks);
         this.resourcePackRepository.setRepositories(Collections.emptyList());
         this.resourceManager.reloadResources(list);
         gameSettings.resourcePacks.clear();
         gameSettings.incompatibleResourcePacks.clear();
         gameSettings.saveOptions();
      }

      this.languageManager.parseLanguageMetadata(list);
      if (this.renderGlobal != null) {
         this.renderGlobal.loadRenderers();
      }

   }

   private ByteBuffer readImageToBuffer(InputStream imageStream) throws IOException {
      BufferedImage bufferedimage = ImageIO.read(imageStream);
      int[] aint = bufferedimage.getRGB(0, 0, bufferedimage.getWidth(), bufferedimage.getHeight(), (int[])null, 0, bufferedimage.getWidth());
      ByteBuffer bytebuffer = ByteBuffer.allocate(4 * aint.length);
      int[] var5 = aint;
      int var6 = aint.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         int i = var5[var7];
         bytebuffer.putInt(i << 8 | i >> 24 & 255);
      }

      bytebuffer.flip();
      return bytebuffer;
   }

   private void updateDisplayMode() throws LWJGLException {
      Set<DisplayMode> set = Sets.newHashSet();
      Collections.addAll(set, Display.getAvailableDisplayModes());
      DisplayMode displaymode = Display.getDesktopDisplayMode();
      if (!set.contains(displaymode) && Util.getOSType() == Util.EnumOS.OSX) {
         Iterator var3 = MAC_DISPLAY_MODES.iterator();

         label52:
         while(true) {
            while(true) {
               DisplayMode displaymode1;
               boolean flag;
               Iterator iterator;
               DisplayMode displaymode3;
               do {
                  if (!var3.hasNext()) {
                     break label52;
                  }

                  displaymode1 = (DisplayMode)var3.next();
                  flag = true;
                  iterator = set.iterator();

                  while(iterator.hasNext()) {
                     displaymode3 = (DisplayMode)iterator.next();
                     if (displaymode3.getBitsPerPixel() == 32 && displaymode3.getWidth() == displaymode1.getWidth() && displaymode3.getHeight() == displaymode1.getHeight()) {
                        flag = false;
                        break;
                     }
                  }
               } while(flag);

               iterator = set.iterator();

               while(iterator.hasNext()) {
                  displaymode3 = (DisplayMode)iterator.next();
                  if (displaymode3.getBitsPerPixel() == 32 && displaymode3.getWidth() == displaymode1.getWidth() / 2 && displaymode3.getHeight() == displaymode1.getHeight() / 2) {
                     displaymode = displaymode3;
                     break;
                  }
               }
            }
         }
      }

      Display.setDisplayMode(displaymode);
      this.displayWidth = displaymode.getWidth();
      this.displayHeight = displaymode.getHeight();
   }

   private void drawSplashScreen(zf textureManagerInstance) throws LWJGLException {
      mC scaledresolution = new mC(this);
      int i = scaledresolution.getScaleFactor();
      Bn framebuffer = new Bn(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i, true);
      framebuffer.bindFramebuffer(false);
      yh.matrixMode(5889);
      yh.loadIdentity();
      yh.ortho(0.0, (double)scaledresolution.getScaledWidth(), (double)scaledresolution.getScaledHeight(), 0.0, 1000.0, 3000.0);
      yh.matrixMode(5888);
      yh.loadIdentity();
      yh.translate(0.0F, 0.0F, -2000.0F);
      yh.disableLighting();
      yh.disableFog();
      yh.disableDepth();
      yh.enableTexture2D();
      InputStream inputstream = null;

      try {
         inputstream = this.defaultResourcePack.getInputStream(LOCATION_MOJANG_PNG);
         this.mojangLogo = textureManagerInstance.getDynamicTextureLocation("logo", new yP(ImageIO.read(inputstream)));
         textureManagerInstance.bindTexture(this.mojangLogo);
      } catch (IOException var12) {
         IOException ioexception = var12;
         LOGGER.error("Unable to load logo: {}", LOCATION_MOJANG_PNG, ioexception);
      } finally {
         IOUtils.closeQuietly(inputstream);
      }

      yN tessellator = yN.getInstance();
      tN bufferbuilder = tessellator.getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
      bufferbuilder.pos(0.0, (double)this.displayHeight, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      bufferbuilder.pos((double)this.displayWidth, (double)this.displayHeight, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      bufferbuilder.pos((double)this.displayWidth, 0.0, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      bufferbuilder.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).color(255, 255, 255, 255).endVertex();
      tessellator.draw();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      int j = true;
      int k = true;
      this.draw((scaledresolution.getScaledWidth() - 256) / 2, (scaledresolution.getScaledHeight() - 256) / 2, 0, 0, 256, 256, 255, 255, 255, 255);
      yh.disableLighting();
      yh.disableFog();
      framebuffer.unbindFramebuffer();
      framebuffer.framebufferRender(scaledresolution.getScaledWidth() * i, scaledresolution.getScaledHeight() * i);
      yh.enableAlpha();
      yh.alphaFunc(516, 0.1F);
      this.updateDisplay();
   }

   public void draw(int posX, int posY, int texU, int texV, int width, int height, int red, int green, int blue, int alpha) {
      tN bufferbuilder = yN.getInstance().getBuffer();
      bufferbuilder.begin(7, zK.POSITION_TEX_COLOR);
      float f = 0.00390625F;
      float f1 = 0.00390625F;
      bufferbuilder.pos((double)posX, (double)(posY + height), 0.0).tex((double)((float)texU * 0.00390625F), (double)((float)(texV + height) * 0.00390625F)).color(red, green, blue, alpha).endVertex();
      bufferbuilder.pos((double)(posX + width), (double)(posY + height), 0.0).tex((double)((float)(texU + width) * 0.00390625F), (double)((float)(texV + height) * 0.00390625F)).color(red, green, blue, alpha).endVertex();
      bufferbuilder.pos((double)(posX + width), (double)posY, 0.0).tex((double)((float)(texU + width) * 0.00390625F), (double)((float)texV * 0.00390625F)).color(red, green, blue, alpha).endVertex();
      bufferbuilder.pos((double)posX, (double)posY, 0.0).tex((double)((float)texU * 0.00390625F), (double)((float)texV * 0.00390625F)).color(red, green, blue, alpha).endVertex();
      yN.getInstance().draw();
   }

   public bgl getSaveLoader() {
      return this.saveLoader;
   }

   public void displayGuiScreen(@Nullable lg guiScreenIn) {
      if (this.currentScreen != null) {
         this.currentScreen.onGuiClosed();
      }

      if (guiScreenIn == null && this.world == null) {
         guiScreenIn = new 0cx();
      } else if (guiScreenIn == null && player.getHealth() <= 0.0F) {
         guiScreenIn = new kk((ITextComponent)null);
      }

      if (guiScreenIn instanceof 0cx || guiScreenIn instanceof kI) {
         gameSettings.showDebugInfo = false;
      }

      this.currentScreen = (lg)guiScreenIn;
      if (guiScreenIn != null) {
         this.setIngameNotInFocus();
         Bl.unPressAllKeys();

         while(true) {
            if (!Mouse.next()) {
               while(Keyboard.next()) {
               }

               mC scaledresolution = new mC(this);
               int i = scaledresolution.getScaledWidth();
               int j = scaledresolution.getScaledHeight();
               ((lg)guiScreenIn).setWorldAndResolution(this, i, j);
               this.skipRenderWorld = false;
               break;
            }
         }
      } else {
         this.soundHandler.resumeSounds();
         this.setIngameFocus();
      }

   }

   private void checkGLError(String message) {
      int i = yh.glGetError();
      if (i != 0) {
         String s = GLU.gluErrorString(i);
         LOGGER.error("########## GL ERROR ##########");
         LOGGER.error("@ {}", message);
         LOGGER.error("{}: {}", i, s);
      }

   }

   public void shutdownMinecraftApplet() {
      try {
         LOGGER.info("Stopping!");

         try {
            this.loadWorld((pm)null);
         } catch (Throwable var5) {
         }

         this.soundHandler.unloadSounds();
      } finally {
         Display.destroy();
         if (!this.hasCrashed) {
            System.exit(0);
         }

      }

      System.gc();
   }

   private void runGameLoop() throws IOException {
      long i = System.nanoTime();
      this.profiler.startSection("root");
      if (Display.isCreated() && Display.isCloseRequested()) {
         this.shutdown();
      }

      this.timer.updateTimer();
      this.profiler.startSection("scheduledExecutables");
      synchronized(this.scheduledTasks) {
         while(!this.scheduledTasks.isEmpty()) {
            Util.runTask((FutureTask)this.scheduledTasks.poll(), LOGGER);
         }
      }

      this.profiler.endSection();
      long l = System.nanoTime();
      this.profiler.startSection("tick");

      for(int j = 0; j < Math.min(10, this.timer.elapsedTicks); ++j) {
         this.runTick();
      }

      this.profiler.endStartSection("preRenderErrors");
      long i1 = System.nanoTime() - l;
      this.checkGLError("Pre render");
      this.profiler.endStartSection("sound");
      this.soundHandler.setListener(player, this.timer.renderPartialTicks);
      this.profiler.endSection();
      this.profiler.startSection("render");
      yh.pushMatrix();
      yh.clear(16640);
      this.framebuffer.bindFramebuffer(true);
      this.profiler.startSection("display");
      yh.enableTexture2D();
      this.profiler.endSection();
      if (!this.skipRenderWorld) {
         this.profiler.endStartSection("gameRenderer");
         this.entityRenderer.updateCameraAndRender(this.isGamePaused ? this.renderPartialTicksPaused : this.timer.renderPartialTicks, i);
         this.profiler.endStartSection("toasts");
         this.toastGui.drawToast(new mC(this));
         this.profiler.endSection();
      }

      this.profiler.endSection();
      if (gameSettings.showDebugInfo && gameSettings.showDebugProfilerChart && !gameSettings.hideGUI) {
         if (!this.profiler.profilingEnabled) {
            this.profiler.clearProfiling();
         }

         this.profiler.profilingEnabled = true;
         this.displayDebugInfo(i1);
      } else {
         this.profiler.profilingEnabled = false;
         this.prevFrameTime = System.nanoTime();
      }

      this.framebuffer.unbindFramebuffer();
      yh.popMatrix();
      yh.pushMatrix();
      this.framebuffer.framebufferRender(this.displayWidth, this.displayHeight);
      yh.popMatrix();
      yh.pushMatrix();
      this.entityRenderer.renderStreamIndicator(this.timer.renderPartialTicks);
      yh.popMatrix();
      this.profiler.startSection("root");
      this.updateDisplay();
      Thread.yield();
      this.checkGLError("Post render");
      ++this.fpsCounter;
      boolean flag = this.isSingleplayer() && this.currentScreen != null && this.currentScreen.doesGuiPauseGame() && !this.integratedServer.getPublic();
      if (this.isGamePaused != flag) {
         if (this.isGamePaused) {
            this.renderPartialTicksPaused = this.timer.renderPartialTicks;
         } else {
            this.timer.renderPartialTicks = this.renderPartialTicksPaused;
         }

         this.isGamePaused = flag;
      }

      long k = System.nanoTime();
      this.frameTimer.addFrame(k - this.startNanoTime);
      this.startNanoTime = k;

      while(getSystemTime() >= this.debugUpdateTime + 1000L) {
         debugFPS = this.fpsCounter;
         this.debug = String.format("%d fps (%d chunk update%s) T: %s%s%s%s%s", debugFPS, ug.renderChunksUpdated, ug.renderChunksUpdated == 1 ? "" : "s", (float)gameSettings.limitFramerate == Bi.FRAMERATE_LIMIT.getValueMax() ? "inf" : gameSettings.limitFramerate, gameSettings.enableVsync ? " vsync" : "", gameSettings.fancyGraphics ? "" : " fast", gameSettings.clouds == 0 ? "" : (gameSettings.clouds == 1 ? " fast-clouds" : " fancy-clouds"), ys.useVbo() ? " vbo" : "");
         ug.renderChunksUpdated = 0;
         this.debugUpdateTime += 1000L;
         this.fpsCounter = 0;
         this.usageSnooper.addMemoryStatsToSnooper();
         if (!this.usageSnooper.isSnooperRunning()) {
            this.usageSnooper.startSnooper();
         }
      }

      if (this.isFramerateLimitBelowMax()) {
         this.profiler.startSection("fpslimit_wait");
         Display.sync(this.getLimitFramerate());
         this.profiler.endSection();
      }

      this.profiler.endSection();
   }

   public void updateDisplay() {
      this.profiler.startSection("display_update");
      Display.update();
      this.profiler.endSection();
      this.checkWindowResize();
   }

   protected void checkWindowResize() {
      if (!this.fullscreen && Display.wasResized()) {
         int i = this.displayWidth;
         int j = this.displayHeight;
         this.displayWidth = Display.getWidth();
         this.displayHeight = Display.getHeight();
         if (this.displayWidth != i || this.displayHeight != j) {
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }

            this.resize(this.displayWidth, this.displayHeight);
         }
      }

   }

   public int getLimitFramerate() {
      return this.world == null && this.currentScreen != null ? 30 : gameSettings.limitFramerate;
   }

   public boolean isFramerateLimitBelowMax() {
      return (float)this.getLimitFramerate() < Bi.FRAMERATE_LIMIT.getValueMax();
   }

   public void freeMemory() {
      try {
         memoryReserve = new byte[0];
         this.renderGlobal.deleteAllDisplayLists();
      } catch (Throwable var3) {
      }

      try {
         System.gc();
         this.loadWorld((pm)null);
      } catch (Throwable var2) {
      }

      System.gc();
   }

   private void updateDebugProfilerName(int keyCount) {
      List<Wj> list = this.profiler.getProfilingData(this.debugProfilerName);
      if (!list.isEmpty()) {
         Wj profiler$result = (Wj)list.remove(0);
         if (keyCount == 0) {
            if (!profiler$result.profilerName.isEmpty()) {
               int i = this.debugProfilerName.lastIndexOf(46);
               if (i >= 0) {
                  this.debugProfilerName = this.debugProfilerName.substring(0, i);
               }
            }
         } else {
            --keyCount;
            if (keyCount < list.size() && !"unspecified".equals(((Wj)list.get(keyCount)).profilerName)) {
               if (!this.debugProfilerName.isEmpty()) {
                  this.debugProfilerName = this.debugProfilerName + ".";
               }

               this.debugProfilerName = this.debugProfilerName + ((Wj)list.get(keyCount)).profilerName;
            }
         }
      }

   }

   private void displayDebugInfo(long elapsedTicksTime) {
      if (this.profiler.profilingEnabled) {
         List<Wj> list = this.profiler.getProfilingData(this.debugProfilerName);
         Wj profiler$result = (Wj)list.remove(0);
         yh.clear(256);
         yh.matrixMode(5889);
         yh.enableColorMaterial();
         yh.loadIdentity();
         yh.ortho(0.0, (double)this.displayWidth, (double)this.displayHeight, 0.0, 1000.0, 3000.0);
         yh.matrixMode(5888);
         yh.loadIdentity();
         yh.translate(0.0F, 0.0F, -2000.0F);
         yh.glLineWidth(1.0F);
         yh.disableTexture2D();
         yN tessellator = yN.getInstance();
         tN bufferbuilder = tessellator.getBuffer();
         int i = true;
         int j = this.displayWidth - 160 - 10;
         int k = this.displayHeight - 320;
         yh.enableBlend();
         bufferbuilder.begin(7, zK.POSITION_COLOR);
         bufferbuilder.pos((double)((float)j - 176.0F), (double)((float)k - 96.0F - 16.0F), 0.0).color(200, 0, 0, 0).endVertex();
         bufferbuilder.pos((double)((float)j - 176.0F), (double)(k + 320), 0.0).color(200, 0, 0, 0).endVertex();
         bufferbuilder.pos((double)((float)j + 176.0F), (double)(k + 320), 0.0).color(200, 0, 0, 0).endVertex();
         bufferbuilder.pos((double)((float)j + 176.0F), (double)((float)k - 96.0F - 16.0F), 0.0).color(200, 0, 0, 0).endVertex();
         tessellator.draw();
         yh.disableBlend();
         double d0 = 0.0;

         int i1;
         int k2;
         for(int l = 0; l < list.size(); ++l) {
            Wj profiler$result1 = (Wj)list.get(l);
            i1 = MathHelper.floor(profiler$result1.usePercentage / 4.0) + 1;
            bufferbuilder.begin(6, zK.POSITION_COLOR);
            k2 = profiler$result1.getColor();
            int k1 = k2 >> 16 & 255;
            int l1 = k2 >> 8 & 255;
            int i2 = k2 & 255;
            bufferbuilder.pos((double)j, (double)k, 0.0).color(k1, l1, i2, 255).endVertex();

            int i3;
            float f3;
            float f4;
            float f5;
            for(i3 = i1; i3 >= 0; --i3) {
               f3 = (float)((d0 + profiler$result1.usePercentage * (double)i3 / (double)i1) * 6.283185307179586 / 100.0);
               f4 = MathHelper.sin(f3) * 160.0F;
               f5 = MathHelper.cos(f3) * 160.0F * 0.5F;
               bufferbuilder.pos((double)((float)j + f4), (double)((float)k - f5), 0.0).color(k1, l1, i2, 255).endVertex();
            }

            tessellator.draw();
            bufferbuilder.begin(5, zK.POSITION_COLOR);

            for(i3 = i1; i3 >= 0; --i3) {
               f3 = (float)((d0 + profiler$result1.usePercentage * (double)i3 / (double)i1) * 6.283185307179586 / 100.0);
               f4 = MathHelper.sin(f3) * 160.0F;
               f5 = MathHelper.cos(f3) * 160.0F * 0.5F;
               bufferbuilder.pos((double)((float)j + f4), (double)((float)k - f5), 0.0).color(k1 >> 1, l1 >> 1, i2 >> 1, 255).endVertex();
               bufferbuilder.pos((double)((float)j + f4), (double)((float)k - f5 + 10.0F), 0.0).color(k1 >> 1, l1 >> 1, i2 >> 1, 255).endVertex();
            }

            tessellator.draw();
            d0 += profiler$result1.usePercentage;
         }

         DecimalFormat decimalformat = new DecimalFormat("##0.00");
         yh.enableTexture2D();
         String s = "";
         if (!"unspecified".equals(profiler$result.profilerName)) {
            s = s + "[0] ";
         }

         if (profiler$result.profilerName.isEmpty()) {
            s = s + "ROOT ";
         } else {
            s = s + profiler$result.profilerName + ' ';
         }

         i1 = 16777215;
         this.fontRenderer.drawStringWithShadow(s, (float)(j - 160), (float)(k - 80 - 16), 16777215);
         s = decimalformat.format(profiler$result.totalUsePercentage) + "%";
         this.fontRenderer.drawStringWithShadow(s, (float)(j + 160 - this.fontRenderer.getStringWidth(s)), (float)(k - 80 - 16), 16777215);

         for(k2 = 0; k2 < list.size(); ++k2) {
            Wj profiler$result2 = (Wj)list.get(k2);
            StringBuilder stringbuilder = new StringBuilder();
            if ("unspecified".equals(profiler$result2.profilerName)) {
               stringbuilder.append("[?] ");
            } else {
               stringbuilder.append("[").append(k2 + 1).append("] ");
            }

            String s1 = stringbuilder.append(profiler$result2.profilerName).toString();
            this.fontRenderer.drawStringWithShadow(s1, (float)(j - 160), (float)(k + 80 + k2 * 8 + 20), profiler$result2.getColor());
            s1 = decimalformat.format(profiler$result2.usePercentage) + "%";
            this.fontRenderer.drawStringWithShadow(s1, (float)(j + 160 - 50 - this.fontRenderer.getStringWidth(s1)), (float)(k + 80 + k2 * 8 + 20), profiler$result2.getColor());
            s1 = decimalformat.format(profiler$result2.totalUsePercentage) + "%";
            this.fontRenderer.drawStringWithShadow(s1, (float)(j + 160 - this.fontRenderer.getStringWidth(s1)), (float)(k + 80 + k2 * 8 + 20), profiler$result2.getColor());
         }
      }

   }

   public void shutdown() {
      this.running = false;
   }

   public void setIngameFocus() {
      if (Display.isActive() && !this.inGameHasFocus) {
         if (!IS_RUNNING_ON_MAC) {
            Bl.updateKeyBindState();
         }

         this.inGameHasFocus = true;
         this.mouseHelper.grabMouseCursor();
         this.displayGuiScreen((lg)null);
         this.leftClickCounter = 10000;
      }

   }

   public void setIngameNotInFocus() {
      if (this.inGameHasFocus) {
         this.inGameHasFocus = false;
         this.mouseHelper.ungrabMouseCursor();
      }

   }

   public void displayInGameMenu() {
      if (this.currentScreen == null) {
         this.displayGuiScreen(new ko());
         if (this.isSingleplayer() && !this.integratedServer.getPublic()) {
            this.soundHandler.pauseSounds();
         }
      }

   }

   private void sendClickBlockToController(boolean leftClick) {
      if (!leftClick) {
         this.leftClickCounter = 0;
      }

      if (this.leftClickCounter <= 0 && !player.isHandActive()) {
         if (leftClick && this.objectMouseOver != null && this.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = this.objectMouseOver.getBlockPos();
            if (this.world.getBlockState(blockpos).getMaterial() != hM.AIR && this.playerController.onPlayerDamageBlock(blockpos, this.objectMouseOver.sideHit)) {
               this.effectRenderer.addBlockHitEffects(blockpos, this.objectMouseOver.sideHit);
               player.swingArm(EnumHand.MAIN_HAND);
            }
         } else {
            this.playerController.resetBlockRemoving();
         }
      }

   }

   public void clickMouse() {
      if (this.leftClickCounter <= 0) {
         if (this.objectMouseOver == null) {
            LOGGER.error("Null returned as 'hitResult', this shouldn't happen!");
            if (this.playerController.isNotCreative()) {
               this.leftClickCounter = 10;
            }
         } else if (!player.isRowingBoat()) {
            switch (this.objectMouseOver.typeOfHit) {
               case ENTITY:
                  this.playerController.attackEntity(player, this.objectMouseOver.entityHit);
                  break;
               case BLOCK:
                  BlockPos blockpos = this.objectMouseOver.getBlockPos();
                  if (this.world.getBlockState(blockpos).getMaterial() != hM.AIR) {
                     this.playerController.clickBlock(blockpos, this.objectMouseOver.sideHit);
                     break;
                  }
               case MISS:
                  if (this.playerController.isNotCreative()) {
                     this.leftClickCounter = 10;
                  }

                  player.resetCooldown();
            }

            player.swingArm(EnumHand.MAIN_HAND);
         }
      }

   }

   public void rightClickMouse() {
      if (!this.playerController.getIsHittingBlock()) {
         this.rightClickDelayTimer = 4;
         if (!player.isRowingBoat()) {
            if (this.objectMouseOver == null) {
               LOGGER.warn("Null returned as 'hitResult', this shouldn't happen!");
            }

            EnumHand[] var1 = EnumHand.values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
               EnumHand enumhand = var1[var3];
               Qy itemstack = player.getHeldItem(enumhand);
               if (this.objectMouseOver != null) {
                  switch (this.objectMouseOver.typeOfHit) {
                     case ENTITY:
                        0dk eventInteractWithEntity = new 0dk(this.objectMouseOver.entityHit);
                        0bz.method_Qm().method_Qn().post(eventInteractWithEntity);
                        if (!eventInteractWithEntity.method_bzU()) {
                           if (this.playerController.interactWithEntity(player, this.objectMouseOver.entityHit, this.objectMouseOver, enumhand) == EnumActionResult.SUCCESS) {
                              return;
                           }

                           if (this.playerController.interactWithEntity(player, this.objectMouseOver.entityHit, enumhand) == EnumActionResult.SUCCESS) {
                              return;
                           }
                        }
                        break;
                     case BLOCK:
                        BlockPos blockpos = this.objectMouseOver.getBlockPos();
                        if (this.world.getBlockState(blockpos).getMaterial() != hM.AIR) {
                           int i = itemstack.getCount();
                           EnumActionResult enumactionresult = this.playerController.processRightClickBlock(player, this.world, blockpos, this.objectMouseOver.sideHit, this.objectMouseOver.hitVec, enumhand);
                           if (enumactionresult == EnumActionResult.SUCCESS) {
                              player.swingArm(enumhand);
                              if (!itemstack.isEmpty() && (itemstack.getCount() != i || this.playerController.isInCreativeMode())) {
                                 this.entityRenderer.itemRenderer.resetEquippedProgress(enumhand);
                              }

                              return;
                           }
                        }
                  }
               }

               if (!itemstack.isEmpty() && this.playerController.processRightClick(player, this.world, enumhand) == EnumActionResult.SUCCESS) {
                  this.entityRenderer.itemRenderer.resetEquippedProgress(enumhand);
                  return;
               }
            }
         }
      }

   }

   public void toggleFullscreen() {
      try {
         this.fullscreen = !this.fullscreen;
         gameSettings.fullScreen = this.fullscreen;
         if (this.fullscreen) {
            this.updateDisplayMode();
            this.displayWidth = Display.getDisplayMode().getWidth();
            this.displayHeight = Display.getDisplayMode().getHeight();
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         } else {
            Display.setDisplayMode(new DisplayMode(this.tempDisplayWidth, this.tempDisplayHeight));
            this.displayWidth = this.tempDisplayWidth;
            this.displayHeight = this.tempDisplayHeight;
            if (this.displayWidth <= 0) {
               this.displayWidth = 1;
            }

            if (this.displayHeight <= 0) {
               this.displayHeight = 1;
            }
         }

         if (this.currentScreen != null) {
            this.resize(this.displayWidth, this.displayHeight);
         } else {
            this.updateFramebufferSize();
         }

         Display.setFullscreen(this.fullscreen);
         Display.setVSyncEnabled(gameSettings.enableVsync);
         this.updateDisplay();
      } catch (Exception var2) {
         Exception exception = var2;
         LOGGER.error("Couldn't toggle fullscreen", exception);
      }

   }

   private void resize(int width, int height) {
      this.displayWidth = Math.max(1, width);
      this.displayHeight = Math.max(1, height);
      if (this.currentScreen != null) {
         mC scaledresolution = new mC(this);
         this.currentScreen.onResize(this, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
      }

      this.loadingScreen = new nl(this);
      this.updateFramebufferSize();
   }

   private void updateFramebufferSize() {
      this.framebuffer.createBindFramebuffer(this.displayWidth, this.displayHeight);
      if (this.entityRenderer != null) {
         this.entityRenderer.updateShaderGroupSize(this.displayWidth, this.displayHeight);
      }

   }

   public iK getMusicTicker() {
      return this.musicTicker;
   }

   public void runTick() throws IOException {
      if (this.rightClickDelayTimer > 0) {
         --this.rightClickDelayTimer;
      }

      this.profiler.startSection("gui");
      if (!this.isGamePaused) {
         this.ingameGUI.updateTick();
      }

      this.profiler.endSection();
      this.entityRenderer.getMouseOver(1.0F);
      this.tutorial.onMouseHover(this.world, this.objectMouseOver);
      this.profiler.startSection("gameMode");
      if (!this.isGamePaused && this.world != null) {
         this.playerController.updateController();
      }

      this.profiler.endStartSection("textures");
      if (this.world != null) {
         this.renderEngine.tick();
      }

      if (this.currentScreen == null && player != null) {
         if (player.getHealth() <= 0.0F && !(this.currentScreen instanceof kk)) {
            this.displayGuiScreen((lg)null);
         } else if (player.isPlayerSleeping() && this.world != null) {
            this.displayGuiScreen(new lv());
         }
      } else if (this.currentScreen != null && this.currentScreen instanceof lv && !player.isPlayerSleeping()) {
         this.displayGuiScreen((lg)null);
      }

      if (this.currentScreen != null) {
         this.leftClickCounter = 10000;
      }

      Throwable throwable2;
      Er crashreport2;
      Ey crashreportcategory2;
      if (this.currentScreen != null) {
         try {
            this.currentScreen.handleInput();
         } catch (Throwable var5) {
            throwable2 = var5;
            crashreport2 = Er.makeCrashReport(throwable2, "Updating screen events");
            crashreportcategory2 = crashreport2.makeCategory("Affected screen");
            crashreportcategory2.addDetail("Screen name", new Ez<String>() {
               public String call() throws Exception {
                  return nC.this.currentScreen.getClass().getCanonicalName();
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            throw new ReportedException(crashreport2);
         }

         if (this.currentScreen != null) {
            try {
               this.currentScreen.updateScreen();
            } catch (Throwable var4) {
               throwable2 = var4;
               crashreport2 = Er.makeCrashReport(throwable2, "Ticking screen");
               crashreportcategory2 = crashreport2.makeCategory("Affected screen");
               crashreportcategory2.addDetail("Screen name", new Ez<String>() {
                  public String call() throws Exception {
                     return nC.this.currentScreen.getClass().getCanonicalName();
                  }

                  // $FF: synthetic method
                  // $FF: bridge method
                  public Object call() throws Exception {
                     return this.call();
                  }
               });
               throw new ReportedException(crashreport2);
            }
         }
      }

      if (this.currentScreen == null || this.currentScreen.allowUserInput) {
         this.profiler.endStartSection("mouse");
         this.runTickMouse();
         if (this.leftClickCounter > 0) {
            --this.leftClickCounter;
         }

         this.profiler.endStartSection("keyboard");
         this.runTickKeyboard();
      }

      if (this.world != null) {
         if (player != null) {
            ++this.joinPlayerCounter;
            if (this.joinPlayerCounter == 30) {
               this.joinPlayerCounter = 0;
               this.world.joinEntityInSurroundings(player);
            }
         }

         this.profiler.endStartSection("gameRenderer");
         if (!this.isGamePaused) {
            this.entityRenderer.updateRenderer();
         }

         this.profiler.endStartSection("levelRenderer");
         if (!this.isGamePaused) {
            this.renderGlobal.updateClouds();
         }

         this.profiler.endStartSection("level");
         if (!this.isGamePaused) {
            if (this.world.getLastLightningBolt() > 0) {
               this.world.setLastLightningBolt(this.world.getLastLightningBolt() - 1);
            }

            this.world.updateEntities();
         }
      } else if (this.entityRenderer.isShaderActive()) {
         this.entityRenderer.stopUseShader();
      }

      if (!this.isGamePaused) {
         this.musicTicker.update();
         this.soundHandler.update();
      }

      if (this.world != null) {
         if (!this.isGamePaused) {
            this.world.setAllowedSpawnTypes(this.world.getDifficulty() != baV.PEACEFUL, true);
            this.tutorial.update();

            try {
               this.world.tick();
            } catch (Throwable var6) {
               throwable2 = var6;
               crashreport2 = Er.makeCrashReport(throwable2, "Exception in world tick");
               if (this.world == null) {
                  crashreportcategory2 = crashreport2.makeCategory("Affected level");
                  crashreportcategory2.addCrashSection("Problem", "Level is null!");
               } else {
                  this.world.addWorldInfoToCrashReport(crashreport2);
               }

               throw new ReportedException(crashreport2);
            }
         }

         this.profiler.endStartSection("animateTick");
         if (!this.isGamePaused && this.world != null) {
            this.world.doVoidFogParticles(MathHelper.floor(player.posX), MathHelper.floor(player.posY), MathHelper.floor(player.posZ));
         }

         this.profiler.endStartSection("particles");
         if (!this.isGamePaused) {
            this.effectRenderer.updateEffects();
         }
      } else if (this.networkManager != null) {
         this.profiler.endStartSection("pendingConnection");
         this.networkManager.processReceivedPackets();
      }

      this.profiler.endSection();
      this.systemTime = getSystemTime();
   }

   private void runTickKeyboard() throws IOException {
      while(Keyboard.next()) {
         int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
         if (this.debugCrashKeyPressTime > 0L) {
            if (getSystemTime() - this.debugCrashKeyPressTime >= 6000L) {
               throw new ReportedException(new Er("Manually triggered debug crash", new Throwable()));
            }

            if (!Keyboard.isKeyDown(46) || !Keyboard.isKeyDown(61)) {
               this.debugCrashKeyPressTime = -1L;
            }
         } else if (Keyboard.isKeyDown(46) && Keyboard.isKeyDown(61)) {
            this.actionKeyF3 = true;
            this.debugCrashKeyPressTime = getSystemTime();
         }

         this.dispatchKeypresses();
         if (this.currentScreen != null) {
            this.currentScreen.handleKeyboardInput();
         }

         boolean flag = Keyboard.getEventKeyState();
         if (flag) {
            0bz.method_Qm().method_Qn().post(new 0dd(i));
            if (i == 62 && this.entityRenderer != null) {
               this.entityRenderer.switchUseShader();
            }

            boolean flag1 = false;
            if (this.currentScreen == null) {
               if (i == 1) {
                  this.displayInGameMenu();
               }

               flag1 = Keyboard.isKeyDown(61) && this.processKeyF3(i);
               this.actionKeyF3 |= flag1;
               if (i == 59) {
                  gameSettings.hideGUI = !gameSettings.hideGUI;
               }
            }

            if (flag1) {
               Bl.setKeyBindState(i, false);
            } else {
               Bl.setKeyBindState(i, true);
               Bl.onTick(i);
            }

            if (gameSettings.showDebugProfilerChart) {
               if (i == 11) {
                  this.updateDebugProfilerName(0);
               }

               for(int j = 0; j < 9; ++j) {
                  if (i == 2 + j) {
                     this.updateDebugProfilerName(j + 1);
                  }
               }
            }
         } else {
            Bl.setKeyBindState(i, false);
            if (i == 61) {
               if (this.actionKeyF3) {
                  this.actionKeyF3 = false;
               } else {
                  gameSettings.showDebugInfo = !gameSettings.showDebugInfo;
                  gameSettings.showDebugProfilerChart = gameSettings.showDebugInfo && lg.isShiftKeyDown();
                  gameSettings.showLagometer = gameSettings.showDebugInfo && lg.isAltKeyDown();
               }
            }
         }
      }

      this.processKeyBinds();
   }

   private boolean processKeyF3(int auxKey) {
      if (auxKey == 30) {
         this.renderGlobal.loadRenderers();
         this.debugFeedbackTranslated("debug.reload_chunks.message");
         return true;
      } else {
         boolean flag;
         if (auxKey == 48) {
            flag = !this.renderManager.isDebugBoundingBox();
            this.renderManager.setDebugBoundingBox(flag);
            this.debugFeedbackTranslated(flag ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
            return true;
         } else if (auxKey == 32) {
            if (this.ingameGUI != null) {
               this.ingameGUI.getChatGUI().clearChatMessages(false);
            }

            return true;
         } else if (auxKey == 33) {
            gameSettings.setOptionValue(Bi.RENDER_DISTANCE, lg.isShiftKeyDown() ? -1 : 1);
            this.debugFeedbackTranslated("debug.cycle_renderdistance.message", gameSettings.renderDistanceChunks);
            return true;
         } else if (auxKey == 34) {
            flag = this.debugRenderer.toggleChunkBorders();
            this.debugFeedbackTranslated(flag ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off");
            return true;
         } else if (auxKey == 35) {
            gameSettings.advancedItemTooltips = !gameSettings.advancedItemTooltips;
            this.debugFeedbackTranslated(gameSettings.advancedItemTooltips ? "debug.advanced_tooltips.on" : "debug.advanced_tooltips.off");
            gameSettings.saveOptions();
            return true;
         } else if (auxKey == 49) {
            if (!player.canUseCommand(2, "")) {
               this.debugFeedbackTranslated("debug.creative_spectator.error");
            } else if (player.isCreative()) {
               player.sendChatMessage("/gamemode spectator");
            } else if (player.isSpectator()) {
               player.sendChatMessage("/gamemode creative");
            }

            return true;
         } else if (auxKey == 25) {
            gameSettings.pauseOnLostFocus = !gameSettings.pauseOnLostFocus;
            gameSettings.saveOptions();
            this.debugFeedbackTranslated(gameSettings.pauseOnLostFocus ? "debug.pause_focus.on" : "debug.pause_focus.off");
            return true;
         } else if (auxKey == 16) {
            this.debugFeedbackTranslated("debug.help.message");
            kJ guinewchat = this.ingameGUI.getChatGUI();
            guinewchat.printChatMessage(new TextComponentTranslation("debug.reload_chunks.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.show_hitboxes.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.clear_chat.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.cycle_renderdistance.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.chunk_boundaries.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.advanced_tooltips.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.creative_spectator.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.pause_focus.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.help.help", new Object[0]));
            guinewchat.printChatMessage(new TextComponentTranslation("debug.reload_resourcepacks.help", new Object[0]));
            return true;
         } else if (auxKey == 20) {
            this.debugFeedbackTranslated("debug.reload_resourcepacks.message");
            this.refreshResources();
            return true;
         } else {
            return false;
         }
      }
   }

   private void processKeyBinds() {
      for(; gameSettings.keyBindTogglePerspective.isPressed(); this.renderGlobal.setDisplayListEntitiesDirty()) {
         ++gameSettings.thirdPersonView;
         if (gameSettings.thirdPersonView > 2) {
            gameSettings.thirdPersonView = 0;
         }

         if (gameSettings.thirdPersonView == 0) {
            this.entityRenderer.loadEntityShader(this.getRenderViewEntity());
         } else if (gameSettings.thirdPersonView == 1) {
            this.entityRenderer.loadEntityShader((Ig)null);
         }
      }

      while(gameSettings.keyBindSmoothCamera.isPressed()) {
         gameSettings.smoothCamera = !gameSettings.smoothCamera;
      }

      for(int i = 0; i < 9; ++i) {
         boolean flag = gameSettings.keyBindSaveToolbar.isKeyDown();
         boolean flag1 = gameSettings.keyBindLoadToolbar.isKeyDown();
         if (gameSettings.keyBindsHotbar[i].isPressed()) {
            if (player.isSpectator()) {
               this.ingameGUI.getSpectatorGui().onHotbarSelected(i);
            } else if (player.isCreative() && this.currentScreen == null && (flag1 || flag)) {
               lY.handleHotbarSnapshots(this, i, flag1, flag);
            } else {
               player.inventory.currentItem = i;
            }
         }
      }

      while(gameSettings.keyBindInventory.isPressed()) {
         if (this.playerController.isRidingHorse()) {
            player.sendHorseInventory();
         } else {
            this.tutorial.openInventory();
            this.displayGuiScreen(new mh(player));
         }
      }

      while(gameSettings.keyBindAdvancements.isPressed()) {
         this.displayGuiScreen(new jx(player.connection.getAdvancementManager()));
      }

      while(gameSettings.keyBindSwapHands.isPressed()) {
         if (!player.isSpectator()) {
            this.getConnection().sendPacket(new Tb(Ta.SWAP_HELD_ITEMS, BlockPos.ORIGIN, EnumFacing.DOWN));
         }
      }

      while(gameSettings.keyBindDrop.isPressed()) {
         if (!player.isSpectator()) {
            player.dropItem(lg.isCtrlKeyDown());
         }
      }

      boolean flag2 = gameSettings.chatVisibility != MB.HIDDEN;
      if (flag2) {
         while(gameSettings.keyBindChat.isPressed()) {
            this.displayGuiScreen(new jP());
         }

         if (this.currentScreen == null && gameSettings.keyBindCommand.isPressed()) {
            this.displayGuiScreen(new jP("/"));
         }
      }

      if (player.isHandActive()) {
         if (!gameSettings.keyBindUseItem.isKeyDown()) {
            this.playerController.onStoppedUsingItem(player);
         }

         while(gameSettings.keyBindAttack.isPressed()) {
         }

         label104:
         while(true) {
            if (!gameSettings.keyBindUseItem.isPressed()) {
               while(true) {
                  if (gameSettings.keyBindPickBlock.isPressed()) {
                     continue;
                  }
                  break label104;
               }
            }
         }
      } else {
         while(gameSettings.keyBindAttack.isPressed()) {
            this.clickMouse();
         }

         while(gameSettings.keyBindUseItem.isPressed()) {
            this.rightClickMouse();
         }

         while(gameSettings.keyBindPickBlock.isPressed()) {
            this.middleClickMouse();
         }
      }

      if (gameSettings.keyBindUseItem.isKeyDown() && this.rightClickDelayTimer == 0 && !player.isHandActive()) {
         this.rightClickMouse();
      }

      this.sendClickBlockToController(this.currentScreen == null && gameSettings.keyBindAttack.isKeyDown() && this.inGameHasFocus);
   }

   private void runTickMouse() throws IOException {
      while(Mouse.next()) {
         int i = Mouse.getEventButton();
         Bl.setKeyBindState(i - 100, Mouse.getEventButtonState());
         if (Mouse.getEventButtonState()) {
            0bz.method_Qm().method_Qn().post(new 0df(i));
            if (player.isSpectator() && i == 2) {
               this.ingameGUI.getSpectatorGui().onMiddleClick();
            } else {
               Bl.onTick(i - 100);
            }
         }

         long j = getSystemTime() - this.systemTime;
         if (j <= 200L) {
            int k = Mouse.getEventDWheel();
            if (k != 0) {
               if (player.isSpectator()) {
                  k = k < 0 ? -1 : 1;
                  if (this.ingameGUI.getSpectatorGui().isMenuActive()) {
                     this.ingameGUI.getSpectatorGui().onMouseScroll(-k);
                  } else {
                     float f = MathHelper.clamp(player.capabilities.getFlySpeed() + (float)k * 0.005F, 0.0F, 0.2F);
                     player.capabilities.setFlySpeed(f);
                  }
               } else {
                  player.inventory.changeCurrentItem(k);
               }
            }

            if (this.currentScreen == null) {
               if (!this.inGameHasFocus && Mouse.getEventButtonState()) {
                  this.setIngameFocus();
               }
            } else if (this.currentScreen != null) {
               this.currentScreen.handleMouseInput();
            }
         }
      }

   }

   private void debugFeedbackTranslated(String untranslatedTemplate, Object... objs) {
      this.ingameGUI.getChatGUI().printChatMessage((new TextComponentString("")).appendSibling((new TextComponentTranslation("debug.prefix", new Object[0])).setStyle((new Style()).setColor(TextFormatting.YELLOW).setBold(true))).appendText(" ").appendSibling(new TextComponentTranslation(untranslatedTemplate, objs)));
   }

   public void launchIntegratedServer(String folderName, String worldName, @Nullable biw worldSettingsIn) {
      this.loadWorld((pm)null);
      System.gc();
      bgm isavehandler = this.saveLoader.getSaveLoader(folderName, false);
      bhY worldinfo = isavehandler.loadWorldInfo();
      if (worldinfo == null && worldSettingsIn != null) {
         worldinfo = new bhY(worldSettingsIn, folderName);
         isavehandler.saveWorldInfo(worldinfo);
      }

      if (worldSettingsIn == null) {
         worldSettingsIn = new biw(worldinfo);
      }

      try {
         YggdrasilAuthenticationService yggdrasilauthenticationservice = new YggdrasilAuthenticationService(this.proxy, UUID.randomUUID().toString());
         MinecraftSessionService minecraftsessionservice = yggdrasilauthenticationservice.createMinecraftSessionService();
         GameProfileRepository gameprofilerepository = yggdrasilauthenticationservice.createProfileRepository();
         Xd playerprofilecache = new Xd(gameprofilerepository, new File(this.gameDir, Xx.USER_CACHE_FILE.getName()));
         YR.setProfileCache(playerprofilecache);
         YR.setSessionService(minecraftsessionservice);
         Xd.setOnlineMode(false);
         this.integratedServer = new WK(this, folderName, worldName, worldSettingsIn, yggdrasilauthenticationservice, minecraftsessionservice, gameprofilerepository, playerprofilecache);
         this.integratedServer.startServerThread();
         this.integratedServerIsRunning = true;
      } catch (Throwable var11) {
         Throwable throwable = var11;
         Er crashreport = Er.makeCrashReport(throwable, "Starting integrated server");
         Ey crashreportcategory = crashreport.makeCategory("Starting integrated server");
         crashreportcategory.addCrashSection("Level ID", folderName);
         crashreportcategory.addCrashSection("Level Name", worldName);
         throw new ReportedException(crashreport);
      }

      this.loadingScreen.displaySavingString(Ax.format("menu.loadingLevel"));

      while(!this.integratedServer.serverIsInRunLoop()) {
         String s = this.integratedServer.getUserMessage();
         if (s != null) {
            this.loadingScreen.displayLoadingString(Ax.format(s));
         } else {
            this.loadingScreen.displayLoadingString("");
         }

         try {
            Thread.sleep(200L);
         } catch (InterruptedException var10) {
         }
      }

      this.displayGuiScreen(new lt());
      SocketAddress socketaddress = this.integratedServer.getNetworkSystem().addLocalEndpoint();
      Sp networkmanager = Sp.provideLocalClient(socketaddress);
      networkmanager.setNetHandler(new pt(networkmanager, this, (lg)null));
      networkmanager.sendPacket(new RD(socketaddress.toString(), 0, RB.LOGIN));
      networkmanager.sendPacket(new RK(this.getSession().getProfile()));
      this.networkManager = networkmanager;
   }

   public void loadWorld(@Nullable pm worldClientIn) {
      this.loadWorld(worldClientIn, "");
   }

   public void loadWorld(@Nullable pm worldClientIn, String loadingMessage) {
      if (worldClientIn == null) {
         py nethandlerplayclient = this.getConnection();
         if (nethandlerplayclient != null) {
            nethandlerplayclient.cleanup();
         }

         if (this.integratedServer != null && this.integratedServer.isAnvilFileSet()) {
            this.integratedServer.initiateShutdown();
         }

         this.integratedServer = null;
         this.entityRenderer.resetData();
         this.playerController = null;
         jC.INSTANCE.clear();
      }

      this.renderViewEntity = null;
      this.networkManager = null;
      if (this.loadingScreen != null) {
         this.loadingScreen.resetProgressAndMessage(loadingMessage);
         this.loadingScreen.displayLoadingString("");
      }

      if (worldClientIn == null && this.world != null) {
         this.resourcePackRepository.clearResourcePack();
         this.ingameGUI.resetPlayersOverlayFooterHeader();
         this.setServerData((pf)null);
         this.integratedServerIsRunning = false;
      }

      this.soundHandler.stopSounds();
      this.world = worldClientIn;
      if (this.renderGlobal != null) {
         this.renderGlobal.setWorldAndLoadRenderers(worldClientIn);
      }

      if (this.effectRenderer != null) {
         this.effectRenderer.clearEffects(worldClientIn);
      }

      zz.instance.setWorld(worldClientIn);
      if (worldClientIn != null) {
         if (!this.integratedServerIsRunning) {
            AuthenticationService authenticationservice = new YggdrasilAuthenticationService(this.proxy, UUID.randomUUID().toString());
            MinecraftSessionService minecraftsessionservice = authenticationservice.createMinecraftSessionService();
            GameProfileRepository gameprofilerepository = authenticationservice.createProfileRepository();
            Xd playerprofilecache = new Xd(gameprofilerepository, new File(this.gameDir, Xx.USER_CACHE_FILE.getName()));
            YR.setProfileCache(playerprofilecache);
            YR.setSessionService(minecraftsessionservice);
            Xd.setOnlineMode(false);
         }

         if (player == null) {
            player = this.playerController.createPlayer(worldClientIn, new XT(), new BP());
            this.playerController.flipPlayer(player);
         }

         player.preparePlayerToSpawn();
         worldClientIn.spawnEntity(player);
         player.movementInput = new MovementInputFromOptions(gameSettings);
         this.playerController.setPlayerCapabilities(player);
         this.renderViewEntity = player;
      } else {
         this.saveLoader.flushCache();
         player = null;
      }

      System.gc();
      this.systemTime = 0L;
   }

   public void setDimensionAndSpawnPlayer(int dimension) {
      this.world.setInitialSpawnLocation();
      this.world.removeAllEntities();
      int i = 0;
      String s = null;
      if (player != null) {
         i = player.getEntityId();
         this.world.removeEntity(player);
         s = player.getServerBrand();
      }

      this.renderViewEntity = null;
      jh entityplayersp = player;
      player = this.playerController.createPlayer(this.world, player == null ? new XT() : player.getStatFileWriter(), player == null ? new XK() : player.getRecipeBook());
      player.getDataManager().setEntryValues(entityplayersp.getDataManager().getAll());
      player.dimension = dimension;
      this.renderViewEntity = player;
      player.preparePlayerToSpawn();
      player.setServerBrand(s);
      this.world.spawnEntity(player);
      this.playerController.flipPlayer(player);
      player.movementInput = new MovementInputFromOptions(gameSettings);
      player.setEntityId(i);
      this.playerController.setPlayerCapabilities(player);
      player.setReducedDebug(entityplayersp.hasReducedDebug());
      if (this.currentScreen instanceof kk) {
         this.displayGuiScreen((lg)null);
      }

   }

   public final boolean isDemo() {
      return this.isDemo;
   }

   @Nullable
   public py getConnection() {
      return player == null ? null : player.connection;
   }

   public static boolean isGuiEnabled() {
      boolean var0;
      if (instance != null) {
         nC var10000 = instance;
         if (gameSettings.hideGUI) {
            var0 = false;
            return var0;
         }
      }

      var0 = true;
      return var0;
   }

   public static boolean isFancyGraphicsEnabled() {
      boolean var0;
      if (instance != null) {
         nC var10000 = instance;
         if (gameSettings.fancyGraphics) {
            var0 = true;
            return var0;
         }
      }

      var0 = false;
      return var0;
   }

   public static boolean isAmbientOcclusionEnabled() {
      boolean var0;
      if (instance != null) {
         nC var10000 = instance;
         if (gameSettings.ambientOcclusion != 0) {
            var0 = true;
            return var0;
         }
      }

      var0 = false;
      return var0;
   }

   private void middleClickMouse() {
      if (this.objectMouseOver != null && this.objectMouseOver.typeOfHit != RayTraceResult.Type.MISS) {
         boolean flag = player.capabilities.isCreativeMode;
         Yg tileentity = null;
         Qy itemstack;
         if (this.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = this.objectMouseOver.getBlockPos();
            in iblockstate = this.world.getBlockState(blockpos);
            co block = iblockstate.getBlock();
            if (iblockstate.getMaterial() == hM.AIR) {
               return;
            }

            itemstack = block.getItem(this.world, blockpos, iblockstate);
            if (itemstack.isEmpty()) {
               return;
            }

            if (flag && lg.isCtrlKeyDown() && block.hasTileEntity()) {
               tileentity = this.world.getTileEntity(blockpos);
            }
         } else {
            if (this.objectMouseOver.typeOfHit != RayTraceResult.Type.ENTITY || this.objectMouseOver.entityHit == null || !flag) {
               return;
            }

            if (this.objectMouseOver.entityHit instanceof Jq) {
               itemstack = new Qy(NK.PAINTING);
            } else if (this.objectMouseOver.entityHit instanceof Ip) {
               itemstack = new Qy(NK.LEAD);
            } else if (this.objectMouseOver.entityHit instanceof IZ) {
               IZ entityitemframe = (IZ)this.objectMouseOver.entityHit;
               Qy itemstack1 = entityitemframe.getDisplayedItem();
               if (itemstack1.isEmpty()) {
                  itemstack = new Qy(NK.ITEM_FRAME);
               } else {
                  itemstack = itemstack1.copy();
               }
            } else if (this.objectMouseOver.entityHit instanceof Jc) {
               Jc entityminecart = (Jc)this.objectMouseOver.entityHit;
               OL item1;
               switch (entityminecart.getType()) {
                  case FURNACE:
                     item1 = NK.FURNACE_MINECART;
                     break;
                  case CHEST:
                     item1 = NK.CHEST_MINECART;
                     break;
                  case TNT:
                     item1 = NK.TNT_MINECART;
                     break;
                  case HOPPER:
                     item1 = NK.HOPPER_MINECART;
                     break;
                  case COMMAND_BLOCK:
                     item1 = NK.COMMAND_BLOCK_MINECART;
                     break;
                  default:
                     item1 = NK.MINECART;
               }

               itemstack = new Qy(item1);
            } else if (this.objectMouseOver.entityHit instanceof IR) {
               itemstack = new Qy(((IR)this.objectMouseOver.entityHit).getItemBoat());
            } else if (this.objectMouseOver.entityHit instanceof IN) {
               itemstack = new Qy(NK.ARMOR_STAND);
            } else if (this.objectMouseOver.entityHit instanceof IS) {
               itemstack = new Qy(NK.END_CRYSTAL);
            } else {
               ResourceLocation resourcelocation = Ir.getKey(this.objectMouseOver.entityHit);
               if (resourcelocation == null || !Ir.ENTITY_EGGS.containsKey(resourcelocation)) {
                  return;
               }

               itemstack = new Qy(NK.SPAWN_EGG);
               PX.applyEntityIdToItemStack(itemstack, resourcelocation);
            }
         }

         if (itemstack.isEmpty()) {
            String s = "";
            if (this.objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
               s = ((ResourceLocation)co.REGISTRY.getNameForObject(this.world.getBlockState(this.objectMouseOver.getBlockPos()).getBlock())).toString();
            } else if (this.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
               s = Ir.getKey(this.objectMouseOver.entityHit).toString();
            }

            LOGGER.warn("Picking on: [{}] {} gave null item", this.objectMouseOver.typeOfHit, s);
         } else {
            MJ inventoryplayer = player.inventory;
            if (tileentity != null) {
               this.storeTEInStack(itemstack, tileentity);
            }

            int i = inventoryplayer.getSlotFor(itemstack);
            if (flag) {
               inventoryplayer.setPickedItemStack(itemstack);
               this.playerController.sendSlotPacket(player.getHeldItem(EnumHand.MAIN_HAND), 36 + inventoryplayer.currentItem);
            } else if (i != -1) {
               if (MJ.isHotbar(i)) {
                  inventoryplayer.currentItem = i;
               } else {
                  this.playerController.pickItem(i);
               }
            }
         }
      }

   }

   private Qy storeTEInStack(Qy stack, Yg te) {
      QQ nbttagcompound = te.writeToNBT(new QQ());
      QQ nbttagcompound1;
      if (stack.getItem() == NK.SKULL && nbttagcompound.hasKey("Owner")) {
         nbttagcompound1 = nbttagcompound.getCompoundTag("Owner");
         QQ nbttagcompound3 = new QQ();
         nbttagcompound3.setTag("SkullOwner", nbttagcompound1);
         stack.setTagCompound(nbttagcompound3);
         return stack;
      } else {
         stack.setTagInfo("BlockEntityTag", nbttagcompound);
         nbttagcompound1 = new QQ();
         QW nbttaglist = new QW();
         nbttaglist.appendTag(new Ra("(+NBT)"));
         nbttagcompound1.setTag("Lore", nbttaglist);
         stack.setTagInfo("display", nbttagcompound1);
         return stack;
      }
   }

   public Er addGraphicsAndWorldToCrashReport(Er theCrash) {
      theCrash.getCategory().addDetail("Launched Version", new Ez<String>() {
         public String call() throws Exception {
            return nC.this.launchedVersion;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("LWJGL", new Ez<String>() {
         public String call() throws Exception {
            return Sys.getVersion();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("OpenGL", new Ez<String>() {
         public String call() {
            return yh.glGetString(7937) + " GL version " + yh.glGetString(7938) + ", " + yh.glGetString(7936);
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("GL Caps", new Ez<String>() {
         public String call() {
            return ys.getLogText();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("Using VBOs", new Ez<String>() {
         public String call() {
            nC var10000 = nC.this;
            return nC.gameSettings.useVbo ? "Yes" : "No";
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("Is Modded", new Ez<String>() {
         public String call() throws Exception {
            String s = je.getClientModName();
            if (!"vanilla".equals(s)) {
               return "Definitely; Client brand changed to '" + s + "'";
            } else {
               return nC.class.getSigners() == null ? "Very likely; Jar signature invalidated" : "Probably not. Jar signature remains and client brand is untouched.";
            }
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("Type", new Ez<String>() {
         public String call() throws Exception {
            return "Client (map_client.txt)";
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("Resource Packs", new Ez<String>() {
         public String call() throws Exception {
            StringBuilder stringbuilder = new StringBuilder();
            nC var10000 = nC.this;
            Iterator var2 = nC.gameSettings.resourcePacks.iterator();

            while(var2.hasNext()) {
               String s = (String)var2.next();
               if (stringbuilder.length() > 0) {
                  stringbuilder.append(", ");
               }

               stringbuilder.append(s);
               var10000 = nC.this;
               if (nC.gameSettings.incompatibleResourcePacks.contains(s)) {
                  stringbuilder.append(" (incompatible)");
               }
            }

            return stringbuilder.toString();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("Current Language", new Ez<String>() {
         public String call() throws Exception {
            return nC.this.languageManager.getCurrentLanguage().toString();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("Profiler Position", new Ez<String>() {
         public String call() throws Exception {
            return nC.this.profiler.profilingEnabled ? nC.this.profiler.getNameOfLastSection() : "N/A (disabled)";
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      theCrash.getCategory().addDetail("CPU", new Ez<String>() {
         public String call() throws Exception {
            return ys.getCpu();
         }

         // $FF: synthetic method
         // $FF: bridge method
         public Object call() throws Exception {
            return this.call();
         }
      });
      if (this.world != null) {
         this.world.addWorldInfoToCrashReport(theCrash);
      }

      return theCrash;
   }

   public static nC getMinecraft() {
      return instance;
   }

   public ListenableFuture<Object> scheduleResourcesRefresh() {
      return this.addScheduledTask(new Runnable() {
         public void run() {
            nC.this.refreshResources();
         }
      });
   }

   public void addServerStatsToSnooper(Wm playerSnooper) {
      playerSnooper.addClientStat("fps", debugFPS);
      playerSnooper.addClientStat("vsync_enabled", gameSettings.enableVsync);
      playerSnooper.addClientStat("display_frequency", Display.getDisplayMode().getFrequency());
      playerSnooper.addClientStat("display_type", this.fullscreen ? "fullscreen" : "windowed");
      playerSnooper.addClientStat("run_time", (Xx.getCurrentTimeMillis() - playerSnooper.getMinecraftStartTimeMillis()) / 60L * 1000L);
      playerSnooper.addClientStat("current_action", this.getCurrentAction());
      playerSnooper.addClientStat("language", gameSettings.language == null ? "en_us" : gameSettings.language);
      String s = ByteOrder.nativeOrder() == ByteOrder.LITTLE_ENDIAN ? "little" : "big";
      playerSnooper.addClientStat("endianness", s);
      playerSnooper.addClientStat("subtitles", gameSettings.showSubtitles);
      playerSnooper.addClientStat("touch", gameSettings.touchscreen ? "touch" : "mouse");
      playerSnooper.addClientStat("resource_packs", this.resourcePackRepository.getRepositoryEntries().size());
      int i = 0;
      Iterator var4 = this.resourcePackRepository.getRepositoryEntries().iterator();

      while(var4.hasNext()) {
         AU resourcepackrepository$entry = (AU)var4.next();
         playerSnooper.addClientStat("resource_pack[" + i++ + "]", resourcepackrepository$entry.getResourcePackName());
      }

      if (this.integratedServer != null && this.integratedServer.getPlayerUsageSnooper() != null) {
         playerSnooper.addClientStat("snooper_partner", this.integratedServer.getPlayerUsageSnooper().getUniqueID());
      }

   }

   private String getCurrentAction() {
      if (this.integratedServer != null) {
         return this.integratedServer.getPublic() ? "hosting_lan" : "singleplayer";
      } else if (this.currentServerData != null) {
         return this.currentServerData.isOnLAN() ? "playing_lan" : "multiplayer";
      } else {
         return "out_of_game";
      }
   }

   public void addServerTypeToSnooper(Wm playerSnooper) {
      playerSnooper.addStatToSnooper("opengl_version", yh.glGetString(7938));
      playerSnooper.addStatToSnooper("opengl_vendor", yh.glGetString(7936));
      playerSnooper.addStatToSnooper("client_brand", je.getClientModName());
      playerSnooper.addStatToSnooper("launched_version", this.launchedVersion);
      ContextCapabilities contextcapabilities = GLContext.getCapabilities();
      playerSnooper.addStatToSnooper("gl_caps[ARB_arrays_of_arrays]", contextcapabilities.GL_ARB_arrays_of_arrays);
      playerSnooper.addStatToSnooper("gl_caps[ARB_base_instance]", contextcapabilities.GL_ARB_base_instance);
      playerSnooper.addStatToSnooper("gl_caps[ARB_blend_func_extended]", contextcapabilities.GL_ARB_blend_func_extended);
      playerSnooper.addStatToSnooper("gl_caps[ARB_clear_buffer_object]", contextcapabilities.GL_ARB_clear_buffer_object);
      playerSnooper.addStatToSnooper("gl_caps[ARB_color_buffer_float]", contextcapabilities.GL_ARB_color_buffer_float);
      playerSnooper.addStatToSnooper("gl_caps[ARB_compatibility]", contextcapabilities.GL_ARB_compatibility);
      playerSnooper.addStatToSnooper("gl_caps[ARB_compressed_texture_pixel_storage]", contextcapabilities.GL_ARB_compressed_texture_pixel_storage);
      playerSnooper.addStatToSnooper("gl_caps[ARB_compute_shader]", contextcapabilities.GL_ARB_compute_shader);
      playerSnooper.addStatToSnooper("gl_caps[ARB_copy_buffer]", contextcapabilities.GL_ARB_copy_buffer);
      playerSnooper.addStatToSnooper("gl_caps[ARB_copy_image]", contextcapabilities.GL_ARB_copy_image);
      playerSnooper.addStatToSnooper("gl_caps[ARB_depth_buffer_float]", contextcapabilities.GL_ARB_depth_buffer_float);
      playerSnooper.addStatToSnooper("gl_caps[ARB_compute_shader]", contextcapabilities.GL_ARB_compute_shader);
      playerSnooper.addStatToSnooper("gl_caps[ARB_copy_buffer]", contextcapabilities.GL_ARB_copy_buffer);
      playerSnooper.addStatToSnooper("gl_caps[ARB_copy_image]", contextcapabilities.GL_ARB_copy_image);
      playerSnooper.addStatToSnooper("gl_caps[ARB_depth_buffer_float]", contextcapabilities.GL_ARB_depth_buffer_float);
      playerSnooper.addStatToSnooper("gl_caps[ARB_depth_clamp]", contextcapabilities.GL_ARB_depth_clamp);
      playerSnooper.addStatToSnooper("gl_caps[ARB_depth_texture]", contextcapabilities.GL_ARB_depth_texture);
      playerSnooper.addStatToSnooper("gl_caps[ARB_draw_buffers]", contextcapabilities.GL_ARB_draw_buffers);
      playerSnooper.addStatToSnooper("gl_caps[ARB_draw_buffers_blend]", contextcapabilities.GL_ARB_draw_buffers_blend);
      playerSnooper.addStatToSnooper("gl_caps[ARB_draw_elements_base_vertex]", contextcapabilities.GL_ARB_draw_elements_base_vertex);
      playerSnooper.addStatToSnooper("gl_caps[ARB_draw_indirect]", contextcapabilities.GL_ARB_draw_indirect);
      playerSnooper.addStatToSnooper("gl_caps[ARB_draw_instanced]", contextcapabilities.GL_ARB_draw_instanced);
      playerSnooper.addStatToSnooper("gl_caps[ARB_explicit_attrib_location]", contextcapabilities.GL_ARB_explicit_attrib_location);
      playerSnooper.addStatToSnooper("gl_caps[ARB_explicit_uniform_location]", contextcapabilities.GL_ARB_explicit_uniform_location);
      playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_layer_viewport]", contextcapabilities.GL_ARB_fragment_layer_viewport);
      playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_program]", contextcapabilities.GL_ARB_fragment_program);
      playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_shader]", contextcapabilities.GL_ARB_fragment_shader);
      playerSnooper.addStatToSnooper("gl_caps[ARB_fragment_program_shadow]", contextcapabilities.GL_ARB_fragment_program_shadow);
      playerSnooper.addStatToSnooper("gl_caps[ARB_framebuffer_object]", contextcapabilities.GL_ARB_framebuffer_object);
      playerSnooper.addStatToSnooper("gl_caps[ARB_framebuffer_sRGB]", contextcapabilities.GL_ARB_framebuffer_sRGB);
      playerSnooper.addStatToSnooper("gl_caps[ARB_geometry_shader4]", contextcapabilities.GL_ARB_geometry_shader4);
      playerSnooper.addStatToSnooper("gl_caps[ARB_gpu_shader5]", contextcapabilities.GL_ARB_gpu_shader5);
      playerSnooper.addStatToSnooper("gl_caps[ARB_half_float_pixel]", contextcapabilities.GL_ARB_half_float_pixel);
      playerSnooper.addStatToSnooper("gl_caps[ARB_half_float_vertex]", contextcapabilities.GL_ARB_half_float_vertex);
      playerSnooper.addStatToSnooper("gl_caps[ARB_instanced_arrays]", contextcapabilities.GL_ARB_instanced_arrays);
      playerSnooper.addStatToSnooper("gl_caps[ARB_map_buffer_alignment]", contextcapabilities.GL_ARB_map_buffer_alignment);
      playerSnooper.addStatToSnooper("gl_caps[ARB_map_buffer_range]", contextcapabilities.GL_ARB_map_buffer_range);
      playerSnooper.addStatToSnooper("gl_caps[ARB_multisample]", contextcapabilities.GL_ARB_multisample);
      playerSnooper.addStatToSnooper("gl_caps[ARB_multitexture]", contextcapabilities.GL_ARB_multitexture);
      playerSnooper.addStatToSnooper("gl_caps[ARB_occlusion_query2]", contextcapabilities.GL_ARB_occlusion_query2);
      playerSnooper.addStatToSnooper("gl_caps[ARB_pixel_buffer_object]", contextcapabilities.GL_ARB_pixel_buffer_object);
      playerSnooper.addStatToSnooper("gl_caps[ARB_seamless_cube_map]", contextcapabilities.GL_ARB_seamless_cube_map);
      playerSnooper.addStatToSnooper("gl_caps[ARB_shader_objects]", contextcapabilities.GL_ARB_shader_objects);
      playerSnooper.addStatToSnooper("gl_caps[ARB_shader_stencil_export]", contextcapabilities.GL_ARB_shader_stencil_export);
      playerSnooper.addStatToSnooper("gl_caps[ARB_shader_texture_lod]", contextcapabilities.GL_ARB_shader_texture_lod);
      playerSnooper.addStatToSnooper("gl_caps[ARB_shadow]", contextcapabilities.GL_ARB_shadow);
      playerSnooper.addStatToSnooper("gl_caps[ARB_shadow_ambient]", contextcapabilities.GL_ARB_shadow_ambient);
      playerSnooper.addStatToSnooper("gl_caps[ARB_stencil_texturing]", contextcapabilities.GL_ARB_stencil_texturing);
      playerSnooper.addStatToSnooper("gl_caps[ARB_sync]", contextcapabilities.GL_ARB_sync);
      playerSnooper.addStatToSnooper("gl_caps[ARB_tessellation_shader]", contextcapabilities.GL_ARB_tessellation_shader);
      playerSnooper.addStatToSnooper("gl_caps[ARB_texture_border_clamp]", contextcapabilities.GL_ARB_texture_border_clamp);
      playerSnooper.addStatToSnooper("gl_caps[ARB_texture_buffer_object]", contextcapabilities.GL_ARB_texture_buffer_object);
      playerSnooper.addStatToSnooper("gl_caps[ARB_texture_cube_map]", contextcapabilities.GL_ARB_texture_cube_map);
      playerSnooper.addStatToSnooper("gl_caps[ARB_texture_cube_map_array]", contextcapabilities.GL_ARB_texture_cube_map_array);
      playerSnooper.addStatToSnooper("gl_caps[ARB_texture_non_power_of_two]", contextcapabilities.GL_ARB_texture_non_power_of_two);
      playerSnooper.addStatToSnooper("gl_caps[ARB_uniform_buffer_object]", contextcapabilities.GL_ARB_uniform_buffer_object);
      playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_blend]", contextcapabilities.GL_ARB_vertex_blend);
      playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_buffer_object]", contextcapabilities.GL_ARB_vertex_buffer_object);
      playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_program]", contextcapabilities.GL_ARB_vertex_program);
      playerSnooper.addStatToSnooper("gl_caps[ARB_vertex_shader]", contextcapabilities.GL_ARB_vertex_shader);
      playerSnooper.addStatToSnooper("gl_caps[EXT_bindable_uniform]", contextcapabilities.GL_EXT_bindable_uniform);
      playerSnooper.addStatToSnooper("gl_caps[EXT_blend_equation_separate]", contextcapabilities.GL_EXT_blend_equation_separate);
      playerSnooper.addStatToSnooper("gl_caps[EXT_blend_func_separate]", contextcapabilities.GL_EXT_blend_func_separate);
      playerSnooper.addStatToSnooper("gl_caps[EXT_blend_minmax]", contextcapabilities.GL_EXT_blend_minmax);
      playerSnooper.addStatToSnooper("gl_caps[EXT_blend_subtract]", contextcapabilities.GL_EXT_blend_subtract);
      playerSnooper.addStatToSnooper("gl_caps[EXT_draw_instanced]", contextcapabilities.GL_EXT_draw_instanced);
      playerSnooper.addStatToSnooper("gl_caps[EXT_framebuffer_multisample]", contextcapabilities.GL_EXT_framebuffer_multisample);
      playerSnooper.addStatToSnooper("gl_caps[EXT_framebuffer_object]", contextcapabilities.GL_EXT_framebuffer_object);
      playerSnooper.addStatToSnooper("gl_caps[EXT_framebuffer_sRGB]", contextcapabilities.GL_EXT_framebuffer_sRGB);
      playerSnooper.addStatToSnooper("gl_caps[EXT_geometry_shader4]", contextcapabilities.GL_EXT_geometry_shader4);
      playerSnooper.addStatToSnooper("gl_caps[EXT_gpu_program_parameters]", contextcapabilities.GL_EXT_gpu_program_parameters);
      playerSnooper.addStatToSnooper("gl_caps[EXT_gpu_shader4]", contextcapabilities.GL_EXT_gpu_shader4);
      playerSnooper.addStatToSnooper("gl_caps[EXT_multi_draw_arrays]", contextcapabilities.GL_EXT_multi_draw_arrays);
      playerSnooper.addStatToSnooper("gl_caps[EXT_packed_depth_stencil]", contextcapabilities.GL_EXT_packed_depth_stencil);
      playerSnooper.addStatToSnooper("gl_caps[EXT_paletted_texture]", contextcapabilities.GL_EXT_paletted_texture);
      playerSnooper.addStatToSnooper("gl_caps[EXT_rescale_normal]", contextcapabilities.GL_EXT_rescale_normal);
      playerSnooper.addStatToSnooper("gl_caps[EXT_separate_shader_objects]", contextcapabilities.GL_EXT_separate_shader_objects);
      playerSnooper.addStatToSnooper("gl_caps[EXT_shader_image_load_store]", contextcapabilities.GL_EXT_shader_image_load_store);
      playerSnooper.addStatToSnooper("gl_caps[EXT_shadow_funcs]", contextcapabilities.GL_EXT_shadow_funcs);
      playerSnooper.addStatToSnooper("gl_caps[EXT_shared_texture_palette]", contextcapabilities.GL_EXT_shared_texture_palette);
      playerSnooper.addStatToSnooper("gl_caps[EXT_stencil_clear_tag]", contextcapabilities.GL_EXT_stencil_clear_tag);
      playerSnooper.addStatToSnooper("gl_caps[EXT_stencil_two_side]", contextcapabilities.GL_EXT_stencil_two_side);
      playerSnooper.addStatToSnooper("gl_caps[EXT_stencil_wrap]", contextcapabilities.GL_EXT_stencil_wrap);
      playerSnooper.addStatToSnooper("gl_caps[EXT_texture_3d]", contextcapabilities.GL_EXT_texture_3d);
      playerSnooper.addStatToSnooper("gl_caps[EXT_texture_array]", contextcapabilities.GL_EXT_texture_array);
      playerSnooper.addStatToSnooper("gl_caps[EXT_texture_buffer_object]", contextcapabilities.GL_EXT_texture_buffer_object);
      playerSnooper.addStatToSnooper("gl_caps[EXT_texture_integer]", contextcapabilities.GL_EXT_texture_integer);
      playerSnooper.addStatToSnooper("gl_caps[EXT_texture_lod_bias]", contextcapabilities.GL_EXT_texture_lod_bias);
      playerSnooper.addStatToSnooper("gl_caps[EXT_texture_sRGB]", contextcapabilities.GL_EXT_texture_sRGB);
      playerSnooper.addStatToSnooper("gl_caps[EXT_vertex_shader]", contextcapabilities.GL_EXT_vertex_shader);
      playerSnooper.addStatToSnooper("gl_caps[EXT_vertex_weighting]", contextcapabilities.GL_EXT_vertex_weighting);
      playerSnooper.addStatToSnooper("gl_caps[gl_max_vertex_uniforms]", yh.glGetInteger(35658));
      yh.glGetError();
      playerSnooper.addStatToSnooper("gl_caps[gl_max_fragment_uniforms]", yh.glGetInteger(35657));
      yh.glGetError();
      playerSnooper.addStatToSnooper("gl_caps[gl_max_vertex_attribs]", yh.glGetInteger(34921));
      yh.glGetError();
      playerSnooper.addStatToSnooper("gl_caps[gl_max_vertex_texture_image_units]", yh.glGetInteger(35660));
      yh.glGetError();
      playerSnooper.addStatToSnooper("gl_caps[gl_max_texture_image_units]", yh.glGetInteger(34930));
      yh.glGetError();
      playerSnooper.addStatToSnooper("gl_caps[gl_max_array_texture_layers]", yh.glGetInteger(35071));
      yh.glGetError();
      playerSnooper.addStatToSnooper("gl_max_texture_size", getGLMaximumTextureSize());
      GameProfile gameprofile = this.session.getProfile();
      if (gameprofile != null && gameprofile.getId() != null) {
         playerSnooper.addStatToSnooper("uuid", Hashing.sha1().hashBytes(gameprofile.getId().toString().getBytes(Charsets.ISO_8859_1)).toString());
      }

   }

   public static int getGLMaximumTextureSize() {
      for(int i = 16384; i > 0; i >>= 1) {
         yh.glTexImage2D(32868, 0, 6408, i, i, 0, 6408, 5121, (IntBuffer)null);
         int j = yh.glGetTexLevelParameteri(32868, 0, 4096);
         if (j != 0) {
            return i;
         }
      }

      return -1;
   }

   public boolean isSnooperEnabled() {
      return gameSettings.snooperEnabled;
   }

   public void setServerData(pf serverDataIn) {
      this.currentServerData = serverDataIn;
   }

   @Nullable
   public pf getCurrentServerData() {
      return this.currentServerData;
   }

   public boolean isIntegratedServerRunning() {
      return this.integratedServerIsRunning;
   }

   public boolean isSingleplayer() {
      return this.integratedServerIsRunning && this.integratedServer != null;
   }

   @Nullable
   public WK getIntegratedServer() {
      return this.integratedServer;
   }

   public static void stopIntegratedServer() {
      if (instance != null) {
         WK integratedserver = instance.getIntegratedServer();
         if (integratedserver != null) {
            integratedserver.stopServer();
         }
      }

   }

   public Wm getPlayerUsageSnooper() {
      return this.usageSnooper;
   }

   public static long getSystemTime() {
      return Sys.getTime() * 1000L / Sys.getTimerResolution();
   }

   public boolean isFullScreen() {
      return this.fullscreen;
   }

   public Session getSession() {
      return this.session;
   }

   public PropertyMap getProfileProperties() {
      if (this.profileProperties.isEmpty()) {
         GameProfile gameprofile = this.getSessionService().fillProfileProperties(this.session.getProfile(), false);
         this.profileProperties.putAll(gameprofile.getProperties());
      }

      return this.profileProperties;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public zf getTextureManager() {
      return this.renderEngine;
   }

   public AA getResourceManager() {
      return this.resourceManager;
   }

   public AV getResourcePackRepository() {
      return this.resourcePackRepository;
   }

   public AE getLanguageManager() {
      return this.languageManager;
   }

   public zj getTextureMapBlocks() {
      return this.textureMapBlocks;
   }

   public boolean isJava64bit() {
      return this.jvm64bit;
   }

   public boolean isGamePaused() {
      return this.isGamePaused;
   }

   public iU getSoundHandler() {
      return this.soundHandler;
   }

   public iJ getAmbientMusicType() {
      if (this.currentScreen instanceof lH) {
         return iJ.CREDITS;
      } else if (player == null) {
         return iJ.MENU;
      } else if (player.world.provider instanceof bio) {
         return iJ.NETHER;
      } else if (player.world.provider instanceof bim) {
         return this.ingameGUI.getBossOverlay().shouldPlayEndBossMusic() ? iJ.END_BOSS : iJ.END;
      } else {
         return player.capabilities.isCreativeMode && player.capabilities.allowFlying ? iJ.CREATIVE : iJ.GAME;
      }
   }

   public void dispatchKeypresses() {
      int i = Keyboard.getEventKey() == 0 ? Keyboard.getEventCharacter() + 256 : Keyboard.getEventKey();
      if (i != 0 && !Keyboard.isRepeatEvent() && (!(this.currentScreen instanceof jU) || ((jU)this.currentScreen).time <= getSystemTime() - 20L) && Keyboard.getEventKeyState()) {
         if (i == gameSettings.keyBindFullscreen.getKeyCode()) {
            this.toggleFullscreen();
         } else if (i == gameSettings.keyBindScreenshot.getKeyCode()) {
            this.ingameGUI.getChatGUI().printChatMessage(ScreenShotHelper.saveScreenshot(this.gameDir, this.displayWidth, this.displayHeight, this.framebuffer));
         } else if (i == 48 && lg.isCtrlKeyDown() && (this.currentScreen == null || this.currentScreen != null && !this.currentScreen.isFocused())) {
            gameSettings.setOptionValue(Bi.NARRATOR, 1);
            if (this.currentScreen instanceof mD) {
               ((mD)this.currentScreen).updateNarratorButton();
            }
         }
      }

   }

   public MinecraftSessionService getSessionService() {
      return this.sessionService;
   }

   public Be getSkinManager() {
      return this.skinManager;
   }

   @Nullable
   public Ig getRenderViewEntity() {
      return this.renderViewEntity;
   }

   public void setRenderViewEntity(Ig viewingEntity) {
      this.renderViewEntity = viewingEntity;
      this.entityRenderer.loadEntityShader(viewingEntity);
   }

   public <V> ListenableFuture<V> addScheduledTask(Callable<V> callableToSchedule) {
      Validate.notNull(callableToSchedule);
      if (this.isCallingFromMinecraftThread()) {
         try {
            return Futures.immediateFuture(callableToSchedule.call());
         } catch (Exception var5) {
            Exception exception = var5;
            return Futures.immediateFailedCheckedFuture(exception);
         }
      } else {
         ListenableFutureTask<V> listenablefuturetask = ListenableFutureTask.create(callableToSchedule);
         synchronized(this.scheduledTasks) {
            this.scheduledTasks.add(listenablefuturetask);
            return listenablefuturetask;
         }
      }
   }

   public ListenableFuture<Object> addScheduledTask(Runnable runnableToSchedule) {
      Validate.notNull(runnableToSchedule);
      return this.addScheduledTask(Executors.callable(runnableToSchedule));
   }

   public boolean isCallingFromMinecraftThread() {
      return Thread.currentThread() == this.thread;
   }

   public tJ getBlockRendererDispatcher() {
      return this.blockRenderDispatcher;
   }

   public wC getRenderManager() {
      return this.renderManager;
   }

   public yK getRenderItem() {
      return this.renderItem;
   }

   public yo getItemRenderer() {
      return this.itemRenderer;
   }

   public <T> BH<T> getSearchTree(BU<T> key) {
      return this.searchTreeManager.get(key);
   }

   public static int getDebugFPS() {
      return debugFPS;
   }

   public FrameTimer getFrameTimer() {
      return this.frameTimer;
   }

   public boolean isConnectedToRealms() {
      return this.connectedToRealms;
   }

   public void setConnectedToRealms(boolean isConnected) {
      this.connectedToRealms = isConnected;
   }

   public DataFixer getDataFixer() {
      return this.dataFixer;
   }

   public float getRenderPartialTicks() {
      return this.timer.renderPartialTicks;
   }

   public float getTickLength() {
      return this.timer.elapsedPartialTicks;
   }

   public uy getBlockColors() {
      return this.blockColors;
   }

   public boolean isReducedDebug() {
      return player != null && player.hasReducedDebug() || gameSettings.reducedDebugInfo;
   }

   public nc getToastGui() {
      return this.toastGui;
   }

   public BF getTutorial() {
      return this.tutorial;
   }

   static {
      IS_RUNNING_ON_MAC = Util.getOSType() == Util.EnumOS.OSX;
      memoryReserve = new byte[10485760];
      MAC_DISPLAY_MODES = Lists.newArrayList(new DisplayMode[]{new DisplayMode(2560, 1600), new DisplayMode(2880, 1800)});
   }
}
