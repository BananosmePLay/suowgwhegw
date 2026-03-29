package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class Ty implements Sz<Ts> {
   private BlockPos blockPosition;
   private int instrument;
   private int pitch;
   private co block;

   public Ty() {
   }

   public Ty(BlockPos pos, co blockIn, int instrumentIn, int pitchIn) {
      this.blockPosition = pos;
      this.instrument = instrumentIn;
      this.pitch = pitchIn;
      this.block = blockIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.blockPosition = buf.readBlockPos();
      this.instrument = buf.readUnsignedByte();
      this.pitch = buf.readUnsignedByte();
      this.block = co.getBlockById(buf.readVarInt() & 4095);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBlockPos(this.blockPosition);
      buf.writeByte(this.instrument);
      buf.writeByte(this.pitch);
      buf.writeVarInt(co.getIdFromBlock(this.block) & 4095);
   }

   public void processPacket(Ts handler) {
      handler.handleBlockAction(this);
   }

   public BlockPos getBlockPosition() {
      return this.blockPosition;
   }

   public int getData1() {
      return this.instrument;
   }

   public int getData2() {
      return this.pitch;
   }

   public co getBlockType() {
      return this.block;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
