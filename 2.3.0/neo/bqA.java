package neo;

public class bqA<T> {
   private final T item;
   private bqA<T> prev;
   private bqA<T> next;
   private bqB<T> parent;

   public bqA(T item) {
      this.item = item;
   }

   public T getItem() {
      return this.item;
   }

   public bqA<T> getPrev() {
      return this.prev;
   }

   public bqA<T> getNext() {
      return this.next;
   }

   private void setPrev(bqA<T> prev) {
      this.prev = prev;
   }

   private void setNext(bqA<T> next) {
      this.next = next;
   }

   private void setParent(bqB<T> parent) {
      this.parent = parent;
   }

   public String toString() {
      return "" + this.item;
   }

   // $FF: synthetic method
   static void access$000(bqA x0, bqA x1) {
      x0.setNext(x1);
   }

   // $FF: synthetic method
   static void access$100(bqA x0, bqA x1) {
      x0.setPrev(x1);
   }

   // $FF: synthetic method
   static void access$200(bqA x0, bqB x1) {
      x0.setParent(x1);
   }

   // $FF: synthetic method
   static bqB access$300(bqA x0) {
      return x0.parent;
   }

   // $FF: synthetic method
   static bqA access$400(bqA x0) {
      return x0.next;
   }
}
