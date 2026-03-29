package neo;

public enum bjg {
   CLEAR,
   RAIN,
   THUNDER;

   private bjg() {
   }

   public static bjg getWeather(bij world, float partialTicks) {
      float f = world.getThunderStrength(partialTicks);
      if (f > 0.5F) {
         return THUNDER;
      } else {
         float f1 = world.getRainStrength(partialTicks);
         return f1 > 0.5F ? RAIN : CLEAR;
      }
   }
}
