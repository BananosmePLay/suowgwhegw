package neo;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class Oj implements NT {
   public Oj() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      int i = 0;
      int j = 0;

      for(int k = 0; k < inv.getSizeInventory(); ++k) {
         Qy itemstack = inv.getStackInSlot(k);
         if (!itemstack.isEmpty()) {
            if (co.getBlockFromItem(itemstack.getItem()) instanceof gr) {
               ++i;
            } else {
               if (itemstack.getItem() != NK.DYE) {
                  return false;
               }

               ++j;
            }

            if (j > 1 || i > 1) {
               return false;
            }
         }
      }

      return i == 1 && j == 1;
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      Qy itemstack = Qy.EMPTY;
      Qy itemstack1 = Qy.EMPTY;

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack2 = inv.getStackInSlot(i);
         if (!itemstack2.isEmpty()) {
            if (co.getBlockFromItem(itemstack2.getItem()) instanceof gr) {
               itemstack = itemstack2;
            } else if (itemstack2.getItem() == NK.DYE) {
               itemstack1 = itemstack2;
            }
         }
      }

      Qy itemstack3 = gr.getColoredItemStack(Om.byDyeDamage(itemstack1.getMetadata()));
      if (itemstack.hasTagCompound()) {
         itemstack3.setTagCompound(itemstack.getTagCompound().copy());
      }

      return itemstack3;
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
