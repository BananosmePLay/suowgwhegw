package neo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class QN extends QH {
   private byte[] data;

   QN() {
   }

   public QN(byte[] data) {
      this.data = data;
   }

   public QN(List<Byte> p_i47529_1_) {
      this(toArray(p_i47529_1_));
   }

   private static byte[] toArray(List<Byte> p_193589_0_) {
      byte[] abyte = new byte[p_193589_0_.size()];

      for(int i = 0; i < p_193589_0_.size(); ++i) {
         Byte obyte = (Byte)p_193589_0_.get(i);
         abyte[i] = obyte == null ? 0 : obyte;
      }

      return abyte;
   }

   void write(DataOutput output) throws IOException {
      output.writeInt(this.data.length);
      output.write(this.data);
   }

   void read(DataInput input, int depth, QL sizeTracker) throws IOException {
      sizeTracker.read(192L);
      int i = input.readInt();
      sizeTracker.read((long)(8 * i));
      this.data = new byte[i];
      input.readFully(this.data);
   }

   public byte getId() {
      return 7;
   }

   public String toString() {
      StringBuilder stringbuilder = new StringBuilder("[B;");

      for(int i = 0; i < this.data.length; ++i) {
         if (i != 0) {
            stringbuilder.append(',');
         }

         stringbuilder.append(this.data[i]).append('B');
      }

      return stringbuilder.append(']').toString();
   }

   public QH copy() {
      byte[] abyte = new byte[this.data.length];
      System.arraycopy(this.data, 0, abyte, 0, this.data.length);
      return new QN(abyte);
   }

   public boolean equals(Object p_equals_1_) {
      return super.equals(p_equals_1_) && Arrays.equals(this.data, ((QN)p_equals_1_).data);
   }

   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.data);
   }

   public byte[] getByteArray() {
      return this.data;
   }
}
