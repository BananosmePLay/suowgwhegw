package neo;

import java.io.IOException;
import java.util.List;

public class bmw extends lg {
   public bmw() {
   }

   protected void actionPerformedRightClick(jK button) throws IOException {
   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      super.mouseClicked(mouseX, mouseY, mouseButton);
      if (mouseButton == 1) {
         jK guibutton = getSelectedButton(mouseX, mouseY, this.buttonList);
         if (guibutton != null && guibutton.enabled) {
            guibutton.playPressSound(this.mc.getSoundHandler());
            this.actionPerformedRightClick(guibutton);
         }
      }

   }

   public static jK getSelectedButton(int x, int y, List<jK> listButtons) {
      for(int i = 0; i < listButtons.size(); ++i) {
         jK guibutton = (jK)listButtons.get(i);
         if (guibutton.visible) {
            int j = lG.getButtonWidth(guibutton);
            int k = lG.getButtonHeight(guibutton);
            if (x >= guibutton.x && y >= guibutton.y && x < guibutton.x + j && y < guibutton.y + k) {
               return guibutton;
            }
         }
      }

      return null;
   }
}
