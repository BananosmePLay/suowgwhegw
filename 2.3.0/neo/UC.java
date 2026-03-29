package neo;

import java.io.IOException;

public class UC implements Sz<Ts> {
   private String url;
   private String hash;

   public UC() {
   }

   public UC(String urlIn, String hashIn) {
      this.url = urlIn;
      this.hash = hashIn;
      if (hashIn.length() > 40) {
         throw new IllegalArgumentException("Hash is too long (max 40, was " + hashIn.length() + ")");
      }
   }

   public void readPacketData(SA buf) throws IOException {
      this.url = buf.readString(32767);
      this.hash = buf.readString(40);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.url);
      buf.writeString(this.hash);
   }

   public void processPacket(Ts handler) {
      handler.handleResourcePack(this);
   }

   public String getURL() {
      return this.url;
   }

   public String getHash() {
      return this.hash;
   }

   public String toString() {
      return "SPacketResourcePackSend{url='" + this.url + '\'' + ", hash='" + this.hash + '\'' + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
