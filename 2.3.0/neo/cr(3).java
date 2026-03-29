package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class cr extends dH {
   public static final hW FACING;
   public static final hZ DAMAGE;
   protected static final AxisAlignedBB X_AXIS_AABB;
   protected static final AxisAlignedBB Z_AXIS_AABB;
   protected static final Logger LOGGER;

   protected cr() {
      super(hM.ANVIL);
      this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH).withProperty(DAMAGE, 0));
      this.setLightOpacity(0);
      this.setCreativeTab(EN.DECORATIONS);
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public in getStateForPlacement(bij worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, Iw placer) {
      EnumFacing enumfacing = placer.getHorizontalFacing().rotateY();

      try {
         return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, meta, placer).withProperty(FACING, enumfacing).withProperty(DAMAGE, meta >> 2);
      } catch (IllegalArgumentException var11) {
         if (!worldIn.isRemote) {
            LOGGER.warn(String.format("Invalid damage property for anvil at %s. Found %d, must be in [0, 1, 2]", pos, meta >> 2));
            if (placer instanceof ME) {
               placer.sendMessage(new TextComponentTranslation("Invalid damage property. Please pick in [0, 1, 2]", new Object[0]));
            }
         }

         return super.getStateForPlacement(worldIn, pos, facing, hitX, hitY, hitZ, 0, placer).withProperty(FACING, enumfacing).withProperty(DAMAGE, 0);
      }
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (!worldIn.isRemote) {
         playerIn.displayGui(new cq(worldIn, pos));
      }

      return true;
   }

   public int damageDropped(in state) {
      return (Integer)state.getValue(DAMAGE);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      EnumFacing enumfacing = (EnumFacing)state.getValue(FACING);
      return enumfacing.getAxis() == EnumFacing.Axis.X ? X_AXIS_AABB : Z_AXIS_AABB;
   }

   public void getSubBlocks(EN itemIn, NonNullList<Qy> items) {
      items.add(new Qy(this));
      items.add(new Qy(this, 1, 1));
      items.add(new Qy(this, 1, 2));
   }

   protected void onStartFalling(IW fallingEntity) {
      fallingEntity.setHurtEntities(true);
   }

   public void onEndFalling(bij worldIn, BlockPos pos, in fallingState, in hitState) {
      worldIn.playEvent(1031, pos, 0);
   }

   public void onBroken(bij worldIn, BlockPos pos) {
      worldIn.playEvent(1029, pos, 0);
   }

   /** @deprecated */
   public boolean shouldSideBeRendered(in blockState, bfZ blockAccess, BlockPos pos, EnumFacing side) {
      return true;
   }

   public in getStateFromMeta(int meta) {
      return this.getDefaultState().withProperty(FACING, EnumFacing.byHorizontalIndex(meta & 3)).withProperty(DAMAGE, (meta & 15) >> 2);
   }

   public int getMetaFromState(in state) {
      int i = 0;
      i |= ((EnumFacing)state.getValue(FACING)).getHorizontalIndex();
      i |= (Integer)state.getValue(DAMAGE) << 2;
      return i;
   }

   /** @deprecated */
   public in withRotation(in state, Rotation rot) {
      return state.getBlock() != this ? state : state.withProperty(FACING, rot.rotate((EnumFacing)state.getValue(FACING)));
   }

   protected ii createBlockState() {
      return new ii(this, new hT[]{FACING, DAMAGE});
   }

   static {
      FACING = en.FACING;
      DAMAGE = hZ.create("damage", 0, 2);
      X_AXIS_AABB = new AxisAlignedBB(0.0, 0.0, 0.125, 1.0, 1.0, 0.875);
      Z_AXIS_AABB = new AxisAlignedBB(0.125, 0.0, 0.0, 0.875, 1.0, 1.0);
      LOGGER = LogManager.getLogger();
   }
}
