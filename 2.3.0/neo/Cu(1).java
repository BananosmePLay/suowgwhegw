package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class Cu extends Ch {
   public Cu() {
   }

   public String getName() {
      return "execute";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.execute.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 5) {
         throw new Ej("commands.execute.usage", new Object[0]);
      } else {
         Ig entity = getEntity(server, sender, args[0], Ig.class);
         double d0 = parseDouble(entity.posX, args[1], false);
         double d1 = parseDouble(entity.posY, args[2], false);
         double d2 = parseDouble(entity.posZ, args[3], false);
         new BlockPos(d0, d1, d2);
         int i = 4;
         if ("detect".equals(args[4]) && args.length > 10) {
            bij world = entity.getEntityWorld();
            double d3 = parseDouble(d0, args[5], false);
            double d4 = parseDouble(d1, args[6], false);
            double d5 = parseDouble(d2, args[7], false);
            co block = getBlockByText(sender, args[8]);
            BlockPos blockpos = new BlockPos(d3, d4, d5);
            if (!world.isBlockLoaded(blockpos)) {
               throw new Ct("commands.execute.failed", new Object[]{"detect", entity.getName()});
            }

            in iblockstate = world.getBlockState(blockpos);
            if (iblockstate.getBlock() != block) {
               throw new Ct("commands.execute.failed", new Object[]{"detect", entity.getName()});
            }

            if (!Ch.convertArgToBlockStatePredicate(block, args[9]).apply(iblockstate)) {
               throw new Ct("commands.execute.failed", new Object[]{"detect", entity.getName()});
            }

            i = 10;
         }

         String s = buildString(args, i);
         DB icommandsender = CM.create(sender).withEntity(entity, new Vec3d(d0, d1, d2)).withSendCommandFeedback(server.worlds[0].getGameRules().getBoolean("commandBlockOutput"));
         DA icommandmanager = server.getCommandManager();

         try {
            int j = icommandmanager.executeCommand(icommandsender, s);
            if (j < 1) {
               throw new Ct("commands.execute.allInvocationsFailed", new Object[]{s});
            }
         } catch (Throwable var22) {
            throw new Ct("commands.execute.failed", new Object[]{s, entity.getName()});
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else if (args.length > 1 && args.length <= 4) {
         return getTabCompletionCoordinate(args, 1, targetPos);
      } else if (args.length > 5 && args.length <= 8 && "detect".equals(args[4])) {
         return getTabCompletionCoordinate(args, 5, targetPos);
      } else {
         return args.length == 9 && "detect".equals(args[4]) ? getListOfStringsMatchingLastWord(args, co.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return index == 0;
   }
}
