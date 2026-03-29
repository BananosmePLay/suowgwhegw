package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;

public class bhB implements JsonDeserializer<bhC>, JsonSerializer<bhC> {
   public bhB() {
   }

   public bhC deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      if (JsonUtils.isNumber(p_deserialize_1_)) {
         return new bhC(JsonUtils.getFloat(p_deserialize_1_, "value"));
      } else {
         JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "value");
         float f = JsonUtils.getFloat(jsonobject, "min");
         float f1 = JsonUtils.getFloat(jsonobject, "max");
         return new bhC(f, f1);
      }
   }

   public JsonElement serialize(bhC p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      if (bhC.access$000(p_serialize_1_) == bhC.access$100(p_serialize_1_)) {
         return new JsonPrimitive(bhC.access$000(p_serialize_1_));
      } else {
         JsonObject jsonobject = new JsonObject();
         jsonobject.addProperty("min", bhC.access$000(p_serialize_1_));
         jsonobject.addProperty("max", bhC.access$100(p_serialize_1_));
         return jsonobject;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((bhC)var1, var2, var3);
   }
}
