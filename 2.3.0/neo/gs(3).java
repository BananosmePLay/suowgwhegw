package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class gs extends dd {
   protected static final AxisAlignedBB SIGN_AABB = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 0.75);

   protected gs() {
      super(hM.WOOD);
   }

   /** @deprecated */
   public AxisAlignedBB getBoundingBox(in state, bfZ source, BlockPos pos) {
      return SIGN_AABB;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   /** @deprecated */
   public boolean hasCustomBreakingProgress(in state) {
      return true;
   }

   public boolean isPassable(bfZ worldIn, BlockPos pos) {
      return true;
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   public boolean canSpawnInBlock() {
      return true;
   }

   public Yg createNewTileEntity(bij worldIn, int meta) {
      return new YQ();
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.SIGN;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(NK.SIGN);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         Yg tileentity = worldIn.getTileEntity(pos);
         return tileentity instanceof YQ ? ((YQ)tileentity).executeCommand(playerIn) : false;
      }
   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return !this.hasInvalidNeighbor(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos);
   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
