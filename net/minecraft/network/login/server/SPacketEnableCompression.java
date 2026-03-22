package net.minecraft.network.login.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class SPacketEnableCompression implements Packet<INetHandlerLoginClient> {
   private int compressionThreshold;

   public SPacketEnableCompression() {
   }

   public SPacketEnableCompression(int thresholdIn) {
      this.compressionThreshold = thresholdIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.compressionThreshold = buf.readVarInt();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarInt(this.compressionThreshold);
   }

   public void processPacket(INetHandlerLoginClient handler) {
      handler.handleEnableCompression(this);
   }

   public int getCompressionThreshold() {
      return this.compressionThreshold;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerLoginClient)var1);
   }
}
