package neo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class bjR {
   private static String suffixEmissive = null;
   private static String suffixEmissivePng = null;
   private static boolean active = false;
   private static boolean render = false;
   private static boolean hasEmissive = false;
   private static boolean renderEmissive = false;
   private static float lightMapX;
   private static float lightMapY;
   private static final String SUFFIX_PNG = ".png";
   private static final ResourceLocation LOCATION_EMPTY = new ResourceLocation("mcpatcher/ctm/default/empty.png");

   public bjR() {
   }

   public static boolean isActive() {
      return active;
   }

   public static String getSuffixEmissive() {
      return suffixEmissive;
   }

   public static void beginRender() {
      render = true;
      hasEmissive = false;
   }

   public static yR getEmissiveTexture(yR texture, Map<ResourceLocation, yR> mapTextures) {
      if (!render) {
         return texture;
      } else if (!(texture instanceof yY)) {
         return texture;
      } else {
         yY simpletexture = (yY)texture;
         ResourceLocation resourcelocation = simpletexture.locationEmissive;
         if (!renderEmissive) {
            if (resourcelocation != null) {
               hasEmissive = true;
            }

            return texture;
         } else {
            if (resourcelocation == null) {
               resourcelocation = LOCATION_EMPTY;
            }

            yR itextureobject = (yR)mapTextures.get(resourcelocation);
            if (itextureobject == null) {
               itextureobject = new yY(resourcelocation);
               zf texturemanager = XH.getTextureManager();
               texturemanager.loadTexture(resourcelocation, (yR)itextureobject);
            }

            return (yR)itextureobject;
         }
      }
   }

   public static boolean hasEmissive() {
      return hasEmissive;
   }

   public static void beginRenderEmissive() {
      lightMapX = ys.lastBrightnessX;
      lightMapY = ys.lastBrightnessY;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, 240.0F, lightMapY);
      renderEmissive = true;
   }

   public static void endRenderEmissive() {
      renderEmissive = false;
      ys.setLightmapTextureCoords(ys.lightmapTexUnit, lightMapX, lightMapY);
   }

   public static void endRender() {
      render = false;
      hasEmissive = false;
   }

   public static void update() {
      active = false;
      suffixEmissive = null;
      suffixEmissivePng = null;
      if (XH.isEmissiveTextures()) {
         try {
            String s = "optifine/emissive.properties";
            ResourceLocation resourcelocation = new ResourceLocation(s);
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            if (inputstream == null) {
               return;
            }

            dbg("Loading " + s);
            Properties properties = new bqL();
            ((Properties)properties).load(inputstream);
            inputstream.close();
            suffixEmissive = ((Properties)properties).getProperty("suffix.emissive");
            if (suffixEmissive != null) {
               suffixEmissivePng = suffixEmissive + ".png";
            }

            active = suffixEmissive != null;
         } catch (FileNotFoundException var4) {
            return;
         } catch (IOException var5) {
            IOException ioexception = var5;
            ioexception.printStackTrace();
         }
      }

   }

   private static void dbg(String str) {
      XH.dbg("EmissiveTextures: " + str);
   }

   private static void warn(String str) {
      XH.warn("EmissiveTextures: " + str);
   }

   public static boolean isEmissive(ResourceLocation loc) {
      return suffixEmissivePng == null ? false : loc.getPath().endsWith(suffixEmissivePng);
   }

   public static void loadTexture(ResourceLocation loc, yY tex) {
      if (loc != null && tex != null) {
         tex.isEmissive = false;
         tex.locationEmissive = null;
         if (suffixEmissivePng != null) {
            String s = loc.getPath();
            if (s.endsWith(".png")) {
               if (s.endsWith(suffixEmissivePng)) {
                  tex.isEmissive = true;
               } else {
                  String s1 = s.substring(0, s.length() - ".png".length()) + suffixEmissivePng;
                  ResourceLocation resourcelocation = new ResourceLocation(loc.getNamespace(), s1);
                  if (XH.hasResource(resourcelocation)) {
                     tex.locationEmissive = resourcelocation;
                  }
               }
            }
         }
      }

   }
}
