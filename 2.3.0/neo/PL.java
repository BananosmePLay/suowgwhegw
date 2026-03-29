package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class PL extends OL {
   private final Class<? extends Io> hangingEntityClass;

   public PL(Class<? extends Io> entityClass) {
      this.hangingEntityClass = entityClass;
      this.setCreativeTab(EN.DECORATIONS);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      Qy itemstack = player.getHeldItem(hand);
      BlockPos blockpos = pos.offset(facing);
      if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && player.canPlayerEdit(blockpos, facing, itemstack)) {
         Io entityhanging = this.createEntity(worldIn, blockpos, facing);
         if (entityhanging != null && entityhanging.onValidSurface()) {
            if (!worldIn.isRemote) {
               entityhanging.playPlaceSound();
               worldIn.spawnEntity(entityhanging);
            }

            itemstack.shrink(1);
         }

         return EnumActionResult.SUCCESS;
      } else {
         return EnumActionResult.FAIL;
      }
   }

   @Nullable
   private Io createEntity(bij worldIn, BlockPos pos, EnumFacing clickedSide) {
      if (this.hangingEntityClass == Jq.class) {
         return new Jq(worldIn, pos, clickedSide);
      } else {
         return this.hangingEntityClass == IZ.class ? new IZ(worldIn, pos, clickedSide) : null;
      }
   }
}
