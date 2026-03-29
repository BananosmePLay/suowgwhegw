package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class CV extends Ch {
   public CV() {
   }

   public String getName() {
      return "time";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.time.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length > 1) {
         int i;
         if ("set".equals(args[0])) {
            if ("day".equals(args[1])) {
               i = 1000;
            } else if ("night".equals(args[1])) {
               i = 13000;
            } else {
               i = parseInt(args[1], 0);
            }

            this.setAllWorldTimes(server, i);
            notifyCommandListener(sender, this, "commands.time.set", new Object[]{i});
            return;
         }

         if ("add".equals(args[0])) {
            i = parseInt(args[1], 0);
            this.incrementAllWorldTimes(server, i);
            notifyCommandListener(sender, this, "commands.time.added", new Object[]{i});
            return;
         }

         if ("query".equals(args[0])) {
            if ("daytime".equals(args[1])) {
               i = (int)(sender.getEntityWorld().getWorldTime() % 24000L);
               sender.setCommandStat(CK.QUERY_RESULT, i);
               notifyCommandListener(sender, this, "commands.time.query", new Object[]{i});
               return;
            }

            if ("day".equals(args[1])) {
               i = (int)(sender.getEntityWorld().getWorldTime() / 24000L % 2147483647L);
               sender.setCommandStat(CK.QUERY_RESULT, i);
               notifyCommandListener(sender, this, "commands.time.query", new Object[]{i});
               return;
            }

            if ("gametime".equals(args[1])) {
               i = (int)(sender.getEntityWorld().getTotalWorldTime() % 2147483647L);
               sender.setCommandStat(CK.QUERY_RESULT, i);
               notifyCommandListener(sender, this, "commands.time.query", new Object[]{i});
               return;
            }
         }
      }

      throw new Ej("commands.time.usage", new Object[0]);
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"set", "add", "query"});
      } else if (args.length == 2 && "set".equals(args[0])) {
         return getListOfStringsMatchingLastWord(args, new String[]{"day", "night"});
      } else {
         return args.length == 2 && "query".equals(args[0]) ? getListOfStringsMatchingLastWord(args, new String[]{"daytime", "gametime", "day"}) : Collections.emptyList();
      }
   }

   protected void setAllWorldTimes(Xx server, int time) {
      for(int i = 0; i < server.worlds.length; ++i) {
         server.worlds[i].setWorldTime((long)time);
      }

   }

   protected void incrementAllWorldTimes(Xx server, int amount) {
      for(int i = 0; i < server.worlds.length; ++i) {
         bis worldserver = server.worlds[i];
         worldserver.setWorldTime(worldserver.getWorldTime() + (long)amount);
      }

   }
}
