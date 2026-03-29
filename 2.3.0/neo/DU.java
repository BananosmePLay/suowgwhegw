package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;

public class DU extends Ch {
   public DU() {
   }

   public String getName() {
      return "save-all";
   }

   public String getUsage(DB sender) {
      return "commands.save.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      sender.sendMessage(new TextComponentTranslation("commands.save.start", new Object[0]));
      if (server.getPlayerList() != null) {
         server.getPlayerList().saveAllPlayerData();
      }

      try {
         bis worldserver1;
         boolean flag1;
         int j;
         for(j = 0; j < server.worlds.length; ++j) {
            if (server.worlds[j] != null) {
               worldserver1 = server.worlds[j];
               flag1 = worldserver1.disableLevelSaving;
               worldserver1.disableLevelSaving = false;
               worldserver1.saveAllChunks(true, (IProgressUpdate)null);
               worldserver1.disableLevelSaving = flag1;
            }
         }

         if (args.length > 0 && "flush".equals(args[0])) {
            sender.sendMessage(new TextComponentTranslation("commands.save.flushStart", new Object[0]));

            for(j = 0; j < server.worlds.length; ++j) {
               if (server.worlds[j] != null) {
                  worldserver1 = server.worlds[j];
                  flag1 = worldserver1.disableLevelSaving;
                  worldserver1.disableLevelSaving = false;
                  worldserver1.flushToDisk();
                  worldserver1.disableLevelSaving = flag1;
               }
            }

            sender.sendMessage(new TextComponentTranslation("commands.save.flushEnd", new Object[0]));
         }
      } catch (bgf var7) {
         bgf minecraftexception = var7;
         notifyCommandListener(sender, this, "commands.save.failed", new Object[]{minecraftexception.getMessage()});
         return;
      }

      notifyCommandListener(sender, this, "commands.save.success", new Object[0]);
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, new String[]{"flush"}) : Collections.emptyList();
   }
}
