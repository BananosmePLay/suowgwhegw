package neo;

import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;

public class Px extends OL {
   public Px() {
      this.setCreativeTab(EN.MISC);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      Qy itemstack = player.getHeldItem(hand);
      if (player.canPlayerEdit(pos.offset(facing), facing, itemstack) && iblockstate.getBlock() == Nk.END_PORTAL_FRAME && !(Boolean)iblockstate.getValue(dD.EYE)) {
         if (worldIn.isRemote) {
            return EnumActionResult.SUCCESS;
         } else {
            worldIn.setBlockState(pos, iblockstate.withProperty(dD.EYE, true), 2);
            worldIn.updateComparatorOutputLevel(pos, Nk.END_PORTAL_FRAME);
            itemstack.shrink(1);

            for(int i = 0; i < 16; ++i) {
               double d0 = (double)((float)pos.getX() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
               double d1 = (double)((float)pos.getY() + 0.8125F);
               double d2 = (double)((float)pos.getZ() + (5.0F + itemRand.nextFloat() * 6.0F) / 16.0F);
               double d3 = 0.0;
               double d4 = 0.0;
               double d5 = 0.0;
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0, 0.0, 0.0);
            }

            worldIn.playSound((ME)null, pos, NO.BLOCK_END_PORTAL_FRAME_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
            is blockpattern$patternhelper = dD.getOrCreatePortalShape().match(worldIn, pos);
            if (blockpattern$patternhelper != null) {
               BlockPos blockpos = blockpattern$patternhelper.getFrontTopLeft().add(-3, 0, -3);

               for(int j = 0; j < 3; ++j) {
                  for(int k = 0; k < 3; ++k) {
                     worldIn.setBlockState(blockpos.add(j, 0, k), Nk.END_PORTAL.getDefaultState(), 2);
                  }
               }

               worldIn.playBroadcastSound(1038, blockpos.add(1, 0, 1), 0);
            }

            return EnumActionResult.SUCCESS;
         }
      } else {
         return EnumActionResult.FAIL;
      }
   }

   public ActionResult<Qy> onItemRightClick(bij worldIn, ME playerIn, EnumHand handIn) {
      Qy itemstack = playerIn.getHeldItem(handIn);
      RayTraceResult raytraceresult = this.rayTrace(worldIn, playerIn, false);
      if (raytraceresult != null && raytraceresult.typeOfHit == RayTraceResult.Type.BLOCK && worldIn.getBlockState(raytraceresult.getBlockPos()).getBlock() == Nk.END_PORTAL_FRAME) {
         return new ActionResult(EnumActionResult.PASS, itemstack);
      } else {
         playerIn.setActiveHand(handIn);
         if (!worldIn.isRemote) {
            BlockPos blockpos = ((bis)worldIn).getChunkProvider().getNearestStructurePos(worldIn, "Stronghold", new BlockPos(playerIn), false);
            if (blockpos != null) {
               IT entityendereye = new IT(worldIn, playerIn.posX, playerIn.posY + (double)(playerIn.height / 2.0F), playerIn.posZ);
               entityendereye.moveTowards(blockpos);
               worldIn.spawnEntity(entityendereye);
               if (playerIn instanceof MG) {
                  bY.USED_ENDER_EYE.trigger((MG)playerIn, blockpos);
               }

               worldIn.playSound((ME)null, playerIn.posX, playerIn.posY, playerIn.posZ, NO.ENTITY_ENDEREYE_LAUNCH, SoundCategory.NEUTRAL, 0.5F, 0.4F / (itemRand.nextFloat() * 0.4F + 0.8F));
               worldIn.playEvent((ME)null, 1003, new BlockPos(playerIn), 0);
               if (!playerIn.capabilities.isCreativeMode) {
                  itemstack.shrink(1);
               }

               playerIn.addStat(XV.getObjectUseStats(this));
               return new ActionResult(EnumActionResult.SUCCESS, itemstack);
            }
         }

         return new ActionResult(EnumActionResult.SUCCESS, itemstack);
      }
   }
}
