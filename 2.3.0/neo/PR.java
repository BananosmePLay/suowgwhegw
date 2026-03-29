package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class PR extends Pm {
   public PR(co block) {
      super(block, false);
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, true);
      if (raytraceresult == null) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else {
         if (raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK) {
            BlockPos blockpos = raytraceresult.getBlockPos();
            if (!worldIn.isBlockModifiable(playerIn, blockpos) || !playerIn.canPlayerEdit(blockpos.offset(raytraceresult.sideHit), raytraceresult.sideHit, itemstack)) {
               return new ActionResult(EnumActionResult.FAIL, itemstack);
            }

            BlockPos blockpos1 = blockpos.up();
            in iblockstate = worldIn.getBlockState(blockpos);
            if (iblockstate.getMaterial() == hM.WATER && (Integer)iblockstate.getValue(eB.LEVEL) == 0 && worldIn.isAirBlock(blockpos1)) {
               worldIn.setBlockState(blockpos1, Nk.WATERLILY.getDefaultState(), 11);
               if (playerIn instanceof MG) {
                  bY.PLACED_BLOCK.trigger((MG)playerIn, blockpos1, itemstack);
               }

               if (!playerIn.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
               }

               playerIn.addStat(XV.getObjectUseStats(this));
               worldIn.playSound(playerIn, blockpos, NO.BLOCK_WATERLILY_PLACE, SoundCategory.BLOCKS, 1.0F, 1.0F);
               return new ActionResult(EnumActionResult.SUCCESS, itemstack);
            }
         }

         return new ActionResult(EnumActionResult.FAIL, itemstack);
      }
   }
}
