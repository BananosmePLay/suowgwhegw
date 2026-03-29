package net.minecraft.util.datafix.fixes;

import neo.QQ;
import neo.QW;
import net.minecraft.util.datafix.IFixableData;

public class BannerItemColor implements IFixableData {
   public BannerItemColor() {
   }

   public int getFixVersion() {
      return 804;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("minecraft:banner".equals(compound.getString("id")) && compound.hasKey("tag", 10)) {
         QQ nbttagcompound = compound.getCompoundTag("tag");
         if (nbttagcompound.hasKey("BlockEntityTag", 10)) {
            QQ nbttagcompound1 = nbttagcompound.getCompoundTag("BlockEntityTag");
            if (nbttagcompound1.hasKey("Base", 99)) {
               compound.setShort("Damage", (short)(nbttagcompound1.getShort("Base") & 15));
               if (nbttagcompound.hasKey("display", 10)) {
                  QQ nbttagcompound2 = nbttagcompound.getCompoundTag("display");
                  if (nbttagcompound2.hasKey("Lore", 9)) {
                     QW nbttaglist = nbttagcompound2.getTagList("Lore", 8);
                     if (nbttaglist.tagCount() == 1 && "(+NBT)".equals(nbttaglist.getStringTagAt(0))) {
                        return compound;
                     }
                  }
               }

               nbttagcompound1.removeTag("Base");
               if (nbttagcompound1.isEmpty()) {
                  nbttagcompound.removeTag("BlockEntityTag");
               }

               if (nbttagcompound.isEmpty()) {
                  compound.removeTag("tag");
               }
            }
         }
      }

      return compound;
   }
}
