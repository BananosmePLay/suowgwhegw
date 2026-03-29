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

public class sR implements JsonDeserializer<sS> {
   public sR() {
   }

   public sS deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      return new sS(this.getSelectors(p_deserialize_3_, p_deserialize_1_.getAsJsonArray()));
   }

   private List<sX> getSelectors(JsonDeserializationContext context, JsonArray elements) {
      List<sX> list = Lists.newArrayList();
      Iterator var4 = elements.iterator();

      while(var4.hasNext()) {
         JsonElement jsonelement = (JsonElement)var4.next();
         list.add((sX)context.deserialize(jsonelement, sX.class));
      }

      return list;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
