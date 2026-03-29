package neo;

import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumHand;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.math.BlockPos;

public class Jk extends Jh implements Ya {
   private boolean isBlocked = true;
   private int transferTicker = -1;
   private final BlockPos lastPosition;

   public Jk(bij worldIn) {
      super(worldIn);
      this.lastPosition = BlockPos.ORIGIN;
   }

   public Jk(bij worldIn, double x, double y, double z) {
      super(worldIn, x, y, z);
      this.lastPosition = BlockPos.ORIGIN;
   }

   public Jb getType() {
      return Jb.HOPPER;
   }

   public in getDefaultDisplayTile() {
      return Nk.HOPPER.getDefaultState();
   }

   public int getDefaultDisplayTileOffset() {
      return 1;
   }

   public int getSizeInventory() {
      return 5;
   }

   public boolean processInitialInteract(ME player, EnumHand hand) {
      if (!this.world.isRemote) {
         player.displayGUIChest(this);
      }

      return true;
   }

   public void onActivatorRailPass(int x, int y, int z, boolean receivingPower) {
      boolean flag = !receivingPower;
      if (flag != this.getBlocked()) {
         this.setBlocked(flag);
      }

   }

   public boolean getBlocked() {
      return this.isBlocked;
   }

   public void setBlocked(boolean p_96110_1_) {
      this.isBlocked = p_96110_1_;
   }

   public bij getWorld() {
      return this.world;
   }

   public double getXPos() {
      return this.posX;
   }

   public double getYPos() {
      return this.posY + 0.5;
   }

   public double getZPos() {
      return this.posZ;
   }

   public void onUpdate() {
      super.onUpdate();
      if (!this.world.isRemote && this.isEntityAlive() && this.getBlocked()) {
         BlockPos blockpos = new BlockPos(this);
         if (blockpos.equals(this.lastPosition)) {
            --this.transferTicker;
         } else {
            this.setTransferTicker(0);
         }

         if (!this.canTransfer()) {
            this.setTransferTicker(0);
            if (this.captureDroppedItems()) {
               this.setTransferTicker(4);
               this.markDirty();
            }
         }
      }

   }

   public boolean captureDroppedItems() {
      if (YB.pullItems(this)) {
         return true;
      } else {
         List<IY> list = this.world.getEntitiesWithinAABB(IY.class, this.getEntityBoundingBox().grow(0.25, 0.0, 0.25), EntitySelectors.IS_ALIVE);
         if (!list.isEmpty()) {
            YB.putDropInInventoryAllSlots((IInventory)null, this, (IY)list.get(0));
         }

         return false;
      }
   }

   public void killMinecart(DamageSource source) {
      super.killMinecart(source);
      if (this.world.getGameRules().getBoolean("doEntityDrops")) {
         this.dropItemWithOffset(OL.getItemFromBlock(Nk.HOPPER), 1, 0.0F);
      }

   }

   public static void registerFixesMinecartHopper(DataFixer fixer) {
      Jh.addDataFixers(fixer, Jk.class);
   }

   protected void writeEntityToNBT(QQ compound) {
      super.writeEntityToNBT(compound);
      compound.setInteger("TransferCooldown", this.transferTicker);
      compound.setBoolean("Enabled", this.isBlocked);
   }

   protected void readEntityFromNBT(QQ compound) {
      super.readEntityFromNBT(compound);
      this.transferTicker = compound.getInteger("TransferCooldown");
      this.isBlocked = compound.hasKey("Enabled") ? compound.getBoolean("Enabled") : true;
   }

   public void setTransferTicker(int p_98042_1_) {
      this.transferTicker = p_98042_1_;
   }

   public boolean canTransfer() {
      return this.transferTicker > 0;
   }

   public String getGuiID() {
      return "minecraft:hopper";
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      return new ContainerHopper(playerInventory, this, playerIn);
   }
}
