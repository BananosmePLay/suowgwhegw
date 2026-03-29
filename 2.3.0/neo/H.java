package neo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.annotation.Nullable;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.math.MathHelper;

public class H {
   public static final H ANY;
   private final bm x;
   private final bm y;
   private final bm z;
   private final bm horizontal;
   private final bm absolute;

   public H(bm x, bm y, bm z, bm horizontal, bm absolute) {
      this.x = x;
      this.y = y;
      this.z = z;
      this.horizontal = horizontal;
      this.absolute = absolute;
   }

   public boolean test(double x1, double y1, double z1, double x2, double y2, double z2) {
      float f = (float)(x1 - x2);
      float f1 = (float)(y1 - y2);
      float f2 = (float)(z1 - z2);
      if (this.x.test(MathHelper.abs(f)) && this.y.test(MathHelper.abs(f1)) && this.z.test(MathHelper.abs(f2))) {
         return !this.horizontal.testSquare((double)(f * f + f2 * f2)) ? false : this.absolute.testSquare((double)(f * f + f1 * f1 + f2 * f2));
      } else {
         return false;
      }
   }

   public static H deserialize(@Nullable JsonElement element) {
      if (element != null && !element.isJsonNull()) {
         JsonObject jsonobject = JsonUtils.getJsonObject(element, "distance");
         bm minmaxbounds = bm.deserialize(jsonobject.get("x"));
         bm minmaxbounds1 = bm.deserialize(jsonobject.get("y"));
         bm minmaxbounds2 = bm.deserialize(jsonobject.get("z"));
         bm minmaxbounds3 = bm.deserialize(jsonobject.get("horizontal"));
         bm minmaxbounds4 = bm.deserialize(jsonobject.get("absolute"));
         return new H(minmaxbounds, minmaxbounds1, minmaxbounds2, minmaxbounds3, minmaxbounds4);
      } else {
         return ANY;
      }
   }

   static {
      ANY = new H(bm.UNBOUNDED, bm.UNBOUNDED, bm.UNBOUNDED, bm.UNBOUNDED, bm.UNBOUNDED);
   }
}
