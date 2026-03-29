package net.minecraft.util.datafix.fixes;

import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class ShulkerBoxTileColor implements IFixableData {
   public ShulkerBoxTileColor() {
   }

   public int getFixVersion() {
      return 813;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("minecraft:shulker".equals(compound.getString("id"))) {
         compound.removeTag("Color");
      }

      return compound;
   }
}
