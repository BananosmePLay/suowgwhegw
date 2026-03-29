package neo;

import java.util.ArrayList;

public class Bk extends ArrayList<Qy> {
   public static final int HOTBAR_SIZE = MJ.getHotbarSize();

   public Bk() {
      this.ensureCapacity(HOTBAR_SIZE);

      for(int i = 0; i < HOTBAR_SIZE; ++i) {
         this.add(Qy.EMPTY);
      }

   }

   public QW createTag() {
      QW nbttaglist = new QW();

      for(int i = 0; i < HOTBAR_SIZE; ++i) {
         nbttaglist.appendTag(((Qy)this.get(i)).writeToNBT(new QQ()));
      }

      return nbttaglist;
   }

   public void fromTag(QW tag) {
      for(int i = 0; i < HOTBAR_SIZE; ++i) {
         this.set(i, new Qy(tag.getCompoundTagAt(i)));
      }

   }

   public boolean isEmpty() {
      for(int i = 0; i < HOTBAR_SIZE; ++i) {
         if (!((Qy)this.get(i)).isEmpty()) {
            return false;
         }
      }

      return true;
   }
}
