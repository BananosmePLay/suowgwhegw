package net.minecraft.util.datafix;

import neo.QQ;

public interface IFixableData {
   int getFixVersion();

   QQ fixTagCompound(QQ var1);
}
