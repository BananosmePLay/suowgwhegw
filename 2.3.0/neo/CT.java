package neo;

import com.google.common.collect.Lists;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class CT extends Ch {
   public CT() {
   }

   public String getName() {
      return "stats";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.stats.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 1) {
         throw new Ej("commands.stats.usage", new Object[0]);
      } else {
         boolean flag;
         if ("entity".equals(args[0])) {
            flag = false;
         } else {
            if (!"block".equals(args[0])) {
               throw new Ej("commands.stats.usage", new Object[0]);
            }

            flag = true;
         }

         int i;
         if (flag) {
            if (args.length < 5) {
               throw new Ej("commands.stats.block.usage", new Object[0]);
            }

            i = 4;
         } else {
            if (args.length < 3) {
               throw new Ej("commands.stats.entity.usage", new Object[0]);
            }

            i = 2;
         }

         String s = args[i++];
         if ("set".equals(s)) {
            if (args.length < i + 3) {
               if (i == 5) {
                  throw new Ej("commands.stats.block.set.usage", new Object[0]);
               }

               throw new Ej("commands.stats.entity.set.usage", new Object[0]);
            }
         } else {
            if (!"clear".equals(s)) {
               throw new Ej("commands.stats.usage", new Object[0]);
            }

            if (args.length < i + 1) {
               if (i == 5) {
                  throw new Ej("commands.stats.block.clear.usage", new Object[0]);
               }

               throw new Ej("commands.stats.entity.clear.usage", new Object[0]);
            }
         }

         CK commandresultstats$type = CK.getTypeByName(args[i++]);
         if (commandresultstats$type == null) {
            throw new Ct("commands.stats.failed", new Object[0]);
         } else {
            bij world = sender.getEntityWorld();
            CL commandresultstats;
            BlockPos blockpos1;
            Yg tileentity1;
            if (flag) {
               blockpos1 = parseBlockPos(sender, args, 1, false);
               tileentity1 = world.getTileEntity(blockpos1);
               if (tileentity1 == null) {
                  throw new Ct("commands.stats.noCompatibleBlock", new Object[]{blockpos1.getX(), blockpos1.getY(), blockpos1.getZ()});
               }

               if (tileentity1 instanceof Yq) {
                  commandresultstats = ((Yq)tileentity1).getCommandResultStats();
               } else {
                  if (!(tileentity1 instanceof YQ)) {
                     throw new Ct("commands.stats.noCompatibleBlock", new Object[]{blockpos1.getX(), blockpos1.getY(), blockpos1.getZ()});
                  }

                  commandresultstats = ((YQ)tileentity1).getStats();
               }
            } else {
               Ig entity = getEntity(server, sender, args[1]);
               commandresultstats = entity.getCommandStats();
            }

            if ("set".equals(s)) {
               String s1 = args[i++];
               String s2 = args[i];
               if (s1.isEmpty() || s2.isEmpty()) {
                  throw new Ct("commands.stats.failed", new Object[0]);
               }

               CL.setScoreBoardStat(commandresultstats, commandresultstats$type, s1, s2);
               notifyCommandListener(sender, this, "commands.stats.success", new Object[]{commandresultstats$type.getTypeName(), s2, s1});
            } else if ("clear".equals(s)) {
               CL.setScoreBoardStat(commandresultstats, commandresultstats$type, (String)null, (String)null);
               notifyCommandListener(sender, this, "commands.stats.cleared", new Object[]{commandresultstats$type.getTypeName()});
            }

            if (flag) {
               blockpos1 = parseBlockPos(sender, args, 1, false);
               tileentity1 = world.getTileEntity(blockpos1);
               tileentity1.markDirty();
            }

         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length == 1) {
         return getListOfStringsMatchingLastWord(args, new String[]{"entity", "block"});
      } else if (args.length == 2 && "entity".equals(args[0])) {
         return getListOfStringsMatchingLastWord(args, server.getOnlinePlayerNames());
      } else if (args.length >= 2 && args.length <= 4 && "block".equals(args[0])) {
         return getTabCompletionCoordinate(args, 1, targetPos);
      } else if (args.length == 3 && "entity".equals(args[0]) || args.length == 5 && "block".equals(args[0])) {
         return getListOfStringsMatchingLastWord(args, new String[]{"set", "clear"});
      } else if ((args.length != 4 || !"entity".equals(args[0])) && (args.length != 6 || !"block".equals(args[0]))) {
         return args.length == 6 && "entity".equals(args[0]) || args.length == 8 && "block".equals(args[0]) ? getListOfStringsMatchingLastWord(args, this.getObjectiveNames(server)) : Collections.emptyList();
      } else {
         return getListOfStringsMatchingLastWord(args, CK.getTypeNames());
      }
   }

   protected List<String> getObjectiveNames(Xx server) {
      Collection<Wz> collection = server.getWorld(0).getScoreboard().getScoreObjectives();
      List<String> list = Lists.newArrayList();
      Iterator var4 = collection.iterator();

      while(var4.hasNext()) {
         Wz scoreobjective = (Wz)var4.next();
         if (!scoreobjective.getCriteria().isReadOnly()) {
            list.add(scoreobjective.getName());
         }
      }

      return list;
   }

   public boolean isUsernameIndex(String[] args, int index) {
      return args.length > 0 && "entity".equals(args[0]) && index == 1;
   }
}
