package neo;

public enum MN {
   DISALLOWED,
   ALLOWED,
   CREATIVE_ONLY;

   private MN() {
   }

   public static MN getByOrdinal(int ordinal) {
      if (ordinal < 0 || ordinal > values().length) {
         ordinal = 0;
      }

      return values()[ordinal];
   }
}
