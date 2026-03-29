package neo;

import java.util.Random;

public class bgY extends bgI {
   private final QQ tag;

   public bgY(bgv[] conditionsIn, QQ tagIn) {
      super(conditionsIn);
      this.tag = tagIn;
   }

   public Qy apply(Qy stack, Random rand, bhg context) {
      QQ nbttagcompound = stack.getTagCompound();
      if (nbttagcompound == null) {
         nbttagcompound = this.tag.copy();
      } else {
         nbttagcompound.merge(this.tag);
      }

      stack.setTagCompound(nbttagcompound);
      return stack;
   }

   // $FF: synthetic method
   static QQ access$000(bgY x0) {
      return x0.tag;
   }
}
