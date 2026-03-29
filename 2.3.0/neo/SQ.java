package neo;

import java.io.IOException;

public class SQ implements Sz<Tt> {
   private int entityID;
   private SP action;
   private int auxData;
   public static boolean lastUpdatedSprint;

   public SQ() {
   }

   public SQ(Ig entityIn, SP actionIn) {
      this(entityIn, actionIn, 0);
   }

   public SQ(Ig entityIn, SP actionIn, int auxDataIn) {
      this.entityID = entityIn.getEntityId();
      this.action = actionIn;
      this.auxData = auxDataIn;
      if (actionIn == SP.START_SPRINTING) {
         lastUpdatedSprint = true;
      } else if (actionIn == SP.STOP_SPRINTING) {
         lastUpdatedSprint = false;
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.entityID = buf.readVarInt();
      this.action = (SP)buf.readEnumValue(SP.class);
      this.auxData = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityID);
      buf.writeEnumValue(this.action);
      buf.writeVarInt(this.auxData);
   }

   public void processPacket(Tt handler) {
      handler.processEntityAction(this);
   }

   public SP getAction() {
      return this.action;
   }

   public int getAuxData() {
      return this.auxData;
   }

   public String toString() {
      return "CPacketEntityAction{entityID=" + this.entityID + ", action=" + this.action + ", auxData=" + this.auxData + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
