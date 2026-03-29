package neo;

import java.util.ArrayDeque;

public class ce {
   private final cf functionManager;
   private final DB sender;
   private final Dv entry;

   public ce(cf functionManagerIn, DB senderIn, Dv entryIn) {
      this.functionManager = functionManagerIn;
      this.sender = senderIn;
      this.entry = entryIn;
   }

   public void execute(ArrayDeque<ce> commandQueue, int maxCommandChainLength) {
      this.entry.execute(this.functionManager, this.sender, commandQueue, maxCommandChainLength);
   }

   public String toString() {
      return this.entry.toString();
   }
}
