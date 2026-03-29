package neo;

import java.io.IOException;

public class TG implements Sz<Ts> {
   private int collectedItemEntityId;
   private int entityId;
   private int collectedQuantity;

   public TG() {
   }

   public TG(int p_i47316_1_, int p_i47316_2_, int p_i47316_3_) {
      this.collectedItemEntityId = p_i47316_1_;
      this.entityId = p_i47316_2_;
      this.collectedQuantity = p_i47316_3_;
   }

   public void readPacketData(SA buf) throws IOException {
      this.collectedItemEntityId = buf.readVarInt();
      this.entityId = buf.readVarInt();
      this.collectedQuantity = buf.readVarInt();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.collectedItemEntityId);
      buf.writeVarInt(this.entityId);
      buf.writeVarInt(this.collectedQuantity);
   }

   public void processPacket(Ts handler) {
      handler.handleCollectItem(this);
   }

   public int getCollectedItemEntityID() {
      return this.collectedItemEntityId;
   }

   public int getEntityID() {
      return this.entityId;
   }

   public int getAmount() {
      return this.collectedQuantity;
   }

   public String toString() {
      return "SPacketCollectItem{collectedItemEntityId=" + this.collectedItemEntityId + ", entityId=" + this.entityId + ", collectedQuantity=" + this.collectedQuantity + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
