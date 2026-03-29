package neo;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;

public class sW implements JsonDeserializer<sX> {
   private static final Function<JsonElement, sQ> FUNCTION_OR_AND = new Function<JsonElement, sQ>() {
      @Nullable
      public sQ apply(@Nullable JsonElement p_apply_1_) {
         return p_apply_1_ == null ? null : sW.getOrAndCondition(p_apply_1_.getAsJsonObject());
      }

      // $FF: synthetic method
      // $FF: bridge method
      @Nullable
      public Object apply(@Nullable Object var1) {
         return this.apply((JsonElement)var1);
      }
   };
   private static final Function<Map.Entry<String, JsonElement>, sQ> FUNCTION_PROPERTY_VALUE = new Function<Map.Entry<String, JsonElement>, sQ>() {
      @Nullable
      public sQ apply(@Nullable Map.Entry<String, JsonElement> p_apply_1_) {
         return p_apply_1_ == null ? null : sW.makePropertyValue(p_apply_1_);
      }

      // $FF: synthetic method
      // $FF: bridge method
      @Nullable
      public Object apply(@Nullable Object var1) {
         return this.apply((Map.Entry)var1);
      }
   };

   public sW() {
   }

   public sX deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      return new sX(this.getWhenCondition(jsonobject), (tg)p_deserialize_3_.deserialize(jsonobject.get("apply"), tg.class));
   }

   private sQ getWhenCondition(JsonObject json) {
      return json.has("when") ? getOrAndCondition(JsonUtils.getJsonObject(json, "when")) : sQ.TRUE;
   }

   @VisibleForTesting
   static sQ getOrAndCondition(JsonObject json) {
      Set<Map.Entry<String, JsonElement>> set = json.entrySet();
      if (set.isEmpty()) {
         throw new JsonParseException("No elements found in selector");
      } else if (set.size() == 1) {
         if (json.has("OR")) {
            return new sI(Iterables.transform(JsonUtils.getJsonArray(json, "OR"), FUNCTION_OR_AND));
         } else {
            return (sQ)(json.has("AND") ? new sG(Iterables.transform(JsonUtils.getJsonArray(json, "AND"), FUNCTION_OR_AND)) : makePropertyValue((Map.Entry)set.iterator().next()));
         }
      } else {
         return new sG(Iterables.transform(set, FUNCTION_PROPERTY_VALUE));
      }
   }

   private static sL makePropertyValue(Map.Entry<String, JsonElement> entry) {
      return new sL((String)entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
