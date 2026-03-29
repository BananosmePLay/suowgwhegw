package neo;

public class DW extends Ch {
   public DW() {
   }

   public String getName() {
      return "save-on";
   }

   public String getUsage(DB sender) {
      return "commands.save-on.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      boolean flag = false;

      for(int i = 0; i < server.worlds.length; ++i) {
         if (server.worlds[i] != null) {
            bis worldserver = server.worlds[i];
            if (worldserver.disableLevelSaving) {
               worldserver.disableLevelSaving = false;
               flag = true;
            }
         }
      }

      if (flag) {
         notifyCommandListener(sender, this, "commands.save.enabled", new Object[0]);
      } else {
         throw new Ct("commands.save-on.alreadyOn", new Object[0]);
      }
   }
}
