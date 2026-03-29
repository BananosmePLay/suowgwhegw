package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;

public class Ao extends Aa<An> {
   public Ao() {
   }

   public An deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      boolean flag = JsonUtils.getBoolean(jsonobject, "blur", false);
      boolean flag1 = JsonUtils.getBoolean(jsonobject, "clamp", false);
      return new An(flag, flag1);
   }

   public String getSectionName() {
      return "texture";
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
