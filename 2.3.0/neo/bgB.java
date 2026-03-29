package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgB extends bgu<bgC> {
   protected bgB() {
      super(new ResourceLocation("random_chance_with_looting"), bgC.class);
   }

   public void serialize(JsonObject json, bgC value, JsonSerializationContext context) {
      json.addProperty("chance", bgC.access$000(value));
      json.addProperty("looting_multiplier", bgC.access$100(value));
   }

   public bgC deserialize(JsonObject json, JsonDeserializationContext context) {
      return new bgC(JsonUtils.getFloat(json, "chance"), JsonUtils.getFloat(json, "looting_multiplier"));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgv deserialize(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserialize(var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgv var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgC)var2, var3);
   }
}
