package neo;

public class Wu implements Wo {
   private final String dummyName;

   public Wu(String name) {
      this.dummyName = name;
      Wo.INSTANCES.put(name, this);
   }

   public String getName() {
      return this.dummyName;
   }

   public boolean isReadOnly() {
      return false;
   }

   public Wn getRenderType() {
      return Wn.INTEGER;
   }
}
