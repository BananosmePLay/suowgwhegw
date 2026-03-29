package neo;

public class Ab implements Ad {
   private final float[] charWidths;
   private final float[] charLefts;
   private final float[] charSpacings;

   public Ab(float[] charWidthsIn, float[] charLeftsIn, float[] charSpacingsIn) {
      this.charWidths = charWidthsIn;
      this.charLefts = charLeftsIn;
      this.charSpacings = charSpacingsIn;
   }
}
