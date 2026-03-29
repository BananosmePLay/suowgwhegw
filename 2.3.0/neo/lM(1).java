package neo;

import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.NonNullList;

public class lM implements IContainerListener {
   private final nC mc;

   public lM(nC mc) {
      this.mc = mc;
   }

   public void sendAllContents(Container containerToSend, NonNullList<Qy> itemsList) {
   }

   public void sendSlotContents(Container containerToSend, int slotInd, Qy stack) {
      this.mc.playerController.sendSlotPacket(stack, slotInd);
   }

   public void sendWindowProperty(Container containerIn, int varToUpdate, int newValue) {
   }

   public void sendAllWindowProperties(Container containerIn, IInventory inventory) {
   }
}
