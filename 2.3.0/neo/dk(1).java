package neo;

import java.util.Random;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryDefaulted;

public class dk extends dd {
   public static final hW FACING;
   public static final hV TRIGGERED;
   public static final RegistryDefaulted<OL, ES> DISPENSE_BEHAVIOR_REGISTRY;
   protected Random rand = new Random();

   protected dk() {
      super(hM.ROCK);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(TRIGGERED, false));
      this.setCreativeTab(EN.REDSTONE);
   }

   public int tickRate(bij worldIn) {
      return 4;
   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      super.onBlockAdded(worldIn, pos, state);
      this.setDefaultDirection(worldIn, pos, state);
   }

   private void setDefaultDirection(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
         boolean flag = worldIn.getBlockState(pos.north()).isFullBlock();
         boolean flag1 = worldIn.getBlockState(pos.south()).isFullBlock();
         if (enumfacing == EnumFacing.NORTH && flag && !flag1) {
            enumfacing = EnumFacing.SOUTH;
         } else if (enumfacing == EnumFacing.SOUTH && flag1 && !flag) {
            enumfacing = EnumFacing.NORTH;
         } else {
            boolean flag2 = worldIn.getBlockState(pos.west()).isFullBlock();
            boolean flag3 = worldIn.getBlockState(pos.east()).isFullBlock();
            if (enumfacing == EnumFacing.WEST && flag2 && !flag3) {
               enumfacing = EnumFacing.EAST;
            } else if (enumfacing == EnumFacing.EAST && flag3 && !flag2) {
               enumfacing = EnumFacing.WEST;
            }
         }

         worldIn.setBlockState(pos, state.withProperty(FACING, enumfacing).withProperty(TRIGGERED, false), 2);
      }

   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yt) {
            playerIn.displayGUIChest((Yt)tileentity);
            if (tileentity instanceof Yu) {
               playerIn.addStat(XV.DROPPER_INSPECTED);
            } else {
               playerIn.addStat(XV.DISPENSER_INSPECTED);
            }
         }

         return true;
      }
   }

   protected void dispense(bij worldIn, BlockPos pos) {
      gL blocksourceimpl = new gL(worldIn, pos);
      Yt tileentitydispenser = (Yt)blocksourceimpl.getBlockTileEntity();
      if (tileentitydispenser != null) {
         int i = tileentitydispenser.getDispenseSlot();
         if (i < 0) {
            worldIn.playEvent(1001, pos, 0);
         } else {
            Qy itemstack = tileentitydispenser.getStackInSlot(i);
            ES ibehaviordispenseitem = this.getBehavior(itemstack);
            if (ibehaviordispenseitem != ES.DEFAULT_BEHAVIOR) {
               tileentitydispenser.setInventorySlotContents(i, ibehaviordispenseitem.dispense(blocksourceimpl, itemstack));
            }
         }
      }

   }

   protected ES getBehavior(Qy stack) {
      return (ES)DISPENSE_BEHAVIOR_REGISTRY.getObject(stack.getItem());
   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      boolean flag = worldIn.isBlockPowered(pos) || worldIn.isBlockPowered(pos.up());
      boolean flag1 = (Boolean)state.getValue(TRIGGERED);
      if (flag && !flag1) {
         worldIn.scheduleUpdate(pos, this, this.tickRate(worldIn));
         worldIn.setBlockState(pos, state.withProperty(TRIGGERED, true), 4);
      } else if (!flag && flag1) {
         worldIn.setBlockState(pos, state.withProperty(TRIGGERED, false), 4);
      }

   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote) {
         this.dispense(worldIn, pos);
      }

   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new Yt();
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)).withProperty(TRIGGERED, false);
   }

   public void onBlockPlacedBy(bij worldIn, BlockPos pos, in state, Iw placer, Qy stack) {
      worldIn.setBlockState(pos, state.withProperty(FACING, EnumFacing.getDirectionFromEntityLiving(pos, placer)), 2);
      if (stack.hasDisplayName()) {
         Yg tileentity = worldIn.getTileEntity(pos);
         if (tileentity instanceof Yt) {
            ((Yt)tileentity).setCustomName(stack.getDisplayName());
         }
      }

   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      Yg tileentity = worldIn.getTileEntity(pos);
      if (tileentity instanceof Yt) {
         InventoryHelper.dropInventoryItems(worldIn, (BlockPos)pos, (Yt)tileentity);
         worldIn.updateComparatorOutputLevel(pos, this);
      }

      super.breakBlock(worldIn, pos, state);
   }

   public static EW getDispensePosition(ET coords) {
      EnumFacing enumfacing = (EnumFacing)coords.getBlockState().getValue(FACING);
      double d0 = coords.getX() + 0.7 * (double)enumfacing.getXOffset();
      double d1 = coords.getY() + 0.7 * (double)enumfacing.getYOffset();
      double d2 = coords.getZ() + 0.7 * (double)enumfacing.getZOffset();
      return new EY(d0, d1, d2);
   }

   /** @deprecated */
   public boolean hasComparatorInputOverride(in state) {
      return true;
   }

   /** @deprecated */
   public int getComparatorInputOverride(in blockState, bij worldIn, BlockPos pos) {
      return Container.calcRedstone(worldIn.getTileEntity(pos));
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.MODEL;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byIndex(meta & 7)).withProperty(TRIGGERED, (meta & 8) > 0);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getIndex();
      if ((Boolean)state.getValue(TRIGGERED)) {
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
      return new ii(this, new hT[]{FACING, TRIGGERED});
   }

   static {
      FACING = dh.FACING;
      TRIGGERED = hV.create("triggered");
      DISPENSE_BEHAVIOR_REGISTRY = new RegistryDefaulted(new EP());
   }
}
