package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

class sl implements JsonDeserializer<sm> {
   sl() {
   }

   public sm deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(jsonobject, "model"));
      Map<ResourceLocation, Float> map = this.makeMapResourceValues(jsonobject);
      return new sm(resourcelocation, map);
   }

   protected Map<ResourceLocation, Float> makeMapResourceValues(JsonObject p_188025_1_) {
      Map<ResourceLocation, Float> map = Maps.newLinkedHashMap();
      JsonObject jsonobject = JsonUtils.getJsonObject(p_188025_1_, "predicate");
      Iterator var4 = jsonobject.entrySet().iterator();

      while(var4.hasNext()) {
         Map.Entry<String, JsonElement> entry = (Map.Entry)var4.next();
         map.put(new ResourceLocation((String)entry.getKey()), JsonUtils.getFloat((JsonElement)entry.getValue(), (String)entry.getKey()));
      }

      return map;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
