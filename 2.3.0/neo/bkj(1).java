package neo;

import net.minecraft.util.ResourceLocation;

public class bkj {
   private String name = null;
   private String basePath = null;
   private ResourceLocation textureLocation = null;
   private bkl[] customModelRenderers = null;
   private float shadowSize = 0.0F;

   public bkj(String name, String basePath, ResourceLocation textureLocation, bkl[] customModelRenderers, float shadowSize) {
      this.name = name;
      this.basePath = basePath;
      this.textureLocation = textureLocation;
      this.customModelRenderers = customModelRenderers;
      this.shadowSize = shadowSize;
   }

   public String getName() {
      return this.name;
   }

   public String getBasePath() {
      return this.basePath;
   }

   public ResourceLocation getTextureLocation() {
      return this.textureLocation;
   }

   public bkl[] getCustomModelRenderers() {
      return this.customModelRenderers;
   }

   public float getShadowSize() {
      return this.shadowSize;
   }
}
