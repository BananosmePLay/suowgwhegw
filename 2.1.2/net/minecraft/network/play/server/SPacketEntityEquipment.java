package net.minecraft.network.play.server;

import java.io.IOException;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class SPacketEntityEquipment implements Packet<INetHandlerPlayClient> {
   private int entityID;
   private EntityEquipmentSlot equipmentSlot;
   private ItemStack itemStack;

   public SPacketEntityEquipment() {
      this.itemStack = ItemStack.EMPTY;
   }

   public SPacketEntityEquipment(int entityIdIn, EntityEquipmentSlot equipmentSlotIn, ItemStack itemStackIn) {
      this.itemStack = ItemStack.EMPTY;
      this.entityID = entityIdIn;
      this.equipmentSlot = equipmentSlotIn;
      this.itemStack = itemStackIn.copy();
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.entityID = buf.readVarInt();
      this.equipmentSlot = (EntityEquipmentSlot)buf.readEnumValue(EntityEquipmentSlot.class);
      this.itemStack = buf.readItemStack();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeVarInt(this.entityID);
      buf.writeEnumValue(this.equipmentSlot);
      buf.writeItemStack(this.itemStack);
   }

   public void processPacket(INetHandlerPlayClient handler) {
      handler.handleEntityEquipment(this);
   }

   public ItemStack getItemStack() {
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
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayClient)var1);
   }
}
