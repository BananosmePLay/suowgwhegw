package neo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class bnd {
   private List<rK> listQuads = new ArrayList();
   private List<in> listBlockStates = new ArrayList();
   private List<rK> listQuadsSingle = Arrays.asList();

   public bnd() {
   }

   public void addQuad(rK quad, in blockState) {
      if (quad != null) {
         this.listQuads.add(quad);
         this.listBlockStates.add(blockState);
      }

   }

   public int size() {
      return this.listQuads.size();
   }

   public rK getQuad(int index) {
      return (rK)this.listQuads.get(index);
   }

   public in getBlockState(int index) {
      return index >= 0 && index < this.listBlockStates.size() ? (in)this.listBlockStates.get(index) : Nk.AIR.getDefaultState();
   }

   public List<rK> getListQuadsSingle(rK quad) {
      this.listQuadsSingle.set(0, quad);
      return this.listQuadsSingle;
   }

   public void clear() {
      this.listQuads.clear();
      this.listBlockStates.clear();
   }
}
