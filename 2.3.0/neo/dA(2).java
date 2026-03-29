package neo;

import java.util.Random;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class dA extends dd {
   public static final hW FACING;
   protected static final AxisAlignedBB ENDER_CHEST_AABB;

   protected dA() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH));
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return ENDER_CHEST_AABB;
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

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.OBSIDIAN);
   }

   public int quantityDropped(Random random) {
      return 8;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, placer.getHorizontalFacing().getOpposite());
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      worldIn.setBlockState(pos, state.withProperty(FACING, placer.getHorizontalFacing().getOpposite()), 2);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      InventoryEnderChest inventoryenderchest = playerIn.getInventoryEnderChest();
      Yg tileentity = worldIn.getTileEntity(pos);
      if (inventoryenderchest != null && tileentity instanceof Yw) {
         if (worldIn.getBlockState(pos.up()).isNormalCube()) {
            return true;
         } else if (worldIn.isRemote) {
            return true;
         } else {
            inventoryenderchest.setChestTileEntity((Yw)tileentity);
            playerIn.displayGUIChest(inventoryenderchest);
            playerIn.addStat(XV.ENDERCHEST_OPENED);
            return true;
         }
      } else {
         return true;
      }
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yw();
   }

   public void randomDisplayTick(in stateIn, bij worldIn, BlockPos pos, Random rand) {
      for(int i = 0; i < 3; ++i) {
         int j = rand.nextInt(2) * 2 - 1;
         int k = rand.nextInt(2) * 2 - 1;
         double d0 = (double)pos.getX() + 0.5 + 0.25 * (double)j;
         double d1 = (double)((float)pos.getY() + rand.nextFloat());
         double d2 = (double)pos.getZ() + 0.5 + 0.25 * (double)k;
         double d3 = (double)(rand.nextFloat() * (float)j);
         double d4 = ((double)rand.nextFloat() - 0.5) * 0.125;
         double d5 = (double)(rand.nextFloat() * (float)k);
         worldIn.spawnParticle(EnumParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
      }

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
      ENDER_CHEST_AABB = new AxisAlignedBB(0.0625, 0.0, 0.0625, 0.9375, 0.875, 0.9375);
   }
}
