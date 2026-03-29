package neo;

import net.minecraft.util.math.BlockPos;

class Ck {
   public final BlockPos pos;
   public final in blockState;
   public final QQ nbt;

   public Ck(BlockPos posIn, in stateIn, QQ compoundIn) {
      this.pos = posIn;
      this.blockState = stateIn;
      this.nbt = compoundIn;
   }
}
