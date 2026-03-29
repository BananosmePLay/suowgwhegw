package neo;

import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class tq extends tr {
   private final hT<?> name;
   private final String suffix;
   private final List<hT<?>> ignored;

   private tq(@Nullable hT<?> name, @Nullable String suffix, List<hT<?>> ignored) {
      this.name = name;
      this.suffix = suffix;
      this.ignored = ignored;
   }

   protected sD getModelResourceLocation(in state) {
      Map<hT<?>, Comparable<?>> map = Maps.newLinkedHashMap(state.getProperties());
      String s;
      if (this.name == null) {
         s = ((ResourceLocation)co.REGISTRY.getNameForObject(state.getBlock())).toString();
      } else {
         s = this.removeName(this.name, map);
      }

      if (this.suffix != null) {
         s = s + this.suffix;
      }

      Iterator var4 = this.ignored.iterator();

      while(var4.hasNext()) {
         hT<?> iproperty = (hT)var4.next();
         map.remove(iproperty);
      }

      return new sD(s, this.getPropertyString(map));
   }

   private <T extends Comparable<T>> String removeName(hT<T> property, Map<hT<?>, Comparable<?>> values) {
      return property.getName((Comparable)values.remove(this.name));
   }

   // $FF: synthetic method
   tq(hT x0, String x1, List x2, Object x3) {
      this(x0, x1, x2);
   }
}
