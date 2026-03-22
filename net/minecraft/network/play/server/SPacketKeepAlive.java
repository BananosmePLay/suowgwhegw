package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketKeepAlive implements Packet<INetHandlerPlayClient> {
   private long id;

   public SPacketKeepAlive() {
   }

   public SPacketKeepAlive(long idIn) {
      this.id = idIn;
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleKeepAlive(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.id = buf.readLong();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeLong(this.id);
   }

   public long getId() {
      return this.id;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
