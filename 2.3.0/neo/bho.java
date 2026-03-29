package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;

public class bho implements JsonDeserializer<bhp>, JsonSerializer<bhp> {
   public bho() {
   }

   public bhp deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "loot table");
      bhn[] alootpool = (bhn[])((bhn[])JsonUtils.deserializeClass(jsonobject, "pools", new bhn[0], p_deserialize_3_, bhn[].class));
      return new bhp(alootpool);
   }

   public JsonElement serialize(bhp p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.add("pools", p_serialize_3_.serialize(bhp.access$000(p_serialize_1_)));
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
      return this.serialize((bhp)var1, var2, var3);
   }
}
