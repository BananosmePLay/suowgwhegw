package neo;

import java.io.IOException;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;

public class TD implements Sz<Ts> {
   private ITextComponent chatComponent;
   private ChatType type;

   public TD() {
   }

   public TD(ITextComponent componentIn) {
      this(componentIn, ChatType.SYSTEM);
   }

   public TD(ITextComponent message, ChatType type) {
      this.chatComponent = message;
      this.type = type;
   }

   public void readPacketData(SA buf) throws IOException {
      this.chatComponent = buf.readTextComponent();
      this.type = ChatType.byId(buf.readByte());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeTextComponent(this.chatComponent);
      buf.writeByte(this.type.getId());
   }

   public void processPacket(Ts handler) {
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

   public String toString() {
      return "SPacketChat{chatComponent=" + this.chatComponent + ", type=" + this.type + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
