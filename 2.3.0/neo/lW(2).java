package neo;

import javax.annotation.Nullable;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;

class lW extends Slot {
   private final Slot slot;
   // $FF: synthetic field
   final lY this$0;

   public lW(lY this$0, Slot p_i46313_2_, int index) {
      super(p_i46313_2_.inventory, index, 0, 0);
      this.this$0 = this$0;
      this.slot = p_i46313_2_;
   }

   public Qy onTake(ME thePlayer, Qy stack) {
      this.slot.onTake(thePlayer, stack);
      return stack;
   }

   public boolean isItemValid(Qy stack) {
      return this.slot.isItemValid(stack);
   }

   public Qy getStack() {
      return this.slot.getStack();
   }

   public boolean getHasStack() {
      return this.slot.getHasStack();
   }

   public void putStack(Qy stack) {
      this.slot.putStack(stack);
   }

   public void onSlotChanged() {
      this.slot.onSlotChanged();
   }

   public int getSlotStackLimit() {
      return this.slot.getSlotStackLimit();
   }

   public int getItemStackLimit(Qy stack) {
      return this.slot.getItemStackLimit(stack);
   }

   @Nullable
   public String getSlotTexture() {
      return this.slot.getSlotTexture();
   }

   public Qy decrStackSize(int amount) {
      return this.slot.decrStackSize(amount);
   }

   public boolean isHere(IInventory inv, int slotIn) {
      return this.slot.isHere(inv, slotIn);
   }

   public boolean isEnabled() {
      return this.slot.isEnabled();
   }

   public boolean canTakeStack(ME playerIn) {
      return this.slot.canTakeStack(playerIn);
   }

   // $FF: synthetic method
   static Slot access$000(lW x0) {
      return x0.slot;
   }
}
