package neo;

class baY {
   private String valueString;
   private boolean valueBoolean;
   private int valueInteger;
   private double valueDouble;
   private final baZ type;

   public baY(String value, baZ type) {
      this.type = type;
      this.setValue(value);
   }

   public void setValue(String value) {
      this.valueString = value;
      if (value != null) {
         if (value.equals("false")) {
            this.valueBoolean = false;
            return;
         }

         if (value.equals("true")) {
            this.valueBoolean = true;
            return;
         }
      }

      this.valueBoolean = Boolean.parseBoolean(value);
      this.valueInteger = this.valueBoolean ? 1 : 0;

      try {
         this.valueInteger = Integer.parseInt(value);
      } catch (NumberFormatException var4) {
      }

      try {
         this.valueDouble = Double.parseDouble(value);
      } catch (NumberFormatException var3) {
      }

   }

   public String getString() {
      return this.valueString;
   }

   public boolean getBoolean() {
      return this.valueBoolean;
   }

   public int getInt() {
      return this.valueInteger;
   }

   public baZ getType() {
      return this.type;
   }
}
