package neo;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class Oc implements NT {
   public Oc() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      int i = 0;
      Qy itemstack = Qy.EMPTY;

      for(int j = 0; j < inv.getSizeInventory(); ++j) {
         Qy itemstack1 = inv.getStackInSlot(j);
         if (!itemstack1.isEmpty()) {
            if (itemstack1.getItem() == NK.FILLED_MAP) {
               if (!itemstack.isEmpty()) {
                  return false;
               }

               itemstack = itemstack1;
            } else {
               if (itemstack1.getItem() != NK.MAP) {
                  return false;
               }

               ++i;
            }
         }
      }

      return !itemstack.isEmpty() && i > 0;
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      int i = 0;
      Qy itemstack = Qy.EMPTY;

      for(int j = 0; j < inv.getSizeInventory(); ++j) {
         Qy itemstack1 = inv.getStackInSlot(j);
         if (!itemstack1.isEmpty()) {
            if (itemstack1.getItem() == NK.FILLED_MAP) {
               if (!itemstack.isEmpty()) {
                  return Qy.EMPTY;
               }

               itemstack = itemstack1;
            } else {
               if (itemstack1.getItem() != NK.MAP) {
                  return Qy.EMPTY;
               }

               ++i;
            }
         }
      }

      if (!itemstack.isEmpty() && i >= 1) {
         Qy itemstack2 = new Qy(NK.FILLED_MAP, i + 1, itemstack.getMetadata());
         if (itemstack.hasDisplayName()) {
            itemstack2.setStackDisplayName(itemstack.getDisplayName());
         }

         if (itemstack.hasTagCompound()) {
            itemstack2.setTagCompound(itemstack.getTagCompound());
         }

         return itemstack2;
      } else {
         return Qy.EMPTY;
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
      return width >= 3 && height >= 3;
   }
}
