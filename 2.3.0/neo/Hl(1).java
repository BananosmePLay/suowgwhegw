package neo;

import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.math.MathHelper;

public class Hl extends Hr {
   private int interactionDelay;
   private final Mq villager;

   public Hl(Mq villagerIn) {
      super(villagerIn, Mq.class, 3.0F, 0.02F);
      this.villager = villagerIn;
   }

   public void startExecuting() {
      super.startExecuting();
      if (this.villager.canAbondonItems() && this.closestEntity instanceof Mq && ((Mq)this.closestEntity).wantsMoreFood()) {
         this.interactionDelay = 10;
      } else {
         this.interactionDelay = 0;
      }

   }

   public void updateTask() {
      super.updateTask();
      if (this.interactionDelay > 0) {
         --this.interactionDelay;
         if (this.interactionDelay == 0) {
            InventoryBasic inventorybasic = this.villager.getVillagerInventory();

            for(int i = 0; i < inventorybasic.getSizeInventory(); ++i) {
               Qy itemstack = inventorybasic.getStackInSlot(i);
               Qy itemstack1 = Qy.EMPTY;
               if (!itemstack.isEmpty()) {
                  OL item = itemstack.getItem();
                  int j;
                  if ((item == NK.BREAD || item == NK.POTATO || item == NK.CARROT || item == NK.BEETROOT) && itemstack.getCount() > 3) {
                     j = itemstack.getCount() / 2;
                     itemstack.shrink(j);
                     itemstack1 = new Qy(item, j, itemstack.getMetadata());
                  } else if (item == NK.WHEAT && itemstack.getCount() > 5) {
                     j = itemstack.getCount() / 2 / 3 * 3;
                     int k = j / 3;
                     itemstack.shrink(j);
                     itemstack1 = new Qy(NK.BREAD, k, 0);
                  }

                  if (itemstack.isEmpty()) {
                     inventorybasic.setInventorySlotContents(i, Qy.EMPTY);
                  }
               }

               if (!itemstack1.isEmpty()) {
                  double d0 = this.villager.posY - 0.30000001192092896 + (double)this.villager.getEyeHeight();
                  IY entityitem = new IY(this.villager.world, this.villager.posX, d0, this.villager.posZ, itemstack1);
                  float f = 0.3F;
                  float f1 = this.villager.rotationYawHead;
                  float f2 = this.villager.rotationPitch;
                  entityitem.motionX = (double)(-MathHelper.sin(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F) * 0.3F);
                  entityitem.motionZ = (double)(MathHelper.cos(f1 * 0.017453292F) * MathHelper.cos(f2 * 0.017453292F) * 0.3F);
                  entityitem.motionY = (double)(-MathHelper.sin(f2 * 0.017453292F) * 0.3F + 0.1F);
                  entityitem.setDefaultPickupDelay();
                  this.villager.world.spawnEntity(entityitem);
                  break;
               }
            }
         }
      }

   }
}
