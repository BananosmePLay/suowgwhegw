package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class Dc extends Ch {
   public Dc() {
   }

   public String getName() {
      return "xp";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.xp.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length <= 0) {
         throw new Ej("commands.xp.usage", new Object[0]);
      } else {
         String s = args[0];
         boolean flag = s.endsWith("l") || s.endsWith("L");
         if (flag && s.length() > 1) {
            s = s.substring(0, s.length() - 1);
         }

         int i = parseInt(s);
         boolean flag1 = i < 0;
         if (flag1) {
            i *= -1;
         }

         ME entityplayer = args.length > 1 ? getPlayer(server, sender, args[1]) : getCommandSenderAsPlayer(sender);
         if (flag) {
            sender.setCommandStat(CK.QUERY_RESULT, entityplayer.experienceLevel);
            if (flag1) {
               ((ME)entityplayer).addExperienceLevel(-i);
               notifyCommandListener(sender, this, "commands.xp.success.negative.levels", new Object[]{i, ((ME)entityplayer).getName()});
            } else {
               ((ME)entityplayer).addExperienceLevel(i);
               notifyCommandListener(sender, this, "commands.xp.success.levels", new Object[]{i, ((ME)entityplayer).getName()});
            }
         } else {
            sender.setCommandStat(CK.QUERY_RESULT, entityplayer.experienceTotal);
            if (flag1) {
               throw new Ct("commands.xp.failure.widthdrawXp", new Object[0]);
            }

            ((ME)entityplayer).addExperience(i);
            notifyCommandListener(sender, this, "commands.xp.success", new Object[]{i, ((ME)entityplayer).getName()});
         }

      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 2 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 1;
   }
}
