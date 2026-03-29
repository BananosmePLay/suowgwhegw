package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgJ implements JsonDeserializer<bgI>, JsonSerializer<bgI> {
   public bgJ() {
   }

   public bgI deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "function");
      ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "function"));

      bgH serializer;
      try {
         serializer = bgK.getSerializerForName(resourcelocation);
      } catch (IllegalArgumentException var8) {
         throw new JsonSyntaxException("Unknown function '" + resourcelocation + "'");
      }

      return serializer.deserialize(jsonobject, p_deserialize_3_, (bgv[])((bgv[])JsonUtils.deserializeClass(jsonobject, "conditions", new bgv[0], p_deserialize_3_, bgv[].class)));
   }

   public JsonElement serialize(bgI p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      bgH<bgI> serializer = bgK.getSerializerFor(p_serialize_1_);
      JsonObject jsonobject = new JsonObject();
      serializer.serialize(jsonobject, p_serialize_1_, p_serialize_3_);
      jsonobject.addProperty("function", serializer.getFunctionName().toString());
      if (p_serialize_1_.getConditions() != null && p_serialize_1_.getConditions().length > 0) {
         jsonobject.add("conditions", p_serialize_3_.serialize(p_serialize_1_.getConditions()));
      }

      return jsonobject;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((bgI)var1, var2, var3);
   }
}
