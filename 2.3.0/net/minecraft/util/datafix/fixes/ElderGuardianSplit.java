package net.minecraft.util.datafix.fixes;

import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class ElderGuardianSplit implements IFixableData {
   public ElderGuardianSplit() {
   }

   public int getFixVersion() {
      return 700;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("Guardian".equals(compound.getString("id"))) {
         if (compound.getBoolean("Elder")) {
            compound.setString("id", "ElderGuardian");
         }

         compound.removeTag("Elder");
      }

      return compound;
   }
}
