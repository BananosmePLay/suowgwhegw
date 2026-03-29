package net.minecraft.client.renderer.texture;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.crash.ICrashReportDetail;
import net.minecraft.src.Config;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import net.optifine.CustomGuis;
import net.optifine.EmissiveTextures;
import net.optifine.RandomEntities;
import net.optifine.shaders.ShadersTex;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TextureManager implements ITickable, IResourceManagerReloadListener {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final ResourceLocation RESOURCE_LOCATION_EMPTY = new ResourceLocation("");
   private final Map<ResourceLocation, ITextureObject> mapTextureObjects = Maps.newHashMap();
   private final List<ITickable> listTickables = Lists.newArrayList();
   private final Map<String, Integer> mapTextureCounters = Maps.newHashMap();
   private final IResourceManager resourceManager;
   private ITextureObject boundTexture;
   private ResourceLocation boundTextureLocation;

   public TextureManager(IResourceManager resourceManager) {
      this.resourceManager = resourceManager;
   }

   public void bindTexture(ResourceLocation resource) {
      if (Config.isRandomEntities()) {
         resource = RandomEntities.getTextureLocation(resource);
      }

      if (Config.isCustomGuis()) {
         resource = CustomGuis.getTextureLocation(resource);
      }

      ITextureObject itextureobject = (ITextureObject)this.mapTextureObjects.get(resource);
      if (EmissiveTextures.isActive()) {
         itextureobject = EmissiveTextures.getEmissiveTexture((ITextureObject)itextureobject, this.mapTextureObjects);
      }

      if (itextureobject == null) {
         itextureobject = new SimpleTexture(resource);
         this.loadTexture(resource, (ITextureObject)itextureobject);
      }

      if (Config.isShaders()) {
         ShadersTex.bindTexture((ITextureObject)itextureobject);
      } else {
         TextureUtil.bindTexture(((ITextureObject)itextureobject).getGlTextureId());
      }

      this.boundTexture = (ITextureObject)itextureobject;
      this.boundTextureLocation = resource;
   }

   public boolean loadTickableTexture(ResourceLocation textureLocation, ITickableTextureObject textureObj) {
      if (this.loadTexture(textureLocation, textureObj)) {
         this.listTickables.add(textureObj);
         return true;
      } else {
         return false;
      }
   }

   public boolean loadTexture(ResourceLocation textureLocation, ITextureObject textureObj) {
      boolean flag = true;

      try {
         ((ITextureObject)textureObj).loadTexture(this.resourceManager);
      } catch (IOException var8) {
         IOException ioexception = var8;
         if (textureLocation != RESOURCE_LOCATION_EMPTY) {
            LOGGER.warn("Failed to load texture: {}", textureLocation, ioexception);
         }

         textureObj = TextureUtil.MISSING_TEXTURE;
         this.mapTextureObjects.put(textureLocation, textureObj);
         flag = false;
      } catch (Throwable var9) {
         Throwable throwable = var9;
         final ITextureObject textureObjf = textureObj;
         CrashReport crashreport = CrashReport.makeCrashReport(throwable, "Registering texture");
         CrashReportCategory crashreportcategory = crashreport.makeCategory("Resource location being registered");
         crashreportcategory.addCrashSection("Resource location", textureLocation);
         crashreportcategory.addDetail("Texture object class", new ICrashReportDetail<String>() {
            public String call() throws Exception {
               return textureObjf.getClass().getName();
            }

            // $FF: synthetic method
            // $FF: bridge method
            public Object call() throws Exception {
               return this.call();
            }
         });
         throw new ReportedException(crashreport);
      }

      this.mapTextureObjects.put(textureLocation, textureObj);
      return flag;
   }

   public ITextureObject getTexture(ResourceLocation textureLocation) {
      return (ITextureObject)this.mapTextureObjects.get(textureLocation);
   }

   public ResourceLocation getDynamicTextureLocation(String name, DynamicTexture texture) {
      if (name.equals("logo")) {
         texture = Config.getMojangLogoTexture(texture);
      }

      Integer integer = (Integer)this.mapTextureCounters.get(name);
      if (integer == null) {
         integer = 1;
      } else {
         integer = integer + 1;
      }

      this.mapTextureCounters.put(name, integer);
      ResourceLocation resourcelocation = new ResourceLocation(String.format("dynamic/%s_%d", name, integer));
      this.loadTexture(resourcelocation, texture);
      return resourcelocation;
   }

   public void tick() {
      Iterator var1 = this.listTickables.iterator();

      while(var1.hasNext()) {
         ITickable itickable = (ITickable)var1.next();
         itickable.tick();
      }

   }

   public void deleteTexture(ResourceLocation textureLocation) {
      ITextureObject itextureobject = this.getTexture(textureLocation);
      if (itextureobject != null) {
         this.mapTextureObjects.remove(textureLocation);
         TextureUtil.deleteTexture(itextureobject.getGlTextureId());
      }

   }

   public void onResourceManagerReload(IResourceManager resourceManager) {
      Config.dbg("*** Reloading textures ***");
      Config.log("Resource packs: " + Config.getResourcePackNames());
      Iterator iterator = this.mapTextureObjects.keySet().iterator();

      while(true) {
         ResourceLocation resourcelocation;
         String s;
         do {
            if (!iterator.hasNext()) {
               EmissiveTextures.update();
               Set<Map.Entry<ResourceLocation, ITextureObject>> set = new HashSet(this.mapTextureObjects.entrySet());
               Iterator<Map.Entry<ResourceLocation, ITextureObject>> iterator1 = set.iterator();

               while(iterator1.hasNext()) {
                  Map.Entry<ResourceLocation, ITextureObject> entry = (Map.Entry)iterator1.next();
                  ITextureObject itextureobject1 = (ITextureObject)entry.getValue();
                  if (itextureobject1 == TextureUtil.MISSING_TEXTURE) {
                     iterator1.remove();
                  } else {
                     this.loadTexture((ResourceLocation)entry.getKey(), itextureobject1);
                  }
               }

               return;
            }

            resourcelocation = (ResourceLocation)iterator.next();
            s = resourcelocation.getPath();
         } while(!s.startsWith("mcpatcher/") && !s.startsWith("optifine/") && !EmissiveTextures.isEmissive(resourcelocation));

         ITextureObject itextureobject = (ITextureObject)this.mapTextureObjects.get(resourcelocation);
         if (itextureobject instanceof AbstractTexture) {
            AbstractTexture abstracttexture = (AbstractTexture)itextureobject;
            abstracttexture.deleteGlTexture();
         }

         iterator.remove();
      }
   }

   public void reloadBannerTextures() {
      Iterator var1 = (new HashSet(this.mapTextureObjects.entrySet())).iterator();

      while(var1.hasNext()) {
         Map.Entry<ResourceLocation, ITextureObject> entry = (Map.Entry)var1.next();
         ResourceLocation resourcelocation = (ResourceLocation)entry.getKey();
         ITextureObject itextureobject = (ITextureObject)entry.getValue();
         if (itextureobject instanceof LayeredColorMaskTexture) {
            this.loadTexture(resourcelocation, itextureobject);
         }
      }

   }

   public ITextureObject getBoundTexture() {
      return this.boundTexture;
   }

   public ResourceLocation getBoundTextureLocation() {
      return this.boundTextureLocation;
   }
}
