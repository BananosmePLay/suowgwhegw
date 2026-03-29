package neo;

import net.minecraft.util.ResourceLocation;

public class te {
   private final ResourceLocation modelLocation;
   private final sE rotation;
   private final boolean uvLock;
   private final int weight;

   public te(ResourceLocation modelLocationIn, sE rotationIn, boolean uvLockIn, int weightIn) {
      this.modelLocation = modelLocationIn;
      this.rotation = rotationIn;
      this.uvLock = uvLockIn;
      this.weight = weightIn;
   }

   public ResourceLocation getModelLocation() {
      return this.modelLocation;
   }

   public sE getRotation() {
      return this.rotation;
   }

   public boolean isUvLock() {
      return this.uvLock;
   }

   public int getWeight() {
      return this.weight;
   }

   public String toString() {
      return "Variant{modelLocation=" + this.modelLocation + ", rotation=" + this.rotation + ", uvLock=" + this.uvLock + ", weight=" + this.weight + '}';
   }

   public boolean equals(Object p_equals_1_) {
      if (this == p_equals_1_) {
         return true;
      } else if (!(p_equals_1_ instanceof te)) {
         return false;
      } else {
         te variant = (te)p_equals_1_;
         return this.modelLocation.equals(variant.modelLocation) && this.rotation == variant.rotation && this.uvLock == variant.uvLock && this.weight == variant.weight;
      }
   }

   public int hashCode() {
      int i = this.modelLocation.hashCode();
      i = 31 * i + this.rotation.hashCode();
      i = 31 * i + Boolean.valueOf(this.uvLock).hashCode();
      i = 31 * i + this.weight;
      return i;
   }
}
