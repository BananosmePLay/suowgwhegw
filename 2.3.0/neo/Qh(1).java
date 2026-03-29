package neo;

import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

public class Qh extends OL {
   public Qh() {
      this.maxStackSize = 1;
      this.setCreativeTab(EN.TRANSPORTATION);
   }

   public boolean itemInteractionForEntity(Qy stack, ME playerIn, Iw target, EnumHand hand) {
      if (target instanceof LQ) {
         LQ entitypig = (LQ)target;
         if (!entitypig.getSaddled() && !entitypig.isChild()) {
            entitypig.setSaddled(true);
            entitypig.world.playSound(playerIn, entitypig.posX, entitypig.posY, entitypig.posZ, NO.ENTITY_PIG_SADDLE, SoundCategory.NEUTRAL, 0.5F, 1.0F);
            stack.shrink(1);
         }

         return true;
      } else {
         return false;
      }
   }
}
