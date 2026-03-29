package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;

public class Cv extends Ch {
   public Cv() {
   }

   public String getName() {
      return "fill";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.fill.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 7) {
         throw new Ej("commands.fill.usage", new Object[0]);
      } else {
         sender.setCommandStat(CK.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = parseBlockPos(sender, args, 0, false);
         BlockPos blockpos1 = parseBlockPos(sender, args, 3, false);
         co block = Ch.getBlockByText(sender, args[6]);
         in iblockstate;
         if (args.length >= 8) {
            iblockstate = convertArgToBlockState(block, args[7]);
         } else {
            iblockstate = block.getDefaultState();
         }

         BlockPos blockpos2 = new BlockPos(Math.min(blockpos.getX(), blockpos1.getX()), Math.min(blockpos.getY(), blockpos1.getY()), Math.min(blockpos.getZ(), blockpos1.getZ()));
         BlockPos blockpos3 = new BlockPos(Math.max(blockpos.getX(), blockpos1.getX()), Math.max(blockpos.getY(), blockpos1.getY()), Math.max(blockpos.getZ(), blockpos1.getZ()));
         int i = (blockpos3.getX() - blockpos2.getX() + 1) * (blockpos3.getY() - blockpos2.getY() + 1) * (blockpos3.getZ() - blockpos2.getZ() + 1);
         if (i > 32768) {
            throw new Ct("commands.fill.tooManyBlocks", new Object[]{i, 32768});
         } else if (blockpos2.getY() >= 0 && blockpos3.getY() < 256) {
            bij world = sender.getEntityWorld();

            for(int j = blockpos2.getZ(); j <= blockpos3.getZ(); j += 16) {
               for(int k = blockpos2.getX(); k <= blockpos3.getX(); k += 16) {
                  if (!world.isBlockLoaded(new BlockPos(k, blockpos3.getY() - blockpos2.getY(), j))) {
                     throw new Ct("commands.fill.outOfWorld", new Object[0]);
                  }
               }
            }

            QQ nbttagcompound = new QQ();
            boolean flag = false;
            if (args.length >= 10 && block.hasTileEntity()) {
               String s = buildString(args, 9);

               try {
                  nbttagcompound = QG.getTagFromJson(s);
                  flag = true;
               } catch (QI var21) {
                  QI nbtexception = var21;
                  throw new Ct("commands.fill.tagError", new Object[]{nbtexception.getMessage()});
               }
            }

            List<BlockPos> list = Lists.newArrayList();
            i = 0;

            for(int l = blockpos2.getZ(); l <= blockpos3.getZ(); ++l) {
               for(int i1 = blockpos2.getY(); i1 <= blockpos3.getY(); ++i1) {
                  for(int j1 = blockpos2.getX(); j1 <= blockpos3.getX(); ++j1) {
                     BlockPos blockpos4 = new BlockPos(j1, i1, l);
                     if (args.length >= 9) {
                        if (!"outline".equals(args[8]) && !"hollow".equals(args[8])) {
                           if ("destroy".equals(args[8])) {
                              world.destroyBlock(blockpos4, true);
                           } else if ("keep".equals(args[8])) {
                              if (!world.isAirBlock(blockpos4)) {
                                 continue;
                              }
                           } else if ("replace".equals(args[8]) && !block.hasTileEntity() && args.length > 9) {
                              co block1 = Ch.getBlockByText(sender, args[9]);
                              if (world.getBlockState(blockpos4).getBlock() != block1 || args.length > 10 && !"-1".equals(args[10]) && !"*".equals(args[10]) && !Ch.convertArgToBlockStatePredicate(block1, args[10]).apply(world.getBlockState(blockpos4))) {
                                 continue;
                              }
                           }
                        } else if (j1 != blockpos2.getX() && j1 != blockpos3.getX() && i1 != blockpos2.getY() && i1 != blockpos3.getY() && l != blockpos2.getZ() && l != blockpos3.getZ()) {
                           if ("hollow".equals(args[8])) {
                              world.setBlockState(blockpos4, Nk.AIR.getDefaultState(), 2);
                              list.add(blockpos4);
                           }
                           continue;
                        }
                     }

                     Yg tileentity1 = world.getTileEntity(blockpos4);
                     if (tileentity1 != null && tileentity1 instanceof IInventory) {
                        ((IInventory)tileentity1).clear();
                     }

                     if (world.setBlockState(blockpos4, iblockstate, 2)) {
                        list.add(blockpos4);
                        ++i;
                        if (flag) {
                           Yg tileentity = world.getTileEntity(blockpos4);
                           if (tileentity != null) {
                              nbttagcompound.setInteger("x", blockpos4.getX());
                              nbttagcompound.setInteger("y", blockpos4.getY());
                              nbttagcompound.setInteger("z", blockpos4.getZ());
                              tileentity.readFromNBT(nbttagcompound);
                           }
                        }
                     }
                  }
               }
            }

            Iterator var26 = list.iterator();

            while(var26.hasNext()) {
               BlockPos blockpos5 = (BlockPos)var26.next();
               co block2 = world.getBlockState(blockpos5).getBlock();
               world.notifyNeighborsRespectDebug(blockpos5, block2, false);
            }

            if (i <= 0) {
               throw new Ct("commands.fill.failed", new Object[0]);
            } else {
               sender.setCommandStat(CK.AFFECTED_BLOCKS, i);
               notifyCommandListener(sender, this, "commands.fill.success", new Object[]{i});
            }
         } else {
            throw new Ct("commands.fill.outOfWorld", new Object[0]);
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length > 0 && args.length <= 3) {
         return getTabCompletionCoordinate(args, 0, targetPos);
      } else if (args.length > 3 && args.length <= 6) {
         return getTabCompletionCoordinate(args, 3, targetPos);
      } else if (args.length == 7) {
         return getListOfStringsMatchingLastWord(args, co.REGISTRY.getKeys());
      } else if (args.length == 9) {
         return getListOfStringsMatchingLastWord(args, new String[]{"replace", "destroy", "keep", "hollow", "outline"});
      } else {
         return args.length == 10 && "replace".equals(args[8]) ? getListOfStringsMatchingLastWord(args, co.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }
}
