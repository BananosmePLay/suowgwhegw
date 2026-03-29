package neo;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class bdu extends beM {
   private boolean hasMoreThanTwoComponents;

   public bdu() {
   }

   public bdu(bij worldIn, Random rand, int x, int z, int size) {
      super(x, z);
      List<bfs> list = bfz.getStructureVillageWeightedPieceList(rand, size);
      bfu structurevillagepieces$start = new bfu(worldIn.getBiomeProvider(), 0, rand, (x << 4) + 2, (z << 4) + 2, list, size);
      this.components.add(structurevillagepieces$start);
      structurevillagepieces$start.buildComponent(structurevillagepieces$start, this.components, rand);
      List<bdB> list1 = structurevillagepieces$start.pendingRoads;
      List<bdB> list2 = structurevillagepieces$start.pendingHouses;

      int k;
      while(!list1.isEmpty() || !list2.isEmpty()) {
         bdB structurecomponent;
         if (list1.isEmpty()) {
            k = rand.nextInt(list2.size());
            structurecomponent = (bdB)list2.remove(k);
            structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
         } else {
            k = rand.nextInt(list1.size());
            structurecomponent = (bdB)list1.remove(k);
            structurecomponent.buildComponent(structurevillagepieces$start, this.components, rand);
         }
      }

      this.updateBoundingBox();
      k = 0;
      Iterator var13 = this.components.iterator();

      while(var13.hasNext()) {
         bdB structurecomponent1 = (bdB)var13.next();
         if (!(structurecomponent1 instanceof bft)) {
            ++k;
         }
      }

      this.hasMoreThanTwoComponents = k > 2;
   }

   public boolean isSizeableStructure() {
      return this.hasMoreThanTwoComponents;
   }

   public void writeToNBT(QQ tagCompound) {
      super.writeToNBT(tagCompound);
      tagCompound.setBoolean("Valid", this.hasMoreThanTwoComponents);
   }

   public void readFromNBT(QQ tagCompound) {
      super.readFromNBT(tagCompound);
      this.hasMoreThanTwoComponents = tagCompound.getBoolean("Valid");
   }
}
