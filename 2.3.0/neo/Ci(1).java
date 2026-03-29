package neo;

import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.math.BlockPos;

public class Ci extends Ch {
   public Ci() {
   }

   public String getName() {
      return "blockdata";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.blockdata.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 4) {
         throw new Ej("commands.blockdata.usage", new Object[0]);
      } else {
         sender.setCommandStat(CK.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = parseBlockPos(sender, args, 0, false);
         bij world = sender.getEntityWorld();
         if (!world.isBlockLoaded(blockpos)) {
            throw new Ct("commands.blockdata.outOfWorld", new Object[0]);
         } else {
            in iblockstate = world.getBlockState(blockpos);
            Yg tileentity = world.getTileEntity(blockpos);
            if (tileentity == null) {
               throw new Ct("commands.blockdata.notValid", new Object[0]);
            } else {
               QQ nbttagcompound = tileentity.writeToNBT(new QQ());
               QQ nbttagcompound1 = nbttagcompound.copy();

               QQ nbttagcompound2;
               try {
                  nbttagcompound2 = QG.getTagFromJson(buildString(args, 3));
               } catch (QI var12) {
                  QI nbtexception = var12;
                  throw new Ct("commands.blockdata.tagError", new Object[]{nbtexception.getMessage()});
               }

               nbttagcompound.merge(nbttagcompound2);
               nbttagcompound.setInteger("x", blockpos.getX());
               nbttagcompound.setInteger("y", blockpos.getY());
               nbttagcompound.setInteger("z", blockpos.getZ());
               if (nbttagcompound.equals(nbttagcompound1)) {
                  throw new Ct("commands.blockdata.failed", new Object[]{nbttagcompound.toString()});
               } else {
                  tileentity.readFromNBT(nbttagcompound);
                  tileentity.markDirty();
                  world.notifyBlockUpdate(blockpos, iblockstate, iblockstate, 3);
                  sender.setCommandStat(CK.AFFECTED_BLOCKS, 1);
                  notifyCommandListener(sender, this, "commands.blockdata.success", new Object[]{nbttagcompound.toString()});
               }
            }
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      return args.length > 0 && args.length <= 3 ? getTabCompletionCoordinate(args, 0, targetPos) : Collections.emptyList();
   }
}
