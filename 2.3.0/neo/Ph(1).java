package neo;

import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;

public class Ph extends PI {
   public Ph(int amount, float saturation) {
      super(amount, saturation, false);
   }

   public Qy onItemUseFinish(Qy stack, bij worldIn, Iw entityLiving) {
      Qy itemstack = super.onItemUseFinish(stack, worldIn, entityLiving);
      if (!worldIn.isRemote) {
         double d0 = entityLiving.posX;
         double d1 = entityLiving.posY;
         double d2 = entityLiving.posZ;

         for(int i = 0; i < 16; ++i) {
            double d3 = entityLiving.posX + (entityLiving.getRNG().nextDouble() - 0.5) * 16.0;
            double d4 = MathHelper.clamp(entityLiving.posY + (double)(entityLiving.getRNG().nextInt(16) - 8), 0.0, (double)(worldIn.getActualHeight() - 1));
            double d5 = entityLiving.posZ + (entityLiving.getRNG().nextDouble() - 0.5) * 16.0;
            if (entityLiving.isRiding()) {
               entityLiving.dismountRidingEntity();
            }

            if (entityLiving.attemptTeleport(d3, d4, d5)) {
               worldIn.playSound((ME)null, d0, d1, d2, NO.ITEM_CHORUS_FRUIT_TELEPORT, SoundCategory.PLAYERS, 1.0F, 1.0F);
               entityLiving.playSound(NO.ITEM_CHORUS_FRUIT_TELEPORT, 1.0F, 1.0F);
               break;
            }
         }

         if (entityLiving instanceof ME) {
            ((ME)entityLiving).getCooldownTracker().setCooldown(this, 20);
         }
      }

      return itemstack;
   }
}
