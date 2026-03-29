package net.minecraft.inventory;

import java.util.Iterator;
import javax.annotation.Nullable;
import neo.ME;
import neo.NT;
import neo.Qy;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;

public class InventoryCraftResult implements IInventory {
   private final NonNullList<Qy> stackResult;
   private NT recipeUsed;

   public InventoryCraftResult() {
      this.stackResult = NonNullList.withSize(1, Qy.EMPTY);
   }

   public int getSizeInventory() {
      return 1;
   }

   public boolean isEmpty() {
      Iterator var1 = this.stackResult.iterator();

      Qy itemstack;
      do {
         if (!var1.hasNext()) {
            return true;
         }

         itemstack = (Qy)var1.next();
      } while(itemstack.isEmpty());

      return false;
   }

   public Qy getStackInSlot(int index) {
      return (Qy)this.stackResult.get(0);
   }

   public String getName() {
      return "Result";
   }

   public boolean hasCustomName() {
      return false;
   }

   public ITextComponent getDisplayName() {
      return (ITextComponent)(this.hasCustomName() ? new TextComponentString(this.getName()) : new TextComponentTranslation(this.getName(), new Object[0]));
   }

   public Qy decrStackSize(int index, int count) {
      return ItemStackHelper.getAndRemove(this.stackResult, 0);
   }

   public Qy removeStackFromSlot(int index) {
      return ItemStackHelper.getAndRemove(this.stackResult, 0);
   }

   public void setInventorySlotContents(int index, Qy stack) {
      this.stackResult.set(0, stack);
   }

   public int getInventoryStackLimit() {
      return 64;
   }

   public void markDirty() {
   }

   public boolean isUsableByPlayer(ME player) {
      return true;
   }

   public void openInventory(ME player) {
   }

   public void closeInventory(ME player) {
   }

   public boolean isItemValidForSlot(int index, Qy stack) {
      return true;
   }

   public int getField(int id) {
      return 0;
   }

   public void setField(int id, int value) {
   }

   public int getFieldCount() {
      return 0;
   }

   public void clear() {
      this.stackResult.clear();
   }

   public void setRecipeUsed(@Nullable NT p_193056_1_) {
      this.recipeUsed = p_193056_1_;
   }

   @Nullable
   public NT getRecipeUsed() {
      return this.recipeUsed;
   }
}
