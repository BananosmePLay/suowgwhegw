package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;

public class Pr extends OL {
   public Pr() {
      this.maxStackSize = 16;
      this.setCreativeTab(EN.MATERIALS);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      if (!playerIn.capabilities.isCreativeMode) {
         itemstack.shrink(1);
      }

      worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ENTITY_EGG_THROW, SoundCategory.PLAYERS, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
      if (!worldIn.isRemote) {
         MQ entityegg = new MQ(worldIn, playerIn);
         entityegg.shoot(playerIn, playerIn.rotationPitch, playerIn.rotationYaw, 0.0F, 1.5F, 1.0F);
         worldIn.spawnEntity(entityegg);
      }

      playerIn.addStat(XV.getObjectUseStats(this));
      return new ActionResult(EnumActionResult.SUCCESS, itemstack);
   }
}
