package neo;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class bnj {
   private static bni[] propertiesByIndex = new bni[0];

   public bnj() {
   }

   public static void update() {
      propertiesByIndex = new bni[0];
      if (XH.isNaturalTextures()) {
         String s = "optifine/natural.properties";

         try {
            ResourceLocation resourcelocation = new ResourceLocation(s);
            if (!XH.hasResource(resourcelocation)) {
               XH.dbg("NaturalTextures: configuration \"" + s + "\" not found");
               return;
            }

            boolean flag = XH.isFromDefaultResourcePack(resourcelocation);
            InputStream inputstream = XH.getResourceStream(resourcelocation);
            ArrayList arraylist = new ArrayList(256);
            String s1 = XH.readInputStream(inputstream);
            inputstream.close();
            String[] astring = XH.tokenize(s1, "\n\r");
            if (flag) {
               XH.dbg("Natural Textures: Parsing default configuration \"" + s + "\"");
               XH.dbg("Natural Textures: Valid only for textures from default resource pack");
            } else {
               XH.dbg("Natural Textures: Parsing configuration \"" + s + "\"");
            }

            zj texturemap = bqS.getTextureMapBlocks();

            for(int i = 0; i < astring.length; ++i) {
               String s2 = astring[i].trim();
               if (!s2.startsWith("#")) {
                  String[] astring1 = XH.tokenize(s2, "=");
                  if (astring1.length != 2) {
                     XH.warn("Natural Textures: Invalid \"" + s + "\" line: " + s2);
                  } else {
                     String s3 = astring1[0].trim();
                     String s4 = astring1[1].trim();
                     zd textureatlassprite = texturemap.getSpriteSafe("minecraft:blocks/" + s3);
                     if (textureatlassprite == null) {
                        XH.warn("Natural Textures: Texture not found: \"" + s + "\" line: " + s2);
                     } else {
                        int j = textureatlassprite.getIndexInMap();
                        if (j < 0) {
                           XH.warn("Natural Textures: Invalid \"" + s + "\" line: " + s2);
                        } else {
                           if (flag && !XH.isFromDefaultResourcePack(new ResourceLocation("textures/blocks/" + s3 + ".png"))) {
                              return;
                           }

                           bni naturalproperties = new bni(s4);
                           if (naturalproperties.isValid()) {
                              while(arraylist.size() <= j) {
                                 arraylist.add((Object)null);
                              }

                              arraylist.set(j, naturalproperties);
                              XH.dbg("NaturalTextures: " + s3 + " = " + s4);
                           }
                        }
                     }
                  }
               }
            }

            propertiesByIndex = (bni[])((bni[])arraylist.toArray(new bni[arraylist.size()]));
         } catch (FileNotFoundException var16) {
            XH.warn("NaturalTextures: configuration \"" + s + "\" not found");
            return;
         } catch (Exception var17) {
            Exception exception = var17;
            exception.printStackTrace();
         }
      }

   }

   public static rK getNaturalTexture(BlockPos blockPosIn, rK quad) {
      zd textureatlassprite = quad.getSprite();
      if (textureatlassprite == null) {
         return quad;
      } else {
         bni naturalproperties = getNaturalProperties(textureatlassprite);
         if (naturalproperties == null) {
            return quad;
         } else {
            int i = bjj.getSide(quad.getFace());
            int j = XH.getRandom(blockPosIn, i);
            int k = 0;
            boolean flag = false;
            if (naturalproperties.rotation > 1) {
               k = j & 3;
            }

            if (naturalproperties.rotation == 2) {
               k = k / 2 * 2;
            }

            if (naturalproperties.flip) {
               flag = (j & 4) != 0;
            }

            return naturalproperties.getQuad(quad, k, flag);
         }
      }
   }

   public static bni getNaturalProperties(zd icon) {
      if (!(icon instanceof zd)) {
         return null;
      } else {
         int i = icon.getIndexInMap();
         if (i >= 0 && i < propertiesByIndex.length) {
            bni naturalproperties = propertiesByIndex[i];
            return naturalproperties;
         } else {
            return null;
         }
      }
   }
}
