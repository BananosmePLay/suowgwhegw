package neo;

import java.util.Iterator;
import net.minecraft.util.math.MathHelper;

public class boS extends bmw {
   private lg prevScreen;
   protected String title;
   private Bj settings;
   private bmy tooltipManager;
   private String screenName;
   private String screenText;
   private boolean changed;
   public static final String OPTION_PROFILE = "<profile>";
   public static final String OPTION_EMPTY = "<empty>";
   public static final String OPTION_REST = "*";

   public boS(lg guiscreen, Bj gamesettings) {
      this.tooltipManager = new bmy(this, new bmC());
      this.screenName = null;
      this.screenText = null;
      this.changed = false;
      this.title = "Shader Options";
      this.prevScreen = guiscreen;
      this.settings = gamesettings;
   }

   public boS(lg guiscreen, Bj gamesettings, String screenName) {
      this(guiscreen, gamesettings);
      this.screenName = screenName;
      if (screenName != null) {
         this.screenText = bpq.translate("screen." + screenName, screenName);
      }

   }

   public void initGui() {
      this.title = Ax.format("of.options.shaderOptionsTitle");
      int i = 100;
      int j = false;
      int k = 30;
      int l = 20;
      int i1 = 120;
      int j1 = 20;
      int k1 = bpq.getShaderPackColumns(this.screenName, 2);
      bou[] ashaderoption = bpq.getShaderPackOptions(this.screenName);
      if (ashaderoption != null) {
         int l1 = MathHelper.ceil((double)ashaderoption.length / 9.0);
         if (k1 < l1) {
            k1 = l1;
         }

         for(int i2 = 0; i2 < ashaderoption.length; ++i2) {
            bou shaderoption = ashaderoption[i2];
            if (shaderoption != null && shaderoption.isVisible()) {
               int j2 = i2 % k1;
               int k2 = i2 / k1;
               int l2 = Math.min(this.width / k1, 200);
               int j = (this.width - l2 * k1) / 2;
               int i3 = j2 * l2 + 5 + j;
               int j3 = k + k2 * l;
               int k3 = l2 - 10;
               String s = getButtonText(shaderoption, k3);
               Object guibuttonshaderoption;
               if (bpq.isShaderPackOptionSlider(shaderoption.getName())) {
                  guibuttonshaderoption = new boV(i + i2, i3, j3, k3, j1, shaderoption, s);
               } else {
                  guibuttonshaderoption = new boR(i + i2, i3, j3, k3, j1, shaderoption, s);
               }

               ((boR)guibuttonshaderoption).enabled = shaderoption.isEnabled();
               this.buttonList.add(guibuttonshaderoption);
            }
         }
      }

      this.buttonList.add(new jK(201, this.width / 2 - i1 - 20, this.height / 6 + 168 + 11, i1, j1, Ax.format("controls.reset")));
      this.buttonList.add(new jK(200, this.width / 2 + 20, this.height / 6 + 168 + 11, i1, j1, Ax.format("gui.done")));
   }

   public static String getButtonText(bou so, int btnWidth) {
      String s = so.getNameText();
      if (so instanceof boy) {
         boy shaderoptionscreen = (boy)so;
         return s + "...";
      } else {
         jH fontrenderer = XH.getMinecraft().fontRenderer;

         for(int i = fontrenderer.getStringWidth(": " + bmW.getOff()) + 5; fontrenderer.getStringWidth(s) + i >= btnWidth && s.length() > 0; s = s.substring(0, s.length() - 1)) {
         }

         String s1 = so.isChanged() ? so.getValueColor(so.getValue()) : "";
         String s2 = so.getValueText(so.getValue());
         return s + ": " + s1 + s2;
      }
   }

   protected void actionPerformed(jK guibutton) {
      if (guibutton.enabled) {
         if (guibutton.id < 200 && guibutton instanceof boR) {
            boR guibuttonshaderoption = (boR)guibutton;
            bou shaderoption = guibuttonshaderoption.getShaderOption();
            if (shaderoption instanceof boy) {
               String s = shaderoption.getName();
               boS guishaderoptions = new boS(this, this.settings, s);
               this.mc.displayGuiScreen(guishaderoptions);
               return;
            }

            if (isShiftKeyDown()) {
               shaderoption.resetValue();
            } else if (guibuttonshaderoption.isSwitchable()) {
               shaderoption.nextValue();
            }

            this.updateAllButtons();
            this.changed = true;
         }

         if (guibutton.id == 201) {
            bou[] ashaderoption = bpq.getChangedOptions(bpq.getShaderPackOptions());

            for(int i = 0; i < ashaderoption.length; ++i) {
               bou shaderoption1 = ashaderoption[i];
               shaderoption1.resetValue();
               this.changed = true;
            }

            this.updateAllButtons();
         }

         if (guibutton.id == 200) {
            if (this.changed) {
               bpq.saveShaderPackOptions();
               this.changed = false;
               bpq.uninit();
            }

            this.mc.displayGuiScreen(this.prevScreen);
         }
      }

   }

   protected void actionPerformedRightClick(jK btn) {
      if (btn instanceof boR) {
         boR guibuttonshaderoption = (boR)btn;
         bou shaderoption = guibuttonshaderoption.getShaderOption();
         if (isShiftKeyDown()) {
            shaderoption.resetValue();
         } else if (guibuttonshaderoption.isSwitchable()) {
            shaderoption.prevValue();
         }

         this.updateAllButtons();
         this.changed = true;
      }

   }

   public void onGuiClosed() {
      super.onGuiClosed();
      if (this.changed) {
         bpq.saveShaderPackOptions();
         this.changed = false;
         bpq.uninit();
      }

   }

   private void updateAllButtons() {
      Iterator var1 = this.buttonList.iterator();

      while(var1.hasNext()) {
         jK guibutton = (jK)var1.next();
         if (guibutton instanceof boR) {
            boR guibuttonshaderoption = (boR)guibutton;
            bou shaderoption = guibuttonshaderoption.getShaderOption();
            if (shaderoption instanceof bov) {
               bov shaderoptionprofile = (bov)shaderoption;
               shaderoptionprofile.updateProfile();
            }

            guibuttonshaderoption.displayString = getButtonText(shaderoption, guibuttonshaderoption.getButtonWidth());
            guibuttonshaderoption.valueChanged();
         }
      }

   }

   public void drawScreen(int x, int y, float f) {
      this.drawDefaultBackground();
      if (this.screenText != null) {
         this.drawCenteredString(this.fontRenderer, this.screenText, this.width / 2, 15, 16777215);
      } else {
         this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 15, 16777215);
      }

      super.drawScreen(x, y, f);
      this.tooltipManager.drawTooltips(x, y, this.buttonList);
   }
}
