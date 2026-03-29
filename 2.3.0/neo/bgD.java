package neo;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgD extends bgH<bgE> {
   public bgD() {
      super(new ResourceLocation("enchant_randomly"), bgE.class);
   }

   public void serialize(JsonObject object, bgE functionClazz, JsonSerializationContext serializationContext) {
      if (!bgE.access$000(functionClazz).isEmpty()) {
         JsonArray jsonarray = new JsonArray();
         Iterator var5 = bgE.access$000(functionClazz).iterator();

         while(var5.hasNext()) {
            Fa enchantment = (Fa)var5.next();
            ResourceLocation resourcelocation = (ResourceLocation)Fa.REGISTRY.getNameForObject(enchantment);
            if (resourcelocation == null) {
               throw new IllegalArgumentException("Don't know how to serialize enchantment " + enchantment);
            }

            jsonarray.add(new JsonPrimitive(resourcelocation.toString()));
         }

         object.add("enchantments", jsonarray);
      }

   }

   public bgE deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      List<Fa> list = Lists.newArrayList();
      if (object.has("enchantments")) {
         Iterator var5 = JsonUtils.getJsonArray(object, "enchantments").iterator();

         while(var5.hasNext()) {
            JsonElement jsonelement = (JsonElement)var5.next();
            String s = JsonUtils.getString(jsonelement, "enchantment");
            Fa enchantment = (Fa)Fa.REGISTRY.getObject(new ResourceLocation(s));
            if (enchantment == null) {
               throw new JsonSyntaxException("Unknown enchantment '" + s + "'");
            }

            list.add(enchantment);
         }
      }

      return new bgE(conditionsIn, list);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgE)var2, var3);
   }
}
