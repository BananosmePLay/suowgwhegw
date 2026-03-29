package neo;

import java.io.IOException;

public class RD implements Sz<RF> {
   private int protocolVersion;
   private String ip;
   private int port;
   private RB requestedState;

   public RD() {
   }

   public RD(String p_i47613_1_, int p_i47613_2_, RB p_i47613_3_) {
      this.protocolVersion = 340;
      this.ip = p_i47613_1_;
      this.port = p_i47613_2_;
      this.requestedState = p_i47613_3_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.protocolVersion = buf.readVarInt();
      this.ip = buf.readString(255);
      this.port = buf.readUnsignedShort();
      this.requestedState = RB.getById(buf.readVarInt());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.protocolVersion);
      buf.writeString(this.ip);
      buf.writeShort(this.port);
      buf.writeVarInt(this.requestedState.getId());
   }

   public void processPacket(RF handler) {
      handler.processHandshake(this);
   }

   public RB getRequestedState() {
      return this.requestedState;
   }

   public int getProtocolVersion() {
      return this.protocolVersion;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((RF)var1);
   }
}
