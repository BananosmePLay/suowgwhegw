package neo;

import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Iterator;
import java.util.Map;

public abstract class tr implements tm {
   protected Map<in, sD> mapStateModelLocations = Maps.newLinkedHashMap();

   public tr() {
   }

   public String getPropertyString(Map<hT<?>, Comparable<?>> values) {
      StringBuilder stringbuilder = new StringBuilder();
      Iterator var3 = values.entrySet().iterator();

      while(var3.hasNext()) {
         Map.Entry<hT<?>, Comparable<?>> entry = (Map.Entry)var3.next();
         if (stringbuilder.length() != 0) {
            stringbuilder.append(",");
         }

         hT<?> iproperty = (hT)entry.getKey();
         stringbuilder.append(iproperty.getName());
         stringbuilder.append("=");
         stringbuilder.append(this.getPropertyName(iproperty, (Comparable)entry.getValue()));
      }

      if (stringbuilder.length() == 0) {
         stringbuilder.append("normal");
      }

      return stringbuilder.toString();
   }

   private <T extends Comparable<T>> String getPropertyName(hT<T> property, Comparable<?> value) {
      return property.getName(value);
   }

   public Map<in, sD> putStateModelLocations(co blockIn) {
      UnmodifiableIterator unmodifiableiterator = blockIn.getBlockState().getValidStates().iterator();

      while(unmodifiableiterator.hasNext()) {
         in iblockstate = (in)unmodifiableiterator.next();
         this.mapStateModelLocations.put(iblockstate, this.getModelResourceLocation(iblockstate));
      }

      return this.mapStateModelLocations;
   }

   protected abstract sD getModelResourceLocation(in var1);
}
