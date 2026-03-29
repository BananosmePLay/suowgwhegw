package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

class beI implements bez {
   private beI() {
   }

   public boolean fits(beC definition) {
      return definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed;
   }

   public beB create(EnumFacing p_175968_1_, beC p_175968_2_, Random p_175968_3_) {
      p_175968_2_.claimed = true;
      p_175968_2_.connections[EnumFacing.UP.getIndex()].claimed = true;
      return new ber(p_175968_1_, p_175968_2_, p_175968_3_);
   }

   // $FF: synthetic method
   beI(Object x0) {
      this();
   }
}
