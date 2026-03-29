package neo;

import com.google.common.collect.Lists;
import it.unimi.dsi.fastutil.ints.IntList;
import java.util.BitSet;
import java.util.List;

public class mB {
   private List<NT> recipes = Lists.newArrayList();
   private final BitSet craftable = new BitSet();
   private final BitSet canFit = new BitSet();
   private final BitSet inBook = new BitSet();
   private boolean singleResultItem = true;

   public mB() {
   }

   public boolean isNotEmpty() {
      return !this.inBook.isEmpty();
   }

   public void updateKnownRecipes(XK book) {
      for(int i = 0; i < this.recipes.size(); ++i) {
         this.inBook.set(i, book.isUnlocked((NT)this.recipes.get(i)));
      }

   }

   public void canCraft(BR handler, int width, int height, XK book) {
      for(int i = 0; i < this.recipes.size(); ++i) {
         NT irecipe = (NT)this.recipes.get(i);
         boolean flag = irecipe.canFit(width, height) && book.isUnlocked(irecipe);
         this.canFit.set(i, flag);
         this.craftable.set(i, flag && handler.canCraft(irecipe, (IntList)null));
      }

   }

   public boolean isCraftable(NT recipe) {
      return this.craftable.get(this.recipes.indexOf(recipe));
   }

   public boolean containsCraftableRecipes() {
      return !this.craftable.isEmpty();
   }

   public boolean containsValidRecipes() {
      return !this.canFit.isEmpty();
   }

   public List<NT> getRecipes() {
      return this.recipes;
   }

   public List<NT> getRecipes(boolean onlyCraftable) {
      List<NT> list = Lists.newArrayList();

      for(int i = this.inBook.nextSetBit(0); i >= 0; i = this.inBook.nextSetBit(i + 1)) {
         if ((onlyCraftable ? this.craftable : this.canFit).get(i)) {
            list.add(this.recipes.get(i));
         }
      }

      return list;
   }

   public List<NT> getDisplayRecipes(boolean onlyCraftable) {
      List<NT> list = Lists.newArrayList();

      for(int i = this.inBook.nextSetBit(0); i >= 0; i = this.inBook.nextSetBit(i + 1)) {
         if (this.canFit.get(i) && this.craftable.get(i) == onlyCraftable) {
            list.add(this.recipes.get(i));
         }
      }

      return list;
   }

   public void add(NT recipe) {
      this.recipes.add(recipe);
      if (this.singleResultItem) {
         Qy itemstack = ((NT)this.recipes.get(0)).getRecipeOutput();
         Qy itemstack1 = recipe.getRecipeOutput();
         this.singleResultItem = Qy.areItemsEqual(itemstack, itemstack1) && Qy.areItemStackTagsEqual(itemstack, itemstack1);
      }

   }

   public boolean hasSingleResultItem() {
      return this.singleResultItem;
   }
}
