package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

public class Pz extends OL {
   public Pz() {
      this.setCreativeTab(EN.MISC);
   }

   public boolean hasEffect(Qy stack) {
      return true;
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (!playerIn.capabilities.isCreativeMode) {
         itemstack.shrink(1);
      }

      worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ENTITY_EXPERIENCE_BOTTLE_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if (!worldIn.isRemote) {
         IV entityexpbottle = new IV(worldIn, playerIn);
         entityexpbottle.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, -20.0F, 0.7F, 1.0F);
         worldIn.spawnEntity(entityexpbottle);
      }

      playerIn.addStat(XV.getObjectUseStats(this));
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }
}
