package net.minecraft.block;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.BlockFaceShape;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockSign extends BlockContainer {
   protected static final AxisAlignedBB SIGN_AABB = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 0.75);

   protected BlockSign() {
      super(Material.WOOD);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
      return SIGN_AABB;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, IBlockAccess worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isFullCube(IBlockState state) {
      return false;
   }

   /** @deprecated */
   public boolean hasCustomBreakingProgress(IBlockState state) {
      return true;
   }

   public boolean isPassable(IBlockAccess worldIn, BlockPos pos) {
      return true;
   }

   /** @deprecated */
   public boolean isOpaqueCube(IBlockState state) {
      return false;
   }

   public boolean canSpawnInBlock() {
      return true;
   }

   public TileEntity createNewTileEntity(World worldIn, int meta) {
      return new TileEntitySign();
   }

   public Item getItemDropped(IBlockState state, Random rand, int fortune) {
      return Items.SIGN;
   }

   public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
      return new ItemStack(Items.SIGN);
   }

   public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         TileEntity tileentity = worldIn.getTileEntity(pos);
         return tileentity instanceof TileEntitySign ? ((TileEntitySign)tileentity).executeCommand(playerIn) : false;
      }
   }

   public boolean canPlaceBlockAt(World worldIn, BlockPos pos) {
      return !this.hasInvalidNeighbor(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos);
   }

   /** @deprecated */
   public BlockFaceShape getBlockFaceShape(IBlockAccess worldIn, IBlockState state, BlockPos pos, EnumFacing face) {
      return BlockFaceShape.UNDEFINED;
   }
}
