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
import net.minecraft.util.text.ITextComponent;

public class Vr implements JsonDeserializer<Vu>, JsonSerializer<Vu> {
   public Vr() {
   }

   public Vu deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "status");
      Vu serverstatusresponse = new Vu();
      if (jsonobject.has("description")) {
         serverstatusresponse.setServerDescription((ITextComponent)p_deserialize_3_.deserialize(jsonobject.get("description"), ITextComponent.class));
      }

      if (jsonobject.has("players")) {
         serverstatusresponse.setPlayers((Vq)p_deserialize_3_.deserialize(jsonobject.get("players"), Vq.class));
      }

      if (jsonobject.has("version")) {
         serverstatusresponse.setVersion((Vt)p_deserialize_3_.deserialize(jsonobject.get("version"), Vt.class));
      }

      if (jsonobject.has("favicon")) {
         serverstatusresponse.setFavicon(JsonUtils.getString(jsonobject, "favicon"));
      }

      return serverstatusresponse;
   }

   public JsonElement serialize(Vu p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      if (p_serialize_1_.getServerDescription() != null) {
         jsonobject.add("description", p_serialize_3_.serialize(p_serialize_1_.getServerDescription()));
      }

      if (p_serialize_1_.getPlayers() != null) {
         jsonobject.add("players", p_serialize_3_.serialize(p_serialize_1_.getPlayers()));
      }

      if (p_serialize_1_.getVersion() != null) {
         jsonobject.add("version", p_serialize_3_.serialize(p_serialize_1_.getVersion()));
      }

      if (p_serialize_1_.getFavicon() != null) {
         jsonobject.addProperty("favicon", p_serialize_1_.getFavicon());
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
      return this.serialize((Vu)var1, var2, var3);
   }
}
