package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;

public class sz implements JsonDeserializer<sB> {
   public sz() {
   }

   public sB deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      Map<String, tg> map = this.parseMapVariants(p_deserialize_3_, jsonobject);
      sS multipart = this.parseMultipart(p_deserialize_3_, jsonobject);
      if (map.isEmpty() && (multipart == null || multipart.getVariants().isEmpty())) {
         throw new JsonParseException("Neither 'variants' nor 'multipart' found");
      } else {
         return new sB(map, multipart);
      }
   }

   protected Map<String, tg> parseMapVariants(JsonDeserializationContext deserializationContext, JsonObject object) {
      Map<String, tg> map = Maps.newHashMap();
      if (object.has("variants")) {
         JsonObject jsonobject = JsonUtils.getJsonObject(object, "variants");
         Iterator var5 = jsonobject.entrySet().iterator();

         while(var5.hasNext()) {
            Map.Entry<String, JsonElement> entry = (Map.Entry)var5.next();
            map.put(entry.getKey(), (tg)deserializationContext.deserialize((JsonElement)entry.getValue(), tg.class));
         }
      }

      return map;
   }

   @Nullable
   protected sS parseMultipart(JsonDeserializationContext deserializationContext, JsonObject object) {
      if (!object.has("multipart")) {
         return null;
      } else {
         JsonArray jsonarray = JsonUtils.getJsonArray(object, "multipart");
         return (sS)deserializationContext.deserialize(jsonarray, sS.class);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
