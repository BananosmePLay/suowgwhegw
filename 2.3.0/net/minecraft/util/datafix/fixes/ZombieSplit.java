package net.minecraft.util.datafix.fixes;

import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class ZombieSplit implements IFixableData {
   public ZombieSplit() {
   }

   public int getFixVersion() {
      return 702;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("Zombie".equals(compound.getString("id"))) {
         int i = compound.getInteger("ZombieType");
         switch (i) {
            case 0:
            default:
               break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
               compound.setString("id", "ZombieVillager");
               compound.setInteger("Profession", i - 1);
               break;
            case 6:
               compound.setString("id", "Husk");
         }

         compound.removeTag("ZombieType");
      }

      return compound;
   }
}
