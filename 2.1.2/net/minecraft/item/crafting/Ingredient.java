package net.minecraft.item.crafting;

import com.google.common.base.Predicate;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import javax.annotation.Nullable;
import net.minecraft.client.util.RecipeItemHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class Ingredient implements Predicate<ItemStack> {
   public static final Ingredient EMPTY = new Ingredient(new ItemStack[0]) {
      public boolean apply(@Nullable ItemStack p_apply_1_) {
         return p_apply_1_.isEmpty();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((ItemStack)var1);
      }
   };
   private final ItemStack[] matchingStacks;
   private IntList matchingStacksPacked;

   private Ingredient(ItemStack... p_i47503_1_) {
      this.matchingStacks = p_i47503_1_;
   }

   public ItemStack[] getMatchingStacks() {
      return this.matchingStacks;
   }

   public boolean apply(@Nullable ItemStack p_apply_1_) {
      if (p_apply_1_ == null) {
         return false;
      } else {
         ItemStack[] var2 = this.matchingStacks;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            ItemStack itemstack = var2[var4];
            if (itemstack.getItem() == p_apply_1_.getItem()) {
               int i = itemstack.getMetadata();
               if (i == 32767 || i == p_apply_1_.getMetadata()) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public IntList getValidItemStacksPacked() {
      if (this.matchingStacksPacked == null) {
         this.matchingStacksPacked = new IntArrayList(this.matchingStacks.length);
         ItemStack[] var1 = this.matchingStacks;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            ItemStack itemstack = var1[var3];
            this.matchingStacksPacked.add(RecipeItemHelper.pack(itemstack));
         }

         this.matchingStacksPacked.sort(IntComparators.NATURAL_COMPARATOR);
      }

      return this.matchingStacksPacked;
   }

   public static Ingredient fromItem(Item p_193367_0_) {
      return fromStacks(new ItemStack(p_193367_0_, 1, 32767));
   }

   public static Ingredient fromItems(Item... items) {
      ItemStack[] aitemstack = new ItemStack[items.length];

      for(int i = 0; i < items.length; ++i) {
         aitemstack[i] = new ItemStack(items[i]);
      }

      return fromStacks(aitemstack);
   }

   public static Ingredient fromStacks(ItemStack... stacks) {
      if (stacks.length > 0) {
         ItemStack[] var1 = stacks;
         int var2 = stacks.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            ItemStack itemstack = var1[var3];
            if (!itemstack.isEmpty()) {
               return new Ingredient(stacks);
            }
         }
      }

      return EMPTY;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((ItemStack)var1);
   }

   // $FF: synthetic method
   Ingredient(ItemStack[] x0, Object x1) {
      this(x0);
   }
}
