package neo;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import java.util.Collection;
import net.minecraft.util.EnumFacing;

public class hW extends hX<EnumFacing> {
   protected hW(String name, Collection<EnumFacing> values) {
      super(name, EnumFacing.class, values);
   }

   public static hW create(String name) {
      return create(name, Predicates.alwaysTrue());
   }

   public static hW create(String name, Predicate<EnumFacing> filter) {
      return create(name, Collections2.filter(Lists.newArrayList(EnumFacing.values()), filter));
   }

   public static hW create(String name, Collection<EnumFacing> values) {
      return new hW(name, values);
   }
}
