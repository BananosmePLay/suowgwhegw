package neo;

public interface ES {
   ES DEFAULT_BEHAVIOR = new ES() {
      public Qy dispense(ET source, Qy stack) {
         return stack;
      }
   };

   Qy dispense(ET var1, Qy var2);
}
