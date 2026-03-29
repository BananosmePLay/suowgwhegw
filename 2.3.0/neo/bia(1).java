package neo;

public class bia implements Runnable {
   private final bhZ data;

   public bia(bhZ dataIn) {
      this.data = dataIn;
   }

   public void run() {
      this.data.markDirty();
   }
}
