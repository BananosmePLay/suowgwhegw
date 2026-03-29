package neo;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.ITabCompleter;
import net.minecraft.util.TabCompleter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.input.Keyboard;

public class mc extends lg implements ITabCompleter {
   private lE commandField;
   private lE previousEdit;
   private final XZ commandBlockLogic;
   private jK doneButton;
   private jK cancelButton;
   private jK outputButton;
   private boolean trackOutput;
   private TabCompleter tabCompleter;

   public mc(XZ p_i46595_1_) {
      this.commandBlockLogic = p_i46595_1_;
   }

   public void updateScreen() {
      this.commandField.updateCursorCounter();
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.doneButton = this.addButton(new jK(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, Ax.format("gui.done")));
      this.cancelButton = this.addButton(new jK(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, Ax.format("gui.cancel")));
      this.outputButton = this.addButton(new jK(4, this.width / 2 + 150 - 20, 150, 20, 20, "O"));
      this.commandField = new lE(2, this.fontRenderer, this.width / 2 - 150, 50, 300, 20);
      this.commandField.setMaxStringLength(32500);
      this.commandField.setFocused(true);
      this.commandField.setText(this.commandBlockLogic.getCommand());
      this.previousEdit = new lE(3, this.fontRenderer, this.width / 2 - 150, 150, 276, 20);
      this.previousEdit.setMaxStringLength(32500);
      this.previousEdit.setEnabled(false);
      this.previousEdit.setText("-");
      this.trackOutput = this.commandBlockLogic.shouldTrackOutput();
      this.updateCommandOutput();
      this.doneButton.enabled = !this.commandField.getText().trim().isEmpty();
      this.tabCompleter = new TabCompleter(this.commandField, true) {
         @Nullable
         public BlockPos getTargetBlockPos() {
            return mc.this.commandBlockLogic.getPosition();
         }
      };
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 1) {
            this.commandBlockLogic.setTrackOutput(this.trackOutput);
            this.mc.displayGuiScreen((lg)null);
         } else if (button.id == 0) {
            SA packetbuffer = new SA(Unpooled.buffer());
            packetbuffer.writeByte(this.commandBlockLogic.getCommandBlockType());
            this.commandBlockLogic.fillInInfo(packetbuffer);
            packetbuffer.writeString(this.commandField.getText());
            packetbuffer.writeBoolean(this.commandBlockLogic.shouldTrackOutput());
            this.mc.getConnection().sendPacket(new SN("MC|AdvCmd", packetbuffer));
            if (!this.commandBlockLogic.shouldTrackOutput()) {
               this.commandBlockLogic.setLastOutput((ITextComponent)null);
            }

            this.mc.displayGuiScreen((lg)null);
         } else if (button.id == 4) {
            this.commandBlockLogic.setTrackOutput(!this.commandBlockLogic.shouldTrackOutput());
            this.updateCommandOutput();
         }
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      this.tabCompleter.resetRequested();
      if (keyCode == 15) {
         this.tabCompleter.complete();
      } else {
         this.tabCompleter.resetDidComplete();
      }

      this.commandField.textboxKeyTyped(typedChar, keyCode);
      this.previousEdit.textboxKeyTyped(typedChar, keyCode);
      this.doneButton.enabled = !this.commandField.getText().trim().isEmpty();
      if (keyCode != 28 && keyCode != 156) {
         if (keyCode == 1) {
            this.actionPerformed(this.cancelButton);
         }
      } else {
         this.actionPerformed(this.doneButton);
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.commandField.mouseClicked(mouseX, mouseY, mouseButton);
      this.previousEdit.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, Ax.format("advMode.setCommand"), this.width / 2, 20, 16777215);
      this.drawString(this.fontRenderer, Ax.format("advMode.command"), this.width / 2 - 150, 40, 10526880);
      this.commandField.drawTextBox();
      int i = 75;
      int j = 0;
      this.drawString(this.fontRenderer, Ax.format("advMode.nearestPlayer"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.randomPlayer"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.allPlayers"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.allEntities"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.self"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      if (!this.previousEdit.getText().isEmpty()) {
         i = i + j * this.fontRenderer.FONT_HEIGHT + 20;
         this.drawString(this.fontRenderer, Ax.format("advMode.previousOutput"), this.width / 2 - 150, i, 10526880);
         this.previousEdit.drawTextBox();
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   private void updateCommandOutput() {
      if (this.commandBlockLogic.shouldTrackOutput()) {
         this.outputButton.displayString = "O";
         if (this.commandBlockLogic.getLastOutput() != null) {
            this.previousEdit.setText(this.commandBlockLogic.getLastOutput().getUnformattedText());
         }
      } else {
         this.outputButton.displayString = "X";
         this.previousEdit.setText("-");
      }

   }

   public void setCompletions(String... newCompletions) {
      this.tabCompleter.setCompletions(newCompletions);
   }
}
