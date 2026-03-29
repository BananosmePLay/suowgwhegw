package neo;

import java.io.Closeable;
import java.io.InputStream;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public interface Az extends Closeable {
   ResourceLocation getResourceLocation();

   InputStream getInputStream();

   boolean hasMetadata();

   @Nullable
   <T extends Ad> T getMetadata(String var1);

   String getResourcePackName();
}
