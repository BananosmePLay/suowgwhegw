package neo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public class yX {
   public final int pngWidth;
   public final int pngHeight;

   public yX(InputStream stream) throws IOException {
      DataInputStream datainputstream = new DataInputStream(stream);
      if (datainputstream.readLong() != -8552249625308161526L) {
         throw new IOException("Bad PNG Signature");
      } else if (datainputstream.readInt() != 13) {
         throw new IOException("Bad length for IHDR chunk!");
      } else if (datainputstream.readInt() != 1229472850) {
         throw new IOException("Bad type for IHDR chunk!");
      } else {
         this.pngWidth = datainputstream.readInt();
         this.pngHeight = datainputstream.readInt();
         IOUtils.closeQuietly(datainputstream);
      }
   }

   public static yX makeFromResource(Az resource) throws IOException {
      yX pngsizeinfo;
      try {
         pngsizeinfo = new yX(resource.getInputStream());
      } finally {
         IOUtils.closeQuietly(resource);
      }

      return pngsizeinfo;
   }
}
