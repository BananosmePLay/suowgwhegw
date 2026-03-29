package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.RegistrySimple;

public class jd extends RegistrySimple<ResourceLocation, iQ> {
   private Map<ResourceLocation, iQ> soundRegistry;

   public jd() {
   }

   protected Map<ResourceLocation, iQ> createUnderlyingMap() {
      this.soundRegistry = Maps.newHashMap();
      return this.soundRegistry;
   }

   public void add(iQ accessor) {
      this.putObject(accessor.getLocation(), accessor);
   }

   public void clearMap() {
      this.soundRegistry.clear();
   }
}
