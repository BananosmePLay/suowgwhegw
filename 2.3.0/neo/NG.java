package neo;

public abstract class NG extends EP {
   protected boolean successful = true;

   public NG() {
   }

   protected void playDispenseSound(ET source) {
      source.getWorld().playEvent(this.successful ? 1000 : 1001, source.getBlockPos(), 0);
   }
}
