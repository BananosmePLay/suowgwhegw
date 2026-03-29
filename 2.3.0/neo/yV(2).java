package neo;

import com.google.common.collect.Lists;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class yV extends yO {
   private static final Logger LOGGER = LogManager.getLogger();
   public final List<String> layeredTextureNames;
   private ResourceLocation textureLocation;

   public yV(String... textureNames) {
      this.layeredTextureNames = Lists.newArrayList(textureNames);
      if (textureNames.length > 0 && textureNames[0] != null) {
         this.textureLocation = new ResourceLocation(textureNames[0]);
      }

   }

   public void loadTexture(AA resourceManager) throws IOException {
      this.deleteGlTexture();
      BufferedImage bufferedimage = null;
      Iterator var3 = this.layeredTextureNames.iterator();

      while(var3.hasNext()) {
         String s = (String)var3.next();
         Az iresource = null;

         try {
            if (s != null) {
               iresource = resourceManager.getResource(new ResourceLocation(s));
               BufferedImage bufferedimage1 = zk.readBufferedImage(iresource.getInputStream());
               if (bufferedimage == null) {
                  bufferedimage = new BufferedImage(bufferedimage1.getWidth(), bufferedimage1.getHeight(), 2);
               }

               bufferedimage.getGraphics().drawImage(bufferedimage1, 0, 0, (ImageObserver)null);
            }
            continue;
         } catch (IOException var10) {
            IOException ioexception1 = var10;
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
