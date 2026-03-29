package neo;

import java.io.IOException;
import net.minecraft.inventory.ClickType;

public class SF implements Sz<Tt> {
   private int windowId;
   private int slotId;
   private int packedClickData;
   private short actionNumber;
   private Qy clickedItem;
   private ClickType mode;

   public SF() {
      this.clickedItem = Qy.EMPTY;
   }

   public SF(int windowIdIn, int slotIdIn, int usedButtonIn, ClickType modeIn, Qy clickedItemIn, short actionNumberIn) {
      this.clickedItem = Qy.EMPTY;
      this.windowId = windowIdIn;
      this.slotId = slotIdIn;
      this.packedClickData = usedButtonIn;
      this.clickedItem = clickedItemIn.copy();
      this.actionNumber = actionNumberIn;
      this.mode = modeIn;
   }

   public void processPacket(Tt handler) {
      handler.processClickWindow(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readByte();
      this.slotId = buf.readShort();
      this.packedClickData = buf.readByte();
      this.actionNumber = buf.readShort();
      this.mode = (ClickType)buf.readEnumValue(ClickType.class);
      this.clickedItem = buf.readItemStack();
   }

   public void writePacketData(SA buf) throws IOException {
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

   public Qy getClickedItem() {
      return this.clickedItem;
   }

   public ClickType getClickType() {
      return this.mode;
   }

   public String toString() {
      return "CPacketClickWindow{windowId=" + this.windowId + ", slotId=" + this.slotId + ", packedClickData=" + this.packedClickData + ", actionNumber=" + this.actionNumber + ", clickedItem=" + this.clickedItem + ", mode=" + this.mode + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Tt)var1);
   }
}
