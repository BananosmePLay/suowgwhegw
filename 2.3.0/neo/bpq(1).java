package neo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentString;
import org.apache.commons.io.IOUtils;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.ARBGeometryShader4;
import org.lwjgl.opengl.ARBShaderObjects;
import org.lwjgl.opengl.ARBVertexShader;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector4f;

public class bpq {
   static nC mc = nC.getMinecraft();
   static xz entityRenderer;
   public static boolean isInitializedOnce = false;
   public static boolean isShaderPackInitialized = false;
   public static ContextCapabilities capabilities;
   public static String glVersionString;
   public static String glVendorString;
   public static String glRendererString;
   public static boolean hasGlGenMipmap = false;
   public static int countResetDisplayLists = 0;
   private static int renderDisplayWidth = 0;
   private static int renderDisplayHeight = 0;
   public static int renderWidth = 0;
   public static int renderHeight = 0;
   public static boolean isRenderingWorld = false;
   public static boolean isRenderingSky = false;
   public static boolean isCompositeRendered = false;
   public static boolean isRenderingDfb = false;
   public static boolean isShadowPass = false;
   public static boolean isEntitiesGlowing = false;
   public static boolean isSleeping;
   private static boolean isRenderingFirstPersonHand;
   private static boolean isHandRenderedMain;
   private static boolean isHandRenderedOff;
   private static boolean skipRenderHandMain;
   private static boolean skipRenderHandOff;
   public static boolean renderItemKeepDepthMask = false;
   public static boolean itemToRenderMainTranslucent = false;
   public static boolean itemToRenderOffTranslucent = false;
   static float[] sunPosition = new float[4];
   static float[] moonPosition = new float[4];
   static float[] shadowLightPosition = new float[4];
   static float[] upPosition = new float[4];
   static float[] shadowLightPositionVector = new float[4];
   static float[] upPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   static float[] sunPosModelView = new float[]{0.0F, 100.0F, 0.0F, 0.0F};
   static float[] moonPosModelView = new float[]{0.0F, -100.0F, 0.0F, 0.0F};
   private static float[] tempMat = new float[16];
   static float clearColorR;
   static float clearColorG;
   static float clearColorB;
   static float skyColorR;
   static float skyColorG;
   static float skyColorB;
   static long worldTime = 0L;
   static long lastWorldTime = 0L;
   static long diffWorldTime = 0L;
   static float celestialAngle = 0.0F;
   static float sunAngle = 0.0F;
   static float shadowAngle = 0.0F;
   static int moonPhase = 0;
   static long systemTime = 0L;
   static long lastSystemTime = 0L;
   static long diffSystemTime = 0L;
   static int frameCounter = 0;
   static float frameTime = 0.0F;
   static float frameTimeCounter = 0.0F;
   static int systemTimeInt32 = 0;
   static float rainStrength = 0.0F;
   static float wetness = 0.0F;
   public static float wetnessHalfLife = 600.0F;
   public static float drynessHalfLife = 200.0F;
   public static float eyeBrightnessHalflife = 10.0F;
   static boolean usewetness = false;
   static int isEyeInWater = 0;
   static int eyeBrightness = 0;
   static float eyeBrightnessFadeX = 0.0F;
   static float eyeBrightnessFadeY = 0.0F;
   static float eyePosY = 0.0F;
   static float centerDepth = 0.0F;
   static float centerDepthSmooth = 0.0F;
   static float centerDepthSmoothHalflife = 1.0F;
   static boolean centerDepthSmoothEnabled = false;
   static int superSamplingLevel = 1;
   static float nightVision = 0.0F;
   static float blindness = 0.0F;
   static boolean lightmapEnabled = false;
   static boolean fogEnabled = true;
   public static int entityAttrib = 10;
   public static int midTexCoordAttrib = 11;
   public static int tangentAttrib = 12;
   public static boolean useEntityAttrib = false;
   public static boolean useMidTexCoordAttrib = false;
   public static boolean useTangentAttrib = false;
   public static boolean progUseEntityAttrib = false;
   public static boolean progUseMidTexCoordAttrib = false;
   public static boolean progUseTangentAttrib = false;
   private static boolean progArbGeometryShader4 = false;
   private static int progMaxVerticesOut = 3;
   private static boolean hasGeometryShaders = false;
   public static int atlasSizeX = 0;
   public static int atlasSizeY = 0;
   private static bpS shaderUniforms = new bpS();
   public static bpO uniform_entityColor;
   public static bpK uniform_entityId;
   public static bpK uniform_blockEntityId;
   public static bpK uniform_texture;
   public static bpK uniform_lightmap;
   public static bpK uniform_normals;
   public static bpK uniform_specular;
   public static bpK uniform_shadow;
   public static bpK uniform_watershadow;
   public static bpK uniform_shadowtex0;
   public static bpK uniform_shadowtex1;
   public static bpK uniform_depthtex0;
   public static bpK uniform_depthtex1;
   public static bpK uniform_shadowcolor;
   public static bpK uniform_shadowcolor0;
   public static bpK uniform_shadowcolor1;
   public static bpK uniform_noisetex;
   public static bpK uniform_gcolor;
   public static bpK uniform_gdepth;
   public static bpK uniform_gnormal;
   public static bpK uniform_composite;
   public static bpK uniform_gaux1;
   public static bpK uniform_gaux2;
   public static bpK uniform_gaux3;
   public static bpK uniform_gaux4;
   public static bpK uniform_colortex0;
   public static bpK uniform_colortex1;
   public static bpK uniform_colortex2;
   public static bpK uniform_colortex3;
   public static bpK uniform_colortex4;
   public static bpK uniform_colortex5;
   public static bpK uniform_colortex6;
   public static bpK uniform_colortex7;
   public static bpK uniform_gdepthtex;
   public static bpK uniform_depthtex2;
   public static bpK uniform_tex;
   public static bpK uniform_heldItemId;
   public static bpK uniform_heldBlockLightValue;
   public static bpK uniform_heldItemId2;
   public static bpK uniform_heldBlockLightValue2;
   public static bpK uniform_fogMode;
   public static bpJ uniform_fogDensity;
   public static bpN uniform_fogColor;
   public static bpN uniform_skyColor;
   public static bpK uniform_worldTime;
   public static bpK uniform_worldDay;
   public static bpK uniform_moonPhase;
   public static bpK uniform_frameCounter;
   public static bpJ uniform_frameTime;
   public static bpJ uniform_frameTimeCounter;
   public static bpJ uniform_sunAngle;
   public static bpJ uniform_shadowAngle;
   public static bpJ uniform_rainStrength;
   public static bpJ uniform_aspectRatio;
   public static bpJ uniform_viewWidth;
   public static bpJ uniform_viewHeight;
   public static bpJ uniform_near;
   public static bpJ uniform_far;
   public static bpN uniform_sunPosition;
   public static bpN uniform_moonPosition;
   public static bpN uniform_shadowLightPosition;
   public static bpN uniform_upPosition;
   public static bpN uniform_previousCameraPosition;
   public static bpN uniform_cameraPosition;
   public static bpR uniform_gbufferModelView;
   public static bpR uniform_gbufferModelViewInverse;
   public static bpR uniform_gbufferPreviousProjection;
   public static bpR uniform_gbufferProjection;
   public static bpR uniform_gbufferProjectionInverse;
   public static bpR uniform_gbufferPreviousModelView;
   public static bpR uniform_shadowProjection;
   public static bpR uniform_shadowProjectionInverse;
   public static bpR uniform_shadowModelView;
   public static bpR uniform_shadowModelViewInverse;
   public static bpJ uniform_wetness;
   public static bpJ uniform_eyeAltitude;
   public static bpM uniform_eyeBrightness;
   public static bpM uniform_eyeBrightnessSmooth;
   public static bpM uniform_terrainTextureSize;
   public static bpK uniform_terrainIconSize;
   public static bpK uniform_isEyeInWater;
   public static bpJ uniform_nightVision;
   public static bpJ uniform_blindness;
   public static bpJ uniform_screenBrightness;
   public static bpK uniform_hideGUI;
   public static bpJ uniform_centerDepthSmooth;
   public static bpM uniform_atlasSize;
   public static bpP uniform_blendFunc;
   public static bpK uniform_instanceId;
   static double previousCameraPositionX;
   static double previousCameraPositionY;
   static double previousCameraPositionZ;
   static double cameraPositionX;
   static double cameraPositionY;
   static double cameraPositionZ;
   static int cameraOffsetX;
   static int cameraOffsetZ;
   static int shadowPassInterval;
   public static boolean needResizeShadow;
   static int shadowMapWidth;
   static int shadowMapHeight;
   static int spShadowMapWidth;
   static int spShadowMapHeight;
   static float shadowMapFOV;
   static float shadowMapHalfPlane;
   static boolean shadowMapIsOrtho;
   static float shadowDistanceRenderMul;
   static int shadowPassCounter;
   static int preShadowPassThirdPersonView;
   public static boolean shouldSkipDefaultShadow;
   static boolean waterShadowEnabled;
   static final int MaxDrawBuffers = 8;
   static final int MaxColorBuffers = 8;
   static final int MaxDepthBuffers = 3;
   static final int MaxShadowColorBuffers = 8;
   static final int MaxShadowDepthBuffers = 2;
   static int usedColorBuffers;
   static int usedDepthBuffers;
   static int usedShadowColorBuffers;
   static int usedShadowDepthBuffers;
   static int usedColorAttachs;
   static int usedDrawBuffers;
   static int dfb;
   static int sfb;
   private static int[] gbuffersFormat;
   public static boolean[] gbuffersClear;
   public static Vector4f[] gbuffersClearColor;
   private static bph programs;
   public static final bpg ProgramNone;
   public static final bpg ProgramShadow;
   public static final bpg ProgramShadowSolid;
   public static final bpg ProgramShadowCutout;
   public static final bpg ProgramBasic;
   public static final bpg ProgramTextured;
   public static final bpg ProgramTexturedLit;
   public static final bpg ProgramSkyBasic;
   public static final bpg ProgramSkyTextured;
   public static final bpg ProgramClouds;
   public static final bpg ProgramTerrain;
   public static final bpg ProgramTerrainSolid;
   public static final bpg ProgramTerrainCutoutMip;
   public static final bpg ProgramTerrainCutout;
   public static final bpg ProgramDamagedBlock;
   public static final bpg ProgramBlock;
   public static final bpg ProgramBeaconBeam;
   public static final bpg ProgramItem;
   public static final bpg ProgramEntities;
   public static final bpg ProgramEntitiesGlowing;
   public static final bpg ProgramArmorGlint;
   public static final bpg ProgramSpiderEyes;
   public static final bpg ProgramHand;
   public static final bpg ProgramWeather;
   public static final bpg ProgramDeferredPre;
   public static final bpg[] ProgramsDeferred;
   public static final bpg ProgramDeferred;
   public static final bpg ProgramWater;
   public static final bpg ProgramHandWater;
   public static final bpg ProgramCompositePre;
   public static final bpg[] ProgramsComposite;
   public static final bpg ProgramComposite;
   public static final bpg ProgramFinal;
   public static final int ProgramCount;
   public static final bpg[] ProgramsAll;
   public static bpg activeProgram;
   public static int activeProgramID;
   private static bpi programStack;
   private static boolean hasDeferredPrograms;
   static IntBuffer activeDrawBuffers;
   private static int activeCompositeMipmapSetting;
   public static Properties loadedShaders;
   public static Properties shadersConfig;
   public static yR defaultTexture;
   public static boolean[] shadowHardwareFilteringEnabled;
   public static boolean[] shadowMipmapEnabled;
   public static boolean[] shadowFilterNearest;
   public static boolean[] shadowColorMipmapEnabled;
   public static boolean[] shadowColorFilterNearest;
   public static boolean configTweakBlockDamage;
   public static boolean configCloudShadow;
   public static float configHandDepthMul;
   public static float configRenderResMul;
   public static float configShadowResMul;
   public static int configTexMinFilB;
   public static int configTexMinFilN;
   public static int configTexMinFilS;
   public static int configTexMagFilB;
   public static int configTexMagFilN;
   public static int configTexMagFilS;
   public static boolean configShadowClipFrustrum;
   public static boolean configNormalMap;
   public static boolean configSpecularMap;
   public static bon configOldLighting;
   public static bon configOldHandLight;
   public static int configAntialiasingLevel;
   public static final int texMinFilRange = 3;
   public static final int texMagFilRange = 2;
   public static final String[] texMinFilDesc;
   public static final String[] texMagFilDesc;
   public static final int[] texMinFilValue;
   public static final int[] texMagFilValue;
   private static bpa shaderPack;
   public static boolean shaderPackLoaded;
   public static String currentShaderName;
   public static final String SHADER_PACK_NAME_NONE = "OFF";
   public static final String SHADER_PACK_NAME_DEFAULT = "(internal)";
   public static final String SHADER_PACKS_DIR_NAME = "shaderpacks";
   public static final String OPTIONS_FILE_NAME = "optionsshaders.txt";
   public static final File shaderPacksDir;
   static File configFile;
   private static bou[] shaderPackOptions;
   private static Set<String> shaderPackOptionSliders;
   static boG[] shaderPackProfiles;
   static Map<String, bop> shaderPackGuiScreens;
   static Map<String, blV> shaderPackProgramConditions;
   public static final String PATH_SHADERS_PROPERTIES = "/shaders/shaders.properties";
   public static bom shaderPackClouds;
   public static bon shaderPackOldLighting;
   public static bon shaderPackOldHandLight;
   public static bon shaderPackDynamicHandLight;
   public static bon shaderPackShadowTranslucent;
   public static bon shaderPackUnderwaterOverlay;
   public static bon shaderPackSun;
   public static bon shaderPackMoon;
   public static bon shaderPackVignette;
   public static bon shaderPackBackFaceSolid;
   public static bon shaderPackBackFaceCutout;
   public static bon shaderPackBackFaceCutoutMipped;
   public static bon shaderPackBackFaceTranslucent;
   public static bon shaderPackRainDepth;
   public static bon shaderPackBeaconBeamDepth;
   public static bon shaderPackSeparateAo;
   public static bon shaderPackFrustumCulling;
   private static Map<String, String> shaderPackResources;
   private static bij currentWorld;
   private static List<Integer> shaderPackDimensions;
   private static boZ[] customTexturesGbuffers;
   private static boZ[] customTexturesComposite;
   private static boZ[] customTexturesDeferred;
   private static String noiseTexturePath;
   private static bpC customUniforms;
   private static final int STAGE_GBUFFERS = 0;
   private static final int STAGE_COMPOSITE = 1;
   private static final int STAGE_DEFERRED = 2;
   private static final String[] STAGE_NAMES;
   public static final boolean enableShadersOption = true;
   private static final boolean enableShadersDebug = true;
   public static final boolean saveFinalShaders;
   public static float blockLightLevel05;
   public static float blockLightLevel06;
   public static float blockLightLevel08;
   public static float aoLevel;
   public static float sunPathRotation;
   public static float shadowAngleInterval;
   public static int fogMode;
   public static float fogDensity;
   public static float fogColorR;
   public static float fogColorG;
   public static float fogColorB;
   public static float shadowIntervalSize;
   public static int terrainIconSize;
   public static int[] terrainTextureSize;
   private static boZ noiseTexture;
   private static boolean noiseTextureEnabled;
   private static int noiseTextureResolution;
   static final int[] colorTextureImageUnit;
   private static final int bigBufferSize;
   private static final ByteBuffer bigBuffer;
   static final float[] faProjection;
   static final float[] faProjectionInverse;
   static final float[] faModelView;
   static final float[] faModelViewInverse;
   static final float[] faShadowProjection;
   static final float[] faShadowProjectionInverse;
   static final float[] faShadowModelView;
   static final float[] faShadowModelViewInverse;
   static final FloatBuffer projection;
   static final FloatBuffer projectionInverse;
   static final FloatBuffer modelView;
   static final FloatBuffer modelViewInverse;
   static final FloatBuffer shadowProjection;
   static final FloatBuffer shadowProjectionInverse;
   static final FloatBuffer shadowModelView;
   static final FloatBuffer shadowModelViewInverse;
   static final FloatBuffer previousProjection;
   static final FloatBuffer previousModelView;
   static final FloatBuffer tempMatrixDirectBuffer;
   static final FloatBuffer tempDirectFloatBuffer;
   static final IntBuffer dfbColorTextures;
   static final IntBuffer dfbDepthTextures;
   static final IntBuffer sfbColorTextures;
   static final IntBuffer sfbDepthTextures;
   static final IntBuffer dfbDrawBuffers;
   static final IntBuffer sfbDrawBuffers;
   static final IntBuffer drawBuffersNone;
   static final IntBuffer drawBuffersColorAtt0;
   static final boN dfbColorTexturesFlip;
   static Map<co, Integer> mapBlockToEntityData;
   private static final String[] formatNames;
   private static final int[] formatIds;
   private static final Pattern patternLoadEntityDataMap;
   public static int[] entityData;
   public static int entityDataIndex;

   public bpq() {
   }

   private static ByteBuffer nextByteBuffer(int size) {
      ByteBuffer bytebuffer = bigBuffer;
      int i = bytebuffer.limit();
      bytebuffer.position(i).limit(i + size);
      return bytebuffer.slice();
   }

   public static IntBuffer nextIntBuffer(int size) {
      ByteBuffer bytebuffer = bigBuffer;
      int i = bytebuffer.limit();
      bytebuffer.position(i).limit(i + size * 4);
      return bytebuffer.asIntBuffer();
   }

   private static FloatBuffer nextFloatBuffer(int size) {
      ByteBuffer bytebuffer = bigBuffer;
      int i = bytebuffer.limit();
      bytebuffer.position(i).limit(i + size * 4);
      return bytebuffer.asFloatBuffer();
   }

   private static IntBuffer[] nextIntBufferArray(int count, int size) {
      IntBuffer[] aintbuffer = new IntBuffer[count];

      for(int i = 0; i < count; ++i) {
         aintbuffer[i] = nextIntBuffer(size);
      }

      return aintbuffer;
   }

   public static void loadConfig() {
      bpx.info("Load shaders configuration.");

      try {
         if (!shaderPacksDir.exists()) {
            shaderPacksDir.mkdir();
         }
      } catch (Exception var8) {
         bpx.severe("Failed to open the shaderpacks directory: " + shaderPacksDir);
      }

      shadersConfig = new bqL();
      shadersConfig.setProperty(bog.SHADER_PACK.getPropertyKey(), "");
      if (configFile.exists()) {
         try {
            FileReader filereader = new FileReader(configFile);
            shadersConfig.load(filereader);
            filereader.close();
         } catch (Exception var7) {
         }
      }

      if (!configFile.exists()) {
         try {
            storeConfig();
         } catch (Exception var6) {
         }
      }

      bog[] aenumshaderoption = bog.values();

      for(int i = 0; i < aenumshaderoption.length; ++i) {
         bog enumshaderoption = aenumshaderoption[i];
         String s = enumshaderoption.getPropertyKey();
         String s1 = enumshaderoption.getValueDefault();
         String s2 = shadersConfig.getProperty(s, s1);
         setEnumShaderOption(enumshaderoption, s2);
      }

      loadShaderPack();
   }

   private static void setEnumShaderOption(bog eso, String str) {
      if (str == null) {
         str = eso.getValueDefault();
      }

      switch (eso) {
         case ANTIALIASING:
            configAntialiasingLevel = XH.parseInt(str, 0);
            break;
         case NORMAL_MAP:
            configNormalMap = XH.parseBoolean(str, true);
            break;
         case SPECULAR_MAP:
            configSpecularMap = XH.parseBoolean(str, true);
            break;
         case RENDER_RES_MUL:
            configRenderResMul = XH.parseFloat(str, 1.0F);
            break;
         case SHADOW_RES_MUL:
            configShadowResMul = XH.parseFloat(str, 1.0F);
            break;
         case HAND_DEPTH_MUL:
            configHandDepthMul = XH.parseFloat(str, 0.125F);
            break;
         case CLOUD_SHADOW:
            configCloudShadow = XH.parseBoolean(str, true);
            break;
         case OLD_HAND_LIGHT:
            configOldHandLight.setPropertyValue(str);
            break;
         case OLD_LIGHTING:
            configOldLighting.setPropertyValue(str);
            break;
         case SHADER_PACK:
            currentShaderName = str;
            break;
         case TWEAK_BLOCK_DAMAGE:
            configTweakBlockDamage = XH.parseBoolean(str, true);
            break;
         case SHADOW_CLIP_FRUSTRUM:
            configShadowClipFrustrum = XH.parseBoolean(str, true);
            break;
         case TEX_MIN_FIL_B:
            configTexMinFilB = XH.parseInt(str, 0);
            break;
         case TEX_MIN_FIL_N:
            configTexMinFilN = XH.parseInt(str, 0);
            break;
         case TEX_MIN_FIL_S:
            configTexMinFilS = XH.parseInt(str, 0);
            break;
         case TEX_MAG_FIL_B:
            configTexMagFilB = XH.parseInt(str, 0);
            break;
         case TEX_MAG_FIL_N:
            configTexMagFilB = XH.parseInt(str, 0);
            break;
         case TEX_MAG_FIL_S:
            configTexMagFilB = XH.parseInt(str, 0);
            break;
         default:
            throw new IllegalArgumentException("Unknown option: " + eso);
      }

   }

   public static void storeConfig() {
      bpx.info("Save shaders configuration.");
      if (shadersConfig == null) {
         shadersConfig = new bqL();
      }

      bog[] aenumshaderoption = bog.values();

      for(int i = 0; i < aenumshaderoption.length; ++i) {
         bog enumshaderoption = aenumshaderoption[i];
         String s = enumshaderoption.getPropertyKey();
         String s1 = getEnumShaderOption(enumshaderoption);
         shadersConfig.setProperty(s, s1);
      }

      try {
         FileWriter filewriter = new FileWriter(configFile);
         shadersConfig.store(filewriter, (String)null);
         filewriter.close();
      } catch (Exception var5) {
         bpx.severe("Error saving configuration: " + var5.getClass().getName() + ": " + var5.getMessage());
      }

   }

   public static String getEnumShaderOption(bog eso) {
      switch (eso) {
         case ANTIALIASING:
            return Integer.toString(configAntialiasingLevel);
         case NORMAL_MAP:
            return Boolean.toString(configNormalMap);
         case SPECULAR_MAP:
            return Boolean.toString(configSpecularMap);
         case RENDER_RES_MUL:
            return Float.toString(configRenderResMul);
         case SHADOW_RES_MUL:
            return Float.toString(configShadowResMul);
         case HAND_DEPTH_MUL:
            return Float.toString(configHandDepthMul);
         case CLOUD_SHADOW:
            return Boolean.toString(configCloudShadow);
         case OLD_HAND_LIGHT:
            return configOldHandLight.getPropertyValue();
         case OLD_LIGHTING:
            return configOldLighting.getPropertyValue();
         case SHADER_PACK:
            return currentShaderName;
         case TWEAK_BLOCK_DAMAGE:
            return Boolean.toString(configTweakBlockDamage);
         case SHADOW_CLIP_FRUSTRUM:
            return Boolean.toString(configShadowClipFrustrum);
         case TEX_MIN_FIL_B:
            return Integer.toString(configTexMinFilB);
         case TEX_MIN_FIL_N:
            return Integer.toString(configTexMinFilN);
         case TEX_MIN_FIL_S:
            return Integer.toString(configTexMinFilS);
         case TEX_MAG_FIL_B:
            return Integer.toString(configTexMagFilB);
         case TEX_MAG_FIL_N:
            return Integer.toString(configTexMagFilB);
         case TEX_MAG_FIL_S:
            return Integer.toString(configTexMagFilB);
         default:
            throw new IllegalArgumentException("Unknown option: " + eso);
      }
   }

   public static void setShaderPack(String par1name) {
      currentShaderName = par1name;
      shadersConfig.setProperty(bog.SHADER_PACK.getPropertyKey(), par1name);
      loadShaderPack();
   }

   public static void loadShaderPack() {
      boolean flag = shaderPackLoaded;
      boolean flag1 = isOldLighting();
      if (mc.renderGlobal != null) {
         mc.renderGlobal.pauseChunkUpdates();
      }

      shaderPackLoaded = false;
      if (shaderPack != null) {
         shaderPack.close();
         shaderPack = null;
         shaderPackResources.clear();
         shaderPackDimensions.clear();
         shaderPackOptions = null;
         shaderPackOptionSliders = null;
         shaderPackProfiles = null;
         shaderPackGuiScreens = null;
         shaderPackProgramConditions.clear();
         shaderPackClouds.resetValue();
         shaderPackOldHandLight.resetValue();
         shaderPackDynamicHandLight.resetValue();
         shaderPackOldLighting.resetValue();
         resetCustomTextures();
         noiseTexturePath = null;
      }

      boolean flag2 = false;
      if (XH.isAntialiasing()) {
         bpx.info("Shaders can not be loaded, Antialiasing is enabled: " + XH.getAntialiasingLevel() + "x");
         flag2 = true;
      }

      if (XH.isAnisotropicFiltering()) {
         bpx.info("Shaders can not be loaded, Anisotropic Filtering is enabled: " + XH.getAnisotropicFilterLevel() + "x");
         flag2 = true;
      }

      if (XH.isFastRender()) {
         bpx.info("Shaders can not be loaded, Fast Render is enabled.");
         flag2 = true;
      }

      String s = shadersConfig.getProperty(bog.SHADER_PACK.getPropertyKey(), "(internal)");
      if (!flag2) {
         shaderPack = getShaderPack(s);
         shaderPackLoaded = shaderPack != null;
      }

      if (shaderPackLoaded) {
         bpx.info("Loaded shaderpack: " + getShaderPackName());
      } else {
         bpx.info("No shaderpack loaded.");
         shaderPack = new bpm();
      }

      if (saveFinalShaders) {
         clearDirectory(new File(shaderPacksDir, "debug"));
      }

      loadShaderPackResources();
      loadShaderPackDimensions();
      shaderPackOptions = loadShaderPackOptions();
      loadShaderPackProperties();
      boolean flag3 = shaderPackLoaded != flag;
      boolean flag4 = isOldLighting() != flag1;
      if (flag3 || flag4) {
         zK.updateVertexFormats();
         if (bnK.LightUtil.exists()) {
            bnK.LightUtil_itemConsumer.setValue((Object)null);
            bnK.LightUtil_tessellator.setValue((Object)null);
         }

         updateBlockLightLevel();
      }

      if (mc.getResourcePackRepository() != null) {
         bjp.update();
      }

      if (mc.renderGlobal != null) {
         mc.renderGlobal.resumeChunkUpdates();
      }

      if ((flag3 || flag4) && mc.getResourceManager() != null) {
         mc.scheduleResourcesRefresh();
      }

   }

   public static bpa getShaderPack(String name) {
      if (name == null) {
         return null;
      } else {
         name = name.trim();
         if (!name.isEmpty() && !name.equals("OFF")) {
            if (name.equals("(internal)")) {
               return new bpk();
            } else {
               try {
                  File file1 = new File(shaderPacksDir, name);
                  if (file1.isDirectory()) {
                     return new bpl(name, file1);
                  } else {
                     return file1.isFile() && name.toLowerCase().endsWith(".zip") ? new bpn(name, file1) : null;
                  }
               } catch (Exception var2) {
                  Exception exception = var2;
                  exception.printStackTrace();
                  return null;
               }
            }
         } else {
            return null;
         }
      }
   }

   public static bpa getShaderPack() {
      return shaderPack;
   }

   private static void loadShaderPackDimensions() {
      shaderPackDimensions.clear();

      for(int i = -128; i <= 128; ++i) {
         String s = "/shaders/world" + i;
         if (shaderPack.hasDirectory(s)) {
            shaderPackDimensions.add(i);
         }
      }

      if (shaderPackDimensions.size() > 0) {
         Integer[] ainteger = (Integer[])((Integer[])shaderPackDimensions.toArray(new Integer[shaderPackDimensions.size()]));
         XH.dbg("[Shaders] Worlds: " + XH.arrayToString((Object[])ainteger));
      }

   }

   private static void loadShaderPackProperties() {
      shaderPackClouds.resetValue();
      shaderPackOldHandLight.resetValue();
      shaderPackDynamicHandLight.resetValue();
      shaderPackOldLighting.resetValue();
      shaderPackShadowTranslucent.resetValue();
      shaderPackUnderwaterOverlay.resetValue();
      shaderPackSun.resetValue();
      shaderPackMoon.resetValue();
      shaderPackVignette.resetValue();
      shaderPackBackFaceSolid.resetValue();
      shaderPackBackFaceCutout.resetValue();
      shaderPackBackFaceCutoutMipped.resetValue();
      shaderPackBackFaceTranslucent.resetValue();
      shaderPackRainDepth.resetValue();
      shaderPackBeaconBeamDepth.resetValue();
      shaderPackSeparateAo.resetValue();
      shaderPackFrustumCulling.resetValue();
      boe.reset();
      bpb.reset();
      boM.reset();
      customUniforms = null;

      for(int i = 0; i < ProgramsAll.length; ++i) {
         bpg program = ProgramsAll[i];
         program.resetProperties();
      }

      if (shaderPack != null) {
         boe.update(shaderPack);
         bpb.update(shaderPack);
         boM.update(shaderPack);
         String s = "/shaders/shaders.properties";

         try {
            InputStream inputstream = shaderPack.getResourceAsStream(s);
            if (inputstream == null) {
               return;
            }

            inputstream = boj.process(inputstream, s);
            Properties properties = new bqL();
            ((Properties)properties).load(inputstream);
            inputstream.close();
            shaderPackClouds.loadFrom(properties);
            shaderPackOldHandLight.loadFrom(properties);
            shaderPackDynamicHandLight.loadFrom(properties);
            shaderPackOldLighting.loadFrom(properties);
            shaderPackShadowTranslucent.loadFrom(properties);
            shaderPackUnderwaterOverlay.loadFrom(properties);
            shaderPackSun.loadFrom(properties);
            shaderPackVignette.loadFrom(properties);
            shaderPackMoon.loadFrom(properties);
            shaderPackBackFaceSolid.loadFrom(properties);
            shaderPackBackFaceCutout.loadFrom(properties);
            shaderPackBackFaceCutoutMipped.loadFrom(properties);
            shaderPackBackFaceTranslucent.loadFrom(properties);
            shaderPackRainDepth.loadFrom(properties);
            shaderPackBeaconBeamDepth.loadFrom(properties);
            shaderPackSeparateAo.loadFrom(properties);
            shaderPackFrustumCulling.loadFrom(properties);
            shaderPackOptionSliders = boE.parseOptionSliders(properties, shaderPackOptions);
            shaderPackProfiles = boE.parseProfiles(properties, shaderPackOptions);
            shaderPackGuiScreens = boE.parseGuiScreens(properties, shaderPackProfiles, shaderPackOptions);
            shaderPackProgramConditions = boE.parseProgramConditions(properties, shaderPackOptions);
            customTexturesGbuffers = loadCustomTextures(properties, 0);
            customTexturesComposite = loadCustomTextures(properties, 1);
            customTexturesDeferred = loadCustomTextures(properties, 2);
            noiseTexturePath = ((Properties)properties).getProperty("texture.noise");
            if (noiseTexturePath != null) {
               noiseTextureEnabled = true;
            }

            customUniforms = boE.parseCustomUniforms(properties);
            boE.parseAlphaStates(properties);
            boE.parseBlendStates(properties);
            boE.parseRenderScales(properties);
            boE.parseBuffersFlip(properties);
         } catch (IOException var3) {
            XH.warn("[Shaders] Error reading: " + s);
         }
      }

   }

   private static boZ[] loadCustomTextures(Properties props, int stage) {
      String s = "texture." + STAGE_NAMES[stage] + ".";
      Set set = props.keySet();
      List<boZ> list = new ArrayList();
      Iterator var5 = set.iterator();

      while(var5.hasNext()) {
         Object s11 = var5.next();
         String s1 = (String)s11;
         if (s1.startsWith(s)) {
            String s2 = bqP.removePrefix(s1, s);
            s2 = bqP.removeSuffix(s2, new String[]{".0", ".1", ".2", ".3", ".4", ".5", ".6", ".7", ".8", ".9"});
            String s3 = props.getProperty(s1).trim();
            int i = getTextureIndex(stage, s2);
            if (i < 0) {
               bpx.warning("Invalid texture name: " + s1);
            } else {
               boZ icustomtexture = loadCustomTexture(i, s3);
               if (icustomtexture != null) {
                  bpx.info("Custom texture: " + s1 + " = " + s3);
                  list.add(icustomtexture);
               }
            }
         }
      }

      if (list.size() <= 0) {
         return null;
      } else {
         boZ[] aicustomtexture = (boZ[])((boZ[])list.toArray(new boZ[list.size()]));
         return aicustomtexture;
      }
   }

   private static boZ loadCustomTexture(int textureUnit, String path) {
      if (path == null) {
         return null;
      } else {
         path = path.trim();
         if (path.indexOf(58) >= 0) {
            return loadCustomTextureLocation(textureUnit, path);
         } else {
            return path.indexOf(32) >= 0 ? loadCustomTextureRaw(textureUnit, path) : loadCustomTextureShaders(textureUnit, path);
         }
      }
   }

   private static boZ loadCustomTextureLocation(int textureUnit, String path) {
      String s = path.trim();
      int i = 0;
      if (s.startsWith("minecraft:textures/")) {
         s = bqP.addSuffixCheck(s, ".png");
         if (s.endsWith("_n.png")) {
            s = bqP.replaceSuffix(s, "_n.png", ".png");
            i = 1;
         } else if (s.endsWith("_s.png")) {
            s = bqP.replaceSuffix(s, "_s.png", ".png");
            i = 2;
         }
      }

      ResourceLocation resourcelocation = new ResourceLocation(s);
      boI customtexturelocation = new boI(textureUnit, resourcelocation, i);
      return customtexturelocation;
   }

   private static boZ loadCustomTextureRaw(int textureUnit, String line) {
      biS connectedparser = new biS("Shaders");
      String[] astring = XH.tokenize(line, " ");
      Deque<String> deque = new ArrayDeque(Arrays.asList(astring));
      String s = (String)deque.poll();
      bqc texturetype = (bqc)connectedparser.parseEnum((String)deque.poll(), bqc.values(), "texture type");
      if (texturetype == null) {
         bpx.warning("Invalid raw texture type: " + line);
         return null;
      } else {
         bpZ internalformat = (bpZ)connectedparser.parseEnum((String)deque.poll(), bpZ.values(), "internal format");
         if (internalformat == null) {
            bpx.warning("Invalid raw texture internal format: " + line);
            return null;
         } else {
            int i = false;
            int j = 0;
            int k = 0;
            int i;
            switch (texturetype) {
               case TEXTURE_1D:
                  i = connectedparser.parseInt((String)deque.poll(), -1);
                  break;
               case TEXTURE_2D:
                  i = connectedparser.parseInt((String)deque.poll(), -1);
                  j = connectedparser.parseInt((String)deque.poll(), -1);
                  break;
               case TEXTURE_3D:
                  i = connectedparser.parseInt((String)deque.poll(), -1);
                  j = connectedparser.parseInt((String)deque.poll(), -1);
                  k = connectedparser.parseInt((String)deque.poll(), -1);
                  break;
               case TEXTURE_RECTANGLE:
                  i = connectedparser.parseInt((String)deque.poll(), -1);
                  j = connectedparser.parseInt((String)deque.poll(), -1);
                  break;
               default:
                  bpx.warning("Invalid raw texture type: " + texturetype);
                  return null;
            }

            if (i >= 0 && j >= 0 && k >= 0) {
               bqa pixelformat = (bqa)connectedparser.parseEnum((String)deque.poll(), bqa.values(), "pixel format");
               if (pixelformat == null) {
                  bpx.warning("Invalid raw texture pixel format: " + line);
                  return null;
               } else {
                  bqb pixeltype = (bqb)connectedparser.parseEnum((String)deque.poll(), bqb.values(), "pixel type");
                  if (pixeltype == null) {
                     bpx.warning("Invalid raw texture pixel type: " + line);
                     return null;
                  } else if (!deque.isEmpty()) {
                     bpx.warning("Invalid raw texture, too many parameters: " + line);
                     return null;
                  } else {
                     return loadCustomTextureRaw(textureUnit, line, s, texturetype, internalformat, i, j, k, pixelformat, pixeltype);
                  }
               }
            } else {
               bpx.warning("Invalid raw texture size: " + line);
               return null;
            }
         }
      }
   }

   private static boZ loadCustomTextureRaw(int textureUnit, String line, String path, bqc type, bpZ internalFormat, int width, int height, int depth, bqa pixelFormat, bqb pixelType) {
      try {
         String s = "shaders/" + bqP.removePrefix(path, "/");
         InputStream inputstream = shaderPack.getResourceAsStream(s);
         if (inputstream == null) {
            bpx.warning("Raw texture not found: " + path);
            return null;
         } else {
            byte[] abyte = XH.readAll(inputstream);
            IOUtils.closeQuietly(inputstream);
            ByteBuffer bytebuffer = xE.createDirectByteBuffer(abyte.length);
            bytebuffer.put(abyte);
            bytebuffer.flip();
            An texturemetadatasection = bpv.loadTextureMetadataSection(s, new An(true, true));
            boK customtextureraw = new boK(type, internalFormat, width, height, depth, pixelFormat, pixelType, bytebuffer, textureUnit, texturemetadatasection.getTextureBlur(), texturemetadatasection.getTextureClamp());
            return customtextureraw;
         }
      } catch (IOException var16) {
         IOException ioexception = var16;
         bpx.warning("Error loading raw texture: " + path);
         bpx.warning("" + ioexception.getClass().getName() + ": " + ioexception.getMessage());
         return null;
      }
   }

   private static boZ loadCustomTextureShaders(int textureUnit, String path) {
      path = path.trim();
      if (path.indexOf(46) < 0) {
         path = path + ".png";
      }

      try {
         String s = "shaders/" + bqP.removePrefix(path, "/");
         InputStream inputstream = shaderPack.getResourceAsStream(s);
         if (inputstream == null) {
            bpx.warning("Texture not found: " + path);
            return null;
         } else {
            IOUtils.closeQuietly(inputstream);
            bpv simpleshadertexture = new bpv(s);
            simpleshadertexture.loadTexture(mc.getResourceManager());
            boH customtexture = new boH(textureUnit, s, simpleshadertexture);
            return customtexture;
         }
      } catch (IOException var6) {
         IOException ioexception = var6;
         bpx.warning("Error loading texture: " + path);
         bpx.warning("" + ioexception.getClass().getName() + ": " + ioexception.getMessage());
         return null;
      }
   }

   private static int getTextureIndex(int stage, String name) {
      if (stage == 0) {
         label225: {
            if (name.equals("texture")) {
               return 0;
            }

            if (name.equals("lightmap")) {
               return 1;
            }

            if (name.equals("normals")) {
               return 2;
            }

            if (name.equals("specular")) {
               return 3;
            }

            if (!name.equals("shadowtex0") && !name.equals("watershadow")) {
               if (name.equals("shadow")) {
                  return waterShadowEnabled ? 5 : 4;
               }

               if (name.equals("shadowtex1")) {
                  return 5;
               }

               if (name.equals("depthtex0")) {
                  return 6;
               }

               if (name.equals("gaux1")) {
                  return 7;
               }

               if (name.equals("gaux2")) {
                  return 8;
               }

               if (name.equals("gaux3")) {
                  return 9;
               }

               if (name.equals("gaux4")) {
                  return 10;
               }

               if (name.equals("depthtex1")) {
                  return 12;
               }

               if (!name.equals("shadowcolor0") && !name.equals("shadowcolor")) {
                  if (name.equals("shadowcolor1")) {
                     return 14;
                  }

                  if (name.equals("noisetex")) {
                     return 15;
                  }
                  break label225;
               }

               return 13;
            }

            return 4;
         }
      }

      if (stage == 1 || stage == 2) {
         if (name.equals("colortex0") || name.equals("colortex0")) {
            return 0;
         }

         if (name.equals("colortex1") || name.equals("gdepth")) {
            return 1;
         }

         if (name.equals("colortex2") || name.equals("gnormal")) {
            return 2;
         }

         if (name.equals("colortex3") || name.equals("composite")) {
            return 3;
         }

         if (name.equals("shadowtex0") || name.equals("watershadow")) {
            return 4;
         }

         if (name.equals("shadow")) {
            return waterShadowEnabled ? 5 : 4;
         }

         if (name.equals("shadowtex1")) {
            return 5;
         }

         if (name.equals("depthtex0") || name.equals("gdepthtex")) {
            return 6;
         }

         if (name.equals("colortex4") || name.equals("gaux1")) {
            return 7;
         }

         if (name.equals("colortex5") || name.equals("gaux2")) {
            return 8;
         }

         if (name.equals("colortex6") || name.equals("gaux3")) {
            return 9;
         }

         if (name.equals("colortex7") || name.equals("gaux4")) {
            return 10;
         }

         if (name.equals("depthtex1")) {
            return 11;
         }

         if (name.equals("depthtex2")) {
            return 12;
         }

         if (name.equals("shadowcolor0") || name.equals("shadowcolor")) {
            return 13;
         }

         if (name.equals("shadowcolor1")) {
            return 14;
         }

         if (name.equals("noisetex")) {
            return 15;
         }
      }

      return -1;
   }

   private static void bindCustomTextures(boZ[] cts) {
      if (cts != null) {
         for(int i = 0; i < cts.length; ++i) {
            boZ icustomtexture = cts[i];
            yh.setActiveTexture('蓀' + icustomtexture.getTextureUnit());
            int j = icustomtexture.getTextureId();
            int k = icustomtexture.getTarget();
            if (k == 3553) {
               yh.bindTexture(j);
            } else {
               GL11.glBindTexture(k, j);
            }
         }
      }

   }

   private static void resetCustomTextures() {
      deleteCustomTextures(customTexturesGbuffers);
      deleteCustomTextures(customTexturesComposite);
      deleteCustomTextures(customTexturesDeferred);
      customTexturesGbuffers = null;
      customTexturesComposite = null;
      customTexturesDeferred = null;
   }

   private static void deleteCustomTextures(boZ[] cts) {
      if (cts != null) {
         for(int i = 0; i < cts.length; ++i) {
            boZ icustomtexture = cts[i];
            icustomtexture.deleteTexture();
         }
      }

   }

   public static bou[] getShaderPackOptions(String screenName) {
      bou[] ashaderoption = (bou[])((bou[])shaderPackOptions.clone());
      if (shaderPackGuiScreens == null) {
         if (shaderPackProfiles != null) {
            bov shaderoptionprofile = new bov(shaderPackProfiles, ashaderoption);
            ashaderoption = (bou[])((bou[])XH.addObjectToArray(ashaderoption, shaderoptionprofile, 0));
         }

         ashaderoption = getVisibleOptions(ashaderoption);
         return ashaderoption;
      } else {
         String s = screenName != null ? "screen." + screenName : "screen";
         bop screenshaderoptions = (bop)shaderPackGuiScreens.get(s);
         if (screenshaderoptions == null) {
            return new bou[0];
         } else {
            bou[] ashaderoption1 = screenshaderoptions.getShaderOptions();
            List<bou> list = new ArrayList();

            for(int i = 0; i < ashaderoption1.length; ++i) {
               bou shaderoption = ashaderoption1[i];
               if (shaderoption == null) {
                  list.add((Object)null);
               } else if (shaderoption instanceof box) {
                  bou[] ashaderoption2 = getShaderOptionsRest(shaderPackGuiScreens, ashaderoption);
                  list.addAll(Arrays.asList(ashaderoption2));
               } else {
                  list.add(shaderoption);
               }
            }

            bou[] ashaderoption3 = (bou[])((bou[])list.toArray(new bou[list.size()]));
            return ashaderoption3;
         }
      }
   }

   public static int getShaderPackColumns(String screenName, int def) {
      String s = screenName != null ? "screen." + screenName : "screen";
      if (shaderPackGuiScreens == null) {
         return def;
      } else {
         bop screenshaderoptions = (bop)shaderPackGuiScreens.get(s);
         return screenshaderoptions == null ? def : screenshaderoptions.getColumns();
      }
   }

   private static bou[] getShaderOptionsRest(Map<String, bop> mapScreens, bou[] ops) {
      Set<String> set = new HashSet();
      Iterator var3 = mapScreens.keySet().iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         bop screenshaderoptions = (bop)mapScreens.get(s);
         bou[] ashaderoption = screenshaderoptions.getShaderOptions();

         for(int i = 0; i < ashaderoption.length; ++i) {
            bou shaderoption = ashaderoption[i];
            if (shaderoption != null) {
               set.add(shaderoption.getName());
            }
         }
      }

      List<bou> list = new ArrayList();

      for(int j = 0; j < ops.length; ++j) {
         bou shaderoption1 = ops[j];
         if (shaderoption1.isVisible()) {
            String s1 = shaderoption1.getName();
            if (!set.contains(s1)) {
               list.add(shaderoption1);
            }
         }
      }

      bou[] ashaderoption1 = (bou[])((bou[])list.toArray(new bou[list.size()]));
      return ashaderoption1;
   }

   public static bou getShaderOption(String name) {
      return bpt.getShaderOption(name, shaderPackOptions);
   }

   public static bou[] getShaderPackOptions() {
      return shaderPackOptions;
   }

   public static boolean isShaderPackOptionSlider(String name) {
      return shaderPackOptionSliders == null ? false : shaderPackOptionSliders.contains(name);
   }

   private static bou[] getVisibleOptions(bou[] ops) {
      List<bou> list = new ArrayList();

      for(int i = 0; i < ops.length; ++i) {
         bou shaderoption = ops[i];
         if (shaderoption.isVisible()) {
            list.add(shaderoption);
         }
      }

      bou[] ashaderoption = (bou[])((bou[])list.toArray(new bou[list.size()]));
      return ashaderoption;
   }

   public static void saveShaderPackOptions() {
      saveShaderPackOptions(shaderPackOptions, shaderPack);
   }

   private static void saveShaderPackOptions(bou[] sos, bpa sp) {
      Properties properties = new bqL();
      if (shaderPackOptions != null) {
         for(int i = 0; i < sos.length; ++i) {
            bou shaderoption = sos[i];
            if (shaderoption.isChanged() && shaderoption.isEnabled()) {
               ((Properties)properties).setProperty(shaderoption.getName(), shaderoption.getValue());
            }
         }
      }

      try {
         saveOptionProperties(sp, properties);
      } catch (IOException var5) {
         XH.warn("[Shaders] Error saving configuration for " + shaderPack.getName());
         var5.printStackTrace();
      }

   }

   private static void saveOptionProperties(bpa sp, Properties props) throws IOException {
      String s = "shaderpacks/" + sp.getName() + ".txt";
      File file1 = new File(nC.getMinecraft().gameDir, s);
      if (props.isEmpty()) {
         file1.delete();
      } else {
         FileOutputStream fileoutputstream = new FileOutputStream(file1);
         props.store(fileoutputstream, (String)null);
         fileoutputstream.flush();
         fileoutputstream.close();
      }

   }

   private static bou[] loadShaderPackOptions() {
      try {
         String[] astring = programs.getProgramNames();
         bou[] ashaderoption = boE.parseShaderPackOptions(shaderPack, astring, shaderPackDimensions);
         Properties properties = loadOptionProperties(shaderPack);

         for(int i = 0; i < ashaderoption.length; ++i) {
            bou shaderoption = ashaderoption[i];
            String s = properties.getProperty(shaderoption.getName());
            if (s != null) {
               shaderoption.resetValue();
               if (!shaderoption.setValue(s)) {
                  XH.warn("[Shaders] Invalid value, option: " + shaderoption.getName() + ", value: " + s);
               }
            }
         }

         return ashaderoption;
      } catch (IOException var6) {
         IOException ioexception = var6;
         XH.warn("[Shaders] Error reading configuration for " + shaderPack.getName());
         ioexception.printStackTrace();
         return null;
      }
   }

   private static Properties loadOptionProperties(bpa sp) throws IOException {
      Properties properties = new bqL();
      String s = "shaderpacks/" + sp.getName() + ".txt";
      File file1 = new File(nC.getMinecraft().gameDir, s);
      if (file1.exists() && file1.isFile() && file1.canRead()) {
         FileInputStream fileinputstream = new FileInputStream(file1);
         ((Properties)properties).load(fileinputstream);
         fileinputstream.close();
         return properties;
      } else {
         return properties;
      }
   }

   public static bou[] getChangedOptions(bou[] ops) {
      List<bou> list = new ArrayList();

      for(int i = 0; i < ops.length; ++i) {
         bou shaderoption = ops[i];
         if (shaderoption.isEnabled() && shaderoption.isChanged()) {
            list.add(shaderoption);
         }
      }

      bou[] ashaderoption = (bou[])((bou[])list.toArray(new bou[list.size()]));
      return ashaderoption;
   }

   private static String applyOptions(String line, bou[] ops) {
      if (ops != null && ops.length > 0) {
         for(int i = 0; i < ops.length; ++i) {
            bou shaderoption = ops[i];
            if (shaderoption.matchesLine(line)) {
               line = shaderoption.getSourceLine();
               break;
            }
         }

         return line;
      } else {
         return line;
      }
   }

   public static ArrayList listOfShaders() {
      ArrayList<String> arraylist = new ArrayList();
      arraylist.add("OFF");
      arraylist.add("(internal)");
      int i = arraylist.size();

      try {
         if (!shaderPacksDir.exists()) {
            shaderPacksDir.mkdir();
         }

         File[] afile = shaderPacksDir.listFiles();

         for(int j = 0; j < afile.length; ++j) {
            File file1 = afile[j];
            String s = file1.getName();
            if (file1.isDirectory()) {
               if (!s.equals("debug")) {
                  File file2 = new File(file1, "shaders");
                  if (file2.exists() && file2.isDirectory()) {
                     arraylist.add(s);
                  }
               }
            } else if (file1.isFile() && s.toLowerCase().endsWith(".zip")) {
               arraylist.add(s);
            }
         }
      } catch (Exception var7) {
      }

      List<String> list = arraylist.subList(i, arraylist.size());
      Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
      return arraylist;
   }

   public static int checkFramebufferStatus(String location) {
      int i = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (i != 36053) {
         System.err.format("FramebufferStatus 0x%04X at %s\n", i, location);
      }

      return i;
   }

   public static int checkGLError(String location) {
      int i = yh.glGetError();
      if (i != 0 && bmk.isEnabled(i)) {
         String s = XH.getGlErrorString(i);
         String s1 = getErrorInfo(i, location);
         String s2 = String.format("OpenGL error: %s (%s)%s, at: %s", i, s, s1, location);
         bpx.severe(s2);
         if (XH.isShowGlErrors() && bqU.isActive("ShowGlErrorShaders", 10000L)) {
            String s3 = Ax.format("of.message.openglError", i, s);
            printChat(s3);
         }
      }

      return i;
   }

   private static String getErrorInfo(int errorCode, String location) {
      StringBuilder stringbuilder = new StringBuilder();
      String s3;
      if (errorCode == 1286) {
         int i = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         String s = getFramebufferStatusText(i);
         s3 = ", fbStatus: " + i + " (" + s + ")";
         stringbuilder.append(s3);
      }

      String s2 = activeProgram.getName();
      if (s2.isEmpty()) {
         s2 = "none";
      }

      stringbuilder.append(", program: " + s2);
      bpg program = getProgramById(activeProgramID);
      if (program != activeProgram) {
         s3 = program.getName();
         if (s3.isEmpty()) {
            s3 = "none";
         }

         stringbuilder.append(" (" + s3 + ")");
      }

      if (location.equals("setDrawBuffers")) {
         stringbuilder.append(", drawBuffers: " + activeProgram.getDrawBufSettings());
      }

      return stringbuilder.toString();
   }

   private static bpg getProgramById(int programID) {
      for(int i = 0; i < ProgramsAll.length; ++i) {
         bpg program = ProgramsAll[i];
         if (program.getId() == programID) {
            return program;
         }
      }

      return ProgramNone;
   }

   private static String getFramebufferStatusText(int fbStatusCode) {
      switch (fbStatusCode) {
         case 33305:
            return "Undefined";
         case 36053:
            return "Complete";
         case 36054:
            return "Incomplete attachment";
         case 36055:
            return "Incomplete missing attachment";
         case 36059:
            return "Incomplete draw buffer";
         case 36060:
            return "Incomplete read buffer";
         case 36061:
            return "Unsupported";
         case 36182:
            return "Incomplete multisample";
         case 36264:
            return "Incomplete layer targets";
         default:
            return "Unknown";
      }
   }

   private static void printChat(String str) {
      mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(str));
   }

   private static void printChatAndLogError(String str) {
      bpx.severe(str);
      mc.ingameGUI.getChatGUI().printChatMessage(new TextComponentString(str));
   }

   public static void printIntBuffer(String title, IntBuffer buf) {
      StringBuilder stringbuilder = new StringBuilder(128);
      stringbuilder.append(title).append(" [pos ").append(buf.position()).append(" lim ").append(buf.limit()).append(" cap ").append(buf.capacity()).append(" :");
      int i = buf.limit();

      for(int j = 0; j < i; ++j) {
         stringbuilder.append(" ").append(buf.get(j));
      }

      stringbuilder.append("]");
      bpx.info(stringbuilder.toString());
   }

   public static void startup(nC mc) {
      checkShadersModInstalled();
      mc = nC.getMinecraft();
      capabilities = GLContext.getCapabilities();
      glVersionString = GL11.glGetString(7938);
      glVendorString = GL11.glGetString(7936);
      glRendererString = GL11.glGetString(7937);
      bpx.info("OpenGL Version: " + glVersionString);
      bpx.info("Vendor:  " + glVendorString);
      bpx.info("Renderer: " + glRendererString);
      bpx.info("Capabilities: " + (capabilities.OpenGL20 ? " 2.0 " : " - ") + (capabilities.OpenGL21 ? " 2.1 " : " - ") + (capabilities.OpenGL30 ? " 3.0 " : " - ") + (capabilities.OpenGL32 ? " 3.2 " : " - ") + (capabilities.OpenGL40 ? " 4.0 " : " - "));
      bpx.info("GL_MAX_DRAW_BUFFERS: " + GL11.glGetInteger(34852));
      bpx.info("GL_MAX_COLOR_ATTACHMENTS_EXT: " + GL11.glGetInteger(36063));
      bpx.info("GL_MAX_TEXTURE_IMAGE_UNITS: " + GL11.glGetInteger(34930));
      hasGlGenMipmap = capabilities.OpenGL30;
      loadConfig();
   }

   public static void updateBlockLightLevel() {
      if (isOldLighting()) {
         blockLightLevel05 = 0.5F;
         blockLightLevel06 = 0.6F;
         blockLightLevel08 = 0.8F;
      } else {
         blockLightLevel05 = 1.0F;
         blockLightLevel06 = 1.0F;
         blockLightLevel08 = 1.0F;
      }

   }

   public static boolean isOldHandLight() {
      if (!configOldHandLight.isDefault()) {
         return configOldHandLight.isTrue();
      } else {
         return !shaderPackOldHandLight.isDefault() ? shaderPackOldHandLight.isTrue() : true;
      }
   }

   public static boolean isDynamicHandLight() {
      return !shaderPackDynamicHandLight.isDefault() ? shaderPackDynamicHandLight.isTrue() : true;
   }

   public static boolean isOldLighting() {
      if (!configOldLighting.isDefault()) {
         return configOldLighting.isTrue();
      } else {
         return !shaderPackOldLighting.isDefault() ? shaderPackOldLighting.isTrue() : true;
      }
   }

   public static boolean isRenderShadowTranslucent() {
      return !shaderPackShadowTranslucent.isFalse();
   }

   public static boolean isUnderwaterOverlay() {
      return !shaderPackUnderwaterOverlay.isFalse();
   }

   public static boolean isSun() {
      return !shaderPackSun.isFalse();
   }

   public static boolean isMoon() {
      return !shaderPackMoon.isFalse();
   }

   public static boolean isVignette() {
      return !shaderPackVignette.isFalse();
   }

   public static boolean isRenderBackFace(BlockRenderLayer blockLayerIn) {
      switch (blockLayerIn) {
         case SOLID:
            return shaderPackBackFaceSolid.isTrue();
         case CUTOUT:
            return shaderPackBackFaceCutout.isTrue();
         case CUTOUT_MIPPED:
            return shaderPackBackFaceCutoutMipped.isTrue();
         case TRANSLUCENT:
            return shaderPackBackFaceTranslucent.isTrue();
         default:
            return false;
      }
   }

   public static boolean isRainDepth() {
      return shaderPackRainDepth.isTrue();
   }

   public static boolean isBeaconBeamDepth() {
      return shaderPackBeaconBeamDepth.isTrue();
   }

   public static boolean isSeparateAo() {
      return shaderPackSeparateAo.isTrue();
   }

   public static boolean isFrustumCulling() {
      return !shaderPackFrustumCulling.isFalse();
   }

   public static void init() {
      boolean flag;
      if (!isInitializedOnce) {
         isInitializedOnce = true;
         flag = true;
      } else {
         flag = false;
      }

      if (!isShaderPackInitialized) {
         checkGLError("Shaders.init pre");
         if (getShaderPackName() != null) {
         }

         if (!capabilities.OpenGL20) {
            printChatAndLogError("No OpenGL 2.0");
         }

         if (!capabilities.GL_EXT_framebuffer_object) {
            printChatAndLogError("No EXT_framebuffer_object");
         }

         dfbDrawBuffers.position(0).limit(8);
         dfbColorTextures.position(0).limit(16);
         dfbDepthTextures.position(0).limit(3);
         sfbDrawBuffers.position(0).limit(8);
         sfbDepthTextures.position(0).limit(2);
         sfbColorTextures.position(0).limit(8);
         usedColorBuffers = 4;
         usedDepthBuffers = 1;
         usedShadowColorBuffers = 0;
         usedShadowDepthBuffers = 0;
         usedColorAttachs = 1;
         usedDrawBuffers = 1;
         Arrays.fill(gbuffersFormat, 6408);
         Arrays.fill(gbuffersClear, true);
         Arrays.fill(gbuffersClearColor, (Object)null);
         Arrays.fill(shadowHardwareFilteringEnabled, false);
         Arrays.fill(shadowMipmapEnabled, false);
         Arrays.fill(shadowFilterNearest, false);
         Arrays.fill(shadowColorMipmapEnabled, false);
         Arrays.fill(shadowColorFilterNearest, false);
         centerDepthSmoothEnabled = false;
         noiseTextureEnabled = false;
         sunPathRotation = 0.0F;
         shadowIntervalSize = 2.0F;
         shadowMapWidth = 1024;
         shadowMapHeight = 1024;
         spShadowMapWidth = 1024;
         spShadowMapHeight = 1024;
         shadowMapFOV = 90.0F;
         shadowMapHalfPlane = 160.0F;
         shadowMapIsOrtho = true;
         shadowDistanceRenderMul = -1.0F;
         aoLevel = -1.0F;
         useEntityAttrib = false;
         useMidTexCoordAttrib = false;
         useTangentAttrib = false;
         waterShadowEnabled = false;
         hasGeometryShaders = false;
         updateBlockLightLevel();
         bpT.resetValues();
         shaderUniforms.reset();
         if (customUniforms != null) {
            customUniforms.reset();
         }

         boG shaderprofile = bpt.detectProfile(shaderPackProfiles, shaderPackOptions, false);
         String s = "";
         int j1;
         if (currentWorld != null) {
            j1 = currentWorld.provider.getDimensionType().getId();
            if (shaderPackDimensions.contains(j1)) {
               s = "world" + j1 + "/";
            }
         }

         for(j1 = 0; j1 < ProgramsAll.length; ++j1) {
            bpg program = ProgramsAll[j1];
            program.resetId();
            program.resetConfiguration();
            if (program.getProgramStage() != bpj.NONE) {
               String s1 = program.getName();
               String s2 = s + s1;
               boolean flag1 = true;
               if (shaderPackProgramConditions.containsKey(s2)) {
                  flag1 = flag1 && ((blV)shaderPackProgramConditions.get(s2)).eval();
               }

               if (shaderprofile != null) {
                  flag1 = flag1 && !shaderprofile.isProgramDisabled(s2);
               }

               if (!flag1) {
                  bpx.info("Program disabled: " + s2);
                  s1 = "<disabled>";
                  s2 = s + s1;
               }

               String s3 = "/shaders/" + s2;
               String s4 = s3 + ".vsh";
               String s5 = s3 + ".gsh";
               String s6 = s3 + ".fsh";
               setupProgram(program, s4, s5, s6);
               int j = program.getId();
               if (j > 0) {
                  bpx.info("Program loaded: " + s2);
               }

               initDrawBuffers(program);
               updateToggleBuffers(program);
            }
         }

         hasDeferredPrograms = false;

         for(j1 = 0; j1 < ProgramsDeferred.length; ++j1) {
            if (ProgramsDeferred[j1].getId() != 0) {
               hasDeferredPrograms = true;
               break;
            }
         }

         usedColorAttachs = usedColorBuffers;
         shadowPassInterval = usedShadowDepthBuffers > 0 ? 1 : 0;
         shouldSkipDefaultShadow = usedShadowDepthBuffers > 0;
         bpx.info("usedColorBuffers: " + usedColorBuffers);
         bpx.info("usedDepthBuffers: " + usedDepthBuffers);
         bpx.info("usedShadowColorBuffers: " + usedShadowColorBuffers);
         bpx.info("usedShadowDepthBuffers: " + usedShadowDepthBuffers);
         bpx.info("usedColorAttachs: " + usedColorAttachs);
         bpx.info("usedDrawBuffers: " + usedDrawBuffers);
         dfbDrawBuffers.position(0).limit(usedDrawBuffers);
         dfbColorTextures.position(0).limit(usedColorBuffers * 2);
         dfbColorTexturesFlip.reset();

         for(j1 = 0; j1 < usedDrawBuffers; ++j1) {
            dfbDrawBuffers.put(j1, '賠' + j1);
         }

         j1 = GL11.glGetInteger(34852);
         if (usedDrawBuffers > j1) {
            printChatAndLogError("[Shaders] Error: Not enough draw buffers, needed: " + usedDrawBuffers + ", available: " + j1);
         }

         sfbDrawBuffers.position(0).limit(usedShadowColorBuffers);

         int l1;
         for(l1 = 0; l1 < usedShadowColorBuffers; ++l1) {
            sfbDrawBuffers.put(l1, '賠' + l1);
         }

         for(l1 = 0; l1 < ProgramsAll.length; ++l1) {
            bpg program1 = ProgramsAll[l1];

            bpg program2;
            for(program2 = program1; program2.getId() == 0 && program2.getProgramBackup() != program2; program2 = program2.getProgramBackup()) {
            }

            if (program2 != program1 && program1 != ProgramShadow) {
               program1.copyFrom(program2);
            }
         }

         resize();
         resizeShadow();
         if (noiseTextureEnabled) {
            setupNoiseTexture();
         }

         if (defaultTexture == null) {
            defaultTexture = bps.createDefaultTexture();
         }

         yh.pushMatrix();
         yh.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
         preCelestialRotate();
         postCelestialRotate();
         yh.popMatrix();
         isShaderPackInitialized = true;
         loadEntityDataMap();
         resetDisplayLists();
         if (!flag) {
         }

         checkGLError("Shaders.init");
      }

   }

   private static void initDrawBuffers(bpg p) {
      int i = GL11.glGetInteger(34852);
      Arrays.fill(p.getToggleColorTextures(), false);
      if (p == ProgramFinal) {
         p.setDrawBuffers((IntBuffer)null);
      } else if (p.getId() == 0) {
         if (p == ProgramShadow) {
            p.setDrawBuffers(drawBuffersNone);
         } else {
            p.setDrawBuffers(drawBuffersColorAtt0);
         }
      } else {
         String s = p.getDrawBufSettings();
         if (s == null) {
            if (p != ProgramShadow && p != ProgramShadowSolid && p != ProgramShadowCutout) {
               p.setDrawBuffers(dfbDrawBuffers);
               usedDrawBuffers = usedColorBuffers;
               Arrays.fill(p.getToggleColorTextures(), 0, usedColorBuffers, true);
            } else {
               p.setDrawBuffers(sfbDrawBuffers);
            }
         } else {
            IntBuffer intbuffer = p.getDrawBuffersBuffer();
            int j = s.length();
            usedDrawBuffers = Math.max(usedDrawBuffers, j);
            j = Math.min(j, i);
            p.setDrawBuffers(intbuffer);
            intbuffer.limit(j);

            for(int k = 0; k < j; ++k) {
               int l = getDrawBuffer(p, s, k);
               intbuffer.put(k, l);
            }
         }
      }

   }

   private static int getDrawBuffer(bpg p, String str, int i) {
      int drawBuffer = 0;
      if (i >= str.length()) {
         return drawBuffer;
      } else {
         int ca = str.charAt(i) - 48;
         if (p == ProgramShadow) {
            if (ca >= 0 && ca <= 1) {
               drawBuffer = ca + '賠';
               usedShadowColorBuffers = Math.max(usedShadowColorBuffers, ca);
            }

            return drawBuffer;
         } else {
            if (ca >= 0 && ca <= 7) {
               p.getToggleColorTextures()[ca] = true;
               drawBuffer = ca + '賠';
               usedColorAttachs = Math.max(usedColorAttachs, ca);
               usedColorBuffers = Math.max(usedColorBuffers, ca);
            }

            return drawBuffer;
         }
      }
   }

   private static void updateToggleBuffers(bpg p) {
      boolean[] aboolean = p.getToggleColorTextures();
      Boolean[] aboolean1 = p.getBuffersFlip();

      for(int i = 0; i < aboolean1.length; ++i) {
         Boolean obool = aboolean1[i];
         if (obool != null) {
            aboolean[i] = obool;
         }
      }

   }

   public static void resetDisplayLists() {
      bpx.info("Reset model renderers");
      ++countResetDisplayLists;
      bpx.info("Reset world renderers");
      mc.renderGlobal.loadRenderers();
   }

   private static void setupProgram(bpg program, String vShaderPath, String gShaderPath, String fShaderPath) {
      checkGLError("pre setupProgram");
      int i = ARBShaderObjects.glCreateProgramObjectARB();
      checkGLError("create");
      if (i != 0) {
         progUseEntityAttrib = false;
         progUseMidTexCoordAttrib = false;
         progUseTangentAttrib = false;
         int j = createVertShader(program, vShaderPath);
         int k = createGeomShader(program, gShaderPath);
         int l = createFragShader(program, fShaderPath);
         checkGLError("create");
         boolean i;
         if (j == 0 && k == 0 && l == 0) {
            ARBShaderObjects.glDeleteObjectARB(i);
            i = false;
            program.resetId();
         } else {
            if (j != 0) {
               ARBShaderObjects.glAttachObjectARB(i, j);
               checkGLError("attach");
            }

            if (k != 0) {
               ARBShaderObjects.glAttachObjectARB(i, k);
               checkGLError("attach");
               if (progArbGeometryShader4) {
                  ARBGeometryShader4.glProgramParameteriARB(i, 36315, 4);
                  ARBGeometryShader4.glProgramParameteriARB(i, 36316, 5);
                  ARBGeometryShader4.glProgramParameteriARB(i, 36314, progMaxVerticesOut);
                  checkGLError("arbGeometryShader4");
               }

               hasGeometryShaders = true;
            }

            if (l != 0) {
               ARBShaderObjects.glAttachObjectARB(i, l);
               checkGLError("attach");
            }

            if (progUseEntityAttrib) {
               ARBVertexShader.glBindAttribLocationARB(i, entityAttrib, "mc_Entity");
               checkGLError("mc_Entity");
            }

            if (progUseMidTexCoordAttrib) {
               ARBVertexShader.glBindAttribLocationARB(i, midTexCoordAttrib, "mc_midTexCoord");
               checkGLError("mc_midTexCoord");
            }

            if (progUseTangentAttrib) {
               ARBVertexShader.glBindAttribLocationARB(i, tangentAttrib, "at_tangent");
               checkGLError("at_tangent");
            }

            ARBShaderObjects.glLinkProgramARB(i);
            if (GL20.glGetProgrami(i, 35714) != 1) {
               bpx.severe("Error linking program: " + i + " (" + program.getName() + ")");
            }

            printLogInfo(i, program.getName());
            if (j != 0) {
               ARBShaderObjects.glDetachObjectARB(i, j);
               ARBShaderObjects.glDeleteObjectARB(j);
            }

            if (k != 0) {
               ARBShaderObjects.glDetachObjectARB(i, k);
               ARBShaderObjects.glDeleteObjectARB(k);
            }

            if (l != 0) {
               ARBShaderObjects.glDetachObjectARB(i, l);
               ARBShaderObjects.glDeleteObjectARB(l);
            }

            program.setId(i);
            program.setRef(i);
            useProgram(program);
            ARBShaderObjects.glValidateProgramARB(i);
            useProgram(ProgramNone);
            printLogInfo(i, program.getName());
            int i1 = GL20.glGetProgrami(i, 35715);
            if (i1 != 1) {
               String s = "\"";
               printChatAndLogError("[Shaders] Error: Invalid program " + s + program.getName() + s);
               ARBShaderObjects.glDeleteObjectARB(i);
               i = false;
               program.resetId();
            }
         }
      }

   }

   private static int createVertShader(bpg program, String filename) {
      int i = ARBShaderObjects.glCreateShaderObjectARB(35633);
      if (i == 0) {
         return 0;
      } else {
         StringBuilder stringbuilder = new StringBuilder(131072);
         BufferedReader bufferedreader = null;

         try {
            bufferedreader = new BufferedReader(getShaderReader(filename));
         } catch (Exception var10) {
            ARBShaderObjects.glDeleteObjectARB(i);
            return 0;
         }

         bou[] ashaderoption = getChangedOptions(shaderPackOptions);
         List<String> list = new ArrayList();
         if (bufferedreader != null) {
            try {
               bufferedreader = boE.resolveIncludes(bufferedreader, filename, shaderPack, 0, list, 0);
               bok macrostate = new bok();

               while(true) {
                  String s = bufferedreader.readLine();
                  if (s == null) {
                     bufferedreader.close();
                     break;
                  }

                  s = applyOptions(s, ashaderoption);
                  stringbuilder.append(s).append('\n');
                  if (macrostate.processLine(s)) {
                     boq shaderline = boF.parseLine(s);
                     if (shaderline != null) {
                        if (shaderline.isAttribute("mc_Entity")) {
                           useEntityAttrib = true;
                           progUseEntityAttrib = true;
                        } else if (shaderline.isAttribute("mc_midTexCoord")) {
                           useMidTexCoordAttrib = true;
                           progUseMidTexCoordAttrib = true;
                        } else if (shaderline.isAttribute("at_tangent")) {
                           useTangentAttrib = true;
                           progUseTangentAttrib = true;
                        }

                        if (shaderline.isConstInt("countInstances")) {
                           program.setCountInstances(shaderline.getValueInt());
                           bpx.info("countInstances: " + program.getCountInstances());
                        }
                     }
                  }
               }
            } catch (Exception var11) {
               Exception exception = var11;
               bpx.severe("Couldn't read " + filename + "!");
               exception.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(i);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(filename, stringbuilder.toString());
         }

         ARBShaderObjects.glShaderSourceARB(i, stringbuilder);
         ARBShaderObjects.glCompileShaderARB(i);
         if (GL20.glGetShaderi(i, 35713) != 1) {
            bpx.severe("Error compiling vertex shader: " + filename);
         }

         printShaderLogInfo(i, filename, list);
         return i;
      }
   }

   private static int createGeomShader(bpg program, String filename) {
      int i = ARBShaderObjects.glCreateShaderObjectARB(36313);
      if (i == 0) {
         return 0;
      } else {
         StringBuilder stringbuilder = new StringBuilder(131072);
         BufferedReader bufferedreader = null;

         try {
            bufferedreader = new BufferedReader(getShaderReader(filename));
         } catch (Exception var11) {
            ARBShaderObjects.glDeleteObjectARB(i);
            return 0;
         }

         bou[] ashaderoption = getChangedOptions(shaderPackOptions);
         List<String> list = new ArrayList();
         progArbGeometryShader4 = false;
         progMaxVerticesOut = 3;
         if (bufferedreader != null) {
            try {
               bufferedreader = boE.resolveIncludes(bufferedreader, filename, shaderPack, 0, list, 0);
               bok macrostate = new bok();

               label60:
               while(true) {
                  boq shaderline;
                  do {
                     String s;
                     do {
                        s = bufferedreader.readLine();
                        if (s == null) {
                           bufferedreader.close();
                           break label60;
                        }

                        s = applyOptions(s, ashaderoption);
                        stringbuilder.append(s).append('\n');
                     } while(!macrostate.processLine(s));

                     shaderline = boF.parseLine(s);
                  } while(shaderline == null);

                  if (shaderline.isExtension("GL_ARB_geometry_shader4")) {
                     String s1 = XH.normalize(shaderline.getValue());
                     if (s1.equals("enable") || s1.equals("require") || s1.equals("warn")) {
                        progArbGeometryShader4 = true;
                     }
                  }

                  if (shaderline.isConstInt("maxVerticesOut")) {
                     progMaxVerticesOut = shaderline.getValueInt();
                  }
               }
            } catch (Exception var12) {
               Exception exception = var12;
               bpx.severe("Couldn't read " + filename + "!");
               exception.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(i);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(filename, stringbuilder.toString());
         }

         ARBShaderObjects.glShaderSourceARB(i, stringbuilder);
         ARBShaderObjects.glCompileShaderARB(i);
         if (GL20.glGetShaderi(i, 35713) != 1) {
            bpx.severe("Error compiling geometry shader: " + filename);
         }

         printShaderLogInfo(i, filename, list);
         return i;
      }
   }

   private static int createFragShader(bpg program, String filename) {
      int i = ARBShaderObjects.glCreateShaderObjectARB(35632);
      if (i == 0) {
         return 0;
      } else {
         StringBuilder stringbuilder = new StringBuilder(131072);
         BufferedReader bufferedreader = null;

         try {
            bufferedreader = new BufferedReader(getShaderReader(filename));
         } catch (Exception var14) {
            ARBShaderObjects.glDeleteObjectARB(i);
            return 0;
         }

         bou[] ashaderoption = getChangedOptions(shaderPackOptions);
         List<String> list = new ArrayList();
         if (bufferedreader != null) {
            try {
               bufferedreader = boE.resolveIncludes(bufferedreader, filename, shaderPack, 0, list, 0);
               bok macrostate = new bok();

               label265:
               while(true) {
                  while(true) {
                     while(true) {
                        boq shaderline;
                        do {
                           String s;
                           do {
                              s = bufferedreader.readLine();
                              if (s == null) {
                                 bufferedreader.close();
                                 break label265;
                              }

                              s = applyOptions(s, ashaderoption);
                              stringbuilder.append(s).append('\n');
                           } while(!macrostate.processLine(s));

                           shaderline = boF.parseLine(s);
                        } while(shaderline == null);

                        String s1;
                        int j;
                        if (shaderline.isUniform()) {
                           s1 = shaderline.getName();
                           if ((j = boF.getShadowDepthIndex(s1)) >= 0) {
                              usedShadowDepthBuffers = Math.max(usedShadowDepthBuffers, j + 1);
                           } else if ((j = boF.getShadowColorIndex(s1)) >= 0) {
                              usedShadowColorBuffers = Math.max(usedShadowColorBuffers, j + 1);
                           } else if ((j = boF.getDepthIndex(s1)) >= 0) {
                              usedDepthBuffers = Math.max(usedDepthBuffers, j + 1);
                           } else if (s1.equals("gdepth") && gbuffersFormat[1] == 6408) {
                              gbuffersFormat[1] = 34836;
                           } else if ((j = boF.getColorIndex(s1)) >= 0) {
                              usedColorBuffers = Math.max(usedColorBuffers, j + 1);
                           } else if (s1.equals("centerDepthSmooth")) {
                              centerDepthSmoothEnabled = true;
                           }
                        } else if (!shaderline.isConstInt("shadowMapResolution") && !shaderline.isProperty("SHADOWRES")) {
                           if (!shaderline.isConstFloat("shadowMapFov") && !shaderline.isProperty("SHADOWFOV")) {
                              if (!shaderline.isConstFloat("shadowDistance") && !shaderline.isProperty("SHADOWHPL")) {
                                 if (shaderline.isConstFloat("shadowDistanceRenderMul")) {
                                    shadowDistanceRenderMul = shaderline.getValueFloat();
                                    bpx.info("Shadow distance render mul: " + shadowDistanceRenderMul);
                                 } else if (shaderline.isConstFloat("shadowIntervalSize")) {
                                    shadowIntervalSize = shaderline.getValueFloat();
                                    bpx.info("Shadow map interval size: " + shadowIntervalSize);
                                 } else if (shaderline.isConstBool("generateShadowMipmap", true)) {
                                    Arrays.fill(shadowMipmapEnabled, true);
                                    bpx.info("Generate shadow mipmap");
                                 } else if (shaderline.isConstBool("generateShadowColorMipmap", true)) {
                                    Arrays.fill(shadowColorMipmapEnabled, true);
                                    bpx.info("Generate shadow color mipmap");
                                 } else if (shaderline.isConstBool("shadowHardwareFiltering", true)) {
                                    Arrays.fill(shadowHardwareFilteringEnabled, true);
                                    bpx.info("Hardware shadow filtering enabled.");
                                 } else if (shaderline.isConstBool("shadowHardwareFiltering0", true)) {
                                    shadowHardwareFilteringEnabled[0] = true;
                                    bpx.info("shadowHardwareFiltering0");
                                 } else if (shaderline.isConstBool("shadowHardwareFiltering1", true)) {
                                    shadowHardwareFilteringEnabled[1] = true;
                                    bpx.info("shadowHardwareFiltering1");
                                 } else if (shaderline.isConstBool("shadowtex0Mipmap", "shadowtexMipmap", true)) {
                                    shadowMipmapEnabled[0] = true;
                                    bpx.info("shadowtex0Mipmap");
                                 } else if (shaderline.isConstBool("shadowtex1Mipmap", true)) {
                                    shadowMipmapEnabled[1] = true;
                                    bpx.info("shadowtex1Mipmap");
                                 } else if (shaderline.isConstBool("shadowcolor0Mipmap", "shadowColor0Mipmap", true)) {
                                    shadowColorMipmapEnabled[0] = true;
                                    bpx.info("shadowcolor0Mipmap");
                                 } else if (shaderline.isConstBool("shadowcolor1Mipmap", "shadowColor1Mipmap", true)) {
                                    shadowColorMipmapEnabled[1] = true;
                                    bpx.info("shadowcolor1Mipmap");
                                 } else if (shaderline.isConstBool("shadowtex0Nearest", "shadowtexNearest", "shadow0MinMagNearest", true)) {
                                    shadowFilterNearest[0] = true;
                                    bpx.info("shadowtex0Nearest");
                                 } else if (shaderline.isConstBool("shadowtex1Nearest", "shadow1MinMagNearest", true)) {
                                    shadowFilterNearest[1] = true;
                                    bpx.info("shadowtex1Nearest");
                                 } else if (shaderline.isConstBool("shadowcolor0Nearest", "shadowColor0Nearest", "shadowColor0MinMagNearest", true)) {
                                    shadowColorFilterNearest[0] = true;
                                    bpx.info("shadowcolor0Nearest");
                                 } else if (shaderline.isConstBool("shadowcolor1Nearest", "shadowColor1Nearest", "shadowColor1MinMagNearest", true)) {
                                    shadowColorFilterNearest[1] = true;
                                    bpx.info("shadowcolor1Nearest");
                                 } else if (!shaderline.isConstFloat("wetnessHalflife") && !shaderline.isProperty("WETNESSHL")) {
                                    if (!shaderline.isConstFloat("drynessHalflife") && !shaderline.isProperty("DRYNESSHL")) {
                                       if (shaderline.isConstFloat("eyeBrightnessHalflife")) {
                                          eyeBrightnessHalflife = shaderline.getValueFloat();
                                          bpx.info("Eye brightness halflife: " + eyeBrightnessHalflife);
                                       } else if (shaderline.isConstFloat("centerDepthHalflife")) {
                                          centerDepthSmoothHalflife = shaderline.getValueFloat();
                                          bpx.info("Center depth halflife: " + centerDepthSmoothHalflife);
                                       } else if (shaderline.isConstFloat("sunPathRotation")) {
                                          sunPathRotation = shaderline.getValueFloat();
                                          bpx.info("Sun path rotation: " + sunPathRotation);
                                       } else if (shaderline.isConstFloat("ambientOcclusionLevel")) {
                                          aoLevel = XH.limit(shaderline.getValueFloat(), 0.0F, 1.0F);
                                          bpx.info("AO Level: " + aoLevel);
                                       } else if (shaderline.isConstInt("superSamplingLevel")) {
                                          int i1 = shaderline.getValueInt();
                                          if (i1 > 1) {
                                             bpx.info("Super sampling level: " + i1 + "x");
                                             superSamplingLevel = i1;
                                          } else {
                                             superSamplingLevel = 1;
                                          }
                                       } else if (shaderline.isConstInt("noiseTextureResolution")) {
                                          noiseTextureResolution = shaderline.getValueInt();
                                          noiseTextureEnabled = true;
                                          bpx.info("Noise texture enabled");
                                          bpx.info("Noise texture resolution: " + noiseTextureResolution);
                                       } else {
                                          int k;
                                          if (shaderline.isConstIntSuffix("Format")) {
                                             s1 = bqP.removeSuffix(shaderline.getName(), "Format");
                                             String s7 = shaderline.getValue();
                                             k = getBufferIndexFromString(s1);
                                             int l = getTextureFormatFromString(s7);
                                             if (k >= 0 && l != 0) {
                                                gbuffersFormat[k] = l;
                                                bpx.info("%s format: %s", s1, s7);
                                             }
                                          } else if (shaderline.isConstBoolSuffix("Clear", false)) {
                                             if (boF.isComposite(filename) || boF.isDeferred(filename)) {
                                                s1 = bqP.removeSuffix(shaderline.getName(), "Clear");
                                                j = getBufferIndexFromString(s1);
                                                if (j >= 0) {
                                                   gbuffersClear[j] = false;
                                                   bpx.info("%s clear disabled", s1);
                                                }
                                             }
                                          } else if (shaderline.isConstVec4Suffix("ClearColor")) {
                                             if (boF.isComposite(filename) || boF.isDeferred(filename)) {
                                                s1 = bqP.removeSuffix(shaderline.getName(), "ClearColor");
                                                j = getBufferIndexFromString(s1);
                                                if (j >= 0) {
                                                   Vector4f vector4f = shaderline.getValueVec4();
                                                   if (vector4f != null) {
                                                      gbuffersClearColor[j] = vector4f;
                                                      bpx.info("%s clear color: %s %s %s %s", s1, vector4f.getX(), vector4f.getY(), vector4f.getZ(), vector4f.getW());
                                                   } else {
                                                      bpx.warning("Invalid color value: " + shaderline.getValue());
                                                   }
                                                }
                                             }
                                          } else if (shaderline.isProperty("GAUX4FORMAT", "RGBA32F")) {
                                             gbuffersFormat[7] = 34836;
                                             bpx.info("gaux4 format : RGB32AF");
                                          } else if (shaderline.isProperty("GAUX4FORMAT", "RGB32F")) {
                                             gbuffersFormat[7] = 34837;
                                             bpx.info("gaux4 format : RGB32F");
                                          } else if (shaderline.isProperty("GAUX4FORMAT", "RGB16")) {
                                             gbuffersFormat[7] = 32852;
                                             bpx.info("gaux4 format : RGB16");
                                          } else if (shaderline.isConstBoolSuffix("MipmapEnabled", true)) {
                                             if (boF.isComposite(filename) || boF.isDeferred(filename) || boF.isFinal(filename)) {
                                                s1 = bqP.removeSuffix(shaderline.getName(), "MipmapEnabled");
                                                j = getBufferIndexFromString(s1);
                                                if (j >= 0) {
                                                   k = program.getCompositeMipmapSetting();
                                                   k |= 1 << j;
                                                   program.setCompositeMipmapSetting(k);
                                                   bpx.info("%s mipmap enabled", s1);
                                                }
                                             }
                                          } else if (shaderline.isProperty("DRAWBUFFERS")) {
                                             s1 = shaderline.getValue();
                                             if (boF.isValidDrawBuffers(s1)) {
                                                program.setDrawBufSettings(s1);
                                             } else {
                                                bpx.warning("Invalid draw buffers: " + s1);
                                             }
                                          }
                                       }
                                    } else {
                                       drynessHalfLife = shaderline.getValueFloat();
                                       bpx.info("Dryness halflife: " + drynessHalfLife);
                                    }
                                 } else {
                                    wetnessHalfLife = shaderline.getValueFloat();
                                    bpx.info("Wetness halflife: " + wetnessHalfLife);
                                 }
                              } else {
                                 shadowMapHalfPlane = shaderline.getValueFloat();
                                 shadowMapIsOrtho = true;
                                 bpx.info("Shadow map distance: " + shadowMapHalfPlane);
                              }
                           } else {
                              shadowMapFOV = shaderline.getValueFloat();
                              shadowMapIsOrtho = false;
                              bpx.info("Shadow map field of view: " + shadowMapFOV);
                           }
                        } else {
                           spShadowMapWidth = spShadowMapHeight = shaderline.getValueInt();
                           shadowMapWidth = shadowMapHeight = Math.round((float)spShadowMapWidth * configShadowResMul);
                           bpx.info("Shadow map resolution: " + spShadowMapWidth);
                        }
                     }
                  }
               }
            } catch (Exception var15) {
               Exception exception = var15;
               bpx.severe("Couldn't read " + filename + "!");
               exception.printStackTrace();
               ARBShaderObjects.glDeleteObjectARB(i);
               return 0;
            }
         }

         if (saveFinalShaders) {
            saveShader(filename, stringbuilder.toString());
         }

         ARBShaderObjects.glShaderSourceARB(i, stringbuilder);
         ARBShaderObjects.glCompileShaderARB(i);
         if (GL20.glGetShaderi(i, 35713) != 1) {
            bpx.severe("Error compiling fragment shader: " + filename);
         }

         printShaderLogInfo(i, filename, list);
         return i;
      }
   }

   private static Reader getShaderReader(String filename) {
      return new InputStreamReader(shaderPack.getResourceAsStream(filename));
   }

   public static void saveShader(String filename, String code) {
      try {
         File file1 = new File(shaderPacksDir, "debug/" + filename);
         file1.getParentFile().mkdirs();
         XH.writeFile(file1, code);
      } catch (IOException var3) {
         IOException ioexception = var3;
         XH.warn("Error saving: " + filename);
         ioexception.printStackTrace();
      }

   }

   private static void clearDirectory(File dir) {
      if (dir.exists() && dir.isDirectory()) {
         File[] afile = dir.listFiles();
         if (afile != null) {
            for(int i = 0; i < afile.length; ++i) {
               File file1 = afile[i];
               if (file1.isDirectory()) {
                  clearDirectory(file1);
               }

               file1.delete();
            }
         }
      }

   }

   private static boolean printLogInfo(int obj, String name) {
      IntBuffer intbuffer = BufferUtils.createIntBuffer(1);
      ARBShaderObjects.glGetObjectParameterARB(obj, 35716, intbuffer);
      int i = intbuffer.get();
      if (i > 1) {
         ByteBuffer bytebuffer = BufferUtils.createByteBuffer(i);
         intbuffer.flip();
         ARBShaderObjects.glGetInfoLogARB(obj, intbuffer, bytebuffer);
         byte[] abyte = new byte[i];
         bytebuffer.get(abyte);
         if (abyte[i - 1] == 0) {
            abyte[i - 1] = 10;
         }

         String s = new String(abyte, StandardCharsets.US_ASCII);
         s = bqP.trim(s, " \n\r\t");
         bpx.info("Info log: " + name + "\n" + s);
         return false;
      } else {
         return true;
      }
   }

   private static boolean printShaderLogInfo(int shader, String name, List<String> listFiles) {
      IntBuffer intbuffer = BufferUtils.createIntBuffer(1);
      int i = GL20.glGetShaderi(shader, 35716);
      if (i <= 1) {
         return true;
      } else {
         for(int j = 0; j < listFiles.size(); ++j) {
            String s = (String)listFiles.get(j);
            bpx.info("File: " + (j + 1) + " = " + s);
         }

         String s1 = GL20.glGetShaderInfoLog(shader, i);
         s1 = bqP.trim(s1, " \n\r\t");
         bpx.info("Shader info log: " + name + "\n" + s1);
         return false;
      }
   }

   public static void setDrawBuffers(IntBuffer drawBuffers) {
      if (drawBuffers == null) {
         drawBuffers = drawBuffersNone;
      }

      if (activeDrawBuffers != drawBuffers) {
         activeDrawBuffers = drawBuffers;
         GL20.glDrawBuffers(drawBuffers);
         checkGLError("setDrawBuffers");
      }

   }

   public static void useProgram(bpg program) {
      checkGLError("pre-useProgram");
      if (isShadowPass) {
         program = ProgramShadow;
      } else if (isEntitiesGlowing) {
         program = ProgramEntitiesGlowing;
      }

      if (activeProgram != program) {
         updateAlphaBlend(activeProgram, program);
         activeProgram = program;
         int i = program.getId();
         activeProgramID = i;
         ARBShaderObjects.glUseProgramObjectARB(i);
         if (checkGLError("useProgram") != 0) {
            program.setId(0);
            i = program.getId();
            activeProgramID = i;
            ARBShaderObjects.glUseProgramObjectARB(i);
         }

         shaderUniforms.setProgram(i);
         if (customUniforms != null) {
            customUniforms.setProgram(i);
         }

         if (i != 0) {
            IntBuffer intbuffer = program.getDrawBuffers();
            if (isRenderingDfb) {
               setDrawBuffers(intbuffer);
            }

            activeCompositeMipmapSetting = program.getCompositeMipmapSetting();
            switch (program.getProgramStage()) {
               case GBUFFERS:
                  setProgramUniform1i(uniform_texture, 0);
                  setProgramUniform1i(uniform_lightmap, 1);
                  setProgramUniform1i(uniform_normals, 2);
                  setProgramUniform1i(uniform_specular, 3);
                  setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                  setProgramUniform1i(uniform_watershadow, 4);
                  setProgramUniform1i(uniform_shadowtex0, 4);
                  setProgramUniform1i(uniform_shadowtex1, 5);
                  setProgramUniform1i(uniform_depthtex0, 6);
                  if (customTexturesGbuffers != null || hasDeferredPrograms) {
                     setProgramUniform1i(uniform_gaux1, 7);
                     setProgramUniform1i(uniform_gaux2, 8);
                     setProgramUniform1i(uniform_gaux3, 9);
                     setProgramUniform1i(uniform_gaux4, 10);
                  }

                  setProgramUniform1i(uniform_depthtex1, 11);
                  setProgramUniform1i(uniform_shadowcolor, 13);
                  setProgramUniform1i(uniform_shadowcolor0, 13);
                  setProgramUniform1i(uniform_shadowcolor1, 14);
                  setProgramUniform1i(uniform_noisetex, 15);
                  break;
               case DEFERRED:
               case COMPOSITE:
                  setProgramUniform1i(uniform_gcolor, 0);
                  setProgramUniform1i(uniform_gdepth, 1);
                  setProgramUniform1i(uniform_gnormal, 2);
                  setProgramUniform1i(uniform_composite, 3);
                  setProgramUniform1i(uniform_gaux1, 7);
                  setProgramUniform1i(uniform_gaux2, 8);
                  setProgramUniform1i(uniform_gaux3, 9);
                  setProgramUniform1i(uniform_gaux4, 10);
                  setProgramUniform1i(uniform_colortex0, 0);
                  setProgramUniform1i(uniform_colortex1, 1);
                  setProgramUniform1i(uniform_colortex2, 2);
                  setProgramUniform1i(uniform_colortex3, 3);
                  setProgramUniform1i(uniform_colortex4, 7);
                  setProgramUniform1i(uniform_colortex5, 8);
                  setProgramUniform1i(uniform_colortex6, 9);
                  setProgramUniform1i(uniform_colortex7, 10);
                  setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                  setProgramUniform1i(uniform_watershadow, 4);
                  setProgramUniform1i(uniform_shadowtex0, 4);
                  setProgramUniform1i(uniform_shadowtex1, 5);
                  setProgramUniform1i(uniform_gdepthtex, 6);
                  setProgramUniform1i(uniform_depthtex0, 6);
                  setProgramUniform1i(uniform_depthtex1, 11);
                  setProgramUniform1i(uniform_depthtex2, 12);
                  setProgramUniform1i(uniform_shadowcolor, 13);
                  setProgramUniform1i(uniform_shadowcolor0, 13);
                  setProgramUniform1i(uniform_shadowcolor1, 14);
                  setProgramUniform1i(uniform_noisetex, 15);
                  break;
               case SHADOW:
                  setProgramUniform1i(uniform_tex, 0);
                  setProgramUniform1i(uniform_texture, 0);
                  setProgramUniform1i(uniform_lightmap, 1);
                  setProgramUniform1i(uniform_normals, 2);
                  setProgramUniform1i(uniform_specular, 3);
                  setProgramUniform1i(uniform_shadow, waterShadowEnabled ? 5 : 4);
                  setProgramUniform1i(uniform_watershadow, 4);
                  setProgramUniform1i(uniform_shadowtex0, 4);
                  setProgramUniform1i(uniform_shadowtex1, 5);
                  if (customTexturesGbuffers != null) {
                     setProgramUniform1i(uniform_gaux1, 7);
                     setProgramUniform1i(uniform_gaux2, 8);
                     setProgramUniform1i(uniform_gaux3, 9);
                     setProgramUniform1i(uniform_gaux4, 10);
                  }

                  setProgramUniform1i(uniform_shadowcolor, 13);
                  setProgramUniform1i(uniform_shadowcolor0, 13);
                  setProgramUniform1i(uniform_shadowcolor1, 14);
                  setProgramUniform1i(uniform_noisetex, 15);
            }

            nC var10000 = mc;
            Qy var13;
            if (nC.player != null) {
               var10000 = mc;
               var13 = nC.player.getHeldItemMainhand();
            } else {
               var13 = null;
            }

            Qy itemstack = var13;
            OL item = itemstack != null ? itemstack.getItem() : null;
            int j = -1;
            co block = null;
            if (item != null) {
               j = OL.REGISTRY.getIDForObject(item);
               block = (co)co.REGISTRY.getObjectById(j);
               j = bpb.getItemAliasId(j);
            }

            int k = block != null ? block.getLightValue(block.getDefaultState()) : 0;
            var10000 = mc;
            if (nC.player != null) {
               var10000 = mc;
               var13 = nC.player.getHeldItemOffhand();
            } else {
               var13 = null;
            }

            Qy itemstack1 = var13;
            OL item1 = itemstack1 != null ? itemstack1.getItem() : null;
            int l = -1;
            co block1 = null;
            if (item1 != null) {
               l = OL.REGISTRY.getIDForObject(item1);
               block1 = (co)co.REGISTRY.getObjectById(l);
               l = bpb.getItemAliasId(l);
            }

            int i1 = block1 != null ? block1.getLightValue(block1.getDefaultState()) : 0;
            if (isOldHandLight() && i1 > k) {
               j = l;
               k = i1;
            }

            setProgramUniform1i(uniform_heldItemId, j);
            setProgramUniform1i(uniform_heldBlockLightValue, k);
            setProgramUniform1i(uniform_heldItemId2, l);
            setProgramUniform1i(uniform_heldBlockLightValue2, i1);
            setProgramUniform1i(uniform_fogMode, fogEnabled ? fogMode : 0);
            setProgramUniform1f(uniform_fogDensity, fogEnabled ? fogDensity : 0.0F);
            setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
            setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
            setProgramUniform1i(uniform_worldTime, (int)(worldTime % 24000L));
            setProgramUniform1i(uniform_worldDay, (int)(worldTime / 24000L));
            setProgramUniform1i(uniform_moonPhase, moonPhase);
            setProgramUniform1i(uniform_frameCounter, frameCounter);
            setProgramUniform1f(uniform_frameTime, frameTime);
            setProgramUniform1f(uniform_frameTimeCounter, frameTimeCounter);
            setProgramUniform1f(uniform_sunAngle, sunAngle);
            setProgramUniform1f(uniform_shadowAngle, shadowAngle);
            setProgramUniform1f(uniform_rainStrength, rainStrength);
            setProgramUniform1f(uniform_aspectRatio, (float)renderWidth / (float)renderHeight);
            setProgramUniform1f(uniform_viewWidth, (float)renderWidth);
            setProgramUniform1f(uniform_viewHeight, (float)renderHeight);
            setProgramUniform1f(uniform_near, 0.05F);
            nC var10001 = mc;
            setProgramUniform1f(uniform_far, (float)(nC.gameSettings.renderDistanceChunks * 16));
            setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
            setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
            setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
            setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
            setProgramUniform3f(uniform_previousCameraPosition, (float)previousCameraPositionX, (float)previousCameraPositionY, (float)previousCameraPositionZ);
            setProgramUniform3f(uniform_cameraPosition, (float)cameraPositionX, (float)cameraPositionY, (float)cameraPositionZ);
            setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
            setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
            setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
            setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
            setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
            setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
            if (usedShadowDepthBuffers > 0) {
               setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
               setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
               setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
               setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
            }

            setProgramUniform1f(uniform_wetness, wetness);
            setProgramUniform1f(uniform_eyeAltitude, eyePosY);
            setProgramUniform2i(uniform_eyeBrightness, eyeBrightness & '\uffff', eyeBrightness >> 16);
            setProgramUniform2i(uniform_eyeBrightnessSmooth, Math.round(eyeBrightnessFadeX), Math.round(eyeBrightnessFadeY));
            setProgramUniform2i(uniform_terrainTextureSize, terrainTextureSize[0], terrainTextureSize[1]);
            setProgramUniform1i(uniform_terrainIconSize, terrainIconSize);
            setProgramUniform1i(uniform_isEyeInWater, isEyeInWater);
            setProgramUniform1f(uniform_nightVision, nightVision);
            setProgramUniform1f(uniform_blindness, blindness);
            var10001 = mc;
            setProgramUniform1f(uniform_screenBrightness, nC.gameSettings.gammaSetting);
            var10001 = mc;
            setProgramUniform1i(uniform_hideGUI, nC.gameSettings.hideGUI ? 1 : 0);
            setProgramUniform1f(uniform_centerDepthSmooth, centerDepthSmooth);
            setProgramUniform2i(uniform_atlasSize, atlasSizeX, atlasSizeY);
            if (customUniforms != null) {
               customUniforms.update();
            }

            checkGLError("end useProgram");
         }
      }

   }

   private static void updateAlphaBlend(bpg programOld, bpg programNew) {
      if (programOld.getAlphaState() != null) {
         yh.unlockAlpha();
      }

      if (programOld.getBlendState() != null) {
         yh.unlockBlend();
      }

      bnY glalphastate = programNew.getAlphaState();
      if (glalphastate != null) {
         yh.lockAlpha(glalphastate);
      }

      bnZ glblendstate = programNew.getBlendState();
      if (glblendstate != null) {
         yh.lockBlend(glblendstate);
      }

   }

   private static void setProgramUniform1i(bpK su, int value) {
      su.setValue(value);
   }

   private static void setProgramUniform2i(bpM su, int i0, int i1) {
      su.setValue(i0, i1);
   }

   private static void setProgramUniform1f(bpJ su, float value) {
      su.setValue(value);
   }

   private static void setProgramUniform3f(bpN su, float f0, float f1, float f2) {
      su.setValue(f0, f1, f2);
   }

   private static void setProgramUniformMatrix4ARB(bpR su, boolean transpose, FloatBuffer matrix) {
      su.setValue(transpose, matrix);
   }

   public static int getBufferIndexFromString(String name) {
      if (!name.equals("colortex0") && !name.equals("gcolor")) {
         if (!name.equals("colortex1") && !name.equals("gdepth")) {
            if (!name.equals("colortex2") && !name.equals("gnormal")) {
               if (!name.equals("colortex3") && !name.equals("composite")) {
                  if (!name.equals("colortex4") && !name.equals("gaux1")) {
                     if (!name.equals("colortex5") && !name.equals("gaux2")) {
                        if (!name.equals("colortex6") && !name.equals("gaux3")) {
                           return !name.equals("colortex7") && !name.equals("gaux4") ? -1 : 7;
                        } else {
                           return 6;
                        }
                     } else {
                        return 5;
                     }
                  } else {
                     return 4;
                  }
               } else {
                  return 3;
               }
            } else {
               return 2;
            }
         } else {
            return 1;
         }
      } else {
         return 0;
      }
   }

   private static int getTextureFormatFromString(String par) {
      par = par.trim();

      for(int i = 0; i < formatNames.length; ++i) {
         String s = formatNames[i];
         if (par.equals(s)) {
            return formatIds[i];
         }
      }

      return 0;
   }

   private static void setupNoiseTexture() {
      if (noiseTexture == null && noiseTexturePath != null) {
         noiseTexture = loadCustomTexture(15, noiseTexturePath);
      }

      if (noiseTexture == null) {
         noiseTexture = new boY(noiseTextureResolution, noiseTextureResolution);
      }

   }

   private static void loadEntityDataMap() {
      mapBlockToEntityData = new IdentityHashMap(300);
      if (mapBlockToEntityData.isEmpty()) {
         Iterator var0 = co.REGISTRY.getKeys().iterator();

         while(var0.hasNext()) {
            ResourceLocation resourcelocation = (ResourceLocation)var0.next();
            co block = (co)co.REGISTRY.getObject(resourcelocation);
            int i = co.REGISTRY.getIDForObject(block);
            mapBlockToEntityData.put(block, i);
         }
      }

      BufferedReader bufferedreader = null;

      try {
         bufferedreader = new BufferedReader(new InputStreamReader(shaderPack.getResourceAsStream("/mc_Entity_x.txt")));
      } catch (Exception var8) {
      }

      if (bufferedreader != null) {
         String s1;
         try {
            while((s1 = bufferedreader.readLine()) != null) {
               Matcher matcher = patternLoadEntityDataMap.matcher(s1);
               if (matcher.matches()) {
                  String s2 = matcher.group(1);
                  String s = matcher.group(2);
                  int j = Integer.parseInt(s);
                  co block1 = co.getBlockFromName(s2);
                  if (block1 != null) {
                     mapBlockToEntityData.put(block1, j);
                  } else {
                     bpx.warning("Unknown block name %s", s2);
                  }
               } else {
                  bpx.warning("unmatched %s\n", s1);
               }
            }
         } catch (Exception var9) {
            bpx.warning("Error parsing mc_Entity_x.txt");
         }
      }

      if (bufferedreader != null) {
         try {
            bufferedreader.close();
         } catch (Exception var7) {
         }
      }

   }

   private static IntBuffer fillIntBufferZero(IntBuffer buf) {
      int i = buf.limit();

      for(int j = buf.position(); j < i; ++j) {
         buf.put(j, 0);
      }

      return buf;
   }

   public static void uninit() {
      if (isShaderPackInitialized) {
         checkGLError("Shaders.uninit pre");

         for(int i = 0; i < ProgramsAll.length; ++i) {
            bpg program = ProgramsAll[i];
            if (program.getRef() != 0) {
               ARBShaderObjects.glDeleteObjectARB(program.getRef());
               checkGLError("del programRef");
            }

            program.setRef(0);
            program.setId(0);
            program.setDrawBufSettings((String)null);
            program.setDrawBuffers((IntBuffer)null);
            program.setCompositeMipmapSetting(0);
         }

         hasDeferredPrograms = false;
         if (dfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
            dfb = 0;
            checkGLError("del dfb");
         }

         if (sfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
            sfb = 0;
            checkGLError("del sfb");
         }

         if (dfbDepthTextures != null) {
            yh.deleteTextures(dfbDepthTextures);
            fillIntBufferZero(dfbDepthTextures);
            checkGLError("del dfbDepthTextures");
         }

         if (dfbColorTextures != null) {
            yh.deleteTextures(dfbColorTextures);
            fillIntBufferZero(dfbColorTextures);
            checkGLError("del dfbTextures");
         }

         if (sfbDepthTextures != null) {
            yh.deleteTextures(sfbDepthTextures);
            fillIntBufferZero(sfbDepthTextures);
            checkGLError("del shadow depth");
         }

         if (sfbColorTextures != null) {
            yh.deleteTextures(sfbColorTextures);
            fillIntBufferZero(sfbColorTextures);
            checkGLError("del shadow color");
         }

         if (dfbDrawBuffers != null) {
            fillIntBufferZero(dfbDrawBuffers);
         }

         if (noiseTexture != null) {
            noiseTexture.deleteTexture();
            noiseTexture = null;
         }

         bpx.info("Uninit");
         shadowPassInterval = 0;
         shouldSkipDefaultShadow = false;
         isShaderPackInitialized = false;
         checkGLError("Shaders.uninit");
      }

   }

   public static void scheduleResize() {
      renderDisplayHeight = 0;
   }

   public static void scheduleResizeShadow() {
      needResizeShadow = true;
   }

   private static void resize() {
      renderDisplayWidth = mc.displayWidth;
      renderDisplayHeight = mc.displayHeight;
      renderWidth = Math.round((float)renderDisplayWidth * configRenderResMul);
      renderHeight = Math.round((float)renderDisplayHeight * configRenderResMul);
      setupFrameBuffer();
   }

   private static void resizeShadow() {
      needResizeShadow = false;
      shadowMapWidth = Math.round((float)spShadowMapWidth * configShadowResMul);
      shadowMapHeight = Math.round((float)spShadowMapHeight * configShadowResMul);
      setupShadowFrameBuffer();
   }

   private static void setupFrameBuffer() {
      if (dfb != 0) {
         EXTFramebufferObject.glDeleteFramebuffersEXT(dfb);
         yh.deleteTextures(dfbDepthTextures);
         yh.deleteTextures(dfbColorTextures);
      }

      dfb = EXTFramebufferObject.glGenFramebuffersEXT();
      GL11.glGenTextures((IntBuffer)dfbDepthTextures.clear().limit(usedDepthBuffers));
      GL11.glGenTextures((IntBuffer)dfbColorTextures.clear().limit(16));
      dfbDepthTextures.position(0);
      dfbColorTextures.position(0);
      EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
      GL20.glDrawBuffers(0);
      GL11.glReadBuffer(0);

      int i1;
      for(i1 = 0; i1 < usedDepthBuffers; ++i1) {
         yh.bindTexture(dfbDepthTextures.get(i1));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9728);
         GL11.glTexParameteri(3553, 10240, 9728);
         GL11.glTexParameteri(3553, 34891, 6409);
         GL11.glTexImage2D(3553, 0, 6402, renderWidth, renderHeight, 0, 6402, 5126, (FloatBuffer)null);
      }

      EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
      GL20.glDrawBuffers(dfbDrawBuffers);
      GL11.glReadBuffer(0);
      checkGLError("FT d");

      for(i1 = 0; i1 < usedColorBuffers; ++i1) {
         yh.bindTexture(dfbColorTexturesFlip.getA(i1));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, gbuffersFormat[i1], renderWidth, renderHeight, 0, getPixelFormat(gbuffersFormat[i1]), 33639, (ByteBuffer)null);
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + i1, 3553, dfbColorTexturesFlip.getA(i1), 0);
         checkGLError("FT c");
      }

      for(i1 = 0; i1 < usedColorBuffers; ++i1) {
         yh.bindTexture(dfbColorTexturesFlip.getB(i1));
         GL11.glTexParameteri(3553, 10242, 33071);
         GL11.glTexParameteri(3553, 10243, 33071);
         GL11.glTexParameteri(3553, 10241, 9729);
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexImage2D(3553, 0, gbuffersFormat[i1], renderWidth, renderHeight, 0, getPixelFormat(gbuffersFormat[i1]), 33639, (ByteBuffer)null);
         checkGLError("FT ca");
      }

      i1 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
      if (i1 == 36058) {
         printChatAndLogError("[Shaders] Error: Failed framebuffer incomplete formats");

         for(int j = 0; j < usedColorBuffers; ++j) {
            yh.bindTexture(dfbColorTexturesFlip.getA(j));
            GL11.glTexImage2D(3553, 0, 6408, renderWidth, renderHeight, 0, 32993, 33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + j, 3553, dfbColorTexturesFlip.getA(j), 0);
            checkGLError("FT c");
         }

         i1 = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (i1 == 36053) {
            bpx.info("complete");
         }
      }

      yh.bindTexture(0);
      if (i1 != 36053) {
         printChatAndLogError("[Shaders] Error: Failed creating framebuffer! (Status " + i1 + ")");
      } else {
         bpx.info("Framebuffer created.");
      }

   }

   private static int getPixelFormat(int internalFormat) {
      switch (internalFormat) {
         case 33333:
         case 33334:
         case 33339:
         case 33340:
         case 36208:
         case 36209:
         case 36226:
         case 36227:
            return 36251;
         default:
            return 32993;
      }
   }

   private static void setupShadowFrameBuffer() {
      if (usedShadowDepthBuffers != 0) {
         if (sfb != 0) {
            EXTFramebufferObject.glDeleteFramebuffersEXT(sfb);
            yh.deleteTextures(sfbDepthTextures);
            yh.deleteTextures(sfbColorTextures);
         }

         sfb = EXTFramebufferObject.glGenFramebuffersEXT();
         EXTFramebufferObject.glBindFramebufferEXT(36160, sfb);
         GL11.glDrawBuffer(0);
         GL11.glReadBuffer(0);
         GL11.glGenTextures((IntBuffer)sfbDepthTextures.clear().limit(usedShadowDepthBuffers));
         GL11.glGenTextures((IntBuffer)sfbColorTextures.clear().limit(usedShadowColorBuffers));
         sfbDepthTextures.position(0);
         sfbColorTextures.position(0);

         int l;
         int i1;
         for(l = 0; l < usedShadowDepthBuffers; ++l) {
            yh.bindTexture(sfbDepthTextures.get(l));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            i1 = shadowFilterNearest[l] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, i1);
            GL11.glTexParameteri(3553, 10240, i1);
            if (shadowHardwareFilteringEnabled[l]) {
               GL11.glTexParameteri(3553, 34892, 34894);
            }

            GL11.glTexImage2D(3553, 0, 6402, shadowMapWidth, shadowMapHeight, 0, 6402, 5126, (FloatBuffer)null);
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
         checkGLError("FT sd");

         for(l = 0; l < usedShadowColorBuffers; ++l) {
            yh.bindTexture(sfbColorTextures.get(l));
            GL11.glTexParameterf(3553, 10242, 33071.0F);
            GL11.glTexParameterf(3553, 10243, 33071.0F);
            i1 = shadowColorFilterNearest[l] ? 9728 : 9729;
            GL11.glTexParameteri(3553, 10241, i1);
            GL11.glTexParameteri(3553, 10240, i1);
            GL11.glTexImage2D(3553, 0, 6408, shadowMapWidth, shadowMapHeight, 0, 32993, 33639, (ByteBuffer)null);
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + l, 3553, sfbColorTextures.get(l), 0);
            checkGLError("FT sc");
         }

         yh.bindTexture(0);
         if (usedShadowColorBuffers > 0) {
            GL20.glDrawBuffers(sfbDrawBuffers);
         }

         l = EXTFramebufferObject.glCheckFramebufferStatusEXT(36160);
         if (l != 36053) {
            printChatAndLogError("[Shaders] Error: Failed creating shadow framebuffer! (Status " + l + ")");
         } else {
            bpx.info("Shadow framebuffer created.");
         }
      }

   }

   public static void beginRender(nC minecraft, float partialTicks, long finishTimeNano) {
      checkGLError("pre beginRender");
      checkWorldChanged(mc.world);
      mc = minecraft;
      mc.profiler.startSection("init");
      entityRenderer = mc.entityRenderer;
      if (!isShaderPackInitialized) {
         try {
            init();
         } catch (IllegalStateException var11) {
            IllegalStateException illegalstateexception = var11;
            if (XH.normalize(illegalstateexception.getMessage()).equals("Function is not supported")) {
               printChatAndLogError("[Shaders] Error: " + illegalstateexception.getMessage());
               illegalstateexception.printStackTrace();
               setShaderPack("OFF");
               return;
            }
         }
      }

      if (mc.displayWidth != renderDisplayWidth || mc.displayHeight != renderDisplayHeight) {
         resize();
      }

      if (needResizeShadow) {
         resizeShadow();
      }

      worldTime = mc.world.getWorldTime();
      diffWorldTime = (worldTime - lastWorldTime) % 24000L;
      if (diffWorldTime < 0L) {
         diffWorldTime += 24000L;
      }

      lastWorldTime = worldTime;
      moonPhase = mc.world.getMoonPhase();
      ++frameCounter;
      if (frameCounter >= 720720) {
         frameCounter = 0;
      }

      systemTime = System.currentTimeMillis();
      if (lastSystemTime == 0L) {
         lastSystemTime = systemTime;
      }

      diffSystemTime = systemTime - lastSystemTime;
      lastSystemTime = systemTime;
      frameTime = (float)diffSystemTime / 1000.0F;
      frameTimeCounter += frameTime;
      frameTimeCounter %= 3600.0F;
      rainStrength = minecraft.world.getRainStrength(partialTicks);
      float f = (float)diffSystemTime * 0.01F;
      float f1 = (float)Math.exp(Math.log(0.5) * (double)f / (double)(wetness < rainStrength ? drynessHalfLife : wetnessHalfLife));
      wetness = wetness * f1 + rainStrength * (1.0F - f1);
      Ig entity = mc.getRenderViewEntity();
      if (entity != null) {
         isSleeping = entity instanceof Iw && ((Iw)entity).isPlayerSleeping();
         eyePosY = (float)entity.posY * partialTicks + (float)entity.lastTickPosY * (1.0F - partialTicks);
         eyeBrightness = entity.getBrightnessForRender();
         f1 = (float)diffSystemTime * 0.01F;
         float f2 = (float)Math.exp(Math.log(0.5) * (double)f1 / (double)eyeBrightnessHalflife);
         eyeBrightnessFadeX = eyeBrightnessFadeX * f2 + (float)(eyeBrightness & '\uffff') * (1.0F - f2);
         eyeBrightnessFadeY = eyeBrightnessFadeY * f2 + (float)(eyeBrightness >> 16) * (1.0F - f2);
         in iblockstate = rF.getBlockStateAtEntityViewpoint(mc.world, entity, partialTicks);
         hM material = iblockstate.getMaterial();
         if (material == hM.WATER) {
            isEyeInWater = 1;
         } else if (material == hM.LAVA) {
            isEyeInWater = 2;
         } else {
            isEyeInWater = 0;
         }

         nC var10000 = mc;
         if (nC.player != null) {
            nightVision = 0.0F;
            var10000 = mc;
            if (nC.player.isPotionActive(NL.NIGHT_VISION)) {
               nC var10001 = mc;
               nightVision = XH.getMinecraft().entityRenderer.getNightVisionBrightness(nC.player, partialTicks);
            }

            blindness = 0.0F;
            var10000 = mc;
            if (nC.player.isPotionActive(NL.BLINDNESS)) {
               var10000 = mc;
               int i = nC.player.getActivePotionEffect(NL.BLINDNESS).getDuration();
               blindness = XH.limit((float)i / 20.0F, 0.0F, 1.0F);
            }
         }

         Vec3d vec3d = mc.world.getSkyColor(entity, partialTicks);
         vec3d = bjy.getWorldSkyColor(vec3d, currentWorld, entity, partialTicks);
         skyColorR = (float)vec3d.x;
         skyColorG = (float)vec3d.y;
         skyColorB = (float)vec3d.z;
      }

      isRenderingWorld = true;
      isCompositeRendered = false;
      isShadowPass = false;
      isHandRenderedMain = false;
      isHandRenderedOff = false;
      skipRenderHandMain = false;
      skipRenderHandOff = false;
      bindGbuffersTextures();
      previousCameraPositionX = cameraPositionX;
      previousCameraPositionY = cameraPositionY;
      previousCameraPositionZ = cameraPositionZ;
      previousProjection.position(0);
      projection.position(0);
      previousProjection.put(projection);
      previousProjection.position(0);
      projection.position(0);
      previousModelView.position(0);
      modelView.position(0);
      previousModelView.put(modelView);
      previousModelView.position(0);
      modelView.position(0);
      checkGLError("beginRender");
      bpr.renderShadowMap(entityRenderer, 0, partialTicks, finishTimeNano);
      mc.profiler.endSection();
      EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);

      for(int j = 0; j < usedColorBuffers; ++j) {
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + j, 3553, dfbColorTexturesFlip.getA(j), 0);
      }

      checkGLError("end beginRender");
   }

   private static void bindGbuffersTextures() {
      if (usedShadowDepthBuffers >= 1) {
         yh.setActiveTexture(33988);
         yh.bindTexture(sfbDepthTextures.get(0));
         if (usedShadowDepthBuffers >= 2) {
            yh.setActiveTexture(33989);
            yh.bindTexture(sfbDepthTextures.get(1));
         }
      }

      yh.setActiveTexture(33984);

      int k;
      for(k = 0; k < usedColorBuffers; ++k) {
         yh.bindTexture(dfbColorTexturesFlip.getA(k));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
         yh.bindTexture(dfbColorTexturesFlip.getB(k));
         GL11.glTexParameteri(3553, 10240, 9729);
         GL11.glTexParameteri(3553, 10241, 9729);
      }

      yh.bindTexture(0);

      for(k = 0; k < 4 && 4 + k < usedColorBuffers; ++k) {
         yh.setActiveTexture('蓇' + k);
         yh.bindTexture(dfbColorTexturesFlip.getA(4 + k));
      }

      yh.setActiveTexture(33990);
      yh.bindTexture(dfbDepthTextures.get(0));
      if (usedDepthBuffers >= 2) {
         yh.setActiveTexture(33995);
         yh.bindTexture(dfbDepthTextures.get(1));
         if (usedDepthBuffers >= 3) {
            yh.setActiveTexture(33996);
            yh.bindTexture(dfbDepthTextures.get(2));
         }
      }

      for(k = 0; k < usedShadowColorBuffers; ++k) {
         yh.setActiveTexture('蓍' + k);
         yh.bindTexture(sfbColorTextures.get(k));
      }

      if (noiseTextureEnabled) {
         yh.setActiveTexture('蓀' + noiseTexture.getTextureUnit());
         yh.bindTexture(noiseTexture.getTextureId());
      }

      bindCustomTextures(customTexturesGbuffers);
      yh.setActiveTexture(33984);
   }

   public static void checkWorldChanged(bij world) {
      if (currentWorld != world) {
         bij oldWorld = currentWorld;
         currentWorld = world;
         setCameraOffset(mc.getRenderViewEntity());
         int i = getDimensionId(oldWorld);
         int j = getDimensionId(world);
         if (j != i) {
            boolean flag = shaderPackDimensions.contains(i);
            boolean flag1 = shaderPackDimensions.contains(j);
            if (flag || flag1) {
               uninit();
            }
         }

         bpT.resetValues();
      }

   }

   private static int getDimensionId(bij world) {
      return world == null ? Integer.MIN_VALUE : world.provider.getDimensionType().getId();
   }

   public static void beginRenderPass(int pass, float partialTicks, long finishTimeNano) {
      if (!isShadowPass) {
         EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
         GL11.glViewport(0, 0, renderWidth, renderHeight);
         activeDrawBuffers = null;
         bps.bindNSTextures(defaultTexture.getMultiTexID());
         useProgram(ProgramTextured);
         checkGLError("end beginRenderPass");
      }

   }

   public static void setViewport(int vx, int vy, int vw, int vh) {
      yh.colorMask(true, true, true, true);
      if (isShadowPass) {
         GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
      } else {
         GL11.glViewport(0, 0, renderWidth, renderHeight);
         EXTFramebufferObject.glBindFramebufferEXT(36160, dfb);
         isRenderingDfb = true;
         yh.enableCull();
         yh.enableDepth();
         setDrawBuffers(drawBuffersNone);
         useProgram(ProgramTextured);
         checkGLError("beginRenderPass");
      }

   }

   public static void setFogMode(int value) {
      fogMode = value;
      if (fogEnabled) {
         setProgramUniform1i(uniform_fogMode, value);
      }

   }

   public static void setFogColor(float r, float g, float b) {
      fogColorR = r;
      fogColorG = g;
      fogColorB = b;
      setProgramUniform3f(uniform_fogColor, fogColorR, fogColorG, fogColorB);
   }

   public static void setClearColor(float red, float green, float blue, float alpha) {
      yh.clearColor(red, green, blue, alpha);
      clearColorR = red;
      clearColorG = green;
      clearColorB = blue;
   }

   public static void clearRenderBuffer() {
      if (isShadowPass) {
         checkGLError("shadow clear pre");
         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, sfbDepthTextures.get(0), 0);
         GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
         GL20.glDrawBuffers(ProgramShadow.getDrawBuffers());
         checkFramebufferStatus("shadow clear");
         GL11.glClear(16640);
         checkGLError("shadow clear");
      } else {
         checkGLError("clear pre");
         Vector4f vector4f2;
         if (gbuffersClear[0]) {
            vector4f2 = gbuffersClearColor[0];
            if (vector4f2 != null) {
               GL11.glClearColor(vector4f2.getX(), vector4f2.getY(), vector4f2.getZ(), vector4f2.getW());
            }

            if (dfbColorTexturesFlip.isChanged(0)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, dfbColorTexturesFlip.getB(0), 0);
               GL20.glDrawBuffers(36064);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36064, 3553, dfbColorTexturesFlip.getA(0), 0);
            }

            GL20.glDrawBuffers(36064);
            GL11.glClear(16384);
         }

         if (gbuffersClear[1]) {
            GL11.glClearColor(1.0F, 1.0F, 1.0F, 1.0F);
            vector4f2 = gbuffersClearColor[1];
            if (vector4f2 != null) {
               GL11.glClearColor(vector4f2.getX(), vector4f2.getY(), vector4f2.getZ(), vector4f2.getW());
            }

            if (dfbColorTexturesFlip.isChanged(1)) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, dfbColorTexturesFlip.getB(1), 0);
               GL20.glDrawBuffers(36065);
               GL11.glClear(16384);
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36065, 3553, dfbColorTexturesFlip.getA(1), 0);
            }

            GL20.glDrawBuffers(36065);
            GL11.glClear(16384);
         }

         for(int i = 2; i < usedColorBuffers; ++i) {
            if (gbuffersClear[i]) {
               GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
               Vector4f vector4f1 = gbuffersClearColor[i];
               if (vector4f1 != null) {
                  GL11.glClearColor(vector4f1.getX(), vector4f1.getY(), vector4f1.getZ(), vector4f1.getW());
               }

               if (dfbColorTexturesFlip.isChanged(i)) {
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + i, 3553, dfbColorTexturesFlip.getB(i), 0);
                  GL20.glDrawBuffers('賠' + i);
                  GL11.glClear(16384);
                  EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + i, 3553, dfbColorTexturesFlip.getA(i), 0);
               }

               GL20.glDrawBuffers('賠' + i);
               GL11.glClear(16384);
            }
         }

         setDrawBuffers(dfbDrawBuffers);
         checkFramebufferStatus("clear");
         checkGLError("clear");
      }

   }

   public static void setCamera(float partialTicks) {
      Ig entity = mc.getRenderViewEntity();
      double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
      double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
      double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
      updateCameraOffset(entity);
      cameraPositionX = d0 - (double)cameraOffsetX;
      cameraPositionY = d1;
      cameraPositionZ = d2 - (double)cameraOffsetZ;
      GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
      bpw.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
      projection.position(0);
      projectionInverse.position(0);
      GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
      bpw.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
      modelView.position(0);
      modelViewInverse.position(0);
      checkGLError("setCamera");
   }

   private static void updateCameraOffset(Ig viewEntity) {
      double d0 = Math.abs(cameraPositionX - previousCameraPositionX);
      double d1 = Math.abs(cameraPositionZ - previousCameraPositionZ);
      double d2 = Math.abs(cameraPositionX);
      double d3 = Math.abs(cameraPositionZ);
      if (d0 > 1000.0 || d1 > 1000.0 || d2 > 1000000.0 || d3 > 1000000.0) {
         setCameraOffset(viewEntity);
      }

   }

   private static void setCameraOffset(Ig viewEntity) {
      if (viewEntity == null) {
         cameraOffsetX = 0;
         cameraOffsetZ = 0;
      } else {
         cameraOffsetX = (int)viewEntity.posX / 1000 * 1000;
         cameraOffsetZ = (int)viewEntity.posZ / 1000 * 1000;
      }

   }

   public static void setCameraShadow(float partialTicks) {
      Ig entity = mc.getRenderViewEntity();
      double d0 = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)partialTicks;
      double d1 = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)partialTicks;
      double d2 = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)partialTicks;
      updateCameraOffset(entity);
      cameraPositionX = d0 - (double)cameraOffsetX;
      cameraPositionY = d1;
      cameraPositionZ = d2 - (double)cameraOffsetZ;
      GL11.glGetFloat(2983, (FloatBuffer)projection.position(0));
      bpw.invertMat4FBFA((FloatBuffer)projectionInverse.position(0), (FloatBuffer)projection.position(0), faProjectionInverse, faProjection);
      projection.position(0);
      projectionInverse.position(0);
      GL11.glGetFloat(2982, (FloatBuffer)modelView.position(0));
      bpw.invertMat4FBFA((FloatBuffer)modelViewInverse.position(0), (FloatBuffer)modelView.position(0), faModelViewInverse, faModelView);
      modelView.position(0);
      modelViewInverse.position(0);
      GL11.glViewport(0, 0, shadowMapWidth, shadowMapHeight);
      GL11.glMatrixMode(5889);
      GL11.glLoadIdentity();
      if (shadowMapIsOrtho) {
         GL11.glOrtho((double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, (double)(-shadowMapHalfPlane), (double)shadowMapHalfPlane, 0.05000000074505806, 256.0);
      } else {
         GLU.gluPerspective(shadowMapFOV, (float)shadowMapWidth / (float)shadowMapHeight, 0.05F, 256.0F);
      }

      GL11.glMatrixMode(5888);
      GL11.glLoadIdentity();
      GL11.glTranslatef(0.0F, 0.0F, -100.0F);
      GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
      celestialAngle = mc.world.getCelestialAngle(partialTicks);
      sunAngle = celestialAngle < 0.75F ? celestialAngle + 0.25F : celestialAngle - 0.75F;
      float f = celestialAngle * -360.0F;
      float f1 = shadowAngleInterval > 0.0F ? f % shadowAngleInterval - shadowAngleInterval * 0.5F : 0.0F;
      if ((double)sunAngle <= 0.5) {
         GL11.glRotatef(f - f1, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
         shadowAngle = sunAngle;
      } else {
         GL11.glRotatef(f + 180.0F - f1, 0.0F, 0.0F, 1.0F);
         GL11.glRotatef(sunPathRotation, 1.0F, 0.0F, 0.0F);
         shadowAngle = sunAngle - 0.5F;
      }

      float f9;
      float f10;
      if (shadowMapIsOrtho) {
         f9 = shadowIntervalSize;
         f10 = f9 / 2.0F;
         GL11.glTranslatef((float)d0 % f9 - f10, (float)d1 % f9 - f10, (float)d2 % f9 - f10);
      }

      f9 = sunAngle * 6.2831855F;
      f10 = (float)Math.cos((double)f9);
      float f4 = (float)Math.sin((double)f9);
      float f5 = sunPathRotation * 6.2831855F;
      float f6 = f10;
      float f7 = f4 * (float)Math.cos((double)f5);
      float f8 = f4 * (float)Math.sin((double)f5);
      if ((double)sunAngle > 0.5) {
         f6 = -f10;
         f7 = -f7;
         f8 = -f8;
      }

      shadowLightPositionVector[0] = f6;
      shadowLightPositionVector[1] = f7;
      shadowLightPositionVector[2] = f8;
      shadowLightPositionVector[3] = 0.0F;
      GL11.glGetFloat(2983, (FloatBuffer)shadowProjection.position(0));
      bpw.invertMat4FBFA((FloatBuffer)shadowProjectionInverse.position(0), (FloatBuffer)shadowProjection.position(0), faShadowProjectionInverse, faShadowProjection);
      shadowProjection.position(0);
      shadowProjectionInverse.position(0);
      GL11.glGetFloat(2982, (FloatBuffer)shadowModelView.position(0));
      bpw.invertMat4FBFA((FloatBuffer)shadowModelViewInverse.position(0), (FloatBuffer)shadowModelView.position(0), faShadowModelViewInverse, faShadowModelView);
      shadowModelView.position(0);
      shadowModelViewInverse.position(0);
      setProgramUniformMatrix4ARB(uniform_gbufferProjection, false, projection);
      setProgramUniformMatrix4ARB(uniform_gbufferProjectionInverse, false, projectionInverse);
      setProgramUniformMatrix4ARB(uniform_gbufferPreviousProjection, false, previousProjection);
      setProgramUniformMatrix4ARB(uniform_gbufferModelView, false, modelView);
      setProgramUniformMatrix4ARB(uniform_gbufferModelViewInverse, false, modelViewInverse);
      setProgramUniformMatrix4ARB(uniform_gbufferPreviousModelView, false, previousModelView);
      setProgramUniformMatrix4ARB(uniform_shadowProjection, false, shadowProjection);
      setProgramUniformMatrix4ARB(uniform_shadowProjectionInverse, false, shadowProjectionInverse);
      setProgramUniformMatrix4ARB(uniform_shadowModelView, false, shadowModelView);
      setProgramUniformMatrix4ARB(uniform_shadowModelViewInverse, false, shadowModelViewInverse);
      nC var10000 = mc;
      nC.gameSettings.thirdPersonView = 1;
      checkGLError("setCamera");
   }

   public static void preCelestialRotate() {
      GL11.glRotatef(sunPathRotation * 1.0F, 0.0F, 0.0F, 1.0F);
      checkGLError("preCelestialRotate");
   }

   public static void postCelestialRotate() {
      FloatBuffer floatbuffer = tempMatrixDirectBuffer;
      floatbuffer.clear();
      GL11.glGetFloat(2982, floatbuffer);
      floatbuffer.get(tempMat, 0, 16);
      bpw.multiplyMat4xVec4(sunPosition, tempMat, sunPosModelView);
      bpw.multiplyMat4xVec4(moonPosition, tempMat, moonPosModelView);
      System.arraycopy(shadowAngle == sunAngle ? sunPosition : moonPosition, 0, shadowLightPosition, 0, 3);
      setProgramUniform3f(uniform_sunPosition, sunPosition[0], sunPosition[1], sunPosition[2]);
      setProgramUniform3f(uniform_moonPosition, moonPosition[0], moonPosition[1], moonPosition[2]);
      setProgramUniform3f(uniform_shadowLightPosition, shadowLightPosition[0], shadowLightPosition[1], shadowLightPosition[2]);
      if (customUniforms != null) {
         customUniforms.update();
      }

      checkGLError("postCelestialRotate");
   }

   public static void setUpPosition() {
      FloatBuffer floatbuffer = tempMatrixDirectBuffer;
      floatbuffer.clear();
      GL11.glGetFloat(2982, floatbuffer);
      floatbuffer.get(tempMat, 0, 16);
      bpw.multiplyMat4xVec4(upPosition, tempMat, upPosModelView);
      setProgramUniform3f(uniform_upPosition, upPosition[0], upPosition[1], upPosition[2]);
      if (customUniforms != null) {
         customUniforms.update();
      }

   }

   public static void genCompositeMipmap() {
      if (hasGlGenMipmap) {
         for(int i = 0; i < usedColorBuffers; ++i) {
            if ((activeCompositeMipmapSetting & 1 << i) != 0) {
               yh.setActiveTexture('蓀' + colorTextureImageUnit[i]);
               GL11.glTexParameteri(3553, 10241, 9987);
               GL30.glGenerateMipmap(3553);
            }
         }

         yh.setActiveTexture(33984);
      }

   }

   public static void drawComposite() {
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      drawCompositeQuad();
      int i = activeProgram.getCountInstances();
      if (i > 1) {
         for(int j = 1; j < i; ++j) {
            uniform_instanceId.setValue(j);
            drawCompositeQuad();
         }

         uniform_instanceId.setValue(0);
      }

   }

   private static void drawCompositeQuad() {
      if (!canRenderQuads()) {
         GL11.glBegin(5);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glEnd();
      } else {
         GL11.glBegin(7);
         GL11.glTexCoord2f(0.0F, 0.0F);
         GL11.glVertex3f(0.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 0.0F);
         GL11.glVertex3f(1.0F, 0.0F, 0.0F);
         GL11.glTexCoord2f(1.0F, 1.0F);
         GL11.glVertex3f(1.0F, 1.0F, 0.0F);
         GL11.glTexCoord2f(0.0F, 1.0F);
         GL11.glVertex3f(0.0F, 1.0F, 0.0F);
         GL11.glEnd();
      }

   }

   public static void renderDeferred() {
      if (!isShadowPass) {
         boolean flag = checkBufferFlip(ProgramDeferredPre);
         if (hasDeferredPrograms) {
            checkGLError("pre-render Deferred");
            renderComposites(ProgramsDeferred, false);
            flag = true;
         }

         if (flag) {
            bindGbuffersTextures();

            for(int i = 0; i < usedColorBuffers; ++i) {
               EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + i, 3553, dfbColorTexturesFlip.getA(i), 0);
            }

            if (ProgramWater.getDrawBuffers() != null) {
               setDrawBuffers(ProgramWater.getDrawBuffers());
            } else {
               setDrawBuffers(dfbDrawBuffers);
            }

            yh.setActiveTexture(33984);
            mc.getTextureManager().bindTexture(zj.LOCATION_BLOCKS_TEXTURE);
         }
      }

   }

   public static void renderCompositeFinal() {
      if (!isShadowPass) {
         checkBufferFlip(ProgramCompositePre);
         checkGLError("pre-render CompositeFinal");
         renderComposites(ProgramsComposite, true);
      }

   }

   private static boolean checkBufferFlip(bpg program) {
      boolean flag = false;
      Boolean[] aboolean = program.getBuffersFlip();

      for(int i = 0; i < usedColorBuffers; ++i) {
         if (XH.isTrue(aboolean[i])) {
            dfbColorTexturesFlip.flip(i);
            flag = true;
         }
      }

      return flag;
   }

   private static void renderComposites(bpg[] ps, boolean renderFinal) {
      if (!isShadowPass) {
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glMatrixMode(5889);
         GL11.glPushMatrix();
         GL11.glLoadIdentity();
         GL11.glOrtho(0.0, 1.0, 0.0, 1.0, 0.0, 1.0);
         GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
         yh.enableTexture2D();
         yh.disableAlpha();
         yh.disableBlend();
         yh.enableDepth();
         yh.depthFunc(519);
         yh.depthMask(false);
         yh.disableLighting();
         if (usedShadowDepthBuffers >= 1) {
            yh.setActiveTexture(33988);
            yh.bindTexture(sfbDepthTextures.get(0));
            if (usedShadowDepthBuffers >= 2) {
               yh.setActiveTexture(33989);
               yh.bindTexture(sfbDepthTextures.get(1));
            }
         }

         int i1;
         for(i1 = 0; i1 < usedColorBuffers; ++i1) {
            yh.setActiveTexture('蓀' + colorTextureImageUnit[i1]);
            yh.bindTexture(dfbColorTexturesFlip.getA(i1));
         }

         yh.setActiveTexture(33990);
         yh.bindTexture(dfbDepthTextures.get(0));
         if (usedDepthBuffers >= 2) {
            yh.setActiveTexture(33995);
            yh.bindTexture(dfbDepthTextures.get(1));
            if (usedDepthBuffers >= 3) {
               yh.setActiveTexture(33996);
               yh.bindTexture(dfbDepthTextures.get(2));
            }
         }

         for(i1 = 0; i1 < usedShadowColorBuffers; ++i1) {
            yh.setActiveTexture('蓍' + i1);
            yh.bindTexture(sfbColorTextures.get(i1));
         }

         if (noiseTextureEnabled) {
            yh.setActiveTexture('蓀' + noiseTexture.getTextureUnit());
            yh.bindTexture(noiseTexture.getTextureId());
         }

         if (renderFinal) {
            bindCustomTextures(customTexturesComposite);
         } else {
            bindCustomTextures(customTexturesDeferred);
         }

         yh.setActiveTexture(33984);

         for(i1 = 0; i1 < usedColorBuffers; ++i1) {
            EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + i1, 3553, dfbColorTexturesFlip.getB(i1), 0);
         }

         EXTFramebufferObject.glFramebufferTexture2DEXT(36160, 36096, 3553, dfbDepthTextures.get(0), 0);
         GL20.glDrawBuffers(dfbDrawBuffers);
         checkGLError("pre-composite");

         for(i1 = 0; i1 < ps.length; ++i1) {
            bpg program = ps[i1];
            if (program.getId() != 0) {
               useProgram(program);
               checkGLError(program.getName());
               if (activeCompositeMipmapSetting != 0) {
                  genCompositeMipmap();
               }

               preDrawComposite();
               drawComposite();
               postDrawComposite();

               for(int j = 0; j < usedColorBuffers; ++j) {
                  if (program.getToggleColorTextures()[j]) {
                     dfbColorTexturesFlip.flip(j);
                     yh.setActiveTexture('蓀' + colorTextureImageUnit[j]);
                     yh.bindTexture(dfbColorTexturesFlip.getA(j));
                     EXTFramebufferObject.glFramebufferTexture2DEXT(36160, '賠' + j, 3553, dfbColorTexturesFlip.getB(j), 0);
                  }
               }

               yh.setActiveTexture(33984);
            }
         }

         checkGLError("composite");
         if (renderFinal) {
            renderFinal();
            isCompositeRendered = true;
         }

         yh.enableLighting();
         yh.enableTexture2D();
         yh.enableAlpha();
         yh.enableBlend();
         yh.depthFunc(515);
         yh.depthMask(true);
         GL11.glPopMatrix();
         GL11.glMatrixMode(5888);
         GL11.glPopMatrix();
         useProgram(ProgramNone);
      }

   }

   private static void preDrawComposite() {
      boo renderscale = activeProgram.getRenderScale();
      if (renderscale != null) {
         int i = (int)((float)renderWidth * renderscale.getOffsetX());
         int j = (int)((float)renderHeight * renderscale.getOffsetY());
         int k = (int)((float)renderWidth * renderscale.getScale());
         int l = (int)((float)renderHeight * renderscale.getScale());
         GL11.glViewport(i, j, k, l);
      }

   }

   private static void postDrawComposite() {
      boo renderscale = activeProgram.getRenderScale();
      if (renderscale != null) {
         GL11.glViewport(0, 0, renderWidth, renderHeight);
      }

   }

   private static void renderFinal() {
      isRenderingDfb = false;
      mc.getFramebuffer().bindFramebuffer(true);
      ys.glFramebufferTexture2D(ys.GL_FRAMEBUFFER, ys.GL_COLOR_ATTACHMENT0, 3553, mc.getFramebuffer().framebufferTexture, 0);
      GL11.glViewport(0, 0, mc.displayWidth, mc.displayHeight);
      if (xz.anaglyphEnable) {
         boolean flag = xz.anaglyphField != 0;
         yh.colorMask(flag, !flag, !flag, true);
      }

      yh.depthMask(true);
      GL11.glClearColor(clearColorR, clearColorG, clearColorB, 1.0F);
      GL11.glClear(16640);
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      yh.enableTexture2D();
      yh.disableAlpha();
      yh.disableBlend();
      yh.enableDepth();
      yh.depthFunc(519);
      yh.depthMask(false);
      checkGLError("pre-final");
      useProgram(ProgramFinal);
      checkGLError("final");
      if (activeCompositeMipmapSetting != 0) {
         genCompositeMipmap();
      }

      drawComposite();
      checkGLError("renderCompositeFinal");
   }

   public static void endRender() {
      if (isShadowPass) {
         checkGLError("shadow endRender");
      } else {
         if (!isCompositeRendered) {
            renderCompositeFinal();
         }

         isRenderingWorld = false;
         yh.colorMask(true, true, true, true);
         useProgram(ProgramNone);
         yz.disableStandardItemLighting();
         checkGLError("endRender end");
      }

   }

   public static void beginSky() {
      isRenderingSky = true;
      fogEnabled = true;
      setDrawBuffers(dfbDrawBuffers);
      useProgram(ProgramSkyTextured);
      pushEntity(-2, 0);
   }

   public static void setSkyColor(Vec3d v3color) {
      skyColorR = (float)v3color.x;
      skyColorG = (float)v3color.y;
      skyColorB = (float)v3color.z;
      setProgramUniform3f(uniform_skyColor, skyColorR, skyColorG, skyColorB);
   }

   public static void drawHorizon() {
      tN bufferbuilder = yN.getInstance().getBuffer();
      nC var10000 = mc;
      float f = (float)(nC.gameSettings.renderDistanceChunks * 16);
      double d0 = (double)f * 0.9238;
      double d1 = (double)f * 0.3826;
      double d2 = -d1;
      double d3 = -d0;
      double d4 = 16.0;
      double d5 = -cameraPositionY;
      bufferbuilder.begin(7, zK.POSITION);
      bufferbuilder.pos(d2, d5, d3).endVertex();
      bufferbuilder.pos(d2, d4, d3).endVertex();
      bufferbuilder.pos(d3, d4, d2).endVertex();
      bufferbuilder.pos(d3, d5, d2).endVertex();
      bufferbuilder.pos(d3, d5, d2).endVertex();
      bufferbuilder.pos(d3, d4, d2).endVertex();
      bufferbuilder.pos(d3, d4, d1).endVertex();
      bufferbuilder.pos(d3, d5, d1).endVertex();
      bufferbuilder.pos(d3, d5, d1).endVertex();
      bufferbuilder.pos(d3, d4, d1).endVertex();
      bufferbuilder.pos(d2, d4, d0).endVertex();
      bufferbuilder.pos(d2, d5, d0).endVertex();
      bufferbuilder.pos(d2, d5, d0).endVertex();
      bufferbuilder.pos(d2, d4, d0).endVertex();
      bufferbuilder.pos(d1, d4, d0).endVertex();
      bufferbuilder.pos(d1, d5, d0).endVertex();
      bufferbuilder.pos(d1, d5, d0).endVertex();
      bufferbuilder.pos(d1, d4, d0).endVertex();
      bufferbuilder.pos(d0, d4, d1).endVertex();
      bufferbuilder.pos(d0, d5, d1).endVertex();
      bufferbuilder.pos(d0, d5, d1).endVertex();
      bufferbuilder.pos(d0, d4, d1).endVertex();
      bufferbuilder.pos(d0, d4, d2).endVertex();
      bufferbuilder.pos(d0, d5, d2).endVertex();
      bufferbuilder.pos(d0, d5, d2).endVertex();
      bufferbuilder.pos(d0, d4, d2).endVertex();
      bufferbuilder.pos(d1, d4, d3).endVertex();
      bufferbuilder.pos(d1, d5, d3).endVertex();
      bufferbuilder.pos(d1, d5, d3).endVertex();
      bufferbuilder.pos(d1, d4, d3).endVertex();
      bufferbuilder.pos(d2, d4, d3).endVertex();
      bufferbuilder.pos(d2, d5, d3).endVertex();
      bufferbuilder.pos(d3, d5, d3).endVertex();
      bufferbuilder.pos(d3, d5, d0).endVertex();
      bufferbuilder.pos(d0, d5, d0).endVertex();
      bufferbuilder.pos(d0, d5, d3).endVertex();
      yN.getInstance().draw();
   }

   public static void preSkyList() {
      setUpPosition();
      GL11.glColor3f(fogColorR, fogColorG, fogColorB);
      drawHorizon();
      GL11.glColor3f(skyColorR, skyColorG, skyColorB);
   }

   public static void endSky() {
      isRenderingSky = false;
      setDrawBuffers(dfbDrawBuffers);
      useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      popEntity();
   }

   public static void beginUpdateChunks() {
      checkGLError("beginUpdateChunks1");
      checkFramebufferStatus("beginUpdateChunks1");
      if (!isShadowPass) {
         useProgram(ProgramTerrain);
      }

      checkGLError("beginUpdateChunks2");
      checkFramebufferStatus("beginUpdateChunks2");
   }

   public static void endUpdateChunks() {
      checkGLError("endUpdateChunks1");
      checkFramebufferStatus("endUpdateChunks1");
      if (!isShadowPass) {
         useProgram(ProgramTerrain);
      }

      checkGLError("endUpdateChunks2");
      checkFramebufferStatus("endUpdateChunks2");
   }

   public static boolean shouldRenderClouds(Bj gs) {
      if (!shaderPackLoaded) {
         return true;
      } else {
         checkGLError("shouldRenderClouds");
         return isShadowPass ? configCloudShadow : gs.clouds > 0;
      }
   }

   public static void beginClouds() {
      fogEnabled = true;
      pushEntity(-3, 0);
      useProgram(ProgramClouds);
   }

   public static void endClouds() {
      disableFog();
      popEntity();
      useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
   }

   public static void beginEntities() {
      if (isRenderingWorld) {
         useProgram(ProgramEntities);
      }

   }

   public static void nextEntity(Ig entity) {
      if (isRenderingWorld) {
         useProgram(ProgramEntities);
         setEntityId(entity);
      }

   }

   public static void setEntityId(Ig entity) {
      if (uniform_entityId.isDefined()) {
         int i = bqp.getEntityIdByClass(entity);
         int j = boM.getEntityAliasId(i);
         if (j >= 0) {
            i = j;
         }

         uniform_entityId.setValue(i);
      }

   }

   public static void beginSpiderEyes() {
      if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
         useProgram(ProgramSpiderEyes);
         yh.enableAlpha();
         yh.alphaFunc(516, 0.0F);
         yh.blendFunc(770, 771);
      }

   }

   public static void endSpiderEyes() {
      if (isRenderingWorld && ProgramSpiderEyes.getId() != ProgramNone.getId()) {
         useProgram(ProgramEntities);
         yh.disableAlpha();
      }

   }

   public static void endEntities() {
      if (isRenderingWorld) {
         setEntityId((Ig)null);
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }

   }

   public static void beginEntitiesGlowing() {
      if (isRenderingWorld) {
         isEntitiesGlowing = true;
      }

   }

   public static void endEntitiesGlowing() {
      if (isRenderingWorld) {
         isEntitiesGlowing = false;
      }

   }

   public static void setEntityColor(float r, float g, float b, float a) {
      if (isRenderingWorld && !isShadowPass) {
         uniform_entityColor.setValue(r, g, b, a);
      }

   }

   public static void beginLivingDamage() {
      if (isRenderingWorld) {
         bps.bindTexture(defaultTexture);
         if (!isShadowPass) {
            setDrawBuffers(drawBuffersColorAtt0);
         }
      }

   }

   public static void endLivingDamage() {
      if (isRenderingWorld && !isShadowPass) {
         setDrawBuffers(ProgramEntities.getDrawBuffers());
      }

   }

   public static void beginBlockEntities() {
      if (isRenderingWorld) {
         checkGLError("beginBlockEntities");
         useProgram(ProgramBlock);
      }

   }

   public static void nextBlockEntity(Yg tileEntity) {
      if (isRenderingWorld) {
         checkGLError("nextBlockEntity");
         useProgram(ProgramBlock);
         setBlockEntityId(tileEntity);
      }

   }

   public static void setBlockEntityId(Yg tileEntity) {
      if (uniform_blockEntityId.isDefined()) {
         int i = getBlockEntityId(tileEntity);
         uniform_blockEntityId.setValue(i);
      }

   }

   private static int getBlockEntityId(Yg tileEntity) {
      if (tileEntity == null) {
         return -1;
      } else {
         co block = tileEntity.getBlockType();
         if (block == null) {
            return 0;
         } else {
            int i = co.getIdFromBlock(block);
            int j = tileEntity.getBlockMetadata();
            int k = boe.getBlockAliasId(i, j);
            if (k >= 0) {
               i = k;
            }

            return i;
         }
      }
   }

   public static void endBlockEntities() {
      if (isRenderingWorld) {
         checkGLError("endBlockEntities");
         setBlockEntityId((Yg)null);
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
         bps.bindNSTextures(defaultTexture.getMultiTexID());
      }

   }

   public static void beginLitParticles() {
      useProgram(ProgramTexturedLit);
   }

   public static void beginParticles() {
      useProgram(ProgramTextured);
   }

   public static void endParticles() {
      useProgram(ProgramTexturedLit);
   }

   public static void readCenterDepth() {
      if (!isShadowPass && centerDepthSmoothEnabled) {
         tempDirectFloatBuffer.clear();
         GL11.glReadPixels(renderWidth / 2, renderHeight / 2, 1, 1, 6402, 5126, tempDirectFloatBuffer);
         centerDepth = tempDirectFloatBuffer.get(0);
         float f = (float)diffSystemTime * 0.01F;
         float f1 = (float)Math.exp(Math.log(0.5) * (double)f / (double)centerDepthSmoothHalflife);
         centerDepthSmooth = centerDepthSmooth * f1 + centerDepth * (1.0F - f1);
      }

   }

   public static void beginWeather() {
      if (!isShadowPass) {
         if (usedDepthBuffers >= 3) {
            yh.setActiveTexture(33996);
            GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
            yh.setActiveTexture(33984);
         }

         yh.enableDepth();
         yh.enableBlend();
         yh.blendFunc(770, 771);
         yh.enableAlpha();
         useProgram(ProgramWeather);
      }

   }

   public static void endWeather() {
      yh.disableBlend();
      useProgram(ProgramTexturedLit);
   }

   public static void preWater() {
      if (usedDepthBuffers >= 2) {
         yh.setActiveTexture(33995);
         checkGLError("pre copy depth");
         GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, renderWidth, renderHeight);
         checkGLError("copy depth");
         yh.setActiveTexture(33984);
      }

      bps.bindNSTextures(defaultTexture.getMultiTexID());
   }

   public static void beginWater() {
      if (isRenderingWorld) {
         if (!isShadowPass) {
            renderDeferred();
            useProgram(ProgramWater);
            yh.enableBlend();
            yh.depthMask(true);
         } else {
            yh.depthMask(true);
         }
      }

   }

   public static void endWater() {
      if (isRenderingWorld) {
         if (isShadowPass) {
         }

         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }

   }

   public static void applyHandDepth() {
      if ((double)configHandDepthMul != 1.0) {
         GL11.glScaled(1.0, 1.0, (double)configHandDepthMul);
      }

   }

   public static void beginHand(boolean translucent) {
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glMatrixMode(5888);
      if (translucent) {
         useProgram(ProgramHandWater);
      } else {
         useProgram(ProgramHand);
      }

      checkGLError("beginHand");
      checkFramebufferStatus("beginHand");
   }

   public static void endHand() {
      checkGLError("pre endHand");
      checkFramebufferStatus("pre endHand");
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      yh.blendFunc(770, 771);
      checkGLError("endHand");
   }

   public static void beginFPOverlay() {
      yh.disableLighting();
      yh.disableBlend();
   }

   public static void endFPOverlay() {
   }

   public static void glEnableWrapper(int cap) {
      GL11.glEnable(cap);
      if (cap == 3553) {
         enableTexture2D();
      } else if (cap == 2912) {
         enableFog();
      }

   }

   public static void glDisableWrapper(int cap) {
      GL11.glDisable(cap);
      if (cap == 3553) {
         disableTexture2D();
      } else if (cap == 2912) {
         disableFog();
      }

   }

   public static void sglEnableT2D(int cap) {
      GL11.glEnable(cap);
      enableTexture2D();
   }

   public static void sglDisableT2D(int cap) {
      GL11.glDisable(cap);
      disableTexture2D();
   }

   public static void sglEnableFog(int cap) {
      GL11.glEnable(cap);
      enableFog();
   }

   public static void sglDisableFog(int cap) {
      GL11.glDisable(cap);
      disableFog();
   }

   public static void enableTexture2D() {
      if (isRenderingSky) {
         useProgram(ProgramSkyTextured);
      } else if (activeProgram == ProgramBasic) {
         useProgram(lightmapEnabled ? ProgramTexturedLit : ProgramTextured);
      }

   }

   public static void disableTexture2D() {
      if (isRenderingSky) {
         useProgram(ProgramSkyBasic);
      } else if (activeProgram == ProgramTextured || activeProgram == ProgramTexturedLit) {
         useProgram(ProgramBasic);
      }

   }

   public static void pushProgram() {
      programStack.push(activeProgram);
   }

   public static void popProgram() {
      bpg program = programStack.pop();
      useProgram(program);
   }

   public static void beginLeash() {
      pushProgram();
      useProgram(ProgramBasic);
   }

   public static void endLeash() {
      popProgram();
   }

   public static void enableFog() {
      fogEnabled = true;
      setProgramUniform1i(uniform_fogMode, fogMode);
      setProgramUniform1f(uniform_fogDensity, fogDensity);
   }

   public static void disableFog() {
      fogEnabled = false;
      setProgramUniform1i(uniform_fogMode, 0);
   }

   public static void setFogMode(xS fogMode) {
      setFogMode(fogMode.capabilityId);
   }

   public static void setFogDensity(float value) {
      fogDensity = value;
      if (fogEnabled) {
         setProgramUniform1f(uniform_fogDensity, value);
      }

   }

   public static void sglFogi(int pname, int param) {
      GL11.glFogi(pname, param);
      if (pname == 2917) {
         fogMode = param;
         if (fogEnabled) {
            setProgramUniform1i(uniform_fogMode, fogMode);
         }
      }

   }

   public static void enableLightmap() {
      lightmapEnabled = true;
      if (activeProgram == ProgramTextured) {
         useProgram(ProgramTexturedLit);
      }

   }

   public static void disableLightmap() {
      lightmapEnabled = false;
      if (activeProgram == ProgramTexturedLit) {
         useProgram(ProgramTextured);
      }

   }

   public static int getEntityData() {
      return entityData[entityDataIndex * 2];
   }

   public static int getEntityData2() {
      return entityData[entityDataIndex * 2 + 1];
   }

   public static int setEntityData1(int data1) {
      entityData[entityDataIndex * 2] = entityData[entityDataIndex * 2] & '\uffff' | data1 << 16;
      return data1;
   }

   public static int setEntityData2(int data2) {
      entityData[entityDataIndex * 2 + 1] = entityData[entityDataIndex * 2 + 1] & -65536 | data2 & '\uffff';
      return data2;
   }

   public static void pushEntity(int data0, int data1) {
      ++entityDataIndex;
      entityData[entityDataIndex * 2] = data0 & '\uffff' | data1 << 16;
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void pushEntity(int data0) {
      ++entityDataIndex;
      entityData[entityDataIndex * 2] = data0 & '\uffff';
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void pushEntity(co block) {
      ++entityDataIndex;
      int i = block.getRenderType(block.getDefaultState()).ordinal();
      entityData[entityDataIndex * 2] = co.REGISTRY.getIDForObject(block) & '\uffff' | i << 16;
      entityData[entityDataIndex * 2 + 1] = 0;
   }

   public static void popEntity() {
      entityData[entityDataIndex * 2] = 0;
      entityData[entityDataIndex * 2 + 1] = 0;
      --entityDataIndex;
   }

   public static void mcProfilerEndSection() {
      mc.profiler.endSection();
   }

   public static String getShaderPackName() {
      if (shaderPack == null) {
         return null;
      } else {
         return shaderPack instanceof bpm ? null : shaderPack.getName();
      }
   }

   public static InputStream getShaderPackResourceStream(String path) {
      return shaderPack == null ? null : shaderPack.getResourceAsStream(path);
   }

   public static void nextAntialiasingLevel(boolean forward) {
      if (forward) {
         configAntialiasingLevel += 2;
         if (configAntialiasingLevel > 4) {
            configAntialiasingLevel = 0;
         }
      } else {
         configAntialiasingLevel -= 2;
         if (configAntialiasingLevel < 0) {
            configAntialiasingLevel = 4;
         }
      }

      configAntialiasingLevel = configAntialiasingLevel / 2 * 2;
      configAntialiasingLevel = XH.limit(configAntialiasingLevel, 0, 4);
   }

   public static void checkShadersModInstalled() {
      try {
         Class var0 = Class.forName("shadersmod.transform.SMCClassTransformer");
      } catch (Throwable var1) {
         return;
      }

      throw new RuntimeException("Shaders Mod detected. Please remove it, OptiFine has built-in support for shaders.");
   }

   public static void resourcesReloaded() {
      loadShaderPackResources();
      if (shaderPackLoaded) {
         boe.resourcesReloaded();
         bpb.resourcesReloaded();
         boM.resourcesReloaded();
      }

   }

   private static void loadShaderPackResources() {
      shaderPackResources = new HashMap();
      if (shaderPackLoaded) {
         List<String> list = new ArrayList();
         String s = "/shaders/lang/";
         String s1 = "en_US";
         String s2 = ".lang";
         list.add(s + s1 + s2);
         if (!XH.getGameSettings().language.equals(s1)) {
            list.add(s + XH.getGameSettings().language + s2);
         }

         try {
            Iterator var13 = list.iterator();

            while(true) {
               InputStream inputstream;
               do {
                  if (!var13.hasNext()) {
                     return;
                  }

                  String s3 = (String)var13.next();
                  inputstream = shaderPack.getResourceAsStream(s3);
               } while(inputstream == null);

               Properties properties = new bqL();
               bmW.loadLocaleData(inputstream, properties);
               inputstream.close();
               Iterator var8 = ((Properties)properties).keySet().iterator();

               while(var8.hasNext()) {
                  Object s44 = var8.next();
                  String s4 = (String)s44;
                  String s5 = ((Properties)properties).getProperty(s4);
                  shaderPackResources.put(s4, s5);
               }
            }
         } catch (IOException var12) {
            IOException ioexception = var12;
            ioexception.printStackTrace();
         }
      }

   }

   public static String translate(String key, String def) {
      String s = (String)shaderPackResources.get(key);
      return s == null ? def : s;
   }

   public static boolean isProgramPath(String path) {
      if (path == null) {
         return false;
      } else if (path.length() <= 0) {
         return false;
      } else {
         int i = path.lastIndexOf("/");
         if (i >= 0) {
            path = path.substring(i + 1);
         }

         bpg program = getProgram(path);
         return program != null;
      }
   }

   public static bpg getProgram(String name) {
      return programs.getProgram(name);
   }

   public static void setItemToRenderMain(Qy itemToRenderMain) {
      itemToRenderMainTranslucent = isTranslucentBlock(itemToRenderMain);
   }

   public static void setItemToRenderOff(Qy itemToRenderOff) {
      itemToRenderOffTranslucent = isTranslucentBlock(itemToRenderOff);
   }

   public static boolean isItemToRenderMainTranslucent() {
      return itemToRenderMainTranslucent;
   }

   public static boolean isItemToRenderOffTranslucent() {
      return itemToRenderOffTranslucent;
   }

   public static boolean isBothHandsRendered() {
      return isHandRenderedMain && isHandRenderedOff;
   }

   private static boolean isTranslucentBlock(Qy stack) {
      if (stack == null) {
         return false;
      } else {
         OL item = stack.getItem();
         if (item == null) {
            return false;
         } else if (!(item instanceof OX)) {
            return false;
         } else {
            OX itemblock = (OX)item;
            co block = itemblock.getBlock();
            if (block == null) {
               return false;
            } else {
               BlockRenderLayer blockrenderlayer = block.getRenderLayer();
               return blockrenderlayer == BlockRenderLayer.TRANSLUCENT;
            }
         }
      }
   }

   public static boolean isSkipRenderHand(EnumHand hand) {
      if (hand == EnumHand.MAIN_HAND && skipRenderHandMain) {
         return true;
      } else {
         return hand == EnumHand.OFF_HAND && skipRenderHandOff;
      }
   }

   public static boolean isRenderBothHands() {
      return !skipRenderHandMain && !skipRenderHandOff;
   }

   public static void setSkipRenderHands(boolean skipMain, boolean skipOff) {
      skipRenderHandMain = skipMain;
      skipRenderHandOff = skipOff;
   }

   public static void setHandsRendered(boolean handMain, boolean handOff) {
      isHandRenderedMain = handMain;
      isHandRenderedOff = handOff;
   }

   public static boolean isHandRenderedMain() {
      return isHandRenderedMain;
   }

   public static boolean isHandRenderedOff() {
      return isHandRenderedOff;
   }

   public static float getShadowRenderDistance() {
      return shadowDistanceRenderMul < 0.0F ? -1.0F : shadowMapHalfPlane * shadowDistanceRenderMul;
   }

   public static void setRenderingFirstPersonHand(boolean flag) {
      isRenderingFirstPersonHand = flag;
   }

   public static boolean isRenderingFirstPersonHand() {
      return isRenderingFirstPersonHand;
   }

   public static void beginBeacon() {
      if (isRenderingWorld) {
         useProgram(ProgramBeaconBeam);
      }

   }

   public static void endBeacon() {
      if (isRenderingWorld) {
         useProgram(ProgramBlock);
      }

   }

   public static bij getCurrentWorld() {
      return currentWorld;
   }

   public static BlockPos getCameraPosition() {
      return new BlockPos(cameraPositionX, cameraPositionY, cameraPositionZ);
   }

   public static boolean isCustomUniforms() {
      return customUniforms != null;
   }

   public static boolean canRenderQuads() {
      return hasGeometryShaders ? capabilities.GL_NV_geometry_shader4 : true;
   }

   static {
      uniform_entityColor = shaderUniforms.make4f("entityColor");
      uniform_entityId = shaderUniforms.make1i("entityId");
      uniform_blockEntityId = shaderUniforms.make1i("blockEntityId");
      uniform_texture = shaderUniforms.make1i("texture");
      uniform_lightmap = shaderUniforms.make1i("lightmap");
      uniform_normals = shaderUniforms.make1i("normals");
      uniform_specular = shaderUniforms.make1i("specular");
      uniform_shadow = shaderUniforms.make1i("shadow");
      uniform_watershadow = shaderUniforms.make1i("watershadow");
      uniform_shadowtex0 = shaderUniforms.make1i("shadowtex0");
      uniform_shadowtex1 = shaderUniforms.make1i("shadowtex1");
      uniform_depthtex0 = shaderUniforms.make1i("depthtex0");
      uniform_depthtex1 = shaderUniforms.make1i("depthtex1");
      uniform_shadowcolor = shaderUniforms.make1i("shadowcolor");
      uniform_shadowcolor0 = shaderUniforms.make1i("shadowcolor0");
      uniform_shadowcolor1 = shaderUniforms.make1i("shadowcolor1");
      uniform_noisetex = shaderUniforms.make1i("noisetex");
      uniform_gcolor = shaderUniforms.make1i("gcolor");
      uniform_gdepth = shaderUniforms.make1i("gdepth");
      uniform_gnormal = shaderUniforms.make1i("gnormal");
      uniform_composite = shaderUniforms.make1i("composite");
      uniform_gaux1 = shaderUniforms.make1i("gaux1");
      uniform_gaux2 = shaderUniforms.make1i("gaux2");
      uniform_gaux3 = shaderUniforms.make1i("gaux3");
      uniform_gaux4 = shaderUniforms.make1i("gaux4");
      uniform_colortex0 = shaderUniforms.make1i("colortex0");
      uniform_colortex1 = shaderUniforms.make1i("colortex1");
      uniform_colortex2 = shaderUniforms.make1i("colortex2");
      uniform_colortex3 = shaderUniforms.make1i("colortex3");
      uniform_colortex4 = shaderUniforms.make1i("colortex4");
      uniform_colortex5 = shaderUniforms.make1i("colortex5");
      uniform_colortex6 = shaderUniforms.make1i("colortex6");
      uniform_colortex7 = shaderUniforms.make1i("colortex7");
      uniform_gdepthtex = shaderUniforms.make1i("gdepthtex");
      uniform_depthtex2 = shaderUniforms.make1i("depthtex2");
      uniform_tex = shaderUniforms.make1i("tex");
      uniform_heldItemId = shaderUniforms.make1i("heldItemId");
      uniform_heldBlockLightValue = shaderUniforms.make1i("heldBlockLightValue");
      uniform_heldItemId2 = shaderUniforms.make1i("heldItemId2");
      uniform_heldBlockLightValue2 = shaderUniforms.make1i("heldBlockLightValue2");
      uniform_fogMode = shaderUniforms.make1i("fogMode");
      uniform_fogDensity = shaderUniforms.make1f("fogDensity");
      uniform_fogColor = shaderUniforms.make3f("fogColor");
      uniform_skyColor = shaderUniforms.make3f("skyColor");
      uniform_worldTime = shaderUniforms.make1i("worldTime");
      uniform_worldDay = shaderUniforms.make1i("worldDay");
      uniform_moonPhase = shaderUniforms.make1i("moonPhase");
      uniform_frameCounter = shaderUniforms.make1i("frameCounter");
      uniform_frameTime = shaderUniforms.make1f("frameTime");
      uniform_frameTimeCounter = shaderUniforms.make1f("frameTimeCounter");
      uniform_sunAngle = shaderUniforms.make1f("sunAngle");
      uniform_shadowAngle = shaderUniforms.make1f("shadowAngle");
      uniform_rainStrength = shaderUniforms.make1f("rainStrength");
      uniform_aspectRatio = shaderUniforms.make1f("aspectRatio");
      uniform_viewWidth = shaderUniforms.make1f("viewWidth");
      uniform_viewHeight = shaderUniforms.make1f("viewHeight");
      uniform_near = shaderUniforms.make1f("near");
      uniform_far = shaderUniforms.make1f("far");
      uniform_sunPosition = shaderUniforms.make3f("sunPosition");
      uniform_moonPosition = shaderUniforms.make3f("moonPosition");
      uniform_shadowLightPosition = shaderUniforms.make3f("shadowLightPosition");
      uniform_upPosition = shaderUniforms.make3f("upPosition");
      uniform_previousCameraPosition = shaderUniforms.make3f("previousCameraPosition");
      uniform_cameraPosition = shaderUniforms.make3f("cameraPosition");
      uniform_gbufferModelView = shaderUniforms.makeM4("gbufferModelView");
      uniform_gbufferModelViewInverse = shaderUniforms.makeM4("gbufferModelViewInverse");
      uniform_gbufferPreviousProjection = shaderUniforms.makeM4("gbufferPreviousProjection");
      uniform_gbufferProjection = shaderUniforms.makeM4("gbufferProjection");
      uniform_gbufferProjectionInverse = shaderUniforms.makeM4("gbufferProjectionInverse");
      uniform_gbufferPreviousModelView = shaderUniforms.makeM4("gbufferPreviousModelView");
      uniform_shadowProjection = shaderUniforms.makeM4("shadowProjection");
      uniform_shadowProjectionInverse = shaderUniforms.makeM4("shadowProjectionInverse");
      uniform_shadowModelView = shaderUniforms.makeM4("shadowModelView");
      uniform_shadowModelViewInverse = shaderUniforms.makeM4("shadowModelViewInverse");
      uniform_wetness = shaderUniforms.make1f("wetness");
      uniform_eyeAltitude = shaderUniforms.make1f("eyeAltitude");
      uniform_eyeBrightness = shaderUniforms.make2i("eyeBrightness");
      uniform_eyeBrightnessSmooth = shaderUniforms.make2i("eyeBrightnessSmooth");
      uniform_terrainTextureSize = shaderUniforms.make2i("terrainTextureSize");
      uniform_terrainIconSize = shaderUniforms.make1i("terrainIconSize");
      uniform_isEyeInWater = shaderUniforms.make1i("isEyeInWater");
      uniform_nightVision = shaderUniforms.make1f("nightVision");
      uniform_blindness = shaderUniforms.make1f("blindness");
      uniform_screenBrightness = shaderUniforms.make1f("screenBrightness");
      uniform_hideGUI = shaderUniforms.make1i("hideGUI");
      uniform_centerDepthSmooth = shaderUniforms.make1f("centerDepthSmooth");
      uniform_atlasSize = shaderUniforms.make2i("atlasSize");
      uniform_blendFunc = shaderUniforms.make4i("blendFunc");
      uniform_instanceId = shaderUniforms.make1i("instanceId");
      shadowPassInterval = 0;
      needResizeShadow = false;
      shadowMapWidth = 1024;
      shadowMapHeight = 1024;
      spShadowMapWidth = 1024;
      spShadowMapHeight = 1024;
      shadowMapFOV = 90.0F;
      shadowMapHalfPlane = 160.0F;
      shadowMapIsOrtho = true;
      shadowDistanceRenderMul = -1.0F;
      shadowPassCounter = 0;
      shouldSkipDefaultShadow = false;
      waterShadowEnabled = false;
      usedColorBuffers = 0;
      usedDepthBuffers = 0;
      usedShadowColorBuffers = 0;
      usedShadowDepthBuffers = 0;
      usedColorAttachs = 0;
      usedDrawBuffers = 0;
      dfb = 0;
      sfb = 0;
      gbuffersFormat = new int[8];
      gbuffersClear = new boolean[8];
      gbuffersClearColor = new Vector4f[8];
      programs = new bph();
      ProgramNone = programs.getProgramNone();
      ProgramShadow = programs.makeShadow("shadow", ProgramNone);
      ProgramShadowSolid = programs.makeShadow("shadow_solid", ProgramShadow);
      ProgramShadowCutout = programs.makeShadow("shadow_cutout", ProgramShadow);
      ProgramBasic = programs.makeGbuffers("gbuffers_basic", ProgramNone);
      ProgramTextured = programs.makeGbuffers("gbuffers_textured", ProgramBasic);
      ProgramTexturedLit = programs.makeGbuffers("gbuffers_textured_lit", ProgramTextured);
      ProgramSkyBasic = programs.makeGbuffers("gbuffers_skybasic", ProgramBasic);
      ProgramSkyTextured = programs.makeGbuffers("gbuffers_skytextured", ProgramTextured);
      ProgramClouds = programs.makeGbuffers("gbuffers_clouds", ProgramTextured);
      ProgramTerrain = programs.makeGbuffers("gbuffers_terrain", ProgramTexturedLit);
      ProgramTerrainSolid = programs.makeGbuffers("gbuffers_terrain_solid", ProgramTerrain);
      ProgramTerrainCutoutMip = programs.makeGbuffers("gbuffers_terrain_cutout_mip", ProgramTerrain);
      ProgramTerrainCutout = programs.makeGbuffers("gbuffers_terrain_cutout", ProgramTerrain);
      ProgramDamagedBlock = programs.makeGbuffers("gbuffers_damagedblock", ProgramTerrain);
      ProgramBlock = programs.makeGbuffers("gbuffers_block", ProgramTerrain);
      ProgramBeaconBeam = programs.makeGbuffers("gbuffers_beaconbeam", ProgramTextured);
      ProgramItem = programs.makeGbuffers("gbuffers_item", ProgramTexturedLit);
      ProgramEntities = programs.makeGbuffers("gbuffers_entities", ProgramTexturedLit);
      ProgramEntitiesGlowing = programs.makeGbuffers("gbuffers_entities_glowing", ProgramEntities);
      ProgramArmorGlint = programs.makeGbuffers("gbuffers_armor_glint", ProgramTextured);
      ProgramSpiderEyes = programs.makeGbuffers("gbuffers_spidereyes", ProgramTextured);
      ProgramHand = programs.makeGbuffers("gbuffers_hand", ProgramTexturedLit);
      ProgramWeather = programs.makeGbuffers("gbuffers_weather", ProgramTexturedLit);
      ProgramDeferredPre = programs.makeVirtual("deferred_pre");
      ProgramsDeferred = programs.makeDeferreds("deferred", 16);
      ProgramDeferred = ProgramsDeferred[0];
      ProgramWater = programs.makeGbuffers("gbuffers_water", ProgramTerrain);
      ProgramHandWater = programs.makeGbuffers("gbuffers_hand_water", ProgramHand);
      ProgramCompositePre = programs.makeVirtual("composite_pre");
      ProgramsComposite = programs.makeComposites("composite", 16);
      ProgramComposite = ProgramsComposite[0];
      ProgramFinal = programs.makeComposite("final");
      ProgramCount = programs.getCount();
      ProgramsAll = programs.getPrograms();
      activeProgram = ProgramNone;
      activeProgramID = 0;
      programStack = new bpi();
      hasDeferredPrograms = false;
      activeDrawBuffers = null;
      activeCompositeMipmapSetting = 0;
      loadedShaders = null;
      shadersConfig = null;
      defaultTexture = null;
      shadowHardwareFilteringEnabled = new boolean[2];
      shadowMipmapEnabled = new boolean[2];
      shadowFilterNearest = new boolean[2];
      shadowColorMipmapEnabled = new boolean[8];
      shadowColorFilterNearest = new boolean[8];
      configTweakBlockDamage = false;
      configCloudShadow = false;
      configHandDepthMul = 0.125F;
      configRenderResMul = 1.0F;
      configShadowResMul = 1.0F;
      configTexMinFilB = 0;
      configTexMinFilN = 0;
      configTexMinFilS = 0;
      configTexMagFilB = 0;
      configTexMagFilN = 0;
      configTexMagFilS = 0;
      configShadowClipFrustrum = true;
      configNormalMap = true;
      configSpecularMap = true;
      configOldLighting = new bon("oldLighting", "Classic Lighting", 0);
      configOldHandLight = new bon("oldHandLight", "Old Hand Light", 0);
      configAntialiasingLevel = 0;
      texMinFilDesc = new String[]{"Nearest", "Nearest-Nearest", "Nearest-Linear"};
      texMagFilDesc = new String[]{"Nearest", "Linear"};
      texMinFilValue = new int[]{9728, 9984, 9986};
      texMagFilValue = new int[]{9728, 9729};
      shaderPack = null;
      shaderPackLoaded = false;
      shaderPackOptions = null;
      shaderPackOptionSliders = null;
      shaderPackProfiles = null;
      shaderPackGuiScreens = null;
      shaderPackProgramConditions = new HashMap();
      shaderPackClouds = new bom("clouds", "Clouds", 0);
      shaderPackOldLighting = new bon("oldLighting", "Classic Lighting", 0);
      shaderPackOldHandLight = new bon("oldHandLight", "Old Hand Light", 0);
      shaderPackDynamicHandLight = new bon("dynamicHandLight", "Dynamic Hand Light", 0);
      shaderPackShadowTranslucent = new bon("shadowTranslucent", "Shadow Translucent", 0);
      shaderPackUnderwaterOverlay = new bon("underwaterOverlay", "Underwater Overlay", 0);
      shaderPackSun = new bon("sun", "Sun", 0);
      shaderPackMoon = new bon("moon", "Moon", 0);
      shaderPackVignette = new bon("vignette", "Vignette", 0);
      shaderPackBackFaceSolid = new bon("backFace.solid", "Back-face Solid", 0);
      shaderPackBackFaceCutout = new bon("backFace.cutout", "Back-face Cutout", 0);
      shaderPackBackFaceCutoutMipped = new bon("backFace.cutoutMipped", "Back-face Cutout Mipped", 0);
      shaderPackBackFaceTranslucent = new bon("backFace.translucent", "Back-face Translucent", 0);
      shaderPackRainDepth = new bon("rain.depth", "Rain Depth", 0);
      shaderPackBeaconBeamDepth = new bon("beacon.beam.depth", "Rain Depth", 0);
      shaderPackSeparateAo = new bon("separateAo", "Separate AO", 0);
      shaderPackFrustumCulling = new bon("frustum.culling", "Frustum Culling", 0);
      shaderPackResources = new HashMap();
      currentWorld = null;
      shaderPackDimensions = new ArrayList();
      customTexturesGbuffers = null;
      customTexturesComposite = null;
      customTexturesDeferred = null;
      noiseTexturePath = null;
      customUniforms = null;
      STAGE_NAMES = new String[]{"gbuffers", "composite", "deferred"};
      saveFinalShaders = System.getProperty("shaders.debug.save", "false").equals("true");
      blockLightLevel05 = 0.5F;
      blockLightLevel06 = 0.6F;
      blockLightLevel08 = 0.8F;
      aoLevel = -1.0F;
      sunPathRotation = 0.0F;
      shadowAngleInterval = 0.0F;
      fogMode = 0;
      fogDensity = 0.0F;
      shadowIntervalSize = 2.0F;
      terrainIconSize = 16;
      terrainTextureSize = new int[2];
      noiseTextureEnabled = false;
      noiseTextureResolution = 256;
      colorTextureImageUnit = new int[]{0, 1, 2, 3, 7, 8, 9, 10};
      bigBufferSize = (285 + 8 * ProgramCount) * 4;
      bigBuffer = (ByteBuffer)BufferUtils.createByteBuffer(bigBufferSize).limit(0);
      faProjection = new float[16];
      faProjectionInverse = new float[16];
      faModelView = new float[16];
      faModelViewInverse = new float[16];
      faShadowProjection = new float[16];
      faShadowProjectionInverse = new float[16];
      faShadowModelView = new float[16];
      faShadowModelViewInverse = new float[16];
      projection = nextFloatBuffer(16);
      projectionInverse = nextFloatBuffer(16);
      modelView = nextFloatBuffer(16);
      modelViewInverse = nextFloatBuffer(16);
      shadowProjection = nextFloatBuffer(16);
      shadowProjectionInverse = nextFloatBuffer(16);
      shadowModelView = nextFloatBuffer(16);
      shadowModelViewInverse = nextFloatBuffer(16);
      previousProjection = nextFloatBuffer(16);
      previousModelView = nextFloatBuffer(16);
      tempMatrixDirectBuffer = nextFloatBuffer(16);
      tempDirectFloatBuffer = nextFloatBuffer(16);
      dfbColorTextures = nextIntBuffer(16);
      dfbDepthTextures = nextIntBuffer(3);
      sfbColorTextures = nextIntBuffer(8);
      sfbDepthTextures = nextIntBuffer(2);
      dfbDrawBuffers = nextIntBuffer(8);
      sfbDrawBuffers = nextIntBuffer(8);
      drawBuffersNone = (IntBuffer)nextIntBuffer(8).limit(0);
      drawBuffersColorAtt0 = (IntBuffer)nextIntBuffer(8).put(36064).position(0).limit(1);
      dfbColorTexturesFlip = new boN(dfbColorTextures, 8);
      formatNames = new String[]{"R8", "RG8", "RGB8", "RGBA8", "R8_SNORM", "RG8_SNORM", "RGB8_SNORM", "RGBA8_SNORM", "R16", "RG16", "RGB16", "RGBA16", "R16_SNORM", "RG16_SNORM", "RGB16_SNORM", "RGBA16_SNORM", "R16F", "RG16F", "RGB16F", "RGBA16F", "R32F", "RG32F", "RGB32F", "RGBA32F", "R32I", "RG32I", "RGB32I", "RGBA32I", "R32UI", "RG32UI", "RGB32UI", "RGBA32UI", "R3_G3_B2", "RGB5_A1", "RGB10_A2", "R11F_G11F_B10F", "RGB9_E5"};
      formatIds = new int[]{33321, 33323, 32849, 32856, 36756, 36757, 36758, 36759, 33322, 33324, 32852, 32859, 36760, 36761, 36762, 36763, 33325, 33327, 34843, 34842, 33326, 33328, 34837, 34836, 33333, 33339, 36227, 36226, 33334, 33340, 36209, 36208, 10768, 32855, 32857, 35898, 35901};
      patternLoadEntityDataMap = Pattern.compile("\\s*([\\w:]+)\\s*=\\s*([-]?\\d+)\\s*");
      entityData = new int[32];
      entityDataIndex = 0;
      shaderPacksDir = new File(nC.getMinecraft().gameDir, "shaderpacks");
      configFile = new File(nC.getMinecraft().gameDir, "optionsshaders.txt");
   }
}
