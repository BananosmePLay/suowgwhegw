package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class CZ extends Ch {
   public CZ() {
   }

   public String getName() {
      return "trigger";
   }

   public int getRequiredPermissionLevel() {
      return 0;
   }

   public String getUsage(DB sender) {
      return "commands.trigger.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 3) {
         throw new Ej("commands.trigger.usage", new Object[0]);
      } else {
         MG entityplayermp;
         if (sender instanceof MG) {
            entityplayermp = (MG)sender;
         } else {
            Ig entity = sender.getCommandSenderEntity();
            if (!(entity instanceof MG)) {
               throw new Ct("commands.trigger.invalidPlayer", new Object[0]);
            }

            entityplayermp = (MG)entity;
         }

         Ws scoreboard = server.getWorld(0).getScoreboard();
         Wz scoreobjective = scoreboard.getObjective(args[0]);
         if (scoreobjective != null && scoreobjective.getCriteria() == Wo.TRIGGER) {
            int i = parseInt(args[2]);
            if (!scoreboard.entityHasObjective(entityplayermp.getName(), scoreobjective)) {
               throw new Ct("commands.trigger.invalidObjective", new Object[]{args[0]});
            } else {
               Wr score = scoreboard.getOrCreateScore(entityplayermp.getName(), scoreobjective);
               if (score.isLocked()) {
                  throw new Ct("commands.trigger.disabled", new Object[]{args[0]});
               } else {
                  if ("set".equals(args[1])) {
                     score.setScorePoints(i);
                  } else {
                     if (!"add".equals(args[1])) {
                        throw new Ct("commands.trigger.invalidMode", new Object[]{args[1]});
                     }

                     score.increaseScore(i);
                  }

                  score.setLocked(true);
                  if (entityplayermp.interactionManager.isCreative()) {
                     notifyCommandListener(sender, this, "commands.trigger.success", new Object[]{args[0], args[1], args[2]});
                  }

               }
            }
         } else {
            throw new Ct("commands.trigger.invalidObjective", new Object[]{args[0]});
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         Ws scoreboard = server.getWorld(0).getScoreboard();
         List<String> list = Lists.newArrayList();
         Iterator var7 = scoreboard.getScoreObjectives().iterator();

         while(var7.hasNext()) {
            Wz scoreobjective = (Wz)var7.next();
            if (scoreobjective.getCriteria() == Wo.TRIGGER) {
               list.add(scoreobjective.getName());
            }
         }

         return getListOfStringsMatchingLastWord(args, (String[])((String[])list.toArray(new String[list.size()])));
      } else {
         return args.length == 2 ? getListOfStringsMatchingLastWord(args, new String[]{"add", "set"}) : Collections.emptyList();
      }
   }
}
