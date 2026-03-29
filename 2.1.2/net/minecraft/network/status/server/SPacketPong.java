package net.minecraft.network.status.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusClient;

public class SPacketPong implements Packet<INetHandlerStatusClient> {
   private long clientTime;

   public SPacketPong() {
   }

   public SPacketPong(long clientTimeIn) {
      this.clientTime = clientTimeIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.clientTime = buf.readLong();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeLong(this.clientTime);
   }

   public void processPacket(INetHandlerStatusClient handler) {
      handler.handlePong(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerStatusClient)var1);
   }
}
