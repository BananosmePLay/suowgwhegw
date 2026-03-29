package neo;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.NonNullList;

public interface NT {
   boolean matches(InventoryCrafting var1, bij var2);

   Qy getCraftingResult(InventoryCrafting var1);

   boolean canFit(int var1, int var2);

   Qy getRecipeOutput();

   NonNullList<Qy> getRemainingItems(InventoryCrafting var1);

   default NonNullList<NS> getIngredients() {
      return NonNullList.create();
   }

   default boolean isDynamic() {
      return false;
   }

   default String getGroup() {
      return "";
   }
}
