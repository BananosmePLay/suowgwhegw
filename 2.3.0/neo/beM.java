package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.math.ChunkPos;

public abstract class beM {
   protected List<bdB> components = Lists.newLinkedList();
   protected bdy boundingBox;
   private int chunkPosX;
   private int chunkPosZ;

   public beM() {
   }

   public beM(int chunkX, int chunkZ) {
      this.chunkPosX = chunkX;
      this.chunkPosZ = chunkZ;
   }

   public bdy getBoundingBox() {
      return this.boundingBox;
   }

   public List<bdB> getComponents() {
      return this.components;
   }

   public void generateStructure(bij worldIn, Random rand, bdy structurebb) {
      Iterator<bdB> iterator = this.components.iterator();

      while(iterator.hasNext()) {
         bdB structurecomponent = (bdB)iterator.next();
         if (structurecomponent.getBoundingBox().intersectsWith(structurebb) && !structurecomponent.addComponentParts(worldIn, rand, structurebb)) {
            iterator.remove();
         }
      }

   }

   protected void updateBoundingBox() {
      this.boundingBox = bdy.getNewBoundingBox();
      Iterator var1 = this.components.iterator();

      while(var1.hasNext()) {
         bdB structurecomponent = (bdB)var1.next();
         this.boundingBox.expandTo(structurecomponent.getBoundingBox());
      }

   }

   public QQ writeStructureComponentsToNBT(int chunkX, int chunkZ) {
      QQ nbttagcompound = new QQ();
      nbttagcompound.setString("id", bdt.getStructureStartName(this));
      nbttagcompound.setInteger("ChunkX", chunkX);
      nbttagcompound.setInteger("ChunkZ", chunkZ);
      nbttagcompound.setTag("BB", this.boundingBox.toNBTTagIntArray());
      QW nbttaglist = new QW();
      Iterator var5 = this.components.iterator();

      while(var5.hasNext()) {
         bdB structurecomponent = (bdB)var5.next();
         nbttaglist.appendTag(structurecomponent.createStructureBaseNBT());
      }

      nbttagcompound.setTag("Children", nbttaglist);
      this.writeToNBT(nbttagcompound);
      return nbttagcompound;
   }

   public void writeToNBT(QQ tagCompound) {
   }

   public void readStructureComponentsFromNBT(bij worldIn, QQ tagCompound) {
      this.chunkPosX = tagCompound.getInteger("ChunkX");
      this.chunkPosZ = tagCompound.getInteger("ChunkZ");
      if (tagCompound.hasKey("BB")) {
         this.boundingBox = new bdy(tagCompound.getIntArray("BB"));
      }

      QW nbttaglist = tagCompound.getTagList("Children", 10);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         this.components.add(bdt.getStructureComponent(nbttaglist.getCompoundTagAt(i), worldIn));
      }

      this.readFromNBT(tagCompound);
   }

   public void readFromNBT(QQ tagCompound) {
   }

   protected void markAvailableHeight(bij worldIn, Random rand, int p_75067_3_) {
      int i = worldIn.getSeaLevel() - p_75067_3_;
      int j = this.boundingBox.getYSize() + 1;
      if (j < i) {
         j += rand.nextInt(i - j);
      }

      int k = j - this.boundingBox.maxY;
      this.boundingBox.offset(0, k, 0);
      Iterator var7 = this.components.iterator();

      while(var7.hasNext()) {
         bdB structurecomponent = (bdB)var7.next();
         structurecomponent.offset(0, k, 0);
      }

   }

   protected void setRandomHeight(bij worldIn, Random rand, int p_75070_3_, int p_75070_4_) {
      int i = p_75070_4_ - p_75070_3_ + 1 - this.boundingBox.getYSize();
      int j;
      if (i > 1) {
         j = p_75070_3_ + rand.nextInt(i);
      } else {
         j = p_75070_3_;
      }

      int k = j - this.boundingBox.minY;
      this.boundingBox.offset(0, k, 0);
      Iterator var8 = this.components.iterator();

      while(var8.hasNext()) {
         bdB structurecomponent = (bdB)var8.next();
         structurecomponent.offset(0, k, 0);
      }

   }

   public boolean isSizeableStructure() {
      return true;
   }

   public boolean isValidForPostProcess(ChunkPos pair) {
      return true;
   }

   public void notifyPostProcessAt(ChunkPos pair) {
   }

   public int getChunkPosX() {
      return this.chunkPosX;
   }

   public int getChunkPosZ() {
      return this.chunkPosZ;
   }
}
