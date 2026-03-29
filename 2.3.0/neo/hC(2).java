package neo;

import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class hC extends co {
   public hC() {
      super(hM.WEB);
      this.setCreativeTab(EN.DECORATIONS);
   }

   public void onEntityCollision(bij worldIn, BlockPos pos, in state, Ig entityIn) {
      entityIn.setInWeb();
   }

   /** @deprecated */
   public boolean isOpaqueCube(in state) {
      return false;
   }

   @Nullable
   public AxisAlignedBB getCollisionBoundingBox(in blockState, bfZ worldIn, BlockPos pos) {
      return NULL_AABB;
   }

   /** @deprecated */
   public boolean isFullCube(in state) {
      return false;
   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return NK.STRING;
   }

   protected boolean canSilkHarvest() {
      return true;
   }

   public BlockRenderLayer getRenderLayer() {
      return BlockRenderLayer.CUTOUT;
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (!worldIn.isRemote && stack.getItem() == NK.SHEARS) {
         player.addStat(XV.getBlockStats(this));
         spawnAsEntity(worldIn, pos, new Qy(OL.getItemFromBlock(this), 1));
      } else {
         super.harvestBlock(worldIn, player, pos, state, te, stack);
      }

   }

   /** @deprecated */
   public ib getBlockFaceShape(bfZ worldIn, in state, BlockPos pos, EnumFacing face) {
      return ib.UNDEFINED;
   }
}
