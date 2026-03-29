package neo;

import net.minecraft.util.ResourceLocation;

public class boI implements boZ {
   private int textureUnit = -1;
   private ResourceLocation location;
   private int variant = 0;
   private yR texture;
   public static final int VARIANT_BASE = 0;
   public static final int VARIANT_NORMAL = 1;
   public static final int VARIANT_SPECULAR = 2;

   public boI(int textureUnit, ResourceLocation location, int variant) {
      this.textureUnit = textureUnit;
      this.location = location;
      this.variant = variant;
   }

   public yR getTexture() {
      if (this.texture == null) {
         zf texturemanager = nC.getMinecraft().getTextureManager();
         this.texture = texturemanager.getTexture(this.location);
         if (this.texture == null) {
            this.texture = new yY(this.location);
            texturemanager.loadTexture(this.location, this.texture);
            this.texture = texturemanager.getTexture(this.location);
         }
      }

      return this.texture;
   }

   public int getTextureId() {
      yR itextureobject = this.getTexture();
      if (this.variant != 0 && itextureobject instanceof yO) {
         yO abstracttexture = (yO)itextureobject;
         bpf multitexid = abstracttexture.multiTex;
         if (multitexid != null) {
            if (this.variant == 1) {
               return multitexid.norm;
            }

            if (this.variant == 2) {
               return multitexid.spec;
            }
         }
      }

      return itextureobject.getGlTextureId();
   }

   public int getTextureUnit() {
      return this.textureUnit;
   }

   public void deleteTexture() {
   }

   public String toString() {
      return "textureUnit: " + this.textureUnit + ", location: " + this.location + ", glTextureId: " + (this.texture != null ? this.texture.getGlTextureId() : "");
   }
}
