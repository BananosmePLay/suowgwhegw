package neo;

import java.util.Random;
import net.minecraft.util.math.MathHelper;

public class bhC {
   private final float min;
   private final float max;

   public bhC(float minIn, float maxIn) {
      this.min = minIn;
      this.max = maxIn;
   }

   public bhC(float value) {
      this.min = value;
      this.max = value;
   }

   public float getMin() {
      return this.min;
   }

   public float getMax() {
      return this.max;
   }

   public int generateInt(Random rand) {
      return MathHelper.getInt(rand, MathHelper.floor(this.min), MathHelper.floor(this.max));
   }

   public float generateFloat(Random rand) {
      return MathHelper.nextFloat(rand, this.min, this.max);
   }

   public boolean isInRange(int value) {
      return (float)value <= this.max && (float)value >= this.min;
   }

   // $FF: synthetic method
   static float access$000(bhC x0) {
      return x0.min;
   }

   // $FF: synthetic method
   static float access$100(bhC x0) {
      return x0.max;
   }
}
