package neo;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;

public class tf implements JsonDeserializer<tg> {
   public tf() {
   }

   public tg deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      List<te> list = Lists.newArrayList();
      if (p_deserialize_1_.isJsonArray()) {
         JsonArray jsonarray = p_deserialize_1_.getAsJsonArray();
         if (jsonarray.size() == 0) {
            throw new JsonParseException("Empty variant array");
         }

         Iterator var6 = jsonarray.iterator();

         while(var6.hasNext()) {
            JsonElement jsonelement = (JsonElement)var6.next();
            list.add((te)p_deserialize_3_.deserialize(jsonelement, te.class));
         }
      } else {
         list.add((te)p_deserialize_3_.deserialize(p_deserialize_1_, te.class));
      }

      return new tg(list);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
