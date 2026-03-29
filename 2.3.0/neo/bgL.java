package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgL extends bgH<bgM> {
   protected bgL() {
      super(new ResourceLocation("looting_enchant"), bgM.class);
   }

   public void serialize(JsonObject object, bgM functionClazz, JsonSerializationContext serializationContext) {
      object.add("count", serializationContext.serialize(bgM.access$000(functionClazz)));
      if (bgM.access$100(functionClazz) > 0) {
         object.add("limit", serializationContext.serialize(bgM.access$100(functionClazz)));
      }

   }

   public bgM deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      int i = JsonUtils.getInt(object, "limit", 0);
      return new bgM(conditionsIn, (bhC)JsonUtils.deserializeClass(object, "count", deserializationContext, bhC.class), i);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgM)var2, var3);
   }
}
