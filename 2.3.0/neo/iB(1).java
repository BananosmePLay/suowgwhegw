package neo;

public enum iB {
   NONE(0),
   LINEAR(2);

   private final int type;

   private iB(int typeIn) {
      this.type = typeIn;
   }

   public int getTypeInt() {
      return this.type;
   }
}
