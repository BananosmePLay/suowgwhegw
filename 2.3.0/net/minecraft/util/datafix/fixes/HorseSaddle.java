package net.minecraft.util.datafix.fixes;

import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class HorseSaddle implements IFixableData {
   public HorseSaddle() {
   }

   public int getFixVersion() {
      return 110;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("EntityHorse".equals(compound.getString("id")) && !compound.hasKey("SaddleItem", 10) && compound.getBoolean("Saddle")) {
         QQ nbttagcompound = new QQ();
         nbttagcompound.setString("id", "minecraft:saddle");
         nbttagcompound.setByte("Count", (byte)1);
         nbttagcompound.setShort("Damage", (short)0);
         compound.setTag("SaddleItem", nbttagcompound);
         compound.removeTag("Saddle");
      }

      return compound;
   }
}
