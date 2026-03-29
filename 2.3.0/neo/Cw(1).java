package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;

public class Cw extends Ch {
   public Cw() {
   }

   public String getName() {
      return "function";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.function.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length != 1 && args.length != 3) {
         throw new Ej("commands.function.usage", new Object[0]);
      } else {
         ResourceLocation resourcelocation = new ResourceLocation(args[0]);
         Dx functionobject = server.getFunctionManager().getFunction(resourcelocation);
         if (functionobject == null) {
            throw new Ct("commands.function.unknown", new Object[]{resourcelocation});
         } else {
            if (args.length == 3) {
               String s = args[1];
               boolean flag;
               if ("if".equals(s)) {
                  flag = true;
               } else {
                  if (!"unless".equals(s)) {
                     throw new Ej("commands.function.usage", new Object[0]);
                  }

                  flag = false;
               }

               boolean flag1 = false;

               try {
                  flag1 = !getEntityList(server, sender, args[2]).isEmpty();
               } catch (Dd var10) {
               }

               if (flag != flag1) {
                  throw new Ct("commands.function.skipped", new Object[]{resourcelocation});
               }
            }

            int i = server.getFunctionManager().execute(functionobject, CM.create(sender).computePositionVector().withPermissionLevel(2).withSendCommandFeedback(false));
            notifyCommandListener(sender, this, "commands.function.success", new Object[]{resourcelocation, i});
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getFunctionManager().getFunctions().keySet());
      } else if (args.length == 2) {
         return getListOfStringsMatchingLastWord(args, new String[]{"if", "unless"});
      } else {
         return args.length == 3 ? getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames()) : Collections.emptyList();
      }
   }
}
