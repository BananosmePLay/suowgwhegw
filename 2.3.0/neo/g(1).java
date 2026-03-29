package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.JsonUtils;

public class g implements JsonDeserializer<h>, JsonSerializer<h> {
   public g() {
   }

   public JsonElement serialize(h p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      JsonObject jsonobject1 = new JsonObject();
      Iterator var6 = h.access$000(p_serialize_1_).entrySet().iterator();

      while(var6.hasNext()) {
         Map.Entry<String, ca> entry = (Map.Entry)var6.next();
         ca criterionprogress = (ca)entry.getValue();
         if (criterionprogress.isObtained()) {
            jsonobject1.add((String)entry.getKey(), criterionprogress.serialize());
         }
      }

      if (!jsonobject1.entrySet().isEmpty()) {
         jsonobject.add("criteria", jsonobject1);
      }

      jsonobject.addProperty("done", p_serialize_1_.isDone());
      return jsonobject;
   }

   public h deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "advancement");
      JsonObject jsonobject1 = JsonUtils.getJsonObject(jsonobject, "criteria", new JsonObject());
      h advancementprogress = new h();
      Iterator var7 = jsonobject1.entrySet().iterator();

      while(var7.hasNext()) {
         Map.Entry<String, JsonElement> entry = (Map.Entry)var7.next();
         String s = (String)entry.getKey();
         h.access$000(advancementprogress).put(s, ca.fromDateTime(advancementprogress, JsonUtils.getString((JsonElement)entry.getValue(), s)));
      }

      return advancementprogress;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((h)var1, var2, var3);
   }
}
