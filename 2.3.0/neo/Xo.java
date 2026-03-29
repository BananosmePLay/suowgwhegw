package neo;

import com.google.gson.JsonObject;
import java.io.File;
import java.net.SocketAddress;

public class Xo extends Xj<String, Xp> {
   public Xo(File bansFile) {
      super(bansFile);
   }

   protected Xm<String> createEntry(JsonObject entryData) {
      return new Xp(entryData);
   }

   public boolean isBanned(SocketAddress address) {
      String s = this.addressToString(address);
      return this.hasEntry(s);
   }

   public Xp getBanEntry(SocketAddress address) {
      String s = this.addressToString(address);
      return (Xp)this.getEntry(s);
   }

   private String addressToString(SocketAddress address) {
      String s = address.toString();
      if (s.contains("/")) {
         s = s.substring(s.indexOf(47) + 1);
      }

      if (s.contains(":")) {
         s = s.substring(0, s.indexOf(58));
      }

      return s;
   }
}
