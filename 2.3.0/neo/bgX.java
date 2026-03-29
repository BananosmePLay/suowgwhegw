package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSyntaxException;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class bgX extends bgH<bgY> {
   public bgX() {
      super(new ResourceLocation("set_nbt"), bgY.class);
   }

   public void serialize(JsonObject object, bgY functionClazz, JsonSerializationContext serializationContext) {
      object.addProperty("tag", bgY.access$000(functionClazz).toString());
   }

   public bgY deserialize(JsonObject object, JsonDeserializationContext deserializationContext, bgv[] conditionsIn) {
      try {
         QQ nbttagcompound = QG.getTagFromJson(JsonUtils.getString(object, "tag"));
         return new bgY(conditionsIn, nbttagcompound);
      } catch (QI var5) {
         QI nbtexception = var5;
         throw new JsonSyntaxException(nbtexception);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public bgI deserialize(JsonObject var1, JsonDeserializationContext var2, bgv[] var3) {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void serialize(JsonObject var1, bgI var2, JsonSerializationContext var3) {
      this.serialize(var1, (bgY)var2, var3);
   }
}
