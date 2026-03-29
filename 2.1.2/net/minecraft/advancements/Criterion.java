package net.minecraft.advancements;

import com.google.common.collect.Maps;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class Criterion {
   private final ICriterionInstance criterionInstance;

   public Criterion(ICriterionInstance p_i47470_1_) {
      this.criterionInstance = p_i47470_1_;
   }

   public Criterion() {
      this.criterionInstance = null;
   }

   public void serializeToNetwork(PacketBuffer p_192140_1_) {
   }

   public static Criterion criterionFromJson(JsonObject json, JsonDeserializationContext context) {
      ResourceLocation resourcelocation = new ResourceLocation(JsonUtils.getString(json, "trigger"));
      ICriterionTrigger<?> icriteriontrigger = CriteriaTriggers.get(resourcelocation);
      if (icriteriontrigger == null) {
         throw new JsonSyntaxException("Invalid criterion trigger: " + resourcelocation);
      } else {
         ICriterionInstance icriterioninstance = icriteriontrigger.deserializeInstance(JsonUtils.getJsonObject(json, "conditions", new JsonObject()), context);
         return new Criterion(icriterioninstance);
      }
   }

   public static Criterion criterionFromNetwork(PacketBuffer p_192146_0_) {
      return new Criterion();
   }

   public static Map<String, Criterion> criteriaFromJson(JsonObject json, JsonDeserializationContext context) {
      Map<String, Criterion> map = Maps.newHashMap();
      Iterator var3 = json.entrySet().iterator();

      while(var3.hasNext()) {
         Map.Entry<String, JsonElement> entry = (Map.Entry)var3.next();
         map.put(entry.getKey(), criterionFromJson(JsonUtils.getJsonObject((JsonElement)entry.getValue(), "criterion"), context));
      }

      return map;
   }

   public static Map<String, Criterion> criteriaFromNetwork(PacketBuffer bus) {
      Map<String, Criterion> map = Maps.newHashMap();
      int i = bus.readVarInt();

      for(int j = 0; j < i; ++j) {
         map.put(bus.readString(32767), criterionFromNetwork(bus));
      }

      return map;
   }

   public static void serializeToNetwork(Map<String, Criterion> criteria, PacketBuffer buf) {
      buf.writeVarInt(criteria.size());
      Iterator var2 = criteria.entrySet().iterator();

      while(var2.hasNext()) {
         Map.Entry<String, Criterion> entry = (Map.Entry)var2.next();
         buf.writeString((String)entry.getKey());
         ((Criterion)entry.getValue()).serializeToNetwork(buf);
      }

   }

   @Nullable
   public ICriterionInstance getCriterionInstance() {
      return this.criterionInstance;
   }
}
