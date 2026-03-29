package neo;

import javax.annotation.Nullable;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;

public abstract class dd extends co implements hI {
   protected dd(hM materialIn) {
      this(materialIn, materialIn.getMaterialMapColor());
   }

   protected dd(hM materialIn, hK color) {
      super(materialIn, color);
      this.hasTileEntity = true;
   }

   protected boolean isInvalidNeighbor(bij worldIn, BlockPos pos, EnumFacing facing) {
      return worldIn.getBlockState(pos.offset(facing)).getMaterial() == hM.CACTUS;
   }

   protected boolean hasInvalidNeighbor(bij worldIn, BlockPos pos) {
      return this.isInvalidNeighbor(worldIn, pos, EnumFacing.NORTH) || this.isInvalidNeighbor(worldIn, pos, EnumFacing.SOUTH) || this.isInvalidNeighbor(worldIn, pos, EnumFacing.WEST) || this.isInvalidNeighbor(worldIn, pos, EnumFacing.EAST);
   }

   /** @deprecated */
   public EnumBlockRenderType getRenderType(in state) {
      return EnumBlockRenderType.INVISIBLE;
   }

   public void breakBlock(bij worldIn, BlockPos pos, in state) {
      super.breakBlock(worldIn, pos, state);
      worldIn.removeTileEntity(pos);
   }

   public void harvestBlock(bij worldIn, ME player, BlockPos pos, in state, @Nullable Yg te, Qy stack) {
      if (te instanceof bgd && ((bgd)te).hasCustomName()) {
         player.addStat(XV.getBlockStats(this));
         player.addExhaustion(0.005F);
         if (worldIn.isRemote) {
            return;
         }

         int i = Ft.getEnchantmentLevel(NJ.FORTUNE, stack);
         OL item = this.getItemDropped(state, worldIn.rand, i);
         if (item == NK.AIR) {
            return;
         }

         Qy itemstack = new Qy(item, this.quantityDropped(worldIn.rand));
         itemstack.setStackDisplayName(((bgd)te).getName());
         spawnAsEntity(worldIn, pos, itemstack);
      } else {
         super.harvestBlock(worldIn, player, pos, state, (Yg)null, stack);
      }

   }

   /** @deprecated */
   public boolean eventReceived(in state, bij worldIn, BlockPos pos, int id, int param) {
      super.eventReceived(state, worldIn, pos, id, param);
      Yg tileentity = worldIn.getTileEntity(pos);
      return tileentity == null ? false : tileentity.receiveClientEvent(id, param);
   }
}
