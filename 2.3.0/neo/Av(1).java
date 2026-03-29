package neo;

import java.io.IOException;
import net.minecraft.util.ResourceLocation;

public class Av implements AB {
   private static final ResourceLocation LOC_FOLIAGE_PNG = new ResourceLocation("textures/colormap/foliage.png");

   public Av() {
   }

   public void onResourceManagerReload(AA resourceManager) {
      try {
         baJ.setFoliageBiomeColorizer(zk.readImageData(resourceManager, LOC_FOLIAGE_PNG));
      } catch (IOException var3) {
      }

   }
}
