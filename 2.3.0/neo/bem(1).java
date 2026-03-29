package neo;

import com.google.common.collect.Sets;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.ChunkPos;

public class bem extends beM {
   private final Set<ChunkPos> processed = Sets.newHashSet();
   private boolean wasCreated;

   public bem() {
   }

   public bem(bij worldIn, Random random, int chunkX, int chunkZ) {
      super(chunkX, chunkZ);
      this.create(worldIn, random, chunkX, chunkZ);
   }

   private void create(bij worldIn, Random random, int chunkX, int chunkZ) {
      random.setSeed(worldIn.getSeed());
      long i = random.nextLong();
      long j = random.nextLong();
      long k = (long)chunkX * i;
      long l = (long)chunkZ * j;
      random.setSeed(k ^ l ^ worldIn.getSeed());
      int i1 = chunkX * 16 + 8 - 29;
      int j1 = chunkZ * 16 + 8 - 29;
      EnumFacing enumfacing = EnumFacing.Plane.HORIZONTAL.random(random);
      this.components.add(new bex(random, i1, j1, enumfacing));
      this.updateBoundingBox();
      this.wasCreated = true;
   }

   public void generateStructure(bij worldIn, Random rand, bdy structurebb) {
      if (!this.wasCreated) {
         this.components.clear();
         this.create(worldIn, rand, this.getChunkPosX(), this.getChunkPosZ());
      }

      super.generateStructure(worldIn, rand, structurebb);
   }

   public boolean isValidForPostProcess(ChunkPos pair) {
      return this.processed.contains(pair) ? false : super.isValidForPostProcess(pair);
   }

   public void notifyPostProcessAt(ChunkPos pair) {
      super.notifyPostProcessAt(pair);
      this.processed.add(pair);
   }

   public void writeToNBT(QQ tagCompound) {
      super.writeToNBT(tagCompound);
      QW nbttaglist = new QW();
      Iterator var3 = this.processed.iterator();

      while(var3.hasNext()) {
         ChunkPos chunkpos = (ChunkPos)var3.next();
         QQ nbttagcompound = new QQ();
         nbttagcompound.setInteger("X", chunkpos.x);
         nbttagcompound.setInteger("Z", chunkpos.z);
         nbttaglist.appendTag(nbttagcompound);
      }

      tagCompound.setTag("Processed", nbttaglist);
   }

   public void readFromNBT(QQ tagCompound) {
      super.readFromNBT(tagCompound);
      if (tagCompound.hasKey("Processed", 9)) {
         QW nbttaglist = tagCompound.getTagList("Processed", 10);

         for(int i = 0; i < nbttaglist.tagCount(); ++i) {
            QQ nbttagcompound = nbttaglist.getCompoundTagAt(i);
            this.processed.add(new ChunkPos(nbttagcompound.getInteger("X"), nbttagcompound.getInteger("Z")));
         }
      }

   }
}
