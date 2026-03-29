package neo;

import java.io.IOException;
import net.minecraft.util.text.ITextComponent;

public class Uo implements Sz<Ts> {
   private int windowId;
   private String inventoryType;
   private ITextComponent windowTitle;
   private int slotCount;
   private int entityId;

   public Uo() {
   }

   public Uo(int windowIdIn, String inventoryTypeIn, ITextComponent windowTitleIn) {
      this(windowIdIn, inventoryTypeIn, windowTitleIn, 0);
   }

   public Uo(int windowIdIn, String inventoryTypeIn, ITextComponent windowTitleIn, int slotCountIn) {
      this.windowId = windowIdIn;
      this.inventoryType = inventoryTypeIn;
      this.windowTitle = windowTitleIn;
      this.slotCount = slotCountIn;
   }

   public Uo(int windowIdIn, String inventoryTypeIn, ITextComponent windowTitleIn, int slotCountIn, int entityIdIn) {
      this(windowIdIn, inventoryTypeIn, windowTitleIn, slotCountIn);
      this.entityId = entityIdIn;
   }

   public void processPacket(Ts handler) {
      handler.handleOpenWindow(this);
   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
      this.inventoryType = buf.readString(32);
      this.windowTitle = buf.readTextComponent();
      this.slotCount = buf.readUnsignedByte();
      if (this.inventoryType.equals("EntityHorse")) {
         this.entityId = buf.readInt();
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeString(this.inventoryType);
      buf.writeTextComponent(this.windowTitle);
      buf.writeByte(this.slotCount);
      if (this.inventoryType.equals("EntityHorse")) {
         buf.writeInt(this.entityId);
      }

   }

   public int getWindowId() {
      return this.windowId;
   }

   public String getGuiId() {
      return this.inventoryType;
   }

   public ITextComponent getWindowTitle() {
      return this.windowTitle;
   }

   public int getSlotCount() {
      return this.slotCount;
   }

   public int getEntityId() {
      return this.entityId;
   }

   public boolean hasSlots() {
      return this.slotCount > 0;
   }

   public String toString() {
      return "SPacketOpenWindow{windowId=" + this.windowId + ", inventoryType='" + this.inventoryType + '\'' + ", windowTitle=" + this.windowTitle + ", slotCount=" + this.slotCount + ", entityId=" + this.entityId + '}';
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
