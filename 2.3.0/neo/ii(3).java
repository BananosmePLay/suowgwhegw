package neo;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.minecraft.util.MapPopulator;
import net.minecraft.util.math.Cartesian;

public class ii {
   private static final Pattern NAME_PATTERN = Pattern.compile("^[a-z0-9_]+$");
   private static final Function<hT<?>, String> GET_NAME_FUNC = new Function<hT<?>, String>() {
      @Nullable
      public String apply(@Nullable hT<?> p_apply_1_) {
         return p_apply_1_ == null ? "<NULL>" : p_apply_1_.getName();
      }

      // $FF: synthetic method
      // $FF: bridge method
      @Nullable
      public Object apply(@Nullable Object var1) {
         return this.apply((hT)var1);
      }
   };
   private final co block;
   private final ImmutableSortedMap<String, hT<?>> properties;
   private final ImmutableList<in> validStates;

   public ii(co blockIn, hT<?>... properties) {
      this(blockIn, properties, (ImmutableMap)null);
   }

   protected ih createState(co p_createState_1_, ImmutableMap<hT<?>, Comparable<?>> p_createState_2_, @Nullable ImmutableMap<biF<?>, Optional<?>> p_createState_3_) {
      return new ih(p_createState_1_, p_createState_2_);
   }

   protected ii(co p_i2_1_, hT<?>[] p_i2_2_, ImmutableMap<biF<?>, Optional<?>> p_i2_3_) {
      this.block = p_i2_1_;
      Map<String, hT<?>> map = Maps.newHashMap();
      hT[] var5 = p_i2_2_;
      int var6 = p_i2_2_.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         hT<?> iproperty = var5[var7];
         validateProperty(p_i2_1_, iproperty);
         map.put(iproperty.getName(), iproperty);
      }

      this.properties = ImmutableSortedMap.copyOf(map);
      Map<Map<hT<?>, Comparable<?>>, ih> map2 = Maps.newLinkedHashMap();
      List<ih> list = Lists.newArrayList();
      Iterator var13 = Cartesian.cartesianProduct(this.getAllowedValues()).iterator();

      while(var13.hasNext()) {
         List<Comparable<?>> list1 = (List)var13.next();
         Map<hT<?>, Comparable<?>> map1 = MapPopulator.createMap(this.properties.values(), list1);
         ih blockstatecontainer$stateimplementation = this.createState(p_i2_1_, ImmutableMap.copyOf(map1), p_i2_3_);
         map2.put(map1, blockstatecontainer$stateimplementation);
         list.add(blockstatecontainer$stateimplementation);
      }

      var13 = list.iterator();

      while(var13.hasNext()) {
         ih blockstatecontainer$stateimplementation1 = (ih)var13.next();
         blockstatecontainer$stateimplementation1.buildPropertyValueTable(map2);
      }

      this.validStates = ImmutableList.copyOf(list);
   }

   public static <T extends Comparable<T>> String validateProperty(co block, hT<T> property) {
      String s = property.getName();
      if (!NAME_PATTERN.matcher(s).matches()) {
         throw new IllegalArgumentException("Block: " + block.getClass() + " has invalidly named property: " + s);
      } else {
         Iterator var3 = property.getAllowedValues().iterator();

         String s1;
         do {
            if (!var3.hasNext()) {
               return s;
            }

            T t = (Comparable)var3.next();
            s1 = property.getName(t);
         } while(NAME_PATTERN.matcher(s1).matches());

         throw new IllegalArgumentException("Block: " + block.getClass() + " has property: " + s + " with invalidly named value: " + s1);
      }
   }

   public ImmutableList<in> getValidStates() {
      return this.validStates;
   }

   private List<Iterable<Comparable<?>>> getAllowedValues() {
      List<Iterable<Comparable<?>>> list = Lists.newArrayList();
      ImmutableCollection<hT<?>> immutablecollection = this.properties.values();
      UnmodifiableIterator unmodifiableiterator = immutablecollection.iterator();

      while(unmodifiableiterator.hasNext()) {
         hT<?> iproperty = (hT)unmodifiableiterator.next();
         list.add(iproperty.getAllowedValues());
      }

      return list;
   }

   public in getBaseState() {
      return (in)this.validStates.get(0);
   }

   public co getBlock() {
      return this.block;
   }

   public Collection<hT<?>> getProperties() {
      return this.properties.values();
   }

   public String toString() {
      return MoreObjects.toStringHelper(this).add("block", co.REGISTRY.getNameForObject(this.block)).add("properties", Iterables.transform(this.properties.values(), GET_NAME_FUNC)).toString();
   }

   @Nullable
   public hT<?> getProperty(String propertyName) {
      return (hT)this.properties.get(propertyName);
   }
}
