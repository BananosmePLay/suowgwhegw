package neo;

import java.io.IOException;

public class kv extends lg {
   protected lg parentScreen;
   private ku list;
   private final Bj game_settings_3;
   private final AE languageManager;
   private kK forceUnicodeFontBtn;
   private kK confirmSettingsBtn;

   public kv(lg screen, Bj gameSettingsObj, AE manager) {
      this.parentScreen = screen;
      this.game_settings_3 = gameSettingsObj;
      this.languageManager = manager;
   }

   public void initGui() {
      this.forceUnicodeFontBtn = (kK)this.addButton(new kK(100, this.width / 2 - 155, this.height - 38, Bi.FORCE_UNICODE_FONT, this.game_settings_3.getKeyBinding(Bi.FORCE_UNICODE_FONT)));
      this.confirmSettingsBtn = (kK)this.addButton(new kK(6, this.width / 2 - 155 + 160, this.height - 38, Ax.format("gui.done")));
      this.list = new ku(this, this.mc);
      this.list.registerScrollButtons(7, 8);
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         switch (button.id) {
            case 5:
               break;
            case 6:
               this.mc.displayGuiScreen(this.parentScreen);
               break;
            case 100:
               if (button instanceof kK) {
                  this.game_settings_3.setOptionValue(((kK)button).getOption(), 1);
                  button.displayString = this.game_settings_3.getKeyBinding(Bi.FORCE_UNICODE_FONT);
                  mC scaledresolution = new mC(this.mc);
                  int i = scaledresolution.getScaledWidth();
                  int j = scaledresolution.getScaledHeight();
                  this.setWorldAndResolution(this.mc, i, j);
               }
               break;
            default:
               this.list.actionPerformed(button);
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, Ax.format("options.language"), this.width / 2, 16, 16777215);
      this.drawCenteredString(this.fontRenderer, "(" + Ax.format("options.languageWarning") + ")", this.width / 2, this.height - 56, 8421504);
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   // $FF: synthetic method
   static AE access$000(kv x0) {
      return x0.languageManager;
   }

   // $FF: synthetic method
   static Bj access$100(kv x0) {
      return x0.game_settings_3;
   }

   // $FF: synthetic method
   static kK access$200(kv x0) {
      return x0.confirmSettingsBtn;
   }

   // $FF: synthetic method
   static kK access$300(kv x0) {
      return x0.forceUnicodeFontBtn;
   }
}
