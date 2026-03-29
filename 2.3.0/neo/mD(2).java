package neo;

import java.io.IOException;

public class mD extends lg {
   private static final Bi[] CHAT_OPTIONS;
   private final lg parentScreen;
   private final Bj game_settings;
   private String chatTitle;
   private kK narratorButton;

   public mD(lg parentScreenIn, Bj gameSettingsIn) {
      this.parentScreen = parentScreenIn;
      this.game_settings = gameSettingsIn;
   }

   public void initGui() {
      this.chatTitle = Ax.format("options.chat.title");
      int i = 0;
      Bi[] var2 = CHAT_OPTIONS;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Bi gamesettings$options = var2[var4];
         if (gamesettings$options.isFloat()) {
            this.buttonList.add(new kM(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), gamesettings$options));
         } else {
            kK guioptionbutton = new kK(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 + 24 * (i >> 1), gamesettings$options, this.game_settings.getKeyBinding(gamesettings$options));
            this.buttonList.add(guioptionbutton);
            if (gamesettings$options == Bi.NARRATOR) {
               this.narratorButton = guioptionbutton;
               guioptionbutton.enabled = jC.INSTANCE.isActive();
            }
         }

         ++i;
      }

      this.buttonList.add(new jK(200, this.width / 2 - 100, this.height / 6 + 144, Ax.format("gui.done")));
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
         if (button.id < 100 && button instanceof kK) {
            this.game_settings.setOptionValue(((kK)button).getOption(), 1);
            button.displayString = this.game_settings.getKeyBinding(Bi.byOrdinal(button.id));
         }

         if (button.id == 200) {
            nC var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(this.parentScreen);
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.chatTitle, this.width / 2, 20, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   public void updateNarratorButton() {
      this.narratorButton.displayString = this.game_settings.getKeyBinding(Bi.byOrdinal(this.narratorButton.id));
   }

   static {
      CHAT_OPTIONS = new Bi[]{Bi.CHAT_VISIBILITY, Bi.CHAT_COLOR, Bi.CHAT_LINKS, Bi.CHAT_OPACITY, Bi.CHAT_LINKS_PROMPT, Bi.CHAT_SCALE, Bi.CHAT_HEIGHT_FOCUSED, Bi.CHAT_HEIGHT_UNFOCUSED, Bi.CHAT_WIDTH, Bi.REDUCED_DEBUG_INFO, Bi.NARRATOR};
   }
}
