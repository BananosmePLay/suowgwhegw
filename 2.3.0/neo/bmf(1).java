package neo;

public class bmf {
   private bmi type;
   private String text;

   public bmf(bmi type, String text) {
      this.type = type;
      this.text = text;
   }

   public bmi getType() {
      return this.type;
   }

   public String getText() {
      return this.text;
   }

   public String toString() {
      return this.text;
   }
}
