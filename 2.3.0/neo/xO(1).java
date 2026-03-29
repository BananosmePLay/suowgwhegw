package neo;

public enum xO {
   FRONT(1028),
   BACK(1029),
   FRONT_AND_BACK(1032);

   public final int mode;

   private xO(int modeIn) {
      this.mode = modeIn;
   }
}
