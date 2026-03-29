package neo;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

public class td implements JsonDeserializer<te> {
   public td() {
   }

   public te deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      String s = this.getStringModel(jsonobject);
      sE modelrotation = this.parseModelRotation(jsonobject);
      boolean flag = this.parseUvLock(jsonobject);
      int i = this.parseWeight(jsonobject);
      return new te(this.getResourceLocationBlock(s), modelrotation, flag, i);
   }

   private ResourceLocation getResourceLocationBlock(String p_188041_1_) {
      ResourceLocation resourcelocation = new ResourceLocation(p_188041_1_);
      resourcelocation = new ResourceLocation(resourcelocation.getNamespace(), "block/" + resourcelocation.getPath());
      return resourcelocation;
   }

   private boolean parseUvLock(JsonObject json) {
      return JsonUtils.getBoolean(json, "uvlock", false);
   }

   protected sE parseModelRotation(JsonObject json) {
      int i = JsonUtils.getInt(json, "x", 0);
      int j = JsonUtils.getInt(json, "y", 0);
      sE modelrotation = sE.getModelRotation(i, j);
      if (modelrotation == null) {
         throw new JsonParseException("Invalid BlockModelRotation x: " + i + ", y: " + j);
      } else {
         return modelrotation;
      }
   }

   protected String getStringModel(JsonObject json) {
      return JsonUtils.getString(json, "model");
   }

   protected int parseWeight(JsonObject json) {
      int i = JsonUtils.getInt(json, "weight", 1);
      if (i < 1) {
         throw new JsonParseException("Invalid weight " + i + " found, expected integer >= 1");
      } else {
         return i;
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
