package neo;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.lwjgl.input.Mouse;

abstract class jj extends ly {
   protected int headerPressed;
   protected List<XS> statsHolder;
   protected Comparator<XS> statSorter;
   protected int sortColumn;
   protected int sortOrder;
   // $FF: synthetic field
   final jq this$0;

   protected jj(jq this$0, nC p_i47550_2_) {
      super(p_i47550_2_, this$0.width, this$0.height, 32, this$0.height - 64, 20);
      this.this$0 = this$0;
      this.headerPressed = -1;
      this.sortColumn = -1;
      this.setShowSelectionBox(false);
      this.setHasListHeader(true, 20);
   }

   protected void elementClicked(int slotIndex, boolean isDoubleClick, int mouseX, int mouseY) {
   }

   protected boolean isSelected(int slotIndex) {
      return false;
   }

   public int getListWidth() {
      return 375;
   }

   protected int getScrollBarX() {
      return this.width / 2 + 140;
   }

   protected void drawBackground() {
      this.this$0.drawDefaultBackground();
   }

   protected void drawListHeader(int insideLeft, int insideTop, yN tessellatorIn) {
      if (!Mouse.isButtonDown(0)) {
         this.headerPressed = -1;
      }

      if (this.headerPressed == 0) {
         jq.access$000(this.this$0, insideLeft + 115 - 18, insideTop + 1, 0, 0);
      } else {
         jq.access$000(this.this$0, insideLeft + 115 - 18, insideTop + 1, 0, 18);
      }

      if (this.headerPressed == 1) {
         jq.access$000(this.this$0, insideLeft + 165 - 18, insideTop + 1, 0, 0);
      } else {
         jq.access$000(this.this$0, insideLeft + 165 - 18, insideTop + 1, 0, 18);
      }

      if (this.headerPressed == 2) {
         jq.access$000(this.this$0, insideLeft + 215 - 18, insideTop + 1, 0, 0);
      } else {
         jq.access$000(this.this$0, insideLeft + 215 - 18, insideTop + 1, 0, 18);
      }

      if (this.headerPressed == 3) {
         jq.access$000(this.this$0, insideLeft + 265 - 18, insideTop + 1, 0, 0);
      } else {
         jq.access$000(this.this$0, insideLeft + 265 - 18, insideTop + 1, 0, 18);
      }

      if (this.headerPressed == 4) {
         jq.access$000(this.this$0, insideLeft + 315 - 18, insideTop + 1, 0, 0);
      } else {
         jq.access$000(this.this$0, insideLeft + 315 - 18, insideTop + 1, 0, 18);
      }

      if (this.sortColumn != -1) {
         int i = 79;
         int j = 18;
         if (this.sortColumn == 1) {
            i = 129;
         } else if (this.sortColumn == 2) {
            i = 179;
         } else if (this.sortColumn == 3) {
            i = 229;
         } else if (this.sortColumn == 4) {
            i = 279;
         }

         if (this.sortOrder == 1) {
            j = 36;
         }

         jq.access$000(this.this$0, insideLeft + i, insideTop + 1, j, 0);
      }

   }

   protected void clickedHeader(int p_148132_1_, int p_148132_2_) {
      this.headerPressed = -1;
      if (p_148132_1_ >= 79 && p_148132_1_ < 115) {
         this.headerPressed = 0;
      } else if (p_148132_1_ >= 129 && p_148132_1_ < 165) {
         this.headerPressed = 1;
      } else if (p_148132_1_ >= 179 && p_148132_1_ < 215) {
         this.headerPressed = 2;
      } else if (p_148132_1_ >= 229 && p_148132_1_ < 265) {
         this.headerPressed = 3;
      } else if (p_148132_1_ >= 279 && p_148132_1_ < 315) {
         this.headerPressed = 4;
      }

      if (this.headerPressed >= 0) {
         this.sortByColumn(this.headerPressed);
         this.mc.getSoundHandler().playSound(iN.getMasterRecord(NO.UI_BUTTON_CLICK, 1.0F));
      }

   }

   protected final int getSize() {
      return this.statsHolder.size();
   }

   protected final XS getSlotStat(int p_148211_1_) {
      return (XS)this.statsHolder.get(p_148211_1_);
   }

   protected abstract String getHeaderDescriptionId(int var1);

   protected void renderStat(XQ p_148209_1_, int p_148209_2_, int p_148209_3_, boolean p_148209_4_) {
      String s;
      if (p_148209_1_ != null) {
         s = p_148209_1_.format(jq.access$100(this.this$0).readStat(p_148209_1_));
         this.this$0.drawString(jq.access$200(this.this$0), s, p_148209_2_ - jq.access$300(this.this$0).getStringWidth(s), p_148209_3_ + 5, p_148209_4_ ? 16777215 : 9474192);
      } else {
         s = "-";
         this.this$0.drawString(jq.access$400(this.this$0), "-", p_148209_2_ - jq.access$500(this.this$0).getStringWidth("-"), p_148209_3_ + 5, p_148209_4_ ? 16777215 : 9474192);
      }

   }

   protected void renderDecorations(int mouseXIn, int mouseYIn) {
      if (mouseYIn >= this.top && mouseYIn <= this.bottom) {
         int i = this.getSlotIndexFromScreenCoords(mouseXIn, mouseYIn);
         int j = (this.width - this.getListWidth()) / 2;
         if (i >= 0) {
            if (mouseXIn < j + 40 || mouseXIn > j + 40 + 20) {
               return;
            }

            XS statcrafting = this.getSlotStat(i);
            this.renderMouseHoverToolTip(statcrafting, mouseXIn, mouseYIn);
         } else {
            String s;
            if (mouseXIn >= j + 115 - 18 && mouseXIn <= j + 115) {
               s = this.getHeaderDescriptionId(0);
            } else if (mouseXIn >= j + 165 - 18 && mouseXIn <= j + 165) {
               s = this.getHeaderDescriptionId(1);
            } else if (mouseXIn >= j + 215 - 18 && mouseXIn <= j + 215) {
               s = this.getHeaderDescriptionId(2);
            } else if (mouseXIn >= j + 265 - 18 && mouseXIn <= j + 265) {
               s = this.getHeaderDescriptionId(3);
            } else {
               if (mouseXIn < j + 315 - 18 || mouseXIn > j + 315) {
                  return;
               }

               s = this.getHeaderDescriptionId(4);
            }

            s = ("" + Ax.format(s)).trim();
            if (!s.isEmpty()) {
               int k = mouseXIn + 12;
               int l = mouseYIn - 12;
               int i1 = jq.access$600(this.this$0).getStringWidth(s);
               jq.access$700(this.this$0, k - 3, l - 3, k + i1 + 3, l + 8 + 3, -1073741824, -1073741824);
               jq.access$800(this.this$0).drawStringWithShadow(s, (float)k, (float)l, -1);
            }
         }
      }

   }

   protected void renderMouseHoverToolTip(XS p_148213_1_, int p_148213_2_, int p_148213_3_) {
      if (p_148213_1_ != null) {
         OL item = p_148213_1_.getItem();
         Qy itemstack = new Qy(item);
         String s = itemstack.getTranslationKey();
         String s1 = ("" + Ax.format(s + ".name")).trim();
         if (!s1.isEmpty()) {
            int i = p_148213_2_ + 12;
            int j = p_148213_3_ - 12;
            int k = jq.access$900(this.this$0).getStringWidth(s1);
            jq.access$1000(this.this$0, i - 3, j - 3, i + k + 3, j + 8 + 3, -1073741824, -1073741824);
            jq.access$1100(this.this$0).drawStringWithShadow(s1, (float)i, (float)j, -1);
         }
      }

   }

   protected void sortByColumn(int p_148212_1_) {
      if (p_148212_1_ != this.sortColumn) {
         this.sortColumn = p_148212_1_;
         this.sortOrder = -1;
      } else if (this.sortOrder == -1) {
         this.sortOrder = 1;
      } else {
         this.sortColumn = -1;
         this.sortOrder = 0;
      }

      Collections.sort(this.statsHolder, this.statSorter);
   }
}
