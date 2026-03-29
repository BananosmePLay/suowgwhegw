package neo;

import net.minecraft.util.math.BlockPos;

public class baA {
   public baA() {
   }

   public static baz load(QQ nbt) {
      int i = nbt.getInteger("xPos");
      int j = nbt.getInteger("zPos");
      baz chunkloader$anvilconverterdata = new baz(i, j);
      chunkloader$anvilconverterdata.blocks = nbt.getByteArray("Blocks");
      chunkloader$anvilconverterdata.data = new baD(nbt.getByteArray("Data"), 7);
      chunkloader$anvilconverterdata.skyLight = new baD(nbt.getByteArray("SkyLight"), 7);
      chunkloader$anvilconverterdata.blockLight = new baD(nbt.getByteArray("BlockLight"), 7);
      chunkloader$anvilconverterdata.heightmap = nbt.getByteArray("HeightMap");
      chunkloader$anvilconverterdata.terrainPopulated = nbt.getBoolean("TerrainPopulated");
      chunkloader$anvilconverterdata.entities = nbt.getTagList("Entities", 10);
      chunkloader$anvilconverterdata.tileEntities = nbt.getTagList("TileEntities", 10);
      chunkloader$anvilconverterdata.tileTicks = nbt.getTagList("TileTicks", 10);

      try {
         chunkloader$anvilconverterdata.lastUpdated = nbt.getLong("LastUpdate");
      } catch (ClassCastException var5) {
         chunkloader$anvilconverterdata.lastUpdated = (long)nbt.getInteger("LastUpdate");
      }

      return chunkloader$anvilconverterdata;
   }

   public static void convertToAnvilFormat(baz converterData, QQ compound, ZL provider) {
      compound.setInteger("xPos", converterData.x);
      compound.setInteger("zPos", converterData.z);
      compound.setLong("LastUpdate", converterData.lastUpdated);
      int[] aint = new int[converterData.heightmap.length];

      for(int i = 0; i < converterData.heightmap.length; ++i) {
         aint[i] = converterData.heightmap[i];
      }

      compound.setIntArray("HeightMap", aint);
      compound.setBoolean("TerrainPopulated", converterData.terrainPopulated);
      QW nbttaglist = new QW();

      int k;
      int l;
      for(int j = 0; j < 8; ++j) {
         boolean flag = true;

         int j3;
         for(k = 0; k < 16 && flag; ++k) {
            for(l = 0; l < 16 && flag; ++l) {
               for(int i1 = 0; i1 < 16; ++i1) {
                  int j1 = k << 11 | i1 << 7 | l + (j << 4);
                  j3 = converterData.blocks[j1];
                  if (j3 != 0) {
                     flag = false;
                     break;
                  }
               }
            }
         }

         if (!flag) {
            byte[] abyte1 = new byte[4096];
            bas nibblearray = new bas();
            bas nibblearray1 = new bas();
            bas nibblearray2 = new bas();

            for(j3 = 0; j3 < 16; ++j3) {
               for(int l1 = 0; l1 < 16; ++l1) {
                  for(int i2 = 0; i2 < 16; ++i2) {
                     int j2 = j3 << 11 | i2 << 7 | l1 + (j << 4);
                     int k2 = converterData.blocks[j2];
                     abyte1[l1 << 8 | i2 << 4 | j3] = (byte)(k2 & 255);
                     nibblearray.set(j3, l1, i2, converterData.data.get(j3, l1 + (j << 4), i2));
                     nibblearray1.set(j3, l1, i2, converterData.skyLight.get(j3, l1 + (j << 4), i2));
                     nibblearray2.set(j3, l1, i2, converterData.blockLight.get(j3, l1 + (j << 4), i2));
                  }
               }
            }

            QQ nbttagcompound = new QQ();
            nbttagcompound.setByte("Y", (byte)(j & 255));
            nbttagcompound.setByteArray("Blocks", abyte1);
            nbttagcompound.setByteArray("Data", nibblearray.getData());
            nbttagcompound.setByteArray("SkyLight", nibblearray1.getData());
            nbttagcompound.setByteArray("BlockLight", nibblearray2.getData());
            nbttaglist.appendTag(nbttagcompound);
         }
      }

      compound.setTag("Sections", nbttaglist);
      byte[] abyte = new byte[256];
      BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

      for(k = 0; k < 16; ++k) {
         for(l = 0; l < 16; ++l) {
            blockpos$mutableblockpos.setPos(converterData.x << 4 | k, 0, converterData.z << 4 | l);
            abyte[l << 4 | k] = (byte)(Zi.getIdForBiome(provider.getBiome(blockpos$mutableblockpos, Nj.DEFAULT)) & 255);
         }
      }

      compound.setByteArray("Biomes", abyte);
      compound.setTag("Entities", converterData.entities);
      compound.setTag("TileEntities", converterData.tileEntities);
      if (converterData.tileTicks != null) {
         compound.setTag("TileTicks", converterData.tileTicks);
      }

   }
}
