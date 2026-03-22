package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.text.ITextComponent;

public class SPacketPlayerListHeaderFooter implements Packet<INetHandlerPlayClient> {
   private ITextComponent header;
   private ITextComponent footer;

   public SPacketPlayerListHeaderFooter() {
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.header = buf.readTextComponent();
      this.footer = buf.readTextComponent();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeTextComponent(this.header);
      buf.writeTextComponent(this.footer);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handlePlayerListHeaderFooter(this);
   }

   public ITextComponent getHeader() {
      return this.header;
   }

   public ITextComponent getFooter() {
      return this.footer;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
