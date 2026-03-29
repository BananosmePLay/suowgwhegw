package neo;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class mY {
   private static final mQ CLOSE_ITEM = new mW();
   private static final mQ SCROLL_LEFT = new mX(-1, true);
   private static final mQ SCROLL_RIGHT_ENABLED = new mX(1, true);
   private static final mQ SCROLL_RIGHT_DISABLED = new mX(1, false);
   public static final mQ EMPTY_SLOT = new mQ() {
      public void selectItem(mY menu) {
      }

      public ITextComponent getSpectatorName() {
         return new TextComponentString("");
      }

      public void renderIcon(float brightness, int alpha) {
      }

      public boolean isEnabled() {
         return false;
      }
   };
   private final mR listener;
   private final List<mL> previousCategories = Lists.newArrayList();
   private mS category = new mJ();
   private int selectedSlot = -1;
   private int page;

   public mY(mR menu) {
      this.listener = menu;
   }

   public mQ getItem(int index) {
      int i = index + this.page * 6;
      if (this.page > 0 && index == 0) {
         return SCROLL_LEFT;
      } else if (index == 7) {
         return i < this.category.getItems().size() ? SCROLL_RIGHT_ENABLED : SCROLL_RIGHT_DISABLED;
      } else if (index == 8) {
         return CLOSE_ITEM;
      } else {
         return i >= 0 && i < this.category.getItems().size() ? (mQ)MoreObjects.firstNonNull(this.category.getItems().get(i), EMPTY_SLOT) : EMPTY_SLOT;
      }
   }

   public List<mQ> getItems() {
      List<mQ> list = Lists.newArrayList();

      for(int i = 0; i <= 8; ++i) {
         list.add(this.getItem(i));
      }

      return list;
   }

   public mQ getSelectedItem() {
      return this.getItem(this.selectedSlot);
   }

   public mS getSelectedCategory() {
      return this.category;
   }

   public void selectSlot(int slotIn) {
      mQ ispectatormenuobject = this.getItem(slotIn);
      if (ispectatormenuobject != EMPTY_SLOT) {
         if (this.selectedSlot == slotIn && ispectatormenuobject.isEnabled()) {
            ispectatormenuobject.selectItem(this);
         } else {
            this.selectedSlot = slotIn;
         }
      }

   }

   public void exit() {
      this.listener.onSpectatorMenuClosed(this);
   }

   public int getSelectedSlot() {
      return this.selectedSlot;
   }

   public void selectCategory(mS menuView) {
      this.previousCategories.add(this.getCurrentPage());
      this.category = menuView;
      this.selectedSlot = -1;
      this.page = 0;
   }

   public mL getCurrentPage() {
      return new mL(this.category, this.getItems(), this.selectedSlot);
   }

   // $FF: synthetic method
   static int access$102(mY x0, int x1) {
      return x0.page = x1;
   }

   // $FF: synthetic method
   static int access$100(mY x0) {
      return x0.page;
   }
}
