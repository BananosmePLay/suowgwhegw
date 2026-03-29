package neo;

import java.io.IOException;

public class UG implements Sz<Ts> {
   private baV difficulty;
   private boolean difficultyLocked;

   public UG() {
   }

   public UG(baV difficultyIn, boolean difficultyLockedIn) {
      this.difficulty = difficultyIn;
      this.difficultyLocked = difficultyLockedIn;
   }

   public void processPacket(Ts handler) {
      handler.handleServerDifficulty(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.difficulty = baV.byId(buf.readUnsignedByte());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.difficulty.getId());
   }

   public boolean isDifficultyLocked() {
      return this.difficultyLocked;
   }

   public baV getDifficulty() {
      return this.difficulty;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
