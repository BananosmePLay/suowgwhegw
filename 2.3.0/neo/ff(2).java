package neo;

import com.google.common.collect.Lists;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class ff extends dh {
   public static final hV EXTENDED = hV.create("extended");
   protected static final AxisAlignedBB PISTON_BASE_EAST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.75, 1.0, 1.0);
   protected static final AxisAlignedBB PISTON_BASE_WEST_AABB = new AxisAlignedBB(0.25, 0.0, 0.0, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB PISTON_BASE_SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.75);
   protected static final AxisAlignedBB PISTON_BASE_NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.25, 1.0, 1.0, 1.0);
   protected static final AxisAlignedBB PISTON_BASE_UP_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 0.75, 1.0);
   protected static final AxisAlignedBB PISTON_BASE_DOWN_AABB = new AxisAlignedBB(0.0, 0.25, 0.0, 1.0, 1.0, 1.0);
   private final boolean isSticky;

   public ff(boolean isSticky) {
      super(hM.PISTON);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(EXTENDED, false));
      this.isSticky = isSticky;
      this.setSoundType(ia.STONE);
      this.setHardness(0.5F);
      this.setCreativeTab(EN.REDSTONE);
   }

   /** @deprecated */
   public boolean causesSuffocation(in state) {
      return !(Boolean)state.getValue(EXTENDED);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      if ((Boolean)state.getValue(EXTENDED)) {
         switch ((EnumFacing)state.getValue(FACING)) {
            case DOWN:
               return PISTON_BASE_DOWN_AABB;
            case UP:
            default:
               return PISTON_BASE_UP_AABB;
            case NORTH:
               return PISTON_BASE_NORTH_AABB;
            case SOUTH:
               return PISTON_BASE_SOUTH_AABB;
            case WEST:
               return PISTON_BASE_WEST_AABB;
            case EAST:
               return PISTON_BASE_EAST_AABB;
         }
      } else {
         return FULL_BLOCK_AABB;
      }
   }

   /** @deprecated */
   public boolean isTopSolid(in state) {
      return !(Boolean)state.getValue(EXTENDED) || state.getValue(FACING) == EnumFacing.DOWN;
   }

   public void addCollisionBoxToList(in state, bij worldIn, BlockPos pos, AxisAlignedBB entityBox, List<AxisAlignedBB> collidingBoxes, @Nullable Ig entityIn, boolean isActualState) {
      addCollisionBoxToList(pos, entityBox, collidingBoxes, state.getBoundingBox(worldIn, pos));
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
      if (!worldIn.isRemote) {
         this.checkForMove(worldIn, pos, state);
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         this.checkForMove(worldIn, pos, state);
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote && worldIn.getTileEntity(pos) == null) {
         this.checkForMove(worldIn, pos, state);
      }

   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(EXTENDED, false);
   }

   private void checkForMove(bij worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      boolean flag = this.shouldBeExtended(worldIn, pos, enumfacing);
      if (flag && !(Boolean)state.getValue(EXTENDED)) {
         if ((new ic(worldIn, pos, enumfacing, true)).canMove()) {
            worldIn.addBlockEvent(pos, this, 0, enumfacing.getIndex());
         }
      } else if (!flag && (Boolean)state.getValue(EXTENDED)) {
         worldIn.addBlockEvent(pos, this, 1, enumfacing.getIndex());
      }

   }

   private boolean shouldBeExtended(bij worldIn, BlockPos pos, EnumFacing facing) {
      EnumFacing[] var4 = EnumFacing.values();
      int var5 = var4.length;

      int var6;
      for(var6 = 0; var6 < var5; ++var6) {
         EnumFacing enumfacing = var4[var6];
         if (enumfacing != facing && worldIn.isSidePowered(pos.offset(enumfacing), enumfacing)) {
            return true;
         }
      }

      if (worldIn.isSidePowered(pos, EnumFacing.DOWN)) {
         return true;
      } else {
         BlockPos blockpos = pos.up();
         EnumFacing[] var10 = EnumFacing.values();
         var6 = var10.length;

         for(int var11 = 0; var11 < var6; ++var11) {
            EnumFacing enumfacing1 = var10[var11];
            if (enumfacing1 != EnumFacing.DOWN && worldIn.isSidePowered(blockpos.offset(enumfacing1), enumfacing1)) {
               return true;
            }
         }

         return false;
      }
   }

   /** @deprecated */
   public boolean eventReceived(in state, bij worldIn, BlockPos pos, int id, int param) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      if (!worldIn.isRemote) {
         boolean flag = this.shouldBeExtended(worldIn, pos, enumfacing);
         if (flag && id == 1) {
            worldIn.setBlockState(pos, state.withProperty(EXTENDED, true), 2);
            return false;
         }

         if (!flag && id == 0) {
            return false;
         }
      }

      if (id == 0) {
         if (!this.doMove(worldIn, pos, enumfacing, true)) {
            return false;
         }

         worldIn.setBlockState(pos, state.withProperty(EXTENDED, true), 3);
         worldIn.playSound((ME)null, pos, NO.BLOCK_PISTON_EXTEND, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.25F + 0.6F);
      } else if (id == 1) {
         Yg tileentity1 = worldIn.getTileEntity(pos.offset(enumfacing));
         if (tileentity1 instanceof YK) {
            ((YK)tileentity1).clearPistonTileEntity();
         }

         worldIn.setBlockState(pos, Nk.PISTON_EXTENSION.getDefaultState().withProperty(fj.FACING, enumfacing).withProperty(fj.TYPE, this.isSticky ? fh.STICKY : fh.DEFAULT), 3);
         worldIn.setTileEntity(pos, fj.createTilePiston(this.getStateFromMeta(param), enumfacing, false, true));
         if (this.isSticky) {
            BlockPos blockpos = pos.add(enumfacing.getXOffset() * 2, enumfacing.getYOffset() * 2, enumfacing.getZOffset() * 2);
            in iblockstate = worldIn.getBlockState(blockpos);
            co block = iblockstate.getBlock();
            boolean flag1 = false;
            if (block == Nk.PISTON_EXTENSION) {
               Yg tileentity = worldIn.getTileEntity(blockpos);
               if (tileentity instanceof YK) {
                  YK tileentitypiston = (YK)tileentity;
                  if (tileentitypiston.getFacing() == enumfacing && tileentitypiston.isExtending()) {
                     tileentitypiston.clearPistonTileEntity();
                     flag1 = true;
                  }
               }
            }

            if (!flag1 && iblockstate.getMaterial() != hM.AIR && canPush(iblockstate, worldIn, blockpos, enumfacing.getOpposite(), false, enumfacing) && (iblockstate.getPushReaction() == hJ.NORMAL || block == Nk.PISTON || block == Nk.STICKY_PISTON)) {
               this.doMove(worldIn, pos, enumfacing, false);
            }
         } else {
            worldIn.setBlockToAir(pos.offset(enumfacing));
         }

         worldIn.playSound((ME)null, pos, NO.BLOCK_PISTON_CONTRACT, SoundCategory.BLOCKS, 0.5F, worldIn.rand.nextFloat() * 0.15F + 0.6F);
      }

      return true;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   @Nullable
   public static EnumFacing getFacing(int meta) {
      int i = meta & 7;
      return i > 5 ? null : EnumFacing.byIndex(i);
   }

   public static boolean canPush(in blockStateIn, bij worldIn, BlockPos pos, EnumFacing facing, boolean destroyBlocks, EnumFacing p_185646_5_) {
      co block = blockStateIn.getBlock();
      if (block == Nk.OBSIDIAN) {
         return false;
      } else if (!worldIn.getWorldBorder().contains(pos)) {
         return false;
      } else if (pos.getY() < 0 || facing == EnumFacing.DOWN && pos.getY() == 0) {
         return false;
      } else if (pos.getY() > worldIn.getHeight() - 1 || facing == EnumFacing.UP && pos.getY() == worldIn.getHeight() - 1) {
         return false;
      } else {
         if (block != Nk.PISTON && block != Nk.STICKY_PISTON) {
            if (blockStateIn.getBlockHardness(worldIn, pos) == -1.0F) {
               return false;
            }

            switch (blockStateIn.getPushReaction()) {
               case BLOCK:
                  return false;
               case DESTROY:
                  return destroyBlocks;
               case PUSH_ONLY:
                  return facing == p_185646_5_;
            }
         } else if ((Boolean)blockStateIn.getValue(EXTENDED)) {
            return false;
         }

         return !block.hasTileEntity();
      }
   }

   private boolean doMove(bij worldIn, BlockPos pos, EnumFacing direction, boolean extending) {
      if (!extending) {
         worldIn.setBlockToAir(pos.offset(direction));
      }

      ic blockpistonstructurehelper = new ic(worldIn, pos, direction, extending);
      if (!blockpistonstructurehelper.canMove()) {
         return false;
      } else {
         List<BlockPos> list = blockpistonstructurehelper.getBlocksToMove();
         List<in> list1 = Lists.newArrayList();

         for(int i = 0; i < list.size(); ++i) {
            BlockPos blockpos = (BlockPos)list.get(i);
            list1.add(worldIn.getBlockState(blockpos).getActualState(worldIn, blockpos));
         }

         List<BlockPos> list2 = blockpistonstructurehelper.getBlocksToDestroy();
         int k = list.size() + list2.size();
         in[] aiblockstate = new in[k];
         EnumFacing enumfacing = extending ? direction : direction.getOpposite();

         int l;
         BlockPos blockpos3;
         in iblockstate3;
         for(l = list2.size() - 1; l >= 0; --l) {
            blockpos3 = (BlockPos)list2.get(l);
            iblockstate3 = worldIn.getBlockState(blockpos3);
            iblockstate3.getBlock().dropBlockAsItem(worldIn, blockpos3, iblockstate3, 0);
            worldIn.setBlockState(blockpos3, Nk.AIR.getDefaultState(), 4);
            --k;
            aiblockstate[k] = iblockstate3;
         }

         for(l = list.size() - 1; l >= 0; --l) {
            blockpos3 = (BlockPos)list.get(l);
            iblockstate3 = worldIn.getBlockState(blockpos3);
            worldIn.setBlockState(blockpos3, Nk.AIR.getDefaultState(), 2);
            blockpos3 = blockpos3.offset(enumfacing);
            worldIn.setBlockState(blockpos3, Nk.PISTON_EXTENSION.getDefaultState().withProperty(FACING, direction), 4);
            worldIn.setTileEntity(blockpos3, fj.createTilePiston((in)list1.get(l), direction, extending, false));
            --k;
            aiblockstate[k] = iblockstate3;
         }

         BlockPos blockpos2 = pos.offset(direction);
         if (extending) {
            fh blockpistonextension$enumpistontype = this.isSticky ? fh.STICKY : fh.DEFAULT;
            iblockstate3 = Nk.PISTON_HEAD.getDefaultState().withProperty(fi.FACING, direction).withProperty(fi.TYPE, blockpistonextension$enumpistontype);
            in iblockstate1 = Nk.PISTON_EXTENSION.getDefaultState().withProperty(fj.FACING, direction).withProperty(fj.TYPE, this.isSticky ? fh.STICKY : fh.DEFAULT);
            worldIn.setBlockState(blockpos2, iblockstate1, 4);
            worldIn.setTileEntity(blockpos2, fj.createTilePiston(iblockstate3, direction, true, true));
         }

         int j1;
         for(j1 = list2.size() - 1; j1 >= 0; --j1) {
            worldIn.notifyNeighborsOfStateChange((BlockPos)list2.get(j1), aiblockstate[k++].getBlock(), false);
         }

         for(j1 = list.size() - 1; j1 >= 0; --j1) {
            worldIn.notifyNeighborsOfStateChange((BlockPos)list.get(j1), aiblockstate[k++].getBlock(), false);
         }

         if (extending) {
            worldIn.notifyNeighborsOfStateChange(blockpos2, Nk.PISTON_HEAD, false);
         }

         return true;
      }
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, getFacing(meta)).withProperty(EXTENDED, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if ((Boolean)state.getValue(EXTENDED)) {
         i |= 8;
      }

      return i;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, EXTENDED});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      state = this.getActualState(state, worldIn, pos);
      return state.getValue(FACING) != face.getOpposite() && (Boolean)state.getValue(EXTENDED) ? ib.UNDEFINED : ib.SOLID;
   }
}
