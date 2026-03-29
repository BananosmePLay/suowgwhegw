package neo;

public class Wa extends VW {
   public Wa(boolean isBadEffectIn, int liquidColorIn) {
      super(isBadEffectIn, liquidColorIn);
   }

   public boolean isInstant() {
      return true;
   }

   public boolean isReady(int duration, int amplifier) {
      return duration >= 1;
   }
}
