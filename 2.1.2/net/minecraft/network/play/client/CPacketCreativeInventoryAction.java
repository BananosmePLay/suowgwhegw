package net.minecraft.network.play.client;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class CPacketCreativeInventoryAction implements Packet<INetHandlerPlayServer> {
   private int slotId;
   private ItemStack stack;

   public CPacketCreativeInventoryAction() {
      this.stack = ItemStack.EMPTY;
   }

   public CPacketCreativeInventoryAction(int slotIdIn, ItemStack stackIn) {
      this.stack = ItemStack.EMPTY;
      this.slotId = slotIdIn;
      this.stack = stackIn.copy();
   }

   public void processPacket(INetHandlerPlayServer handler) {
      handler.processCreativeInventoryAction(this);
   }

   public void readPacketData(PacketBuffer buf) throws IOException {
      this.slotId = buf.readShort();
      this.stack = buf.readItemStack();
   }

   public void writePacketData(PacketBuffer buf) throws IOException {
      buf.writeShort(this.slotId);
      buf.writeItemStack(this.stack);
   }

   public int getSlotId() {
      return this.slotId;
   }

   public ItemStack getStack() {
      return this.stack;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(INetHandler var1) {
      this.processPacket((INetHandlerPlayServer)var1);
   }
}
