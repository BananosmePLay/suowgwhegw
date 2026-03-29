package neo;

public class boL extends yO {
   public boL() {
      this.loadTexture((AA)null);
   }

   public void loadTexture(AA resourcemanager) {
      int[] aint = bps.createAIntImage(1, -1);
      bps.setupTexture(this.getMultiTexID(), aint, 1, 1, false, false);
   }
}
