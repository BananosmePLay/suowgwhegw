package neo;

public class Yi {
   private final float[] colors;
   private int height;

   public Yi(float[] colorsIn) {
      this.colors = colorsIn;
      this.height = 1;
   }

   protected void incrementHeight() {
      ++this.height;
   }

   public float[] getColors() {
      return this.colors;
   }

   public int getHeight() {
      return this.height;
   }
}
