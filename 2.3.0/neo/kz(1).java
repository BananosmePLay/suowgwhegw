package neo;

import com.google.common.collect.Lists;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class kz extends ky {
   private static final Logger LOGGER = LogManager.getLogger();
   private final lJ worldSelection;
   private final List<kC> entries = Lists.newArrayList();
   private int selectedIdx = -1;

   public kz(lJ p_i46590_1_, nC clientIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
      super(clientIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
      this.worldSelection = p_i46590_1_;
      this.refreshList();
   }

   public void refreshList() {
      bgl isaveformat = this.mc.getSaveLoader();

      List list;
      try {
         list = isaveformat.getSaveList();
      } catch (iy var5) {
         iy anvilconverterexception = var5;
         LOGGER.error("Couldn't load level list", anvilconverterexception);
         this.mc.displayGuiScreen(new kg(Ax.format("selectWorld.unable_to_load"), anvilconverterexception.getMessage()));
         return;
      }

      Collections.sort(list);
      Iterator var6 = list.iterator();

      while(var6.hasNext()) {
         bib worldsummary = (bib)var6.next();
         this.entries.add(new kC(this, worldsummary, this.mc.getSaveLoader()));
      }

   }

   public kC getListEntry(int index) {
      return (kC)this.entries.get(index);
   }

   protected int getSize() {
      return this.entries.size();
   }

   protected int getScrollBarX() {
      return super.getScrollBarX() + 20;
   }

   public int getListWidth() {
      return super.getListWidth() + 50;
   }

   public void selectWorld(int idx) {
      this.selectedIdx = idx;
      this.worldSelection.selectWorld(this.getSelectedWorld());
   }

   protected boolean isSelected(int slotIndex) {
      return slotIndex == this.selectedIdx;
   }

   @Nullable
   public kC getSelectedWorld() {
      return this.selectedIdx >= 0 && this.selectedIdx < this.getSize() ? this.getListEntry(this.selectedIdx) : null;
   }

   public lJ getGuiWorldSelection() {
      return this.worldSelection;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public kx getListEntry(int var1) {
      return this.getListEntry(var1);
   }
}
