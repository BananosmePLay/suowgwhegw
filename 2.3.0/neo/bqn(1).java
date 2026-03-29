package neo;

public class bqn {
   private int startValue;
   private int value;

   public bqn(int startValue) {
      this.startValue = startValue;
      this.value = startValue;
   }

   public synchronized int nextValue() {
      int i = this.value++;
      return i;
   }

   public synchronized void reset() {
      this.value = this.startValue;
   }

   public int getValue() {
      return this.value;
   }
}
