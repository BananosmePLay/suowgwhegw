package neo;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public interface AA {
   Set<String> getResourceDomains();

   Az getResource(ResourceLocation var1) throws IOException;

   List<Az> getAllResources(ResourceLocation var1) throws IOException;
}
