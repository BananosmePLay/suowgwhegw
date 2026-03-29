package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.util.UUID;
import net.minecraft.util.JsonUtils;

public class Vp implements JsonDeserializer<Vq>, JsonSerializer<Vq> {
   public Vp() {
   }

   public Vq deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "players");
      Vq serverstatusresponse$players = new Vq(JsonUtils.getInt(jsonobject, "max"), JsonUtils.getInt(jsonobject, "online"));
      if (JsonUtils.isJsonArray(jsonobject, "sample")) {
         JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "sample");
         if (jsonarray.size() > 0) {
            GameProfile[] agameprofile = new GameProfile[jsonarray.size()];

            for(int i = 0; i < agameprofile.length; ++i) {
               JsonObject jsonobject1 = JsonUtils.getJsonObject(jsonarray.get(i), "player[" + i + "]");
               String s = JsonUtils.getString(jsonobject1, "id");
               agameprofile[i] = new GameProfile(UUID.fromString(s), JsonUtils.getString(jsonobject1, "name"));
            }

            serverstatusresponse$players.setPlayers(agameprofile);
         }
      }

      return serverstatusresponse$players;
   }

   public JsonElement serialize(Vq p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.addProperty("max", p_serialize_1_.getMaxPlayers());
      jsonobject.addProperty("online", p_serialize_1_.getOnlinePlayerCount());
      if (p_serialize_1_.getPlayers() != null && p_serialize_1_.getPlayers().length > 0) {
         JsonArray jsonarray = new JsonArray();

         for(int i = 0; i < p_serialize_1_.getPlayers().length; ++i) {
            JsonObject jsonobject1 = new JsonObject();
            UUID uuid = p_serialize_1_.getPlayers()[i].getId();
            jsonobject1.addProperty("id", uuid == null ? "" : uuid.toString());
            jsonobject1.addProperty("name", p_serialize_1_.getPlayers()[i].getName());
            jsonarray.add(jsonobject1);
         }

         jsonobject.add("sample", jsonarray);
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
      return this.serialize((Vq)var1, var2, var3);
   }
}
