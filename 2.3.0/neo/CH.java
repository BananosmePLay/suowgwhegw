package neo;

public class CH extends Ch {
   public CH() {
   }

   public String getName() {
      return "reload";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getUsage(DB sender) {
      return "commands.reload.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length > 0) {
         throw new Ej("commands.reload.usage", new Object[0]);
      } else {
         server.reload();
         notifyCommandListener(sender, this, "commands.reload.success", new Object[0]);
      }
   }
}
