package neo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class yU extends yO {
   private static final Logger LOGGER = LogManager.getLogger();
   private final ResourceLocation textureLocation;
   private final List<String> listTextures;
   private final List<Om> listDyeColors;

   public yU(ResourceLocation textureLocationIn, List<String> p_i46101_2_, List<Om> p_i46101_3_) {
      this.textureLocation = textureLocationIn;
      this.listTextures = p_i46101_2_;
      this.listDyeColors = p_i46101_3_;
   }

   public void loadTexture(AA resourceManager) throws IOException {
      this.deleteGlTexture();
      Az iresource = null;

      BufferedImage bufferedimage;
      label267: {
         try {
            iresource = resourceManager.getResource(this.textureLocation);
            BufferedImage bufferedimage1 = zk.readBufferedImage(iresource.getInputStream());
            int i = bufferedimage1.getType();
            if (i == 0) {
               i = 6;
            }

            bufferedimage = new BufferedImage(bufferedimage1.getWidth(), bufferedimage1.getHeight(), i);
            Graphics graphics = bufferedimage.getGraphics();
            graphics.drawImage(bufferedimage1, 0, 0, (ImageObserver)null);
            int j = 0;

            while(true) {
               if (j >= 17 || j >= this.listTextures.size() || j >= this.listDyeColors.size()) {
                  break label267;
               }

               Az iresource1 = null;

               try {
                  String s = (String)this.listTextures.get(j);
                  int k = ((Om)this.listDyeColors.get(j)).getColorValue();
                  if (s != null) {
                     iresource1 = resourceManager.getResource(new ResourceLocation(s));
                     BufferedImage bufferedimage2 = bnK.MinecraftForgeClient_getImageLayer.exists() ? (BufferedImage)bnK.call(bnK.MinecraftForgeClient_getImageLayer, new ResourceLocation(s), resourceManager) : zk.readBufferedImage(iresource1.getInputStream());
                     if (bufferedimage2.getWidth() == bufferedimage.getWidth() && bufferedimage2.getHeight() == bufferedimage.getHeight() && bufferedimage2.getType() == 6) {
                        int l = 0;

                        while(true) {
                           if (l >= bufferedimage2.getHeight()) {
                              bufferedimage.getGraphics().drawImage(bufferedimage2, 0, 0, (ImageObserver)null);
                              break;
                           }

                           for(int i1 = 0; i1 < bufferedimage2.getWidth(); ++i1) {
                              int j1 = bufferedimage2.getRGB(i1, l);
                              if ((j1 & -16777216) != 0) {
                                 int k1 = (j1 & 16711680) << 8 & -16777216;
                                 int l1 = bufferedimage1.getRGB(i1, l);
                                 int i2 = MathHelper.multiplyColor(l1, k) & 16777215;
                                 bufferedimage2.setRGB(i1, l, k1 | i2);
                              }
                           }

                           ++l;
                        }
                     }
                  }
               } finally {
                  IOUtils.closeQuietly(iresource1);
               }

               ++j;
            }
         } catch (IOException var27) {
            IOException ioexception1 = var27;
            LOGGER.error("Couldn't load layered image", ioexception1);
         } finally {
            IOUtils.closeQuietly(iresource);
         }

         return;
      }

      if (XH.isShaders()) {
         bps.loadSimpleTexture(this.getGlTextureId(), bufferedimage, false, false, resourceManager, this.textureLocation, this.getMultiTexID());
      } else {
         zk.uploadTextureImage(this.getGlTextureId(), bufferedimage);
      }

   }
}
