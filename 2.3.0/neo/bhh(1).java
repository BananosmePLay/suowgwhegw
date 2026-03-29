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

public class bhh implements JsonDeserializer<bhi>, JsonSerializer<bhi> {
   public bhh() {
   }

   public bhi deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "loot item");
      String s = JsonUtils.getString(jsonobject, "type");
      int i = JsonUtils.getInt(jsonobject, "weight", 1);
      int j = JsonUtils.getInt(jsonobject, "quality", 0);
      bgv[] alootcondition;
      if (jsonobject.has("conditions")) {
         alootcondition = (bgv[])((bgv[])JsonUtils.deserializeClass(jsonobject, "conditions", p_deserialize_3_, bgv[].class));
      } else {
         alootcondition = new bgv[0];
      }

      if ("item".equals(s)) {
         return bhk.deserialize(jsonobject, p_deserialize_3_, i, j, alootcondition);
      } else if ("loot_table".equals(s)) {
         return bhl.deserialize(jsonobject, p_deserialize_3_, i, j, alootcondition);
      } else if ("empty".equals(s)) {
         return bhj.deserialize(jsonobject, p_deserialize_3_, i, j, alootcondition);
      } else {
         throw new JsonSyntaxException("Unknown loot entry type '" + s + "'");
      }
   }

   public JsonElement serialize(bhi p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.addProperty("weight", p_serialize_1_.weight);
      jsonobject.addProperty("quality", p_serialize_1_.quality);
      if (p_serialize_1_.conditions.length > 0) {
         jsonobject.add("conditions", p_serialize_3_.serialize(p_serialize_1_.conditions));
      }

      if (p_serialize_1_ instanceof bhk) {
         jsonobject.addProperty("type", "item");
      } else if (p_serialize_1_ instanceof bhl) {
         jsonobject.addProperty("type", "loot_table");
      } else {
         if (!(p_serialize_1_ instanceof bhj)) {
            throw new IllegalArgumentException("Don't know how to serialize " + p_serialize_1_);
         }

         jsonobject.addProperty("type", "empty");
      }

      p_serialize_1_.serialize(jsonobject, p_serialize_3_);
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
      return this.serialize((bhi)var1, var2, var3);
   }
}
