package neo;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class bbl implements bar {
   private static final Logger LOGGER = LogManager.getLogger();
   private final Set<Long> droppedChunks = Sets.newHashSet();
   private final bcn chunkGenerator;
   private final baC chunkLoader;
   private final Long2ObjectMap<bam> loadedChunks = new Long2ObjectOpenHashMap(8192);
   private final bis world;

   public bbl(bis worldObjIn, baC chunkLoaderIn, bcn chunkGeneratorIn) {
      this.world = worldObjIn;
      this.chunkLoader = chunkLoaderIn;
      this.chunkGenerator = chunkGeneratorIn;
   }

   public Collection<bam> getLoadedChunks() {
      return this.loadedChunks.values();
   }

   public void queueUnload(bam chunkIn) {
      if (this.world.provider.canDropChunk(chunkIn.x, chunkIn.z)) {
         this.droppedChunks.add(ChunkPos.asLong(chunkIn.x, chunkIn.z));
         chunkIn.unloadQueued = true;
      }

   }

   public void queueUnloadAll() {
      ObjectIterator objectiterator = this.loadedChunks.values().iterator();

      while(objectiterator.hasNext()) {
         bam chunk = (bam)objectiterator.next();
         this.queueUnload(chunk);
      }

   }

   @Nullable
   public bam getLoadedChunk(int x, int z) {
      long i = ChunkPos.asLong(x, z);
      bam chunk = (bam)this.loadedChunks.get(i);
      if (chunk != null) {
         chunk.unloadQueued = false;
      }

      return chunk;
   }

   @Nullable
   public bam loadChunk(int x, int z) {
      bam chunk = this.getLoadedChunk(x, z);
      if (chunk == null) {
         chunk = this.loadChunkFromFile(x, z);
         if (chunk != null) {
            this.loadedChunks.put(ChunkPos.asLong(x, z), chunk);
            chunk.onLoad();
            chunk.populate(this, this.chunkGenerator);
         }
      }

      return chunk;
   }

   public bam provideChunk(int x, int z) {
      bam chunk = this.loadChunk(x, z);
      if (chunk == null) {
         long i = ChunkPos.asLong(x, z);

         try {
            chunk = this.chunkGenerator.generateChunk(x, z);
         } catch (Throwable var9) {
            Throwable throwable = var9;
            Er crashreport = Er.makeCrashReport(throwable, "Exception generating new chunk");
            Ey crashreportcategory = crashreport.makeCategory("Chunk to be generated");
            crashreportcategory.addCrashSection("Location", String.format("%d,%d", x, z));
            crashreportcategory.addCrashSection("Position hash", i);
            crashreportcategory.addCrashSection("Generator", this.chunkGenerator);
            throw new ReportedException(crashreport);
         }

         this.loadedChunks.put(i, chunk);
         chunk.onLoad();
         chunk.populate(this, this.chunkGenerator);
      }

      return chunk;
   }

   @Nullable
   private bam loadChunkFromFile(int x, int z) {
      try {
         bam chunk = this.chunkLoader.loadChunk(this.world, x, z);
         if (chunk != null) {
            chunk.setLastSaveTime(this.world.getTotalWorldTime());
            this.chunkGenerator.recreateStructures(chunk, x, z);
         }

         return chunk;
      } catch (Exception var4) {
         Exception exception = var4;
         LOGGER.error("Couldn't load chunk", exception);
         return null;
      }
   }

   private void saveChunkExtraData(bam chunkIn) {
      try {
         this.chunkLoader.saveExtraChunkData(this.world, chunkIn);
      } catch (Exception var3) {
         Exception exception = var3;
         LOGGER.error("Couldn't save entities", exception);
      }

   }

   private void saveChunkData(bam chunkIn) {
      try {
         chunkIn.setLastSaveTime(this.world.getTotalWorldTime());
         this.chunkLoader.saveChunk(this.world, chunkIn);
      } catch (IOException var3) {
         IOException ioexception = var3;
         LOGGER.error("Couldn't save chunk", ioexception);
      } catch (bgf var4) {
         bgf minecraftexception = var4;
         LOGGER.error("Couldn't save chunk; already in use by another instance of Minecraft?", minecraftexception);
      }

   }

   public boolean saveChunks(boolean all) {
      int i = 0;
      List<bam> list = Lists.newArrayList(this.loadedChunks.values());

      for(int j = 0; j < list.size(); ++j) {
         bam chunk = (bam)list.get(j);
         if (all) {
            this.saveChunkExtraData(chunk);
         }

         if (chunk.needsSaving(all)) {
            this.saveChunkData(chunk);
            chunk.setModified(false);
            ++i;
            if (i == 24 && !all) {
               return false;
            }
         }
      }

      return true;
   }

   public void flushToDisk() {
      this.chunkLoader.flush();
   }

   public boolean tick() {
      if (!this.world.disableLevelSaving) {
         if (!this.droppedChunks.isEmpty()) {
            Iterator<Long> iterator = this.droppedChunks.iterator();

            for(int i = 0; i < 100 && iterator.hasNext(); iterator.remove()) {
               Long olong = (Long)iterator.next();
               bam chunk = (bam)this.loadedChunks.get(olong);
               if (chunk != null && chunk.unloadQueued) {
                  chunk.onUnload();
                  this.saveChunkData(chunk);
                  this.saveChunkExtraData(chunk);
                  this.loadedChunks.remove(olong);
                  ++i;
               }
            }
         }

         this.chunkLoader.chunkTick();
      }

      return false;
   }

   public boolean canSave() {
      return !this.world.disableLevelSaving;
   }

   public String makeString() {
      return "ServerChunkCache: " + this.loadedChunks.size() + " Drop: " + this.droppedChunks.size();
   }

   public List<Zg> getPossibleCreatures(IC creatureType, BlockPos pos) {
      return this.chunkGenerator.getPossibleCreatures(creatureType, pos);
   }

   @Nullable
   public BlockPos getNearestStructurePos(bij worldIn, String structureName, BlockPos position, boolean findUnexplored) {
      return this.chunkGenerator.getNearestStructurePos(worldIn, structureName, position, findUnexplored);
   }

   public boolean isInsideStructure(bij worldIn, String structureName, BlockPos pos) {
      return this.chunkGenerator.isInsideStructure(worldIn, structureName, pos);
   }

   public int getLoadedChunkCount() {
      return this.loadedChunks.size();
   }

   public boolean chunkExists(int x, int z) {
      return this.loadedChunks.containsKey(ChunkPos.asLong(x, z));
   }

   public boolean isChunkGeneratedAt(int x, int z) {
      return this.loadedChunks.containsKey(ChunkPos.asLong(x, z)) || this.chunkLoader.isChunkGeneratedAt(x, z);
   }
}
