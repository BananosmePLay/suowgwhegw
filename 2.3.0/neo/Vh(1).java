package neo;

import java.io.IOException;
import net.minecraft.util.math.BlockPos;

public class Vh implements Sz<Ts> {
   private int playerID;
   private BlockPos bedPos;

   public Vh() {
   }

   public Vh(ME player, BlockPos posIn) {
      this.playerID = player.getEntityId();
      this.bedPos = posIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.playerID = buf.readVarInt();
      this.bedPos = buf.readBlockPos();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.playerID);
      buf.writeBlockPos(this.bedPos);
   }

   public void processPacket(Ts handler) {
      handler.handleUseBed(this);
   }

   public ME getPlayer(bij worldIn) {
      return (ME)worldIn.getEntityByID(this.playerID);
   }

   public BlockPos getBedPosition() {
      return this.bedPos;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
