package neo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import net.minecraft.util.math.MathHelper;

public class QR extends QJ {
   private double data;

   QR() {
   }

   public QR(double data) {
      this.data = data;
   }

   void write(DataOutput output) throws IOException {
      output.writeDouble(this.data);
   }

   void read(DataInput input, int depth, QL sizeTracker) throws IOException {
      sizeTracker.read(128L);
      this.data = input.readDouble();
   }

   public byte getId() {
      return 6;
   }

   public String toString() {
      return this.data + "d";
   }

   public QR copy() {
      return new QR(this.data);
   }

   public boolean equals(Object p_equals_1_) {
      return super.equals(p_equals_1_) && this.data == ((QR)p_equals_1_).data;
   }

   public int hashCode() {
      long i = Double.doubleToLongBits(this.data);
      return super.hashCode() ^ (int)(i ^ i >>> 32);
   }

   public long getLong() {
      return (long)Math.floor(this.data);
   }

   public int getInt() {
      return MathHelper.floor(this.data);
   }

   public short getShort() {
      return (short)(MathHelper.floor(this.data) & '\uffff');
   }

   public byte getByte() {
      return (byte)(MathHelper.floor(this.data) & 255);
   }

   public double getDouble() {
      return this.data;
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
