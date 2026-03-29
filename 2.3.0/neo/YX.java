package neo;

import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Nullable;

public class YX extends ArrayList<YW> {
   public YX() {
   }

   public YX(QQ compound) {
      this.readRecipiesFromTags(compound);
   }

   @Nullable
   public YW canRecipeBeUsed(Qy stack0, Qy stack1, int index) {
      if (index > 0 && index < this.size()) {
         YW merchantrecipe1 = (YW)this.get(index);
         return this.areItemStacksExactlyEqual(stack0, merchantrecipe1.getItemToBuy()) && (stack1.isEmpty() && !merchantrecipe1.hasSecondItemToBuy() || merchantrecipe1.hasSecondItemToBuy() && this.areItemStacksExactlyEqual(stack1, merchantrecipe1.getSecondItemToBuy())) && stack0.getCount() >= merchantrecipe1.getItemToBuy().getCount() && (!merchantrecipe1.hasSecondItemToBuy() || stack1.getCount() >= merchantrecipe1.getSecondItemToBuy().getCount()) ? merchantrecipe1 : null;
      } else {
         for(int i = 0; i < this.size(); ++i) {
            YW merchantrecipe = (YW)this.get(i);
            if (this.areItemStacksExactlyEqual(stack0, merchantrecipe.getItemToBuy()) && stack0.getCount() >= merchantrecipe.getItemToBuy().getCount() && (!merchantrecipe.hasSecondItemToBuy() && stack1.isEmpty() || merchantrecipe.hasSecondItemToBuy() && this.areItemStacksExactlyEqual(stack1, merchantrecipe.getSecondItemToBuy()) && stack1.getCount() >= merchantrecipe.getSecondItemToBuy().getCount())) {
               return merchantrecipe;
            }
         }

         return null;
      }
   }

   private boolean areItemStacksExactlyEqual(Qy stack1, Qy stack2) {
      return Qy.areItemsEqual(stack1, stack2) && (!stack2.hasTagCompound() || stack1.hasTagCompound() && Rb.areNBTEquals(stack2.getTagCompound(), stack1.getTagCompound(), false));
   }

   public void writeToBuf(SA buffer) {
      buffer.writeByte((byte)(this.size() & 255));

      for(int i = 0; i < this.size(); ++i) {
         YW merchantrecipe = (YW)this.get(i);
         buffer.writeItemStack(merchantrecipe.getItemToBuy());
         buffer.writeItemStack(merchantrecipe.getItemToSell());
         Qy itemstack = merchantrecipe.getSecondItemToBuy();
         buffer.writeBoolean(!itemstack.isEmpty());
         if (!itemstack.isEmpty()) {
            buffer.writeItemStack(itemstack);
         }

         buffer.writeBoolean(merchantrecipe.isRecipeDisabled());
         buffer.writeInt(merchantrecipe.getToolUses());
         buffer.writeInt(merchantrecipe.getMaxTradeUses());
      }

   }

   public static YX readFromBuf(SA buffer) throws IOException {
      YX merchantrecipelist = new YX();
      int i = buffer.readByte() & 255;

      for(int j = 0; j < i; ++j) {
         Qy itemstack = buffer.readItemStack();
         Qy itemstack1 = buffer.readItemStack();
         Qy itemstack2 = Qy.EMPTY;
         if (buffer.readBoolean()) {
            itemstack2 = buffer.readItemStack();
         }

         boolean flag = buffer.readBoolean();
         int k = buffer.readInt();
         int l = buffer.readInt();
         YW merchantrecipe = new YW(itemstack, itemstack2, itemstack1, k, l);
         if (flag) {
            merchantrecipe.compensateToolUses();
         }

         merchantrecipelist.add(merchantrecipe);
      }

      return merchantrecipelist;
   }

   public void readRecipiesFromTags(QQ compound) {
      QW nbttaglist = compound.getTagList("Recipes", 10);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
         this.add(new YW(nbttagcompound));
      }

   }

   public QQ getRecipiesAsTags() {
      QQ nbttagcompound = new QQ();
      QW nbttaglist = new QW();

      for(int i = 0; i < this.size(); ++i) {
         YW merchantrecipe = (YW)this.get(i);
         nbttaglist.appendTag(merchantrecipe.writeToTags());
      }

      nbttagcompound.setTag("Recipes", nbttaglist);
      return nbttagcompound;
   }
}
