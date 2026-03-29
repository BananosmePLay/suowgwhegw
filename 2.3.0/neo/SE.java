package neo;

import java.io.IOException;

public class SE implements Sz<Tt> {
   private String message;

   public SE() {
   }

   public SE(String messageIn) {
      if (messageIn.length() > 256) {
         messageIn = messageIn.substring(0, 256);
      }

      this.message = messageIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.message = buf.readString(256);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.message);
   }

   public void processPacket(Tt handler) {
      handler.processChatMessage(this);
   }

   public String getMessage() {
      return this.message;
   }

   public String toString() {
      return "CPacketChatMessage{message='" + this.message + '\'' + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
