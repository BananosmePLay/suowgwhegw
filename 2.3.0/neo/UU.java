package neo;

import java.io.IOException;

public class UU implements Sz<Ts> {
   private String[] matches;

   public UU() {
   }

   public UU(String[] matchesIn) {
      this.matches = matchesIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.matches = new String[buf.readVarInt()];

      for(int i = 0; i < this.matches.length; ++i) {
         this.matches[i] = buf.readString(32767);
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.matches.length);
      String[] var2 = this.matches;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         String s = var2[var4];
         buf.writeString(s);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleTabComplete(this);
   }

   public String[] getMatches() {
      return this.matches;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
