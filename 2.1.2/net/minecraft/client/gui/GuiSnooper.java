package net.minecraft.client.gui;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;

public class GuiSnooper extends GuiScreen {
   private final GuiScreen lastScreen;
   private final GameSettings game_settings_2;
   private final java.util.List<String> keys = Lists.newArrayList();
   private final java.util.List<String> values = Lists.newArrayList();
   private String title;
   private String[] desc;
   private List list;
   private GuiButton toggleButton;

   public GuiSnooper(GuiScreen p_i1061_1_, GameSettings p_i1061_2_) {
      this.lastScreen = p_i1061_1_;
      this.game_settings_2 = p_i1061_2_;
   }

   public void initGui() {
      this.title = I18n.format("options.snooper.title");
      String s = I18n.format("options.snooper.desc");
      java.util.List<String> list = Lists.newArrayList();
      Iterator var3 = this.fontRenderer.listFormattedStringToWidth(s, this.width - 30).iterator();

      while(var3.hasNext()) {
         String s1 = (String)var3.next();
         list.add(s1);
      }

      this.desc = (String[])((String[])list.toArray(new String[list.size()]));
      this.keys.clear();
      this.values.clear();
      this.toggleButton = this.addButton(new GuiButton(1, this.width / 2 - 152, this.height - 30, 150, 20, this.game_settings_2.getKeyBinding(GameSettings.Options.SNOOPER_ENABLED)));
      this.buttonList.add(new GuiButton(2, this.width / 2 + 2, this.height - 30, 150, 20, I18n.format("gui.done")));
      boolean flag = this.mc.getIntegratedServer() != null && this.mc.getIntegratedServer().getPlayerUsageSnooper() != null;
      Iterator var7 = (new TreeMap(this.mc.getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();

      Map.Entry entry1;
      while(var7.hasNext()) {
         entry1 = (Map.Entry)var7.next();
         this.keys.add((flag ? "C " : "") + (String)entry1.getKey());
         this.values.add(this.fontRenderer.trimStringToWidth((String)entry1.getValue(), this.width - 220));
      }

      if (flag) {
         var7 = (new TreeMap(this.mc.getIntegratedServer().getPlayerUsageSnooper().getCurrentStats())).entrySet().iterator();

         while(var7.hasNext()) {
            entry1 = (Map.Entry)var7.next();
            this.keys.add("S " + (String)entry1.getKey());
            this.values.add(this.fontRenderer.trimStringToWidth((String)entry1.getValue(), this.width - 220));
         }
      }

      this.list = new List();
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   protected void actionPerformed(GuiButton button) throws IOException {
      if (button.enabled) {
         if (button.id == 2) {
            this.game_settings_2.saveOptions();
            this.game_settings_2.saveOptions();
            this.mc.displayGuiScreen(this.lastScreen);
         }

         if (button.id == 1) {
            this.game_settings_2.setOptionValue(GameSettings.Options.SNOOPER_ENABLED, 1);
            this.toggleButton.displayString = this.game_settings_2.getKeyBinding(GameSettings.Options.SNOOPER_ENABLED);
         }
      }

   }

   public void drawScreen(int mouseX, int mouseY, float partialTicks) {
      this.drawDefaultBackground();
      this.list.drawScreen(mouseX, mouseY, partialTicks);
      this.drawCenteredString(this.fontRenderer, this.title, this.width / 2, 8, 16777215);
      int i = 22;
      String[] var5 = this.desc;
      int var6 = var5.length;

      for(int var7 = 0; var7 < var6; ++var7) {
         String s = var5[var7];
         this.drawCenteredString(this.fontRenderer, s, this.width / 2, i, 8421504);
         i += this.fontRenderer.FONT_HEIGHT;
      }

      super.drawScreen(mouseX, mouseY, partialTicks);
   }

   class List extends GuiSlot {
      public List() {
         super(GuiSnooper.this.mc, GuiSnooper.this.width, GuiSnooper.this.height, 80, GuiSnooper.this.height - 40, GuiSnooper.this.fontRenderer.FONT_HEIGHT + 1);
      }

      protected int getSize() {
         return GuiSnooper.this.keys.size();
      }

      protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
      }

      protected boolean isSelected(int slotIndex) {
         return false;
      }

      protected void drawBackground() {
      }

      protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
         GuiSnooper.this.fontRenderer.drawString((String)GuiSnooper.this.keys.get(slotIndex), 10, yPos, 16777215);
         GuiSnooper.this.fontRenderer.drawString((String)GuiSnooper.this.values.get(slotIndex), 230, yPos, 16777215);
      }

      protected int getScrollBarX() {
         return this.width - 10;
      }
   }
}
