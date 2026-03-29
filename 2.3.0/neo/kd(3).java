package neo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.text.ITextComponent;

public class kd extends lg {
   private final String reason;
   private final ITextComponent message;
   private List<String> multilineMessage;
   private final lg parentScreen;
   private int textHeight;

   public kd(lg screen, String reasonLocalizationKey, ITextComponent chatComp) {
      this.parentScreen = screen;
      this.reason = Ax.format(reasonLocalizationKey);
      this.message = chatComp;
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   public void initGui() {
      this.buttonList.clear();
      this.multilineMessage = this.fontRenderer.listFormattedStringToWidth(this.message.getFormattedText(), this.width - 50);
      this.textHeight = this.multilineMessage.size() * this.fontRenderer.FONT_HEIGHT;
      this.buttonList.add(new jK(0, this.width / 2 - 100, Math.min(this.height / 2 + this.textHeight / 2 + this.fontRenderer.FONT_HEIGHT, this.height - 30), Ax.format("gui.toMenu")));
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 0) {
         this.mc.displayGuiScreen(this.parentScreen);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.reason, this.width / 2, this.height / 2 - this.textHeight / 2 - this.fontRenderer.FONT_HEIGHT * 2, 11184810);
      int i = this.height / 2 - this.textHeight / 2;
      if (this.multilineMessage != null) {
         for(Iterator var5 = this.multilineMessage.iterator(); var5.hasNext(); i += this.fontRenderer.FONT_HEIGHT) {
            String s = (String)var5.next();
            this.drawCenteredString(this.fontRenderer, s, this.width / 2, i, 16777215);
         }
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
