package neo;

public enum bbb {
   NOT_SET(-1, "", ""),
   SURVIVAL(0, "survival", "s"),
   CREATIVE(1, "creative", "c"),
   ADVENTURE(2, "adventure", "a"),
   SPECTATOR(3, "spectator", "sp");

   final int id;
   final String name;
   final String shortName;

   private bbb(int idIn, String nameIn, String shortNameIn) {
      this.id = idIn;
      this.name = nameIn;
      this.shortName = shortNameIn;
   }

   public int getID() {
      return this.id;
   }

   public String getName() {
      return this.name;
   }

   public void configurePlayerCapabilities(ML capabilities) {
      if (this == CREATIVE) {
         capabilities.allowFlying = true;
         capabilities.isCreativeMode = true;
         capabilities.disableDamage = true;
      } else if (this == SPECTATOR) {
         capabilities.allowFlying = true;
         capabilities.isCreativeMode = false;
         capabilities.disableDamage = true;
         capabilities.isFlying = true;
      } else {
         capabilities.allowFlying = false;
         capabilities.isCreativeMode = false;
         capabilities.disableDamage = false;
         capabilities.isFlying = false;
      }

      capabilities.allowEdit = !this.hasLimitedInteractions();
   }

   public boolean hasLimitedInteractions() {
      return this == ADVENTURE || this == SPECTATOR;
   }

   public boolean isCreative() {
      return this == CREATIVE;
   }

   public boolean isSurvivalOrAdventure() {
      return this == SURVIVAL || this == ADVENTURE;
   }

   public static bbb getByID(int idIn) {
      return parseGameTypeWithDefault(idIn, SURVIVAL);
   }

   public static bbb parseGameTypeWithDefault(int targetId, bbb fallback) {
      bbb[] var2 = values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         bbb gametype = var2[var4];
         if (gametype.id == targetId) {
            return gametype;
         }
      }

      return fallback;
   }

   public static bbb getByName(String gamemodeName) {
      return parseGameTypeWithDefault(gamemodeName, SURVIVAL);
   }

   public static bbb parseGameTypeWithDefault(String targetName, bbb fallback) {
      bbb[] var2 = values();
      int var3 = var2.length;

      for(int var4 = 0; var4 < var3; ++var4) {
         bbb gametype = var2[var4];
         if (gametype.name.equals(targetName) || gametype.shortName.equals(targetName)) {
            return gametype;
         }
      }

      return fallback;
   }
}
