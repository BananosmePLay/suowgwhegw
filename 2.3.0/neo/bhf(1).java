package neo;

public enum bhf {
   THIS("this"),
   KILLER("killer"),
   KILLER_PLAYER("killer_player");

   private final String targetType;

   private bhf(String type) {
      this.targetType = type;
   }

   public static bhf fromString(String type) {
      bhf[] var1 = values();
      int var2 = var1.length;

      for(int var3 = 0; var3 < var2; ++var3) {
         bhf lootcontext$entitytarget = var1[var3];
         if (lootcontext$entitytarget.targetType.equals(type)) {
            return lootcontext$entitytarget;
         }
      }

      throw new IllegalArgumentException("Invalid entity target " + type);
   }

   // $FF: synthetic method
   static String access$000(bhf x0) {
      return x0.targetType;
   }
}
