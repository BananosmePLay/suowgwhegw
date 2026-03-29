package neo;

public class boH implements boZ {
   private int textureUnit = -1;
   private String path = null;
   private yR texture = null;

   public boH(int textureUnit, String path, yR texture) {
      this.textureUnit = textureUnit;
      this.path = path;
      this.texture = texture;
   }

   public int getTextureUnit() {
      return this.textureUnit;
   }

   public String getPath() {
      return this.path;
   }

   public yR getTexture() {
      return this.texture;
   }

   public int getTextureId() {
      return this.texture.getGlTextureId();
   }

   public void deleteTexture() {
      zk.deleteTexture(this.texture.getGlTextureId());
   }

   public String toString() {
      return "textureUnit: " + this.textureUnit + ", path: " + this.path + ", glTextureId: " + this.getTextureId();
   }
}
