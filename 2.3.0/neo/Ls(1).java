package neo;

import javax.annotation.Nullable;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentTranslation;

public class Ls implements IH {
   private final InventoryMerchant merchantInventory;
   private final ME customer;
   private YX recipeList;
   private final ITextComponent name;

   public Ls(ME customerIn, ITextComponent nameIn) {
      this.customer = customerIn;
      this.name = nameIn;
      this.merchantInventory = new InventoryMerchant(customerIn, this);
   }

   @Nullable
   public ME getCustomer() {
      return this.customer;
   }

   public void setCustomer(@Nullable ME player) {
   }

   @Nullable
   public YX getRecipes(ME player) {
      return this.recipeList;
   }

   public void setRecipes(@Nullable YX recipeList) {
      this.recipeList = recipeList;
   }

   public void useRecipe(YW recipe) {
      recipe.incrementToolUses();
   }

   public void verifySellingItem(Qy stack) {
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.name != null ? this.name : new TextComponentTranslation("entity.Villager.name", new Object[0]));
   }

   public bij getWorld() {
      return this.customer.world;
   }

   public BlockPos getPos() {
      return new BlockPos(this.customer);
   }
}
