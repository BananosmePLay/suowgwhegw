package net.minecraft.util.datafix.fixes;

import neo.QQ;
import net.minecraft.util.datafix.IFixableData;

public class TotemItemRename implements IFixableData {
   public TotemItemRename() {
   }

   public int getFixVersion() {
      return 820;
   }

   public QQ fixTagCompound(QQ compound) {
      if ("minecraft:totem".equals(compound.getString("id"))) {
         compound.setString("id", "minecraft:totem_of_undying");
      }

      return compound;
   }
}
