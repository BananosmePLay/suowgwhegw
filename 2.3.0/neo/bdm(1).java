package neo;

import java.util.List;
import java.util.Random;

public class bdm extends beM {
   public bdm() {
   }

   public bdm(bij worldIn, Random random, int chunkX, int chunkZ) {
      super(chunkX, chunkZ);
      bfh.prepareStructurePieces();
      bfb structurestrongholdpieces$stairs2 = new bfb(0, random, (chunkX << 4) + 2, (chunkZ << 4) + 2);
      this.components.add(structurestrongholdpieces$stairs2);
      structurestrongholdpieces$stairs2.buildComponent(structurestrongholdpieces$stairs2, this.components, random);
      List<bdB> list = structurestrongholdpieces$stairs2.pendingChildren;

      while(!list.isEmpty()) {
         int i = random.nextInt(list.size());
         bdB structurecomponent = (bdB)list.remove(i);
         structurecomponent.buildComponent(structurestrongholdpieces$stairs2, this.components, random);
      }

      this.updateBoundingBox();
      this.markAvailableHeight(worldIn, random, 10);
   }
}
