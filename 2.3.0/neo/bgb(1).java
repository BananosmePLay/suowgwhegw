package neo;

import net.minecraft.inventory.IInventory;

public interface bgb extends IInventory, bga {
   boolean isLocked();

   void setLockCode(bge var1);

   bge getLockCode();
}
