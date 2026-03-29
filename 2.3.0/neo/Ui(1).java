package neo;

import java.io.IOException;

public class Ui implements Sz<Ts> {
   private int playerId;
   private boolean hardcoreMode;
   private bbb gameType;
   private int dimension;
   private baV difficulty;
   private int maxPlayers;
   private bix worldType;
   private boolean reducedDebugInfo;

   public Ui() {
   }

   public Ui(int playerIdIn, bbb gameTypeIn, boolean hardcoreModeIn, int dimensionIn, baV difficultyIn, int maxPlayersIn, bix worldTypeIn, boolean reducedDebugInfoIn) {
      this.playerId = playerIdIn;
      this.dimension = dimensionIn;
      this.difficulty = difficultyIn;
      this.gameType = gameTypeIn;
      this.maxPlayers = maxPlayersIn;
      this.hardcoreMode = hardcoreModeIn;
      this.worldType = worldTypeIn;
      this.reducedDebugInfo = reducedDebugInfoIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.playerId = buf.readInt();
      int i = buf.readUnsignedByte();
      this.hardcoreMode = (i & 8) == 8;
      i &= -9;
      this.gameType = bbb.getByID(i);
      this.dimension = buf.readInt();
      this.difficulty = baV.byId(buf.readUnsignedByte());
      this.maxPlayers = buf.readUnsignedByte();
      this.worldType = bix.byName(buf.readString(16));
      if (this.worldType == null) {
         this.worldType = bix.DEFAULT;
      }

      this.reducedDebugInfo = buf.readBoolean();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeInt(this.playerId);
      int i = this.gameType.getID();
      if (this.hardcoreMode) {
         i |= 8;
      }

      buf.writeByte(i);
      buf.writeInt(this.dimension);
      buf.writeByte(this.difficulty.getId());
      buf.writeByte(this.maxPlayers);
      buf.writeString(this.worldType.getName());
      buf.writeBoolean(this.reducedDebugInfo);
   }

   public void processPacket(Ts handler) {
      handler.handleJoinGame(this);
   }

   public int getPlayerId() {
      return this.playerId;
   }

   public boolean isHardcoreMode() {
      return this.hardcoreMode;
   }

   public bbb getGameType() {
      return this.gameType;
   }

   public int getDimension() {
      return this.dimension;
   }

   public baV getDifficulty() {
      return this.difficulty;
   }

   public int getMaxPlayers() {
      return this.maxPlayers;
   }

   public bix getWorldType() {
      return this.worldType;
   }

   public boolean isReducedDebugInfo() {
      return this.reducedDebugInfo;
   }

   public String toString() {
      return "SPacketJoinGame{playerId=" + this.playerId + ", hardcoreMode=" + this.hardcoreMode + ", gameType=" + this.gameType + ", dimension=" + this.dimension + ", difficulty=" + this.difficulty + ", maxPlayers=" + this.maxPlayers + ", worldType=" + this.worldType + ", reducedDebugInfo=" + this.reducedDebugInfo + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
