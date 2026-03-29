package neo;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class lA extends lg {
   private final lg lastScreen;
   private final Bj game_settings_2;
   private final java.util.List<String> keys = Lists.newArrayList();
   private final java.util.List<String> values = Lists.newArrayList();
   private String title;
   private String[] desc;
   private lz list;
   private jK toggleButton;

   public lA(lg p_i1061_1_, Bj p_i1061_2_) {
      this.lastScreen = p_i1061_1_;
      this.game_settings_2 = p_i1061_2_;
   }

   public void initGui() {
      this.title = Ax.format("options.snooper.title");
      String s = Ax.format("options.snooper.desc");
      java.util.List<String> list = Lists.newArrayList();
      Iterator var3 = this.fontRenderer.listFormattedStringToWidth(s, this.width - 30).iterator();

      while(var3.hasNext()) {
         String s1 = (String)var3.next();
         list.add(s1);
      }

      this.desc = (String[])((String[])list.toArray(new String[list.size()]));
      this.keys.clear();
      this.values.clear();
      this.toggleButton = this.addButton(new jK(1, this.width / 2 - 152, this.height - 30, 150, 20, this.game_settings_2.getKeyBinding(Bi.SNOOPER_ENABLED)));
      this.buttonList.add(new jK(2, this.width / 2 + 2, this.height - 30, 150, 20, Ax.format("gui.done")));
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

      this.list = new lz(this);
   }

   public void handleMouseInput() throws IOException {
      super.handleMouseInput();
      this.list.handleMouseInput();
   }

   protected void actionPerformed(jK button) throws IOException {
      if (button.enabled) {
         if (button.id == 2) {
            this.game_settings_2.saveOptions();
            this.game_settings_2.saveOptions();
            this.mc.displayGuiScreen(this.lastScreen);
         }

         if (button.id == 1) {
            this.game_settings_2.setOptionValue(Bi.SNOOPER_ENABLED, 1);
            this.toggleButton.displayString = this.game_settings_2.getKeyBinding(Bi.SNOOPER_ENABLED);
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

   // $FF: synthetic method
   static java.util.List access$000(lA x0) {
      return x0.keys;
   }

   // $FF: synthetic method
   static java.util.List access$100(lA x0) {
      return x0.values;
   }
}
