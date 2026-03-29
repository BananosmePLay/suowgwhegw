package neo;

import java.util.List;
import java.util.Random;

public class bdi extends beM {
   public bdi() {
   }

   public bdi(bij worldIn, Random random, int chunkX, int chunkZ) {
      super(chunkX, chunkZ);
      bei structurenetherbridgepieces$start = new bei(random, (chunkX << 4) + 2, (chunkZ << 4) + 2);
      this.components.add(structurenetherbridgepieces$start);
      structurenetherbridgepieces$start.buildComponent(structurenetherbridgepieces$start, this.components, random);
      List<bdB> list = structurenetherbridgepieces$start.pendingChildren;

      while(!list.isEmpty()) {
         int i = random.nextInt(list.size());
         bdB structurecomponent = (bdB)list.remove(i);
         structurecomponent.buildComponent(structurenetherbridgepieces$start, this.components, random);
      }

      this.updateBoundingBox();
      this.setRandomHeight(worldIn, random, 48, 70);
   }
}
