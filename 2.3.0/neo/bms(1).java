package neo;

public class bms extends lg implements lL {
   private lg prevScreen;
   protected String title;
   private Bj settings;
   private static Bi[] enumOptions;
   private bmy tooltipManager = new bmy(this, new bmB());

   public bms(lg guiscreen, Bj gamesettings) {
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public void initGui() {
      this.title = Ax.format("of.options.otherTitle");
      this.buttonList.clear();

      for(int i = 0; i < enumOptions.length; ++i) {
         Bi gamesettings$options = enumOptions[i];
         int j = this.width / 2 - 155 + i % 2 * 160;
         int k = this.height / 6 + 21 * (i / 2) - 12;
         if (!gamesettings$options.isFloat()) {
            this.buttonList.add(new bmq(gamesettings$options.getOrdinal(), j, k, gamesettings$options, this.settings.getKeyBinding(gamesettings$options)));
         } else {
            this.buttonList.add(new bmr(gamesettings$options.getOrdinal(), j, k, gamesettings$options));
         }
      }

      this.buttonList.add(new jK(210, this.width / 2 - 100, this.height / 6 + 168 + 11 - 44, Ax.format("of.options.other.reset")));
      this.buttonList.add(new jK(200, this.width / 2 - 100, this.height / 6 + 168 + 11, Ax.format("gui.done")));
   }

   protected void actionPerformed(jK guibutton) {
      if (guibutton.enabled) {
         if (guibutton.id < 200 && guibutton instanceof kK) {
            this.settings.setOptionValue(((kK)guibutton).getOption(), 1);
            guibutton.displayString = this.settings.getKeyBinding(Bi.byOrdinal(guibutton.id));
         }

         nC var10000;
         if (guibutton.id == 200) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(this.prevScreen);
         }

         if (guibutton.id == 210) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            lK guiyesno = new lK(this, Ax.format("of.message.other.reset"), "", 9999);
            this.mc.displayGuiScreen(guiyesno);
         }
      }

   }

   public void confirmClicked(boolean flag, int i) {
      if (flag) {
         nC var10000 = this.mc;
         nC.gameSettings.resetSettings();
      }

      this.mc.displayGuiScreen(this);
   }

   public void drawScreen(int x, int y, float f) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(x, y, f);
      this.tooltipManager.drawTooltips(x, y, this.buttonList);
   }

   static {
      enumOptions = new Bi[]{Bi.LAGOMETER, Bi.PROFILER, Bi.SHOW_FPS, Bi.ADVANCED_TOOLTIPS, Bi.WEATHER, Bi.TIME, Bi.USE_FULLSCREEN, Bi.FULLSCREEN_MODE, Bi.ANAGLYPH, Bi.AUTOSAVE_TICKS, Bi.SCREENSHOT_SIZE, Bi.SHOW_GL_ERRORS};
   }
}
