package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class lK extends lg {
   protected lL parentScreen;
   protected String messageLine1;
   private final String messageLine2;
   private final List<String> listLines = Lists.newArrayList();
   protected String confirmButtonText;
   protected String cancelButtonText;
   protected int parentButtonClickedId;
   private int ticksUntilEnable;

   public lK(lL parentScreenIn, String messageLine1In, String messageLine2In, int parentButtonClickedIdIn) {
      this.parentScreen = parentScreenIn;
      this.messageLine1 = messageLine1In;
      this.messageLine2 = messageLine2In;
      this.parentButtonClickedId = parentButtonClickedIdIn;
      this.confirmButtonText = Ax.format("gui.yes");
      this.cancelButtonText = Ax.format("gui.no");
   }

   public lK(lL parentScreenIn, String messageLine1In, String messageLine2In, String confirmButtonTextIn, String cancelButtonTextIn, int parentButtonClickedIdIn) {
      this.parentScreen = parentScreenIn;
      this.messageLine1 = messageLine1In;
      this.messageLine2 = messageLine2In;
      this.confirmButtonText = confirmButtonTextIn;
      this.cancelButtonText = cancelButtonTextIn;
      this.parentButtonClickedId = parentButtonClickedIdIn;
   }

   public void initGui() {
      this.buttonList.add(new kK(0, this.width / 2 - 155, this.height / 6 + 96, this.confirmButtonText));
      this.buttonList.add(new kK(1, this.width / 2 - 155 + 160, this.height / 6 + 96, this.cancelButtonText));
      this.listLines.clear();
      this.listLines.addAll(this.fontRenderer.listFormattedStringToWidth(this.messageLine2, this.width - 50));
   }

   protected void actionPerformed(jK button) throws IOException {
      this.parentScreen.confirmClicked(button.id == 0, this.parentButtonClickedId);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.messageLine1, this.width / 2, 70, 16777215);
      int i = 90;

      for(Iterator var5 = this.listLines.iterator(); var5.hasNext(); i += this.fontRenderer.FONT_HEIGHT) {
         String s = (String)var5.next();
         this.drawCenteredString(this.fontRenderer, s, this.width / 2, i, 16777215);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void setButtonDelay(int ticksUntilEnableIn) {
      this.ticksUntilEnable = ticksUntilEnableIn;

      jK guibutton;
      for(Iterator var2 = this.buttonList.iterator(); var2.hasNext(); guibutton.enabled = false) {
         guibutton = (jK)var2.next();
      }

   }

   public void updateScreen() {
      super.updateScreen();
      jK guibutton;
      if (--this.ticksUntilEnable == 0) {
         for(Iterator var1 = this.buttonList.iterator(); var1.hasNext(); guibutton.enabled = true) {
            guibutton = (jK)var1.next();
         }
      }

   }
}
