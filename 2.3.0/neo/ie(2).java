package neo;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableTable;
import com.google.common.collect.Iterables;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public abstract class ie implements in {
   private static final Joiner COMMA_JOINER = Joiner.on(',');
   private static final Function<Map.Entry<hT<?>, Comparable<?>>, String> MAP_ENTRY_TO_STRING = new Function<Map.Entry<hT<?>, Comparable<?>>, String>() {
      @Nullable
      public String apply(@Nullable Map.Entry<hT<?>, Comparable<?>> p_apply_1_) {
         if (p_apply_1_ == null) {
            return "<NULL>";
         } else {
            hT<?> iproperty = (hT)p_apply_1_.getKey();
            return iproperty.getName() + "=" + this.getPropertyName(iproperty, (Comparable)p_apply_1_.getValue());
         }
      }

      private <T extends Comparable<T>> String getPropertyName(hT<T> property, Comparable<?> entry) {
         return property.getName(entry);
      }

      // $FF: synthetic method
      // $FF: bridge method
      @Nullable
      public Object apply(@Nullable Object var1) {
         return this.apply((Map.Entry)var1);
      }
   };
   private int blockId = -1;
   private int blockStateId = -1;
   private int metadata = -1;
   private ResourceLocation blockLocation = null;

   public ie() {
   }

   public int getBlockId() {
      if (this.blockId < 0) {
         this.blockId = co.getIdFromBlock(this.getBlock());
      }

      return this.blockId;
   }

   public int getBlockStateId() {
      if (this.blockStateId < 0) {
         this.blockStateId = co.getStateId(this);
      }

      return this.blockStateId;
   }

   public int getMetadata() {
      if (this.metadata < 0) {
         this.metadata = this.getBlock().getMetaFromState(this);
      }

      return this.metadata;
   }

   public ResourceLocation getBlockLocation() {
      if (this.blockLocation == null) {
         this.blockLocation = (ResourceLocation)co.REGISTRY.getNameForObject(this.getBlock());
      }

      return this.blockLocation;
   }

   public ImmutableTable<hT<?>, Comparable<?>, in> getPropertyValueTable() {
      return null;
   }

   public <T extends Comparable<T>> in cycleProperty(hT<T> property) {
      return this.withProperty(property, (Comparable)cyclePropertyValue(property.getAllowedValues(), this.getValue(property)));
   }

   protected static <T> T cyclePropertyValue(Collection<T> values, T currentValue) {
      Iterator<T> iterator = values.iterator();

      do {
         if (!iterator.hasNext()) {
            return iterator.next();
         }
      } while(!iterator.next().equals(currentValue));

      if (iterator.hasNext()) {
         return iterator.next();
      } else {
         return values.iterator().next();
      }
   }

   public String toString() {
      StringBuilder stringbuilder = new StringBuilder();
      stringbuilder.append(co.REGISTRY.getNameForObject(this.getBlock()));
      if (!this.getProperties().isEmpty()) {
         stringbuilder.append("[");
         COMMA_JOINER.appendTo(stringbuilder, Iterables.transform(this.getProperties().entrySet(), MAP_ENTRY_TO_STRING));
         stringbuilder.append("]");
      }

      return stringbuilder.toString();
   }
}
