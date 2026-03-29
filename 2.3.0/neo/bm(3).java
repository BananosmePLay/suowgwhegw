package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;

public class bm {
   public static final bm UNBOUNDED = new bm((Float)null, (Float)null);
   private final Float min;
   private final Float max;

   public bm(@Nullable Float min, @Nullable Float max) {
      this.min = min;
      this.max = max;
   }

   public boolean test(float value) {
      if (this.min != null && this.min > value) {
         return false;
      } else {
         return this.max == null || this.max >= value;
      }
   }

   public boolean testSquare(double value) {
      if (this.min != null && (double)(this.min * this.min) > value) {
         return false;
      } else {
         return this.max == null || (double)(this.max * this.max) >= value;
      }
   }

   public static bm deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         if (JsonUtils.isNumber(element)) {
            float f2 = JsonUtils.getFloat(element, "value");
            return new bm(f2, f2);
         } else {
            JsonObject jsonobject = JsonUtils.getJsonObject(element, "value");
            Float f = jsonobject.has("min") ? JsonUtils.getFloat(jsonobject, "min") : null;
            Float f1 = jsonobject.has("max") ? JsonUtils.getFloat(jsonobject, "max") : null;
            return new bm(f, f1);
         }
      } else {
         return UNBOUNDED;
      }
   }
}
