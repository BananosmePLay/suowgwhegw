package neo;

public class bjZ {
   private String modelVariableName;
   private String expressionText;
   private bjW modelVariable;
   private blX expression;

   public boolean initialize(bjS mr) {
      this.modelVariable = mr.getModelVariable(this.modelVariableName);
      if (this.modelVariable == null) {
         XH.warn("Model variable not found: " + this.modelVariableName);
         return false;
      } else {
         try {
            blM expressionparser = new blM(mr);
            this.expression = expressionparser.parseFloat(this.expressionText);
            return true;
         } catch (bmd var3) {
            bmd parseexception = var3;
            XH.warn("Error parsing expression: " + this.expressionText);
            XH.warn(parseexception.getClass().getName() + ": " + parseexception.getMessage());
            return false;
         }
      }
   }

   public bjZ(String modelVariableName, String expressionText) {
      this.modelVariableName = modelVariableName;
      this.expressionText = expressionText;
   }

   public void update() {
      float f = this.expression.eval();
      this.modelVariable.setValue(f);
   }
}
