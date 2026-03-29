package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.JsonUtils;

class rR implements JsonDeserializer<rS> {
   rR() {
   }

   public rS deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      EnumFacing enumfacing = this.parseCullFace(jsonobject);
      int i = this.parseTintIndex(jsonobject);
      String s = this.parseTexture(jsonobject);
      rN blockfaceuv = (rN)p_deserialize_3_.deserialize(jsonobject, rN.class);
      return new rS(enumfacing, i, s, blockfaceuv);
   }

   protected int parseTintIndex(JsonObject object) {
      return JsonUtils.getInt(object, "tintindex", -1);
   }

   private String parseTexture(JsonObject object) {
      return JsonUtils.getString(object, "texture");
   }

   @Nullable
   private EnumFacing parseCullFace(JsonObject object) {
      String s = JsonUtils.getString(object, "cullface", "");
      return EnumFacing.byName(s);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
