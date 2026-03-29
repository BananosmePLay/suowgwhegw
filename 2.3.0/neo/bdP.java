package neo;

import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.util.EnumFacing;

public class bdP extends bdO {
   private final List<bdy> connectedRooms = Lists.newLinkedList();

   public bdP() {
   }

   public bdP(int p_i47137_1_, Random p_i47137_2_, int p_i47137_3_, int p_i47137_4_, bdg p_i47137_5_) {
      super(p_i47137_1_, p_i47137_5_);
      this.mineShaftType = p_i47137_5_;
      this.boundingBox = new bdy(p_i47137_3_, 50, p_i47137_4_, p_i47137_3_ + 7 + p_i47137_2_.nextInt(6), 54 + p_i47137_2_.nextInt(6), p_i47137_4_ + 7 + p_i47137_2_.nextInt(6));
   }

   public void buildComponent(bdB componentIn, List<bdB> listIn, Random rand) {
      int i = this.getComponentType();
      int j = this.boundingBox.getYSize() - 3 - 1;
      if (j <= 0) {
         j = 1;
      }

      int k;
      bdO structurecomponent;
      bdy structureboundingbox3;
      for(k = 0; k < this.boundingBox.getXSize(); k += 4) {
         k += rand.nextInt(this.boundingBox.getXSize());
         if (k + 3 > this.boundingBox.getXSize()) {
            break;
         }

         structurecomponent = bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + k, this.boundingBox.minY + rand.nextInt(j) + 1, this.boundingBox.minZ - 1, EnumFacing.NORTH, i);
         if (structurecomponent != null) {
            structureboundingbox3 = structurecomponent.getBoundingBox();
            this.connectedRooms.add(new bdy(structureboundingbox3.minX, structureboundingbox3.minY, this.boundingBox.minZ, structureboundingbox3.maxX, structureboundingbox3.maxY, this.boundingBox.minZ + 1));
         }
      }

      for(k = 0; k < this.boundingBox.getXSize(); k += 4) {
         k += rand.nextInt(this.boundingBox.getXSize());
         if (k + 3 > this.boundingBox.getXSize()) {
            break;
         }

         structurecomponent = bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX + k, this.boundingBox.minY + rand.nextInt(j) + 1, this.boundingBox.maxZ + 1, EnumFacing.SOUTH, i);
         if (structurecomponent != null) {
            structureboundingbox3 = structurecomponent.getBoundingBox();
            this.connectedRooms.add(new bdy(structureboundingbox3.minX, structureboundingbox3.minY, this.boundingBox.maxZ - 1, structureboundingbox3.maxX, structureboundingbox3.maxY, this.boundingBox.maxZ));
         }
      }

      for(k = 0; k < this.boundingBox.getZSize(); k += 4) {
         k += rand.nextInt(this.boundingBox.getZSize());
         if (k + 3 > this.boundingBox.getZSize()) {
            break;
         }

         structurecomponent = bdR.access$000(componentIn, listIn, rand, this.boundingBox.minX - 1, this.boundingBox.minY + rand.nextInt(j) + 1, this.boundingBox.minZ + k, EnumFacing.WEST, i);
         if (structurecomponent != null) {
            structureboundingbox3 = structurecomponent.getBoundingBox();
            this.connectedRooms.add(new bdy(this.boundingBox.minX, structureboundingbox3.minY, structureboundingbox3.minZ, this.boundingBox.minX + 1, structureboundingbox3.maxY, structureboundingbox3.maxZ));
         }
      }

      for(k = 0; k < this.boundingBox.getZSize(); k += 4) {
         k += rand.nextInt(this.boundingBox.getZSize());
         if (k + 3 > this.boundingBox.getZSize()) {
            break;
         }

         structurecomponent = bdR.access$000(componentIn, listIn, rand, this.boundingBox.maxX + 1, this.boundingBox.minY + rand.nextInt(j) + 1, this.boundingBox.minZ + k, EnumFacing.EAST, i);
         if (structurecomponent != null) {
            structureboundingbox3 = ((bdB)structurecomponent).getBoundingBox();
            this.connectedRooms.add(new bdy(this.boundingBox.maxX - 1, structureboundingbox3.minY, structureboundingbox3.minZ, this.boundingBox.maxX, structureboundingbox3.maxY, structureboundingbox3.maxZ));
         }
      }

   }

   public boolean addComponentParts(bij worldIn, Random randomIn, bdy structureBoundingBoxIn) {
      if (this.isLiquidInStructureBoundingBox(worldIn, structureBoundingBoxIn)) {
         return false;
      } else {
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.minY, this.boundingBox.maxZ, Nk.DIRT.getDefaultState(), Nk.AIR.getDefaultState(), true);
         this.fillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY + 1, this.boundingBox.minZ, this.boundingBox.maxX, Math.min(this.boundingBox.minY + 3, this.boundingBox.maxY), this.boundingBox.maxZ, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         Iterator var4 = this.connectedRooms.iterator();

         while(var4.hasNext()) {
            bdy structureboundingbox = (bdy)var4.next();
            this.fillWithBlocks(worldIn, structureBoundingBoxIn, structureboundingbox.minX, structureboundingbox.maxY - 2, structureboundingbox.minZ, structureboundingbox.maxX, structureboundingbox.maxY, structureboundingbox.maxZ, Nk.AIR.getDefaultState(), Nk.AIR.getDefaultState(), false);
         }

         this.randomlyRareFillWithBlocks(worldIn, structureBoundingBoxIn, this.boundingBox.minX, this.boundingBox.minY + 4, this.boundingBox.minZ, this.boundingBox.maxX, this.boundingBox.maxY, this.boundingBox.maxZ, Nk.AIR.getDefaultState(), false);
         return true;
      }
   }

   public void offset(int x, int y, int z) {
      super.offset(x, y, z);
      Iterator var4 = this.connectedRooms.iterator();

      while(var4.hasNext()) {
         bdy structureboundingbox = (bdy)var4.next();
         structureboundingbox.offset(x, y, z);
      }

   }

   protected void writeStructureToNBT(QQ tagCompound) {
      super.writeStructureToNBT(tagCompound);
      QW nbttaglist = new QW();
      Iterator var3 = this.connectedRooms.iterator();

      while(var3.hasNext()) {
         bdy structureboundingbox = (bdy)var3.next();
         nbttaglist.appendTag(structureboundingbox.toNBTTagIntArray());
      }

      tagCompound.setTag("Entrances", nbttaglist);
   }

   protected void readStructureFromNBT(QQ tagCompound, bfL p_143011_2_) {
      super.readStructureFromNBT(tagCompound, p_143011_2_);
      QW nbttaglist = tagCompound.getTagList("Entrances", 11);

      for(int i = 0; i < nbttaglist.tagCount(); ++i) {
         this.connectedRooms.add(new bdy(nbttaglist.getIntArrayAt(i)));
      }

   }
}
