package neo;

import java.util.Random;

class Mm implements Mk {
   public Qy enchantedItemStack;
   public Mo priceInfo;

   public Mm(OL p_i45814_1_, Mo p_i45814_2_) {
      this.enchantedItemStack = new Qy(p_i45814_1_);
      this.priceInfo = p_i45814_2_;
   }

   public void addMerchantRecipe(IH merchant, YX recipeList, Random random) {
      int i = 1;
      if (this.priceInfo != null) {
         i = this.priceInfo.getPrice(random);
      }

      Qy itemstack = new Qy(NK.EMERALD, i, 0);
      Qy itemstack1 = Ft.addRandomEnchantment(random, new Qy(this.enchantedItemStack.getItem(), 1, this.enchantedItemStack.getMetadata()), 5 + random.nextInt(15), false);
      recipeList.add(new YW(itemstack, itemstack1));
   }
}
