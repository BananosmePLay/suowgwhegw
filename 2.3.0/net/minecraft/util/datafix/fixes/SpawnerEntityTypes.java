package net.minecraft.util.datafix.fixes;

import neo.QQ;
import neo.QW;
import net.minecraft.util.datafix.IFixableData;

public class SpawnerEntityTypes implements IFixableData {
   public SpawnerEntityTypes() {
   }

   public int getFixVersion() {
      return 107;
   }

   public QQ fixTagCompound(QQ compound) {
      if (!"MobSpawner".equals(compound.getString("id"))) {
         return compound;
      } else {
         if (compound.hasKey("EntityId", 8)) {
            String s = compound.getString("EntityId");
            QQ nbttagcompound = compound.getCompoundTag("SpawnData");
            nbttagcompound.setString("id", s.isEmpty() ? "Pig" : s);
            compound.setTag("SpawnData", nbttagcompound);
            compound.removeTag("EntityId");
         }

         if (compound.hasKey("SpawnPotentials", 9)) {
            QW nbttaglist = compound.getTagList("SpawnPotentials", 10);

            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               QQ nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
               if (nbttagcompound1.hasKey("Type", 8)) {
                  QQ nbttagcompound2 = nbttagcompound1.getCompoundTag("Properties");
                  nbttagcompound2.setString("id", nbttagcompound1.getString("Type"));
                  nbttagcompound1.setTag("Entity", nbttagcompound2);
                  nbttagcompound1.removeTag("Type");
                  nbttagcompound1.removeTag("Properties");
               }
            }
         }

         return compound;
      }
   }
}
