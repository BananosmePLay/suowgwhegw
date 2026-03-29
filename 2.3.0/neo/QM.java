package neo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class QM extends QJ {
   private byte data;

   QM() {
   }

   public QM(byte data) {
      this.data = data;
   }

   void write(DataOutput output) throws IOException {
      output.writeByte(this.data);
   }

   void read(DataInput input, int depth, QL sizeTracker) throws IOException {
      sizeTracker.read(72L);
      this.data = input.readByte();
   }

   public byte getId() {
      return 1;
   }

   public String toString() {
      return this.data + "b";
   }

   public QM copy() {
      return new QM(this.data);
   }

   public boolean equals(Object p_equals_1_) {
      return super.equals(p_equals_1_) && this.data == ((QM)p_equals_1_).data;
   }

   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   public long getLong() {
      return (long)this.data;
   }

   public int getInt() {
      return this.data;
   }

   public short getShort() {
      return (short)this.data;
   }

   public byte getByte() {
      return this.data;
   }

   public double getDouble() {
      return (double)this.data;
   }

   public float getFloat() {
      return (float)this.data;
   }

   // $FF: synthetic method
   // $FF: bridge method
   public QH copy() {
      return this.copy();
   }
}
