package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.text.ITextComponent;

public class Am extends Aa<Al> implements JsonSerializer<Al> {
   public Am() {
   }

   public Al deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      ITextComponent itextcomponent = (ITextComponent)p_deserialize_3_.deserialize(jsonobject.get("description"), ITextComponent.class);
      if (itextcomponent == null) {
         throw new JsonParseException("Invalid/missing description!");
      } else {
         int i = JsonUtils.getInt(jsonobject, "pack_format");
         return new Al(itextcomponent, i);
      }
   }

   public JsonElement serialize(Al p_serialize_1_, Type p_serialize_2_, JsonSerializationContext p_serialize_3_) {
      JsonObject jsonobject = new JsonObject();
      jsonobject.addProperty("pack_format", p_serialize_1_.getPackFormat());
      jsonobject.add("description", p_serialize_3_.serialize(p_serialize_1_.getPackDescription()));
      return jsonobject;
   }

   public String getSectionName() {
      return "pack";
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public JsonElement serialize(Object var1, Type var2, JsonSerializationContext var3) {
      return this.serialize((Al)var1, var2, var3);
   }
}
