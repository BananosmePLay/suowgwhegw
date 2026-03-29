package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgF extends bgH<bgG> {
   public bgF() {
      super(new ResourceLocation("enchant_with_levels"), bgG.class);
   }

   public void serialize(JsonObject object, bgG functionClazz, JsonSerializationContext serializationContext) {
      object.add("levels", serializationContext.serialize(bgG.access$000(functionClazz)));
      object.addProperty("treasure", bgG.access$100(functionClazz));
   }

   public bgG deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      bhC randomvaluerange = (bhC)JsonUtils.deserializeClass(object, "levels", deserializationContext, bhC.class);
      boolean flag = JsonUtils.getBoolean(object, "treasure", false);
      return new bgG(conditionsIn, randomvaluerange, flag);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgG)var2, var3);
   }
}
