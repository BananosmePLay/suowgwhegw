package neo;

public enum IQ {
   OAK(fk.OAK.getMetadata(), "oak"),
   SPRUCE(fk.SPRUCE.getMetadata(), "spruce"),
   BIRCH(fk.BIRCH.getMetadata(), "birch"),
   JUNGLE(fk.JUNGLE.getMetadata(), "jungle"),
   ACACIA(fk.ACACIA.getMetadata(), "acacia"),
   DARK_OAK(fk.DARK_OAK.getMetadata(), "dark_oak");

   private final String name;
   private final int metadata;

   private IQ(int metadataIn, String nameIn) {
      this.name = nameIn;
      this.metadata = metadataIn;
   }

   public String getName() {
      return this.name;
   }

   public int getMetadata() {
      return this.metadata;
   }

   public String toString() {
      return this.name;
   }

   public static IQ byId(int id) {
      if (id < 0 || id >= values().length) {
         id = 0;
      }

      return values()[id];
   }

   public static IQ getTypeFromString(String nameIn) {
      for(int i = 0; i < values().length; ++i) {
         if (values()[i].getName().equals(nameIn)) {
            return values()[i];
         }
      }

      return values()[0];
   }
}
