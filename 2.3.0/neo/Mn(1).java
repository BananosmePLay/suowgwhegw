package neo;

import java.util.Random;

class Mn implements Mk {
   public Qy itemToBuy;
   public Mo priceInfo;

   public Mn(OL par1Item, Mo priceInfo) {
      this.itemToBuy = new Qy(par1Item);
      this.priceInfo = priceInfo;
   }

   public Mn(Qy stack, Mo priceInfo) {
      this.itemToBuy = stack;
      this.priceInfo = priceInfo;
   }

   public void addMerchantRecipe(IH merchant, YX recipeList, Random random) {
      int i = 1;
      if (this.priceInfo != null) {
         i = this.priceInfo.getPrice(random);
      }

      Qy itemstack;
      Qy itemstack1;
      if (i < 0) {
         itemstack = new Qy(NK.EMERALD);
         itemstack1 = new Qy(this.itemToBuy.getItem(), -i, this.itemToBuy.getMetadata());
      } else {
         itemstack = new Qy(NK.EMERALD, i, 0);
         itemstack1 = new Qy(this.itemToBuy.getItem(), 1, this.itemToBuy.getMetadata());
      }

      recipeList.add(new YW(itemstack, itemstack1));
   }
}
