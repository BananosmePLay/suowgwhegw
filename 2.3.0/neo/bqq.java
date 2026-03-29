package neo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import net.minecraft.util.ResourceLocation;

public class bqq {
   public bqq() {
   }

   public static Properties readFontProperties(ResourceLocation locationFontTexture) {
      String s = locationFontTexture.getPath();
      Properties properties = new bqL();
      String s1 = ".png";
      if (!s.endsWith(s1)) {
         return properties;
      } else {
         String s2 = s.substring(0, s.length() - s1.length()) + ".properties";

         try {
            ResourceLocation resourcelocation = new ResourceLocation(locationFontTexture.getNamespace(), s2);
            InputStream inputstream = XH.getResourceStream(XH.getResourceManager(), resourcelocation);
            if (inputstream == null) {
               return properties;
            }

            XH.log("Loading " + s2);
            ((Properties)properties).load(inputstream);
            inputstream.close();
         } catch (FileNotFoundException var7) {
         } catch (IOException var8) {
            IOException ioexception = var8;
            ioexception.printStackTrace();
         }

         return properties;
      }
   }

   public static void readCustomCharWidths(Properties props, float[] charWidth) {
      Iterator var2 = props.keySet().iterator();

      while(var2.hasNext()) {
         Object a = var2.next();
         String s = (String)a;
         String s1 = "width.";
         if (s.startsWith(s1)) {
            String s2 = s.substring(s1.length());
            int i = XH.parseInt(s2, -1);
            if (i >= 0 && i < charWidth.length) {
               String s3 = props.getProperty(s);
               float f = XH.parseFloat(s3, -1.0F);
               if (f >= 0.0F) {
                  charWidth[i] = f;
               }
            }
         }
      }

   }

   public static float readFloat(Properties props, String key, float defOffset) {
      String s = props.getProperty(key);
      if (s == null) {
         return defOffset;
      } else {
         float f = XH.parseFloat(s, Float.MIN_VALUE);
         if (f == Float.MIN_VALUE) {
            XH.warn("Invalid value for " + key + ": " + s);
            return defOffset;
         } else {
            return f;
         }
      }
   }

   public static boolean readBoolean(Properties props, String key, boolean defVal) {
      String s = props.getProperty(key);
      if (s == null) {
         return defVal;
      } else {
         String s1 = s.toLowerCase().trim();
         if (!s1.equals("true") && !s1.equals("on")) {
            if (!s1.equals("false") && !s1.equals("off")) {
               XH.warn("Invalid value for " + key + ": " + s);
               return defVal;
            } else {
               return false;
            }
         } else {
            return true;
         }
      }
   }

   public static ResourceLocation getHdFontLocation(ResourceLocation fontLoc) {
      if (!XH.isCustomFonts()) {
         return fontLoc;
      } else if (fontLoc == null) {
         return fontLoc;
      } else if (!XH.isMinecraftThread()) {
         return fontLoc;
      } else {
         String s = fontLoc.getPath();
         String s1 = "textures/";
         String s2 = "mcpatcher/";
         if (!s.startsWith(s1)) {
            return fontLoc;
         } else {
            s = s.substring(s1.length());
            s = s2 + s;
            ResourceLocation resourcelocation = new ResourceLocation(fontLoc.getNamespace(), s);
            return XH.hasResource(XH.getResourceManager(), resourcelocation) ? resourcelocation : fontLoc;
         }
      }
   }
}
