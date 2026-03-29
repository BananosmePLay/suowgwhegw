package neo;

public class bjW implements blX {
   private String name;
   private ow modelRenderer;
   private bjY enumModelVariable;

   public bjW(String name, ow modelRenderer, bjY enumModelVariable) {
      this.name = name;
      this.modelRenderer = modelRenderer;
      this.enumModelVariable = enumModelVariable;
   }

   public float eval() {
      return this.getValue();
   }

   public float getValue() {
      return this.enumModelVariable.getFloat(this.modelRenderer);
   }

   public void setValue(float value) {
      this.enumModelVariable.setFloat(this.modelRenderer, value);
   }

   public String toString() {
      return this.name;
   }
}
