package neo;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class 0R implements 0p {
   public final IBlockState state;
   public final BufferBuilder bufferBuilder;
   public final BlockPos pos;
   public final IBlockAccess access;

   public BlockPos getPos() {
      return W9s9geFviB(this);
   }

   private static IBlockAccess rmwE8devfr(0R var0) {
      return var0.access;
   }

   private static BufferBuilder ayFwXDrJVE(0R var0) {
      return var0.bufferBuilder;
   }

   private static BlockPos W9s9geFviB(0R var0) {
      return var0.pos;
   }

   public IBlockState getState() {
      return hLtD4zZ54Q(this);
   }

   public BufferBuilder getBufferBuilder() {
      return ayFwXDrJVE(this);
   }

   public _R/* $FF was: 0R*/(IBlockState state, BlockPos pos, IBlockAccess access, BufferBuilder bufferBuilder) {
      this.state = state;
      this.pos = pos;
      this.access = access;
      this.bufferBuilder = bufferBuilder;
   }

   private static IBlockState hLtD4zZ54Q(0R var0) {
      return var0.state;
   }

   public IBlockAccess getAccess() {
      return rmwE8devfr(this);
   }
}
