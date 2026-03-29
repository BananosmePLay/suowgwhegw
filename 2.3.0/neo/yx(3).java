package neo;

import net.minecraft.util.EnumFacing;

public class yx {
   final ug renderChunk;
   EnumFacing facing;
   int setFacing;

   public yx(ug p_i5_1_, EnumFacing p_i5_2_, int p_i5_3_) {
      this.renderChunk = p_i5_1_;
      this.facing = p_i5_2_;
      this.setFacing = p_i5_3_;
   }

   public void setDirection(byte p_189561_1_, EnumFacing p_189561_2_) {
      this.setFacing = this.setFacing | p_189561_1_ | 1 << p_189561_2_.ordinal();
   }

   public boolean hasDirection(EnumFacing p_189560_1_) {
      return (this.setFacing & 1 << p_189560_1_.ordinal()) > 0;
   }

   private void initialize(EnumFacing p_initialize_1_, int p_initialize_2_) {
      this.facing = p_initialize_1_;
      this.setFacing = p_initialize_2_;
   }

   // $FF: synthetic method
   static void access$000(yx x0, EnumFacing x1, int x2) {
      x0.initialize(x1, x2);
   }
}
