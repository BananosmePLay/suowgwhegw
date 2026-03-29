package neo;

import net.minecraft.inventory.IInventory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class WW {
   public bij world;
   public MG player;
   private bbb gameType;
   private boolean isDestroyingBlock;
   private int initialDamage;
   private BlockPos destroyPos;
   private int curblockDamage;
   private boolean receivedFinishDiggingPacket;
   private BlockPos delayedDestroyPos;
   private int initialBlockDamage;
   private int durabilityRemainingOnBlock;

   public WW(bij worldIn) {
      this.gameType = bbb.NOT_SET;
      this.destroyPos = BlockPos.ORIGIN;
      this.delayedDestroyPos = BlockPos.ORIGIN;
      this.durabilityRemainingOnBlock = -1;
      this.world = worldIn;
   }

   public void setGameType(bbb type) {
      this.gameType = type;
      type.configurePlayerCapabilities(this.player.capabilities);
      this.player.sendPlayerAbilities();
      this.player.server.getPlayerList().sendPacketToAllPlayers(new Uw(Uu.UPDATE_GAME_MODE, new MG[]{this.player}));
      this.world.updateAllPlayersSleepingFlag();
   }

   public bbb getGameType() {
      return this.gameType;
   }

   public boolean survivalOrAdventure() {
      return this.gameType.isSurvivalOrAdventure();
   }

   public boolean isCreative() {
      return this.gameType.isCreative();
   }

   public void initializeGameType(bbb type) {
      if (this.gameType == bbb.NOT_SET) {
         this.gameType = type;
      }

      this.setGameType(this.gameType);
   }

   public void updateBlockRemoving() {
      ++this.curblockDamage;
      float f;
      int l;
      if (this.receivedFinishDiggingPacket) {
         int i = this.curblockDamage - this.initialBlockDamage;
         in iblockstate = this.world.getBlockState(this.delayedDestroyPos);
         if (iblockstate.getMaterial() == hM.AIR) {
            this.receivedFinishDiggingPacket = false;
         } else {
            f = iblockstate.getPlayerRelativeBlockHardness(this.player, this.player.world, this.delayedDestroyPos) * (float)(i + 1);
            l = (int)(f * 10.0F);
            if (l != this.durabilityRemainingOnBlock) {
               this.world.sendBlockBreakProgress(this.player.getEntityId(), this.delayedDestroyPos, l);
               this.durabilityRemainingOnBlock = l;
            }

            if (f >= 1.0F) {
               this.receivedFinishDiggingPacket = false;
               this.tryHarvestBlock(this.delayedDestroyPos);
            }
         }
      } else if (this.isDestroyingBlock) {
         in iblockstate1 = this.world.getBlockState(this.destroyPos);
         if (iblockstate1.getMaterial() == hM.AIR) {
            this.world.sendBlockBreakProgress(this.player.getEntityId(), this.destroyPos, -1);
            this.durabilityRemainingOnBlock = -1;
            this.isDestroyingBlock = false;
         } else {
            int k = this.curblockDamage - this.initialDamage;
            f = iblockstate1.getPlayerRelativeBlockHardness(this.player, this.player.world, this.delayedDestroyPos) * (float)(k + 1);
            l = (int)(f * 10.0F);
            if (l != this.durabilityRemainingOnBlock) {
               this.world.sendBlockBreakProgress(this.player.getEntityId(), this.destroyPos, l);
               this.durabilityRemainingOnBlock = l;
            }
         }
      }

   }

   public void onBlockClicked(BlockPos pos, EnumFacing side) {
      if (this.isCreative()) {
         if (!this.world.extinguishFire((ME)null, pos, side)) {
            this.tryHarvestBlock(pos);
         }
      } else {
         in iblockstate = this.world.getBlockState(pos);
         co block = iblockstate.getBlock();
         if (this.gameType.hasLimitedInteractions()) {
            if (this.gameType == bbb.SPECTATOR) {
               return;
            }

            if (!this.player.isAllowEdit()) {
               Qy itemstack = this.player.getHeldItemMainhand();
               if (itemstack.isEmpty()) {
                  return;
               }

               if (!itemstack.canDestroy(block)) {
                  return;
               }
            }
         }

         this.world.extinguishFire((ME)null, pos, side);
         this.initialDamage = this.curblockDamage;
         float f = 1.0F;
         if (iblockstate.getMaterial() != hM.AIR) {
            block.onBlockClicked(this.world, pos, this.player);
            f = iblockstate.getPlayerRelativeBlockHardness(this.player, this.player.world, pos);
         }

         if (iblockstate.getMaterial() != hM.AIR && f >= 1.0F) {
            this.tryHarvestBlock(pos);
         } else {
            this.isDestroyingBlock = true;
            this.destroyPos = pos;
            int i = (int)(f * 10.0F);
            this.world.sendBlockBreakProgress(this.player.getEntityId(), pos, i);
            this.durabilityRemainingOnBlock = i;
         }
      }

   }

   public void blockRemoving(BlockPos pos) {
      if (pos.equals(this.destroyPos)) {
         int i = this.curblockDamage - this.initialDamage;
         in iblockstate = this.world.getBlockState(pos);
         if (iblockstate.getMaterial() != hM.AIR) {
            float f = iblockstate.getPlayerRelativeBlockHardness(this.player, this.player.world, pos) * (float)(i + 1);
            if (f >= 0.7F) {
               this.isDestroyingBlock = false;
               this.world.sendBlockBreakProgress(this.player.getEntityId(), pos, -1);
               this.tryHarvestBlock(pos);
            } else if (!this.receivedFinishDiggingPacket) {
               this.isDestroyingBlock = false;
               this.receivedFinishDiggingPacket = true;
               this.delayedDestroyPos = pos;
               this.initialBlockDamage = this.initialDamage;
            }
         }
      }

   }

   public void cancelDestroyingBlock() {
      this.isDestroyingBlock = false;
      this.world.sendBlockBreakProgress(this.player.getEntityId(), this.destroyPos, -1);
   }

   private boolean removeBlock(BlockPos pos) {
      in iblockstate = this.world.getBlockState(pos);
      iblockstate.getBlock().onBlockHarvested(this.world, pos, iblockstate, this.player);
      boolean flag = this.world.setBlockToAir(pos);
      if (flag) {
         iblockstate.getBlock().onPlayerDestroy(this.world, pos, iblockstate);
      }

      return flag;
   }

   public boolean tryHarvestBlock(BlockPos pos) {
      if (this.gameType.isCreative() && !this.player.getHeldItemMainhand().isEmpty() && this.player.getHeldItemMainhand().getItem() instanceof Qz) {
         return false;
      } else {
         in iblockstate = this.world.getBlockState(pos);
         Yg tileentity = this.world.getTileEntity(pos);
         co block = iblockstate.getBlock();
         if ((block instanceof da || block instanceof hh) && !this.player.canUseCommandBlock()) {
            this.world.notifyBlockUpdate(pos, iblockstate, iblockstate, 3);
            return false;
         } else {
            if (this.gameType.hasLimitedInteractions()) {
               if (this.gameType == bbb.SPECTATOR) {
                  return false;
               }

               if (!this.player.isAllowEdit()) {
                  Qy itemstack = this.player.getHeldItemMainhand();
                  if (itemstack.isEmpty()) {
                     return false;
                  }

                  if (!itemstack.canDestroy(block)) {
                     return false;
                  }
               }
            }

            this.world.playEvent(this.player, 2001, pos, co.getStateId(iblockstate));
            boolean flag1 = this.removeBlock(pos);
            if (this.isCreative()) {
               this.player.connection.sendPacket(new TA(this.world, pos));
            } else {
               Qy itemstack1 = this.player.getHeldItemMainhand();
               Qy itemstack2 = itemstack1.isEmpty() ? Qy.EMPTY : itemstack1.copy();
               boolean flag = this.player.canHarvestBlock(iblockstate);
               if (!itemstack1.isEmpty()) {
                  itemstack1.onBlockDestroyed(this.world, iblockstate, pos, this.player);
               }

               if (flag1 && flag) {
                  iblockstate.getBlock().harvestBlock(this.world, this.player, pos, iblockstate, tileentity, itemstack2);
               }
            }

            return flag1;
         }
      }
   }

   public EnumActionResult processRightClick(ME player, bij worldIn, Qy stack, EnumHand hand) {
      if (this.gameType == bbb.SPECTATOR) {
         return EnumActionResult.PASS;
      } else if (player.getCooldownTracker().hasCooldown(stack.getItem())) {
         return EnumActionResult.PASS;
      } else {
         int i = stack.getCount();
         int j = stack.getMetadata();
         ActionResult<Qy> actionresult = stack.useItemRightClick(worldIn, player, hand);
         Qy itemstack = (Qy)actionresult.getResult();
         if (itemstack == stack && itemstack.getCount() == i && itemstack.getMaxItemUseDuration() <= 0 && itemstack.getMetadata() == j) {
            return actionresult.getType();
         } else if (actionresult.getType() == EnumActionResult.FAIL && itemstack.getMaxItemUseDuration() > 0 && !player.isHandActive()) {
            return actionresult.getType();
         } else {
            player.setHeldItem(hand, itemstack);
            if (this.isCreative()) {
               itemstack.setCount(i);
               if (itemstack.isItemStackDamageable()) {
                  itemstack.setItemDamage(j);
               }
            }

            if (itemstack.isEmpty()) {
               player.setHeldItem(hand, Qy.EMPTY);
            }

            if (!player.isHandActive()) {
               ((MG)player).sendContainerToPlayer(player.inventoryContainer);
            }

            return actionresult.getType();
         }
      }
   }

   public EnumActionResult processRightClickBlock(ME player, bij worldIn, Qy stack, EnumHand hand, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (this.gameType == bbb.SPECTATOR) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof bgb) {
            co block1 = worldIn.getBlockState(pos).getBlock();
            bgb ilockablecontainer = (bgb)tileentity;
            if (ilockablecontainer instanceof Yn && block1 instanceof cT) {
               ilockablecontainer = ((cT)block1).getLockableContainer(worldIn, pos);
            }

            if (ilockablecontainer != null) {
               player.displayGUIChest(ilockablecontainer);
               return EnumActionResult.SUCCESS;
            }
         } else if (tileentity instanceof IInventory) {
            player.displayGUIChest((IInventory)tileentity);
            return EnumActionResult.SUCCESS;
         }

         return EnumActionResult.PASS;
      } else {
         if (!player.isSneaking() || player.getHeldItemMainhand().isEmpty() && player.getHeldItemOffhand().isEmpty()) {
            in iblockstate = worldIn.getBlockState(pos);
            if (iblockstate.getBlock().onBlockActivated(worldIn, pos, iblockstate, player, hand, facing, hitX, hitY, hitZ)) {
               return EnumActionResult.SUCCESS;
            }
         }

         if (stack.isEmpty()) {
            return EnumActionResult.PASS;
         } else if (player.getCooldownTracker().hasCooldown(stack.getItem())) {
            return EnumActionResult.PASS;
         } else {
            if (stack.getItem() instanceof OX && !player.canUseCommandBlock()) {
               co block = ((OX)stack.getItem()).getBlock();
               if (block instanceof da || block instanceof hh) {
                  return EnumActionResult.FAIL;
               }
            }

            if (this.isCreative()) {
               int j = stack.getMetadata();
               int i = stack.getCount();
               EnumActionResult enumactionresult = stack.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
               stack.setItemDamage(j);
               stack.setCount(i);
               return enumactionresult;
            } else {
               return stack.onItemUse(player, worldIn, pos, hand, facing, hitX, hitY, hitZ);
            }
         }
      }
   }

   public void setWorld(bis serverWorld) {
      this.world = serverWorld;
   }
}
