package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;

public class mI extends ky {
   private final kI owner;
   private final List<mH> serverListInternet = Lists.newArrayList();
   private final List<mE> serverListLan = Lists.newArrayList();
   private final kx lanScanEntry = new mF();
   private int selectedSlotIndex = -1;

   public mI(kI ownerIn, nC mcIn, int widthIn, int heightIn, int topIn, int bottomIn, int slotHeightIn) {
      super(mcIn, widthIn, heightIn, topIn, bottomIn, slotHeightIn);
      this.owner = ownerIn;
   }

   public kx getListEntry(int index) {
      if (index < this.serverListInternet.size()) {
         return (kx)this.serverListInternet.get(index);
      } else {
         index -= this.serverListInternet.size();
         if (index == 0) {
            return this.lanScanEntry;
         } else {
            --index;
            return (kx)this.serverListLan.get(index);
         }
      }
   }

   protected int getSize() {
      return this.serverListInternet.size() + 1 + this.serverListLan.size();
   }

   public void setSelectedSlotIndex(int selectedSlotIndexIn) {
      this.selectedSlotIndex = selectedSlotIndexIn;
   }

   protected boolean isSelected(int slotIndex) {
      return slotIndex == this.selectedSlotIndex;
   }

   public int getSelected() {
      return this.selectedSlotIndex;
   }

   public void updateOnlineServers(pg p_148195_1_) {
      this.serverListInternet.clear();

      for(int i = 0; i < p_148195_1_.countServers(); ++i) {
         this.serverListInternet.add(new mH(this.owner, p_148195_1_.getServerData(i)));
      }

   }

   public void updateNetworkServers(List<pq> p_148194_1_) {
      this.serverListLan.clear();
      Iterator var2 = p_148194_1_.iterator();

      while(var2.hasNext()) {
         pq lanserverinfo = (pq)var2.next();
         this.serverListLan.add(new mE(this.owner, lanserverinfo));
      }

   }

   protected int getScrollBarX() {
      return super.getScrollBarX() + 30;
   }

   public int getListWidth() {
      return super.getListWidth() + 85;
   }
}
