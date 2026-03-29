package neo;

import com.google.common.collect.Lists;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class BM {
   private String filename;
   private final List<String> jsonKeys;

   private BM() {
      this.jsonKeys = Lists.newArrayList();
   }

   private void addJsonKey(String key) {
      this.jsonKeys.add(0, key);
   }

   public String getJsonKeys() {
      return StringUtils.join(this.jsonKeys, "->");
   }

   public String toString() {
      if (this.filename != null) {
         return this.jsonKeys.isEmpty() ? this.filename : this.filename + " " + this.getJsonKeys();
      } else {
         return this.jsonKeys.isEmpty() ? "(Unknown file)" : "(Unknown file) " + this.getJsonKeys();
      }
   }

   // $FF: synthetic method
   BM(Object x0) {
      this();
   }

   // $FF: synthetic method
   static void access$100(BM x0, String x1) {
      x0.addJsonKey(x1);
   }

   // $FF: synthetic method
   static String access$202(BM x0, String x1) {
      return x0.filename = x1;
   }
}
