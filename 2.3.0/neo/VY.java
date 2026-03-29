package neo;

public class VY extends VW {
   protected final double bonusPerLevel;

   protected VY(boolean isBadEffectIn, int liquidColorIn, double bonusPerLevelIn) {
      super(isBadEffectIn, liquidColorIn);
      this.bonusPerLevel = bonusPerLevelIn;
   }

   public double getAttributeModifierAmount(int amplifier, FW modifier) {
      return this.bonusPerLevel * (double)(amplifier + 1);
   }
}
