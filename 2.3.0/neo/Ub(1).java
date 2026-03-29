package neo;

import java.util.Collection;

public class Ub {
   private final String name;
   private final double baseValue;
   private final Collection<FW> modifiers;
   // $FF: synthetic field
   final Uc this$0;

   public Ub(Uc this$0, String nameIn, double baseValueIn, Collection modifiersIn) {
      this.this$0 = this$0;
      this.name = nameIn;
      this.baseValue = baseValueIn;
      this.modifiers = modifiersIn;
   }

   public String getName() {
      return this.name;
   }

   public double getBaseValue() {
      return this.baseValue;
   }

   public Collection<FW> getModifiers() {
      return this.modifiers;
   }
}
