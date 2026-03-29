package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

public class Py extends OL {
   public Py() {
      this.maxStackSize = 16;
      this.setCreativeTab(EN.MISC);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (!playerIn.capabilities.isCreativeMode) {
         itemstack.shrink(1);
      }

      worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ENTITY_ENDERPEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      playerIn.getCooldownTracker().setCooldown(this, 20);
      if (!worldIn.isRemote) {
         IU entityenderpearl = new IU(worldIn, playerIn);
         entityenderpearl.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
         worldIn.spawnEntity(entityenderpearl);
      }

      playerIn.addStat(XV.getObjectUseStats(this));
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }
}
