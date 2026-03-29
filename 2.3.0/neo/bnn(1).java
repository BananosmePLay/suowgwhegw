package neo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.util.regex.Pattern;
import net.minecraft.util.ResourceLocation;

public class bnn {
   private static final Pattern PATTERN_USERNAME = Pattern.compile("[a-zA-Z0-9_]+");

   public bnn() {
   }

   public static void downloadCape(jf player) {
      String s = player.getNameClear();
      if (0e.get(s) == null) {
         if (s != null && !s.isEmpty() && !s.contains("\u0000") && PATTERN_USERNAME.matcher(s).matches()) {
            String s1 = "http://s.optifine.net/capes/" + s + ".png";
            ResourceLocation resourcelocation = new ResourceLocation("capeof/" + s);
            zf texturemanager = nC.getMinecraft().getTextureManager();
            yR itextureobject = texturemanager.getTexture(resourcelocation);
            if (itextureobject != null && itextureobject instanceof zm) {
               zm threaddownloadimagedata = (zm)itextureobject;
               if (threaddownloadimagedata.imageFound != null) {
                  if (threaddownloadimagedata.imageFound) {
                     player.setLocationOfCape(resourcelocation);
                     if (threaddownloadimagedata.getImageBuffer() instanceof bnm) {
                        bnm capeimagebuffer1 = (bnm)threaddownloadimagedata.getImageBuffer();
                        player.setElytraOfCape(capeimagebuffer1.isElytraOfCape());
                     }
                  }

                  return;
               }
            }

            bnm capeimagebuffer = new bnm(player, resourcelocation);
            zm threaddownloadimagedata1 = new zm((File)null, s1, (ResourceLocation)null, capeimagebuffer);
            threaddownloadimagedata1.pipeline = true;
            texturemanager.loadTexture(resourcelocation, threaddownloadimagedata1);
         }

      }
   }

   public static BufferedImage parseCape(BufferedImage img) {
      int i = 64;
      int j = 32;
      int k = img.getWidth();

      for(int l = img.getHeight(); i < k || j < l; j *= 2) {
         i *= 2;
      }

      BufferedImage bufferedimage = new BufferedImage(i, j, 2);
      Graphics graphics = bufferedimage.getGraphics();
      graphics.drawImage(img, 0, 0, (ImageObserver)null);
      graphics.dispose();
      return bufferedimage;
   }

   public static boolean isElytraCape(BufferedImage imageRaw, BufferedImage imageFixed) {
      return imageRaw.getWidth() > imageFixed.getHeight();
   }

   public static void reloadCape(jf player) {
      String s = player.getNameClear();
      ResourceLocation resourcelocation = new ResourceLocation("capeof/" + s);
      zf texturemanager = XH.getTextureManager();
      yR itextureobject = texturemanager.getTexture(resourcelocation);
      if (itextureobject instanceof yY) {
         yY simpletexture = (yY)itextureobject;
         simpletexture.deleteGlTexture();
         texturemanager.deleteTexture(resourcelocation);
      }

      player.setLocationOfCape((ResourceLocation)null);
      player.setElytraOfCape(false);
      downloadCape(player);
   }
}
