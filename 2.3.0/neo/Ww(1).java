package neo;

public class Ww extends Wu {
   public Ww(String name) {
      super(name);
   }

   public boolean isReadOnly() {
      return true;
   }

   public Wn getRenderType() {
      return Wn.HEARTS;
   }
}
