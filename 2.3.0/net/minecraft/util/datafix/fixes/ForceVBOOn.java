package net.minecraft.util.datafix.fixes;

import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class ForceVBOOn implements IFixableData {
   public ForceVBOOn() {
   }

   public int getFixVersion() {
      return 505;
   }

   public QQ fixTagCompound(QQ compound) {
      compound.setString("useVbo", "true");
      return compound;
   }
}
