package neo;

import java.util.Random;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class dY extends dd {
   public static final hW FACING;
   private final boolean isBurning;
   private static boolean keepInventory;

   protected dY(boolean isBurning) {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.isBurning = isBurning;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.FURNACE);
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      this.setDefaultFacing(worldIn, pos, state);
   }

   private void setDefaultFacing(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         in iblockstate = worldIn.getBlockState(pos.north());
         in iblockstate1 = worldIn.getBlockState(pos.south());
         in iblockstate2 = worldIn.getBlockState(pos.west());
         in iblockstate3 = worldIn.getBlockState(pos.east());
         EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         if (enumfacing == EnumFacing.NORTH && iblockstate.isFullBlock() && !iblockstate1.isFullBlock()) {
            enumfacing = EnumFacing.SOUTH;
         } else if (enumfacing == EnumFacing.SOUTH && iblockstate1.isFullBlock() && !iblockstate.isFullBlock()) {
            enumfacing = EnumFacing.NORTH;
         } else if (enumfacing == EnumFacing.WEST && iblockstate2.isFullBlock() && !iblockstate3.isFullBlock()) {
            enumfacing = EnumFacing.EAST;
         } else if (enumfacing == EnumFacing.EAST && iblockstate3.isFullBlock() && !iblockstate2.isFullBlock()) {
            enumfacing = EnumFacing.WEST;
         }

         worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing), 2);
      }

   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      if (this.isBurning) {
         EnumFacing enumfacing = (EnumFacing)stateIn.getValue(FACING);
         double d0 = (double)pos.getX() + 0.5;
         double d1 = (double)pos.getY() + rand.nextDouble() * 6.0 / 16.0;
         double d2 = (double)pos.getZ() + 0.5;
         double d3 = 0.52;
         double d4 = rand.nextDouble() * 0.6 - 0.3;
         if (rand.nextDouble() < 0.1) {
            worldIn.playSound((double)pos.getX() + 0.5, (double)pos.getY(), (double)pos.getZ() + 0.5, NO.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
         }

         switch (enumfacing) {
            case WEST:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 - 0.52, d1, d2 + d4, 0.0, 0.0, 0.0);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 - 0.52, d1, d2 + d4, 0.0, 0.0, 0.0);
               break;
            case EAST:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + 0.52, d1, d2 + d4, 0.0, 0.0, 0.0);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + 0.52, d1, d2 + d4, 0.0, 0.0, 0.0);
               break;
            case NORTH:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 - 0.52, 0.0, 0.0, 0.0);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 - 0.52, 0.0, 0.0, 0.0);
               break;
            case SOUTH:
               worldIn.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, d0 + d4, d1, d2 + 0.52, 0.0, 0.0, 0.0);
               worldIn.spawnParticle(EnumParticleTypes.FLAME, d0 + d4, d1, d2 + 0.52, 0.0, 0.0, 0.0);
         }
      }

   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YA) {
            playerIn.displayGUIChest((YA)tileentity);
            playerIn.addStat(XV.FURNACE_INTERACTION);
         }

         return true;
      }
   }

   public static void setState(boolean active, bij worldIn, BlockPos pos) {
      in iblockstate = worldIn.getBlockState(pos);
      Yg tileentity = worldIn.getTileEntity(pos);
      keepInventory = true;
      if (active) {
         worldIn.setBlockState(pos, Nk.LIT_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
         worldIn.setBlockState(pos, Nk.LIT_FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
      } else {
         worldIn.setBlockState(pos, Nk.FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
         worldIn.setBlockState(pos, Nk.FURNACE.getDefaultState().withProperty(FACING, iblockstate.getValue(FACING)), 3);
      }

      keepInventory = false;
      if (tileentity != null) {
         tileentity.validate();
         worldIn.setTileEntity(pos, tileentity);
      }

   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YA();
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YA) {
            ((YA)tileentity).setCustomInventoryName(stack.getDisplayName());
         }
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      if (!keepInventory) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof YA) {
            InventoryHelper.dropInventoryItems(worldIn, (BlockPos)pos, (YA)tileentity);
            worldIn.updateComparatorOutputLevel(pos, this);
         }
      }

      super.breakBlock(worldIn, pos, state);
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return Container.calcRedstone(worldIn.getTileEntity(pos));
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.FURNACE);
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
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

   static {
      FACING = en.FACING;
   }
}
