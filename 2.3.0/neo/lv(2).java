package neo;

import java.io.IOException;

public class lv extends jP {
   public lv() {
   }

   public void initGui() {
      super.initGui();
      this.buttonList.add(new jK(1, this.width / 2 - 100, this.height - 40, Ax.format("multiplayer.stopSleeping")));
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 1) {
         this.wakeFromSleep();
      } else if (keyCode != 28 && keyCode != 156) {
         super.keyTyped(typedChar, keyCode);
      } else {
         String s = this.inputField.getText().trim();
         if (!s.isEmpty()) {
            nC var10000 = this.mc;
            nC.player.sendChatMessage(s);
         }

         this.inputField.setText("");
         this.mc.ingameGUI.getChatGUI().resetScroll();
      }

   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 1) {
         this.wakeFromSleep();
      } else {
         super.actionPerformed(button);
      }

   }

   private void wakeFromSleep() {
      nC var10000 = this.mc;
      py nethandlerplayclient = nC.player.connection;
      nC var10003 = this.mc;
      nethandlerplayclient.sendPacket(new SQ(nC.player, SP.STOP_SLEEPING));
   }
}
