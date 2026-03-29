package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bhz {
   private static final Map<ResourceLocation, bhx<?>> NAME_TO_SERIALIZER_MAP = Maps.newHashMap();
   private static final Map<Class<? extends bhy>, bhx<?>> CLASS_TO_SERIALIZER_MAP = Maps.newHashMap();

   public bhz() {
   }

   public static <T extends bhy> void registerProperty(bhx<? extends T> serializer) {
      ResourceLocation resourcelocation = serializer.getName();
      Class<T> oclass = serializer.getPropertyClass();
      if (NAME_TO_SERIALIZER_MAP.containsKey(resourcelocation)) {
         throw new IllegalArgumentException("Can't re-register entity property name " + resourcelocation);
      } else if (CLASS_TO_SERIALIZER_MAP.containsKey(oclass)) {
         throw new IllegalArgumentException("Can't re-register entity property class " + oclass.getName());
      } else {
         NAME_TO_SERIALIZER_MAP.put(resourcelocation, serializer);
         CLASS_TO_SERIALIZER_MAP.put(oclass, serializer);
      }
   }

   public static bhx<?> getSerializerForName(ResourceLocation name) {
      bhx<?> serializer = (bhx)NAME_TO_SERIALIZER_MAP.get(name);
      if (serializer == null) {
         throw new IllegalArgumentException("Unknown loot entity property '" + name + "'");
      } else {
         return serializer;
      }
   }

   public static <T extends bhy> bhx<T> getSerializerFor(T property) {
      bhx<?> serializer = (bhx)CLASS_TO_SERIALIZER_MAP.get(property.getClass());
      if (serializer == null) {
         throw new IllegalArgumentException("Unknown loot entity property " + property);
      } else {
         return serializer;
      }
   }

   static {
      registerProperty(new bhv());
   }
}
