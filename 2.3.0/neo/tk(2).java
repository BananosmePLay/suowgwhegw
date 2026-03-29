package neo;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public class tk {
   private final Map<co, tm> blockStateMap = Maps.newIdentityHashMap();
   private final Set<co> setBuiltInBlocks = Sets.newIdentityHashSet();

   public tk() {
   }

   public void registerBlockStateMapper(co blockIn, tm stateMapper) {
      this.blockStateMap.put(blockIn, stateMapper);
   }

   public void registerBuiltInBlocks(co... blockIn) {
      Collections.addAll(this.setBuiltInBlocks, blockIn);
   }

   public Map<in, sD> putAllStateModelLocations() {
      Map<in, sD> map = Maps.newIdentityHashMap();
      Iterator var2 = co.REGISTRY.iterator();

      while(var2.hasNext()) {
         co block = (co)var2.next();
         map.putAll(this.getVariants(block));
      }

      return map;
   }

   public Set<ResourceLocation> getBlockstateLocations(co blockIn) {
      if (this.setBuiltInBlocks.contains(blockIn)) {
         return Collections.emptySet();
      } else {
         tm istatemapper = (tm)this.blockStateMap.get(blockIn);
         if (istatemapper == null) {
            return Collections.singleton(co.REGISTRY.getNameForObject(blockIn));
         } else {
            Set<ResourceLocation> set = Sets.newHashSet();
            Iterator var4 = istatemapper.putStateModelLocations(blockIn).values().iterator();

            while(var4.hasNext()) {
               sD modelresourcelocation = (sD)var4.next();
               set.add(new ResourceLocation(modelresourcelocation.getNamespace(), modelresourcelocation.getPath()));
            }

            return set;
         }
      }
   }

   public Map<in, sD> getVariants(co blockIn) {
      return this.setBuiltInBlocks.contains(blockIn) ? Collections.emptyMap() : ((tm)MoreObjects.firstNonNull(this.blockStateMap.get(blockIn), new tl())).putStateModelLocations(blockIn);
   }
}
