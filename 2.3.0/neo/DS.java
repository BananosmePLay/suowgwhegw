package neo;

import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class DS extends Ch {
   public DS() {
   }

   public String getName() {
      return "pardon";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getUsage(DB sender) {
      return "commands.unban.usage";
   }

   public boolean checkPermission(Xx server, DB sender) {
      return server.getPlayerList().getBannedPlayers().isLanServer() && super.checkPermission(server, sender);
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length == 1 && args[0].length() > 0) {
         GameProfile gameprofile = server.getPlayerList().getBannedPlayers().getBannedProfile(args[0]);
         if (gameprofile == null) {
            throw new Ct("commands.unban.failed", new Object[]{args[0]});
         } else {
            server.getPlayerList().getBannedPlayers().removeEntry(gameprofile);
            notifyCommandListener(sender, this, "commands.unban.success", new Object[]{args[0]});
         }
      } else {
         throw new Ej("commands.unban.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getPlayerList().getBannedPlayers().getKeys()) : Collections.emptyList();
   }
}
