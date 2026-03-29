package neo;

public class Ea extends Ch {
   public Ea() {
   }

   public String getName() {
      return "stop";
   }

   public String getUsage(DB sender) {
      return "commands.stop.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (server.worlds != null) {
         notifyCommandListener(sender, this, "commands.stop.start", new Object[0]);
      }

      server.initiateShutdown();
   }
}
