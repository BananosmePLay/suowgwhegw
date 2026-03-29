package neo;

import java.io.File;
import net.minecraft.util.ResourceLocation;

public class AK extends AJ {
   private final File baseDir;

   public AK(File folder) {
      this.baseDir = folder;
   }

   public File getFile(ResourceLocation location) {
      return new File(this.baseDir, location.toString().replace(':', '/'));
   }

   public File getPackMcmeta() {
      return new File(this.baseDir, "pack.mcmeta");
   }
}
