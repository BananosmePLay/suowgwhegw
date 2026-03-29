package neo;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.List;
import net.minecraft.util.JsonUtils;
import org.apache.commons.lang3.Validate;

public class iW implements JsonDeserializer<iV> {
   public iW() {
   }

   public iV deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "entry");
      boolean flag = JsonUtils.getBoolean(jsonobject, "replace", false);
      String s = JsonUtils.getString(jsonobject, "subtitle", (String)null);
      List<iP> list = this.deserializeSounds(jsonobject);
      return new iV(list, flag, s);
   }

   private List<iP> deserializeSounds(JsonObject object) {
      List<iP> list = Lists.newArrayList();
      if (object.has("sounds")) {
         JsonArray jsonarray = JsonUtils.getJsonArray(object, "sounds");

         for(int i = 0; i < jsonarray.size(); ++i) {
            JsonElement jsonelement = jsonarray.get(i);
            if (JsonUtils.isString(jsonelement)) {
               String s = JsonUtils.getString(jsonelement, "sound");
               list.add(new iP(s, 1.0F, 1.0F, 1, iO.FILE, false));
            } else {
               list.add(this.deserializeSound(JsonUtils.getJsonObject(jsonelement, "sound")));
            }
         }
      }

      return list;
   }

   private iP deserializeSound(JsonObject object) {
      String s = JsonUtils.getString(object, "name");
      iO sound$type = this.deserializeType(object, iO.FILE);
      float f = JsonUtils.getFloat(object, "volume", 1.0F);
      Validate.isTrue(f > 0.0F, "Invalid volume", new Object[0]);
      float f1 = JsonUtils.getFloat(object, "pitch", 1.0F);
      Validate.isTrue(f1 > 0.0F, "Invalid pitch", new Object[0]);
      int i = JsonUtils.getInt(object, "weight", 1);
      Validate.isTrue(i > 0, "Invalid weight", new Object[0]);
      boolean flag = JsonUtils.getBoolean(object, "stream", false);
      return new iP(s, f, f1, i, sound$type, flag);
   }

   private iO deserializeType(JsonObject object, iO defaultValue) {
      iO sound$type = defaultValue;
      if (object.has("type")) {
         sound$type = iO.getByName(JsonUtils.getString(object, "type"));
         Validate.notNull(sound$type, "Invalid type", new Object[0]);
      }

      return sound$type;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
