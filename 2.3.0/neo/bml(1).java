package neo;

public class bml extends lg {
   private lg prevScreen;
   protected String title;
   private Bj settings;
   private static Bi[] enumOptions;

   public bml(lg guiscreen, Bj gamesettings) {
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public void initGui() {
      this.title = Ax.format("of.options.animationsTitle");
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

      this.buttonList.add(new jK(210, this.width / 2 - 155, this.height / 6 + 168 + 11, 70, 20, bmW.get("of.options.animation.allOn")));
      this.buttonList.add(new jK(211, this.width / 2 - 155 + 80, this.height / 6 + 168 + 11, 70, 20, bmW.get("of.options.animation.allOff")));
      this.buttonList.add(new kK(200, this.width / 2 + 5, this.height / 6 + 168 + 11, Ax.format("gui.done")));
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
            nC.gameSettings.setAllAnimations(true);
         }

         if (guibutton.id == 211) {
            var10000 = this.mc;
            nC.gameSettings.setAllAnimations(false);
         }

         mC scaledresolution = new mC(this.mc);
         this.setWorldAndResolution(this.mc, scaledresolution.getScaledWidth(), scaledresolution.getScaledHeight());
      }

   }

   public void drawScreen(int x, int y, float f) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(x, y, f);
   }

   static {
      enumOptions = new Bi[]{Bi.ANIMATED_WATER, Bi.ANIMATED_LAVA, Bi.ANIMATED_FIRE, Bi.ANIMATED_PORTAL, Bi.ANIMATED_REDSTONE, Bi.ANIMATED_EXPLOSION, Bi.ANIMATED_FLAME, Bi.ANIMATED_SMOKE, Bi.VOID_PARTICLES, Bi.WATER_PARTICLES, Bi.RAIN_SPLASH, Bi.PORTAL_PARTICLES, Bi.POTION_PARTICLES, Bi.DRIPPING_WATER_LAVA, Bi.ANIMATED_TERRAIN, Bi.ANIMATED_TEXTURES, Bi.FIREWORK_PARTICLES, Bi.PARTICLES};
   }
}
