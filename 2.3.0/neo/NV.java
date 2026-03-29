package neo;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class NV implements NT {
   public NV() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      int i = 0;
      Qy itemstack = Qy.EMPTY;

      for(int j = 0; j < inv.getSizeInventory(); ++j) {
         Qy itemstack1 = inv.getStackInSlot(j);
         if (!itemstack1.isEmpty()) {
            if (itemstack1.getItem() == NK.WRITTEN_BOOK) {
               if (!itemstack.isEmpty()) {
                  return false;
               }

               itemstack = itemstack1;
            } else {
               if (itemstack1.getItem() != NK.WRITABLE_BOOK) {
                  return false;
               }

               ++i;
            }
         }
      }

      return !itemstack.isEmpty() && itemstack.hasTagCompound() && i > 0;
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      int i = 0;
      Qy itemstack = Qy.EMPTY;

      for(int j = 0; j < inv.getSizeInventory(); ++j) {
         Qy itemstack1 = inv.getStackInSlot(j);
         if (!itemstack1.isEmpty()) {
            if (itemstack1.getItem() == NK.WRITTEN_BOOK) {
               if (!itemstack.isEmpty()) {
                  return Qy.EMPTY;
               }

               itemstack = itemstack1;
            } else {
               if (itemstack1.getItem() != NK.WRITABLE_BOOK) {
                  return Qy.EMPTY;
               }

               ++i;
            }
         }
      }

      if (!itemstack.isEmpty() && itemstack.hasTagCompound() && i >= 1 && QD.getGeneration(itemstack) < 2) {
         Qy itemstack2 = new Qy(NK.WRITTEN_BOOK, i);
         itemstack2.setTagCompound(itemstack.getTagCompound().copy());
         itemstack2.getTagCompound().setInteger("generation", QD.getGeneration(itemstack) + 1);
         if (itemstack.hasDisplayName()) {
            itemstack2.setStackDisplayName(itemstack.getDisplayName());
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
         if (itemstack.getItem() instanceof QD) {
            Qy itemstack1 = itemstack.copy();
            itemstack1.setCount(1);
            nonnulllist.set(i, itemstack1);
            break;
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
