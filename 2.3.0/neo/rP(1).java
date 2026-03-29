package neo;

import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.util.vector.Vector3f;

class rP implements JsonDeserializer<rQ> {
   rP() {
   }

   public rQ deserialize(JsonElement p_deserialize_1_, Type p_deserialize_2_, JsonDeserializationContext p_deserialize_3_) throws JsonParseException {
      JsonObject jsonobject = p_deserialize_1_.getAsJsonObject();
      Vector3f vector3f = this.parsePositionFrom(jsonobject);
      Vector3f vector3f1 = this.parsePositionTo(jsonobject);
      rT blockpartrotation = this.parseRotation(jsonobject);
      Map<EnumFacing, rS> map = this.parseFacesCheck(p_deserialize_3_, jsonobject);
      if (jsonobject.has("shade") && !JsonUtils.isBoolean(jsonobject, "shade")) {
         throw new JsonParseException("Expected shade to be a Boolean");
      } else {
         boolean flag = JsonUtils.getBoolean(jsonobject, "shade", true);
         return new rQ(vector3f, vector3f1, map, blockpartrotation, flag);
      }
   }

   @Nullable
   private rT parseRotation(JsonObject object) {
      rT blockpartrotation = null;
      if (object.has("rotation")) {
         JsonObject jsonobject = JsonUtils.getJsonObject(object, "rotation");
         Vector3f vector3f = this.parsePosition(jsonobject, "origin");
         vector3f.scale(0.0625F);
         EnumFacing.Axis enumfacing$axis = this.parseAxis(jsonobject);
         float f = this.parseAngle(jsonobject);
         boolean flag = JsonUtils.getBoolean(jsonobject, "rescale", false);
         blockpartrotation = new rT(vector3f, enumfacing$axis, f, flag);
      }

      return blockpartrotation;
   }

   private float parseAngle(JsonObject object) {
      float f = JsonUtils.getFloat(object, "angle");
      if (f != 0.0F && MathHelper.abs(f) != 22.5F && MathHelper.abs(f) != 45.0F) {
         throw new JsonParseException("Invalid rotation " + f + " found, only -45/-22.5/0/22.5/45 allowed");
      } else {
         return f;
      }
   }

   private EnumFacing.Axis parseAxis(JsonObject object) {
      String s = JsonUtils.getString(object, "axis");
      EnumFacing.Axis enumfacing$axis = EnumFacing.Axis.byName(s.toLowerCase(Locale.ROOT));
      if (enumfacing$axis == null) {
         throw new JsonParseException("Invalid rotation axis: " + s);
      } else {
         return enumfacing$axis;
      }
   }

   private Map<EnumFacing, rS> parseFacesCheck(JsonDeserializationContext deserializationContext, JsonObject object) {
      Map<EnumFacing, rS> map = this.parseFaces(deserializationContext, object);
      if (map.isEmpty()) {
         throw new JsonParseException("Expected between 1 and 6 unique faces, got 0");
      } else {
         return map;
      }
   }

   private Map<EnumFacing, rS> parseFaces(JsonDeserializationContext deserializationContext, JsonObject object) {
      Map<EnumFacing, rS> map = Maps.newEnumMap(EnumFacing.class);
      JsonObject jsonobject = JsonUtils.getJsonObject(object, "faces");
      Iterator var5 = jsonobject.entrySet().iterator();

      while(var5.hasNext()) {
         Map.Entry<String, JsonElement> entry = (Map.Entry)var5.next();
         EnumFacing enumfacing = this.parseEnumFacing((String)entry.getKey());
         map.put(enumfacing, (rS)deserializationContext.deserialize((JsonElement)entry.getValue(), rS.class));
      }

      return map;
   }

   private EnumFacing parseEnumFacing(String name) {
      EnumFacing enumfacing = EnumFacing.byName(name);
      if (enumfacing == null) {
         throw new JsonParseException("Unknown facing: " + name);
      } else {
         return enumfacing;
      }
   }

   private Vector3f parsePositionTo(JsonObject object) {
      Vector3f vector3f = this.parsePosition(object, "to");
      if (vector3f.x >= -16.0F && vector3f.y >= -16.0F && vector3f.z >= -16.0F && vector3f.x <= 32.0F && vector3f.y <= 32.0F && vector3f.z <= 32.0F) {
         return vector3f;
      } else {
         throw new JsonParseException("'to' specifier exceeds the allowed boundaries: " + vector3f);
      }
   }

   private Vector3f parsePositionFrom(JsonObject object) {
      Vector3f vector3f = this.parsePosition(object, "from");
      if (vector3f.x >= -16.0F && vector3f.y >= -16.0F && vector3f.z >= -16.0F && vector3f.x <= 32.0F && vector3f.y <= 32.0F && vector3f.z <= 32.0F) {
         return vector3f;
      } else {
         throw new JsonParseException("'from' specifier exceeds the allowed boundaries: " + vector3f);
      }
   }

   private Vector3f parsePosition(JsonObject object, String memberName) {
      JsonArray jsonarray = JsonUtils.getJsonArray(object, memberName);
      if (jsonarray.size() != 3) {
         throw new JsonParseException("Expected 3 " + memberName + " values, found: " + jsonarray.size());
      } else {
         float[] afloat = new float[3];

         for(int i = 0; i < afloat.length; ++i) {
            afloat[i] = JsonUtils.getFloat(jsonarray.get(i), memberName + "[" + i + "]");
         }

         return new Vector3f(afloat[0], afloat[1], afloat[2]);
      }
   }

   // $FF: synthetic method
   // $FF: bridge method
   public Object deserialize(JsonElement var1, Type var2, JsonDeserializationContext var3) throws JsonParseException {
      return this.deserialize(var1, var2, var3);
   }
}
