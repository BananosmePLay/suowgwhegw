package neo;

import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class hx extends co {
   public static final hV UP = hV.create("up");
   public static final hV NORTH = hV.create("north");
   public static final hV EAST = hV.create("east");
   public static final hV SOUTH = hV.create("south");
   public static final hV WEST = hV.create("west");
   public static final hV[] ALL_FACES;
   protected static final AxisAlignedBB UP_AABB;
   protected static final AxisAlignedBB WEST_AABB;
   protected static final AxisAlignedBB EAST_AABB;
   protected static final AxisAlignedBB NORTH_AABB;
   protected static final AxisAlignedBB SOUTH_AABB;

   public hx() {
      super(hM.VINE);
      this.setDefaultState(this.blockState.getBaseState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false));
      this.setTickRandomly(true);
      this.setCreativeTab(EN.DECORATIONS);
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      state = state.getActualState(source, pos);
      int i = 0;
      AxisAlignedBB axisalignedbb = FULL_BLOCK_AABB;
      if ((Boolean)state.getValue(UP)) {
         axisalignedbb = UP_AABB;
         ++i;
      }

      if ((Boolean)state.getValue(NORTH)) {
         axisalignedbb = NORTH_AABB;
         ++i;
      }

      if ((Boolean)state.getValue(EAST)) {
         axisalignedbb = EAST_AABB;
         ++i;
      }

      if ((Boolean)state.getValue(SOUTH)) {
         axisalignedbb = SOUTH_AABB;
         ++i;
      }

      if ((Boolean)state.getValue(WEST)) {
         axisalignedbb = WEST_AABB;
         ++i;
      }

      return i == 1 ? axisalignedbb : FULL_BLOCK_AABB;
   }

   public in getActualState(in state, bfZ worldIn, BlockPos pos) {
      BlockPos blockpos = pos.up();
      return state.withProperty(UP, worldIn.getBlockState(blockpos).getBlockFaceShape(worldIn, blockpos, EnumFacing.DOWN) == ib.SOLID);
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public boolean isReplaceable(bfZ worldIn, BlockPos pos) {
      return true;
   }

   public boolean canPlaceBlockOnSide(bij worldIn, BlockPos pos, EnumFacing side) {
      return side != EnumFacing.DOWN && side != EnumFacing.UP && this.canAttachTo(worldIn, pos, side);
   }

   public boolean canAttachTo(bij p_193395_1_, BlockPos p_193395_2_, EnumFacing p_193395_3_) {
      co block = p_193395_1_.getBlockState(p_193395_2_.up()).getBlock();
      return this.isAcceptableNeighbor(p_193395_1_, p_193395_2_.offset(p_193395_3_.getOpposite()), p_193395_3_) && (block == Nk.AIR || block == Nk.VINE || this.isAcceptableNeighbor(p_193395_1_, p_193395_2_.up(), EnumFacing.UP));
   }

   private boolean isAcceptableNeighbor(bij p_193396_1_, BlockPos p_193396_2_, EnumFacing p_193396_3_) {
      in iblockstate = p_193396_1_.getBlockState(p_193396_2_);
      return iblockstate.getBlockFaceShape(p_193396_1_, p_193396_2_, p_193396_3_) == ib.SOLID && !isExceptBlockForAttaching(iblockstate.getBlock());
   }

   protected static boolean isExceptBlockForAttaching(co p_193397_0_) {
      return p_193397_0_ instanceof gr || p_193397_0_ == Nk.BEACON || p_193397_0_ == Nk.CAULDRON || p_193397_0_ == Nk.GLASS || p_193397_0_ == Nk.STAINED_GLASS || p_193397_0_ == Nk.PISTON || p_193397_0_ == Nk.STICKY_PISTON || p_193397_0_ == Nk.PISTON_HEAD || p_193397_0_ == Nk.TRAPDOOR;
   }

   private boolean recheckGrownSides(bij worldIn, BlockPos pos, in state) {
      in iblockstate = state;
      Iterator var5 = EnumFacing.Plane.HORIZONTAL.iterator();

      while(true) {
         hV propertybool;
         in iblockstate1;
         do {
            EnumFacing enumfacing;
            do {
               do {
                  if (!var5.hasNext()) {
                     if (getNumGrownFaces(state) == 0) {
                        return false;
                     }

                     if (iblockstate != state) {
                        worldIn.setBlockState(pos, state, 2);
                     }

                     return true;
                  }

                  enumfacing = (EnumFacing)var5.next();
                  propertybool = getPropertyFor(enumfacing);
               } while(!(Boolean)state.getValue(propertybool));
            } while(this.canAttachTo(worldIn, pos, enumfacing.getOpposite()));

            iblockstate1 = worldIn.getBlockState(pos.up());
         } while(iblockstate1.getBlock() == this && (Boolean)iblockstate1.getValue(propertybool));

         state = state.withProperty(propertybool, false);
      }
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote && !this.recheckGrownSides(worldIn, pos, state)) {
         this.dropBlockAsItem(worldIn, pos, state, 0);
         worldIn.setBlockToAir(pos);
      }

   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote && worldIn.rand.nextInt(4) == 0) {
         int i = true;
         int j = 5;
         boolean flag = false;

         label176:
         for(int k = -4; k <= 4; ++k) {
            for(int l = -4; l <= 4; ++l) {
               for(int i1 = -1; i1 <= 1; ++i1) {
                  if (worldIn.getBlockState(pos.add(k, i1, l)).getBlock() == this) {
                     --j;
                     if (j <= 0) {
                        flag = true;
                        break label176;
                     }
                  }
               }
            }
         }

         EnumFacing enumfacing1 = EnumFacing.random(rand);
         BlockPos blockpos2 = pos.up();
         if (enumfacing1 == EnumFacing.UP && pos.getY() < 255 && worldIn.isAirBlock(blockpos2)) {
            in iblockstate2 = state;
            Iterator var23 = EnumFacing.Plane.HORIZONTAL.iterator();

            while(true) {
               while(var23.hasNext()) {
                  EnumFacing enumfacing2 = (EnumFacing)var23.next();
                  if (rand.nextBoolean() && this.canAttachTo(worldIn, blockpos2, enumfacing2.getOpposite())) {
                     iblockstate2 = iblockstate2.withProperty(getPropertyFor(enumfacing2), true);
                  } else {
                     iblockstate2 = iblockstate2.withProperty(getPropertyFor(enumfacing2), false);
                  }
               }

               if ((Boolean)iblockstate2.getValue(NORTH) || (Boolean)iblockstate2.getValue(EAST) || (Boolean)iblockstate2.getValue(SOUTH) || (Boolean)iblockstate2.getValue(WEST)) {
                  worldIn.setBlockState(blockpos2, iblockstate2, 2);
               }
               break;
            }
         } else {
            in iblockstate;
            co block;
            BlockPos blockpos3;
            if (enumfacing1.getAxis().isHorizontal() && !(Boolean)state.getValue(getPropertyFor(enumfacing1))) {
               if (!flag) {
                  blockpos3 = pos.offset(enumfacing1);
                  iblockstate = worldIn.getBlockState(blockpos3);
                  block = iblockstate.getBlock();
                  if (block.material == hM.AIR) {
                     EnumFacing enumfacing3 = enumfacing1.rotateY();
                     EnumFacing enumfacing4 = enumfacing1.rotateYCCW();
                     boolean flag1 = (Boolean)state.getValue(getPropertyFor(enumfacing3));
                     boolean flag2 = (Boolean)state.getValue(getPropertyFor(enumfacing4));
                     BlockPos blockpos = blockpos3.offset(enumfacing3);
                     BlockPos blockpos1 = blockpos3.offset(enumfacing4);
                     if (flag1 && this.canAttachTo(worldIn, blockpos.offset(enumfacing3), enumfacing3)) {
                        worldIn.setBlockState(blockpos3, this.getDefaultState().withProperty(getPropertyFor(enumfacing3), true), 2);
                     } else if (flag2 && this.canAttachTo(worldIn, blockpos1.offset(enumfacing4), enumfacing4)) {
                        worldIn.setBlockState(blockpos3, this.getDefaultState().withProperty(getPropertyFor(enumfacing4), true), 2);
                     } else if (flag1 && worldIn.isAirBlock(blockpos) && this.canAttachTo(worldIn, blockpos, enumfacing1)) {
                        worldIn.setBlockState(blockpos, this.getDefaultState().withProperty(getPropertyFor(enumfacing1.getOpposite()), true), 2);
                     } else if (flag2 && worldIn.isAirBlock(blockpos1) && this.canAttachTo(worldIn, blockpos1, enumfacing1)) {
                        worldIn.setBlockState(blockpos1, this.getDefaultState().withProperty(getPropertyFor(enumfacing1.getOpposite()), true), 2);
                     }
                  } else if (iblockstate.getBlockFaceShape(worldIn, blockpos3, enumfacing1) == ib.SOLID) {
                     worldIn.setBlockState(pos, state.withProperty(getPropertyFor(enumfacing1), true), 2);
                  }
               }
            } else if (pos.getY() > 1) {
               blockpos3 = pos.down();
               iblockstate = worldIn.getBlockState(blockpos3);
               block = iblockstate.getBlock();
               in iblockstate4;
               Iterator var14;
               EnumFacing enumfacing;
               if (block.material == hM.AIR) {
                  iblockstate4 = state;
                  var14 = EnumFacing.Plane.HORIZONTAL.iterator();

                  while(var14.hasNext()) {
                     enumfacing = (EnumFacing)var14.next();
                     if (rand.nextBoolean()) {
                        iblockstate4 = iblockstate4.withProperty(getPropertyFor(enumfacing), false);
                     }
                  }

                  if ((Boolean)iblockstate4.getValue(NORTH) || (Boolean)iblockstate4.getValue(EAST) || (Boolean)iblockstate4.getValue(SOUTH) || (Boolean)iblockstate4.getValue(WEST)) {
                     worldIn.setBlockState(blockpos3, iblockstate4, 2);
                  }
               } else if (block == this) {
                  iblockstate4 = iblockstate;
                  var14 = EnumFacing.Plane.HORIZONTAL.iterator();

                  while(var14.hasNext()) {
                     enumfacing = (EnumFacing)var14.next();
                     hV propertybool = getPropertyFor(enumfacing);
                     if (rand.nextBoolean() && (Boolean)state.getValue(propertybool)) {
                        iblockstate4 = iblockstate4.withProperty(propertybool, true);
                     }
                  }

                  if ((Boolean)iblockstate4.getValue(NORTH) || (Boolean)iblockstate4.getValue(EAST) || (Boolean)iblockstate4.getValue(SOUTH) || (Boolean)iblockstate4.getValue(WEST)) {
                     worldIn.setBlockState(blockpos3, iblockstate4, 2);
                  }
               }
            }
         }
      }

   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      in iblockstate = this.getDefaultState().withProperty(UP, false).withProperty(NORTH, false).withProperty(EAST, false).withProperty(SOUTH, false).withProperty(WEST, false);
      return facing.getAxis().isHorizontal() ? iblockstate.withProperty(getPropertyFor(facing.getOpposite()), true) : iblockstate;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.AIR;
   }

   public int quantityDropped(Random random) {
      return 0;
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (!worldIn.isRemote && stack.getItem() == NK.SHEARS) {
         player.addStat(XV.getBlockStats(this));
         spawnAsEntity(worldIn, pos, new Qy(Nk.VINE, 1, 0));
      } else {
         super.harvestBlock(worldIn, player, pos, state, te, stack);
      }

   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(SOUTH, (meta & 1) > 0).withProperty(WEST, (meta & 2) > 0).withProperty(NORTH, (meta & 4) > 0).withProperty(EAST, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      if ((Boolean)state.getValue(SOUTH)) {
         i |= 1;
      }

      if ((Boolean)state.getValue(WEST)) {
         i |= 2;
      }

      if ((Boolean)state.getValue(NORTH)) {
         i |= 4;
      }

      if ((Boolean)state.getValue(EAST)) {
         i |= 8;
      }

      return i;
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{UP, NORTH, EAST, SOUTH, WEST});
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      switch (rot) {
         case CLOCKWISE_180:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(EAST, state.getValue(WEST)).withProperty(SOUTH, state.getValue(NORTH)).withProperty(WEST, state.getValue(EAST));
         case COUNTERCLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(EAST)).withProperty(EAST, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(WEST)).withProperty(WEST, state.getValue(NORTH));
         case CLOCKWISE_90:
            return state.withProperty(NORTH, state.getValue(WEST)).withProperty(EAST, state.getValue(NORTH)).withProperty(SOUTH, state.getValue(EAST)).withProperty(WEST, state.getValue(SOUTH));
         default:
            return state;
      }
   }

   /** @deprecated */
   public in withMirror(in state, Mirror mirrorIn) {
      switch (mirrorIn) {
         case LEFT_RIGHT:
            return state.withProperty(NORTH, state.getValue(SOUTH)).withProperty(SOUTH, state.getValue(NORTH));
         case FRONT_BACK:
            return state.withProperty(EAST, state.getValue(WEST)).withProperty(WEST, state.getValue(EAST));
         default:
            return super.withMirror(state, mirrorIn);
      }
   }

   public static hV getPropertyFor(EnumFacing side) {
      switch (side) {
         case UP:
            return UP;
         case NORTH:
            return NORTH;
         case SOUTH:
            return SOUTH;
         case WEST:
            return WEST;
         case EAST:
            return EAST;
         default:
            throw new IllegalArgumentException(side + " is an invalid choice");
      }
   }

   public static int getNumGrownFaces(in state) {
      int i = 0;
      hV[] var2 = ALL_FACES;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         hV propertybool = var2[var4];
         if ((Boolean)state.getValue(propertybool)) {
            ++i;
         }
      }

      return i;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      ALL_FACES = new hV[]{UP, NORTH, SOUTH, WEST, EAST};
      UP_AABB = new AxisAlignedBB(0.0, 0.9375, 0.0, 1.0, 1.0, 1.0);
      WEST_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 0.0625, 1.0, 1.0);
      EAST_AABB = new AxisAlignedBB(0.9375, 0.0, 0.0, 1.0, 1.0, 1.0);
      NORTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.0, 1.0, 1.0, 0.0625);
      SOUTH_AABB = new AxisAlignedBB(0.0, 0.0, 0.9375, 1.0, 1.0, 1.0);
   }
}
