package neo;

public class CX extends Ch {
   public CX() {
   }

   public String getName() {
      return "toggledownfall";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.downfall.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      this.toggleRainfall(server);
      notifyCommandListener(sender, this, "commands.downfall.success", new Object[0]);
   }

   protected void toggleRainfall(Xx server) {
      bhY worldinfo = server.worlds[0].getWorldInfo();
      worldinfo.setRaining(!worldinfo.isRaining());
   }
}
