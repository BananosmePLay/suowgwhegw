package neo;

import io.netty.buffer.Unpooled;
import java.io.IOException;
import javax.annotation.Nullable;
import net.minecraft.util.ITabCompleter;
import net.minecraft.util.TabCompleter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import org.lwjgl.input.Keyboard;

public class jS extends lg implements ITabCompleter {
   private lE commandTextField;
   private lE previousOutputTextField;
   private final Yq commandBlock;
   private jK doneBtn;
   private jK cancelBtn;
   private jK outputBtn;
   private jK modeBtn;
   private jK conditionalBtn;
   private jK autoExecBtn;
   private boolean trackOutput;
   private Yp commandBlockMode;
   private TabCompleter tabCompleter;
   private boolean conditional;
   private boolean automatic;

   public jS(Yq commandBlockIn) {
      this.commandBlockMode = Yp.REDSTONE;
      this.commandBlock = commandBlockIn;
   }

   public void updateScreen() {
      this.commandTextField.updateCursorCounter();
   }

   public void initGui() {
      final XZ commandblockbaselogic = this.commandBlock.getCommandBlockLogic();
      Keyboard.enableRepeatEvents(true);
      this.buttonList.clear();
      this.doneBtn = this.addButton(new jK(0, this.width / 2 - 4 - 150, this.height / 4 + 120 + 12, 150, 20, Ax.format("gui.done")));
      this.cancelBtn = this.addButton(new jK(1, this.width / 2 + 4, this.height / 4 + 120 + 12, 150, 20, Ax.format("gui.cancel")));
      this.outputBtn = this.addButton(new jK(4, this.width / 2 + 150 - 20, 135, 20, 20, "O"));
      this.modeBtn = this.addButton(new jK(5, this.width / 2 - 50 - 100 - 4, 165, 100, 20, Ax.format("advMode.mode.sequence")));
      this.conditionalBtn = this.addButton(new jK(6, this.width / 2 - 50, 165, 100, 20, Ax.format("advMode.mode.unconditional")));
      this.autoExecBtn = this.addButton(new jK(7, this.width / 2 + 50 + 4, 165, 100, 20, Ax.format("advMode.mode.redstoneTriggered")));
      this.commandTextField = new lE(2, this.fontRenderer, this.width / 2 - 150, 50, 300, 20);
      this.commandTextField.setMaxStringLength(32500);
      this.commandTextField.setFocused(true);
      this.previousOutputTextField = new lE(3, this.fontRenderer, this.width / 2 - 150, 135, 276, 20);
      this.previousOutputTextField.setMaxStringLength(32500);
      this.previousOutputTextField.setEnabled(false);
      this.previousOutputTextField.setText("-");
      this.doneBtn.enabled = false;
      this.outputBtn.enabled = false;
      this.modeBtn.enabled = false;
      this.conditionalBtn.enabled = false;
      this.autoExecBtn.enabled = false;
      this.tabCompleter = new TabCompleter(this.commandTextField, true) {
         @Nullable
         public BlockPos getTargetBlockPos() {
            return commandblockbaselogic.getPosition();
         }
      };
   }

   public void updateGui() {
      XZ commandblockbaselogic = this.commandBlock.getCommandBlockLogic();
      this.commandTextField.setText(commandblockbaselogic.getCommand());
      this.trackOutput = commandblockbaselogic.shouldTrackOutput();
      this.commandBlockMode = this.commandBlock.getMode();
      this.conditional = this.commandBlock.isConditional();
      this.automatic = this.commandBlock.isAuto();
      this.updateCmdOutput();
      this.updateMode();
      this.updateConditional();
      this.updateAutoExec();
      this.doneBtn.enabled = true;
      this.outputBtn.enabled = true;
      this.modeBtn.enabled = true;
      this.conditionalBtn.enabled = true;
      this.autoExecBtn.enabled = true;
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         XZ commandblockbaselogic = this.commandBlock.getCommandBlockLogic();
         if (button.id == 1) {
            commandblockbaselogic.setTrackOutput(this.trackOutput);
            this.mc.displayGuiScreen((lg)null);
         } else if (button.id == 0) {
            SA packetbuffer = new SA(Unpooled.buffer());
            commandblockbaselogic.fillInInfo(packetbuffer);
            packetbuffer.writeString(this.commandTextField.getText());
            packetbuffer.writeBoolean(commandblockbaselogic.shouldTrackOutput());
            packetbuffer.writeString(this.commandBlockMode.name());
            packetbuffer.writeBoolean(this.conditional);
            packetbuffer.writeBoolean(this.automatic);
            this.mc.getConnection().sendPacket(new SN("MC|AutoCmd", packetbuffer));
            if (!commandblockbaselogic.shouldTrackOutput()) {
               commandblockbaselogic.setLastOutput((ITextComponent)null);
            }

            this.mc.displayGuiScreen((lg)null);
         } else if (button.id == 4) {
            commandblockbaselogic.setTrackOutput(!commandblockbaselogic.shouldTrackOutput());
            this.updateCmdOutput();
         } else if (button.id == 5) {
            this.nextMode();
            this.updateMode();
         } else if (button.id == 6) {
            this.conditional = !this.conditional;
            this.updateConditional();
         } else if (button.id == 7) {
            this.automatic = !this.automatic;
            this.updateAutoExec();
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

      this.commandTextField.textboxKeyTyped(typedChar, keyCode);
      this.previousOutputTextField.textboxKeyTyped(typedChar, keyCode);
      if (keyCode != 28 && keyCode != 156) {
         if (keyCode == 1) {
            this.actionPerformed(this.cancelBtn);
         }
      } else {
         this.actionPerformed(this.doneBtn);
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      this.commandTextField.mouseClicked(mouseX, mouseY, mouseButton);
      this.previousOutputTextField.mouseClicked(mouseX, mouseY, mouseButton);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, Ax.format("advMode.setCommand"), this.width / 2, 20, 16777215);
      this.drawString(this.fontRenderer, Ax.format("advMode.command"), this.width / 2 - 150, 40, 10526880);
      this.commandTextField.drawTextBox();
      int i = 75;
      int j = 0;
      this.drawString(this.fontRenderer, Ax.format("advMode.nearestPlayer"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.randomPlayer"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.allPlayers"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.allEntities"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      this.drawString(this.fontRenderer, Ax.format("advMode.self"), this.width / 2 - 140, i + j++ * this.fontRenderer.FONT_HEIGHT, 10526880);
      if (!this.previousOutputTextField.getText().isEmpty()) {
         i = i + j * this.fontRenderer.FONT_HEIGHT + 1;
         this.drawString(this.fontRenderer, Ax.format("advMode.previousOutput"), this.width / 2 - 150, i + 4, 10526880);
         this.previousOutputTextField.drawTextBox();
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   private void updateCmdOutput() {
      XZ commandblockbaselogic = this.commandBlock.getCommandBlockLogic();
      if (commandblockbaselogic.shouldTrackOutput()) {
         this.outputBtn.displayString = "O";
         if (commandblockbaselogic.getLastOutput() != null) {
            this.previousOutputTextField.setText(commandblockbaselogic.getLastOutput().getUnformattedText());
         }
      } else {
         this.outputBtn.displayString = "X";
         this.previousOutputTextField.setText("-");
      }

   }

   private void updateMode() {
      switch (this.commandBlockMode) {
         case SEQUENCE:
            this.modeBtn.displayString = Ax.format("advMode.mode.sequence");
            break;
         case AUTO:
            this.modeBtn.displayString = Ax.format("advMode.mode.auto");
            break;
         case REDSTONE:
            this.modeBtn.displayString = Ax.format("advMode.mode.redstone");
      }

   }

   private void nextMode() {
      switch (this.commandBlockMode) {
         case SEQUENCE:
            this.commandBlockMode = Yp.AUTO;
            break;
         case AUTO:
            this.commandBlockMode = Yp.REDSTONE;
            break;
         case REDSTONE:
            this.commandBlockMode = Yp.SEQUENCE;
      }

   }

   private void updateConditional() {
      if (this.conditional) {
         this.conditionalBtn.displayString = Ax.format("advMode.mode.conditional");
      } else {
         this.conditionalBtn.displayString = Ax.format("advMode.mode.unconditional");
      }

   }

   private void updateAutoExec() {
      if (this.automatic) {
         this.autoExecBtn.displayString = Ax.format("advMode.mode.autoexec.bat");
      } else {
         this.autoExecBtn.displayString = Ax.format("advMode.mode.redstoneTriggered");
      }

   }

   public void setCompletions(String... newCompletions) {
      this.tabCompleter.setCompletions(newCompletions);
   }
}
