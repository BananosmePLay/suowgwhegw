package neo;

public class bnp {
   private bnt[] playerItemModels = new bnt[0];
   private boolean initialized = false;

   public bnp() {
   }

   public void renderPlayerItems(nM modelBiped, jf player, float scale, float partialTicks) {
      if (this.initialized) {
         for(int i = 0; i < this.playerItemModels.length; ++i) {
            bnt playeritemmodel = this.playerItemModels[i];
            playeritemmodel.render(modelBiped, player, scale, partialTicks);
         }
      }

   }

   public boolean isInitialized() {
      return this.initialized;
   }

   public void setInitialized(boolean initialized) {
      this.initialized = initialized;
   }

   public bnt[] getPlayerItemModels() {
      return this.playerItemModels;
   }

   public void addPlayerItemModel(bnt playerItemModel) {
      this.playerItemModels = (bnt[])((bnt[])XH.addObjectToArray(this.playerItemModels, playerItemModel));
   }
}
