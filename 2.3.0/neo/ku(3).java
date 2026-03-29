package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

class ku extends ly {
   private final List<String> langCodeList;
   private final Map<String, AD> languageMap;
   // $FF: synthetic field
   final kv this$0;

   public ku(kv this$0, nC mcIn) {
      super(mcIn, this$0.width, this$0.height, 32, this$0.height - 65 + 4, 18);
      this.this$0 = this$0;
      this.langCodeList = Lists.newArrayList();
      this.languageMap = Maps.newHashMap();
      Iterator var3 = kv.access$000(this$0).getLanguages().iterator();

      while(var3.hasNext()) {
         AD language = (AD)var3.next();
         this.languageMap.put(language.getLanguageCode(), language);
         this.langCodeList.add(language.getLanguageCode());
      }

   }

   protected int getSize() {
      return this.langCodeList.size();
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
      AD language = (AD)this.languageMap.get(this.langCodeList.get(slotIndex));
      kv.access$000(this.this$0).setCurrentLanguage(language);
      kv.access$100(this.this$0).language = language.getLanguageCode();
      this.mc.refreshResources();
      this.this$0.fontRenderer.setUnicodeFlag(kv.access$000(this.this$0).isCurrentLocaleUnicode() || kv.access$100(this.this$0).forceUnicodeFont);
      this.this$0.fontRenderer.setBidiFlag(kv.access$000(this.this$0).isCurrentLanguageBidirectional());
      kv.access$200(this.this$0).displayString = Ax.format("gui.done");
      kv.access$300(this.this$0).displayString = kv.access$100(this.this$0).getKeyBinding(Bi.FORCE_UNICODE_FONT);
      kv.access$100(this.this$0).saveOptions();
   }

   protected boolean isSelected(int slotIndex) {
      return ((String)this.langCodeList.get(slotIndex)).equals(kv.access$000(this.this$0).getCurrentLanguage().getLanguageCode());
   }

   protected int getContentHeight() {
      return this.getSize() * 18;
   }

   protected void drawBackground() {
      this.this$0.drawDefaultBackground();
   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      this.this$0.fontRenderer.setBidiFlag(true);
      this.this$0.drawCenteredString(this.this$0.fontRenderer, ((AD)this.languageMap.get(this.langCodeList.get(slotIndex))).toString(), this.width / 2, yPos + 1, 16777215);
      this.this$0.fontRenderer.setBidiFlag(kv.access$000(this.this$0).getCurrentLanguage().isBidirectional());
   }
}
