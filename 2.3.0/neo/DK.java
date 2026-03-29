package neo;

import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class DK extends Ch {
   public DK() {
   }

   public String getName() {
      return "deop";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getUsage(DB sender) {
      return "commands.deop.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length == 1 && args[0].length() > 0) {
         GameProfile gameprofile = server.getPlayerList().getOppedPlayers().getGameProfileFromName(args[0]);
         if (gameprofile == null) {
            throw new Ct("commands.deop.failed", new Object[]{args[0]});
         } else {
            server.getPlayerList().removeOp(gameprofile);
            notifyCommandListener(sender, this, "commands.deop.success", new Object[]{args[0]});
         }
      } else {
         throw new Ej("commands.deop.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getPlayerList().getOppedPlayerNames()) : Collections.emptyList();
   }
}
