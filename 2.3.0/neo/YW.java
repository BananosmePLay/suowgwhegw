package neo;

public class YW {
   private Qy itemToBuy;
   private Qy secondItemToBuy;
   private Qy itemToSell;
   private int toolUses;
   private int maxTradeUses;
   private boolean rewardsExp;

   public YW(QQ tagCompound) {
      this.itemToBuy = Qy.EMPTY;
      this.secondItemToBuy = Qy.EMPTY;
      this.itemToSell = Qy.EMPTY;
      this.readFromTags(tagCompound);
   }

   public YW(Qy buy1, Qy buy2, Qy sell) {
      this(buy1, buy2, sell, 0, 7);
   }

   public YW(Qy buy1, Qy buy2, Qy sell, int toolUsesIn, int maxTradeUsesIn) {
      this.itemToBuy = Qy.EMPTY;
      this.secondItemToBuy = Qy.EMPTY;
      this.itemToSell = Qy.EMPTY;
      this.itemToBuy = buy1;
      this.secondItemToBuy = buy2;
      this.itemToSell = sell;
      this.toolUses = toolUsesIn;
      this.maxTradeUses = maxTradeUsesIn;
      this.rewardsExp = true;
   }

   public YW(Qy buy1, Qy sell) {
      this(buy1, Qy.EMPTY, sell);
   }

   public YW(Qy buy1, OL sellItem) {
      this(buy1, new Qy(sellItem));
   }

   public Qy getItemToBuy() {
      return this.itemToBuy;
   }

   public Qy getSecondItemToBuy() {
      return this.secondItemToBuy;
   }

   public boolean hasSecondItemToBuy() {
      return !this.secondItemToBuy.isEmpty();
   }

   public Qy getItemToSell() {
      return this.itemToSell;
   }

   public int getToolUses() {
      return this.toolUses;
   }

   public int getMaxTradeUses() {
      return this.maxTradeUses;
   }

   public void incrementToolUses() {
      ++this.toolUses;
   }

   public void increaseMaxTradeUses(int increment) {
      this.maxTradeUses += increment;
   }

   public boolean isRecipeDisabled() {
      return this.toolUses >= this.maxTradeUses;
   }

   public void compensateToolUses() {
      this.toolUses = this.maxTradeUses;
   }

   public boolean getRewardsExp() {
      return this.rewardsExp;
   }

   public void readFromTags(QQ tagCompound) {
      QQ nbttagcompound = tagCompound.getCompoundTag("buy");
      this.itemToBuy = new Qy(nbttagcompound);
      QQ nbttagcompound1 = tagCompound.getCompoundTag("sell");
      this.itemToSell = new Qy(nbttagcompound1);
      if (tagCompound.hasKey("buyB", 10)) {
         this.secondItemToBuy = new Qy(tagCompound.getCompoundTag("buyB"));
      }

      if (tagCompound.hasKey("uses", 99)) {
         this.toolUses = tagCompound.getInteger("uses");
      }

      if (tagCompound.hasKey("maxUses", 99)) {
         this.maxTradeUses = tagCompound.getInteger("maxUses");
      } else {
         this.maxTradeUses = 7;
      }

      if (tagCompound.hasKey("rewardExp", 1)) {
         this.rewardsExp = tagCompound.getBoolean("rewardExp");
      } else {
         this.rewardsExp = true;
      }

   }

   public QQ writeToTags() {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setTag("buy", this.itemToBuy.writeToNBT(new QQ()));
      nbttagcompound.setTag("sell", this.itemToSell.writeToNBT(new QQ()));
      if (!this.secondItemToBuy.isEmpty()) {
         nbttagcompound.setTag("buyB", this.secondItemToBuy.writeToNBT(new QQ()));
      }

      nbttagcompound.setInteger("uses", this.toolUses);
      nbttagcompound.setInteger("maxUses", this.maxTradeUses);
      nbttagcompound.setBoolean("rewardExp", this.rewardsExp);
      return nbttagcompound;
   }
}
