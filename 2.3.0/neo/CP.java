package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class CP extends Ch {
   public CP() {
   }

   public String getName() {
      return "spawnpoint";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.spawnpoint.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length > 1 && args.length < 4) {
         throw new Ej("commands.spawnpoint.usage", new Object[0]);
      } else {
         MG entityplayermp = args.length > 0 ? getPlayer(server, sender, args[0]) : getCommandSenderAsPlayer(sender);
         BlockPos blockpos = args.length > 3 ? parseBlockPos(sender, args, 1, true) : entityplayermp.getPosition();
         if (entityplayermp.world != null) {
            entityplayermp.setSpawnPoint(blockpos, true);
            notifyCommandListener(sender, this, "commands.spawnpoint.success", new Object[]{entityplayermp.getName(), blockpos.getX(), blockpos.getY(), blockpos.getZ()});
         }

      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else {
         return args.length > 1 && args.length <= 4 ? getTabCompletionCoordinate(args, 1, targetPos) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
