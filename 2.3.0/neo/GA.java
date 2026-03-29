package neo;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.math.BlockPos;

public class GA extends GL {
   private final Mq villager;
   private boolean hasFarmItem;
   private boolean wantsToReapStuff;
   private int currentTask;

   public GA(Mq villagerIn, double speedIn) {
      super(villagerIn, speedIn, 16);
      this.villager = villagerIn;
   }

   public boolean shouldExecute() {
      if (this.runDelay <= 0) {
         if (!this.villager.world.getGameRules().getBoolean("mobGriefing")) {
            return false;
         }

         this.currentTask = -1;
         this.hasFarmItem = this.villager.isFarmItemInInventory();
         this.wantsToReapStuff = this.villager.wantsMoreFood();
      }

      return super.shouldExecute();
   }

   public boolean shouldContinueExecuting() {
      return this.currentTask >= 0 && super.shouldContinueExecuting();
   }

   public void updateTask() {
      super.updateTask();
      this.villager.getLookHelper().setLookPosition((double)this.destinationBlock.getX() + 0.5, (double)(this.destinationBlock.getY() + 1), (double)this.destinationBlock.getZ() + 0.5, 10.0F, (float)this.villager.getVerticalFaceSpeed());
      if (this.getIsAboveDestination()) {
         bij world = this.villager.world;
         BlockPos blockpos = this.destinationBlock.up();
         in iblockstate = world.getBlockState(blockpos);
         co block = iblockstate.getBlock();
         if (this.currentTask == 0 && block instanceof de && ((de)block).isMaxAge(iblockstate)) {
            world.destroyBlock(blockpos, true);
         } else if (this.currentTask == 1 && iblockstate.getMaterial() == hM.AIR) {
            InventoryBasic inventorybasic = this.villager.getVillagerInventory();

            for(int i = 0; i < inventorybasic.getSizeInventory(); ++i) {
               Qy itemstack = inventorybasic.getStackInSlot(i);
               boolean flag = false;
               if (!itemstack.isEmpty()) {
                  if (itemstack.getItem() == NK.WHEAT_SEEDS) {
                     world.setBlockState(blockpos, Nk.WHEAT.getDefaultState(), 3);
                     flag = true;
                  } else if (itemstack.getItem() == NK.POTATO) {
                     world.setBlockState(blockpos, Nk.POTATOES.getDefaultState(), 3);
                     flag = true;
                  } else if (itemstack.getItem() == NK.CARROT) {
                     world.setBlockState(blockpos, Nk.CARROTS.getDefaultState(), 3);
                     flag = true;
                  } else if (itemstack.getItem() == NK.BEETROOT_SEEDS) {
                     world.setBlockState(blockpos, Nk.BEETROOTS.getDefaultState(), 3);
                     flag = true;
                  }
               }

               if (flag) {
                  itemstack.shrink(1);
                  if (itemstack.isEmpty()) {
                     inventorybasic.setInventorySlotContents(i, Qy.EMPTY);
                  }
                  break;
               }
            }
         }

         this.currentTask = -1;
         this.runDelay = 10;
      }

   }

   protected boolean shouldMoveTo(bij worldIn, BlockPos pos) {
      co block = worldIn.getBlockState(pos).getBlock();
      if (block == Nk.FARMLAND) {
         pos = pos.up();
         in iblockstate = worldIn.getBlockState(pos);
         block = iblockstate.getBlock();
         if (block instanceof de && ((de)block).isMaxAge(iblockstate) && this.wantsToReapStuff && (this.currentTask == 0 || this.currentTask < 0)) {
            this.currentTask = 0;
            return true;
         }

         if (iblockstate.getMaterial() == hM.AIR && this.hasFarmItem && (this.currentTask == 1 || this.currentTask < 0)) {
            this.currentTask = 1;
            return true;
         }
      }

      return false;
   }
}
