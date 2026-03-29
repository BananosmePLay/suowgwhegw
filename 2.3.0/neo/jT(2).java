package neo;

import java.io.IOException;

public class jT extends lK {
   private final String openLinkWarning;
   private final String copyLinkButtonText;
   private final String linkText;
   private boolean showSecurityWarning = true;

   public jT(lL parentScreenIn, String linkTextIn, int parentButtonClickedIdIn, boolean trusted) {
      super(parentScreenIn, Ax.format(trusted ? "chat.link.confirmTrusted" : "chat.link.confirm"), linkTextIn, parentButtonClickedIdIn);
      this.confirmButtonText = Ax.format(trusted ? "chat.link.open" : "gui.yes");
      this.cancelButtonText = Ax.format(trusted ? "gui.cancel" : "gui.no");
      this.copyLinkButtonText = Ax.format("chat.copy");
      this.openLinkWarning = Ax.format("chat.link.warning");
      this.linkText = linkTextIn;
   }

   public void initGui() {
      super.initGui();
      this.buttonList.clear();
      this.buttonList.add(new jK(0, this.width / 2 - 50 - 105, this.height / 6 + 96, 100, 20, this.confirmButtonText));
      this.buttonList.add(new jK(2, this.width / 2 - 50, this.height / 6 + 96, 100, 20, this.copyLinkButtonText));
      this.buttonList.add(new jK(1, this.width / 2 - 50 + 105, this.height / 6 + 96, 100, 20, this.cancelButtonText));
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 2) {
         this.copyLinkToClipboard();
      }

      this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
   }

   public void copyLinkToClipboard() {
      setClipboardString(this.linkText);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      super.drawScreen(mouseX, mouseY, partialTicks);
      if (this.showSecurityWarning) {
         this.drawCenteredString(this.fontRenderer, this.openLinkWarning, this.width / 2, 110, 16764108);
      }

   }

   public void disableSecurityWarning() {
      this.showSecurityWarning = false;
   }
}
