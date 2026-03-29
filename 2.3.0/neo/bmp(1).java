package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class bmp extends lg {
   private lg parentScreen;
   private String messageLine1;
   private String messageLine2;
   private final List listLines2 = Lists.newArrayList();
   protected String confirmButtonText;
   private int ticksUntilEnable;

   public bmp(lg parentScreen, String line1, String line2) {
      this.parentScreen = parentScreen;
      this.messageLine1 = line1;
      this.messageLine2 = line2;
      this.confirmButtonText = Ax.format("gui.done");
   }

   public void initGui() {
      this.buttonList.add(new kK(0, this.width / 2 - 74, this.height / 6 + 96, this.confirmButtonText));
      this.listLines2.clear();
      this.listLines2.addAll(this.fontRenderer.listFormattedStringToWidth(this.messageLine2, this.width - 50));
   }

   protected void actionPerformed(jK button) throws IOException {
      XH.getMinecraft().displayGuiScreen(this.parentScreen);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.messageLine1, this.width / 2, 70, 16777215);
      int i = 90;

      for(Iterator var5 = this.listLines2.iterator(); var5.hasNext(); i += this.fontRenderer.FONT_HEIGHT) {
         Object s = var5.next();
         this.drawCenteredString(this.fontRenderer, (String)s, this.width / 2, i, 16777215);
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void setButtonDelay(int ticksUntilEnable) {
      this.ticksUntilEnable = ticksUntilEnable;

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
