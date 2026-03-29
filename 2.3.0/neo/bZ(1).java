package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bZ {
   private final cg criterionInstance;

   public bZ(cg p_i47470_1_) {
      this.criterionInstance = p_i47470_1_;
   }

   public bZ() {
      this.criterionInstance = null;
   }

   public void serializeToNetwork(SA p_192140_1_) {
   }

   public static bZ criterionFromJson(JsonObject json, JsonDeserializationContext context) {
      ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "trigger"));
      ci<?> icriteriontrigger = bY.get(resourcelocation);
      if (icriteriontrigger == null) {
         throw new JsonSyntaxException("Invalid criterion trigger: " + resourcelocation);
      } else {
         cg icriterioninstance = icriteriontrigger.deserializeInstance(JsonUtils.getJsonObject(json, "conditions", new JsonObject()), context);
         return new bZ(icriterioninstance);
      }
   }

   public static bZ criterionFromNetwork(SA p_192146_0_) {
      return new bZ();
   }

   public static Map<String, bZ> criteriaFromJson(JsonObject json, JsonDeserializationContext context) {
      Map<String, bZ> map = Maps.newHashMap();
      Iterator var3 = json.entrySet().iterator();

      while(var3.hasNext()) {
         Map.Entry<String, JsonElement> entry = (Map.Entry)var3.next();
         map.put(entry.getKey(), criterionFromJson(JsonUtils.getJsonObject((JsonElement)entry.getValue(), "criterion"), context));
      }

      return map;
   }

   public static Map<String, bZ> criteriaFromNetwork(SA bus) {
      Map<String, bZ> map = Maps.newHashMap();
      int i = bus.readVarInt();

      for(int j = 0; j < i; ++j) {
         map.put(bus.readString(32767), criterionFromNetwork(bus));
      }

      return map;
   }

   public static void serializeToNetwork(Map<String, bZ> criteria, SA buf) {
      buf.writeVarInt(criteria.size());
      Iterator var2 = criteria.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, bZ> entry = (Map.Entry)var2.next();
         buf.writeString((String)entry.getKey());
         ((bZ)entry.getValue()).serializeToNetwork(buf);
      }

   }

   @Nullable
   public cg getCriterionInstance() {
      return this.criterionInstance;
   }
}
