package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;

public class bgx {
   private static final Map<ResourceLocation, bgu<?>> NAME_TO_SERIALIZER_MAP = Maps.newHashMap();
   private static final Map<Class<? extends bgv>, bgu<?>> CLASS_TO_SERIALIZER_MAP = Maps.newHashMap();

   public bgx() {
   }

   public static <T extends bgv> void registerCondition(bgu<? extends T> condition) {
      ResourceLocation resourcelocation = condition.getLootTableLocation();
      Class<T> oclass = condition.getConditionClass();
      if (NAME_TO_SERIALIZER_MAP.containsKey(resourcelocation)) {
         throw new IllegalArgumentException("Can't re-register item condition name " + resourcelocation);
      } else if (CLASS_TO_SERIALIZER_MAP.containsKey(oclass)) {
         throw new IllegalArgumentException("Can't re-register item condition class " + oclass.getName());
      } else {
         NAME_TO_SERIALIZER_MAP.put(resourcelocation, condition);
         CLASS_TO_SERIALIZER_MAP.put(oclass, condition);
      }
   }

   public static boolean testAllConditions(@Nullable bgv[] conditions, Random rand, bhg context) {
      if (conditions == null) {
         return true;
      } else {
         bgv[] var3 = conditions;
         int var4 = conditions.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            bgv lootcondition = var3[var5];
            if (!lootcondition.testCondition(rand, context)) {
               return false;
            }
         }

         return true;
      }
   }

   public static bgu<?> getSerializerForName(ResourceLocation location) {
      bgu<?> serializer = (bgu)NAME_TO_SERIALIZER_MAP.get(location);
      if (serializer == null) {
         throw new IllegalArgumentException("Unknown loot item condition '" + location + "'");
      } else {
         return serializer;
      }
   }

   public static <T extends bgv> bgu<T> getSerializerFor(T conditionClass) {
      bgu<T> serializer = (bgu)CLASS_TO_SERIALIZER_MAP.get(conditionClass.getClass());
      if (serializer == null) {
         throw new IllegalArgumentException("Unknown loot item condition " + conditionClass);
      } else {
         return serializer;
      }
   }

   static {
      registerCondition(new bgz());
      registerCondition(new bgB());
      registerCondition(new bgo());
      registerCondition(new bgs());
      registerCondition(new bgq());
   }
}
