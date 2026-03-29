package neo;

import java.io.IOException;

public class lG extends bmw {
   private lg parentGuiScreen;
   protected String screenTitle = "Video Settings";
   private Bj guiGameSettings;
   private static Bi[] videoOptions;
   private static final String __OBFID = "CL_00000718";
   private bmy tooltipManager = new bmy(this, new bmB());

   public lG(lg parentScreenIn, Bj gameSettingsIn) {
      this.parentGuiScreen = parentScreenIn;
      this.guiGameSettings = gameSettingsIn;
   }

   public void initGui() {
      this.screenTitle = Ax.format("options.videoTitle");
      this.buttonList.clear();

      int i;
      for(i = 0; i < videoOptions.length; ++i) {
         Bi gamesettings$options = videoOptions[i];
         if (gamesettings$options != null) {
            int j = this.width / 2 - 155 + i % 2 * 160;
            int k = this.height / 6 + 21 * (i / 2) - 12;
            if (gamesettings$options.isFloat()) {
               this.buttonList.add(new bmr(gamesettings$options.getOrdinal(), j, k, gamesettings$options));
            } else {
               this.buttonList.add(new bmq(gamesettings$options.getOrdinal(), j, k, gamesettings$options, this.guiGameSettings.getKeyBinding(gamesettings$options)));
            }
         }
      }

      i = this.height / 6 + 21 * (videoOptions.length / 2) - 12;
      int i1 = false;
      int i1 = this.width / 2 - 155 + 0;
      this.buttonList.add(new kK(231, i1, i, bmW.get("of.options.shaders")));
      i1 = this.width / 2 - 155 + 160;
      this.buttonList.add(new kK(202, i1, i, bmW.get("of.options.quality")));
      i += 21;
      i1 = this.width / 2 - 155 + 0;
      this.buttonList.add(new kK(201, i1, i, bmW.get("of.options.details")));
      i1 = this.width / 2 - 155 + 160;
      this.buttonList.add(new kK(212, i1, i, bmW.get("of.options.performance")));
      i += 21;
      i1 = this.width / 2 - 155 + 0;
      this.buttonList.add(new kK(211, i1, i, bmW.get("of.options.animations")));
      i1 = this.width / 2 - 155 + 160;
      this.buttonList.add(new kK(222, i1, i, bmW.get("of.options.other")));
      i += 21;
      this.buttonList.add(new jK(200, this.width / 2 - 100, this.height / 6 + 168 + 11, Ax.format("gui.done")));
   }

   protected void actionPerformed(jK button) throws IOException {
      this.actionPerformed(button, 1);
   }

   protected void actionPerformedRightClick(jK p_actionPerformedRightClick_1_) {
      if (p_actionPerformedRightClick_1_.id == Bi.GUI_SCALE.ordinal()) {
         this.actionPerformed(p_actionPerformedRightClick_1_, -1);
      }

   }

   private void actionPerformed(jK p_actionPerformed_1_, int p_actionPerformed_2_) {
      if (p_actionPerformed_1_.enabled) {
         int i = this.guiGameSettings.guiScale;
         if (p_actionPerformed_1_.id < 200 && p_actionPerformed_1_ instanceof kK) {
            this.guiGameSettings.setOptionValue(((kK)p_actionPerformed_1_).getOption(), p_actionPerformed_2_);
            p_actionPerformed_1_.displayString = this.guiGameSettings.getKeyBinding(Bi.byOrdinal(p_actionPerformed_1_.id));
         }

         nC var10000;
         if (p_actionPerformed_1_.id == 200) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            this.mc.displayGuiScreen(this.parentGuiScreen);
         }

         if (this.guiGameSettings.guiScale != i) {
            mC scaledresolution = new mC(this.mc);
            int j = scaledresolution.getScaledWidth();
            int k = scaledresolution.getScaledHeight();
            this.setWorldAndResolution(this.mc, j, k);
         }

         if (p_actionPerformed_1_.id == 201) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            bmo guidetailsettingsof = new bmo(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guidetailsettingsof);
         }

         if (p_actionPerformed_1_.id == 202) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            bmu guiqualitysettingsof = new bmu(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiqualitysettingsof);
         }

         if (p_actionPerformed_1_.id == 211) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            bml guianimationsettingsof = new bml(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guianimationsettingsof);
         }

         if (p_actionPerformed_1_.id == 212) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            bmt guiperformancesettingsof = new bmt(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiperformancesettingsof);
         }

         if (p_actionPerformed_1_.id == 222) {
            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            bms guiothersettingsof = new bms(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guiothersettingsof);
         }

         if (p_actionPerformed_1_.id == 231) {
            if (XH.isAntialiasing() || XH.isAntialiasingConfigured()) {
               XH.showGuiMessage(bmW.get("of.message.shaders.aa1"), bmW.get("of.message.shaders.aa2"));
               return;
            }

            if (XH.isAnisotropicFiltering()) {
               XH.showGuiMessage(bmW.get("of.message.shaders.af1"), bmW.get("of.message.shaders.af2"));
               return;
            }

            if (XH.isFastRender()) {
               XH.showGuiMessage(bmW.get("of.message.shaders.fr1"), bmW.get("of.message.shaders.fr2"));
               return;
            }

            if (XH.getGameSettings().anaglyph) {
               XH.showGuiMessage(bmW.get("of.message.shaders.an1"), bmW.get("of.message.shaders.an2"));
               return;
            }

            var10000 = this.mc;
            nC.gameSettings.saveOptions();
            boU guishaders = new boU(this, this.guiGameSettings);
            this.mc.displayGuiScreen(guishaders);
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 15, 16777215);
      String s = XH.getVersion();
      String s1 = "HD_U";
      if (s1.equals("HD")) {
         s = "OptiFine HD G5";
      }

      if (s1.equals("HD_U")) {
         s = "OptiFine HD G5 Ultra";
      }

      if (s1.equals("L")) {
         s = "OptiFine G5 Light";
      }

      this.drawString(this.fontRenderer, s, 2, this.height - 10, 8421504);
      String s2 = "Minecraft 1.12.2";
      int i = this.fontRenderer.getStringWidth(s2);
      this.drawString(this.fontRenderer, s2, this.width - i - 2, this.height - 10, 8421504);
      super.drawScreen(mouseX, mouseY, partialTicks);
      this.tooltipManager.drawTooltips(mouseX, mouseY, this.buttonList);
   }

   public static int getButtonWidth(jK p_getButtonWidth_0_) {
      return p_getButtonWidth_0_.width;
   }

   public static int getButtonHeight(jK p_getButtonHeight_0_) {
      return p_getButtonHeight_0_.height;
   }

   public static void drawGradientRect(lg p_drawGradientRect_0_, int p_drawGradientRect_1_, int p_drawGradientRect_2_, int p_drawGradientRect_3_, int p_drawGradientRect_4_, int p_drawGradientRect_5_, int p_drawGradientRect_6_) {
      p_drawGradientRect_0_.drawGradientRect(p_drawGradientRect_1_, p_drawGradientRect_2_, p_drawGradientRect_3_, p_drawGradientRect_4_, p_drawGradientRect_5_, p_drawGradientRect_6_);
   }

   public static String getGuiChatText(jP p_getGuiChatText_0_) {
      return p_getGuiChatText_0_.inputField.getText();
   }

   static {
      videoOptions = new Bi[]{Bi.GRAPHICS, Bi.RENDER_DISTANCE, Bi.AMBIENT_OCCLUSION, Bi.FRAMERATE_LIMIT, Bi.AO_LEVEL, Bi.VIEW_BOBBING, Bi.GUI_SCALE, Bi.USE_VBO, Bi.GAMMA, Bi.ATTACK_INDICATOR, Bi.DYNAMIC_LIGHTS, Bi.DYNAMIC_FOV};
   }
}
