package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.walkers.ItemStackDataLists;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class Yn extends YD implements ITickable {
   private NonNullList<Qy> chestContents;
   public boolean adjacentChestChecked;
   public Yn adjacentChestZNeg;
   public Yn adjacentChestXPos;
   public Yn adjacentChestXNeg;
   public Yn adjacentChestZPos;
   public float lidAngle;
   public float prevLidAngle;
   public int numPlayersUsing;
   private int ticksSinceSync;
   private cS cachedChestType;

   public Yn() {
      this.chestContents = NonNullList.withSize(27, Qy.EMPTY);
   }

   public Yn(cS typeIn) {
      this.chestContents = NonNullList.withSize(27, Qy.EMPTY);
      this.cachedChestType = typeIn;
   }

   public int getSizeInventory() {
      return 27;
   }

   public boolean isEmpty() {
      Iterator var1 = this.chestContents.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public String getName() {
      return this.hasCustomName() ? this.customName : "container.chest";
   }

   public static void registerFixesChest(DataFixer fixer) {
      fixer.registerWalker(FixTypes.BLOCK_ENTITY, new ItemStackDataLists(Yn.class, new String[]{"Items"}));
   }

   public void readFromNBT(QQ compound) {
      super.readFromNBT(compound);
      this.chestContents = NonNullList.withSize(this.getSizeInventory(), Qy.EMPTY);
      if (!this.checkLootAndRead(compound)) {
         ItemStackHelper.loadAllItems(compound, this.chestContents);
      }

      if (compound.hasKey("CustomName", 8)) {
         this.customName = compound.getString("CustomName");
      }

   }

   public QQ writeToNBT(QQ compound) {
      super.writeToNBT(compound);
      if (!this.checkLootAndWrite(compound)) {
         ItemStackHelper.saveAllItems(compound, this.chestContents);
      }

      if (this.hasCustomName()) {
         compound.setString("CustomName", this.customName);
      }

      return compound;
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void updateContainingBlockInfo() {
      super.updateContainingBlockInfo();
      this.adjacentChestChecked = false;
   }

   private void setNeighbor(Yn chestTe, EnumFacing side) {
      if (chestTe.isInvalid()) {
         this.adjacentChestChecked = false;
      } else if (this.adjacentChestChecked) {
         switch (side) {
            case NORTH:
               if (this.adjacentChestZNeg != chestTe) {
                  this.adjacentChestChecked = false;
               }
               break;
            case SOUTH:
               if (this.adjacentChestZPos != chestTe) {
                  this.adjacentChestChecked = false;
               }
               break;
            case EAST:
               if (this.adjacentChestXPos != chestTe) {
                  this.adjacentChestChecked = false;
               }
               break;
            case WEST:
               if (this.adjacentChestXNeg != chestTe) {
                  this.adjacentChestChecked = false;
               }
         }
      }

   }

   public void checkForAdjacentChests() {
      if (!this.adjacentChestChecked) {
         this.adjacentChestChecked = true;
         this.adjacentChestXNeg = this.getAdjacentChest(EnumFacing.WEST);
         this.adjacentChestXPos = this.getAdjacentChest(EnumFacing.EAST);
         this.adjacentChestZNeg = this.getAdjacentChest(EnumFacing.NORTH);
         this.adjacentChestZPos = this.getAdjacentChest(EnumFacing.SOUTH);
      }

   }

   @Nullable
   protected Yn getAdjacentChest(EnumFacing side) {
      BlockPos blockpos = this.pos.offset(side);
      if (this.isChestAt(blockpos)) {
         Yg tileentity = this.world.getTileEntity(blockpos);
         if (tileentity instanceof Yn) {
            Yn tileentitychest = (Yn)tileentity;
            tileentitychest.setNeighbor(this, side.getOpposite());
            return tileentitychest;
         }
      }

      return null;
   }

   private boolean isChestAt(BlockPos posIn) {
      if (this.world == null) {
         return false;
      } else {
         co block = this.world.getBlockState(posIn).getBlock();
         return block instanceof cT && ((cT)block).chestType == this.getChestType();
      }
   }

   public void update() {
      this.checkForAdjacentChests();
      int i = this.pos.getX();
      int j = this.pos.getY();
      int k = this.pos.getZ();
      ++this.ticksSinceSync;
      float f1;
      if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + i + j + k) % 200 == 0) {
         this.numPlayersUsing = 0;
         f1 = 5.0F;
         Iterator var5 = this.world.getEntitiesWithinAABB(ME.class, new AxisAlignedBB((double)((float)i - 5.0F), (double)((float)j - 5.0F), (double)((float)k - 5.0F), (double)((float)(i + 1) + 5.0F), (double)((float)(j + 1) + 5.0F), (double)((float)(k + 1) + 5.0F))).iterator();

         label93:
         while(true) {
            IInventory iinventory;
            do {
               ME entityplayer;
               do {
                  if (!var5.hasNext()) {
                     break label93;
                  }

                  entityplayer = (ME)var5.next();
               } while(!(entityplayer.openContainer instanceof ContainerChest));

               iinventory = ((ContainerChest)entityplayer.openContainer).getLowerChestInventory();
            } while(iinventory != this && (!(iinventory instanceof InventoryLargeChest) || !((InventoryLargeChest)iinventory).isPartOfLargeChest(this)));

            ++this.numPlayersUsing;
         }
      }

      this.prevLidAngle = this.lidAngle;
      f1 = 0.1F;
      double d3;
      if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
         double d1 = (double)i + 0.5;
         d3 = (double)k + 0.5;
         if (this.adjacentChestZPos != null) {
            d3 += 0.5;
         }

         if (this.adjacentChestXPos != null) {
            d1 += 0.5;
         }

         this.world.playSound((ME)null, d1, (double)j + 0.5, d3, NO.BLOCK_CHEST_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
      }

      if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F) {
         float f2 = this.lidAngle;
         if (this.numPlayersUsing > 0) {
            this.lidAngle += 0.1F;
         } else {
            this.lidAngle -= 0.1F;
         }

         if (this.lidAngle > 1.0F) {
            this.lidAngle = 1.0F;
         }

         float f3 = 0.5F;
         if (this.lidAngle < 0.5F && f2 >= 0.5F && this.adjacentChestZNeg == null && this.adjacentChestXNeg == null) {
            d3 = (double)i + 0.5;
            double d0 = (double)k + 0.5;
            if (this.adjacentChestZPos != null) {
               d0 += 0.5;
            }

            if (this.adjacentChestXPos != null) {
               d3 += 0.5;
            }

            this.world.playSound((ME)null, d3, (double)j + 0.5, d0, NO.BLOCK_CHEST_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
         }

         if (this.lidAngle < 0.0F) {
            this.lidAngle = 0.0F;
         }
      }

   }

   public boolean receiveClientEvent(int id, int type) {
      if (id == 1) {
         this.numPlayersUsing = type;
         return true;
      } else {
         return super.receiveClientEvent(id, type);
      }
   }

   public void openInventory(ME player) {
      if (!player.isSpectator()) {
         if (this.numPlayersUsing < 0) {
            this.numPlayersUsing = 0;
         }

         ++this.numPlayersUsing;
         this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
         this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
         if (this.getChestType() == cS.TRAP) {
            this.world.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType(), false);
         }
      }

   }

   public void closeInventory(ME player) {
      if (!player.isSpectator() && this.getBlockType() instanceof cT) {
         --this.numPlayersUsing;
         this.world.addBlockEvent(this.pos, this.getBlockType(), 1, this.numPlayersUsing);
         this.world.notifyNeighborsOfStateChange(this.pos, this.getBlockType(), false);
         if (this.getChestType() == cS.TRAP) {
            this.world.notifyNeighborsOfStateChange(this.pos.down(), this.getBlockType(), false);
         }
      }

   }

   public void invalidate() {
      super.invalidate();
      this.updateContainingBlockInfo();
      this.checkForAdjacentChests();
   }

   public cS getChestType() {
      if (this.cachedChestType == null) {
         if (this.world == null || !(this.getBlockType() instanceof cT)) {
            return cS.BASIC;
         }

         this.cachedChestType = ((cT)this.getBlockType()).chestType;
      }

      return this.cachedChestType;
   }

   public String getGuiID() {
      return "minecraft:chest";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      this.fillWithLoot(playerIn);
      return new ContainerChest(playerInventory, this, playerIn);
   }

   protected NonNullList<Qy> getItems() {
      return this.chestContents;
   }
}
