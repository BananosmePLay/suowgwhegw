package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class fn {
   private final bij world;
   private final EnumFacing.Axis axis;
   private final EnumFacing rightDir;
   private final EnumFacing leftDir;
   private int portalBlockCount;
   private BlockPos bottomLeft;
   private int height;
   private int width;

   public fn(bij worldIn, BlockPos p_i45694_2_, EnumFacing.Axis p_i45694_3_) {
      this.world = worldIn;
      this.axis = p_i45694_3_;
      if (p_i45694_3_ == EnumFacing.Axis.X) {
         this.leftDir = EnumFacing.EAST;
         this.rightDir = EnumFacing.WEST;
      } else {
         this.leftDir = EnumFacing.NORTH;
         this.rightDir = EnumFacing.SOUTH;
      }

      for(BlockPos blockpos = p_i45694_2_; p_i45694_2_.getY() > blockpos.getY() - 21 && p_i45694_2_.getY() > 0 && this.isEmptyBlock(worldIn.getBlockState(p_i45694_2_.down()).getBlock()); p_i45694_2_ = p_i45694_2_.down()) {
      }

      int i = this.getDistanceUntilEdge(p_i45694_2_, this.leftDir) - 1;
      if (i >= 0) {
         this.bottomLeft = p_i45694_2_.offset(this.leftDir, i);
         this.width = this.getDistanceUntilEdge(this.bottomLeft, this.rightDir);
         if (this.width < 2 || this.width > 21) {
            this.bottomLeft = null;
            this.width = 0;
         }
      }

      if (this.bottomLeft != null) {
         this.height = this.calculatePortalHeight();
      }

   }

   protected int getDistanceUntilEdge(BlockPos p_180120_1_, EnumFacing p_180120_2_) {
      int i;
      for(i = 0; i < 22; ++i) {
         BlockPos blockpos = p_180120_1_.offset(p_180120_2_, i);
         if (!this.isEmptyBlock(this.world.getBlockState(blockpos).getBlock()) || this.world.getBlockState(blockpos.down()).getBlock() != Nk.OBSIDIAN) {
            break;
         }
      }

      co block = this.world.getBlockState(p_180120_1_.offset(p_180120_2_, i)).getBlock();
      return block == Nk.OBSIDIAN ? i : 0;
   }

   public int getHeight() {
      return this.height;
   }

   public int getWidth() {
      return this.width;
   }

   protected int calculatePortalHeight() {
      int i;
      label56:
      for(this.height = 0; this.height < 21; ++this.height) {
         for(i = 0; i < this.width; ++i) {
            BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i).up(this.height);
            co block = this.world.getBlockState(blockpos).getBlock();
            if (!this.isEmptyBlock(block)) {
               break label56;
            }

            if (block == Nk.PORTAL) {
               ++this.portalBlockCount;
            }

            if (i == 0) {
               block = this.world.getBlockState(blockpos.offset(this.leftDir)).getBlock();
               if (block != Nk.OBSIDIAN) {
                  break label56;
               }
            } else if (i == this.width - 1) {
               block = this.world.getBlockState(blockpos.offset(this.rightDir)).getBlock();
               if (block != Nk.OBSIDIAN) {
                  break label56;
               }
            }
         }
      }

      for(i = 0; i < this.width; ++i) {
         if (this.world.getBlockState(this.bottomLeft.offset(this.rightDir, i).up(this.height)).getBlock() != Nk.OBSIDIAN) {
            this.height = 0;
            break;
         }
      }

      if (this.height <= 21 && this.height >= 3) {
         return this.height;
      } else {
         this.bottomLeft = null;
         this.width = 0;
         this.height = 0;
         return 0;
      }
   }

   protected boolean isEmptyBlock(co blockIn) {
      return blockIn.material == hM.AIR || blockIn == Nk.FIRE || blockIn == Nk.PORTAL;
   }

   public boolean isValid() {
      return this.bottomLeft != null && this.width >= 2 && this.width <= 21 && this.height >= 3 && this.height <= 21;
   }

   public void placePortalBlocks() {
      for(int i = 0; i < this.width; ++i) {
         BlockPos blockpos = this.bottomLeft.offset(this.rightDir, i);

         for(int j = 0; j < this.height; ++j) {
            this.world.setBlockState(blockpos.up(j), Nk.PORTAL.getDefaultState().withProperty(fo.AXIS, this.axis), 2);
         }
      }

   }

   // $FF: synthetic method
   static int access$000(fn x0) {
      return x0.portalBlockCount;
   }

   // $FF: synthetic method
   static int access$100(fn x0) {
      return x0.width;
   }

   // $FF: synthetic method
   static int access$200(fn x0) {
      return x0.height;
   }

   // $FF: synthetic method
   static EnumFacing access$300(fn x0) {
      return x0.rightDir;
   }

   // $FF: synthetic method
   static BlockPos access$400(fn x0) {
      return x0.bottomLeft;
   }
}
