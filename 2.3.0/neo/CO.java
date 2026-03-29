package neo;

public class CO extends Ch {
   public CO() {
   }

   public String getName() {
      return "setidletimeout";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getUsage(DB sender) {
      return "commands.setidletimeout.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length != 1) {
         throw new Ej("commands.setidletimeout.usage", new Object[0]);
      } else {
         int i = parseInt(args[0], 0);
         server.setPlayerIdleTimeout(i);
         notifyCommandListener(sender, this, "commands.setidletimeout.success", new Object[]{i});
      }
   }
}
