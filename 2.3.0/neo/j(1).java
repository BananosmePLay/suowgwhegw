package neo;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class j implements JsonDeserializer<k> {
   public j() {
   }

   public k deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = JsonUtils.getJsonObject(p_deserialize_1_, "rewards");
      int i = JsonUtils.getInt(jsonobject, "experience", 0);
      JsonArray jsonarray = JsonUtils.getJsonArray(jsonobject, "loot", new JsonArray());
      ResourceLocation[] aresourcelocation = new ResourceLocation[jsonarray.size()];

      for(int j = 0; j < aresourcelocation.length; ++j) {
         aresourcelocation[j] = new ResourceLocation(JsonUtils.getString(jsonarray.get(j), "loot[" + j + "]"));
      }

      JsonArray jsonarray1 = JsonUtils.getJsonArray(jsonobject, "recipes", new JsonArray());
      ResourceLocation[] aresourcelocation1 = new ResourceLocation[jsonarray1.size()];

      for(int k = 0; k < aresourcelocation1.length; ++k) {
         aresourcelocation1[k] = new ResourceLocation(JsonUtils.getString(jsonarray1.get(k), "recipes[" + k + "]"));
         NT irecipe = NP.getRecipe(aresourcelocation1[k]);
         if (irecipe == null) {
            throw new JsonSyntaxException("Unknown recipe '" + aresourcelocation1[k] + "'");
         }
      }

      Dt functionobject$cacheablefunction;
      if (jsonobject.has("function")) {
         functionobject$cacheablefunction = new Dt(new ResourceLocation(JsonUtils.getString(jsonobject, "function")));
      } else {
         functionobject$cacheablefunction = Dt.EMPTY;
      }

      return new k(i, aresourcelocation, aresourcelocation1, functionobject$cacheablefunction);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
