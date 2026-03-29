package neo;

import com.google.common.collect.Lists;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class BN extends IOException {
   private final List<BM> entries = Lists.newArrayList();
   private final String message;

   public BN(String messageIn) {
      this.entries.add(new BM());
      this.message = messageIn;
   }

   public BN(String messageIn, Throwable cause) {
      super(cause);
      this.entries.add(new BM());
      this.message = messageIn;
   }

   public void prependJsonKey(String key) {
      BM.access$100((BM)this.entries.get(0), key);
   }

   public void setFilenameAndFlush(String filenameIn) {
      BM.access$202((BM)this.entries.get(0), filenameIn);
      this.entries.add(0, new BM());
   }

   public String getMessage() {
      return "Invalid " + this.entries.get(this.entries.size() - 1) + ": " + this.message;
   }

   public static BN forException(Exception exception) {
      if (exception instanceof BN) {
         return (BN)exception;
      } else {
         String s = exception.getMessage();
         if (exception instanceof FileNotFoundException) {
            s = "File not found";
         }

         return new BN(s, exception);
      }
   }
}
