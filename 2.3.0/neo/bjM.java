package neo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class bjM {
   private static bjN[][] worldSkyLayers = (bjN[][])((bjN[][])null);

   public bjM() {
   }

   public static void reset() {
      worldSkyLayers = (bjN[][])((bjN[][])null);
   }

   public static void update() {
      reset();
      if (XH.isCustomSky()) {
         worldSkyLayers = readCustomSkies();
      }

   }

   private static bjN[][] readCustomSkies() {
      bjN[][] acustomskylayer = new bjN[10][0];
      String s = "mcpatcher/sky/world";
      int i = -1;

      int j;
      for(j = 0; j < acustomskylayer.length; ++j) {
         String s1 = s + j + "/sky";
         List list = new ArrayList();

         for(int k = 1; k < 1000; ++k) {
            String s2 = s1 + k + ".properties";

            try {
               ResourceLocation resourcelocation = new ResourceLocation(s2);
               InputStream inputstream = XH.getResourceStream(resourcelocation);
               if (inputstream == null) {
                  break;
               }

               Properties properties = new bqL();
               ((Properties)properties).load(inputstream);
               inputstream.close();
               XH.dbg("CustomSky properties: " + s2);
               String s3 = s1 + k + ".png";
               bjN customskylayer = new bjN(properties, s3);
               if (customskylayer.isValid(s2)) {
                  ResourceLocation resourcelocation1 = new ResourceLocation(customskylayer.source);
                  yR itextureobject = bqS.getTexture(resourcelocation1);
                  if (itextureobject == null) {
                     XH.log("CustomSky: Texture not found: " + resourcelocation1);
                  } else {
                     customskylayer.textureId = itextureobject.getGlTextureId();
                     list.add(customskylayer);
                     inputstream.close();
                  }
               }
            } catch (FileNotFoundException var15) {
               break;
            } catch (IOException var16) {
               IOException ioexception = var16;
               ioexception.printStackTrace();
            }
         }

         if (list.size() > 0) {
            bjN[] acustomskylayer2 = (bjN[])((bjN[])list.toArray(new bjN[list.size()]));
            acustomskylayer[j] = acustomskylayer2;
            i = j;
         }
      }

      if (i < 0) {
         return (bjN[][])((bjN[][])null);
      } else {
         j = i + 1;
         bjN[][] acustomskylayer1 = new bjN[j][0];

         for(int i1 = 0; i1 < acustomskylayer1.length; ++i1) {
            acustomskylayer1[i1] = acustomskylayer[i1];
         }

         return acustomskylayer1;
      }
   }

   public static void renderSky(bij world, zf re, float partialTicks) {
      if (worldSkyLayers != null) {
         int i = world.provider.getDimensionType().getId();
         if (i >= 0 && i < worldSkyLayers.length) {
            bjN[] acustomskylayer = worldSkyLayers[i];
            if (acustomskylayer != null) {
               long j = world.getWorldTime();
               int k = (int)(j % 24000L);
               float f = world.getCelestialAngle(partialTicks);
               float f1 = world.getRainStrength(partialTicks);
               float f2 = world.getThunderStrength(partialTicks);
               if (f1 > 0.0F) {
                  f2 /= f1;
               }

               for(int l = 0; l < acustomskylayer.length; ++l) {
                  bjN customskylayer = acustomskylayer[l];
                  if (customskylayer.isActive(world, k)) {
                     customskylayer.render(world, k, f, f1, f2);
                  }
               }

               float f3 = 1.0F - f1;
               bnV.clearBlend(f3);
            }
         }
      }

   }

   public static boolean hasSkyLayers(bij world) {
      if (worldSkyLayers == null) {
         return false;
      } else {
         int i = world.provider.getDimensionType().getId();
         if (i >= 0 && i < worldSkyLayers.length) {
            bjN[] acustomskylayer = worldSkyLayers[i];
            if (acustomskylayer == null) {
               return false;
            } else {
               return acustomskylayer.length > 0;
            }
         } else {
            return false;
         }
      }
   }
}
