package neo;

import java.util.List;
import javax.annotation.Nullable;

public class OM extends OL {
   private final co block;

   public OM(co blockIn) {
      this.block = blockIn;
   }

   public String getTranslationKey(Qy stack) {
      return this.block.getTranslationKey();
   }

   public String getTranslationKey() {
      return this.block.getTranslationKey();
   }

   public void addInformation(Qy stack, @Nullable bij worldIn, List<String> tooltip, BJ flagIn) {
      super.addInformation(stack, worldIn, tooltip, flagIn);
      this.block.addInformation(stack, worldIn, tooltip, flagIn);
   }
}
