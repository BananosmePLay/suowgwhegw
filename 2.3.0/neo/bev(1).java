package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

class bev implements bez {
   private bev() {
   }

   public boolean fits(beC definition) {
      return true;
   }

   public beB create(EnumFacing p_175968_1_, beC p_175968_2_, Random p_175968_3_) {
      p_175968_2_.claimed = true;
      return new beD(p_175968_1_, p_175968_2_, p_175968_3_);
   }

   // $FF: synthetic method
   bev(Object x0) {
      this();
   }
}
