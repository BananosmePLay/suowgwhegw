package neo;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;

public class hF extends co {
   protected hF() {
      super(hM.WOOD);
      this.setCreativeTab(EN.DECORATIONS);
   }

   public boolean onBlockActivated(bij worldIn, BlockPos pos, in state, ME playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
      if (worldIn.isRemote) {
         return true;
      } else {
         playerIn.displayGui(new hE(worldIn, pos));
         playerIn.addStat(XV.CRAFTING_TABLE_INTERACTION);
         return true;
      }
   }
}
