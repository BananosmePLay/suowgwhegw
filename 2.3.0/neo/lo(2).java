package neo;

import java.io.IOException;
import java.net.URI;
import net.minecraft.util.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class lo extends lg {
   private static final Logger LOGGER = LogManager.getLogger();
   private static final ResourceLocation DEMO_BACKGROUND_LOCATION = new ResourceLocation("textures/gui/demo_background.png");

   public lo() {
   }

   public void initGui() {
      this.buttonList.clear();
      int i = true;
      this.buttonList.add(new jK(1, this.width / 2 - 116, this.height / 2 + 62 + -16, 114, 20, Ax.format("demo.help.buy")));
      this.buttonList.add(new jK(2, this.width / 2 + 2, this.height / 2 + 62 + -16, 114, 20, Ax.format("demo.help.later")));
   }

   protected void actionPerformed(jK button) throws IOException {
      switch (button.id) {
         case 1:
            button.enabled = false;

            try {
               Class<?> oclass = Class.forName("java.awt.Desktop");
               Object object = oclass.getMethod("getDesktop").invoke((Object)null);
               oclass.getMethod("browse", URI.class).invoke(object, new URI("http://www.minecraft.net/store?source=demo"));
            } catch (Throwable var4) {
               Throwable throwable = var4;
               LOGGER.error("Couldn't open link", throwable);
            }
            break;
         case 2:
            this.mc.displayGuiScreen((lg)null);
            this.mc.setIngameFocus();
      }

   }

   public void drawDefaultBackground() {
      super.drawDefaultBackground();
      yh.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(DEMO_BACKGROUND_LOCATION);
      int i = (this.width - 248) / 2;
      int j = (this.height - 166) / 2;
      this.drawTexturedModalRect(i, j, 0, 0, 248, 166);
   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      int i = (this.width - 248) / 2 + 10;
      int j = (this.height - 166) / 2 + 8;
      this.fontRenderer.drawString(Ax.format("demo.help.title"), i, j, 2039583);
      j += 12;
      nC var10000 = this.mc;
      Bj gamesettings = nC.gameSettings;
      this.fontRenderer.drawString(Ax.format("demo.help.movementShort", Bj.getKeyDisplayString(gamesettings.keyBindForward.getKeyCode()), Bj.getKeyDisplayString(gamesettings.keyBindLeft.getKeyCode()), Bj.getKeyDisplayString(gamesettings.keyBindBack.getKeyCode()), Bj.getKeyDisplayString(gamesettings.keyBindRight.getKeyCode())), i, j, 5197647);
      this.fontRenderer.drawString(Ax.format("demo.help.movementMouse"), i, j + 12, 5197647);
      this.fontRenderer.drawString(Ax.format("demo.help.jump", Bj.getKeyDisplayString(gamesettings.keyBindJump.getKeyCode())), i, j + 24, 5197647);
      this.fontRenderer.drawString(Ax.format("demo.help.inventory", Bj.getKeyDisplayString(gamesettings.keyBindInventory.getKeyCode())), i, j + 36, 5197647);
      this.fontRenderer.drawSplitString(Ax.format("demo.help.fullWrapped"), i, j + 68, 218, 2039583);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }
}
