package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class Tz implements Sz<Ts> {
   private int breakerId;
   private BlockPos position;
   private int progress;

   public Tz() {
   }

   public Tz(int breakerIdIn, BlockPos positionIn, int progressIn) {
      this.breakerId = breakerIdIn;
      this.position = positionIn;
      this.progress = progressIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.breakerId = buf.readVarInt();
      this.position = buf.readBlockPos();
      this.progress = buf.readUnsignedByte();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.breakerId);
      buf.writeBlockPos(this.position);
      buf.writeByte(this.progress);
   }

   public void processPacket(Ts handler) {
      handler.handleBlockBreakAnim(this);
   }

   public int getBreakerId() {
      return this.breakerId;
   }

   public BlockPos getPosition() {
      return this.position;
   }

   public int getProgress() {
      return this.progress;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
