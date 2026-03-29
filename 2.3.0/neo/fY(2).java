package neo;

import java.util.Random;
import net.minecraft.util.math.BlockPos;

public class fY extends co {
   private final boolean isOn;

   public fY(boolean isOn) {
      super(hM.REDSTONE_LIGHT);
      this.isOn = isOn;
      if (isOn) {
         this.setLightLevel(1.0F);
      }

   }

   public void onBlockAdded(bij worldIn, BlockPos pos, in state) {
      if (!worldIn.isRemote) {
         if (this.isOn && !worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, Nk.REDSTONE_LAMP.getDefaultState(), 2);
         } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, Nk.LIT_REDSTONE_LAMP.getDefaultState(), 2);
         }
      }

   }

   public void neighborChanged(in state, bij worldIn, BlockPos pos, co blockIn, BlockPos fromPos) {
      if (!worldIn.isRemote) {
         if (this.isOn && !worldIn.isBlockPowered(pos)) {
            worldIn.scheduleUpdate(pos, this, 4);
         } else if (!this.isOn && worldIn.isBlockPowered(pos)) {
            worldIn.setBlockState(pos, Nk.LIT_REDSTONE_LAMP.getDefaultState(), 2);
         }
      }

   }

   public void updateTick(bij worldIn, BlockPos pos, in state, Random rand) {
      if (!worldIn.isRemote && this.isOn && !worldIn.isBlockPowered(pos)) {
         worldIn.setBlockState(pos, Nk.REDSTONE_LAMP.getDefaultState(), 2);
      }

   }

   public OL getItemDropped(in state, Random rand, int fortune) {
      return OL.getItemFromBlock(Nk.REDSTONE_LAMP);
   }

   public Qy getItem(bij worldIn, BlockPos pos, in state) {
      return new Qy(Nk.REDSTONE_LAMP);
   }

   protected Qy getSilkTouchDrop(in state) {
      return new Qy(Nk.REDSTONE_LAMP);
   }
}
