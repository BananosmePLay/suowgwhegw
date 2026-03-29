package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class TA implements Sz<Ts> {
   private BlockPos blockPosition;
   private in blockState;

   public TA() {
   }

   public TA(bij worldIn, BlockPos posIn) {
      this.blockPosition = posIn;
      this.blockState = worldIn.getBlockState(posIn);
   }

   public void readPacketData(SA buf) throws IOException {
      this.blockPosition = buf.readBlockPos();
      this.blockState = (in)co.BLOCK_STATE_IDS.getByValue(buf.readVarInt());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBlockPos(this.blockPosition);
      buf.writeVarInt(co.BLOCK_STATE_IDS.get(this.blockState));
   }

   public void processPacket(Ts handler) {
      handler.handleBlockChange(this);
   }

   public in getBlockState() {
      return this.blockState;
   }

   public BlockPos getBlockPosition() {
      return this.blockPosition;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
