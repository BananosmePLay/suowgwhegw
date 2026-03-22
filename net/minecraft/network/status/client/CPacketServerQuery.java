package net.minecraft.network.status.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.status.INetHandlerStatusServer;

public class CPacketServerQuery implements Packet<INetHandlerStatusServer> {
   public CPacketServerQuery() {
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
   }

   public void processPacket(INetHandlerStatusServer handler) {
      handler.processServerQuery(this);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerStatusServer)var1);
   }
}
