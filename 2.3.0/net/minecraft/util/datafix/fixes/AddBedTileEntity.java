package net.minecraft.util.datafix.fixes;

import neo.QQ;
import neo.QW;
import net.minecraft.util.datafix.IFixableData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AddBedTileEntity implements IFixableData {
   private static final Logger LOGGER = LogManager.getLogger();

   public AddBedTileEntity() {
   }

   public int getFixVersion() {
      return 1125;
   }

   public QQ fixTagCompound(QQ compound) {
      int i = true;

      try {
         QQ nbttagcompound = compound.getCompoundTag("Level");
         int j = nbttagcompound.getInteger("xPos");
         int k = nbttagcompound.getInteger("zPos");
         QW nbttaglist = nbttagcompound.getTagList("TileEntities", 10);
         QW nbttaglist1 = nbttagcompound.getTagList("Sections", 10);

         for(int l = 0; l < nbttaglist1.tagCount(); ++l) {
            QQ nbttagcompound1 = nbttaglist1.getCompoundTagAt(l);
            int i1 = nbttagcompound1.getByte("Y");
            byte[] abyte = nbttagcompound1.getByteArray("Blocks");

            for(int j1 = 0; j1 < abyte.length; ++j1) {
               if (416 == (abyte[j1] & 255) << 4) {
                  int k1 = j1 & 15;
                  int l1 = j1 >> 8 & 15;
                  int i2 = j1 >> 4 & 15;
                  QQ nbttagcompound2 = new QQ();
                  nbttagcompound2.setString("id", "bed");
                  nbttagcompound2.setInteger("x", k1 + (j << 4));
                  nbttagcompound2.setInteger("y", l1 + (i1 << 4));
                  nbttagcompound2.setInteger("z", i2 + (k << 4));
                  nbttaglist.appendTag(nbttagcompound2);
               }
            }
         }
      } catch (Exception var17) {
         LOGGER.warn("Unable to datafix Bed blocks, level format may be missing tags.");
      }

      return compound;
   }
}
