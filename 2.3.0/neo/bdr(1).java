package neo;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectIterator;
import java.util.Iterator;
import java.util.Random;
import javax.annotation.Nullable;
import net.minecraft.util.ReportedException;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

public abstract class bdr extends bcM {
   private bds structureData;
   protected Long2ObjectMap<beM> structureMap = new Long2ObjectOpenHashMap(1024);

   public bdr() {
   }

   public abstract String getStructureName();

   protected final synchronized void recursiveGenerate(bij worldIn, final int chunkX, final int chunkZ, int originalX, int originalZ, ban chunkPrimerIn) {
      this.initializeStructureData(worldIn);
      if (!this.structureMap.containsKey(ChunkPos.asLong(chunkX, chunkZ))) {
         this.rand.nextInt();

         try {
            if (this.canSpawnStructureAtCoords(chunkX, chunkZ)) {
               beM structurestart = this.getStructureStart(chunkX, chunkZ);
               this.structureMap.put(ChunkPos.asLong(chunkX, chunkZ), structurestart);
               if (structurestart.isSizeableStructure()) {
                  this.setStructureStart(chunkX, chunkZ, structurestart);
               }
            }
         } catch (Throwable var10) {
            Throwable throwable = var10;
            Er crashreport = Er.makeCrashReport(throwable, "Exception preparing structure feature");
            Ey crashreportcategory = crashreport.makeCategory("Feature being prepared");
            crashreportcategory.addDetail("Is feature chunk", new Ez<String>() {
               public String call() throws Exception {
                  return bdr.this.canSpawnStructureAtCoords(chunkX, chunkZ) ? "True" : "False";
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            crashreportcategory.addCrashSection("Chunk location", String.format("%d,%d", chunkX, chunkZ));
            crashreportcategory.addDetail("Chunk pos hash", new Ez<String>() {
               public String call() throws Exception {
                  return String.valueOf(ChunkPos.asLong(chunkX, chunkZ));
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            crashreportcategory.addDetail("Structure type", new Ez<String>() {
               public String call() throws Exception {
                  return bdr.this.getClass().getCanonicalName();
               }

               // $FF: synthetic method
               // $FF: bridge method
               public Object call() throws Exception {
                  return this.call();
               }
            });
            throw new ReportedException(crashreport);
         }
      }

   }

   public synchronized boolean generateStructure(bij worldIn, Random randomIn, ChunkPos chunkCoord) {
      this.initializeStructureData(worldIn);
      int i = (chunkCoord.x << 4) + 8;
      int j = (chunkCoord.z << 4) + 8;
      boolean flag = false;
      ObjectIterator objectiterator = this.structureMap.values().iterator();

      while(objectiterator.hasNext()) {
         beM structurestart = (beM)objectiterator.next();
         if (structurestart.isSizeableStructure() && structurestart.isValidForPostProcess(chunkCoord) && structurestart.getBoundingBox().intersectsWith(i, j, i + 15, j + 15)) {
            structurestart.generateStructure(worldIn, randomIn, new bdy(i, j, i + 15, j + 15));
            structurestart.notifyPostProcessAt(chunkCoord);
            flag = true;
            this.setStructureStart(structurestart.getChunkPosX(), structurestart.getChunkPosZ(), structurestart);
         }
      }

      return flag;
   }

   public boolean isInsideStructure(BlockPos pos) {
      if (this.world == null) {
         return false;
      } else {
         this.initializeStructureData(this.world);
         return this.getStructureAt(pos) != null;
      }
   }

   @Nullable
   protected beM getStructureAt(BlockPos pos) {
      ObjectIterator objectiterator = this.structureMap.values().iterator();

      while(true) {
         beM structurestart;
         do {
            do {
               if (!objectiterator.hasNext()) {
                  return null;
               }

               structurestart = (beM)objectiterator.next();
            } while(!structurestart.isSizeableStructure());
         } while(!structurestart.getBoundingBox().isVecInside(pos));

         Iterator<bdB> iterator = structurestart.getComponents().iterator();

         while(iterator.hasNext()) {
            bdB structurecomponent = (bdB)iterator.next();
            if (structurecomponent.getBoundingBox().isVecInside(pos)) {
               return structurestart;
            }
         }
      }
   }

   public boolean isPositionInStructure(bij worldIn, BlockPos pos) {
      this.initializeStructureData(worldIn);
      ObjectIterator objectiterator = this.structureMap.values().iterator();

      beM structurestart;
      do {
         if (!objectiterator.hasNext()) {
            return false;
         }

         structurestart = (beM)objectiterator.next();
      } while(!structurestart.isSizeableStructure() || !structurestart.getBoundingBox().isVecInside(pos));

      return true;
   }

   @Nullable
   public abstract BlockPos getNearestStructurePos(bij var1, BlockPos var2, boolean var3);

   protected void initializeStructureData(bij worldIn) {
      if (this.structureData == null && worldIn != null) {
         this.structureData = (bds)worldIn.loadData(bds.class, this.getStructureName());
         if (this.structureData == null) {
            this.structureData = new bds(this.getStructureName());
            worldIn.setData(this.getStructureName(), this.structureData);
         } else {
            QQ nbttagcompound = this.structureData.getTagCompound();
            Iterator var3 = nbttagcompound.getKeySet().iterator();

            while(var3.hasNext()) {
               String s = (String)var3.next();
               QH nbtbase = nbttagcompound.getTag(s);
               if (nbtbase.getId() == 10) {
                  QQ nbttagcompound1 = (QQ)nbtbase;
                  if (nbttagcompound1.hasKey("ChunkX") && nbttagcompound1.hasKey("ChunkZ")) {
                     int i = nbttagcompound1.getInteger("ChunkX");
                     int j = nbttagcompound1.getInteger("ChunkZ");
                     beM structurestart = bdt.getStructureStart(nbttagcompound1, worldIn);
                     if (structurestart != null) {
                        this.structureMap.put(ChunkPos.asLong(i, j), structurestart);
                     }
                  }
               }
            }
         }
      }

   }

   private void setStructureStart(int chunkX, int chunkZ, beM start) {
      this.structureData.writeInstance(start.writeStructureComponentsToNBT(chunkX, chunkZ), chunkX, chunkZ);
      this.structureData.markDirty();
   }

   protected abstract boolean canSpawnStructureAtCoords(int var1, int var2);

   protected abstract beM getStructureStart(int var1, int var2);

   protected static BlockPos findNearestStructurePosBySpacing(bij worldIn, bdr structureType, BlockPos startPos, int distanceStep, int stepOffset, int randomSeedZ, boolean addExtraRandomness, int maxAttempts, boolean findUnexplored) {
      int i = startPos.getX() >> 4;
      int j = startPos.getZ() >> 4;
      int k = 0;

      for(Random random = new Random(); k <= maxAttempts; ++k) {
         for(int l = -k; l <= k; ++l) {
            boolean flag = l == -k || l == k;

            for(int i1 = -k; i1 <= k; ++i1) {
               boolean flag1 = i1 == -k || i1 == k;
               if (flag || flag1) {
                  int j1 = i + distanceStep * l;
                  int k1 = j + distanceStep * i1;
                  if (j1 < 0) {
                     j1 -= distanceStep - 1;
                  }

                  if (k1 < 0) {
                     k1 -= distanceStep - 1;
                  }

                  int l1 = j1 / distanceStep;
                  int i2 = k1 / distanceStep;
                  Random random1 = worldIn.setRandomSeed(l1, i2, randomSeedZ);
                  l1 *= distanceStep;
                  i2 *= distanceStep;
                  if (addExtraRandomness) {
                     l1 += (random1.nextInt(distanceStep - stepOffset) + random1.nextInt(distanceStep - stepOffset)) / 2;
                     i2 += (random1.nextInt(distanceStep - stepOffset) + random1.nextInt(distanceStep - stepOffset)) / 2;
                  } else {
                     l1 += random1.nextInt(distanceStep - stepOffset);
                     i2 += random1.nextInt(distanceStep - stepOffset);
                  }

                  bcM.setupChunkSeed(worldIn.getSeed(), random, l1, i2);
                  random.nextInt();
                  if (structureType.canSpawnStructureAtCoords(l1, i2)) {
                     if (!findUnexplored || !worldIn.isChunkGeneratedAt(l1, i2)) {
                        return new BlockPos((l1 << 4) + 8, 64, (i2 << 4) + 8);
                     }
                  } else if (k == 0) {
                     break;
                  }
               }
            }

            if (k == 0) {
               break;
            }
         }
      }

      return null;
   }
}
