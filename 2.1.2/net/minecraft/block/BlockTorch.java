package net.minecraft.block;

import com.google.common.base.Predicate;
import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockTorch extends Block {
   public static final PropertyDirection FACING = PropertyDirection.create("facing", new Predicate<EnumFacing>() {
      public boolean apply(@Nullable EnumFacing p_apply_1_) {
         return p_apply_1_ != EnumFacing.DOWN;
      }

      // $FF: synthetic method
      // $FF: bridge method
      public boolean apply(@Nullable Object var1) {
         return this.apply((EnumFacing)var1);
      }
   });
   protected static final AxisAlignedBB STANDING_AABB = new AxisAlignedBB(0.4000000059604645, 0.0, 0.4000000059604645, 0.6000000238418579, 0.6000000238418579, 0.6000000238418579);
   protected static final AxisAlignedBB TORCH_NORTH_AABB = new AxisAlignedBB(0.3499999940395355, 0.20000000298023224, 0.699999988079071, 0.6499999761581421, 0.800000011920929, 1.0);
   protected static final AxisAlignedBB TORCH_SOUTH_AABB = new AxisAlignedBB(0.3499999940395355, 0.20000000298023224, 0.0, 0.6499999761581421, 0.800000011920929, 0.30000001192092896);
   protected static final AxisAlignedBB TORCH_WEST_AABB = new AxisAlignedBB(0.699999988079071, 0.20000000298023224, 0.3499999940395355, 1.0, 0.800000011920929, 0.6499999761581421);
   protected static final AxisAlignedBB TORCH_EAST_AABB = new AxisAlignedBB(0.0, 0.20000000298023224, 0.3499999940395355, 0.30000001192092896, 0.800000011920929, 0.6499999761581421);

   protected BlockTorch() {
      super(Material.CIRCUITS);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
      this.setTickRandomly(true);
      this.setCreativeTab(CreativeTabs.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      switch ((EnumFacing)state.getValue(FACING)) {
         case EAST:
            return TORCH_EAST_AABB;
         case WEST:
            return TORCH_WEST_AABB;
         case SOUTH:
            return TORCH_SOUTH_AABB;
         case NORTH:
            return TORCH_NORTH_AABB;
         default:
            return STANDING_AABB;
      }
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   private boolean canPlaceOn(World worldIn, BlockPos pos) {
      Block block = worldIn.getBlockState(pos).getBlock();
      boolean flag = block == Blocks.END_GATEWAY || block == Blocks.LIT_PUMPKIN;
      if (worldIn.getBlockState(pos).isTopSolid()) {
         return !flag;
      } else {
         boolean flag1 = block instanceof BlockFence || block == Blocks.GLASS || block == Blocks.COBBLESTONE_WALL || block == Blocks.STAINED_GLASS;
         return flag1 && !flag;
      }
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      Iterator var3 = FACING.getAllowedValues().iterator();

      EnumFacing enumfacing;
      do {
         if (!var3.hasNext()) {
            return false;
         }

         enumfacing = (EnumFacing)var3.next();
      } while(!this.canPlaceAt(worldIn, pos, enumfacing));

      return true;
   }

   private boolean canPlaceAt(World worldIn, BlockPos pos, EnumFacing facing) {
      BlockPos blockpos = pos.offset(facing.getOpposite());
      IBlockState iblockstate = worldIn.getBlockState(blockpos);
      Block block = iblockstate.getBlock();
      BlockFaceShape blockfaceshape = iblockstate.getBlockFaceShape(worldIn, blockpos, facing);
      if (facing.equals(EnumFacing.UP) && this.canPlaceOn(worldIn, blockpos)) {
         return true;
      } else if (facing != EnumFacing.UP && facing != EnumFacing.DOWN) {
         return !isExceptBlockForAttachWithPiston(block) && blockfaceshape == BlockFaceShape.SOLID;
      } else {
         return false;
      }
   }

   public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
      if (this.canPlaceAt(worldIn, pos, facing)) {
         return this.getDefaultState().withProperty(FACING, facing);
      } else {
         Iterator var9 = EnumFacing.Plane.HORIZONTAL.iterator();

         EnumFacing enumfacing;
         do {
            if (!var9.hasNext()) {
               return this.getDefaultState();
            }

            enumfacing = (EnumFacing)var9.next();
         } while(!this.canPlaceAt(worldIn, pos, enumfacing));

         return this.getDefaultState().withProperty(FACING, enumfacing);
      }
   }

   public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
      this.checkForDrop(worldIn, pos, state);
   }

   public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
      this.onNeighborChangeInternal(worldIn, pos, state);
   }

   protected boolean onNeighborChangeInternal(World worldIn, BlockPos pos, IBlockState state) {
      if (!this.checkForDrop(worldIn, pos, state)) {
         return true;
      } else {
         EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         EnumFacing.Axis enumfacing$axis = enumfacing.getAxis();
         EnumFacing enumfacing1 = enumfacing.getOpposite();
         BlockPos blockpos = pos.offset(enumfacing1);
         boolean flag = false;
         if (enumfacing$axis.isHorizontal() && worldIn.getBlockState(blockpos).getBlockFaceShape(worldIn, blockpos, enumfacing) != BlockFaceShape.SOLID) {
            flag = true;
         } else if (enumfacing$axis.isVertical() && !this.canPlaceOn(worldIn, blockpos)) {
            flag = true;
         }

         if (flag) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
            return true;
         } else {
            return false;
         }
      }
   }

   protected boolean checkForDrop(World worldIn, BlockPos pos, IBlockState state) {
      if (state.getBlock() == this && this.canPlaceAt(worldIn, pos, (EnumFacing)state.getValue(FACING))) {
         return true;
      } else {
         if (worldIn.getBlockState(pos).getBlock() == this) {
            this.dropBlockAsItem(worldIn, pos, state, 0);
            worldIn.setBlockToAir(pos);
         }

         return false;
      }
   }

   public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
      EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
      double d0 = (double)pos.getX() + 0.5;
      double d1 = (double)pos.getY() + 0.7;
      double d2 = (double)pos.getZ() + 0.5;
      double d3 = 0.22;
      double d4 = 0.27;
      if (enumfacing.getAxis().isHorizontal()) {
         EnumFacing enumfacing1 = enumfacing.getOpposite();
         worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.27 * (double)enumfacing1.getXOffset(), d1 + 0.22, d2 + 0.27 * (double)enumfacing1.getZOffset(), 0.0, 0.0, 0.0);
         worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.27 * (double)enumfacing1.getXOffset(), d1 + 0.22, d2 + 0.27 * (double)enumfacing1.getZOffset(), 0.0, 0.0, 0.0);
      } else {
         worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0, d1, d2, 0.0, 0.0, 0.0);
         worldIn.spawnParticle(EnumParticleTypes.FLAME, d0, d1, d2, 0.0, 0.0, 0.0);
      }

   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public IBlockState getStateFromMeta(int meta) {
      IBlockState iblockstate = this.getDefaultState();
      switch (meta) {
         case 1:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.EAST);
            break;
         case 2:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.WEST);
            break;
         case 3:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.SOUTH);
            break;
         case 4:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.NORTH);
            break;
         case 5:
         default:
            iblockstate = iblockstate.withProperty(FACING, EnumFacing.UP);
      }

      return iblockstate;
   }

   public int getMetaFromState(IBlockState state) {
      int i = 0;
      switch ((EnumFacing)state.getValue(FACING)) {
         case EAST:
            i |= 1;
            break;
         case WEST:
            i |= 2;
            break;
         case SOUTH:
            i |= 3;
            break;
         case NORTH:
            i |= 4;
            break;
         case DOWN:
         case UP:
         default:
            i |= 5;
      }

      return i;
   }

   /** @deprecated */
   public IBlockState withRotation(IBlockState state, Rotation rot) {
      return state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   /** @deprecated */
   public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
      return state.withRotation(mirrorIn.toRotation((EnumFacing)state.getValue(FACING)));
   }

   protected BlockStateContainer createBlockState() {
      return new BlockStateContainer(this, new IProperty[]{FACING});
   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }
}
