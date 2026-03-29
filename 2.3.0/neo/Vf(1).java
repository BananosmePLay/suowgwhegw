package neo;

import java.io.IOException;

public class Vf implements Sz<Ts> {
   private String name = "";
   private String objective = "";
   private int value;
   private Ve action;

   public Vf() {
   }

   public Vf(Wr scoreIn) {
      this.name = scoreIn.getPlayerName();
      this.objective = scoreIn.getObjective().getName();
      this.value = scoreIn.getScorePoints();
      this.action = Ve.CHANGE;
   }

   public Vf(String nameIn) {
      this.name = nameIn;
      this.objective = "";
      this.value = 0;
      this.action = Ve.REMOVE;
   }

   public Vf(String nameIn, Wz objectiveIn) {
      this.name = nameIn;
      this.objective = objectiveIn.getName();
      this.value = 0;
      this.action = Ve.REMOVE;
   }

   public void readPacketData(SA buf) throws IOException {
      this.name = buf.readString(40);
      this.action = (Ve)buf.readEnumValue(Ve.class);
      this.objective = buf.readString(16);
      if (this.action != Ve.REMOVE) {
         this.value = buf.readVarInt();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.name);
      buf.writeEnumValue(this.action);
      buf.writeString(this.objective);
      if (this.action != Ve.REMOVE) {
         buf.writeVarInt(this.value);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleUpdateScore(this);
   }

   public String getPlayerName() {
      return this.name;
   }

   public String getObjectiveName() {
      return this.objective;
   }

   public int getScoreValue() {
      return this.value;
   }

   public Ve getScoreAction() {
      return this.action;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
