package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

class bew implements bez {
   private bew() {
   }

   public boolean fits(beC definition) {
      return !definition.hasOpening[EnumFacing.WEST.getIndex()] && !definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.hasOpening[EnumFacing.SOUTH.getIndex()] && !definition.hasOpening[EnumFacing.UP.getIndex()];
   }

   public beB create(EnumFacing p_175968_1_, beC p_175968_2_, Random p_175968_3_) {
      p_175968_2_.claimed = true;
      return new beE(p_175968_1_, p_175968_2_, p_175968_3_);
   }

   // $FF: synthetic method
   bew(Object x0) {
      this();
   }
}
