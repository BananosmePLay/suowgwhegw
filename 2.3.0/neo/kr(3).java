package neo;

import net.minecraft.util.text.TextFormatting;

public class kr implements kx {
   private final Bl keybinding;
   private final String keyDesc;
   private final jK btnChangeKeyBinding;
   private final jK btnReset;
   // $FF: synthetic field
   final ks this$0;

   private kr(ks this$0, Bl name) {
      this.this$0 = this$0;
      this.keybinding = name;
      this.keyDesc = Ax.format(name.getKeyDescription());
      this.btnChangeKeyBinding = new jK(0, 0, 0, 75, 20, Ax.format(name.getKeyDescription()));
      this.btnReset = new jK(0, 0, 0, 50, 20, Ax.format("controls.reset"));
   }

   public void drawEntry(int slotIndex, int x, int y, int listWidth, int slotHeight, int mouseX, int mouseY, boolean isSelected, float partialTicks) {
      boolean flag = ks.access$200(this.this$0).buttonId == this.keybinding;
      ks.access$100(this.this$0).fontRenderer.drawString(this.keyDesc, x + 90 - ks.access$300(this.this$0), y + slotHeight / 2 - ks.access$100(this.this$0).fontRenderer.FONT_HEIGHT / 2, 16777215);
      this.btnReset.x = x + 190;
      this.btnReset.y = y;
      this.btnReset.enabled = this.keybinding.getKeyCode() != this.keybinding.getKeyCodeDefault();
      this.btnReset.drawButton(ks.access$100(this.this$0), mouseX, mouseY, partialTicks);
      this.btnChangeKeyBinding.x = x + 105;
      this.btnChangeKeyBinding.y = y;
      this.btnChangeKeyBinding.displayString = Bj.getKeyDisplayString(this.keybinding.getKeyCode());
      boolean flag1 = false;
      if (this.keybinding.getKeyCode() != 0) {
         ks.access$100(this.this$0);
         Bl[] var12 = nC.gameSettings.keyBindings;
         int var13 = var12.length;

         for(int var14 = 0; var14 < var13; ++var14) {
            Bl keybinding = var12[var14];
            if (keybinding != this.keybinding && keybinding.getKeyCode() == this.keybinding.getKeyCode()) {
               flag1 = true;
               break;
            }
         }
      }

      if (flag) {
         this.btnChangeKeyBinding.displayString = TextFormatting.WHITE + "> " + TextFormatting.YELLOW + this.btnChangeKeyBinding.displayString + TextFormatting.WHITE + " <";
      } else if (flag1) {
         this.btnChangeKeyBinding.displayString = TextFormatting.RED + this.btnChangeKeyBinding.displayString;
      }

      this.btnChangeKeyBinding.drawButton(ks.access$100(this.this$0), mouseX, mouseY, partialTicks);
   }

   public boolean mousePressed(int slotIndex, int mouseX, int mouseY, int mouseEvent, int relativeX, int relativeY) {
      if (this.btnChangeKeyBinding.mousePressed(ks.access$100(this.this$0), mouseX, mouseY)) {
         ks.access$200(this.this$0).buttonId = this.keybinding;
         return true;
      } else if (this.btnReset.mousePressed(ks.access$100(this.this$0), mouseX, mouseY)) {
         ks.access$100(this.this$0);
         nC.gameSettings.setOptionKeyBinding(this.keybinding, this.keybinding.getKeyCodeDefault());
         Bl.resetKeyBindingArrayAndHash();
         return true;
      } else {
         return false;
      }
   }

   public void mouseReleased(int slotIndex, int x, int y, int mouseEvent, int relativeX, int relativeY) {
      this.btnChangeKeyBinding.mouseReleased(x, y);
      this.btnReset.mouseReleased(x, y);
   }

   public void updatePosition(int slotIndex, int x, int y, float partialTicks) {
   }

   // $FF: synthetic method
   kr(ks x0, Bl x1, Object x2) {
      this(x0, x1);
   }
}
