package neo;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import java.util.ArrayList;
import java.util.List;

public class bjQ {
   private Int2ObjectMap<bjO> map = new Int2ObjectOpenHashMap();
   private List<bjO> list = new ArrayList();
   private boolean dirty = false;

   public bjQ() {
   }

   public bjO put(int id, bjO dynamicLight) {
      bjO dynamiclight = (bjO)this.map.put(id, dynamicLight);
      this.setDirty();
      return dynamiclight;
   }

   public bjO get(int id) {
      return (bjO)this.map.get(id);
   }

   public int size() {
      return this.map.size();
   }

   public bjO remove(int id) {
      bjO dynamiclight = (bjO)this.map.remove(id);
      if (dynamiclight != null) {
         this.setDirty();
      }

      return dynamiclight;
   }

   public void clear() {
      this.map.clear();
      this.list.clear();
      this.setDirty();
   }

   private void setDirty() {
      this.dirty = true;
   }

   public List<bjO> valueList() {
      if (this.dirty) {
         this.list.clear();
         this.list.addAll(this.map.values());
         this.dirty = false;
      }

      return this.list;
   }
}
