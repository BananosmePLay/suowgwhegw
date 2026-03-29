package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;

class Xi implements JsonDeserializer<Xm<K>>, JsonSerializer<Xm<K>> {
   // $FF: synthetic field
   final Xj this$0;

   private Xi(Xj this$0) {
      this.this$0 = this$0;
   }

   public JsonElement serialize(Xm<K> p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      p_serialize_1_.onSerialization(jsonobject);
      return jsonobject;
   }

   public Xm<K> deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      if (p_deserialize_1_.isJsonObject()) {
         JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
         return this.this$0.createEntry(jsonobject);
      } else {
         return null;
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
      return this.serialize((Xm)var1, var2, var3);
   }

   // $FF: synthetic method
   Xi(Xj x0, Object x1) {
      this(x0);
   }
}
