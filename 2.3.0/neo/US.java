package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class US implements Sz<Ts> {
   private BlockPos spawnBlockPos;

   public US() {
   }

   public US(BlockPos posIn) {
      this.spawnBlockPos = posIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.spawnBlockPos = buf.readBlockPos();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeBlockPos(this.spawnBlockPos);
   }

   public void processPacket(Ts handler) {
      handler.handleSpawnPosition(this);
   }

   public BlockPos getSpawnPos() {
      return this.spawnBlockPos;
   }

   public String toString() {
      return "SPacketSpawnPosition{spawnBlockPos=" + this.spawnBlockPos + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
