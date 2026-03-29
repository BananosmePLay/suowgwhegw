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
import org.apache.commons.lang3.ArrayUtils;

public class bhm implements JsonDeserializer<bhn>, JsonSerializer<bhn> {
   public bhm() {
   }

   public bhn deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "loot pool");
      bhi[] alootentry = (bhi[])((bhi[])JsonUtils.deserializeClass(jsonobject, "entries", p_deserialize_3_, bhi[].class));
      bgv[] alootcondition = (bgv[])((bgv[])JsonUtils.deserializeClass(jsonobject, "conditions", new bgv[0], p_deserialize_3_, bgv[].class));
      bhC randomvaluerange = (bhC)JsonUtils.deserializeClass(jsonobject, "rolls", p_deserialize_3_, bhC.class);
      bhC randomvaluerange1 = (bhC)JsonUtils.deserializeClass(jsonobject, "bonus_rolls", new bhC(0.0F, 0.0F), p_deserialize_3_, bhC.class);
      return new bhn(alootentry, alootcondition, randomvaluerange, randomvaluerange1);
   }

   public JsonElement serialize(bhn p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.add("entries", p_serialize_3_.serialize(bhn.access$000(p_serialize_1_)));
      jsonobject.add("rolls", p_serialize_3_.serialize(bhn.access$100(p_serialize_1_)));
      if (bhn.access$200(p_serialize_1_).getMin() != 0.0F && bhn.access$200(p_serialize_1_).getMax() != 0.0F) {
         jsonobject.add("bonus_rolls", p_serialize_3_.serialize(bhn.access$200(p_serialize_1_)));
      }

      if (!ArrayUtils.isEmpty((Object[])bhn.access$300(p_serialize_1_))) {
         jsonobject.add("conditions", p_serialize_3_.serialize(bhn.access$300(p_serialize_1_)));
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
      return this.serialize((bhn)var1, var2, var3);
   }
}
