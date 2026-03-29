package neo;

import com.google.common.primitives.Floats;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.ResourceLocation;

public class bmS {
   private bmT[] itemOverrideProperties;
   private Map<bqm, ResourceLocation> mapValueModels = new HashMap();
   public static final ResourceLocation LOCATION_NULL = new ResourceLocation("");

   public bmS(bmT[] itemOverrideProperties) {
      this.itemOverrideProperties = itemOverrideProperties;
   }

   public ResourceLocation getModelLocation(Qy stack, bij world, Iw entity) {
      bqm compoundkey = this.getValueKey(stack, world, entity);
      return compoundkey == null ? null : (ResourceLocation)this.mapValueModels.get(compoundkey);
   }

   public void putModelLocation(Qy stack, bij world, Iw entity, ResourceLocation location) {
      bqm compoundkey = this.getValueKey(stack, world, entity);
      if (compoundkey != null) {
         this.mapValueModels.put(compoundkey, location);
      }

   }

   private bqm getValueKey(Qy stack, bij world, Iw entity) {
      Integer[] ainteger = new Integer[this.itemOverrideProperties.length];

      for(int i = 0; i < ainteger.length; ++i) {
         Integer integer = this.itemOverrideProperties[i].getValueIndex(stack, world, entity);
         if (integer == null) {
            return null;
         }

         ainteger[i] = integer;
      }

      return new bqm(ainteger);
   }

   public static bmS make(List<sm> overrides) {
      if (overrides.isEmpty()) {
         return null;
      } else if (!bnK.ItemOverride_mapResourceValues.exists()) {
         return null;
      } else {
         Map<ResourceLocation, Set<Float>> map = new LinkedHashMap();
         Iterator var2 = overrides.iterator();

         while(var2.hasNext()) {
            sm itemoverride = (sm)var2.next();
            Map<ResourceLocation, Float> map1 = (Map)bnK.getFieldValue(itemoverride, bnK.ItemOverride_mapResourceValues);
            Iterator var5 = map1.keySet().iterator();

            while(var5.hasNext()) {
               ResourceLocation resourcelocation = (ResourceLocation)var5.next();
               Float f = (Float)map1.get(resourcelocation);
               if (f != null) {
                  Set<Float> set = (Set)map.get(resourcelocation);
                  if (set == null) {
                     set = new HashSet();
                     map.put(resourcelocation, set);
                  }

                  ((Set)set).add(f);
               }
            }
         }

         List<bmT> list = new ArrayList();
         Iterator var10 = map.keySet().iterator();

         while(var10.hasNext()) {
            ResourceLocation resourcelocation1 = (ResourceLocation)var10.next();
            Set<Float> set1 = (Set)map.get(resourcelocation1);
            float[] afloat = Floats.toArray(set1);
            bmT itemoverrideproperty = new bmT(resourcelocation1, afloat);
            list.add(itemoverrideproperty);
         }

         bmT[] aitemoverrideproperty = (bmT[])((bmT[])list.toArray(new bmT[list.size()]));
         bmS itemoverridecache = new bmS(aitemoverrideproperty);
         logCache(aitemoverrideproperty, overrides);
         return itemoverridecache;
      }
   }

   private static void logCache(bmT[] props, List<sm> overrides) {
      StringBuffer stringbuffer = new StringBuffer();

      for(int i = 0; i < props.length; ++i) {
         bmT itemoverrideproperty = props[i];
         if (stringbuffer.length() > 0) {
            stringbuffer.append(", ");
         }

         stringbuffer.append("" + itemoverrideproperty.getLocation() + "=" + itemoverrideproperty.getValues().length);
      }

      if (overrides.size() > 0) {
         stringbuffer.append(" -> " + ((sm)overrides.get(0)).getLocation() + " ...");
      }

      XH.dbg("ItemOverrideCache: " + stringbuffer.toString());
   }

   public String toString() {
      return "properties: " + this.itemOverrideProperties.length + ", models: " + this.mapValueModels.size();
   }
}
