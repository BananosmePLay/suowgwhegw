package neo;

import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class bbD extends bbE {
   public static final BlockPos END_PODIUM_LOCATION;
   public static final BlockPos END_PODIUM_CHUNK_POS;
   private final boolean activePortal;

   public bbD(boolean activePortalIn) {
      this.activePortal = activePortalIn;
   }

   public boolean generate(bij worldIn, Random rand, BlockPos position) {
      Iterator var4 = BlockPos.getAllInBoxMutable(new BlockPos(position.getX() - 4, position.getY() - 1, position.getZ() - 4), new BlockPos(position.getX() + 4, position.getY() + 32, position.getZ() + 4)).iterator();

      while(var4.hasNext()) {
         BlockPos.MutableBlockPos blockpos$mutableblockpos = (BlockPos.MutableBlockPos)var4.next();
         double d0 = blockpos$mutableblockpos.getDistance(position.getX(), blockpos$mutableblockpos.getY(), position.getZ());
         if (d0 <= 3.5) {
            if (blockpos$mutableblockpos.getY() < position.getY()) {
               if (d0 <= 2.5) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.BEDROCK.getDefaultState());
               } else if (blockpos$mutableblockpos.getY() < position.getY()) {
                  this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.END_STONE.getDefaultState());
               }
            } else if (blockpos$mutableblockpos.getY() > position.getY()) {
               this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.AIR.getDefaultState());
            } else if (d0 > 2.5) {
               this.setBlockAndNotifyAdequately(worldIn, blockpos$mutableblockpos, Nk.BEDROCK.getDefaultState());
            } else if (this.activePortal) {
               this.setBlockAndNotifyAdequately(worldIn, new BlockPos(blockpos$mutableblockpos), Nk.END_PORTAL.getDefaultState());
            } else {
               this.setBlockAndNotifyAdequately(worldIn, new BlockPos(blockpos$mutableblockpos), Nk.AIR.getDefaultState());
            }
         }
      }

      for(int i = 0; i < 4; ++i) {
         this.setBlockAndNotifyAdequately(worldIn, position.up(i), Nk.BEDROCK.getDefaultState());
      }

      BlockPos blockpos = position.up(2);
      Iterator var10 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var10.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)var10.next();
         this.setBlockAndNotifyAdequately(worldIn, blockpos.offset(enumfacing), Nk.TORCH.getDefaultState().withProperty(ho.FACING, enumfacing));
      }

      return true;
   }

   static {
      END_PODIUM_LOCATION = BlockPos.ORIGIN;
      END_PODIUM_CHUNK_POS = new BlockPos(END_PODIUM_LOCATION.getX() - 4 & -16, 0, END_PODIUM_LOCATION.getZ() - 4 & -16);
   }
}
