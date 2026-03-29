package neo;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public interface AC {
   InputStream getInputStream(ResourceLocation var1) throws IOException;

   boolean resourceExists(ResourceLocation var1);

   Set<String> getResourceDomains();

   @Nullable
   <T extends Ad> T getPackMetadata(Aj var1, String var2) throws IOException;

   BufferedImage getPackImage() throws IOException;

   String getPackName();
}
