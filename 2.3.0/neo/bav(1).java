package neo;

import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.datafix.DataFixer;
import net.minecraft.util.datafix.FixTypes;
import net.minecraft.util.datafix.IDataFixer;
import net.minecraft.util.datafix.IDataWalker;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bav implements baC, bgn {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Map<ChunkPos, QQ> chunksToSave = Maps.newConcurrentMap();
   private final Set<ChunkPos> chunksBeingSaved = Collections.newSetFromMap(Maps.newConcurrentMap());
   private final File chunkSaveLocation;
   private final DataFixer fixer;
   private boolean flushing;

   public bav(File chunkSaveLocationIn, DataFixer dataFixerIn) {
      this.chunkSaveLocation = chunkSaveLocationIn;
      this.fixer = dataFixerIn;
   }

   @Nullable
   public bam loadChunk(bij worldIn, int x, int z) throws IOException {
      ChunkPos chunkpos = new ChunkPos(x, z);
      QQ nbttagcompound = (QQ)this.chunksToSave.get(chunkpos);
      if (nbttagcompound == null) {
         DataInputStream datainputstream = baH.getChunkInputStream(this.chunkSaveLocation, x, z);
         if (datainputstream == null) {
            return null;
         }

         nbttagcompound = this.fixer.process(FixTypes.CHUNK, QF.read(datainputstream));
      }

      return this.checkedReadChunkFromNBT(worldIn, x, z, nbttagcompound);
   }

   public boolean isChunkGeneratedAt(int x, int z) {
      ChunkPos chunkpos = new ChunkPos(x, z);
      QQ nbttagcompound = (QQ)this.chunksToSave.get(chunkpos);
      return nbttagcompound != null ? true : baH.chunkExists(this.chunkSaveLocation, x, z);
   }

   @Nullable
   protected bam checkedReadChunkFromNBT(bij worldIn, int x, int z, QQ compound) {
      if (!compound.hasKey("Level", 10)) {
         LOGGER.error("Chunk file at {},{} is missing level data, skipping", x, z);
         return null;
      } else {
         QQ nbttagcompound = compound.getCompoundTag("Level");
         if (!nbttagcompound.hasKey("Sections", 9)) {
            LOGGER.error("Chunk file at {},{} is missing block data, skipping", x, z);
            return null;
         } else {
            bam chunk = this.readChunkFromNBT(worldIn, nbttagcompound);
            if (!chunk.isAtLocation(x, z)) {
               LOGGER.error("Chunk file at {},{} is in the wrong location; relocating. (Expected {}, {}, got {}, {})", x, z, x, z, chunk.x, chunk.z);
               nbttagcompound.setInteger("xPos", x);
               nbttagcompound.setInteger("zPos", z);
               chunk = this.readChunkFromNBT(worldIn, nbttagcompound);
            }

            return chunk;
         }
      }
   }

   public void saveChunk(bij worldIn, bam chunkIn) throws bgf, IOException {
      worldIn.checkSessionLock();

      try {
         QQ nbttagcompound = new QQ();
         QQ nbttagcompound1 = new QQ();
         nbttagcompound.setTag("Level", nbttagcompound1);
         nbttagcompound.setInteger("DataVersion", 1343);
         this.writeChunkToNBT(chunkIn, worldIn, nbttagcompound1);
         this.addChunkToPending(chunkIn.getPos(), nbttagcompound);
      } catch (Exception var5) {
         Exception exception = var5;
         LOGGER.error("Failed to save chunk", exception);
      }

   }

   protected void addChunkToPending(ChunkPos pos, QQ compound) {
      if (!this.chunksBeingSaved.contains(pos)) {
         this.chunksToSave.put(pos, compound);
      }

      bhN.getThreadedIOInstance().queueIO(this);
   }

   public boolean writeNextIO() {
      if (this.chunksToSave.isEmpty()) {
         if (this.flushing) {
            LOGGER.info("ThreadedAnvilChunkStorage ({}): All chunks are saved", this.chunkSaveLocation.getName());
         }

         return false;
      } else {
         ChunkPos chunkpos = (ChunkPos)this.chunksToSave.keySet().iterator().next();

         boolean lvt_3_1_;
         try {
            this.chunksBeingSaved.add(chunkpos);
            QQ nbttagcompound = (QQ)this.chunksToSave.remove(chunkpos);
            if (nbttagcompound != null) {
               try {
                  this.writeChunkData(chunkpos, nbttagcompound);
               } catch (Exception var8) {
                  Exception exception = var8;
                  LOGGER.error("Failed to save chunk", exception);
               }
            }

            lvt_3_1_ = true;
         } finally {
            this.chunksBeingSaved.remove(chunkpos);
         }

         return lvt_3_1_;
      }
   }

   private void writeChunkData(ChunkPos pos, QQ compound) throws IOException {
      DataOutputStream dataoutputstream = baH.getChunkOutputStream(this.chunkSaveLocation, pos.x, pos.z);
      QF.write(compound, (DataOutput)dataoutputstream);
      dataoutputstream.close();
   }

   public void saveExtraChunkData(bij worldIn, bam chunkIn) throws IOException {
   }

   public void chunkTick() {
   }

   public void flush() {
      try {
         this.flushing = true;

         while(true) {
            if (this.writeNextIO()) {
               continue;
            }
         }
      } finally {
         this.flushing = false;
      }

   }

   public static void registerFixes(DataFixer fixer) {
      fixer.registerWalker(FixTypes.CHUNK, new IDataWalker() {
         public QQ process(IDataFixer fixer, QQ compound, int versionIn) {
            if (compound.hasKey("Level", 10)) {
               QQ nbttagcompound = compound.getCompoundTag("Level");
               QW nbttaglist1;
               int j;
               if (nbttagcompound.hasKey("Entities", 9)) {
                  nbttaglist1 = nbttagcompound.getTagList("Entities", 10);

                  for(j = 0; j < nbttaglist1.tagCount(); ++j) {
                     nbttaglist1.set(j, fixer.process(FixTypes.ENTITY, (QQ)nbttaglist1.get(j), versionIn));
                  }
               }

               if (nbttagcompound.hasKey("TileEntities", 9)) {
                  nbttaglist1 = nbttagcompound.getTagList("TileEntities", 10);

                  for(j = 0; j < nbttaglist1.tagCount(); ++j) {
                     nbttaglist1.set(j, fixer.process(FixTypes.BLOCK_ENTITY, (QQ)nbttaglist1.get(j), versionIn));
                  }
               }
            }

            return compound;
         }
      });
   }

   private void writeChunkToNBT(bam chunkIn, bij worldIn, QQ compound) {
      compound.setInteger("xPos", chunkIn.x);
      compound.setInteger("zPos", chunkIn.z);
      compound.setLong("LastUpdate", worldIn.getTotalWorldTime());
      compound.setIntArray("HeightMap", chunkIn.getHeightMap());
      compound.setBoolean("TerrainPopulated", chunkIn.isTerrainPopulated());
      compound.setBoolean("LightPopulated", chunkIn.isLightPopulated());
      compound.setLong("InhabitedTime", chunkIn.getInhabitedTime());
      baB[] aextendedblockstorage = chunkIn.getBlockStorageArray();
      QW nbttaglist = new QW();
      boolean flag = worldIn.provider.hasSkyLight();
      baB[] var7 = aextendedblockstorage;
      int i = aextendedblockstorage.length;

      QQ nbttagcompound2;
      for(int var9 = 0; var9 < i; ++var9) {
         baB extendedblockstorage = var7[var9];
         if (extendedblockstorage != bam.NULL_BLOCK_STORAGE) {
            nbttagcompound2 = new QQ();
            nbttagcompound2.setByte("Y", (byte)(extendedblockstorage.getYLocation() >> 4 & 255));
            byte[] abyte = new byte[4096];
            bas nibblearray = new bas();
            bas nibblearray1 = extendedblockstorage.getData().getDataForNBT(abyte, nibblearray);
            nbttagcompound2.setByteArray("Blocks", abyte);
            nbttagcompound2.setByteArray("Data", nibblearray.getData());
            if (nibblearray1 != null) {
               nbttagcompound2.setByteArray("Add", nibblearray1.getData());
            }

            nbttagcompound2.setByteArray("BlockLight", extendedblockstorage.getBlockLight().getData());
            if (flag) {
               nbttagcompound2.setByteArray("SkyLight", extendedblockstorage.getSkyLight().getData());
            } else {
               nbttagcompound2.setByteArray("SkyLight", new byte[extendedblockstorage.getBlockLight().getData().length]);
            }

            nbttaglist.appendTag(nbttagcompound2);
         }
      }

      compound.setTag("Sections", nbttaglist);
      compound.setByteArray("Biomes", chunkIn.getBiomeArray());
      chunkIn.setHasEntities(false);
      QW nbttaglist1 = new QW();

      Iterator var19;
      for(i = 0; i < chunkIn.getEntityLists().length; ++i) {
         var19 = chunkIn.getEntityLists()[i].iterator();

         while(var19.hasNext()) {
            Ig entity = (Ig)var19.next();
            nbttagcompound2 = new QQ();
            if (entity.writeToNBTOptional(nbttagcompound2)) {
               chunkIn.setHasEntities(true);
               nbttaglist1.appendTag(nbttagcompound2);
            }
         }
      }

      compound.setTag("Entities", nbttaglist1);
      QW nbttaglist2 = new QW();
      var19 = chunkIn.getTileEntityMap().values().iterator();

      while(var19.hasNext()) {
         Yg tileentity = (Yg)var19.next();
         nbttagcompound2 = tileentity.writeToNBT(new QQ());
         nbttaglist2.appendTag(nbttagcompound2);
      }

      compound.setTag("TileEntities", nbttaglist2);
      List<bgg> list = worldIn.getPendingBlockUpdates(chunkIn, false);
      if (list != null) {
         long j = worldIn.getTotalWorldTime();
         QW nbttaglist3 = new QW();
         Iterator var25 = list.iterator();

         while(var25.hasNext()) {
            bgg nextticklistentry = (bgg)var25.next();
            QQ nbttagcompound1 = new QQ();
            ResourceLocation resourcelocation = (ResourceLocation)co.REGISTRY.getNameForObject(nextticklistentry.getBlock());
            nbttagcompound1.setString("i", resourcelocation == null ? "" : resourcelocation.toString());
            nbttagcompound1.setInteger("x", nextticklistentry.position.getX());
            nbttagcompound1.setInteger("y", nextticklistentry.position.getY());
            nbttagcompound1.setInteger("z", nextticklistentry.position.getZ());
            nbttagcompound1.setInteger("t", (int)(nextticklistentry.scheduledTime - j));
            nbttagcompound1.setInteger("p", nextticklistentry.priority);
            nbttaglist3.appendTag(nbttagcompound1);
         }

         compound.setTag("TileTicks", nbttaglist3);
      }

   }

   private bam readChunkFromNBT(bij worldIn, QQ compound) {
      int i = compound.getInteger("xPos");
      int j = compound.getInteger("zPos");
      bam chunk = new bam(worldIn, i, j);
      chunk.setHeightMap(compound.getIntArray("HeightMap"));
      chunk.setTerrainPopulated(compound.getBoolean("TerrainPopulated"));
      chunk.setLightPopulated(compound.getBoolean("LightPopulated"));
      chunk.setInhabitedTime(compound.getLong("InhabitedTime"));
      QW nbttaglist = compound.getTagList("Sections", 10);
      int k = true;
      baB[] aextendedblockstorage = new baB[16];
      boolean flag = worldIn.provider.hasSkyLight();

      int k1;
      for(int l = 0; l < nbttaglist.tagCount(); ++l) {
         QQ nbttagcompound = nbttaglist.getCompoundTagAt(l);
         k1 = nbttagcompound.getByte("Y");
         baB extendedblockstorage = new baB(k1 << 4, flag);
         byte[] abyte = nbttagcompound.getByteArray("Blocks");
         bas nibblearray = new bas(nbttagcompound.getByteArray("Data"));
         bas nibblearray1 = nbttagcompound.hasKey("Add", 7) ? new bas(nbttagcompound.getByteArray("Add")) : null;
         extendedblockstorage.getData().setDataFromNBT(abyte, nibblearray, nibblearray1);
         extendedblockstorage.setBlockLight(new bas(nbttagcompound.getByteArray("BlockLight")));
         if (flag) {
            extendedblockstorage.setSkyLight(new bas(nbttagcompound.getByteArray("SkyLight")));
         }

         extendedblockstorage.recalculateRefCounts();
         aextendedblockstorage[k1] = extendedblockstorage;
      }

      chunk.setStorageArrays(aextendedblockstorage);
      if (compound.hasKey("Biomes", 7)) {
         chunk.setBiomeArray(compound.getByteArray("Biomes"));
      }

      QW nbttaglist1 = compound.getTagList("Entities", 10);

      for(int j1 = 0; j1 < nbttaglist1.tagCount(); ++j1) {
         QQ nbttagcompound1 = nbttaglist1.getCompoundTagAt(j1);
         readChunkEntity(nbttagcompound1, worldIn, chunk);
         chunk.setHasEntities(true);
      }

      QW nbttaglist2 = compound.getTagList("TileEntities", 10);

      for(k1 = 0; k1 < nbttaglist2.tagCount(); ++k1) {
         QQ nbttagcompound2 = nbttaglist2.getCompoundTagAt(k1);
         Yg tileentity = Yg.create(worldIn, nbttagcompound2);
         if (tileentity != null) {
            chunk.addTileEntity(tileentity);
         }
      }

      if (compound.hasKey("TileTicks", 9)) {
         QW nbttaglist3 = compound.getTagList("TileTicks", 10);

         for(int l1 = 0; l1 < nbttaglist3.tagCount(); ++l1) {
            QQ nbttagcompound3 = nbttaglist3.getCompoundTagAt(l1);
            co block;
            if (nbttagcompound3.hasKey("i", 8)) {
               block = co.getBlockFromName(nbttagcompound3.getString("i"));
            } else {
               block = co.getBlockById(nbttagcompound3.getInteger("i"));
            }

            worldIn.scheduleBlockUpdate(new BlockPos(nbttagcompound3.getInteger("x"), nbttagcompound3.getInteger("y"), nbttagcompound3.getInteger("z")), block, nbttagcompound3.getInteger("t"), nbttagcompound3.getInteger("p"));
         }
      }

      return chunk;
   }

   @Nullable
   public static Ig readChunkEntity(QQ compound, bij worldIn, bam chunkIn) {
      Ig entity = createEntityFromNBT(compound, worldIn);
      if (entity == null) {
         return null;
      } else {
         chunkIn.addEntity(entity);
         if (compound.hasKey("Passengers", 9)) {
            QW nbttaglist = compound.getTagList("Passengers", 10);

            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               Ig entity1 = readChunkEntity(nbttaglist.getCompoundTagAt(i), worldIn, chunkIn);
               if (entity1 != null) {
                  entity1.startRiding(entity, true);
               }
            }
         }

         return entity;
      }
   }

   @Nullable
   public static Ig readWorldEntityPos(QQ compound, bij worldIn, double x, double y, double z, boolean attemptSpawn) {
      Ig entity = createEntityFromNBT(compound, worldIn);
      if (entity == null) {
         return null;
      } else {
         entity.setLocationAndAngles(x, y, z, entity.rotationYaw, entity.rotationPitch);
         if (attemptSpawn && !worldIn.spawnEntity(entity)) {
            return null;
         } else {
            if (compound.hasKey("Passengers", 9)) {
               QW nbttaglist = compound.getTagList("Passengers", 10);

               for(int i = 0; i < nbttaglist.tagCount(); ++i) {
                  Ig entity1 = readWorldEntityPos(nbttaglist.getCompoundTagAt(i), worldIn, x, y, z, attemptSpawn);
                  if (entity1 != null) {
                     entity1.startRiding(entity, true);
                  }
               }
            }

            return entity;
         }
      }
   }

   @Nullable
   protected static Ig createEntityFromNBT(QQ compound, bij worldIn) {
      try {
         return Ir.createEntityFromNBT(compound, worldIn);
      } catch (RuntimeException var3) {
         return null;
      }
   }

   public static void spawnEntity(Ig entityIn, bij worldIn) {
      if (worldIn.spawnEntity(entityIn) && entityIn.isBeingRidden()) {
         Iterator var2 = entityIn.getPassengers().iterator();

         while(var2.hasNext()) {
            Ig entity = (Ig)var2.next();
            spawnEntity(entity, worldIn);
         }
      }

   }

   @Nullable
   public static Ig readWorldEntity(QQ compound, bij worldIn, boolean p_186051_2_) {
      Ig entity = createEntityFromNBT(compound, worldIn);
      if (entity == null) {
         return null;
      } else if (p_186051_2_ && !worldIn.spawnEntity(entity)) {
         return null;
      } else {
         if (compound.hasKey("Passengers", 9)) {
            QW nbttaglist = compound.getTagList("Passengers", 10);

            for(int i = 0; i < nbttaglist.tagCount(); ++i) {
               Ig entity1 = readWorldEntity(nbttaglist.getCompoundTagAt(i), worldIn, p_186051_2_);
               if (entity1 != null) {
                  entity1.startRiding(entity, true);
               }
            }
         }

         return entity;
      }
   }
}
