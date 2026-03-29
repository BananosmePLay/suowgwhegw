package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

class beG implements bez {
   private beG() {
   }

   public boolean fits(beC definition) {
      return definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.connections[EnumFacing.EAST.getIndex()].claimed;
   }

   public beB create(EnumFacing p_175968_1_, beC p_175968_2_, Random p_175968_3_) {
      p_175968_2_.claimed = true;
      p_175968_2_.connections[EnumFacing.EAST.getIndex()].claimed = true;
      return new bep(p_175968_1_, p_175968_2_, p_175968_3_);
   }

   // $FF: synthetic method
   beG(Object x0) {
      this();
   }
}
