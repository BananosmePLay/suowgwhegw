package neo;

public class Vt {
   private final String name;
   private final int protocol;

   public Vt(String nameIn, int protocolIn) {
      this.name = nameIn;
      this.protocol = protocolIn;
   }

   public String getName() {
      return this.name;
   }

   public int getProtocol() {
      return this.protocol;
   }
}
