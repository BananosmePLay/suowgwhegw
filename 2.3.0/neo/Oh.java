package neo;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class Oh implements NT {
   public Oh() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      Qy itemstack = Qy.EMPTY;
      Qy itemstack1 = Qy.EMPTY;

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack2 = inv.getStackInSlot(i);
         if (!itemstack2.isEmpty()) {
            if (itemstack2.getItem() == NK.BANNER) {
               if (!itemstack1.isEmpty()) {
                  return false;
               }

               itemstack1 = itemstack2;
            } else {
               if (itemstack2.getItem() != NK.SHIELD) {
                  return false;
               }

               if (!itemstack.isEmpty()) {
                  return false;
               }

               if (itemstack2.getSubCompound("BlockEntityTag") != null) {
                  return false;
               }

               itemstack = itemstack2;
            }
         }
      }

      if (!itemstack.isEmpty() && !itemstack1.isEmpty()) {
         return true;
      } else {
         return false;
      }
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      Qy itemstack = Qy.EMPTY;
      Qy itemstack1 = Qy.EMPTY;

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack2 = inv.getStackInSlot(i);
         if (!itemstack2.isEmpty()) {
            if (itemstack2.getItem() == NK.BANNER) {
               itemstack = itemstack2;
            } else if (itemstack2.getItem() == NK.SHIELD) {
               itemstack1 = itemstack2.copy();
            }
         }
      }

      if (itemstack1.isEmpty()) {
         return itemstack1;
      } else {
         QQ nbttagcompound = itemstack.getSubCompound("BlockEntityTag");
         QQ nbttagcompound1 = nbttagcompound == null ? new QQ() : nbttagcompound.copy();
         nbttagcompound1.setInteger("Base", itemstack.getMetadata() & 15);
         itemstack1.setTagInfo("BlockEntityTag", nbttagcompound1);
         return itemstack1;
      }
   }

   public Qy getRecipeOutput() {
      return Qy.EMPTY;
   }

   public NonNullList<Qy> getRemainingItems(InventoryCrafting inv) {
      NonNullList<Qy> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), Qy.EMPTY);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         Qy itemstack = inv.getStackInSlot(i);
         if (itemstack.getItem().hasContainerItem()) {
            nonnulllist.set(i, new Qy(itemstack.getItem().getContainerItem()));
         }
      }

      return nonnulllist;
   }

   public boolean isDynamic() {
      return true;
   }

   public boolean canFit(int width, int height) {
      return width * height >= 2;
   }
}
