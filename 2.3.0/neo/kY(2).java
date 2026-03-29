package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.util.IntHashMap;

public class kY extends ky {
   private final List<kT> entries = Lists.newArrayList();
   private final IntHashMap<jI> componentMap = new IntHashMap();
   private final List<lE> editBoxes = Lists.newArrayList();
   private final kV[][] pages;
   private int page;
   private final kW responder;
   private jI focusedControl;

   public kY(nC mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn, kW p_i45536_7_, kV[]... p_i45536_8_) {
      super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
      this.responder = p_i45536_7_;
      this.pages = p_i45536_8_;
      this.centerListVertically = false;
      this.populateComponents();
      this.populateEntries();
   }

   private void populateComponents() {
      kV[][] var1 = this.pages;
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         kV[] aguipagebuttonlist$guilistentry = var1[var3];

         for(int i = 0; i < aguipagebuttonlist$guilistentry.length; i += 2) {
            kV guipagebuttonlist$guilistentry = aguipagebuttonlist$guilistentry[i];
            kV guipagebuttonlist$guilistentry1 = i < aguipagebuttonlist$guilistentry.length - 1 ? aguipagebuttonlist$guilistentry[i + 1] : null;
            jI gui = this.createEntry(guipagebuttonlist$guilistentry, 0, guipagebuttonlist$guilistentry1 == null);
            jI gui1 = this.createEntry(guipagebuttonlist$guilistentry1, 160, guipagebuttonlist$guilistentry == null);
            kT guipagebuttonlist$guientry = new kT(gui, gui1);
            this.entries.add(guipagebuttonlist$guientry);
            if (guipagebuttonlist$guilistentry != null && gui != null) {
               this.componentMap.addKey(guipagebuttonlist$guilistentry.getId(), gui);
               if (gui instanceof lE) {
                  this.editBoxes.add((lE)gui);
               }
            }

            if (guipagebuttonlist$guilistentry1 != null && gui1 != null) {
               this.componentMap.addKey(guipagebuttonlist$guilistentry1.getId(), gui1);
               if (gui1 instanceof lE) {
                  this.editBoxes.add((lE)gui1);
               }
            }
         }
      }

   }

   private void populateEntries() {
      this.entries.clear();

      for(int i = 0; i < this.pages[this.page].length; i += 2) {
         kV guipagebuttonlist$guilistentry = this.pages[this.page][i];
         kV guipagebuttonlist$guilistentry1 = i < this.pages[this.page].length - 1 ? this.pages[this.page][i + 1] : null;
         jI gui = (jI)this.componentMap.lookup(guipagebuttonlist$guilistentry.getId());
         jI gui1 = guipagebuttonlist$guilistentry1 != null ? (jI)this.componentMap.lookup(guipagebuttonlist$guilistentry1.getId()) : null;
         kT guipagebuttonlist$guientry = new kT(gui, gui1);
         this.entries.add(guipagebuttonlist$guientry);
      }

   }

   public void setPage(int p_181156_1_) {
      if (p_181156_1_ != this.page) {
         int i = this.page;
         this.page = p_181156_1_;
         this.populateEntries();
         this.markVisibility(i, p_181156_1_);
         this.amountScrolled = 0.0F;
      }

   }

   public int getPage() {
      return this.page;
   }

   public int getPageCount() {
      return this.pages.length;
   }

   public jI getFocusedControl() {
      return this.focusedControl;
   }

   public void previousPage() {
      if (this.page > 0) {
         this.setPage(this.page - 1);
      }

   }

   public void nextPage() {
      if (this.page < this.pages.length - 1) {
         this.setPage(this.page + 1);
      }

   }

   public jI getComponent(int p_178061_1_) {
      return (jI)this.componentMap.lookup(p_178061_1_);
   }

   private void markVisibility(int p_178060_1_, int p_178060_2_) {
      kV[] var3 = this.pages[p_178060_1_];
      int var4 = var3.length;

      int var5;
      kV guipagebuttonlist$guilistentry1;
      for(var5 = 0; var5 < var4; ++var5) {
         guipagebuttonlist$guilistentry1 = var3[var5];
         if (guipagebuttonlist$guilistentry1 != null) {
            this.setComponentVisibility((jI)this.componentMap.lookup(guipagebuttonlist$guilistentry1.getId()), false);
         }
      }

      var3 = this.pages[p_178060_2_];
      var4 = var3.length;

      for(var5 = 0; var5 < var4; ++var5) {
         guipagebuttonlist$guilistentry1 = var3[var5];
         if (guipagebuttonlist$guilistentry1 != null) {
            this.setComponentVisibility((jI)this.componentMap.lookup(guipagebuttonlist$guilistentry1.getId()), true);
         }
      }

   }

   private void setComponentVisibility(jI p_178066_1_, boolean p_178066_2_) {
      if (p_178066_1_ instanceof jK) {
         ((jK)p_178066_1_).visible = p_178066_2_;
      } else if (p_178066_1_ instanceof lE) {
         ((lE)p_178066_1_).setVisible(p_178066_2_);
      } else if (p_178066_1_ instanceof kt) {
         ((kt)p_178066_1_).visible = p_178066_2_;
      }

   }

   @Nullable
   private jI createEntry(@Nullable kV p_178058_1_, int p_178058_2_, boolean p_178058_3_) {
      if (p_178058_1_ instanceof kX) {
         return this.createSlider(this.width / 2 - 155 + p_178058_2_, 0, (kX)p_178058_1_);
      } else if (p_178058_1_ instanceof kS) {
         return this.createButton(this.width / 2 - 155 + p_178058_2_, 0, (kS)p_178058_1_);
      } else if (p_178058_1_ instanceof kR) {
         return this.createTextField(this.width / 2 - 155 + p_178058_2_, 0, (kR)p_178058_1_);
      } else {
         return p_178058_1_ instanceof kU ? this.createLabel(this.width / 2 - 155 + p_178058_2_, 0, (kU)p_178058_1_, p_178058_3_) : null;
      }
   }

   public void setActive(boolean p_181155_1_) {
      Iterator var2 = this.entries.iterator();

      while(var2.hasNext()) {
         kT guipagebuttonlist$guientry = (kT)var2.next();
         if (kT.access$000(guipagebuttonlist$guientry) instanceof jK) {
            ((jK)kT.access$000(guipagebuttonlist$guientry)).enabled = p_181155_1_;
         }

         if (kT.access$100(guipagebuttonlist$guientry) instanceof jK) {
            ((jK)kT.access$100(guipagebuttonlist$guientry)).enabled = p_181155_1_;
         }
      }

   }

   public boolean mouseClicked(int mouseX, int mouseY, int mouseEvent) {
      boolean flag = super.mouseClicked(mouseX, mouseY, mouseEvent);
      int i = this.getSlotIndexFromScreenCoords(mouseX, mouseY);
      if (i >= 0) {
         kT guipagebuttonlist$guientry = this.getListEntry(i);
         if (this.focusedControl != kT.access$200(guipagebuttonlist$guientry) && this.focusedControl != null && this.focusedControl instanceof lE) {
            ((lE)this.focusedControl).setFocused(false);
         }

         this.focusedControl = kT.access$200(guipagebuttonlist$guientry);
      }

      return flag;
   }

   private lx createSlider(int p_178067_1_, int p_178067_2_, kX p_178067_3_) {
      lx guislider = new lx(this.responder, p_178067_3_.getId(), p_178067_1_, p_178067_2_, p_178067_3_.getCaption(), p_178067_3_.getMinValue(), p_178067_3_.getMaxValue(), p_178067_3_.getInitalValue(), p_178067_3_.getFormatter());
      guislider.visible = p_178067_3_.shouldStartVisible();
      return guislider;
   }

   private kw createButton(int p_178065_1_, int p_178065_2_, kS p_178065_3_) {
      kw guilistbutton = new kw(this.responder, p_178065_3_.getId(), p_178065_1_, p_178065_2_, p_178065_3_.getCaption(), p_178065_3_.getInitialValue());
      guilistbutton.visible = p_178065_3_.shouldStartVisible();
      return guilistbutton;
   }

   private lE createTextField(int p_178068_1_, int p_178068_2_, kR p_178068_3_) {
      lE guitextfield = new lE(p_178068_3_.getId(), this.mc.fontRenderer, p_178068_1_, p_178068_2_, 150, 20);
      guitextfield.setText(p_178068_3_.getCaption());
      guitextfield.setGuiResponder(this.responder);
      guitextfield.setVisible(p_178068_3_.shouldStartVisible());
      guitextfield.setValidator(p_178068_3_.getFilter());
      return guitextfield;
   }

   private kt createLabel(int p_178063_1_, int p_178063_2_, kU p_178063_3_, boolean p_178063_4_) {
      kt guilabel;
      if (p_178063_4_) {
         guilabel = new kt(this.mc.fontRenderer, p_178063_3_.getId(), p_178063_1_, p_178063_2_, this.width - p_178063_1_ * 2, 20, -1);
      } else {
         guilabel = new kt(this.mc.fontRenderer, p_178063_3_.getId(), p_178063_1_, p_178063_2_, 150, 20, -1);
      }

      guilabel.visible = p_178063_3_.shouldStartVisible();
      guilabel.addLine(p_178063_3_.getCaption());
      guilabel.setCentered();
      return guilabel;
   }

   public void onKeyPressed(char p_178062_1_, int p_178062_2_) {
      if (this.focusedControl instanceof lE) {
         lE guitextfield = (lE)this.focusedControl;
         int i1;
         if (!lg.isKeyComboCtrlV(p_178062_2_)) {
            if (p_178062_2_ == 15) {
               guitextfield.setFocused(false);
               int k = this.editBoxes.indexOf(this.focusedControl);
               if (lg.isShiftKeyDown()) {
                  if (k == 0) {
                     k = this.editBoxes.size() - 1;
                  } else {
                     --k;
                  }
               } else if (k == this.editBoxes.size() - 1) {
                  k = 0;
               } else {
                  ++k;
               }

               this.focusedControl = (jI)this.editBoxes.get(k);
               guitextfield = (lE)this.focusedControl;
               guitextfield.setFocused(true);
               int l = guitextfield.y + this.slotHeight;
               i1 = guitextfield.y;
               if (l > this.bottom) {
                  this.amountScrolled += (float)(l - this.bottom);
               } else if (i1 < this.top) {
                  this.amountScrolled = (float)i1;
               }
            } else {
               guitextfield.textboxKeyTyped(p_178062_1_, p_178062_2_);
            }
         } else {
            String s = lg.getClipboardString();
            String[] astring = s.split(";");
            i1 = this.editBoxes.indexOf(this.focusedControl);
            int j = i1;
            String[] var8 = astring;
            int var9 = astring.length;

            for(int var10 = 0; var10 < var9; ++var10) {
               String s1 = var8[var10];
               lE guitextfield1 = (lE)this.editBoxes.get(j);
               guitextfield1.setText(s1);
               guitextfield1.setResponderEntryValue(guitextfield1.getId(), s1);
               if (j == this.editBoxes.size() - 1) {
                  j = 0;
               } else {
                  ++j;
               }

               if (j == i1) {
                  break;
               }
            }
         }
      }

   }

   public kT getListEntry(int index) {
      return (kT)this.entries.get(index);
   }

   public int getSize() {
      return this.entries.size();
   }

   public int getListWidth() {
      return 400;
   }

   protected int getScrollBarX() {
      return super.getScrollBarX() + 32;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public kx getListEntry(int var1) {
      return this.getListEntry(var1);
   }
}
