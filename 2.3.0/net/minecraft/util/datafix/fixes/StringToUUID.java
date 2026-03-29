package net.minecraft.util.datafix.fixes;

import java.util.UUID;
import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class StringToUUID implements IFixableData {
   public StringToUUID() {
   }

   public int getFixVersion() {
      return 108;
   }

   public QQ fixTagCompound(QQ compound) {
      if (compound.hasKey("UUID", 8)) {
         compound.setUniqueId("UUID", UUID.fromString(compound.getString("UUID")));
      }

      return compound;
   }
}
