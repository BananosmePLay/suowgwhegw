package neo;

public enum iO {
   FILE("file"),
   SOUND_EVENT("event");

   private final String name;

   private iO(String nameIn) {
      this.name = nameIn;
   }

   public static iO getByName(String nameIn) {
      iO[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         iO sound$type = var1[var3];
         if (sound$type.name.equals(nameIn)) {
            return sound$type;
         }
      }

      return null;
   }
}
