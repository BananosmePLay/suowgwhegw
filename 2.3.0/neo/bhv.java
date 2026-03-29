package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bhv extends bhx<bhw> {
   protected bhv() {
      super(new ResourceLocation("on_fire"), bhw.class);
   }

   public JsonElement serialize(bhw property, JsonSerializationContext serializationContext) {
      return new JsonPrimitive(bhw.access$000(property));
   }

   public bhw deserialize(JsonElement element, JsonDeserializationContext deserializationContext) {
      return new bhw(JsonUtils.getBoolean(element, "on_fire"));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bhy deserialize(JsonElement var1, JsonDeserializationContext var2) {
      return this.deserialize(var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public JsonElement serialize(bhy var1, JsonSerializationContext var2) {
      return this.serialize((bhw)var1, var2);
   }
}
