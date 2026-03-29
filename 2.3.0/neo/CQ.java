package neo;

import net.minecraft.util.text.TextComponentTranslation;

public class CQ extends Ch {
   public CQ() {
   }

   public boolean checkPermission(Xx server, DB sender) {
      return server.isSinglePlayer() || super.checkPermission(server, sender);
   }

   public String getName() {
      return "seed";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.seed.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      bij world = sender instanceof ME ? ((ME)sender).world : server.getWorld(0);
      sender.sendMessage(new TextComponentTranslation("commands.seed.success", new Object[]{((bij)world).getSeed()}));
   }
}
