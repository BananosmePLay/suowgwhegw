package neo;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;
import javax.vecmath.Matrix4f;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bnK {
   private static final Logger LOGGER = LogManager.getLogger();
   private static boolean logForge = logEntry("*** Reflector Forge ***");
   public static bnL BetterFoliageClient = new bnL("mods.betterfoliage.client.BetterFoliageClient");
   public static bnL ChunkWatchEvent_UnWatch = new bnL("net.minecraftforge.event.world.ChunkWatchEvent$UnWatch");
   public static bnM ChunkWatchEvent_UnWatch_Constructor;
   public static bnL CoreModManager;
   public static bnR CoreModManager_onCrash;
   public static bnL DimensionManager;
   public static bnR DimensionManager_createProviderFor;
   public static bnR DimensionManager_getStaticDimensionIDs;
   public static bnL DrawScreenEvent_Pre;
   public static bnM DrawScreenEvent_Pre_Constructor;
   public static bnL DrawScreenEvent_Post;
   public static bnM DrawScreenEvent_Post_Constructor;
   public static bnL EntityViewRenderEvent_CameraSetup;
   public static bnM EntityViewRenderEvent_CameraSetup_Constructor;
   public static bnR EntityViewRenderEvent_CameraSetup_getRoll;
   public static bnR EntityViewRenderEvent_CameraSetup_getPitch;
   public static bnR EntityViewRenderEvent_CameraSetup_getYaw;
   public static bnL EntityViewRenderEvent_FogColors;
   public static bnM EntityViewRenderEvent_FogColors_Constructor;
   public static bnR EntityViewRenderEvent_FogColors_getRed;
   public static bnR EntityViewRenderEvent_FogColors_getGreen;
   public static bnR EntityViewRenderEvent_FogColors_getBlue;
   public static bnL EntityViewRenderEvent_RenderFogEvent;
   public static bnM EntityViewRenderEvent_RenderFogEvent_Constructor;
   public static bnL Event;
   public static bnR Event_isCanceled;
   public static bnL EventBus;
   public static bnR EventBus_post;
   public static bnL Event_Result;
   public static bnN Event_Result_DENY;
   public static bnN Event_Result_ALLOW;
   public static bnN Event_Result_DEFAULT;
   public static bnL ExtendedBlockState;
   public static bnM ExtendedBlockState_Constructor;
   public static bnL FMLClientHandler;
   public static bnR FMLClientHandler_instance;
   public static bnR FMLClientHandler_handleLoadingScreen;
   public static bnR FMLClientHandler_isLoading;
   public static bnR FMLClientHandler_refreshResources;
   public static bnR FMLClientHandler_renderClouds;
   public static bnR FMLClientHandler_trackBrokenTexture;
   public static bnR FMLClientHandler_trackMissingTexture;
   public static bnL FMLCommonHandler;
   public static bnR FMLCommonHandler_callFuture;
   public static bnR FMLCommonHandler_enhanceCrashReport;
   public static bnR FMLCommonHandler_getBrandings;
   public static bnR FMLCommonHandler_handleServerAboutToStart;
   public static bnR FMLCommonHandler_handleServerStarting;
   public static bnR FMLCommonHandler_instance;
   public static bnL ActiveRenderInfo;
   public static bnR ActiveRenderInfo_getCameraPosition;
   public static bnR ActiveRenderInfo_updateRenderInfo2;
   public static bnL ForgeBiome;
   public static bnR ForgeBiome_getWaterColorMultiplier;
   public static bnL ForgeBiomeSpawnListEntry;
   public static bnR ForgeBiomeSpawnListEntry_newInstance;
   public static bnL ForgeBlock;
   public static bnR ForgeBlock_addDestroyEffects;
   public static bnR ForgeBlock_addHitEffects;
   public static bnR ForgeBlock_canCreatureSpawn;
   public static bnR ForgeBlock_canRenderInLayer;
   public static bnR ForgeBlock_doesSideBlockRendering;
   public static bnR ForgeBlock_doesSideBlockChestOpening;
   public static bnR ForgeBlock_getBedDirection;
   public static bnR ForgeBlock_getExtendedState;
   public static bnR ForgeBlock_getFogColor;
   public static bnR ForgeBlock_getLightOpacity;
   public static bnR ForgeBlock_getLightValue;
   public static bnR ForgeBlock_getSoundType;
   public static bnR ForgeBlock_hasTileEntity;
   public static bnR ForgeBlock_isAir;
   public static bnR ForgeBlock_isBed;
   public static bnR ForgeBlock_isBedFoot;
   public static bnR ForgeBlock_isSideSolid;
   public static bnL ForgeIBakedModel;
   public static bnR ForgeIBakedModel_isAmbientOcclusion2;
   public static bnL ForgeIBlockProperties;
   public static bnR ForgeIBlockProperties_getLightValue2;
   public static bnL ForgeChunkCache;
   public static bnR ForgeChunkCache_isSideSolid;
   public static bnL ForgeEntity;
   public static bnR ForgeEntity_canRiderInteract;
   public static bnN ForgeEntity_captureDrops;
   public static bnN ForgeEntity_capturedDrops;
   public static bnR ForgeEntity_shouldRenderInPass;
   public static bnR ForgeEntity_shouldRiderSit;
   public static bnL ForgeEntityList;
   public static bnR ForgeEntityList_getClass;
   public static bnR ForgeEntityList_getID;
   public static bnL ForgeEventFactory;
   public static bnR ForgeEventFactory_canEntitySpawn;
   public static bnR ForgeEventFactory_canEntityDespawn;
   public static bnR ForgeEventFactory_doSpecialSpawn;
   public static bnR ForgeEventFactory_getMaxSpawnPackSize;
   public static bnR ForgeEventFactory_getMobGriefingEvent;
   public static bnR ForgeEventFactory_renderBlockOverlay;
   public static bnR ForgeEventFactory_renderFireOverlay;
   public static bnR ForgeEventFactory_renderWaterOverlay;
   public static bnL ForgeHooks;
   public static bnR ForgeHooks_onLivingAttack;
   public static bnR ForgeHooks_onLivingDeath;
   public static bnR ForgeHooks_onLivingDrops;
   public static bnR ForgeHooks_onLivingFall;
   public static bnR ForgeHooks_onLivingHurt;
   public static bnR ForgeHooks_onLivingJump;
   public static bnR ForgeHooks_onLivingSetAttackTarget;
   public static bnR ForgeHooks_onLivingUpdate;
   public static bnL ForgeHooksClient;
   public static bnR ForgeHooksClient_applyTransform_M4;
   public static bnR ForgeHooksClient_applyTransform_MR;
   public static bnR ForgeHooksClient_applyUVLock;
   public static bnR ForgeHooksClient_dispatchRenderLast;
   public static bnR ForgeHooksClient_drawScreen;
   public static bnR ForgeHooksClient_fillNormal;
   public static bnR ForgeHooksClient_handleCameraTransforms;
   public static bnR ForgeHooksClient_getArmorModel;
   public static bnR ForgeHooksClient_getArmorTexture;
   public static bnR ForgeHooksClient_getFogDensity;
   public static bnR ForgeHooksClient_getFOVModifier;
   public static bnR ForgeHooksClient_getMatrix;
   public static bnR ForgeHooksClient_getOffsetFOV;
   public static bnR ForgeHooksClient_loadEntityShader;
   public static bnR ForgeHooksClient_onDrawBlockHighlight;
   public static bnR ForgeHooksClient_onFogRender;
   public static bnR ForgeHooksClient_onScreenshot;
   public static bnR ForgeHooksClient_onTextureStitchedPre;
   public static bnR ForgeHooksClient_onTextureStitchedPost;
   public static bnR ForgeHooksClient_orientBedCamera;
   public static bnR ForgeHooksClient_putQuadColor;
   public static bnR ForgeHooksClient_renderFirstPersonHand;
   public static bnR ForgeHooksClient_renderLitItem;
   public static bnR ForgeHooksClient_renderMainMenu;
   public static bnR ForgeHooksClient_renderSpecificFirstPersonHand;
   public static bnR ForgeHooksClient_setRenderLayer;
   public static bnR ForgeHooksClient_setRenderPass;
   public static bnR ForgeHooksClient_shouldCauseReequipAnimation;
   public static bnR ForgeHooksClient_transform;
   public static bnL ForgeItem;
   public static bnN ForgeItem_delegate;
   public static bnR ForgeItem_getDurabilityForDisplay;
   public static bnR ForgeItem_getEquipmentSlot;
   public static bnR ForgeItem_getTileEntityItemStackRenderer;
   public static bnR ForgeItem_getRGBDurabilityForDisplay;
   public static bnR ForgeItem_isShield;
   public static bnR ForgeItem_onEntitySwing;
   public static bnR ForgeItem_shouldCauseReequipAnimation;
   public static bnR ForgeItem_showDurabilityBar;
   public static bnL ForgeItemArmor;
   public static bnR ForgeItemArmor_hasOverlay;
   public static bnL ForgeKeyBinding;
   public static bnR ForgeKeyBinding_setKeyConflictContext;
   public static bnR ForgeKeyBinding_setKeyModifierAndCode;
   public static bnR ForgeKeyBinding_getKeyModifier;
   public static bnL ForgeModContainer;
   public static bnN ForgeModContainer_forgeLightPipelineEnabled;
   public static bnN ForgeModContainer_allowEmissiveItems;
   public static bnL ForgeModelBlockDefinition;
   public static bnR ForgeModelBlockDefinition_parseFromReader2;
   public static bnL ForgePotion;
   public static bnR ForgePotion_shouldRenderHUD;
   public static bnR ForgePotion_renderHUDEffect;
   public static bnL ForgePotionEffect;
   public static bnR ForgePotionEffect_isCurativeItem;
   public static bnL ForgeTileEntity;
   public static bnR ForgeTileEntity_canRenderBreaking;
   public static bnR ForgeTileEntity_getRenderBoundingBox;
   public static bnR ForgeTileEntity_hasFastRenderer;
   public static bnR ForgeTileEntity_shouldRenderInPass;
   public static bnL ForgeVertexFormatElementEnumUseage;
   public static bnR ForgeVertexFormatElementEnumUseage_preDraw;
   public static bnR ForgeVertexFormatElementEnumUseage_postDraw;
   public static bnL ForgeWorld;
   public static bnR ForgeWorld_countEntities;
   public static bnR ForgeWorld_getPerWorldStorage;
   public static bnR ForgeWorld_initCapabilities;
   public static bnL ForgeWorldProvider;
   public static bnR ForgeWorldProvider_getCloudRenderer;
   public static bnR ForgeWorldProvider_getSkyRenderer;
   public static bnR ForgeWorldProvider_getWeatherRenderer;
   public static bnR ForgeWorldProvider_getLightmapColors;
   public static bnR ForgeWorldProvider_getSaveFolder;
   public static bnL GuiModList;
   public static bnM GuiModList_Constructor;
   public static bnL IExtendedBlockState;
   public static bnR IExtendedBlockState_getClean;
   public static bnL IForgeRegistryEntry_Impl;
   public static bnR IForgeRegistryEntry_Impl_getRegistryName;
   public static bnL IModel;
   public static bnR IModel_getTextures;
   public static bnL IRenderHandler;
   public static bnR IRenderHandler_render;
   public static bnL ItemModelMesherForge;
   public static bnM ItemModelMesherForge_Constructor;
   public static bnL KeyConflictContext;
   public static bnN KeyConflictContext_IN_GAME;
   public static bnL KeyModifier;
   public static bnR KeyModifier_valueFromString;
   public static bnN KeyModifier_NONE;
   public static bnL Launch;
   public static bnN Launch_blackboard;
   public static bnL LightUtil;
   public static bnN LightUtil_itemConsumer;
   public static bnR LightUtil_putBakedQuad;
   public static bnR LightUtil_renderQuadColor;
   public static bnN LightUtil_tessellator;
   public static bnL Loader;
   public static bnR Loader_getActiveModList;
   public static bnR Loader_instance;
   public static bnL MinecraftForge;
   public static bnN MinecraftForge_EVENT_BUS;
   public static bnL MinecraftForgeClient;
   public static bnR MinecraftForgeClient_getImageLayer;
   public static bnR MinecraftForgeClient_getRenderPass;
   public static bnR MinecraftForgeClient_onRebuildChunk;
   public static bnL ModContainer;
   public static bnR ModContainer_getModId;
   public static bnL ModelLoader;
   public static bnN ModelLoader_stateModels;
   public static bnR ModelLoader_onRegisterItems;
   public static bnR ModelLoader_getInventoryVariant;
   public static bnL ModelLoader_VanillaLoader;
   public static bnN ModelLoader_VanillaLoader_INSTANCE;
   public static bnR ModelLoader_VanillaLoader_loadModel;
   public static bnL ModelLoaderRegistry;
   public static bnN ModelLoaderRegistry_textures;
   public static bnL NotificationModUpdateScreen;
   public static bnR NotificationModUpdateScreen_init;
   public static bnL RenderBlockOverlayEvent_OverlayType;
   public static bnN RenderBlockOverlayEvent_OverlayType_BLOCK;
   public static bnL RenderingRegistry;
   public static bnR RenderingRegistry_loadEntityRenderers;
   public static bnL RenderItemInFrameEvent;
   public static bnM RenderItemInFrameEvent_Constructor;
   public static bnL RenderLivingEvent_Pre;
   public static bnM RenderLivingEvent_Pre_Constructor;
   public static bnL RenderLivingEvent_Post;
   public static bnM RenderLivingEvent_Post_Constructor;
   public static bnL RenderLivingEvent_Specials_Pre;
   public static bnM RenderLivingEvent_Specials_Pre_Constructor;
   public static bnL RenderLivingEvent_Specials_Post;
   public static bnM RenderLivingEvent_Specials_Post_Constructor;
   public static bnL ScreenshotEvent;
   public static bnR ScreenshotEvent_getCancelMessage;
   public static bnR ScreenshotEvent_getScreenshotFile;
   public static bnR ScreenshotEvent_getResultMessage;
   public static bnL SplashScreen;
   public static bnL VanillaResourceType;
   public static bnN VanillaResourceType_TEXTURES;
   public static bnL WorldEvent_Load;
   public static bnM WorldEvent_Load_Constructor;
   private static boolean logVanilla;
   public static bnL ChunkProviderClient;
   public static bnN ChunkProviderClient_chunkMapping;
   public static bnL EntityVillager;
   public static bnN EntityVillager_careerId;
   public static bnN EntityVillager_careerLevel;
   public static bnL GuiBeacon;
   public static bnN GuiBeacon_tileBeacon;
   public static bnL GuiBrewingStand;
   public static bnN GuiBrewingStand_tileBrewingStand;
   public static bnL GuiChest;
   public static bnN GuiChest_lowerChestInventory;
   public static bnL GuiEnchantment;
   public static bnN GuiEnchantment_nameable;
   public static bnL GuiFurnace;
   public static bnN GuiFurnace_tileFurnace;
   public static bnL GuiHopper;
   public static bnN GuiHopper_hopperInventory;
   public static bnL GuiMainMenu;
   public static bnN GuiMainMenu_splashText;
   public static bnL GuiShulkerBox;
   public static bnN GuiShulkerBox_inventory;
   public static bnL ItemOverride;
   public static bnN ItemOverride_mapResourceValues;
   public static bnL LegacyV2Adapter;
   public static bnN LegacyV2Adapter_pack;
   public static bnL Minecraft;
   public static bnN Minecraft_defaultResourcePack;
   public static bnN Minecraft_actionKeyF3;
   public static bnL ModelHumanoidHead;
   public static bnN ModelHumanoidHead_head;
   public static bnL ModelBat;
   public static bnO ModelBat_ModelRenderers;
   public static bnL ModelBlaze;
   public static bnN ModelBlaze_blazeHead;
   public static bnN ModelBlaze_blazeSticks;
   public static bnL ModelDragon;
   public static bnO ModelDragon_ModelRenderers;
   public static bnL ModelEnderCrystal;
   public static bnO ModelEnderCrystal_ModelRenderers;
   public static bnL RenderEnderCrystal;
   public static bnN RenderEnderCrystal_modelEnderCrystal;
   public static bnN RenderEnderCrystal_modelEnderCrystalNoBase;
   public static bnL ModelEnderMite;
   public static bnN ModelEnderMite_bodyParts;
   public static bnL ModelEvokerFangs;
   public static bnO ModelEvokerFangs_ModelRenderers;
   public static bnL ModelGhast;
   public static bnN ModelGhast_body;
   public static bnN ModelGhast_tentacles;
   public static bnL ModelGuardian;
   public static bnN ModelGuardian_body;
   public static bnN ModelGuardian_eye;
   public static bnN ModelGuardian_spines;
   public static bnN ModelGuardian_tail;
   public static bnL ModelDragonHead;
   public static bnN ModelDragonHead_head;
   public static bnN ModelDragonHead_jaw;
   public static bnL ModelHorse;
   public static bnO ModelHorse_ModelRenderers;
   public static bnL RenderLeashKnot;
   public static bnN RenderLeashKnot_leashKnotModel;
   public static bnL ModelMagmaCube;
   public static bnN ModelMagmaCube_core;
   public static bnN ModelMagmaCube_segments;
   public static bnL ModelOcelot;
   public static bnO ModelOcelot_ModelRenderers;
   public static bnL ModelParrot;
   public static bnO ModelParrot_ModelRenderers;
   public static bnL ModelRabbit;
   public static bnO ModelRabbit_renderers;
   public static bnL ModelSilverfish;
   public static bnN ModelSilverfish_bodyParts;
   public static bnN ModelSilverfish_wingParts;
   public static bnL ModelSlime;
   public static bnO ModelSlime_ModelRenderers;
   public static bnL ModelSquid;
   public static bnN ModelSquid_body;
   public static bnN ModelSquid_tentacles;
   public static bnL ModelVex;
   public static bnN ModelVex_leftWing;
   public static bnN ModelVex_rightWing;
   public static bnL ModelWitch;
   public static bnN ModelWitch_mole;
   public static bnN ModelWitch_hat;
   public static bnL ModelWither;
   public static bnN ModelWither_bodyParts;
   public static bnN ModelWither_heads;
   public static bnL ModelWolf;
   public static bnN ModelWolf_tail;
   public static bnN ModelWolf_mane;
   public static bnL OptiFineClassTransformer;
   public static bnN OptiFineClassTransformer_instance;
   public static bnR OptiFineClassTransformer_getOptiFineResource;
   public static bnL RenderBoat;
   public static bnN RenderBoat_modelBoat;
   public static bnL RenderEvokerFangs;
   public static bnN RenderEvokerFangs_model;
   public static bnL RenderMinecart;
   public static bnN RenderMinecart_modelMinecart;
   public static bnL RenderShulkerBullet;
   public static bnN RenderShulkerBullet_model;
   public static bnL RenderWitherSkull;
   public static bnN RenderWitherSkull_model;
   public static bnL TileEntityBannerRenderer;
   public static bnN TileEntityBannerRenderer_bannerModel;
   public static bnL TileEntityBedRenderer;
   public static bnN TileEntityBedRenderer_model;
   public static bnL TileEntityBeacon;
   public static bnN TileEntityBeacon_customName;
   public static bnL TileEntityBrewingStand;
   public static bnN TileEntityBrewingStand_customName;
   public static bnL TileEntityChestRenderer;
   public static bnN TileEntityChestRenderer_simpleChest;
   public static bnN TileEntityChestRenderer_largeChest;
   public static bnL TileEntityEnchantmentTable;
   public static bnN TileEntityEnchantmentTable_customName;
   public static bnL TileEntityEnchantmentTableRenderer;
   public static bnN TileEntityEnchantmentTableRenderer_modelBook;
   public static bnL TileEntityEnderChestRenderer;
   public static bnN TileEntityEnderChestRenderer_modelChest;
   public static bnL TileEntityFurnace;
   public static bnN TileEntityFurnace_customName;
   public static bnL TileEntityLockableLoot;
   public static bnN TileEntityLockableLoot_customName;
   public static bnL TileEntityShulkerBoxRenderer;
   public static bnN TileEntityShulkerBoxRenderer_model;
   public static bnL TileEntitySignRenderer;
   public static bnN TileEntitySignRenderer_model;
   public static bnL TileEntitySkullRenderer;
   public static bnN TileEntitySkullRenderer_dragonHead;
   public static bnN TileEntitySkullRenderer_skeletonHead;
   public static bnN TileEntitySkullRenderer_humanoidHead;

   public bnK() {
   }

   public static void callVoid(bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return;
         }

         method.invoke((Object)null, params);
      } catch (Throwable var3) {
         Throwable throwable = var3;
         handleException(throwable, (Object)null, refMethod, params);
      }

   }

   public static boolean callBoolean(bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return false;
         } else {
            Boolean obool = (Boolean)method.invoke((Object)null, params);
            return obool;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         handleException(throwable, (Object)null, refMethod, params);
         return false;
      }
   }

   public static int callInt(bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return 0;
         } else {
            Integer integer = (Integer)method.invoke((Object)null, params);
            return integer;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         handleException(throwable, (Object)null, refMethod, params);
         return 0;
      }
   }

   public static float callFloat(bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return 0.0F;
         } else {
            Float f = (Float)method.invoke((Object)null, params);
            return f;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         handleException(throwable, (Object)null, refMethod, params);
         return 0.0F;
      }
   }

   public static double callDouble(bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return 0.0;
         } else {
            Double d0 = (Double)method.invoke((Object)null, params);
            return d0;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         handleException(throwable, (Object)null, refMethod, params);
         return 0.0;
      }
   }

   public static String callString(bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return null;
         } else {
            String s = (String)method.invoke((Object)null, params);
            return s;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         handleException(throwable, (Object)null, refMethod, params);
         return null;
      }
   }

   public static Object call(bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return null;
         } else {
            Object object = method.invoke((Object)null, params);
            return object;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         handleException(throwable, (Object)null, refMethod, params);
         return null;
      }
   }

   public static void callVoid(Object obj, bnR refMethod, Object... params) {
      try {
         if (obj == null) {
            return;
         }

         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return;
         }

         method.invoke(obj, params);
      } catch (Throwable var4) {
         Throwable throwable = var4;
         handleException(throwable, obj, refMethod, params);
      }

   }

   public static boolean callBoolean(Object obj, bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return false;
         } else {
            Boolean obool = (Boolean)method.invoke(obj, params);
            return obool;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         handleException(throwable, obj, refMethod, params);
         return false;
      }
   }

   public static int callInt(Object obj, bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return 0;
         } else {
            Integer integer = (Integer)method.invoke(obj, params);
            return integer;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         handleException(throwable, obj, refMethod, params);
         return 0;
      }
   }

   public static float callFloat(Object obj, bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return 0.0F;
         } else {
            Float f = (Float)method.invoke(obj, params);
            return f;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         handleException(throwable, obj, refMethod, params);
         return 0.0F;
      }
   }

   public static double callDouble(Object obj, bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return 0.0;
         } else {
            Double d0 = (Double)method.invoke(obj, params);
            return d0;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         handleException(throwable, obj, refMethod, params);
         return 0.0;
      }
   }

   public static String callString(Object obj, bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return null;
         } else {
            String s = (String)method.invoke(obj, params);
            return s;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         handleException(throwable, obj, refMethod, params);
         return null;
      }
   }

   public static Object call(Object obj, bnR refMethod, Object... params) {
      try {
         Method method = refMethod.getTargetMethod();
         if (method == null) {
            return null;
         } else {
            Object object = method.invoke(obj, params);
            return object;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         handleException(throwable, obj, refMethod, params);
         return null;
      }
   }

   public static Object getFieldValue(bnN refField) {
      return getFieldValue((Object)null, refField);
   }

   public static Object getFieldValue(Object obj, bnN refField) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return null;
         } else {
            Object object = field.get(obj);
            return object;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         bmZ.error("", throwable);
         return null;
      }
   }

   public static boolean getFieldValueBoolean(bnN refField, boolean def) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return def;
         } else {
            boolean flag = field.getBoolean((Object)null);
            return flag;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         bmZ.error("", throwable);
         return def;
      }
   }

   public static boolean getFieldValueBoolean(Object obj, bnN refField, boolean def) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return def;
         } else {
            boolean flag = field.getBoolean(obj);
            return flag;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         bmZ.error("", throwable);
         return def;
      }
   }

   public static Object getFieldValue(bnO refFields, int index) {
      bnN reflectorfield = refFields.getReflectorField(index);
      return reflectorfield == null ? null : getFieldValue(reflectorfield);
   }

   public static Object getFieldValue(Object obj, bnO refFields, int index) {
      bnN reflectorfield = refFields.getReflectorField(index);
      return reflectorfield == null ? null : getFieldValue(obj, reflectorfield);
   }

   public static float getFieldValueFloat(Object obj, bnN refField, float def) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return def;
         } else {
            float f = field.getFloat(obj);
            return f;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         bmZ.error("", throwable);
         return def;
      }
   }

   public static int getFieldValueInt(Object obj, bnN refField, int def) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return def;
         } else {
            int i = field.getInt(obj);
            return i;
         }
      } catch (Throwable var5) {
         Throwable throwable = var5;
         bmZ.error("", throwable);
         return def;
      }
   }

   public static long getFieldValueLong(Object obj, bnN refField, long def) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return def;
         } else {
            long i = field.getLong(obj);
            return i;
         }
      } catch (Throwable var7) {
         Throwable throwable = var7;
         bmZ.error("", throwable);
         return def;
      }
   }

   public static boolean setFieldValue(bnN refField, Object value) {
      return setFieldValue((Object)null, refField, value);
   }

   public static boolean setFieldValue(Object obj, bnN refField, Object value) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return false;
         } else {
            field.set(obj, value);
            return true;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         bmZ.error("", throwable);
         return false;
      }
   }

   public static boolean setFieldValueInt(bnN refField, int value) {
      return setFieldValueInt((Object)null, refField, value);
   }

   public static boolean setFieldValueInt(Object obj, bnN refField, int value) {
      try {
         Field field = refField.getTargetField();
         if (field == null) {
            return false;
         } else {
            field.setInt(obj, value);
            return true;
         }
      } catch (Throwable var4) {
         Throwable throwable = var4;
         bmZ.error("", throwable);
         return false;
      }
   }

   public static boolean postForgeBusEvent(bnM constr, Object... params) {
      Object object = newInstance(constr, params);
      return object == null ? false : postForgeBusEvent(object);
   }

   public static boolean postForgeBusEvent(Object event) {
      if (event == null) {
         return false;
      } else {
         Object object = getFieldValue(MinecraftForge_EVENT_BUS);
         if (object == null) {
            return false;
         } else {
            Object object1 = call(object, EventBus_post, event);
            if (!(object1 instanceof Boolean)) {
               return false;
            } else {
               Boolean obool = (Boolean)object1;
               return obool;
            }
         }
      }
   }

   public static Object newInstance(bnM constr, Object... params) {
      Constructor constructor = constr.getTargetConstructor();
      if (constructor == null) {
         return null;
      } else {
         try {
            Object object = constructor.newInstance(params);
            return object;
         } catch (Throwable var4) {
            Throwable throwable = var4;
            handleException(throwable, constr, params);
            return null;
         }
      }
   }

   public static boolean matchesTypes(Class[] pTypes, Class[] cTypes) {
      if (pTypes.length != cTypes.length) {
         return false;
      } else {
         for(int i = 0; i < cTypes.length; ++i) {
            Class oclass = pTypes[i];
            Class oclass1 = cTypes[i];
            if (oclass != oclass1) {
               return false;
            }
         }

         return true;
      }
   }

   private static void dbgCall(boolean isStatic, String callType, bnR refMethod, Object[] params, Object retVal) {
      String s = refMethod.getTargetMethod().getDeclaringClass().getName();
      String s1 = refMethod.getTargetMethod().getName();
      String s2 = "";
      if (isStatic) {
         s2 = " static";
      }

      bmZ.dbg(callType + s2 + " " + s + "." + s1 + "(" + bqh.arrayToString(params) + ") => " + retVal);
   }

   private static void dbgCallVoid(boolean isStatic, String callType, bnR refMethod, Object[] params) {
      String s = refMethod.getTargetMethod().getDeclaringClass().getName();
      String s1 = refMethod.getTargetMethod().getName();
      String s2 = "";
      if (isStatic) {
         s2 = " static";
      }

      bmZ.dbg(callType + s2 + " " + s + "." + s1 + "(" + bqh.arrayToString(params) + ")");
   }

   private static void dbgFieldValue(boolean isStatic, String accessType, bnN refField, Object val) {
      String s = refField.getTargetField().getDeclaringClass().getName();
      String s1 = refField.getTargetField().getName();
      String s2 = "";
      if (isStatic) {
         s2 = " static";
      }

      bmZ.dbg(accessType + s2 + " " + s + "." + s1 + " => " + val);
   }

   private static void handleException(Throwable e, Object obj, bnR refMethod, Object[] params) {
      if (e instanceof InvocationTargetException) {
         Throwable throwable = e.getCause();
         if (throwable instanceof RuntimeException) {
            RuntimeException runtimeexception = (RuntimeException)throwable;
            throw runtimeexception;
         }

         bmZ.error("", e);
      } else {
         bmZ.warn("*** Exception outside of method ***");
         bmZ.warn("Method deactivated: " + refMethod.getTargetMethod());
         refMethod.deactivate();
         if (e instanceof IllegalArgumentException) {
            bmZ.warn("*** IllegalArgumentException ***");
            bmZ.warn("Method: " + refMethod.getTargetMethod());
            bmZ.warn("Object: " + obj);
            bmZ.warn("Parameter classes: " + bqh.arrayToString(getClasses(params)));
            bmZ.warn("Parameters: " + bqh.arrayToString(params));
         }

         bmZ.warn("", e);
      }

   }

   private static void handleException(Throwable e, bnM refConstr, Object[] params) {
      if (e instanceof InvocationTargetException) {
         bmZ.error("", e);
      } else {
         bmZ.warn("*** Exception outside of constructor ***");
         bmZ.warn("Constructor deactivated: " + refConstr.getTargetConstructor());
         refConstr.deactivate();
         if (e instanceof IllegalArgumentException) {
            bmZ.warn("*** IllegalArgumentException ***");
            bmZ.warn("Constructor: " + refConstr.getTargetConstructor());
            bmZ.warn("Parameter classes: " + bqh.arrayToString(getClasses(params)));
            bmZ.warn("Parameters: " + bqh.arrayToString(params));
         }

         bmZ.warn("", e);
      }

   }

   private static Object[] getClasses(Object[] objs) {
      if (objs == null) {
         return new Class[0];
      } else {
         Class[] aclass = new Class[objs.length];

         for(int i = 0; i < aclass.length; ++i) {
            Object object = objs[i];
            if (object != null) {
               aclass[i] = object.getClass();
            }
         }

         return aclass;
      }
   }

   private static bnN[] getReflectorFields(bnL parentClass, Class fieldType, int count) {
      bnN[] areflectorfield = new bnN[count];

      for(int i = 0; i < areflectorfield.length; ++i) {
         areflectorfield[i] = new bnN(parentClass, fieldType, i);
      }

      return areflectorfield;
   }

   private static boolean logEntry(String str) {
      LOGGER.info("[OptiFine] " + str);
      return true;
   }

   private static boolean registerResolvable(final String str) {
      bnI iresolvable = new bnI() {
         public void resolve() {
            bnK.LOGGER.info("[OptiFine] " + str);
         }
      };
      bnT.register(iresolvable);
      return true;
   }

   static {
      ChunkWatchEvent_UnWatch_Constructor = new bnM(ChunkWatchEvent_UnWatch, new Class[]{ChunkPos.class, MG.class});
      CoreModManager = new bnL("net.minecraftforge.fml.relauncher.CoreModManager");
      CoreModManager_onCrash = new bnR(CoreModManager, "onCrash");
      DimensionManager = new bnL("net.minecraftforge.common.DimensionManager");
      DimensionManager_createProviderFor = new bnR(DimensionManager, "createProviderFor");
      DimensionManager_getStaticDimensionIDs = new bnR(DimensionManager, "getStaticDimensionIDs");
      DrawScreenEvent_Pre = new bnL("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre");
      DrawScreenEvent_Pre_Constructor = new bnM(DrawScreenEvent_Pre, new Class[]{lg.class, Integer.TYPE, Integer.TYPE, Float.TYPE});
      DrawScreenEvent_Post = new bnL("net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post");
      DrawScreenEvent_Post_Constructor = new bnM(DrawScreenEvent_Post, new Class[]{lg.class, Integer.TYPE, Integer.TYPE, Float.TYPE});
      EntityViewRenderEvent_CameraSetup = new bnL("net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup");
      EntityViewRenderEvent_CameraSetup_Constructor = new bnM(EntityViewRenderEvent_CameraSetup, new Class[]{xz.class, Ig.class, in.class, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
      EntityViewRenderEvent_CameraSetup_getRoll = new bnR(EntityViewRenderEvent_CameraSetup, "getRoll");
      EntityViewRenderEvent_CameraSetup_getPitch = new bnR(EntityViewRenderEvent_CameraSetup, "getPitch");
      EntityViewRenderEvent_CameraSetup_getYaw = new bnR(EntityViewRenderEvent_CameraSetup, "getYaw");
      EntityViewRenderEvent_FogColors = new bnL("net.minecraftforge.client.event.EntityViewRenderEvent$FogColors");
      EntityViewRenderEvent_FogColors_Constructor = new bnM(EntityViewRenderEvent_FogColors, new Class[]{xz.class, Ig.class, in.class, Double.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
      EntityViewRenderEvent_FogColors_getRed = new bnR(EntityViewRenderEvent_FogColors, "getRed");
      EntityViewRenderEvent_FogColors_getGreen = new bnR(EntityViewRenderEvent_FogColors, "getGreen");
      EntityViewRenderEvent_FogColors_getBlue = new bnR(EntityViewRenderEvent_FogColors, "getBlue");
      EntityViewRenderEvent_RenderFogEvent = new bnL("net.minecraftforge.client.event.EntityViewRenderEvent$RenderFogEvent");
      EntityViewRenderEvent_RenderFogEvent_Constructor = new bnM(EntityViewRenderEvent_RenderFogEvent, new Class[]{xz.class, Ig.class, in.class, Double.TYPE, Integer.TYPE, Float.TYPE});
      Event = new bnL("net.minecraftforge.fml.common.eventhandler.Event");
      Event_isCanceled = new bnR(Event, "isCanceled");
      EventBus = new bnL("net.minecraftforge.fml.common.eventhandler.EventBus");
      EventBus_post = new bnR(EventBus, "post");
      Event_Result = new bnL("net.minecraftforge.fml.common.eventhandler.Event$Result");
      Event_Result_DENY = new bnN(Event_Result, "DENY");
      Event_Result_ALLOW = new bnN(Event_Result, "ALLOW");
      Event_Result_DEFAULT = new bnN(Event_Result, "DEFAULT");
      ExtendedBlockState = new bnL("net.minecraftforge.common.property.ExtendedBlockState");
      ExtendedBlockState_Constructor = new bnM(ExtendedBlockState, new Class[]{co.class, hT[].class, biF[].class});
      FMLClientHandler = new bnL("net.minecraftforge.fml.client.FMLClientHandler");
      FMLClientHandler_instance = new bnR(FMLClientHandler, "instance");
      FMLClientHandler_handleLoadingScreen = new bnR(FMLClientHandler, "handleLoadingScreen");
      FMLClientHandler_isLoading = new bnR(FMLClientHandler, "isLoading");
      FMLClientHandler_refreshResources = new bnR(FMLClientHandler, "refreshResources", new Class[]{biA[].class});
      FMLClientHandler_renderClouds = new bnR(FMLClientHandler, "renderClouds");
      FMLClientHandler_trackBrokenTexture = new bnR(FMLClientHandler, "trackBrokenTexture");
      FMLClientHandler_trackMissingTexture = new bnR(FMLClientHandler, "trackMissingTexture");
      FMLCommonHandler = new bnL("net.minecraftforge.fml.common.FMLCommonHandler");
      FMLCommonHandler_callFuture = new bnR(FMLCommonHandler, "callFuture");
      FMLCommonHandler_enhanceCrashReport = new bnR(FMLCommonHandler, "enhanceCrashReport");
      FMLCommonHandler_getBrandings = new bnR(FMLCommonHandler, "getBrandings");
      FMLCommonHandler_handleServerAboutToStart = new bnR(FMLCommonHandler, "handleServerAboutToStart");
      FMLCommonHandler_handleServerStarting = new bnR(FMLCommonHandler, "handleServerStarting");
      FMLCommonHandler_instance = new bnR(FMLCommonHandler, "instance");
      ActiveRenderInfo = new bnL(rF.class);
      ActiveRenderInfo_getCameraPosition = new bnR(ActiveRenderInfo, "getCameraPosition");
      ActiveRenderInfo_updateRenderInfo2 = new bnR(ActiveRenderInfo, "updateRenderInfo", new Class[]{Ig.class, Boolean.TYPE});
      ForgeBiome = new bnL(Zi.class);
      ForgeBiome_getWaterColorMultiplier = new bnR(ForgeBiome, "getWaterColorMultiplier");
      ForgeBiomeSpawnListEntry = new bnL(Zg.class);
      ForgeBiomeSpawnListEntry_newInstance = new bnR(ForgeBiomeSpawnListEntry, "newInstance");
      ForgeBlock = new bnL(co.class);
      ForgeBlock_addDestroyEffects = new bnR(ForgeBlock, "addDestroyEffects");
      ForgeBlock_addHitEffects = new bnR(ForgeBlock, "addHitEffects");
      ForgeBlock_canCreatureSpawn = new bnR(ForgeBlock, "canCreatureSpawn");
      ForgeBlock_canRenderInLayer = new bnR(ForgeBlock, "canRenderInLayer", new Class[]{in.class, BlockRenderLayer.class});
      ForgeBlock_doesSideBlockRendering = new bnR(ForgeBlock, "doesSideBlockRendering");
      ForgeBlock_doesSideBlockChestOpening = new bnR(ForgeBlock, "doesSideBlockChestOpening");
      ForgeBlock_getBedDirection = new bnR(ForgeBlock, "getBedDirection");
      ForgeBlock_getExtendedState = new bnR(ForgeBlock, "getExtendedState");
      ForgeBlock_getFogColor = new bnR(ForgeBlock, "getFogColor");
      ForgeBlock_getLightOpacity = new bnR(ForgeBlock, "getLightOpacity", new Class[]{in.class, bfZ.class, BlockPos.class});
      ForgeBlock_getLightValue = new bnR(ForgeBlock, "getLightValue", new Class[]{in.class, bfZ.class, BlockPos.class});
      ForgeBlock_getSoundType = new bnR(ForgeBlock, "getSoundType", new Class[]{in.class, bij.class, BlockPos.class, Ig.class});
      ForgeBlock_hasTileEntity = new bnR(ForgeBlock, "hasTileEntity", new Class[]{in.class});
      ForgeBlock_isAir = new bnR(ForgeBlock, "isAir");
      ForgeBlock_isBed = new bnR(ForgeBlock, "isBed");
      ForgeBlock_isBedFoot = new bnR(ForgeBlock, "isBedFoot");
      ForgeBlock_isSideSolid = new bnR(ForgeBlock, "isSideSolid");
      ForgeIBakedModel = new bnL(sc.class);
      ForgeIBakedModel_isAmbientOcclusion2 = new bnR(ForgeIBakedModel, "isAmbientOcclusion", new Class[]{in.class});
      ForgeIBlockProperties = new bnL(im.class);
      ForgeIBlockProperties_getLightValue2 = new bnR(ForgeIBlockProperties, "getLightValue", new Class[]{bfZ.class, BlockPos.class});
      ForgeChunkCache = new bnL(baI.class);
      ForgeChunkCache_isSideSolid = new bnR(ForgeChunkCache, "isSideSolid");
      ForgeEntity = new bnL(Ig.class);
      ForgeEntity_canRiderInteract = new bnR(ForgeEntity, "canRiderInteract");
      ForgeEntity_captureDrops = new bnN(ForgeEntity, "captureDrops");
      ForgeEntity_capturedDrops = new bnN(ForgeEntity, "capturedDrops");
      ForgeEntity_shouldRenderInPass = new bnR(ForgeEntity, "shouldRenderInPass");
      ForgeEntity_shouldRiderSit = new bnR(ForgeEntity, "shouldRiderSit");
      ForgeEntityList = new bnL(Ir.class);
      ForgeEntityList_getClass = new bnR(ForgeEntityList, "getClass", new Class[]{ResourceLocation.class});
      ForgeEntityList_getID = new bnR(ForgeEntityList, "getID", new Class[]{Class.class});
      ForgeEventFactory = new bnL("net.minecraftforge.event.ForgeEventFactory");
      ForgeEventFactory_canEntitySpawn = new bnR(ForgeEventFactory, "canEntitySpawn", new Class[]{Iu.class, bij.class, Float.TYPE, Float.TYPE, Float.TYPE, Boolean.TYPE});
      ForgeEventFactory_canEntityDespawn = new bnR(ForgeEventFactory, "canEntityDespawn");
      ForgeEventFactory_doSpecialSpawn = new bnR(ForgeEventFactory, "doSpecialSpawn", new Class[]{Iu.class, bij.class, Float.TYPE, Float.TYPE, Float.TYPE});
      ForgeEventFactory_getMaxSpawnPackSize = new bnR(ForgeEventFactory, "getMaxSpawnPackSize");
      ForgeEventFactory_getMobGriefingEvent = new bnR(ForgeEventFactory, "getMobGriefingEvent");
      ForgeEventFactory_renderBlockOverlay = new bnR(ForgeEventFactory, "renderBlockOverlay");
      ForgeEventFactory_renderFireOverlay = new bnR(ForgeEventFactory, "renderFireOverlay");
      ForgeEventFactory_renderWaterOverlay = new bnR(ForgeEventFactory, "renderWaterOverlay");
      ForgeHooks = new bnL("net.minecraftforge.common.ForgeHooks");
      ForgeHooks_onLivingAttack = new bnR(ForgeHooks, "onLivingAttack");
      ForgeHooks_onLivingDeath = new bnR(ForgeHooks, "onLivingDeath");
      ForgeHooks_onLivingDrops = new bnR(ForgeHooks, "onLivingDrops");
      ForgeHooks_onLivingFall = new bnR(ForgeHooks, "onLivingFall");
      ForgeHooks_onLivingHurt = new bnR(ForgeHooks, "onLivingHurt");
      ForgeHooks_onLivingJump = new bnR(ForgeHooks, "onLivingJump");
      ForgeHooks_onLivingSetAttackTarget = new bnR(ForgeHooks, "onLivingSetAttackTarget");
      ForgeHooks_onLivingUpdate = new bnR(ForgeHooks, "onLivingUpdate");
      ForgeHooksClient = new bnL("net.minecraftforge.client.ForgeHooksClient");
      ForgeHooksClient_applyTransform_M4 = new bnR(ForgeHooksClient, "applyTransform", new Class[]{Matrix4f.class, Optional.class});
      ForgeHooksClient_applyTransform_MR = new bnR(ForgeHooksClient, "applyTransform", new Class[]{sE.class, Optional.class});
      ForgeHooksClient_applyUVLock = new bnR(ForgeHooksClient, "applyUVLock");
      ForgeHooksClient_dispatchRenderLast = new bnR(ForgeHooksClient, "dispatchRenderLast");
      ForgeHooksClient_drawScreen = new bnR(ForgeHooksClient, "drawScreen");
      ForgeHooksClient_fillNormal = new bnR(ForgeHooksClient, "fillNormal");
      ForgeHooksClient_handleCameraTransforms = new bnR(ForgeHooksClient, "handleCameraTransforms");
      ForgeHooksClient_getArmorModel = new bnR(ForgeHooksClient, "getArmorModel");
      ForgeHooksClient_getArmorTexture = new bnR(ForgeHooksClient, "getArmorTexture");
      ForgeHooksClient_getFogDensity = new bnR(ForgeHooksClient, "getFogDensity");
      ForgeHooksClient_getFOVModifier = new bnR(ForgeHooksClient, "getFOVModifier");
      ForgeHooksClient_getMatrix = new bnR(ForgeHooksClient, "getMatrix", new Class[]{sE.class});
      ForgeHooksClient_getOffsetFOV = new bnR(ForgeHooksClient, "getOffsetFOV");
      ForgeHooksClient_loadEntityShader = new bnR(ForgeHooksClient, "loadEntityShader");
      ForgeHooksClient_onDrawBlockHighlight = new bnR(ForgeHooksClient, "onDrawBlockHighlight");
      ForgeHooksClient_onFogRender = new bnR(ForgeHooksClient, "onFogRender");
      ForgeHooksClient_onScreenshot = new bnR(ForgeHooksClient, "onScreenshot");
      ForgeHooksClient_onTextureStitchedPre = new bnR(ForgeHooksClient, "onTextureStitchedPre");
      ForgeHooksClient_onTextureStitchedPost = new bnR(ForgeHooksClient, "onTextureStitchedPost");
      ForgeHooksClient_orientBedCamera = new bnR(ForgeHooksClient, "orientBedCamera");
      ForgeHooksClient_putQuadColor = new bnR(ForgeHooksClient, "putQuadColor");
      ForgeHooksClient_renderFirstPersonHand = new bnR(ForgeHooksClient, "renderFirstPersonHand");
      ForgeHooksClient_renderLitItem = new bnR(ForgeHooksClient, "renderLitItem");
      ForgeHooksClient_renderMainMenu = new bnR(ForgeHooksClient, "renderMainMenu");
      ForgeHooksClient_renderSpecificFirstPersonHand = new bnR(ForgeHooksClient, "renderSpecificFirstPersonHand");
      ForgeHooksClient_setRenderLayer = new bnR(ForgeHooksClient, "setRenderLayer");
      ForgeHooksClient_setRenderPass = new bnR(ForgeHooksClient, "setRenderPass");
      ForgeHooksClient_shouldCauseReequipAnimation = new bnR(ForgeHooksClient, "shouldCauseReequipAnimation");
      ForgeHooksClient_transform = new bnR(ForgeHooksClient, "transform");
      ForgeItem = new bnL(OL.class);
      ForgeItem_delegate = new bnN(ForgeItem, "delegate");
      ForgeItem_getDurabilityForDisplay = new bnR(ForgeItem, "getDurabilityForDisplay");
      ForgeItem_getEquipmentSlot = new bnR(ForgeItem, "getEquipmentSlot");
      ForgeItem_getTileEntityItemStackRenderer = new bnR(ForgeItem, "getTileEntityItemStackRenderer");
      ForgeItem_getRGBDurabilityForDisplay = new bnR(ForgeItem, "getRGBDurabilityForDisplay");
      ForgeItem_isShield = new bnR(ForgeItem, "isShield");
      ForgeItem_onEntitySwing = new bnR(ForgeItem, "onEntitySwing");
      ForgeItem_shouldCauseReequipAnimation = new bnR(ForgeItem, "shouldCauseReequipAnimation");
      ForgeItem_showDurabilityBar = new bnR(ForgeItem, "showDurabilityBar");
      ForgeItemArmor = new bnL(OR.class);
      ForgeItemArmor_hasOverlay = new bnR(ForgeItemArmor, "hasOverlay");
      ForgeKeyBinding = new bnL(Bl.class);
      ForgeKeyBinding_setKeyConflictContext = new bnR(ForgeKeyBinding, "setKeyConflictContext");
      ForgeKeyBinding_setKeyModifierAndCode = new bnR(ForgeKeyBinding, "setKeyModifierAndCode");
      ForgeKeyBinding_getKeyModifier = new bnR(ForgeKeyBinding, "getKeyModifier");
      ForgeModContainer = new bnL("net.minecraftforge.common.ForgeModContainer");
      ForgeModContainer_forgeLightPipelineEnabled = new bnN(ForgeModContainer, "forgeLightPipelineEnabled");
      ForgeModContainer_allowEmissiveItems = new bnN(ForgeModContainer, "allowEmissiveItems");
      ForgeModelBlockDefinition = new bnL(sB.class);
      ForgeModelBlockDefinition_parseFromReader2 = new bnR(ForgeModelBlockDefinition, "parseFromReader", new Class[]{Reader.class, ResourceLocation.class});
      ForgePotion = new bnL(VW.class);
      ForgePotion_shouldRenderHUD = ForgePotion.makeMethod("shouldRenderHUD");
      ForgePotion_renderHUDEffect = ForgePotion.makeMethod("renderHUDEffect", new Class[]{VZ.class, jI.class, Integer.TYPE, Integer.TYPE, Float.TYPE, Float.TYPE});
      ForgePotionEffect = new bnL(VZ.class);
      ForgePotionEffect_isCurativeItem = new bnR(ForgePotionEffect, "isCurativeItem");
      ForgeTileEntity = new bnL(Yg.class);
      ForgeTileEntity_canRenderBreaking = new bnR(ForgeTileEntity, "canRenderBreaking");
      ForgeTileEntity_getRenderBoundingBox = new bnR(ForgeTileEntity, "getRenderBoundingBox");
      ForgeTileEntity_hasFastRenderer = new bnR(ForgeTileEntity, "hasFastRenderer");
      ForgeTileEntity_shouldRenderInPass = new bnR(ForgeTileEntity, "shouldRenderInPass");
      ForgeVertexFormatElementEnumUseage = new bnL(zQ.class);
      ForgeVertexFormatElementEnumUseage_preDraw = new bnR(ForgeVertexFormatElementEnumUseage, "preDraw");
      ForgeVertexFormatElementEnumUseage_postDraw = new bnR(ForgeVertexFormatElementEnumUseage, "postDraw");
      ForgeWorld = new bnL(bij.class);
      ForgeWorld_countEntities = new bnR(ForgeWorld, "countEntities", new Class[]{IC.class, Boolean.TYPE});
      ForgeWorld_getPerWorldStorage = new bnR(ForgeWorld, "getPerWorldStorage");
      ForgeWorld_initCapabilities = new bnR(ForgeWorld, "initCapabilities");
      ForgeWorldProvider = new bnL(bil.class);
      ForgeWorldProvider_getCloudRenderer = new bnR(ForgeWorldProvider, "getCloudRenderer");
      ForgeWorldProvider_getSkyRenderer = new bnR(ForgeWorldProvider, "getSkyRenderer");
      ForgeWorldProvider_getWeatherRenderer = new bnR(ForgeWorldProvider, "getWeatherRenderer");
      ForgeWorldProvider_getLightmapColors = new bnR(ForgeWorldProvider, "getLightmapColors");
      ForgeWorldProvider_getSaveFolder = new bnR(ForgeWorldProvider, "getSaveFolder");
      GuiModList = new bnL("net.minecraftforge.fml.client.GuiModList");
      GuiModList_Constructor = new bnM(GuiModList, new Class[]{lg.class});
      IExtendedBlockState = new bnL("net.minecraftforge.common.property.IExtendedBlockState");
      IExtendedBlockState_getClean = new bnR(IExtendedBlockState, "getClean");
      IForgeRegistryEntry_Impl = new bnL("net.minecraftforge.registries.IForgeRegistryEntry$Impl");
      IForgeRegistryEntry_Impl_getRegistryName = new bnR(IForgeRegistryEntry_Impl, "getRegistryName");
      IModel = new bnL("net.minecraftforge.client.model.IModel");
      IModel_getTextures = new bnR(IModel, "getTextures");
      IRenderHandler = new bnL("net.minecraftforge.client.IRenderHandler");
      IRenderHandler_render = new bnR(IRenderHandler, "render");
      ItemModelMesherForge = new bnL("net.minecraftforge.client.ItemModelMesherForge");
      ItemModelMesherForge_Constructor = new bnM(ItemModelMesherForge, new Class[]{sC.class});
      KeyConflictContext = new bnL("net.minecraftforge.client.settings.KeyConflictContext");
      KeyConflictContext_IN_GAME = new bnN(KeyConflictContext, "IN_GAME");
      KeyModifier = new bnL("net.minecraftforge.client.settings.KeyModifier");
      KeyModifier_valueFromString = new bnR(KeyModifier, "valueFromString");
      KeyModifier_NONE = new bnN(KeyModifier, "NONE");
      Launch = new bnL("net.minecraft.launchwrapper.Launch");
      Launch_blackboard = new bnN(Launch, "blackboard");
      LightUtil = new bnL("net.minecraftforge.client.model.pipeline.LightUtil");
      LightUtil_itemConsumer = new bnN(LightUtil, "itemConsumer");
      LightUtil_putBakedQuad = new bnR(LightUtil, "putBakedQuad");
      LightUtil_renderQuadColor = new bnR(LightUtil, "renderQuadColor");
      LightUtil_tessellator = new bnN(LightUtil, "tessellator");
      Loader = new bnL("net.minecraftforge.fml.common.Loader");
      Loader_getActiveModList = new bnR(Loader, "getActiveModList");
      Loader_instance = new bnR(Loader, "instance");
      MinecraftForge = new bnL("net.minecraftforge.common.MinecraftForge");
      MinecraftForge_EVENT_BUS = new bnN(MinecraftForge, "EVENT_BUS");
      MinecraftForgeClient = new bnL("net.minecraftforge.client.MinecraftForgeClient");
      MinecraftForgeClient_getImageLayer = new bnR(MinecraftForgeClient, "getImageLayer");
      MinecraftForgeClient_getRenderPass = new bnR(MinecraftForgeClient, "getRenderPass");
      MinecraftForgeClient_onRebuildChunk = new bnR(MinecraftForgeClient, "onRebuildChunk");
      ModContainer = new bnL("net.minecraftforge.fml.common.ModContainer");
      ModContainer_getModId = new bnR(ModContainer, "getModId");
      ModelLoader = new bnL("net.minecraftforge.client.model.ModelLoader");
      ModelLoader_stateModels = new bnN(ModelLoader, "stateModels");
      ModelLoader_onRegisterItems = new bnR(ModelLoader, "onRegisterItems");
      ModelLoader_getInventoryVariant = new bnR(ModelLoader, "getInventoryVariant");
      ModelLoader_VanillaLoader = new bnL("net.minecraftforge.client.model.ModelLoader$VanillaLoader");
      ModelLoader_VanillaLoader_INSTANCE = new bnN(ModelLoader_VanillaLoader, "INSTANCE");
      ModelLoader_VanillaLoader_loadModel = new bnR(ModelLoader_VanillaLoader, "loadModel");
      ModelLoaderRegistry = new bnL("net.minecraftforge.client.model.ModelLoaderRegistry");
      ModelLoaderRegistry_textures = new bnN(ModelLoaderRegistry, "textures");
      NotificationModUpdateScreen = new bnL("net.minecraftforge.client.gui.NotificationModUpdateScreen");
      NotificationModUpdateScreen_init = new bnR(NotificationModUpdateScreen, "init");
      RenderBlockOverlayEvent_OverlayType = new bnL("net.minecraftforge.client.event.RenderBlockOverlayEvent$OverlayType");
      RenderBlockOverlayEvent_OverlayType_BLOCK = new bnN(RenderBlockOverlayEvent_OverlayType, "BLOCK");
      RenderingRegistry = new bnL("net.minecraftforge.fml.client.registry.RenderingRegistry");
      RenderingRegistry_loadEntityRenderers = new bnR(RenderingRegistry, "loadEntityRenderers", new Class[]{wC.class, Map.class});
      RenderItemInFrameEvent = new bnL("net.minecraftforge.client.event.RenderItemInFrameEvent");
      RenderItemInFrameEvent_Constructor = new bnM(RenderItemInFrameEvent, new Class[]{IZ.class, wt.class});
      RenderLivingEvent_Pre = new bnL("net.minecraftforge.client.event.RenderLivingEvent$Pre");
      RenderLivingEvent_Pre_Constructor = new bnM(RenderLivingEvent_Pre, new Class[]{Iw.class, wy.class, Float.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});
      RenderLivingEvent_Post = new bnL("net.minecraftforge.client.event.RenderLivingEvent$Post");
      RenderLivingEvent_Post_Constructor = new bnM(RenderLivingEvent_Post, new Class[]{Iw.class, wy.class, Float.TYPE, Double.TYPE, Double.TYPE, Double.TYPE});
      RenderLivingEvent_Specials_Pre = new bnL("net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre");
      RenderLivingEvent_Specials_Pre_Constructor = new bnM(RenderLivingEvent_Specials_Pre, new Class[]{Iw.class, wy.class, Double.TYPE, Double.TYPE, Double.TYPE});
      RenderLivingEvent_Specials_Post = new bnL("net.minecraftforge.client.event.RenderLivingEvent$Specials$Post");
      RenderLivingEvent_Specials_Post_Constructor = new bnM(RenderLivingEvent_Specials_Post, new Class[]{Iw.class, wy.class, Double.TYPE, Double.TYPE, Double.TYPE});
      ScreenshotEvent = new bnL("net.minecraftforge.client.event.ScreenshotEvent");
      ScreenshotEvent_getCancelMessage = new bnR(ScreenshotEvent, "getCancelMessage");
      ScreenshotEvent_getScreenshotFile = new bnR(ScreenshotEvent, "getScreenshotFile");
      ScreenshotEvent_getResultMessage = new bnR(ScreenshotEvent, "getResultMessage");
      SplashScreen = new bnL("net.minecraftforge.fml.client.SplashProgress");
      VanillaResourceType = new bnL("net.minecraftforge.client.resource.VanillaResourceType");
      VanillaResourceType_TEXTURES = new bnN(VanillaResourceType, "TEXTURES");
      WorldEvent_Load = new bnL("net.minecraftforge.event.world.WorldEvent$Load");
      WorldEvent_Load_Constructor = new bnM(WorldEvent_Load, new Class[]{bij.class});
      logVanilla = logEntry("*** Reflector Vanilla ***");
      ChunkProviderClient = new bnL(oW.class);
      ChunkProviderClient_chunkMapping = new bnN(ChunkProviderClient, Long2ObjectMap.class);
      EntityVillager = new bnL(Mq.class);
      EntityVillager_careerId = new bnN(new bnG(Mq.class, new Class[0], Integer.TYPE, new Class[]{Integer.TYPE, Boolean.TYPE, Boolean.TYPE, InventoryBasic.class}, "EntityVillager.careerId"));
      EntityVillager_careerLevel = new bnN(new bnG(Mq.class, new Class[]{Integer.TYPE}, Integer.TYPE, new Class[]{Boolean.TYPE, Boolean.TYPE, InventoryBasic.class}, "EntityVillager.careerLevel"));
      GuiBeacon = new bnL(lR.class);
      GuiBeacon_tileBeacon = new bnN(GuiBeacon, IInventory.class);
      GuiBrewingStand = new bnL(lS.class);
      GuiBrewingStand_tileBrewingStand = new bnN(GuiBrewingStand, IInventory.class);
      GuiChest = new bnL(lT.class);
      GuiChest_lowerChestInventory = new bnN(GuiChest, IInventory.class, 1);
      GuiEnchantment = new bnL(kf.class);
      GuiEnchantment_nameable = new bnN(GuiEnchantment, bgd.class);
      GuiFurnace = new bnL(mg.class);
      GuiFurnace_tileFurnace = new bnN(GuiFurnace, IInventory.class);
      GuiHopper = new bnL(kl.class);
      GuiHopper_hopperInventory = new bnN(GuiHopper, IInventory.class, 1);
      GuiMainMenu = new bnL(0cx.class);
      GuiMainMenu_splashText = new bnN(GuiMainMenu, String.class);
      GuiShulkerBox = new bnL(mj.class);
      GuiShulkerBox_inventory = new bnN(GuiShulkerBox, IInventory.class);
      ItemOverride = new bnL(sm.class);
      ItemOverride_mapResourceValues = new bnN(ItemOverride, Map.class);
      LegacyV2Adapter = new bnL(AG.class);
      LegacyV2Adapter_pack = new bnN(LegacyV2Adapter, AC.class);
      Minecraft = new bnL(nC.class);
      Minecraft_defaultResourcePack = new bnN(Minecraft, Aq.class);
      Minecraft_actionKeyF3 = new bnN(new bnC());
      ModelHumanoidHead = new bnL(of.class);
      ModelHumanoidHead_head = new bnN(ModelHumanoidHead, ow.class);
      ModelBat = new bnL(nI.class);
      ModelBat_ModelRenderers = new bnO(ModelBat, ow.class, 6);
      ModelBlaze = new bnL(nN.class);
      ModelBlaze_blazeHead = new bnN(ModelBlaze, ow.class);
      ModelBlaze_blazeSticks = new bnN(ModelBlaze, ow[].class);
      ModelDragon = new bnL(nV.class);
      ModelDragon_ModelRenderers = new bnO(ModelDragon, ow.class, 12);
      ModelEnderCrystal = new bnL(nY.class);
      ModelEnderCrystal_ModelRenderers = new bnO(ModelEnderCrystal, ow.class, 3);
      RenderEnderCrystal = new bnL(vZ.class);
      RenderEnderCrystal_modelEnderCrystal = new bnN(RenderEnderCrystal, nH.class, 0);
      RenderEnderCrystal_modelEnderCrystalNoBase = new bnN(RenderEnderCrystal, nH.class, 1);
      ModelEnderMite = new bnL(oa.class);
      ModelEnderMite_bodyParts = new bnN(ModelEnderMite, ow[].class);
      ModelEvokerFangs = new bnL(ob.class);
      ModelEvokerFangs_ModelRenderers = new bnO(ModelEvokerFangs, ow.class, 3);
      ModelGhast = new bnL(oc.class);
      ModelGhast_body = new bnN(ModelGhast, ow.class);
      ModelGhast_tentacles = new bnN(ModelGhast, ow[].class);
      ModelGuardian = new bnL(od.class);
      ModelGuardian_body = new bnN(ModelGuardian, ow.class, 0);
      ModelGuardian_eye = new bnN(ModelGuardian, ow.class, 1);
      ModelGuardian_spines = new bnN(ModelGuardian, ow[].class, 0);
      ModelGuardian_tail = new bnN(ModelGuardian, ow[].class, 1);
      ModelDragonHead = new bnL(nW.class);
      ModelDragonHead_head = new bnN(ModelDragonHead, ow.class, 0);
      ModelDragonHead_jaw = new bnN(ModelDragonHead, ow.class, 1);
      ModelHorse = new bnL(oe.class);
      ModelHorse_ModelRenderers = new bnO(ModelHorse, ow.class, 39);
      RenderLeashKnot = new bnL(wu.class);
      RenderLeashKnot_leashKnotModel = new bnN(RenderLeashKnot, oj.class);
      ModelMagmaCube = new bnL(om.class);
      ModelMagmaCube_core = new bnN(ModelMagmaCube, ow.class);
      ModelMagmaCube_segments = new bnN(ModelMagmaCube, ow[].class);
      ModelOcelot = new bnL(oo.class);
      ModelOcelot_ModelRenderers = new bnO(ModelOcelot, ow.class, 8);
      ModelParrot = new bnL(oq.class);
      ModelParrot_ModelRenderers = new bnO(ModelParrot, ow.class, 11);
      ModelRabbit = new bnL(ov.class);
      ModelRabbit_renderers = new bnO(ModelRabbit, ow.class, 12);
      ModelSilverfish = new bnL(oD.class);
      ModelSilverfish_bodyParts = new bnN(ModelSilverfish, ow[].class, 0);
      ModelSilverfish_wingParts = new bnN(ModelSilverfish, ow[].class, 1);
      ModelSlime = new bnL(oG.class);
      ModelSlime_ModelRenderers = new bnO(ModelSlime, ow.class, 4);
      ModelSquid = new bnL(oJ.class);
      ModelSquid_body = new bnN(ModelSquid, ow.class);
      ModelSquid_tentacles = new bnN(ModelSquid, ow[].class);
      ModelVex = new bnL(oK.class);
      ModelVex_leftWing = new bnN(ModelVex, ow.class, 0);
      ModelVex_rightWing = new bnN(ModelVex, ow.class, 1);
      ModelWitch = new bnL(oM.class);
      ModelWitch_mole = new bnN(ModelWitch, ow.class, 0);
      ModelWitch_hat = new bnN(ModelWitch, ow.class, 1);
      ModelWither = new bnL(oN.class);
      ModelWither_bodyParts = new bnN(ModelWither, ow[].class, 0);
      ModelWither_heads = new bnN(ModelWither, ow[].class, 1);
      ModelWolf = new bnL(oO.class);
      ModelWolf_tail = new bnN(ModelWolf, ow.class, 6);
      ModelWolf_mane = new bnN(ModelWolf, ow.class, 7);
      OptiFineClassTransformer = new bnL("optifine.OptiFineClassTransformer");
      OptiFineClassTransformer_instance = new bnN(OptiFineClassTransformer, "instance");
      OptiFineClassTransformer_getOptiFineResource = new bnR(OptiFineClassTransformer, "getOptiFineResource");
      RenderBoat = new bnL(vR.class);
      RenderBoat_modelBoat = new bnN(RenderBoat, nH.class);
      RenderEvokerFangs = new bnL(wg.class);
      RenderEvokerFangs_model = new bnN(RenderEvokerFangs, ob.class);
      RenderMinecart = new bnL(wD.class);
      RenderMinecart_modelMinecart = new bnN(RenderMinecart, nH.class);
      RenderShulkerBullet = new bnL(wU.class);
      RenderShulkerBullet_model = new bnN(RenderShulkerBullet, oB.class);
      RenderWitherSkull = new bnL(xp.class);
      RenderWitherSkull_model = new bnN(RenderWitherSkull, oF.class);
      TileEntityBannerRenderer = new bnL(zo.class);
      TileEntityBannerRenderer_bannerModel = new bnN(TileEntityBannerRenderer, nG.class);
      TileEntityBedRenderer = new bnL(zq.class);
      TileEntityBedRenderer_model = new bnN(TileEntityBedRenderer, nJ.class);
      TileEntityBeacon = new bnL(Yj.class);
      TileEntityBeacon_customName = new bnN(TileEntityBeacon, String.class);
      TileEntityBrewingStand = new bnL(Yl.class);
      TileEntityBrewingStand_customName = new bnN(TileEntityBrewingStand, String.class);
      TileEntityChestRenderer = new bnL(zr.class);
      TileEntityChestRenderer_simpleChest = new bnN(TileEntityChestRenderer, nR.class, 0);
      TileEntityChestRenderer_largeChest = new bnN(TileEntityChestRenderer, nR.class, 1);
      TileEntityEnchantmentTable = new bnL(Yv.class);
      TileEntityEnchantmentTable_customName = new bnN(TileEntityEnchantmentTable, String.class);
      TileEntityEnchantmentTableRenderer = new bnL(zs.class);
      TileEntityEnchantmentTableRenderer_modelBook = new bnN(TileEntityEnchantmentTableRenderer, nP.class);
      TileEntityEnderChestRenderer = new bnL(zt.class);
      TileEntityEnderChestRenderer_modelChest = new bnN(TileEntityEnderChestRenderer, nR.class);
      TileEntityFurnace = new bnL(YA.class);
      TileEntityFurnace_customName = new bnN(TileEntityFurnace, String.class);
      TileEntityLockableLoot = new bnL(YD.class);
      TileEntityLockableLoot_customName = new bnN(TileEntityLockableLoot, String.class);
      TileEntityShulkerBoxRenderer = new bnL(zB.class);
      TileEntityShulkerBoxRenderer_model = new bnN(TileEntityShulkerBoxRenderer, oA.class);
      TileEntitySignRenderer = new bnL(zC.class);
      TileEntitySignRenderer_model = new bnN(TileEntitySignRenderer, oC.class);
      TileEntitySkullRenderer = new bnL(zE.class);
      TileEntitySkullRenderer_dragonHead = new bnN(TileEntitySkullRenderer, nW.class, 0);
      TileEntitySkullRenderer_skeletonHead = new bnN(TileEntitySkullRenderer, oF.class, 0);
      TileEntitySkullRenderer_humanoidHead = new bnN(TileEntitySkullRenderer, oF.class, 1);
   }
}
