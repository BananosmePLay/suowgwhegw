package neo;

import java.io.IOException;

public class kg extends lg {
   private final String title;
   private final String message;

   public kg(String titleIn, String messageIn) {
      this.title = titleIn;
      this.message = messageIn;
   }

   public void initGui() {
      super.initGui();
      this.buttonList.add(new jK(0, this.width / 2 - 100, 140, Ax.format("gui.cancel")));
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawGradientRect(0, 0, this.width, this.height, -12574688, -11530224);
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 90, 16777215);
      this.drawCenteredString(this.fontRenderer, this.message, this.width / 2, 110, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
   }

   protected void actionPerformed(jK button) throws IOException {
      this.mc.displayGuiScreen((lg)null);
   }
}
