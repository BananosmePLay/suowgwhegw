package neo;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.EnumHandSide;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Bj {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final Gson GSON = new Gson();
   private static final Type TYPE_LIST_STRING = new ParameterizedType() {
      public Type[] getActualTypeArguments() {
         return new Type[]{String.class};
      }

      public Type getRawType() {
         return List.class;
      }

      public Type getOwnerType() {
         return null;
      }
   };
   public static final Splitter COLON_SPLITTER = Splitter.on(':');
   private static final String[] GUISCALES = new String[]{"options.guiScale.auto", "options.guiScale.small", "options.guiScale.normal", "options.guiScale.large"};
   private static final String[] PARTICLES = new String[]{"options.particles.all", "options.particles.decreased", "options.particles.minimal"};
   private static final String[] AMBIENT_OCCLUSIONS = new String[]{"options.ao.off", "options.ao.min", "options.ao.max"};
   private static final String[] CLOUDS_TYPES = new String[]{"options.off", "options.clouds.fast", "options.clouds.fancy"};
   private static final String[] ATTACK_INDICATORS = new String[]{"options.off", "options.attack.crosshair", "options.attack.hotbar"};
   public static final String[] NARRATOR_MODES = new String[]{"options.narrator.off", "options.narrator.all", "options.narrator.chat", "options.narrator.system"};
   public float mouseSensitivity = 0.5F;
   public boolean invertMouse;
   public int renderDistanceChunks = -1;
   public boolean viewBobbing = true;
   public boolean anaglyph;
   public boolean fboEnable = true;
   public int limitFramerate = 120;
   public int clouds = 2;
   public boolean fancyGraphics = true;
   public int ambientOcclusion = 2;
   public List<String> resourcePacks = Lists.newArrayList();
   public List<String> incompatibleResourcePacks = Lists.newArrayList();
   public MB chatVisibility;
   public boolean chatColours;
   public boolean chatLinks;
   public boolean chatLinksPrompt;
   public float chatOpacity;
   public boolean snooperEnabled;
   public boolean fullScreen;
   public boolean enableVsync;
   public boolean useVbo;
   public boolean reducedDebugInfo;
   public boolean hideServerAddress;
   public boolean advancedItemTooltips;
   public boolean pauseOnLostFocus;
   private final Set<MH> setModelParts;
   public boolean touchscreen;
   public EnumHandSide mainHand;
   public int overrideWidth;
   public int overrideHeight;
   public boolean heldItemTooltips;
   public float chatScale;
   public float chatWidth;
   public float chatHeightUnfocused;
   public float chatHeightFocused;
   public int mipmapLevels;
   private final Map<SoundCategory, Float> soundLevels;
   public boolean useNativeTransport;
   public boolean entityShadows;
   public int attackIndicator;
   public boolean enableWeakAttacks;
   public boolean showSubtitles;
   public boolean realmsNotifications;
   public boolean autoJump;
   public BG tutorialStep;
   public Bl keyBindForward;
   public Bl keyBindLeft;
   public Bl keyBindBack;
   public Bl keyBindRight;
   public Bl keyBindJump;
   public Bl keyBindSneak;
   public Bl keyBindSprint;
   public Bl keyBindInventory;
   public Bl keyBindSwapHands;
   public Bl keyBindDrop;
   public Bl keyBindUseItem;
   public Bl keyBindAttack;
   public Bl keyBindPickBlock;
   public Bl keyBindChat;
   public Bl keyBindPlayerList;
   public Bl keyBindCommand;
   public Bl keyBindScreenshot;
   public Bl keyBindTogglePerspective;
   public Bl keyBindSmoothCamera;
   public Bl keyBindFullscreen;
   public Bl keyBindSpectatorOutlines;
   public Bl keyBindAdvancements;
   public Bl[] keyBindsHotbar;
   public Bl keyBindSaveToolbar;
   public Bl keyBindLoadToolbar;
   public Bl[] keyBindings;
   protected nC mc;
   private File optionsFile;
   public baV difficulty;
   public boolean hideGUI;
   public int thirdPersonView;
   public boolean showDebugInfo;
   public boolean showDebugProfilerChart;
   public boolean showLagometer;
   public String lastServer;
   public boolean smoothCamera;
   public boolean debugCamEnable;
   public float fovSetting;
   public float gammaSetting;
   public float saturation;
   public int guiScale;
   public int particleSetting;
   public int narrator;
   public String language;
   public boolean forceUnicodeFont;
   public int ofFogType;
   public float ofFogStart;
   public int ofMipmapType;
   public boolean ofOcclusionFancy;
   public boolean ofSmoothFps;
   public boolean ofSmoothWorld;
   public boolean ofLazyChunkLoading;
   public boolean ofRenderRegions;
   public boolean ofSmartAnimations;
   public float ofAoLevel;
   public int ofAaLevel;
   public int ofAfLevel;
   public int ofClouds;
   public float ofCloudsHeight;
   public int ofTrees;
   public int ofRain;
   public int ofDroppedItems;
   public int ofBetterGrass;
   public int ofAutoSaveTicks;
   public boolean ofLagometer;
   public boolean ofProfiler;
   public boolean ofShowFps;
   public boolean ofWeather;
   public boolean ofSky;
   public boolean ofStars;
   public boolean ofSunMoon;
   public int ofVignette;
   public int ofChunkUpdates;
   public boolean ofChunkUpdatesDynamic;
   public int ofTime;
   public boolean ofClearWater;
   public boolean ofBetterSnow;
   public String ofFullscreenMode;
   public boolean ofSwampColors;
   public boolean ofRandomEntities;
   public boolean ofSmoothBiomes;
   public boolean ofCustomFonts;
   public boolean ofCustomColors;
   public boolean ofCustomSky;
   public boolean ofShowCapes;
   public int ofConnectedTextures;
   public boolean ofCustomItems;
   public boolean ofNaturalTextures;
   public boolean ofEmissiveTextures;
   public boolean ofFastMath;
   public boolean ofFastRender;
   public int ofTranslucentBlocks;
   public boolean ofDynamicFov;
   public boolean ofAlternateBlocks;
   public int ofDynamicLights;
   public boolean ofCustomEntityModels;
   public boolean ofCustomGuis;
   public boolean ofShowGlErrors;
   public int ofScreenshotSize;
   public int ofAnimatedWater;
   public int ofAnimatedLava;
   public boolean ofAnimatedFire;
   public boolean ofAnimatedPortal;
   public boolean ofAnimatedRedstone;
   public boolean ofAnimatedExplosion;
   public boolean ofAnimatedFlame;
   public boolean ofAnimatedSmoke;
   public boolean ofVoidParticles;
   public boolean ofWaterParticles;
   public boolean ofRainSplash;
   public boolean ofPortalParticles;
   public boolean ofPotionParticles;
   public boolean ofFireworkParticles;
   public boolean ofDrippingWaterLava;
   public boolean ofAnimatedTerrain;
   public boolean ofAnimatedTextures;
   public static final int DEFAULT = 0;
   public static final int FAST = 1;
   public static final int FANCY = 2;
   public static final int OFF = 3;
   public static final int SMART = 4;
   public static final int ANIM_ON = 0;
   public static final int ANIM_GENERATED = 1;
   public static final int ANIM_OFF = 2;
   public static final String DEFAULT_STR = "Default";
   private static final int[] OF_TREES_VALUES = new int[]{0, 1, 4, 2};
   private static final int[] OF_DYNAMIC_LIGHTS = new int[]{3, 1, 2};
   private static final String[] KEYS_DYNAMIC_LIGHTS = new String[]{"options.off", "options.graphics.fast", "options.graphics.fancy"};
   public Bl ofKeyBindZoom;
   private File optionsFileOF;
   private boolean needsResourceRefresh;

   public Bj(nC mcIn, File mcDataDir) {
      this.chatVisibility = MB.FULL;
      this.chatColours = true;
      this.chatLinks = true;
      this.chatLinksPrompt = true;
      this.chatOpacity = 1.0F;
      this.snooperEnabled = true;
      this.enableVsync = true;
      this.useVbo = true;
      this.pauseOnLostFocus = true;
      this.setModelParts = Sets.newHashSet(MH.values());
      this.mainHand = EnumHandSide.RIGHT;
      this.heldItemTooltips = true;
      this.chatScale = 1.0F;
      this.chatWidth = 1.0F;
      this.chatHeightUnfocused = 0.44366196F;
      this.chatHeightFocused = 1.0F;
      this.mipmapLevels = 4;
      this.soundLevels = Maps.newEnumMap(SoundCategory.class);
      this.useNativeTransport = true;
      this.entityShadows = true;
      this.attackIndicator = 1;
      this.realmsNotifications = true;
      this.autoJump = true;
      this.tutorialStep = BG.MOVEMENT;
      this.keyBindForward = new Bl("key.forward", 17, "key.categories.movement");
      this.keyBindLeft = new Bl("key.left", 30, "key.categories.movement");
      this.keyBindBack = new Bl("key.back", 31, "key.categories.movement");
      this.keyBindRight = new Bl("key.right", 32, "key.categories.movement");
      this.keyBindJump = new Bl("key.jump", 57, "key.categories.movement");
      this.keyBindSneak = new Bl("key.sneak", 42, "key.categories.movement");
      this.keyBindSprint = new Bl("key.sprint", 29, "key.categories.movement");
      this.keyBindInventory = new Bl("key.inventory", 18, "key.categories.inventory");
      this.keyBindSwapHands = new Bl("key.swapHands", 33, "key.categories.inventory");
      this.keyBindDrop = new Bl("key.drop", 16, "key.categories.inventory");
      this.keyBindUseItem = new Bl("key.use", -99, "key.categories.gameplay");
      this.keyBindAttack = new Bl("key.attack", -100, "key.categories.gameplay");
      this.keyBindPickBlock = new Bl("key.pickItem", -98, "key.categories.gameplay");
      this.keyBindChat = new Bl("key.chat", 20, "key.categories.multiplayer");
      this.keyBindPlayerList = new Bl("key.playerlist", 15, "key.categories.multiplayer");
      this.keyBindCommand = new Bl("key.command", 53, "key.categories.multiplayer");
      this.keyBindScreenshot = new Bl("key.screenshot", 60, "key.categories.misc");
      this.keyBindTogglePerspective = new Bl("key.togglePerspective", 63, "key.categories.misc");
      this.keyBindSmoothCamera = new Bl("key.smoothCamera", 0, "key.categories.misc");
      this.keyBindFullscreen = new Bl("key.fullscreen", 87, "key.categories.misc");
      this.keyBindSpectatorOutlines = new Bl("key.spectatorOutlines", 0, "key.categories.misc");
      this.keyBindAdvancements = new Bl("key.advancements", 38, "key.categories.misc");
      this.keyBindsHotbar = new Bl[]{new Bl("key.hotbar.1", 2, "key.categories.inventory"), new Bl("key.hotbar.2", 3, "key.categories.inventory"), new Bl("key.hotbar.3", 4, "key.categories.inventory"), new Bl("key.hotbar.4", 5, "key.categories.inventory"), new Bl("key.hotbar.5", 6, "key.categories.inventory"), new Bl("key.hotbar.6", 7, "key.categories.inventory"), new Bl("key.hotbar.7", 8, "key.categories.inventory"), new Bl("key.hotbar.8", 9, "key.categories.inventory"), new Bl("key.hotbar.9", 10, "key.categories.inventory")};
      this.keyBindSaveToolbar = new Bl("key.saveToolbarActivator", 46, "key.categories.creative");
      this.keyBindLoadToolbar = new Bl("key.loadToolbarActivator", 45, "key.categories.creative");
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofOcclusionFancy = false;
      this.ofSmoothFps = false;
      this.ofSmoothWorld = XH.isSingleProcessor();
      this.ofLazyChunkLoading = XH.isSingleProcessor();
      this.ofRenderRegions = false;
      this.ofSmartAnimations = false;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofAfLevel = 1;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofRain = 0;
      this.ofDroppedItems = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofProfiler = false;
      this.ofShowFps = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomEntities = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofCustomItems = true;
      this.ofNaturalTextures = false;
      this.ofEmissiveTextures = true;
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 0;
      this.ofDynamicFov = true;
      this.ofAlternateBlocks = true;
      this.ofDynamicLights = 3;
      this.ofCustomEntityModels = true;
      this.ofCustomGuis = true;
      this.ofShowGlErrors = true;
      this.ofScreenshotSize = 1;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofFireworkParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedTextures = true;
      this.needsResourceRefresh = false;
      this.setForgeKeybindProperties();
      this.keyBindings = (Bl[])((Bl[])ArrayUtils.addAll(new Bl[]{this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindSprint, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindFullscreen, this.keyBindSpectatorOutlines, this.keyBindSwapHands, this.keyBindSaveToolbar, this.keyBindLoadToolbar, this.keyBindAdvancements}, this.keyBindsHotbar));
      this.difficulty = baV.NORMAL;
      this.lastServer = "";
      this.fovSetting = 70.0F;
      this.language = "en_us";
      this.mc = mcIn;
      this.optionsFile = new File(mcDataDir, "options.txt");
      if (mcIn.isJava64bit() && Runtime.getRuntime().maxMemory() >= 1000000000L) {
         Bi.RENDER_DISTANCE.setValueMax(32.0F);
         long i = 1000000L;
         if (Runtime.getRuntime().maxMemory() >= 1500L * i) {
            Bi.RENDER_DISTANCE.setValueMax(48.0F);
         }

         if (Runtime.getRuntime().maxMemory() >= 2500L * i) {
            Bi.RENDER_DISTANCE.setValueMax(64.0F);
         }
      } else {
         Bi.RENDER_DISTANCE.setValueMax(16.0F);
      }

      this.renderDistanceChunks = mcIn.isJava64bit() ? 12 : 8;
      this.optionsFileOF = new File(mcDataDir, "optionsof.txt");
      this.limitFramerate = (int)Bi.FRAMERATE_LIMIT.getValueMax();
      this.ofKeyBindZoom = new Bl("of.key.zoom", 46, "key.categories.misc");
      this.keyBindings = (Bl[])((Bl[])ArrayUtils.add(this.keyBindings, this.ofKeyBindZoom));
      bqy.fixKeyConflicts(this.keyBindings, new Bl[]{this.ofKeyBindZoom});
      this.renderDistanceChunks = 8;
      this.loadOptions();
      XH.initGameSettings(this);
   }

   public Bj() {
      this.chatVisibility = MB.FULL;
      this.chatColours = true;
      this.chatLinks = true;
      this.chatLinksPrompt = true;
      this.chatOpacity = 1.0F;
      this.snooperEnabled = true;
      this.enableVsync = true;
      this.useVbo = true;
      this.pauseOnLostFocus = true;
      this.setModelParts = Sets.newHashSet(MH.values());
      this.mainHand = EnumHandSide.RIGHT;
      this.heldItemTooltips = true;
      this.chatScale = 1.0F;
      this.chatWidth = 1.0F;
      this.chatHeightUnfocused = 0.44366196F;
      this.chatHeightFocused = 1.0F;
      this.mipmapLevels = 4;
      this.soundLevels = Maps.newEnumMap(SoundCategory.class);
      this.useNativeTransport = true;
      this.entityShadows = true;
      this.attackIndicator = 1;
      this.realmsNotifications = true;
      this.autoJump = true;
      this.tutorialStep = BG.MOVEMENT;
      this.keyBindForward = new Bl("key.forward", 17, "key.categories.movement");
      this.keyBindLeft = new Bl("key.left", 30, "key.categories.movement");
      this.keyBindBack = new Bl("key.back", 31, "key.categories.movement");
      this.keyBindRight = new Bl("key.right", 32, "key.categories.movement");
      this.keyBindJump = new Bl("key.jump", 57, "key.categories.movement");
      this.keyBindSneak = new Bl("key.sneak", 42, "key.categories.movement");
      this.keyBindSprint = new Bl("key.sprint", 29, "key.categories.movement");
      this.keyBindInventory = new Bl("key.inventory", 18, "key.categories.inventory");
      this.keyBindSwapHands = new Bl("key.swapHands", 33, "key.categories.inventory");
      this.keyBindDrop = new Bl("key.drop", 16, "key.categories.inventory");
      this.keyBindUseItem = new Bl("key.use", -99, "key.categories.gameplay");
      this.keyBindAttack = new Bl("key.attack", -100, "key.categories.gameplay");
      this.keyBindPickBlock = new Bl("key.pickItem", -98, "key.categories.gameplay");
      this.keyBindChat = new Bl("key.chat", 20, "key.categories.multiplayer");
      this.keyBindPlayerList = new Bl("key.playerlist", 15, "key.categories.multiplayer");
      this.keyBindCommand = new Bl("key.command", 53, "key.categories.multiplayer");
      this.keyBindScreenshot = new Bl("key.screenshot", 60, "key.categories.misc");
      this.keyBindTogglePerspective = new Bl("key.togglePerspective", 63, "key.categories.misc");
      this.keyBindSmoothCamera = new Bl("key.smoothCamera", 0, "key.categories.misc");
      this.keyBindFullscreen = new Bl("key.fullscreen", 87, "key.categories.misc");
      this.keyBindSpectatorOutlines = new Bl("key.spectatorOutlines", 0, "key.categories.misc");
      this.keyBindAdvancements = new Bl("key.advancements", 38, "key.categories.misc");
      this.keyBindsHotbar = new Bl[]{new Bl("key.hotbar.1", 2, "key.categories.inventory"), new Bl("key.hotbar.2", 3, "key.categories.inventory"), new Bl("key.hotbar.3", 4, "key.categories.inventory"), new Bl("key.hotbar.4", 5, "key.categories.inventory"), new Bl("key.hotbar.5", 6, "key.categories.inventory"), new Bl("key.hotbar.6", 7, "key.categories.inventory"), new Bl("key.hotbar.7", 8, "key.categories.inventory"), new Bl("key.hotbar.8", 9, "key.categories.inventory"), new Bl("key.hotbar.9", 10, "key.categories.inventory")};
      this.keyBindSaveToolbar = new Bl("key.saveToolbarActivator", 46, "key.categories.creative");
      this.keyBindLoadToolbar = new Bl("key.loadToolbarActivator", 45, "key.categories.creative");
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofOcclusionFancy = false;
      this.ofSmoothFps = false;
      this.ofSmoothWorld = XH.isSingleProcessor();
      this.ofLazyChunkLoading = XH.isSingleProcessor();
      this.ofRenderRegions = false;
      this.ofSmartAnimations = false;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofAfLevel = 1;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofRain = 0;
      this.ofDroppedItems = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofProfiler = false;
      this.ofShowFps = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomEntities = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofCustomItems = true;
      this.ofNaturalTextures = false;
      this.ofEmissiveTextures = true;
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 0;
      this.ofDynamicFov = true;
      this.ofAlternateBlocks = true;
      this.ofDynamicLights = 3;
      this.ofCustomEntityModels = true;
      this.ofCustomGuis = true;
      this.ofShowGlErrors = true;
      this.ofScreenshotSize = 1;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofFireworkParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedTextures = true;
      this.needsResourceRefresh = false;
      this.setForgeKeybindProperties();
      this.keyBindings = (Bl[])((Bl[])ArrayUtils.addAll(new Bl[]{this.keyBindAttack, this.keyBindUseItem, this.keyBindForward, this.keyBindLeft, this.keyBindBack, this.keyBindRight, this.keyBindJump, this.keyBindSneak, this.keyBindSprint, this.keyBindDrop, this.keyBindInventory, this.keyBindChat, this.keyBindPlayerList, this.keyBindPickBlock, this.keyBindCommand, this.keyBindScreenshot, this.keyBindTogglePerspective, this.keyBindSmoothCamera, this.keyBindFullscreen, this.keyBindSpectatorOutlines, this.keyBindSwapHands, this.keyBindSaveToolbar, this.keyBindLoadToolbar, this.keyBindAdvancements}, this.keyBindsHotbar));
      this.difficulty = baV.NORMAL;
      this.lastServer = "";
      this.fovSetting = 70.0F;
      this.language = "en_us";
   }

   public static String getKeyDisplayString(int key) {
      if (key < 0) {
         switch (key) {
            case -100:
               return Ax.format("key.mouse.left");
            case -99:
               return Ax.format("key.mouse.right");
            case -98:
               return Ax.format("key.mouse.middle");
            default:
               return Ax.format("key.mouseButton", key + 101);
         }
      } else {
         return key < 256 ? Keyboard.getKeyName(key) : String.format("%c", (char)(key - 256)).toUpperCase();
      }
   }

   public static boolean isKeyDown(Bl key) {
      int i = key.getKeyCode();
      if (i != 0 && i < 256) {
         return i < 0 ? Mouse.isButtonDown(i + 100) : Keyboard.isKeyDown(i);
      } else {
         return false;
      }
   }

   public void setOptionKeyBinding(Bl key, int keyCode) {
      key.setKeyCode(keyCode);
      this.saveOptions();
   }

   public void setOptionFloatValue(Bi settingsOption, float value) {
      this.setOptionFloatValueOF(settingsOption, value);
      if (settingsOption == Bi.SENSITIVITY) {
         this.mouseSensitivity = value;
      }

      if (settingsOption == Bi.FOV) {
         this.fovSetting = value;
      }

      if (settingsOption == Bi.GAMMA) {
         this.gammaSetting = value;
      }

      if (settingsOption == Bi.FRAMERATE_LIMIT) {
         this.limitFramerate = (int)value;
         this.enableVsync = false;
         if (this.limitFramerate <= 0) {
            this.limitFramerate = (int)Bi.FRAMERATE_LIMIT.getValueMax();
            this.enableVsync = true;
         }

         this.updateVSync();
      }

      if (settingsOption == Bi.CHAT_OPACITY) {
         this.chatOpacity = value;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (settingsOption == Bi.CHAT_HEIGHT_FOCUSED) {
         this.chatHeightFocused = value;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (settingsOption == Bi.CHAT_HEIGHT_UNFOCUSED) {
         this.chatHeightUnfocused = value;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (settingsOption == Bi.CHAT_WIDTH) {
         this.chatWidth = value;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (settingsOption == Bi.CHAT_SCALE) {
         this.chatScale = value;
         this.mc.ingameGUI.getChatGUI().refreshChat();
      }

      if (settingsOption == Bi.MIPMAP_LEVELS) {
         int i = this.mipmapLevels;
         this.mipmapLevels = (int)value;
         if ((float)i != value) {
            this.mc.getTextureMapBlocks().setMipmapLevels(this.mipmapLevels);
            this.mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
            this.mc.getTextureMapBlocks().setBlurMipmapDirect(false, this.mipmapLevels > 0);
            this.mc.scheduleResourcesRefresh();
         }
      }

      if (settingsOption == Bi.RENDER_DISTANCE) {
         this.renderDistanceChunks = (int)value;
         this.mc.renderGlobal.setDisplayListEntitiesDirty();
      }

   }

   public void setOptionValue(Bi settingsOption, int value) {
      this.setOptionValueOF(settingsOption, value);
      if (settingsOption == Bi.RENDER_DISTANCE) {
         this.setOptionFloatValue(settingsOption, MathHelper.clamp((float)(this.renderDistanceChunks + value), settingsOption.getValueMin(), settingsOption.getValueMax()));
      }

      if (settingsOption == Bi.MAIN_HAND) {
         this.mainHand = this.mainHand.opposite();
      }

      if (settingsOption == Bi.INVERT_MOUSE) {
         this.invertMouse = !this.invertMouse;
      }

      if (settingsOption == Bi.GUI_SCALE) {
         this.guiScale += value;
         if (lg.isShiftKeyDown()) {
            this.guiScale = 0;
         }

         DisplayMode displaymode = XH.getLargestDisplayMode();
         int i = displaymode.getWidth() / 320;
         int j = displaymode.getHeight() / 240;
         int k = Math.min(i, j);
         if (this.guiScale < 0) {
            this.guiScale = k - 1;
         }

         if (this.mc.isUnicode() && this.guiScale % 2 != 0) {
            this.guiScale += value;
         }

         if (this.guiScale < 0 || this.guiScale >= k) {
            this.guiScale = 0;
         }
      }

      if (settingsOption == Bi.PARTICLES) {
         this.particleSetting = (this.particleSetting + value) % 3;
      }

      if (settingsOption == Bi.VIEW_BOBBING) {
         this.viewBobbing = !this.viewBobbing;
      }

      if (settingsOption == Bi.RENDER_CLOUDS) {
         this.clouds = (this.clouds + value) % 3;
      }

      if (settingsOption == Bi.FORCE_UNICODE_FONT) {
         this.forceUnicodeFont = !this.forceUnicodeFont;
         this.mc.fontRenderer.setUnicodeFlag(this.mc.getLanguageManager().isCurrentLocaleUnicode() || this.forceUnicodeFont);
      }

      if (settingsOption == Bi.FBO_ENABLE) {
         this.fboEnable = !this.fboEnable;
      }

      if (settingsOption == Bi.ANAGLYPH) {
         if (!this.anaglyph && XH.isShaders()) {
            XH.showGuiMessage(bmW.get("of.message.an.shaders1"), bmW.get("of.message.an.shaders2"));
            return;
         }

         this.anaglyph = !this.anaglyph;
         this.mc.refreshResources();
         if (bnK.FMLClientHandler_refreshResources.exists()) {
            Object object = bnK.call(bnK.FMLClientHandler_instance);
            biA iresourcetype = (biA)bnK.VanillaResourceType_TEXTURES.getValue();
            bnK.call(object, bnK.FMLClientHandler_refreshResources, iresourcetype);
         }
      }

      if (settingsOption == Bi.GRAPHICS) {
         this.fancyGraphics = !this.fancyGraphics;
         this.updateRenderClouds();
         this.mc.renderGlobal.loadRenderers();
      }

      if (settingsOption == Bi.AMBIENT_OCCLUSION) {
         this.ambientOcclusion = (this.ambientOcclusion + value) % 3;
         this.mc.renderGlobal.loadRenderers();
      }

      if (settingsOption == Bi.CHAT_VISIBILITY) {
         this.chatVisibility = MB.getEnumChatVisibility((this.chatVisibility.getChatVisibility() + value) % 3);
      }

      if (settingsOption == Bi.CHAT_COLOR) {
         this.chatColours = !this.chatColours;
      }

      if (settingsOption == Bi.CHAT_LINKS) {
         this.chatLinks = !this.chatLinks;
      }

      if (settingsOption == Bi.CHAT_LINKS_PROMPT) {
         this.chatLinksPrompt = !this.chatLinksPrompt;
      }

      if (settingsOption == Bi.SNOOPER_ENABLED) {
         this.snooperEnabled = !this.snooperEnabled;
      }

      if (settingsOption == Bi.TOUCHSCREEN) {
         this.touchscreen = !this.touchscreen;
      }

      if (settingsOption == Bi.USE_FULLSCREEN) {
         this.fullScreen = !this.fullScreen;
         if (this.mc.isFullScreen() != this.fullScreen) {
            this.mc.toggleFullscreen();
         }
      }

      if (settingsOption == Bi.ENABLE_VSYNC) {
         this.enableVsync = !this.enableVsync;
         Display.setVSyncEnabled(this.enableVsync);
      }

      if (settingsOption == Bi.USE_VBO) {
         this.useVbo = !this.useVbo;
         this.mc.renderGlobal.loadRenderers();
      }

      if (settingsOption == Bi.REDUCED_DEBUG_INFO) {
         this.reducedDebugInfo = !this.reducedDebugInfo;
      }

      if (settingsOption == Bi.ENTITY_SHADOWS) {
         this.entityShadows = !this.entityShadows;
      }

      if (settingsOption == Bi.ATTACK_INDICATOR) {
         this.attackIndicator = (this.attackIndicator + value) % 3;
      }

      if (settingsOption == Bi.SHOW_SUBTITLES) {
         this.showSubtitles = !this.showSubtitles;
      }

      if (settingsOption == Bi.REALMS_NOTIFICATIONS) {
         this.realmsNotifications = !this.realmsNotifications;
      }

      if (settingsOption == Bi.AUTO_JUMP) {
         this.autoJump = !this.autoJump;
      }

      if (settingsOption == Bi.NARRATOR) {
         if (jC.INSTANCE.isActive()) {
            this.narrator = (this.narrator + value) % NARRATOR_MODES.length;
         } else {
            this.narrator = 0;
         }

         jC.INSTANCE.announceMode(this.narrator);
      }

      this.saveOptions();
   }

   public float getOptionFloatValue(Bi settingOption) {
      float f = this.getOptionFloatValueOF(settingOption);
      if (f != Float.MAX_VALUE) {
         return f;
      } else if (settingOption == Bi.FOV) {
         return this.fovSetting;
      } else if (settingOption == Bi.GAMMA) {
         return this.gammaSetting;
      } else if (settingOption == Bi.SATURATION) {
         return this.saturation;
      } else if (settingOption == Bi.SENSITIVITY) {
         return this.mouseSensitivity;
      } else if (settingOption == Bi.CHAT_OPACITY) {
         return this.chatOpacity;
      } else if (settingOption == Bi.CHAT_HEIGHT_FOCUSED) {
         return this.chatHeightFocused;
      } else if (settingOption == Bi.CHAT_HEIGHT_UNFOCUSED) {
         return this.chatHeightUnfocused;
      } else if (settingOption == Bi.CHAT_SCALE) {
         return this.chatScale;
      } else if (settingOption == Bi.CHAT_WIDTH) {
         return this.chatWidth;
      } else if (settingOption == Bi.FRAMERATE_LIMIT) {
         return (float)this.limitFramerate;
      } else if (settingOption == Bi.MIPMAP_LEVELS) {
         return (float)this.mipmapLevels;
      } else {
         return settingOption == Bi.RENDER_DISTANCE ? (float)this.renderDistanceChunks : 0.0F;
      }
   }

   public boolean getOptionOrdinalValue(Bi settingOption) {
      switch (settingOption) {
         case INVERT_MOUSE:
            return this.invertMouse;
         case VIEW_BOBBING:
            return this.viewBobbing;
         case ANAGLYPH:
            return this.anaglyph;
         case FBO_ENABLE:
            return this.fboEnable;
         case CHAT_COLOR:
            return this.chatColours;
         case CHAT_LINKS:
            return this.chatLinks;
         case CHAT_LINKS_PROMPT:
            return this.chatLinksPrompt;
         case SNOOPER_ENABLED:
            return this.snooperEnabled;
         case USE_FULLSCREEN:
            return this.fullScreen;
         case ENABLE_VSYNC:
            return this.enableVsync;
         case USE_VBO:
            return this.useVbo;
         case TOUCHSCREEN:
            return this.touchscreen;
         case FORCE_UNICODE_FONT:
            return this.forceUnicodeFont;
         case REDUCED_DEBUG_INFO:
            return this.reducedDebugInfo;
         case ENTITY_SHADOWS:
            return this.entityShadows;
         case SHOW_SUBTITLES:
            return this.showSubtitles;
         case REALMS_NOTIFICATIONS:
            return this.realmsNotifications;
         case ENABLE_WEAK_ATTACKS:
            return this.enableWeakAttacks;
         case AUTO_JUMP:
            return this.autoJump;
         default:
            return false;
      }
   }

   private static String getTranslation(String[] strArray, int index) {
      if (index < 0 || index >= strArray.length) {
         index = 0;
      }

      return Ax.format(strArray[index]);
   }

   public String getKeyBinding(Bi settingOption) {
      String s = this.getKeyBindingOF(settingOption);
      if (s != null) {
         return s;
      } else {
         String s1 = Ax.format(settingOption.getTranslation()) + ": ";
         if (settingOption.isFloat()) {
            float f1 = this.getOptionFloatValue(settingOption);
            float f = settingOption.normalizeValue(f1);
            if (settingOption == Bi.SENSITIVITY) {
               if (f == 0.0F) {
                  return s1 + Ax.format("options.sensitivity.min");
               } else {
                  return f == 1.0F ? s1 + Ax.format("options.sensitivity.max") : s1 + (int)(f * 200.0F) + "%";
               }
            } else if (settingOption == Bi.FOV) {
               if (f1 == 70.0F) {
                  return s1 + Ax.format("options.fov.min");
               } else {
                  return f1 == 110.0F ? s1 + Ax.format("options.fov.max") : s1 + (int)f1;
               }
            } else if (settingOption == Bi.FRAMERATE_LIMIT) {
               return f1 == Bi.access$000(settingOption) ? s1 + Ax.format("options.framerateLimit.max") : s1 + Ax.format("options.framerate", (int)f1);
            } else if (settingOption == Bi.RENDER_CLOUDS) {
               return f1 == Bi.access$100(settingOption) ? s1 + Ax.format("options.cloudHeight.min") : s1 + ((int)f1 + 128);
            } else if (settingOption == Bi.GAMMA) {
               if (f == 0.0F) {
                  return s1 + Ax.format("options.gamma.min");
               } else {
                  return f == 1.0F ? s1 + Ax.format("options.gamma.max") : s1 + "+" + (int)(f * 100.0F) + "%";
               }
            } else if (settingOption == Bi.SATURATION) {
               return s1 + (int)(f * 400.0F) + "%";
            } else if (settingOption == Bi.CHAT_OPACITY) {
               return s1 + (int)(f * 90.0F + 10.0F) + "%";
            } else if (settingOption == Bi.CHAT_HEIGHT_UNFOCUSED) {
               return s1 + kJ.calculateChatboxHeight(f) + "px";
            } else if (settingOption == Bi.CHAT_HEIGHT_FOCUSED) {
               return s1 + kJ.calculateChatboxHeight(f) + "px";
            } else if (settingOption == Bi.CHAT_WIDTH) {
               return s1 + kJ.calculateChatboxWidth(f) + "px";
            } else if (settingOption == Bi.RENDER_DISTANCE) {
               return s1 + Ax.format("options.chunks", (int)f1);
            } else if (settingOption == Bi.MIPMAP_LEVELS) {
               if ((double)f1 >= 4.0) {
                  return s1 + bmW.get("of.general.max");
               } else {
                  return f1 == 0.0F ? s1 + Ax.format("options.off") : s1 + (int)f1;
               }
            } else {
               return f == 0.0F ? s1 + Ax.format("options.off") : s1 + (int)(f * 100.0F) + "%";
            }
         } else if (settingOption.isBoolean()) {
            boolean flag = this.getOptionOrdinalValue(settingOption);
            return flag ? s1 + Ax.format("options.on") : s1 + Ax.format("options.off");
         } else if (settingOption == Bi.MAIN_HAND) {
            return s1 + this.mainHand;
         } else if (settingOption == Bi.GUI_SCALE) {
            return this.guiScale >= GUISCALES.length ? s1 + this.guiScale + "x" : s1 + getTranslation(GUISCALES, this.guiScale);
         } else if (settingOption == Bi.CHAT_VISIBILITY) {
            return s1 + Ax.format(this.chatVisibility.getResourceKey());
         } else if (settingOption == Bi.PARTICLES) {
            return s1 + getTranslation(PARTICLES, this.particleSetting);
         } else if (settingOption == Bi.AMBIENT_OCCLUSION) {
            return s1 + getTranslation(AMBIENT_OCCLUSIONS, this.ambientOcclusion);
         } else if (settingOption == Bi.RENDER_CLOUDS) {
            return s1 + getTranslation(CLOUDS_TYPES, this.clouds);
         } else if (settingOption == Bi.GRAPHICS) {
            if (this.fancyGraphics) {
               return s1 + Ax.format("options.graphics.fancy");
            } else {
               String s2 = "options.graphics.fast";
               return s1 + Ax.format("options.graphics.fast");
            }
         } else if (settingOption == Bi.ATTACK_INDICATOR) {
            return s1 + getTranslation(ATTACK_INDICATORS, this.attackIndicator);
         } else if (settingOption == Bi.NARRATOR) {
            return jC.INSTANCE.isActive() ? s1 + getTranslation(NARRATOR_MODES, this.narrator) : s1 + Ax.format("options.narrator.notavailable");
         } else {
            return s1;
         }
      }
   }

   public void loadOptions() {
      FileInputStream fileinputstream = null;

      try {
         if (!this.optionsFile.exists()) {
            return;
         }

         this.soundLevels.clear();
         List<String> list = IOUtils.readLines(fileinputstream = new FileInputStream(this.optionsFile), StandardCharsets.UTF_8);
         QQ nbttagcompound = new QQ();
         Iterator var4 = list.iterator();

         String s1;
         while(var4.hasNext()) {
            s1 = (String)var4.next();

            try {
               Iterator<String> iterator = COLON_SPLITTER.omitEmptyStrings().limit(2).split(s1).iterator();
               nbttagcompound.setString((String)iterator.next(), (String)iterator.next());
            } catch (Exception var18) {
               LOGGER.warn("Skipping bad option: {}", s1);
            }
         }

         nbttagcompound = this.dataFix(nbttagcompound);
         var4 = nbttagcompound.getKeySet().iterator();

         while(var4.hasNext()) {
            s1 = (String)var4.next();
            String s2 = nbttagcompound.getString(s1);

            try {
               if ("mouseSensitivity".equals(s1)) {
                  this.mouseSensitivity = this.parseFloat(s2);
               }

               if ("fov".equals(s1)) {
                  this.fovSetting = this.parseFloat(s2) * 40.0F + 70.0F;
               }

               if ("gamma".equals(s1)) {
                  this.gammaSetting = this.parseFloat(s2);
               }

               if ("saturation".equals(s1)) {
                  this.saturation = this.parseFloat(s2);
               }

               if ("invertYMouse".equals(s1)) {
                  this.invertMouse = "true".equals(s2);
               }

               if ("renderDistance".equals(s1)) {
                  this.renderDistanceChunks = Integer.parseInt(s2);
               }

               if ("guiScale".equals(s1)) {
                  this.guiScale = Integer.parseInt(s2);
               }

               if ("particles".equals(s1)) {
                  this.particleSetting = Integer.parseInt(s2);
               }

               if ("bobView".equals(s1)) {
                  this.viewBobbing = "true".equals(s2);
               }

               if ("anaglyph3d".equals(s1)) {
                  this.anaglyph = "true".equals(s2);
               }

               if ("maxFps".equals(s1)) {
                  this.limitFramerate = Integer.parseInt(s2);
                  if (this.enableVsync) {
                     this.limitFramerate = (int)Bi.FRAMERATE_LIMIT.getValueMax();
                  }

                  if (this.limitFramerate <= 0) {
                     this.limitFramerate = (int)Bi.FRAMERATE_LIMIT.getValueMax();
                  }
               }

               if ("fboEnable".equals(s1)) {
                  this.fboEnable = "true".equals(s2);
               }

               if ("difficulty".equals(s1)) {
                  this.difficulty = baV.byId(Integer.parseInt(s2));
               }

               if ("fancyGraphics".equals(s1)) {
                  this.fancyGraphics = "true".equals(s2);
                  this.updateRenderClouds();
               }

               if ("tutorialStep".equals(s1)) {
                  this.tutorialStep = BG.getTutorial(s2);
               }

               if ("ao".equals(s1)) {
                  if ("true".equals(s2)) {
                     this.ambientOcclusion = 2;
                  } else if ("false".equals(s2)) {
                     this.ambientOcclusion = 0;
                  } else {
                     this.ambientOcclusion = Integer.parseInt(s2);
                  }
               }

               if ("renderClouds".equals(s1)) {
                  if ("true".equals(s2)) {
                     this.clouds = 2;
                  } else if ("false".equals(s2)) {
                     this.clouds = 0;
                  } else if ("fast".equals(s2)) {
                     this.clouds = 1;
                  }
               }

               if ("attackIndicator".equals(s1)) {
                  if ("0".equals(s2)) {
                     this.attackIndicator = 0;
                  } else if ("1".equals(s2)) {
                     this.attackIndicator = 1;
                  } else if ("2".equals(s2)) {
                     this.attackIndicator = 2;
                  }
               }

               if ("resourcePacks".equals(s1)) {
                  this.resourcePacks = (List)JsonUtils.gsonDeserialize(GSON, s2, TYPE_LIST_STRING);
                  if (this.resourcePacks == null) {
                     this.resourcePacks = Lists.newArrayList();
                  }
               }

               if ("incompatibleResourcePacks".equals(s1)) {
                  this.incompatibleResourcePacks = (List)JsonUtils.gsonDeserialize(GSON, s2, TYPE_LIST_STRING);
                  if (this.incompatibleResourcePacks == null) {
                     this.incompatibleResourcePacks = Lists.newArrayList();
                  }
               }

               if ("lastServer".equals(s1)) {
                  this.lastServer = s2;
               }

               if ("lang".equals(s1)) {
                  this.language = s2;
               }

               if ("chatVisibility".equals(s1)) {
                  this.chatVisibility = MB.getEnumChatVisibility(Integer.parseInt(s2));
               }

               if ("chatColors".equals(s1)) {
                  this.chatColours = "true".equals(s2);
               }

               if ("chatLinks".equals(s1)) {
                  this.chatLinks = "true".equals(s2);
               }

               if ("chatLinksPrompt".equals(s1)) {
                  this.chatLinksPrompt = "true".equals(s2);
               }

               if ("chatOpacity".equals(s1)) {
                  this.chatOpacity = this.parseFloat(s2);
               }

               if ("snooperEnabled".equals(s1)) {
                  this.snooperEnabled = "true".equals(s2);
               }

               if ("fullscreen".equals(s1)) {
                  this.fullScreen = "true".equals(s2);
               }

               if ("enableVsync".equals(s1)) {
                  this.enableVsync = "true".equals(s2);
                  if (this.enableVsync) {
                     this.limitFramerate = (int)Bi.FRAMERATE_LIMIT.getValueMax();
                  }

                  this.updateVSync();
               }

               if ("useVbo".equals(s1)) {
                  this.useVbo = "true".equals(s2);
               }

               if ("hideServerAddress".equals(s1)) {
                  this.hideServerAddress = "true".equals(s2);
               }

               if ("advancedItemTooltips".equals(s1)) {
                  this.advancedItemTooltips = "true".equals(s2);
               }

               if ("pauseOnLostFocus".equals(s1)) {
                  this.pauseOnLostFocus = "true".equals(s2);
               }

               if ("touchscreen".equals(s1)) {
                  this.touchscreen = "true".equals(s2);
               }

               if ("overrideHeight".equals(s1)) {
                  this.overrideHeight = Integer.parseInt(s2);
               }

               if ("overrideWidth".equals(s1)) {
                  this.overrideWidth = Integer.parseInt(s2);
               }

               if ("heldItemTooltips".equals(s1)) {
                  this.heldItemTooltips = "true".equals(s2);
               }

               if ("chatHeightFocused".equals(s1)) {
                  this.chatHeightFocused = this.parseFloat(s2);
               }

               if ("chatHeightUnfocused".equals(s1)) {
                  this.chatHeightUnfocused = this.parseFloat(s2);
               }

               if ("chatScale".equals(s1)) {
                  this.chatScale = this.parseFloat(s2);
               }

               if ("chatWidth".equals(s1)) {
                  this.chatWidth = this.parseFloat(s2);
               }

               if ("mipmapLevels".equals(s1)) {
                  this.mipmapLevels = Integer.parseInt(s2);
               }

               if ("forceUnicodeFont".equals(s1)) {
                  this.forceUnicodeFont = "true".equals(s2);
               }

               if ("reducedDebugInfo".equals(s1)) {
                  this.reducedDebugInfo = "true".equals(s2);
               }

               if ("useNativeTransport".equals(s1)) {
                  this.useNativeTransport = "true".equals(s2);
               }

               if ("entityShadows".equals(s1)) {
                  this.entityShadows = "true".equals(s2);
               }

               if ("mainHand".equals(s1)) {
                  this.mainHand = "left".equals(s2) ? EnumHandSide.LEFT : EnumHandSide.RIGHT;
               }

               if ("showSubtitles".equals(s1)) {
                  this.showSubtitles = "true".equals(s2);
               }

               if ("realmsNotifications".equals(s1)) {
                  this.realmsNotifications = "true".equals(s2);
               }

               if ("enableWeakAttacks".equals(s1)) {
                  this.enableWeakAttacks = "true".equals(s2);
               }

               if ("autoJump".equals(s1)) {
                  this.autoJump = "true".equals(s2);
               }

               if ("narrator".equals(s1)) {
                  this.narrator = Integer.parseInt(s2);
               }

               Bl[] var24 = this.keyBindings;
               int var8 = var24.length;

               int var9;
               for(var9 = 0; var9 < var8; ++var9) {
                  Bl keybinding = var24[var9];
                  if (s1.equals("key_" + keybinding.getKeyDescription())) {
                     if (bnK.KeyModifier_valueFromString.exists()) {
                        if (s2.indexOf(58) != -1) {
                           String[] astring = s2.split(":");
                           Object object = bnK.call(bnK.KeyModifier_valueFromString, astring[1]);
                           bnK.call(keybinding, bnK.ForgeKeyBinding_setKeyModifierAndCode, object, Integer.parseInt(astring[0]));
                        } else {
                           Object object1 = bnK.getFieldValue(bnK.KeyModifier_NONE);
                           bnK.call(keybinding, bnK.ForgeKeyBinding_setKeyModifierAndCode, object1, Integer.parseInt(s2));
                        }
                     } else {
                        keybinding.setKeyCode(Integer.parseInt(s2));
                     }
                  }
               }

               SoundCategory[] var25 = SoundCategory.values();
               var8 = var25.length;

               for(var9 = 0; var9 < var8; ++var9) {
                  SoundCategory soundcategory = var25[var9];
                  if (s1.equals("soundCategory_" + soundcategory.getName())) {
                     this.soundLevels.put(soundcategory, this.parseFloat(s2));
                  }
               }

               MH[] var26 = MH.values();
               var8 = var26.length;

               for(var9 = 0; var9 < var8; ++var9) {
                  MH enumplayermodelparts = var26[var9];
                  if (s1.equals("modelPart_" + enumplayermodelparts.getPartName())) {
                     this.setModelPartEnabled(enumplayermodelparts, "true".equals(s2));
                  }
               }
            } catch (Exception var19) {
               Exception exception1 = var19;
               LOGGER.warn("Skipping bad option: {}:{}", s1, s2);
               exception1.printStackTrace();
            }
         }

         Bl.resetKeyBindingArrayAndHash();
      } catch (Exception var20) {
         Exception exception1 = var20;
         LOGGER.error("Failed to load options", exception1);
      } finally {
         IOUtils.closeQuietly(fileinputstream);
      }

      this.loadOfOptions();
   }

   private QQ dataFix(QQ p_189988_1_) {
      int i = 0;

      try {
         i = Integer.parseInt(p_189988_1_.getString("version"));
      } catch (RuntimeException var4) {
      }

      return this.mc.getDataFixer().process(FixTypes.OPTIONS, p_189988_1_, i);
   }

   private float parseFloat(String str) {
      if ("true".equals(str)) {
         return 1.0F;
      } else {
         return "false".equals(str) ? 0.0F : Float.parseFloat(str);
      }
   }

   public void saveOptions() {
      if (bnK.FMLClientHandler.exists()) {
         Object object = bnK.call(bnK.FMLClientHandler_instance);
         if (object != null && bnK.callBoolean(object, bnK.FMLClientHandler_isLoading)) {
            return;
         }
      }

      PrintWriter printwriter = null;

      try {
         printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.optionsFile), StandardCharsets.UTF_8));
         printwriter.println("version:1343");
         printwriter.println("invertYMouse:" + this.invertMouse);
         printwriter.println("mouseSensitivity:" + this.mouseSensitivity);
         printwriter.println("fov:" + (this.fovSetting - 70.0F) / 40.0F);
         printwriter.println("gamma:" + this.gammaSetting);
         printwriter.println("saturation:" + this.saturation);
         printwriter.println("renderDistance:" + this.renderDistanceChunks);
         printwriter.println("guiScale:" + this.guiScale);
         printwriter.println("particles:" + this.particleSetting);
         printwriter.println("bobView:" + this.viewBobbing);
         printwriter.println("anaglyph3d:" + this.anaglyph);
         printwriter.println("maxFps:" + this.limitFramerate);
         printwriter.println("fboEnable:" + this.fboEnable);
         printwriter.println("difficulty:" + this.difficulty.getId());
         printwriter.println("fancyGraphics:" + this.fancyGraphics);
         printwriter.println("ao:" + this.ambientOcclusion);
         switch (this.clouds) {
            case 0:
               printwriter.println("renderClouds:false");
               break;
            case 1:
               printwriter.println("renderClouds:fast");
               break;
            case 2:
               printwriter.println("renderClouds:true");
         }

         printwriter.println("resourcePacks:" + GSON.toJson(this.resourcePacks));
         printwriter.println("incompatibleResourcePacks:" + GSON.toJson(this.incompatibleResourcePacks));
         printwriter.println("lastServer:" + this.lastServer);
         printwriter.println("lang:" + this.language);
         printwriter.println("chatVisibility:" + this.chatVisibility.getChatVisibility());
         printwriter.println("chatColors:" + this.chatColours);
         printwriter.println("chatLinks:" + this.chatLinks);
         printwriter.println("chatLinksPrompt:" + this.chatLinksPrompt);
         printwriter.println("chatOpacity:" + this.chatOpacity);
         printwriter.println("snooperEnabled:" + this.snooperEnabled);
         printwriter.println("fullscreen:" + this.fullScreen);
         printwriter.println("enableVsync:" + this.enableVsync);
         printwriter.println("useVbo:" + this.useVbo);
         printwriter.println("hideServerAddress:" + this.hideServerAddress);
         printwriter.println("advancedItemTooltips:" + this.advancedItemTooltips);
         printwriter.println("pauseOnLostFocus:" + this.pauseOnLostFocus);
         printwriter.println("touchscreen:" + this.touchscreen);
         printwriter.println("overrideWidth:" + this.overrideWidth);
         printwriter.println("overrideHeight:" + this.overrideHeight);
         printwriter.println("heldItemTooltips:" + this.heldItemTooltips);
         printwriter.println("chatHeightFocused:" + this.chatHeightFocused);
         printwriter.println("chatHeightUnfocused:" + this.chatHeightUnfocused);
         printwriter.println("chatScale:" + this.chatScale);
         printwriter.println("chatWidth:" + this.chatWidth);
         printwriter.println("mipmapLevels:" + this.mipmapLevels);
         printwriter.println("forceUnicodeFont:" + this.forceUnicodeFont);
         printwriter.println("reducedDebugInfo:" + this.reducedDebugInfo);
         printwriter.println("useNativeTransport:" + this.useNativeTransport);
         printwriter.println("entityShadows:" + this.entityShadows);
         printwriter.println("mainHand:" + (this.mainHand == EnumHandSide.LEFT ? "left" : "right"));
         printwriter.println("attackIndicator:" + this.attackIndicator);
         printwriter.println("showSubtitles:" + this.showSubtitles);
         printwriter.println("realmsNotifications:" + this.realmsNotifications);
         printwriter.println("enableWeakAttacks:" + this.enableWeakAttacks);
         printwriter.println("autoJump:" + this.autoJump);
         printwriter.println("narrator:" + this.narrator);
         printwriter.println("tutorialStep:" + this.tutorialStep.getName());
         Bl[] var15 = this.keyBindings;
         int var3 = var15.length;

         int var4;
         for(var4 = 0; var4 < var3; ++var4) {
            Bl keybinding = var15[var4];
            if (bnK.ForgeKeyBinding_getKeyModifier.exists()) {
               String s = "key_" + keybinding.getKeyDescription() + ":" + keybinding.getKeyCode();
               Object object1 = bnK.call(keybinding, bnK.ForgeKeyBinding_getKeyModifier);
               Object object2 = bnK.getFieldValue(bnK.KeyModifier_NONE);
               printwriter.println(object1 != object2 ? s + ":" + object1 : s);
            } else {
               printwriter.println("key_" + keybinding.getKeyDescription() + ":" + keybinding.getKeyCode());
            }
         }

         SoundCategory[] var16 = SoundCategory.values();
         var3 = var16.length;

         for(var4 = 0; var4 < var3; ++var4) {
            SoundCategory soundcategory = var16[var4];
            printwriter.println("soundCategory_" + soundcategory.getName() + ":" + this.getSoundLevel(soundcategory));
         }

         MH[] var17 = MH.values();
         var3 = var17.length;

         for(var4 = 0; var4 < var3; ++var4) {
            MH enumplayermodelparts = var17[var4];
            printwriter.println("modelPart_" + enumplayermodelparts.getPartName() + ":" + this.setModelParts.contains(enumplayermodelparts));
         }
      } catch (Exception var12) {
         Exception exception = var12;
         LOGGER.error("Failed to save options", exception);
      } finally {
         IOUtils.closeQuietly(printwriter);
      }

      this.saveOfOptions();
      this.sendSettingsToServer();
   }

   public float getSoundLevel(SoundCategory category) {
      return this.soundLevels.containsKey(category) ? (Float)this.soundLevels.get(category) : 1.0F;
   }

   public void setSoundLevel(SoundCategory category, float volume) {
      this.mc.getSoundHandler().setSoundLevel(category, volume);
      this.soundLevels.put(category, volume);
   }

   public void sendSettingsToServer() {
      nC var10000 = this.mc;
      if (nC.player != null) {
         int i = 0;

         MH enumplayermodelparts;
         for(Iterator var2 = this.setModelParts.iterator(); var2.hasNext(); i |= enumplayermodelparts.getPartMask()) {
            enumplayermodelparts = (MH)var2.next();
         }

         var10000 = this.mc;
         nC.player.connection.sendPacket(new SG(this.language, this.renderDistanceChunks, this.chatVisibility, this.chatColours, i, this.mainHand));
      }

   }

   public Set<MH> getModelParts() {
      return ImmutableSet.copyOf(this.setModelParts);
   }

   public void setModelPartEnabled(MH modelPart, boolean enable) {
      if (enable) {
         this.setModelParts.add(modelPart);
      } else {
         this.setModelParts.remove(modelPart);
      }

      this.sendSettingsToServer();
   }

   public void switchModelPartEnabled(MH modelPart) {
      if (this.getModelParts().contains(modelPart)) {
         this.setModelParts.remove(modelPart);
      } else {
         this.setModelParts.add(modelPart);
      }

      this.sendSettingsToServer();
   }

   public int shouldRenderClouds() {
      return this.renderDistanceChunks >= 4 ? this.clouds : 0;
   }

   public boolean isUsingNativeTransport() {
      return this.useNativeTransport;
   }

   private void setOptionFloatValueOF(Bi p_setOptionFloatValueOF_1_, float p_setOptionFloatValueOF_2_) {
      if (p_setOptionFloatValueOF_1_ == Bi.CLOUD_HEIGHT) {
         this.ofCloudsHeight = p_setOptionFloatValueOF_2_;
         this.mc.renderGlobal.resetClouds();
      }

      if (p_setOptionFloatValueOF_1_ == Bi.AO_LEVEL) {
         this.ofAoLevel = p_setOptionFloatValueOF_2_;
         this.mc.renderGlobal.loadRenderers();
      }

      int i1;
      if (p_setOptionFloatValueOF_1_ == Bi.AA_LEVEL) {
         i1 = (int)p_setOptionFloatValueOF_2_;
         if (i1 > 0 && XH.isShaders()) {
            XH.showGuiMessage(bmW.get("of.message.aa.shaders1"), bmW.get("of.message.aa.shaders2"));
            return;
         }

         int[] aint = new int[]{0, 2, 4, 6, 8, 12, 16};
         this.ofAaLevel = 0;

         for(int j = 0; j < aint.length; ++j) {
            if (i1 >= aint[j]) {
               this.ofAaLevel = aint[j];
            }
         }

         this.ofAaLevel = XH.limit(this.ofAaLevel, 0, 16);
      }

      if (p_setOptionFloatValueOF_1_ == Bi.AF_LEVEL) {
         i1 = (int)p_setOptionFloatValueOF_2_;
         if (i1 > 1 && XH.isShaders()) {
            XH.showGuiMessage(bmW.get("of.message.af.shaders1"), bmW.get("of.message.af.shaders2"));
            return;
         }

         for(this.ofAfLevel = 1; this.ofAfLevel * 2 <= i1; this.ofAfLevel *= 2) {
         }

         this.ofAfLevel = XH.limit(this.ofAfLevel, 1, 16);
         this.mc.refreshResources();
      }

      if (p_setOptionFloatValueOF_1_ == Bi.MIPMAP_TYPE) {
         i1 = (int)p_setOptionFloatValueOF_2_;
         this.ofMipmapType = XH.limit(i1, 0, 3);
         this.mc.refreshResources();
      }

      if (p_setOptionFloatValueOF_1_ == Bi.FULLSCREEN_MODE) {
         i1 = (int)p_setOptionFloatValueOF_2_ - 1;
         String[] astring = XH.getDisplayModeNames();
         if (i1 < 0 || i1 >= astring.length) {
            this.ofFullscreenMode = "Default";
            return;
         }

         this.ofFullscreenMode = astring[i1];
      }

   }

   private float getOptionFloatValueOF(Bi p_getOptionFloatValueOF_1_) {
      if (p_getOptionFloatValueOF_1_ == Bi.CLOUD_HEIGHT) {
         return this.ofCloudsHeight;
      } else if (p_getOptionFloatValueOF_1_ == Bi.AO_LEVEL) {
         return this.ofAoLevel;
      } else if (p_getOptionFloatValueOF_1_ == Bi.AA_LEVEL) {
         return (float)this.ofAaLevel;
      } else if (p_getOptionFloatValueOF_1_ == Bi.AF_LEVEL) {
         return (float)this.ofAfLevel;
      } else if (p_getOptionFloatValueOF_1_ == Bi.MIPMAP_TYPE) {
         return (float)this.ofMipmapType;
      } else if (p_getOptionFloatValueOF_1_ != Bi.FRAMERATE_LIMIT) {
         if (p_getOptionFloatValueOF_1_ == Bi.FULLSCREEN_MODE) {
            if (this.ofFullscreenMode.equals("Default")) {
               return 0.0F;
            } else {
               List list = Arrays.asList(XH.getDisplayModeNames());
               int i = list.indexOf(this.ofFullscreenMode);
               return i < 0 ? 0.0F : (float)(i + 1);
            }
         } else {
            return Float.MAX_VALUE;
         }
      } else {
         return (float)this.limitFramerate == Bi.FRAMERATE_LIMIT.getValueMax() && this.enableVsync ? 0.0F : (float)this.limitFramerate;
      }
   }

   private void setOptionValueOF(Bi p_setOptionValueOF_1_, int p_setOptionValueOF_2_) {
      if (p_setOptionValueOF_1_ == Bi.FOG_FANCY) {
         switch (this.ofFogType) {
            case 1:
               this.ofFogType = 2;
               if (!XH.isFancyFogAvailable()) {
                  this.ofFogType = 3;
               }
               break;
            case 2:
               this.ofFogType = 3;
               break;
            case 3:
               this.ofFogType = 1;
               break;
            default:
               this.ofFogType = 1;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.FOG_START) {
         this.ofFogStart += 0.2F;
         if (this.ofFogStart > 0.81F) {
            this.ofFogStart = 0.2F;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.SMOOTH_FPS) {
         this.ofSmoothFps = !this.ofSmoothFps;
      }

      if (p_setOptionValueOF_1_ == Bi.SMOOTH_WORLD) {
         this.ofSmoothWorld = !this.ofSmoothWorld;
         XH.updateThreadPriorities();
      }

      if (p_setOptionValueOF_1_ == Bi.CLOUDS) {
         ++this.ofClouds;
         if (this.ofClouds > 3) {
            this.ofClouds = 0;
         }

         this.updateRenderClouds();
         this.mc.renderGlobal.resetClouds();
      }

      if (p_setOptionValueOF_1_ == Bi.TREES) {
         this.ofTrees = nextValue(this.ofTrees, OF_TREES_VALUES);
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.DROPPED_ITEMS) {
         ++this.ofDroppedItems;
         if (this.ofDroppedItems > 2) {
            this.ofDroppedItems = 0;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.RAIN) {
         ++this.ofRain;
         if (this.ofRain > 3) {
            this.ofRain = 0;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_WATER) {
         ++this.ofAnimatedWater;
         if (this.ofAnimatedWater == 1) {
            ++this.ofAnimatedWater;
         }

         if (this.ofAnimatedWater > 2) {
            this.ofAnimatedWater = 0;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_LAVA) {
         ++this.ofAnimatedLava;
         if (this.ofAnimatedLava == 1) {
            ++this.ofAnimatedLava;
         }

         if (this.ofAnimatedLava > 2) {
            this.ofAnimatedLava = 0;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_FIRE) {
         this.ofAnimatedFire = !this.ofAnimatedFire;
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_PORTAL) {
         this.ofAnimatedPortal = !this.ofAnimatedPortal;
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_REDSTONE) {
         this.ofAnimatedRedstone = !this.ofAnimatedRedstone;
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_EXPLOSION) {
         this.ofAnimatedExplosion = !this.ofAnimatedExplosion;
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_FLAME) {
         this.ofAnimatedFlame = !this.ofAnimatedFlame;
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_SMOKE) {
         this.ofAnimatedSmoke = !this.ofAnimatedSmoke;
      }

      if (p_setOptionValueOF_1_ == Bi.VOID_PARTICLES) {
         this.ofVoidParticles = !this.ofVoidParticles;
      }

      if (p_setOptionValueOF_1_ == Bi.WATER_PARTICLES) {
         this.ofWaterParticles = !this.ofWaterParticles;
      }

      if (p_setOptionValueOF_1_ == Bi.PORTAL_PARTICLES) {
         this.ofPortalParticles = !this.ofPortalParticles;
      }

      if (p_setOptionValueOF_1_ == Bi.POTION_PARTICLES) {
         this.ofPotionParticles = !this.ofPotionParticles;
      }

      if (p_setOptionValueOF_1_ == Bi.FIREWORK_PARTICLES) {
         this.ofFireworkParticles = !this.ofFireworkParticles;
      }

      if (p_setOptionValueOF_1_ == Bi.DRIPPING_WATER_LAVA) {
         this.ofDrippingWaterLava = !this.ofDrippingWaterLava;
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_TERRAIN) {
         this.ofAnimatedTerrain = !this.ofAnimatedTerrain;
      }

      if (p_setOptionValueOF_1_ == Bi.ANIMATED_TEXTURES) {
         this.ofAnimatedTextures = !this.ofAnimatedTextures;
      }

      if (p_setOptionValueOF_1_ == Bi.RAIN_SPLASH) {
         this.ofRainSplash = !this.ofRainSplash;
      }

      if (p_setOptionValueOF_1_ == Bi.LAGOMETER) {
         this.ofLagometer = !this.ofLagometer;
      }

      if (p_setOptionValueOF_1_ == Bi.SHOW_FPS) {
         this.ofShowFps = !this.ofShowFps;
      }

      if (p_setOptionValueOF_1_ == Bi.AUTOSAVE_TICKS) {
         int i = 900;
         this.ofAutoSaveTicks = Math.max(this.ofAutoSaveTicks / i * i, i);
         this.ofAutoSaveTicks *= 2;
         if (this.ofAutoSaveTicks > 32 * i) {
            this.ofAutoSaveTicks = i;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.BETTER_GRASS) {
         ++this.ofBetterGrass;
         if (this.ofBetterGrass > 3) {
            this.ofBetterGrass = 1;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.CONNECTED_TEXTURES) {
         ++this.ofConnectedTextures;
         if (this.ofConnectedTextures > 3) {
            this.ofConnectedTextures = 1;
         }

         if (this.ofConnectedTextures == 2) {
            this.mc.renderGlobal.loadRenderers();
         } else {
            this.mc.refreshResources();
         }
      }

      if (p_setOptionValueOF_1_ == Bi.WEATHER) {
         this.ofWeather = !this.ofWeather;
      }

      if (p_setOptionValueOF_1_ == Bi.SKY) {
         this.ofSky = !this.ofSky;
      }

      if (p_setOptionValueOF_1_ == Bi.STARS) {
         this.ofStars = !this.ofStars;
      }

      if (p_setOptionValueOF_1_ == Bi.SUN_MOON) {
         this.ofSunMoon = !this.ofSunMoon;
      }

      if (p_setOptionValueOF_1_ == Bi.VIGNETTE) {
         ++this.ofVignette;
         if (this.ofVignette > 2) {
            this.ofVignette = 0;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.CHUNK_UPDATES) {
         ++this.ofChunkUpdates;
         if (this.ofChunkUpdates > 5) {
            this.ofChunkUpdates = 1;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.CHUNK_UPDATES_DYNAMIC) {
         this.ofChunkUpdatesDynamic = !this.ofChunkUpdatesDynamic;
      }

      if (p_setOptionValueOF_1_ == Bi.TIME) {
         ++this.ofTime;
         if (this.ofTime > 2) {
            this.ofTime = 0;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.CLEAR_WATER) {
         this.ofClearWater = !this.ofClearWater;
         this.updateWaterOpacity();
      }

      if (p_setOptionValueOF_1_ == Bi.PROFILER) {
         this.ofProfiler = !this.ofProfiler;
      }

      if (p_setOptionValueOF_1_ == Bi.BETTER_SNOW) {
         this.ofBetterSnow = !this.ofBetterSnow;
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.SWAMP_COLORS) {
         this.ofSwampColors = !this.ofSwampColors;
         bjy.updateUseDefaultGrassFoliageColors();
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.RANDOM_ENTITIES) {
         this.ofRandomEntities = !this.ofRandomEntities;
         bnx.update();
      }

      if (p_setOptionValueOF_1_ == Bi.SMOOTH_BIOMES) {
         this.ofSmoothBiomes = !this.ofSmoothBiomes;
         bjy.updateUseDefaultGrassFoliageColors();
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.CUSTOM_FONTS) {
         this.ofCustomFonts = !this.ofCustomFonts;
         this.mc.fontRenderer.onResourceManagerReload(XH.getResourceManager());
         this.mc.standardGalacticFontRenderer.onResourceManagerReload(XH.getResourceManager());
      }

      if (p_setOptionValueOF_1_ == Bi.CUSTOM_COLORS) {
         this.ofCustomColors = !this.ofCustomColors;
         bjy.update();
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.CUSTOM_ITEMS) {
         this.ofCustomItems = !this.ofCustomItems;
         this.mc.refreshResources();
      }

      if (p_setOptionValueOF_1_ == Bi.CUSTOM_SKY) {
         this.ofCustomSky = !this.ofCustomSky;
         bjM.update();
      }

      if (p_setOptionValueOF_1_ == Bi.SHOW_CAPES) {
         this.ofShowCapes = !this.ofShowCapes;
      }

      if (p_setOptionValueOF_1_ == Bi.NATURAL_TEXTURES) {
         this.ofNaturalTextures = !this.ofNaturalTextures;
         bnj.update();
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.EMISSIVE_TEXTURES) {
         this.ofEmissiveTextures = !this.ofEmissiveTextures;
         this.mc.refreshResources();
      }

      if (p_setOptionValueOF_1_ == Bi.FAST_MATH) {
         this.ofFastMath = !this.ofFastMath;
         MathHelper.fastMath = this.ofFastMath;
      }

      if (p_setOptionValueOF_1_ == Bi.FAST_RENDER) {
         if (!this.ofFastRender && XH.isShaders()) {
            XH.showGuiMessage(bmW.get("of.message.fr.shaders1"), bmW.get("of.message.fr.shaders2"));
            return;
         }

         this.ofFastRender = !this.ofFastRender;
         if (this.ofFastRender) {
            this.mc.entityRenderer.stopUseShader();
         }

         XH.updateFramebufferSize();
      }

      if (p_setOptionValueOF_1_ == Bi.TRANSLUCENT_BLOCKS) {
         if (this.ofTranslucentBlocks == 0) {
            this.ofTranslucentBlocks = 1;
         } else if (this.ofTranslucentBlocks == 1) {
            this.ofTranslucentBlocks = 2;
         } else if (this.ofTranslucentBlocks == 2) {
            this.ofTranslucentBlocks = 0;
         } else {
            this.ofTranslucentBlocks = 0;
         }

         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.LAZY_CHUNK_LOADING) {
         this.ofLazyChunkLoading = !this.ofLazyChunkLoading;
      }

      if (p_setOptionValueOF_1_ == Bi.RENDER_REGIONS) {
         this.ofRenderRegions = !this.ofRenderRegions;
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.SMART_ANIMATIONS) {
         this.ofSmartAnimations = !this.ofSmartAnimations;
         this.mc.renderGlobal.loadRenderers();
      }

      if (p_setOptionValueOF_1_ == Bi.DYNAMIC_FOV) {
         this.ofDynamicFov = !this.ofDynamicFov;
      }

      if (p_setOptionValueOF_1_ == Bi.ALTERNATE_BLOCKS) {
         this.ofAlternateBlocks = !this.ofAlternateBlocks;
         this.mc.refreshResources();
      }

      if (p_setOptionValueOF_1_ == Bi.DYNAMIC_LIGHTS) {
         this.ofDynamicLights = nextValue(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
         bjP.removeLights(this.mc.renderGlobal);
      }

      if (p_setOptionValueOF_1_ == Bi.SCREENSHOT_SIZE) {
         ++this.ofScreenshotSize;
         if (this.ofScreenshotSize > 4) {
            this.ofScreenshotSize = 1;
         }

         if (!ys.isFramebufferEnabled()) {
            this.ofScreenshotSize = 1;
         }
      }

      if (p_setOptionValueOF_1_ == Bi.CUSTOM_ENTITY_MODELS) {
         this.ofCustomEntityModels = !this.ofCustomEntityModels;
         this.mc.refreshResources();
      }

      if (p_setOptionValueOF_1_ == Bi.CUSTOM_GUIS) {
         this.ofCustomGuis = !this.ofCustomGuis;
         bjD.update();
      }

      if (p_setOptionValueOF_1_ == Bi.SHOW_GL_ERRORS) {
         this.ofShowGlErrors = !this.ofShowGlErrors;
      }

      if (p_setOptionValueOF_1_ == Bi.HELD_ITEM_TOOLTIPS) {
         this.heldItemTooltips = !this.heldItemTooltips;
      }

      if (p_setOptionValueOF_1_ == Bi.ADVANCED_TOOLTIPS) {
         this.advancedItemTooltips = !this.advancedItemTooltips;
      }

   }

   private String getKeyBindingOF(Bi p_getKeyBindingOF_1_) {
      String s = Ax.format(p_getKeyBindingOF_1_.getTranslation()) + ": ";
      if (s == null) {
         s = p_getKeyBindingOF_1_.getTranslation();
      }

      int k;
      if (p_getKeyBindingOF_1_ == Bi.RENDER_DISTANCE) {
         k = (int)this.getOptionFloatValue(p_getKeyBindingOF_1_);
         String s1 = Ax.format("of.options.renderDistance.tiny");
         int i = 2;
         if (k >= 4) {
            s1 = Ax.format("of.options.renderDistance.short");
            i = 4;
         }

         if (k >= 8) {
            s1 = Ax.format("of.options.renderDistance.normal");
            i = 8;
         }

         if (k >= 16) {
            s1 = Ax.format("of.options.renderDistance.far");
            i = 16;
         }

         if (k >= 32) {
            s1 = bmW.get("of.options.renderDistance.extreme");
            i = 32;
         }

         if (k >= 48) {
            s1 = bmW.get("of.options.renderDistance.insane");
            i = 48;
         }

         if (k >= 64) {
            s1 = bmW.get("of.options.renderDistance.ludicrous");
            i = 64;
         }

         int j = this.renderDistanceChunks - i;
         String s2 = s1;
         if (j > 0) {
            s2 = s1 + "+";
         }

         return s + k + " " + s2 + "";
      } else if (p_getKeyBindingOF_1_ == Bi.FOG_FANCY) {
         switch (this.ofFogType) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            case 3:
               return s + bmW.getOff();
            default:
               return s + bmW.getOff();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.FOG_START) {
         return s + this.ofFogStart;
      } else if (p_getKeyBindingOF_1_ == Bi.MIPMAP_TYPE) {
         switch (this.ofMipmapType) {
            case 0:
               return s + bmW.get("of.options.mipmap.nearest");
            case 1:
               return s + bmW.get("of.options.mipmap.linear");
            case 2:
               return s + bmW.get("of.options.mipmap.bilinear");
            case 3:
               return s + bmW.get("of.options.mipmap.trilinear");
            default:
               return s + "of.options.mipmap.nearest";
         }
      } else if (p_getKeyBindingOF_1_ == Bi.SMOOTH_FPS) {
         return this.ofSmoothFps ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SMOOTH_WORLD) {
         return this.ofSmoothWorld ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.CLOUDS) {
         switch (this.ofClouds) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            case 3:
               return s + bmW.getOff();
            default:
               return s + bmW.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.TREES) {
         switch (this.ofTrees) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            case 3:
            default:
               return s + bmW.getDefault();
            case 4:
               return s + bmW.get("of.general.smart");
         }
      } else if (p_getKeyBindingOF_1_ == Bi.DROPPED_ITEMS) {
         switch (this.ofDroppedItems) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            default:
               return s + bmW.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.RAIN) {
         switch (this.ofRain) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            case 3:
               return s + bmW.getOff();
            default:
               return s + bmW.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_WATER) {
         switch (this.ofAnimatedWater) {
            case 1:
               return s + bmW.get("of.options.animation.dynamic");
            case 2:
               return s + bmW.getOff();
            default:
               return s + bmW.getOn();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_LAVA) {
         switch (this.ofAnimatedLava) {
            case 1:
               return s + bmW.get("of.options.animation.dynamic");
            case 2:
               return s + bmW.getOff();
            default:
               return s + bmW.getOn();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_FIRE) {
         return this.ofAnimatedFire ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_PORTAL) {
         return this.ofAnimatedPortal ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_REDSTONE) {
         return this.ofAnimatedRedstone ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_EXPLOSION) {
         return this.ofAnimatedExplosion ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_FLAME) {
         return this.ofAnimatedFlame ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_SMOKE) {
         return this.ofAnimatedSmoke ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.VOID_PARTICLES) {
         return this.ofVoidParticles ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.WATER_PARTICLES) {
         return this.ofWaterParticles ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.PORTAL_PARTICLES) {
         return this.ofPortalParticles ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.POTION_PARTICLES) {
         return this.ofPotionParticles ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.FIREWORK_PARTICLES) {
         return this.ofFireworkParticles ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.DRIPPING_WATER_LAVA) {
         return this.ofDrippingWaterLava ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_TERRAIN) {
         return this.ofAnimatedTerrain ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ANIMATED_TEXTURES) {
         return this.ofAnimatedTextures ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.RAIN_SPLASH) {
         return this.ofRainSplash ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.LAGOMETER) {
         return this.ofLagometer ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SHOW_FPS) {
         return this.ofShowFps ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.AUTOSAVE_TICKS) {
         int l = 900;
         if (this.ofAutoSaveTicks <= l) {
            return s + bmW.get("of.options.save.45s");
         } else if (this.ofAutoSaveTicks <= 2 * l) {
            return s + bmW.get("of.options.save.90s");
         } else if (this.ofAutoSaveTicks <= 4 * l) {
            return s + bmW.get("of.options.save.3min");
         } else if (this.ofAutoSaveTicks <= 8 * l) {
            return s + bmW.get("of.options.save.6min");
         } else {
            return this.ofAutoSaveTicks <= 16 * l ? s + bmW.get("of.options.save.12min") : s + bmW.get("of.options.save.24min");
         }
      } else if (p_getKeyBindingOF_1_ == Bi.BETTER_GRASS) {
         switch (this.ofBetterGrass) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            default:
               return s + bmW.getOff();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.CONNECTED_TEXTURES) {
         switch (this.ofConnectedTextures) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            default:
               return s + bmW.getOff();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.WEATHER) {
         return this.ofWeather ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SKY) {
         return this.ofSky ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.STARS) {
         return this.ofStars ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SUN_MOON) {
         return this.ofSunMoon ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.VIGNETTE) {
         switch (this.ofVignette) {
            case 1:
               return s + bmW.getFast();
            case 2:
               return s + bmW.getFancy();
            default:
               return s + bmW.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.CHUNK_UPDATES) {
         return s + this.ofChunkUpdates;
      } else if (p_getKeyBindingOF_1_ == Bi.CHUNK_UPDATES_DYNAMIC) {
         return this.ofChunkUpdatesDynamic ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.TIME) {
         if (this.ofTime == 1) {
            return s + bmW.get("of.options.time.dayOnly");
         } else {
            return this.ofTime == 2 ? s + bmW.get("of.options.time.nightOnly") : s + bmW.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.CLEAR_WATER) {
         return this.ofClearWater ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.AA_LEVEL) {
         String s3 = "";
         if (this.ofAaLevel != XH.getAntialiasingLevel()) {
            s3 = " (" + bmW.get("of.general.restart") + ")";
         }

         return this.ofAaLevel == 0 ? s + bmW.getOff() + s3 : s + this.ofAaLevel + s3;
      } else if (p_getKeyBindingOF_1_ == Bi.AF_LEVEL) {
         return this.ofAfLevel == 1 ? s + bmW.getOff() : s + this.ofAfLevel;
      } else if (p_getKeyBindingOF_1_ == Bi.PROFILER) {
         return this.ofProfiler ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.BETTER_SNOW) {
         return this.ofBetterSnow ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SWAMP_COLORS) {
         return this.ofSwampColors ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.RANDOM_ENTITIES) {
         return this.ofRandomEntities ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SMOOTH_BIOMES) {
         return this.ofSmoothBiomes ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.CUSTOM_FONTS) {
         return this.ofCustomFonts ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.CUSTOM_COLORS) {
         return this.ofCustomColors ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.CUSTOM_SKY) {
         return this.ofCustomSky ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SHOW_CAPES) {
         return this.ofShowCapes ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.CUSTOM_ITEMS) {
         return this.ofCustomItems ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.NATURAL_TEXTURES) {
         return this.ofNaturalTextures ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.EMISSIVE_TEXTURES) {
         return this.ofEmissiveTextures ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.FAST_MATH) {
         return this.ofFastMath ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.FAST_RENDER) {
         return this.ofFastRender ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.TRANSLUCENT_BLOCKS) {
         if (this.ofTranslucentBlocks == 1) {
            return s + bmW.getFast();
         } else {
            return this.ofTranslucentBlocks == 2 ? s + bmW.getFancy() : s + bmW.getDefault();
         }
      } else if (p_getKeyBindingOF_1_ == Bi.LAZY_CHUNK_LOADING) {
         return this.ofLazyChunkLoading ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.RENDER_REGIONS) {
         return this.ofRenderRegions ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SMART_ANIMATIONS) {
         return this.ofSmartAnimations ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.DYNAMIC_FOV) {
         return this.ofDynamicFov ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ALTERNATE_BLOCKS) {
         return this.ofAlternateBlocks ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.DYNAMIC_LIGHTS) {
         k = indexOf(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
         return s + getTranslation(KEYS_DYNAMIC_LIGHTS, k);
      } else if (p_getKeyBindingOF_1_ == Bi.SCREENSHOT_SIZE) {
         return this.ofScreenshotSize <= 1 ? s + bmW.getDefault() : s + this.ofScreenshotSize + "x";
      } else if (p_getKeyBindingOF_1_ == Bi.CUSTOM_ENTITY_MODELS) {
         return this.ofCustomEntityModels ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.CUSTOM_GUIS) {
         return this.ofCustomGuis ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.SHOW_GL_ERRORS) {
         return this.ofShowGlErrors ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.FULLSCREEN_MODE) {
         return this.ofFullscreenMode.equals("Default") ? s + bmW.getDefault() : s + this.ofFullscreenMode;
      } else if (p_getKeyBindingOF_1_ == Bi.HELD_ITEM_TOOLTIPS) {
         return this.heldItemTooltips ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.ADVANCED_TOOLTIPS) {
         return this.advancedItemTooltips ? s + bmW.getOn() : s + bmW.getOff();
      } else if (p_getKeyBindingOF_1_ == Bi.FRAMERATE_LIMIT) {
         float f = this.getOptionFloatValue(p_getKeyBindingOF_1_);
         if (f == 0.0F) {
            return s + bmW.get("of.options.framerateLimit.vsync");
         } else {
            return f == Bi.access$000(p_getKeyBindingOF_1_) ? s + Ax.format("options.framerateLimit.max") : s + (int)f + " fps";
         }
      } else {
         return null;
      }
   }

   public void loadOfOptions() {
      try {
         File file1 = this.optionsFileOF;
         if (!file1.exists()) {
            file1 = this.optionsFile;
         }

         if (!file1.exists()) {
            return;
         }

         BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file1), StandardCharsets.UTF_8));
         String s = "";

         while((s = bufferedreader.readLine()) != null) {
            try {
               String[] astring = s.split(":");
               if (astring[0].equals("ofRenderDistanceChunks") && astring.length >= 2) {
                  this.renderDistanceChunks = Integer.valueOf(astring[1]);
                  this.renderDistanceChunks = XH.limit(this.renderDistanceChunks, 2, 1024);
               }

               if (astring[0].equals("ofFogType") && astring.length >= 2) {
                  this.ofFogType = Integer.valueOf(astring[1]);
                  this.ofFogType = XH.limit(this.ofFogType, 1, 3);
               }

               if (astring[0].equals("ofFogStart") && astring.length >= 2) {
                  this.ofFogStart = Float.valueOf(astring[1]);
                  if (this.ofFogStart < 0.2F) {
                     this.ofFogStart = 0.2F;
                  }

                  if (this.ofFogStart > 0.81F) {
                     this.ofFogStart = 0.8F;
                  }
               }

               if (astring[0].equals("ofMipmapType") && astring.length >= 2) {
                  this.ofMipmapType = Integer.valueOf(astring[1]);
                  this.ofMipmapType = XH.limit(this.ofMipmapType, 0, 3);
               }

               if (astring[0].equals("ofOcclusionFancy") && astring.length >= 2) {
                  this.ofOcclusionFancy = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofSmoothFps") && astring.length >= 2) {
                  this.ofSmoothFps = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofSmoothWorld") && astring.length >= 2) {
                  this.ofSmoothWorld = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAoLevel") && astring.length >= 2) {
                  this.ofAoLevel = Float.valueOf(astring[1]);
                  this.ofAoLevel = XH.limit(this.ofAoLevel, 0.0F, 1.0F);
               }

               if (astring[0].equals("ofClouds") && astring.length >= 2) {
                  this.ofClouds = Integer.valueOf(astring[1]);
                  this.ofClouds = XH.limit(this.ofClouds, 0, 3);
                  this.updateRenderClouds();
               }

               if (astring[0].equals("ofCloudsHeight") && astring.length >= 2) {
                  this.ofCloudsHeight = Float.valueOf(astring[1]);
                  this.ofCloudsHeight = XH.limit(this.ofCloudsHeight, 0.0F, 1.0F);
               }

               if (astring[0].equals("ofTrees") && astring.length >= 2) {
                  this.ofTrees = Integer.valueOf(astring[1]);
                  this.ofTrees = limit(this.ofTrees, OF_TREES_VALUES);
               }

               if (astring[0].equals("ofDroppedItems") && astring.length >= 2) {
                  this.ofDroppedItems = Integer.valueOf(astring[1]);
                  this.ofDroppedItems = XH.limit(this.ofDroppedItems, 0, 2);
               }

               if (astring[0].equals("ofRain") && astring.length >= 2) {
                  this.ofRain = Integer.valueOf(astring[1]);
                  this.ofRain = XH.limit(this.ofRain, 0, 3);
               }

               if (astring[0].equals("ofAnimatedWater") && astring.length >= 2) {
                  this.ofAnimatedWater = Integer.valueOf(astring[1]);
                  this.ofAnimatedWater = XH.limit(this.ofAnimatedWater, 0, 2);
               }

               if (astring[0].equals("ofAnimatedLava") && astring.length >= 2) {
                  this.ofAnimatedLava = Integer.valueOf(astring[1]);
                  this.ofAnimatedLava = XH.limit(this.ofAnimatedLava, 0, 2);
               }

               if (astring[0].equals("ofAnimatedFire") && astring.length >= 2) {
                  this.ofAnimatedFire = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAnimatedPortal") && astring.length >= 2) {
                  this.ofAnimatedPortal = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAnimatedRedstone") && astring.length >= 2) {
                  this.ofAnimatedRedstone = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAnimatedExplosion") && astring.length >= 2) {
                  this.ofAnimatedExplosion = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAnimatedFlame") && astring.length >= 2) {
                  this.ofAnimatedFlame = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAnimatedSmoke") && astring.length >= 2) {
                  this.ofAnimatedSmoke = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofVoidParticles") && astring.length >= 2) {
                  this.ofVoidParticles = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofWaterParticles") && astring.length >= 2) {
                  this.ofWaterParticles = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofPortalParticles") && astring.length >= 2) {
                  this.ofPortalParticles = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofPotionParticles") && astring.length >= 2) {
                  this.ofPotionParticles = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofFireworkParticles") && astring.length >= 2) {
                  this.ofFireworkParticles = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofDrippingWaterLava") && astring.length >= 2) {
                  this.ofDrippingWaterLava = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAnimatedTerrain") && astring.length >= 2) {
                  this.ofAnimatedTerrain = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAnimatedTextures") && astring.length >= 2) {
                  this.ofAnimatedTextures = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofRainSplash") && astring.length >= 2) {
                  this.ofRainSplash = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofLagometer") && astring.length >= 2) {
                  this.ofLagometer = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofShowFps") && astring.length >= 2) {
                  this.ofShowFps = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAutoSaveTicks") && astring.length >= 2) {
                  this.ofAutoSaveTicks = Integer.valueOf(astring[1]);
                  this.ofAutoSaveTicks = XH.limit(this.ofAutoSaveTicks, 40, 40000);
               }

               if (astring[0].equals("ofBetterGrass") && astring.length >= 2) {
                  this.ofBetterGrass = Integer.valueOf(astring[1]);
                  this.ofBetterGrass = XH.limit(this.ofBetterGrass, 1, 3);
               }

               if (astring[0].equals("ofConnectedTextures") && astring.length >= 2) {
                  this.ofConnectedTextures = Integer.valueOf(astring[1]);
                  this.ofConnectedTextures = XH.limit(this.ofConnectedTextures, 1, 3);
               }

               if (astring[0].equals("ofWeather") && astring.length >= 2) {
                  this.ofWeather = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofSky") && astring.length >= 2) {
                  this.ofSky = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofStars") && astring.length >= 2) {
                  this.ofStars = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofSunMoon") && astring.length >= 2) {
                  this.ofSunMoon = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofVignette") && astring.length >= 2) {
                  this.ofVignette = Integer.valueOf(astring[1]);
                  this.ofVignette = XH.limit(this.ofVignette, 0, 2);
               }

               if (astring[0].equals("ofChunkUpdates") && astring.length >= 2) {
                  this.ofChunkUpdates = Integer.valueOf(astring[1]);
                  this.ofChunkUpdates = XH.limit(this.ofChunkUpdates, 1, 5);
               }

               if (astring[0].equals("ofChunkUpdatesDynamic") && astring.length >= 2) {
                  this.ofChunkUpdatesDynamic = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofTime") && astring.length >= 2) {
                  this.ofTime = Integer.valueOf(astring[1]);
                  this.ofTime = XH.limit(this.ofTime, 0, 2);
               }

               if (astring[0].equals("ofClearWater") && astring.length >= 2) {
                  this.ofClearWater = Boolean.valueOf(astring[1]);
                  this.updateWaterOpacity();
               }

               if (astring[0].equals("ofAaLevel") && astring.length >= 2) {
                  this.ofAaLevel = Integer.valueOf(astring[1]);
                  this.ofAaLevel = XH.limit(this.ofAaLevel, 0, 16);
               }

               if (astring[0].equals("ofAfLevel") && astring.length >= 2) {
                  this.ofAfLevel = Integer.valueOf(astring[1]);
                  this.ofAfLevel = XH.limit(this.ofAfLevel, 1, 16);
               }

               if (astring[0].equals("ofProfiler") && astring.length >= 2) {
                  this.ofProfiler = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofBetterSnow") && astring.length >= 2) {
                  this.ofBetterSnow = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofSwampColors") && astring.length >= 2) {
                  this.ofSwampColors = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofRandomEntities") && astring.length >= 2) {
                  this.ofRandomEntities = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofSmoothBiomes") && astring.length >= 2) {
                  this.ofSmoothBiomes = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofCustomFonts") && astring.length >= 2) {
                  this.ofCustomFonts = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofCustomColors") && astring.length >= 2) {
                  this.ofCustomColors = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofCustomItems") && astring.length >= 2) {
                  this.ofCustomItems = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofCustomSky") && astring.length >= 2) {
                  this.ofCustomSky = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofShowCapes") && astring.length >= 2) {
                  this.ofShowCapes = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofNaturalTextures") && astring.length >= 2) {
                  this.ofNaturalTextures = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofEmissiveTextures") && astring.length >= 2) {
                  this.ofEmissiveTextures = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofLazyChunkLoading") && astring.length >= 2) {
                  this.ofLazyChunkLoading = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofRenderRegions") && astring.length >= 2) {
                  this.ofRenderRegions = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofSmartAnimations") && astring.length >= 2) {
                  this.ofSmartAnimations = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofDynamicFov") && astring.length >= 2) {
                  this.ofDynamicFov = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofAlternateBlocks") && astring.length >= 2) {
                  this.ofAlternateBlocks = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofDynamicLights") && astring.length >= 2) {
                  this.ofDynamicLights = Integer.valueOf(astring[1]);
                  this.ofDynamicLights = limit(this.ofDynamicLights, OF_DYNAMIC_LIGHTS);
               }

               if (astring[0].equals("ofScreenshotSize") && astring.length >= 2) {
                  this.ofScreenshotSize = Integer.valueOf(astring[1]);
                  this.ofScreenshotSize = XH.limit(this.ofScreenshotSize, 1, 4);
               }

               if (astring[0].equals("ofCustomEntityModels") && astring.length >= 2) {
                  this.ofCustomEntityModels = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofCustomGuis") && astring.length >= 2) {
                  this.ofCustomGuis = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofShowGlErrors") && astring.length >= 2) {
                  this.ofShowGlErrors = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofFullscreenMode") && astring.length >= 2) {
                  this.ofFullscreenMode = astring[1];
               }

               if (astring[0].equals("ofFastMath") && astring.length >= 2) {
                  this.ofFastMath = Boolean.valueOf(astring[1]);
                  MathHelper.fastMath = this.ofFastMath;
               }

               if (astring[0].equals("ofFastRender") && astring.length >= 2) {
                  this.ofFastRender = Boolean.valueOf(astring[1]);
               }

               if (astring[0].equals("ofTranslucentBlocks") && astring.length >= 2) {
                  this.ofTranslucentBlocks = Integer.valueOf(astring[1]);
                  this.ofTranslucentBlocks = XH.limit(this.ofTranslucentBlocks, 0, 2);
               }

               if (astring[0].equals("key_" + this.ofKeyBindZoom.getKeyDescription())) {
                  this.ofKeyBindZoom.setKeyCode(Integer.parseInt(astring[1]));
               }
            } catch (Exception var5) {
               Exception exception1 = var5;
               XH.dbg("Skipping bad option: " + s);
               exception1.printStackTrace();
            }
         }

         bqy.fixKeyConflicts(this.keyBindings, new Bl[]{this.ofKeyBindZoom});
         Bl.resetKeyBindingArrayAndHash();
         bufferedreader.close();
      } catch (Exception var6) {
         Exception exception11 = var6;
         XH.warn("Failed to load options");
         exception11.printStackTrace();
      }

   }

   public void saveOfOptions() {
      try {
         PrintWriter printwriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(this.optionsFileOF), StandardCharsets.UTF_8));
         printwriter.println("ofFogType:" + this.ofFogType);
         printwriter.println("ofFogStart:" + this.ofFogStart);
         printwriter.println("ofMipmapType:" + this.ofMipmapType);
         printwriter.println("ofOcclusionFancy:" + this.ofOcclusionFancy);
         printwriter.println("ofSmoothFps:" + this.ofSmoothFps);
         printwriter.println("ofSmoothWorld:" + this.ofSmoothWorld);
         printwriter.println("ofAoLevel:" + this.ofAoLevel);
         printwriter.println("ofClouds:" + this.ofClouds);
         printwriter.println("ofCloudsHeight:" + this.ofCloudsHeight);
         printwriter.println("ofTrees:" + this.ofTrees);
         printwriter.println("ofDroppedItems:" + this.ofDroppedItems);
         printwriter.println("ofRain:" + this.ofRain);
         printwriter.println("ofAnimatedWater:" + this.ofAnimatedWater);
         printwriter.println("ofAnimatedLava:" + this.ofAnimatedLava);
         printwriter.println("ofAnimatedFire:" + this.ofAnimatedFire);
         printwriter.println("ofAnimatedPortal:" + this.ofAnimatedPortal);
         printwriter.println("ofAnimatedRedstone:" + this.ofAnimatedRedstone);
         printwriter.println("ofAnimatedExplosion:" + this.ofAnimatedExplosion);
         printwriter.println("ofAnimatedFlame:" + this.ofAnimatedFlame);
         printwriter.println("ofAnimatedSmoke:" + this.ofAnimatedSmoke);
         printwriter.println("ofVoidParticles:" + this.ofVoidParticles);
         printwriter.println("ofWaterParticles:" + this.ofWaterParticles);
         printwriter.println("ofPortalParticles:" + this.ofPortalParticles);
         printwriter.println("ofPotionParticles:" + this.ofPotionParticles);
         printwriter.println("ofFireworkParticles:" + this.ofFireworkParticles);
         printwriter.println("ofDrippingWaterLava:" + this.ofDrippingWaterLava);
         printwriter.println("ofAnimatedTerrain:" + this.ofAnimatedTerrain);
         printwriter.println("ofAnimatedTextures:" + this.ofAnimatedTextures);
         printwriter.println("ofRainSplash:" + this.ofRainSplash);
         printwriter.println("ofLagometer:" + this.ofLagometer);
         printwriter.println("ofShowFps:" + this.ofShowFps);
         printwriter.println("ofAutoSaveTicks:" + this.ofAutoSaveTicks);
         printwriter.println("ofBetterGrass:" + this.ofBetterGrass);
         printwriter.println("ofConnectedTextures:" + this.ofConnectedTextures);
         printwriter.println("ofWeather:" + this.ofWeather);
         printwriter.println("ofSky:" + this.ofSky);
         printwriter.println("ofStars:" + this.ofStars);
         printwriter.println("ofSunMoon:" + this.ofSunMoon);
         printwriter.println("ofVignette:" + this.ofVignette);
         printwriter.println("ofChunkUpdates:" + this.ofChunkUpdates);
         printwriter.println("ofChunkUpdatesDynamic:" + this.ofChunkUpdatesDynamic);
         printwriter.println("ofTime:" + this.ofTime);
         printwriter.println("ofClearWater:" + this.ofClearWater);
         printwriter.println("ofAaLevel:" + this.ofAaLevel);
         printwriter.println("ofAfLevel:" + this.ofAfLevel);
         printwriter.println("ofProfiler:" + this.ofProfiler);
         printwriter.println("ofBetterSnow:" + this.ofBetterSnow);
         printwriter.println("ofSwampColors:" + this.ofSwampColors);
         printwriter.println("ofRandomEntities:" + this.ofRandomEntities);
         printwriter.println("ofSmoothBiomes:" + this.ofSmoothBiomes);
         printwriter.println("ofCustomFonts:" + this.ofCustomFonts);
         printwriter.println("ofCustomColors:" + this.ofCustomColors);
         printwriter.println("ofCustomItems:" + this.ofCustomItems);
         printwriter.println("ofCustomSky:" + this.ofCustomSky);
         printwriter.println("ofShowCapes:" + this.ofShowCapes);
         printwriter.println("ofNaturalTextures:" + this.ofNaturalTextures);
         printwriter.println("ofEmissiveTextures:" + this.ofEmissiveTextures);
         printwriter.println("ofLazyChunkLoading:" + this.ofLazyChunkLoading);
         printwriter.println("ofRenderRegions:" + this.ofRenderRegions);
         printwriter.println("ofSmartAnimations:" + this.ofSmartAnimations);
         printwriter.println("ofDynamicFov:" + this.ofDynamicFov);
         printwriter.println("ofAlternateBlocks:" + this.ofAlternateBlocks);
         printwriter.println("ofDynamicLights:" + this.ofDynamicLights);
         printwriter.println("ofScreenshotSize:" + this.ofScreenshotSize);
         printwriter.println("ofCustomEntityModels:" + this.ofCustomEntityModels);
         printwriter.println("ofCustomGuis:" + this.ofCustomGuis);
         printwriter.println("ofShowGlErrors:" + this.ofShowGlErrors);
         printwriter.println("ofFullscreenMode:" + this.ofFullscreenMode);
         printwriter.println("ofFastMath:" + this.ofFastMath);
         printwriter.println("ofFastRender:" + this.ofFastRender);
         printwriter.println("ofTranslucentBlocks:" + this.ofTranslucentBlocks);
         printwriter.println("key_" + this.ofKeyBindZoom.getKeyDescription() + ":" + this.ofKeyBindZoom.getKeyCode());
         printwriter.close();
      } catch (Exception var2) {
         Exception exception1 = var2;
         XH.warn("Failed to save options");
         exception1.printStackTrace();
      }

   }

   private void updateRenderClouds() {
      switch (this.ofClouds) {
         case 1:
            this.clouds = 1;
            break;
         case 2:
            this.clouds = 2;
            break;
         case 3:
            this.clouds = 0;
            break;
         default:
            if (this.fancyGraphics) {
               this.clouds = 2;
            } else {
               this.clouds = 1;
            }
      }

   }

   public void resetSettings() {
      this.renderDistanceChunks = 8;
      this.viewBobbing = true;
      this.anaglyph = false;
      this.limitFramerate = (int)Bi.FRAMERATE_LIMIT.getValueMax();
      this.enableVsync = false;
      this.updateVSync();
      this.mipmapLevels = 4;
      this.fancyGraphics = true;
      this.ambientOcclusion = 2;
      this.clouds = 2;
      this.fovSetting = 70.0F;
      this.gammaSetting = 0.0F;
      this.guiScale = 0;
      this.particleSetting = 0;
      this.heldItemTooltips = true;
      this.useVbo = false;
      this.forceUnicodeFont = false;
      this.ofFogType = 1;
      this.ofFogStart = 0.8F;
      this.ofMipmapType = 0;
      this.ofOcclusionFancy = false;
      this.ofSmartAnimations = false;
      this.ofSmoothFps = false;
      XH.updateAvailableProcessors();
      this.ofSmoothWorld = XH.isSingleProcessor();
      this.ofLazyChunkLoading = false;
      this.ofRenderRegions = false;
      this.ofFastMath = false;
      this.ofFastRender = false;
      this.ofTranslucentBlocks = 0;
      this.ofDynamicFov = true;
      this.ofAlternateBlocks = true;
      this.ofDynamicLights = 3;
      this.ofScreenshotSize = 1;
      this.ofCustomEntityModels = true;
      this.ofCustomGuis = true;
      this.ofShowGlErrors = true;
      this.ofAoLevel = 1.0F;
      this.ofAaLevel = 0;
      this.ofAfLevel = 1;
      this.ofClouds = 0;
      this.ofCloudsHeight = 0.0F;
      this.ofTrees = 0;
      this.ofRain = 0;
      this.ofBetterGrass = 3;
      this.ofAutoSaveTicks = 4000;
      this.ofLagometer = false;
      this.ofShowFps = false;
      this.ofProfiler = false;
      this.ofWeather = true;
      this.ofSky = true;
      this.ofStars = true;
      this.ofSunMoon = true;
      this.ofVignette = 0;
      this.ofChunkUpdates = 1;
      this.ofChunkUpdatesDynamic = false;
      this.ofTime = 0;
      this.ofClearWater = false;
      this.ofBetterSnow = false;
      this.ofFullscreenMode = "Default";
      this.ofSwampColors = true;
      this.ofRandomEntities = true;
      this.ofSmoothBiomes = true;
      this.ofCustomFonts = true;
      this.ofCustomColors = true;
      this.ofCustomItems = true;
      this.ofCustomSky = true;
      this.ofShowCapes = true;
      this.ofConnectedTextures = 2;
      this.ofNaturalTextures = false;
      this.ofEmissiveTextures = true;
      this.ofAnimatedWater = 0;
      this.ofAnimatedLava = 0;
      this.ofAnimatedFire = true;
      this.ofAnimatedPortal = true;
      this.ofAnimatedRedstone = true;
      this.ofAnimatedExplosion = true;
      this.ofAnimatedFlame = true;
      this.ofAnimatedSmoke = true;
      this.ofVoidParticles = true;
      this.ofWaterParticles = true;
      this.ofRainSplash = true;
      this.ofPortalParticles = true;
      this.ofPotionParticles = true;
      this.ofFireworkParticles = true;
      this.ofDrippingWaterLava = true;
      this.ofAnimatedTerrain = true;
      this.ofAnimatedTextures = true;
      bpq.setShaderPack("OFF");
      bpq.configAntialiasingLevel = 0;
      bpq.uninit();
      bpq.storeConfig();
      this.updateWaterOpacity();
      this.mc.refreshResources();
      this.saveOptions();
   }

   public void updateVSync() {
      Display.setVSyncEnabled(this.enableVsync);
   }

   private void updateWaterOpacity() {
      if (XH.isIntegratedServerRunning()) {
         XH.waterOpacityChanged = true;
      }

      biP.updateWaterOpacity(this, this.mc.world);
   }

   public void setAllAnimations(boolean p_setAllAnimations_1_) {
      int i = p_setAllAnimations_1_ ? 0 : 2;
      this.ofAnimatedWater = i;
      this.ofAnimatedLava = i;
      this.ofAnimatedFire = p_setAllAnimations_1_;
      this.ofAnimatedPortal = p_setAllAnimations_1_;
      this.ofAnimatedRedstone = p_setAllAnimations_1_;
      this.ofAnimatedExplosion = p_setAllAnimations_1_;
      this.ofAnimatedFlame = p_setAllAnimations_1_;
      this.ofAnimatedSmoke = p_setAllAnimations_1_;
      this.ofVoidParticles = p_setAllAnimations_1_;
      this.ofWaterParticles = p_setAllAnimations_1_;
      this.ofRainSplash = p_setAllAnimations_1_;
      this.ofPortalParticles = p_setAllAnimations_1_;
      this.ofPotionParticles = p_setAllAnimations_1_;
      this.ofFireworkParticles = p_setAllAnimations_1_;
      this.particleSetting = p_setAllAnimations_1_ ? 0 : 2;
      this.ofDrippingWaterLava = p_setAllAnimations_1_;
      this.ofAnimatedTerrain = p_setAllAnimations_1_;
      this.ofAnimatedTextures = p_setAllAnimations_1_;
   }

   private static int nextValue(int p_nextValue_0_, int[] p_nextValue_1_) {
      int i = indexOf(p_nextValue_0_, p_nextValue_1_);
      if (i < 0) {
         return p_nextValue_1_[0];
      } else {
         ++i;
         if (i >= p_nextValue_1_.length) {
            i = 0;
         }

         return p_nextValue_1_[i];
      }
   }

   private static int limit(int p_limit_0_, int[] p_limit_1_) {
      int i = indexOf(p_limit_0_, p_limit_1_);
      return i < 0 ? p_limit_1_[0] : p_limit_0_;
   }

   private static int indexOf(int p_indexOf_0_, int[] p_indexOf_1_) {
      for(int i = 0; i < p_indexOf_1_.length; ++i) {
         if (p_indexOf_1_[i] == p_indexOf_0_) {
            return i;
         }
      }

      return -1;
   }

   private void setForgeKeybindProperties() {
      if (bnK.KeyConflictContext_IN_GAME.exists() && bnK.ForgeKeyBinding_setKeyConflictContext.exists()) {
         Object object = bnK.getFieldValue(bnK.KeyConflictContext_IN_GAME);
         bnK.call(this.keyBindForward, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindLeft, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindBack, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindRight, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindJump, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindSneak, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindSprint, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindAttack, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindChat, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindPlayerList, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindCommand, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindTogglePerspective, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindSmoothCamera, bnK.ForgeKeyBinding_setKeyConflictContext, object);
         bnK.call(this.keyBindSwapHands, bnK.ForgeKeyBinding_setKeyConflictContext, object);
      }

   }

   public void onGuiClosed() {
      if (this.needsResourceRefresh) {
         this.mc.scheduleResourcesRefresh();
         this.needsResourceRefresh = false;
      }

   }
}
