package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class sw implements JsonDeserializer<sy> {
   public sw() {
   }

   public sy deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      List<rQ> list = this.getModelElements(p_deserialize_3_, jsonobject);
      String s = this.getParent(jsonobject);
      Map<String, String> map = this.getTextures(jsonobject);
      boolean flag = this.getAmbientOcclusionEnabled(jsonobject);
      sg itemcameratransforms = sg.DEFAULT;
      if (jsonobject.has("display")) {
         JsonObject jsonobject1 = JsonUtils.getJsonObject(jsonobject, "display");
         itemcameratransforms = (sg)p_deserialize_3_.deserialize(jsonobject1, sg.class);
      }

      List<sm> list1 = this.getItemOverrides(p_deserialize_3_, jsonobject);
      ResourceLocation resourcelocation = s.isEmpty() ? null : new ResourceLocation(s);
      return new sy(resourcelocation, list, map, flag, true, itemcameratransforms, list1);
   }

   protected List<sm> getItemOverrides(JsonDeserializationContext deserializationContext, JsonObject object) {
      List<sm> list = Lists.newArrayList();
      if (object.has("overrides")) {
         Iterator var4 = JsonUtils.getJsonArray(object, "overrides").iterator();

         while(var4.hasNext()) {
            JsonElement jsonelement = (JsonElement)var4.next();
            list.add((sm)deserializationContext.deserialize(jsonelement, sm.class));
         }
      }

      return list;
   }

   private Map<String, String> getTextures(JsonObject object) {
      Map<String, String> map = Maps.newHashMap();
      if (object.has("textures")) {
         JsonObject jsonobject = object.getAsJsonObject("textures");
         Iterator var4 = jsonobject.entrySet().iterator();

         while(var4.hasNext()) {
            Map.Entry<String, JsonElement> entry = (Map.Entry)var4.next();
            map.put(entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
         }
      }

      return map;
   }

   private String getParent(JsonObject object) {
      return JsonUtils.getString(object, "parent", "");
   }

   protected boolean getAmbientOcclusionEnabled(JsonObject object) {
      return JsonUtils.getBoolean(object, "ambientocclusion", true);
   }

   protected List<rQ> getModelElements(JsonDeserializationContext deserializationContext, JsonObject object) {
      List<rQ> list = Lists.newArrayList();
      if (object.has("elements")) {
         Iterator var4 = JsonUtils.getJsonArray(object, "elements").iterator();

         while(var4.hasNext()) {
            JsonElement jsonelement = (JsonElement)var4.next();
            list.add((rQ)deserializationContext.deserialize(jsonelement, rQ.class));
         }
      }

      return list;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
