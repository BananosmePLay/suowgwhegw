package neo;

import java.io.IOException;
import javax.annotation.Nullable;

public class UB implements Sz<Ts> {
   private int entityId;
   private VW effectId;

   public UB() {
   }

   public UB(int entityIdIn, VW potionIn) {
      this.entityId = entityIdIn;
      this.effectId = potionIn;
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityId = buf.readVarInt();
      this.effectId = VW.getPotionById(buf.readUnsignedByte());
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityId);
      buf.writeByte(VW.getIdFromPotion(this.effectId));
   }

   public void processPacket(Ts handler) {
      handler.handleRemoveEntityEffect(this);
   }

   @Nullable
   public Ig getEntity(bij worldIn) {
      return worldIn.getEntityByID(this.entityId);
   }

   @Nullable
   public VW getPotion() {
      return this.effectId;
   }

   public String toString() {
      return "SPacketRemoveEntityEffect{entityId=" + this.entityId + ", effectId=" + this.effectId + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
