package neo;

import java.io.IOException;
import net.minecraft.util.ITabCompleter;
import net.minecraft.util.TabCompleter;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.ITextComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class jP extends lg implements ITabCompleter {
   private static final Logger LOGGER = LogManager.getLogger();
   private String historyBuffer = "";
   private int sentHistoryCursor = -1;
   private TabCompleter tabCompleter;
   protected lE inputField;
   private String defaultInputFieldText = "";

   public jP() {
   }

   public jP(String defaultText) {
      this.defaultInputFieldText = defaultText;
   }

   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.sentHistoryCursor = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
      this.inputField = new lE(0, this.fontRenderer, 4, this.height - 12, this.width - 4, 12);
      this.inputField.setMaxStringLength(256);
      this.inputField.setEnableBackgroundDrawing(false);
      this.inputField.setFocused(true);
      this.inputField.setText(this.defaultInputFieldText);
      this.inputField.setCanLoseFocus(false);
      this.tabCompleter = new jO(this.inputField);
   }

   public void onGuiClosed() {
      Keyboard.enableRepeatEvents(false);
      this.mc.ingameGUI.getChatGUI().resetScroll();
   }

   public void updateScreen() {
      this.inputField.updateCursorCounter();
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      this.tabCompleter.resetRequested();
      if (keyCode == 15) {
         this.tabCompleter.complete();
      } else {
         this.tabCompleter.resetDidComplete();
      }

      if (keyCode == 1) {
         this.mc.displayGuiScreen((lg)null);
      } else if (keyCode != 28 && keyCode != 156) {
         if (keyCode == 200) {
            this.getSentHistory(-1);
         } else if (keyCode == 208) {
            this.getSentHistory(1);
         } else if (keyCode == 201) {
            this.mc.ingameGUI.getChatGUI().scroll(this.mc.ingameGUI.getChatGUI().getLineCount() - 1);
         } else if (keyCode == 209) {
            this.mc.ingameGUI.getChatGUI().scroll(-this.mc.ingameGUI.getChatGUI().getLineCount() + 1);
         } else {
            this.inputField.textboxKeyTyped(typedChar, keyCode);
         }
      } else {
         String s = this.inputField.getText().trim();
         if (!s.isEmpty()) {
            this.sendChatMessage(s);
         }

         this.mc.displayGuiScreen((lg)null);
      }

   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      int i = Mouse.getEventDWheel();
      if (i != 0) {
         if (i > 1) {
            i = 1;
         }

         if (i < -1) {
            i = -1;
         }

         if (!isShiftKeyDown()) {
            i *= 7;
         }

         this.mc.ingameGUI.getChatGUI().scroll(i);
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (mouseButton == 0) {
         ITextComponent itextcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
         if (itextcomponent != null && this.handleComponentClick(itextcomponent)) {
            return;
         }
      }

      this.inputField.mouseClicked(mouseX, mouseY, mouseButton);
      super.mouseClicked(mouseX, mouseY, mouseButton);
   }

   protected void setText(String newChatText, boolean shouldOverwrite) {
      if (shouldOverwrite) {
         this.inputField.setText(newChatText);
      } else {
         this.inputField.writeText(newChatText);
      }

   }

   public void getSentHistory(int msgPos) {
      int i = this.sentHistoryCursor + msgPos;
      int j = this.mc.ingameGUI.getChatGUI().getSentMessages().size();
      i = MathHelper.clamp(i, 0, j);
      if (i != this.sentHistoryCursor) {
         if (i == j) {
            this.sentHistoryCursor = j;
            this.inputField.setText(this.historyBuffer);
         } else {
            if (this.sentHistoryCursor == j) {
               this.historyBuffer = this.inputField.getText();
            }

            this.inputField.setText((String)this.mc.ingameGUI.getChatGUI().getSentMessages().get(i));
            this.sentHistoryCursor = i;
         }
      }

   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      super.mouseReleased(mouseX, mouseY, state);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      drawRect(2, this.height - 14, this.width - 2, this.height - 2, Integer.MIN_VALUE);
      this.inputField.drawTextBox();

      try {
         ITextComponent itextcomponent = this.mc.ingameGUI.getChatGUI().getChatComponent(Mouse.getX(), Mouse.getY());
         if (itextcomponent != null) {
            itextcomponent.getStyle();
            if (itextcomponent.getStyle().getHoverEvent() != null) {
               this.handleComponentHover(itextcomponent, mouseX, mouseY);
            }
         }
      } catch (Exception var5) {
         Exception exception = var5;
         exception.printStackTrace();
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public boolean doesGuiPauseGame() {
      return false;
   }

   public void setCompletions(String... newCompletions) {
      this.tabCompleter.setCompletions(newCompletions);
   }
}
