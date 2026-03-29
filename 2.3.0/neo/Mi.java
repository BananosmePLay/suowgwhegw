package neo;

import java.util.Random;

class Mi implements Mk {
   public OL buyingItem;
   public Mo price;

   public Mi(OL itemIn, Mo priceIn) {
      this.buyingItem = itemIn;
      this.price = priceIn;
   }

   public void addMerchantRecipe(IH merchant, YX recipeList, Random random) {
      int i = 1;
      if (this.price != null) {
         i = this.price.getPrice(random);
      }

      recipeList.add(new YW(new Qy(this.buyingItem, i, 0), NK.EMERALD));
   }
}
