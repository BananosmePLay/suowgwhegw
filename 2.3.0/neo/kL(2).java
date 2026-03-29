package neo;

import java.io.IOException;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class kL extends lg {
   private static final Bi[] SCREEN_OPTIONS;
   private final lg lastScreen;
   private final Bj settings;
   private jK difficultyButton;
   private kE lockButton;
   protected String title = "Options";

   public kL(lg p_i1046_1_, Bj p_i1046_2_) {
      this.lastScreen = p_i1046_1_;
      this.settings = p_i1046_2_;
   }

   public void initGui() {
      this.title = Ax.format("options.title");
      int i = 0;
      Bi[] var2 = SCREEN_OPTIONS;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Bi gamesettings$options = var2[var4];
         if (gamesettings$options.isFloat()) {
            this.buttonList.add(new kM(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), gamesettings$options));
         } else {
            kK guioptionbutton = new kK(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), gamesettings$options, this.settings.getKeyBinding(gamesettings$options));
            this.buttonList.add(guioptionbutton);
         }

         ++i;
      }

      if (this.mc.world != null) {
         baV enumdifficulty = this.mc.world.getDifficulty();
         this.difficultyButton = new jK(108, this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), 150, 20, this.getDifficultyText(enumdifficulty));
         this.buttonList.add(this.difficultyButton);
         if (this.mc.isSingleplayer() && !this.mc.world.getWorldInfo().isHardcoreModeEnabled()) {
            this.difficultyButton.setWidth(this.difficultyButton.getButtonWidth() - 20);
            this.lockButton = new kE(109, this.difficultyButton.x + this.difficultyButton.getButtonWidth(), this.difficultyButton.y);
            this.buttonList.add(this.lockButton);
            this.lockButton.setLocked(this.mc.world.getWorldInfo().isDifficultyLocked());
            this.lockButton.enabled = !this.lockButton.isLocked();
            this.difficultyButton.enabled = !this.lockButton.isLocked();
         } else {
            this.difficultyButton.enabled = false;
         }
      } else {
         this.buttonList.add(new kK(Bi.REALMS_NOTIFICATIONS.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), Bi.REALMS_NOTIFICATIONS, this.settings.getKeyBinding(Bi.REALMS_NOTIFICATIONS)));
      }

      this.buttonList.add(new jK(110, this.width / 2 - 155, this.height / 6 + 48 - 6, 150, 20, Ax.format("options.skinCustomisation")));
      this.buttonList.add(new jK(106, this.width / 2 + 5, this.height / 6 + 48 - 6, 150, 20, Ax.format("options.sounds")));
      this.buttonList.add(new jK(101, this.width / 2 - 155, this.height / 6 + 72 - 6, 150, 20, Ax.format("options.video")));
      this.buttonList.add(new jK(100, this.width / 2 + 5, this.height / 6 + 72 - 6, 150, 20, Ax.format("options.controls")));
      this.buttonList.add(new jK(102, this.width / 2 - 155, this.height / 6 + 96 - 6, 150, 20, Ax.format("options.language")));
      this.buttonList.add(new jK(103, this.width / 2 + 5, this.height / 6 + 96 - 6, 150, 20, Ax.format("options.chat.title")));
      this.buttonList.add(new jK(105, this.width / 2 - 155, this.height / 6 + 120 - 6, 150, 20, Ax.format("options.resourcepack")));
      this.buttonList.add(new jK(104, this.width / 2 + 5, this.height / 6 + 120 - 6, 150, 20, Ax.format("options.snooper.view")));
      this.buttonList.add(new jK(200, this.width / 2 - 100, this.height / 6 + 168, Ax.format("gui.done")));
   }

   public String getDifficultyText(baV p_175355_1_) {
      ITextComponent itextcomponent = new TextComponentString("");
      itextcomponent.appendSibling(new TextComponentTranslation("options.difficulty", new Object[0]));
      itextcomponent.appendText(": ");
      itextcomponent.appendSibling(new TextComponentTranslation(p_175355_1_.getTranslationKey(), new Object[0]));
      return itextcomponent.getFormattedText();
   }

   public void confirmClicked(boolean result, int id) {
      this.mc.displayGuiScreen(this);
      if (id == 109 && result && this.mc.world != null) {
         this.mc.world.getWorldInfo().setDifficultyLocked(true);
         this.lockButton.setLocked(true);
         this.lockButton.enabled = false;
         this.difficultyButton.enabled = false;
      }

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
            Bi gamesettings$options = ((kK)button).getOption();
            this.settings.setOptionValue(gamesettings$options, 1);
            button.displayString = this.settings.getKeyBinding(Bi.byOrdinal(button.id));
         }

         if (button.id == 108) {
            this.mc.world.getWorldInfo().setDifficulty(baV.byId(this.mc.world.getDifficulty().getId() + 1));
            this.difficultyButton.displayString = this.getDifficultyText(this.mc.world.getDifficulty());
         }

         if (button.id == 109) {
            this.mc.displayGuiScreen(new lK(this, (new TextComponentTranslation("difficulty.lock.title", new Object[0])).getFormattedText(), (new TextComponentTranslation("difficulty.lock.question", new Object[]{new TextComponentTranslation(this.mc.world.getWorldInfo().getDifficulty().getTranslationKey(), new Object[0])})).getFormattedText(), 109));
         }

         nC var10000;
         if (button.id == 110) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new ka(this));
         }

         if (button.id == 101) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new lG(this, this.settings));
         }

         if (button.id == 100) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new jU(this, this.settings));
         }

         if (button.id == 102) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new kv(this, this.settings, this.mc.getLanguageManager()));
         }

         if (button.id == 103) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new mD(this, this.settings));
         }

         if (button.id == 104) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new lA(this, this.settings));
         }

         if (button.id == 200) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(this.lastScreen);
         }

         if (button.id == 105) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new lr(this));
         }

         if (button.id == 106) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(new lq(this, this.settings));
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   static {
      SCREEN_OPTIONS = new Bi[]{Bi.FOV};
   }
}
