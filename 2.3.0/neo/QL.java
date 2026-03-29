package neo;

public class QL {
   public static final QL INFINITE = new QL(0L) {
      public void read(long bits) {
      }
   };
   private final long max;
   private long read;

   public QL(long max) {
      this.max = max;
   }

   public void read(long bits) {
      this.read += bits / 8L;
      if (this.read > this.max) {
         throw new RuntimeException("Tried to read NBT tag that was too big; tried to allocate: " + this.read + "bytes where max allowed: " + this.max);
      }
   }
}
