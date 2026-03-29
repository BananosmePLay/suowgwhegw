package neo;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.util.text.ITextComponent;

public class rC extends InventoryBasic implements bgb {
   private final String guiID;
   private final Map<Integer, Integer> dataValues = Maps.newHashMap();

   public rC(String id, ITextComponent title, int slotCount) {
      super(title, slotCount);
      this.guiID = id;
   }

   public int getField(int id) {
      return this.dataValues.containsKey(id) ? (Integer)this.dataValues.get(id) : 0;
   }

   public void setField(int id, int value) {
      this.dataValues.put(id, value);
   }

   public int getFieldCount() {
      return this.dataValues.size();
   }

   public boolean isLocked() {
      return false;
   }

   public void setLockCode(bge code) {
   }

   public bge getLockCode() {
      return bge.EMPTY_CODE;
   }

   public String getGuiID() {
      return this.guiID;
   }

   public Container createContainer(MJ playerInventory, ME playerIn) {
      throw new UnsupportedOperationException();
   }
}
