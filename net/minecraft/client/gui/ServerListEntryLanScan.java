package net.minecraft.client.gui;

import net.minecraft.client.Minecraft;

public class ServerListEntryLanScan implements GuiListExtended.IGuiListEntry {
   private final Minecraft mc = Minecraft.getMinecraft();

   public ServerListEntryLanScan() {
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      return false;
   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
   }
}
