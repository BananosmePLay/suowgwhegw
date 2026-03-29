package neo;

import com.google.common.base.Predicate;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;
import javax.annotation.Nullable;

public class NS implements Predicate<Qy> {
   public static final NS EMPTY = new NS(new Qy[0]) {
      public boolean apply(@Nullable Qy p_apply_1_) {
         return p_apply_1_.isEmpty();
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((Qy)var1);
      }
   };
   private final Qy[] matchingStacks;
   private IntList matchingStacksPacked;

   private NS(Qy... p_i47503_1_) {
      this.matchingStacks = p_i47503_1_;
   }

   public Qy[] getMatchingStacks() {
      return this.matchingStacks;
   }

   public boolean apply(@Nullable Qy p_apply_1_) {
      if (p_apply_1_ == null) {
         return false;
      } else {
         Qy[] var2 = this.matchingStacks;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Qy itemstack = var2[var4];
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
         Qy[] var1 = this.matchingStacks;
         int var2 = var1.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            Qy itemstack = var1[var3];
            this.matchingStacksPacked.add(BR.pack(itemstack));
         }

         this.matchingStacksPacked.sort(IntComparators.NATURAL_COMPARATOR);
      }

      return this.matchingStacksPacked;
   }

   public static NS fromItem(OL p_193367_0_) {
      return fromStacks(new Qy(p_193367_0_, 1, 32767));
   }

   public static NS fromItems(OL... items) {
      Qy[] aitemstack = new Qy[items.length];

      for(int i = 0; i < items.length; ++i) {
         aitemstack[i] = new Qy(items[i]);
      }

      return fromStacks(aitemstack);
   }

   public static NS fromStacks(Qy... stacks) {
      if (stacks.length > 0) {
         Qy[] var1 = stacks;
         int var2 = stacks.length;

         for(int var3 = 0; var3 < var2; ++var3) {
            Qy itemstack = var1[var3];
            if (!itemstack.isEmpty()) {
               return new NS(stacks);
            }
         }
      }

      return EMPTY;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public boolean apply(@Nullable Object var1) {
      return this.apply((Qy)var1);
   }

   // $FF: synthetic method
   NS(Qy[] x0, Object x1) {
      this(x0);
   }
}
