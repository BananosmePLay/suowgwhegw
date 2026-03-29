package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;

public class Pf extends OL {
   public Pf() {
      this.setMaxStackSize(1);
      this.setCreativeTab(EN.MISC);
   }

   public Qy onItemUseFinish(Qy stack, bij worldIn, Iw entityLiving) {
      if (entityLiving instanceof MG) {
         MG entityplayermp = (MG)entityLiving;
         bY.CONSUME_ITEM.trigger(entityplayermp, stack);
         entityplayermp.addStat(XV.getObjectUseStats(this));
      }

      if (entityLiving instanceof ME && !((ME)entityLiving).capabilities.isCreativeMode) {
         stack.shrink(1);
      }

      if (!worldIn.isRemote) {
         entityLiving.clearActivePotions();
      }

      return stack.isEmpty() ? new Qy(NK.BUCKET) : stack;
   }

   public int getMaxItemUseDuration(Qy stack) {
      return 32;
   }

   public Ol getItemUseAction(Qy stack) {
      return Ol.DRINK;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      playerIn.setActiveHand(handIn);
      return new ActionResult(EnumActionResult.SUCCESS, playerIn.getHeldItem(handIn));
   }
}
