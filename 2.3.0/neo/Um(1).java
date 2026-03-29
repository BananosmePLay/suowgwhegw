package neo;

import net.minecraft.util.math.BlockPos;

public class Um {
   private final short offset;
   private final in blockState;
   // $FF: synthetic field
   final Un this$0;

   public Um(Un this$0, short p_i46544_2_, in p_i46544_3_) {
      this.this$0 = this$0;
      this.offset = p_i46544_2_;
      this.blockState = p_i46544_3_;
   }

   public Um(Un this$0, short p_i46545_2_, bam p_i46545_3_) {
      this.this$0 = this$0;
      this.offset = p_i46545_2_;
      this.blockState = p_i46545_3_.getBlockState(this.getPos());
   }

   public BlockPos getPos() {
      return new BlockPos(this.this$0.chunkPos.getBlock(this.offset >> 12 & 15, this.offset & 255, this.offset >> 8 & 15));
   }

   public short getOffset() {
      return this.offset;
   }

   public in getBlockState() {
      return this.blockState;
   }
}
