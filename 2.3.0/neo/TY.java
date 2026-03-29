package neo;

import java.io.IOException;
import net.minecraft.inventory.EntityEquipmentSlot;

public class TY implements Sz<Ts> {
   private int entityID;
   private EntityEquipmentSlot equipmentSlot;
   private Qy itemStack;

   public TY() {
      this.itemStack = Qy.EMPTY;
   }

   public TY(int entityIdIn, EntityEquipmentSlot equipmentSlotIn, Qy itemStackIn) {
      this.itemStack = Qy.EMPTY;
      this.entityID = entityIdIn;
      this.equipmentSlot = equipmentSlotIn;
      this.itemStack = itemStackIn.copy();
   }

   public void readPacketData(SA buf) throws IOException {
      this.entityID = buf.readVarInt();
      this.equipmentSlot = (EntityEquipmentSlot)buf.readEnumValue(EntityEquipmentSlot.class);
      this.itemStack = buf.readItemStack();
   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeVarInt(this.entityID);
      buf.writeEnumValue(this.equipmentSlot);
      buf.writeItemStack(this.itemStack);
   }

   public void processPacket(Ts handler) {
      handler.handleEntityEquipment(this);
   }

   public Qy getItemStack() {
      return this.itemStack;
   }

   public int getEntityID() {
      return this.entityID;
   }

   public EntityEquipmentSlot getEquipmentSlot() {
      return this.equipmentSlot;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
