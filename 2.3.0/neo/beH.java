package neo;

import java.util.Random;
import net.minecraft.util.EnumFacing;

class beH implements bez {
   private beH() {
   }

   public boolean fits(beC definition) {
      if (definition.hasOpening[EnumFacing.EAST.getIndex()] && !definition.connections[EnumFacing.EAST.getIndex()].claimed && definition.hasOpening[EnumFacing.UP.getIndex()] && !definition.connections[EnumFacing.UP.getIndex()].claimed) {
         beC structureoceanmonumentpieces$roomdefinition = definition.connections[EnumFacing.EAST.getIndex()];
         return structureoceanmonumentpieces$roomdefinition.hasOpening[EnumFacing.UP.getIndex()] && !structureoceanmonumentpieces$roomdefinition.connections[EnumFacing.UP.getIndex()].claimed;
      } else {
         return false;
      }
   }

   public beB create(EnumFacing p_175968_1_, beC p_175968_2_, Random p_175968_3_) {
      p_175968_2_.claimed = true;
      p_175968_2_.connections[EnumFacing.EAST.getIndex()].claimed = true;
      p_175968_2_.connections[EnumFacing.UP.getIndex()].claimed = true;
      p_175968_2_.connections[EnumFacing.EAST.getIndex()].connections[EnumFacing.UP.getIndex()].claimed = true;
      return new beq(p_175968_1_, p_175968_2_, p_175968_3_);
   }

   // $FF: synthetic method
   beH(Object x0) {
      this();
   }
}
