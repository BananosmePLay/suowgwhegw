package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;

public class DY extends Ch {
   public DY() {
   }

   public String getName() {
      return "setblock";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.setblock.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 4) {
         throw new Ej("commands.setblock.usage", new Object[0]);
      } else {
         sender.setCommandStat(CK.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = parseBlockPos(sender, args, 0, false);
         co block = Ch.getBlockByText(sender, args[3]);
         in iblockstate;
         if (args.length >= 5) {
            iblockstate = convertArgToBlockState(block, args[4]);
         } else {
            iblockstate = block.getDefaultState();
         }

         bij world = sender.getEntityWorld();
         if (!world.isBlockLoaded(blockpos)) {
            throw new Ct("commands.setblock.outOfWorld", new Object[0]);
         } else {
            QQ nbttagcompound = new QQ();
            boolean flag = false;
            if (args.length >= 7 && block.hasTileEntity()) {
               String s = buildString(args, 6);

               try {
                  nbttagcompound = QG.getTagFromJson(s);
                  flag = true;
               } catch (QI var12) {
                  QI nbtexception = var12;
                  throw new Ct("commands.setblock.tagError", new Object[]{nbtexception.getMessage()});
               }
            }

            if (args.length >= 6) {
               if ("destroy".equals(args[5])) {
                  world.destroyBlock(blockpos, true);
                  if (block == Nk.AIR) {
                     notifyCommandListener(sender, this, "commands.setblock.success", new Object[0]);
                     return;
                  }
               } else if ("keep".equals(args[5]) && !world.isAirBlock(blockpos)) {
                  throw new Ct("commands.setblock.noChange", new Object[0]);
               }
            }

            Yg tileentity1 = world.getTileEntity(blockpos);
            if (tileentity1 != null && tileentity1 instanceof IInventory) {
               ((IInventory)tileentity1).clear();
            }

            if (!world.setBlockState(blockpos, iblockstate, 2)) {
               throw new Ct("commands.setblock.noChange", new Object[0]);
            } else {
               if (flag) {
                  Yg tileentity = world.getTileEntity(blockpos);
                  if (tileentity != null) {
                     nbttagcompound.setInteger("x", blockpos.getX());
                     nbttagcompound.setInteger("y", blockpos.getY());
                     nbttagcompound.setInteger("z", blockpos.getZ());
                     tileentity.readFromNBT(nbttagcompound);
                  }
               }

               world.notifyNeighborsRespectDebug(blockpos, iblockstate.getBlock(), false);
               sender.setCommandStat(CK.AFFECTED_BLOCKS, 1);
               notifyCommandListener(sender, this, "commands.setblock.success", new Object[0]);
            }
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length > 0 && args.length <= 3) {
         return getTabCompletionCoordinate(args, 0, targetPos);
      } else if (args.length == 4) {
         return getListOfStringsMatchingLastWord(args, co.REGISTRY.getKeys());
      } else {
         return args.length == 6 ? getListOfStringsMatchingLastWord(args, new String[]{"replace", "destroy", "keep"}) : Collections.emptyList();
      }
   }
}
