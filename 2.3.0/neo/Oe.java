package neo;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public class Oe implements NT {
   public Oe() {
   }

   public boolean matches(InventoryCrafting inv, bij worldIn) {
      if (inv.getWidth() == 3 && inv.getHeight() == 3) {
         for(int i = 0; i < inv.getWidth(); ++i) {
            for(int j = 0; j < inv.getHeight(); ++j) {
               Qy itemstack = inv.getStackInRowAndColumn(i, j);
               if (itemstack.isEmpty()) {
                  return false;
               }

               OL item = itemstack.getItem();
               if (i == 1 && j == 1) {
                  if (item != NK.LINGERING_POTION) {
                     return false;
                  }
               } else if (item != NK.ARROW) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public Qy getCraftingResult(InventoryCrafting inv) {
      Qy itemstack = inv.getStackInRowAndColumn(1, 1);
      if (itemstack.getItem() != NK.LINGERING_POTION) {
         return Qy.EMPTY;
      } else {
         Qy itemstack1 = new Qy(NK.TIPPED_ARROW, 8);
         Wg.addPotionToItemStack(itemstack1, Wg.getPotionFromItem(itemstack));
         Wg.appendEffects(itemstack1, Wg.getFullEffectsFromItem(itemstack));
         return itemstack1;
      }
   }

   public Qy getRecipeOutput() {
      return Qy.EMPTY;
   }

   public NonNullList<Qy> getRemainingItems(InventoryCrafting inv) {
      return NonNullList.withSize(inv.getSizeInventory(), Qy.EMPTY);
   }

   public boolean isDynamic() {
      return true;
   }

   public boolean canFit(int width, int height) {
      return width >= 2 && height >= 2;
   }
}
