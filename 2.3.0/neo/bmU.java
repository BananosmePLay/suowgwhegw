package neo;

public class bmU {
   public long timeStartNano = 0L;
   public long timeNano = 0L;

   public bmU() {
   }

   public void start() {
      if (bmV.active && this.timeStartNano == 0L) {
         this.timeStartNano = System.nanoTime();
      }

   }

   public void end() {
      if (bmV.active && this.timeStartNano != 0L) {
         this.timeNano += System.nanoTime() - this.timeStartNano;
         this.timeStartNano = 0L;
      }

   }

   private void reset() {
      this.timeNano = 0L;
      this.timeStartNano = 0L;
   }

   // $FF: synthetic method
   static void access$000(bmU x0) {
      x0.reset();
   }
}
