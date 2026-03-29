package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketClientStatus implements Packet<INetHandlerPlayServer> {
   private State status;

   public CPacketClientStatus() {
   }

   public CPacketClientStatus(State p_i46886_1_) {
      this.status = p_i46886_1_;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.status = (State)buf.readEnumValue(State.class);
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeEnumValue(this.status);
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processClientStatus(this);
   }

   public State getStatus() {
      return this.status;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayServer)var1);
   }

   public static enum State {
      PERFORM_RESPAWN,
      REQUEST_STATS;

      private State() {
      }
   }
}
