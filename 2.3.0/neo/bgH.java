package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.ResourceLocation;

public abstract class bgH<T extends bgI> {
   private final ResourceLocation lootTableLocation;
   private final Class<T> functionClass;

   protected bgH(ResourceLocation location, Class<T> clazz) {
      this.lootTableLocation = location;
      this.functionClass = clazz;
   }

   public ResourceLocation getFunctionName() {
      return this.lootTableLocation;
   }

   public Class<T> getFunctionClass() {
      return this.functionClass;
   }

   public abstract void serialize(JsonObject var1, T var2, JsonSerializationContext var3);

   public abstract T deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3);
}
