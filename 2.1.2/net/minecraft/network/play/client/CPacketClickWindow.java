package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.inventory.ClickType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketClickWindow implements Packet<INetHandlerPlayServer> {
   private int windowId;
   private int slotId;
   private int packedClickData;
   private short actionNumber;
   private ItemStack clickedItem;
   private ClickType mode;

   public CPacketClickWindow() {
      this.clickedItem = ItemStack.EMPTY;
   }

   public CPacketClickWindow(int windowIdIn, int slotIdIn, int usedButtonIn, ClickType modeIn, ItemStack clickedItemIn, short actionNumberIn) {
      this.clickedItem = ItemStack.EMPTY;
      this.windowId = windowIdIn;
      this.slotId = slotIdIn;
      this.packedClickData = usedButtonIn;
      this.clickedItem = clickedItemIn.copy();
      this.actionNumber = actionNumberIn;
      this.mode = modeIn;
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processClickWindow(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.windowId = buf.readByte();
      this.slotId = buf.readShort();
      this.packedClickData = buf.readByte();
      this.actionNumber = buf.readShort();
      this.mode = (ClickType)buf.readEnumValue(ClickType.class);
      this.clickedItem = buf.readItemStack();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.slotId);
      buf.writeByte(this.packedClickData);
      buf.writeShort(this.actionNumber);
      buf.writeEnumValue(this.mode);
      buf.writeItemStack(this.clickedItem);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public int getSlotId() {
      return this.slotId;
   }

   public int getUsedButton() {
      return this.packedClickData;
   }

   public short getActionNumber() {
      return this.actionNumber;
   }

   public ItemStack getClickedItem() {
      return this.clickedItem;
   }

   public ClickType getClickType() {
      return this.mode;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayServer)var1);
   }
}
