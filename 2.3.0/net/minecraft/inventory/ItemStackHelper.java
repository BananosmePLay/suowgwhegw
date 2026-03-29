package net.minecraft.inventory;

import java.util.List;
import neo.QQ;
import neo.QW;
import neo.Qy;
import net.minecraft.util.NonNullList;

public class ItemStackHelper {
   public ItemStackHelper() {
   }

   public static Qy getAndSplit(List<Qy> stacks, int index, int amount) {
      return index >= 0 && index < stacks.size() && !((Qy)stacks.get(index)).isEmpty() && amount > 0 ? ((Qy)stacks.get(index)).splitStack(amount) : Qy.EMPTY;
   }

   public static Qy getAndRemove(List<Qy> stacks, int index) {
      return index >= 0 && index < stacks.size() ? (Qy)stacks.set(index, Qy.EMPTY) : Qy.EMPTY;
   }

   public static QQ saveAllItems(QQ tag, NonNullList<Qy> list) {
      return saveAllItems(tag, list, true);
   }

   public static QQ saveAllItems(QQ tag, NonNullList<Qy> list, boolean saveEmpty) {
      QW nbttaglist = new QW();

      for(int i = 0; i < list.size(); ++i) {
         Qy itemstack = (Qy)list.get(i);
         if (!itemstack.isEmpty()) {
            QQ nbttagcompound = new QQ();
            nbttagcompound.setByte("Slot", (byte)i);
            itemstack.writeToNBT(nbttagcompound);
            nbttaglist.appendTag(nbttagcompound);
         }
      }

      if (!nbttaglist.isEmpty() || saveEmpty) {
         tag.setTag("Items", nbttaglist);
      }

      return tag;
   }

   public static void loadAllItems(QQ tag, NonNullList<Qy> list) {
      QW nbttaglist = tag.getTagList("Items", 10);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
         int j = nbttagcompound.getByte("Slot") & 255;
         if (j >= 0 && j < list.size()) {
            list.set(j, new Qy(nbttagcompound));
         }
      }

   }
}
