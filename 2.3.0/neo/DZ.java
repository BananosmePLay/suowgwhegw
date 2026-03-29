package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class DZ extends Ch {
   public DZ() {
   }

   public String getName() {
      return "setworldspawn";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.setworldspawn.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      BlockPos blockpos;
      if (args.length == 0) {
         blockpos = getCommandSenderAsPlayer(sender).getPosition();
      } else {
         if (args.length != 3 || sender.getEntityWorld() == null) {
            throw new Ej("commands.setworldspawn.usage", new Object[0]);
         }

         blockpos = parseBlockPos(sender, args, 0, true);
      }

      sender.getEntityWorld().setSpawnPoint(blockpos);
      server.getPlayerList().sendPacketToAllPlayers(new US(blockpos));
      notifyCommandListener(sender, this, "commands.setworldspawn.success", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ()});
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length > 0 && args.length <= 3 ? getTabCompletionCoordinate(args, 0, targetPos) : Collections.emptyList();
   }
}
