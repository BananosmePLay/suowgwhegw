package neo;

import net.minecraft.util.NonNullList;

public class OO extends PI {
   public OO(int amount, float saturation, boolean isWolfFood) {
      super(amount, saturation, isWolfFood);
      this.setHasSubtypes(true);
   }

   public boolean hasEffect(Qy stack) {
      return super.hasEffect(stack) || stack.getMetadata() > 0;
   }

   public On getRarity(Qy stack) {
      return stack.getMetadata() == 0 ? On.RARE : On.EPIC;
   }

   protected void onFoodEaten(Qy stack, bij worldIn, ME player) {
      if (!worldIn.isRemote) {
         if (stack.getMetadata() > 0) {
            player.addPotionEffect(new VZ(NL.REGENERATION, 400, 1));
            player.addPotionEffect(new VZ(NL.RESISTANCE, 6000, 0));
            player.addPotionEffect(new VZ(NL.FIRE_RESISTANCE, 6000, 0));
            player.addPotionEffect(new VZ(NL.ABSORPTION, 2400, 3));
         } else {
            player.addPotionEffect(new VZ(NL.REGENERATION, 100, 1));
            player.addPotionEffect(new VZ(NL.ABSORPTION, 2400, 0));
         }
      }

   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         items.add(new Qy(this));
         items.add(new Qy(this, 1, 1));
      }

   }
}
