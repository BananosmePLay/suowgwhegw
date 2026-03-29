package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgq extends bgu<bgr> {
   protected bgq() {
      super(new ResourceLocation("entity_scores"), bgr.class);
   }

   public void serialize(JsonObject json, bgr value, JsonSerializationContext context) {
      JsonObject jsonobject = new JsonObject();
      Iterator var5 = bgr.access$000(value).entrySet().iterator();

      while(var5.hasNext()) {
         Map.Entry<String, bhC> entry = (Map.Entry)var5.next();
         jsonobject.add((String)entry.getKey(), context.serialize(entry.getValue()));
      }

      json.add("scores", jsonobject);
      json.add("entity", context.serialize(bgr.access$100(value)));
   }

   public bgr deserialize(JsonObject json, JsonDeserializationContext context) {
      Set<Map.Entry<String, JsonElement>> set = JsonUtils.getJsonObject(json, "scores").entrySet();
      Map<String, bhC> map = Maps.newLinkedHashMap();
      Iterator var5 = set.iterator();

      while(var5.hasNext()) {
         Map.Entry<String, JsonElement> entry = (Map.Entry)var5.next();
         map.put(entry.getKey(), JsonUtils.deserializeClass((JsonElement)entry.getValue(), "score", context, bhC.class));
      }

      return new bgr(map, (bhf)JsonUtils.deserializeClass(json, "entity", context, bhf.class));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgv deserialize(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserialize(var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgv var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgr)var2, var3);
   }
}
