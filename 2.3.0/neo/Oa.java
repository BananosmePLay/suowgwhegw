package neo;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class Oa implements NT {
   public Oa() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      Qy itemstack = Qy.EMPTY;
      Qy itemstack1 = Qy.EMPTY;

      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack2 = inv.getStackInSlot(i);
         if (!itemstack2.isEmpty()) {
            if (itemstack2.getItem() != NK.BANNER) {
               return false;
            }

            if (!itemstack.isEmpty() && !itemstack1.isEmpty()) {
               return false;
            }

            Om enumdyecolor = OV.getBaseColor(itemstack2);
            boolean flag = Yh.getPatterns(itemstack2) > 0;
            if (!itemstack.isEmpty()) {
               if (flag) {
                  return false;
               }

               if (enumdyecolor != OV.getBaseColor(itemstack)) {
                  return false;
               }

               itemstack1 = itemstack2;
            } else if (!itemstack1.isEmpty()) {
               if (!flag) {
                  return false;
               }

               if (enumdyecolor != OV.getBaseColor(itemstack1)) {
                  return false;
               }

               itemstack = itemstack2;
            } else if (flag) {
               itemstack = itemstack2;
            } else {
               itemstack1 = itemstack2;
            }
         }
      }

      return !itemstack.isEmpty() && !itemstack1.isEmpty();
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      for(int i = 0; i < inv.getSizeInventory(); ++i) {
         Qy itemstack = inv.getStackInSlot(i);
         if (!itemstack.isEmpty() && Yh.getPatterns(itemstack) > 0) {
            Qy itemstack1 = itemstack.copy();
            itemstack1.setCount(1);
            return itemstack1;
         }
      }

      return Qy.EMPTY;
   }

   public Qy getRecipeOutput() {
      return Qy.EMPTY;
   }

   public NonNullList<Qy> getRemainingItems(InventoryCrafting inv) {
      NonNullList<Qy> nonnulllist = NonNullList.withSize(inv.getSizeInventory(), Qy.EMPTY);

      for(int i = 0; i < nonnulllist.size(); ++i) {
         Qy itemstack = inv.getStackInSlot(i);
         if (!itemstack.isEmpty()) {
            if (itemstack.getItem().hasContainerItem()) {
               nonnulllist.set(i, new Qy(itemstack.getItem().getContainerItem()));
            } else if (itemstack.hasTagCompound() && Yh.getPatterns(itemstack) > 0) {
               Qy itemstack1 = itemstack.copy();
               itemstack1.setCount(1);
               nonnulllist.set(i, itemstack1);
            }
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
