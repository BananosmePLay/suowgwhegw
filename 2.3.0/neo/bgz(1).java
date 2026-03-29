package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgz extends bgu<bgA> {
   protected bgz() {
      super(new ResourceLocation("random_chance"), bgA.class);
   }

   public void serialize(JsonObject json, bgA value, JsonSerializationContext context) {
      json.addProperty("chance", bgA.access$000(value));
   }

   public bgA deserialize(JsonObject json, JsonDeserializationContext context) {
      return new bgA(JsonUtils.getFloat(json, "chance"));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgv deserialize(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserialize(var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgv var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgA)var2, var3);
   }
}
