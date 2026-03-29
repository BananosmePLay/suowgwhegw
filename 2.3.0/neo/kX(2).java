package neo;

public class kX extends kV {
   private final lw formatter;
   private final float minValue;
   private final float maxValue;
   private final float initialValue;

   public kX(int p_i45530_1_, String p_i45530_2_, boolean p_i45530_3_, lw p_i45530_4_, float p_i45530_5_, float p_i45530_6_, float p_i45530_7_) {
      super(p_i45530_1_, p_i45530_2_, p_i45530_3_);
      this.formatter = p_i45530_4_;
      this.minValue = p_i45530_5_;
      this.maxValue = p_i45530_6_;
      this.initialValue = p_i45530_7_;
   }

   public lw getFormatter() {
      return this.formatter;
   }

   public float getMinValue() {
      return this.minValue;
   }

   public float getMaxValue() {
      return this.maxValue;
   }

   public float getInitalValue() {
      return this.initialValue;
   }
}
