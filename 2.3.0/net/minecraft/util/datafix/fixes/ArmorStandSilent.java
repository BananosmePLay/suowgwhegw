package net.minecraft.util.datafix.fixes;

import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class ArmorStandSilent implements IFixableData {
   public ArmorStandSilent() {
   }

   public int getFixVersion() {
      return 147;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("ArmorStand".equals(compound.getString("id")) && compound.getBoolean("Silent") && !compound.getBoolean("Marker")) {
         compound.removeTag("Silent");
      }

      return compound;
   }
}
