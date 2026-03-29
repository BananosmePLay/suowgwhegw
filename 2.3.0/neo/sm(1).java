package neo;

import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class sm {
   private final ResourceLocation location;
   private final Map<ResourceLocation, Float> mapResourceValues;

   public sm(ResourceLocation locationIn, Map<ResourceLocation, Float> propertyValues) {
      this.location = locationIn;
      this.mapResourceValues = propertyValues;
   }

   public ResourceLocation getLocation() {
      return this.location;
   }

   boolean matchesItemStack(Qy stack, @Nullable bij worldIn, @Nullable Iw livingEntity) {
      OL item = stack.getItem();
      Iterator var5 = this.mapResourceValues.entrySet().iterator();

      Map.Entry entry;
      Oo iitempropertygetter;
      do {
         if (!var5.hasNext()) {
            return true;
         }

         entry = (Map.Entry)var5.next();
         iitempropertygetter = item.getPropertyGetter((ResourceLocation)entry.getKey());
      } while(iitempropertygetter != null && !(iitempropertygetter.apply(stack, worldIn, livingEntity) < (Float)entry.getValue()));

      return false;
   }
}
