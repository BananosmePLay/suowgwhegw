package neo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class baF extends ByteArrayOutputStream {
   private final int chunkX;
   private final int chunkZ;
   // $FF: synthetic field
   final baG this$0;

   public baF(baG this$0, int x, int z) {
      super(8096);
      this.this$0 = this$0;
      this.chunkX = x;
      this.chunkZ = z;
   }

   public void close() throws IOException {
      this.this$0.write(this.chunkX, this.chunkZ, this.buf, this.count);
   }
}
