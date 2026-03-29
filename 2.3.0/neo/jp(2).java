package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

class jp extends ly {
   private final List<Iq> mobs;
   // $FF: synthetic field
   final jq this$0;

   public jp(jq this$0, nC mcIn) {
      super(mcIn, this$0.width, this$0.height, 32, this$0.height - 64, jq.access$1600(this$0).FONT_HEIGHT * 4);
      this.this$0 = this$0;
      this.mobs = Lists.newArrayList();
      this.setShowSelectionBox(false);
      Iterator var3 = Ir.ENTITY_EGGS.values().iterator();

      while(true) {
         Iq entitylist$entityegginfo;
         do {
            if (!var3.hasNext()) {
               return;
            }

            entitylist$entityegginfo = (Iq)var3.next();
         } while(jq.access$100(this$0).readStat(entitylist$entityegginfo.killEntityStat) <= 0 && jq.access$100(this$0).readStat(entitylist$entityegginfo.entityKilledByStat) <= 0);

         this.mobs.add(entitylist$entityegginfo);
      }
   }

   protected int getSize() {
      return this.mobs.size();
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
   }

   protected boolean isSelected(int slotIndex) {
      return false;
   }

   protected int getContentHeight() {
      return this.getSize() * jq.access$1700(this.this$0).FONT_HEIGHT * 4;
   }

   protected void drawBackground() {
      this.this$0.drawDefaultBackground();
   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      Iq entitylist$entityegginfo = (Iq)this.mobs.get(slotIndex);
      String s = Ax.format("entity." + Ir.getTranslationName(entitylist$entityegginfo.spawnedID) + ".name");
      int i = jq.access$100(this.this$0).readStat(entitylist$entityegginfo.killEntityStat);
      int j = jq.access$100(this.this$0).readStat(entitylist$entityegginfo.entityKilledByStat);
      String s1 = Ax.format("stat.entityKills", i, s);
      String s2 = Ax.format("stat.entityKilledBy", s, j);
      if (i == 0) {
         s1 = Ax.format("stat.entityKills.none", s);
      }

      if (j == 0) {
         s2 = Ax.format("stat.entityKilledBy.none", s);
      }

      this.this$0.drawString(jq.access$1800(this.this$0), s, xPos + 2 - 10, yPos + 1, 16777215);
      this.this$0.drawString(jq.access$1900(this.this$0), s1, xPos + 2, yPos + 1 + jq.access$2000(this.this$0).FONT_HEIGHT, i == 0 ? 6316128 : 9474192);
      this.this$0.drawString(jq.access$2100(this.this$0), s2, xPos + 2, yPos + 1 + jq.access$2200(this.this$0).FONT_HEIGHT * 2, j == 0 ? 6316128 : 9474192);
   }
}
