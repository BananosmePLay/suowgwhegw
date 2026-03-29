package neo;

import java.util.List;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class Pw extends OL {
   public Pw() {
      this.setTranslationKey("end_crystal");
      this.setCreativeTab(EN.DECORATIONS);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() != Nk.OBSIDIAN && iblockstate.getBlock() != Nk.BEDROCK) {
         return EnumActionResult.FAIL;
      } else {
         BlockPos blockpos = pos.up();
         Qy itemstack = player.getHeldItem(hand);
         if (!player.canPlayerEdit(blockpos, facing, itemstack)) {
            return EnumActionResult.FAIL;
         } else {
            BlockPos blockpos1 = blockpos.up();
            boolean flag = !worldIn.isAirBlock(blockpos) && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
            flag |= !worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1);
            if (flag) {
               return EnumActionResult.FAIL;
            } else {
               double d0 = (double)blockpos.getX();
               double d1 = (double)blockpos.getY();
               double d2 = (double)blockpos.getZ();
               List<Ig> list = worldIn.getEntitiesWithinAABBExcludingEntity((Ig)null, new AxisAlignedBB(d0, d1, d2, d0 + 1.0, d1 + 2.0, d2 + 1.0));
               if (!list.isEmpty()) {
                  return EnumActionResult.FAIL;
               } else {
                  if (!worldIn.isRemote) {
                     IS entityendercrystal = new IS(worldIn, (double)((float)pos.getX() + 0.5F), (double)(pos.getY() + 1), (double)((float)pos.getZ() + 0.5F));
                     entityendercrystal.setShowBottom(false);
                     worldIn.spawnEntity(entityendercrystal);
                     if (worldIn.provider instanceof bim) {
                        baN dragonfightmanager = ((bim)worldIn.provider).getDragonFightManager();
                        dragonfightmanager.respawnDragon();
                     }
                  }

                  itemstack.shrink(1);
                  return EnumActionResult.SUCCESS;
               }
            }
         }
      }
   }

   public boolean hasEffect(Qy stack) {
      return true;
   }
}
