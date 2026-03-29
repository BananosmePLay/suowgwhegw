package neo;

public class kV {
   private final int id;
   private final String caption;
   private final boolean startVisible;

   public kV(int p_i45531_1_, String p_i45531_2_, boolean p_i45531_3_) {
      this.id = p_i45531_1_;
      this.caption = p_i45531_2_;
      this.startVisible = p_i45531_3_;
   }

   public int getId() {
      return this.id;
   }

   public String getCaption() {
      return this.caption;
   }

   public boolean shouldStartVisible() {
      return this.startVisible;
   }
}
