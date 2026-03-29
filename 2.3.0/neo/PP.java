package neo;

import java.util.Iterator;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class PP extends OL {
   public PP() {
      this.setCreativeTab(EN.TOOLS);
   }

   public EnumActionResult onItemUse(ME player, bij worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      co block = worldIn.getBlockState(pos).getBlock();
      if (!(block instanceof dL)) {
         return EnumActionResult.PASS;
      } else {
         if (!worldIn.isRemote) {
            attachToFence(player, worldIn, pos);
         }

         return EnumActionResult.SUCCESS;
      }
   }

   public static boolean attachToFence(ME player, bij worldIn, BlockPos fence) {
      Ip entityleashknot = Ip.getKnotForPosition(worldIn, fence);
      boolean flag = false;
      double d0 = 7.0;
      int i = fence.getX();
      int j = fence.getY();
      int k = fence.getZ();
      Iterator var10 = worldIn.getEntitiesWithinAABB(Iu.class, new AxisAlignedBB((double)i - 7.0, (double)j - 7.0, (double)k - 7.0, (double)i + 7.0, (double)j + 7.0, (double)k + 7.0)).iterator();

      while(var10.hasNext()) {
         Iu entityliving = (Iu)var10.next();
         if (entityliving.getLeashed() && entityliving.getLeashHolder() == player) {
            if (entityleashknot == null) {
               entityleashknot = Ip.createKnot(worldIn, fence);
            }

            entityliving.setLeashHolder(entityleashknot, true);
            flag = true;
         }
      }

      return flag;
   }
}
