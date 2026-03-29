package neo;

public class DV extends Ch {
   public DV() {
   }

   public String getName() {
      return "save-off";
   }

   public String getUsage(DB sender) {
      return "commands.save-off.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      boolean flag = false;

      for(int i = 0; i < server.worlds.length; ++i) {
         if (server.worlds[i] != null) {
            bis worldserver = server.worlds[i];
            if (!worldserver.disableLevelSaving) {
               worldserver.disableLevelSaving = true;
               flag = true;
            }
         }
      }

      if (flag) {
         notifyCommandListener(sender, this, "commands.save.disabled", new Object[0]);
      } else {
         throw new Ct("commands.save-off.alreadyOff", new Object[0]);
      }
   }
}
