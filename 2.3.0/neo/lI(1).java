package neo;

import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.lwjgl.input.Keyboard;

public class lI extends lg {
   private final lg lastScreen;
   private lE nameEdit;
   private final String worldId;

   public lI(lg p_i46593_1_, String p_i46593_2_) {
      this.lastScreen = p_i46593_1_;
      this.worldId = p_i46593_2_;
   }

   public void updateScreen() {
      this.nameEdit.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      jK guibutton = this.addButton(new jK(3, this.width / 2 - 100, this.height / 4 + 24 + 12, Ax.format("selectWorld.edit.resetIcon")));
      this.buttonList.add(new jK(4, this.width / 2 - 100, this.height / 4 + 48 + 12, Ax.format("selectWorld.edit.openFolder")));
      this.buttonList.add(new jK(0, this.width / 2 - 100, this.height / 4 + 96 + 12, Ax.format("selectWorld.edit.save")));
      this.buttonList.add(new jK(1, this.width / 2 - 100, this.height / 4 + 120 + 12, Ax.format("gui.cancel")));
      guibutton.enabled = this.mc.getSaveLoader().getFile(this.worldId, "icon.png").isFile();
      bgl isaveformat = this.mc.getSaveLoader();
      bhY worldinfo = isaveformat.getWorldInfo(this.worldId);
      String s = worldinfo == null ? "" : worldinfo.getWorldName();
      this.nameEdit = new lE(2, this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
      this.nameEdit.setFocused(true);
      this.nameEdit.setText(s);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 1) {
            this.mc.displayGuiScreen(this.lastScreen);
         } else {
            bgl isaveformat2;
            if (button.id == 0) {
               isaveformat2 = this.mc.getSaveLoader();
               isaveformat2.renameWorld(this.worldId, this.nameEdit.getText().trim());
               this.mc.displayGuiScreen(this.lastScreen);
            } else if (button.id == 3) {
               isaveformat2 = this.mc.getSaveLoader();
               FileUtils.deleteQuietly(isaveformat2.getFile(this.worldId, "icon.png"));
               button.enabled = false;
            } else if (button.id == 4) {
               isaveformat2 = this.mc.getSaveLoader();
               ys.openFile(isaveformat2.getFile(this.worldId, "icon.png").getParentFile());
            }
         }
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      this.nameEdit.textboxKeyTyped(typedChar, keyCode);
      ((jK)this.buttonList.get(2)).enabled = !this.nameEdit.getText().trim().isEmpty();
      if (keyCode == 28 || keyCode == 156) {
         this.actionPerformed((jK)this.buttonList.get(2));
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.nameEdit.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, Ax.format("selectWorld.edit.title"), this.width / 2, 20, 16777215);
      this.drawString(this.fontRenderer, Ax.format("selectWorld.enterName"), this.width / 2 - 100, 47, 10526880);
      this.nameEdit.drawTextBox();
      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
