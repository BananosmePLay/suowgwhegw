package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.ReportedException;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class zf implements yS, AB {
   private static final Logger LOGGER = LogManager.getLogger();
   public static final ResourceLocation RESOURCE_LOCATION_EMPTY = new ResourceLocation("");
   private final Map<ResourceLocation, yR> mapTextureObjects = Maps.newHashMap();
   private final List<yS> listTickables = Lists.newArrayList();
   private final Map<String, Integer> mapTextureCounters = Maps.newHashMap();
   private final AA resourceManager;
   private yR boundTexture;
   private ResourceLocation boundTextureLocation;

   public zf(AA resourceManager) {
      this.resourceManager = resourceManager;
   }

   public void bindTexture(ResourceLocation resource) {
      if (XH.isRandomEntities()) {
         resource = bnx.getTextureLocation(resource);
      }

      if (XH.isCustomGuis()) {
         resource = bjD.getTextureLocation(resource);
      }

      yR itextureobject = (yR)this.mapTextureObjects.get(resource);
      if (bjR.isActive()) {
         itextureobject = bjR.getEmissiveTexture((yR)itextureobject, this.mapTextureObjects);
      }

      if (itextureobject == null) {
         itextureobject = new yY(resource);
         this.loadTexture(resource, (yR)itextureobject);
      }

      if (XH.isShaders()) {
         bps.bindTexture((yR)itextureobject);
      } else {
         zk.bindTexture(((yR)itextureobject).getGlTextureId());
      }

      this.boundTexture = (yR)itextureobject;
      this.boundTextureLocation = resource;
   }

   public boolean loadTickableTexture(ResourceLocation textureLocation, yT textureObj) {
      if (this.loadTexture(textureLocation, textureObj)) {
         this.listTickables.add(textureObj);
         return true;
      } else {
         return false;
      }
   }

   public boolean loadTexture(ResourceLocation textureLocation, yR textureObj) {
      boolean flag = true;

      try {
         ((yR)textureObj).loadTexture(this.resourceManager);
      } catch (IOException var8) {
         IOException ioexception = var8;
         if (textureLocation != RESOURCE_LOCATION_EMPTY) {
            LOGGER.warn("Failed to load texture: {}", textureLocation, ioexception);
         }

         textureObj = zk.MISSING_TEXTURE;
         this.mapTextureObjects.put(textureLocation, textureObj);
         flag = false;
      } catch (Throwable var9) {
         Throwable throwable = var9;
         final yR textureObjf = textureObj;
         Er crashreport = Er.makeCrashReport(throwable, "Registering texture");
         Ey crashreportcategory = crashreport.makeCategory("Resource location being registered");
         crashreportcategory.addCrashSection("Resource location", textureLocation);
         crashreportcategory.addDetail("Texture object class", new Ez<String>() {
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

   public yR getTexture(ResourceLocation textureLocation) {
      return (yR)this.mapTextureObjects.get(textureLocation);
   }

   public ResourceLocation getDynamicTextureLocation(String name, yP texture) {
      if (name.equals("logo")) {
         texture = XH.getMojangLogoTexture(texture);
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
         yS itickable = (yS)var1.next();
         itickable.tick();
      }

   }

   public void deleteTexture(ResourceLocation textureLocation) {
      yR itextureobject = this.getTexture(textureLocation);
      if (itextureobject != null) {
         this.mapTextureObjects.remove(textureLocation);
         zk.deleteTexture(itextureobject.getGlTextureId());
      }

   }

   public void onResourceManagerReload(AA resourceManager) {
      XH.dbg("*** Reloading textures ***");
      XH.log("Resource packs: " + XH.getResourcePackNames());
      Iterator iterator = this.mapTextureObjects.keySet().iterator();

      while(true) {
         ResourceLocation resourcelocation;
         String s;
         do {
            if (!iterator.hasNext()) {
               bjR.update();
               Set<Map.Entry<ResourceLocation, yR>> set = new HashSet(this.mapTextureObjects.entrySet());
               Iterator<Map.Entry<ResourceLocation, yR>> iterator1 = set.iterator();

               while(iterator1.hasNext()) {
                  Map.Entry<ResourceLocation, yR> entry = (Map.Entry)iterator1.next();
                  yR itextureobject1 = (yR)entry.getValue();
                  if (itextureobject1 == zk.MISSING_TEXTURE) {
                     iterator1.remove();
                  } else {
                     this.loadTexture((ResourceLocation)entry.getKey(), itextureobject1);
                  }
               }

               return;
            }

            resourcelocation = (ResourceLocation)iterator.next();
            s = resourcelocation.getPath();
         } while(!s.startsWith("mcpatcher/") && !s.startsWith("optifine/") && !bjR.isEmissive(resourcelocation));

         yR itextureobject = (yR)this.mapTextureObjects.get(resourcelocation);
         if (itextureobject instanceof yO) {
            yO abstracttexture = (yO)itextureobject;
            abstracttexture.deleteGlTexture();
         }

         iterator.remove();
      }
   }

   public void reloadBannerTextures() {
      Iterator var1 = (new HashSet(this.mapTextureObjects.entrySet())).iterator();

      while(var1.hasNext()) {
         Map.Entry<ResourceLocation, yR> entry = (Map.Entry)var1.next();
         ResourceLocation resourcelocation = (ResourceLocation)entry.getKey();
         yR itextureobject = (yR)entry.getValue();
         if (itextureobject instanceof yU) {
            this.loadTexture(resourcelocation, itextureobject);
         }
      }

   }

   public yR getBoundTexture() {
      return this.boundTexture;
   }

   public ResourceLocation getBoundTextureLocation() {
      return this.boundTextureLocation;
   }
}
