package neo;

public class blI implements blX {
   private float value;

   public blI(float value) {
      this.value = value;
   }

   public float eval() {
      return this.value;
   }

   public String toString() {
      return "" + this.value;
   }
}
