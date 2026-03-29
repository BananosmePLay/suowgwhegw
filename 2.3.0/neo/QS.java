package neo;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class QS extends QH {
   public QS() {
   }

   void read(DataInput input, int depth, QL sizeTracker) throws IOException {
      sizeTracker.read(64L);
   }

   void write(DataOutput output) throws IOException {
   }

   public byte getId() {
      return 0;
   }

   public String toString() {
      return "END";
   }

   public QS copy() {
      return new QS();
   }

   // $FF: synthetic method
   // $FF: bridge method
   public QH copy() {
      return this.copy();
   }
}
