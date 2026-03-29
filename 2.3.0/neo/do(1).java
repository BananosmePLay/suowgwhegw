package neo;

import java.util.Random;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class do extends co {
   public static final hW FACING;
   public static final hV OPEN;
   public static final hX<dn> HINGE;
   public static final hV POWERED;
   public static final hX<dm> HALF;
   protected static final AxisAlignedBB SOUTH_AABB;
   protected static final AxisAlignedBB NORTH_AABB;
   protected static final AxisAlignedBB WEST_AABB;
   protected static final AxisAlignedBB EAST_AABB;

   protected do(hM materialIn) {
      super(materialIn);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(OPEN, false).withProperty(HINGE, dn.LEFT).withProperty(POWERED, false).withProperty(HALF, dm.LOWER));
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      state = state.getActualState(source, pos);
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      boolean flag = !(Boolean)state.getValue(OPEN);
      boolean flag1 = state.getValue(HINGE) == dn.RIGHT;
      switch (enumfacing) {
         case EAST:
         default:
            return flag ? EAST_AABB : (flag1 ? NORTH_AABB : SOUTH_AABB);
         case SOUTH:
            return flag ? SOUTH_AABB : (flag1 ? EAST_AABB : WEST_AABB);
         case WEST:
            return flag ? WEST_AABB : (flag1 ? SOUTH_AABB : NORTH_AABB);
         case NORTH:
            return flag ? NORTH_AABB : (flag1 ? WEST_AABB : EAST_AABB);
      }
   }

   public String getLocalizedName() {
      return I18n.translateToLocal((this.getTranslationKey() + ".name").replaceAll("tile", "item"));
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return isOpen(combineMetadata(worldIn, pos));
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   private int getCloseSound() {
      return this.material == hM.IRON ? 1011 : 1012;
   }

   private int getOpenSound() {
      return this.material == hM.IRON ? 1005 : 1006;
   }

   /** @deprecated */
   public hK getMapColor(in state, bfZ worldIn, BlockPos pos) {
      if (state.getBlock() == Nk.IRON_DOOR) {
         return hK.IRON;
      } else if (state.getBlock() == Nk.OAK_DOOR) {
         return fk.OAK.getMapColor();
      } else if (state.getBlock() == Nk.SPRUCE_DOOR) {
         return fk.SPRUCE.getMapColor();
      } else if (state.getBlock() == Nk.BIRCH_DOOR) {
         return fk.BIRCH.getMapColor();
      } else if (state.getBlock() == Nk.JUNGLE_DOOR) {
         return fk.JUNGLE.getMapColor();
      } else if (state.getBlock() == Nk.ACACIA_DOOR) {
         return fk.ACACIA.getMapColor();
      } else {
         return state.getBlock() == Nk.DARK_OAK_DOOR ? fk.DARK_OAK.getMapColor() : super.getMapColor(state, worldIn, pos);
      }
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (this.material == hM.IRON) {
         return false;
      } else {
         BlockPos blockpos = state.getValue(HALF) == dm.LOWER ? pos : pos.down();
         in iblockstate = pos.equals(blockpos) ? state : worldIn.getBlockState(blockpos);
         if (iblockstate.getBlock() != this) {
            return false;
         } else {
            state = iblockstate.cycleProperty(OPEN);
            worldIn.setBlockState(blockpos, state, 10);
            worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
            worldIn.playEvent(playerIn, (Boolean)state.getValue(OPEN) ? this.getOpenSound() : this.getCloseSound(), pos, 0);
            return true;
         }
      }
   }

   public void toggleDoor(bij worldIn, BlockPos pos, boolean open) {
      in iblockstate = worldIn.getBlockState(pos);
      if (iblockstate.getBlock() == this) {
         BlockPos blockpos = iblockstate.getValue(HALF) == dm.LOWER ? pos : pos.down();
         in iblockstate1 = pos == blockpos ? iblockstate : worldIn.getBlockState(blockpos);
         if (iblockstate1.getBlock() == this && (Boolean)iblockstate1.getValue(OPEN) != open) {
            worldIn.setBlockState(blockpos, iblockstate1.withProperty(OPEN, open), 10);
            worldIn.markBlockRangeForRenderUpdate(blockpos, pos);
            worldIn.playEvent((ME)null, open ? this.getOpenSound() : this.getCloseSound(), pos, 0);
         }
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (state.getValue(HALF) == dm.UPPER) {
         BlockPos blockpos = pos.down();
         in iblockstate = worldIn.getBlockState(blockpos);
         if (iblockstate.getBlock() != this) {
            worldIn.setBlockToAir(pos);
         } else if (blockIn != this) {
            iblockstate.neighborChanged(worldIn, blockpos, blockIn, fromPos);
         }
      } else {
         boolean flag1 = false;
         BlockPos blockpos1 = pos.up();
         in iblockstate1 = worldIn.getBlockState(blockpos1);
         if (iblockstate1.getBlock() != this) {
            worldIn.setBlockToAir(pos);
            flag1 = true;
         }

         if (!worldIn.getBlockState(pos.down()).isTopSolid()) {
            worldIn.setBlockToAir(pos);
            flag1 = true;
            if (iblockstate1.getBlock() == this) {
               worldIn.setBlockToAir(blockpos1);
            }
         }

         if (flag1) {
            if (!worldIn.isRemote) {
               this.dropBlockAsItem(worldIn, pos, state, 0);
            }
         } else {
            boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(blockpos1);
            if (blockIn != this && (flag || blockIn.getDefaultState().canProvidePower()) && flag != (Boolean)iblockstate1.getValue(POWERED)) {
               worldIn.setBlockState(blockpos1, iblockstate1.withProperty(POWERED, flag), 2);
               if (flag != (Boolean)state.getValue(OPEN)) {
                  worldIn.setBlockState(pos, state.withProperty(OPEN, flag), 2);
                  worldIn.markBlockRangeForRenderUpdate(pos, pos);
                  worldIn.playEvent((ME)null, flag ? this.getOpenSound() : this.getCloseSound(), pos, 0);
               }
            }
         }
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return state.getValue(HALF) == dm.UPPER ? NK.AIR : this.getItem();
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      if (pos.getY() >= 255) {
         return false;
      } else {
         return worldIn.getBlockState(pos.down()).isTopSolid() && super.canPlaceBlockAt(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos.up());
      }
   }

   /** @deprecated */
   public hJ getPushReaction(in state) {
      return hJ.DESTROY;
   }

   public static int combineMetadata(bfZ worldIn, BlockPos pos) {
      in iblockstate = worldIn.getBlockState(pos);
      int i = iblockstate.getBlock().getMetaFromState(iblockstate);
      boolean flag = isTop(i);
      in iblockstate1 = worldIn.getBlockState(pos.down());
      int j = iblockstate1.getBlock().getMetaFromState(iblockstate1);
      int k = flag ? j : i;
      in iblockstate2 = worldIn.getBlockState(pos.up());
      int l = iblockstate2.getBlock().getMetaFromState(iblockstate2);
      int i1 = flag ? i : l;
      boolean flag1 = (i1 & 1) != 0;
      boolean flag2 = (i1 & 2) != 0;
      return removeHalfBit(k) | (flag ? 8 : 0) | (flag1 ? 16 : 0) | (flag2 ? 32 : 0);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(this.getItem());
   }

   private OL getItem() {
      if (this == Nk.IRON_DOOR) {
         return NK.IRON_DOOR;
      } else if (this == Nk.SPRUCE_DOOR) {
         return NK.SPRUCE_DOOR;
      } else if (this == Nk.BIRCH_DOOR) {
         return NK.BIRCH_DOOR;
      } else if (this == Nk.JUNGLE_DOOR) {
         return NK.JUNGLE_DOOR;
      } else if (this == Nk.ACACIA_DOOR) {
         return NK.ACACIA_DOOR;
      } else {
         return this == Nk.DARK_OAK_DOOR ? NK.DARK_OAK_DOOR : NK.OAK_DOOR;
      }
   }

   public void onBlockHarvested(bij worldIn, BlockPos pos, in state, ME player) {
      BlockPos blockpos = pos.down();
      BlockPos blockpos1 = pos.up();
      if (player.capabilities.isCreativeMode && state.getValue(HALF) == dm.UPPER && worldIn.getBlockState(blockpos).getBlock() == this) {
         worldIn.setBlockToAir(blockpos);
      }

      if (state.getValue(HALF) == dm.LOWER && worldIn.getBlockState(blockpos1).getBlock() == this) {
         if (player.capabilities.isCreativeMode) {
            worldIn.setBlockToAir(pos);
         }

         worldIn.setBlockToAir(blockpos1);
      }

   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      in iblockstate;
      if (state.getValue(HALF) == dm.LOWER) {
         iblockstate = worldIn.getBlockState(pos.up());
         if (iblockstate.getBlock() == this) {
            state = state.withProperty(HINGE, iblockstate.getValue(HINGE)).withProperty(POWERED, iblockstate.getValue(POWERED));
         }
      } else {
         iblockstate = worldIn.getBlockState(pos.down());
         if (iblockstate.getBlock() == this) {
            state = state.withProperty(FACING, iblockstate.getValue(FACING)).withProperty(OPEN, iblockstate.getValue(OPEN));
         }
      }

      return state;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.getValue(HALF) != dm.LOWER ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      return mirrorIn == Mirror.NONE ? state : state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING))).cycleProperty(HINGE);
   }

   public in getStateFromMeta(int meta) {
      return (meta & 8) > 0 ? this.getDefaultState().withProperty(HALF, dm.UPPER).withProperty(HINGE, (meta & 1) > 0 ? dn.RIGHT : dn.LEFT).withProperty(POWERED, (meta & 2) > 0) : this.getDefaultState().withProperty(HALF, dm.LOWER).withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3).rotateYCCW()).withProperty(OPEN, (meta & 4) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      if (state.getValue(HALF) == dm.UPPER) {
         i |= 8;
         if (state.getValue(HINGE) == dn.RIGHT) {
            i |= 1;
         }

         if ((Boolean)state.getValue(POWERED)) {
            i |= 2;
         }
      } else {
         i |= ((EnumFacing)state.getValue(FACING)).rotateY().getHorizontalIndex();
         if ((Boolean)state.getValue(OPEN)) {
            i |= 4;
         }
      }

      return i;
   }

   protected static int removeHalfBit(int meta) {
      return meta & 7;
   }

   public static boolean isOpen(bfZ worldIn, BlockPos pos) {
      return isOpen(combineMetadata(worldIn, pos));
   }

   public static EnumFacing getFacing(bfZ worldIn, BlockPos pos) {
      return getFacing(combineMetadata(worldIn, pos));
   }

   public static EnumFacing getFacing(int combinedMeta) {
      return EnumFacing.byHorizontalIndex(combinedMeta & 3).rotateYCCW();
   }

   protected static boolean isOpen(int combinedMeta) {
      return (combinedMeta & 4) != 0;
   }

   protected static boolean isTop(int meta) {
      return (meta & 8) != 0;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{HALF, FACING, OPEN, HINGE, POWERED});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      FACING = en.FACING;
      OPEN = hV.create("open");
      HINGE = hX.create("hinge", dn.class);
      POWERED = hV.create("powered");
      HALF = hX.create("half", dm.class);
      SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.1875);
      NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.8125, 1.0, 1.0, 1.0);
      WEST_AABB = new AxisAlignedBB(0.8125, 0.0, 0.0, 1.0, 1.0, 1.0);
      EAST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.1875, 1.0, 1.0);
   }
}
