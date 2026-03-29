package neo;

import com.google.common.collect.Lists;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class QW extends QH {
   private static final Logger LOGGER = LogManager.getLogger();
   private List<QH> tagList = Lists.newArrayList();
   private byte tagType = 0;

   public QW() {
   }

   void write(DataOutput output) throws IOException {
      if (this.tagList.isEmpty()) {
         this.tagType = 0;
      } else {
         this.tagType = ((QH)this.tagList.get(0)).getId();
      }

      output.writeByte(this.tagType);
      output.writeInt(this.tagList.size());

      for(int i = 0; i < this.tagList.size(); ++i) {
         ((QH)this.tagList.get(i)).write(output);
      }

   }

   void read(DataInput input, int depth, QL sizeTracker) throws IOException {
      sizeTracker.read(296L);
      if (depth > 512) {
         throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
      } else {
         this.tagType = input.readByte();
         int i = input.readInt();
         if (this.tagType == 0 && i > 0) {
            throw new RuntimeException("Missing type on ListTag");
         } else {
            sizeTracker.read(32L * (long)i);
            this.tagList = Lists.newArrayListWithCapacity(i);

            for(int j = 0; j < i; ++j) {
               QH nbtbase = QH.create(this.tagType);
               nbtbase.read(input, depth + 1, sizeTracker);
               this.tagList.add(nbtbase);
            }

         }
      }
   }

   public byte getId() {
      return 9;
   }

   public String toString() {
      StringBuilder stringbuilder = new StringBuilder("[");

      for(int i = 0; i < this.tagList.size(); ++i) {
         if (i != 0) {
            stringbuilder.append(',');
         }

         stringbuilder.append(this.tagList.get(i));
      }

      return stringbuilder.append(']').toString();
   }

   public void appendTag(QH nbt) {
      if (nbt.getId() == 0) {
         LOGGER.warn("Invalid TagEnd added to ListTag");
      } else {
         if (this.tagType == 0) {
            this.tagType = nbt.getId();
         } else if (this.tagType != nbt.getId()) {
            LOGGER.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.tagList.add(nbt);
      }

   }

   public void set(int idx, QH nbt) {
      if (nbt.getId() == 0) {
         LOGGER.warn("Invalid TagEnd added to ListTag");
      } else if (idx >= 0 && idx < this.tagList.size()) {
         if (this.tagType == 0) {
            this.tagType = nbt.getId();
         } else if (this.tagType != nbt.getId()) {
            LOGGER.warn("Adding mismatching tag types to tag list");
            return;
         }

         this.tagList.set(idx, nbt);
      } else {
         LOGGER.warn("index out of bounds to set tag in tag list");
      }

   }

   public QH removeTag(int i) {
      return (QH)this.tagList.remove(i);
   }

   public boolean isEmpty() {
      return this.tagList.isEmpty();
   }

   public QQ getCompoundTagAt(int i) {
      if (i >= 0 && i < this.tagList.size()) {
         QH nbtbase = (QH)this.tagList.get(i);
         if (nbtbase.getId() == 10) {
            return (QQ)nbtbase;
         }
      }

      return new QQ();
   }

   public int getIntAt(int p_186858_1_) {
      if (p_186858_1_ >= 0 && p_186858_1_ < this.tagList.size()) {
         QH nbtbase = (QH)this.tagList.get(p_186858_1_);
         if (nbtbase.getId() == 3) {
            return ((QU)nbtbase).getInt();
         }
      }

      return 0;
   }

   public int[] getIntArrayAt(int i) {
      if (i >= 0 && i < this.tagList.size()) {
         QH nbtbase = (QH)this.tagList.get(i);
         if (nbtbase.getId() == 11) {
            return ((QV)nbtbase).getIntArray();
         }
      }

      return new int[0];
   }

   public double getDoubleAt(int i) {
      if (i >= 0 && i < this.tagList.size()) {
         QH nbtbase = (QH)this.tagList.get(i);
         if (nbtbase.getId() == 6) {
            return ((QR)nbtbase).getDouble();
         }
      }

      return 0.0;
   }

   public float getFloatAt(int i) {
      if (i >= 0 && i < this.tagList.size()) {
         QH nbtbase = (QH)this.tagList.get(i);
         if (nbtbase.getId() == 5) {
            return ((QT)nbtbase).getFloat();
         }
      }

      return 0.0F;
   }

   public String getStringTagAt(int i) {
      if (i >= 0 && i < this.tagList.size()) {
         QH nbtbase = (QH)this.tagList.get(i);
         return nbtbase.getId() == 8 ? nbtbase.getString() : nbtbase.toString();
      } else {
         return "";
      }
   }

   public QH get(int idx) {
      return (QH)(idx >= 0 && idx < this.tagList.size() ? (QH)this.tagList.get(idx) : new QS());
   }

   public int tagCount() {
      return this.tagList.size();
   }

   public QW copy() {
      QW nbttaglist = new QW();
      nbttaglist.tagType = this.tagType;
      Iterator var2 = this.tagList.iterator();

      while(var2.hasNext()) {
         QH nbtbase = (QH)var2.next();
         QH nbtbase1 = nbtbase.copy();
         nbttaglist.tagList.add(nbtbase1);
      }

      return nbttaglist;
   }

   public boolean equals(Object p_equals_1_) {
      if (!super.equals(p_equals_1_)) {
         return false;
      } else {
         QW nbttaglist = (QW)p_equals_1_;
         return this.tagType == nbttaglist.tagType && Objects.equals(this.tagList, nbttaglist.tagList);
      }
   }

   public int hashCode() {
      return super.hashCode() ^ this.tagList.hashCode();
   }

   public int getTagType() {
      return this.tagType;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public QH copy() {
      return this.copy();
   }
}
