package neo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import net.minecraft.util.ResourceLocation;

class Ar extends InputStream {
   private final InputStream inputStream;
   private final String message;
   private boolean isClosed;

   public Ar(InputStream p_i46093_1_, ResourceLocation location, String resourcePack) {
      this.inputStream = p_i46093_1_;
      ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
      (new Exception()).printStackTrace(new PrintStream(bytearrayoutputstream));
      this.message = "Leaked resource: '" + location + "' loaded from pack: '" + resourcePack + "'\n" + bytearrayoutputstream;
   }

   public void close() throws IOException {
      this.inputStream.close();
      this.isClosed = true;
   }

   protected void finalize() throws Throwable {
      if (!this.isClosed) {
         As.access$000().warn(this.message);
      }

      super.finalize();
   }

   public int read() throws IOException {
      return this.inputStream.read();
   }
}
