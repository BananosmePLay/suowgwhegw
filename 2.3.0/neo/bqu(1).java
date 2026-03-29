package neo;

import java.util.List;

public class bqu implements bqv<Object> {
   private List<Object> list;
   private int index;
   private boolean hasNext;

   public bqu() {
   }

   public void setList(List<Object> list) {
      if (this.hasNext) {
         throw new RuntimeException("Iterator still used, oldList: " + this.list + ", newList: " + list);
      } else {
         this.list = list;
         this.index = 0;
         this.hasNext = list != null && this.index < list.size();
      }
   }

   public Object next() {
      if (!this.hasNext) {
         return null;
      } else {
         Object object = this.list.get(this.index);
         ++this.index;
         this.hasNext = this.index < this.list.size();
         return object;
      }
   }

   public boolean hasNext() {
      if (!this.hasNext) {
         bqw.access$000(this);
         return false;
      } else {
         return this.hasNext;
      }
   }
}
