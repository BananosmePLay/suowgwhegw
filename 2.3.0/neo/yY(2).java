package neo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class yY extends yO {
   private static final Logger LOGGER = LogManager.getLogger();
   protected final ResourceLocation textureLocation;
   public ResourceLocation locationEmissive;
   public boolean isEmissive;

   public yY(ResourceLocation textureResourceLocation) {
      this.textureLocation = textureResourceLocation;
   }

   public void loadTexture(AA resourceManager) throws IOException {
      this.deleteGlTexture();
      Az iresource = null;

      try {
         iresource = resourceManager.getResource(this.textureLocation);
         BufferedImage bufferedimage = zk.readBufferedImage(iresource.getInputStream());
         boolean flag = false;
         boolean flag1 = false;
         if (iresource.hasMetadata()) {
            try {
               An texturemetadatasection = (An)iresource.getMetadata("texture");
               if (texturemetadatasection != null) {
                  flag = texturemetadatasection.getTextureBlur();
                  flag1 = texturemetadatasection.getTextureClamp();
               }
            } catch (RuntimeException var10) {
               RuntimeException runtimeexception1 = var10;
               LOGGER.warn("Failed reading metadata of: {}", this.textureLocation, runtimeexception1);
            }
         }

         if (XH.isShaders()) {
            bps.loadSimpleTexture(this.getGlTextureId(), bufferedimage, flag, flag1, resourceManager, this.textureLocation, this.getMultiTexID());
         } else {
            zk.uploadTextureImageAllocate(this.getGlTextureId(), bufferedimage, flag, flag1);
         }

         if (bjR.isActive()) {
            bjR.loadTexture(this.textureLocation, this);
         }
      } finally {
         IOUtils.closeQuietly(iresource);
      }

   }
}
