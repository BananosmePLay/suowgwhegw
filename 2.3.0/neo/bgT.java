package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgT extends bgH<bgU> {
   protected bgT() {
      super(new ResourceLocation("set_damage"), bgU.class);
   }

   public void serialize(JsonObject object, bgU functionClazz, JsonSerializationContext serializationContext) {
      object.add("damage", serializationContext.serialize(bgU.access$000(functionClazz)));
   }

   public bgU deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      return new bgU(conditionsIn, (bhC)JsonUtils.deserializeClass(object, "damage", deserializationContext, bhC.class));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgU)var2, var3);
   }
}
