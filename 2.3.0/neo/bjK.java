package neo;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import net.minecraft.util.ResourceLocation;

public class bjK {
   private static bjL customPanoramaProperties = null;
   private static final Random random = new Random();

   public bjK() {
   }

   public static bjL getCustomPanoramaProperties() {
      return customPanoramaProperties;
   }

   public static void update() {
      customPanoramaProperties = null;
      String[] astring = getPanoramaFolders();
      if (astring.length > 1) {
         Properties[] aproperties = getPanoramaProperties(astring);
         int[] aint = getWeights(aproperties);
         int i = getRandomIndex(aint);
         String s = astring[i];
         Properties properties = aproperties[i];
         if (properties == null) {
            properties = aproperties[0];
         }

         if (properties == null) {
            properties = new bqL();
         }

         bjL custompanoramaproperties = new bjL(s, (Properties)properties);
         customPanoramaProperties = custompanoramaproperties;
      }

   }

   private static String[] getPanoramaFolders() {
      List<String> list = new ArrayList();
      list.add("textures/gui/title/background");

      for(int i = 0; i < 100; ++i) {
         String s = "optifine/gui/background" + i;
         String s1 = s + "/panorama_0.png";
         ResourceLocation resourcelocation = new ResourceLocation(s1);
         if (XH.hasResource(resourcelocation)) {
            list.add(s);
         }
      }

      String[] astring = (String[])((String[])list.toArray(new String[list.size()]));
      return astring;
   }

   private static Properties[] getPanoramaProperties(String[] folders) {
      Properties[] aproperties = new Properties[folders.length];

      for(int i = 0; i < folders.length; ++i) {
         String s = folders[i];
         if (i == 0) {
            s = "optifine/gui";
         } else {
            XH.dbg("CustomPanorama: " + s);
         }

         ResourceLocation resourcelocation = new ResourceLocation(s + "/background.properties");

         try {
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            if (inputstream != null) {
               Properties properties = new bqL();
               ((Properties)properties).load(inputstream);
               XH.dbg("CustomPanorama: " + resourcelocation.getPath());
               aproperties[i] = properties;
               inputstream.close();
            }
         } catch (IOException var7) {
         }
      }

      return aproperties;
   }

   private static int[] getWeights(Properties[] properties) {
      int[] weights = new int[properties.length];

      for(int i2 = 0; i2 < weights.length; ++i2) {
         Properties prop = properties[i2];
         if (prop == null) {
            prop = properties[0];
         }

         if (prop == null) {
            weights[i2] = 1;
         } else {
            String str = prop.getProperty("weight", (String)null);
            weights[i2] = XH.parseInt(str, 1);
         }
      }

      return weights;
   }

   private static int getRandomIndex(int[] weights) {
      int i = bqD.getSum(weights);
      int j = random.nextInt(i);
      int k = 0;

      for(int l = 0; l < weights.length; ++l) {
         k += weights[l];
         if (k > j) {
            return l;
         }
      }

      return weights.length - 1;
   }
}
