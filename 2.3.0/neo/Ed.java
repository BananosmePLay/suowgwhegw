package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class Ed extends Ch {
   public Ed() {
   }

   public String getName() {
      return "testfor";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.testfor.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 1) {
         throw new Ej("commands.testfor.usage", new Object[0]);
      } else {
         Ig entity = getEntity(server, sender, args[0]);
         QQ nbttagcompound = null;
         if (args.length >= 2) {
            try {
               nbttagcompound = QG.getTagFromJson(buildString(args, 1));
            } catch (QI var7) {
               QI nbtexception = var7;
               throw new Ct("commands.testfor.tagError", new Object[]{nbtexception.getMessage()});
            }
         }

         if (nbttagcompound != null) {
            QQ nbttagcompound1 = entityToNBT(entity);
            if (!Rb.areNBTEquals(nbttagcompound, nbttagcompound1, true)) {
               throw new Ct("commands.testfor.failure", new Object[]{entity.getName()});
            }
         }

         notifyCommandListener(sender, this, "commands.testfor.success", new Object[]{entity.getName()});
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length == 1 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
   }
}
