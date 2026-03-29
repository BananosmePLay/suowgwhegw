package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class Ee extends Ch {
   public Ee() {
   }

   public String getName() {
      return "testforblock";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.testforblock.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 4) {
         throw new Ej("commands.testforblock.usage", new Object[0]);
      } else {
         sender.setCommandStat(CK.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = parseBlockPos(sender, args, 0, false);
         co block = getBlockByText(sender, args[3]);
         if (block == null) {
            throw new DD("commands.setblock.notFound", new Object[]{args[3]});
         } else {
            bij world = sender.getEntityWorld();
            if (!world.isBlockLoaded(blockpos)) {
               throw new Ct("commands.testforblock.outOfWorld", new Object[0]);
            } else {
               QQ nbttagcompound = new QQ();
               boolean flag = false;
               if (args.length >= 6 && block.hasTileEntity()) {
                  String s = buildString(args, 5);

                  try {
                     nbttagcompound = QG.getTagFromJson(s);
                     flag = true;
                  } catch (QI var14) {
                     QI nbtexception = var14;
                     throw new Ct("commands.setblock.tagError", new Object[]{nbtexception.getMessage()});
                  }
               }

               in iblockstate = world.getBlockState(blockpos);
               co block1 = iblockstate.getBlock();
               if (block1 != block) {
                  throw new Ct("commands.testforblock.failed.tile", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ(), block1.getLocalizedName(), block.getLocalizedName()});
               } else if (args.length >= 5 && !Ch.convertArgToBlockStatePredicate(block, args[4]).apply(iblockstate)) {
                  try {
                     int i = iblockstate.getBlock().getMetaFromState(iblockstate);
                     throw new Ct("commands.testforblock.failed.data", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ(), i, Integer.parseInt(args[4])});
                  } catch (NumberFormatException var13) {
                     throw new Ct("commands.testforblock.failed.data", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ(), iblockstate.toString(), args[4]});
                  }
               } else {
                  if (flag) {
                     Yg tileentity = world.getTileEntity(blockpos);
                     if (tileentity == null) {
                        throw new Ct("commands.testforblock.failed.tileEntity", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ()});
                     }

                     QQ nbttagcompound1 = tileentity.writeToNBT(new QQ());
                     if (!Rb.areNBTEquals(nbttagcompound, nbttagcompound1, true)) {
                        throw new Ct("commands.testforblock.failed.nbt", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ()});
                     }
                  }

                  sender.setCommandStat(CK.AFFECTED_BLOCKS, 1);
                  notifyCommandListener(sender, this, "commands.testforblock.success", new Object[]{blockpos.getX(), blockpos.getY(), blockpos.getZ()});
               }
            }
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length > 0 && args.length <= 3) {
         return getTabCompletionCoordinate(args, 0, targetPos);
      } else {
         return args.length == 4 ? getListOfStringsMatchingLastWord(args, co.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }
}
