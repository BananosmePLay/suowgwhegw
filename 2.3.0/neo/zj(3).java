package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class zj extends yO implements yT {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final ResourceLocation LOCATION_MISSING_TEXTURE = new ResourceLocation("missingno");
   public static final ResourceLocation LOCATION_BLOCKS_TEXTURE = new ResourceLocation("textures/atlas/blocks.png");
   private final List<zd> listAnimatedSprites;
   private final Map<String, zd> mapRegisteredSprites;
   private final Map<String, zd> mapUploadedSprites;
   private final String basePath;
   private final yQ iconCreator;
   private int mipmapLevels;
   private final zd missingImage;
   private zd[] iconGrid;
   private int iconGridSize;
   private int iconGridCountX;
   private int iconGridCountY;
   private double iconGridSizeU;
   private double iconGridSizeV;
   private bqn counterIndexInMap;
   public int atlasWidth;
   public int atlasHeight;
   private int countAnimationsActive;
   private int frameCountAnimations;

   public zj(String basePathIn) {
      this(basePathIn, (yQ)null);
   }

   public zj(String p_i8_1_, boolean p_i8_2_) {
      this(p_i8_1_, (yQ)null, p_i8_2_);
   }

   public zj(String basePathIn, @Nullable yQ iconCreatorIn) {
      this(basePathIn, iconCreatorIn, false);
   }

   public zj(String p_i9_1_, yQ p_i9_2_, boolean p_i9_3_) {
      this.iconGrid = null;
      this.iconGridSize = -1;
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGridSizeU = -1.0;
      this.iconGridSizeV = -1.0;
      this.counterIndexInMap = new bqn(0);
      this.atlasWidth = 0;
      this.atlasHeight = 0;
      this.listAnimatedSprites = Lists.newArrayList();
      this.mapRegisteredSprites = Maps.newHashMap();
      this.mapUploadedSprites = Maps.newHashMap();
      this.missingImage = new zd("missingno");
      this.basePath = p_i9_1_;
      this.iconCreator = p_i9_2_;
   }

   private void initMissingImage() {
      int i = this.getMinSpriteSize();
      int[] aint = this.getMissingImageData(i);
      this.missingImage.setIconWidth(i);
      this.missingImage.setIconHeight(i);
      int[][] aint1 = new int[this.mipmapLevels + 1][];
      aint1[0] = aint;
      this.missingImage.setFramesTextureData(Lists.newArrayList(new int[][][]{aint1}));
      this.missingImage.setIndexInMap(this.counterIndexInMap.nextValue());
   }

   public void loadTexture(AA resourceManager) throws IOException {
      if (this.iconCreator != null) {
         this.loadSprites(resourceManager, this.iconCreator);
      }

   }

   public void loadSprites(AA resourceManager, yQ iconCreatorIn) {
      this.mapRegisteredSprites.clear();
      this.counterIndexInMap.reset();
      bnK.callVoid(bnK.ForgeHooksClient_onTextureStitchedPre, this);
      iconCreatorIn.registerSprites(this);
      if (this.mipmapLevels >= 4) {
         this.mipmapLevels = this.detectMaxMipmapLevel(this.mapRegisteredSprites, resourceManager);
         XH.log("Mipmap levels: " + this.mipmapLevels);
      }

      this.initMissingImage();
      this.deleteGlTexture();
      this.loadTextureAtlas(resourceManager);
   }

   public void loadTextureAtlas(AA resourceManager) {
      XH.dbg("Multitexture: " + XH.isMultiTexture());
      if (XH.isMultiTexture()) {
         Iterator var2 = this.mapUploadedSprites.values().iterator();

         while(var2.hasNext()) {
            zd textureatlassprite = (zd)var2.next();
            textureatlassprite.deleteSpriteTexture();
         }
      }

      bjj.updateIcons(this);
      bjG.updateIcons(this);
      biI.updateIcons(this);
      int i2 = bqS.getGLMaximumTextureSize();
      zb stitcher = new zb(i2, i2, 0, this.mipmapLevels);
      this.mapUploadedSprites.clear();
      this.listAnimatedSprites.clear();
      int i = Integer.MAX_VALUE;
      int j = this.getMinSpriteSize();
      this.iconGridSize = j;
      int k = 1 << this.mipmapLevels;
      int l = 0;
      int i1 = 0;
      bpY.reset();
      List<zd> list = new ArrayList(this.mapRegisteredSprites.values());

      int j2;
      int j3;
      for(j2 = 0; j2 < list.size(); ++j2) {
         zd textureatlassprite1 = bpY.resolveDependencies(list, j2, this);
         ResourceLocation resourcelocation = this.getResourceLocation(textureatlassprite1);
         Az iresource = null;
         textureatlassprite1.updateIndexInMap(this.counterIndexInMap);
         if (textureatlassprite1.hasCustomLoader(resourceManager, resourcelocation)) {
            if (textureatlassprite1.load(resourceManager, resourcelocation, (p_lambda$loadTextureAtlas$0_1_) -> {
               return (zd)this.mapRegisteredSprites.get(p_lambda$loadTextureAtlas$0_1_.toString());
            })) {
               XH.detail("Custom loader (skipped): " + textureatlassprite1);
               ++i1;
               continue;
            }

            XH.detail("Custom loader: " + textureatlassprite1);
            ++l;
         } else {
            try {
               yX pngsizeinfo = yX.makeFromResource(resourceManager.getResource(resourcelocation));
               iresource = resourceManager.getResource(resourcelocation);
               boolean flag = iresource.getMetadata("animation") != null;
               textureatlassprite1.loadSprite(pngsizeinfo, flag);
            } catch (RuntimeException var30) {
               LOGGER.error("Unable to parse metadata from {}", resourcelocation, var30);
               bnQ.FMLClientHandler_trackBrokenTexture(resourcelocation, var30.getMessage());
               continue;
            } catch (IOException var31) {
               IOException ioexception = var31;
               LOGGER.error("Using missing texture, unable to load " + resourcelocation + ", " + ioexception.getClass().getName());
               bnQ.FMLClientHandler_trackMissingTexture(resourcelocation);
               continue;
            } finally {
               IOUtils.closeQuietly(iresource);
            }
         }

         j3 = textureatlassprite1.getIconWidth();
         int k3 = textureatlassprite1.getIconHeight();
         if (j3 >= 1 && k3 >= 1) {
            int k1;
            if (j3 < j || this.mipmapLevels > 0) {
               k1 = this.mipmapLevels > 0 ? bqS.scaleToGrid(j3, j) : bqS.scaleToMin(j3, j);
               if (k1 != j3) {
                  if (!bqS.isPowerOfTwo(j3)) {
                     XH.log("Scaled non power of 2: " + textureatlassprite1.getIconName() + ", " + j3 + " -> " + k1);
                  } else {
                     XH.log("Scaled too small texture: " + textureatlassprite1.getIconName() + ", " + j3 + " -> " + k1);
                  }

                  int l1 = k3 * k1 / j3;
                  textureatlassprite1.setIconWidth(k1);
                  textureatlassprite1.setIconHeight(l1);
               }
            }

            i = Math.min(i, Math.min(textureatlassprite1.getIconWidth(), textureatlassprite1.getIconHeight()));
            k1 = Math.min(Integer.lowestOneBit(textureatlassprite1.getIconWidth()), Integer.lowestOneBit(textureatlassprite1.getIconHeight()));
            if (k1 < k) {
               LOGGER.warn("Texture {} with size {}x{} limits mip level from {} to {}", resourcelocation, textureatlassprite1.getIconWidth(), textureatlassprite1.getIconHeight(), MathHelper.log2(k), MathHelper.log2(k1));
               k = k1;
            }

            if (this.generateMipmaps(resourceManager, textureatlassprite1)) {
               stitcher.addSprite(textureatlassprite1);
            }
         } else {
            XH.warn("Invalid sprite size: " + textureatlassprite1);
         }
      }

      if (l > 0) {
         XH.dbg("Custom loader sprites: " + l);
      }

      if (i1 > 0) {
         XH.dbg("Custom loader sprites (skipped): " + i1);
      }

      if (bpY.getCountDependencies() > 0) {
         XH.dbg("Sprite dependencies: " + bpY.getCountDependencies());
      }

      j2 = Math.min(i, k);
      int k2 = MathHelper.log2(j2);
      if (k2 < 0) {
         k2 = 0;
      }

      if (k2 < this.mipmapLevels) {
         LOGGER.warn("{}: dropping miplevel from {} to {}, because of minimum power of two: {}", this.basePath, this.mipmapLevels, k2, j2);
         this.mipmapLevels = k2;
      }

      this.missingImage.generateMipmaps(this.mipmapLevels);
      stitcher.addSprite(this.missingImage);

      try {
         stitcher.doStitch();
      } catch (yM var29) {
         throw var29;
      }

      LOGGER.info("Created: {}x{} {}-atlas", stitcher.getCurrentWidth(), stitcher.getCurrentHeight(), this.basePath);
      if (XH.isShaders()) {
         bps.allocateTextureMap(this.getGlTextureId(), this.mipmapLevels, stitcher.getCurrentWidth(), stitcher.getCurrentHeight(), stitcher, this);
      } else {
         zk.allocateTextureImpl(this.getGlTextureId(), this.mipmapLevels, stitcher.getCurrentWidth(), stitcher.getCurrentHeight());
      }

      Map<String, zd> map = Maps.newHashMap(this.mapRegisteredSprites);
      Iterator var38 = stitcher.getStichSlots().iterator();

      zd textureatlassprite2;
      while(var38.hasNext()) {
         textureatlassprite2 = (zd)var38.next();
         String s = textureatlassprite2.getIconName();
         map.remove(s);
         this.mapUploadedSprites.put(s, textureatlassprite2);

         try {
            if (XH.isShaders()) {
               bps.uploadTexSubForLoadAtlas(this, textureatlassprite2.getIconName(), textureatlassprite2.getFrameTextureData(0), textureatlassprite2.getIconWidth(), textureatlassprite2.getIconHeight(), textureatlassprite2.getOriginX(), textureatlassprite2.getOriginY(), false, false);
            } else {
               zk.uploadTextureMipmap(textureatlassprite2.getFrameTextureData(0), textureatlassprite2.getIconWidth(), textureatlassprite2.getIconHeight(), textureatlassprite2.getOriginX(), textureatlassprite2.getOriginY(), false, false);
            }
         } catch (Throwable var28) {
            Er crashreport = Er.makeCrashReport(var28, "Stitching texture atlas");
            Ey crashreportcategory = crashreport.makeCategory("Texture being stitched together");
            crashreportcategory.addCrashSection("Atlas path", this.basePath);
            crashreportcategory.addCrashSection("Sprite", textureatlassprite2);
            throw new ReportedException(crashreport);
         }

         if (textureatlassprite2.hasAnimationMetadata()) {
            textureatlassprite2.setAnimationIndex(this.listAnimatedSprites.size());
            this.listAnimatedSprites.add(textureatlassprite2);
         }
      }

      var38 = map.values().iterator();

      while(var38.hasNext()) {
         textureatlassprite2 = (zd)var38.next();
         textureatlassprite2.copyFrom(this.missingImage);
      }

      XH.log("Animated sprites: " + this.listAnimatedSprites.size());
      if (XH.isMultiTexture()) {
         int l2 = stitcher.getCurrentWidth();
         j3 = stitcher.getCurrentHeight();
         Iterator var46 = stitcher.getStichSlots().iterator();

         while(var46.hasNext()) {
            zd textureatlassprite4 = (zd)var46.next();
            textureatlassprite4.sheetWidth = l2;
            textureatlassprite4.sheetHeight = j3;
            textureatlassprite4.mipmapLevels = this.mipmapLevels;
            zd textureatlassprite5 = textureatlassprite4.spriteSingle;
            if (textureatlassprite5 != null) {
               if (textureatlassprite5.getIconWidth() <= 0) {
                  textureatlassprite5.setIconWidth(textureatlassprite4.getIconWidth());
                  textureatlassprite5.setIconHeight(textureatlassprite4.getIconHeight());
                  textureatlassprite5.initSprite(textureatlassprite4.getIconWidth(), textureatlassprite4.getIconHeight(), 0, 0, false);
                  textureatlassprite5.clearFramesTextureData();
                  List<int[][]> list1 = textureatlassprite4.getFramesTextureData();
                  textureatlassprite5.setFramesTextureData(list1);
                  textureatlassprite5.setAnimationMetadata(textureatlassprite4.getAnimationMetadata());
               }

               textureatlassprite5.sheetWidth = l2;
               textureatlassprite5.sheetHeight = j3;
               textureatlassprite5.mipmapLevels = this.mipmapLevels;
               textureatlassprite5.setAnimationIndex(textureatlassprite4.getAnimationIndex());
               textureatlassprite4.bindSpriteTexture();
               boolean flag2 = false;
               boolean flag1 = true;

               try {
                  zk.uploadTextureMipmap(textureatlassprite5.getFrameTextureData(0), textureatlassprite5.getIconWidth(), textureatlassprite5.getIconHeight(), textureatlassprite5.getOriginX(), textureatlassprite5.getOriginY(), flag2, flag1);
               } catch (Exception var27) {
                  Exception exception = var27;
                  XH.dbg("Error uploading sprite single: " + textureatlassprite5 + ", parent: " + textureatlassprite4);
                  exception.printStackTrace();
               }
            }
         }

         XH.getMinecraft().getTextureManager().bindTexture(LOCATION_BLOCKS_TEXTURE);
      }

      bnK.callVoid(bnK.ForgeHooksClient_onTextureStitchedPost, this);
      this.updateIconGrid(stitcher.getCurrentWidth(), stitcher.getCurrentHeight());
      if (XH.equals(System.getProperty("saveTextureMap"), "true")) {
         XH.dbg("Exporting texture map: " + this.basePath);
         bqS.saveGlTexture("debug/" + this.basePath.replaceAll("/", "_"), this.getGlTextureId(), this.mipmapLevels, stitcher.getCurrentWidth(), stitcher.getCurrentHeight());
      }

   }

   public boolean generateMipmaps(AA resourceManager, final zd texture) {
      ResourceLocation resourcelocation1 = this.getResourceLocation(texture);
      Az iresource1 = null;
      if (texture.hasCustomLoader(resourceManager, resourcelocation1)) {
         bqS.generateCustomMipmaps(texture, this.mipmapLevels);
      } else {
         label60: {
            boolean var8;
            try {
               boolean flag4;
               try {
                  iresource1 = resourceManager.getResource(resourcelocation1);
                  texture.loadSpriteFrames(iresource1, this.mipmapLevels + 1);
                  break label60;
               } catch (RuntimeException var15) {
                  RuntimeException runtimeexception1 = var15;
                  LOGGER.error("Unable to parse metadata from {}", resourcelocation1, runtimeexception1);
                  flag4 = false;
                  return flag4;
               } catch (IOException var16) {
                  IOException ioexception1 = var16;
                  LOGGER.error("Using missing texture, unable to load {}", resourcelocation1, ioexception1);
                  flag4 = false;
                  boolean crashreportcategory = flag4;
                  var8 = crashreportcategory;
               }
            } finally {
               IOUtils.closeQuietly(iresource1);
            }

            return var8;
         }
      }

      try {
         texture.generateMipmaps(this.mipmapLevels);
         return true;
      } catch (Throwable var14) {
         Throwable throwable1 = var14;
         Er crashreport1 = Er.makeCrashReport(throwable1, "Applying mipmap");
         Ey crashreportcategory1 = crashreport1.makeCategory("Sprite being mipmapped");
         crashreportcategory1.addDetail("Sprite name", new Ez<String>() {
            public String call() throws Exception {
               return texture.getIconName();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         crashreportcategory1.addDetail("Sprite size", new Ez<String>() {
            public String call() throws Exception {
               return texture.getIconWidth() + " x " + texture.getIconHeight();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         crashreportcategory1.addDetail("Sprite frames", new Ez<String>() {
            public String call() throws Exception {
               return texture.getFrameCount() + " frames";
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         crashreportcategory1.addCrashSection("Mipmap levels", this.mipmapLevels);
         throw new ReportedException(crashreport1);
      }
   }

   public ResourceLocation getResourceLocation(zd p_184396_1_) {
      ResourceLocation resourcelocation1 = new ResourceLocation(p_184396_1_.getIconName());
      return this.completeResourceLocation(resourcelocation1);
   }

   public ResourceLocation completeResourceLocation(ResourceLocation p_completeResourceLocation_1_) {
      return this.isAbsoluteLocation(p_completeResourceLocation_1_) ? new ResourceLocation(p_completeResourceLocation_1_.getNamespace(), p_completeResourceLocation_1_.getPath() + ".png") : new ResourceLocation(p_completeResourceLocation_1_.getNamespace(), String.format("%s/%s%s", this.basePath, p_completeResourceLocation_1_.getPath(), ".png"));
   }

   public zd getAtlasSprite(String iconName) {
      zd textureatlassprite6 = (zd)this.mapUploadedSprites.get(iconName);
      if (textureatlassprite6 == null) {
         textureatlassprite6 = this.missingImage;
      }

      return textureatlassprite6;
   }

   public void updateAnimations() {
      boolean flag3 = false;
      boolean flag4 = false;
      zk.bindTexture(this.getGlTextureId());
      int i4 = 0;
      Iterator var4 = this.listAnimatedSprites.iterator();

      zd textureatlassprite10;
      while(var4.hasNext()) {
         textureatlassprite10 = (zd)var4.next();
         if (this.isTerrainAnimationActive(textureatlassprite10)) {
            textureatlassprite10.updateAnimation();
            if (textureatlassprite10.isAnimationActive()) {
               ++i4;
            }

            if (textureatlassprite10.spriteNormal != null) {
               flag3 = true;
            }

            if (textureatlassprite10.spriteSpecular != null) {
               flag4 = true;
            }
         }
      }

      if (XH.isMultiTexture()) {
         var4 = this.listAnimatedSprites.iterator();

         label134:
         while(true) {
            zd textureatlassprite7;
            do {
               do {
                  if (!var4.hasNext()) {
                     zk.bindTexture(this.getGlTextureId());
                     break label134;
                  }

                  textureatlassprite10 = (zd)var4.next();
               } while(!this.isTerrainAnimationActive(textureatlassprite10));

               textureatlassprite7 = textureatlassprite10.spriteSingle;
            } while(textureatlassprite7 == null);

            if (textureatlassprite10 == bqS.iconClock || textureatlassprite10 == bqS.iconCompass) {
               textureatlassprite7.frameCounter = textureatlassprite10.frameCounter;
            }

            textureatlassprite10.bindSpriteTexture();
            textureatlassprite7.updateAnimation();
            if (textureatlassprite7.isAnimationActive()) {
               ++i4;
            }
         }
      }

      if (XH.isShaders()) {
         if (flag3) {
            zk.bindTexture(this.getMultiTexID().norm);
            var4 = this.listAnimatedSprites.iterator();

            label113:
            while(true) {
               do {
                  do {
                     if (!var4.hasNext()) {
                        break label113;
                     }

                     textureatlassprite10 = (zd)var4.next();
                  } while(textureatlassprite10.spriteNormal == null);
               } while(!this.isTerrainAnimationActive(textureatlassprite10));

               if (textureatlassprite10 == bqS.iconClock || textureatlassprite10 == bqS.iconCompass) {
                  textureatlassprite10.spriteNormal.frameCounter = textureatlassprite10.frameCounter;
               }

               textureatlassprite10.spriteNormal.updateAnimation();
               if (textureatlassprite10.spriteNormal.isAnimationActive()) {
                  ++i4;
               }
            }
         }

         if (flag4) {
            zk.bindTexture(this.getMultiTexID().spec);
            var4 = this.listAnimatedSprites.iterator();

            label92:
            while(true) {
               do {
                  do {
                     if (!var4.hasNext()) {
                        break label92;
                     }

                     textureatlassprite10 = (zd)var4.next();
                  } while(textureatlassprite10.spriteSpecular == null);
               } while(!this.isTerrainAnimationActive(textureatlassprite10));

               if (textureatlassprite10 == bqS.iconClock || textureatlassprite10 == bqS.iconCompass) {
                  textureatlassprite10.spriteNormal.frameCounter = textureatlassprite10.frameCounter;
               }

               textureatlassprite10.spriteSpecular.updateAnimation();
               if (textureatlassprite10.spriteSpecular.isAnimationActive()) {
                  ++i4;
               }
            }
         }

         if (flag3 || flag4) {
            zk.bindTexture(this.getGlTextureId());
         }
      }

      int j4 = XH.getMinecraft().entityRenderer.frameCount;
      if (j4 != this.frameCountAnimations) {
         this.countAnimationsActive = i4;
         this.frameCountAnimations = j4;
      }

      if (bpW.isActive()) {
         bpW.resetSpritesRendered();
      }

   }

   public zd registerSprite(ResourceLocation location) {
      if (location == null) {
         throw new IllegalArgumentException("Location cannot be null!");
      } else {
         zd textureatlassprite6 = (zd)this.mapRegisteredSprites.get(location.toString());
         if (textureatlassprite6 == null) {
            textureatlassprite6 = zd.makeAtlasSprite(location);
            this.mapRegisteredSprites.put(location.toString(), textureatlassprite6);
            textureatlassprite6.updateIndexInMap(this.counterIndexInMap);
            if (XH.isEmissiveTextures()) {
               this.checkEmissive(location, textureatlassprite6);
            }
         }

         return textureatlassprite6;
      }
   }

   public void tick() {
      this.updateAnimations();
   }

   public void setMipmapLevels(int mipmapLevelsIn) {
      this.mipmapLevels = mipmapLevelsIn;
   }

   public zd getMissingSprite() {
      return this.missingImage;
   }

   @Nullable
   public zd getTextureExtry(String p_getTextureExtry_1_) {
      return (zd)this.mapRegisteredSprites.get(p_getTextureExtry_1_);
   }

   public boolean setTextureEntry(zd p_setTextureEntry_1_) {
      String s1 = p_setTextureEntry_1_.getIconName();
      if (!this.mapRegisteredSprites.containsKey(s1)) {
         this.mapRegisteredSprites.put(s1, p_setTextureEntry_1_);
         p_setTextureEntry_1_.updateIndexInMap(this.counterIndexInMap);
         return true;
      } else {
         return false;
      }
   }

   public String getBasePath() {
      return this.basePath;
   }

   public int getMipmapLevels() {
      return this.mipmapLevels;
   }

   private boolean isAbsoluteLocation(ResourceLocation p_isAbsoluteLocation_1_) {
      String s1 = p_isAbsoluteLocation_1_.getPath();
      return this.isAbsoluteLocationPath(s1);
   }

   private boolean isAbsoluteLocationPath(String p_isAbsoluteLocationPath_1_) {
      String s1 = p_isAbsoluteLocationPath_1_.toLowerCase();
      return s1.startsWith("mcpatcher/") || s1.startsWith("optifine/");
   }

   public zd getSpriteSafe(String p_getSpriteSafe_1_) {
      ResourceLocation resourcelocation1 = new ResourceLocation(p_getSpriteSafe_1_);
      return (zd)this.mapRegisteredSprites.get(resourcelocation1.toString());
   }

   public zd getRegisteredSprite(ResourceLocation p_getRegisteredSprite_1_) {
      return (zd)this.mapRegisteredSprites.get(p_getRegisteredSprite_1_.toString());
   }

   private boolean isTerrainAnimationActive(zd p_isTerrainAnimationActive_1_) {
      if (p_isTerrainAnimationActive_1_ != bqS.iconWaterStill && p_isTerrainAnimationActive_1_ != bqS.iconWaterFlow) {
         if (p_isTerrainAnimationActive_1_ != bqS.iconLavaStill && p_isTerrainAnimationActive_1_ != bqS.iconLavaFlow) {
            if (p_isTerrainAnimationActive_1_ != bqS.iconFireLayer0 && p_isTerrainAnimationActive_1_ != bqS.iconFireLayer1) {
               if (p_isTerrainAnimationActive_1_ == bqS.iconPortal) {
                  return XH.isAnimatedPortal();
               } else {
                  return p_isTerrainAnimationActive_1_ != bqS.iconClock && p_isTerrainAnimationActive_1_ != bqS.iconCompass ? XH.isAnimatedTerrain() : true;
               }
            } else {
               return XH.isAnimatedFire();
            }
         } else {
            return XH.isAnimatedLava();
         }
      } else {
         return XH.isAnimatedWater();
      }
   }

   public int getCountRegisteredSprites() {
      return this.counterIndexInMap.getValue();
   }

   private int detectMaxMipmapLevel(Map p_detectMaxMipmapLevel_1_, AA p_detectMaxMipmapLevel_2_) {
      int i4 = this.detectMinimumSpriteSize(p_detectMaxMipmapLevel_1_, p_detectMaxMipmapLevel_2_, 20);
      if (i4 < 16) {
         i4 = 16;
      }

      i4 = MathHelper.smallestEncompassingPowerOfTwo(i4);
      if (i4 > 16) {
         XH.log("Sprite size: " + i4);
      }

      int j4 = MathHelper.log2(i4);
      if (j4 < 4) {
         j4 = 4;
      }

      return j4;
   }

   private int detectMinimumSpriteSize(Map p_detectMinimumSpriteSize_1_, AA p_detectMinimumSpriteSize_2_, int p_detectMinimumSpriteSize_3_) {
      Map map1 = new HashMap();
      Iterator var5 = p_detectMinimumSpriteSize_1_.entrySet().iterator();

      int k3;
      while(var5.hasNext()) {
         Object entry = var5.next();
         zd textureatlassprite6 = (zd)((Map.Entry)entry).getValue();
         ResourceLocation resourcelocation1 = new ResourceLocation(textureatlassprite6.getIconName());
         ResourceLocation resourcelocation2 = this.completeResourceLocation(resourcelocation1);
         if (!textureatlassprite6.hasCustomLoader(p_detectMinimumSpriteSize_2_, resourcelocation1)) {
            try {
               Az iresource1 = p_detectMinimumSpriteSize_2_.getResource(resourcelocation2);
               if (iresource1 != null) {
                  InputStream inputstream = iresource1.getInputStream();
                  if (inputstream != null) {
                     Dimension dimension = bqS.getImageSize(inputstream, "png");
                     if (dimension != null) {
                        k3 = dimension.width;
                        int l3 = MathHelper.smallestEncompassingPowerOfTwo(k3);
                        if (!map1.containsKey(l3)) {
                           map1.put(l3, 1);
                        } else {
                           int i4 = (Integer)map1.get(l3);
                           map1.put(l3, i4 + 1);
                        }
                     }
                  }
               }
            } catch (Exception var16) {
            }
         }
      }

      int l4 = 0;
      Set set = map1.keySet();
      Set set1 = new TreeSet(set);

      int l5;
      int k5;
      for(Iterator iterator = set1.iterator(); iterator.hasNext(); l4 += l5) {
         k5 = (Integer)iterator.next();
         l5 = (Integer)map1.get(k5);
      }

      int i5 = 16;
      k5 = 0;
      l5 = l4 * p_detectMinimumSpriteSize_3_ / 100;
      Iterator iterator1 = set1.iterator();

      do {
         if (!iterator1.hasNext()) {
            return i5;
         }

         int i6 = (Integer)iterator1.next();
         k3 = (Integer)map1.get(i6);
         k5 += k3;
         if (i6 > i5) {
            i5 = i6;
         }
      } while(k5 <= l5);

      return i5;
   }

   private int getMinSpriteSize() {
      int i4 = 1 << this.mipmapLevels;
      if (i4 < 8) {
         i4 = 8;
      }

      return i4;
   }

   private int[] getMissingImageData(int p_getMissingImageData_1_) {
      BufferedImage bufferedimage = new BufferedImage(16, 16, 2);
      bufferedimage.setRGB(0, 0, 16, 16, zk.MISSING_TEXTURE_DATA, 0, 16);
      BufferedImage bufferedimage1 = bqS.scaleImage(bufferedimage, p_getMissingImageData_1_);
      int[] aint = new int[p_getMissingImageData_1_ * p_getMissingImageData_1_];
      bufferedimage1.getRGB(0, 0, p_getMissingImageData_1_, p_getMissingImageData_1_, aint, 0, p_getMissingImageData_1_);
      return aint;
   }

   public boolean isTextureBound() {
      int i4 = yh.getBoundTexture();
      int j4 = this.getGlTextureId();
      return i4 == j4;
   }

   private void updateIconGrid(int p_updateIconGrid_1_, int p_updateIconGrid_2_) {
      this.iconGridCountX = -1;
      this.iconGridCountY = -1;
      this.iconGrid = null;
      if (this.iconGridSize > 0) {
         this.iconGridCountX = p_updateIconGrid_1_ / this.iconGridSize;
         this.iconGridCountY = p_updateIconGrid_2_ / this.iconGridSize;
         this.iconGrid = new zd[this.iconGridCountX * this.iconGridCountY];
         this.iconGridSizeU = 1.0 / (double)this.iconGridCountX;
         this.iconGridSizeV = 1.0 / (double)this.iconGridCountY;
         Iterator var3 = this.mapUploadedSprites.values().iterator();

         while(var3.hasNext()) {
            zd textureatlassprite6 = (zd)var3.next();
            double d0 = 0.5 / (double)p_updateIconGrid_1_;
            double d1 = 0.5 / (double)p_updateIconGrid_2_;
            double d2 = (double)Math.min(textureatlassprite6.getMinU(), textureatlassprite6.getMaxU()) + d0;
            double d3 = (double)Math.min(textureatlassprite6.getMinV(), textureatlassprite6.getMaxV()) + d1;
            double d4 = (double)Math.max(textureatlassprite6.getMinU(), textureatlassprite6.getMaxU()) - d0;
            double d5 = (double)Math.max(textureatlassprite6.getMinV(), textureatlassprite6.getMaxV()) - d1;
            int i4 = (int)(d2 / this.iconGridSizeU);
            int j4 = (int)(d3 / this.iconGridSizeV);
            int k4 = (int)(d4 / this.iconGridSizeU);
            int l4 = (int)(d5 / this.iconGridSizeV);

            for(int i5 = i4; i5 <= k4; ++i5) {
               if (i5 >= 0 && i5 < this.iconGridCountX) {
                  for(int j5 = j4; j5 <= l4; ++j5) {
                     if (j5 >= 0 && j5 < this.iconGridCountX) {
                        int k5 = j5 * this.iconGridCountX + i5;
                        this.iconGrid[k5] = textureatlassprite6;
                     } else {
                        XH.warn("Invalid grid V: " + j5 + ", icon: " + textureatlassprite6.getIconName());
                     }
                  }
               } else {
                  XH.warn("Invalid grid U: " + i5 + ", icon: " + textureatlassprite6.getIconName());
               }
            }
         }
      }

   }

   public zd getIconByUV(double p_getIconByUV_1_, double p_getIconByUV_3_) {
      if (this.iconGrid == null) {
         return null;
      } else {
         int i4 = (int)(p_getIconByUV_1_ / this.iconGridSizeU);
         int j4 = (int)(p_getIconByUV_3_ / this.iconGridSizeV);
         int k4 = j4 * this.iconGridCountX + i4;
         return k4 >= 0 && k4 <= this.iconGrid.length ? this.iconGrid[k4] : null;
      }
   }

   private void checkEmissive(ResourceLocation p_checkEmissive_1_, zd p_checkEmissive_2_) {
      String s1 = bjR.getSuffixEmissive();
      if (s1 != null && !p_checkEmissive_1_.getPath().endsWith(s1)) {
         ResourceLocation resourcelocation1 = new ResourceLocation(p_checkEmissive_1_.getNamespace(), p_checkEmissive_1_.getPath() + s1);
         ResourceLocation resourcelocation2 = this.completeResourceLocation(resourcelocation1);
         if (XH.hasResource(resourcelocation2)) {
            zd textureatlassprite6 = this.registerSprite(resourcelocation1);
            textureatlassprite6.isEmissive = true;
            p_checkEmissive_2_.spriteEmissive = textureatlassprite6;
         }
      }

   }

   public int getCountAnimations() {
      return this.listAnimatedSprites.size();
   }

   public int getCountAnimationsActive() {
      return this.countAnimationsActive;
   }
}
