package neo;

import java.util.List;
import net.minecraft.util.text.TextFormatting;

public abstract class le extends ky {
   protected final nC mc;
   protected final List<AN> resourcePackEntries;

   public le(nC mcIn, int p_i45055_2_, int p_i45055_3_, List<AN> p_i45055_4_) {
      super(mcIn, p_i45055_2_, p_i45055_3_, 32, p_i45055_3_ - 55 + 4, 36);
      this.mc = mcIn;
      this.resourcePackEntries = p_i45055_4_;
      this.centerListVertically = false;
      this.setHasListHeader(true, (int)((float)mcIn.fontRenderer.FONT_HEIGHT * 1.5F));
   }

   protected void drawListHeader(int insideLeft, int insideTop, yN tessellatorIn) {
      String s = TextFormatting.UNDERLINE + "" + TextFormatting.BOLD + this.getListHeader();
      this.mc.fontRenderer.drawString(s, insideLeft + this.width / 2 - this.mc.fontRenderer.getStringWidth(s) / 2, Math.min(this.top + 3, insideTop), 16777215);
   }

   protected abstract String getListHeader();

   public List<AN> getList() {
      return this.resourcePackEntries;
   }

   protected int getSize() {
      return this.getList().size();
   }

   public AN getListEntry(int index) {
      return (AN)this.getList().get(index);
   }

   public int getListWidth() {
      return this.width;
   }

   protected int getScrollBarX() {
      return this.right - 6;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public kx getListEntry(int var1) {
      return this.getListEntry(var1);
   }
}
