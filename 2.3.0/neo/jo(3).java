package neo;

import com.google.common.collect.Lists;
import java.util.Comparator;
import java.util.Iterator;

class jo extends jj {
   // $FF: synthetic field
   final jq this$0;

   public jo(final jq this$0, nC mcIn) {
      super(this$0, mcIn);
      this.this$0 = this$0;
      this.statsHolder = Lists.newArrayList();
      Iterator var3 = XV.USE_ITEM_STATS.iterator();

      while(var3.hasNext()) {
         XS statcrafting = (XS)var3.next();
         boolean flag = false;
         OL item = statcrafting.getItem();
         if (jq.access$100(this$0).readStat(statcrafting) > 0) {
            flag = true;
         } else if (XV.getObjectBreakStats(item) != null && jq.access$100(this$0).readStat(XV.getObjectBreakStats(item)) > 0) {
            flag = true;
         } else if (XV.getCraftStats(item) != null && jq.access$100(this$0).readStat(XV.getCraftStats(item)) > 0) {
            flag = true;
         } else if (XV.getObjectsPickedUpStats(item) != null && jq.access$100(this$0).readStat(XV.getObjectsPickedUpStats(item)) > 0) {
            flag = true;
         } else if (XV.getDroppedObjectStats(item) != null && jq.access$100(this$0).readStat(XV.getDroppedObjectStats(item)) > 0) {
            flag = true;
         }

         if (flag) {
            this.statsHolder.add(statcrafting);
         }
      }

      this.statSorter = new Comparator<XS>() {
         public int compare(XS p_compare_1_, XS p_compare_2_) {
            OL item1 = p_compare_1_.getItem();
            OL item2 = p_compare_2_.getItem();
            int i = OL.getIdFromItem(item1);
            int j = OL.getIdFromItem(item2);
            XQ statbase = null;
            XQ statbase1 = null;
            if (jo.this.sortColumn == 0) {
               statbase = XV.getObjectBreakStats(item1);
               statbase1 = XV.getObjectBreakStats(item2);
            } else if (jo.this.sortColumn == 1) {
               statbase = XV.getCraftStats(item1);
               statbase1 = XV.getCraftStats(item2);
            } else if (jo.this.sortColumn == 2) {
               statbase = XV.getObjectUseStats(item1);
               statbase1 = XV.getObjectUseStats(item2);
            } else if (jo.this.sortColumn == 3) {
               statbase = XV.getObjectsPickedUpStats(item1);
               statbase1 = XV.getObjectsPickedUpStats(item2);
            } else if (jo.this.sortColumn == 4) {
               statbase = XV.getDroppedObjectStats(item1);
               statbase1 = XV.getDroppedObjectStats(item2);
            }

            if (statbase != null || statbase1 != null) {
               if (statbase == null) {
                  return 1;
               }

               if (statbase1 == null) {
                  return -1;
               }

               int k = jq.access$100(jo.this.this$0).readStat(statbase);
               int l = jq.access$100(jo.this.this$0).readStat(statbase1);
               if (k != l) {
                  return (k - l) * jo.this.sortOrder;
               }
            }

            return i - j;
         }

         // $FF: synthetic method
         // $FF: bridge method
         public int compare(Object var1, Object var2) {
            return this.compare((XS)var1, (XS)var2);
         }
      };
   }

   protected void drawListHeader(int insideLeft, int insideTop, yN tessellatorIn) {
      super.drawListHeader(insideLeft, insideTop, tessellatorIn);
      if (this.headerPressed == 0) {
         jq.access$000(this.this$0, insideLeft + 115 - 18 + 1, insideTop + 1 + 1, 72, 18);
      } else {
         jq.access$000(this.this$0, insideLeft + 115 - 18, insideTop + 1, 72, 18);
      }

      if (this.headerPressed == 1) {
         jq.access$000(this.this$0, insideLeft + 165 - 18 + 1, insideTop + 1 + 1, 18, 18);
      } else {
         jq.access$000(this.this$0, insideLeft + 165 - 18, insideTop + 1, 18, 18);
      }

      if (this.headerPressed == 2) {
         jq.access$000(this.this$0, insideLeft + 215 - 18 + 1, insideTop + 1 + 1, 36, 18);
      } else {
         jq.access$000(this.this$0, insideLeft + 215 - 18, insideTop + 1, 36, 18);
      }

      if (this.headerPressed == 3) {
         jq.access$000(this.this$0, insideLeft + 265 - 18 + 1, insideTop + 1 + 1, 90, 18);
      } else {
         jq.access$000(this.this$0, insideLeft + 265 - 18, insideTop + 1, 90, 18);
      }

      if (this.headerPressed == 4) {
         jq.access$000(this.this$0, insideLeft + 315 - 18 + 1, insideTop + 1 + 1, 108, 18);
      } else {
         jq.access$000(this.this$0, insideLeft + 315 - 18, insideTop + 1, 108, 18);
      }

   }

   protected void drawSlot(int slotIndex, int xPos, int yPos, int heightIn, int mouseXIn, int mouseYIn, float partialTicks) {
      XS statcrafting = this.getSlotStat(slotIndex);
      OL item = statcrafting.getItem();
      jq.access$1200(this.this$0, xPos + 40, yPos, item);
      this.renderStat(XV.getObjectBreakStats(item), xPos + 115, yPos, slotIndex % 2 == 0);
      this.renderStat(XV.getCraftStats(item), xPos + 165, yPos, slotIndex % 2 == 0);
      this.renderStat(statcrafting, xPos + 215, yPos, slotIndex % 2 == 0);
      this.renderStat(XV.getObjectsPickedUpStats(item), xPos + 265, yPos, slotIndex % 2 == 0);
      this.renderStat(XV.getDroppedObjectStats(item), xPos + 315, yPos, slotIndex % 2 == 0);
   }

   protected String getHeaderDescriptionId(int p_148210_1_) {
      if (p_148210_1_ == 1) {
         return "stat.crafted";
      } else if (p_148210_1_ == 2) {
         return "stat.used";
      } else if (p_148210_1_ == 3) {
         return "stat.pickup";
      } else {
         return p_148210_1_ == 4 ? "stat.dropped" : "stat.depleted";
      }
   }
}
