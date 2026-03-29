package neo;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.NonNullList;

public class Vi implements Sz<Ts> {
   private int windowId;
   private List<Qy> itemStacks;

   public Vi() {
   }

   public Vi(int p_i47317_1_, NonNullList<Qy> p_i47317_2_) {
      this.windowId = p_i47317_1_;
      this.itemStacks = NonNullList.withSize(p_i47317_2_.size(), Qy.EMPTY);

      for(int i = 0; i < this.itemStacks.size(); ++i) {
         Qy itemstack = (Qy)p_i47317_2_.get(i);
         this.itemStacks.set(i, itemstack.copy());
      }

   }

   public void readPacketData(SA buf) throws IOException {
      this.windowId = buf.readUnsignedByte();
      int i = buf.readShort();
      this.itemStacks = NonNullList.withSize(i, Qy.EMPTY);

      for(int j = 0; j < i; ++j) {
         this.itemStacks.set(j, buf.readItemStack());
      }

   }

   public void writePacketData(SA buf) throws IOException {
      buf.writeByte(this.windowId);
      buf.writeShort(this.itemStacks.size());
      Iterator var2 = this.itemStacks.iterator();

      while(var2.hasNext()) {
         Qy itemstack = (Qy)var2.next();
         buf.writeItemStack(itemstack);
      }

   }

   public void processPacket(Ts handler) {
      handler.handleWindowItems(this);
   }

   public int getWindowId() {
      return this.windowId;
   }

   public List<Qy> getItemStacks() {
      return this.itemStacks;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public void processPacket(RH var1) {
      this.processPacket((Ts)var1);
   }
}
