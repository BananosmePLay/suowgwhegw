package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgR extends bgH<bgS> {
   protected bgR() {
      super(new ResourceLocation("set_count"), bgS.class);
   }

   public void serialize(JsonObject object, bgS functionClazz, JsonSerializationContext serializationContext) {
      object.add("count", serializationContext.serialize(bgS.access$000(functionClazz)));
   }

   public bgS deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      return new bgS(conditionsIn, (bhC)JsonUtils.deserializeClass(object, "count", deserializationContext, bhC.class));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgS)var2, var3);
   }
}
