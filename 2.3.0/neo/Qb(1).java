package neo;

import net.minecraft.util.EnumHand;

public class Qb extends OL {
   public Qb() {
      this.setCreativeTab(EN.TOOLS);
   }

   public boolean itemInteractionForEntity(Qy stack, ME playerIn, Iw target, EnumHand hand) {
      if (stack.hasDisplayName() && !(target instanceof ME)) {
         target.setCustomNameTag(stack.getDisplayName());
         if (target instanceof Iu) {
            ((Iu)target).enablePersistence();
         }

         stack.shrink(1);
         return true;
      } else {
         return false;
      }
   }
}
