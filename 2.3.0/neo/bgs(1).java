package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgs extends bgu<bgt> {
   protected bgs() {
      super(new ResourceLocation("killed_by_player"), bgt.class);
   }

   public void serialize(JsonObject json, bgt value, JsonSerializationContext context) {
      json.addProperty("inverse", bgt.access$000(value));
   }

   public bgt deserialize(JsonObject json, JsonDeserializationContext context) {
      return new bgt(JsonUtils.getBoolean(json, "inverse", false));
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgv deserialize(JsonObject var1, JsonDeserializationContext var2) {
      return this.deserialize(var1, var2);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgv var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgt)var2, var3);
   }
}
