package neo;

import java.io.IOException;

public class ka extends lg {
   private final lg parentScreen;
   private String title;

   public ka(lg parentScreenIn) {
      this.parentScreen = parentScreenIn;
   }

   public void initGui() {
      int i = 0;
      this.title = Ax.format("options.skinCustomisation.title");
      MH[] var2 = MH.values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         MH enumplayermodelparts = var2[var4];
         this.buttonList.add(new jZ(this, enumplayermodelparts.getPartId(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), 150, 20, enumplayermodelparts));
         ++i;
      }

      int var10004 = this.width / 2 - 155 + i % 2 * 160;
      int var10005 = this.height / 6 + 24 * (i >> 1);
      nC var10007 = this.mc;
      this.buttonList.add(new kK(199, var10004, var10005, Bi.MAIN_HAND, nC.gameSettings.getKeyBinding(Bi.MAIN_HAND)));
      ++i;
      if (i % 2 == 1) {
         ++i;
      }

      this.buttonList.add(new bmm(210, this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), Ax.format("of.options.skinCustomisation.ofCape")));
      i += 2;
      this.buttonList.add(new jK(200, this.width / 2 - 100, this.height / 6 + 24 * (i >> 1), Ax.format("gui.done")));
   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (keyCode == 1) {
         nC var10000 = this.mc;
         nC.gameSettings.saveOptions();
      }

      super.keyTyped(typedChar, keyCode);
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 210) {
            this.mc.displayGuiScreen(new bmv(this));
         }

         nC var10000;
         if (button.id == 200) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(this.parentScreen);
         } else if (button.id == 199) {
            var10000 = this.mc;
            nC.gameSettings.setOptionValue(Bi.MAIN_HAND, 1);
            nC var10001 = this.mc;
            button.displayString = nC.gameSettings.getKeyBinding(Bi.MAIN_HAND);
            var10000 = this.mc;
            nC.gameSettings.sendSettingsToServer();
         } else if (button instanceof jZ) {
            MH enumplayermodelparts = jZ.access$100((jZ)button);
            var10000 = this.mc;
            nC.gameSettings.switchModelPartEnabled(enumplayermodelparts);
            button.displayString = this.getMessage(enumplayermodelparts);
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   private String getMessage(MH playerModelParts) {
      nC var10000 = this.mc;
      String s;
      if (nC.gameSettings.getModelParts().contains(playerModelParts)) {
         s = Ax.format("options.on");
      } else {
         s = Ax.format("options.off");
      }

      return playerModelParts.getName().getFormattedText() + ": " + s;
   }

   // $FF: synthetic method
   static String access$200(ka x0, MH x1) {
      return x0.getMessage(x1);
   }
}
