package neo;

import javax.annotation.Nullable;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;

public class cM extends cK {
   protected cM() {
      super(true);
   }

   protected void playClickSound(@Nullable ME player, bij worldIn, BlockPos pos) {
      worldIn.playSound(player, pos, NO.BLOCK_WOOD_BUTTON_CLICK_ON, SoundCategory.BLOCKS, 0.3F, 0.6F);
   }

   protected void playReleaseSound(bij worldIn, BlockPos pos) {
      worldIn.playSound((ME)null, pos, NO.BLOCK_WOOD_BUTTON_CLICK_OFF, SoundCategory.BLOCKS, 0.3F, 0.5F);
   }
}
