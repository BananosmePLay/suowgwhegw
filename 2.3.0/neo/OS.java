package neo;

import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Rotations;

public class OS extends OL {
   public OS() {
      this.setCreativeTab(EN.DECORATIONS);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (facing == EnumFacing.DOWN) {
         return EnumActionResult.FAIL;
      } else {
         boolean flag = worldIn.getBlockState(pos).getBlock().isReplaceable(worldIn, pos);
         BlockPos blockpos = flag ? pos : pos.offset(facing);
         Qy itemstack = player.getHeldItem(hand);
         if (!player.canPlayerEdit(blockpos, facing, itemstack)) {
            return EnumActionResult.FAIL;
         } else {
            BlockPos blockpos1 = blockpos.up();
            boolean flag1 = !worldIn.isAirBlock(blockpos) && !worldIn.getBlockState(blockpos).getBlock().isReplaceable(worldIn, blockpos);
            flag1 |= !worldIn.isAirBlock(blockpos1) && !worldIn.getBlockState(blockpos1).getBlock().isReplaceable(worldIn, blockpos1);
            if (flag1) {
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
                     worldIn.setBlockToAir(blockpos);
                     worldIn.setBlockToAir(blockpos1);
                     IN entityarmorstand = new IN(worldIn, d0 + 0.5, d1, d2 + 0.5);
                     float f = (float)MathHelper.floor((MathHelper.wrapDegrees(player.rotationYaw - 180.0F) + 22.5F) / 45.0F) * 45.0F;
                     entityarmorstand.setLocationAndAngles(d0 + 0.5, d1, d2 + 0.5, f, 0.0F);
                     this.applyRandomRotations(entityarmorstand, worldIn.rand);
                     PX.applyItemEntityDataToEntity(worldIn, player, itemstack, entityarmorstand);
                     worldIn.spawnEntity(entityarmorstand);
                     worldIn.playSound((ME)null, entityarmorstand.posX, entityarmorstand.posY, entityarmorstand.posZ, NO.ENTITY_ARMORSTAND_PLACE, SoundCategory.BLOCKS, 0.75F, 0.8F);
                  }

                  itemstack.shrink(1);
                  return EnumActionResult.SUCCESS;
               }
            }
         }
      }
   }

   private void applyRandomRotations(IN armorStand, Random rand) {
      Rotations rotations = armorStand.getHeadRotation();
      float f = rand.nextFloat() * 5.0F;
      float f1 = rand.nextFloat() * 20.0F - 10.0F;
      Rotations rotations1 = new Rotations(rotations.getX() + f, rotations.getY() + f1, rotations.getZ());
      armorStand.setHeadRotation(rotations1);
      rotations = armorStand.getBodyRotation();
      f = rand.nextFloat() * 10.0F - 5.0F;
      rotations1 = new Rotations(rotations.getX(), rotations.getY() + f, rotations.getZ());
      armorStand.setBodyRotation(rotations1);
   }
}
