package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class bgK {
   private static final Map<ResourceLocation, bgH<?>> NAME_TO_SERIALIZER_MAP = Maps.newHashMap();
   private static final Map<Class<? extends bgI>, bgH<?>> CLASS_TO_SERIALIZER_MAP = Maps.newHashMap();

   public bgK() {
   }

   public static <T extends bgI> void registerFunction(bgH<? extends T> serializer) {
      ResourceLocation resourcelocation = serializer.getFunctionName();
      Class<T> oclass = serializer.getFunctionClass();
      if (NAME_TO_SERIALIZER_MAP.containsKey(resourcelocation)) {
         throw new IllegalArgumentException("Can't re-register item function name " + resourcelocation);
      } else if (CLASS_TO_SERIALIZER_MAP.containsKey(oclass)) {
         throw new IllegalArgumentException("Can't re-register item function class " + oclass.getName());
      } else {
         NAME_TO_SERIALIZER_MAP.put(resourcelocation, serializer);
         CLASS_TO_SERIALIZER_MAP.put(oclass, serializer);
      }
   }

   public static bgH<?> getSerializerForName(ResourceLocation location) {
      bgH<?> serializer = (bgH)NAME_TO_SERIALIZER_MAP.get(location);
      if (serializer == null) {
         throw new IllegalArgumentException("Unknown loot item function '" + location + "'");
      } else {
         return serializer;
      }
   }

   public static <T extends bgI> bgH<T> getSerializerFor(T functionClass) {
      bgH<T> serializer = (bgH)CLASS_TO_SERIALIZER_MAP.get(functionClass.getClass());
      if (serializer == null) {
         throw new IllegalArgumentException("Unknown loot item function " + functionClass);
      } else {
         return serializer;
      }
   }

   static {
      registerFunction(new bgR());
      registerFunction(new bgV());
      registerFunction(new bgF());
      registerFunction(new bgD());
      registerFunction(new bgX());
      registerFunction(new bgZ());
      registerFunction(new bgL());
      registerFunction(new bgT());
      registerFunction(new bgP());
   }
}
