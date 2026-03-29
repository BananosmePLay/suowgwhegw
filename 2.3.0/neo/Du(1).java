package neo;

import java.util.ArrayDeque;

public class Du implements Dv {
   private final String command;

   public Du(String p_i47534_1_) {
      this.command = p_i47534_1_;
   }

   public void execute(cf functionManagerIn, DB sender, ArrayDeque<ce> commandQueue, int maxCommandChainLength) {
      functionManagerIn.getCommandManager().executeCommand(sender, this.command);
   }

   public String toString() {
      return "/" + this.command;
   }
}
