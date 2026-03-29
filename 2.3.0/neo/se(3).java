package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

class se implements JsonDeserializer<sg> {
   se() {
   }

   public sg deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      sp itemtransformvec3f = this.getTransform(p_deserialize_3_, jsonobject, "thirdperson_righthand");
      sp itemtransformvec3f1 = this.getTransform(p_deserialize_3_, jsonobject, "thirdperson_lefthand");
      if (itemtransformvec3f1 == sp.DEFAULT) {
         itemtransformvec3f1 = itemtransformvec3f;
      }

      sp itemtransformvec3f2 = this.getTransform(p_deserialize_3_, jsonobject, "firstperson_righthand");
      sp itemtransformvec3f3 = this.getTransform(p_deserialize_3_, jsonobject, "firstperson_lefthand");
      if (itemtransformvec3f3 == sp.DEFAULT) {
         itemtransformvec3f3 = itemtransformvec3f2;
      }

      sp itemtransformvec3f4 = this.getTransform(p_deserialize_3_, jsonobject, "head");
      sp itemtransformvec3f5 = this.getTransform(p_deserialize_3_, jsonobject, "gui");
      sp itemtransformvec3f6 = this.getTransform(p_deserialize_3_, jsonobject, "ground");
      sp itemtransformvec3f7 = this.getTransform(p_deserialize_3_, jsonobject, "fixed");
      return new sg(itemtransformvec3f1, itemtransformvec3f, itemtransformvec3f3, itemtransformvec3f2, itemtransformvec3f4, itemtransformvec3f5, itemtransformvec3f6, itemtransformvec3f7);
   }

   private sp getTransform(JsonDeserializationContext p_181683_1_, JsonObject p_181683_2_, String p_181683_3_) {
      return p_181683_2_.has(p_181683_3_) ? (sp)p_181683_1_.deserialize(p_181683_2_.get(p_181683_3_), sp.class) : sp.DEFAULT;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
