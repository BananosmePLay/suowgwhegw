package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

class beK implements bez {
   private beK() {
   }

   public boolean fits(beC definition) {
      return definition.hasOpening[EnumFacing.NORTH.getIndex()] && !definition.connections[EnumFacing.NORTH.getIndex()].claimed;
   }

   public beB create(EnumFacing p_175968_1_, beC p_175968_2_, Random p_175968_3_) {
      beC structureoceanmonumentpieces$roomdefinition = p_175968_2_;
      if (!p_175968_2_.hasOpening[EnumFacing.NORTH.getIndex()] || p_175968_2_.connections[EnumFacing.NORTH.getIndex()].claimed) {
         structureoceanmonumentpieces$roomdefinition = p_175968_2_.connections[EnumFacing.SOUTH.getIndex()];
      }

      structureoceanmonumentpieces$roomdefinition.claimed = true;
      structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.NORTH.getIndex()].claimed = true;
      return new bet(p_175968_1_, structureoceanmonumentpieces$roomdefinition, p_175968_3_);
   }

   // $FF: synthetic method
   beK(Object x0) {
      this();
   }
}
