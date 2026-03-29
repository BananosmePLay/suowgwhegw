package neo;

import javax.annotation.Nullable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class cL extends cK {
   protected cL() {
      super(false);
   }

   protected void playClickSound(@Nullable ME player, bij worldIn, BlockPos pos) {
      worldIn.playSound(player, pos, NO.BLOCK_STONE_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
   }

   protected void playReleaseSound(bij worldIn, BlockPos pos) {
      worldIn.playSound((ME)null, pos, NO.BLOCK_STONE_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
   }
}
