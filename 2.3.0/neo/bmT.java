package neo;

import java.util.Arrays;
import net.minecraft.util.ResourceLocation;

public class bmT {
   private ResourceLocation location;
   private float[] values;

   public bmT(ResourceLocation location, float[] values) {
      this.location = location;
      this.values = (float[])((float[])(([F)values).clone());
      Arrays.sort(this.values);
   }

   public Integer getValueIndex(Qy stack, bij world, Iw entity) {
      OL item = stack.getItem();
      Oo iitempropertygetter = item.getPropertyGetter(this.location);
      if (iitempropertygetter == null) {
         return null;
      } else {
         float f = iitempropertygetter.apply(stack, world, entity);
         int i = Arrays.binarySearch(this.values, f);
         return i;
      }
   }

   public ResourceLocation getLocation() {
      return this.location;
   }

   public float[] getValues() {
      return this.values;
   }

   public String toString() {
      return "location: " + this.location + ", values: [" + XH.arrayToString(this.values) + "]";
   }
}
