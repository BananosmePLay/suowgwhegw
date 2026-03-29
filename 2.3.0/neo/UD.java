package neo;

import java.io.IOException;

public class UD implements Sz<Ts> {
   private int dimensionID;
   private baV difficulty;
   private bbb gameType;
   private bix worldType;

   public UD() {
   }

   public UD(int dimensionIdIn, baV difficultyIn, bix worldTypeIn, bbb gameModeIn) {
      this.dimensionID = dimensionIdIn;
      this.difficulty = difficultyIn;
      this.gameType = gameModeIn;
      this.worldType = worldTypeIn;
   }

   public void processPacket(Ts handler) {
      handler.handleRespawn(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.dimensionID = buf.readInt();
      this.difficulty = baV.byId(buf.readUnsignedByte());
      this.gameType = bbb.getByID(buf.readUnsignedByte());
      this.worldType = bix.byName(buf.readString(16));
      if (this.worldType == null) {
         this.worldType = bix.DEFAULT;
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.dimensionID);
      buf.writeByte(this.difficulty.getId());
      buf.writeByte(this.gameType.getID());
      buf.writeString(this.worldType.getName());
   }

   public int getDimensionID() {
      return this.dimensionID;
   }

   public baV getDifficulty() {
      return this.difficulty;
   }

   public bbb getGameType() {
      return this.gameType;
   }

   public bix getWorldType() {
      return this.worldType;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
