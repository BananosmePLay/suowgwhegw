package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class CC extends Ch {
   public CC() {
   }

   public String getName() {
      return "kill";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.kill.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length == 0) {
         ME entityplayer = getCommandSenderAsPlayer(sender);
         ((ME)entityplayer).onKillCommand();
         notifyCommandListener(sender, this, "commands.kill.successful", new Object[]{((ME)entityplayer).getDisplayName()});
      } else {
         Ig entity = getEntity(server, sender, args[0]);
         entity.onKillCommand();
         notifyCommandListener(sender, this, "commands.kill.successful", new Object[]{entity.getDisplayName()});
      }

   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }
}
