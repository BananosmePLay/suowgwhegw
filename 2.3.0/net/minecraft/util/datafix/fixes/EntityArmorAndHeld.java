package net.minecraft.util.datafix.fixes;

import neo.QQ;
import neo.QT;
import neo.QW;
import net.minecraft.util.datafix.IFixableData;

public class EntityArmorAndHeld implements IFixableData {
   public EntityArmorAndHeld() {
   }

   public int getFixVersion() {
      return 100;
   }

   public QQ fixTagCompound(QQ compound) {
      QW nbttaglist = compound.getTagList("Equipment", 10);
      QW nbttaglist4;
      if (!nbttaglist.isEmpty() && !compound.hasKey("HandItems", 10)) {
         nbttaglist4 = new QW();
         nbttaglist4.appendTag(nbttaglist.get(0));
         nbttaglist4.appendTag(new QQ());
         compound.setTag("HandItems", nbttaglist4);
      }

      if (nbttaglist.tagCount() > 1 && !compound.hasKey("ArmorItem", 10)) {
         nbttaglist4 = new QW();
         nbttaglist4.appendTag(nbttaglist.getCompoundTagAt(1));
         nbttaglist4.appendTag(nbttaglist.getCompoundTagAt(2));
         nbttaglist4.appendTag(nbttaglist.getCompoundTagAt(3));
         nbttaglist4.appendTag(nbttaglist.getCompoundTagAt(4));
         compound.setTag("ArmorItems", nbttaglist4);
      }

      compound.removeTag("Equipment");
      if (compound.hasKey("DropChances", 9)) {
         nbttaglist4 = compound.getTagList("DropChances", 5);
         QW nbttaglist5;
         if (!compound.hasKey("HandDropChances", 10)) {
            nbttaglist5 = new QW();
            nbttaglist5.appendTag(new QT(nbttaglist4.getFloatAt(0)));
            nbttaglist5.appendTag(new QT(0.0F));
            compound.setTag("HandDropChances", nbttaglist5);
         }

         if (!compound.hasKey("ArmorDropChances", 10)) {
            nbttaglist5 = new QW();
            nbttaglist5.appendTag(new QT(nbttaglist4.getFloatAt(1)));
            nbttaglist5.appendTag(new QT(nbttaglist4.getFloatAt(2)));
            nbttaglist5.appendTag(new QT(nbttaglist4.getFloatAt(3)));
            nbttaglist5.appendTag(new QT(nbttaglist4.getFloatAt(4)));
            compound.setTag("ArmorDropChances", nbttaglist5);
         }

         compound.removeTag("DropChances");
      }

      return compound;
   }
}
