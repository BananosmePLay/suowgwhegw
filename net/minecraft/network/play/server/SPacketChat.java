package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;

public class SPacketChat implements Packet<INetHandlerPlayClient> {
   private ITextComponent chatComponent;
   private ChatType type;

   public SPacketChat() {
   }

   public SPacketChat(ITextComponent componentIn) {
      this(componentIn, ChatType.SYSTEM);
   }

   public SPacketChat(ITextComponent message, ChatType type) {
      this.chatComponent = message;
      this.type = type;
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.chatComponent = buf.readTextComponent();
      this.type = ChatType.byId(buf.readByte());
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeTextComponent(this.chatComponent);
      buf.writeByte(this.type.getId());
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleChat(this);
   }

   public ITextComponent getChatComponent() {
      return this.chatComponent;
   }

   public boolean isSystem() {
      return this.type == ChatType.SYSTEM || this.type == ChatType.GAME_INFO;
   }

   public ChatType getType() {
      return this.type;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
