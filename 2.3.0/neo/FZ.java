package neo;

import java.util.Collection;
import java.util.UUID;
import javax.annotation.Nullable;

public interface FZ {
   FY getAttribute();

   double getBaseValue();

   void setBaseValue(double var1);

   Collection<FW> getModifiersByOperation(int var1);

   Collection<FW> getModifiers();

   boolean hasModifier(FW var1);

   @Nullable
   FW getModifier(UUID var1);

   void applyModifier(FW var1);

   void removeModifier(FW var1);

   void removeModifier(UUID var1);

   void removeAllModifiers();

   double getAttributeValue();
}
