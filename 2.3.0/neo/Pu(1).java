package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;

public class Pu extends PU {
   protected Pu() {
      this.setCreativeTab(EN.MISC);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = PT.setupNewMap(worldIn, playerIn.posX, playerIn.posZ, (byte)0, true, false);
      Qy itemstack1 = playerIn.getHeldItem(handIn);
      itemstack1.shrink(1);
      if (itemstack1.isEmpty()) {
         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      } else {
         if (!playerIn.inventory.addItemStackToInventory(itemstack.copy())) {
            playerIn.dropItem(itemstack, false);
         }

         playerIn.addStat(XV.getObjectUseStats(this));
         return new ActionResult(EnumActionResult.SUCCESS, itemstack1);
      }
   }
}
