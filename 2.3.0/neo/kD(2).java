package neo;

enum kD {
   LOCKED(0, 146),
   LOCKED_HOVER(0, 166),
   LOCKED_DISABLED(0, 186),
   UNLOCKED(20, 146),
   UNLOCKED_HOVER(20, 166),
   UNLOCKED_DISABLED(20, 186);

   private final int x;
   private final int y;

   private kD(int xIn, int yIn) {
      this.x = xIn;
      this.y = yIn;
   }

   public int getX() {
      return this.x;
   }

   public int getY() {
      return this.y;
   }
}
