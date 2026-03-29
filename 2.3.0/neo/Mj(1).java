package neo;

import java.util.Random;

class Mj implements Mk {
   public Qy buyingItemStack;
   public Mo buyingPriceInfo;
   public Qy sellingItemstack;
   public Mo sellingPriceInfo;

   public Mj(OL p_i45813_1_, Mo p_i45813_2_, OL p_i45813_3_, Mo p_i45813_4_) {
      this.buyingItemStack = new Qy(p_i45813_1_);
      this.buyingPriceInfo = p_i45813_2_;
      this.sellingItemstack = new Qy(p_i45813_3_);
      this.sellingPriceInfo = p_i45813_4_;
   }

   public void addMerchantRecipe(IH merchant, YX recipeList, Random random) {
      int i = this.buyingPriceInfo.getPrice(random);
      int j = this.sellingPriceInfo.getPrice(random);
      recipeList.add(new YW(new Qy(this.buyingItemStack.getItem(), i, this.buyingItemStack.getMetadata()), new Qy(NK.EMERALD), new Qy(this.sellingItemstack.getItem(), j, this.sellingItemstack.getMetadata())));
   }
}
