package neo;

import com.google.common.base.MoreObjects;
import java.util.List;

public class mL {
   private final mS category;
   private final List<mQ> items;
   private final int selectedSlot;

   public mL(mS categoryIn, List<mQ> itemsIn, int selectedIndex) {
      this.category = categoryIn;
      this.items = itemsIn;
      this.selectedSlot = selectedIndex;
   }

   public mQ getObject(int index) {
      return index >= 0 && index < this.items.size() ? (mQ)MoreObjects.firstNonNull(this.items.get(index), mY.EMPTY_SLOT) : mY.EMPTY_SLOT;
   }

   public int getSelectedSlot() {
      return this.selectedSlot;
   }
}
