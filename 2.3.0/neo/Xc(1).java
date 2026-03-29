package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

class Xc implements JsonDeserializer<Xb>, JsonSerializer<Xb> {
   // $FF: synthetic field
   final Xd this$0;

   private Xc(Xd this$0) {
      this.this$0 = this$0;
   }

   public JsonElement serialize(Xb p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.addProperty("name", p_serialize_1_.getGameProfile().getName());
      UUID uuid = p_serialize_1_.getGameProfile().getId();
      jsonobject.addProperty("uuid", uuid == null ? "" : uuid.toString());
      jsonobject.addProperty("expiresOn", Xd.DATE_FORMAT.format(p_serialize_1_.getExpirationDate()));
      return jsonobject;
   }

   public Xb deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      if (p_deserialize_1_.isJsonObject()) {
         JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
         JsonElement jsonelement = jsonobject.get("name");
         JsonElement jsonelement1 = jsonobject.get("uuid");
         JsonElement jsonelement2 = jsonobject.get("expiresOn");
         if (jsonelement != null && jsonelement1 != null) {
            String s = jsonelement1.getAsString();
            String s1 = jsonelement.getAsString();
            Date date = null;
            if (jsonelement2 != null) {
               try {
                  date = Xd.DATE_FORMAT.parse(jsonelement2.getAsString());
               } catch (ParseException var14) {
                  date = null;
               }
            }

            if (s1 != null && s != null) {
               UUID uuid;
               try {
                  uuid = UUID.fromString(s);
               } catch (Throwable var13) {
                  return null;
               }

               Xd var10002 = this.this$0;
               var10002.getClass();
               return new Xb(var10002, new GameProfile(uuid, s1), date);
            } else {
               return null;
            }
         } else {
            return null;
         }
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
      return this.serialize((Xb)var1, var2, var3);
   }

   // $FF: synthetic method
   Xc(Xd x0, Object x1) {
      this(x0);
   }
}
