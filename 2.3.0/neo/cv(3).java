package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;

public class cv extends dd {
   public static final hW FACING;
   public static final hZ ROTATION;
   protected static final AxisAlignedBB STANDING_AABB;

   protected cv() {
      super(hM.WOOD);
   }

   public String getLocalizedName() {
      return I18n.translateToLocal("item.banner.white.name");
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
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
      return new Yh();
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.BANNER;
   }

   private Qy getTileDataItemStack(bij worldIn, BlockPos pos) {
      Yg tileentity = worldIn.getTileEntity(pos);
      return tileentity instanceof Yh ? ((Yh)tileentity).getItem() : Qy.EMPTY;
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      Qy itemstack = this.getTileDataItemStack(worldIn, pos);
      return itemstack.isEmpty() ? new Qy(NK.BANNER) : itemstack;
   }

   public void dropBlockAsItemWithChance(bij worldIn, BlockPos pos, in state, float chance, int fortune) {
      Qy itemstack = this.getTileDataItemStack(worldIn, pos);
      if (itemstack.isEmpty()) {
         super.dropBlockAsItemWithChance(worldIn, pos, state, chance, fortune);
      } else {
         spawnAsEntity(worldIn, pos, itemstack);
      }

   }

   public boolean canPlaceBlockAt(bij worldIn, BlockPos pos) {
      return !this.hasInvalidNeighbor(worldIn, pos) && super.canPlaceBlockAt(worldIn, pos);
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (te instanceof Yh) {
         Yh tileentitybanner = (Yh)te;
         Qy itemstack = tileentitybanner.getItem();
         spawnAsEntity(worldIn, pos, itemstack);
      } else {
         super.harvestBlock(worldIn, player, pos, state, (Yg)null, stack);
      }

   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }

   static {
      FACING = en.FACING;
      ROTATION = hZ.create("rotation", 0, 15);
      STANDING_AABB = new AxisAlignedBB(0.25, 0.0, 0.25, 0.75, 1.0, 0.75);
   }
}
