package neo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class QU extends QJ {
   private int data;

   QU() {
   }

   public QU(int data) {
      this.data = data;
   }

   void write(DataOutput output) throws IOException {
      output.writeInt(this.data);
   }

   void read(DataInput input, int depth, QL sizeTracker) throws IOException {
      sizeTracker.read(96L);
      this.data = input.readInt();
   }

   public byte getId() {
      return 3;
   }

   public String toString() {
      return String.valueOf(this.data);
   }

   public QU copy() {
      return new QU(this.data);
   }

   public boolean equals(Object p_equals_1_) {
      return super.equals(p_equals_1_) && this.data == ((QU)p_equals_1_).data;
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
      return (short)(this.data & '\uffff');
   }

   public byte getByte() {
      return (byte)(this.data & 255);
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
