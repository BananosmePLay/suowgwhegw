package neo;

import java.io.IOException;
import net.minecraft.util.SoundCategory;

public class lq extends lg {
   private final lg parent;
   private final Bj game_settings_4;
   protected String title = "Options";
   private String offDisplayString;

   public lq(lg parentIn, Bj settingsIn) {
      this.parent = parentIn;
      this.game_settings_4 = settingsIn;
   }

   public void initGui() {
      this.title = Ax.format("options.sounds.title");
      this.offDisplayString = Ax.format("options.off");
      int i = 0;
      this.buttonList.add(new lp(this, SoundCategory.MASTER.ordinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), SoundCategory.MASTER, true));
      i += 2;
      SoundCategory[] var2 = SoundCategory.values();
      int k = var2.length;

      for(int var4 = 0; var4 < k; ++var4) {
         SoundCategory soundcategory = var2[var4];
         if (soundcategory != SoundCategory.MASTER) {
            this.buttonList.add(new lp(this, soundcategory.ordinal(), this.width / 2 - 155 + i % 2 * 160, this.height / 6 - 12 + 24 * (i >> 1), soundcategory, false));
            ++i;
         }
      }

      int j = this.width / 2 - 75;
      k = this.height / 6 - 12;
      ++i;
      this.buttonList.add(new kK(201, j, k + 24 * (i >> 1), Bi.SHOW_SUBTITLES, this.game_settings_4.getKeyBinding(Bi.SHOW_SUBTITLES)));
      this.buttonList.add(new jK(200, this.width / 2 - 100, this.height / 6 + 168, Ax.format("gui.done")));
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
         nC var10000;
         if (button.id == 200) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(this.parent);
         } else if (button.id == 201) {
            var10000 = this.mc;
            nC.gameSettings.setOptionValue(Bi.SHOW_SUBTITLES, 1);
            nC var10001 = this.mc;
            button.displayString = nC.gameSettings.getKeyBinding(Bi.SHOW_SUBTITLES);
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   protected String getDisplayString(SoundCategory category) {
      float f = this.game_settings_4.getSoundLevel(category);
      return f == 0.0F ? this.offDisplayString : (int)(f * 100.0F) + "%";
   }

   // $FF: synthetic method
   static Bj access$000(lq x0) {
      return x0.game_settings_4;
   }
}
