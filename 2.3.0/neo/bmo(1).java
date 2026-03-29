package neo;

public class bmo extends lg {
   private lg prevScreen;
   protected String title;
   private Bj settings;
   private static Bi[] enumOptions;
   private bmy tooltipManager = new bmy(this, new bmB());

   public bmo(lg guiscreen, Bj gamesettings) {
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public void initGui() {
      this.title = Ax.format("of.options.detailsTitle");
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

      this.buttonList.add(new jK(200, this.width / 2 - 100, this.height / 6 + 168 + 11, Ax.format("gui.done")));
   }

   protected void actionPerformed(jK guibutton) {
      if (guibutton.enabled) {
         if (guibutton.id < 200 && guibutton instanceof kK) {
            this.settings.setOptionValue(((kK)guibutton).getOption(), 1);
            guibutton.displayString = this.settings.getKeyBinding(Bi.byOrdinal(guibutton.id));
         }

         if (guibutton.id == 200) {
            nC var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(this.prevScreen);
         }
      }

   }

   public void drawScreen(int x, int y, float f) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
      super.drawScreen(x, y, f);
      this.tooltipManager.drawTooltips(x, y, this.buttonList);
   }

   static {
      enumOptions = new Bi[]{Bi.CLOUDS, Bi.CLOUD_HEIGHT, Bi.TREES, Bi.RAIN, Bi.SKY, Bi.STARS, Bi.SUN_MOON, Bi.SHOW_CAPES, Bi.FOG_FANCY, Bi.FOG_START, Bi.TRANSLUCENT_BLOCKS, Bi.HELD_ITEM_TOOLTIPS, Bi.DROPPED_ITEMS, Bi.ENTITY_SHADOWS, Bi.VIGNETTE, Bi.ALTERNATE_BLOCKS, Bi.SWAMP_COLORS, Bi.SMOOTH_BIOMES};
   }
}
