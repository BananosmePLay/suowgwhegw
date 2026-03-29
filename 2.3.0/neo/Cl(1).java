package neo;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.math.BlockPos;

public class Cl extends Ch {
   public Cl() {
   }

   public String getName() {
      return "clone";
   }

   public int getRequiredPermissionLevel() {
      return 2;
   }

   public String getUsage(DB sender) {
      return "commands.clone.usage";
   }

   public void execute(Xx server, DB sender, String[] args) throws Ct {
      if (args.length < 9) {
         throw new Ej("commands.clone.usage", new Object[0]);
      } else {
         sender.setCommandStat(CK.AFFECTED_BLOCKS, 0);
         BlockPos blockpos = parseBlockPos(sender, args, 0, false);
         BlockPos blockpos1 = parseBlockPos(sender, args, 3, false);
         BlockPos blockpos2 = parseBlockPos(sender, args, 6, false);
         bdy structureboundingbox = new bdy(blockpos, blockpos1);
         bdy structureboundingbox1 = new bdy(blockpos2, blockpos2.add(structureboundingbox.getLength()));
         int i = structureboundingbox.getXSize() * structureboundingbox.getYSize() * structureboundingbox.getZSize();
         if (i > 32768) {
            throw new Ct("commands.clone.tooManyBlocks", new Object[]{i, 32768});
         } else {
            boolean flag = false;
            co block = null;
            Predicate<in> predicate = null;
            if ((args.length < 11 || !"force".equals(args[10]) && !"move".equals(args[10])) && structureboundingbox.intersectsWith(structureboundingbox1)) {
               throw new Ct("commands.clone.noOverlap", new Object[0]);
            } else {
               if (args.length >= 11 && "move".equals(args[10])) {
                  flag = true;
               }

               if (structureboundingbox.minY >= 0 && structureboundingbox.maxY < 256 && structureboundingbox1.minY >= 0 && structureboundingbox1.maxY < 256) {
                  bij world = sender.getEntityWorld();
                  if (world.isAreaLoaded(structureboundingbox) && world.isAreaLoaded(structureboundingbox1)) {
                     boolean flag1 = false;
                     if (args.length >= 10) {
                        if ("masked".equals(args[9])) {
                           flag1 = true;
                        } else if ("filtered".equals(args[9])) {
                           if (args.length < 12) {
                              throw new Ej("commands.clone.usage", new Object[0]);
                           }

                           block = getBlockByText(sender, args[11]);
                           if (args.length >= 13) {
                              predicate = convertArgToBlockStatePredicate(block, args[12]);
                           }
                        }
                     }

                     List<Ck> list = Lists.newArrayList();
                     List<Ck> list1 = Lists.newArrayList();
                     List<Ck> list2 = Lists.newArrayList();
                     Deque<BlockPos> deque = Lists.newLinkedList();
                     BlockPos blockpos3 = new BlockPos(structureboundingbox1.minX - structureboundingbox.minX, structureboundingbox1.minY - structureboundingbox.minY, structureboundingbox1.minZ - structureboundingbox.minZ);

                     for(int j = structureboundingbox.minZ; j <= structureboundingbox.maxZ; ++j) {
                        for(int k = structureboundingbox.minY; k <= structureboundingbox.maxY; ++k) {
                           for(int l = structureboundingbox.minX; l <= structureboundingbox.maxX; ++l) {
                              BlockPos blockpos4 = new BlockPos(l, k, j);
                              BlockPos blockpos5 = blockpos4.add(blockpos3);
                              in iblockstate = world.getBlockState(blockpos4);
                              if ((!flag1 || iblockstate.getBlock() != Nk.AIR) && (block == null || iblockstate.getBlock() == block && (predicate == null || predicate.apply(iblockstate)))) {
                                 Yg tileentity = world.getTileEntity(blockpos4);
                                 if (tileentity != null) {
                                    QQ nbttagcompound = tileentity.writeToNBT(new QQ());
                                    list1.add(new Ck(blockpos5, iblockstate, nbttagcompound));
                                    deque.addLast(blockpos4);
                                 } else if (!iblockstate.isFullBlock() && !iblockstate.isFullCube()) {
                                    list2.add(new Ck(blockpos5, iblockstate, (QQ)null));
                                    deque.addFirst(blockpos4);
                                 } else {
                                    list.add(new Ck(blockpos5, iblockstate, (QQ)null));
                                    deque.addLast(blockpos4);
                                 }
                              }
                           }
                        }
                     }

                     if (flag) {
                        Iterator var28;
                        BlockPos blockpos7;
                        for(var28 = deque.iterator(); var28.hasNext(); world.setBlockState(blockpos7, Nk.BARRIER.getDefaultState(), 2)) {
                           blockpos7 = (BlockPos)var28.next();
                           Yg tileentity1 = world.getTileEntity(blockpos7);
                           if (tileentity1 instanceof IInventory) {
                              ((IInventory)tileentity1).clear();
                           }
                        }

                        var28 = deque.iterator();

                        while(var28.hasNext()) {
                           blockpos7 = (BlockPos)var28.next();
                           world.setBlockState(blockpos7, Nk.AIR.getDefaultState(), 3);
                        }
                     }

                     List<Ck> list3 = Lists.newArrayList();
                     list3.addAll(list);
                     list3.addAll(list1);
                     list3.addAll(list2);
                     List<Ck> list4 = Lists.reverse(list3);

                     Iterator var33;
                     Ck commandclone$staticclonedata3;
                     Yg tileentity3;
                     for(var33 = list4.iterator(); var33.hasNext(); world.setBlockState(commandclone$staticclonedata3.pos, Nk.BARRIER.getDefaultState(), 2)) {
                        commandclone$staticclonedata3 = (Ck)var33.next();
                        tileentity3 = world.getTileEntity(commandclone$staticclonedata3.pos);
                        if (tileentity3 instanceof IInventory) {
                           ((IInventory)tileentity3).clear();
                        }
                     }

                     i = 0;
                     var33 = list3.iterator();

                     while(var33.hasNext()) {
                        commandclone$staticclonedata3 = (Ck)var33.next();
                        if (world.setBlockState(commandclone$staticclonedata3.pos, commandclone$staticclonedata3.blockState, 2)) {
                           ++i;
                        }
                     }

                     for(var33 = list1.iterator(); var33.hasNext(); world.setBlockState(commandclone$staticclonedata3.pos, commandclone$staticclonedata3.blockState, 2)) {
                        commandclone$staticclonedata3 = (Ck)var33.next();
                        tileentity3 = world.getTileEntity(commandclone$staticclonedata3.pos);
                        if (commandclone$staticclonedata3.nbt != null && tileentity3 != null) {
                           commandclone$staticclonedata3.nbt.setInteger("x", commandclone$staticclonedata3.pos.getX());
                           commandclone$staticclonedata3.nbt.setInteger("y", commandclone$staticclonedata3.pos.getY());
                           commandclone$staticclonedata3.nbt.setInteger("z", commandclone$staticclonedata3.pos.getZ());
                           tileentity3.readFromNBT(commandclone$staticclonedata3.nbt);
                           tileentity3.markDirty();
                        }
                     }

                     var33 = list4.iterator();

                     while(var33.hasNext()) {
                        commandclone$staticclonedata3 = (Ck)var33.next();
                        world.notifyNeighborsRespectDebug(commandclone$staticclonedata3.pos, commandclone$staticclonedata3.blockState.getBlock(), false);
                     }

                     List<bgg> list5 = world.getPendingBlockUpdates(structureboundingbox, false);
                     if (list5 != null) {
                        Iterator var37 = list5.iterator();

                        while(var37.hasNext()) {
                           bgg nextticklistentry = (bgg)var37.next();
                           if (structureboundingbox.isVecInside(nextticklistentry.position)) {
                              BlockPos blockpos8 = nextticklistentry.position.add(blockpos3);
                              world.scheduleBlockUpdate(blockpos8, nextticklistentry.getBlock(), (int)(nextticklistentry.scheduledTime - world.getWorldInfo().getWorldTotalTime()), nextticklistentry.priority);
                           }
                        }
                     }

                     if (i <= 0) {
                        throw new Ct("commands.clone.failed", new Object[0]);
                     } else {
                        sender.setCommandStat(CK.AFFECTED_BLOCKS, i);
                        notifyCommandListener(sender, this, "commands.clone.success", new Object[]{i});
                     }
                  } else {
                     throw new Ct("commands.clone.outOfWorld", new Object[0]);
                  }
               } else {
                  throw new Ct("commands.clone.outOfWorld", new Object[0]);
               }
            }
         }
      }
   }

   public List<String> getTabCompletions(Xx server, DB sender, String[] args, @Nullable BlockPos targetPos) {
      if (args.length > 0 && args.length <= 3) {
         return getTabCompletionCoordinate(args, 0, targetPos);
      } else if (args.length > 3 && args.length <= 6) {
         return getTabCompletionCoordinate(args, 3, targetPos);
      } else if (args.length > 6 && args.length <= 9) {
         return getTabCompletionCoordinate(args, 6, targetPos);
      } else if (args.length == 10) {
         return getListOfStringsMatchingLastWord(args, new String[]{"replace", "masked", "filtered"});
      } else if (args.length == 11) {
         return getListOfStringsMatchingLastWord(args, new String[]{"normal", "force", "move"});
      } else {
         return args.length == 12 && "filtered".equals(args[9]) ? getListOfStringsMatchingLastWord(args, co.REGISTRY.getKeys()) : Collections.emptyList();
      }
   }
}
