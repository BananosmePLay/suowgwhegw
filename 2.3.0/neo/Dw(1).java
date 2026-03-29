package neo;

import java.util.ArrayDeque;

public class Dw implements Dv {
   private final Dt function;

   public Dw(Dx functionIn) {
      this.function = new Dt(functionIn);
   }

   public void execute(cf functionManagerIn, DB sender, ArrayDeque<ce> commandQueue, int maxCommandChainLength) {
      Dx functionobject = this.function.get(functionManagerIn);
      if (functionobject != null) {
         Dv[] afunctionobject$entry = functionobject.getEntries();
         int i = maxCommandChainLength - commandQueue.size();
         int j = Math.min(afunctionobject$entry.length, i);

         for(int k = j - 1; k >= 0; --k) {
            commandQueue.addFirst(new ce(functionManagerIn, sender, afunctionobject$entry[k]));
         }
      }

   }

   public String toString() {
      return "/function " + this.function;
   }
}
