package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgP extends bgH<bgQ> {
   public bgP() {
      super(new ResourceLocation("set_attributes"), bgQ.class);
   }

   public void serialize(JsonObject object, bgQ functionClazz, JsonSerializationContext serializationContext) {
      JsonArray jsonarray = new JsonArray();
      bgO[] var5 = bgQ.access$600(functionClazz);
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         bgO setattributes$modifier = var5[var7];
         jsonarray.add(setattributes$modifier.serialize(serializationContext));
      }

      object.add("modifiers", jsonarray);
   }

   public bgQ deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      JsonArray jsonarray = JsonUtils.getJsonArray(object, "modifiers");
      bgO[] asetattributes$modifier = new bgO[jsonarray.size()];
      int i = 0;

      JsonElement jsonelement;
      for(Iterator var7 = jsonarray.iterator(); var7.hasNext(); asetattributes$modifier[i++] = bgO.deserialize(JsonUtils.getJsonObject(jsonelement, "modifier"), deserializationContext)) {
         jsonelement = (JsonElement)var7.next();
      }

      if (asetattributes$modifier.length == 0) {
         throw new JsonSyntaxException("Invalid attribute modifiers array; cannot be empty");
      } else {
         return new bgQ(conditionsIn, asetattributes$modifier);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgQ)var2, var3);
   }
}
