package neo;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class DQ extends Ch {
   public DQ() {
   }

   public String getName() {
      return "op";
   }

   public int getRequiredPermissionLevel() {
      return 3;
   }

   public String getUsage(DB sender) {
      return "commands.op.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length == 1 && args[0].length() > 0) {
         GameProfile gameprofile = server.getPlayerProfileCache().getGameProfileForUsername(args[0]);
         if (gameprofile == null) {
            throw new Ct("commands.op.failed", new Object[]{args[0]});
         } else {
            server.getPlayerList().addOp(gameprofile);
            notifyCommandListener(sender, this, "commands.op.success", new Object[]{args[0]});
         }
      } else {
         throw new Ej("commands.op.usage", new Object[0]);
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         String s = args[args.length - 1];
         List<String> list = Lists.newArrayList();
         GameProfile[] var7 = server.getOnlinePlayerProfiles();
         int var8 = var7.length;

         for(int var9 = 0; var9 < var8; ++var9) {
            GameProfile gameprofile = var7[var9];
            if (!server.getPlayerList().canSendCommands(gameprofile) && doesStringStartWith(s, gameprofile.getName())) {
               list.add(gameprofile.getName());
            }
         }

         return list;
      } else {
         return Collections.emptyList();
      }
   }
}
