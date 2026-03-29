package neo;

import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class DN extends Ch {
   public DN() {
   }

   public String getName() {
      return "list";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getUsage(DB sender) {
      return "commands.players.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      int i = server.getCurrentPlayerCount();
      sender.sendMessage(new TextComponentTranslation("commands.players.list", new Object[]{i, server.getMaxPlayers()}));
      sender.sendMessage(new TextComponentString(server.getPlayerList().getFormattedListOfPlayers(args.length > 0 && "uuids".equalsIgnoreCase(args[0]))));
      sender.setCommandStat(CK.QUERY_RESULT, i);
   }
}
