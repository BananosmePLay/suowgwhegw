package neo;

import java.io.IOException;

public class UE implements Sz<Ts> {
   private String objectiveName;
   private String objectiveValue;
   private Wn type;
   private int action;

   public UE() {
   }

   public UE(Wz objective, int actionIn) {
      this.objectiveName = objective.getName();
      this.objectiveValue = objective.getDisplayName();
      this.type = objective.getCriteria().getRenderType();
      this.action = actionIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.objectiveName = buf.readString(16);
      this.action = buf.readByte();
      if (this.action == 0 || this.action == 2) {
         this.objectiveValue = buf.readString(32);
         this.type = Wn.getByName(buf.readString(16));
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeString(this.objectiveName);
      buf.writeByte(this.action);
      if (this.action == 0 || this.action == 2) {
         buf.writeString(this.objectiveValue);
         buf.writeString(this.type.getRenderType());
      }

   }

   public void processPacket(Ts handler) {
      handler.handleScoreboardObjective(this);
   }

   public String getObjectiveName() {
      return this.objectiveName;
   }

   public String getObjectiveValue() {
      return this.objectiveValue;
   }

   public int getAction() {
      return this.action;
   }

   public Wn getRenderType() {
      return this.type;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
