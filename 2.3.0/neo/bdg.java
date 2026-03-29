package neo;

public enum bdg {
   NORMAL,
   MESA;

   private bdg() {
   }

   public static bdg byId(int id) {
      return id >= 0 && id < values().length ? values()[id] : NORMAL;
   }
}
