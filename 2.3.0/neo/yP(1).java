package neo;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class yP extends yO {
   private final int[] dynamicTextureData;
   private final int width;
   private final int height;
   private boolean shadersInitialized;

   public yP(BufferedImage bufferedImage) {
      this(bufferedImage.getWidth(), bufferedImage.getHeight());
      bufferedImage.getRGB(0, 0, bufferedImage.getWidth(), bufferedImage.getHeight(), this.dynamicTextureData, 0, bufferedImage.getWidth());
      this.updateDynamicTexture();
   }

   public yP(int textureWidth, int textureHeight) {
      this.shadersInitialized = false;
      this.width = textureWidth;
      this.height = textureHeight;
      this.dynamicTextureData = new int[textureWidth * textureHeight * 3];
      if (XH.isShaders()) {
         bps.initDynamicTexture(this.getGlTextureId(), textureWidth, textureHeight, this);
         this.shadersInitialized = true;
      } else {
         zk.allocateTexture(this.getGlTextureId(), textureWidth, textureHeight);
      }

   }

   public void loadTexture(AA resourceManager) throws IOException {
   }

   public void updateDynamicTexture() {
      if (XH.isShaders()) {
         if (!this.shadersInitialized) {
            bps.initDynamicTexture(this.getGlTextureId(), this.width, this.height, this);
            this.shadersInitialized = true;
         }

         bps.updateDynamicTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height, this);
      } else {
         zk.uploadTexture(this.getGlTextureId(), this.dynamicTextureData, this.width, this.height);
      }

   }

   public int[] getTextureData() {
      return this.dynamicTextureData;
   }
}
