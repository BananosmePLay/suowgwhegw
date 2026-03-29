package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.ResourceLocation;

public abstract class bgu<T extends bgv> {
   private final ResourceLocation lootTableLocation;
   private final Class<T> conditionClass;

   protected bgu(ResourceLocation location, Class<T> clazz) {
      this.lootTableLocation = location;
      this.conditionClass = clazz;
   }

   public ResourceLocation getLootTableLocation() {
      return this.lootTableLocation;
   }

   public Class<T> getConditionClass() {
      return this.conditionClass;
   }

   public abstract void serialize(JsonObject var1, T var2, JsonSerializationContext var3);

   public abstract T deserialize(JsonObject var1, JsonDeserializationContext var2);
}
