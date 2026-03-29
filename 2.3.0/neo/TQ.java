package neo;

import java.io.IOException;

public class TQ implements Sz<Ts> {
   private int position;
   private String scoreName;

   public TQ() {
   }

   public TQ(int positionIn, Wz objective) {
      this.position = positionIn;
      if (objective == null) {
         this.scoreName = "";
      } else {
         this.scoreName = objective.getName();
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.position = buf.readByte();
      this.scoreName = buf.readString(16);
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.position);
      buf.writeString(this.scoreName);
   }

   public void processPacket(Ts handler) {
      handler.handleDisplayObjective(this);
   }

   public int getPosition() {
      return this.position;
   }

   public String getName() {
      return this.scoreName;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
