package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.InventoryLargeChest;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public class cT extends dd {
   public static final hW FACING;
   protected static final AxisAlignedBB NORTH_CHEST_AABB;
   protected static final AxisAlignedBB SOUTH_CHEST_AABB;
   protected static final AxisAlignedBB WEST_CHEST_AABB;
   protected static final AxisAlignedBB EAST_CHEST_AABB;
   protected static final AxisAlignedBB NOT_CONNECTED_AABB;
   public final cS chestType;

   protected cT(cS chestTypeIn) {
      super(hM.WOOD);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.chestType = chestTypeIn;
      this.setCreativeTab(chestTypeIn == cS.TRAP ? EN.REDSTONE : EN.DECORATIONS);
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean hasCustomBreakingProgress(in state) {
      return true;
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      if (source.getBlockState(pos.north()).getBlock() == this) {
         return NORTH_CHEST_AABB;
      } else if (source.getBlockState(pos.south()).getBlock() == this) {
         return SOUTH_CHEST_AABB;
      } else if (source.getBlockState(pos.west()).getBlock() == this) {
         return WEST_CHEST_AABB;
      } else {
         return source.getBlockState(pos.east()).getBlock() == this ? EAST_CHEST_AABB : NOT_CONNECTED_AABB;
      }
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      this.checkForSurroundingChests(worldIn, pos, state);
      Iterator var4 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var4.hasNext()) {
         EnumFacing enumfacing = (EnumFacing)var4.next();
         BlockPos blockpos = pos.offset(enumfacing);
         in iblockstate = worldIn.getBlockState(blockpos);
         if (iblockstate.getBlock() == this) {
            this.checkForSurroundingChests(worldIn, blockpos, iblockstate);
         }
      }

   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing());
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      EnumFacing enumfacing = EnumFacing.byHorizontalIndex(MathHelper.floor((double)(placer.rotationYaw * 4.0F / 360.0F) + 0.5) & 3).getOpposite();
      state = state.withProperty(FACING, enumfacing);
      BlockPos blockpos = pos.north();
      BlockPos blockpos1 = pos.south();
      BlockPos blockpos2 = pos.west();
      BlockPos blockpos3 = pos.east();
      boolean flag = this == worldIn.getBlockState(blockpos).getBlock();
      boolean flag1 = this == worldIn.getBlockState(blockpos1).getBlock();
      boolean flag2 = this == worldIn.getBlockState(blockpos2).getBlock();
      boolean flag3 = this == worldIn.getBlockState(blockpos3).getBlock();
      if (!flag && !flag1 && !flag2 && !flag3) {
         worldIn.setBlockState(pos, state, 3);
      } else if (enumfacing.getAxis() == EnumFacing.Axis.X && (flag || flag1)) {
         if (flag) {
            worldIn.setBlockState(blockpos, state, 3);
         } else {
            worldIn.setBlockState(blockpos1, state, 3);
         }

         worldIn.setBlockState(pos, state, 3);
      } else if (enumfacing.getAxis() == EnumFacing.Axis.Z && (flag2 || flag3)) {
         if (flag2) {
            worldIn.setBlockState(blockpos2, state, 3);
         } else {
            worldIn.setBlockState(blockpos3, state, 3);
         }

         worldIn.setBlockState(pos, state, 3);
      }

      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yn) {
            ((Yn)tileentity).setCustomName(stack.getDisplayName());
         }
      }

   }

   public in checkForSurroundingChests(bij worldIn, BlockPos pos, in state) {
      if (worldIn.isRemote) {
         return state;
      } else {
         in iblockstate = worldIn.getBlockState(pos.north());
         in iblockstate1 = worldIn.getBlockState(pos.south());
         in iblockstate2 = worldIn.getBlockState(pos.west());
         in iblockstate3 = worldIn.getBlockState(pos.east());
         EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         if (iblockstate.getBlock() != this && iblockstate1.getBlock() != this) {
            boolean flag = iblockstate.isFullBlock();
            boolean flag1 = iblockstate1.isFullBlock();
            if (iblockstate2.getBlock() == this || iblockstate3.getBlock() == this) {
               BlockPos blockpos1 = iblockstate2.getBlock() == this ? pos.west() : pos.east();
               in iblockstate7 = worldIn.getBlockState(blockpos1.north());
               in iblockstate6 = worldIn.getBlockState(blockpos1.south());
               enumfacing = EnumFacing.SOUTH;
               EnumFacing enumfacing2;
               if (iblockstate2.getBlock() == this) {
                  enumfacing2 = (EnumFacing)iblockstate2.getValue(FACING);
               } else {
                  enumfacing2 = (EnumFacing)iblockstate3.getValue(FACING);
               }

               if (enumfacing2 == EnumFacing.NORTH) {
                  enumfacing = EnumFacing.NORTH;
               }

               if ((flag || iblockstate7.isFullBlock()) && !flag1 && !iblockstate6.isFullBlock()) {
                  enumfacing = EnumFacing.SOUTH;
               }

               if ((flag1 || iblockstate6.isFullBlock()) && !flag && !iblockstate7.isFullBlock()) {
                  enumfacing = EnumFacing.NORTH;
               }
            }
         } else {
            BlockPos blockpos = iblockstate.getBlock() == this ? pos.north() : pos.south();
            in iblockstate4 = worldIn.getBlockState(blockpos.west());
            in iblockstate5 = worldIn.getBlockState(blockpos.east());
            enumfacing = EnumFacing.EAST;
            EnumFacing enumfacing1;
            if (iblockstate.getBlock() == this) {
               enumfacing1 = (EnumFacing)iblockstate.getValue(FACING);
            } else {
               enumfacing1 = (EnumFacing)iblockstate1.getValue(FACING);
            }

            if (enumfacing1 == EnumFacing.WEST) {
               enumfacing = EnumFacing.WEST;
            }

            if ((iblockstate2.isFullBlock() || iblockstate4.isFullBlock()) && !iblockstate3.isFullBlock() && !iblockstate5.isFullBlock()) {
               enumfacing = EnumFacing.EAST;
            }

            if ((iblockstate3.isFullBlock() || iblockstate5.isFullBlock()) && !iblockstate2.isFullBlock() && !iblockstate4.isFullBlock()) {
               enumfacing = EnumFacing.WEST;
            }
         }

         state = state.withProperty(FACING, enumfacing);
         worldIn.setBlockState(pos, state, 3);
         return state;
      }
   }

   public in correctFacing(bij worldIn, BlockPos pos, in state) {
      EnumFacing enumfacing = null;
      Iterator var5 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(var5.hasNext()) {
         EnumFacing enumfacing1 = (EnumFacing)var5.next();
         in iblockstate = worldIn.getBlockState(pos.offset(enumfacing1));
         if (iblockstate.getBlock() == this) {
            return state;
         }

         if (iblockstate.isFullBlock()) {
            if (enumfacing != null) {
               enumfacing = null;
               break;
            }

            enumfacing = enumfacing1;
         }
      }

      if (enumfacing != null) {
         return state.withProperty(FACING, enumfacing.getOpposite());
      } else {
         EnumFacing enumfacing2 = (EnumFacing)state.getValue(FACING);
         if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock()) {
            enumfacing2 = enumfacing2.getOpposite();
         }

         if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock()) {
            enumfacing2 = enumfacing2.rotateY();
         }

         if (worldIn.getBlockState(pos.offset(enumfacing2)).isFullBlock()) {
            enumfacing2 = enumfacing2.getOpposite();
         }

         return state.withProperty(FACING, enumfacing2);
      }
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      int i = 0;
      BlockPos blockpos = pos.west();
      BlockPos blockpos1 = pos.east();
      BlockPos blockpos2 = pos.north();
      BlockPos blockpos3 = pos.south();
      if (worldIn.getBlockState(blockpos).getBlock() == this) {
         if (this.isDoubleChest(worldIn, blockpos)) {
            return false;
         }

         ++i;
      }

      if (worldIn.getBlockState(blockpos1).getBlock() == this) {
         if (this.isDoubleChest(worldIn, blockpos1)) {
            return false;
         }

         ++i;
      }

      if (worldIn.getBlockState(blockpos2).getBlock() == this) {
         if (this.isDoubleChest(worldIn, blockpos2)) {
            return false;
         }

         ++i;
      }

      if (worldIn.getBlockState(blockpos3).getBlock() == this) {
         if (this.isDoubleChest(worldIn, blockpos3)) {
            return false;
         }

         ++i;
      }

      return i <= 1;
   }

   private boolean isDoubleChest(bij worldIn, BlockPos pos) {
      if (worldIn.getBlockState(pos).getBlock() != this) {
         return false;
      } else {
         Iterator var3 = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing enumfacing;
         do {
            if (!var3.hasNext()) {
               return false;
            }

            enumfacing = (EnumFacing)var3.next();
         } while(worldIn.getBlockState(pos.offset(enumfacing)).getBlock() != this);

         return true;
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof Yn) {
         tileentity.updateContainingBlockInfo();
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof IInventory) {
         InventoryHelper.dropInventoryItems(worldIn, pos, (IInventory)tileentity);
         worldIn.updateComparatorOutputLevel(pos, this);
      }

      super.breakBlock(worldIn, pos, state);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         bgb ilockablecontainer = this.getLockableContainer(worldIn, pos);
         if (ilockablecontainer != null) {
            playerIn.displayGUIChest(ilockablecontainer);
            if (this.chestType == cS.BASIC) {
               playerIn.addStat(XV.CHEST_OPENED);
            } else if (this.chestType == cS.TRAP) {
               playerIn.addStat(XV.TRAPPED_CHEST_TRIGGERED);
            }
         }

         return true;
      }
   }

   @Nullable
   public bgb getLockableContainer(bij worldIn, BlockPos pos) {
      return this.getContainer(worldIn, pos, false);
   }

   @Nullable
   public bgb getContainer(bij worldIn, BlockPos pos, boolean allowBlocking) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (!(tileentity instanceof Yn)) {
         return null;
      } else {
         bgb ilockablecontainer = (Yn)tileentity;
         if (!allowBlocking && this.isBlocked(worldIn, pos)) {
            return null;
         } else {
            Iterator var6 = EnumFacing.Plane.HORIZONTAL.iterator();

            while(true) {
               while(true) {
                  EnumFacing enumfacing;
                  Yg tileentity1;
                  do {
                     BlockPos blockpos;
                     co block;
                     do {
                        if (!var6.hasNext()) {
                           return (bgb)ilockablecontainer;
                        }

                        enumfacing = (EnumFacing)var6.next();
                        blockpos = pos.offset(enumfacing);
                        block = worldIn.getBlockState(blockpos).getBlock();
                     } while(block != this);

                     if (this.isBlocked(worldIn, blockpos)) {
                        return null;
                     }

                     tileentity1 = worldIn.getTileEntity(blockpos);
                  } while(!(tileentity1 instanceof Yn));

                  if (enumfacing != EnumFacing.WEST && enumfacing != EnumFacing.NORTH) {
                     ilockablecontainer = new InventoryLargeChest("container.chestDouble", (bgb)ilockablecontainer, (Yn)tileentity1);
                  } else {
                     ilockablecontainer = new InventoryLargeChest("container.chestDouble", (Yn)tileentity1, (bgb)ilockablecontainer);
                  }
               }
            }
         }
      }
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yn();
   }

   /** @deprecated */
   public boolean canProvidePower(in state) {
      return this.chestType == cS.TRAP;
   }

   /** @deprecated */
   public int getWeakPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      if (!blockState.canProvidePower()) {
         return 0;
      } else {
         int i = 0;
         Yg tileentity = blockAccess.getTileEntity(pos);
         if (tileentity instanceof Yn) {
            i = ((Yn)tileentity).numPlayersUsing;
         }

         return MathHelper.clamp(i, 0, 15);
      }
   }

   /** @deprecated */
   public int getStrongPower(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return side == EnumFacing.UP ? blockState.getWeakPower(blockAccess, pos, side) : 0;
   }

   private boolean isBlocked(bij worldIn, BlockPos pos) {
      return this.isBelowSolidBlock(worldIn, pos) || this.isOcelotSittingOnChest(worldIn, pos);
   }

   private boolean isBelowSolidBlock(bij worldIn, BlockPos pos) {
      return worldIn.getBlockState(pos.up()).isNormalCube();
   }

   private boolean isOcelotSittingOnChest(bij worldIn, BlockPos pos) {
      Iterator var3 = worldIn.getEntitiesWithinAABB(LN.class, new AxisAlignedBB((double)pos.getX(), (double)(pos.getY() + 1), (double)pos.getZ(), (double)(pos.getX() + 1), (double)(pos.getY() + 2), (double)(pos.getZ() + 1))).iterator();

      LN entityocelot;
      do {
         if (!var3.hasNext()) {
            return false;
         }

         Ig entity = (Ig)var3.next();
         entityocelot = (LN)entity;
      } while(!entityocelot.isSitting());

      return true;
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return Container.calcRedstoneFromInventory(this.getLockableContainer(worldIn, pos));
   }

   public in getStateFromMeta(int meta) {
      EnumFacing enumfacing = EnumFacing.byIndex(meta);
      if (enumfacing.getAxis() == EnumFacing.Axis.Y) {
         enumfacing = EnumFacing.NORTH;
      }

      return this.getDefaultState().withProperty(FACING, enumfacing);
   }

   public int getMetaFromState(in state) {
      return ((EnumFacing)state.getValue(FACING)).getIndex();
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
      return new ii(this, new hT[]{FACING});
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      FACING = en.FACING;
      NORTH_CHEST_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0, 0.9375, 0.875, 0.9375);
      SOUTH_CHEST_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.875, 1.0);
      WEST_CHEST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0625, 0.9375, 0.875, 0.9375);
      EAST_CHEST_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 1.0, 0.875, 0.9375);
      NOT_CONNECTED_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.875, 0.9375);
   }
}
