package neo;

public class bon extends bol {
   public static final String[] PROPERTY_VALUES = new String[]{"default", "true", "false"};
   public static final String[] USER_VALUES = new String[]{"Default", "ON", "OFF"};

   public bon(String propertyName, String userName, int defaultValue) {
      super(propertyName, PROPERTY_VALUES, userName, USER_VALUES, defaultValue);
   }

   public String getUserValue() {
      if (this.isDefault()) {
         return bmW.getDefault();
      } else if (this.isTrue()) {
         return bmW.getOn();
      } else {
         return this.isFalse() ? bmW.getOff() : super.getUserValue();
      }
   }

   public boolean isDefault() {
      return this.getValue() == 0;
   }

   public boolean isTrue() {
      return this.getValue() == 1;
   }

   public boolean isFalse() {
      return this.getValue() == 2;
   }
}
