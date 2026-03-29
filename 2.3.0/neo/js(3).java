package neo;

public enum js {
   OBTAINED(0),
   UNOBTAINED(1);

   private final int id;

   private js(int id) {
      this.id = id;
   }

   public int getId() {
      return this.id;
   }
}
