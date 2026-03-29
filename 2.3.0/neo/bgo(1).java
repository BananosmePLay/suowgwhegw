package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgo extends bgu<bgp> {
   protected bgo() {
      super(new ResourceLocation("entity_properties"), bgp.class);
   }

   public void serialize(JsonObject json, bgp value, JsonSerializationContext context) {
      JsonObject jsonobject = new JsonObject();
      bhy[] var5 = bgp.access$000(value);
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         bhy entityproperty = var5[var7];
         bhx<bhy> serializer = bhz.getSerializerFor(entityproperty);
         jsonobject.add(serializer.getName().toString(), serializer.serialize(entityproperty, context));
      }

      json.add("properties", jsonobject);
      json.add("entity", context.serialize(bgp.access$100(value)));
   }

   public bgp deserialize(JsonObject json, JsonDeserializationContext context) {
      Set<Map.Entry<String, JsonElement>> set = JsonUtils.getJsonObject(json, "properties").entrySet();
      bhy[] aentityproperty = new bhy[set.size()];
      int i = 0;

      Map.Entry entry;
      for(Iterator var6 = set.iterator(); var6.hasNext(); aentityproperty[i++] = bhz.getSerializerForName(new ResourceLocation((String)entry.getKey())).deserialize((JsonElement)entry.getValue(), context)) {
         entry = (Map.Entry)var6.next();
      }

      return new bgp(aentityproperty, (bhf)JsonUtils.deserializeClass(json, "entity", context, bhf.class));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgv deserialize(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserialize(var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgv var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgp)var2, var3);
   }
}
