package neo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class bod {
   private int blockAliasId;
   private biZ[] matchBlocks;

   public bod(int blockAliasId, biZ[] matchBlocks) {
      this.blockAliasId = blockAliasId;
      this.matchBlocks = matchBlocks;
   }

   public int getBlockAliasId() {
      return this.blockAliasId;
   }

   public boolean matches(int id, int metadata) {
      for(int i = 0; i < this.matchBlocks.length; ++i) {
         biZ matchblock = this.matchBlocks[i];
         if (matchblock.matches(id, metadata)) {
            return true;
         }
      }

      return false;
   }

   public int[] getMatchBlockIds() {
      Set<Integer> set = new HashSet();

      for(int i = 0; i < this.matchBlocks.length; ++i) {
         biZ matchblock = this.matchBlocks[i];
         int j = matchblock.getBlockId();
         set.add(j);
      }

      Integer[] ainteger = (Integer[])((Integer[])set.toArray(new Integer[set.size()]));
      int[] aint = XH.toPrimitive(ainteger);
      return aint;
   }

   public biZ[] getMatchBlocks(int matchBlockId) {
      List<biZ> list = new ArrayList();

      for(int i = 0; i < this.matchBlocks.length; ++i) {
         biZ matchblock = this.matchBlocks[i];
         if (matchblock.getBlockId() == matchBlockId) {
            list.add(matchblock);
         }
      }

      biZ[] amatchblock = (biZ[])((biZ[])list.toArray(new biZ[list.size()]));
      return amatchblock;
   }

   public String toString() {
      return "block." + this.blockAliasId + "=" + XH.arrayToString((Object[])this.matchBlocks);
   }
}
