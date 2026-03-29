package neo;

import javax.annotation.Nullable;

public abstract class FX implements FY {
   private final FY parent;
   private final String translationKey;
   private final double defaultValue;
   private boolean shouldWatch;

   protected FX(@Nullable FY parentIn, String unlocalizedNameIn, double defaultValueIn) {
      this.parent = parentIn;
      this.translationKey = unlocalizedNameIn;
      this.defaultValue = defaultValueIn;
      if (unlocalizedNameIn == null) {
         throw new IllegalArgumentException("Name cannot be null!");
      }
   }

   public String getName() {
      return this.translationKey;
   }

   public double getDefaultValue() {
      return this.defaultValue;
   }

   public boolean getShouldWatch() {
      return this.shouldWatch;
   }

   public FX setShouldWatch(boolean shouldWatchIn) {
      this.shouldWatch = shouldWatchIn;
      return this;
   }

   @Nullable
   public FY getParent() {
      return this.parent;
   }

   public int hashCode() {
      return this.translationKey.hashCode();
   }

   public boolean equals(Object p_equals_1_) {
      return p_equals_1_ instanceof FY && this.translationKey.equals(((FY)p_equals_1_).getName());
   }
}
