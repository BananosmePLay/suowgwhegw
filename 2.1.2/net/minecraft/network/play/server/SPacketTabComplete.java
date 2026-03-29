package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketTabComplete implements Packet<INetHandlerPlayClient> {
   private String[] matches;

   public SPacketTabComplete() {
   }

   public SPacketTabComplete(String[] matchesIn) {
      this.matches = matchesIn;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.matches = new String[buf.readVarInt()];

      for(int i = 0; i < this.matches.length; ++i) {
         this.matches[i] = buf.readString(32767);
      }

   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarInt(this.matches.length);
      String[] var2 = this.matches;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String s = var2[var4];
         buf.writeString(s);
      }

   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleTabComplete(this);
   }

   public String[] getMatches() {
      return this.matches;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
