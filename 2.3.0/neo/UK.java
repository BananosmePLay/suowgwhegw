package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class UK implements Sz<Ts> {
   private BlockPos signPosition;

   public UK() {
   }

   public UK(BlockPos posIn) {
      this.signPosition = posIn;
   }

   public void processPacket(Ts handler) {
      handler.handleSignEditorOpen(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.signPosition = buf.readBlockPos();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBlockPos(this.signPosition);
   }

   public BlockPos getSignPosition() {
      return this.signPosition;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
