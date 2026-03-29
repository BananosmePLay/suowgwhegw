package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketCloseWindow implements Packet<INetHandlerPlayServer> {
   public int windowId;

   public CPacketCloseWindow() {
   }

   public CPacketCloseWindow(int windowIdIn) {
      this.windowId = windowIdIn;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processCloseWindow(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readByte();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayServer)var1);
   }
}
