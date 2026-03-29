package neo;

public class VX extends VW {
   protected VX(boolean isBadEffectIn, int liquidColorIn) {
      super(isBadEffectIn, liquidColorIn);
   }

   public void removeAttributesModifiersFromEntity(Iw entityLivingBaseIn, FU attributeMapIn, int amplifier) {
      entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() - (float)(4 * (amplifier + 1)));
      super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
   }

   public void applyAttributesModifiersToEntity(Iw entityLivingBaseIn, FU attributeMapIn, int amplifier) {
      entityLivingBaseIn.setAbsorptionAmount(entityLivingBaseIn.getAbsorptionAmount() + (float)(4 * (amplifier + 1)));
      super.applyAttributesModifiersToEntity(entityLivingBaseIn, attributeMapIn, amplifier);
   }
}
