package neo;

import java.util.Iterator;
import javax.annotation.Nullable;
import net.minecraft.util.ObjectIntIdentityMap;

class bfH implements Iterable<in> {
   public static final in DEFAULT_BLOCK_STATE;
   final ObjectIntIdentityMap<in> ids;
   private int lastId;

   private bfH() {
      this.ids = new ObjectIntIdentityMap(16);
   }

   public int idFor(in state) {
      int i = this.ids.get(state);
      if (i == -1) {
         i = this.lastId++;
         this.ids.put(state, i);
      }

      return i;
   }

   @Nullable
   public in stateFor(int id) {
      in iblockstate = (in)this.ids.getByValue(id);
      return iblockstate == null ? DEFAULT_BLOCK_STATE : iblockstate;
   }

   public Iterator<in> iterator() {
      return this.ids.iterator();
   }

   public void addMapping(in p_189956_1_, int p_189956_2_) {
      this.ids.put(p_189956_1_, p_189956_2_);
   }

   // $FF: synthetic method
   bfH(Object x0) {
      this();
   }

   static {
      DEFAULT_BLOCK_STATE = Nk.AIR.getDefaultState();
   }
}
