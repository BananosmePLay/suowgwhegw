package neo;

import java.io.IOException;
import net.minecraft.util.ResourceLocation;

public class Aw implements AB {
   private static final ResourceLocation LOC_GRASS_PNG = new ResourceLocation("textures/colormap/grass.png");

   public Aw() {
   }

   public void onResourceManagerReload(AA resourceManager) {
      try {
         baK.setGrassBiomeColorizer(zk.readImageData(resourceManager, LOC_GRASS_PNG));
      } catch (IOException var3) {
      }

   }
}
