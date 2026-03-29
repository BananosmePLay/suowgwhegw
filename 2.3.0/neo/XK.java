package neo;

import java.util.BitSet;
import javax.annotation.Nullable;

public class XK {
   protected final BitSet recipes = new BitSet();
   protected final BitSet newRecipes = new BitSet();
   protected boolean isGuiOpen;
   protected boolean isFilteringCraftable;

   public XK() {
   }

   public void copyFrom(XK that) {
      this.recipes.clear();
      this.newRecipes.clear();
      this.recipes.or(that.recipes);
      this.newRecipes.or(that.newRecipes);
   }

   public void unlock(NT recipe) {
      if (!recipe.isDynamic()) {
         this.recipes.set(getRecipeId(recipe));
      }

   }

   public boolean isUnlocked(@Nullable NT recipe) {
      return this.recipes.get(getRecipeId(recipe));
   }

   public void lock(NT recipe) {
      int i = getRecipeId(recipe);
      this.recipes.clear(i);
      this.newRecipes.clear(i);
   }

   protected static int getRecipeId(@Nullable NT recipe) {
      return NP.REGISTRY.getIDForObject(recipe);
   }

   public boolean isNew(NT recipe) {
      return this.newRecipes.get(getRecipeId(recipe));
   }

   public void markSeen(NT recipe) {
      this.newRecipes.clear(getRecipeId(recipe));
   }

   public void markNew(NT recipe) {
      this.newRecipes.set(getRecipeId(recipe));
   }

   public boolean isGuiOpen() {
      return this.isGuiOpen;
   }

   public void setGuiOpen(boolean open) {
      this.isGuiOpen = open;
   }

   public boolean isFilteringCraftable() {
      return this.isFilteringCraftable;
   }

   public void setFilteringCraftable(boolean shouldFilter) {
      this.isFilteringCraftable = shouldFilter;
   }
}
