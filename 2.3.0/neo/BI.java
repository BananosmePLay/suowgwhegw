package neo;

public enum BI implements BJ {
   NORMAL(false),
   ADVANCED(true);

   final boolean isAdvanced;

   private BI(boolean advanced) {
      this.isAdvanced = advanced;
   }

   public boolean isAdvanced() {
      return this.isAdvanced;
   }
}
