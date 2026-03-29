package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgV extends bgH<bgW> {
   protected bgV() {
      super(new ResourceLocation("set_data"), bgW.class);
   }

   public void serialize(JsonObject object, bgW functionClazz, JsonSerializationContext serializationContext) {
      object.add("data", serializationContext.serialize(bgW.access$000(functionClazz)));
   }

   public bgW deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      return new bgW(conditionsIn, (bhC)JsonUtils.deserializeClass(object, "data", deserializationContext, bhC.class));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgW)var2, var3);
   }
}
