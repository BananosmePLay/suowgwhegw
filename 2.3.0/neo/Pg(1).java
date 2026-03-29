package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;

public class Pg extends OL {
   public Pg() {
      this.setCreativeTab(EN.TRANSPORTATION);
      this.setMaxStackSize(1);
      this.setMaxDamage(25);
   }

   public boolean isFull3D() {
      return true;
   }

   public boolean shouldRotateAroundWhenRendering() {
      return true;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (worldIn.isRemote) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else {
         if (playerIn.isRiding() && playerIn.getRidingEntity() instanceof LQ) {
            LQ entitypig = (LQ)playerIn.getRidingEntity();
            if (itemstack.getMaxDamage() - itemstack.getMetadata() >= 7 && entitypig.boost()) {
               itemstack.damageItem(7, playerIn);
               if (itemstack.isEmpty()) {
                  Qy itemstack1 = new Qy(NK.FISHING_ROD);
                  itemstack1.setTagCompound(itemstack.getTagCompound());
                  return new ActionResult(EnumActionResult.SUCCESS, itemstack1);
               }

               return new ActionResult(EnumActionResult.SUCCESS, itemstack);
            }
         }

         playerIn.addStat(XV.getObjectUseStats(this));
         return new ActionResult(EnumActionResult.PASS, itemstack);
      }
   }
}
