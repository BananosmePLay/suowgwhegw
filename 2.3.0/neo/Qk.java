package neo;

import net.minecraft.util.math.BlockPos;

public class Qk extends OL {
   public Qk() {
      this.setMaxStackSize(1);
      this.setMaxDamage(238);
      this.setCreativeTab(EN.TOOLS);
   }

   public boolean onBlockDestroyed(Qy stack, bij worldIn, in state, BlockPos pos, Iw entityLiving) {
      if (!worldIn.isRemote) {
         stack.damageItem(1, entityLiving);
      }

      co block = state.getBlock();
      return state.getMaterial() != hM.LEAVES && block != Nk.WEB && block != Nk.TALLGRASS && block != Nk.VINE && block != Nk.TRIPWIRE && block != Nk.WOOL ? super.onBlockDestroyed(stack, worldIn, state, pos, entityLiving) : true;
   }

   public boolean canHarvestBlock(in blockIn) {
      co block = blockIn.getBlock();
      return block == Nk.WEB || block == Nk.REDSTONE_WIRE || block == Nk.TRIPWIRE;
   }

   public float getDestroySpeed(Qy stack, in state) {
      co block = state.getBlock();
      if (block != Nk.WEB && state.getMaterial() != hM.LEAVES) {
         return block == Nk.WOOL ? 5.0F : super.getDestroySpeed(stack, state);
      } else {
         return 15.0F;
      }
   }
}
