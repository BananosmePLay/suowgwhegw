package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.ResourceLocation;

public class bgZ extends bgH<bha> {
   protected bgZ() {
      super(new ResourceLocation("furnace_smelt"), bha.class);
   }

   public void serialize(JsonObject object, bha functionClazz, JsonSerializationContext serializationContext) {
   }

   public bha deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      return new bha(conditionsIn);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bha)var2, var3);
   }
}
