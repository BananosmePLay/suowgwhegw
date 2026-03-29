package neo;

public class Ru<T> {
   private final Rd<T> key;
   private T value;
   private boolean dirty;

   public Ru(Rd<T> keyIn, T valueIn) {
      this.key = keyIn;
      this.value = valueIn;
      this.dirty = true;
   }

   public Rd<T> getKey() {
      return this.key;
   }

   public void setValue(T valueIn) {
      this.value = valueIn;
   }

   public T getValue() {
      return this.value;
   }

   public boolean isDirty() {
      return this.dirty;
   }

   public void setDirty(boolean dirtyIn) {
      this.dirty = dirtyIn;
   }

   public Ru<T> copy() {
      return new Ru(this.key, this.key.getSerializer().copyValue(this.value));
   }

   // $FF: synthetic method
   static boolean access$002(Ru x0, boolean x1) {
      return x0.dirty = x1;
   }
}
