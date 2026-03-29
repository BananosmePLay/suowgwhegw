package neo;

import javax.annotation.Nullable;

public interface FY {
   String getName();

   double clampValue(double var1);

   double getDefaultValue();

   boolean getShouldWatch();

   @Nullable
   FY getParent();
}
