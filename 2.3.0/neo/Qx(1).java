package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.translation.I18n;

public class Qx extends Qe {
   public Qx() {
   }

   public String getItemStackDisplayName(Qy stack) {
      return I18n.translateToLocal(Wg.getPotionFromItem(stack).getNamePrefixed("splash_potion.effect."));
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      Qy itemstack1 = playerIn.capabilities.isCreativeMode ? itemstack.copy() : itemstack.splitStack(1);
      worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ENTITY_SPLASH_POTION_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if (!worldIn.isRemote) {
         MY entitypotion = new MY(worldIn, playerIn, itemstack1);
         entitypotion.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.5F, 1.0F);
         worldIn.spawnEntity(entitypotion);
      }

      playerIn.addStat(XV.getObjectUseStats(this));
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }
}
