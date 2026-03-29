package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public class fJ {
   private final bij world;
   private final BlockPos pos;
   private final fK block;
   private in state;
   private final boolean isPowered;
   private final List<BlockPos> connectedRails;
   // $FF: synthetic field
   final fK this$0;

   public fJ(fK this$0, bij worldIn, BlockPos pos, in state) {
      this.this$0 = this$0;
      this.connectedRails = Lists.newArrayList();
      this.world = worldIn;
      this.pos = pos;
      this.state = state;
      this.block = (fK)state.getBlock();
      fI blockrailbase$enumraildirection = (fI)state.getValue(this.block.getShapeProperty());
      this.isPowered = this.block.isPowered;
      this.updateConnectedRails(blockrailbase$enumraildirection);
   }

   public List<BlockPos> getConnectedRails() {
      return this.connectedRails;
   }

   private void updateConnectedRails(fI railDirection) {
      this.connectedRails.clear();
      switch (railDirection) {
         case NORTH_SOUTH:
            this.connectedRails.add(this.pos.north());
            this.connectedRails.add(this.pos.south());
            break;
         case EAST_WEST:
            this.connectedRails.add(this.pos.west());
            this.connectedRails.add(this.pos.east());
            break;
         case ASCENDING_EAST:
            this.connectedRails.add(this.pos.west());
            this.connectedRails.add(this.pos.east().up());
            break;
         case ASCENDING_WEST:
            this.connectedRails.add(this.pos.west().up());
            this.connectedRails.add(this.pos.east());
            break;
         case ASCENDING_NORTH:
            this.connectedRails.add(this.pos.north().up());
            this.connectedRails.add(this.pos.south());
            break;
         case ASCENDING_SOUTH:
            this.connectedRails.add(this.pos.north());
            this.connectedRails.add(this.pos.south().up());
            break;
         case SOUTH_EAST:
            this.connectedRails.add(this.pos.east());
            this.connectedRails.add(this.pos.south());
            break;
         case SOUTH_WEST:
            this.connectedRails.add(this.pos.west());
            this.connectedRails.add(this.pos.south());
            break;
         case NORTH_WEST:
            this.connectedRails.add(this.pos.west());
            this.connectedRails.add(this.pos.north());
            break;
         case NORTH_EAST:
            this.connectedRails.add(this.pos.east());
            this.connectedRails.add(this.pos.north());
      }

   }

   private void removeSoftConnections() {
      for(int i = 0; i < this.connectedRails.size(); ++i) {
         fJ blockrailbase$rail = this.findRailAt((BlockPos)this.connectedRails.get(i));
         if (blockrailbase$rail != null && blockrailbase$rail.isConnectedToRail(this)) {
            this.connectedRails.set(i, blockrailbase$rail.pos);
         } else {
            this.connectedRails.remove(i--);
         }
      }

   }

   private boolean hasRailAt(BlockPos pos) {
      return fK.isRailBlock(this.world, pos) || fK.isRailBlock(this.world, pos.up()) || fK.isRailBlock(this.world, pos.down());
   }

   @Nullable
   private fJ findRailAt(BlockPos pos) {
      in iblockstate = this.world.getBlockState(pos);
      fK var10002;
      if (fK.isRailBlock(iblockstate)) {
         var10002 = this.this$0;
         var10002.getClass();
         return new fJ(var10002, this.world, pos, iblockstate);
      } else {
         BlockPos lvt_2_1_ = pos.up();
         iblockstate = this.world.getBlockState(lvt_2_1_);
         if (fK.isRailBlock(iblockstate)) {
            var10002 = this.this$0;
            var10002.getClass();
            return new fJ(var10002, this.world, lvt_2_1_, iblockstate);
         } else {
            lvt_2_1_ = pos.down();
            iblockstate = this.world.getBlockState(lvt_2_1_);
            fJ var10000;
            if (fK.isRailBlock(iblockstate)) {
               var10002 = this.this$0;
               var10002.getClass();
               var10000 = new fJ(var10002, this.world, lvt_2_1_, iblockstate);
            } else {
               var10000 = null;
            }

            return var10000;
         }
      }
   }

   private boolean isConnectedToRail(fJ rail) {
      return this.isConnectedTo(rail.pos);
   }

   private boolean isConnectedTo(BlockPos posIn) {
      for(int i = 0; i < this.connectedRails.size(); ++i) {
         BlockPos blockpos = (BlockPos)this.connectedRails.get(i);
         if (blockpos.getX() == posIn.getX() && blockpos.getZ() == posIn.getZ()) {
            return true;
         }
      }

      return false;
   }

   protected int countAdjacentRails() {
      int i = 0;
      Iterator var2 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var2.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)var2.next();
         if (this.hasRailAt(this.pos.offset(enumfacing))) {
            ++i;
         }
      }

      return i;
   }

   private boolean canConnectTo(fJ rail) {
      return this.isConnectedToRail(rail) || this.connectedRails.size() != 2;
   }

   private void connectTo(fJ rail) {
      this.connectedRails.add(rail.pos);
      BlockPos blockpos = this.pos.north();
      BlockPos blockpos1 = this.pos.south();
      BlockPos blockpos2 = this.pos.west();
      BlockPos blockpos3 = this.pos.east();
      boolean flag = this.isConnectedTo(blockpos);
      boolean flag1 = this.isConnectedTo(blockpos1);
      boolean flag2 = this.isConnectedTo(blockpos2);
      boolean flag3 = this.isConnectedTo(blockpos3);
      fI blockrailbase$enumraildirection = null;
      if (flag || flag1) {
         blockrailbase$enumraildirection = fI.NORTH_SOUTH;
      }

      if (flag2 || flag3) {
         blockrailbase$enumraildirection = fI.EAST_WEST;
      }

      if (!this.isPowered) {
         if (flag1 && flag3 && !flag && !flag2) {
            blockrailbase$enumraildirection = fI.SOUTH_EAST;
         }

         if (flag1 && flag2 && !flag && !flag3) {
            blockrailbase$enumraildirection = fI.SOUTH_WEST;
         }

         if (flag && flag2 && !flag1 && !flag3) {
            blockrailbase$enumraildirection = fI.NORTH_WEST;
         }

         if (flag && flag3 && !flag1 && !flag2) {
            blockrailbase$enumraildirection = fI.NORTH_EAST;
         }
      }

      if (blockrailbase$enumraildirection == fI.NORTH_SOUTH) {
         if (fK.isRailBlock(this.world, blockpos.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_NORTH;
         }

         if (fK.isRailBlock(this.world, blockpos1.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_SOUTH;
         }
      }

      if (blockrailbase$enumraildirection == fI.EAST_WEST) {
         if (fK.isRailBlock(this.world, blockpos3.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_EAST;
         }

         if (fK.isRailBlock(this.world, blockpos2.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_WEST;
         }
      }

      if (blockrailbase$enumraildirection == null) {
         blockrailbase$enumraildirection = fI.NORTH_SOUTH;
      }

      this.state = this.state.withProperty(this.block.getShapeProperty(), blockrailbase$enumraildirection);
      this.world.setBlockState(this.pos, this.state, 3);
   }

   private boolean hasNeighborRail(BlockPos posIn) {
      fJ blockrailbase$rail = this.findRailAt(posIn);
      if (blockrailbase$rail == null) {
         return false;
      } else {
         blockrailbase$rail.removeSoftConnections();
         return blockrailbase$rail.canConnectTo(this);
      }
   }

   public fJ place(boolean powered, boolean initialPlacement) {
      BlockPos blockpos = this.pos.north();
      BlockPos blockpos1 = this.pos.south();
      BlockPos blockpos2 = this.pos.west();
      BlockPos blockpos3 = this.pos.east();
      boolean flag = this.hasNeighborRail(blockpos);
      boolean flag1 = this.hasNeighborRail(blockpos1);
      boolean flag2 = this.hasNeighborRail(blockpos2);
      boolean flag3 = this.hasNeighborRail(blockpos3);
      fI blockrailbase$enumraildirection = null;
      if ((flag || flag1) && !flag2 && !flag3) {
         blockrailbase$enumraildirection = fI.NORTH_SOUTH;
      }

      if ((flag2 || flag3) && !flag && !flag1) {
         blockrailbase$enumraildirection = fI.EAST_WEST;
      }

      if (!this.isPowered) {
         if (flag1 && flag3 && !flag && !flag2) {
            blockrailbase$enumraildirection = fI.SOUTH_EAST;
         }

         if (flag1 && flag2 && !flag && !flag3) {
            blockrailbase$enumraildirection = fI.SOUTH_WEST;
         }

         if (flag && flag2 && !flag1 && !flag3) {
            blockrailbase$enumraildirection = fI.NORTH_WEST;
         }

         if (flag && flag3 && !flag1 && !flag2) {
            blockrailbase$enumraildirection = fI.NORTH_EAST;
         }
      }

      if (blockrailbase$enumraildirection == null) {
         if (flag || flag1) {
            blockrailbase$enumraildirection = fI.NORTH_SOUTH;
         }

         if (flag2 || flag3) {
            blockrailbase$enumraildirection = fI.EAST_WEST;
         }

         if (!this.isPowered) {
            if (powered) {
               if (flag1 && flag3) {
                  blockrailbase$enumraildirection = fI.SOUTH_EAST;
               }

               if (flag2 && flag1) {
                  blockrailbase$enumraildirection = fI.SOUTH_WEST;
               }

               if (flag3 && flag) {
                  blockrailbase$enumraildirection = fI.NORTH_EAST;
               }

               if (flag && flag2) {
                  blockrailbase$enumraildirection = fI.NORTH_WEST;
               }
            } else {
               if (flag && flag2) {
                  blockrailbase$enumraildirection = fI.NORTH_WEST;
               }

               if (flag3 && flag) {
                  blockrailbase$enumraildirection = fI.NORTH_EAST;
               }

               if (flag2 && flag1) {
                  blockrailbase$enumraildirection = fI.SOUTH_WEST;
               }

               if (flag1 && flag3) {
                  blockrailbase$enumraildirection = fI.SOUTH_EAST;
               }
            }
         }
      }

      if (blockrailbase$enumraildirection == fI.NORTH_SOUTH) {
         if (fK.isRailBlock(this.world, blockpos.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_NORTH;
         }

         if (fK.isRailBlock(this.world, blockpos1.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_SOUTH;
         }
      }

      if (blockrailbase$enumraildirection == fI.EAST_WEST) {
         if (fK.isRailBlock(this.world, blockpos3.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_EAST;
         }

         if (fK.isRailBlock(this.world, blockpos2.up())) {
            blockrailbase$enumraildirection = fI.ASCENDING_WEST;
         }
      }

      if (blockrailbase$enumraildirection == null) {
         blockrailbase$enumraildirection = fI.NORTH_SOUTH;
      }

      this.updateConnectedRails(blockrailbase$enumraildirection);
      this.state = this.state.withProperty(this.block.getShapeProperty(), blockrailbase$enumraildirection);
      if (initialPlacement || this.world.getBlockState(this.pos) != this.state) {
         this.world.setBlockState(this.pos, this.state, 3);

         for(int i = 0; i < this.connectedRails.size(); ++i) {
            fJ blockrailbase$rail = this.findRailAt((BlockPos)this.connectedRails.get(i));
            if (blockrailbase$rail != null) {
               blockrailbase$rail.removeSoftConnections();
               if (blockrailbase$rail.canConnectTo(this)) {
                  blockrailbase$rail.connectTo(this);
               }
            }
         }
      }

      return this;
   }

   public in getBlockState() {
      return this.state;
   }
}
