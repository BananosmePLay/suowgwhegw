package neo;

import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class DR extends Ch {
   public DR() {
   }

   public String getName() {
      return "pardon-ip";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public boolean checkPermission(Xx server, DB sender) {
      return server.getPlayerList().getBannedIPs().isLanServer() && super.checkPermission(server, sender);
   }

   public String getUsage(DB sender) {
      return "commands.unbanip.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length == 1 && args[0].length() > 1) {
         Matcher matcher = DH.IP_PATTERN.matcher(args[0]);
         if (matcher.matches()) {
            server.getPlayerList().getBannedIPs().removeEntry(args[0]);
            notifyCommandListener(sender, this, "commands.unbanip.success", new Object[]{args[0]});
         } else {
            throw new Ei("commands.unbanip.invalid", new Object[0]);
         }
      } else {
         throw new Ej("commands.unbanip.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getPlayerList().getBannedIPs().getKeys()) : Collections.emptyList();
   }
}
