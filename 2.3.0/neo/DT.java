package neo;

public class DT extends Ch {
   public DT() {
   }

   public String getName() {
      return "publish";
   }

   public String getUsage(DB sender) {
      return "commands.publish.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      String s = server.shareToLAN(bbb.SURVIVAL, false);
      if (s != null) {
         notifyCommandListener(sender, this, "commands.publish.started", new Object[]{s});
      } else {
         notifyCommandListener(sender, this, "commands.publish.failed", new Object[0]);
      }

   }
}
