package neo;

public class Wb extends VW {
   public Wb(boolean isBadEffectIn, int liquidColorIn) {
      super(isBadEffectIn, liquidColorIn);
   }

   public void removeAttributesModifiersFromEntity(Iw entityLivingBaseIn, FU attributeMapIn, int amplifier) {
      super.removeAttributesModifiersFromEntity(entityLivingBaseIn, attributeMapIn, amplifier);
      if (entityLivingBaseIn.getHealth() > entityLivingBaseIn.getMaxHealth()) {
         entityLivingBaseIn.setHealth(entityLivingBaseIn.getMaxHealth());
      }

   }
}
