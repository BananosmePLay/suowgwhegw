package neo;

import java.io.IOException;

public class jU extends lg {
   private static final Bi[] OPTIONS_ARR;
   private final lg parentScreen;
   protected String screenTitle = "Controls";
   private final Bj options;
   public Bl buttonId;
   public long time;
   private ks keyBindingList;
   private jK buttonReset;

   public jU(lg screen, Bj settings) {
      this.parentScreen = screen;
      this.options = settings;
   }

   public void initGui() {
      this.keyBindingList = new ks(this, this.mc);
      this.buttonList.add(new jK(200, this.width / 2 - 155 + 160, this.height - 29, 150, 20, Ax.format("gui.done")));
      this.buttonReset = this.addButton(new jK(201, this.width / 2 - 155, this.height - 29, 150, 20, Ax.format("controls.resetAll")));
      this.screenTitle = Ax.format("controls.title");
      int i = 0;
      Bi[] var2 = OPTIONS_ARR;
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         Bi gamesettings$options = var2[var4];
         if (gamesettings$options.isFloat()) {
            this.buttonList.add(new kM(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gamesettings$options));
         } else {
            this.buttonList.add(new kK(gamesettings$options.getOrdinal(), this.width / 2 - 155 + i % 2 * 160, 18 + 24 * (i >> 1), gamesettings$options, this.options.getKeyBinding(gamesettings$options)));
         }

         ++i;
      }

   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.keyBindingList.handleMouseInput();
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.id == 200) {
         this.mc.displayGuiScreen(this.parentScreen);
      } else if (button.id == 201) {
         nC var10000 = this.mc;
         Bl[] var2 = nC.gameSettings.keyBindings;
         int var3 = var2.length;

         for(int var4 = 0; var4 < var3; ++var4) {
            Bl keybinding = var2[var4];
            keybinding.setKeyCode(keybinding.getKeyCodeDefault());
         }

         Bl.resetKeyBindingArrayAndHash();
      } else if (button.id < 100 && button instanceof kK) {
         this.options.setOptionValue(((kK)button).getOption(), 1);
         button.displayString = this.options.getKeyBinding(Bi.byOrdinal(button.id));
      }

   }

   protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
      if (this.buttonId != null) {
         this.options.setOptionKeyBinding(this.buttonId, -100 + mouseButton);
         this.buttonId = null;
         Bl.resetKeyBindingArrayAndHash();
      } else if (mouseButton != 0 || !this.keyBindingList.mouseClicked(mouseX, mouseY, mouseButton)) {
         super.mouseClicked(mouseX, mouseY, mouseButton);
      }

   }

   protected void mouseReleased(int mouseX, int mouseY, int state) {
      if (state != 0 || !this.keyBindingList.mouseReleased(mouseX, mouseY, state)) {
         super.mouseReleased(mouseX, mouseY, state);
      }

   }

   protected void keyTyped(char typedChar, int keyCode) throws IOException {
      if (this.buttonId != null) {
         if (keyCode == 1) {
            this.options.setOptionKeyBinding(this.buttonId, 0);
         } else if (keyCode != 0) {
            this.options.setOptionKeyBinding(this.buttonId, keyCode);
         } else if (typedChar > 0) {
            this.options.setOptionKeyBinding(this.buttonId, typedChar + 256);
         }

         this.buttonId = null;
         this.time = nC.getSystemTime();
         Bl.resetKeyBindingArrayAndHash();
      } else {
         super.keyTyped(typedChar, keyCode);
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.keyBindingList.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, this.screenTitle, this.width / 2, 8, 16777215);
      boolean flag = false;
      Bl[] var5 = this.options.keyBindings;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         Bl keybinding = var5[var7];
         if (keybinding.getKeyCode() != keybinding.getKeyCodeDefault()) {
            flag = true;
            break;
         }
      }

      this.buttonReset.enabled = flag;
      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   static {
      OPTIONS_ARR = new Bi[]{Bi.INVERT_MOUSE, Bi.SENSITIVITY, Bi.TOUCHSCREEN, Bi.AUTO_JUMP};
   }
}
