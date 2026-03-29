package neo;

public class An implements Ad {
   private final boolean textureBlur;
   private final boolean textureClamp;

   public An(boolean textureBlurIn, boolean textureClampIn) {
      this.textureBlur = textureBlurIn;
      this.textureClamp = textureClampIn;
   }

   public boolean getTextureBlur() {
      return this.textureBlur;
   }

   public boolean getTextureClamp() {
      return this.textureClamp;
   }
}
