package neo;

import com.google.common.collect.Sets;
import java.util.Set;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class Qv extends QB {
   private static final Set<co> EFFECTIVE_ON;

   public Qv(OK material) {
      super(1.5F, -3.0F, material, EFFECTIVE_ON);
   }

   public boolean canHarvestBlock(in blockIn) {
      co block = blockIn.getBlock();
      if (block == Nk.SNOW_LAYER) {
         return true;
      } else {
         return block == Nk.SNOW;
      }
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      if (!player.canPlayerEdit(pos.offset(facing), facing, itemstack)) {
         return EnumActionResult.FAIL;
      } else {
         in iblockstate = worldIn.getBlockState(pos);
         co block = iblockstate.getBlock();
         if (facing != EnumFacing.DOWN && worldIn.getBlockState(pos.up()).getMaterial() == hM.AIR && block == Nk.GRASS) {
            in iblockstate1 = Nk.GRASS_PATH.getDefaultState();
            worldIn.playSound(player, pos, NO.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
            if (!worldIn.isRemote) {
               worldIn.setBlockState(pos, iblockstate1, 11);
               itemstack.damageItem(1, player);
            }

            return EnumActionResult.SUCCESS;
         } else {
            return EnumActionResult.PASS;
         }
      }
   }

   static {
      EFFECTIVE_ON = Sets.newHashSet(new co[]{Nk.CLAY, Nk.DIRT, Nk.FARMLAND, Nk.GRASS, Nk.GRAVEL, Nk.MYCELIUM, Nk.SAND, Nk.SNOW, Nk.SNOW_LAYER, Nk.SOUL_SAND, Nk.GRASS_PATH, Nk.CONCRETE_POWDER});
   }
}
