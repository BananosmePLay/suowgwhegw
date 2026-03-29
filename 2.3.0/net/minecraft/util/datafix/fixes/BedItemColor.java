package net.minecraft.util.datafix.fixes;

import neo.Om;
import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class BedItemColor implements IFixableData {
   public BedItemColor() {
   }

   public int getFixVersion() {
      return 1125;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("minecraft:bed".equals(compound.getString("id")) && compound.getShort("Damage") == 0) {
         compound.setShort("Damage", (short)Om.RED.getMetadata());
      }

      return compound;
   }
}
