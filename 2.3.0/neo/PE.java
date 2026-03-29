package neo;

import net.minecraft.util.NonNullList;

public class PE extends PI {
   private final boolean cooked;

   public PE(boolean cooked) {
      super(0, 0.0F, false);
      this.cooked = cooked;
   }

   public int getHealAmount(Qy stack) {
      PD itemfishfood$fishtype = PD.byItemStack(stack);
      return this.cooked && itemfishfood$fishtype.canCook() ? itemfishfood$fishtype.getCookedHealAmount() : itemfishfood$fishtype.getUncookedHealAmount();
   }

   public float getSaturationModifier(Qy stack) {
      PD itemfishfood$fishtype = PD.byItemStack(stack);
      return this.cooked && itemfishfood$fishtype.canCook() ? itemfishfood$fishtype.getCookedSaturationModifier() : itemfishfood$fishtype.getUncookedSaturationModifier();
   }

   protected void onFoodEaten(Qy stack, bij worldIn, ME player) {
      PD itemfishfood$fishtype = PD.byItemStack(stack);
      if (itemfishfood$fishtype == PD.PUFFERFISH) {
         player.addPotionEffect(new VZ(NL.POISON, 1200, 3));
         player.addPotionEffect(new VZ(NL.HUNGER, 300, 2));
         player.addPotionEffect(new VZ(NL.NAUSEA, 300, 1));
      }

      super.onFoodEaten(stack, worldIn, player);
   }

   public void getSubItems(EN tab, NonNullList<Qy> items) {
      if (this.isInCreativeTab(tab)) {
         PD[] var3 = PD.values();
         int var4 = var3.length;

         for(int var5 = 0; var5 < var4; ++var5) {
            PD itemfishfood$fishtype = var3[var5];
            if (!this.cooked || itemfishfood$fishtype.canCook()) {
               items.add(new Qy(this, 1, itemfishfood$fishtype.getMetadata()));
            }
         }
      }

   }

   public String getTranslationKey(Qy stack) {
      PD itemfishfood$fishtype = PD.byItemStack(stack);
      return this.getTranslationKey() + "." + itemfishfood$fishtype.getTranslationKey() + "." + (this.cooked && itemfishfood$fishtype.canCook() ? "cooked" : "raw");
   }
}
